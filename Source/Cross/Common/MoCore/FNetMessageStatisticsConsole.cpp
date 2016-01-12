
#ifdef _MO_LINUX
#include <unistd.h>
#endif
#include <MoCmStream.h>
#include <MoCmNet.h>
#include "MoCrNetConnection.h"

#define MO_MD_MESSAGE_WAIT_INTERVAL 1

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造消息统计控制台。</T>
//============================================================
FNetMessageStatisticsConsole::FNetMessageStatisticsConsole(){
   _pMachine = MO_CREATE(FNetMessageStatisticsMachine);
   _pMachines = MO_CREATE(FNetMessageStatisticsMachineList);
   _pMachines->Push(_pMachine);
}

//============================================================
// <T>析构消息统计控制台。</T>
//============================================================
FNetMessageStatisticsConsole::~FNetMessageStatisticsConsole(){
   MO_DELETE(_pMachine);
   MO_DELETE(_pMachines);
};

//============================================================
// <T>注册消息统计机。</T>
//
// @param pMachine 消息统计机
//============================================================
void FNetMessageStatisticsConsole::Register(FNetMessageStatisticsMachine* pMachine){
   _pMachines->Push(pMachine);
}

//============================================================
// <T>注销消息统计机。</T>
//
// @param pMachine 消息统计机
//============================================================
void FNetMessageStatisticsConsole::Unregister(FNetMessageStatisticsMachine* pMachine){
   _pMachines->Remove(pMachine);
}

//============================================================
// <T>输出统计信息。</T>
//
// @return 处理结果
//============================================================
TResult FNetMessageStatisticsConsole::StatisticsRefresh(){
   // 消息执行统计
   TFsDump dump;
   TListIteratorC<FNetMessageStatisticsMachine*> iterator = _pMachines->IteratorC();
   while(iterator.Next()){
      FNetMessageStatisticsMachine* pMachine = *iterator;
      pMachine->Dump(dump);
   }
   MO_INFO(TC("Message statistics.%s"), (TCharC*)dump);
   // 消息队列统计
   if(RNetQueueModule::IsValid()){
      RNetQueueModule::Instance().Connection()->Dump();
   }
   if(RNetClientQueueModule::IsValid()){
      RNetClientQueueModule::Instance().Connection()->Dump();
   }
   return ESuccess;
}
   
MO_NAMESPACE_END
