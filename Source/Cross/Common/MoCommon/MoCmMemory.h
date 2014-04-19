//============================================================
// <T>内存管理。</T>
//============================================================
#ifndef __MO_CM_MEMORY_H__
#define __MO_CM_MEMORY_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_LOCK_H__
#include "MoCmLock.h"
#endif // __MO_CM_LOCK_H__

#ifndef __MO_CM_VECTOR_H__
#include "MoCmVector.h"
#endif // __MO_CM_VECTOR_H__

#ifndef __MO_CM_LIST_H__
#include "MoCmList.h"
#endif // __MO_CM_LIST_H__

#ifndef __MO_CM_SET_H__
#include "MoCmSet.h"
#endif // __MO_CM_SET_H__

#ifndef __MO_CM_STRING_H__
#include "MoCmString.h"
#endif // __MO_CM_STRING_H__

#define MO_MEMORY_FORMATLENGTH 40

MO_NAMESPACE_BEGIN

//============================================================
struct SAllocatorInfo;
class FAllocatorStorage;
class MShared;

//============================================================
// <T>内存定义。</T>
//============================================================
enum EMemory{
   /// @enum 未知类型
   EMemory_Unknown = 0,
   /// @enum 系统内存
   EMemory_System = 1,
   /// @enum 共享内存
   EMemory_Share = 2,
};

//============================================================
// <T>内存容量工具类。</T>
//============================================================
class MO_CM_DECLARE RMemoryCapacity{
public:
   static TSize Parse(TCharC* pSize);
   static TCharC* Format(TSize size, TChar* pBuffer, TSize length);
};

//============================================================
// <T>内存收集器接口。</T>
//============================================================
class MO_CM_DECLARE IAllocator{
public:
   //------------------------------------------------------------
   // <T>析构内存收集器接口。</T>
   MO_ABSTRACT ~IAllocator(){
   }
public:
   MO_VIRTUAL TAny* Alloc(TInt size = 0) = 0;
   MO_VIRTUAL TAny* Alloc(TCharC* pOwnerName, TCharC* pTypeName, TInt size, TChar8C* pFileName, TInt lineNumber) = 0;
   MO_VIRTUAL void Free(TAny* pMemory) = 0;
};

//============================================================
// <T>内存原子类。</T>
//
// @history 100224 MAOCY 创建
//============================================================
class MO_CM_DECLARE SMemoryEntry{
public:
   IAllocator* pAllocator;
   SMemoryEntry* pPrior;
   SMemoryEntry* pNext;
   TInt* pAlloc;
   TAny* pMemory;
   TUint size;
   //------------------------------------------------------------
   TCharC* pTypeName;
   TChar8C* pFileName;
   TInt fileLine;
   //------------------------------------------------------------
   TUint allocCount;
   TUint freeCount;
   TDateTime createDateTime;
   TDateTime usedDateTime;
public:
   SMemoryEntry(IAllocator* pAllocator);
   MO_ABSTRACT ~SMemoryEntry();
public:
   void SetTypeName(TCharC* pTypeName);
   void SetFileInfo(TChar8C* pFileName, TInt line);
   void Link(TByte* pData, TUint size);
   void Alloc();
   void Free();
};
//------------------------------------------------------------
typedef MO_CM_DECLARE FList<SMemoryEntry*> FMemoryEntryList;

//============================================================
// <T>内存管理工具模板类。</T>
//============================================================
class MO_CM_DECLARE FMemoryAllocator :
      public FBase,
      public IAllocator{
protected:
   SMemoryEntry* _pAlloc;
   SMemoryEntry* _pFirst;
   SMemoryEntry* _pLast;
   SMemoryEntry* _pUnused;
public:
   FMemoryAllocator();
   MO_ABSTRACT ~FMemoryAllocator();
protected:
   void EntryExtend();
   SMemoryEntry* EntryAlloc();
public:
   MO_OVERRIDE TAny* Alloc(TInt size = 0);
   MO_OVERRIDE TAny* Alloc(TCharC* pOwnerName, TCharC* pTypeName, TInt size, TChar8C* pFileName, TInt lineNumber);
   MO_OVERRIDE void Free(TAny* pMemory);
};

