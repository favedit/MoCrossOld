#include "MoFmRuntime.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造运行窗口。</T>
//============================================================
FFmRuntimeWindow::FFmRuntimeWindow(){
   MO_CLEAR(_pCloseButton);
}

//============================================================
// <T>析构运行窗口。</T>
//============================================================
FFmRuntimeWindow::~FFmRuntimeWindow(){
}

//============================================================
// <T>关闭事件处理。</T>
//
// @param pEvent 事件
// @return 处理结果
//============================================================
TResult FFmRuntimeWindow::OnCloseClick(SMouseEvent* pEvent){
   Hide();
   return ESuccess;
}

//============================================================
// <T>配置后置处理。</T>
//
// @return 处理结果
//============================================================
TResult FFmRuntimeWindow::OnSetupAfter(){
   TResult result = FUiWindow::OnSetupAfter();
   //_pCloseButton = (FUiButton*)ChildGet("btnClose");
   //_pCloseButton->ListenersClick().Register<FFmRuntimeWindow>(this, &FFmRuntimeWindow::OnCloseClick);
   return result;
}

MO_NAMESPACE_END
