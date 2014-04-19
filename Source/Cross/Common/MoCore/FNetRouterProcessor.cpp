#include "MoCrNetConnection.h"

MO_NAMESPACE_BEGIN

//============================================================
FNetRouterProcessor::FNetRouterProcessor(){
   _pHandless = MO_CREATE(FNetRouterHandlesVector, MO_NETMESSAGE_COUNT);
}

//============================================================
FNetRouterProcessor::~FNetRouterProcessor(){
   MO_DELETE(_pHandless);
}

//============================================================
// <T>获得处理器集合。</T>
//============================================================
FNetRouterHandles* FNetRouterProcessor::SyncHandles(TInt code){
   FNetRouterHandles* pHandles = _pHandless->Nvl(code, NULL);
   if(NULL == pHandles){
      pHandles = MO_CREATE(FNetRouterHandles);
      _pHandless->ExtendSet(code, pHandles);
   }
   return pHandles;
}

//============================================================
TBool FNetRouterProcessor::Dispatch(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   // 处理消息
   TInt code = pRouter->MessageHead().Code();
   FNetRouterHandles* pProcessors = _pHandless->Nvl(code, NULL);
   if(NULL != pProcessors){
      // 有注册的消息处理器情况
      pProcessors->Process(NULL, pRouter);
      return ETrue;
   }
   return EFalse;
}

//============================================================
TBool FNetRouterProcessor::Dispatch(FObject* pSender, TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   // 处理消息
   TInt code = pRouter->MessageHead().Code();
   FNetRouterHandles* pProcessors = _pHandless->Nvl(code, NULL);
   if(NULL != pProcessors){
      // 有注册的消息处理器情况
      pProcessors->Process(pSender, pRouter);
      return ETrue;
   }
   return EFalse;
}

MO_NAMESPACE_END
