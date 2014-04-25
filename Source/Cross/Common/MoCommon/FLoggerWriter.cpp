#include <sys/types.h>
#include <sys/stat.h>
#ifdef _MO_LINUX
#include <dirent.h>
#endif
#include "MoCmFormat.h"
#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造日志构造器对象。</T>
//
// @return 日志写入器对象的指针。
//============================================================
 FLoggerWriter::FLoggerWriter(){
   _code  = 0;
   _fileCount = 0;
   _length = 0;
   _capacity = 0;
   _pStream = MO_CREATE(FFileStream);
   _today = RDateTime::Current() / (TDateTime)MO_DATA_DAY_US;
   _forceToFlush = EFalse;
}

//============================================================
// <T>析构日志构造器对象。</T>
//============================================================
FLoggerWriter::~FLoggerWriter(){
   MO_DELETE(_pStream);
}

//============================================================
// <T>获得日志文件的编号。</T>
//
// @param TCharC* pName  日志文件的文件名。
// @return 日志文件的编号。
//============================================================
TInt FLoggerWriter::GetCountFromName(TCharC* pName){
   TInt count = -1;
   TFsName name = pName;
   TInt dateStart = name.IndexOf('_');
   if(ENotFound == dateStart){
      return count;
   }
   dateStart++;
   TInt dateEnd = name.IndexOf('_', dateStart);
   if(ENotFound == dateEnd){
      return count;
   }
   TFsDateTime dateString = name.SubStrC(dateStart, dateEnd);
   TFsDateTime fsDate = _today * (TDateTime)MO_DATA_DAY_US;
   fsDate.Format(TC("%y%m%d"));
   TCharC* pTodayStr = (TCharC*)fsDate;
   TCharC* pDateStr = (TCharC*)dateString;
   if(!RString::Equals(pTodayStr, pDateStr)){
      return count;
   }
   TInt offset1 = name.LastIndexOf('_');
   TInt offset2 = name.LastIndexOf('.');
   if((ENotFound != offset1) && (offset2 > offset1)){
      TFsName mid = name.SubStrC(offset1 + 1, offset2);
      count = RInt::Parse(mid);
   }
   return count;
}
//============================================================
// <T>设置日志写入器的标号。</T>
//
// @param TInt code 要设置的编号。
//============================================================
void FLoggerWriter::SetCode(TInt code){
   _code = code;
}

//============================================================
// <T>设置日志写入器的对应文件名。</T>
//
// @param TCharC* pName 写入日志的文件名。
//============================================================
void FLoggerWriter::SetName(TCharC* pName){
   _name = pName;
}

//============================================================
// <T>设置日志文件目录。</T>
//
// @param TCharC* pPath 日志文件存放的目录。
//============================================================
void FLoggerWriter::SetPath(TCharC* pPath){
   _path = pPath;
}

//============================================================
// <T>设置日志文件的最大大小。</T>
//
// @param TSize capacity 文件的大小。
//============================================================
void FLoggerWriter::SetCapacity(TSize capacity){
   _capacity = capacity;
}

//============================================================
// <T>是否强制刷新到文件。</T>
//
// @param TBool isForceFlush 是否强制刷新。
//============================================================
void FLoggerWriter::SetForceToFlush(TBool isForceFlush){
   _forceToFlush = isForceFlush;
}

//============================================================
// <T>获得日志写入器的编号。</T>
//
// @return 日志写入器的编号。
//============================================================
TInt FLoggerWriter::Code(){
   return _code;
}

