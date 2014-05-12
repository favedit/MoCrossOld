#include "MoBgCore.h"
      
MO_NAMESPACE_BEGIN

//============================================================
TInt RBridgeManager_CreateObject(SBridgeLinker* pLinker, TCharC* pClassName){
   return RBridgeManager::Instance().CreateObject(pLinker, pClassName);
}

//============================================================
TInt RBridgeManager_Invoke(SBridgeLinker* pLinker, TCharC* pMethodName){
   FInstance* pInstance = (FInstance*)pLinker->instanceId;
   return pInstance->ReferCount();
}

MO_NAMESPACE_END

