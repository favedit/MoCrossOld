#include "MoEwWindow.h"

MO_NAMESPACE_BEGIN

static TBool g_runable = ETrue;

//============================================================
// <T>构造渲染窗口。</T>
//============================================================
FRenderOpenGLWindow::FRenderOpenGLWindow(){
   _title = "Render OpenGL";
}

//============================================================
// <T>析构渲染窗口。</T>
//============================================================
FRenderOpenGLWindow::~FRenderOpenGLWindow(){
}

//============================================================
// <T>设置处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderOpenGLWindow::Setup(){
   TResult resultCd = FRenderWindow::Setup();
   // 创建RC
   _hRC = wglCreateContext(_hDC);
   TBool makeResult = wglMakeCurrent(_hDC, _hRC);
   MO_FATAL_CHECK(makeResult, return EFailure, "wglMakeCurrent failure.");
   return resultCd;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderOpenGLWindow::Startup(){
   TBool makeResult = wglMakeCurrent(NULL, NULL);
   MO_FATAL_CHECK(makeResult, return EFailure, "wglMakeCurrent failure.");
   return FRenderWindow::Startup();
}

//============================================================
// <T>释放处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderOpenGLWindow::Dispose(){
   if(_hRC != NULL){
      wglDeleteContext(_hRC);
      _hRC = NULL;
   }
   if(_hDC != NULL){
      ReleaseDC(_handle, _hDC);
      _hDC = NULL;
   }
   return FRenderWindow::Dispose();
}

//============================================================
// <T>渲染处理。</T>
//============================================================
TResult FRenderOpenGLWindow::ProcessRender(){
   // 检查状态
   if(!g_runable){
      return EContinue;
   }
   if(!_statusRenderable){
      return EContinue;
   }
   //............................................................
   _locker.Enter();
   // 选择RC作为当前线程的RC
   TBool result = wglMakeCurrent(_hDC, _hRC);
   if(!result){
      return EContinue;
   }
   MO_FATAL_CHECK(result, return EFailure, "wglMakeCurrent failure.");
   // 舞台管理器处理
   if(!g_runable){
      return EContinue;
   }
   // 处理舞台
   REngineManager::Instance().Process();
   //交换当前缓冲区和后台缓冲区
   if(!g_runable){
      return EContinue;
   }
   result = SwapBuffers(_hDC);
   MO_FATAL_CHECK(result, return EFailure, "SwapBuffers failure.");
   // 取消当前线程选中的RC
   result = wglMakeCurrent(NULL, NULL);
   MO_FATAL_CHECK(result, return EFailure, "wglMakeCurrent failure.");
   _locker.Leave();
   return ESuccess;
}

MO_NAMESPACE_END
