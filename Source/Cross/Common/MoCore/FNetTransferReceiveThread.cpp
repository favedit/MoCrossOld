#ifdef _MO_LINUX
#ifndef __CYGWIN__
#include <sys/syscall.h>
#endif
#include <sys/socket.h>
#include <arpa/inet.h>
#endif
#include "MoCrNetEpoll.h"
#include "MoCrNetPipe.h"
#include "MoCrNetTransfer.h"

//============================================================
#define MO_NET_RECEIVE_DATA_STR "<policy-file-request/>\0";
#define MO_NET_SEND_DATA_STR    "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>\0";
#define MO_NET_RECEIVE_TGW_STR  "tgw_l7_forward"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络数据接收线程实例。</T>
//
// @return 线程实例
//============================================================
FNetTransferReceiveThread::FNetTransferReceiveThread(){
   _code = TC("STR");
   _name = TC("Thread.Transfer.Receive");
   MO_CLEAR(_pService);
   MO_CLEAR(_pCommander);
   MO_CLEAR(_pInputQueue);
   _dataCheck = ETrue;
   _dataCompress = ETrue;
   _dataMask = ETrue;
   _receiveTotal = 0;
   _threadId = 0;
   _pCatcher = MO_CREATE(FNetTransferCatcher);
}

//============================================================
// <T>析构网络数据接收线程实例。</T>
//============================================================
FNetTransferReceiveThread::~FNetTransferReceiveThread(){
   MO_DELETE(_pCatcher);
}

//============================================================
// <T>设置传输服务对象。</T>
//
// @param pService 传输服务对象
//============================================================
void FNetTransferReceiveThread::SetService(FNetTransferService* pService){
   MO_ASSERT(pService);
   _pService = pService;
   _pCommander = pService->Commander();
}

//============================================================
void FNetTransferReceiveThread::NotifyWake(){
}

//============================================================
#ifdef _MO_LINUX
pid_t FNetTransferReceiveThread::ThreadId(){
   return _threadId;
}
#endif

