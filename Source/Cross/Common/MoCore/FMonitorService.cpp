#include <MoCmMonitor.h>
#include "MoCrMonitor.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造监视器服务。</T>
//============================================================
FMonitorService::FMonitorService(){
   _name = TC("Service.Monitor");
   _pMachine = MO_CREATE(FMonitorMachine);
}

//============================================================
// <T>析构监视器服务。</T>
//============================================================
FMonitorService::~FMonitorService(){
   MO_DELETE(_pMachine);
}

//============================================================
// <T>处理服务加载设置事件。</T>
//
// @param pConfig 设置信息
// @return 处理结果
//============================================================
TResult FMonitorService::OnLoadConfig(FXmlNode* pConfig){
   _pMachine->LoadConfig(pConfig);
   return ESuccess;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonitorService::OnStartup(){
   _pMachine->Startup();
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonitorService::OnProcess(){
   _pMachine->Process();
   return ESuccess;
}

//============================================================
// <T>设置同步器。</T>
//
// @param pSection 同步器
//============================================================
void FMonitorService::SetSection(IThreadLocker* pLocker){
   _pMachine->SetLocker(pLocker);
}

//============================================================
// <T>执行处理。</T>
//============================================================
void FMonitorService::Execute(){
   _pMachine->Execute();
}

//============================================================
// <T>注册一个监视器。</T>
//
// @param pMonitor 监视器
//============================================================
void FMonitorService::Register(IMonitor* pMonitor){
   _pMachine->Register(pMonitor);
}

//============================================================
// <T>注销一个监视器。</T>
//
// @param pMonitor 监视器
//============================================================
void FMonitorService::Unregister(IMonitor* pMonitor){
   _pMachine->Unregister(pMonitor);
}

//============================================================
// <T>统计刷新。</T>
//
// @return 处理结果
//============================================================
TResult FMonitorService::StatisticsRefresh(){
   // 消息执行统计
   TFsDump dump;
   FMonitorList::TIteratorC iterator = _pMachine->Monitors()->IteratorC();
   while(iterator.Next()){
      FMonitor* pMonitor = (FMonitor*)*iterator;
      if(NULL != pMonitor){
         TSpeedStatistics& statistics = pMonitor->Statistics();
         dump.AppendFormat(TC("\n   ") MO_FMT_OBJECT_NAME TC(" count=%8d, tick=(%8d - %-8d) - %8d (failure=%16lld, slow=%16lld)"),
               pMonitor->Name(), statistics.Count(), statistics.MinTick(), statistics.MaxTick(),
               statistics.AverageTick(), statistics.FailureCount(), statistics.SlowCount());
      }
   }
   MO_INFO(TC("Monitor statistics. (count=%d)%s"), _pMachine->Monitors()->Count(), (TCharC*)dump);
   return ESuccess;
}

MO_NAMESPACE_END
