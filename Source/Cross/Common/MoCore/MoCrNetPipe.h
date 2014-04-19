#ifndef __MO_CR_NET_PIPE_H__
#define __MO_CR_NET_PIPE_H__

#ifndef __MO_CM_STREAM_H__
#include <MoCmStream.h>
#endif // __MO_CM_STREAM_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

#ifndef __MO_CR_CORE_H__
#include "MoCrCore.h"
#endif // __MO_CR_CORE_H__

#ifndef __MO_CR_STATISTICS_H__
#include "MoCrStatistics.h"
#endif // __MO_CR_STATISTICS_H__

#ifndef __MO_CR_MODULE_H__
#include "MoCrModule.h"
#endif // __MO_CR_MODULE_H__

#ifndef __MO_CR_MONITOR_H__
#include "MoCrMonitor.h"
#endif // __MO_CR_MONITOR_H__

MO_NAMESPACE_BEGIN

//============================================================
class TNetMessage;
class TNetRouter;
class TNetTransfer;

//============================================================
// <T>网络管道。</T>
//============================================================
class MO_CR_DECLARE INetPipe{
public:
   //------------------------------------------------------------
   // <T>析构网络管道。</T>
   MO_ABSTRACT ~INetPipe(){
   }
public:
   MO_VIRTUAL TInt Length() = 0;
public:
   MO_VIRTUAL EStreamResult Peek(TAny* pData, TInt length, TInt* pLength) = 0;
   MO_VIRTUAL EStreamResult Read(TAny* pData, TInt length, TInt* pLength) = 0;
   MO_VIRTUAL EStreamResult Write(TAnyC* pData, TInt length, TInt* pLength) = 0;
public:
   MO_VIRTUAL TBool WriteMessage(TNetMessage* pMessage) = 0;
   MO_VIRTUAL TBool WriteMessageCompress(TNetMessage* pMessage) = 0;
   MO_VIRTUAL TBool WriteRouter(TNetRouter* pRouter) = 0;
   MO_VIRTUAL TBool WriteRouterCompress(TNetRouter* pRouter) = 0;
public:
   MO_VIRTUAL TBool Reset() = 0;
};

//============================================================
// <T>共享网络管道。</T>
//============================================================
class MO_CR_DECLARE FSharedNetPipe :
      public FSharedPipe,
      public INetPipe{
public:
   FSharedNetPipe();
   MO_ABSTRACT ~FSharedNetPipe();
public:
   MO_OVERRIDE TInt Length(){
      return 0;
   }
public:
   MO_OVERRIDE EStreamResult Peek(TAny* pData, TInt length, TInt* pLength){
      return EStreamResult_Success;
   }
   MO_OVERRIDE EStreamResult Read(TAny* pData, TInt length, TInt* pLength){
      return EStreamResult_Success;
   }
   MO_OVERRIDE EStreamResult Write(TAnyC* pData, TInt length, TInt* pLength){
      return EStreamResult_Success;
   }
   MO_OVERRIDE TBool WriteMessage(TNetMessage* pMessage){
      return ETrue;
   }
   MO_OVERRIDE TBool WriteMessageCompress(TNetMessage* pMessage){
      return ETrue;
   }
   MO_OVERRIDE TBool WriteRouter(TNetRouter* pRouter){
      return ETrue;
   }
   MO_OVERRIDE TBool WriteRouterCompress(TNetRouter* pRouter){
      return ETrue;
   }
   MO_OVERRIDE TBool Reset(){
      return ETrue;
   }
public:
   TBool TryWriteMessage(TNetMessage* pMessage);
   TBool TryWriteMessageCompress(TNetMessage* pMessage);
   TBool TryWriteRouter(TNetRouter* pRouter);
   TBool TryWriteRouterCompress(TNetRouter* pRouter);
};

//============================================================
// <T>网络缓冲队列。</T>
//============================================================
class MO_CR_DECLARE FNetBufferedPipe :
      public FBufferedPipe,
      public INetPipe{
public:
   FNetBufferedPipe();
   MO_ABSTRACT ~FNetBufferedPipe();
public:
   MO_OVERRIDE TInt Length();
public:
   MO_OVERRIDE EStreamResult Peek(TAny* pData, TInt length, TInt* pLength);
   MO_OVERRIDE EStreamResult Read(TAny* pData, TInt length, TInt* pLength);
   MO_OVERRIDE EStreamResult Write(TAnyC* pData, TInt length, TInt* pLength);
public:
   MO_OVERRIDE TBool WriteMessage(TNetMessage* pMessage);
   MO_OVERRIDE TBool WriteMessageCompress(TNetMessage* pMessage);
   MO_OVERRIDE TBool WriteRouter(TNetRouter* pRouter);
   MO_OVERRIDE TBool WriteRouterCompress(TNetRouter* pRouter);
public:
   MO_OVERRIDE TBool Reset();
};

