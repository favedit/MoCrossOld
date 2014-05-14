#ifndef __MO_CM_PTR_DICTIONARY_H__
#define __MO_CM_PTR_DICTIONARY_H__

#ifndef __MO_CM_TYPE_H__
#include "MoCmType.h"
#endif // __MO_CM_TYPE_H__

#ifndef __MO_CM_STRING_H__
#include "MoCmString.h"
#endif // __MO_CM_STRING_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>哈希字典节点</T>
//
// @struct
//============================================================
template <typename T>
struct SPtrDictionaryEntry{
public:
   SPtrDictionaryEntry* linkPtr;
   SPtrDictionaryEntry* priorPtr;
   SPtrDictionaryEntry* nextPtr;
   THashCode hash;
   TString name;
   GPtr<T> value;
public:
   //------------------------------------------------------------
   // <T>构造哈希字典节点</T>
   SPtrDictionaryEntry(){
      MO_CLEAR(linkPtr);
      MO_CLEAR(priorPtr);
      MO_CLEAR(nextPtr);
      hash = 0;
   }
public:
   //------------------------------------------------------------
   // <T>判断名称是否相等。</T>
   MO_INLINE TBool IsName(TCharC* pName){
      return name.Equals(pName);
   }
   //------------------------------------------------------------
   // <T>判断不区分大小写名称是否相等。</T>
   MO_INLINE TBool IsNameIgnoreCase(TCharC* pName){
      return name.EqualsIgnoreCase(pName);
   }
   //------------------------------------------------------------
   // <T>获得字符串名称。</T>
   MO_INLINE TCharC* Name(){
      return name;
   }
   //------------------------------------------------------------
   // <T>设置字符串名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      name = pName;
   }
   //------------------------------------------------------------
   // <T>重置内容。</T>
   MO_INLINE void Reset(){
      MO_CLEAR(linkPtr);
      MO_CLEAR(priorPtr);
      MO_CLEAR(nextPtr);
      hash = 0;
      name = NULL;
      value = NULL;
   }
};

