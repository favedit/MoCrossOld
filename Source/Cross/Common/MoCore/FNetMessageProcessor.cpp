#include "MoCrNetConnection.h"

MO_NAMESPACE_BEGIN

//============================================================
FNetMessageProcessor::FNetMessageProcessor(){
   _pHandless = MO_CREATE(FNetMessageHandlesVector, MO_NETMESSAGE_COUNT);
}

//============================================================
FNetMessageProcessor::~FNetMessageProcessor(){
   MO_DELETE(_pHandless);
}

//============================================================
// <T>获得处理器集合。</T>
//============================================================
FNetMessageHandles* FNetMessageProcessor::SyncHandles(TInt code){
   FNetMessageHandles* pHandles = _pHandless->Nvl(code, NULL);
   if(NULL == pHandles){
      pHandles = MO_CREATE(FNetMessageHandles);
      _pHandless->ExtendSet(code, pHandles);
   }
   return pHandles;
}

//============================================================
TBool FNetMessageProcessor::Dispatch(TNetMessage* pMessage){
   MO_ASSERT(pMessage);
   // 处理消息
   TInt code = pMessage->MessageHead().Code();
   FNetMessageHandles* pProcessors = _pHandless->Nvl(code, NULL);
   if(NULL != pProcessors){
      // 有注册的消息处理器情况
      pProcessors->Process(NULL, pMessage);
      return ETrue;
   }
   return EFalse;
}

//============================================================
TBool FNetMessageProcessor::Dispatch(FObject* pSender, TNetMessage* pMessage){
   MO_ASSERT(pMessage);
   // 处理消息
   TInt code = pMessage->MessageHead().Code();
   FNetMessageHandles* pProcessors = _pHandless->Nvl(code, NULL);
   if(NULL != pProcessors){
      // 有注册的消息处理器情况
      pProcessors->Process(pSender, pMessage);
      return ETrue;
   }
   return EFalse;
}

MO_NAMESPACE_END
