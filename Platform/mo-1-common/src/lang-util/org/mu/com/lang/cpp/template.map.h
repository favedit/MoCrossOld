#ifndef __MO_CM_MAP_H__
#define __MO_CM_MAP_H__

#ifndef __MO_CM_TYPE_H__
#include "MoCmType.h"
#endif // __MO_CM_TYPE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>哈希值的计算。</T>
//
// @struct
//============================================================
class RHashCode{
public:
   friend inline THashCode MakeHashCode(TInt8 value){
      return value;
   }
   friend inline THashCode MakeHashCode(TInt16 value){
      return value;
   }
   friend inline THashCode MakeHashCode(TInt32 value){
      return value;
   }
   friend inline THashCode MakeHashCode(TInt64 value){
      return value;
   }
   friend inline THashCode MakeHashCode(TUint8 value){
      return value;
   }
   friend inline THashCode MakeHashCode(TUint16 value){
      return value;
   }
   friend inline THashCode MakeHashCode(TUint32 value){
      return value;
   }
   friend inline THashCode MakeHashCode(TUint64 value){
      return value;
   }
};

//============================================================
// <T>哈希表节点</T>
//
// @struct
//============================================================
template <typename N, typename V>
struct SMapEntry {
   SMapEntry* linkPtr;
   SMapEntry* priorPtr;
   SMapEntry* nextPtr;
   THashCode hash;
   N name;
   V value;
};

