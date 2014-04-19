#ifndef __MO_CM_PIPE_H__
#define __MO_CM_PIPE_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_LOCK_H__
#include "MoCmLock.h"
#endif // __MO_CM_LOCK_H__

#ifndef __MO_CM_STRING_H__
#include "MoCmString.h"
#endif // __MO_CM_STRING_H__

#ifndef __MO_CM_FORMAT_H__
#include "MoCmFormat.h"
#endif // __MO_CM_FORMAT_H__

#ifndef __MO_CM_STREAM_H__
#include "MoCmStream.h"
#endif // __MO_CM_STREAM_H__

#ifndef __MO_CM_SHARED_H__
#include "MoCmShared.h"
#endif // __MO_CM_SHARED_H__

#define MO_PIPE_LENGTH_SIZE 4

MO_NAMESPACE_BEGIN

const TInt MoPipeReserveLength = 16;

//============================================================
// <T>流锁定模式。</T>
//============================================================
enum EStreamLock{
   // 无锁模式（适合单线程）
   EStreamLock_Unlock = 0,
   // 锁定模式（适合多线程）
   EStreamLock_Lock = 1,
};

//============================================================
// <T>流处理结果。</T>
//============================================================
enum EStreamResult{
   // 成功
   EStreamResult_Success = 0,
   // 未知
   EStreamResult_Unknown = -1,
   // 写入空数据
   EStreamResult_WriteEmpty = -2,
   // 写入池满
   EStreamResult_WriteFull = -3,
   // 写入缓冲满
   EStreamResult_WriteCapacity = -4,
   // 读取空数据
   EStreamResult_ReadEmpty = -5,
   // 读取到结尾
   EStreamResult_ReadEnd = -6,
   // 读取缓冲不足
   EStreamResult_ReadCapacity = -7,
};

//============================================================
// <T>缓冲队列块头信息。</T>
//============================================================
#pragma pack(4)
struct FStreamBlockHead{
   TInt32 length;
   TByte data[1];
};
#pragma pack()