//============================================================
// <T>内存管理工具模板类。</T>
//
// @source FMemoryAllocator.cpp
//============================================================
class MO_CM_DECLARE FMemoryLockAllocator : public FMemoryAllocator{
protected:
   TThreadLocker _locker;
public:
   FMemoryLockAllocator();
   MO_ABSTRACT ~FMemoryLockAllocator();
public:
   MO_OVERRIDE TAny* Alloc(TInt size = 0);
   MO_OVERRIDE TAny* Alloc(TCharC* pOwnerName, TCharC* pTypeName, TInt size, TChar8C* pFileName, TInt lineNumber);
   MO_OVERRIDE void Free(TAny* pMemory);
};

//============================================================
// <T>块内存管理类。</T>
//
// @class
//============================================================
class MO_CM_DECLARE FBlockAllocator :
      public FBase,
      public IAllocator{
protected:
   TCharC* _pName;
   TInt _count;
   TInt _blockSize;
   FMemoryEntryList* _pEntries;
   //TInt _total;
   //TInt _memoryTotal;
public:
   FBlockAllocator(TInt blockSize = 0);
   MO_ABSTRACT ~FBlockAllocator();
protected:
   MO_ABSTRACT void EntryExtend();
   MO_INLINE void EntryPush(SMemoryEntry* pEntry);
   MO_INLINE void EntryRemove(SMemoryEntry* pEntry);
   MO_INLINE SMemoryEntry* EntryAlloc();
public:
   TCharC* Name();
   void SetName(TCharC* pName);
   TInt GetMemoryUsed();
   TInt GetMemoryTotal();
public:
   //------------------------------------------------------------
   // <T>获得分块长度。</T>
   MO_INLINE TInt BlockSize(){
      return _blockSize;
   }
   //------------------------------------------------------------
   // <T>设置分块长度。</T>
   MO_INLINE void SetBlockSize(TInt blockSize){
      MO_ASSERT(blockSize > 0);
      if(0 == _blockSize){
         _blockSize = blockSize;
      }else if(_blockSize != blockSize){
         MO_FATAL(TC("Block size is set only once."));
      }
   }
public:
   MO_OVERRIDE TAny* Alloc(TInt size = 0);
   MO_OVERRIDE TAny* Alloc(TCharC* pOwnerName, TCharC* pTypeName, TInt size, TChar8C* pFileName, TInt lineNumber);
   MO_OVERRIDE void Free(TAny* pMemory);
public:
   void Dump();
};
//------------------------------------------------------------
typedef MO_CM_DECLARE FFreeSet<TUint, FBlockAllocator*> FBlockAllocatorFreeSet;
typedef MO_CM_DECLARE FFreeList<FBlockAllocator*> FBlockAllocatorFreeList;

//============================================================
// <T>加锁块内存管理类。</T>
//
// @source FBlockAllocator.cpp
//============================================================
class MO_CM_DECLARE FBlockLockAllocator : public FBlockAllocator{
protected:
   TThreadLocker _locker;
public:
   FBlockLockAllocator(TUint atomSize);
   MO_OVERRIDE ~FBlockLockAllocator();
public:
   MO_OVERRIDE TAny* Alloc(TInt size = 0);
   MO_OVERRIDE TAny* Alloc(TCharC* pOwnerName, TCharC* pTypeName, TInt size, TChar8C* pFileName, TInt lineNumber);
   MO_OVERRIDE void Free(TAny* pMemory);
};
//------------------------------------------------------------
typedef MO_CM_DECLARE FFreeSet<TUint, FBlockLockAllocator*> FBlockLockAllocatorFreeSet;
typedef MO_CM_DECLARE FFreeList<FBlockLockAllocator*> FBlockLockAllocatorFreeList;

//============================================================
// <T>内存管理工具模板类。</T>
// <P>按照内存大小分块存储，相同大小的存储在一起。</P>
//============================================================
class MO_CM_DECLARE FThreadMemory{
protected:
   FBlockAllocatorFreeSet* _pBlockAllocators;
public:
   FThreadMemory();
   MO_ABSTRACT ~FThreadMemory();
public:
   TAny* Alloc(TUint size);
   TAny* Alloc(TCharC* pTypeName, TUint size, TChar8C* pFileName, TInt fileLine);
   void Free(TAny* pMemory);
};

