#define MO_RENDER_DX9
//#define MO_RENDER_DX11

#include <ft2build.h>
#include FT_FREETYPE_H 
#include <MoFeatureLogger.h>
#include <MoFeatureGraphic.h>
#include <MoEngine.h>
#include <MoEngineFace.h>
#include <MoEngineRender.h>
#ifdef MO_RENDER_DX9
#include <MoPlatformDirectX9.h>
#endif // MO_RENDER_DX9
#ifdef MO_RENDER_DX10
#include <MoPlatformDirectX10.h>
#endif // MO_RENDER_DX10
#ifdef MO_RENDER_DX11
#include <MoPlatformDirectX11.h>
#endif // MO_RENDER_DX11
#ifdef MO_RENDER_GLES2
#include <MoPlatformOpenGLES2.h>
#endif // MO_RENDER_GLES2
#include <MoPlatformWindows.h>
#include <MoGameEngine.h>
#include "MoTestLogic.h"
#include "MpMain.h"

#include <d3d11.h>
#include <d3dx11.h>
#include <d3dcompiler.h>
#include <xnamath.h>

MO_NAMESPACE_INCLUDE;

typedef TVector<FTemplate3d*> TTemplate3dVector;
TTimeTick g_lastTick = 0;
TTimeTick g_templateTick = 0;
TTemplate3dVector g_pTemplates;

//============================================================
// <T>鼠标事件处理。</T>
//
// @param pEvent 事件
// @return 处理结果
//============================================================
TResult OnMouseDown(SMouseEvent* pEvent){
   TFloat x = (TFloat)pEvent->position.x;
   TFloat y = (TFloat)pEvent->position.y;
   return DoParticle(x, y);
}

//============================================================
// <T>鼠标事件处理。</T>
//
// @param pEvent 事件
// @return 处理结果
//============================================================
TResult OnMouseMove(SMouseEvent* pEvent){
   TResult result = ESuccess;
   TFloat x = (TFloat)pEvent->position.x;
   TFloat y = (TFloat)pEvent->position.y;
   //if(RDeviceManager::Instance().MouseDevice()->TestMouseDownLeft()){
   //   result = DoParticle(x, y);
   //}
   return result;
}

//============================================================
// <T>鼠标事件处理。</T>
//
// @param pEvent 事件
// @return 处理结果
//============================================================
TResult OnMouseUp(SMouseEvent* pEvent){
   return ESuccess;
}

//============================================================
// <T>鼠标事件处理。</T>
//
// @param pEvent 事件
// @return 处理结果
//============================================================
TResult OnKeyDown(SKeyboardEvent* pEvent){
   if(pEvent->keyCode == EKeyCode_P){
      TBool pause = RDeviceManager::Instance().StatusPause();
      if(pause){
         RDeviceManager::Instance().Resume();
         REffectManager::Instance().Resume();
         RGameSceneManager::Instance().Resume();
      }else{
         RDeviceManager::Instance().Suspend();
         REffectManager::Instance().Suspend();
         RGameSceneManager::Instance().Suspend();
      }
   }
   return ESuccess;
}

//============================================================
// <T>鼠标事件处理。</T>
//
// @param pEvent 事件
// @return 处理结果
//============================================================
TResult OnEnterFrame(SFrameEvent* pEvent){
   //FPd9RenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FPd9RenderDevice>();
    //MO_STATIC_INFO("------------------------------------------------------------");
   //TTimeTick currentTick = RTimeTick::Current();
   //if(g_templateTick == 0){
   //   g_lastTick = currentTick;
   //   g_templateTick = currentTick;
   //   return ESuccess;
   //}
   //TTimeSpan span = currentTick - g_templateTick;
   //TFloat rate = (TFloat)span / 1000000.0f;
   //TTemplate3dVector::TIteratorC iterator = g_pTemplates.IteratorC();
   //while(iterator.Next()){
   //   FTemplate3d* pTemplate = *iterator;
   //   pTemplate->MatrixModel().SetRotation(0.0f, MO_PI_FLOAT / 180.0f * 220.0f + rate * 0.7f, 0.0f);
   //   pTemplate->MatrixModel().UpdateForce();
   //}
   //// 显示统计信息
   //TTimeSpan spanSecond = (currentTick - g_lastTick) / 1000000;
   //if(spanSecond >= 5){
   //   RStatisticsManager::Instance().Track();
   //   RStatisticsManager::Instance().Reset();
   //   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   //   FRenderStatistics* pStatistics = pRenderDevice->Statistics();
   //   pStatistics->Track();
   //   pStatistics->Reset();
   //   g_lastTick = currentTick;
   //}
   return ESuccess;
}

MO_EXTERN_C{
   typedef TResult (*CallSetupFeature)();
}

