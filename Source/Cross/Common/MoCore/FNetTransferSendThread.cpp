#ifdef _MO_LINUX
#ifndef __CYGWIN__
#include <sys/syscall.h>
#endif
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#endif
#include <MoCmStream.h>
#include "MoCrNetEpoll.h"
#include "MoCrNetPipe.h"
#include "MoCrNetTransfer.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络数据发送线程实例。</T>
//
// @return 线程实例
//============================================================
FNetTransferSendThread::FNetTransferSendThread(){
   _code = TC("STS");
   _name = TC("Thread.Transfer.Send");
   MO_CLEAR(_pService);
   MO_CLEAR(_pSocketsModule);
   MO_CLEAR(_pOutputQueue);
   MO_CLEAR(_pUdpServerSocket);
   _dataCompress = EFalse;
   _sendTotal = 0;
   _threadId = 0;
   _pCatcher = MO_CREATE(FNetTransferCatcher);
}

//============================================================
// <T>析构网络数据发送线程实例。</T>
//============================================================
FNetTransferSendThread::~FNetTransferSendThread(){
   MO_DELETE(_pCatcher);
}

//============================================================
// <T>设置传输服务对象。</T>
//
// @param pService 传输服务对象
//============================================================
void FNetTransferSendThread::SetService(FNetTransferService* pService){
   MO_ASSERT(pService);
   _pService = pService;
}

#ifdef _MO_LINUX
//============================================================
void FNetTransferSendThread::NotifyWake(){
   _notifyFutex.WakeGroup(1);
}

//============================================================
void FNetTransferSendThread::NotifyWait(){
   _notifyFutex.WaitGroup();
}

//============================================================
pid_t FNetTransferSendThread::ThreadId(){
   return _threadId;
}
#endif // _LINUX

//============================================================
// <T>发送消息给指定网络端口。</T>
//
// @param pSocket 端口
// @param pMessage 消息
// @return 发送长度
//============================================================
TInt FNetTransferSendThread::SendTcpMessage(FNetBufferedSocket* pSocket, TNetMessage* pMessage){
   MO_ASSERT(pSocket);
   MO_ASSERT(pMessage);
   TFsDump dump;
   TFsDump format;
   // 检查数据大小
   TInt capacity = pMessage->Capacity();
   if(0 == capacity){
      MO_WARN(TC("Empty tcp message.\n%s%s"),
            pMessage->DumpMessage((TChar*)dump, TFsDump::Size()),
            pMessage->DumpMessageMemory((TChar*)format, TFsDump::Size()));
      return 0;
   }
   // 将消息写入链接的输出管道（TCP发送数据）
   TBool result = EFalse;
   if(_dataCompress){
      result = pSocket->OutputPipe()->WriteMessageCompress(pMessage);
   }else{
      result = pSocket->OutputPipe()->WriteMessage(pMessage);
   }
   if(!result){
      // 链接发送区满，抛弃要发送的消息
#ifdef _MO_DEBUG
      MO_WARN(TC("Socket output buffer is full. (length=%d, host=%s:%d#%d)\n%s%s"),
            pSocket->OutputPipe()->Length(), pSocket->Host(), pSocket->Port(), pSocket->Serial(),
            pMessage->DumpMessage((TChar*)dump, dump.Capacity()),
            pMessage->DumpMessageMemory((TChar*)format, dump.Capacity()));
#else
      TInt code = pMessage->MessageHead().Code();
      TNetMessageInfo* pInfo = RNetMessageFactory::CodeInfo(code);
      if(NULL != pInfo){
         MO_WARN(TC("Socket output buffer is full. (length=%d, host=%s:%d#%d, message=%s)"),
               pSocket->OutputPipe()->Length(), pSocket->Host(), pSocket->Port(), pSocket->Serial(), pInfo->Name());
      }else{
         MO_WARN(TC("Socket output buffer is full. (length=%d, host=%s:%d#%d, message=unknown)"),
               pSocket->OutputPipe()->Length(), pSocket->Host(), pSocket->Port(), pSocket->Serial());
      }
#endif
      return 0;
   }
   // 正常发送消息
   MO_DEBUG(TC("Send tcp message to target. (host=%s:%d#%d)\nServer =>> %s%s"),
         pSocket->Host(), pSocket->Port(), pSocket->Serial(),
         pMessage->DumpMessage((TChar*)dump, dump.Capacity()),
         pMessage->DumpMessageMemory((TChar*)format, dump.Capacity()));
   return capacity;
}

