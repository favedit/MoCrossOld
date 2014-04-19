#ifdef _MO_LINUX
#include <unistd.h>
#endif
#include "MoCmMonitor.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造监视器线程。</T>
//============================================================
FMonitorThread::FMonitorThread(FMonitorMachine* pMachine){
   _pMachine = pMachine;
}

//============================================================
// <T>析构构造监视器线程。</T>
//
// @return 处理结果
//============================================================
TResult FMonitorThread::Process(){
   // 获得属性
   TTimeSpan interval = _pMachine->Interval();
   // 循环处理
   while(!IsStop()){
      // 处理内容
      _pMachine->Process();
      // 休眠
      MO_LIB_SLEEP(interval);
   }
   return ESuccess;
}

MO_NAMESPACE_END
