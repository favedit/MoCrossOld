#include "MoCrNetConnection.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造消息处理回调器集合。</T>
//============================================================
FNetTransferHandles::FNetTransferHandles(){
   _pRouterProcessors = MO_CREATE(FNetRouterHandleList);
   _pTransferProcessors = MO_CREATE(FNetTransferHandleList);
   MO_CLEAR(_pCatcher);
}

//============================================================
// <T>析构消息处理回调器集合。</T>
//============================================================
FNetTransferHandles::~FNetTransferHandles(){
   MO_DELETE(_pRouterProcessors);
   MO_DELETE(_pTransferProcessors);
};

//============================================================
// <T>处理路由器。</T>
//
// @param pHandle 句柄
// @param pSender 发送者
// @param pTransfer 传输器
// @return 处理结果
//============================================================
TBool FNetTransferHandles::ProcessRouter(FNetRouterHandle* pHandle, FObject* pSender, TNetTransfer* pTransfer){
   TBool result = ETrue;
#ifdef _MO_ANDROID
   if(!pHandle->Process(pSender, pTransfer)){
      result = EFalse;
   }
#else
   if(NULL != _pCatcher){
      _pCatcher->LinkInfo(pHandle->InvokerName());
      _pCatcher->Enter();
      if(MO_TRAP_CATCHER(_pCatcher)){
         _pCatcher->Recorded();
         if(!pHandle->Process(pSender, pTransfer)){
            result = EFalse;
         }
      }else{
         _pCatcher->JumpFinish();
         result = EFalse;
      }
      _pCatcher->Leave();
   }else{
      if(!pHandle->Process(pSender, pTransfer)){
         result = EFalse;
      }
   }
#endif // _MO_ANDROID
   return result;
}

//============================================================
// <T>处理传输器。</T>
//
// @param pHandle 句柄
// @param pSender 发送者
// @param pTransfer 传输器
// @return 处理结果
//============================================================
TBool FNetTransferHandles::ProcessTransfer(FNetTransferHandle* pHandle, FObject* pSender, TNetTransfer* pTransfer){
   TBool result = ETrue;
#ifdef _MO_ANDROID
   if(!pHandle->Process(pSender, pTransfer)){
      result = EFalse;
   }
#else
   if(NULL != _pCatcher){
      _pCatcher->LinkInfo(pHandle->InvokerName());
      _pCatcher->Enter();
      if(MO_TRAP_CATCHER(_pCatcher)){
         _pCatcher->Recorded();
         if(!pHandle->Process(pSender, pTransfer)){
            result = EFalse;
         }
      }else{
         _pCatcher->JumpFinish();
         result = EFalse;
      }
      _pCatcher->Leave();
   }else{
      if(!pHandle->Process(pSender, pTransfer)){
         result = EFalse;
      }
   }
#endif // _MO_ANDROID
   return result;
}

//============================================================
// <T>执行消息处理。</T>
// @param pSender 发送者
// @param pTransfer 传输器
// @return 处理结果
//============================================================
TBool FNetTransferHandles::Process(FObject* pSender, TNetTransfer* pTransfer){
   TBool result = ETrue;
   // 处理路由器
   if(result && !_pRouterProcessors->IsEmpty()){
      TListIteratorC<FNetRouterHandle*> iterator = _pRouterProcessors->IteratorC();
      while(iterator.Next()){
         FNetRouterHandle* pHandle = *iterator;
         MO_DEBUG(TC("Process router handle. (invoker=%s)"), pHandle->InvokerName());
         TBool processResult = ProcessRouter(pHandle, pSender, pTransfer);
         if(!processResult){
            result = EFalse;
            break;
         }
      }
   }
   // 处理传输器
   if(result && !_pTransferProcessors->IsEmpty()){
      TListIteratorC<FNetTransferHandle*> iterator = _pTransferProcessors->IteratorC();
      while(iterator.Next()){
         FNetTransferHandle* pHandle = *iterator;
         MO_DEBUG(TC("Process transfer handle. (invoker=%s)"), pHandle->InvokerName());
         TBool processResult = ProcessTransfer(pHandle, pSender, pTransfer);
         if(!processResult){
            result = EFalse;
            break;
         }
      }
   }
   return result;
}

MO_NAMESPACE_END
