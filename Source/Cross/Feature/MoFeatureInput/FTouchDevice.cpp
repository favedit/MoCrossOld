#include "MoFiTouch.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTouchDevice, FDevice);

//============================================================
// <T>构造触摸设备。</T>
//============================================================
FTouchDevice::FTouchDevice(){
   _name = "touch.device";
}

//============================================================
// <T>析构触摸设备。</T>
//============================================================
FTouchDevice::~FTouchDevice(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FTouchDevice::Setup(){
   return ESuccess;
}

MO_NAMESPACE_END
