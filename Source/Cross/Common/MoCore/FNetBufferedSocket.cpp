#include "MoCrNetEpoll.h"
#include "MoCrNetPipe.h"
#include "MoCrNetSocket.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造共享网络对象。</T>
//============================================================
FNetBufferedSocket::FNetBufferedSocket(){
   // 设置信息
   RType<SNetBufferedSocket>::Clear(&_info);
   _info.receiveBufferSize = MO_SVC_SOCKET_RECEIVE;
   _info.sendBufferSize = MO_SVC_SOCKET_SEND;
   _processing = EFalse;
   // 设置缓冲
   MO_CLEAR(_pReceivePool);
   MO_CLEAR(_pSendPool);
   // 创建管道
   _pInputPipe = MO_CREATE(FNetBufferedPipe);
   _pInputPipe->SetLockCd(EStreamLock_Unlock);
   _pOutputPipe = MO_CREATE(FNetBufferedPipe);
   _pOutputPipe->SetLockCd(EStreamLock_Unlock);
}

//============================================================
// <T>析构共享网络对象。</T>
//============================================================
FNetBufferedSocket::~FNetBufferedSocket(){
   MO_DELETE(_pInputPipe);
   MO_DELETE(_pOutputPipe);
}

//============================================================
// <T>获得套接信息对象字指针。</T>
//
// @return 套接字信息对象的指针的指针
//============================================================
SNetSocketInfo* FNetBufferedSocket::Info(){
   return &_info;
}

//============================================================
// <T>设置套接字的信息。</T>
//
// @param pInfo 套接字的信息的结构体指针
//============================================================
void FNetBufferedSocket::SetInfo(SNetSocketInfo* pInfo){
   MO_ASSERT(pInfo);
   memcpy(&_info, pInfo, sizeof(SNetSocketInfo));
   _host = pInfo->host;
}

//============================================================
// <T>关联管道分块收集器。</T>
//
// @param pAllocator 管道分块收集器
//============================================================
void FNetBufferedSocket::LinkAllocator(TInt capacity, FBufferedPipeBlockAllocator* pAllocator){
   // 设置输入缓冲
   _pInputPipe->Pool()->SetCapacity(capacity);
   _pInputPipe->SetAllocator(pAllocator);
   // 设置输出缓冲
   _pOutputPipe->Pool()->SetCapacity(capacity);
   _pOutputPipe->SetAllocator(pAllocator);
}

//============================================================
// <T>读取网络端口数据到当前输入数据管道。</T>
//
// @return 读取数据总数,如果返回-1标识用户链接已经关闭。
//============================================================
TInt FNetBufferedSocket::DoBufferReceive(){
   // 检查是否链接
   if(!_isConnected){
      return 0;
   }
   // 循环接收数据
   TInt result = 0;
   while(ETrue){
      // 读取数据
      TByte buffer[MO_SVC_SOCKET_RECEIVE];
      TInt length = Receive(buffer, MO_SVC_SOCKET_RECEIVE);
      if(0 == length){
         // 客户端已断开
         result = -1;
         break;
      }else if(length > 0){
         // 接收数据包
         MO_DEBUG(TC("Receive socket data. (handle=%d, host=%s:%d, length=%d)"),
               _info.handle, _info.host, _info.port, length);
         // TChar dump[MO_DUMP_MAXLENGTH];
         // MO_DEBUG("Receive socket data. (handle=%d, host=%s:%d, length=%d)\n%s",
         //       _gInfo->handle, _gInfo->host, _gInfo->port, length,
         //       RByte::Dump(buffer, length, dump, MO_DUMP_MAXLENGTH));
         // 写入链接的输入管道
         TInt lengthWrite;
         if(EStreamResult_Success != _pInputPipe->Write(buffer, length, &lengthWrite)){
            MO_ERROR(TC("Socket input queue is full. (length=%d, remain=%d)"), length, _pInputPipe->Length());
         }
         result += length;
         break;
      }else{
         // 错误处理
         TInt code = errno;
         if((EINTR == code) || (EAGAIN == code) || (EWOULDBLOCK == code)){
            // 正常跳出，下次继续接收
            break;
         }else if((ENOENT == code) || (ENOTSOCK == code) || (ECONNRESET == code)){
            // 异常跳出，关闭链接
            result = -1;
            break;
         }else{
            // 例外情况，关闭链接
            MO_ERROR(TC("Receive socket data unknown error. (handle=%d, host=%s:%d, length=%d, code=%d)"),
                  _info.handle, _info.host, _info.port, length, code);
            result = -1;
            break;
         }
      }
   }
   return result;
}

