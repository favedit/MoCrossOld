#include "MoCrNetConnection.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造消息处理回调器集合。</T>
//============================================================
FNetRouterHandles::FNetRouterHandles(){
   _pProcessors = MO_CREATE(FNetRouterHandleList);
}

//============================================================
// <T>析构消息处理回调器集合。</T>
//============================================================
FNetRouterHandles::~FNetRouterHandles(){
   MO_DELETE(_pProcessors);
};

//============================================================
// <T>执行消息处理。</T>
//============================================================
TBool FNetRouterHandles::Process(FObject* pSender, TNetRouter* pRouter){
   TBool result = ETrue;
   if(!_pProcessors->IsEmpty()){
      TListIteratorC<FNetRouterHandle*> iterator = _pProcessors->IteratorC();
      while(iterator.Next()){
         FNetRouterHandle* pHandle = *iterator;
         result = pHandle->Process(pSender, pRouter);
         if(result){
            MO_DEBUG(TC("Process router hanle success. (message=%s, invoker=%s)"),
                  pRouter->MessageInfo()->Name(), pHandle->InvokerName());
         }else{
            MO_ERROR(TC("Process router hanle failure. (message=%s, invoker=%s)"),
                  pRouter->MessageInfo()->Name(), pHandle->InvokerName());
         }
      }
   }
   return ETrue;
}

MO_NAMESPACE_END
