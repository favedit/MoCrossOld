#include "MoBridge.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化渲染引擎处理。</T>
//============================================================
void MoBridgeInitialize(){
   RBridgeManager::Create();
   RBridgeManager::Instance().ClassFactory()->Register("Instance", FInstance::Class());
}

//============================================================
// <T>释放渲染引擎处理。</T>
//============================================================
void MoBridgeRelease(){
   RBridgeManager::Destroy();
}

MO_NAMESPACE_END

