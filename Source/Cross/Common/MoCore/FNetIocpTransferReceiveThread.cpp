#include "MoCrNetTransferIocp.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络数据接收线程实例。</T>
//
// @return 线程实例
//============================================================
FNetIocpTransferReceiveThread::FNetIocpTransferReceiveThread(){
}

//============================================================
// <T>析构网络数据接收线程实例。</T>
//============================================================
FNetIocpTransferReceiveThread::~FNetIocpTransferReceiveThread(){
}

//============================================================
// <T>网络数据接收处理。</T>
//
// @return 处理结果
//============================================================
TResult FNetIocpTransferReceiveThread::Process(){
   MO_INFO(TC("Iocp query thread process."));
   // 读取服务信息
   MO_ASSERT(_pService);
   STransferServiceConfig& config = _pService->Config();
   _dataCheck = config.dataCheck;
   _dataCompress = config.dataCompress;
   _dataMask = config.dataMask;
   // 获得接收完成端口
   FNetIocp* pPool = (FNetIocp*)_pService->SocketPool();
   MO_ASSERT(pPool);
   TAny* pHandle = pPool->NativeHandle();
   MO_ASSERT(pHandle);
   // 获得链接管理模块
   //FNetBufferedSocketModule* pSocketsModule = _pService->SocketsModule();
   //MO_ASSERT(pSocketsModule);
   // 获得接收链接管道
   //FQueue* pReceiveQueue = _pService->ReceiveNodeQueue();
   //MO_ASSERT(pReceiveQueue);
   // 获得主输入队列
   //_pInputQueue = _pService->QueueStorage()->Connection()->InputQueue();
   //MO_ASSERT(_pInputQueue);
   //............................................................
   while(!IsStop()){
      // 获取链接事件列表
	  DWORD transdBytes = 0;
      PULONG_PTR completionKey = 0;
      OVERLAPPED* pOverlap= NULL;
      TBool result = GetQueuedCompletionStatus(pHandle, &transdBytes, (PULONG_PTR)&completionKey, &pOverlap, INFINITE);
      MO_DEBUG(TC("Get queued completion status. (result=%d)"), result);
      // 继续处理
      if(EFalse == result){
         continue;
      }
      SIocpData* pIocpOverlap = CONTAINING_RECORD(pOverlap, SIocpData, Overlapped);
      MO_DEBUG(TC("Get queued completion data. (data=%d)"), pIocpOverlap);
      //// 处理链接事件列表
      //TInt n = -1;
      //while(++n < count){
      //   // 获取事件
      //   epoll_event& event = events[n];
      //   // 获得链接对象
      //   INetSocket* pNetSocket = (INetSocket*)event.data.ptr;
      //   if(NULL == pNetSocket){
      //      MO_WARN("Shared net socket is not exists. (socket=0x%08X)", pNetSocket);
      //      continue;
      //   }
      //   FNetBufferedSocket* pSocket = MO_CAST_STATIC(pNetSocket, FNetBufferedSocket*);
      //   if(NULL == pSocket){
      //      MO_WARN("Shared net socket convert invalid. (socket=0x%08X)", pSocket);
      //      continue;
      //   }
      //   // 链接错误处理
      //   TUint32 code = event.events;
      //   if(EPOLLERR == (EPOLLERR & code)){
      //      MO_WARN("Epoll socket error. (socket=0x%08X, error_code=%d)", pSocket, code);
      //      _pService->CloseSocketWithNotify(pSocket);
      //      continue;
      //   }
      //   // 链接半关闭处理
      //   if(EPOLLHUP == (EPOLLHUP & code)){
      //      MO_WARN("Epoll socket half disconnect. (socket=0x%08X, error_code=%d)", pSocket, code);
      //      _pService->CloseSocketWithNotify(pSocket);
      //      continue;
      //   }
      //   // 链接收到数据处理
      //   if(EPOLLIN == (EPOLLIN & code)){
      //      SNetSocketInfo* pSocketInfo = pSocket->Info();
      //      TUint64 lastReceiveTotal = pSocketInfo->receiveTotal;
      //      TInt length = pSocket->DoBufferReceive();
      //      if(length > 0){
      //         _receiveTotal += length;
      //         // 接收首次数据
      //         if(0 == lastReceiveTotal){
      //            ReceiveFirstData(pSocket);
      //         }
      //         // 收到数据处理
      //         ReceiveData(pSocket);
      //      }else if(length < 0){
      //         // 链接关闭处理
      //         MO_INFO("Epoll socket receive disconnect. (socket=0x%08X, code=%d, length=%d)", pSocket, code, length);
      //         _pService->CloseSocketWithNotify(pSocket);
      //         continue;
      //      }
      //   }
      //}
   }
   return ESuccess;
}
MO_NAMESPACE_END
