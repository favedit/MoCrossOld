#include <ft2build.h>
#include FT_FREETYPE_H 
#include <MoFeatureLogger.h>
#include <MoFeatureGraphic.h>
#include <MoEngine.h>
#include <MoEngineFace.h>
#include <MoEngineRender.h>
#include <MoPlatformOpenGLES2.h>
#include <MoPlatformDirectX9.h>
//#include <MoPlatformDirectX10.h>
//#include <MoPlatformDirectX11.h>
#include <MoPlatformWindows.h>
#include <MoGameEngine.h>
#include "MoTestLogic.h"
#include "MpMain.h"

#include <d3d11.h>
#include <d3dx11.h>
#include <d3dcompiler.h>
#include <xnamath.h>

MO_NAMESPACE_INCLUDE;

struct CUSTOMVERTEX
{
    FLOAT x, y, z;      // The untransformed, 3D position for the vertex
    DWORD color;        // The vertex color
};

// Our custom FVF, which describes our custom vertex structure
#define D3DFVF_CUSTOMVERTEX (D3DFVF_XYZ|D3DFVF_DIFFUSE)
LPDIRECT3D9             g_pD3D = NULL; // Used to create the D3DDevice
LPDIRECT3DDEVICE9       g_pd3dDevice = NULL; // Our rendering device
LPDIRECT3DVERTEXBUFFER9 g_pVB = NULL; // Buffer to hold vertices

typedef TVector<FTemplate3d*> TTemplate3dVector;
TTimeTick g_lastTick = 0;
TTimeTick g_templateTick = 0;
TTemplate3dVector g_pTemplates;

void SetupMatrices()
{
    // For our world matrix, we will just rotate the object about the y-axis.
    D3DXMATRIXA16 matWorld;

    // Set up the rotation matrix to generate 1 full rotation (2*PI radians) 
    // every 1000 ms. To avoid the loss of precision inherent in very high 
    // floating point numbers, the system time is modulated by the rotation 
    // period before conversion to a radian angle.
    UINT iTime = RTimeTick::Current() / 1000;
    FLOAT fAngle = iTime * ( 2.0f * D3DX_PI ) / 1000.0f;
    D3DXMatrixRotationY( &matWorld, fAngle );
    g_pd3dDevice->SetTransform( D3DTS_WORLD, &matWorld );

    // Set up our view matrix. A view matrix can be defined given an eye point,
    // a point to lookat, and a direction for which way is up. Here, we set the
    // eye five units back along the z-axis and up three units, look at the
    // origin, and define "up" to be in the y-direction.
    D3DXVECTOR3 vEyePt( 0.0f, 3.0f,-5.0f );
    D3DXVECTOR3 vLookatPt( 0.0f, 0.0f, 0.0f );
    D3DXVECTOR3 vUpVec( 0.0f, 1.0f, 0.0f );
    D3DXMATRIXA16 matView;
    D3DXMatrixLookAtLH( &matView, &vEyePt, &vLookatPt, &vUpVec );
    g_pd3dDevice->SetTransform( D3DTS_VIEW, &matView );

    // For the projection matrix, we set up a perspective transform (which
    // transforms geometry from 3D view space to 2D viewport space, with
    // a perspective divide making objects smaller in the distance). To build
    // a perpsective transform, we need the field of view (1/4 pi is common),
    // the aspect ratio, and the near and far clipping planes (which define at
    // what distances geometry should be no longer be rendered).
    D3DXMATRIXA16 matProj;
    D3DXMatrixPerspectiveFovLH( &matProj, D3DX_PI / 4, 1.0f, 1.0f, 100.0f );
    g_pd3dDevice->SetTransform( D3DTS_PROJECTION, &matProj );
}

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
   FPd9RenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FPd9RenderDevice>();

   //pRenderDevice->Clear();

   //HRESULT dxResult;
   // // Begin the scene

   //SetupMatrices();
   //dxResult = g_pd3dDevice->SetStreamSource( 0, g_pVB, 0, sizeof( CUSTOMVERTEX ) );
   //dxResult = g_pd3dDevice->SetFVF( D3DFVF_CUSTOMVERTEX );
   //pRenderDevice->FrameBegin();
   //dxResult = g_pd3dDevice->DrawPrimitive( D3DPT_TRIANGLESTRIP, 0, 1 );
   //pRenderDevice->FrameEnd();
   //pRenderDevice->CheckError(dxResult, "", "");

   //pRenderDevice->Present();
    
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

//============================================================
// <T>释放游戏库。</T>
//============================================================
TInt WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInst, LPSTR lpszCmdLine, TInt nCmdShow){
   // 初始化处理
   MoGameEngineInitialize();
   MoEngineDirectX9();
   //MoEngineDirectX10();
   //MoEngineDirectX11();
   //MoEngineOpenGLES2();
   RFeatureManager::Instance().Construct();
   //............................................................
   // 设置信息
   //RApplication::Instance().Parameters()->Setup(lpszCmdLine);
   FApplicationParameter* pApplicationParameter = MO_CREATE(FApplicationParameter);
   pApplicationParameter->SetName("-home");
   pApplicationParameter->SetValue("E:/ZW-MoCross/Demo/Android/MpTestDemo/assets");
   RApplication::Instance().Parameters()->Parameters()->Push(pApplicationParameter);
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
   FPd9RenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FPd9RenderDevice>();
   //FPd10RenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FPd10RenderDevice>();
   //FPd11RenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FPd11RenderDevice>();
   pRenderDevice->SetWindowHandle(pWindow->Handle());
   pRenderDevice->Setup();
   g_pd3dDevice = pRenderDevice->NativeDevice();
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
   FGameScene* pScene = RGameSceneManager::Instance().Load("pvw.show.item.001");
   //FGameScene* pScene = RGameSceneManager::Instance().Load("pvw.sc.car.01.001");
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
    // Initialize three vertices for rendering a triangle
    CUSTOMVERTEX g_Vertices[] =
    {
        { -1.0f,-1.0f, 0.0f, 0xffff0000, },
        {  1.0f,-1.0f, 0.0f, 0xff0000ff, },
        {  0.0f, 1.0f, 0.0f, 0xffffffff, },
    };

    // Create the vertex buffer.
    if( FAILED( g_pd3dDevice->CreateVertexBuffer( 3 * sizeof( CUSTOMVERTEX ),
                                                  0, D3DFVF_CUSTOMVERTEX,
                                                  D3DPOOL_DEFAULT, &g_pVB, NULL ) ) )
    {
        return E_FAIL;
    }

    // Fill the vertex buffer.
    VOID* pVertices;
    if( FAILED( g_pVB->Lock( 0, sizeof( g_Vertices ), ( void** )&pVertices, 0 ) ) )
        return E_FAIL;
    memcpy( pVertices, g_Vertices, sizeof( g_Vertices ) );
    g_pVB->Unlock();

        // Turn off culling, so we see the front and back of the triangle
    g_pd3dDevice->SetRenderState( D3DRS_CULLMODE, D3DCULL_NONE );

    // Turn off D3D lighting, since we are providing our own vertex colors
    g_pd3dDevice->SetRenderState( D3DRS_LIGHTING, FALSE );
    //............................................................
   // 处理窗口
   pWindow->Startup();
   pWindow->Process();
   //............................................................
   MoGameEngineShutdown();
   RFeatureManager::Instance().Shutdown();
   //............................................................
   // 释放处理
   MoGameEngineRelease();
	return 0;
}