//============================================================
// <T>只读迭代器。</T>
//
// @tool
//============================================================
template <typename T>
class GPtrDictionaryIteratorC{
public:
   typedef SPtrDictionaryEntry<T> SEntry;
protected:
   SEntry* _pStart;
   SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   GPtrDictionaryIteratorC(){
      InnerInitialize((SEntry*)NULL);
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   GPtrDictionaryIteratorC(const GPtrDictionaryIteratorC<T>& iterator){
      InnerInitialize(iterator._pStart);
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   GPtrDictionaryIteratorC(SEntry* pEntry){
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
   MO_INLINE const T* operator *() const{
      MO_ASSERT(_pEntry);
      return (T*)_pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T* operator->() const{
      MO_ASSERT(_pEntry);
      return (T*)_pEntry->value;
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
   MO_INLINE TBool IsName(TCharC* pName){
      MO_ASSERT(_pEntry);
      return _pEntry->IsName(pName);
   }
   //------------------------------------------------------------
   MO_INLINE TBool IsValue(T* pValue){
      MO_ASSERT(_pEntry);
      return _pEntry->value == pValue;
   }
   //------------------------------------------------------------
   // 获得当前位置的数据名称
   MO_INLINE TCharC* Name() const{
      MO_ASSERT(_pEntry);
      return _pEntry->Name();
   }
   //------------------------------------------------------------
   // 获得当前位置的数据内容
   MO_INLINE T* Value() const{
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
};

//============================================================
// <T>指针字典迭代器。</T>
//
// @tool
//============================================================
template <typename T>
class GPtrDictionaryIterator : public GPtrDictionaryIteratorC<T>{
public:
   typedef SPtrDictionaryEntry<T> SEntry;
public:
   //------------------------------------------------------------
   // <T>构造指针字典迭代器。</T>
   GPtrDictionaryIterator(){
   }
   //------------------------------------------------------------
   // <T>构造指针字典迭代器。</T>
   GPtrDictionaryIterator(const GPtrDictionaryIteratorC<T>& iterator) :
         GPtrDictionaryIteratorC<T>(iterator){
   }
   //------------------------------------------------------------
   // <T>构造指针字典迭代器。</T>
   GPtrDictionaryIterator(SEntry** ppEntries, TInt entryCount) :
         GPtrDictionaryIteratorC<T>(ppEntries, entryCount){
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T* operator *(){
      MO_ASSERT(this->_pEntry);
      return (T*)this->_pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T* operator->(){
      MO_ASSERT(this->_pEntry);
      return (T*)this->_pEntry->value;
   }
public:
   //------------------------------------------------------------
   // <T>设置当前位置的数据内容。</T>
   MO_INLINE void SetValue(T* pValue) const{
      MO_ASSERT(this->_pEntry);
      this->_pEntry->value = pValue;
   }
};

//============================================================
// <T>哈希字典</T>
//============================================================
template <typename T>
class MPtrDictionary{
public:
   typedef SPtrDictionaryEntry<T> SEntry;
   typedef GPtrDictionaryIteratorC<T> TIteratorC;
   typedef GPtrDictionaryIterator<T> TIterator;
protected:
   TInt _count;
   SEntry* _pFirst;
   SEntry* _pLast;
   SEntry* _pUnused;
   TInt _entryCount;
   SEntry** _ppEntries;
protected:
   //------------------------------------------------------------
   // <T>构造哈希字典。</T>
   MPtrDictionary(){
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
      MO_CLEAR(_pUnused);
      _entryCount = 0;
      MO_CLEAR(_ppEntries);
   }
   //------------------------------------------------------------
   // <T>析构哈希字典。</T>
   MO_ABSTRACT ~MPtrDictionary(){
   }
protected:
   MO_VIRTUAL SEntry* EntryCreate() = 0;
   MO_VIRTUAL void EntryRelease(SEntry* pEntry) = 0;
   MO_VIRTUAL SEntry** EntryFlatCreate(TInt size) = 0;
   MO_VIRTUAL void EntryFlatRelease(SEntry** ppEntries) = 0;
protected:
   //------------------------------------------------------------
   // <T>收集一个未使用的节点。</T>
   MO_INLINE SEntry* EntryAlloc(){
      SEntry* pEntry = NULL;
      // 查看未使用节点中是否有自由节点
      if(NULL != _pUnused){
         // 收集未使用的节点
         pEntry = _pUnused;
         _pUnused = _pUnused->nextPtr;
      }else{
         // 收集新节点
         pEntry = EntryCreate();
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>释放一个被使用的节点。</T>
   MO_INLINE void EntryFree(SEntry* pEntry){
      MO_ASSERT(pEntry);
      pEntry->Reset();
      pEntry->nextPtr = _pUnused;
      _pUnused = pEntry;
   }
   //------------------------------------------------------------
   // <T>将链表节点压入首位置。</T>
   MO_INLINE SEntry* EntryGet(TInt index) const{
      MO_ASSERT_RANGE(index, 0, _count);
      // 获得第一个
      if(0 == index){
         return _pFirst;
      }
      // 获得指定位置数据
      SEntry* pEntry = _pFirst;
      while(--index >= 0){
         pEntry = pEntry->nextPtr;
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>将链表节点压入首位置。</T>
   MO_INLINE void EntryUnshift(SEntry* pEntry){
      MO_ASSERT(pEntry);
      if(NULL == _pFirst){
         _pLast = pEntry;
      }else{
         _pFirst->priorPtr = pEntry;
      }
      pEntry->priorPtr = NULL;
      pEntry->nextPtr = _pFirst;
      _pFirst = pEntry;
      _count++;
   }
   //------------------------------------------------------------
   // <T>弹出链表的首节点。</T>
   MO_INLINE SEntry* EntryShift(){
      SEntry* pEntry = _pFirst;
      if(NULL != pEntry){
         // 读指针指向下一个位置
         _pFirst = _pFirst->nextPtr;
         if(NULL == _pFirst){
            _pLast = NULL;
         }else{
            _pFirst->priorPtr = NULL;
         }
         // 设置内容
         _count--;
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>将链表节点压入尾位置。</T>
   MO_INLINE void EntryPush(SEntry* pEntry){
      MO_ASSERT(pEntry);
      if(NULL == _pLast){
         _pFirst = pEntry;
      }else{
         _pLast->nextPtr = pEntry;
      }
      pEntry->priorPtr = _pLast;
      pEntry->nextPtr = NULL;
      _pLast = pEntry;
      _count++;
   }
   //------------------------------------------------------------
   // <T>弹出链表的尾节点。</T>
   MO_INLINE SEntry* EntryPop(){
      SEntry* pEntry = _pLast;
      if(NULL != pEntry){
         _pLast = _pLast->priorPtr;
         if(NULL == _pLast){
            _pFirst = NULL;
         }else{
            _pLast->nextPtr = NULL;
         }
         // 设置内容
         _count--;
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>在指定链表节点后插入一个新链表节点。</T>
   MO_INLINE void EntryInsert(SEntry* pPrior, SEntry* pEntry){
      MO_ASSERT(pPrior);
      MO_ASSERT(pEntry);
      SEntry* pNext = pPrior->nextPtr;
      pPrior->nextPtr = pEntry;
      pEntry->priorPtr = pPrior;
      if(NULL == pNext){
         pEntry->nextPtr = NULL;
         _pLast = pEntry;
      }else{
         pNext->priorPtr = pEntry;
         pEntry->nextPtr = pNext;
      }
   }
   //------------------------------------------------------------
   // <T>从链表上删除链表节点。</T>
   MO_INLINE void EntryRemove(SEntry* pEntry){
      MO_ASSERT(pEntry);
      SEntry* pPrior = pEntry->priorPtr;
      SEntry* pNext = pEntry->nextPtr;
      // 处理前节点
      if(NULL == pPrior){
         _pFirst = pNext;
      }else{
         pPrior->nextPtr = pNext;
      }
      // 处理后节点
      if(NULL == pNext){
         _pLast = pPrior;
      }else{
         pNext->priorPtr = pPrior;
      }
      // 设置内容
      _count--;
   }
public:
   //------------------------------------------------------------
   // <T>判断当前哈希集合和指定哈希集合中所有数据内容是否相等。</T>
   MO_INLINE TBool operator==(const MPtrDictionary<T>& dictionary) const{
      return Equals(&dictionary);
   }
   //------------------------------------------------------------
   // <T>判断当前哈希集合和指定哈希集合中所有数据内容是否不相等。</T>
   MO_INLINE TBool operator!=(const MPtrDictionary<T>& dictionary) const{
      return !Equals(&dictionary);
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   MO_INLINE const T operator[](TCharC* pName) const{
      MO_ASSERT(pName);
      SEntry* pEntry = EntryFind(pName);
      MO_ASSERT(pEntry);
      return pEntry->value;
   }
protected:
   //------------------------------------------------------------
   // <T>查找指定名称的索引位置。</T>
   MO_INLINE SEntry* EntryFind(TCharC* pName) const{
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
   MO_INLINE SEntry* EntryFindValue(T* pValue) const{
      // 数据存在时
      if(_count > 0){
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->value == pValue){
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
   TBool Contains(TCharC* pName) const{
      MO_ASSERT(pName);
      return (NULL != EntryFind(pName));
   }
   //------------------------------------------------------------
   // <T>判断指定名称是否存在。</T>
   TBool ContainsValue(T value) const{
      return (NULL != EntryFindValue(value));
   }
   //------------------------------------------------------------
   // <T>判断当前哈希集合和指定哈希集合内容是否相等。</T>
   TBool Equals(const MPtrDictionary<T>* pPtrDictionary) const{
      MO_ASSERT(pPtrDictionary);
      // 比较数量
      if(_count != pPtrDictionary->_count){
         return EFalse;
      }
      // 比较所有项目
      TIteratorC iterator = pPtrDictionary->IteratorC();
      while(iterator.Next()){
         SEntry* pEntry = EntryFind(iterator.Name());
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
   // <T>获得只读迭代器。</T>
   const GPtrDictionaryIteratorC<T> IteratorC() const{
      return GPtrDictionaryIteratorC<T>(_pFirst);
   }
   //------------------------------------------------------------
   // <T>获得只读迭代器。</T>
   const GPtrDictionaryIteratorC<T> LastIteratorC() const{
      return GPtrDictionaryIteratorC<T>(_pLast);
   }
public:
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   T* Get(TCharC* pName) const{
      MO_ASSERT(pName);
      SEntry* pEntry = EntryFind(pName);
      MO_ASSERT(pEntry);
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   T* Find(TCharC* pName) const{
      if(NULL != pName){
         SEntry* pEntry = EntryFind(pName);
         if(NULL != pEntry){
            return pEntry->value;
         }
      }
      return NULL;
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   TCharC* Search(T* pValue) const{
      SEntry* pEntry = EntryFindValue(pValue);
      return (NULL != pEntry) ? pEntry->Name() : NULL;
   }
public:
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   T& operator[](TCharC* pName){
      MO_ASSERT(pName);
      SEntry* pEntry = EntryFind(pName);
      MO_ASSERT(pEntry);
      return pEntry->value;
   }
protected:
   //------------------------------------------------------------
   // <T>扩充当前对象的哈希值数组。</T>
   MO_INLINE void EntriesResize(SEntry** ppEntries, TInt size){
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
   MO_INLINE TIteratorC Iterator(){
      return TIterator(_pFirst);
   }
   //------------------------------------------------------------
   // <T>获得结尾可写迭代器。</T>
   MO_INLINE TIterator LastIterator(){
      return TIterator(_pLast);
   }
public:
   //------------------------------------------------------------
   // <T>接收源哈希表内的全部数据。</T>
   void Assign(const MPtrDictionary<T>& dictionary){
      Clear();
      Append(dictionary);
   }
   //------------------------------------------------------------
   // <T>追加源哈希表内的全部数据。</T>
   void Append(const MPtrDictionary<T>& dictionary){
      GPtrDictionaryIteratorC<T> iterator = dictionary.IteratorC();
      while(iterator.Next()){
         Set(iterator.Name(), iterator.Value());
      }
   }
   //------------------------------------------------------------
   // <T>根据名称设置数据。</T>
   void Set(TCharC* pName, T* pValue){
      THashCode hash = RString::MakeHashCode(pName);
      TInt index = hash % _entryCount;
      // 查找数据出现的位置
      SEntry* pEntry = _ppEntries[index];
      while(NULL != pEntry) {
         if(pEntry->hash == hash){
            if(pEntry->IsName(pName)){
               pEntry->value = pValue;
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
      pEntry->SetName(pName);
      pEntry->value = pValue;
      _ppEntries[index] = pEntry;
      // 追加到链表尾部
      EntryPush(pEntry);
   }
   //------------------------------------------------------------
   // <T>移除指定名称的数据。</T>
   T* Remove(TCharC* pName){
      THashCode hash = RString::MakeHashCode(pName);
      TInt index = hash % _entryCount;
      // 查找数据出现的位置
      T* pValue = NULL;
      SEntry* pPrior = NULL;
      SEntry* pEntry = _ppEntries[index];
      while(NULL != pEntry){
         if(pEntry->hash == hash){
            if(pEntry->IsName(pName)){
               pValue = pEntry->value;
               if(NULL == pPrior){
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
      return pValue;
   }
   //------------------------------------------------------------
   // <T>清空当前哈希表全部数据，哈希表可以再次被利用。</T>
   void Clear(){
      // 清空节点内容
      SEntry* pEntry = _pFirst;
      while(pEntry != NULL){
         pEntry->Reset();
         pEntry = pEntry->nextPtr;
      }
      // 回收节点集合
      if(_count > 0){
         // 将所有节点设置为未使用
         _pLast->nextPtr = _pUnused;
         _pUnused = _pFirst;
      }
      // 清除数据
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
      for(TInt n = 0; n < _entryCount; n++){
         _ppEntries[n] = NULL;
      }
   }
   //------------------------------------------------------------
   // <T>释放哈希表对象。</T>
   void Release(){
      // 清空内容
      Clear();
      // 删除所有节点
      SEntry* pFind = _pUnused;
      while(pFind != NULL){
         SEntry* pNext = pFind->nextPtr;
         EntryRelease(pFind);
         pFind = pNext;
      }
      // 释放节点数组
      EntryFlatRelease(_ppEntries);
   }
};

//============================================================
// <T>字典对象。</T>
//============================================================
template <typename T>
class GPtrDictionary : public MPtrDictionary<T>{
public:
   typedef SPtrDictionaryEntry<T> SEntry;
public:
   //------------------------------------------------------------
   // <T>创建字典对象。</T>
   GPtrDictionary(){
      this->EnsureSize(MO_OBJECT_CAPACITY);
   }
   //------------------------------------------------------------
   // <T>创建字典对象。</T>
   GPtrDictionary(TInt capacity){
      this->EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>创建字典对象。</T>
   GPtrDictionary(const MPtrDictionary<T>& dictionary){
      this->EnsureSize(dictionary.Count());
      this->Append(dictionary);
   }
   //------------------------------------------------------------
   // <T>创建字典对象。</T>
   GPtrDictionary(const GPtrDictionary<T>& dictionary){
      this->EnsureSize(dictionary.Count());
      this->Append(dictionary);
   }
   //------------------------------------------------------------
   // <T>析构字典对象。</T>
   MO_ABSTRACT ~GPtrDictionary(){
      this->Release();
   }
public:
   //------------------------------------------------------------
   // <T>接收另一个字典对象的全部数据。</T>
   MO_INLINE void operator=(const MPtrDictionary<T>& dictionary){
      Assign(dictionary);
   }
   //------------------------------------------------------------
   // <T>接收另一个字典对象的全部数据。</T>
   MO_INLINE void operator=(const GPtrDictionary<T>& dictionary){
      Assign(dictionary);
   }
protected:
   //------------------------------------------------------------
   // <T>新建一个未使用的节点。</T>
   MO_OVERRIDE SEntry* EntryCreate(){
      this->EnsureSize(this->_count + 1);
      return MO_TYPE_CREATE(SEntry);
   }
   //------------------------------------------------------------
   // <T>释放一个指定的节点。</T>
   MO_OVERRIDE void EntryRelease(SEntry* pEntry){
      MO_ASSERT(pEntry);
      MO_TYPE_DELETE(pEntry);
   }
   //------------------------------------------------------------
   // <T>收集节点列表内存。</T>
   MO_OVERRIDE SEntry** EntryFlatCreate(TInt size){
      SEntry** ppEntities = MO_TYPES_ALLOC(SEntry*, size);
      RTypes<SEntry*>::Clear(ppEntities, size);
      return ppEntities;
   }
   //------------------------------------------------------------
   // <T>释放节点列表内存。</T>
   MO_OVERRIDE void EntryFlatRelease(SEntry** ppEntries){
      MO_ASSERT(ppEntries);
      MO_FREE(ppEntries);
   }
public:
   //------------------------------------------------------------
   // <T>确保当前对象可以容纳指定大小的数据。</T>
   // <P>当哈希表是旧表的8倍时开始扩充，扩大2倍。</P>
   MO_OVERRIDE void EnsureSize(TInt size){
      if(this->_ppEntries == NULL) {
         this->_entryCount = MO_LIB_MAX(size, MO_OBJECT_CAPACITY);
         // 第一次新建时，生成哈希表
         this->_ppEntries = EntryFlatCreate(this->_entryCount);
      } else if (size > (this->_entryCount << 3)) {
         // 扩充内存时处理
         size = this->_entryCount + (MO_LIB_MAX(this->_entryCount, size) >> 1);
         // 当总数大于节点列表长度8倍时，重新扩充节点列表
         SEntry** ppEntries = EntryFlatCreate(size);
         this->EntriesResize(ppEntries, size);
         // 释放旧节点内存
         EntryFlatRelease(this->_ppEntries);
         // 保存新的节点列表
         this->_ppEntries = ppEntries;
         this->_entryCount = size;
      }
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_PTR_DICTIONARY_H__
