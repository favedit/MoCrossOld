#ifndef __MO_CM_TREE_H__
#define __MO_CM_TREE_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_ENTRY_H__
#include "MoCmEntry.h"
#endif // __MO_CM_ENTRY_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>哈希集合对象</T>
//============================================================
template <typename N, typename V>
struct STreeEntry{
   STreeEntry* pPrior;
   STreeEntry* pNext;
   STreeEntry** ppNodes;
   TBool exists;
   N code;
   V value;
};

//============================================================
// <T>只读链表迭代器。</T>
//
// @tool
//============================================================
template <typename N, typename V>
class TTreeIteratorC : public MLinkedIteratorC< STreeEntry<N, V>* >{
public:
   typedef STreeEntry<N, V> SEntry;
public:
   //------------------------------------------------------------
   // 构造只读迭代器
   TTreeIteratorC(){
      Initialize((SEntry*)NULL);
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TTreeIteratorC(const TTreeIteratorC& iterator){
      Initialize(iterator._pStart);
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   TTreeIteratorC(SEntry* pEntry){
      Initialize(pEntry);
   }
public:
   //------------------------------------------------------------
   // 判断当前位置是否相等
   inline EBoolean operator==(const TTreeIteratorC& iterator) const{
      if((NULL != this->_pEntry) && (NULL != iterator._pEntry)){
         return this->_pEntry->value == iterator._pEntry->value;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // 判断当前位置是否不相等
   inline EBoolean operator!=(const TTreeIteratorC& iterator) const{
      if((NULL != this->_pEntry) && (NULL != iterator._pEntry)){
         return this->_pEntry->value != iterator._pEntry->value;
      }
      return ETrue;
   }
   //------------------------------------------------------------
   // 获得当前位置的数据内容
   inline const V& operator *() const{
      MO_ASSERT(this->_pEntry);
      return this->_pEntry->value;
   }
   //------------------------------------------------------------
   // 获得当前位置的数据内容
   inline const V* operator->() const{
      MO_ASSERT(this->_pEntry);
      return &(this->_pEntry->value);
   }
public:
   //------------------------------------------------------------
   // 获得当前位置的数据名称
   inline N Code() const{
      MO_ASSERT(this->_pEntry);
      return this->_pEntry->code;
   }
   //------------------------------------------------------------
   // 获得当前位置的数据内容
   inline V Value() const{
      MO_ASSERT(this->_pEntry);
      return this->_pEntry->value;
   }
};

//============================================================
// <T>可写链表迭代器。</T>
//
// @tool
//============================================================
template <typename N, typename V>
class TTreeIterator : public TTreeIteratorC<N, V>{
public:
   typedef STreeEntry<N, V> SEntry;
public:
   //------------------------------------------------------------
   // 构造链表迭代器
   TTreeIterator(){
   }
   //------------------------------------------------------------
   // 构造链表迭代器
   TTreeIterator(const TTreeIterator& iterator) :
         TTreeIteratorC<N, V>(iterator){
   }
   //------------------------------------------------------------
   // 构造链表迭代器
   TTreeIterator(SEntry* pEntry) :
         TTreeIteratorC<N, V>(pEntry){
   }
public:
   //------------------------------------------------------------
   // 设置当前位置的内容
   inline void SetValue(V value) const{
      MO_ASSERT(this->_pEntry);
      this->_pEntry->value = value;
   }
};

//============================================================
// <T>只读树目录。</T>
// <P>为了简化树历边操作，树为有序树。</P>
//
// @history 100103 MAOCY 创建
//============================================================
template <typename N, typename V>
class MTreeC : public MLinkedEntryC< STreeEntry<N, V>* >{
public:
   typedef STreeEntry<N, V> SEntry;
   typedef MLinkedEntryC<SEntry*> MLinkedC;
   typedef TTreeIteratorC<N, V> TIteratorC;
protected:
   TInt _level;
   SEntry* _pRoot;
protected:
   //------------------------------------------------------------
   // 内部初始化
   inline void Initialize(TInt level){
      MO_ASSERT(level > 1);
      MLinkedC::Initialize();
      _level = level;
      _pRoot = NULL;
   }
public:
   //------------------------------------------------------------
   // <T>比较当前树内容和指定树所有代码和内容是否相等。</T>
   EBoolean operator==(const MTreeC<N, V>& tree) const{
      return Equals(tree);
   }
   //------------------------------------------------------------
   // <T>比较当前树内容和指定树所有代码和内容是否不相等。</T>
   EBoolean operator!=(const MTreeC<N, V>& tree) const{
      return !Equals(tree);
   }
   //------------------------------------------------------------
   inline V operator[](N code) const{
      SEntry* pEntry = EntryFind(code);
      MO_ASSERT(pEntry);
      return pEntry->value;
   }
protected:
   //------------------------------------------------------------
   // 获得指定位置的数据
   inline SEntry* EntryFind(N code) const{
      // 获得指定位置数据
      SEntry* pEntry = _pRoot;
      while(code > 0){
         pEntry = pEntry->ppNodes[code % (1<<_level)];
         if(NULL == pEntry){
            return NULL;
         }
         code = code >> _level;
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // 清空所有使用中的节点，放入未使用链表中循环使用
   inline void EntryClear(){
      // 清除所有节点，并查找到最后一个节点
      SEntry* pEntry = this->_pFirst;
      while(NULL != pEntry){
         if(NULL != pEntry->ppNodes){
            RTypes<SEntry*>::Clear(pEntry->ppNodes, 1 << _level);
         }
         pEntry = pEntry->pNext;
      }
      // 将使用节点全部放入回收节点
      MLinkedEntryC<SEntry*>::EntryClear();
      // 清空内所有内容
      _pRoot = NULL;
   }
public:
   //------------------------------------------------------------
   // 获得层大小
   TInt Level() const{
      return _level;
   }
   //------------------------------------------------------------
   // <T>判断是否存在指定代码。</T>
   TBool Constains(N code) const{
      SEntry* pEntry = EntryFind(code);
      return (NULL != pEntry) ? pEntry->exists : EFalse;
   }
   //------------------------------------------------------------
   // <T>比较当前树内容和指定树所有代码和内容是否相等。</T>
   TBool Equals(const MTreeC<N, V>& tree) const{
      // 比较数量
      if(this->_count == tree._count){
         // 比较所有项目
         TIteratorC iterator = tree.IteratorC();
         while(iterator.Next()){
            SEntry* pEntry = EntryFind(iterator.Code());
            if(NULL == pEntry){
               return EFalse;
            }
            if(pEntry->value != iterator.value){
               return EFalse;
            }
         }
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>获得指定代码的数据。</T>
   V Get(N code) const{
      SEntry* pEntry = EntryFind(code);
      MO_ASSERT(pEntry);
      MO_ASSERT(pEntry->exists);
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得当前树的只读迭代器。</T>
   TIteratorC IteratorC() const{
      return TIteratorC(this->_pFirst);
   }
};

//============================================================
// <T>哈希集合对象</T>
//============================================================
template <typename N, typename V>
class MTree : public MTreeC<N, V>{
public:
   typedef STreeEntry<N, V> SEntry;
   typedef TTreeIterator<N, V> TIterator;
protected:
   //------------------------------------------------------------
   // 内部初始化
   inline void Initialize(TInt level){
      MTreeC<N, V>::Initialize(level);
      this->_level = level;
      this->_pRoot = EntryAlloc();
   }
protected:
   //------------------------------------------------------------
   // 收集
   SEntry* EntryAlloc(){
      SEntry* pEntry = this->_pUnused;
      if(NULL == pEntry){
         pEntry = MO_TYPE_ALLOC(SEntry);
      }else{
         this->_pUnused = pEntry->pNext;
      }
      pEntry->exists = EFalse;
      EntryPush(pEntry);
      return pEntry;
   }
   //------------------------------------------------------------
   // 释放所有节点
   void EntryRelease(){
      SEntry* pEntry = this->_pFirst;
      while(NULL != pEntry){
         // 释放当前节点
         if(NULL != pEntry->ppNodes){
            MO_FREE(pEntry->ppNodes);
         }
         // 处理下一个节点
         SEntry* pNext = pEntry->pNext;
         delete pEntry;
         pEntry = pNext;
      }
   }
public:
   //------------------------------------------------------------
   // <T>获得当前树的只读迭代器。</T>
   TIterator Iterator() const{
      return TIterator(this->_pFirst);
   }
   //------------------------------------------------------------------------
   // 接受一个树到当前树中。
   void Assign(const MTreeC<N, V>& tree){
      Clear();
      Append(tree);
   }
   //------------------------------------------------------------------------
   // 追加一个树到当前树中。
   void Append(const MTreeC<N, V>& tree){
      TTreeIteratorC<N, V> iterator = tree.IteratorC();
      while(iterator.Next()){
         Set(iterator.Code(), iterator.Value());
      }
   }
   //------------------------------------------------------------
   // 将指定代码的数据存储到指定节点
   void Set(N code, V value){
      N find = code;
      SEntry* pFind = this->_pRoot;
      SEntry* pEntry = this->_pRoot;
      // 循环查找
      while(find > 0){
         TInt index = find % (1 << this->_level);
         find = find >> this->_level;
         // 填充节点内部
         if(NULL == pEntry->ppNodes){
            TInt size = 1 << this->_level;
            pEntry->ppNodes = MO_TYPES_ALLOC(SEntry*, size);
            RTypes<SEntry*>::Clear(pEntry->ppNodes, size);
         }
         // 增加存储节点
         pFind = pEntry->ppNodes[index];
         if(NULL == pFind){
            pFind = EntryAlloc();
            pEntry->ppNodes[index] = pFind;
         }
         pEntry = pFind;
      }
      // 设置数据内容
      pFind->exists = ETrue;
      pFind->code = code;
      pFind->value = value;
   }
   //------------------------------------------------------------
   // 将指定代码的数据存储到指定节点
   void Remove(N code){
      SEntry* pEntry = EntryFind(code);
      MO_ASSERT(pEntry);
      // 不删除节点，将数据标志设置为空
      pEntry->exists = EFalse;
   }
   //------------------------------------------------------------
   // 清除树目录所有节点
   void Clear(){
      this->EntryClear();
      this->_pRoot = EntryAlloc();
   }
};

//============================================================
// <T>变长栈链表。</T>
//
// @tool
//============================================================
template <typename N, typename V>
class TTree : public MTree<N, V>{
public:
   //------------------------------------------------------------
   // 构造树
   TTree(TInt level){
      this->Initialize(level);
   }
   //------------------------------------------------------------
   // 构造树
   TTree(const MTreeC<N, V>& tree){
      this->Initialize(tree.Level());
      this->Append(tree);
   }
   //------------------------------------------------------------
   // 析构树
   ~TTree(){
      this->EntryRelease();
   }
public:
   //------------------------------------------------------------------------
   // 追加一个链表到当前链表中。
   void operator=(const MTreeC<N, V>& tree){
      this->Assign(tree);
   }
};

//============================================================
// <T>变长堆链表。</T>
//
// @tool
//============================================================
template <typename N, typename V>
class FTree : public MTree<N, V>{
public:
   //------------------------------------------------------------
   // 构造链表
   FTree(TInt level){
      this->Initialize(level);
   }
   //------------------------------------------------------------
   // 构造链表
   FTree(const MTreeC<N, V>& tree){
      this->Initialize(tree.Level());
      this->Append(tree);
   }
   //------------------------------------------------------------
   // 析构树
   ~FTree(){
   }
public:
   //------------------------------------------------------------------------
   // 追加一个链表到当前链表中。
   void operator=(const MTreeC<N, V>& tree){
      this->Assign(tree);
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_TREE_H__
