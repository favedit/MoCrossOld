#ifndef __MO_CM_DICTIONARY_H__
#define __MO_CM_DICTIONARY_H__

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
struct SDictionaryEntry{
public:
   SDictionaryEntry* linkPtr;
   SDictionaryEntry* priorPtr;
   SDictionaryEntry* nextPtr;
   THashCode hash;
   FString* namePtr;
   T value;
public:
   //------------------------------------------------------------
   // <T>构造哈希字典节点</T>
   SDictionaryEntry(){
      MO_CLEAR(linkPtr);
      MO_CLEAR(priorPtr);
      MO_CLEAR(nextPtr);
      hash = 0;
      MO_CLEAR(namePtr);
   }
   //------------------------------------------------------------
   // <T>析构哈希字典节点</T>
   ~SDictionaryEntry(){
      MO_DELETE(namePtr);
   }
public:
   //------------------------------------------------------------
   // <T>判断名称是否相等。</T>
   MO_INLINE TBool IsName(TCharC* pName){
      if(NULL == namePtr){
         return EFalse;
      }
      return namePtr->Equals(pName);
   }
   //------------------------------------------------------------
   // <T>判断不区分大小写名称是否相等。</T>
   MO_INLINE TBool IsNameIgnoreCase(TCharC* pName){
      if(NULL == namePtr){
         return EFalse;
      }
      return namePtr->EqualsIgnoreCase(pName);
   }
   //------------------------------------------------------------
   // <T>获得字符串名称。</T>
   MO_INLINE TCharC* Name(){
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
public:
   //------------------------------------------------------------
   // <T>释放内容。</T>
   MO_INLINE void Release(){
      MO_DELETE(namePtr);
   }
};

//============================================================
// <T>只读迭代器。</T>
//
// @tool
//============================================================
template <typename T>
class TDictionaryIteratorC{
public:
   typedef SDictionaryEntry<T> SEntry;
protected:
   SEntry* _pStart;
   SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TDictionaryIteratorC(){
      InnerInitialize((SEntry*)NULL);
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TDictionaryIteratorC(const TDictionaryIteratorC<T>& iterator){
      InnerInitialize(iterator._pStart);
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TDictionaryIteratorC(SEntry* pEntry){
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
   MO_INLINE const T& operator *() const{
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T operator->() const{
      MO_ASSERT(_pEntry);
      return _pEntry->value;
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
   MO_INLINE TBool IsValue(T value){
      MO_ASSERT(_pEntry);
      return _pEntry->value == value;
   }
   //------------------------------------------------------------
   // 获得当前位置的数据名称
   MO_INLINE TCharC* Name() const{
      MO_ASSERT(_pEntry);
      return _pEntry->Name();
   }
   //------------------------------------------------------------
   // 获得当前位置的数据内容
   MO_INLINE T Value() const{
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
};

//============================================================
// <T>只读迭代器。</T>
//
// @tool
//============================================================
template <typename T>
class TDictionaryIterator : public TDictionaryIteratorC<T>{
public:
   typedef SDictionaryEntry<T> SEntry;
public:
   //------------------------------------------------------------
   // 构造只读迭代器
   TDictionaryIterator(){
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TDictionaryIterator(const TDictionaryIteratorC<T>& iterator) :
         TDictionaryIteratorC<T>(iterator){
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TDictionaryIterator(SEntry** ppEntries, TInt entryCount) :
         TDictionaryIteratorC<T>(ppEntries, entryCount){
   }
public:
   //------------------------------------------------------------
   // 设置当前位置的数据内容
   MO_INLINE void SetValue(T value) const{
      MO_ASSERT(this->_pEntry);
      this->_pEntry->value = value;
   }
};

//============================================================
// <T>哈希字典</T>
//============================================================
template <typename T>
class MDictionary{
public:
   typedef SDictionaryEntry<T> SEntry;
   typedef TDictionaryIteratorC<T> TIteratorC;
   typedef TDictionaryIterator<T> TIterator;
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
   MDictionary(){
      _count = 0;
      _pFirst = NULL;
      _pLast = NULL;
      _pUnused = NULL;
      _entryCount = 0;
      _ppEntries = NULL;
   }
   //------------------------------------------------------------
   // <T>析构哈希字典。</T>
   MO_ABSTRACT ~MDictionary(){
   }
protected:
   MO_VIRTUAL SEntry* EntryCreate() = 0;
   MO_VIRTUAL void EntryRelease(SEntry* pEntry) = 0;
   MO_VIRTUAL SEntry** EntryFlatCreate(TInt size) = 0;
   MO_VIRTUAL void EntryFlatRelease(SEntry** ppEntries) = 0;
protected:
{source_entry_list}
   //------------------------------------------------------------
   // <T>清空所有使用中的节点。</T>
   MO_INLINE void EntryClear(){
      // 清除表格
      for(TInt n = 0; n < _entryCount; n++){
         _ppEntries[n] = NULL;
      }
      // 清空内容
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
   }
public:
   //------------------------------------------------------------
   // <T>判断当前哈希集合和指定哈希集合中所有数据内容是否相等。</T>
   MO_INLINE TBool operator==(const MDictionary<T>& dictionary) const{
      return Equals(&dictionary);
   }
   //------------------------------------------------------------
   // <T>判断当前哈希集合和指定哈希集合中所有数据内容是否不相等。</T>
   MO_INLINE TBool operator!=(const MDictionary<T>& dictionary) const{
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
   MO_INLINE SEntry* EntryFindValue(T value) const{
      // 数据存在时
      if(_count > 0){
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->value == value){
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
   TBool Equals(const MDictionary<T>* pDictionary) const{
      MO_ASSERT(pDictionary);
      // 比较数量
      if(_count != pDictionary->_count){
         return EFalse;
      }
      // 比较所有项目
      TIteratorC iterator = pDictionary->IteratorC();
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
   const TDictionaryIteratorC<T> IteratorC() const{
      return TDictionaryIteratorC<T>(_pFirst);
   }
   //------------------------------------------------------------
   // <T>获得只读迭代器。</T>
   const TDictionaryIteratorC<T> LastIteratorC() const{
      return TDictionaryIteratorC<T>(_pLast);
   }
public:
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   T Get(TCharC* pName) const{
      MO_ASSERT(pName);
      SEntry* pEntry = EntryFind(pName);
      MO_ASSERT(pEntry);
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   T Find(TCharC* pName) const{
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
   TCharC* Search(T value) const{
      SEntry* pEntry = EntryFindValue(value);
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
   TIteratorC Iterator(){
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
   void Assign(const MDictionary<T>& dictionary){
      Clear();
      Append(dictionary);
   }
   //------------------------------------------------------------
   // <T>追加源哈希表内的全部数据。</T>
   void Append(const MDictionary<T>& dictionary){
      TDictionaryIteratorC<T> iterator = dictionary.IteratorC();
      while(iterator.Next()){
         Set(iterator.Name(), iterator.Value());
      }
   }
   //------------------------------------------------------------
   // <T>根据名称设置数据。</T>
   void Set(TCharC* pName, T value){
      THashCode hash = RString::MakeHashCode(pName);
      TInt index = hash % _entryCount;
      // 查找数据出现的位置
      SEntry* pEntry = _ppEntries[index];
      while(NULL != pEntry) {
         if(pEntry->hash == hash){
            if(pEntry->IsName(pName)){
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
      pEntry->SetName(pName);
      pEntry->value = value;
      _ppEntries[index] = pEntry;
      // 追加到链表尾部
      EntryPush(pEntry);
   }
   //------------------------------------------------------------
   // <T>移除指定名称的数据。</T>
   T Remove(TCharC* pName){
      THashCode hash = RString::MakeHashCode(pName);
      TInt index = hash % _entryCount;
      // 查找数据出现的位置
      T value = NULL;
      SEntry* pPrior = NULL;
      SEntry* pEntry = _ppEntries[index];
      while(NULL != pEntry){
         if(pEntry->hash == hash){
            if(pEntry->IsName(pName)){
               value = pEntry->value;
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
      return value;
   }
   //------------------------------------------------------------
   // <T>清空当前哈希表全部数据，是哈希表可以再次被利用。</T>
   void Clear(){
      EntryClear();
   }
   //------------------------------------------------------------
   // <T>释放哈希表对象。</T>
   void Release(){
      // 释放节点
      if(_count > 0){
         // 将所有节点设置为未使用
         _pLast->nextPtr = _pUnused;
         _pUnused = _pFirst;
      }
      // 删除所有节点
      SEntry* pFind = _pUnused;
      while(NULL != pFind){
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
class TDictionary : public MDictionary<T>{
public:
   typedef SDictionaryEntry<T> SEntry;
public:
   //------------------------------------------------------------
   // <T>创建字典对象。</T>
   TDictionary(){
      this->EnsureSize(MO_OBJECT_CAPACITY);
   }
   //------------------------------------------------------------
   // <T>创建字典对象。</T>
   TDictionary(TInt capacity){
      this->EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>创建字典对象。</T>
   TDictionary(const MDictionary<T>& dictionary){
      this->EnsureSize(dictionary.Count());
      this->Append(dictionary);
   }
   //------------------------------------------------------------
   // <T>创建字典对象。</T>
   TDictionary(const TDictionary<T>& dictionary){
      this->EnsureSize(dictionary.Count());
      this->Append(dictionary);
   }
   //------------------------------------------------------------
   // <T>析构字典对象。</T>
   MO_ABSTRACT ~TDictionary(){
      this->Release();
   }
public:
   //------------------------------------------------------------
   // <T>接收另一个字典对象的全部数据。</T>
   MO_INLINE void operator=(const MDictionary<T>& dictionary){
      Assign(dictionary);
   }
   //------------------------------------------------------------
   // <T>接收另一个字典对象的全部数据。</T>
   MO_INLINE void operator=(const TDictionary<T>& dictionary){
      Assign(dictionary);
   }
protected:
   //------------------------------------------------------------
   // <T>新建一个未使用的节点。</T>
   MO_INLINE SEntry* EntryCreate(){
      this->EnsureSize(this->_count + 1);
      return MO_TYPE_CREATE(SEntry);
   }
   //------------------------------------------------------------
   // <T>释放一个指定的节点。</T>
   MO_INLINE void EntryRelease(SEntry* pEntry){
      MO_ASSERT(pEntry);
      MO_TYPE_DELETE(pEntry);
   }
   //------------------------------------------------------------
   // <T>收集节点列表内存。</T>
   MO_INLINE SEntry** EntryFlatCreate(TInt size){
      SEntry** ppEntities = MO_TYPES_ALLOC(SEntry*, size);
      RTypes<SEntry*>::Clear(ppEntities, size);
      return ppEntities;
   }
   //------------------------------------------------------------
   // <T>释放节点列表内存。</T>
   MO_INLINE void EntryFlatRelease(SEntry** pEntries){
      MO_ASSERT(pEntries);
      MO_FREE(pEntries);
   }
public:
   //------------------------------------------------------------
   // <T>确保当前对象可以容纳指定大小的数据。</T>
   // <P>当哈希表是旧表的8倍时开始扩充，扩大2倍。</P>
   MO_OVERRIDE void EnsureSize(TInt size){
      if(NULL == this->_ppEntries) {
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
//------------------------------------------------------------
typedef MO_CM_DECLARE TDictionary<TAny*> TPtrDictionary;

//============================================================
// <T>字典对象。</T>
//============================================================
template <typename T>
class FDictionary :
      public FObject,
      public MDictionary<T>{
public:
   typedef SDictionaryEntry<T> SEntry;
public:
   //------------------------------------------------------------
   // <T>创建字典对象。</T>
   FDictionary(){
      this->EnsureSize(MO_OBJECT_CAPACITY);
   }
   //------------------------------------------------------------
   // <T>创建字典对象。</T>
   FDictionary(TInt capacity){
      this->EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>创建字典对象。</T>
   FDictionary(const MDictionary<T>& dictionary){
      this->EnsureSize(dictionary.Count());
      this->Append(dictionary);
   }
   //------------------------------------------------------------
   // <T>创建字典对象。</T>
   FDictionary(const FDictionary<T>& dictionary){
      this->EnsureSize(dictionary.Count());
      this->Append(dictionary);
   }
   //------------------------------------------------------------
   // <T>析构字典对象。</T>
   MO_ABSTRACT ~FDictionary(){
      this->Release();
   }
public:
   //------------------------------------------------------------
   // <T>接收另一个字典对象的全部数据。</T>
   MO_INLINE void operator=(const MDictionary<T>& dictionary){
      Assign(dictionary);
   }
   //------------------------------------------------------------
   // <T>接收另一个字典对象的全部数据。</T>
   MO_INLINE void operator=(const FDictionary<T>& dictionary){
      Assign(dictionary);
   }
protected:
   //------------------------------------------------------------
   // <T>新建一个未使用的节点。</T>
   MO_INLINE SEntry* EntryCreate(){
      this->EnsureSize(this->_count + 1);
      return MO_TYPE_CREATE(SEntry);
   }
   //------------------------------------------------------------
   // <T>释放一个指定的节点。</T>
   MO_INLINE void EntryRelease(SEntry* pEntry){
      MO_ASSERT(pEntry);
      MO_TYPE_DELETE(pEntry);
   }
   //------------------------------------------------------------
   // <T>收集节点列表内存。</T>
   MO_INLINE SEntry** EntryFlatCreate(TInt size){
      SEntry** ppEntities = MO_TYPES_ALLOC(SEntry*, size);
      RTypes<SEntry*>::Clear(ppEntities, size);
      return ppEntities;
   }
   //------------------------------------------------------------
   // <T>释放节点列表内存。</T>
   MO_INLINE void EntryFlatRelease(SEntry** ppEntries){
      MO_ASSERT(ppEntries);
      MO_FREE(ppEntries);
   }
public:
   //------------------------------------------------------------
   // <T>确保当前对象可以容纳指定大小的数据。</T>
   // <P>当哈希表是旧表的8倍时开始扩充，扩大2倍。</P>
   MO_OVERRIDE void EnsureSize(TInt size){
      if(NULL == this->_ppEntries) {
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
//------------------------------------------------------------
typedef MO_CM_DECLARE FDictionary<TAny*> FPtrDictionary;

////============================================================
//// <T>不关心字符大小写的哈希字典对象</T>
////============================================================
//template <typename T>
//class MNcDictionaryC : public MEntriesC< SDictionaryEntry<T> >{
//private:
//   typedef SDictionaryEntry<T> SEntry;
//   typedef TDictionaryIteratorC<T> TIteratorC;
//protected:
//   TInt _entryCount;
//   SEntry** _ppEntries;
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
//   // <T>判断当前哈希集合和指定哈希集合中所有数据内容是否相等。</T>
//   TBool operator==(const MNcDictionaryC<T>& dictionary) const{
//      return Equals(&dictionary);
//   }
//   //------------------------------------------------------------
//   // <T>判断当前哈希集合和指定哈希集合中所有数据内容是否不相等。</T>
//   TBool operator!=(const MNcDictionaryC<T>& dictionary) const{
//      return !Equals(&dictionary);
//   }
//   //------------------------------------------------------------
//   // <T>获得指定名称的数据。</T>
//   const T operator[](TCharC* pName) const{
//      MO_ASSERT(pName);
//      SEntry* pEntry = EntryFind(pName);
//      MO_ASSERT(pEntry);
//      return pEntry->value;
//   }
//protected:
//   //------------------------------------------------------------
//   // <T>查找指定名称的索引位置。</T>
//   inline SEntry* EntryFind(TCharC* pName) const{
//      // 数据存在时
//      if(_count > 0){
//         THashCode hash = RString::MakeNocaseHashCode(pName);
//         // 查找名称的索引位置
//         SEntry* pEntry = _ppEntries[hash % _entryCount];
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
//   inline SEntry* EntryFindValue(T value) const{
//      // 数据存在时
//      if(_count > 0){
//         SEntry* pEntry = _pFirst;
//         while(NULL != pEntry){
//            if(pEntry->value == value){
//               return pEntry;
//            }
//            pEntry = pEntry->pNext;
//         }
//      }
//      // 未找到时返回结果
//      return NULL;
//   }
//   //------------------------------------------------------------
//   // 清空链表上所有使用中的节点，放入未使用链表中循环使用
//   inline void EntryClear(){
//      // 清除表格
//      for(TInt n=0; n<_entryCount; n++){
//         _ppEntries[n] = NULL;
//      }
//      // 清除链表
//      MEntriesC<SEntry>::EntryClear();
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
//      MO_ASSERT(pName);
//      return (NULL != EntryFind(pName));
//   }
//   //------------------------------------------------------------
//   // <T>判断指定名称是否存在。</T>
//   TBool ContainsValue(T value) const{
//      return (NULL != EntryFindValue(value));
//   }
//   //------------------------------------------------------------
//   // <T>判断当前哈希集合和指定哈希集合内容是否相等。</T>
//   TBool Equals(const MNcDictionaryC<T>* pDictionary) const{
//      MO_ASSERT(pDictionary);
//      // 比较数量
//      if(_count != pDictionary->_count){
//         return EFalse;
//      }
//      // 比较所有项目
//      TIteratorC iterator = pDictionary->IteratorC();
//      while(iterator.Next()){
//         SEntry* pEntry = EntryFind(iterator.Name());
//         if(NULL == pEntry){
//            return EFalse;
//         }
//         if(!iterator.IsValue(pEntry->value)){
//            return EFalse;
//         }
//      }
//      return ETrue;
//   }
//   //------------------------------------------------------------
//   // <T>获得只读迭代器。</T>
//   const TDictionaryIteratorC<T> IteratorC() const{
//      return TDictionaryIteratorC<T>(_pFirst);
//   }
//   //------------------------------------------------------------
//   // <T>获得只读迭代器。</T>
//   const TDictionaryIteratorC<T> LastIteratorC() const{
//      return TDictionaryIteratorC<T>(_pLast);
//   }
//public:
//   //------------------------------------------------------------
//   // <T>获得指定名称的数据。</T>
//   T Get(TCharC* pName) const{
//      MO_ASSERT(pName);
//      SEntry* pEntry = EntryFind(pName);
//      MO_ASSERT(pEntry);
//      return pEntry->value;
//   }
//   //------------------------------------------------------------
//   // <T>获得指定名称的数据。</T>
//   T Find(TCharC* pName) const{
//      if(NULL != pName){
//         SEntry* pEntry = EntryFind(pName);
//         if(NULL != pEntry){
//            return pEntry->value;
//         }
//      }
//      return NULL;
//   }
//   //------------------------------------------------------------
//   // <T>获得指定名称的数据。</T>
//   TCharC* Search(T value) const{
//      SEntry* pEntry = EntryFindValue(value);
//      return (NULL != pEntry) ? pEntry->pName->MemoryC() : NULL;
//   }
//};
//
////============================================================
//// <T>不关心字符大小写的哈希集合对象</T>
////============================================================
//template <typename A, typename T>
//class MNcDictionary : public MNcDictionaryC<T>{
//public:
//   typedef SDictionaryEntry<T> SEntry;
//   typedef TDictionaryIteratorC<T> TIteratorC;
//   typedef TDictionaryIterator<T> TIterator;
//protected:
//   SEntry* _pUnused;
//private:
//   //------------------------------------------------------------
//   // <T>获得内部类型指针。</T>
//   inline A* InnerCast(){
//      return static_cast<A*>(this);
//   }
//protected:
//   //------------------------------------------------------------
//   // 内部初始化
//   inline void InnerInitialize(TInt size){
//      MNcDictionaryC<T>::InnerInitialize();
//      _pUnused = NULL;
//      InnerCast()->A::EnsureSize(size);
//   }
//   //------------------------------------------------------------
//   // <T>释放哈希表对象。</T>
//   void Release(){
//      A* pInstance = InnerCast();
//      // 释放节点
//      if(_count > 0){
//         // 将所有节点设置为未使用
//         _pLast->pNext = _pUnused;
//         _pUnused = _pFirst;
//         // 删除所有节点
//         SEntry* pFind = _pUnused;
//         while(NULL != pFind){
//            SEntry* pNext = pFind->pNext;
//            pInstance->EntryRelease(pFind);
//            pFind = pNext;
//         }
//      }
//      // 释放节点数组
//      pInstance->EntryFlatRelease(_ppEntries);
//   }
//   //------------------------------------------------------------
//   // <T>收集一个未使用的节点。</T>
//   inline SEntry* EntryAlloc(){
//      SEntry* pEntry = NULL;
//      // 查看未使用节点中是否有自由节点
//      if(NULL != _pUnused){
//         // 收集未使用的节点
//         pEntry = _pUnused;
//         _pUnused = _pUnused->pNext;
//      }else{
//         // 收集新节点
//         pEntry = InnerCast()->A::EntryCreate();
//      }
//      return pEntry;
//   }
//   //------------------------------------------------------------
//   // <T>释放一个被使用的节点。</T>
//   inline void EntryFree(SEntry* pEntry){
//      MO_ASSERT(pEntry);
//      pEntry->pNext = _pUnused;
//      _pUnused = pEntry;
//   }
//public:
//   //------------------------------------------------------------
//   // <T>获得指定名称的数据。</T>
//   T& operator[](TCharC* pName){
//      MO_ASSERT(pName);
//      SEntry* pEntry = EntryFind(pName);
//      MO_ASSERT(pEntry);
//      return pEntry->value;
//   }
//protected:
//   //------------------------------------------------------------
//   // <T>扩充当前对象的哈希值数组。</T>
//   inline void EntriesResize(SEntry** ppEntries, TInt size){
//      // 循环取出旧的节点列表内容，移到新的节点列表上
//      TInt n = -1;
//      while(++n < _entryCount){
//         SEntry* pEntry = _ppEntries[n];
//         while(NULL != pEntry){
//            // 保存当前节点的下一个节点指针
//            SEntry* pLink = pEntry->linkPtr;
//            // 将当前节点存储到新建的节点列表上去
//            TInt index = pEntry->Hash() % size;
//            pEntry->linkPtr = ppEntries[index];
//            ppEntries[index] = pEntry;
//            // 获得保存的下一个节点指针
//            pEntry = pLink;
//         }
//      }
//   }
//public:
//   //------------------------------------------------------------
//   // <T>获得可写迭代器。</T>
//   TIteratorC Iterator(){
//      return TIterator(_pFirst);
//   }
//   //------------------------------------------------------------
//   // <T>获得结尾可写迭代器。</T>
//   TIterator LastIterator(){
//      return TIterator(_pLast);
//   }
//public:
//   //------------------------------------------------------------
//   // <T>接收源哈希表内的全部数据。</T>
//   void Assign(const MNcDictionaryC<T>& dictionary){
//      Clear();
//      Append(dictionary);
//   }
//   //------------------------------------------------------------
//   // <T>追加源哈希表内的全部数据。</T>
//   void Append(const MNcDictionaryC<T>& dictionary){
//      TDictionaryIteratorC<T> iterator = dictionary.IteratorC();
//      while(iterator.Next()){
//         Set(iterator.Name(), iterator.Value());
//      }
//   }
//   //------------------------------------------------------------
//   // <T>根据名称设置数据。</T>
//   void Set(TCharC* pName, T value){
//      THashCode hash = RString::MakeNocaseHashCode(pName);
//      TInt index = hash % _entryCount;
//      // 查找数据出现的位置
//      SEntry* pEntry = _ppEntries[index];
//      while(NULL != pEntry) {
//         if(pEntry->hash == hash){
//            if(pEntry->IsNameIgnoreCase(pName)){
//               pEntry->value = value;
//               return;
//            }
//         }
//         pEntry = pEntry->pNext;
//      }
//      // 如果名称不存在，为新建节点检查内存
//      pEntry = EntryAlloc();
//      index = hash % _entryCount;
//      pEntry->linkPtr = _ppEntries[index];
//      pEntry->hash = hash;
//      pEntry->SetName(pName);
//      pEntry->value = value;
//      _ppEntries[index] = pEntry;
//      // 追加到链表尾部
//      EntryPush(pEntry);
//   }
//   //------------------------------------------------------------
//   // <T>移除指定名称的数据。</T>
//   T Remove(TCharC* pName){
//      THashCode hash = RString::MakeNocaseHashCode(pName);
//      TInt index = hash % _entryCount;
//      // 查找数据出现的位置
//      T value = NULL;
//      SEntry* pPrior = _ppEntries[index];
//      SEntry* pEntry = pPrior;
//      while(NULL != pEntry){
//         if(pEntry->hash == hash){
//            if(pEntry->IsNameIgnoreCase(pName)){
//               value = pEntry->value;
//               if(pEntry == pPrior){
//                  // 当前对象是第一个对象时
//                  _ppEntries[index] = pEntry->pNext;
//               }else{
//                  // 当前对象不是第一个对象时
//                  pPrior->pNext = pEntry->pNext;
//               }
//               // 删除当前节点
//               EntryRemove(pEntry);
//               break;
//            }
//         }
//         pPrior = pEntry;
//         pEntry = pEntry->pNext;
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
//// <T>不关心字符大小写的字典对象。</T>
////============================================================
//template <typename T>
//class TNcDictionary : public MNcDictionary<TNcDictionary<T>, T>{
//public:
//   friend class MNcDictionary< TNcDictionary<T>, T >;
//   typedef SDictionaryEntry<T> SEntry;
//public:
//   //------------------------------------------------------------
//   // <T>创建字典对象。</T>
//   TNcDictionary(){
//      InnerInitialize(MO_OBJECT_CAPACITY);
//   }
//   //------------------------------------------------------------
//   // <T>创建字典对象。</T>
//   TNcDictionary(TInt capacity){
//      InnerInitialize(capacity);
//   }
//   //------------------------------------------------------------
//   // <T>创建字典对象。</T>
//   TNcDictionary(const MNcDictionaryC<T>& dictionary){
//      InnerInitialize(dictionary.Count());
//      Append(dictionary);
//   }
//   //------------------------------------------------------------
//   // <T>创建字典对象。</T>
//   TNcDictionary(const TNcDictionary<T>& dictionary){
//      InnerInitialize(dictionary.Count());
//      Append(dictionary);
//   }
//   //------------------------------------------------------------
//   // <T>创建字典对象。</T>
//   ~TNcDictionary(){
//      InnerRelease();
//   }
//public:
//   //------------------------------------------------------------
//   // <T>接收另一个字典对象的全部数据。</T>
//   void operator=(const MNcDictionaryC<T>& dictionary){
//      Assign(dictionary);
//   }
//   //------------------------------------------------------------
//   // <T>接收另一个字典对象的全部数据。</T>
//   void operator=(const TNcDictionary<T>& dictionary){
//      Assign(dictionary);
//   }
//   protected:
////------------------------------------------------------------
//   // <T>新建一个未使用的节点。</T>
//   inline SEntry* EntryCreate(){
//      EnsureSize(_count + 1);
//      return MO_CREATE(SEntry);
//   }
//   //------------------------------------------------------------
//   // <T>释放一个指定的节点。</T>
//   inline void EntryRelease(SEntry* pEntry){
//      MO_ASSERT(pEntry);
//      MO_DELETE(pEntry);
//   }
//   //------------------------------------------------------------
//   // <T>收集节点列表内存。</T>
//   inline SEntry** EntryFlatCreate(TInt size){
//      SEntry** ppEntries = MO_TYPES_ALLOC(SEntry*, size);
//      RType<SEntry*>::Clear(ppEntries, size);
//      return ppEntries;
//   }
//   //------------------------------------------------------------
//   // <T>释放节点列表内存。</T>
//   inline void EntryFlatRelease(SEntry** ppEntries){
//      MO_ASSERT(ppEntries);
//      MO_FREE(ppEntries);
//   }
//public:
//   //------------------------------------------------------------
//   // <T>确保当前对象可以容纳指定大小的数据。</T>
//   // <P>当哈希表是旧表的8倍时开始扩充，扩大2倍。</P>
//   void EnsureSize(TInt size){
//      if(NULL == _ppEntries) {
//         _entryCount = MO_LIB_MAX(size, MO_OBJECT_CAPACITY);
//         // 第一次新建时，生成哈希表
//         _ppEntries = EntryFlatCreate(_entryCount);
//      } else if (size > (_entryCount << 3)) {
//         // 扩充内存时处理
//         size = _entryCount + (MO_LIB_MAX(_entryCount, size) >> 1);
//         // 当总数大于节点列表长度8倍时，重新扩充节点列表
//         SEntry** ppEntries = EntryFlatCreate(size);
//         EntriesResize(ppEntries, size);
//         // 释放旧节点内存
//         EntryFlatRelease(_ppEntries);
//         // 保存新的节点列表
//         _ppEntries = ppEntries;
//         _entryCount = size;
//      }
//   }
//};
////------------------------------------------------------------
//typedef MO_CM_DECLARE TNcDictionary<TAny*> TPtrNcDictionary;
//
////============================================================
//// <T>不关心字符大小写的字典对象。</T>
////============================================================
//template <typename T>
//class FNcDictionary : public MNcDictionary<FNcDictionary<T>, T>{
//public:
//   friend class MNcDictionary< FNcDictionary<T>, T >;
//   typedef SDictionaryEntry<T> SEntry;
//public:
//   //------------------------------------------------------------
//   // <T>创建字典对象。</T>
//   FNcDictionary(){
//      InnerInitialize(MO_OBJECT_CAPACITY);
//   }
//   //------------------------------------------------------------
//   // <T>创建字典对象。</T>
//   FNcDictionary(TInt capacity){
//      InnerInitialize(capacity);
//   }
//   //------------------------------------------------------------
//   // <T>创建字典对象。</T>
//   FNcDictionary(const MDictionaryC<T>& dictionary){
//      InnerInitialize(dictionary.Count());
//      Append(dictionary);
//   }
//   //------------------------------------------------------------
//   // <T>创建字典对象。</T>
//   FNcDictionary(const FDictionary<T>& dictionary){
//      InnerInitialize(dictionary.Count());
//      Append(dictionary);
//   }
//   //------------------------------------------------------------
//   // <T>释放字典对象。</T>
//   ~FNcDictionary(){
//      InnerRelease();
//   }
//public:
//   //------------------------------------------------------------
//   // <T>接收另一个字典对象的全部数据。</T>
//   void operator=(const MNcDictionaryC<T>& dictionary){
//      Assign(dictionary);
//   }
//   //------------------------------------------------------------
//   // <T>接收另一个字典对象的全部数据。</T>
//   void operator=(const FNcDictionary<T>& dictionary){
//      Assign(dictionary);
//   }
//protected:
//   //------------------------------------------------------------
//   // <T>新建一个未使用的节点。</T>
//   inline SEntry* EntryCreate(){
//      EnsureSize(_count + 1);
//      return MO_CREATE(SEntry);
//   }
//   //------------------------------------------------------------
//   // <T>释放一个指定的节点。</T>
//   inline void EntryRelease(SEntry* pEntry){
//      MO_ASSERT(pEntry);
//      MO_DELETE(pEntry);
//   }
//   //------------------------------------------------------------
//   // <T>收集节点列表内存。</T>
//   inline SEntry** EntryFlatCreate(TInt size){
//      SEntry** ppEntries = MO_TYPES_ALLOC(SEntry*, size);
//      RTypes<SEntry*>::Clear(ppEntries, size);
//      return ppEntries;
//   }
//   //------------------------------------------------------------
//   // <T>释放节点列表内存。</T>
//   inline void EntryFlatRelease(SEntry** ppEntries){
//      MO_ASSERT(ppEntries);
//      MO_FREE(ppEntries);
//   }
//public:
//   //------------------------------------------------------------
//   // <T>确保当前对象可以容纳指定大小的数据。</T>
//   // <P>当哈希表是旧表的8倍时开始扩充，扩大2倍。</P>
//   void EnsureSize(TInt size){
//      if(NULL == _ppEntries) {
//         _entryCount = MO_LIB_MAX(size, MO_OBJECT_CAPACITY);
//         // 第一次新建时，生成哈希表
//         _ppEntries = EntryFlatCreate(_entryCount);
//      } else if (size > (_entryCount << 3)) {
//         // 扩充内存时处理
//         size = _entryCount + (MO_LIB_MAX(_entryCount, size) >> 1);
//         // 当总数大于节点列表长度8倍时，重新扩充节点列表
//         SEntry** ppEntries = EntryFlatCreate(size);
//         EntriesResize(ppEntries, size);
//         // 释放旧节点内存
//         EntryFlatRelease(_ppEntries);
//         // 保存新的节点列表
//         _ppEntries = ppEntries;
//         _entryCount = size;
//      }
//   }
//};
////------------------------------------------------------------
//typedef MO_CM_DECLARE FNcDictionary<TAny*> FPtrNcDictionary;

MO_NAMESPACE_END

#endif // __MO_CM_DICTIONARY_H__
