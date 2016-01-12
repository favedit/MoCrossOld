#include "MoCrNetConnection.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造消息处理回调器集合。</T>
//============================================================
FNetMessageHandles::FNetMessageHandles(){
   _pProcessors = MO_CREATE(FNetMessageHandleList);
}

//============================================================
// <T>析构消息处理回调器集合。</T>
//============================================================
FNetMessageHandles::~FNetMessageHandles(){
   MO_DELETE(_pProcessors);
};

//============================================================
// <T>执行消息处理。</T>
//============================================================
TBool FNetMessageHandles::Process(FObject* pSender, TNetMessage* pMessage){
   TBool result = ETrue;
   if(!_pProcessors->IsEmpty()){
      TListIteratorC<FNetMessageHandle*> iterator = _pProcessors->IteratorC();
      while(iterator.Next()){
         FNetMessageHandle* pHandle = *iterator;
         result = pHandle->Process(pSender, pMessage);
         if(result){
            MO_DEBUG(TC("Process message hanle success. (message=%s, invoker=%s)"),
                  pMessage->MessageInfo()->Name(), pHandle->InvokerName());
         }else{
            MO_ERROR(TC("Process message hanle failure. (message=%s, invoker=%s)"),
                  pMessage->MessageInfo()->Name(), pHandle->InvokerName());
         }
      }
   }
   return result;
}

MO_NAMESPACE_END
