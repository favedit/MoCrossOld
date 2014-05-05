#ifndef __MO_EW_WINDOW_H__
#define __MO_EW_WINDOW_H__

#ifndef __MO_EW_COMMON_H__
#include "MoEwCommon.h"
#endif // __MO_EW_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
class FRenderThread;
class FRenderWindow;

//============================================================
// <T>渲染窗口。</T>
//============================================================
class MO_EW_DECLARE FRenderWindow : public FObject{
protected:
   TFsName _className;
   TFsLabel _title;
   SIntSize2 _size;
   TThreadLocker _locker;
   TBool _statusRenderable;
   HWND _handle;
   ATOM _atom;
   HDC _hDC;
   FRenderThread* _pRenderThread;
   FParticleController* _pParticleController;
public:
   FRenderWindow();
   MO_ABSTRACT ~FRenderWindow();
protected:
   static LRESULT CALLBACK OnProcessMessage(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam);
   void RegisterClass();
public:
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SIntSize2& Size(){
      return _size;
   }
   //------------------------------------------------------------
   // <T>获得窗口句柄。</T>
   MO_INLINE HWND Handle(){
      return _handle;
   }
   //------------------------------------------------------------
   // <T>获得窗口原子。</T>
   MO_INLINE ATOM Atom(){
      return _atom;
   }
public:
   MO_ABSTRACT TResult Setup();
   MO_ABSTRACT TResult Dispose();
   MO_ABSTRACT TResult Startup();
public:
   MO_ABSTRACT TResult ProcessRender();
   TInt Process();
};

//============================================================
// <T>渲染DX窗口。</T>
//============================================================
class MO_EW_DECLARE FRenderDirectXWindow : public FRenderWindow{
public:
   FRenderDirectXWindow();
   MO_ABSTRACT ~FRenderDirectXWindow();
};

//============================================================
// <T>渲染OpenGL窗口。</T>
//============================================================
class MO_EW_DECLARE FRenderOpenGLWindow : public FRenderWindow{
protected:
   HGLRC _hRC;
public:
   FRenderOpenGLWindow();
   MO_ABSTRACT ~FRenderOpenGLWindow();
public:
   MO_OVERRIDE TResult Setup();
   MO_OVERRIDE TResult Dispose();
   MO_OVERRIDE TResult Startup();
public:
   MO_OVERRIDE TResult ProcessRender();
};

//============================================================
// <T>渲染线程。</T>
//============================================================
class MO_EW_DECLARE FRenderThread : public FThread{
protected:
   TInt _interval;
   FRenderWindow* _pWindow;
public:
   FRenderThread();
   MO_ABSTRACT ~FRenderThread();
public:
   //------------------------------------------------------------
   // <T>获得窗口对象。</T>
   MO_INLINE FRenderWindow* Window(){
      return _pWindow;
   }
   //------------------------------------------------------------
   // <T>设置窗口对象。</T>
   MO_INLINE void SetWindow(FRenderWindow* pWindow){
      _pWindow = pWindow;
   }
public:
   MO_OVERRIDE TResult Process();
};

MO_NAMESPACE_END

#endif //__MO_EW_WINDOW_H__
