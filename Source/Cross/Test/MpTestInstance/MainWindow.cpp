#include <ft2build.h>
#include FT_FREETYPE_H 
#include <MoFeatureGraphic.h>
#include <MoEngine.h>
#include <MoEngineFace.h>
#include <MoEngineRender.h>
#include <MoEngineOpenGL.h>
#include <MoEngineWindows.h>
#include <MoGameEngine.h>
#include "MoTestLogic.h"
#include "MpMain.h"

MO_NAMESPACE_INCLUDE;

#define MO_RESOURCE_CODE 910010001

typedef TLooper<FTemplate3d*> TTemplate3dLooper;

TTimeTick g_lastTick = 0;
TTimeTick g_lastMemoryTick = 0;
TTimeTick g_templateTick = 0;
TTemplate3dLooper g_pTemplates;

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
   if(pEvent->keyCode == EKeyCode_K){
      RMemory::Storage()->Enable();
      MO_STATIC_INFO("Memory open.");
   }
   if(pEvent->keyCode == EKeyCode_N){
      RMemory::Storage()->Reset();
      MO_STATIC_INFO("Memory reset.");
   }
   if(pEvent->keyCode == EKeyCode_M){
      RMemory::Storage()->Dump();
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
   TTimeTick currentTick = RTimeTick::Current();
   if(g_templateTick == 0){
      g_lastTick = currentTick;
      g_lastMemoryTick = currentTick;
      g_templateTick = currentTick;
      return ESuccess;
   }
   TTimeSpan span = currentTick - g_templateTick;
   TFloat rate = (TFloat)span / 1000000.0f;
   for(TInt n = 0; n < 256; n++){
      FTemplate3d* pTemplate = g_pTemplates.Next();
      pTemplate->MatrixModel().SetRotation(0.0f, MO_PI_FLOAT / 180.0f * 220.0f + rate * 1.0f, 0.0f);
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
   MoInitialize();
   MoCoreInitialize();
   MoFeatureGraphicsInitialize();
   MoEngineInitialize();
   MoEngine3dInitialize();
   MoEngineFaceInitialize();
   MoEngineOpenGLInitialize();
   MoEngineWindowsInitialize();
   MoGameEngineInitialize();
   //............................................................
   // 设置信息
   RApplication::Instance().Parameters()->Setup(lpszCmdLine);
   RApplication::Instance().SetHinstance(hInstance);
   RApplication::Instance().SetCommandShow(nCmdShow);
   RAssetManager::Instance().Setup();
   //............................................................
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
   SIntSize2 screenSize(500, 800);
   FRenderWindow* pWindow = MO_CREATE(FRenderWindow);
   pWindow->Size().Assign(screenSize);
   pWindow->Setup();
   // 获得屏幕尺寸
   FScreenDevice* pScreenDevice = RDeviceManager::Instance().Find<FScreenDevice>();
   //SIntSize2& screenSize = pScreenDevice->Size();
   RTechniqueManager::Instance().SetOptionInstance(EFalse);
   // 初始化渲染设备
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   pRenderDevice->Setup();
   // 初始化舞台
   MoFeatureGraphicsStartup();
   MoEngineStartup();
   MoEngine3dStartup();
   MoEngineOpenGLStartup();
   MoEngineRenderStartup();
   //MoEngineFaceStartup();
   // 选择渲染方式
   RPipelineManager::Instance().SelectPipeline("simple");
   RTechniqueManager::Instance().SetOptionInstance(ETrue);
   //RPipelineManager::Instance().SelectPipeline("shadow");
   // 注册字体
   FEoFont* pFont = FEoFont::InstanceCreate();
   pFont->CharSize().Set(14, 14);
   pFont->SetAssetName("asset:/font/DroidSansFallback.ttf");
   RFontManager::Instance().RegisterFont(pFont);
   //............................................................
   // 打开资源管理器
   RResource3dManager::Instance().ThemeConsole()->Open("asset:/theme/color.ser");
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
   // 创建舞台
   GPtr<FGameStage> stage = FGameStage::InstanceCreate();
   stage->Size().Assign(screenSize);
   stage->Setup();
   // 设置投影
   FPerspectiveProjection* pProjection = FPerspectiveProjection::InstanceCreate();
   pProjection->Size().Set(1080 >> 1, 1701 >> 1);
   pProjection->SetZ(0.1f, 500.0f);
   pProjection->SetAngle(30.0f);
   pProjection->Update();
   // 设置相机
   FCamera* pCamera = FPerspectiveCamera::InstanceCreate();
   pCamera->Position().Set(0.0f, 80.0f, -80.0f);
   pCamera->LookAt(0.0f, 0.0f, 0.0f);
   pCamera->SetProjection(pProjection);
   pCamera->Update();
   // 设置视角
   FViewport* pViewport = FViewport::InstanceCreate();
   pViewport->Set(0, 0, screenSize.width, screenSize.height);
   // 设置视角
   FRenderView* pView = FRenderView::InstanceCreate();
   pView->SetCamera(pCamera);
   pView->SetViewport(pViewport);
   stage->SceneFrame()->Views()->Push(pView);
   // 设置投影
   FPerspectiveProjection* pLightProjection = FPerspectiveProjection::InstanceCreate();
   pLightProjection->Size().Set(1024, 1024);
   pLightProjection->SetZ(0.1f, 1000.0f);
   pLightProjection->SetAngle(30.0f);
   pLightProjection->Update();
   // 设置光源相机
   FCamera* pLightCamera = FPerspectiveCamera::InstanceCreate();
   pLightCamera->Position().Set(200.0f, 100.0f, -80.0f);
   pLightCamera->LookAt(0.0f, 0.0f, 0.0f);
   pLightCamera->SetProjection(pLightProjection);
   pLightCamera->Update();
   // 设置光源视角
   FViewport* pLightViewport = FViewport::InstanceCreate();
   pLightViewport->Set(0, 0, 1024, 1024);
   // 设置光源
   FDirectionalLight* pLight = FDirectionalLight::InstanceCreate();
   pLight->SetCamera(pLightCamera);
   pLight->SetViewport(pLightViewport);
   pLight->Direction().Set(MO_PI_FLOAT / 4.0f, -MO_PI_FLOAT / 4.0f, MO_PI_FLOAT / 4.0f);
   stage->SetDirectionalLight(pLight);
   // 激活舞台
   RStageManager::Instance().SelectStage(stage);
   stage->ListenersFrameLeave().Register(&OnEnterFrame);
   //............................................................
   for(TInt y = 0; y < 20; y++){
      for(TInt x = 0; x < 20; x++){
         FTemplate3d* pTemplate = RInstance3dManager::Instance().TemplateConsole()->Alloc("entity.001.001");
         pTemplate->MatrixModel().SetTranslate(1.4f * x - 12.0f, 0.0f, 4.0f * y - 30.0f);
         pTemplate->MatrixModel().SetRotation(0.0f, MO_PI_FLOAT / 180.0f * 220.0f, 0.0f);
         pTemplate->MatrixModel().SetScale(0.03f, 0.03f, 0.03f);
         pTemplate->MatrixModel().UpdateForce();
         pTemplate->Animation()->SetBaseTick(10000000 * y + 100000 * x);
         stage->SpriteLayer()->DisplayPush(pTemplate);
         g_pTemplates.Push(pTemplate);
      }
   }
   //............................................................
   // 处理窗口
   pWindow->Startup();
   pWindow->Process();
  //............................................................
   MoFeatureGraphicsShutdown();
   MoEngine3dShutdown();
   MoEngineShutdown();
   //............................................................
   // 释放处理
   MoGameEngineRelease();
   MoEngineWindowsRelease();
   MoEngineOpenGLRelease();
   MoEngineFaceRelease();
   MoEngineRelease();
   MoFeatureGraphicsRelease();
   MoCoreRelease();
   RClassManager::Instance().TrackActive();
   MoRelease();
	return 0;
}