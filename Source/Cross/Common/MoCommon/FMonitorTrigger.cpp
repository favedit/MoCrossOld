#include "MoCmMonitor.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FMonitorTrigger, FMonitor);

//============================================================
// <T>构造监视器对象的实例。</T>
//
// @return 当前实例指针
//============================================================
FMonitorTrigger::FMonitorTrigger(){
   _name = TC("Monitor.Trigger");
   MO_CLEAR(_pTrigger);
}

//============================================================
// <T>析构监视器对象的实例。</T>
//
// @return 当前实例指针
//============================================================
FMonitorTrigger::~FMonitorTrigger(){
}

//============================================================
// <T>刷新处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonitorTrigger::Process(){
   MO_ASSERT(_pTrigger);
   return _pTrigger->TriggerRefresh(_currentTimeTick);
}

MO_NAMESPACE_END
