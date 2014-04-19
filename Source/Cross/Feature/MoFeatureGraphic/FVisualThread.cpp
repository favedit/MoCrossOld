#include "MoFgVisual.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造可见线程。</T>
//============================================================
FVisualThread::FVisualThread(){
   _interval = 10000;
   MO_CLEAR(_pConsole);
}

//============================================================
// <T>析构可见线程。</T>
//============================================================
FVisualThread::~FVisualThread(){
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FVisualThread::Process(){
   while(!IsStop()){
      _pConsole->Process();
      SleepMicro(_interval);
   }
   return ESuccess;
}

MO_NAMESPACE_END