//============================================================
// <T>类型收集信息。</T>
//============================================================
struct MO_CM_DECLARE SAllocatorInfo{
public:
   IMemoryStorage* storagePtr;
   TInt index;
   TInt size;
   TChar8C* ownerNamePtr;
   TChar8C* typeNamePtr;
   TChar8C* fileNamePtr;
   TInt lineNumber;
   TInt count;
   TInt total;
};
//------------------------------------------------------------
typedef MO_CM_DECLARE FFreeVector<SAllocatorInfo*> FAllocatorInfoVector;

//============================================================
// <T>类型收集信息。</T>
//============================================================
struct MO_CM_DECLARE SAllocatorHeader{
public:
   SAllocatorInfo* infoPtr;
};

//============================================================
// <T>收集信息存储器。</T>
//============================================================
class MO_CM_DECLARE FAllocatorStorage :
      public FBase,
      public IMemoryStorage{
protected:
   TBool _able;
   TThreadLocker _locker;
   TInt64 _lengthAlloc;
   TInt64 _lengthFree;
   FAllocatorInfoVector* _pInfos;
public:
   FAllocatorStorage();
   MO_ABSTRACT ~FAllocatorStorage();
public:
   MO_OVERRIDE TBool IsAble();
   MO_OVERRIDE void Enable();
   MO_OVERRIDE void Disable(TBool detail = EFalse);
   MO_OVERRIDE TBool FetchInfo(SMemoryInfo* pInfo);
public:
   MO_OVERRIDE TAny* Alloc(TChar8C* pOwnerName, TChar8C* pTypeName, TInt size, TChar8C* pFileName = NULL, TInt lineNumber = 0);
   MO_OVERRIDE void Free(TAny* pMemory);
public:
   MO_OVERRIDE void Reset();
   MO_OVERRIDE void Dump(TBool detail = EFalse);
};

//============================================================
// <T>内存收集器。</T>
//============================================================
class MO_CM_DECLARE RAllocator{
protected:
   static TThreadLocker _locker;
   static IMemoryStorage* _pStorage;
   static FMemoryAllocator* _pMemoryAllocator;
   static FBlockAllocatorFreeSet* _pBlockAllocatorSet;
   static FBlockAllocatorFreeList* _pBlockAllocators;
   static FBlockLockAllocatorFreeList* _pBlockLockAllocators;
public:
   static void Create();
   static void Destroy();
public:
   static IMemoryStorage* Storage();
public:
   static TAny* Alloc(TUint size);
   static TAny* Alloc(TChar8C* pTypeName, TUint size, TChar8C* pFileName, TInt line);
   static void Free(TAny* pMemory);
public:
   static TAny* TypeAlloc(TUint size);
   static TAny* TypeAlloc(TChar8C* pTypeName, TUint size, TChar8C* pFileName, TInt line);
   static void TypeFree(TAny* pMemory);
public:
   static FBlockAllocator* BlockAllocatorAlloc(TInt size);
   static void BlockAllocatorFree(FBlockAllocator* pAllocator);
public:
   static FBlockLockAllocator* BlockLockAllocatorAlloc(TInt size);
   static void BlockLockAllocatorFree(FBlockLockAllocator* pAllocator);
public:
   static TBool CalculateStatistics(TUint& used, TUint& total);
   static void DumpTrack();
};

//============================================================
// <T>对象管理接口。</T>
//============================================================
class MO_CM_DECLARE RActivator{
protected:
   static TThreadLocker _locker;
public:
   static void Initialize();
   static void Release();
public:
   static TAny* Create(TChar8C* pClassName, TInt size, TChar8C* pFileName, TInt line);
   static void Remove(TAny* pObject);
   static void Destroy(TAny* pObject);
public:
   static void Lock();
   static void Unlock();
};