//============================================================
// <T>网络队列。</T>
//============================================================
class MO_CR_DECLARE INetQueue{
public:
   //------------------------------------------------------------
   // <T>析构网络队列。</T>
   MO_ABSTRACT ~INetQueue(){
   }
public:
   MO_VIRTUAL TInt Count() = 0;
public:
   MO_VIRTUAL TBool TestPushAble(TInt length) = 0;
   MO_VIRTUAL TBool TestPopAble() = 0;
public:
   MO_VIRTUAL TBool Push(TAnyC* pData, TInt length) = 0;
   MO_VIRTUAL TInt Pop(TAny* pData, TInt capacity) = 0;
   MO_VIRTUAL TBool BlockedPush(TAnyC* pData, TInt size) = 0;
   MO_VIRTUAL TInt BlockedPop(TAny* pData, TInt capacity) = 0;
public:
   MO_VIRTUAL TBool PushMessage(TNetMessage* pMessage) = 0;
   MO_VIRTUAL TBool PushRouter(TNetRouter* pRouter) = 0;
   MO_VIRTUAL TBool PushTransfer(TNetTransfer* pTransfer) = 0;
public:
   MO_VIRTUAL TBool FetchInfo(SQueueInfo* pInfo) = 0;
   MO_VIRTUAL void Dump() = 0;
};

//============================================================
// <T>消息处理回调器。</T>
//============================================================
class MO_CR_DECLARE FSharedNetQueue : public FSharedQueue{
public:
   TThreadSection _lockPush;
public:
   FSharedNetQueue();
   MO_ABSTRACT ~FSharedNetQueue();
public:
   MO_OVERRIDE TInt Count(){
      return 0;
   }
public:
   TBool PushMessage(TNetMessage* pMessage);
   TBool PushRouter(TNetRouter* pRouter);
   TBool PushTransfer(TNetTransfer* pTransfer);
   TBool TryPushTransfer(TNetTransfer* pTransfer);
public:
   TBool BlockedPushMessage(TNetMessage* pMessage);
   TBool BlockedPushRouter(TNetRouter* pRouter);
   TBool BlockedPushTransfer(TNetTransfer* pTransfer);
};

//============================================================
// <T>网络缓冲队列。</T>
//============================================================
class MO_CR_DECLARE FNetBufferedQueue :
      public FBufferedQueue,
      public INetQueue{
public:
   FNetBufferedQueue();
   MO_ABSTRACT ~FNetBufferedQueue();
public:
   MO_OVERRIDE TInt Count();
public:
   MO_OVERRIDE TBool TestPushAble(TInt length);
   MO_OVERRIDE TBool TestPopAble();
public:
   MO_OVERRIDE TBool Push(TAnyC* pData, TInt length);
   MO_OVERRIDE TInt Pop(TAny* pData, TInt capacity);
   MO_OVERRIDE TBool BlockedPush(TAnyC* pData, TInt length);
   MO_OVERRIDE TInt BlockedPop(TAny* pData, TInt capacity);
public:
   MO_OVERRIDE TBool PushMessage(TNetMessage* pMessage);
   MO_OVERRIDE TBool PushRouter(TNetRouter* pRouter);
   MO_OVERRIDE TBool PushTransfer(TNetTransfer* pTransfer);
public:
   MO_OVERRIDE TBool FetchInfo(SQueueInfo* pInfo);
   MO_OVERRIDE void Dump();
};

//============================================================
// <T>数据队列。</T>
//
// @history 100309 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetBufferedQueueConnection : public FObject{
protected:
   FNetBufferedQueue* _pInputQueue;
   FNetBufferedQueue* _pOutputQueue;
public:
   FNetBufferedQueueConnection();
   MO_ABSTRACT( ~FNetBufferedQueueConnection() );
public:
   //------------------------------------------------------------
   // <T>获得收取队列的指针。</T>
   MO_INLINE INetQueue* InputQueue(){
      return _pInputQueue;
   }
   //------------------------------------------------------------
   // <T>获得发送队列的指针。</T>
   MO_INLINE INetQueue* OutputQueue(){
      return _pOutputQueue;
   }
