
#ifdef _MO_LINUX
#include <unistd.h>
#endif
#include <MoCmStream.h>
#include <MoCmNet.h>
#include "MoCrNetConnection.h"

#define MO_MD_MESSAGE_WAIT_INTERVAL 1

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造消息统计机。</T>
//============================================================
FNetMessageStatisticsMachine::FNetMessageStatisticsMachine(){
   TInt size = _statistics.Size();
   for(TInt n = 0; n < size; n++){
      _statistics.Push(NULL);
   }
}

//============================================================
// <T>析构消息统计机。</T>
//============================================================
FNetMessageStatisticsMachine::~FNetMessageStatisticsMachine(){
   TInt size = _statistics.Size();
   for(TInt n = 0; n < size; n++){
      FNetMessageStatistics* pStatistics = _statistics[n];
      if(NULL != pStatistics){
         MO_DELETE(pStatistics);
      }
   }
};

//============================================================
// <T>获得有效个数。</T>
//
// @return 有效个数
//============================================================
TInt FNetMessageStatisticsMachine::ValidCount(){
   TInt count = 0;
   TInt size = _statistics.Size();
   for(TInt n = 0; n < size; n++){
      FNetMessageStatistics* pStatistics = _statistics[n];
      if(NULL != pStatistics){
         count++;
      }
   }
   return count;
}

//============================================================
// <T>根据代码获得消息统计对象。</T>
//
// @param code 代码
// @return 统计对象
//============================================================
FNetMessageStatistics* FNetMessageStatisticsMachine::Get(TInt code){
   FNetMessageStatistics* pStatistics = _statistics[code];
   if(NULL == pStatistics){
      pStatistics = MO_CREATE(FNetMessageStatistics);
      pStatistics->SetCode(code);
      _statistics[code] = pStatistics;
   }
   return pStatistics;
}

//============================================================
// <T>重置所有数据。</T>
//============================================================
void FNetMessageStatisticsMachine::Reset(){
   TInt size = _statistics.Size();
   for(TInt n = 0; n < size; n++){
      FNetMessageStatistics* pStatistics = _statistics[n];
      if(NULL != pStatistics){
         pStatistics->Reset();
      }
   }
}

//============================================================
// <T>输出统计信息。</T>
//============================================================
void FNetMessageStatisticsMachine::Dump(TFsDump& dump){
   TInt count = ValidCount();
   if(count > 0){
      dump.AppendFormat(TC("\n-- Message statistics. (name=%s, count=%d)"), (TCharC*)_name, count);
      TInt size = _statistics.Size();
      for(TInt n = 0; n < size; n++){
         FNetMessageStatistics* pStatistics = _statistics[n];
         if(NULL != pStatistics){
            TInt code = pStatistics->Code();
            TCharC* pName = RNetMessageFactory::CodeName(code);
            dump.AppendFormat(TC("\n   ") MO_FMT_OBJECT_NAME TC(" code=0x%04X count=%8d, tick=(%8d - %-8d) - %8d (failure=%16lld, slow=%16lld, socket=%16lld)"),
                  pName, code, pStatistics->Count(),
                  pStatistics->MinTick(), pStatistics->MaxTick(),
                  pStatistics->AverageTick(), pStatistics->FailureCount(), pStatistics->SlowCount(), pStatistics->SocketClose());
         }
      }
   }
}
   
MO_NAMESPACE_END