//============================================================
// <T>从当前链接的输出管道中读取数据，发送给指定网络端口。</T>
//
// @return 读取数据总数,如果返回-1标识用户链接已经关闭。
//============================================================
TInt FNetBufferedSocket::DoBufferSend(){
   // 检查是否链接
   if(!_isConnected){
      return 0;
   }
   // 检查是否存在要发送的数据
   if(0 == _pOutputPipe->Length()){
      return 0;
   }
   // 循环发送数据
   TInt result = 0;
   while(ETrue){
      // 获得要发送的数据
      TInt lengthPeek = 0;
      TByte buffer[MO_SVC_SOCKET_SEND];
      if(EStreamResult_Success != _pOutputPipe->Peek(buffer, MO_SVC_SOCKET_SEND, &lengthPeek)){
         break;
      }
      if(0 == lengthPeek){
         break;
      }
      // 发送数据
      TInt length = Send(buffer, lengthPeek);
      if(0 == length){
         // 客户端已断开
         result = -1;
         break;
      }else if(length > 0){
         MO_DEBUG(TC("Send socket buffer data. (handle=%d, host=%s:%d, peekLength=%d, sendLength=%d)"),
               _info.handle, _info.host, _info.port, lengthPeek, length);
         // 将已经发送的数据部分移出发送管道
         TInt lengthSend = 0;
         if(EStreamResult_Success != _pOutputPipe->Read(buffer, length, &lengthSend)){
            MO_ERROR(TC("Socket send buffer failure. (capacity=%d, length=%d)"), _pOutputPipe->Length(), length);
            result = -1;
            break;
         }
         result += length;
         // 如果没有全部发送完成则正常离开发送循环
         if(length != lengthPeek){
            break;
         }
      }else{
         // 错误处理
         TInt code = errno;
         if((EINTR == code) || (EAGAIN == code) || (EWOULDBLOCK == code)){
            // 发送池已满，可以下次重发
            break;
         }else if((ENOTSOCK == code) || (ENOENT == code) || (EPIPE == code) ||
               (ECONNRESET == code) || (EDESTADDRREQ == code) || (EACCES == code)){
            // 对方已经关闭，当前链接需要关闭
            result = -1;
            break;
         }else{
            // 例外情况，关闭链接
            MO_ERROR(TC("Send socket data unknown error. (handle=%d, host=%s:%d, length=%d, code=%d)"),
                  _info.handle, _info.host, _info.port, length, code);
            result = -1;
            break;
         }
      }
   }
   return result;
}

//============================================================
// <T>更新网络链接。</T>
//============================================================
TBool FNetBufferedSocket::Update(){
   _info.updateDateTime = RDateTime::Current();
   return ETrue;
}

//============================================================
// <T>开始网络链接。</T>
//============================================================
TBool FNetBufferedSocket::Startup(){
   // 检查是否启动
   if(_processing){
      return EFalse;
   }
   // 设置内容
   TDateTime tick = RDateTime::Current();
   _isConnected = ETrue;
   _info.createDateTime = tick;
   _info.updateDateTime = tick;
   _info.receiveSerial = 0;
   _info.receiveDateTime = tick;
   _info.receiveTick = 0;
   _info.sendSerial = 0;
   _info.sendDateTime = tick;
   // 设置缓冲大小
   SetReceiveBufferSize(_info.receiveBufferSize);
   SetSendBufferSize(_info.sendBufferSize);
   // 增加到池中
#ifdef _MO_LINUX
#ifndef __CYGWIN__
   if(NULL != _pReceivePool){
      _pReceivePool->Add(this, EPOLLIN | EPOLLHUP | EPOLLERR);
   }
   if(NULL != _pSendPool){
      _pSendPool->Add(this, EPOLLOUT);
   }
#endif
#endif
   _processing = ETrue;
   return ETrue;
}

//============================================================
// <T>关闭网络链接。</T>
//============================================================
TBool FNetBufferedSocket::Shutdown(){
   // 检查是否启动
   if(!_processing){
      return EFalse;
   }
   // 从池中删除
   if(NULL != _pReceivePool){
      _pReceivePool->Remove(this, 0);
      MO_CLEAR(_pReceivePool);
   }
   if(NULL != _pSendPool){
      _pSendPool->Remove(this, 0);
      MO_CLEAR(_pSendPool);
   }
   // 重置信息
   _pInputPipe->Reset();
   _pOutputPipe->Reset();
   // 关闭对象
   Close();
   _processing = EFalse;
   return ETrue;
}

MO_NAMESPACE_END
