#include "MoCrMessage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造消息逻辑的实例。</T>
//
// @return 实例对象
//============================================================
FMessageLogic::FMessageLogic(){
   _pProcessor = MO_CREATE(FNetTransferProcessor);
   MO_CLEAR(_pStatisticsMachine);
}

//============================================================
// <T>析构消息逻辑的实例。</T>
//============================================================
FMessageLogic::~FMessageLogic(){
   MO_DELETE(_pProcessor);
   MO_DELETE(_pStatisticsMachine);
}

//============================================================
// <T>处理未知消息。</T>
//
// @return 处理结果
//============================================================
TBool FMessageLogic::OnUnknownRouter(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   // 未知消息
   TChar dump[MO_FS_DUMP_LENGTH];
   TChar format[MO_FS_DUMP_LENGTH];
   MO_ERROR(TC("Unknown message. (%s)\n%s"),
         pRouter->Dump(dump, MO_FS_DUMP_LENGTH),
         pRouter->DumpMemory(format, MO_FS_DUMP_LENGTH));
   return EFalse;
}

//============================================================
// <T>注册所有处理器。</T>
//
// @return 处理结果
//============================================================
TBool FMessageLogic::RegisterAllProcessors(){
   return ETrue;
}

//============================================================
// <T>注销所有处理器。</T>
//
// @return 处理结果
//============================================================
TBool FMessageLogic::UnregisterAllProcessors(){
   return ETrue;
}

//============================================================
// <T>处理消息传输器。</T>
//
// @parma pTransfer 传输器
// @return 处理结果
//============================================================
TBool FMessageLogic::ProcessTransfer(TNetTransfer* pTransfer){
   return ETrue;
}

//============================================================
// <T>纷发消息传输器。</T>
//
// @parma pTransfer 传输器
// @return 处理结果
//============================================================
TBool FMessageLogic::DispatchTransfer(TNetTransfer* pTransfer){
   TBool result = EFalse;
   TTimeSpan span = 0;
   TInt code = pTransfer->MessageHead().Code();
   // 分发处理
   FNetMessageStatistics* pStatistics = NULL;
   if(NULL != _pStatisticsMachine){
      pStatistics = _pStatisticsMachine->Get(code);
      pStatistics->DoBegin();
      result = _pProcessor->Dispatch(pTransfer);
      if(result){
         pStatistics->DoEnd();
         span = pStatistics->Update(result);
      }
   }else{
      // 开始计时
      TTimeTick beginTick = RTimeTick::Current();
      result = _pProcessor->Dispatch(pTransfer);
      TTimeTick endTick = RTimeTick::Current();
      span = endTick - beginTick;
   }
   // 日志处理 (没有消息统计器的时候才输出，否则又消息统计器统计)
   if(NULL == pStatistics){
      if(span >= MO_NET_MESSAGE_SLOW_TICK){
         MO_WARN(TC("Dispatch messge slow. plase check logic. (message=%s, tick=%ld)"), pTransfer->MessageHead().CodeName(), span);
      }
   }
   return result;
}

MO_NAMESPACE_END