//============================================================
// <T>只读迭代器。</T>
//
// @tool
//============================================================
template <typename N, typename V>
class TMapIteratorC{
public:
   typedef SMapEntry<N, V> SEntry;
protected:
   SEntry* _pStart;
   SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TMapIteratorC(){
      InnerInitialize((SEntry*)NULL);
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TMapIteratorC(const TMapIteratorC& iterator){
      InnerInitialize(iterator._pStart);
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TMapIteratorC(SEntry* pEntry){
      InnerInitialize(pEntry);
   }
protected:
   //------------------------------------------------------------
   // <T>内部初始化。</T>
   MO_INLINE void InnerInitialize(SEntry* pEntry){
      _pStart = pEntry;
      _pEntry = NULL;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const V& operator *() const{
      MO_ASSERT(this->_pEntry);
      return this->_pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const V* operator->() const{
      MO_ASSERT(this->_pEntry);
      return &(this->_pEntry->value);
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
   MO_INLINE TBool IsName(N name) const{
      MO_ASSERT(this->_pEntry);
      return (name == this->_pEntry->name);
   }
   //------------------------------------------------------------
   // <T>判断内容是否相同。</T>
   MO_INLINE TBool IsValue(V value) const{
      MO_ASSERT(this->_pEntry);
      return (value == this->_pEntry->value);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据名称。</T>
   MO_INLINE N Name() const{
      MO_ASSERT(this->_pEntry);
      return this->_pEntry->name;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE V Value() const{
      MO_ASSERT(this->_pEntry);
      return this->_pEntry->value;
   }
};

//============================================================
// <T>可写迭代器。</T>
//
// @tool
//============================================================
template <typename N, typename V>
class TMapIterator : public TMapIteratorC<N, V>{
public:
   typedef SMapEntry<N, V> SEntry;
public:
   //------------------------------------------------------------
   // <T>构造可写迭代器。</T>
   TMapIterator(){
   }
   //------------------------------------------------------------
   // <T>构造可写迭代器。</T>
   TMapIterator(const TMapIteratorC<N, V>& iterator) :
         TMapIteratorC<N, V>(iterator){
   }
   //------------------------------------------------------------
   // <T>构造可写迭代器。</T>
   TMapIterator(SEntry* pEntry) :
         TMapIteratorC<N, V>(pEntry){
   }
public:
   //------------------------------------------------------------
   // <T>设置当前位置的数据内容。</T>
   MO_INLINE void SetValue(V value){
      MO_ASSERT(this->_pEntry);
      this->_pEntry->value = value;
   }
};

//============================================================
// <T>只读哈希表对象</T>
//
// @manager
// @history 100427 MAOCY
//============================================================
template <typename N, typename V>
class MMap{
public:
   typedef SMapEntry<N, V> SEntry;
   typedef TMapIteratorC<N, V> TIteratorC;
   typedef TMapIterator<N, V>  TIterator;
protected:
   TInt _count;
   SEntry* _pFirst;
   SEntry* _pLast;
   SEntry* _pUnused;
   TInt _entryCount;
   SEntry** _ppEntries;
protected:
   //------------------------------------------------------------
   // <T>内部初始化。</T>
   MMap(){
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
      MO_CLEAR(_pUnused);
      _entryCount = 0;
      MO_CLEAR(_ppEntries);
   }
protected:
   MO_VIRTUAL SEntry* EntryCreate() = 0;
   MO_VIRTUAL void EntryRelease(SEntry* pEntry) = 0;
   MO_VIRTUAL void EnsureSize(TInt size) = 0;
protected:
{source_entry_list}
public:
   //------------------------------------------------------------
   // <T>判断当前哈希表和指定哈希表中所有数据内容是否相等。</T>
   MO_INLINE TBool operator==(const MMap<N, V>& map) const{
      return Equals(&map);
   }
   //------------------------------------------------------------
   // <T>判断当前哈希表和指定哈希表中所有数据内容是否不相等。</T>
   MO_INLINE TBool operator!=(const MMap<N, V>& map) const{
      return !Equals(&map);
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据内容。</T>
   MO_INLINE V operator[](N name){
      SEntry* pEntry = EntryFind(name);
      MO_ASSERT(pEntry);
      return pEntry->value;
   }
protected:
   //------------------------------------------------------------
   // <T>查找指定名称的索引位置。</T>
   MO_INLINE SEntry* EntryFind(N name) const{
      // 数据存在时
      if(_count > 0){
         THashCode hash = MakeHashCode(name);
         // 查找名称的索引位置
         SEntry* pEntry = _ppEntries[hash % _entryCount];
         while(NULL != pEntry){
            if(pEntry->hash == hash){
               if(name == pEntry->name){
                  return pEntry;
               }
            }
            pEntry = pEntry->nextPtr;
         }
      }
      // 未找到时返回结果
      return NULL;
   }
   //------------------------------------------------------------
   // <T>查找指定名称的索引位置。</T>
   MO_INLINE SEntry* EntryFindValue(V value) const{
      // 数据存在时
      if(_count > 0){
         TInt n = 0;
         while(++n < _entryCount){
            SEntry* pEntry = _ppEntries[n];
            while(NULL != pEntry){
               if(pEntry->value == value){
                  return pEntry;
               }
               pEntry = pEntry->nextPtr;
            }
         }
      }
      // 未找到时返回结果
      return NULL;
   }
   //------------------------------------------------------------
   // 清空链表上所有使用中的节点，放入未使用链表中循环使用
   MO_INLINE void EntryClear(){
      // 清除表格
      TInt n = -1;
      while(++n < _entryCount){
         _ppEntries[n] = NULL;
      }
      // 清除链表
      _count = 0;
      _pFirst = NULL;
      _pLast = NULL;
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
   // <T>判断指定名称是否存在。</T>
   EBoolean Contains(N name) const{
      return (NULL != EntryFind(name));
   }
   //------------------------------------------------------------
   // <T>判断指定名称是否存在。</T>
   EBoolean ContainsValue(V value) const{
      return (NULL != EntryFindValue(value));
   }
   //------------------------------------------------------------
   // <T>判断当前哈希表和指定哈希集合表是否相等。</T>
   TBool Equals(const MMap<N, V>* pMap) const{
      MO_ASSERT(pMap);
      // 比较数量
      if(_count != pMap->Count()){
         return EFalse;
      }
      // 比较所有项目
      SEntry* pEntry = _pFirst;
      TIteratorC iterator = pMap->IteratorC();
      while(iterator.Next()){
         N& name = iterator.Name();
         SEntry* pEntry = EntryFind(name);
         if(NULL == pEntry){
            return EFalse;
         }
         if(!iterator.IsValue(pEntry->value)){
            return EFalse;
         }
      }
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>获得首位置的数据。</T>
   V First() const{
      return (NULL != _pFirst) ? _pFirst->value : NULL;
   }
   //------------------------------------------------------------
   // <T>获得尾位置的数据。</T>
   V Last() const{
      return (NULL != _pLast) ? _pLast->value : NULL;
   }
   //------------------------------------------------------------
   // <T>获得只读迭代器。</T>
   TIteratorC IteratorC(){
      return TIteratorC(_pFirst);
   }
   //------------------------------------------------------------
   // <T>获得结尾只读迭代器。</T>
   TIteratorC LastIteratorC(){
      return TIteratorC(_pLast);
   }
public:
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   V Get(N name) const{
      SEntry* pEntry = EntryFind(name);
      MO_ASSERT(pEntry);
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   V Find(N name) const{
      SEntry* pEntry = EntryFind(name);
      return (NULL != pEntry) ? pEntry->value : (V)NULL;
   }
   //------------------------------------------------------------
   // <T>获得指定数据的名称。</T>
   N Search(V value) const{
      SEntry* pEntry = EntryFindValue(value);
      return (NULL != pEntry) ? pEntry->name : (N)NULL;
   }
   //------------------------------------------------------------
   // <T>复制对象到外部数组。</T>
   TInt CopyTo(V* pBuffer, TInt capacity) const{
      TInt count = MO_LIB_MIN(_count, capacity);
      if(count > 0){
         TInt n = 0;
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            pBuffer[n++] = pEntry->value;
            pEntry = pEntry->nextPtr;
         }
      }
      return count;
   }
protected:
   //------------------------------------------------------------
   // <T>内部释放。</T>
   inline void InnerRelease(){
      // 释放节点集合
      if(_count > 0){
         // 将所有节点设置为未使用
         _pLast->nextPtr = _pUnused;
         _pUnused = _pFirst;
      }
      // 删除所有节点
      SEntry* pFind = _pUnused;
      while(NULL != pFind){
         SEntry* pNext = pFind;
         EntryRelease(pFind);
         pFind = pNext;
      }
      // 删除节点数组
      EntryFlatFree(_ppEntries);
   }
protected:
   //------------------------------------------------------------
   // <T>扩充当前对象的哈希值数组。</T>
   inline void EntriesResize(SEntry** ppEntries, TInt size){
      // 循环取出旧的节点列表内容，移到新的节点列表上
      TInt n = -1;
      while(++n < _entryCount){
         SEntry* pEntry = _ppEntries[n];
         while(NULL != pEntry){
            // 保存当前节点的下一个节点指针
            SEntry* pLink = pEntry->linkPtr;
            // 将当前节点存储到新建的节点列表上去
            TInt index = pEntry->hash % size;
            pEntry->linkPtr = ppEntries[index];
            ppEntries[index] = pEntry;
            // 获得保存的下一个节点指针
            pEntry = pLink;
         }
      }
   }
public:
   //------------------------------------------------------------
   // <T>获得可写迭代器。</T>
   TIterator Iterator(){
      return TIterator(_pFirst);
   }
   //------------------------------------------------------------
   // <T>获得结尾可写迭代器。</T>
   TIterator LastIterator(){
      return TIterator(_pLast);
   }
public:
   //------------------------------------------------------------
   // <T>接收源哈希表内的全部数据。</T>
   void Assign(const MMap<N, V>* pMap){
      MO_ASSERT(pMap);
      Clear();
      Append(pMap);
   }
   //------------------------------------------------------------
   // <T>追加源哈希表内的全部数据。</T>
   void Append(const MMap<N, V>* pMap){
      MO_ASSERT(pMap);
      TIteratorC iterator = pMap->IteratorC();
      while(iterator.Next()){
         Set(iterator.Name(), iterator.Value());
      }
   }
   //------------------------------------------------------------
   // <T>根据名称设置数据。</T>
   void Set(N name, V value){
      THashCode hash = MakeHashCode(name);
      TInt index = hash % _entryCount;
      // 查找数据出现的位置
      SEntry* pEntry = _ppEntries[index];
      while(NULL != pEntry) {
         if(pEntry->hash == hash){
            if(name->Equals(pEntry->name)){
               pEntry->value = value;
               return;
            }
         }
         pEntry = pEntry->linkPtr;
      }
      // 如果名称不存在，为新建节点检查内存
      pEntry = EntryAlloc();
      // 大小可能改变，重新计算索引位置
      index = hash % _entryCount;
      pEntry->linkPtr = _ppEntries[index];
      pEntry->hash = hash;
      pEntry->name = name;
      pEntry->value = value;
      _ppEntries[index] = pEntry;
      // 追加到链表尾部
      EntryPush(pEntry);
   }
   //------------------------------------------------------------
   // <T>移除指定名称的数据。</T>
   V Remove(N name){
      THashCode hash = MakeHashCode(name);
      TInt index = hash % _entryCount;
      // 查找数据出现的位置
      V value = NULL;
      SEntry* pPrior = _ppEntries[index];
      SEntry* pEntry = pPrior;
      while(NULL != pEntry){
         if(pEntry->hash == hash){
            if(name->Equals(pEntry->name)){
               value = pEntry->value;
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
   // <T>清空当前哈希表全部数据。</T>
   void Clear(){
      EntryClear();
   }
};

//============================================================
// <T>变长栈哈希表。</T>
//
// @tool
//============================================================
template <typename N, typename V>
class TMap : public MMap<N, V>{
public:
   typedef SMapEntry<N, V> SEntry;
public:
   //------------------------------------------------------------
   // <T>创建哈希表对象。</T>
   TMap(){
   }
   //------------------------------------------------------------
   // <T>创建哈希表对象。</T>
   TMap(TInt capacity){
      this->EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>创建哈希表对象。</T>
   TMap(const MMap<N, V>& map){
      this->EnsureSize(map.Count());
      this->Append(&map);
   }
   //------------------------------------------------------------
   // <T>析构哈希表对象。</T>
   MO_ABSTRACT ~TMap(){
      this->InnerRelease();
   }
protected:
   //------------------------------------------------------------
   // <T>新建一个未使用的节点。</T>
   MO_OVERRIDE SEntry* EntryCreate(){
      EnsureSize(this->_count + 1);
      return MO_TYPE_CREATE(SEntry);
   }
   //------------------------------------------------------------
   // <T>释放一个指定的节点。</T>
   MO_OVERRIDE void EntryRelease(SEntry* pEntry){
      MO_ASSERT(pEntry);
      MO_FREE(pEntry);
   }
   //------------------------------------------------------------
   // <T>收集节点列表内存。</T>
   MO_OVERRIDE SEntry** EntryFlatCreate(TInt size){
      SEntry** ppEntries = MO_TYPES_ALLOC(SEntry*, size);
      RTypes<SEntry*>::Clear(ppEntries, size);
      return ppEntries;
   }
   //------------------------------------------------------------
   // <T>释放节点列表内存。</T>
   MO_OVERRIDE void EntryFlatRelease(SEntry** pEntries){
      MO_ASSERT(pEntries);
      MO_FREE(pEntries);
   }
public:
   //------------------------------------------------------------
   // <T>复制内容到对象内部。</T>
   MO_INLINE void operator=(const MMap<N, V>& map){
      this->Assign(&map);
   }
public:
   //------------------------------------------------------------
   // <T>确保当前对象可以容纳指定大小的数据。</T>
   // <P>当哈希表是旧表的8倍时开始扩充，扩大2倍。</P>
   MO_OVERRIDE void EnsureSize(TInt size){
      if(NULL == this->_ppEntries) {
         this->_entryCount = MO_LIB_MAX(size, MO_OBJECT_CAPACITY);
         // 第一次新建时，生成哈希表
         this->_ppEntries = MO_TYPES_ALLOC(SEntry*, this->_entryCount);
      } else if (size > (this->_entryCount << 3)) {
         // 扩充内存时处理
         size = this->_entryCount + (MO_LIB_MAX(this->_entryCount, size) >> 1);
         // 当总数大于节点列表长度8倍时，重新扩充节点列表
         SEntry** ppEntries = MO_TYPES_ALLOC(SEntry*, size);
         EntriesResize(ppEntries, size);
         // 释放旧节点内存
         MO_FREE(this->_ppEntries);
         // 保存新的节点列表
         this->_ppEntries = ppEntries;
         this->_entryCount = size;
      }
   }
};

//============================================================
// <T>变长堆哈希表。</T>
//
// @class
//============================================================
template <typename N, typename V>
class FMap : public MMap<N, V>{
public:
   typedef SMapEntry<N, V> SEntry;
public:
   //------------------------------------------------------------
   // <T>构造哈希表对象。</T>
   FMap(){
   }
   //------------------------------------------------------------
   // <T>构造哈希表对象。</T>
   FMap(TInt capacity){
      this->EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>构造哈希表对象。</T>
   FMap(const MMap<N, V>& map){
      this->EnsureSize(map.Count());
      this->Append(&map);
   }
   //------------------------------------------------------------
   // <T>析构哈希表对象。</T>
   MO_ABSTRACT ~FMap(){
      this->InnerRelease();
   }
protected:
   //------------------------------------------------------------
   // <T>新建一个未使用的节点。</T>
   MO_INLINE SEntry* EntryCreate(){
      EnsureSize(this->_count + 1);
      return MO_TYPE_ALLOC(SEntry);
   }
   //------------------------------------------------------------
   // <T>释放一个指定的节点。</T>
   MO_INLINE void EntryRelease(SEntry* pEntry){
      MO_ASSERT(pEntry);
      MO_FREE(pEntry);
   }
   //------------------------------------------------------------
   // <T>收集节点列表内存。</T>
   MO_OVERRIDE SEntry** EntryFlatCreate(TInt size){
      SEntry** ppEntries = MO_TYPES_ALLOC(SEntry*, size);
      RTypes<SEntry*>::Clear(ppEntries, size);
      return ppEntries;
   }
   //------------------------------------------------------------
   // <T>释放节点列表内存。</T>
   MO_OVERRIDE void EntryFlatRelease(SEntry** pEntries){
      MO_ASSERT(pEntries);
      MO_FREE(pEntries);
   }
public:
   //------------------------------------------------------------
   // <T>复制内容到对象内部。</T>
   void operator=(const MMap<N, V>& map){
      this->Assign(&map);
   }
public:
   //------------------------------------------------------------
   // <T>确保当前对象可以容纳指定大小的数据。</T>
   // <P>当哈希表是旧表的8倍时开始扩充，扩大2倍。</P>
   void EnsureSize(TInt size){
      if(NULL == this->_ppEntries) {
         this->_entryCount = MO_LIB_MAX(size, MO_OBJECT_CAPACITY);
         // 第一次新建时，生成哈希表
         this->_ppEntries = MO_TYPES_ALLOC(SEntry*, this->_entryCount);
      } else if (size > (this->_entryCount << 3)) {
         // 扩充内存时处理
         size = this->_entryCount + (MO_LIB_MAX(this->_entryCount, size) >> 1);
         // 当总数大于节点列表长度8倍时，重新扩充节点列表
         SEntry** ppEntries = MO_TYPES_ALLOC(SEntry*, size);
         EntriesResize(ppEntries, size);
         // 释放旧节点内存
         MO_FREE(this->_ppEntries);
         // 保存新的节点列表
         this->_ppEntries = ppEntries;
         this->_entryCount = size;
      }
   }
};

//============================================================
// <T>哈希表对象。</T>
//
// @template
// @type N 名称类型
// @type V 内容类型
// @param S 数据长度
//============================================================
template<typename N, typename V, TInt S>
class TFixMap : public MMap<N, V>{
public:
   typedef SMapEntry<N, V> SEntry;
protected:
   TInt _alloc;
   SEntry _entries[S];
public:
   //------------------------------------------------------------
   // 初始化固定长度的哈希表
   TFixMap(){
      this->InnerInitialize(S);
   }
   //------------------------------------------------------------
   // 初始化固定长度的哈希表
   TFixMap(const MMap<N, V>& map){
      this->InnerInitialize(map.Count());
      Append(&map);
   }
public:
   //------------------------------------------------------------
   // <T>初始化。</T>
   void Initialize(){
      this->InnerInitialize(S);
   }
protected:
   //------------------------------------------------------------
   // 内部初始化
   MO_OVERRIDE void InnerInitialize(TInt size){
      MMap<N, V>::InnerInitialize(size);
      this->_alloc = 0;
   }
   //------------------------------------------------------------
   // <T>新建一个未使用的节点。</T>
   MO_OVERRIDE SEntry* EntryCreate(){
      MO_ASSERT(this->_alloc < S);
      return &this->_entries[this->_alloc++];
   }
   //------------------------------------------------------------
   // <T>释放一个指定的节点。</T>
   MO_OVERRIDE void EntryRelease(SEntry* pEntry){
   }
   //------------------------------------------------------------
   // <T>收集节点列表内存。</T>
   MO_INLINE SEntry** EntryFlatCreate(TInt size){
      MO_ASSERT(size <= S);
      return this->_pEntries;
   }
   //------------------------------------------------------------
   // <T>释放节点列表内存。</T>
   MO_INLINE void EntryFlatRelease(SEntry** pEntries){
   }
   //------------------------------------------------------------
   // <T>确保当前对象可以容纳指定大小的数据。</T>
   MO_OVERRIDE void EnsureSize(TInt size){
      MO_ASSERT(size <= S);
      if(NULL == this->_pMemory){
         this->_entryCount = S;
         this->_ppEntries = _entries;
      }
   }
public:
   //------------------------------------------------------------
   // <T>复制一个哈希表到当前哈希表中。</T>
   MO_INLINE void operator=(const MMap<N, V>& map){
      Assign(&map);
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_MAP_H__
