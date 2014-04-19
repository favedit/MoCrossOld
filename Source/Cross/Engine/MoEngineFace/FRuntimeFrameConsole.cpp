#include "MoFmRuntime.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造运行界面控制台。</T>
//============================================================
FRuntimeFrameConsole::FRuntimeFrameConsole(){
}

//============================================================
// <T>析构运行界面控制台。</T>
//============================================================
FRuntimeFrameConsole::~FRuntimeFrameConsole(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FRuntimeFrameConsole::Setup(){
   FUiControlConsole* pControlConsole = RFaceManager::Instance().ControlConsole();
   // 注册缓冲池
   pControlConsole->PoolRegister(MO_CREATE(FFmRuntimeBarPool));
   pControlConsole->PoolRegister(MO_CREATE(FFmRuntimeWindowPool));
   return ESuccess;
}

MO_NAMESPACE_END
