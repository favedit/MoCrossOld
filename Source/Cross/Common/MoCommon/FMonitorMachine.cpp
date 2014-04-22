#include "MoCmXml.h"
#include "MoCmMonitor.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FMonitorMachine, FInstance);

//============================================================
// <T>构造监视器管理机。</T>
//============================================================
FMonitorMachine::FMonitorMachine(){
   _interval = 1000;
   _trackTick = 0;
   MO_CLEAR(_pLocker);
   _pCatcher = MO_CREATE(FMonitorCatcher);
   _pMonitors = MO_CREATE(FMonitorList);
   _pLooper = MO_CREATE(FMonitorLooper);
}

//============================================================
// <T>析构监视器管理机。</T>
//============================================================
FMonitorMachine::~FMonitorMachine(){
   MO_DELETE(_pCatcher);
   MO_DELETE(_pMonitors);
   MO_DELETE(_pLooper);
}

//============================================================
// <T>根据名称查找监视器。</T>
//
// @param pName 名称
// @return 监视器
//============================================================
IMonitor* FMonitorMachine::FindByName(TCharC* pName){
   TListIteratorC<IMonitor*> iterator = _pMonitors->IteratorC();
   while(iterator.Next()){
      IMonitor* pMonitor = *iterator;
      if(pMonitor->IsName(pName)){
         return pMonitor;
      }
   }
   return NULL;
}

//============================================================
// <T>注册监视器。</T>
//
// @param pMonitor 监视器
//============================================================
TResult FMonitorMachine::Register(IMonitor* pMonitor){
   MO_CHECK(pMonitor, return ENull);
   _pMonitors->Push(pMonitor);
   _pLooper->Push(pMonitor);
   return ESuccess;
}

//============================================================
// <T>注销监视器。</T>
//
// @param pMonitor 监视器
//============================================================
TResult FMonitorMachine::Unregister(IMonitor* pMonitor){
   MO_CHECK(pMonitor, return ENull);
   _pMonitors->Remove(pMonitor);
   _pLooper->Remove(pMonitor);
   return ESuccess;
}

//============================================================
// <T>加载设置信息。</T>
//
// @param pConfig 设置信息
// @return 处理结果
//============================================================
TBool FMonitorMachine::LoadConfig(FXmlNode* pConfig){
   TXmlNodeIteratorC iterator = pConfig->NodeIteratorC();
   while(iterator.Next()){
      FXmlNode* pNode = *iterator;
      if(pNode->IsName(TC("Monitor"))){
         // 查找监视器
         TCharC* pName = pNode->Get(TC("name"));
         MO_ASSERT(pName);
         IMonitor* pMonitor = FindByName(pName);
         // 加载设置
         if(NULL != pMonitor){
            pMonitor->LoadConfig(pNode);
         }
      }
   }
   return ETrue;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonitorMachine::Startup(){
   // 安装捕捉器
   _pCatcher->Install();
   // 关联监视器
   TListIteratorC<IMonitor*> iterator = _pMonitors->IteratorC();
   while(iterator.Next()){
      iterator->SetCatcher(_pCatcher);
   }
   return ESuccess;
}

//============================================================
// <T>执行处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonitorMachine::Execute(){
   TDateTime currentTime = RDateTime::Current();
   TListIteratorC<IMonitor*> iterator = _pMonitors->IteratorC();
   while(iterator.Next()){
      IMonitor* pMonitor = *iterator;
      // 执行处理
      if(NULL != _pLocker){
         _pLocker->Enter();
         pMonitor->Execute(currentTime);
         _pLocker->Leave();
      }else{
         pMonitor->Execute(currentTime);
      }
   }
   return ESuccess;
}

//============================================================
// <T>循环处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonitorMachine::Process(){
   // 处理所有监视器
   TInt count = _pLooper->Count();
   for(TInt n = 0; n < count; n++){
      IMonitor* pMonitor = _pLooper->Next();
      if(NULL != pMonitor){
         TTimeTick currentTick = RTimeTick::Current();
         if(pMonitor->Test(currentTick)){
            // 执行处理
            // MO_DEBUG("Monitor porocess. (name=%s, interval=%lld)", pMonitor->Name(), pMonitor->Interval());
            if(NULL != _pLocker){
               _pLocker->Enter();
               pMonitor->Execute(currentTick);
               _pLocker->Leave();
            }else{
               pMonitor->Execute(currentTick);
            }
            // 计时处理
            break;
         }
      }
   }
   return ESuccess;
}

MO_NAMESPACE_END