//============================================================
// <T>接收一个消息到主输入管道。</T>
//
// @param pSocket 网络链接
// @param pBuffer 数据指针
// @param length 数据长度
//============================================================
TInt FNetTransferReceiveThread::ReceiveMessage(FNetBufferedSocket* pSocket, TByteC* pBuffer, TInt size){
   MO_ASSERT(pSocket);
   MO_ASSERT(pBuffer);
   // 获得网络端口信息
   TCharC* pHost = pSocket->Host();
   TUint32 ip  = pSocket->Ip();
   TUint16 port = pSocket->Port();
   TSocket handle = pSocket->Handle();
   TUint16 index = pSocket->Index();
   TUint16 serial = pSocket->Serial();
   TNetSerial receiveSerial = pSocket->ReceiveSerial();
   //TNetTick receiveTick = pSocket->ReceiveTick();
   // 获得消息
   TInt length = 0;
   TNetMessageBuffer message;
   TBool unserializeResult = message.Uncompress(pBuffer, size, &length, _dataMask, _dataCheck);
   //if(_dataCompress){
   //   unserializeResult = message.Uncompress(pBuffer, size, &length);
   //}else{
   //   if(_dataMask){
   //      unserializeResult = message.UnserializeMask(pBuffer, size, &length);
   //   }else{
   //      if(_dataCheck){
   //         unserializeResult = message.Unserialize(pBuffer, size, &length);
   //      }else{
   //         unserializeResult = message.UnserializeUncheck(pBuffer, size, &length);
   //      }
   //   }
   //}
   if(!unserializeResult){
      TChar dump[MO_FS_DUMP_LENGTH];
      TInt dumpSize = MO_LIB_MIN(size, MO_NETMESSAGE_BYTEDUMP_MAXLENGTH);
      MO_ERROR(TC("Receive message unserial failure. (host=%s:%d, handle=%d, index=%d:%d, size=%d)\n%s"),
            pHost, port, handle, index, serial, size,
            RByte::Dump(pBuffer, dumpSize, dump, MO_FS_DUMP_LENGTH));
      _pCommander->SendInvalidUnserialNotify(pSocket);
      return EFailure;
   }
   // 获得消息信息
   TInt code = message.MessageHead().Code();
   TNetMessageInfo* pMessageInfo = message.MessageInfo();
   if(NULL == pMessageInfo){
      TChar dump[MO_FS_DUMP_LENGTH];
      TInt dumpSize = MO_LIB_MIN(size, MO_NETMESSAGE_BYTEDUMP_MAXLENGTH);
      MO_ERROR(TC("Receive message unknown failure. (host=%s:%d, handle=%d, index=%d:%d, code=%d)\n%s"),
            pHost, port, handle, index, serial, code,
            RByte::Dump(pBuffer, dumpSize, dump, MO_FS_DUMP_LENGTH));
      _pCommander->SendInvalidUnknownNotify(pSocket);
      return EFailure;
   }
   // 检查消息版本
   TNetVersion version = message.MessageHead().Version();
   if(pMessageInfo->Version() != version){
      TChar dump[MO_FS_DUMP_LENGTH];
      TInt dumpSize = MO_LIB_MIN(size, MO_NETMESSAGE_BYTEDUMP_MAXLENGTH);
      MO_ERROR(TC("Receive message version failure. (name=%s, receive_version=0x%04X, local_version=0x%04X)\n%s"),
            pMessageInfo->Name(), version, pMessageInfo->Version(),
            RByte::Dump(pBuffer, dumpSize, dump, MO_FS_DUMP_LENGTH));
      _pCommander->SendInvalidVersionNotify(pSocket);
      return EFailure;
   }
   // 检查消息序列
   if(_dataCheck){
      TNetSerial messageSerial = message.MessageHead().Serial();
      if(messageSerial <= receiveSerial){
         TChar dump[MO_FS_DUMP_LENGTH];
         message.Dump(dump, MO_FS_DUMP_LENGTH);
         TChar format[MO_FS_DUMP_LENGTH];
         message.DumpMemory(format, MO_FS_DUMP_LENGTH);
         MO_ERROR(TC("Receive message serial failure. (name=%s, receive_serial=%d, socket_serial=%d)\n%s"),
               pMessageInfo->Name(), messageSerial, receiveSerial, dump, format);
         _pCommander->SendInvalidUnknownNotify(pSocket);
         return EFailure;
      }
      pSocket->SetReceiveSerial(messageSerial);
   }
   // 检查消息时刻 (TODO：暂时不检查)
   if(_dataCheck){
      TNetTick messageTick = message.MessageHead().Tick();
      //if(messageTick < receiveTick){
      //   TChar dump[MO_FS_DUMP_LENGTH];
      //   message.Dump(dump, MO_FS_DUMP_LENGTH);
      //   TChar format[MO_FS_DUMP_LENGTH];
      //   message.DumpMemory(format, MO_FS_DUMP_LENGTH);
      //   MO_ERROR("Receive message tick failure. (name=%s, receive_tick=%d, socket_tick=%d)\n%s",
      //         pMessageInfo->Name(), messageTick, receiveTick, dump, format);
      //   _pService->SendInvalidUnknownNotify(pSocket);
      //   return EFailure;
      //}
      pSocket->SetReceiveTick(messageTick);
   }
   // 重新传输消息头信息
   TNetTransfer transfer(&message);
   TNetTransferHead& head = transfer.TransferHead();
   head.SetHandle(handle);
   head.SetHost(ip, port);
   head.SetSocket(index, serial);
   // 序列化消息内容
   if(!_pInputQueue->PushTransfer(&transfer)){
      TChar dump[MO_FS_DUMP_LENGTH];
      message.Dump(dump, MO_FS_DUMP_LENGTH);
      TChar format[MO_FS_DUMP_LENGTH];
      message.DumpMemory(format, MO_FS_DUMP_LENGTH);
      MO_DEBUG(TC("Receive message full failure. (host=%s:%d, handle=%d:%d:%d)\n%s%s"),
            pHost, port, handle, index, serial, dump, format);
      _pCommander->SendInvalidFullNotify(pSocket);
      return EWarn;
   }
   MO_DEBUG(TC("Receive tcp message. (host=%s:%d, handle=%d:%d:%d, length=%d)"),
         pHost, port, handle, index, serial, length);
   return ESuccess;
}