//============================================================
// <T>发送路由给指定网络端口。</T>
//
// @param pSocket 端口
// @param pRouter 路由
// @return 发送长度
//============================================================
TInt FNetTransferSendThread::SendTcpRouter(FNetBufferedSocket* pSocket, TNetRouter* pRouter){
   MO_ASSERT(pSocket);
   MO_ASSERT(pRouter);
   TFsDump dump;
   TFsDump format;
   // 检查数据大小
   TInt capacity = pRouter->Capacity();
   if(0 == capacity){
      MO_WARN(TC("Empty tcp router.\n%s%s"),
            pRouter->DumpRouter((TChar*)dump, dump.Size()),
            pRouter->DumpRouterMemory((TChar*)format, format.Size()));
      return 0;
   }
   // 将消息写入链接的输出管道（TCP发送数据）
   if(!pSocket->OutputPipe()->WriteRouter(pRouter)){
      TInt code = pRouter->MessageHead().Code();
      TNetMessageInfo* pInfo = RNetMessageFactory::CodeInfo(code);
      if(NULL != pInfo){
         MO_WARN(TC("Socket output buffer is full. (length=%d, host=%s:%d#%d, message=%s)"),
               pSocket->OutputPipe()->Length(), pSocket->Host(), pSocket->Port(), pSocket->Serial(), pInfo->Name());
      }else{
         MO_WARN(TC("Socket output buffer is full. (length=%d, host=%s:%d#%d, message=unknown)"),
               pSocket->OutputPipe()->Length(), pSocket->Host(), pSocket->Port(), pSocket->Serial());
      }
      // 链接发送区满，抛弃要发送的消息
      //MO_WARN("Socket output buffer is full. (capacity=%d, length=%d, host=%s:%d#%d)\n%s%s",
      //      pSocket->OutputPipe()->Capacity(), pSocket->OutputPipe()->Length(),
      //      pSocket->Host(), pSocket->Port(), pSocket->Serial(),
      //      pRouter->DumpRouter((TChar*)dump, dump.Size()),
      //      pRouter->DumpRouterMemory((TChar*)format, format.Size()));
      return 0;
   }
   // 正常发送消息
   MO_DEBUG(TC("Send tcp router to target. (host=%s:%d:%d)\nServer =>> %s%s"),
         pSocket->Host(), pSocket->Port(), pSocket->Serial(),
         pRouter->DumpRouter((TChar*)dump, dump.Size()),
         pRouter->DumpRouterMemory((TChar*)format, format.Size()));
   return capacity;
}

//============================================================
// <T>发送UDP消息给指定网络端口。</T>
//
// @param pSocket 端口
// @param pMessage 消息
// @return 发送长度
//============================================================
TInt FNetTransferSendThread::SendUdpMessage(FNetBufferedSocket* pSocket, TNetMessage* pMessage){
   MO_ASSERT(pSocket);
   MO_ASSERT(pMessage);
   TFsDump dump;
   TFsDump format;
   // 计算发送地址
   TInt length;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pMessage->Serialize(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_ERROR(TC("Serial udp message failure.\n%s%s"),
            pMessage->DumpMessage((TChar*)dump, dump.Size()),
            pMessage->DumpMemory((TChar*)format, format.Size()));
      return 0;
   }
#ifdef _MO_LINUX
   struct sockaddr_in addr;
   socklen_t addrSize = sizeof(struct sockaddr_in);
   addr.sin_family = AF_INET;
   addr.sin_addr.s_addr = pSocket->UdpAddress();
   addr.sin_port = pSocket->UdpPort();
   TSocket udpHandle = _pUdpServerSocket->Handle();
   TInt sended = sendto(udpHandle, buffer, length, 0, (struct sockaddr*)&addr, addrSize);
   if(sended <= 0){
      TChar address[MoNetHostLength];
      inet_ntop(addr.sin_family, &addr.sin_addr, address, MoNetHostLength);
      MO_ERROR("Send udp message failure. (address=%d[%s], port=%d, sended=%d)\n%s%s",
            pSocket->UdpAddress(), address, addr.sin_port, sended,
            pMessage->DumpMessage((TChar*)dump, dump.Size()),
            pMessage->DumpMemory((TChar*)format, format.Size()));
      return 0;
   }
#ifdef _MO_DEBUG
   TChar address[MoNetHostLength];
   inet_ntop(addr.sin_family, &addr.sin_addr, address, MoNetHostLength);
   MO_DEBUG("Send udp message to target. (address=%d[%s], port=%d, sended=%d)",
         pSocket->UdpAddress(), address, addr.sin_port, sended,
         pMessage->DumpMessage((TChar*)dump, dump.Size()),
         pMessage->DumpMemory((TChar*)format, format.Size()));
#endif
#endif // _LINUX
   return length;
}

