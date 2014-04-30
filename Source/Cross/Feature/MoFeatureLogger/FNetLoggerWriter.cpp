#include <sys/types.h>
#include <sys/stat.h>
#ifdef _MO_LINUX
#include <dirent.h>
#endif // _MO_LINUX
#include "MoFlNet.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FNetLoggerWriter, FInstance);

//============================================================
// <T>构造日志构造器对象。</T>
//
// @return 日志写入器对象的指针。
//============================================================
FNetLoggerWriter::FNetLoggerWriter(){
   _code  = 0;
   _pBuffer = MO_CREATE(FByteStream);
   _pBuffer->EnsureSize(1024 * 1024);
   _pData = MO_CREATE(FBytes);
   _pSocket = MO_CREATE(FNetClientSocket);
}

//============================================================
// <T>析构日志构造器对象。</T>
//============================================================
FNetLoggerWriter::~FNetLoggerWriter(){
   MO_DELETE(_pBuffer);
   MO_DELETE(_pData);
   MO_DELETE(_pSocket);
}

//============================================================
// <T>打开日志写入器。</T>
//
// @return 日否打开成功。
//============================================================
TResult FNetLoggerWriter::Open(){
   _pSocket->Connect();
   return ESuccess;
}

//============================================================
// <T>创建一个新的日志文件。</T>
//
// @return 是否创建成功。
//============================================================
TResult FNetLoggerWriter::Create(){
   return ESuccess;
}

//============================================================
// <T>获得日志写入器的编号。</T>
//
// @return 日志写入器的编号。
//============================================================
TInt FNetLoggerWriter::Code(){
   return _code;
}

//============================================================
// <T>将一个消息记录到日志中。</T>
//
// @param TCharC* pMessage 消息的内容。
// @param TSize length 消息的长度。
// @return 是否记录成功。
//============================================================
TResult FNetLoggerWriter::Write(TDateTime time, TCharC* pMessage, TInt length){
   // 设置消息头
   SNetLoggerInfo info;
   info.length = sizeof(SNetLoggerInfo) + sizeof(TChar) * length;
   info.type = 0;
   info.level = 0;
   info.tick = time;
   // 输出内容
   _section.Enter();
   _pBuffer->Write(&info, sizeof(SNetLoggerInfo));
   _pBuffer->Write(pMessage, sizeof(TChar) * length);
   // 发送信息
   TInt sended = _pSocket->Send(_pBuffer->Memory(), _pBuffer->Length());
   if(sended > 0){
      _pBuffer->DeleteSegment(0, sended);
      _pBuffer->Skip(-sended);
   }
   _section.Leave();
   return ESuccess;
}

//============================================================
// <T>关闭日志写入器。</T>
//
// @return 关闭是否成功。
//============================================================
TResult FNetLoggerWriter::Close(){
   TResult result = ESuccess;
   _section.Enter();
   _section.Leave();
   return result;
}

//============================================================
// <T>关闭日志写入器。</T>
//
// @return 关闭是否成功。
//============================================================
TResult FNetLoggerWriter::Refresh(){
   TResult resultCd = ESuccess;
   //TDateTime current = RDateTime::Current();
   //// 写入数据
   //_section.Enter();
   //TInt length = _pBuffer->Length();
   //if(length > 0){
   //   TInt lengthWrite = _pStream->Write(_pBuffer->MemoryC(), length);
   //   if(lengthWrite == length){
   //      _flushLength += length;
   //      result = ETrue;
   //   }
   //   _pBuffer->Clear();
   //}
   ////............................................................
   //// 是否要强制刷新
   //if(result && _forceToFlush){
   //   _pStream->Flush();
   //}
   //// 是否超时刷新
   //TTimeSpan flushSpan = current - _lastFlushDate;
   //if((_flushLength > 0) && (flushSpan > _flushTimeout)){
   //   _pStream->Flush();
   //   _flushLength = 0;
   //   _flushTimeout = current;
   //}
   ////............................................................
   //// 检查文件是否需要切换
   //TDateTime today = current / (TDateTime)MO_DATA_DAY_US;
   //_length += length;
   //if((_length > _capacity) || (_today != today)){
   //   _pStream->Flush();
   //   Create();
   //}
   //_section.Leave();
   return resultCd;
}

//============================================================
// <T>刷新数据。</T>
//
// @return 处理结果
//============================================================
TResult FNetLoggerWriter::Flush(){
   TResult resultCd = Refresh();
   //_section.Enter();
   //result = _pStream->Flush();
   //_section.Leave();
   return resultCd;
}

MO_NAMESPACE_END
