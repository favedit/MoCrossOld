#include <sys/types.h>
#include <sys/stat.h>
#ifdef _MO_LINUX
#include <dirent.h>
#endif // _MO_LINUX
#include "MoFlFile.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造日志构造器对象。</T>
//
// @return 日志写入器对象的指针。
//============================================================
 FBufferedLoggerWriter::FBufferedLoggerWriter(){
   _code  = 0;
   _fileCount = 0;
   _length = 0;
   _capacity = 0;
   _pBuffer = MO_CREATE(FBytes);
   _pBuffer->EnsureSize(1024 * 1024 * 4);
   _pStream = MO_CREATE(FFileStream);
   _today = RDateTime::Current() / (TDateTime)MO_DATA_DAY_US;
   _forceToFlush = EFalse;
   _flushLength = 0;
   _flushTimeout = 10000000;
   _lastFlushDate = 0;
}

//============================================================
// <T>析构日志构造器对象。</T>
//============================================================
FBufferedLoggerWriter::~FBufferedLoggerWriter(){
   MO_DELETE(_pBuffer);
   MO_DELETE(_pStream);
}

//============================================================
// <T>设置日志写入器的标号。</T>
//
// @param TInt code 要设置的编号。
//============================================================
void FBufferedLoggerWriter::SetCode(TInt code){
   _code = code;
}

//============================================================
// <T>设置日志写入器的对应文件名。</T>
//
// @param TCharC* pName 写入日志的文件名。
//============================================================
void FBufferedLoggerWriter::SetName(TCharC* pName){
   _name = pName;
}

//============================================================
// <T>设置日志文件目录。</T>
//
// @param TCharC* pPath 日志文件存放的目录。
//============================================================
void FBufferedLoggerWriter::SetPath(TCharC* pPath){
   _path = pPath;
}

//============================================================
// <T>设置日志文件的最大大小。</T>
//
// @param TSize capacity 文件的大小。
//============================================================
void FBufferedLoggerWriter::SetCapacity(TSize capacity){
   _capacity = capacity;
}

//============================================================
// <T>是否强制刷新到文件。</T>
//
// @param TBool isForceFlush 是否强制刷新。
//============================================================
void FBufferedLoggerWriter::SetForceToFlush(TBool isForceFlush){
   _forceToFlush = isForceFlush;
}

//============================================================
// <T>生成文件名称样例。</T>
//
// @param fileName 文件名称
// @return 处理结果
//============================================================
TBool FBufferedLoggerWriter::MakeFilePattern(TFsFileName& fileName){
   // 格式化日期
   TFsName datetime;
   RDateTime::ToString(datetime.Memory(), datetime.Size(), RDateTime::Current(), "%y%m%d");
   datetime.Fix();
   // 生成文件名称
   fileName.Append(_path);
   fileName.Append("/");
   fileName.Append(_name);
   fileName.Append("_");
   fileName.Append(datetime);
   fileName.Append("_");
   return ETrue;
}

//============================================================
// <T>是否强制刷新到文件。</T>
//
// @param TBool isForceFlush 是否强制刷新。
//============================================================
TInt FBufferedLoggerWriter::CalculateCount(TCharC* pFilePattern, TInt baseCount){
   TFsFileName fileName;
   TInt count = baseCount;
   for(; count < 9999; count++){
      fileName.Assign(pFilePattern);
      fileName.AppendFormat("%04d.log", count);
      if(RFile::ExistFile(fileName)){
         TInt fileLength = RFile::FileSize(fileName);
         if((fileLength >= 0) && (fileLength <= _capacity)){
            return count;
         }
      }else{
         break;
      }
   }
   return count;
}