//============================================================
// <T>接收一个消息邮寄包到主输入管道。</T>
//
// @param pSocket 网络链接
// @param pBuffer 数据指针
// @param length 数据长度
//============================================================
TInt FNetTransferReceiveThread::ReceiveRouter(FNetBufferedSocket* pSocket, TByteC* pBuffer, TInt size){
   MO_ASSERT(pSocket);
   MO_ASSERT(pBuffer);
   TCharC* pHost = pSocket->Host();
   TUint32 ip = pSocket->Ip();
   TUint16 port = pSocket->Port();
   TSocket handle = pSocket->Handle();
   TUint16 index = pSocket->Index();
   TUint16 serial = pSocket->Serial();
   // 反序列化路由内容
   TInt length;
   TNetRouter router;
   if(!router.Unserialize(pBuffer, size, &length)){
      TChar dump[MO_FS_DUMP_LENGTH];
      TInt dumpSize = MO_LIB_MIN(size, MO_NETMESSAGE_BYTEDUMP_MAXLENGTH);
      MO_ERROR(TC("Unserial router failure. (host=%s:%d, handle=%d, index=%d:%d, size=%d)\n%s"),
            pHost, port, handle, index, serial, size,
            RByte::Dump(pBuffer, dumpSize, dump, MO_FS_DUMP_LENGTH));
      _pCommander->SendInvalidUnserialNotify(pSocket);
      return EFailure;
   }
   // 获得传输信息
   TNetTransfer transfer(&router);
   TNetTransferHead& head = transfer.TransferHead();
   head.SetHandle(handle);
   head.SetHost(ip, port);
   head.SetSocket(index, serial);
   // 序列化消息内容
   if(!_pInputQueue->PushTransfer(&transfer)){
      TChar dump[MO_FS_DUMP_LENGTH];
      TChar format[MO_FS_DUMP_LENGTH];
      MO_ERROR(TC("Receive router failure. (host=%s:%d, handle=%d, index=%d:%d)\n%s%s"),
            pHost, port, handle, index, serial,
            transfer.Dump(dump, MO_FS_DUMP_LENGTH),
            transfer.DumpMemory(format, MO_FS_DUMP_LENGTH));
      _pCommander->SendInvalidFullNotify(pSocket);
      return EFailure;
   }
   MO_DEBUG(TC("Receive tcp router. (socket=0x%08X, host=%s:%d, handle=%d, index=%d:%d, length=%d)"),
         pSocket, pHost, port, handle, index, serial, length);
   return ESuccess;
}

//============================================================
// <T>接收一个网络端口的数据。</T>
//
// @param pSocket 网络链接
//============================================================
TInt FNetTransferReceiveThread::ReceiveFirstData(FNetBufferedSocket* pSocket){
   // 获得链接的输入管道
   INetPipe* pPipe = pSocket->InputPipe();
   MO_ASSERT(pPipe);
   TInt lengthPeek = 0;
   TByte buffer[MO_FS_TEXT_LENGTH];
   RBytes::Clear(buffer, MO_FS_TEXT_LENGTH);
   if(EStreamResult_Success == pPipe->Peek(buffer, MO_FS_TEXT_LENGTH, &lengthPeek)){
      TChar8C* pBuffer = (TChar8C*)buffer;
      // 测试TGW协议
      TInt tgwLength = RString8::Length(MO_NET_RECEIVE_TGW_STR);
      if(RTypes<TChar8>::Equals(pBuffer, MO_NET_RECEIVE_TGW_STR, tgwLength)){
         // 读取数据
         TInt lengthRead = 0;
         TInt receiveLength = RString8::Length(pBuffer) + 1;
         EStreamResult resultCd = pPipe->Read(buffer, receiveLength, &lengthRead);
         if(EStreamResult_Success != resultCd){
            TChar dump[MO_FS_DUMP_LENGTH];
            MO_ERROR(TC("Read pipe tgw data failure. (peek_length=%d, receive_length=%d, result_cd=%d)\n%s"),
                  lengthPeek, receiveLength, (TCharC*)buffer, resultCd,
                  RByte::Dump(buffer, lengthPeek, dump, MO_FS_DUMP_LENGTH));
         }
      }
      // 测试权限协议
      if(buffer[lengthPeek - 1] == 0){
         // 判断是否为权限信息
         TChar8C* pReceiveInfo = MO_NET_RECEIVE_DATA_STR;
         TInt receiveLength = RString8::Length(pReceiveInfo) + 1;
         if(RString8::Equals(pReceiveInfo, (TChar8C*)buffer)){
            // 读取权限数据
            TInt lengthRead = 0;
            if(EStreamResult_Success == pPipe->Read(buffer, receiveLength, &lengthRead)){
               MO_DEBUG(TC("Receive and send security data success. (socket=0x%08X, host=%s:%d, handle=%d)"),
                     pSocket, pSocket->Host(), pSocket->Port(), pSocket->Handle());
            }else{
               MO_ERROR(TC("Receive and read security data failue. (socket=0x%08X, host=%s:%d, handle=%d)"),
                     pSocket, pSocket->Host(), pSocket->Port(), pSocket->Handle());
            }
            // 发送权限数据
            INetPipe* pOutputPipe = pSocket->OutputPipe();
            TChar8C* pSendInfo = MO_NET_SEND_DATA_STR;
            TInt lengthWrite = 0;
            TInt sendLength = RString8::Length(pSendInfo) + 1;
            if(EStreamResult_Success != pOutputPipe->Write(pSendInfo, sendLength, &lengthWrite)){
               MO_INFO(TC("Receive and send security data failue. (socket=0x%08X, host=%s:%d, handle=%d)"),
                     pSocket, pSocket->Host(), pSocket->Port(), pSocket->Handle());
            }
         }
      }
   }
   return lengthPeek;
}