//============================================================
// <T>发送一个消息给指定网络链接。</T>
// <P>数据并不发送出去，只是放在链接对象的发送池里。</P>
//
// @param message 消息
// @param handle 网络句柄
// @return 执行结果
//============================================================
TInt FNetTransferSendThread::ProcessTarget(TNetTransfer* pTransfer){
   MO_ASSERT(pTransfer);
   TFsDump format;
   // 获得消息定义
   TNetMessageHead& headMessage = pTransfer->MessageHead();
   TInt messageCode = headMessage.Code();
   TNetMessageInfo* pInfo = pTransfer->MessageInfo();
   if(NULL == pInfo){
      // 非法的消息
      MO_WARN(TC("Invalid message code. (code=%d)\n%s"), headMessage.Code(),
            pTransfer->DumpMemory((TChar*)format, MO_FS_DUMP_LENGTH));
      return 0;
   }
   TBool udpMessage = pInfo->IsUdpSupport();
   // 获得网络端口
   SNetSocketTarget& target = pTransfer->TransferHead().Socket();
   TUint16 index = target.Index(); 
   FNetBufferedSocket* pSocket = _pSocketsModule->FindIndex(index);
   if(NULL == pSocket){
      if(!udpMessage){
         // 链接不存在或以关闭，抛弃要发送的消息
         MO_DEBUG(TC("Socket is not found. (index=0x%04X:%d, message=%s)"), index, index, pInfo->Name());
      }
      return 0;
   }
   // 检查链接是否关闭
   if(!pSocket->IsConnected()){
      // 链接已经关闭，抛弃要发送的消息
      MO_DEBUG(TC("Socket is allready shutdown. (index=0x%04X:%d, message=%s:%08X)"),
            index, index, pInfo->Name(), messageCode);
      return 0;
   }
   // 获得UDP标志
   TBool udpSupport = udpMessage && pSocket->IsUdpSupport();
   // 检查端口创建时间
   TUint16 serial = target.Serial();
   TUint16 socketSerial = pSocket->Serial();
   if(!udpSupport && (socketSerial != serial)){
      // 链接不存在或以关闭，抛弃要发送的消息
      MO_DEBUG(TC("Socket flag is different. (socket=0x%08X(%d), index=%d, socket_serial=%d, message_serial=%d, message=%s:%08X)"),
            pSocket, pSocket->Index(), index, socketSerial, serial, pInfo->Name(), messageCode);
      return 0;
   }
   // 发送广播数据
   if(udpSupport){
      return SendUdpMessage(pSocket, pTransfer);
   }
   // 发送消息数据
   if(ENetProtocol_Message == _protocol){
      return SendTcpMessage(pSocket, pTransfer);
   }
   // 发送路由数据
   return SendTcpRouter(pSocket, pTransfer);
}

