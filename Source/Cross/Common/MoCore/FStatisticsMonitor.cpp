#include "MoCrStatistics.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造统计信息监视器。</T>
//============================================================
FStatisticsMonitor::FStatisticsMonitor(){
   _name = TC("Core.Statistics.Monitor");
   MO_CLEAR(_pConsole);
}

//============================================================
// <T>析构统计信息监视器。</T>
//============================================================
FStatisticsMonitor::~FStatisticsMonitor(){
}

//============================================================
// <T>刷新处理。</T>
//
// @return 处理结果
//============================================================
TResult FStatisticsMonitor::Process(){
   TResult result = ESuccess;
   if(_pConsole != NULL){
      result = _pConsole->TriggerRefresh(_currentTimeTick);
   }
   return result;
}

MO_NAMESPACE_END
