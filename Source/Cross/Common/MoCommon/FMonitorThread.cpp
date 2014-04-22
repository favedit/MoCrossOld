#include "MoCmMonitor.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造监视器线程。</T>
//============================================================
FMonitorThread::FMonitorThread(){
}

//============================================================
// <T>析构监视器线程。</T>
//============================================================
FMonitorThread::~FMonitorThread(){
}

//============================================================
// <T>析构构造监视器线程。</T>
//
// @return 处理结果
//============================================================
TResult FMonitorThread::Process(){
   // 获得属性
   TTimeSpan interval = _machine->Interval();
   // 循环处理
   while(!IsStop()){
      // 处理内容
      _machine->Process();
      // 休眠
      MO_LIB_SLEEP(interval);
   }
   return ESuccess;
}

MO_NAMESPACE_END