//============================================================
// <T>释放游戏库。</T>
//============================================================
TInt WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInst, LPSTR lpszCmdLine, TInt nCmdShow){
   // 初始化处理
   MoGameEngineInitialize();
#ifdef MO_RENDER_DX9
   MoEngineDirectX9();
#endif // MO_RENDER_DX9
#ifdef MO_RENDER_DX10
   MoEngineDirectX10();
#endif // MO_RENDER_DX10
#ifdef MO_RENDER_DX11
   MoEngineDirectX11();
#endif // MO_RENDER_DX11
#ifdef MO_RENDER_GLES2
   MoEngineOpenGLES2();
#endif // MO_RENDER_GLES2
   RFeatureManager::Instance().Construct();
   //............................................................
   // 设置信息
   RApplication::Instance().Parameters()->Setup(lpszCmdLine);
   //FApplicationParameter* pApplicationParameter = MO_CREATE(FApplicationParameter);
   //pApplicationParameter->SetName("-home");
   //pApplicationParameter->SetValue("E:/ZW-MoCross/Demo/Android/MpTestDemo/assets");
   //RApplication::Instance().Parameters()->Parameters()->Push(pApplicationParameter);
   RApplication::Instance().SetHinstance(hInstance);
   RApplication::Instance().SetCommandShow(nCmdShow);
   RAssetManager::Instance().Setup();
   //RTechniqueManager::Instance().Capability().optionInstance = ETrue;
   //RTechniqueManager::Instance().Capability().optionMerge = ETrue;
   //............................................................
   // 设置日志
   FNetLoggerWriter* pWriter = FNetLoggerWriter::InstanceCreate();
   pWriter->SetHost("127.0.0.1");
   pWriter->SetPort(9999);
   //pWriter->Open();
   //RLoggerFeature::Instance().NetLoggerConsole()->Register(pWriter);
   // 注册环境信息
   //TCharC* pHomePath = RApplication::Instance().Parameters()->FindValue("-home");
   //TCharC* pConfigName = RApplication::Instance().Parameters()->FindValue("-config");
   //TFsPath configFileName;
   //configFileName.AppendFormat("%s\\%s.xml", pHomePath, pConfigName);
   //GPtr<FXmlParser> xmlParser = FXmlParser::InstanceCreate();
   //GPtr<FXmlNode> configNode = xmlParser->LoadNodeFile(configFileName);
   //RConfigurationManager::Instance().LoadConfiguration(configFileName);
   //REnvironmentManager::Instance().Register("home", pHomePath);
   //............................................................
   // 创建窗口
   SIntSize2 screenSize(1200, 800);
#if defined(MO_RENDER_DX9) | defined(MO_RENDER_DX10) | defined(MO_RENDER_DX11)
   FRenderWindow* pWindow = MO_CREATE(FRenderDirectXWindow);
#endif // MO_RENDER_DIRECTX
#ifdef MO_RENDER_GLES2
   FRenderWindow* pWindow = MO_CREATE(FRenderOpenGLWindow);
#endif // MO_RENDER_GLES2
   pWindow->Size().Assign(screenSize);
   pWindow->Setup();
   // 初始化渲染设备
#if defined(MO_RENDER_DX9) | defined(MO_RENDER_DX10) | defined(MO_RENDER_DX11)
   FPdxRenderDevice* pRenderDevice = (FPdxRenderDevice*)RDeviceManager::Instance().Find<FRenderDevice>();
   pRenderDevice->SetWindowHandle(pWindow->Handle());
#endif // MO_RENDER_DIRECTX
#ifdef MO_RENDER_GLES2
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
#endif // MO_RENDER_GLES2
   pRenderDevice->Setup();
   // 初始化舞台
   MoGameEngineStartup();
   RFeatureManager::Instance().Startup();
   // 选择渲染方式
   RPipelineManager::Instance().SelectPipeline("simple");
   //RPipelineManager::Instance().SelectPipeline("shadow");
   // 注册字体
   FEoFont* pFont = FEoFont::InstanceCreate();
   pFont->CharSize().Set(14, 14);
   pFont->SetAssetName("asset:/font/DroidSansFallback.ttf");
   RFontManager::Instance().RegisterFont(pFont);
   //............................................................
   // 打开资源管理器
   RResource3dManager::Instance().ThemeConsole()->Open("asset:/theme/color.ser");
   //RResource3dManager::Instance().ThemeConsole()->Open("asset:/theme/shadow.ser");
   //RGmResourceManager::Instance().Open();
   //RGmTemplateConsole::Instance().Open();
   //............................................................
   // 注册事件
   FMouseDevice* pMouseDevice = RDeviceManager::Instance().Find<FMouseDevice>();
   pMouseDevice->ListenersMouseDown().Register(&OnMouseDown);
   pMouseDevice->ListenersMouseMove().Register(&OnMouseMove);
   pMouseDevice->ListenersMouseUp().Register(&OnMouseUp);
   FKeyboardDevice* pKeyboardDevice = RDeviceManager::Instance().Find<FKeyboardDevice>();
   pKeyboardDevice->ListenersKeyDown().Register(&OnKeyDown);
   //............................................................
   // 激活舞台
   //RInstance3dManager::Instance().TemplateConsole()->Alloc("pvw.sc.courtyard.item.02.022");
   //FGameScene* pScene = RGameSceneManager::Instance().Load("pvw.show.item.001");
   FGameScene* pScene = RGameSceneManager::Instance().Load("pvw.sc.car.01.001");
   //FGameScene* pScene = RGameSceneManager::Instance().Load("pvw.sc.car.01.002");
   //FGameScene* pScene = RGameSceneManager::Instance().Load("pvw.sc.car.01.003");
   //FGameScene* pScene = RGameSceneManager::Instance().Load("pvw.sc.house.01.scene");
   //FGameScene* pScene = RGameSceneManager::Instance().Load("pvw.show.skeleton.01");
   //FGameScene* pScene = RGameSceneManager::Instance().Load("pvw.show.skeleton.02");
   //FGameScene* pScene = RGameSceneManager::Instance().Load("pvw.show.skeleton.05");
   //FGameScene* pScene = RGameSceneManager::Instance().Load("pvw.sc.courtyard.scene");
   pScene->ListenersFrameEnter().Register(&OnEnterFrame);
   RStageManager::Instance().SelectStage(pScene);
    //............................................................
   // 处理窗口
   pWindow->Startup();
   pWindow->Process();
   //............................................................
   MoGameEngineShutdown();
   RFeatureManager::Instance().Shutdown();
   //............................................................
   // 释放处理
   RFeatureManager::Instance().Dispose();
   MoGameEngineRelease();
	return 0;
}