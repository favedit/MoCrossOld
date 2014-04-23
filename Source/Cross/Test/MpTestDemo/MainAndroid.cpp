#ifdef _MO_ANDROID
#include <MoEngine.h>
#include <MoEngineFace.h>
#include <MoEngineRender.h>
#include <MoPlatformAndroid.h>
#include <MoPlatformOpenGLES2.h>
#include <MoGameEngine.h>
#include "MoTestLogic.h"
#include "MpMain.h"

MO_NAMESPACE_INCLUDE;

#ifdef __cplusplus
extern "C" {
#endif

#define MO_RESOURCE_CODE 910010001

typedef TVector<FTemplate3d*> TTemplate3dVector;

TTimeTick g_lastTick = 0;
TTimeTick g_templateTick = 0;
FPerspectiveProjection* g_pProjection = NULL;
TTemplate3dVector g_pTemplates;

static TThreadLocker _locker;

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
      result = DoParticle(x, y);
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
TResult OnEnterFrame(SFrameEvent* pEvent){
   TTimeTick currentTick = RTimeTick::Current();
   if(g_templateTick == 0){
      g_lastTick = currentTick;
      g_templateTick = currentTick;
      return ESuccess;
   }
   TTimeSpan span = currentTick - g_templateTick;
   TFloat rate = (TFloat)span / 1000000.0f;
   TTemplate3dVector::TIteratorC iterator = g_pTemplates.IteratorC();
   TInt n = 0;
   while(iterator.Next()){
      FTemplate3d* pTemplate = *iterator;
      pTemplate->MatrixModel().SetRotation(0.0f, MO_PI_FLOAT / 180.0f * 30.0f * n + rate * 0.7f, 0.0f);
      pTemplate->MatrixModel().UpdateForce();
      n++;
   }
   // 显示统计信息
   TTimeSpan spanSecond = (currentTick - g_lastTick) / 1000000;
   if(spanSecond >= 5){
      RStatisticsManager::Instance().Track();
      FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
      FRenderStatistics* pStatistics = pRenderDevice->Statistics();
      pStatistics->Track();
      g_lastTick = currentTick;
   }
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//============================================================
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* pJavaVM, void* pReserved){
	return JNI_VERSION_1_4;
}

//============================================================
// <T>初始化处理。</T>
//
// @param pEnvironment 环境
// @param pObject 对象
//============================================================
JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_initialize(JNIEnv * pEnvironment, jobject pObject, jobject assetManager){
   // 初始化处理
   //MoInitialize();
   //MoCoreInitialize();
   //MoFeatureGraphicsInitialize();
   //MoEngineInitialize();
   //MoEngine3dInitialize();
   //MoEngineFaceInitialize();
   //MoEngineOpenGLES2Initialize();
   //MoPlatformAndroidInitialize();
   MoGameEngineInitialize();
   //............................................................
   // 设置资源管理器
   FEaAssetConsole* pAssetConsole = (FEaAssetConsole*)RAssetManager::InstancePtr();
   pAssetConsole->Link(pEnvironment, assetManager);
   pAssetConsole->Setup();
}

//============================================================
// <T>设置环境。</T>
//
// @param pEnvironment 环境
// @param pObject 对象
// @param code 代码
// @param value 内容
//============================================================
JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_setEnvironment(JNIEnv* pEnvironment, jobject pObject, jstring code, jstring value){
   // 获得内容
   TCharC* pCode = pEnvironment->GetStringUTFChars(code, NULL);
   if(pCode == NULL){
      return;
   }
   TCharC* pValue = pEnvironment->GetStringUTFChars(value, NULL);
   if(pValue == NULL){
      return;
   }
   MO_STATIC_INFO("Set environment. (code=%s, value=%s)", pCode, pValue);
   // 设置屏幕宽度
   if(RString::Equals(pCode, "screen.width")){
      FScreenDevice* pScreenDevice = RDeviceManager::Instance().Find<FScreenDevice>();
      pScreenDevice->Size().width = RInt::Parse(pValue);
   }
   // 设置屏幕高度
   if(RString::Equals(pCode, "screen.height")){
      FScreenDevice* pScreenDevice = RDeviceManager::Instance().Find<FScreenDevice>();
      pScreenDevice->Size().height = RInt::Parse(pValue);
   }
   // 释放内容
   pEnvironment->ReleaseStringUTFChars(code, pCode);
   pEnvironment->ReleaseStringUTFChars(value, pValue);
}

//============================================================
// <T>配置处理。</T>
//
// @param pEnvironment 环境
// @param pObject 对象
//============================================================
JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_setup(JNIEnv * pEnvironment, jobject pObject){
   // 更改屏幕大小
   FScreenDevice* pScreenDevice = RDeviceManager::Instance().Find<FScreenDevice>();
   SIntSize2& screenSize = pScreenDevice->Size();;
   MO_STATIC_INFO("Native api setup. (width=%d, height=%d)", screenSize.width, screenSize.height);
   // 更改画板大小
   //............................................................
   //RTechniqueManager::Instance().Capability().optionInstance = ETrue;
   //RTechniqueManager::Instance().Capability().optionMerge = ETrue;
   // 配置设备
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   pRenderDevice->Setup();
   //pRenderDevice->SetBackBuffer(screenSize.width, screenSize.height, 1);
   // 初始化舞台
   MoGameEngineStartup();
   //............................................................
   // 初始化舞台
   //MoFeatureGraphicsStartup();
   //MoEngineStartup();
   //MoEngine3dStartup();
   //MoEngineOpenGLES2Startup();
   //MoEngineRenderStartup();
   //MoEngineFaceStartup();
   // 选择渲染方式
   //RPipelineManager::Instance().SelectPipeline("shadow");
   RPipelineManager::Instance().SelectPipeline("shadow");
   // 注册字体
   FEoFont* pFont = MO_CREATE(FEoFont);
   pFont->CharSize().Set(14, 14);
   //pFont->SetAssetName("asset:/ft/DroidSansFallback.ttf");
   //RFontManager::Instance().RegisterFont(pFont);
   //............................................................
   // 打开资源管理器
   RResource3dManager::Instance().ThemeConsole()->Open("asset:/theme/shadow.ser");
   //............................................................
   // 注册事件
   FMouseDevice* pMouseDevice = RDeviceManager::Instance().Find<FMouseDevice>();
   pMouseDevice->ListenersMouseDown().Register(&OnMouseDown);
   pMouseDevice->ListenersMouseMove().Register(&OnMouseMove);
   pMouseDevice->ListenersMouseUp().Register(&OnMouseUp);
   //............................................................
   // 激活舞台
   FGameScene* pScene = RGameSceneManager::Instance().Load("pvw.sc.car.01.001");
   //FGameScene* pScene = RGameSceneManager::Instance().Load("pvw.sc.courtyard.scene");
   pScene->ListenersFrameEnter().Register(&OnEnterFrame);
   RStageManager::Instance().SelectStage(pScene);
   // 开始时钟
   RDeviceManager::Instance().Find<FTimerDevice>()->Setup();
   MO_STATIC_INFO("Setup success");
}

//============================================================
// <T>设置大小。</T>
//
// @param pEnvironment 环境
// @param pObject 对象
//============================================================
JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_resize(JNIEnv * pEnvironment, jobject pObject, jint width, jint height){
   MO_STATIC_INFO("Native api resize. (width=%d, height=%d)", width, height);
   _locker.Enter();
   // 更改屏幕大小
   FScreenDevice* pScreenDevice = RDeviceManager::Instance().Find<FScreenDevice>();
   pScreenDevice->Resize(width, height);
   // 更改画板大小
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   pRenderDevice->SetBackBuffer(width, height, 1);
   // 设置投影
   if(g_pProjection != NULL){
      g_pProjection->Size().Set(width, height);
      g_pProjection->Update();
   }
   _locker.Leave();
}

//============================================================
// <T>循环处理。</T>
//
// @param pEnvironment 环境
// @param pObject 对象
//============================================================
JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_process(JNIEnv * pEnvironment, jobject pObject){
   // 引擎处理
   _locker.Enter();
   REngineManager::Instance().Process();
   _locker.Leave();
}

//============================================================
// <T>挂起处理。</T>
//
// @param pEnvironment 环境
// @param pObject 对象
//============================================================
JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_suspend(JNIEnv* pEnvironment, jobject pObject){
   MO_STATIC_INFO("Process suspend.");
   _locker.Enter();
   RDeviceManager::Instance().Suspend();
   REffectManager::Instance().Suspend();
   RGameSceneManager::Instance().Suspend();
   _locker.Leave();
}

//============================================================
// <T>继续处理。</T>
//
// @param pEnvironment 环境
// @param pObject 对象
//============================================================
JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_resume(JNIEnv* pEnvironment, jobject pObject){
   MO_STATIC_INFO("Process Resume.");
   _locker.Enter();
   RDeviceManager::Instance().Resume();
   REffectManager::Instance().Resume();
   RGameSceneManager::Instance().Resume();
   _locker.Leave();
}

//============================================================
// <T>释放处理。</T>
//
// @param pEnvironment 环境
// @param pObject 对象
//============================================================
JNIEXPORT void JNICALL Java_mo_android_api_RNativeApi_release(JNIEnv * pEnvironment, jobject pObject){
   MO_STATIC_INFO("Native api release.");
   MoGameEngineRelease();
   //MoPlatformAndroidRelease();
   //MoEngineOpenGLES2Release();
   //MoEngineFaceRelease();
   //MoEngineRelease();
   //MoFeatureGraphicsRelease();
   //MoCoreRelease();
   //MoRelease();
}

//============================================================
// <T>鼠标落下处理。</T>
//
// @param pEnvironment 环境
// @param pObject 对象
//============================================================
void JNICALL Java_mo_android_api_RNativeApi_onMouseDown(JNIEnv* pEnvironment, jobject pObject, jint buttonCd, jfloat x, jfloat y){
   _locker.Enter();
   RDeviceManager::Instance().Find<FMouseDevice>()->DoMouseDown(buttonCd, x, y);
   _locker.Leave();
}

//============================================================
// <T>鼠标移动处理。</T>
//
// @param pEnvironment 环境
// @param pObject 对象
//============================================================
void JNICALL Java_mo_android_api_RNativeApi_onMouseMove(JNIEnv* pEnvironment, jobject pObject, jint buttonCd, jfloat x, jfloat y){
   _locker.Enter();
   RDeviceManager::Instance().Find<FMouseDevice>()->DoMouseMove(buttonCd, x, y);
   _locker.Leave();
}

//============================================================
// <T>鼠标抬起处理。</T>
//
// @param pEnvironment 环境
// @param pObject 对象
//============================================================
void JNICALL Java_mo_android_api_RNativeApi_onMouseUp(JNIEnv* pEnvironment, jobject pObject, jint buttonCd, jfloat x, jfloat y){
   _locker.Enter();
   RDeviceManager::Instance().Find<FMouseDevice>()->DoMouseUp(buttonCd, x, y);
   _locker.Leave();
}

#ifdef __cplusplus
}
#endif

#endif // _MO_ANDROID