//============================================================
// <T>接收一个网络端口的数据。</T>
//
// @param pSocket 网络链接
//============================================================
TInt FNetTransferReceiveThread::ReceiveData(FNetBufferedSocket* pSocket){
   // 循环处理
   TInt loop = 0;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   // 获得链接的输入管道
   INetPipe* pPipe = pSocket->InputPipe();
   MO_ASSERT(pPipe);
   // 读取输入管道中所有消息
   TBool continueCd = ETrue;
   while(continueCd){
      // 判断是否存在完整消息头
      TInt length = pPipe->Length();
      if(length < (TInt)sizeof(SNetHead)){
         break;
      }
      // 获取消息的长度
      TNetLength size = 0;
      TInt lengthPeek;
      if(EStreamResult_Success != pPipe->Peek(&size, sizeof(TNetLength), &lengthPeek)){
         break;
      }
      if(lengthPeek != sizeof(TNetLength)){
         break;
      }
      if(0 == size){
         break;
      }
      // 消息长度大于管道长度，错误消息
      if(size > MO_NETMESSAGE_MAXLENGTH){
         TChar dump[MO_FS_DUMP_LENGTH];
         TByte buffer[MO_NETMESSAGE_BYTEDUMP_MAXLENGTH];
         TInt peekSize = MO_LIB_MIN(length, MO_NETMESSAGE_BYTEDUMP_MAXLENGTH);
         pPipe->Peek(buffer, peekSize, &lengthPeek);
         MO_ERROR(TC("Invalid net socket data. (length=%d, size=%d, peek_size=%d)\n%s"),
               length, size, peekSize,
               RByte::Dump(buffer, peekSize, dump, MO_FS_DUMP_LENGTH));
         _pService->CloseSocketWithNotify(pSocket);
         break;
      }
      // 消息不够一个完整的
      if((TUint)length < size){
         break;
      }
      // 测试全局管道是否能放下该消息
      if(!_pInputQueue->TestPushAble(size)){
         MO_ERROR(TC("Server input queue is full. (message_size=%d)"), size);
         break;
      }
      // 获得消息数据，写入全局管道
      TInt lengthRead;
      if(EStreamResult_Success != pPipe->Read(buffer, size, &lengthRead)){
         MO_ERROR(TC("Socket pipe try read failure. (size=%d)"), size);
         continue;
      }
      // 获得网络头信息
      SNetHead* pHead = (SNetHead*)buffer;
      if(pHead->length != size){
         MO_ERROR(TC("Socket net head data is invalid. (head_length=%d, size=%d)"), pHead->length, size);
         _pService->CloseSocketWithNotify(pSocket);
         break;
      }
      switch(pHead->protocol){
         case ENetProtocol_Message:{
            // 收到网络消息
            TInt receiveResult = ReceiveMessage(pSocket, buffer, size);
            loop++;
            if(EFailure == receiveResult){
               MO_ERROR(TC("Receive message failure. (socket=0x%08X, receive_result=%d)"), pSocket, receiveResult);
               _pService->CloseSocketWithNotify(pSocket);
               continueCd = EFalse;
            }
            break;
         }
         case ENetProtocol_Router:{
            // 收到网络消息邮寄包
            ReceiveRouter(pSocket, buffer, size);
            loop++;
            break;
         }
         default:{
            // 未知格式
            MO_ERROR(TC("Unsupport package mode. (protocol=%d)"), pHead->protocol);
            _pService->CloseSocketWithNotify(pSocket);
            continueCd = EFalse;
            break;
         }
      }
   }
   return loop;
}

