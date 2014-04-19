#ifndef __MO_CM_PTR_LOOPER_H__
#define __MO_CM_PTR_LOOPER_H__

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
struct SPtrLooperEntry
{
public:
   SPtrLooperEntry* priorPtr;
   SPtrLooperEntry* nextPtr;
   GPtr<T> value;
public:
   //------------------------------------------------------------
   // <T>构造链表的节点定义。</T>
   MO_INLINE SPtrLooperEntry(){
      MO_CLEAR(priorPtr);
      MO_CLEAR(nextPtr);
   }
public:
   //------------------------------------------------------------
   // <T>重置内容。</T>
   MO_INLINE void Reset(){
      MO_CLEAR(priorPtr);
      MO_CLEAR(nextPtr);
      value = NULL;
   }
};

//============================================================
// <T>只读链表的迭代器。</T>
//
// @tool
//============================================================
template <typename T>
class TPtrLooperIteratorC{
public:
   typedef SPtrLooperEntry<T> SEntry;
protected:
   TInt _position;
   TInt _count;
   SEntry* _pPtrLooper;
   SEntry* _pCurrent;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TPtrLooperIteratorC(){
      _position = -1;
      _count = 0;
      MO_CLEAR(_pPtrLooper);
      MO_CLEAR(_pCurrent);
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TPtrLooperIteratorC(const TPtrLooperIteratorC& iterator){
      _position = iterator._position;
      _count = iterator._count;
      _pPtrLooper = iterator._pPtrLooper;
      _pCurrent = iterator._pCurrent;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TPtrLooperIteratorC(TInt count, SEntry* pCurrent){
      _position = -1;
      _count = count;
      _pPtrLooper = pCurrent;
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
            this->_pCurrent = this->_pPtrLooper;
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
            this->_pCurrent = this->_pPtrLooper;
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
class TPtrLooperIterator : public TPtrLooperIteratorC<T>{
public:
   typedef SPtrLooperEntry<T> SEntry;
public:
   //------------------------------------------------------------
   // <T>构造链表迭代器。</T>
   TPtrLooperIterator(){
   }
   //------------------------------------------------------------
   // <T>构造链表迭代器。</T>
   TPtrLooperIterator(const TPtrLooperIteratorC<T>& iterator) :
         TPtrLooperIteratorC<T>(iterator){
   }
   //------------------------------------------------------------
   // <T>构造链表迭代器。</T>
   TPtrLooperIterator(SEntry* pEntry) :
         TPtrLooperIteratorC<T>(pEntry){
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
class MPtrLooper{
public:
   typedef SPtrLooperEntry<T> SEntry;
   typedef TPtrLooperIteratorC<T> TIteratorC;
protected:
   TInt _version;
   TInt _count;
   TInt _recordCount;
   SEntry* _pCurrent;
   SEntry* _pUnused;
public:
   //------------------------------------------------------------
   // <T>构造循环链表。</T>
   MPtrLooper(){
      _version = 0;
      _count = 0;
      _recordCount = -1;
      MO_CLEAR(_pCurrent);
      MO_CLEAR(_pUnused);
   }
   //------------------------------------------------------------
   // <T>析构循环链表。</T>
   ~MPtrLooper(){
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
      if(pEntry == NULL){
         pEntry = MO_TYPE_CREATE(SEntry);
      }else{
         _pUnused = pEntry->nextPtr;
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>删除一个入口。</T>
   void InnerFree(SEntry* pEntry){
      if(pEntry != NULL){
         pEntry->Reset();
         pEntry->nextPtr = _pUnused;
         _pUnused = pEntry;
      }
   }
   //------------------------------------------------------------
   // <T>删除一个入口。</T>
   void InnerDelete(SEntry* pEntry){
      if(pEntry != NULL){
         MO_DELETE(pEntry);
      }
   }
   //------------------------------------------------------------
   // <T>插入一个入口到当前列表。</T>
   void InnerPush(SEntry* pEntry){
      if(pEntry != NULL){
         if(_pCurrent != NULL){
            SEntry* pPrior = _pCurrent->priorPtr;
            pEntry->priorPtr = pPrior;
            pEntry->nextPtr = _pCurrent;
            pPrior->nextPtr = pEntry;
            _pCurrent->priorPtr = pEntry;
         }else{
            pEntry->priorPtr = pEntry;
            pEntry->nextPtr = pEntry;
            _pCurrent = pEntry;
         }
         _count++;
      }
   }
   //------------------------------------------------------------
   // <T>从当前列表删除一个入口。</T>
   void InnerRemove(SEntry* pEntry){
      if(pEntry != NULL){
         // 删除入口
         SEntry* pPrior = pEntry->priorPtr;
         SEntry* pNext = pEntry->nextPtr;
         pPrior->nextPtr = pNext;
         pNext->priorPtr = pPrior;
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
   T* InnerRemoveCurrent(){
      T* pValue = NULL;
      if(_count > 0){
         // 获得内容
         pValue = _pCurrent->value;
         // 移除节点
         InnerRemove(_pCurrent);
      }
      return pValue;
   }
   //------------------------------------------------------------
   // <T>从当前列表移除一个内容。</T>
   void InnerRemoveValue(T* pValue){
      if(_count > 0){
         // 删除首个对象
         if(_pCurrent->value == pValue){
            InnerRemoveCurrent();
            return;
         }
         // 删除其他对象
         SEntry* pCurrent = _pCurrent; 
         SEntry* pNext = _pCurrent->pNext;
         while(pNext != pCurrent){
            if(pNext->value == pValue){
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
   MO_INLINE TIteratorC IteratorC() const{
      return TIteratorC(this->_count, this->_pCurrent);
   }
public:
   //------------------------------------------------------------
   // <T>判断是否包含指定名称的值。</T>
   TBool Contains(T* pValue){
      if(_pCurrent){
         SEntry* pEntry = _pCurrent;
         for(TInt n = 0; n < _count; n++){
            if(pEntry->value == pValue){
               return ETrue;
            }
            pEntry = pEntry->pNext;
         }
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>返回链表的当前位置的内容。</T>
   T* Current(){
      if(_pCurrent != NULL){
         return _pCurrent->value;
      }
      return NULL;
   }
   //------------------------------------------------------------
   // <T>获得链表的下一个内容。</T>
   T* Next(){
      // 移动当前点
      if(_pCurrent != NULL){
         _pCurrent = _pCurrent->nextPtr;
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
   void Push(T* pValue){
      _version++;
      SEntry* pEntry = this->InnerCreate();
      pEntry->value = pValue;
      this->InnerPush(pEntry);
   }
   //------------------------------------------------------------
   // <T>插入一个唯一内容到尾部。</T>
   void PushUnique(T* pValue){
      if(pValue != NULL){
         TBool exists = Contains(pValue);
         if(!exists){
            Push(pValue);
         }
      }
   }
   //------------------------------------------------------------
   // <T>移除当前的元素，并返回该元素的内容。</T>
   T* RemoveCurrent(){
      _version++;
      return this->InnerRemoveCurrent();
   }
   //------------------------------------------------------------
   // <T>从当前列表移除一个内容。</T>
   void Remove(T* pValue){
      _version++;
      this->InnerRemoveValue(pValue);
   }
   //------------------------------------------------------------
   // <T>清除循环链表所有元素。</T>
   void Clear(){
      _version++;
      if(_pCurrent){
         _pCurrent->priorPtr->nextPtr = NULL;
         _pCurrent->priorPtr = this->_pUnused;
         this->_pUnused = _pCurrent;
         _pCurrent = NULL;
      }
      _count = 0;
   }
};

//============================================================
// <T>变长栈读写链表。</T>
//
// @tool
//============================================================
template <typename T>
class GPtrLooper : public MPtrLooper<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   GPtrLooper(){
   }
   //------------------------------------------------------------
   // <T>析构变长读写链表。</T>
   ~GPtrLooper(){
   }
};

//============================================================
// <T>变长栈读写链表。</T>
//
// @tool
//============================================================
template <typename T>
class TPtrLooper : public MPtrLooper<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   TPtrLooper(){
   }
   //------------------------------------------------------------
   // <T>析构变长读写链表。</T>
   ~TPtrLooper(){
   }
};

//============================================================
// <T>变长栈读写链表。</T>
//
// @tool
//============================================================
template <typename T>
class FPtrLooper : public MPtrLooper<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长读写链表。</T>
   FPtrLooper(){
   }
   //------------------------------------------------------------
   // <T>析构变长读写链表。</T>
   ~FPtrLooper(){
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_PTR_LOOPER_H__
