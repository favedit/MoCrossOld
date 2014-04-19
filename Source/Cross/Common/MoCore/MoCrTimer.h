#ifndef __MO_CR_TIMER_H__
#define __MO_CR_TIMER_H__

#ifndef __MO_CM_SINGLETON_H__
#include <MoCmSingleton.h>
#endif // __MO_CM_SINGLETON_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>时间控制台。</T>
// <P>时间用浮点数表示，单位为秒。</P>
//============================================================
class MO_CR_DECLARE FTimerConsole : public FConsole{
private:
   TBool _useLargeTime;
   TFloat _lastTime;
   TFloat _deltaTime;
   TInt64 _oneSecondTicks;
   TFloat _oneSecondPsTicks;
   TInt64 _startCounts;
   TFloat _startTime;
public:
   FTimerConsole();
   MO_ABSTRACT( ~FTimerConsole() );
public:
   TFloat CurrentTime();
   TFloat LastTime();
   TFloat DeltaTime();
public:
   void Update();
   void Reset();
};

//============================================================
// <T>时间管理器。</T>
//============================================================
class MO_CR_DECLARE RTimerManager : public RSingleton<FTimerConsole>{
public:
   static TFloat CurrentTime();
   static TFloat LastTime();
   static TFloat DeltaTime();
public:
   static void Update();
   static void Reset();
};

MO_NAMESPACE_END

#endif // __MO_CR_TIMER_H__
