#include "MoEngineFace.h"
      
MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化界面引擎处理。</T>
//============================================================
void MoEngineFaceInitialize(){
   MO_STATIC_INFO("Engine face initialize.");
   // 初始化控件管理器
   RFaceManager::Create();
   RFaceManager::Instance().Setup();
   // 初始化运行管理器
   RRuntimeFrameManager::Create();
   // 初始化字体管理器
   RFontManager::Create(MO_CREATE(FEoFontConsole));
   RFontManager::Instance().Open();
}

//============================================================
// <T>启动界面引擎处理。</T>
//============================================================
void MoEngineFaceStartup(){
   MO_STATIC_INFO("Engine face startup.");
   // 注册缓冲池
   FUiControlConsole* pControlConsole = RFaceManager::Instance().ControlConsole();
   pControlConsole->PoolRegister(MO_CREATE(FUiLabelPool));
   pControlConsole->PoolRegister(MO_CREATE(FUiEditPool));
   pControlConsole->PoolRegister(MO_CREATE(FUiButtonPool));
   pControlConsole->PoolRegister(MO_CREATE(FUiPanelPool));
   pControlConsole->PoolRegister(MO_CREATE(FUiPagePool));
   pControlConsole->PoolRegister(MO_CREATE(FUiPageControlPool));
   pControlConsole->PoolRegister(MO_CREATE(FUiBarPool));
   pControlConsole->PoolRegister(MO_CREATE(FUiFormPool));
   pControlConsole->PoolRegister(MO_CREATE(FUiWindowPool));
   // 启动控件管理器
   RRuntimeFrameManager::Instance().Setup();
   //............................................................
   // 打开界面管理器
   RFaceManager::Instance().Open();
}

//============================================================
// <T>关闭界面引擎处理。</T>
//============================================================
void MoEngineFaceShutdown(){
   MO_STATIC_INFO("Engine face shutdown.");
}

//============================================================
// <T>释放界面引擎处理。</T>
//============================================================
void MoEngineFaceRelease(){
   MO_STATIC_INFO("Engine face release.");
   // 释放运行管理器
   RRuntimeFrameManager::Destroy();
   // 释放控件管理器
   RFaceManager::Destroy();
}

MO_NAMESPACE_END
