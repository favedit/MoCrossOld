#include "MoCrStatistics.h"
#include "MoCrMonitor.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造统计信息控制台。</T>
//============================================================
FStatisticsConsole::FStatisticsConsole(){
   _pStatisticsDictionary = MO_CREATE(FStatisticsDictionary);
   _pStatisticsSet = MO_CREATE(FStatisticsSet);
   _pStatisticsTriggers = MO_CREATE(FStatisticsTriggerList);
   _pMonitor = MO_CREATE(FStatisticsMonitor);
}

//============================================================
// <T>析构统计信息控制台。</T>
//============================================================
FStatisticsConsole::~FStatisticsConsole(){
   MO_DELETE(_pStatisticsDictionary);
   MO_DELETE(_pStatisticsSet);
   MO_DELETE(_pStatisticsTriggers);
   MO_DELETE(_pMonitor);
}

//============================================================
// <T>根据名称同步统计信息。</T>
//
// @param pName 名称
// @param pClass 类对象
// @return 统计器
//============================================================
FStatistics* FStatisticsConsole::SyncByName(TCharC* pName, FClass* pClass){
   MO_ASSERT(pName);
   FStatistics* pStatistics = _pStatisticsDictionary->Find(pName);
   if(pStatistics == NULL){
      if(pClass == NULL){
         pClass = FStatistics::Class();
      }
      pStatistics = (FStatistics*)pClass->InstanceAlloc();
      pStatistics->SetName(pName);
      _pStatisticsDictionary->Set(pName, pStatistics);
   }
   return pStatistics;
}

//============================================================
// <T>根据统计编号查找一个统计器。</T>
//
// @param code 统计编号
// @return 统计器
//============================================================
IStatistics* FStatisticsConsole::StatisticsFind(TStatisticsId code){
   return _pStatisticsSet->Find(code);
}

//============================================================
// <T>根据分组编号和项目编号查找一个统计器。</T>
//
// @param groupId 分组编号
// @param itemId 项目编号
// @return 统计器
//============================================================
IStatistics* FStatisticsConsole::StatisticsFind(TInt groupId, TInt itemId){
   SStatisticsId code((TUint16)groupId, (TUint16)itemId);
   return _pStatisticsSet->Find(code);
}

//============================================================
// <T>根据统计编号查找一个同步统计器。</T>
//
// @param code 统计编号
// @return 统计器
//============================================================
IStatistics* FStatisticsConsole::StatisticsSync(TStatisticsId code){
   // 查找内容
   IStatistics* pStatistics = StatisticsFind(code);
   if(pStatistics == NULL){
      // 创建对象
      FStatistics* pAlloc = MO_CREATE(FStatistics);
      pAlloc->SetCode(code);
      _pStatisticsSet->Set(code, pAlloc);
      // 设置内容
      pStatistics = pAlloc;
   }
   return pStatistics;
}

//============================================================
// <T>根据分组编号和项目编号同步一个统计器。</T>
//
// @param groupId 分组编号
// @param itemId 项目编号
// @return 统计器
//============================================================
IStatistics* FStatisticsConsole::StatisticsSync(TInt groupId, TInt itemId){
   SStatisticsId code((TUint16)groupId, (TUint16)itemId);
   return StatisticsSync(code);
}

//============================================================
// <T>注册一个统计器。</T>
//
// @param pStatistics 统计器
// @return 处理结果
//============================================================
TResult FStatisticsConsole::StatisticsRegister(IStatistics* pStatistics){
   MO_CHECK(pStatistics, return ENull);
   TStatisticsId code = pStatistics->Code();
   if(_pStatisticsSet->Contains(code)){
      MO_FATAL("Statistics has registered. (code=%d, statistics=" MO_FMT_POINTER ")", code, pStatistics);
      return EDuplicate;
   }
   _pStatisticsSet->Set(code, pStatistics);
   return ESuccess;
}