public:
   void Setup(TInt capacity, FBufferedQueueBlockAllocator* pPool);
public:
   void Dump();
};

//============================================================
// <T>数据队列分块缓冲池模块。</T>
//
// @history 130322 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetPipeBlockPoolModule :
      public FModule,
      public IStatisticsTrigger{
protected:
   TInt _blockLimit;
   FBufferedPipeBlockAllocatorVector* _pAllocators;
public:
   FNetPipeBlockPoolModule();
   MO_ABSTRACT ~FNetPipeBlockPoolModule();
public:
   MO_OVERRIDE TResult LoadConfig(FXmlNode* pConfig);
public:
   FBufferedPipeBlockAllocator* Alloc(TInt blockCapacity);
   void Free(FBufferedPipeBlockAllocator* pPool);
public:
   MO_OVERRIDE TResult StatisticsRefresh();
};
//------------------------------------------------------------
class MO_CR_DECLARE RNetPipeBlockPoolModule : public RModuleSingleton<FNetPipeBlockPoolModule>{
};

//============================================================
// <T>数据队列分块缓冲池模块。</T>
//
// @history 130322 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetQueueBlockPoolModule :
      public FModule,
      public IStatisticsTrigger{
protected:
   TInt _blockLimit;
   FBufferedQueueBlockAllocatorVector* _pAllocators;
public:
   FNetQueueBlockPoolModule();
   MO_ABSTRACT ~FNetQueueBlockPoolModule();
public:
   MO_OVERRIDE TResult LoadConfig(FXmlNode* pConfig);
public:
   FBufferedQueueBlockAllocator* Alloc(TInt blockCapacity);
   void Free(FBufferedQueueBlockAllocator* pPool);
public:
   MO_OVERRIDE TResult StatisticsRefresh();
};
//------------------------------------------------------------
class MO_CR_DECLARE RNetQueueBlockPoolModule : public RModuleSingleton<FNetQueueBlockPoolModule>{
};

//============================================================
// <T>共享网络数据队列模块。</T>
//
// @history 100317 MAOCY 创建
//============================================================
class MO_CR_DECLARE FSharedNetQueueStorage : public FObject{
protected:
   TFsName _name;
   TInt _capacity;
   TInt _blockCapacity;
   FBufferedQueueBlockAllocator* _pAllocator;
   FNetBufferedQueueConnection* _pConnection;
public:
   FSharedNetQueueStorage();
   MO_ABSTRACT ~FSharedNetQueueStorage();
public:
   //------------------------------------------------------------
   // <T>获得容量</T>
   MO_INLINE TInt Capacity(){
      return _capacity;
   }
   //------------------------------------------------------------
   // <T>获得分块容量</T>
   MO_INLINE TInt BlockCapacity(){
      return _blockCapacity;
   }
   //------------------------------------------------------------
   // <T>获得收集器。</T>
   MO_INLINE FBufferedQueueBlockAllocator* Allocator(){
      return _pAllocator;
   }
public:
   MO_OVERRIDE TResult LoadConfig(FXmlNode* pConf1ig);
public:
   FNetBufferedQueueConnection* Connection();
};

//============================================================
// <T>共享网络数据队列模块。</T>
//
// @history 100317 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetBufferedQueueModule : public FModule{
protected:
   FSharedNetQueueStorage* _pStorage;
public:
   FNetBufferedQueueModule();
   MO_ABSTRACT ~FNetBufferedQueueModule();
public:
   MO_OVERRIDE TResult OnLoadConfig(FXmlNode* pConfig);
public:
   FSharedNetQueueStorage* Storage();
   FNetBufferedQueueConnection* Connection();
};
//------------------------------------------------------------
class MO_CR_DECLARE RNetQueueModule : public RModuleSingleton<FNetBufferedQueueModule>{
};

//============================================================
// <T>共享网络数据队列模块。</T>
//
// @history 100413 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetBufferedClientQueueModule : public FNetBufferedQueueModule{
public:
   FNetBufferedClientQueueModule();
   MO_ABSTRACT ~FNetBufferedClientQueueModule();
};
//------------------------------------------------------------
class MO_CR_DECLARE RNetClientQueueModule : public RModuleSingleton<FNetBufferedClientQueueModule>{
};

MO_NAMESPACE_END

#endif // __MO_CR_NET_PIPE_H__