/*
//============================================================
// <T>定长内存收集器。</T>
//
// @reference
//============================================================
template <typename T>
class RBlockAllocator : public RStatic<RBlockAllocator<T>, EManager>{
protected:
   FBlockLockAllocator* _pAllocator;
public:
   //------------------------------------------------------------
   MO_OVERRIDE( RBlockAllocator() ){
      _pAllocator = RAllocator::BlockLockAllocatorAlloc(sizeof(T));
      //MO_DEBUG("Block memory allocator connect. (type=%s(%d), allocator=0x%08X)", typeid(T).name(), size, _pAllocator);
      return this;
   }
   //------------------------------------------------------------
   MO_OVERRIDE( ~RBlockAllocator() ){
      RAllocator::BlockLockAllocatorFree(_pAllocator);
   }
public:
   //------------------------------------------------------------
   // 收集一块指定大小的内存
   static IAllocator* Allocator(){
      return RBlockAllocator::_static._pAllocator;
   };
   //------------------------------------------------------------
   // 收集一块指定大小的内存
   static T* Alloc(TUint size = 0){
      return (T*)RBlockAllocator::_static._pAllocator->Alloc(size);
   };
   //------------------------------------------------------------
   // 收集一块指定大小的内存
   static T* Alloc(TCharC* pTypeName, TUint size, TChar8C* pFileName, TInt line){
      return (T*)RBlockAllocator::_static._pAllocator->Alloc(pTypeName, size, pFileName, line);
   };
   //------------------------------------------------------------
   // 释放内存
   static void Free(TAny* pMemory){
      RBlockAllocator::_static._pAllocator->Free(pMemory);
   }
};*/

//============================================================
// <T>共享内存描述信息。</T>
//
// @history 100305 MAOCY 创建
//============================================================
struct SShareMemoryInfo{
   // 系统管理标志(MOSM)
   TChar8 flag[4];
   // 内存区大小
   TInt size;
   // 创建日期
   TDateTime createDate;
   // 最后使用日期
   TDateTime updateDate;
};

//============================================================
// <T>共享内存对象管理器。</T>
// <P>共享内存的首位置是信息部分，后面为共享数据部分。</P>
//
// @history 100305 MAOCY 创建
//============================================================
typedef TInt TShareKey;
//------------------------------------------------------------
class MO_CM_DECLARE RShareMemory{
protected:
   static TAny* InnerCreate(TShareKey key, TSize size);
public:
   static TShareKey MakeKey(TCharC* pFileName, TInt id);
   //------------------------------------------------------------
   static TBool FetchInfo(TShareKey key, SShareMemoryInfo& info);
   static TAny* Create(TShareKey key, TSize size);
   static TAny* TryCreate(TShareKey key, TSize size, TBool& created);
   static TAny* Connect(TShareKey key, TBool readOnly = EFalse);
   static void Free(TShareKey key);
};

//============================================================
// <T>共享内存管理对象。</T>
//
// @class
// @history 100303 MAOCY 创建
//============================================================
class MO_CM_DECLARE TShareSegment{
protected:
   TByte* _pMemory;
   TInt _position;
   TInt _length;
   TBool _created;
public:
   //------------------------------------------------------------
   // <T>构造共享内存管理对象。</T>
   TShareSegment(){
      _pMemory = NULL;
      _position = 0;
      _length = 0;
      _created = EFalse;
   }
   //------------------------------------------------------------
   // <T>构造共享内存管理对象。</T>
   TShareSegment(TAny* pMemory, TInt length, TBool created){
      _pMemory = (TByte*)pMemory;
      _position = 0;
      _length = length;
      _created = created;
   }
public:
   void Initialize(TAny* pMemory, TInt length, TBool created);
public:
   TBool IsCreated();
   TAny* Memory();
   TInt Position();
   TInt Length();
   TInt Remain();
   TAny* RemainMemory();
   TAny* Alloc(TSize size);
   TShareSegment CreateSegment(TSize size);
   void SharedLink(MShared* pShared);
   void Check();
public:
   //------------------------------------------------------------
   // <T>从共享内存中收集一块指定类型的内存。</T>
   template <typename T> T* TypeAlloc(){
      TByte* pMemory = _pMemory + _position;
      _position += sizeof(T);
      MO_ASSERT(_length >= _position);
      return (T*)pMemory;
   }
   //------------------------------------------------------------
   // <T>从共享内存中收集指定个数的指定类型的内存。</T>
   template <typename T> T* TypeAlloc(TInt count){
      TByte* pMemory = NULL;
      MO_ASSERT(count > 0);
      pMemory = _pMemory + _position;
      _position += sizeof(T) * count;
      MO_ASSERT(_length >= _position);
      return (T*)pMemory;
   }
   //------------------------------------------------------------
   template <typename T> TShareSegment CreateTypeSegment(){
      TShareSegment segment;
      segment.Initialize(_pMemory + _position, sizeof(T), _created);
      _position += sizeof(T);
      MO_ASSERT(_length >= _position);
      return segment;
   }
   //------------------------------------------------------------
   template <typename T> TShareSegment CreateTypeSegment(TInt count){
      TShareSegment segment;
      TInt capacity = sizeof(T) * count;
      segment.Initialize(_pMemory + _position, capacity, _created);
      _position += capacity;
      MO_ASSERT(_length >= _position);
      return segment;
   }
};