//============================================================
// <T>打开日志写入器。</T>
//
// @return 日否打开成功。
//============================================================
TResult FBufferedLoggerWriter::Open(){
   TResult result = ESuccess;
   // 创建文件目录
   if(!RFile::ExistPath(_path)){
      RFile::CreateFullDirectory((TCharC*)_path);
   }
#ifdef _MO_LINUX
   // 获得文件名称
   TFsFileName fileName;
   MakeFilePattern(fileName);
   _fileCount = CalculateCount(fileName, 1);
   fileName.AppendFormat("%04d.log", _fileCount);
   // 检查文件是否存在
   if(RFile::ExistFile(fileName)){
      result = _pStream->OpenAppend(fileName);
      _length = _pStream->Length();
   }else{
      result = _pStream->Create(fileName);
      _length = 0;
   }
#endif
   return result;
}

//============================================================
// <T>创建一个新的日志文件。</T>
//
// @return 是否创建成功。
//============================================================
TResult FBufferedLoggerWriter::Create(){
   TResult result = ESuccess;
   // 关闭上个文件
   _pStream->Close();
   // 获得文件名称
   TFsFileName fileName;
   MakeFilePattern(fileName);
   _fileCount = CalculateCount(fileName, _fileCount);
   fileName.AppendFormat("%04d.log", _fileCount);
   // 检查文件是否存在
   if(RFile::ExistFile(fileName)){
      result = _pStream->OpenAppend(fileName);
   }else{
      result = _pStream->Create(fileName);
   }
   _length = 0;
   return result;
}

//============================================================
// <T>获得日志写入器的编号。</T>
//
// @return 日志写入器的编号。
//============================================================
TInt FBufferedLoggerWriter::Code(){
   return _code;
}

//============================================================
// <T>将一个消息记录到日志中。</T>
//
// @param TCharC* pMessage 消息的内容。
// @param TSize length 消息的长度。
// @return 是否记录成功。
//============================================================
TResult FBufferedLoggerWriter::Write(TDateTime time, TCharC* pMessage, TInt length){
   _section.Enter();
   _pBuffer->Append((TByteC*)pMessage, sizeof(TChar) * length);
   _pBuffer->Append((TByteC*)"\n", sizeof(TChar));
   _section.Leave();
   return ESuccess;
}

//============================================================
// <T>关闭日志写入器。</T>
//
// @return 关闭是否成功。
//============================================================
TResult FBufferedLoggerWriter::Close(){
   TResult result = ESuccess;
   _section.Enter();
   result = _pStream->Close();
   _section.Leave();
   return result;
}

//============================================================
// <T>关闭日志写入器。</T>
//
// @return 关闭是否成功。
//============================================================
TResult FBufferedLoggerWriter::Refresh(){
   TResult result = ESuccess;
   TDateTime current = RDateTime::Current();
   // 写入数据
   _section.Enter();
   TInt length = _pBuffer->Length();
   if(length > 0){
      TInt lengthWrite = _pStream->Write(_pBuffer->MemoryC(), length);
      if(lengthWrite == length){
         _flushLength += length;
         result = ETrue;
      }
      _pBuffer->Clear();
   }
   //............................................................
   // 是否要强制刷新
   if(result && _forceToFlush){
      _pStream->Flush();
   }
   // 是否超时刷新
   TTimeSpan flushSpan = current - _lastFlushDate;
   if((_flushLength > 0) && (flushSpan > _flushTimeout)){
      _pStream->Flush();
      _flushLength = 0;
      _flushTimeout = current;
   }
   //............................................................
   // 检查文件是否需要切换
   TDateTime today = current / (TDateTime)MO_DATA_DAY_US;
   _length += length;
   if((_length > _capacity) || (_today != today)){
      _pStream->Flush();
      Create();
   }
   _section.Leave();
   return result;
}

//============================================================
// <T>刷新数据。</T>
//
// @return 处理结果
//============================================================
TResult FBufferedLoggerWriter::Flush(){
   TResult result = Refresh();
   _section.Enter();
   result = _pStream->Flush();
   _section.Leave();
   return result;
}

MO_NAMESPACE_END
