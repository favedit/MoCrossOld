#include "MoFmCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造控件对象控制台。</T>
//============================================================
FUiFaceConsole::FUiFaceConsole(){
   _pControlConsole = MO_CREATE(FUiControlConsole);
   _pFrameConsole = MO_CREATE(FUiFrameConsole);
}

//============================================================
// <T>析构显示对象控制台。</T>
//============================================================
FUiFaceConsole::~FUiFaceConsole(){
   MO_DELETE(_pControlConsole);
   MO_DELETE(_pFrameConsole);
}

//============================================================
// <T>配置处理</T>
//
// @return 处理结果
//============================================================
TResult FUiFaceConsole::Setup(){
   _pControlConsole->Setup();
   return ESuccess;
}

//============================================================
// <T>打开处理</T>
//
// @return 处理结果
//============================================================
TResult FUiFaceConsole::Open(){
   _pFrameConsole->Open();
   return ESuccess;
}

MO_NAMESPACE_END
