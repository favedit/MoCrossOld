#include "MoEwWindow.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造渲染线程。</T>
//============================================================
FRenderThread::FRenderThread(){
   _interval = 10000;
   MO_CLEAR(_pWindow);
}

//============================================================
// <T>析构渲染线程。</T>
//============================================================
FRenderThread::~FRenderThread(){
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderThread::Process(){
   while(!IsStop()){
      TBool statusPause = RDeviceManager::Instance().StatusPause();
      if(!statusPause){
         _pWindow->ProcessRender();
      }
      SleepMicro(_interval);
   }
   return ESuccess;
}

MO_NAMESPACE_END
