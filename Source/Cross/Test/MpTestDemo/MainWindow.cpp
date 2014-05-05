#include <ft2build.h>
#include FT_FREETYPE_H 
#include <MoFeatureLogger.h>
#include <MoFeatureGraphic.h>
#include <MoEngine.h>
#include <MoEngineFace.h>
#include <MoEngineRender.h>
#include <MoPlatformOpenGLES2.h>
//#include <MoPlatformDirectX10.h>
#include <MoPlatformDirectX11.h>
#include <MoPlatformWindows.h>
#include <MoGameEngine.h>
#include "MoTestLogic.h"
#include "MpMain.h"

MO_NAMESPACE_INCLUDE;

#define MO_RESOURCE_CODE 910010001

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
   // MO_STATIC_INFO("------------------------------------------------------------");
   // return ESuccess;
   TTimeTick currentTick = RTimeTick::Current();
   if(g_templateTick == 0){
      g_lastTick = currentTick;
      g_templateTick = currentTick;
      return ESuccess;
   }
   TTimeSpan span = currentTick - g_templateTick;
   TFloat rate = (TFloat)span / 1000000.0f;
   TTemplate3dVector::TIteratorC iterator = g_pTemplates.IteratorC();
   while(iterator.Next()){
      FTemplate3d* pTemplate = *iterator;
      pTemplate->MatrixModel().SetRotation(0.0f, MO_PI_FLOAT / 180.0f * 220.0f + rate * 0.7f, 0.0f);
      pTemplate->MatrixModel().UpdateForce();
   }
   // 显示统计信息
   TTimeSpan spanSecond = (currentTick - g_lastTick) / 1000000;
   if(spanSecond >= 5){
      RStatisticsManager::Instance().Track();
      RStatisticsManager::Instance().Reset();
      FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
      FRenderStatistics* pStatistics = pRenderDevice->Statistics();
      pStatistics->Track();
      pStatistics->Reset();
      g_lastTick = currentTick;
   }
   return ESuccess;
}

//============================================================
// <T>释放游戏库。</T>
//============================================================
TInt WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInst, LPSTR lpszCmdLine, TInt nCmdShow){
   // 初始化处理
   MoGameEngineInitialize();

   //TString value;
   //value.Append("float4 UniformGlobal;\n");
   //value.Append("float4 main( uniform float4 UniformParam ) : POSITION\n");
   //value.Append("{return UniformGlobal * UniformParam;}\n");
   //FPoRenderShaderTransformer* pTransformer = FPoRenderShaderTransformer::InstanceCreate();
   //pTransformer->Convert(NULL, &value);

   //MoEngineOpenGLES2();
   MoEngineDirectX11();
   RFeatureManager::Instance().Construct();
   //MoEngineDirectX10Initialize();
   //............................................................
   // 设置信息
   RApplication::Instance().Parameters()->Setup(lpszCmdLine);
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
   FRenderWindow* pWindow = MO_CREATE(FRenderDirectXWindow);
   //FRenderWindow* pWindow = MO_CREATE(FRenderOpenGLWindow);
   pWindow->Size().Assign(screenSize);
   pWindow->Setup();
   // 初始化渲染设备
   //FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   //FPd10RenderDevice* pRenderDevice = (FPd10RenderDevice*)RDeviceManager::Instance().Find<FRenderDevice>();
   FPd11RenderDevice* pRenderDevice = (FPd11RenderDevice*)RDeviceManager::Instance().Find<FRenderDevice>();
   pRenderDevice->SetWindowHandle(pWindow->Handle());
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
   //RFeatureManager::Instance().Release();
   //............................................................
   // 释放处理
   MoGameEngineRelease();
	return 0;
}