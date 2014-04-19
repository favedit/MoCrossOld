#include "MoCrNetConnection.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络传输处理器。</T>
//============================================================
FNetTransferProcessor::FNetTransferProcessor(){
   _pHandlesList = MO_CREATE(FNetTransferHandlesList);
   _pHandless = MO_CREATE(FNetTransferHandlesVector, MO_NETMESSAGE_COUNT);
   // 安装捕捉器
   _pCatcher = MO_CREATE(FNetMessageCatcher);
   _pCatcher->Install();
}

//============================================================
// <T>网络传输处理器。</T>
//============================================================
FNetTransferProcessor::~FNetTransferProcessor(){
   MO_DELETE(_pHandlesList);
   MO_DELETE(_pHandless);
   MO_DELETE(_pCatcher);
}

//============================================================
// <T>同步处理器句柄集合。</T>
//
// @param code 代码
// @return 处理器句柄集合
//============================================================
FNetTransferHandles* FNetTransferProcessor::SyncHandles(TInt code){
   FNetTransferHandles* pHandles = _pHandless->Nvl(code, NULL);
   if(NULL == pHandles){
      pHandles = MO_CREATE(FNetTransferHandles);
      pHandles->SetCatcher(_pCatcher);
      _pHandlesList->Push(pHandles);
      _pHandless->ExtendSet(code, pHandles);
   }
   return pHandles;
}

//============================================================
// <T>分发传输器逻辑。</T>
//
// @param pTransfer 传输器
// @return 处理结果
//============================================================
TBool FNetTransferProcessor::Dispatch(TNetTransfer* pTransfer){
   // 获得消息代码
   MO_ASSERT(pTransfer);
   TInt code = pTransfer->MessageHead().Code();
   // 处理消息
   TBool result = EFalse;
   FNetTransferHandles* pProcessors = _pHandless->Nvl(code, NULL);
   if(NULL != pProcessors){
      // 有注册的消息处理器情况
      pProcessors->Process(NULL, pTransfer);
      result = ETrue;
   }
   return result;
}

//============================================================
// <T>分发传输器逻辑。</T>
//
// @param pSender 
// @param pTransfer 传输器
// @return 处理结果
//============================================================
TBool FNetTransferProcessor::Dispatch(FObject* pSender, TNetTransfer* pTransfer){
   // 获得消息代码
   MO_ASSERT(pTransfer);
   TInt code = pTransfer->MessageHead().Code();
   // 处理消息
   TBool result = EFalse;
   FNetTransferHandles* pProcessors = _pHandless->Nvl(code, NULL);
   if(NULL != pProcessors){
      // 有注册的消息处理器情况
      pProcessors->Process(pSender, pTransfer);
      result = ETrue;
   }
   return result;
}

MO_NAMESPACE_END
