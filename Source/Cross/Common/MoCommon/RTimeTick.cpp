#ifdef _MO_WINDOWS
#include <time.h>
#include <windows.h>
#else
#include <sys/time.h>
#endif
#include <sys/timeb.h>
#include <time.h>
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
#ifdef _MO_WINDOWS
TUint64 RTimeTick::_frequency = 0;
#endif

//============================================================
// <T>获得当前时间刻度(微秒)。</T>
//
// @return 时间刻度
//============================================================
TTimeTick RTimeTick::Current(){
#ifdef _MO_SYS_WINDOWS
   // 第一次初始化
   if(0 == _frequency){
      LARGE_INTEGER frequency;
      QueryPerformanceFrequency(&frequency);
      _frequency = frequency.QuadPart;
   }
   // 获得时钟(微妙)
   LARGE_INTEGER current;
   QueryPerformanceCounter(&current);
   return current.QuadPart * 1000000 / _frequency;
#endif // _MO_SYS_WINDOWS
#ifdef _MO_LINUX
   timeval current;
   gettimeofday(&current, NULL);
   TTimeTick result = current.tv_sec;
   result *= 1000000;
   result += current.tv_usec;
   return result;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   timeval current;
   gettimeofday(&current, NULL);
   TTimeTick result = current.tv_sec;
   result *= 1000000;
   result += current.tv_usec;
   return result;
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
   TDateTime current = 0;
   timespec spec;
   TInt result = clock_gettime(CLOCK_REALTIME, &spec);
   if(ESuccess == result){
      current = spec.tv_sec * 1000;
      current += spec.tv_nsec / 1000000;
   }
   return current;
#endif // _MO_FLASCC
}

//============================================================
// <T>格式化时间刻度。</T>
//
// @return 格式化字符串
//============================================================
TFsTimeTick RTimeTick::Format(TTimeTick tick){
   TFsTimeTick result;
   MO_LIB_STRING_FORMAT(result.Memory(), TFsTimeTick::Size(), TC("%llu.%06llus"), tick/1000000, tick%1000000);
   result.Fix();
   return result;
}

MO_NAMESPACE_END

