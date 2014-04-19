#ifndef __MO_CM_SET_H__
#define __MO_CM_SET_H__

#ifndef __MO_CM_TYPE_H__
#include "MoCmType.h"
#endif // __MO_CM_TYPE_H__

#ifndef __MO_CM_ENTRY_H__
#include "MoCmEntry.h"
#endif // __MO_CM_ENTRY_H__

MO_NAMESPACE_BEGIN

#define MO_COMPSET_THRESHOLD 8

//============================================================
// <T>集合节点的定义。</T>
//
// @struct
//============================================================
template <typename N, typename V>
struct SSetEntry{
public:
   // @attribute 关联指针
   SSetEntry* linkPtr;
   // @attribute 前一个指针
   SSetEntry* priorPtr;
   // @attribute 后一个指针
   SSetEntry* nextPtr;
   // @attribute 代码
   N code;
   // @attribute 内容
   V value;
};

//============================================================
// <T>内存排序类。</T>
//
// @tool
// @history 100318 MAOCY 创建
//============================================================
template <typename E, typename T>
class RSet{
public:
   typedef TInt (*HComparer) (const T&, const T&);
private:
   //------------------------------------------------------------
   // <T>调整双向链表的头尾指针。</T>
   static void AdjustHeadTail(E*& h, E*& t, E* p1, E* p2) {
      if(p1 == h){
         h = p2;
      }else if(p2 == h){
         h = p1;
      }if(p1 == t){
         t = p2;
      }else if(p2 == t){
         t = p1;
      }
   }
   //------------------------------------------------------------
   // <T>Set排序中指针交换。</T>
   static void ItemSwap(E*& h, E*& t, E* p1, E* p2) {
      E *tmp;
      if(p1 && p2 && p1 != p2) {
         if(p1->pNext != p2 && p1->pPrior != p2) {
            AdjustHeadTail(h, t, p1, p2);
            MO_SORT_SWAP(tmp, p1->pNext, p2->pNext);
            if(p1->pNext){
               p1->pNext->pPrior = p1;
            }
            if(p2->pNext){
               p2->pNext->pPrior = p2;
            }
            MO_SORT_SWAP(tmp, p1->pPrior, p2->pPrior);
            if(p1->pPrior){
               p1->pPrior->pNext = p1;
            }
            if(p2->pPrior){
               p2->pPrior->pNext = p2;
            }
         } else if(p1->pNext == p2) {
            AdjustHeadTail(h, t, p1, p2);
            p1->pNext = p2->pNext;
            if(p1->pNext){
               p1->pNext->pPrior = p1;
            }
            p2->pPrior = p1->pPrior;
            if(p2->pPrior){
               p2->pPrior->pNext = p2;
            }
            p1->pPrior = p2;
            p2->pNext = p1;
         } else {
            MO_SORT_SWAP(tmp, p1, p2);
            ItemSwap(h, t, p1, p2);
         }
      }
   }
public:
   //------------------------------------------------------------
   // <T>用于元素比较少的双向链表的插入排序。</T>
   static void InsertSort(E *&pHead, E *&pTail, HComparer hComparer) {
      if(pHead != pTail) {
         for(E *p = pHead->pNext; p != pTail->pNext; p = p->pNext) {
            for(E *pp = p; pp != pHead; pp = pp->pPrior) {
               if(hComparer(pp->code, pp->pPrior->code) < 0) {
                   ItemSwap(pHead, pTail, pp, pp->pPrior);
                 } else {
                     break;
                 }
            }
         }
      }
   }
   //------------------------------------------------------------
   // <T>用于元素比较多的双向链表的快速排序。</T>
   static void QuickSort(E *&pHead, E *&pTail, HComparer hComparer){
      if(pHead != pTail) {
         T data = pHead->code;
         E *last = pHead;
         E *tmp;
         for(E *p = pHead->pNext; p != pTail->pNext; p = p->pNext) {
            if(hComparer(p->code, data) < 0) {
               last = last->pNext;
               ItemSwap(pHead, pTail, last, p);
               MO_SORT_SWAP(tmp, last, p);
            }
         }
         tmp = pHead;
         ItemSwap(pHead, pTail, tmp, last);
         last = tmp;
         if(last != pHead){
            last = last->pPrior;
            QuickSort(pHead, last, hComparer);
         }
         if(tmp != pTail){
            tmp = tmp->pNext;
            QuickSort(tmp, pTail, hComparer);
         }
      }
   }
};

