#include "MoEgDevice.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScreenDevice, FDevice);

//============================================================
// <T>构造屏幕设备。</T>
//============================================================
FScreenDevice::FScreenDevice(){
   _name = "screen.device";
}

//============================================================
// <T>析构屏幕设备。</T>
//============================================================
FScreenDevice::~FScreenDevice(){
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FScreenDevice::Setup(){
   return ESuccess;
}

//============================================================
// <T>改变大小。</T>
//
// @param width 宽度
// @param height 高度
//============================================================
TResult FScreenDevice::Resize(TInt width, TInt height){
   // 设置idaxiao
   MO_INFO("Screen resize. (width=%d, height=%d)", width, height);
   _size.Set(width, height);
   _rectangle.SetSize(width, height);
   // 分发处理
   SResizeEvent event(this, width, height);
   _listenersResize.Process(&event);
   return ESuccess;
}

MO_NAMESPACE_END
