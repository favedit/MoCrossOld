#include "MoCrDevice.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTimerDevice, FDevice);

//============================================================
// <T>构造时间设备。</T>
//============================================================
FTimerDevice::FTimerDevice(){
   _name = TC("timer.device");
   _frameCount = 0;
   _startTick = 0;
   _currentTick = 0;
}

//============================================================
// <T>析构时间设备。</T>
//============================================================
FTimerDevice::~FTimerDevice(){
}

//============================================================
// <T>获得每秒帧数。</T>
//
// @return 帧数
//============================================================
TInt FTimerDevice::FramePerSecond(){
   if(_frameCount == 0){
      return 0;
   }
   TTimeSpan span = (_currentTick - _startTick);
   return (TInt)(1000000 / (span / _frameCount));
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FTimerDevice::Setup(){
   _frameCount = 0;
   _startTick = RTimeTick::Current();
   _currentTick = _startTick;
   return ESuccess;
}

//============================================================
// <T>更新处理。</T>
//
// @return 处理结果
//============================================================
TResult FTimerDevice::Update(){
   _frameCount++;
   _currentTick = RTimeTick::Current();
   return ESuccess;
}

MO_NAMESPACE_END
