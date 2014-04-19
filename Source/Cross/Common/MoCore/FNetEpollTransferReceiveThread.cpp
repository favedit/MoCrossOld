#include "MoCrNetTransferEpoll.h"

MO_NAMESPACE_BEGIN

#ifdef _MO_LINUX

//============================================================
// <T>构造网络数据接收线程实例。</T>
//
// @return 线程实例
//============================================================
FNetEpollTransferReceiveThread::FNetEpollTransferReceiveThread(){
}

//============================================================
// <T>析构网络数据接收线程实例。</T>
//============================================================
FNetEpollTransferReceiveThread::~FNetEpollTransferReceiveThread(){
}

//============================================================
// <T>网络数据接收处理。</T>
//
// @return 执行结果
//============================================================
TInt FNetEpollTransferReceiveThread::Process(){
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
#endif // __CYGWIN__
   return ESuccess;
}

#endif // _MO_LINUX

MO_NAMESPACE_END