//============================================================
// <T>共享内存收集器接口。</T>
//
// @class
// @history 100303 MAOCY 创建
//============================================================
class MO_CM_DECLARE IShareAllocator{
public:
   //------------------------------------------------------------
   // <T>析构共享内存收集器接口。</T>
   MO_ABSTRACT ~IShareAllocator(){
   }
public:
   MO_VIRTUAL TAny* MemoryC() = 0;
   MO_VIRTUAL TAny* Alloc(TSize size) = 0;
   MO_VIRTUAL TBool Skip(TSize size) = 0;
   MO_VIRTUAL TShareSegment CreateSegment(TSize size) = 0;
   MO_VIRTUAL TBool Free() = 0;
};

//============================================================
// <T>共享内存管理对象。</T>
//
// @class
// @history 100303 MAOCY 创建
//============================================================
class MO_CM_DECLARE FShareMemory :
      public FObject,
      public IShareAllocator{
protected:
   TBool _linked;
   TBool _created;
   TInt _position;
   TInt _capacity;
   TShareKey _key;
   TFsPath _keyName;
   TByte* _pMemory;
#ifdef _MO_WINDOWS
   HANDLE _hMemory;
#endif
public:
   FShareMemory();
   MO_ABSTRACT ~FShareMemory();
public:
   //------------------------------------------------------------
   // <T>获得主键。</T>
   MO_INLINE TShareKey Key(){
      return _key;
   }
   //------------------------------------------------------------
   // <T>设置主键。</T>
   MO_INLINE void SetKey(TShareKey key){
      _key = key;
   }
   //------------------------------------------------------------
   // <T>获得主键名称。</T>
   MO_INLINE TCharC* KeyName(){
      return _keyName;
   }
   //------------------------------------------------------------
   // <T>设置主键名称。</T>
   MO_INLINE void SetKeyName(TCharC* pKeyName){
      _keyName = pKeyName;
   }
   //------------------------------------------------------------
   // <T>判断是否已经关联。</T>
   TBool IsLinked(){
      return _linked;
   }
   //------------------------------------------------------------
   // <T>判断是否已经创建。</T>
   TBool IsCreated(){
      return _created;
   }
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
   // <T>获得内存。</T>
   MO_INLINE TAny* MemoryC(){
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得位置内存。</T>
   MO_INLINE TAny* PositionMemoryC(){
      return _pMemory + _position;
   }
public:
   MO_ABSTRACT TBool Create();
   MO_ABSTRACT TBool Connect();
   // MO_METHOD_ABSTRACT TBool TryCreate(TShareKey key, TSize capacity);
   //MO_METHOD_ABSTRACT TBool Link(TShareKey key);
public:
   MO_ABSTRACT TAny* Alloc(TSize size);
   MO_ABSTRACT TBool Skip(TSize size);
   MO_ABSTRACT TShareSegment CreateSegment(TSize size);
   MO_ABSTRACT TBool Free();
};

//============================================================
// <T>网络数据块存储缓冲。</T>
//
// @class
// @history 100118 MAOCY 创建
//============================================================
template <typename E>
class FEntryStorage : public FObject{
protected:
   TThreadLocker _locker;
   FBlockAllocator* _pAllocator;
   E* _pUnused;
   TUint _count;
   TUint _total;
public:
   //------------------------------------------------------------
   FEntryStorage(){
      _pAllocator = RAllocator::BlockAllocatorAlloc(sizeof(E));
      MO_CLEAR(_pUnused);
      _count = 0;
      _total = 0;
   }
   //------------------------------------------------------------
   ~FEntryStorage(){
      // 释放所有数据块
      RAllocator::BlockAllocatorFree(_pAllocator);
   }
public:
   //------------------------------------------------------------
   TInt Count(){
      return _count;
   }
   //------------------------------------------------------------
   TInt Total(){
      return _total;
   }
   //------------------------------------------------------------
   E* Alloc(){
      E* pEntry = NULL;
      _locker.Enter();
      if(NULL == _pUnused){
         //pEntry = MO_CALLOC(_pAllocator, E);
         _total++;
      }else{
         _count--;
         pEntry = _pUnused;
         _pUnused = pEntry->pNext;
      }
      _locker.Leave();
      pEntry->pNext = NULL;
      return pEntry;
   }
   //------------------------------------------------------------
   TBool AllocList(TInt count, E** ppFirst, E** ppLast){
      E* pFirst = NULL;
      E* pLast = NULL;
      E* pAlloc = NULL;
      _locker.Enter();
      while(--count >= 0){
         // 收集一个数据块
         if(NULL == _pUnused){
            //pAlloc = MO_CALLOC(_pAllocator, E);
            _total++;
         }else{
            pAlloc = _pUnused;
            _pUnused = pAlloc->pNext;
            _count--;
         }
         // 存储数据块
         pAlloc->pNext = NULL;
         if(NULL == pFirst){
            pFirst = pAlloc;
            pLast = pAlloc;
         }else{
            pLast->pNext = pAlloc;
            pLast = pAlloc;
         }
      }
      *ppFirst = pFirst;
      *ppLast = pAlloc;
      _locker.Leave();
      return ETrue;
   }
   //------------------------------------------------------------
   void Free(E* pEntry){
      MO_ASSERT(pEntry);
      _locker.Enter();
      // 释放节点
      pEntry->pNext = _pUnused;
      _pUnused = pEntry;
      _count++;
      _locker.Leave();
   }
   //------------------------------------------------------------
   void FreeList(TInt count, E* pFirst, E* pLast){
      MO_ASSERT(pFirst);
      MO_ASSERT(pLast);
      _locker.Enter();
      _count += count;
      pLast->pNext = _pUnused;
      _pUnused = pFirst;
      _locker.Leave();
   }
};

//============================================================
// <T>网络数据块存储缓冲。</T>
//
// @class
// @history 100118 MAOCY 创建
//============================================================
template <typename E>
class FEntryBufferedAllocator{
protected:
   FEntryStorage<E>* _pStorage;
   E* _pFirst;
   E* _pLast;
   TInt _count;
   TInt _capacity;
   TInt _total;
public:
   //------------------------------------------------------------
   FEntryBufferedAllocator(FEntryStorage<E>* pStorage){
      _pStorage = pStorage;
      _pFirst = NULL;
      _pLast = NULL;
      _count = 0;
      _capacity = 32;
      _total = 64;
   }
   //------------------------------------------------------------
   ~FEntryBufferedAllocator(){
      Release();
   }
public:
   //------------------------------------------------------------
   E* Alloc(){
      // 如果没有可以收集的，则预先收集
      if(NULL == _pFirst){
         _pStorage->AllocList(_capacity, &_pFirst, &_pLast);
         _count += _capacity;
      }
      // 收集一个未使用块
      E* pEntry = _pFirst;
      _pFirst = pEntry->pNext;
      if(NULL == _pFirst){
         _pLast = NULL;
      }
      pEntry->pNext = NULL;
      _count--;
      return pEntry;
   }
   //------------------------------------------------------------
   void Free(E* pEntry){
      MO_ASSERT(pEntry);
      // 释放对象
      _count++;
      if(NULL == _pFirst){
         _pLast = pEntry;
      }else{
         pEntry->pNext = _pFirst;
      }
      _pFirst = pEntry;
      // 大于缓冲个数时全部释放
      if(_count > _total){
         Release();
      }
   }
   //------------------------------------------------------------
   void FreeList(TInt count, E* pFirst, E* pLast){
      MO_ASSERT(pFirst);
      MO_ASSERT(pLast);
      // 释放对象
      _count += count;
      if(NULL == _pFirst){
         _pLast = pLast;
      }else{
         pLast->pNext = _pFirst;
      }
      _pFirst = pFirst;
      // 大于缓冲个数时全部释放
      if(_count > _total){
         Release();
      }
   }
   //------------------------------------------------------------
   void Release(){
      if(NULL != _pFirst){
         // 释放对象
         _pStorage->FreeList(_count, _pFirst, _pLast);
         _count = 0;
         _pFirst = NULL;
         _pLast = NULL;
      }
   }
};

//============================================================
// <T>网络数据块存储缓冲。</T>
//
// @class
// @history 100118 MAOCY 创建
//============================================================
template <typename A, typename E>
class FEntryBufferedStorage : public FEntryStorage<E>{
public:
   typedef FList<A*> FAtomAllocatorList;
protected:
   FAtomAllocatorList* _pAtomAllocators;
public:
   //------------------------------------------------------------
   FEntryBufferedStorage(){
      _pAtomAllocators = MO_CREATE(FAtomAllocatorList);
   }
   //------------------------------------------------------------
   ~FEntryBufferedStorage(){
      // 释放所有缓冲
      TListIteratorC<A*> iterator = _pAtomAllocators->IteratorC();
      while(iterator.Next()){
         A* pAllocator = iterator.Get();
         MO_DELETE(pAllocator);
      }
      MO_DELETE(_pAtomAllocators);
      // 释放所有数据块
      FEntryStorage<E>::Dispose();
   }
public:
   //------------------------------------------------------------
   A* AllocatorAlloc(){
      A* pAllocator = MO_CREATE(A, this);
      _pAtomAllocators->Push(pAllocator);
      return pAllocator;
   }
   //------------------------------------------------------------
   void AllocatorFree(A* pAllocator){
      MO_ASSERT(pAllocator);
      _pAtomAllocators->Remove(pAllocator);
      MO_DELETE(pAllocator);
   }
};

//============================================================
template <typename T>
struct SAtomEntry{
   SAtomEntry* pPrior;
   SAtomEntry* pNext;
   T value;
};

//============================================================
// <T>原子对象存储器。</T>
//
// @class
// @history 100119 MAOCY 创建
//============================================================
template <typename T>
class FAtomStorage : public FObject{
public:
   typedef SAtomEntry<T> SEntry;
protected:
   TThreadSection _locker;
   FBlockAllocator* _pEntryAllocator;
   SEntry* _pFree;
   SEntry* _pUnused;
public:
   //------------------------------------------------------------
   FAtomStorage(){
      _pEntryAllocator = RAllocator::BlockAllocatorAlloc(sizeof(SEntry));
      MO_CLEAR(_pFree);
      MO_CLEAR(_pUnused);
   }
   //------------------------------------------------------------
   ~FAtomStorage(){
      RAllocator::BlockAllocatorFree(_pEntryAllocator);
   }
public:
   //------------------------------------------------------------
   SEntry* EntryAlloc(){
      // 获得未使用的节点
      SEntry* pEntry = NULL;
      if(NULL != _pUnused){
         pEntry = _pUnused;
         _pUnused = _pUnused->pNext;
      }else{
         //pEntry = _pEntryAllocator->Alloc("SEntry", sizeof(SEntry), __FILE__, __LINE__);
      }
      // 设置节点
      pEntry->pNext = NULL;
      return pEntry;
   }
   //------------------------------------------------------------
   void EntryFree(SEntry* pEntry){
      pEntry->pNext = _pFree;
      _pFree = pEntry;
   }
   //------------------------------------------------------------
   void EntryUnused(SEntry* pEntry){
      pEntry->pNext = _pUnused;
      _pUnused = pEntry;
   }
protected:
   MO_VIRTUAL T AtomCreate() = 0;
   MO_VIRTUAL void AtomDelete(T value) = 0;
public:
   //------------------------------------------------------------
   // 收集块对象
   MO_ABSTRACT T Alloc(){
      // 获得一个可用的类型内存
      if(NULL != _pFree){
         T value = _pFree->value;
         SEntry* pNext = _pFree->pNext;
         EntryUnused(_pFree);
         _pFree = pNext;
         return value;
      }
      // 返回收集的元素
      return AtomCreate();
   }
   //------------------------------------------------------------
   // 释放块对象
   MO_ABSTRACT void Free(T pValue){
      // 收集节点
      SEntry* pEntry = EntryAlloc();
      // 设置信息
      pEntry->value = pValue;
      // 存储节点
      EntryFree(pEntry);
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_MEMORY_H__