//============================================================
// <T>网络数据接收处理。</T>
//
// @return 处理结果
//============================================================
TResult FNetTransferReceiveThread::Process(){
#ifdef _MO_LINUX
#ifndef __CYGWIN__
   _threadId = syscall(SYS_gettid);
#endif
#endif
   // 读取服务信息
   MO_ASSERT(_pService);
   STransferServiceConfig& config = _pService->Config();
   _dataCheck = config.dataCheck;
   _dataCompress = config.dataCompress;
   _dataMask = config.dataMask;
   // 获得接收完成端口
   INetSocketPool* pPool = _pService->SocketPool();
   MO_ASSERT(pPool);
   TUint handle = pPool->Handle();
   MO_ASSERT(handle > 0);
   // 获得链接管理模块
   FNetBufferedSocketModule* pSocketsModule = _pService->SocketsModule();
   MO_ASSERT(pSocketsModule);
   // 获得接收链接管道
   FQueue* pReceiveQueue = _pService->ReceiveNodeQueue();
   MO_ASSERT(pReceiveQueue);
   // 获得主输入队列
   _pInputQueue = _pService->QueueStorage()->Connection()->InputQueue();
   MO_ASSERT(_pInputQueue);
   // 安装扑捉器
   _pCatcher->Install();
#ifdef _MO_LINUX
#ifndef __CYGWIN__
   // 循环处理
   TInt timeout = MO_MD_NTS_EVENT_TIMEOUT;
   epoll_event events[MO_MD_NTS_EVENT_MAXCOUNT];
   while(!IsStop()){
      _pCatcher->Enter();
      if(MO_TRAP_CATCHER(_pCatcher)){
         _pCatcher->Recorded();
         //............................................................
         // 获取链接事件列表
         TInt count = epoll_wait(handle, events, MO_MD_NTS_EVENT_MAXCOUNT, timeout);
         // 数据缓冲满的话，下一次处理强制立刻返回，否则等待超时处理
         if(MO_MD_NTS_EVENT_MAXCOUNT == count){
            timeout = 0;
         }else{
            timeout = MO_MD_NTS_EVENT_TIMEOUT;
         }
         // 无数据处理或超时处理
         if(0 == count){
            _pCatcher->Leave();
            continue;
         }
         // 执行错误时
         if(count < 0){
            if(EINTR == errno){
               // 内核中断处理，
               MO_PWARN(epoll_wait);
               _pCatcher->Leave();
               continue;
            }else{
               MO_PFATAL(epoll_wait);
            }
         }
         // 处理链接事件列表
         TInt n = -1;
         while(++n < count){
            // 获取事件
            epoll_event& event = events[n];
            // 获得链接对象
            INetSocket* pNetSocket = (INetSocket*)event.data.ptr;
            if(NULL == pNetSocket){
               MO_WARN("Shared net socket is not exists. (socket=0x%08X)", pNetSocket);
               continue;
            }
            FNetBufferedSocket* pSocket = MO_CAST_STATIC(pNetSocket, FNetBufferedSocket*);
            if(NULL == pSocket){
               MO_WARN("Shared net socket convert invalid. (socket=0x%08X)", pSocket);
               continue;
            }
            // 链接错误处理
            TUint32 code = event.events;
            if(EPOLLERR == (EPOLLERR & code)){
               MO_WARN("Epoll socket error. (socket=0x%08X, error_code=%d)", pSocket, code);
               _pService->CloseSocketWithNotify(pSocket);
               continue;
            }
            // 链接半关闭处理
            if(EPOLLHUP == (EPOLLHUP & code)){
               MO_WARN("Epoll socket half disconnect. (socket=0x%08X, error_code=%d)", pSocket, code);
               _pService->CloseSocketWithNotify(pSocket);
               continue;
            }
            // 链接收到数据处理
            if(EPOLLIN == (EPOLLIN & code)){
               SNetSocketInfo* pSocketInfo = pSocket->Info();
               TUint64 lastReceiveTotal = pSocketInfo->receiveTotal;
               TInt length = pSocket->DoBufferReceive();
               if(length > 0){
                  _receiveTotal += length;
                  // 接收首次数据
                  if(0 == lastReceiveTotal){
                     ReceiveFirstData(pSocket);
                  }
                  // 收到数据处理
                  ReceiveData(pSocket);
               }else if(length < 0){
                  // 链接关闭处理
                  MO_INFO("Epoll socket receive disconnect. (socket=0x%08X, code=%d, length=%d)", pSocket, code, length);
                  _pService->CloseSocketWithNotify(pSocket);
                  continue;
               }
            }
         }
      }else{
         _pCatcher->JumpFinish();
      }
      _pCatcher->Leave();
   }
#endif
#endif
   return ESuccess;
}
MO_NAMESPACE_END
