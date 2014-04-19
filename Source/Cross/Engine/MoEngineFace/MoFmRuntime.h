#ifndef __MO_FM_RUNTIME_H__
#define __MO_FM_RUNTIME_H__
//************************************************************

#ifndef __MO_FM_COMMON_H__
#include "MoFmCommon.h"
#endif // __MO_FM_COMMON_H__

#ifndef __MO_FM_CORE_H__
#include "MoFmCore.h"
#endif // __MO_FM_CORE_H__

#ifndef __MO_FM_CONTROL_H__
#include "MoFmControl.h"
#endif // __MO_FM_CONTROL_H__

#ifndef __MO_FM_FRAME_H__
#include "MoFmFrame.h"
#endif // __MO_FM_FRAME_H__

MO_NAMESPACE_BEGIN

//============================================================
enum EFmRuntime{
   EFmRuntime_RuntimeBar    = 100100,
   EFmRuntime_RuntimeWindow = 100101,
};

//============================================================
// <T>运行状态栏。</T>
//============================================================
class MO_FM_DECLARE FFmRuntimeBar : public FUiBar{
protected:
   FUiLabel* _pInfoLabel;
   FUiButton* _pWindowButton;
   TTimeTick _lastRefreshTick;
public:
   FFmRuntimeBar();
   MO_ABSTRACT ~FFmRuntimeBar();
public:
   TResult OnRuntimeWindowClick(SMouseEvent* pEvent);
public:
   MO_OVERRIDE TResult OnSetupAfter();
   MO_OVERRIDE TResult OnProcessBefore();
public:
   TResult RefreshStatus();
   MO_OVERRIDE TResult Show();
};
//------------------------------------------------------------
MO_DEF_CONTROL_POOL(MO_FM_DECLARE, FFmRuntimeBarPool, FFmRuntimeBar, EControlType_Bar, "RuntimeBar");

//============================================================
// <T>运行窗口。</T>
//============================================================
class MO_FM_DECLARE FFmRuntimeWindow : public FUiWindow{
protected:
   FUiButton* _pCloseButton;
public:
   FFmRuntimeWindow();
   MO_ABSTRACT ~FFmRuntimeWindow();
public:
   TResult OnCloseClick(SMouseEvent* pEvent);
public:
   MO_OVERRIDE TResult OnSetupAfter();
};
MO_DEF_CONTROL_POOL(MO_FM_DECLARE, FFmRuntimeWindowPool, FFmRuntimeWindow, EControlType_Window, "RuntimeWindow");

//============================================================
// <T>运行界面控制台。</T>
//============================================================
class MO_FM_DECLARE FRuntimeFrameConsole : public FConsole{
protected:
public:
   FRuntimeFrameConsole();
   MO_ABSTRACT ~FRuntimeFrameConsole();
public:
   TResult Setup();
};

//============================================================
// <T>运行界面管理器。</T>
//============================================================
class MO_FM_DECLARE RRuntimeFrameManager : public RSingleton<FRuntimeFrameConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FM_RUNTIME_H__
