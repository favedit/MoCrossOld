#ifdef _MO_WINDOWS
#include <Windows.h>
#include <Mmsystem.h>
#endif
//------------------------------------------------------------
#include "MoCrTimer.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T></T>
//============================================================
FTimerConsole::FTimerConsole(){
   _useLargeTime = EFalse;
   _lastTime = 0.0f;
   _deltaTime = 0.0f;
   // 获得开始时间
   _oneSecondTicks = 0;
   _oneSecondPsTicks = 0.0f;
   _startCounts = 0;
   _startTime = 0.0f;
   Reset();
}

//============================================================
FTimerConsole::~FTimerConsole(){
}

//============================================================
TFloat FTimerConsole::CurrentTime(){
   TFloat time = 0.0f;
#ifdef _MO_WINDOWS
   TUint64 currentCount;
   if(_useLargeTime){
      QueryPerformanceCounter((LARGE_INTEGER*)&currentCount);
      time = (currentCount - _startCounts) / _oneSecondPsTicks;
   }else{
      time = (TFloat)timeGetTime()/1000.0f - _startTime;
   }
#else
   TTimeTick tick = RTimeTick::Current() - _startCounts;
   time = ((TFloat)tick) / 1000000.0f;
#endif
   return time;
}

//============================================================
TFloat FTimerConsole::LastTime(){
   return _lastTime;
}

//============================================================
TFloat FTimerConsole::DeltaTime(){
   return _deltaTime;
}

//============================================================
void FTimerConsole::Update(){
   // 更新时间
   TFloat currentTime = CurrentTime();
   _deltaTime = currentTime - _lastTime;
   _lastTime = currentTime;
}

//============================================================
void FTimerConsole::Reset(){
#ifdef _MO_WINDOWS
   if(!QueryPerformanceFrequency((LARGE_INTEGER*)&_oneSecondTicks)){
      _useLargeTime = ETrue;
      _oneSecondPsTicks = (TFloat)_oneSecondTicks;
      QueryPerformanceCounter((LARGE_INTEGER*)&_startCounts);
   }else{
      _useLargeTime = EFalse;
      _startTime = timeGetTime() / 1000.0f;
   }
#else
   _startCounts = RTimeTick::Current();
#endif
}

MO_NAMESPACE_END