//============================================================
// <T>数据流分块。</T>
//============================================================
class MO_CM_DECLARE FStreamBlock : public FObject{
protected:
   TByte* _pMemory;
   TInt _capacity;
   TInt _length;
   TInt _lengthMax;
   TInt _position;
   TInt _lengthTotalRead;
   TInt _lengthTotalWrite;
public:
   FStreamBlock();
   MO_ABSTRACT ~FStreamBlock();
public:
   //------------------------------------------------------------
   // <T>获得内存。</T>
   MO_INLINE TByte* Memory(){
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得容量。</T>
   MO_INLINE TInt Capacity(){
      return _capacity;
   }
   //------------------------------------------------------------
   // <T>获得位置。</T>
   MO_INLINE TInt Position(){
      return _position;
   }
   //------------------------------------------------------------
   // <T>获得可用长度。</T>
   MO_INLINE TInt LengthFree(){
      return _capacity - _position;
   }
   //------------------------------------------------------------
   // <T>获得读取总长度。</T>
   MO_INLINE TInt LengthTotalRead(){
      return _lengthTotalRead;
   }
   //------------------------------------------------------------
   // <T>获得写入总长度。</T>
   MO_INLINE TInt LengthTotalWrite(){
      return _lengthTotalWrite;
   }
public:
   void ForceCapacity(TInt capacity);
public:
   TBool IsEmpty();
   MO_ABSTRACT TBool TestPushAble(TInt length);
   MO_ABSTRACT TBool TestPopAble(TInt length);
public:
   TBool FlipForRead();
   TBool FlipForWrite();
   TBool Reset();
public:
   void Track(MString* pTrack);
};
//------------------------------------------------------------
typedef MO_CM_DECLARE FList<FStreamBlock*> FStreamBlockList;

//============================================================
// <T>数据队列分块收集器。</T>
//============================================================
class MO_CM_DECLARE FStreamBlockAllocator : public FPool<FStreamBlock*>{
protected:
   TInt _blockCapacity;
   TInt _blockLimit;
   TThreadLocker _locker;
public:
   FStreamBlockAllocator();
   MO_ABSTRACT ~FStreamBlockAllocator();
public:
   //------------------------------------------------------------
   // <T>获得分块容量。</T>
   MO_INLINE TInt BlockCapacity(){
      return _blockCapacity;
   }
   //------------------------------------------------------------
   // <T>设置分块容量。</T>
   MO_INLINE void SetBlockCapacity(TInt blockCapacity){
      _blockCapacity = blockCapacity;
   }
   //------------------------------------------------------------
   // <T>获得分块限制。</T>
   MO_INLINE TInt BlockLimit(){
      return _blockLimit;
   }
   //------------------------------------------------------------
   // <T>设置分块限制。</T>
   MO_INLINE void SetBlockLimit(TInt blockLimit){
      _blockLimit = blockLimit;
   }
protected:
   MO_VIRTUAL FStreamBlock* Create() = 0;
   MO_ABSTRACT void Delete(FStreamBlock* pBlock);
public:
   FStreamBlock* Alloc();
   void Free(FStreamBlock* pBlock);
public:
   MO_ABSTRACT void Track(MString* pTrack);
};
//------------------------------------------------------------
typedef FVector<FStreamBlockAllocator*> FStreamBlockAllocatorVector;

//============================================================
// <T>数据队列分块缓冲池。</T>
//============================================================
class MO_CM_DECLARE FStreamBlockPool : public FObject{
protected:
   TInt _capacity;
   TInt _blockCapacity;
   FStreamBlockList* _pBlocks;
   FStreamBlockAllocator* _pAllocator;
   TThreadLocker _locker;
public:
   FStreamBlockPool();
   MO_ABSTRACT ~FStreamBlockPool();
public:
   //------------------------------------------------------------
   // <T>获得容量。</T>
   MO_INLINE TInt Capacity(){
      return _capacity;
   }
   //------------------------------------------------------------
   // <T>设置容量。</T>
   MO_INLINE void SetCapacity(TInt capacity){
      _capacity = capacity;
   }
   //------------------------------------------------------------
   // <T>获得分块容量。</T>
   MO_INLINE TInt BlockCapacity(){
      return _blockCapacity;
   }
   //------------------------------------------------------------
   // <T>设置分块容量。</T>
   MO_INLINE void SetBlockCapacity(TInt blockCapacity){
      _blockCapacity = blockCapacity;
   }
   //------------------------------------------------------------
   // <T>获得分块收集器。</T>
   MO_INLINE FStreamBlockAllocator* Allocator(){
      return _pAllocator;
   }
   //------------------------------------------------------------
   // <T>设置分块收集器。</T>
   MO_INLINE void SetAllocator(FStreamBlockAllocator* pAllocator){
      _pAllocator = pAllocator;
   }
public:
   FStreamBlock* Alloc();
   void Free(FStreamBlock* pBlock);
public:
   TBool Reset();
public:
   void Track(MString* pTrack);
};
//------------------------------------------------------------
typedef FVector<FStreamBlockPool*> FStreamBlockPoolVector;

//============================================================
// <T>数据管道接口。</T>
//============================================================
class MO_CM_DECLARE IPipe :
      public IInput,
      public IOutput{
public:
   MO_ABSTRACT ~IPipe(){
   }
public:
   MO_VIRTUAL TBool IsEmpty() = 0;
   MO_VIRTUAL TInt Length() = 0;
   MO_VIRTUAL TInt Reamin() = 0;
public:
   MO_VIRTUAL TInt Peek(TAny* pData, TInt size) = 0;
   MO_VIRTUAL TInt Write(TAnyC* pData, TInt size) = 0;
   MO_VIRTUAL TInt Read(TAny* pData, TInt size) = 0;
   MO_VIRTUAL TBool TryPeek(TAny* pData, TInt size) = 0;
   MO_VIRTUAL TBool TryWrite(TAnyC* pData, TInt size) = 0;
   MO_VIRTUAL TBool TryRead(TAny* pData, TInt size) = 0;
   MO_VIRTUAL TBool Reset() = 0;
   MO_VIRTUAL TBool Close() = 0;
};

//============================================================
// <T>数据管道定义。</T>
//============================================================
struct SPipeInfo{
public:
   TInt32 capacity;
   TInt32 first;
   TInt32 last;
};

//============================================================
// <T>管道数据。</T>
//============================================================
struct SPipeAtom{
public:
   SPipeInfo* infoPtr;
   TByte* memoryPtr;
public:
   TInt capacity;
   TInt length;
   TInt first;
   TInt last;
public:
   //------------------------------------------------------------
   MO_INLINE void Fetch(){
      capacity = infoPtr->capacity;
      first = infoPtr->first;
      last = infoPtr->last;
      // 长度中，数据尾位置不算在长度中，算是下一个数据开始
      if(first <= last){
         // 计算长度 [--F===L---] [4 = (6 - 2)]
         //           0123456789
         length = last - first;
      }else{
         // 计算长度 [==L---F===] [6 = (10 - 6) + 2]
         //           0123456789
         length = (capacity - first) + last;
      }
   }
public:
   //------------------------------------------------------------
   MO_INLINE TBool IsEmpty(){
      return (0 == length);
   }
   //------------------------------------------------------------
   MO_INLINE TInt Reamin(){
      return capacity - length - MoPipeReserveLength;
   }
   //------------------------------------------------------------
   MO_INLINE void SetFirst(TInt first){
      infoPtr->first = (TInt32)first;
   }
   //------------------------------------------------------------
   MO_INLINE void SetLast(TInt last){
      infoPtr->last = (TInt32)last;
   }
   //------------------------------------------------------------
   MO_INLINE void Set(TInt first, TInt last){
      infoPtr->first = (TInt32)first;
      infoPtr->last = (TInt32)last;
   }
   //------------------------------------------------------------
   MO_INLINE void Clear(){
      infoPtr->first = 0;
      infoPtr->last = 0;
   }
};

//============================================================
// <T>数据管道。</T>
//============================================================
class MO_CM_DECLARE MPipe : public IPipe{
protected:
   TThreadLocker _locker;
public:
   MPipe();
   MO_ABSTRACT ~MPipe();
public:
   MO_VIRTUAL SPipeInfo* Info() = 0;
   MO_VIRTUAL SPipeAtom Atom() = 0;
public:
   MO_OVERRIDE TBool IsEmpty();
   MO_OVERRIDE TInt Length();
   MO_OVERRIDE TInt Reamin();
public:
   TInt FollowReamin();
   TByte* FollowMemory();
   TBool FollowWrite(TInt size);
   TBool FollowRead(TInt size);
public:
   MO_OVERRIDE TInt Peek(TAny* pData, TInt size);
   MO_OVERRIDE TInt Write(TAnyC* pData, TInt size);
   MO_OVERRIDE TInt Read(TAny* pData, TInt size);
   MO_OVERRIDE TBool TryPeek(TAny* pData, TInt size);
   MO_OVERRIDE TBool TryWrite(TAnyC* pData, TInt size);
   MO_OVERRIDE TBool TryRead(TAny* pData, TInt size);
   MO_OVERRIDE TBool Reset();
   MO_OVERRIDE TBool Close();
   void Track();
};

//============================================================
// <T>数据管道。</T>
//============================================================
class MO_CM_DECLARE FPipe :
      public FObject,
      public MPipe{
protected:
   TBool _linked;
   SPipeInfo _info;
   TByte* _pMemory;
public:
   FPipe();
   FPipe(TSize capacity);
   FPipe(TByte* pMemory, TSize capacity);
   MO_ABSTRACT ~FPipe();
protected:
   MO_OVERRIDE SPipeInfo* Info();
   MO_OVERRIDE SPipeAtom Atom();
public:
   MO_OVERRIDE TInt Length();
   MO_OVERRIDE TInt Reamin();
   TBool Reset();
};

//============================================================
// <T>缓冲队列块。</T>
//============================================================
class MO_CM_DECLARE FBufferedPipeBlock : public FStreamBlock{
public:
   FBufferedPipeBlock();
   MO_ABSTRACT ~FBufferedPipeBlock();
public:
   //------------------------------------------------------------
   // <T>测试是否已经读取到尾部。</T>
   MO_INLINE TBool TestReadEnd(){
      return (_position == _length);
   }
   //------------------------------------------------------------
   // <T>测试是否已经写入到尾部。</T>
   MO_INLINE TBool TestWriteEnd(){
      return (_position == _length);
   }
public:
   TBool TestWriteAble(TInt length);
   TBool TestReadAble(TInt length);
public:
   EStreamResult Peek(TAny* pData, TInt length, TInt *pLength);
   EStreamResult Read(TAny* pData, TInt length, TInt *pLength);
   EStreamResult Write(TAnyC* pData, TInt length, TInt *pLength);
public:
   MO_OVERRIDE void Track(MString* pTrack);
};
//------------------------------------------------------------
typedef MO_CM_DECLARE FList<FBufferedPipeBlock*> FBufferedPipeBlockList;

//============================================================
// <T>数据队列分块收集器。</T>
//============================================================
class MO_CM_DECLARE FBufferedPipeBlockAllocator : public FStreamBlockAllocator{
public:
   //------------------------------------------------------------
   // <T>构造数据队列分块缓冲池。</T>
   FBufferedPipeBlockAllocator(){
   }
   //------------------------------------------------------------
   // <T>析构数据队列分块缓冲池。</T>
   MO_ABSTRACT ~FBufferedPipeBlockAllocator(){
   }
public:
   //------------------------------------------------------------
   // <T>创建一个队列分块。</T>
   MO_OVERRIDE FStreamBlock* Create(){
      return MO_CREATE(FBufferedPipeBlock);
   }
};
//------------------------------------------------------------
typedef MO_CM_DECLARE FVector<FBufferedPipeBlockAllocator*> FBufferedPipeBlockAllocatorVector;

//============================================================
// <T>缓冲队列。</T>
//============================================================
class MO_CM_DECLARE FBufferedPipe : public FObject{
protected:
   EStreamLock _lockCd;
   TInt _countPush;
   TInt _lengthPush;
   TInt _countPop;
   TInt _lengthPop;
protected:
   TThreadLocker _lockerRead;
   TThreadLocker _lockerWrite;
   FStreamBlockPool* _pPool;
   FBufferedPipeBlock* _pReadBlock;
   FBufferedPipeBlockList* _pReadBlocks;
   FBufferedPipeBlock* _pWriteBlock;
   FBufferedPipeBlockList* _pWriteBlocks;
public:
   FBufferedPipe();
   MO_ABSTRACT ~FBufferedPipe();
public:
   //------------------------------------------------------------
   // <T>获得锁定方式。</T>
   MO_INLINE EStreamLock LockCd(){
      return _lockCd;
   }
   //------------------------------------------------------------
   // <T>设置锁定方式。</T>
   MO_INLINE void SetLockCd(EStreamLock lockCd){
      _lockCd = lockCd;
   }
   //------------------------------------------------------------
   // <T>获得缓冲池。</T>
   MO_INLINE FStreamBlockPool* Pool(){
      return _pPool;
   }
   //------------------------------------------------------------
   // <T>获得缓冲收集器。</T>
   MO_INLINE FBufferedPipeBlockAllocator* Allocator(){
      return (FBufferedPipeBlockAllocator*)_pPool->Allocator();
   }
   //------------------------------------------------------------
   // <T>设置缓冲收集器。</T>
   MO_INLINE void SetAllocator(FBufferedPipeBlockAllocator* pAllocator){
      _pPool->SetAllocator(pAllocator);
   }
   //------------------------------------------------------------
   // <T>获得读取块集合。</T>
   MO_INLINE FBufferedPipeBlockList* ReadBlocks(){
      return _pReadBlocks;
   }
   //------------------------------------------------------------
   // <T>获得写入块集合。</T>
   MO_INLINE FBufferedPipeBlockList* WriteBlocks(){
      return _pWriteBlocks;
   }
protected:
   //------------------------------------------------------------
   // <T>读取锁定。</T>
   MO_INLINE void ReadLock(){
      if(EStreamLock_Unlock != _lockCd){
         _lockerRead.Enter();
      }
   }
   //------------------------------------------------------------
   // <T>读取解锁。</T>
   MO_INLINE void ReadUnlock(){
      if(EStreamLock_Unlock != _lockCd){
         _lockerRead.Leave();
      }
   }
   //------------------------------------------------------------
   // <T>写入锁定。</T>
   MO_INLINE void WriteLock(){
      if(EStreamLock_Unlock != _lockCd){
         _lockerWrite.Enter();
      }
   }
   //------------------------------------------------------------
   // <T>写入解锁。</T>
   MO_INLINE void WriteUnlock(){
      if(EStreamLock_Unlock != _lockCd){
         _lockerWrite.Leave();
      }
   }
protected:
   EStreamResult InnerPeek(TAny* pData, TInt length, TInt* pLength);
   EStreamResult InnerRead(TAny* pData, TInt length, TInt* pLength);
   EStreamResult InnerWrite(TAnyC* pData, TInt length, TInt* pLength);
public:
   MO_ABSTRACT TInt Length();
public:
   MO_ABSTRACT TBool TestWriteAble(TInt length);
   MO_ABSTRACT TBool TestReadAble(TInt length);
public:
   MO_ABSTRACT EStreamResult Peek(TAny* pData, TInt length, TInt* pLength);
   MO_ABSTRACT EStreamResult Read(TAny* pData, TInt length, TInt* pLength);
   MO_ABSTRACT EStreamResult Write(TAnyC* pData, TInt length, TInt* pLength);
public:
   MO_ABSTRACT TBool Reset();
public:
   void Dump();
};

//============================================================
// <T>数据队列。</T>
// <P>允许整块压入数据和读取数据。</P>
// <P>Push/Pop函数不能和Write和Read同时使用。</P>
//
// @history 100309 MAOCY 创建
// @history 100714 MAOCY 压入和读取数据的修正
//============================================================
class MO_CM_DECLARE FQueue : public FPipe{
public:
   FQueue();
   FQueue(TSize capacity);
   FQueue(TByte* pMemory, TSize capacity);
   MO_ABSTRACT ~FQueue();
public:
   TBool Push(TAnyC* pData, TInt size);
   TInt Pop(TAny* pData, TInt capacity);
};

//============================================================
// <T>缓冲队列块。</T>
//============================================================
class MO_CM_DECLARE FBufferedQueueBlock : public FStreamBlock{
public:
   TInt _count;
   TInt _countMax;
public:
   FBufferedQueueBlock();
   MO_ABSTRACT ~FBufferedQueueBlock();
public:
   TBool TestPushAble(TInt length);
   TBool TestPopAble(TInt length);
public:
   EStreamResult Push(TAnyC* pData, TInt length);
   EStreamResult Pop(TAny* pData, TInt capacity, TInt* pLength);
public:
   MO_OVERRIDE void Track(MString* pTrack);
};
//------------------------------------------------------------
typedef MO_CM_DECLARE FList<FBufferedQueueBlock*> FBufferedQueueBlockList;

//============================================================
// <T>数据队列分块收集器。</T>
//============================================================
class MO_CM_DECLARE FBufferedQueueBlockAllocator : public FStreamBlockAllocator{
public:
   //------------------------------------------------------------
   // <T>构造数据队列分块缓冲池。</T>
   FBufferedQueueBlockAllocator(){
   }
   //------------------------------------------------------------
   // <T>析构数据队列分块缓冲池。</T>
   MO_ABSTRACT ~FBufferedQueueBlockAllocator(){
   }
public:
   //------------------------------------------------------------
   // <T>创建一个队列分块。</T>
   MO_OVERRIDE FStreamBlock* Create(){
      return MO_CREATE(FBufferedQueueBlock);
   }
};
//------------------------------------------------------------
typedef MO_CM_DECLARE FVector<FBufferedQueueBlockAllocator*> FBufferedQueueBlockAllocatorVector;

//============================================================
// <T>缓冲队列信息。</T>
//============================================================
struct MO_CM_DECLARE SQueueInfo{
   TInt32 count;
   TInt32 length;
   TInt64 countPush;
   TInt64 lengthPush;
   TInt64 countPop;
   TInt64 lengthPop;
};

//============================================================
// <T>缓冲队列。</T>
//============================================================
class MO_CM_DECLARE FBufferedQueue : public FObject{
protected:
   EStreamLock _lockCd;
   TInt64 _countPush;
   TInt64 _lengthPush;
   TInt64 _countPop;
   TInt64 _lengthPop;
protected:
   TThreadLocker _locker;
   FStreamBlockPool* _pPool;
   FBufferedQueueBlock* _pReadBlock;
   FBufferedQueueBlockList* _pReadBlocks;
   FBufferedQueueBlock* _pWriteBlock;
   FBufferedQueueBlockList* _pWriteBlocks;
public:
   FBufferedQueue();
   MO_ABSTRACT ~FBufferedQueue();
public:
   //------------------------------------------------------------
   // <T>获得锁定方式。</T>
   MO_INLINE EStreamLock LockCd(){
      return _lockCd;
   }
   //------------------------------------------------------------
   // <T>设置锁定方式。</T>
   MO_INLINE void SetLockCd(EStreamLock lockCd){
      _lockCd = lockCd;
   }
   //------------------------------------------------------------
   // <T>获得放入个数。</T>
   MO_INLINE TInt64 CountPush(){
      return _countPush;
   }
   //------------------------------------------------------------
   // <T>获得放入长度。</T>
   MO_INLINE TInt64 LengthPush(){
      return _lengthPush;
   }
   //------------------------------------------------------------
   // <T>获得弹出个数。</T>
   MO_INLINE TInt64 CountPop(){
      return _countPop;
   }
   //------------------------------------------------------------
   // <T>获得弹出长度。</T>
   MO_INLINE TInt64 LengthPop(){
      return _lengthPop;
   }
   //------------------------------------------------------------
   // <T>获得缓冲池。</T>
   MO_INLINE FStreamBlockPool* Pool(){
      return _pPool;
   }
   //------------------------------------------------------------
   // <T>获得缓冲收集器。</T>
   MO_INLINE FBufferedQueueBlockAllocator* Allocator(){
      return (FBufferedQueueBlockAllocator*)_pPool->Allocator();
   }
   //------------------------------------------------------------
   // <T>设置缓冲收集器。</T>
   MO_INLINE void SetAllocator(FBufferedQueueBlockAllocator* pAllocator){
      _pPool->SetAllocator(pAllocator);
   }
   //------------------------------------------------------------
   // <T>获得读取块集合。</T>
   MO_INLINE FBufferedQueueBlockList* ReadBlocks(){
      return _pReadBlocks;
   }
   //------------------------------------------------------------
   // <T>获得写入块集合。</T>
   MO_INLINE FBufferedQueueBlockList* WriteBlocks(){
      return _pWriteBlocks;
   }
protected:
   TBool InnerPush(TAnyC* pData, TInt length);
   TInt InnerPop(TAny* pData, TInt capacity);
public:
   TInt Count();
   TBool TestPushAble(TInt length);
   TBool TestPopAble();
public:
   TBool Push(TAnyC* pData, TInt length);
   TInt Pop(TAny* pData, TInt capacity);
   TBool BlockedPush(TAnyC* pData, TInt length);
   TInt BlockedPop(TAny* pData, TInt capacity);
public:
   TBool FetchInfo(SQueueInfo* pInfo);
   void Dump();
};

//============================================================
// <T>数据管道。</T>
//
// @history 100309 MAOCY 创建
//============================================================
class MO_CM_DECLARE FSharedPipe :
      public FObject,
      public MShared,
      public MPipe{
protected:
   TSize _capacity;
   SPipeInfo* _gInfo;
   TByte* _gData;
public:
   FSharedPipe();
   FSharedPipe(TSize capacity);
   MO_ABSTRACT ~FSharedPipe(){
   }
public:
   static TSize CalculateCapacity(TSize capacity);
public:
   TSize Capacity();
   void SetCapacity(TSize capacity);
   MO_OVERRIDE void OnSharedInitialize();
   MO_OVERRIDE void OnSharedLink(TShareSegment& segment);
   MO_OVERRIDE TSize SharedCapacity();
public:
   MO_OVERRIDE SPipeInfo* Info();
   MO_OVERRIDE SPipeAtom Atom();
public:
   MO_ABSTRACT TBool Create();
public:
   MO_OVERRIDE TBool IsEmpty();
   MO_OVERRIDE TInt Length();
   MO_OVERRIDE TInt Reamin();
   TBool Reset();
};

//============================================================
// <T>数据队列。</T>
//
// @history 100309 MAOCY 创建
//============================================================
class MO_CM_DECLARE FSharedQueue : public FSharedPipe{
public:
   FSharedQueue();
   FSharedQueue(TSize capacity);
   MO_ABSTRACT ~FSharedQueue(){
   }
public:
   TBool FollowPush(TInt size);
public:
   TBool Push(TAnyC* pData, TInt size);
   TBool TryPush(TAnyC* pData, TInt size);
   TInt Pop(TAny* pData, TInt capacity);
public:
   TBool BlockedPush(TAnyC* pData, TInt size);
   TInt BlockedPop(TAny* pData, TInt capacity);
	TBool DumpInfo(TChar* pTag);
};

//============================================================
// <T>数据队列。</T>
//
// @history 100309 MAOCY 创建
//============================================================
class MO_CM_DECLARE FSharedQueueConnection : public MShared{
protected:
   FSharedQueue* _pInputQueue;
   FSharedQueue* _pOutputQueue;
public:
   FSharedQueueConnection();
   FSharedQueueConnection(TSize inputCapacity, TSize outputCapacity);
   MO_ABSTRACT ~FSharedQueueConnection();
public:
   void SetInputCapacity(TSize capacity);
   void SetOutputCapacity(TSize capacity);
   MO_OVERRIDE void OnSharedLink(TShareSegment& segment);
   MO_OVERRIDE TSize SharedCapacity();
public:
   FSharedQueue* InputQueue();
   FSharedQueue* OutputQueue();
};

MO_NAMESPACE_END

#endif // __MO_CM_PIPE_H__
