#include "MoEwWindow.h"

MO_NAMESPACE_BEGIN

static TBool g_runable = ETrue;

//============================================================
// <T>构造渲染窗口控制台。</T>
//============================================================
FRenderWindowConsole::FRenderWindowConsole(){
   _pWindows = MO_CREATE(FRenderWindowCollection);
}

//============================================================
// <T>析构渲染窗口控制台。</T>
//============================================================
FRenderWindowConsole::~FRenderWindowConsole(){
   MO_DELETE(_pWindows);
}

//============================================================
// <T>根据句柄查找窗口对象。</T>
//
// @param hWindow 窗口句柄
// @return 窗口对象
//============================================================
FRenderWindow* FRenderWindowConsole::FindByHandle(HWND hWindow){
   TInt count = _pWindows->Count();
   for(TInt n = 0; n < count; n++){
      FRenderWindow* pWindow = _pWindows->Get(n);
      if(pWindow->Handle() == hWindow){
         return pWindow;
      }
   }
   return NULL;
}

//============================================================
// <T>注册一个窗口对象。</T>
//
// @param pWindow 窗口
// @return 处理结果
//============================================================
TResult FRenderWindowConsole::Register(FRenderWindow* pWindow){
   MO_CHECK(pWindow, return ENull);
   _pWindows->Push(pWindow);
   return ESuccess;
}

//============================================================
// <T>注销一个窗口对象。</T>
//
// @param pWindow 窗口
// @return 处理结果
//============================================================
TResult FRenderWindowConsole::Unregister(FRenderWindow* pWindow){
   MO_CHECK(pWindow, return ENull);
   _pWindows->Remove(pWindow);
   return ESuccess;
}

MO_NAMESPACE_END
