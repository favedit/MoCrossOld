#ifndef __MO_CM_ATTRIBUTES_H__
#define __MO_CM_ATTRIBUTES_H__

#ifndef __MO_CM_TYPE_H__
#include "MoCmType.h"
#endif // __MO_CM_TYPE_H__

#ifndef __MO_CM_COLLECTION_H__
#include "MoCmCollection.h"
#endif // __MO_CM_COLLECTION_H__

#ifndef __MO_CM_STRING_H__
#include "MoCmString.h"
#endif // __MO_CM_STRING_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>属性表节点</T>
//
// @struct
//============================================================
struct SAttributesEntry{
public:
   SAttributesEntry* linkPtr;
   SAttributesEntry* priorPtr;
   SAttributesEntry* nextPtr;
   THashCode hash;
   FString* namePtr;
   FString* valuePtr;
public:
   //------------------------------------------------------------
   // <T>构造属性表节点</T>
   SAttributesEntry(){
      MO_CLEAR(linkPtr);
      MO_CLEAR(priorPtr);
      MO_CLEAR(nextPtr);
      hash = 0;
      MO_CLEAR(namePtr);
      MO_CLEAR(valuePtr);
   }
   //------------------------------------------------------------
   // <T>析构属性表节点</T>
   ~SAttributesEntry(){
      MO_DELETE(namePtr);
      MO_DELETE(valuePtr);
   }
public:
   //------------------------------------------------------------
   // <T>判断名称是否相等。</T>
   MO_INLINE TBool IsName(TCharC* pName) const{
      if((NULL != namePtr) && (NULL != pName)){
         return namePtr->Equals(pName);
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>判断不区分大小写名称是否相等。</T>
   MO_INLINE TBool IsNameIgnoreCase(TCharC* pName) const{
      if((NULL != namePtr) && (NULL != pName)){
         return namePtr->EqualsIgnoreCase(pName);
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>获得字符串名称。</T>
   MO_INLINE TCharC* Name() const{
      if(NULL == namePtr){
         return NULL;
      }
      return namePtr->MemoryC();
   }
   //------------------------------------------------------------
   // <T>设置字符串名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      if(NULL == namePtr){
         namePtr = MO_CREATE(FString);
      }
      namePtr->Assign(pName);
   }
   //------------------------------------------------------------
   // <T>获得字符串内容。</T>
   MO_INLINE TCharC* Value() const{
      if(NULL == valuePtr){
         return NULL;
      }
      return valuePtr->MemoryC();
   }
   //------------------------------------------------------------
   // <T>设置字符串内容。</T>
   MO_INLINE void SetValue(TCharC* pValue){
      if(NULL == valuePtr){
         valuePtr = MO_CREATE(FString);
      }
      valuePtr->Assign(pValue);
   }
public:
   //------------------------------------------------------------
   // <T>释放内容。</T>
   MO_INLINE void Release(){
      MO_DELETE(namePtr);
      MO_DELETE(valuePtr);
   }
};

//============================================================
// <T>只读链表迭代器。</T>
//
// @tool
//============================================================
class MO_CM_DECLARE TAttributesIteratorC{
public:
   typedef SAttributesEntry SEntry;
protected:
   SEntry* _pStart;
   SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // 构造只读迭代器
   TAttributesIteratorC(){
      MO_CLEAR(_pStart);
      MO_CLEAR(_pEntry);
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TAttributesIteratorC(const TAttributesIteratorC& iterator){
      _pStart = iterator._pStart;
      _pEntry = iterator._pEntry;
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TAttributesIteratorC(SEntry* pEntry){
      _pStart = pEntry;
      MO_CLEAR(_pEntry);
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const FString& operator *() const{
      MO_ASSERT(_pEntry);
      return *(_pEntry->namePtr);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const FString* operator->() const{
      MO_ASSERT(_pEntry);
      return _pEntry->valuePtr;
   }
public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE TBool IsEmpty(){
      return (NULL == _pEntry);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasNext(){
      return (NULL == _pEntry) ? (NULL != _pStart) : (NULL != _pEntry->nextPtr);
   }
   //------------------------------------------------------------
   //<T>移动到下一个位置。</T>
   MO_INLINE TBool Next(){
      _pEntry = (NULL == _pEntry) ? _pStart : _pEntry->nextPtr;
      return (NULL != _pEntry);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasPrior(){
      return (NULL == _pEntry) ? (NULL != _pStart) : (NULL != _pEntry->priorPtr);
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE TBool Prior(){
      _pEntry = (NULL == _pEntry) ? _pStart : _pEntry->priorPtr;
      return (NULL != _pEntry);
   }
   //------------------------------------------------------------
   // <T>重置位置。</T>
   MO_INLINE void Reset(){
      _pEntry = NULL;
   }
public:
   //------------------------------------------------------------
   // <T>判断名称是否相同。</T>
   MO_INLINE TBool IsName(TCharC* pName) const{
      MO_ASSERT(_pEntry);
      return _pEntry->namePtr->Equals(pName);
   }
   //------------------------------------------------------------
   // <T>判断内容是否相同。</T>
   MO_INLINE TBool IsValue(TCharC* pValue) const{
      MO_ASSERT(_pEntry);
      return _pEntry->valuePtr->Equals(pValue);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据名称。</T>
   MO_INLINE TCharC* Name() const{
      MO_ASSERT(_pEntry);
      return _pEntry->namePtr->MemoryC();
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE TCharC* Value() const{
      MO_ASSERT(_pEntry);
      return _pEntry->valuePtr->MemoryC();
   }
};

//============================================================
// <T>可写链表迭代器。</T>
//
// @tool
//============================================================
class MO_CM_DECLARE TAttributesIterator : public TAttributesIteratorC{
public:
   typedef SAttributesEntry SEntry;
public:
   //------------------------------------------------------------
   // 构造链表迭代器
   TAttributesIterator(){
   }
   //------------------------------------------------------------
   // 构造链表迭代器
   TAttributesIterator(const TAttributesIteratorC& iterator) :
         TAttributesIteratorC(iterator){
   }
   //------------------------------------------------------------
   // 构造链表迭代器
   TAttributesIterator(SEntry* pEntry) :
         TAttributesIteratorC(pEntry){
   }
public:
   //------------------------------------------------------------
   // 设置当前位置的数据内容
   inline void SetValue(TCharC* pValue) const{
      MO_ASSERT(_pEntry);
      _pEntry->valuePtr->Assign(pValue);
   }
};

//============================================================
// <T>属性表（键值和内容都为字符串的哈希表）。</T>
//
// @manager
// @history 091207 MAOCY 创建
//============================================================
class MO_CM_DECLARE MAttributes{
public:
   typedef SAttributesEntry SEntry;
   typedef TAttributesIteratorC TIteratorC;
   typedef TAttributesIterator TIterator;
protected:
   TInt _count;
   SEntry* _pFirst;
   SEntry* _pLast;
   SEntry* _pUnused;
   TInt _entryCount;
   SEntry** _ppEntries;
protected:
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   MAttributes(){
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
      MO_CLEAR(_pUnused);
      _entryCount = 0;
      MO_CLEAR(_ppEntries);
   }
   //------------------------------------------------------------
   // <T>析构属性表。</T>
   MO_ABSTRACT ~MAttributes(){
   }
protected:
   MO_VIRTUAL SEntry* EntryCreate() = 0;
   MO_VIRTUAL void EntryRelease(SEntry* pEntry) = 0;
protected:
{source_entry_list}
   //------------------------------------------------------------
   // <T>查找指定名称的索引位置。</T>
   SEntry* EntryFind(TCharC* pName) const{
      // 数据存在时
      if(_count > 0){
         THashCode hash = RString::MakeHashCode(pName);
         // 查找名称的索引位置
         SEntry* pEntry = _ppEntries[hash % _entryCount];
         while(NULL != pEntry){
            if(pEntry->hash == hash){
               if(pEntry->IsName(pName)){
                  return pEntry;
               }
            }
            pEntry = pEntry->linkPtr;
         }
      }
      // 未找到时返回结果
      return NULL;
   }
   //------------------------------------------------------------
   // <T>查找指定名称的索引位置。</T>
   SEntry* EntryFindValue(TCharC* pValue) const{
      // 数据存在时
      if(_count > 0){
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->valuePtr->Equals(pValue)){
               return pEntry;
            }
            pEntry = pEntry->nextPtr;
         }
      }
      // 未找到时返回结果
      return NULL;
   }
public:
   //------------------------------------------------------------
   TCharC* operator[](TCharC* pName){
      SEntry* pEntry = EntryFind(pName);
      MO_ASSERT(pEntry);
      return pEntry->Value();
   }
public:
   //------------------------------------------------------------
   // <T>当前哈希集合对象是否为空。</T>
   TBool IsEmpty() const{
      return (0 == _count);
   }
   //------------------------------------------------------------
   // <T>获得数据个数。</T>
   TInt Count() const{
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得属性表只读迭代器。</T>
   TIteratorC IteratorC() const{
      return TAttributesIteratorC(_pFirst);
   }
   //------------------------------------------------------------
   // <T>判断指定名称是否存在。</T>
   TBool Contains(TCharC* pName) const{
      return (NULL != EntryFind(pName));
   }
   //------------------------------------------------------------
   // <T>判断指定内容是否存在。</T>
   TBool ContainsValue(TCharC* pValue) const{
      return (NULL != EntryFindValue(pValue));
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   TCharC* Find(TCharC* pName) const{
      SEntry* pEntry = EntryFind(pName);
      if(NULL != pEntry){
         return pEntry->Value();
      }
      return NULL;
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   TCharC* FindNvl(TCharC* pName, TCharC* pDefault) const{
      SEntry* pEntry = EntryFind(pName);
      if(NULL != pEntry){
         return pEntry->Value();
      }
      return pDefault;
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   TCharC* Get(TCharC* pName) const{
      SEntry* pEntry = EntryFind(pName);
      MO_ASSERT(pEntry);
      return pEntry->Value();
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   TCharC* Search(TCharC* pValue) const{
      SEntry* pEntry = EntryFindValue(pValue);
      if(NULL != pEntry){
         return pEntry->Name();
      }
      return NULL;
   }
public:
   //------------------------------------------------------------
   // <T>确保当前对象可以容纳指定大小的数据。</T>
   // 当哈希表是旧表的8倍时开始扩充，扩大为当前指定大小。</P>
   void EnsureSize(TInt size){
      if(NULL == _ppEntries) {
         _entryCount = MO_LIB_MAX(size, MO_OBJECT_CAPACITY);
         // 第一次新建时，生成哈希表
         _ppEntries = MO_TYPES_ALLOC(SEntry*, _entryCount);
         RTypes<SEntry*>::Clear(_ppEntries, _entryCount);
      } else if (size > (_entryCount << 3)) {
         // 当总数大于节点列表长度8倍时，重新扩充节点列表
         SEntry** ppEntries = MO_TYPES_ALLOC(SEntry*, size);
         RTypes<SEntry*>::Clear(ppEntries, size);
         // 循环取出旧的节点列表内容，移到新的节点列表上
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            // 将当前节点存储到新建的节点列表上去
            TInt index = pEntry->hash % size;
            pEntry->linkPtr = ppEntries[index];
            ppEntries[index] = pEntry;
            // 获得保存的下一个节点指针
            pEntry = pEntry->nextPtr;
         }
         // 释放旧节点内存
         MO_FREE(_ppEntries);
         // 保存新的节点列表
         _ppEntries = ppEntries;
         _entryCount = size;
      }
   }
   //------------------------------------------------------------
   // <T>接收源哈希表内的全部数据。</T>
   void Assign(const MAttributes& attributes){
      Clear();
      Append(attributes);
   }
   //------------------------------------------------------------
   // <T>追加源哈希表内的全部数据。</T>
   void Append(const MAttributes& attributes){
      TAttributesIterator iterator = attributes.IteratorC();
      while(iterator.Next()){
         Set(iterator.Name(), iterator.Value());
      }
   }
   //------------------------------------------------------------
   // <T>根据名称设置数据。</T>
   void Set(TCharC* pName, TCharC* pValue){
      THashCode hash = RString::MakeHashCode(pName);
      TInt index = hash % _entryCount;
      // 查找数据出现的位置
      SEntry* pEntry = _ppEntries[index];
      while(NULL != pEntry) {
         if(pEntry->hash == hash){
            if(pEntry->IsName(pName)){
               pEntry->valuePtr->Assign(pValue);
               return;
            }
         }
         pEntry = pEntry->linkPtr;
      }
      // 如果名称不存在，为新建节点检查内存
      EnsureSize(_count + 1);
      pEntry = EntryAlloc();
      index = hash % _entryCount;
      pEntry->hash = hash;
      pEntry->linkPtr = _ppEntries[index];
      pEntry->SetName(pName);
      pEntry->SetValue(pValue);
      _count++;
      _ppEntries[index] = pEntry;
      // 追加到链表尾部
      EntryPush(pEntry);
   }
   //------------------------------------------------------------
   TString Remove(TCharC* pName){
      THashCode hash = RString::MakeHashCode(pName);
      TInt index = hash % _entryCount;
      // 查找数据出现的位置
      TString value;
      SEntry* pPrior = _ppEntries[index];
      SEntry* pEntry = pPrior;
      while(NULL != pEntry){
         if(pEntry->hash == hash){
            if(pEntry->IsName(pName)){
               value.AssignPointer(pEntry->valuePtr);
               if(pEntry == pPrior){
                  // 当前对象是第一个对象时
                  _ppEntries[index] = pEntry->linkPtr;
               }else{
                  // 当前对象不是第一个对象时
                  pPrior->linkPtr = pEntry->linkPtr;
               }
               // 删除当前节点
               EntryRemove(pEntry);
               // 释放当前节点
               EntryFree(pEntry);
               break;
            }
         }
         pPrior = pEntry;
         pEntry = pEntry->linkPtr;
      }
      return value;
   }
   //------------------------------------------------------------
   // <T>清空内容。</T>
   void Clear(){
      // 将所有节点设置为未使用
      if(_count > 0){
         _pLast->nextPtr = _pUnused;
         _pUnused = _pFirst;
      }
      // 清除表格
      MO_LIB_TYPES_CLEAR(SEntry*, _ppEntries, _entryCount);
      // 清空内容
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
   }
   //------------------------------------------------------------
   // <T>释放内容。</T>
   void Release(){
      // 将所有节点设置为未使用
      if(_count > 0){
         _pLast->nextPtr = _pUnused;
         _pUnused = _pFirst;
      }
      // 删除所有节点
      SEntry* pEntry = _pUnused;
      while(NULL != pEntry){
         SEntry* pNext = pEntry->nextPtr;
         this->EntryRelease(pEntry);
         pEntry = pNext;
      }
      // 清空内容
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
      MO_CLEAR(_pUnused);
      // 释放节点数组
      _entryCount = 0;
      MO_FREE(_ppEntries);
   }
   //------------------------------------------------------------
   // <T>获得对象运行时信息。</T>
   TString Dump() const{
      TString dump = TC("MAttributes{");
      dump.AppendInt(_count);
      if(_count > 0){
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            dump.Append(*pEntry->namePtr);
            dump.Append(TC("="));
            dump.Append(*pEntry->valuePtr);
            // 查找下一个
            pEntry = pEntry->nextPtr;
            if(NULL != pEntry){
               dump.Append(',');
            }
         }
      }
      return dump;
   }
};

//============================================================
// <T>属性表。</T>
//============================================================
class MO_CM_DECLARE TAttributes : public MAttributes{
public:
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   TAttributes(){
      EnsureSize(MO_OBJECT_CAPACITY);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   TAttributes(TInt capacity){
      EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>析构属性表。</T>
   MO_ABSTRACT ~TAttributes(){
      Release();
   }
protected:
   //------------------------------------------------------------
   // <T>新建一个节点。</T>
   MO_OVERRIDE SEntry* EntryCreate(){
      SEntry* pEntry = MO_TYPE_ALLOC(SEntry);
      MO_ASSERT(pEntry);
      MO_LIB_TYPE_CLEAR(SEntry, pEntry);
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>释放一个节点。</T>
   MO_OVERRIDE void EntryRelease(SEntry* pEntry){
      MO_ASSERT(pEntry);
      if(NULL != pEntry){
         pEntry->Release();
      }
      MO_FREE(pEntry);
   }
};

//============================================================
// <T>属性表。</T>
//============================================================
class MO_CM_DECLARE FAttributes :
      public FObject,
      public MAttributes{
public:
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FAttributes(){
      EnsureSize(MO_OBJECT_CAPACITY);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FAttributes(TInt capacity){
      EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>析构属性表。</T>
   MO_ABSTRACT ~FAttributes(){
      Release();
   }
protected:
   //------------------------------------------------------------
   // <T>新建一个节点。</T>
   MO_OVERRIDE SEntry* EntryCreate(){
      SEntry* pEntry = MO_TYPE_ALLOC(SEntry);
      MO_ASSERT(pEntry);
      MO_LIB_TYPE_CLEAR(SEntry, pEntry);
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>释放一个节点。</T>
   MO_OVERRIDE void EntryRelease(SEntry* pEntry){
      MO_ASSERT(pEntry);
      if(NULL != pEntry){
         pEntry->Release();
      }
      MO_FREE(pEntry);
   }
};

////============================================================
//// <T>只读属性表（键值和内容都为字符串的哈希表）。</T>
////
//// @manager
//// @history 091207 MAOCY 创建
////============================================================
//class MO_CM_DECLARE MNcAttributesC : public MEntriesC<SAttributesEntry>{
//public:
//   typedef SAttributesEntry SEntry;
//   typedef TAttributesIteratorC TIteratorC;
//protected:
//   TInt _entryCount;
//   SAttributesEntry** _ppEntries;
//protected:
//   //------------------------------------------------------------
//   // <T>内部初始化。</T>
//   inline void InnerInitialize(){
//      MEntriesC<SEntry>::InnerInitialize();
//      _entryCount = 0;
//      _ppEntries = NULL;
//   }
//public:
//   //------------------------------------------------------------
//   TCharC* operator[](TCharC* pName){
//      SAttributesEntry* pEntry = EntryFind(pName);
//      MO_ASSERT(pEntry);
//      return pEntry->Value();
//   }
//protected:
//   //------------------------------------------------------------
//   // <T>查找指定名称的索引位置。</T>
//   SAttributesEntry* EntryFind(TCharC* pName) const{
//      // 数据存在时
//      if(_count > 0){
//         THashCode hash = RString::MakeNocaseHashCode(pName);
//         // 查找名称的索引位置
//         SAttributesEntry* pEntry = _ppEntries[hash % _entryCount];
//         while(NULL != pEntry){
//            if(pEntry->hash == hash){
//               if(pEntry->IsNameIgnoreCase(pName)){
//                  return pEntry;
//               }
//            }
//            pEntry = pEntry->linkPtr;
//         }
//      }
//      // 未找到时返回结果
//      return NULL;
//   }
//   //------------------------------------------------------------
//   // <T>查找指定名称的索引位置。</T>
//   SAttributesEntry* EntryFindValue(TCharC* pValue) const{
//      // 数据存在时
//      if(_count > 0){
//         SAttributesEntry* pEntry = _pFirst;
//         while(NULL != pEntry){
//            if(pEntry->valuePtr->Equals(pValue)){
//               return pEntry;
//            }
//            pEntry = pEntry->nextPtr;
//         }
//      }
//      // 未找到时返回结果
//      return NULL;
//   }
//public:
//   //------------------------------------------------------------
//   // <T>当前哈希集合对象是否为空。</T>
//   TBool IsEmpty() const{
//      return (0 == _count);
//   }
//   //------------------------------------------------------------
//   // <T>获得数据个数。</T>
//   TInt Count() const{
//      return _count;
//   }
//   //------------------------------------------------------------
//   // <T>判断指定名称是否存在。</T>
//   TBool Contains(TCharC* pName) const{
//      return (NULL != EntryFind(pName));
//   }
//   //------------------------------------------------------------
//   TBool ContainsValue(TCharC* pValue) const{
//      return (NULL != EntryFindValue(pValue));
//   }
//   //------------------------------------------------------------
//   // <T>获得指定名称的数据。</T>
//   TCharC* Get(TCharC* pName) const{
//      SAttributesEntry* pEntry = EntryFind(pName);
//      MO_ASSERT(pEntry);
//      return pEntry->valuePtr->MemoryC();
//   }
//   //------------------------------------------------------------
//   // <T>查找指定名称的数据。</T>
//   TCharC* Find(TCharC* pName) const{
//      SAttributesEntry* pEntry = EntryFind(pName);
//      if(NULL != pEntry){
//         return pEntry->valuePtr->MemoryC();
//      }
//      return NULL;
//   }
//   //------------------------------------------------------------
//   // <T>获得指定名称的数据。</T>
//   TCharC* FindNvl(TCharC* pName, TCharC* pDefault) const{
//      SAttributesEntry* pEntry = EntryFind(pName);
//      if(NULL != pEntry){
//         return pEntry->valuePtr->MemoryC();
//      }
//      return pDefault;
//   }
//   //------------------------------------------------------------
//   // <T>获得指定名称的数据。</T>
//   TCharC* Search(TCharC* pValue) const{
//      SAttributesEntry* pEntry = EntryFindValue(pValue);
//      if(NULL != pEntry){
//         return pEntry->namePtr->MemoryC();
//      }
//      return NULL;
//   }
//   //------------------------------------------------------------
//   // <T>获得属性表只读迭代器。</T>
//   TAttributesIteratorC IteratorC() const{
//      return TAttributesIteratorC(_pFirst);
//   }
//   //------------------------------------------------------------
//   // <T>获得对象运行时信息。</T>
//   TString Dump() const{
//      TString dump = TC("MNcAttributesC{");
//      dump.AppendInt(_count);
//      if(_count > 0){
//         SAttributesEntry* pEntry = _pFirst;
//         while(NULL != pEntry){
//            dump.Append(*pEntry->namePtr);
//            dump.Append(TC("="));
//            dump.Append(*pEntry->valuePtr);
//            // 查找下一个
//            pEntry = pEntry->nextPtr;
//            if(NULL != pEntry){
//               dump.Append(',');
//            }
//         }
//      }
//      return dump;
//   }
//};
//
////============================================================
//// 属性集合（键值和内容都为字符串的哈希表）
////
//// @manager
//// @history 091207 MAOCY 创建
////============================================================
//class MO_CM_DECLARE MNcAttributes : public MNcAttributesC{
//public:
//   typedef SAttributesEntry SEntry;
//protected:
//   SEntry* _pUnused;
//protected:
//   //------------------------------------------------------------
//   // <T>内部初始化。</T>
//   inline void InnerInitialize(TInt size){
//      MNcAttributesC::InnerInitialize();
//      _pUnused = NULL;
//      EnsureSize(size);
//   }
//   //------------------------------------------------------------
//   // <T>内部释放。</T>
//   inline void InnerRelease(){
//      // 释放节点
//      if(_count > 0){
//         // 将所有节点设置为未使用
//         _pLast->pNext = _pUnused;
//         _pUnused = _pFirst;
//         // 删除所有节点
//         SEntry* pFind = _pUnused;
//         while(NULL != pFind){
//            SEntry* pNext = pFind;
//            MO_DELETE(pFind);
//            pFind = pNext;
//         }
//      }
//      // 释放节点数组
//      MO_FREE(_ppEntries);
//   }
//public:
//   //------------------------------------------------------------
//   // <T>确保当前对象可以容纳指定大小的数据。</T>
//   // 当哈希表是旧表的8倍时开始扩充，扩大2倍。</P>
//   void EnsureSize(TInt size){
//      if(NULL == _ppEntries) {
//         _entryCount = MO_LIB_MAX(size, MO_OBJECT_CAPACITY);
//         // 第一次新建时，生成哈希表
//         _ppEntries = MO_TYPES_ALLOC(SEntry*, _entryCount);
//         RTypes<SEntry*>::Clear(_ppEntries, _entryCount);
//      } else if (size > (_entryCount << 3)) {
//         // 扩充内存时处理
//         size = _entryCount + ((MO_LIB_MAX(_entryCount, size)) >> 1);
//         // 当总数大于节点列表长度8倍时，重新扩充节点列表
//         SAttributesEntry** ppEntries = MO_TYPES_ALLOC(SEntry*, size);
//         RTypes<SEntry*>::Clear(ppEntries, size);
//         // 循环取出旧的节点列表内容，移到新的节点列表上
//         SAttributesEntry* pEntry = _pFirst;
//         while(NULL == pEntry){
//            // 将当前节点存储到新建的节点列表上去
//            TInt index = pEntry->hash % size;
//            pEntry->linkPtr = ppEntries[index];
//            ppEntries[index] = pEntry;
//               // 获得保存的下一个节点指针
//            pEntry = pEntry->linkPtr;
//         }
//         // 释放旧节点内存
//         MO_FREE(_ppEntries);
//         // 保存新的节点列表
//         _ppEntries = ppEntries;
//         _entryCount = size;
//      }
//   }
//   //------------------------------------------------------------
//   // <T>接收源哈希表内的全部数据。</T>
//   void Assign(const MNcAttributesC& attributes){
//      Clear();
//      Append(attributes);
//   }
//   //------------------------------------------------------------
//   // <T>追加源哈希表内的全部数据。</T>
//   void Append(const MNcAttributesC& attributes){
//      TAttributesIterator iterator = attributes.IteratorC();
//      while(iterator.Next()){
//         Set(iterator.Name(), iterator.Value());
//      }
//   }
//   //------------------------------------------------------------
//   // <T>根据名称设置数据。</T>
//   void Set(TCharC* pName, TCharC* pValue){
//      THashCode hash = RString::MakeNocaseHashCode(pName);
//      TInt index = hash % _entryCount;
//      // 查找数据出现的位置
//      SEntry* pEntry = _ppEntries[index];
//      while(NULL != pEntry) {
//         if(pEntry->hash == hash){
//            if(pEntry->IsNameIgnoreCase(pName)){
//               pEntry->valuePtr->Assign(pValue);
//               return;
//            }
//         }
//         pEntry = pEntry->linkPtr;
//      }
//      // 如果名称不存在，为新建节点检查内存
//      EnsureSize(_count + 1);
//      pEntry = MO_TYPE_ALLOC(SEntry);
//      index = hash % _entryCount;
//      pEntry->hash = hash;
//      pEntry->linkPtr = _ppEntries[index];
//      pEntry->namePtr->Assign(pName);
//      pEntry->valuePtr->Assign(pValue);
//      _count++;
//      _ppEntries[index] = pEntry;
//      // 追加到链表尾部
//      EntryPush(pEntry);
//   }
//   //------------------------------------------------------------
//   // <T>移除指定名称的数据。</T>
//   TString Remove(TCharC* pName){
//      THashCode hash = RString::MakeNocaseHashCode(pName);
//      TInt index = hash % _entryCount;
//      // 查找数据出现的位置
//      TString value;
//      SAttributesEntry* pPrior = _ppEntries[index];
//      SAttributesEntry* pEntry = pPrior;
//      while(NULL != pEntry){
//         if(pEntry->hash == hash){
//            if(pEntry->IsNameIgnoreCase(pName)){
//               value.AssignPointer(pEntry->valuePtr);
//               if(pEntry == pPrior){
//                  // 当前对象是第一个对象时
//                  _ppEntries[index] = pEntry->linkPtr;
//               }else{
//                  // 当前对象不是第一个对象时
//                  pPrior->pLink = pEntry->linkPtr;
//               }
//               // 删除当前节点
//               EntryRemove(pEntry);
//               break;
//            }
//         }
//         pPrior = pEntry;
//         pEntry = pEntry->linkPtr;
//      }
//      return value;
//   }
//   //------------------------------------------------------------
//   // <T>清空当前哈希表全部数据，是哈希表可以再次被利用。</T>
//   void Clear(){
//      EntryClear();
//   }
//};
//
////============================================================
//// <T>属性表对象。</T>
////============================================================
//class MO_CM_DECLARE TNcAttributes : public MNcAttributes{
//public:
//   //------------------------------------------------------------
//   // <T>创建属性表对象。</T>
//   TNcAttributes(){
//      InnerInitialize(MO_OBJECT_CAPACITY);
//   }
//   //------------------------------------------------------------
//   // <T>创建属性表对象。</T>
//   TNcAttributes(TInt capacity){
//      InnerInitialize(capacity);
//   }
//};
//
////============================================================
//// <T>属性表对象。</T>
////============================================================
//class MO_CM_DECLARE FNcAttributes : public MNcAttributes{
//public:
//   //------------------------------------------------------------
//   // <T>构造属性表。</T>
//   FNcAttributes(){
//      InnerInitialize(MO_OBJECT_CAPACITY);
//   }
//   //------------------------------------------------------------
//   // <T>构造属性表。</T>
//   FNcAttributes(TInt capacity){
//      InnerInitialize(capacity);
//   }
//};

MO_NAMESPACE_END

#endif // __MO_CM_ATTRIBUTES_H__
