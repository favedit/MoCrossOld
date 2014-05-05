#include "MoEwWindow.h"

MO_NAMESPACE_BEGIN

static TBool g_runable = ETrue;

//============================================================
// <T>构造渲染窗口。</T>
//============================================================
FRenderWindow::FRenderWindow(){
   _className = "MoRenderWindow";
   _title = "Render window";
   _statusRenderable = EFalse;
   _size.Set(1280, 800);
   _handle = 0;
   _atom = 0;
   MO_CLEAR(_pRenderThread);
   _pParticleController = MO_CREATE(FParticleController);
}

//============================================================
// <T>析构渲染窗口。</T>
//============================================================
FRenderWindow::~FRenderWindow(){
   if(_pRenderThread != NULL){
      _pRenderThread->Stop();
      _pRenderThread = NULL;
   }
}

//============================================================
// <T>消息处理。</T>
//
// @param hWnd 窗口句柄
// @param message 消息代码
// @param wParam 短参数
// @param lParam 长参数
// @return 处理结果
//============================================================
LRESULT CALLBACK FRenderWindow::OnProcessMessage(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam){
   switch(message){
      case WM_DESTROY:
         g_runable = EFalse;
         PostQuitMessage(0);
         break;
      default:
         return DefWindowProc(hWnd, message, wParam, lParam);
   }
   return 0;
}

//============================================================
// <T>注册窗口样式类。</T>
//============================================================
void FRenderWindow::RegisterClass(){
   TInstance hInstance = RApplication::Hinstance();
   WNDCLASSEX wcex;
   wcex.cbSize       = sizeof(WNDCLASSEX);
   wcex.style			= CS_HREDRAW | CS_VREDRAW;
   wcex.lpfnWndProc	= (WNDPROC)OnProcessMessage;
   wcex.cbClsExtra	= 0;
   wcex.cbWndExtra	= 0;
   wcex.hInstance		= hInstance;
   wcex.hIcon	      = LoadIcon(hInstance, IDC_ARROW);
   wcex.hCursor		= LoadCursor(NULL, IDC_ARROW);
   wcex.hbrBackground = (HBRUSH)GetStockObject(BLACK_BRUSH);
   wcex.lpszMenuName	 = NULL;
   wcex.lpszClassName = _className.MemoryC();
   wcex.hIconSm		 = NULL;
   _atom = RegisterClassEx(&wcex);
}

//============================================================
// <T>设置处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderWindow::Setup(){
   // 注册窗口类
   RegisterClass();
   // 创建窗口
   TInstance hInstance = RApplication::Hinstance();
   _handle = CreateWindow((TCharC*)_className, (TCharC*)_title,
         WS_SYSMENU | WS_CAPTION | WS_VISIBLE,
         CW_USEDEFAULT, CW_USEDEFAULT, _size.width, _size.height,
         NULL, NULL, hInstance, NULL);
   MO_ASSERT(_handle);
   // 显示窗口
   TInt commandShow = RApplication::CommandShow();
   ShowWindow(_handle, commandShow);
   //初始化像素格式
   PIXELFORMATDESCRIPTOR descriptor = {
      sizeof(PIXELFORMATDESCRIPTOR),    // 上述格式描述符的大小
      1,                                // 版本号
      PFD_DRAW_TO_WINDOW |              // 格式支持窗口
      PFD_SUPPORT_OPENGL |              // 格式必须支持OpenGL
      PFD_DOUBLEBUFFER,                 // 必须支持双缓冲
      PFD_TYPE_RGBA,                    // 申请RGBA 格式
      24,                               // 选定色彩深度
      0, 0, 0, 0, 0, 0, 0, 0,           // 忽略RGBA
      0,                                // 无累加缓存
      0, 0, 0, 0,                       // 忽略聚集位
      32,                               // 32位Z-缓存(深度缓存)
      0,                                // 无蒙板缓存
      0,                                // 无辅助缓存
      PFD_MAIN_PLANE,                   // 主绘图层
      0,                                // Reserved
      0, 0, 0                           // 忽略层遮罩
   };
   // 获得DC
   _hDC = GetDC(_handle);
   // 选择一个最适合pfd描述的像素格式索引值
   TInt pixelFormat = ChoosePixelFormat(_hDC, &descriptor);
   SetPixelFormat(_hDC, pixelFormat, &descriptor);
   // 更新窗口
   UpdateWindow(_handle);
   // 设置窗口
   RECT rect;
   GetClientRect(_handle, &rect);
   FScreenDevice* pScreenDevice = RDeviceManager::Instance().Find<FScreenDevice>();
   pScreenDevice->Resize(rect.right, rect.bottom);
   return ESuccess;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderWindow::Startup(){
   // 创建渲染线程
   _pRenderThread = MO_CREATE(FRenderThread);
   _pRenderThread->SetWindow(this);
   _pRenderThread->Start();
   // 允许渲染
   _statusRenderable = ETrue;
   // 初始化时钟
   FTimerDevice* pTimerDevice = RDeviceManager::Instance().Find<FTimerDevice>();
   pTimerDevice->Setup();
   return ESuccess;
}

//============================================================
// <T>释放处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderWindow::Dispose(){
   _statusRenderable = EFalse;
   return ESuccess;
}

//============================================================
// <T>渲染处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderWindow::ProcessRender(){
   // 检查状态
   if(!g_runable){
      return EContinue;
   }
   if(!_statusRenderable){
      return EContinue;
   }
   //............................................................
   // 处理舞台
   _locker.Enter();
   REngineManager::Instance().Process();
   _locker.Leave();
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//============================================================
TInt FRenderWindow::Process(){
   MSG message;
   FMouseDevice* pMouseDevice = RDeviceManager::Instance().Find<FMouseDevice>();
   FKeyboardDevice* pKeyboardDevice = RDeviceManager::Instance().Find<FKeyboardDevice>();
   while(GetMessage(&message, NULL, 0, 0)){
      _locker.Enter();
      switch(message.message){
         case WM_LBUTTONDOWN:{
            POINTS point = MAKEPOINTS(message.lParam);
            pMouseDevice->DoMouseDown(EMouseButton_Left, point.x, point.y);
            break;
         }
         case WM_LBUTTONUP:{
            POINTS point = MAKEPOINTS(message.lParam);
            pMouseDevice->DoMouseUp(EMouseButton_Left, point.x, point.y);
            break;
         }
         case WM_MOUSEMOVE:{
            POINTS point = MAKEPOINTS(message.lParam);
            pMouseDevice->DoMouseMove(0, point.x, point.y);
            break;
         }
         case WM_KEYDOWN:{
            pKeyboardDevice->DoKeyDown(message.wParam);
            break;
         }
         case WM_KEYUP:{
            pKeyboardDevice->DoKeyUp(message.wParam);
            break;
         }
         case WM_DESTROY:{
            Dispose();
            PostQuitMessage(0);
            break;
         }
         default:{
            // 分发消息
            TranslateMessage(&message);
            DispatchMessage(&message);
            break;
         }
      }
      _locker.Leave();
   }
   // 停止线程
   _pRenderThread->Stop();
   return message.wParam;
}

MO_NAMESPACE_END
