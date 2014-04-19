#include "MoCrService.h"
#include "MoCrMessage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>处理中断信号。</T>
//
// @return 处理结果
//============================================================
TBool FInterruptListener::Process(){
   // 释放所有模块
   FModuleList* pModuleList = RModuleManager::Instance().Modules();
   if(!pModuleList->IsEmpty()){
      FModuleList::TIteratorC iterator = pModuleList->IteratorC();
      while(iterator.Next()){
         iterator->Release();
      }
   }
   // 释放所有服务
   FServiceList* pServiceList = RServiceManager::Instance().Services();
   if(!pServiceList->IsEmpty()){
      FServiceList::TIteratorC iterator = pServiceList->IteratorC();
      while(iterator.Next()){
         iterator->Shutdown();
      }
   }
   return ETrue;
}

MO_NAMESPACE_END
