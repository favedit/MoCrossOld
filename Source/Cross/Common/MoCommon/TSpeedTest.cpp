#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造速度测试器。</T>
//============================================================
TSpeedTest::TSpeedTest(){
   _beginTick = RTimeTick::Current();
   _endTick = _beginTick;
}

//============================================================
// <T>开始为摸个函数调用计时。</T>
//
// @param pDescription 调用函数名。
//============================================================
TSpeedTest::TSpeedTest(TCharC* pDescription){
   _beginTick = RTimeTick::Current();
   _endTick = _beginTick;
   _description = pDescription;
}

//============================================================
// <T>显示跟踪信息。</T>
//============================================================
void TSpeedTest::Track(){
   TTimeSpan span = TimeSpan();
   if(_description.IsEmpty()){
      MO_DEBUG(TC("Speed test track. (timespan=%d)"), span);
   }else{
      MO_DEBUG(TC("Speed test track. (timespan=%d, description=%s"), span, (TCharC*)_description);
   }
}

MO_NAMESPACE_END
