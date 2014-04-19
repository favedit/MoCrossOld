#include "MoCmSingleton.h"

MO_NAMESPACE_BEGIN

//============================================================
TThreadLocker RSingletonManager::_locker;
FSingletonConsole* RSingletonManager::_pInstance = NULL;

//============================================================
// <T>初始化对象的实例。</T>
//============================================================
void RSingletonManager::Create(){
   MO_ASSERT(!_pInstance);
   _pInstance = MO_PTR_CREATE(FSingletonConsole);
}

//============================================================
// <T>释放对象的实例。</T>
//============================================================
void RSingletonManager::Destroy(){
   MO_ASSERT(_pInstance);
   MO_PTR_DELETE(_pInstance);
}

//============================================================
// <T>注册唯一对象。</T>
//
// @param pSingleton 唯一对象列表
//============================================================
void RSingletonManager::Register(ISingleton* pSingleton){
   FSingletonConsole* pSingletonConsole = SafeInstancePtr();
   pSingletonConsole->Register(pSingleton);
}

//============================================================
// <T>注销唯一对象列表。</T>
//
// @param pSingleton 唯一对象列表
//============================================================
void RSingletonManager::Unregister(ISingleton* pSingleton){
   FSingletonConsole* pSingletonConsole = SafeInstancePtr();
   pSingletonConsole->Unregister(pSingleton);
}

MO_NAMESPACE_END
