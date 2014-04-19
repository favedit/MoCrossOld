#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造应用监听器集合。</T>
//============================================================
MApplicationListeners::MApplicationListeners(){
   // 创建中断监听器
   _pInterruptListener = MO_CREATE(FApplicationListener);
   _pInterruptListener->SetListenerCd(EApplicationListener_Interrrupt);
   _pInterruptListener->SetListeners(this);
   // 创建加载监听器
   _pReloadListener = MO_CREATE(FApplicationListener);
   _pReloadListener->SetListenerCd(EApplicationListener_Reload);
   _pReloadListener->SetListeners(this);
   // 创建卸载监听器
   _pUnloadListener = MO_CREATE(FApplicationListener);
   _pUnloadListener->SetListenerCd(EApplicationListener_Unload);
   _pUnloadListener->SetListeners(this);
   // 创建关闭监听器
   _pShutdownListener = MO_CREATE(FApplicationListener);
   _pShutdownListener->SetListenerCd(EApplicationListener_Shutdown);
   _pShutdownListener->SetListeners(this);
}

//============================================================
// <T>析构应用监听器集合。</T>
//============================================================
MApplicationListeners::~MApplicationListeners(){
   MO_DELETE(_pInterruptListener);
   MO_DELETE(_pReloadListener);
   MO_DELETE(_pUnloadListener);
   MO_DELETE(_pShutdownListener);
}

//============================================================
// <T>中断处理。</T>
//
// @return 处理结果
//============================================================
TResult MApplicationListeners::Interrupt(){
   return ESuccess;
}

//============================================================
// <T>重新加载处理。</T>
//
// @return 处理结果
//============================================================
TResult MApplicationListeners::Reload(){
   return ESuccess;
}

//============================================================
// <T>卸载处理。</T>
//
// @return 处理结果
//============================================================
TResult MApplicationListeners::Unload(){
   return ESuccess;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult MApplicationListeners::Shutdown(){
   return ESuccess;
}

MO_NAMESPACE_END
