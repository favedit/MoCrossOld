#include "MoCrNetTransferSingle.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络数据接收线程实例。</T>
//
// @return 线程实例
//============================================================
FNetSingleSocketReceiveThread::FNetSingleSocketReceiveThread(){
   MO_CLEAR(_pSocket);
}

//============================================================
// <T>析构网络数据接收线程实例。</T>
//============================================================
FNetSingleSocketReceiveThread::~FNetSingleSocketReceiveThread(){
}

//============================================================
// <T>网络数据接收处理。</T>
//
// @return 处理结果
//============================================================
TResult FNetSingleSocketReceiveThread::Process(){
   // 读取服务信息
   MO_ASSERT(_pService);
   STransferServiceConfig& config = _pService->Config();
   _dataCheck = config.dataCheck;
   _dataCompress = config.dataCompress;
   _dataMask = config.dataMask;
   // 获得网络端口信息
   MO_ASSERT(_pSocket);
   // 获得链接管理模块
   FNetBufferedSocketModule* pSocketsModule = _pService->SocketsModule();
   MO_ASSERT(pSocketsModule);
   // 获得接收链接管道
   FQueue* pReceiveQueue = _pService->ReceiveNodeQueue();
   MO_ASSERT(pReceiveQueue);
   // 获得主输入队列
   _pInputQueue = _pService->QueueStorage()->Connection()->InputQueue();
   MO_ASSERT(_pInputQueue);
   while(!IsStop()){
      SNetSocketInfo* pSocketInfo = _pSocket->Info();
      TUint64 lastReceiveTotal = pSocketInfo->receiveTotal;
      TInt length = _pSocket->DoBufferReceive();
      if(length > 0){
         _receiveTotal += length;
         // 接收首次数据
         if(0 == lastReceiveTotal){
            ReceiveFirstData(_pSocket);
         }
         // 收到数据处理
         ReceiveData(_pSocket);
      }else if(length < 0){
         // 链接关闭处理
         MO_INFO(TC("Epoll socket receive disconnect. (socket=0x%08X, length=%d)"), _pSocket, length);
         _pService->CloseSocketWithNotify(_pSocket);
         break;
      }
   }
   return ESuccess;
}
MO_NAMESPACE_END
