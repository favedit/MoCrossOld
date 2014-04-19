#include "MoCrNetTransferEpoll.h"

MO_NAMESPACE_BEGIN

#ifdef _MO_LINUX

//============================================================
// <T>构造网络数据发送线程实例。</T>
//
// @return 线程实例
//============================================================
FNetEpollTransferSendThread::FNetEpollTransferSendThread(){
}

//============================================================
// <T>析构网络数据发送线程实例。</T>
//============================================================
FNetEpollTransferSendThread::~FNetEpollTransferSendThread(){
}

//============================================================
// <T>网络数据发送处理。</T>
// <P>收到网络来的消息，根据目标集合：
// <P> － 目标Server收到网络来的消息，根据目标集合，拆分成多个路由包，发往不同的服务器。。</P>
// <P>收到网络来的消息，根据目标集合，拆分成多个路由包，发往不同的服务器。。</P>
//
// @return 执行结果
//============================================================
TInt FNetEpollTransferSendThread::Process(){
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
            continue;
         }
         // 执行错误时
         if(count < 0){
            if(EINTR == errno){
               // 内核中断处理，
               MO_PWARN(epoll_wait);
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
#endif // __CYGWIN__
   return ESuccess;
}

#endif // _MO_LINUX

MO_NAMESPACE_END