//============================================================
// <T>注销一个统计器。</T>
//
// @param pStatistics 统计器
// @return 处理结果
//============================================================
TResult FStatisticsConsole::StatisticsUnregister(IStatistics* pStatistics){
   MO_CHECK(pStatistics, return ENull);
   TStatisticsId code = pStatistics->Code();
   if(!_pStatisticsSet->Contains(code)){
      MO_FATAL("Statistics has not registered. (code=%d, statistics=" MO_FMT_POINTER ")", code, pStatistics);
      return EDuplicate;
   }
   _pStatisticsSet->Set(code, pStatistics);
   return ESuccess;
}

//============================================================
// <T>注册一个触发器。</T>
//
// @param pTrigger 触发器
// @return 处理结果
//============================================================
TResult FStatisticsConsole::TriggerRegister(IStatisticsTrigger* pTrigger){
   MO_CHECK(pTrigger, return ENull);
   if(_pStatisticsTriggers->Contains(pTrigger)){
      MO_FATAL("Trigger has registered. (trigger=" MO_FMT_POINTER ")", pTrigger);
      return EDuplicate;
   }
   _pStatisticsTriggers->Push(pTrigger);
   return ESuccess;
}

//============================================================
// <T>注销统计触发器。</T>
//
// @param pTrigger 触发器
// @return 处理结果
//============================================================
TResult FStatisticsConsole::TriggerUnregister(IStatisticsTrigger* pTrigger){
   MO_CHECK(pTrigger, return ENull);
   if(!_pStatisticsTriggers->Contains(pTrigger)){
      MO_FATAL("Statistics has not registered. (trigger=" MO_FMT_POINTER ")", pTrigger);
      return ENotExists;
   }
   _pStatisticsTriggers->Remove(pTrigger);
   return ESuccess;
}

//============================================================
// <T>启动监视器。</T>
//
// @return 处理结果
//============================================================
TResult FStatisticsConsole::StartupMonitor(){
   // 注册监视器
   _pMonitor->SetConsole(this);
   RMonitorService::Instance().Register(_pMonitor);
   return ESuccess;
}

//============================================================
// <T>触发器刷新处理。</T>
//
// @param currentTick 当前时刻
// @return 处理结果
//============================================================
TResult FStatisticsConsole::TriggerRefresh(TTimeTick currentTick){
   MO_INFO(TC("Statistics begin: ------------------------------------------------------------"));
   // 内存统计
   RSystem::Dump();
   // 内存存储统计
   IMemoryStorage* pStorage = RMemory::Storage();
   if(NULL != pStorage){
      if(pStorage->IsAble()){
         pStorage->Dump();
      }
   }
   // 统计器集合
   TListIteratorC<IStatisticsTrigger*> iterator = _pStatisticsTriggers->IteratorC();
   while(iterator.Next()){
      iterator->StatisticsRefresh();
   }
   MO_INFO(TC("Statistics end:   ------------------------------------------------------------"));
   return ESuccess;
}

//============================================================
// <T>启动监视器。</T>
//
// @return 处理结果
//============================================================
TResult FStatisticsConsole::Reset(){
   FStatisticsDictionary::TIteratorC iterator = _pStatisticsDictionary->IteratorC();
   while(iterator.Next()){
      FStatistics* pStatistics = *iterator;
      pStatistics->Reset();
   }
   return ESuccess;
}

//============================================================
// <T>显示跟踪信息。</T>
//
// @return 处理结果
//============================================================
TResult FStatisticsConsole::Track(){
   MO_INFO(TC("Statistics begin: ------------------------------------------------------------"));
   // 统计器集合
   FStatisticsDictionary::TIteratorC iterator = _pStatisticsDictionary->IteratorC();
   while(iterator.Next()){
      FStatistics* pStatistics = *iterator;
      pStatistics->Track();
   }
   MO_INFO(TC("Statistics end:   ------------------------------------------------------------"));
   return ESuccess;
}

MO_NAMESPACE_END
