#include "MoEgDevice.h"
#include "MoEgEngine.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEngineStatistics, FStatistics);

//============================================================
// <T>构造引擎统计器。</T>
//============================================================
FEngineStatistics::FEngineStatistics(){
}

//============================================================
// <T>析构引擎统计器。</T>
//============================================================
FEngineStatistics::~FEngineStatistics(){
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FEngineStatistics::Setup(){
   _frameStatistics = RStatisticsManager::Instance().SyncByName("engine.frame");
   _frameProcessBeforeStatistics = RStatisticsManager::Instance().SyncByName("engine.frame.before");
   _frameDrawStatistics = RStatisticsManager::Instance().SyncByName("engine.frame.draw");
   _frameProcessAfterStatistics = RStatisticsManager::Instance().SyncByName("engine.frame.after");
   return ESuccess;
}

MO_NAMESPACE_END