//============================================================
// <T>只读迭代器。</T>
//
// @tool
//============================================================
template <typename N, typename V>
class TSetIteratorC{
public:
   typedef SSetEntry<N, V> SEntry;
protected:
   SEntry* _pStart;
   SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TSetIteratorC(){
      this->InnerInitialize((SEntry*)NULL);
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TSetIteratorC(const TSetIteratorC& iterator){
      this->InnerInitialize(iterator._pStart);
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TSetIteratorC(SEntry* pEntry){
      this->InnerInitialize(pEntry);
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
   MO_INLINE const V operator->() const{
      MO_ASSERT(this->_pEntry);
      return this->_pEntry->value;
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
public:
   //------------------------------------------------------------
   // <T>判断代码是否相同。</T>
   MO_INLINE TBool IsCode(N code) const{
      MO_ASSERT(this->_pEntry);
      return (code == this->_pEntry->code);
   }
   //------------------------------------------------------------
   // <T>判断内容是否相同。</T>
   MO_INLINE TBool IsValue(V value) const{
      MO_ASSERT(this->_pEntry);
      return (value == this->_pEntry->value);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据名称。</T>
   MO_INLINE N Code() const{
      MO_ASSERT(this->_pEntry);
      return this->_pEntry->code;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE V Value() const{
      MO_ASSERT(this->_pEntry);
      return this->_pEntry->value;
   }
   //------------------------------------------------------------
   // <T>重置位置。</T>
   MO_INLINE void Reset(){
      _pEntry = NULL;
   }
};

//============================================================
// <T>可写迭代器。</T>
//
// @tool
//============================================================
template <typename N, typename V>
class TSetIterator : public TSetIteratorC<N, V>{
private:
   typedef SSetEntry<N, V> SEntry;
public:
   //------------------------------------------------------------
   // <T>构造可写迭代器。</T>
   TSetIterator(){
   }
   //------------------------------------------------------------
   // <T>构造可写迭代器。</T>
   TSetIterator(const TSetIteratorC<N, V>& iterator) :
         TSetIteratorC<N, V>(iterator){
   }
   //------------------------------------------------------------
   // <T>构造可写迭代器。</T>
   TSetIterator(SEntry* pEntry) :
         TSetIteratorC<N, V>(pEntry){
   }
public:
   //------------------------------------------------------------
   // <T>设置当前位置的数据内容。</T>
   MO_INLINE void SetValue(V value) const{
      MO_ASSERT(this->_pEntry);
      this->_pEntry->value = value;
   }
};

//============================================================
// <T>哈希集合</T>
//
// @manager
// @typename N 代码类型
// @typename V 内容类型
// @history 100427 MAOCY
//============================================================
template <typename N, typename V>
class MSet{
public:
   typedef TInt (*HComparer) (const N&, const N&);
   typedef SSetEntry<N, V>     SEntry;
   typedef TSetIteratorC<N, V> TIteratorC;
   typedef TSetIterator<N, V>  TIterator;
protected:
   TInt _count;
   SEntry* _pFirst;
   SEntry* _pLast;
   SEntry* _pUnused;
   TInt _entryCount;
   SEntry** _ppEntries;
public:
   //------------------------------------------------------------
   // <T>构造哈希集合。</T>
   MSet(){
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
      MO_CLEAR(_pUnused);
      _entryCount = 0;
      MO_CLEAR(_ppEntries);
   }
   //------------------------------------------------------------
   // <T>析构哈希集合。</T>
   MO_ABSTRACT ~MSet(){
   }
protected:
   MO_VIRTUAL SEntry* EntryCreate() = 0;
   MO_VIRTUAL void EntryRelease(SEntry* pEntry) = 0;
   MO_VIRTUAL void EnsureSize(TInt size) = 0;
protected:
{source_entry_list}
public:
   //------------------------------------------------------------
   // <T>判断当前哈希集合和指定哈希集合中所有数据内容是否相等。</T>
   TBool operator==(const MSet<N, V>& set) const{
      return Equals(&set);
   }
   //------------------------------------------------------------
   // <T>判断当前哈希集合和指定哈希集合中所有数据内容是否不相等。</T>
   TBool operator!=(const MSet<N, V>& set) const{
      return !Equals(&set);
   }
   //------------------------------------------------------------
   // <T>获得指定代码的数据内容。</T>
   V operator[](N code){
      SEntry* pEntry = EntryFind(code);
      MO_ASSERT(pEntry);
      return pEntry->value;
   }
   //------------------------------------------------------------
   // 追加一个链表到当前链表中。
   void operator+=(const MSet<N, V>& set){
      Append(&set);
   }
protected:
   //------------------------------------------------------------
   // <T>生成代码的索引。</T>
   MO_INLINE TInt MakeIndex(N code) const{
      TInt index = (TInt)code;
      index %= this->_entryCount;
      if(index < 0){
         index = -index;;
      }
      return index;
   }
   //------------------------------------------------------------
   // <T>查找指定名称的索引位置。</T>
   MO_INLINE SEntry* EntryFind(N code) const{
      // 数据存在时
      if(this->_count > 0){
         // 查找名称的索引位置
         TInt index = MakeIndex(code);
         SEntry* pEntry = _ppEntries[index];
         while(NULL != pEntry){
            if(pEntry->code == code){
               return pEntry;
            }
            pEntry = pEntry->linkPtr;
         }
      }
      // 未找到时返回结果
      return NULL;
   }
   //------------------------------------------------------------
   // <T>查找指定名称的索引位置。</T>
   MO_INLINE SEntry* EntryFindValue(V value) const{
      // 数据存在时
      if(this->_count > 0){
         TInt n = 0;
         while(++n < _entryCount){
            SEntry* pEntry = _ppEntries[n];
            while(NULL != pEntry){
               if(pEntry->value == value){
                  return pEntry;
               }
               pEntry = pEntry->linkPtr;
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
      for(TInt n=0; n<_entryCount; n++){
         _ppEntries[n] = NULL;
      }
      // 清除链表
      // 清空内容
      _count = 0;
      _pFirst = NULL;
      _pLast = NULL;
   }
public:
   //------------------------------------------------------------
   // <T>当前哈希集合对象是否为空。</T>
   TBool IsEmpty() const{
      return (0 == this->_count);
   }
   //------------------------------------------------------------
   // <T>获得数据个数。</T>
   TInt Count() const{
      return this->_count;
   }
   //------------------------------------------------------------
   // <T>判断指定名称是否存在。</T>
   TBool Contains(N code) const{
      return (NULL != EntryFind(code));
   }
   //------------------------------------------------------------
   // <T>判断指定名称是否存在。</T>
   TBool ContainsValue(V value) const{
      return (NULL != EntryFindValue(value));
   }
   //------------------------------------------------------------
   // <T>判断当前哈希集合和指定哈希集合内容是否相等。</T>
   TBool Equals(const MSet<N, V>* pSet) const{
      MO_ASSERT(pSet);
      // 比较数量
      if(this->_count != pSet->_count){
         return EFalse;
      }
      // 比较所有项目
      TIteratorC iterator = pSet->IteratorC();
      while(iterator.Next()){
         N code = iterator.Code();
         SEntry* pEntry = this->EntryFind(code);
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
      return (NULL != this->_pFirst) ? this->_pFirst->value : NULL;
   }
   //------------------------------------------------------------
   // <T>获得尾位置的数据。</T>
   V Last() const{
      return (NULL != this->_pLast) ? this->_pLast->value : NULL;
   }
   //------------------------------------------------------------
   // <T>获得只读迭代器。</T>
   TIteratorC IteratorC(){
      return TIteratorC(this->_pFirst);
   }
   //------------------------------------------------------------
   // <T>获得结尾只读迭代器。</T>
   TIteratorC LastIteratorC(){
      return TIteratorC(this->_pLast);
   }
public:
   //------------------------------------------------------------
   // <T>获得指定代码的数据。</T>
   V Get(N code) const{
      SEntry* pEntry = EntryFind(code);
      MO_ASSERT(pEntry);
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得指定代码的数据。</T>
   V Find(N code) const{
      SEntry* pEntry = EntryFind(code);
      return (NULL != pEntry) ? pEntry->value : (V)NULL;
   }
   //------------------------------------------------------------
   // <T>获得指定数据的代码。</T>
   N Search(V value) const{
      SEntry* pEntry = EntryFindValue(value);
      return (NULL != pEntry) ? pEntry->code : (N)NULL;
   }
   //------------------------------------------------------------
   // <T>复制对象到外部数组。</T>
   TInt CopyTo(V* pBuffer, TInt capacity) const{
      TInt count = MO_LIB_MIN(this->_count, capacity);
      if(count > 0){
         TInt n = 0;
         SEntry* pEntry = this->_pFirst;
         while(NULL != pEntry){
            pBuffer[n++] = pEntry->value;
            pEntry = pEntry->nextPtr;
         }
      }
      return count;
   }
protected:
   //------------------------------------------------------------
   // <T>扩充当前对象的哈希值数组。</T>
   MO_INLINE void EntriesResize(SEntry** ppEntries, TInt size){
      // 循环取出旧的节点列表内容，移到新的节点列表上
      TInt n = -1;
      while(++n < this->_entryCount){
         SEntry* pEntry = this->_ppEntries[n];
         while(NULL != pEntry){
            // 保存当前节点的下一个节点指针
            SEntry* pLink = pEntry->linkPtr;
            // 将当前节点存储到新建的节点列表上去
            TInt index = (TInt)pEntry->code;
            index %= size;
            if(index < 0){
               index = -index;
            }
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
      return TIterator(this->_pFirst);
   }
   //------------------------------------------------------------
   // <T>获得结尾可写迭代器。</T>
   TIterator LastIterator(){
      return TIterator(this->_pLast);
   }
public:
   //------------------------------------------------------------
   // <T>复制集合内的全部数据。</T>
   void Assign(const MSet<N, V>* pSet){
      MO_ASSERT(pSet);
      Clear();
      Append(pSet);
   }
   //------------------------------------------------------------
   // <T>追加集合内的全部数据。</T>
   void Append(const MSet<N, V>* pSet){
      MO_ASSERT(pSet);
      TIteratorC iterator = pSet->IteratorC();
      while(iterator.Next()){
         Set(iterator.Code(), iterator.Value());
      }
   }
   //------------------------------------------------------------
   // <T>根据名称设置数据。</T>
   void Set(N code, V value){
      // 查找数据出现的位置
      TInt index = this->MakeIndex(code);
      SEntry* pEntry = this->_ppEntries[index];
      while(NULL != pEntry) {
         if(pEntry->code == code){
            pEntry->value = value;
            return;
         }
         pEntry = pEntry->linkPtr;
      }
      // 如果名称不存在，为新建节点检查内存
      pEntry = EntryAlloc();
      // 大小可能改变，重新计算索引位置
      index = this->MakeIndex(code);
      pEntry->linkPtr = this->_ppEntries[index];
      pEntry->code = code;
      pEntry->value = value;
      this->_ppEntries[index] = pEntry;
      // 追加到链表尾部
      this->EntryPush(pEntry);
   }
   //------------------------------------------------------------
   // <T>移除指定名称的数据。</T>
   V Remove(N code){
      TInt index = this->MakeIndex(code);
      // 查找数据出现的位置
      V value;
      memset(&value, 0, sizeof(V));
      SEntry* pPrior = this->_ppEntries[index];
      SEntry* pEntry = pPrior;
      while(NULL != pEntry){
         if(pEntry->code == code){
            value = pEntry->value;
            if(pEntry == pPrior){
               // 当前对象是第一个对象时
               this->_ppEntries[index] = pEntry->linkPtr;
            }else{
               // 当前对象不是第一个对象时
               pPrior->linkPtr = pEntry->linkPtr;
            }
            // 删除当前节点
            this->EntryRemove(pEntry);
            // 释放当前节点
            this->EntryFree(pEntry);
            break;
         }
         pPrior = pEntry;
         pEntry = pEntry->linkPtr;
      }
      return value;
   }
   //------------------------------------------------------------
   // <T>使用排序器对集合对象进行排序</T>
   void Sort(HComparer hComparer){
      MO_ASSERT(hComparer);
      if(this->_pFirst && this->_pLast && this->_count > 1) {
         if(MO_COMPLIST_THRESHOLD > this->_count){
            RSet<SEntry, N>::InsertSort(this->_pFirst, this->_pLast, hComparer);
         } else{
            RSet<SEntry, N>::QuickSort(this->_pFirst, this->_pLast, hComparer);
         }
      }
   }
   //------------------------------------------------------------
   // <T>清空当前哈希集合全部数据。</T>
   void Clear(){
      if(this->_count > 0){
         this->_pLast->nextPtr = this->_pUnused;
         this->_pUnused = this->_pFirst;
      }
      this->EntryClear();
   }
};

//============================================================
// <T>变长栈集合。</T>
//
// @tool
//============================================================
template <typename N, typename V>
class TSet : public MSet<N, V>{
public:
   typedef SSetEntry<N, V> SEntry;
public:
   //------------------------------------------------------------
   // <T>构造哈希集合对象。</T>
   TSet(){
      this->InnerInitialize(MO_OBJECT_CAPACITY);
   }
   //------------------------------------------------------------
   // <T>构造哈希集合对象。</T>
   TSet(const MSet<N, V>& set){
      this->InnerInitialize(set.Count());
      this->Append(&set);
   }
   //------------------------------------------------------------
   // <T>构造哈希集合对象。</T>
   TSet(TInt capacity){
      this->InnerInitialize(capacity);
   }
   //------------------------------------------------------------
   // <T>析构哈希集合对象。</T>
   MO_ABSTRACT ~TSet(){
      // 释放节点集合
      if(this->_count > 0){
         this->_pLast->nextPtr = this->_pUnused;
         this->_pUnused = this->_pFirst;
      }
      // 删除所有节点
      SEntry* pFind = this->_pUnused;
      while(NULL != pFind){
         SEntry* pNext = pFind->nextPtr;
         MO_FREE(pFind);
         pFind = pNext;
      }
      // 删除节点数组
      MO_FREE(this->_ppEntries);
   }
protected:
   //------------------------------------------------------------
   // <T>内部初始化。</T>
   MO_INLINE void InnerInitialize(TInt size){
      this->_entryCount = MO_LIB_MAX(size, MO_OBJECT_CAPACITY);
      this->_ppEntries = MO_TYPES_ALLOC(SEntry*, this->_entryCount);
      RTypes<SEntry*>::Clear(this->_ppEntries, this->_entryCount);
   }
   //------------------------------------------------------------
   // <T>新建一个节点。</T>
   MO_OVERRIDE SEntry* EntryCreate(){
      EnsureSize(this->_count + 1);
      SEntry* pEntry = MO_TYPE_ALLOC(SEntry);
      MO_ASSERT(pEntry);
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>释放一个节点。</T>
   MO_OVERRIDE void EntryRelease(SEntry* pEntry){
      MO_ASSERT(pEntry);
      MO_FREE(pEntry);
   }
public:
   //------------------------------------------------------------
   // <T>复制内容到对象内部。</T>
   MO_INLINE void operator=(const MSet<N, V>& set){
      this->Assign(&set);
   }
public:
   //------------------------------------------------------------
   // <T>确保当前对象可以容纳指定大小的数据。</T>
   // <P>当哈希表是旧表的8倍时开始扩充，扩大2倍。</P>
   MO_OVERRIDE void EnsureSize(TInt size){
      if (size > (this->_entryCount << 3)) {
         // 扩充内存时处理
         size = this->_entryCount + (MO_LIB_MAX(this->_entryCount, size) >> 1);
         // 当总数大于节点列表长度8倍时，重新扩充节点列表
         SEntry** ppEntries = MO_TYPES_ALLOC(SEntry*, size);
         RTypes<SEntry*>::Clear(ppEntries, size);
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
// <T>变长堆集合。</T>
//
// @tool
//============================================================
template <typename N, typename V>
class FSet :
      public FObject,
      public MSet<N, V>{
public:
   typedef SSetEntry<N, V> SEntry;
public:
   //------------------------------------------------------------
   // <T>构造集合对象。</T>
   FSet(){
      this->InnerInitialize(MO_OBJECT_CAPACITY);
   }
   //------------------------------------------------------------
   // <T>构造集合对象。</T>
   FSet(TInt capacity){
      this->InnerInitialize(MO_OBJECT_CAPACITY);
   }
   //------------------------------------------------------------
   // <T>构造集合对象。</T>
   FSet(const MSet<N, V>& set){
      this->InnerInitialize(set.Count());
      this->Append(&set);
   }
   //------------------------------------------------------------
   // <T>析构集合对象。</T>
   MO_ABSTRACT ~FSet(){
      // 释放节点集合
      if(this->_count > 0){
         // 将所有节点设置为未使用
         this->_pLast->nextPtr = this->_pUnused;
         this->_pUnused = this->_pFirst;
      }
      // 删除所有节点
      SEntry* pFind = this->_pUnused;
      while(NULL != pFind){
         SEntry* pNext = pFind->nextPtr;
         MO_FREE(pFind);
         pFind = pNext;
      }
      // 删除节点数组
      MO_FREE(this->_ppEntries);
   }
protected:
   //------------------------------------------------------------
   // <T>内部初始化。</T>
   MO_INLINE void InnerInitialize(TInt size){
      this->_entryCount = MO_LIB_MAX(size, MO_OBJECT_CAPACITY);
      this->_ppEntries = MO_TYPES_ALLOC(SEntry*, this->_entryCount);
      RTypes<SEntry*>::Clear(this->_ppEntries, this->_entryCount);
   }
   //------------------------------------------------------------
   // <T>新建一个节点。</T>
   MO_OVERRIDE SEntry* EntryCreate(){
      EnsureSize(this->_count + 1);
      SEntry* pEntry = MO_TYPE_ALLOC(SEntry);
      MO_ASSERT(pEntry);
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>释放一个节点。</T>
   MO_OVERRIDE void EntryRelease(SEntry* pEntry){
      MO_ASSERT(pEntry);
      MO_FREE(pEntry);
   }
public:
   //------------------------------------------------------------
   // <T>复制内容到对象内部。</T>
   MO_INLINE void operator=(const MSet<N, V>& set){
      this->Assign(&set);
   }
public:
   //------------------------------------------------------------
   // <T>确保当前对象可以容纳指定大小的数据。</T>
   // <P>当哈希表是旧表的8倍时开始扩充，扩大2倍。</P>
   MO_OVERRIDE void EnsureSize(TInt size){
      if (size > (this->_entryCount << 3)) {
         // 扩充内存时处理
         size = this->_entryCount + (MO_LIB_MAX(this->_entryCount, size) >> 1);
         // 当总数大于节点列表长度8倍时，重新扩充节点列表
         SEntry** ppEntries = MO_TYPES_ALLOC(SEntry*, size);
         RTypes<SEntry*>::Clear(ppEntries, size);
         this->EntriesResize(ppEntries, size);
         // 释放旧节点内存
         MO_FREE(this->_ppEntries);
         // 保存新的节点列表
         this->_ppEntries = ppEntries;
         this->_entryCount = size;
      }
   }
};

//============================================================
// <T>变长堆集合。</T>
//
// @tool
//============================================================
template <typename N, typename V>
class FFreeSet :
      public FFree,
      public MSet<N, V>{
public:
   typedef SSetEntry<N, V> SEntry;
public:
   //------------------------------------------------------------
   // <T>构造集合对象。</T>
   FFreeSet(){
      this->InnerInitialize(MO_OBJECT_CAPACITY);
   }
   //------------------------------------------------------------
   // <T>构造集合对象。</T>
   FFreeSet(TInt capacity){
      this->InnerInitialize(MO_OBJECT_CAPACITY);
   }
   //------------------------------------------------------------
   // <T>构造集合对象。</T>
   FFreeSet(const MSet<N, V>& set){
      this->InnerInitialize(set.Count());
      this->Append(&set);
   }
   //------------------------------------------------------------
   // <T>析构集合对象。</T>
   MO_ABSTRACT ~FFreeSet(){
      // 释放节点
      if(this->_count > 0){
         // 将所有节点设置为未使用
         this->_pLast->nextPtr = this->_pUnused;
         this->_pUnused = this->_pFirst;
         // 删除所有节点
         SEntry* pFind = this->_pUnused;
         while(NULL != pFind){
            SEntry* pNext = pFind->nextPtr;
            MO_FREE(pFind);
            pFind = pNext;
         }
      }
      // 释放节点数组
      MO_FREE(this->_ppEntries);
   }
protected:
   //------------------------------------------------------------
   // <T>内部初始化。</T>
   MO_INLINE void InnerInitialize(TInt size){
      this->_entryCount = MO_LIB_MAX(size, MO_OBJECT_CAPACITY);
      this->_ppEntries = MO_TYPES_ALLOC(SEntry*, this->_entryCount);
      RTypes<SEntry*>::Clear(this->_ppEntries, this->_entryCount);
   }
   //------------------------------------------------------------
   // <T>新建一个节点。</T>
   MO_OVERRIDE SEntry* EntryCreate(){
      this->EnsureSize(this->_count + 1);
      SEntry* pEntry = MO_MEM_CREATE(SEntry);
      MO_ASSERT(pEntry);
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>释放一个节点。</T>
   MO_OVERRIDE void EntryRelease(SEntry* pEntry){
      MO_ASSERT(pEntry);
      MO_MEM_DELETE(pEntry);
   }
public:
   //------------------------------------------------------------
   // <T>复制内容到对象内部。</T>
   MO_INLINE void operator=(const MSet<N, V>& set){
      this->Assign(&set);
   }
public:
   //------------------------------------------------------------
   // <T>确保当前对象可以容纳指定大小的数据。</T>
   // <P>当哈希表是旧表的8倍时开始扩充，扩大2倍。</P>
   MO_OVERRIDE void EnsureSize(TInt size){
      if (size > (this->_entryCount << 3)) {
         // 扩充内存时处理
         size = this->_entryCount + (MO_LIB_MAX(this->_entryCount, size) >> 1);
         // 当总数大于节点列表长度8倍时，重新扩充节点列表
         SEntry** ppEntries = MO_TYPES_ALLOC(SEntry*, size);
         RTypes<SEntry*>::Clear(ppEntries, size);
         this->EntriesResize(ppEntries, size);
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
class TFixSet : public MSet<N, V>{
public:
   typedef SSetEntry<N, V> SEntry;
protected:
   SEntry* _memoryEntries[S];
   TInt _alloc;
   SEntry _allocEntries[S];
public:
   //------------------------------------------------------------
   // <T>构造固定长度的哈希表。</T>
   TFixSet(){
      this->InnerInitialize();
   }
   //------------------------------------------------------------
   // <T>构造固定长度的哈希表。</T>
   TFixSet(const MSet<N, V>& set){
      this->InnerInitialize();
      this->Append(&set);
   }
   //------------------------------------------------------------
   // <T>析构化固定长度的哈希表。</T>
   MO_ABSTRACT ~TFixSet(){
   }
protected:
   //------------------------------------------------------------
   // <T>内部初始化。</T>
   MO_INLINE void InnerInitialize(){
      this->_entryCount = S;
      this->_ppEntries = _memoryEntries;
      this->_alloc = 0;
   }
   //------------------------------------------------------------
   // <T>新建一个节点。</T>
   MO_OVERRIDE SEntry* EntryCreate(){
      MO_ASSERT(_alloc < S);
      return &_allocEntries[_alloc++];
   }
   //------------------------------------------------------------
   // <T>释放一个节点。</T>
   MO_OVERRIDE void EntryRelease(SEntry* pEntry){
   }
   //------------------------------------------------------------
   // <T>确保当前对象可以容纳指定大小的数据。</T>
   MO_OVERRIDE void EnsureSize(TInt size){
      MO_ASSERT(size <= S);
   }
public:
   //------------------------------------------------------------
   // <T>复制一个集合到当前集合中。</T>
   MO_INLINE void operator=(const MSet<N, V>& set){
      Assign(&set);
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_SET_H__
