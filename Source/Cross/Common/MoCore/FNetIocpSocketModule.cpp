#include "MoCrNetTransferIocp.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造套接字模块对象。</T>
//
// @return 当前对象指针。
//============================================================
FNetIocpSocketModule::FNetIocpSocketModule(){
}

//============================================================
// <T>析构套接字模块对象。</T>
//
// @return 无返回值
//============================================================
FNetIocpSocketModule::~FNetIocpSocketModule(){
}

//============================================================
// <T>初始化处理。</T>
//
// @return 处理结果
//============================================================
TResult FNetIocpSocketModule::OnInitialize(){
   // 获得管道分块收集器
   FBufferedPipeBlockAllocator* pAllocator = RNetPipeBlockPoolModule::Instance().Alloc(_blockCapacity);
   // 建立端口对象
   _sectionPool.Enter();
   for(TInt n = 0; n < _socketCount; n++){
      // 创建链接对象
      FNetIocpSocket* pSocket = MO_CREATE(FNetIocpSocket);
      // 存储缓冲
      TInt index = _pPool->Store(pSocket);
      pSocket->SetIndex((TUint16)index);
      pSocket->NetInfo()->receiveBufferSize = (TUint32)_socketReceiveBuffer;
      pSocket->NetInfo()->sendBufferSize = (TUint32)_socketSendBuffer;
      pSocket->LinkAllocator(_socketCapacity, pAllocator);
   }
   _sectionPool.Leave();
   return ESuccess;
}


MO_NAMESPACE_END
