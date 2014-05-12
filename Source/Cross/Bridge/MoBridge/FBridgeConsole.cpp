#include "MoBgCore.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>构造桥接控制台。</T>
//============================================================
FBridgeConsole::FBridgeConsole(){
   _pClassFactory = MO_CREATE(FClassFactory);
}

//============================================================
// <T>析构桥接控制台。</T>
//============================================================
FBridgeConsole::~FBridgeConsole(){
   MO_DELETE(_pClassFactory);
}

//============================================================
// <T>创建对象。</T>
//============================================================
TInt FBridgeConsole::CreateObject(SBridgeLinker* pLinker, TCharC* pClassName){
   FInstance* pInstance = _pClassFactory->Create(pClassName);
   pInstance->ReferIncrease();
   pLinker->instanceId = (TUint32)pInstance;
   return ESuccess;
}

MO_NAMESPACE_END

