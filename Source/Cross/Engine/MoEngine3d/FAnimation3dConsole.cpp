#include "MoE3Display.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造实体3D动画管理器。</T>
//============================================================
FAnimation3dConsole::FAnimation3dConsole(){
   _pThread = MO_CREATE(FAnimation3dThread);
}

//============================================================
// <T>析构实体3D动画管理器。</T>
//============================================================
FAnimation3dConsole::~FAnimation3dConsole(){
}

//============================================================
// <T>根据名称获得材质实例。</T>
//
// @param pName 名称
// @return 材质实例
//============================================================
TResult FAnimation3dConsole::Register(FAnimation3d* pAnimation){
   TResult result = _pThread->Register(pAnimation);
   return result;
}

//============================================================
// <T>根据名称获得材质实例。</T>
//
// @param pName 名称
// @return 材质实例
//============================================================
TResult FAnimation3dConsole::Unregister(FAnimation3d* pAnimation){
   TResult result = _pThread->Unregister(pAnimation);
   return result;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FAnimation3dConsole::Startup(){
   _pThread->Start();
   return ESuccess;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FAnimation3dConsole::Shutdown(){
   _pThread->Stop();
   return ESuccess;
}

//============================================================
// <T>清空处理。</T>
//
// @return 处理结果
//============================================================
TResult FAnimation3dConsole::Clear(){
   TResult result = _pThread->Clear();
   return result;
}

MO_NAMESPACE_END