//============================================================
// <T>网络数据发送处理。</T>
// <P>收到网络来的消息，根据目标集合：
// <P> － 目标Server收到网络来的消息，根据目标集合，拆分成多个路由包，发往不同的服务器。。</P>
// <P>收到网络来的消息，根据目标集合，拆分成多个路由包，发往不同的服务器。。</P>
//
// @return 处理结果
//============================================================
TResult FNetTransferSendThread::Process(){
#ifdef _MO_LINUX
#ifndef __CYGWIN__
   // 获得设置信息
   MO_ASSERT(_pService);
   STransferServiceConfig& config = _pService->Config();
   _protocol = config.protocol;
   _dataCompress = config.dataCompress;
   TInt receiveFirstTimeout = config.receiveFirstTimeout;
   TInt receiveTimeout = config.receiveTimeout;
   TInt sendTimeout = config.sendTimeout;
   // 获得发送完成端口
   INetSocketPool* pSendPool = _pService->SendPool();
   MO_ASSERT(pSendPool);
   TInt handle = pSendPool->Handle();
   // 获得链接管理模块
   _pSocketsModule = _pService->SocketsModule();
   MO_ASSERT(_pSocketsModule);
   // 获得主输出队列
   _pOutputQueue = _pService->QueueStorage()->Connection()->OutputQueue();
   MO_ASSERT(_pOutputQueue);
   // 安装扑捉器
   _pCatcher->Install();
   _threadId = syscall(SYS_gettid);
   // 处理消息
   TInt loop = 0;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   TNetTransfer transfer;
   TInt timeout = MO_MD_NTS_EVENT_TIMEOUT;
   epoll_event events[MO_MD_NTS_EVENT_MAXCOUNT];
   while(!IsStop()){
      _pCatcher->Enter();
      if(MO_TRAP_CATCHER(_pCatcher)){
         _pCatcher->Recorded();
         //............................................................
         TInt readCount = 0;
         TInt readTotal = 0;
         TDateTime tick = RDateTime::Current();
         // 处理输入消息流
         for(TInt n = 0; n < MO_MD_NTS_EVENT_MAXCOUNT; n++){
            // 读出一个消息，分布到链接输出管道上
            TInt readed = _pOutputQueue->Pop(buffer, MO_NETMESSAGE_MAXLENGTH);
            if(0 == readed){
               break;
            } 
            readCount++;
            readTotal += readed;
            // 读取管道消息
            TInt length;
            if(transfer.Unserialize(buffer, readed, &length)){
               // 获取消息类型
               TInt targetType = transfer.RouterHead().TargetType();
               // 处理消息
               if(ENetTerminal_ServerTransfer == targetType){
                  // 处理传输控制消息
                  _pService->ProcessTransfer(&transfer);
               }else{
                  // 发送消息给目标
                  ProcessTarget(&transfer);
               }
            }else{
               MO_ERROR("Router unserialize failure. (length=%d)", length);
            }
         }
         //............................................................
         // 获取链接事件列表
         TInt count = epoll_wait(handle, events, MO_MD_NTS_EVENT_MAXCOUNT, timeout);
         // 数据缓冲满的话，下一次处理强制立刻返回，否则等待超时处理
         if(MO_MD_NTS_EVENT_MAXCOUNT == count){
            timeout = 0;
         }else{
            timeout = MO_MD_NTS_EVENT_TIMEOUT;
         }
         // 无数据时，睡眠一次
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
         //............................................................
         // 处理链接事件列表
         TInt n = -1;
         TUint sendCount = 0;
         while(++n < count){
            // 获取事件
            epoll_event& event = events[n];
            // 获得链接对象
            INetSocket* pNetSocket = (INetSocket*)event.data.ptr;
            FNetBufferedSocket* pSocket = MO_CAST_STATIC(pNetSocket, FNetBufferedSocket*);
            // 链接可能已经被关闭，无效的读取数据
            if(NULL == pSocket){
               MO_WARN("Shared net socket is not exists. (socket=0x%08X)", pSocket);
               continue;
            }
            // 检查超时
            SNetSocketInfo* pInfo = pSocket->Info();
            if(receiveFirstTimeout > 0){
               if(pInfo->receiveDateTime == pInfo->createDateTime){
                  TTimeTick firstTick = tick - pInfo->receiveDateTime;
                  if(firstTick > receiveFirstTimeout){
                     MO_WARN("Epoll socket disconnect first timeout. (socket=0x%08X, current_tick=0x%16X, receive_tick=0x%16X, first_timeout=%d, first_tick=%d)",
                           pSocket, tick, pInfo->receiveDateTime, receiveFirstTimeout, firstTick);
                     _pService->CloseSocketWithNotify(pSocket);
                     continue;
                  }
               }
            }
            if(receiveTimeout > 0){
               TTimeTick receiveTick = tick - pInfo->receiveDateTime;
               if(receiveTick > receiveTimeout){
                  MO_WARN("Epoll socket receive timeout. (socket=0x%08X, current_tick=0x%16X, receive_tick=0x%16X, receive_timeout=%d, receive_tick=%d)",
                        pSocket, tick, pInfo->receiveDateTime, receiveTimeout, receiveTick);
                  _pService->CloseSocketWithNotify(pSocket);
                  continue;
               }
            }
            if(sendTimeout > 0){
               TTimeTick sendTick = tick - pInfo->sendDateTime;
               if(sendTick > sendTimeout){
                  MO_WARN("Epoll socket send timeout. (socket=0x%08X, current_tick=0x%16X, send_tick=0x%16X, send_timeout=%d, send_tick=%d)",
                        pSocket, tick, pInfo->sendDateTime, sendTimeout, sendTick);
                  _pService->CloseSocketWithNotify(pSocket);
                  continue;
               }
            }
            // 链接错误处理
            TUint32 code = event.events;
            if(EPOLLERR & code){
               MO_WARN("Epoll socket error. (socket=0x%08X, error_code=%d)", pSocket, code);
               _pService->CloseSocketWithNotify(pSocket);
               continue;
            }
            // 链接半关闭处理
            if(EPOLLHUP & code){
               MO_WARN("Epoll socket half disconnect. (socket=0x%08X, error_code=%d)", pSocket, code);
               _pService->CloseSocketWithNotify(pSocket);
               continue;
            }
            // 链接发送数据处理
            if(EPOLLOUT & code){
               // 发送消息数据
               TInt length = pSocket->DoBufferSend();
               if(length > 0){
                  sendCount++;
                  _sendTotal += length;
                  loop++;
               }else if(-1 == length){
                  // 关闭链接
                  MO_INFO("Epoll socket receive disconnect. (socket=0x%08X, code=%d, length=%d)", pSocket, code, length);
                  _pService->CloseSocketWithNotify(pSocket);
               }
            }
         }
         // 未读取到数据和未发送数据的情况下，休眠处理
         if((0 == readCount) && (0 == sendCount)){
            MO_LIB_MICRO_SLEEP(MO_MD_NTS_NOTIFY_INTERVAL);
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
