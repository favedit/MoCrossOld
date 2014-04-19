#ifndef __MO_CM_LOOPER_H__
#define __MO_CM_LOOPER_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_ENTRY_H__
#include "MoCmEntry.h"
#endif // __MO_CM_ENTRY_H__

#define MO_COMPLIST_THRESHOLD 8

MO_NAMESPACE_BEGIN

//============================================================
// <T>链表的节点定义。</T>
//
// @struct
//============================================================
template <typename T>
struct SLooperEntry{
   SLooperEntry* pPrior;
   SLooperEntry* pNext;
   T value;
};

//===========================================================./cc
// <T>只读链表的迭代器。</T>
//
// @tool
//============================================================
template <typename T>
class TLooperIteratorC{
public:
   typedef SLooperEntry<T> SEntry;
protected:
   TInt _position;
   TInt _count;
   SEntry* _pLooper;
   SEntry* _pCurrent;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TLooperIteratorC(){
      _position = -1;
      _count = 0;
      MO_CLEAR(_pLooper);
      MO_CLEAR(_pCurrent);
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TLooperIteratorC(const TLooperIteratorC& iterator){
      _position = iterator._position;
      _count = iterator._count;
      _pLooper = iterator._pLooper;
      _pCurrent = iterator._pCurrent;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TLooperIteratorC(TInt count, SEntry* pCurrent){
      _position = -1;
      _count = count;
      _pLooper = pCurrent;
      MO_CLEAR(_pCurrent);
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T& operator *() const{
      MO_ASSERT(this->_pCurrent);
      return this->_pCurrent->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T operator->() const{
      MO_ASSERT(this->_pCurrent);
      return this->_pCurrent->value;
   }
public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE TBool IsEmpty(){
      return (0 == this->_count);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasNext(){
      if(this->_count > 0){
         return (this->_position < this->_count);
      }
      return EFalse;
   }
   //------------------------------------------------------------
   //<T>移动到下一个位置。</T>
   MO_INLINE TBool Next(){
      if(this->_position < this->_count){
         if(NULL == this->_pCurrent){
            this->_pCurrent = this->_pLooper;
         }else{
            this->_pCurrent = this->_pCurrent->pNext;
         }
         this->_position++;
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasPrior(){
      if(this->_count > 0){
         return (this->_position < this->_count);
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE TBool Prior(){
      if(this->_position < this->_count){
         if(NULL == this->_pCurrent){
            this->_pCurrent = this->_pLooper;
         }else{
            this->_pCurrent = this->_pCurrent->pPrior;
         }
         this->_position++;
         return ETrue;
      }
      return EFalse;
   }
public:
   //------------------------------------------------------------
   // <T>判断数据内容是否相等。</T>
   MO_INLINE TBool Equals(T value){
      MO_ASSERT(this->_pCurrent);
      return this->_pCurrent->value == value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T GetC() const{
      MO_ASSERT(this->_pCurrent);
      return this->_pCurrent->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T Get(){
      MO_ASSERT(this->_pCurrent);
      return this->_pCurrent->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   template <typename C>
   MO_INLINE C Get(){
      MO_ASSERT(this->_pCurrent);
      return (C)this->_pCurrent->value;
   }
   //------------------------------------------------------------
   // <T>重置位置。</T>
   MO_INLINE void Reset(){
      this->_position = -1;
      this->_pCurrent = NULL;
   }
};

//============================================================
// <T>可写链表的迭代器。</T>
//
// @tool
//============================================================
template <typename T>
class TLooperIterator : public TLooperIteratorC<T>{
public:
   typedef SLooperEntry<T> SEntry;
public:
   //------------------------------------------------------------
   // <T>构造链表迭代器。</T>
   TLooperIterator(){
   }
   //------------------------------------------------------------
   // <T>构造链表迭代器。</T>
   TLooperIterator(const TLooperIteratorC<T>& iterator) :
         TLooperIteratorC<T>(iterator){
   }
   //------------------------------------------------------------
   // <T>构造链表迭代器。</T>
   TLooperIterator(SEntry* pEntry) :
         TLooperIteratorC<T>(pEntry){
   }
public:
   //------------------------------------------------------------
   // <T>设置当前位置的数据名称。</T>
   MO_INLINE void Set(T value) const{
      MO_ASSERT(this->_pCurrent);
      this->_pCurrent->value = value;
   }
};

//============================================================
// <T>循环链表。</T>
//
// @history 121229 MAOCY 创建
//================ ============================================
template <typename T>
class MLooper{
public:
   typedef SLooperEntry<T> SEntry;
   typedef TLooperIteratorC<T> TIteratorC;
protected:
   TInt _version;
   TInt _count;
   TInt _recordCount;
   SEntry* _pCurrent;
   SEntry* _pUnused;
public:
   //------------------------------------------------------------
   // <T>构造循环链表。</T>
   MLooper(){
      _version = 0;
      _count = 0;
      _recordCount = -1;
      MO_CLEAR(_pCurrent);
      MO_CLEAR(_pUnused);
   }
   //------------------------------------------------------------
   // <T>析构循环链表。</T>
   ~MLooper(){
   }
public:
   //------------------------------------------------------------
   // <T>获得本地版本。</T>
   MO_INLINE TInt NativeVersion(){
      return _version;
   }
   //------------------------------------------------------------
   // <T>获得本地节点。</T>
   MO_INLINE SEntry* NativeCurrent(){
      return _pCurrent;
   }
protected:
   //------------------------------------------------------------
   // <T>创建一个入口对象。</T>
   SEntry* InnerCreate(){
      SEntry* pEntry = _pUnused;
      if(NULL == pEntry){
         pEntry = MO_TYPE_ALLOC(SEntry);
      }else{
         _pUnused = pEntry->pNext;
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>删除一个入口。</T>
   void InnerFree(SEntry* pEntry){
      if(NULL != pEntry){
         pEntry->pNext = _pUnused;
         _pUnused = pEntry;
      }
   }
   //------------------------------------------------------------
   // <T>删除一个入口。</T>
   void InnerDelete(SEntry* pEntry){
      if(NULL != pEntry){
         MO_FREE(pEntry);
      }
   }
   //------------------------------------------------------------
   // <T>插入一个入口到当前列表。</T>
   void InnerPush(SEntry* pEntry){
      if(NULL != pEntry){
         if(NULL != _pCurrent){
            SEntry* pPrior = _pCurrent->pPrior;
            pEntry->pPrior = pPrior;
            pEntry->pNext = _pCurrent;
            pPrior->pNext = pEntry;
            _pCurrent->pPrior = pEntry;
         }else{
            pEntry->pPrior = pEntry;
            pEntry->pNext = pEntry;
            _pCurrent = pEntry;
         }
         _count++;
      }
   }
   //------------------------------------------------------------
   // <T>从当前列表删除一个入口。</T>
   void InnerRemove(SEntry* pEntry){
      if(NULL != pEntry){
         // 删除入口
         SEntry* pPrior = pEntry->pPrior;
         SEntry* pNext = pEntry->pNext;
         pPrior->pNext = pNext;
         pNext->pPrior = pPrior;
         // 设置数据
         _count--;
         if(_count > 0){
            _pCurrent = pNext;
         }else{
            _pCurrent = NULL;
         }
         // 释放入口
         this->InnerFree(pEntry);
      }
   }
   //------------------------------------------------------------
   // <T>从当前列表移除当前内容。</T>
   T InnerRemoveCurrent(){
      T value = NULL;
      if(_count > 0){
         // 获得内容
         value = _pCurrent->value;
         // 移除节点
         InnerRemove(_pCurrent);
      }
      return value;
   }
   //------------------------------------------------------------
   // <T>从当前列表移除一个内容。</T>
   void InnerRemoveValue(T value){
      if(_count > 0){
         // 删除首个对象
         if(_pCurrent->value == value){
            InnerRemoveCurrent();
            return;
         }
         // 删除其他对象
         SEntry* pCurrent = _pCurrent; 
         SEntry* pNext = _pCurrent->pNext;
         while(pNext != pCurrent){
            if(pNext->value == value){
               this->InnerRemove(pNext);
               // 重置到原始位置
               _pCurrent = pCurrent;
               return;
            }
            pNext = pNext->pNext;
         }
      }
   }
public:
   //------------------------------------------------------------
   // <T>当前集合是否为空</T>
   MO_INLINE TBool isEmpty(){
      return (0 == this->_count);
   }
   //------------------------------------------------------------
   // <T>获得总数。</T>
   MO_INLINE TInt Count() const{
      return this->_count;
   }
   //------------------------------------------------------------
   // <T>记录当前刻录点。</T>
   MO_INLINE void Record(){
      this->_recordCount = this->_count;
   }
   //------------------------------------------------------------
   // <T>消除当前刻录点。</T>
   MO_INLINE void Unrecord(){
      this->_recordCount = -1;
   }
public:
   //------------------------------------------------------------
   // <T>获得首位置的只读数据迭代器。</T>
   TIteratorC IteratorC() const{
      return TIteratorC(this->_count, this->_pCurrent);
   }
public:
   //------------------------------------------------------------
   // <T>判断是否包含指定名称的值。</T>
   TBool Contains(T value){
      if(_pCurrent){
         SEntry* pEntry = _pCurrent;
         for(TInt n = 0; n < _count; n++){
            if(pEntry->value == value){
               return ETrue;
            }
            pEntry = pEntry->pNext;
         }
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>返回链表的当前位置的内容。</T>
   T Current(){
      if(NULL != _pCurrent){
         return _pCurrent->value;
      }
      return NULL;
   }
   //------------------------------------------------------------
   // <T>获得链表的下一个内容。</T>
   T Next(){
      // 移动当前点
      if(NULL != _pCurrent){
         _pCurrent = _pCurrent->pNext;
      }
      // 检查刻录点（当只有一个元素时，刻录点无效）
      if(_recordCount > 0){
         _recordCount--;
      }else if(_recordCount == 0){
         return NULL;
      }
      // 返回内容
      if(NULL != _pCurrent){
         return _pCurrent->value;
      }
      return NULL;
   }
   //------------------------------------------------------------
   // <T>插入一个内容到尾部。</T>
   void Push(T value){
      _version++;
      SEntry* pEntry = this->InnerCreate();
      pEntry->value = value;
      this->InnerPush(pEntry);
   }
   //------------------------------------------------------------
   // <T>插入一个唯一内容到尾部。</T>
   void PushUnique(T value){
      if(NULL != value){
         TBool exists = Contains(value);
         if(!exists){
            Push(value);
         }
      }
   }
   //------------------------------------------------------------
   // <T>移除当前的元素，并返回该元素的内容。</T>
   T RemoveCurrent(){
      _version++;
      return this->InnerRemoveCurrent();
   }
   //------------------------------------------------------------
   // <T>从当前列表移除一个内容。</T>
   void Remove(T value){
      _version++;
      this->InnerRemoveValue(value);
   }
   //------------------------------------------------------------
   // <T>清除循环链表所有元素。</T>
   void Clear(){
      _version++;
      if(_pCurrent){
         _pCurrent->pPrior->pNext = NULL;
         _pCurrent->pPrior = this->_pUnused;
         this->_pUnused = _pCurrent;
         _pCurrent = NULL;
      }
      _count = 0;
   }
};

//===========================================================./cc
// <T>只读链表的迭代器。</T>
//
// @tool
//============================================================
template <typename T>
class TLooperRefer{
public:
   typedef SLooperEntry<T> SEntry;
   friend class MLooper<T>;
protected:
   MLooper<T>* _pLooper;
   TInt _version;
   SEntry* _pCurrent;
   TInt _recordCount;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TLooperRefer(){
      MO_CLEAR(_pLooper);
      _version = 0;
      MO_CLEAR(_pCurrent);
      _recordCount = -1;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TLooperRefer(MLooper<T>* pLooper){
      Link(pLooper);
   }
protected:
   //------------------------------------------------------------
   // <T>内部检查。</T>
   MO_INLINE void InnerCheck(){
      TInt version = this->_pLooper->NativeVersion();
      if(this->_version != version){
         this->_version = version;
         this->_pCurrent = this->_pLooper->NativeCurrent();
         this->_recordCount = this->_pLooper->Count();
      }
   }
public:
   //------------------------------------------------------------
   // <T>链接循环器。</T>
   MO_INLINE void Link(MLooper<T>* pLooper){
      MO_ASSERT(pLooper);
      this->_pLooper = pLooper;
      this->_version = pLooper->NativeVersion();
      this->_pCurrent = pLooper->NativeCurrent();
      this->_recordCount = -1;
   }
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE TBool IsEmpty(){
      MO_ASSERT(this->_pLooper);
      return this->_pLooper->isEmpty();
   }
   //------------------------------------------------------------
   // <T>获得总数。</T>
   MO_INLINE TInt Count() const{
      MO_ASSERT(this->_pLooper);
      return this->_pLooper->Count();
   }
   //------------------------------------------------------------
   // <T>记录当前刻录点。</T>
   MO_INLINE void Record(){
      // 内部检查
      InnerCheck()();
      // 记录位置
      this->_recordCount = this->_pLooper->Count();
   }
   //------------------------------------------------------------
   // <T>消除当前刻录点。</T>
   MO_INLINE void Unrecord(){
      this->_recordCount = -1;
   }
public:
   //------------------------------------------------------------
   // <T>判断数据内容是否相等。</T>
   MO_INLINE T Current(){
      // 获得对象
      if(NULL != _pCurrent){
         return _pCurrent->value;
      }
      return NULL;
   }
   //------------------------------------------------------------
   //<T>移动到下一个位置。</T>
   MO_INLINE T Next(){
      // 移动当前点
      if(NULL != _pCurrent){
         _pCurrent = _pCurrent->pNext;
      }
      // 检查刻录点（当只有一个元素时，刻录点无效）
      if(_recordCount > 0){
         _recordCount--;
      }else if(_recordCount == 0){
         return NULL;
      }
      // 返回内容
      if(NULL != _pCurrent){
         return _pCurrent->value;
      }
      return NULL;
   }
};

//============================================================
// <T>变长栈读写链表。</T>
//
// @tool
//============================================================
template <typename T>
class TLooper : public MLooper<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   TLooper(){
   }
   //------------------------------------------------------------
   // <T>析构变长读写链表。</T>
   ~TLooper(){
   }
};

//============================================================
// <T>变长堆读写链表。</T>
//
// @tool
//============================================================
template <typename T>
class FLooper :
      public FObject,
      public MLooper<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   FLooper(){
   }
   //------------------------------------------------------------
   // <T>析构变长读写链表。</T>
   ~FLooper(){
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_LOOPER_H__