//============================================================
// <T>打开日志写入器。</T>
//
// @return 日否打开成功。
//============================================================
TResult FLoggerWriter::Open(){
   _locker.Enter();
   if(!RFile::ExistPath(_path)){
      RFile::CreateFullDirectory((TCharC*)_path);
   }
   TResult resultCd = ESuccess;
#ifdef _MO_LINUX
   _fileCount = 1;
   TFsDateTime fsDate = RDateTime::Current();
   TFsName pathPrefix;
   pathPrefix.Append(_path);
   pathPrefix.Append("/");
   pathPrefix.Append(_name);
   pathPrefix.Append("_");
   pathPrefix.Append(fsDate.Format("%y%m%d"));
   pathPrefix.Append("_");
   TFsName fullName;
   fullName.Append(pathPrefix);
   fullName.AppendFormat("%04d.log", _fileCount);
   // 看有无000max
   if(RFile::ExistFile(fullName)){
      struct dirent* pDir;
      DIR* pDirBase = opendir(_path);
      while(NULL != (pDir = readdir(pDirBase))){
         //if((strcmp(pDir->d_name, ".") == 0) || (strcmp(pDir->d_name,".."))) {
           // continue;
         //
         TInt count = GetCountFromName((TCharC*)pDir->d_name);
         if(count > _fileCount){
            _fileCount = count;
         }
      }
      closedir(pDirBase);
      fullName.Clear();
      fullName.Append(pathPrefix);
      fullName.AppendFormat("%04d.log", _fileCount);
      struct stat st;
      if(stat((TCharC*)fullName,&st)){
         MO_ERROR("Stat file:%s failed, errno:%d.",(TCharC*)fullName , errno);
         return EFalse;
      }else{
         if((TSize)st.st_size >= _capacity){ //写满
            _fileCount++;
            fullName.Clear();
            fullName.Append(pathPrefix);
            fullName.AppendFormat("%04d.log", _fileCount);
            ret = _pStream->Create((TCharC*)fullName);
         }else{
            ret = _pStream->OpenAppend((TCharC*)fullName);
            _length = st.st_size;
         }
      }
   }else{
      ret = _pStream->Create((TCharC*)fullName);
   }
#endif
   _locker.Leave();
   return resultCd;
}

//============================================================
// <T>创建一个新的日志文件。</T>
//
// @return 是否创建成功。
//============================================================
TResult FLoggerWriter::Create(){
   _locker.Enter();
   //TInt count = GetCountFromName(_pStream->FileName());
   _fileCount++;
   TDateTime curDay = RDateTime::Current() / (TDateTime)MO_DATA_DAY_US;
   if(_today < curDay){
      _fileCount = 1;
      _today = curDay;
   }
   TDateTime currentTime = RDateTime::Current();
   //TChar buffer[256];
   //RDateTime::ToString(buffer, 256, currentTime, "%y%m%d");
   //_today = currentTime / (TDateTime)MO_DATA_DAY_US;
   TFsDateTime fsDate = currentTime;
   TFsName fullName;
   fullName.Append(_path);
   fullName.Append('/');
   fullName.Append(_name);
   fullName.Append('_');
   fullName.Append(fsDate.Format(TC("%y%m%d")));
   //fullName.Append(buffer);
   fullName.Append('_');
   fullName.AppendFormat(TC("%04d.log"), _fileCount);
   _pStream->Close();
   _pStream->Open((TCharC*)fullName);
   _length = 0;
   _locker.Leave();
   return ESuccess;
}

//============================================================
// <T>将一个消息记录到日志中。</T>
//
// @param TCharC* pMessage 消息的内容。
// @param TSize length 消息的长度。
// @return 是否记录成功。
//============================================================
TResult FLoggerWriter::Write(TDateTime time, TCharC* pMessage, TInt length){
   time /= (TDateTime)MO_DATA_DAY_US;
   // 检查文件是否需要切换
   _length += length;
   if((_length > _capacity) || (_today < time)){
      Create();
   }
   // 写入日志
   TResult resultCd = EFailure;
   _locker.Enter();
   // 写入数据
   TInt lengthWrite = _pStream->Write(pMessage, length);
   if(lengthWrite == length){
      resultCd = ESuccess;
   }
   // 写入换行
   if(resultCd == ESuccess){
      lengthWrite = _pStream->Write(TC("\n"), sizeof(TChar));
      if(lengthWrite == sizeof(TChar)){
         resultCd = ESuccess;
      }
   }
   // 是否要强制刷新
   if(resultCd){
      if(_forceToFlush){
         _pStream->Flush();
      }
   }
   _locker.Leave();
   return resultCd;
}

//============================================================
// <T>刷新数据。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerWriter::Flush(){
   TResult result = ESuccess;
   _locker.Enter();
   result = _pStream->Flush();
   _locker.Leave();
   return result;
}

//============================================================
// <T>关闭日志写入器。</T>
//
// @return 关闭是否成功。
//============================================================
TResult FLoggerWriter::Close(){
   TResult result = ESuccess;
   _locker.Enter();
   result = _pStream->Close();
   _locker.Leave();
   return result;
}

//============================================================
// <T>关闭日志写入器。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerWriter::Refresh(){
   return ESuccess;
}

MO_NAMESPACE_END
