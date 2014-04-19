#include "MoCmSharedList.h"

MO_NAMESPACE_BEGIN

//============================================================
void TSharedList::Initialize(FSharedListAllocator* pAllocator){
   _pAllocator = pAllocator;
   _gHead = pAllocator->AllocList();
   _gEntries = pAllocator->Entries();
   MO_ASSERT(_pAllocator);
   MO_ASSERT(_gHead);
   MO_ASSERT(_gEntries);
}

//============================================================
void TSharedList::Release(){
   MO_ASSERT(_pAllocator);
   MO_ASSERT(_gHead);
   _pAllocator->FreeList(_gHead);
}

//============================================================
// 判断当前链表和指定链表中所有数据内容是否相等。
//============================================================
TBool TSharedList::operator==(const TSharedList& list) const{
   return Equals(list);
}

//============================================================
// 判断当前链表和指定链表中所有数据内容是否不相等。
//============================================================
TBool TSharedList::operator!=(const TSharedList& list) const{
   return !Equals(list);
}

//============================================================
// 追加一个链表到当前链表中。
//============================================================
void TSharedList::operator+=(const TSharedList& list){
   Append(list);
}

//============================================================
// 获得是否为空
//============================================================
TBool TSharedList::IsEmpty() const{
   MO_ASSERT(_gHead);
   return (0 == _gHead->count);
}

//============================================================
// 获得总数
//============================================================
TInt TSharedList::Count() const{
   MO_ASSERT(_gHead);
   return _gHead->count;
}

//============================================================
// 判断当前链表和指定链表是否相等
//============================================================
TBool TSharedList::Equals(const TSharedList& list) const{
   MO_ASSERT(_gHead);
   MO_ASSERT(_gEntries);
   // 比较数量
   if(_gHead->count != list._gHead->count){
      return EFalse;
   }
   // 比较所有项目
   TInt current = _gHead->first;
   TIteratorC iterator = list.IteratorC();
   while(current >= 0 && iterator.Next()){
      SSharedListEntry& entry = _gEntries[current];
      if(entry.code != *iterator){
         return EFalse;
      }
      current = entry.next;
   }
   return ETrue;
}

//============================================================
// <T>判断当前链表是否含有指定代码。</T>
//
// @param code 代码
// @return 是否含有
//============================================================
TBool TSharedList::Contains(TInt32 code){
   MO_ASSERT(_gHead);
   MO_ASSERT(_gEntries);
   TInt current = _gHead->first;
   while(current >= 0){
      SSharedListEntry& entry = _gEntries[current];
      if(entry.code == code){
         return ETrue;
      }
      current = entry.next;
   }
   return EFalse;
}

//============================================================
// 获得首位置的只读数据迭代器
//============================================================
TSharedListIteratorC TSharedList::IteratorC() const{
   MO_ASSERT(_pAllocator);
   MO_ASSERT(_gHead);
   return TIteratorC(_pAllocator->Entries(), _gHead->first);
}

//============================================================
// 获得尾位置的只读数据迭代器
//============================================================
TSharedListIteratorC TSharedList::LastIteratorC() const{
   MO_ASSERT(_pAllocator);
   MO_ASSERT(_gHead);
   return TIteratorC(_pAllocator->Entries(), _gHead->last);
}

//============================================================
// 接收一个链表到当前链表中。
//============================================================
void TSharedList::Assign(const TSharedList& list){
   Clear();
   Append(list);
}

//============================================================
// 追加一个链表到当前链表中。
//============================================================
void TSharedList::Append(const TSharedList& list){
   TIteratorC iterator = list.IteratorC();
   while(iterator.Next()){
      Push(*iterator);
   }
}

//============================================================
// 将数据压入首位置
//============================================================
void TSharedList::Unshift(TInt32 code){
   MO_ASSERT(_pAllocator);
   MO_ASSERT(_gHead);
   // 收集一个未使用的节点
   SSharedListEntry& entry = _pAllocator->AllocEntry();
   // 将当前节点压入首节点
   if(_gHead->first < 0){
      _gHead->last = entry.index;
   }else{
      _gEntries[_gHead->first].prior = entry.index;
   }
   // 设置内容
   entry.prior = -1;
   entry.next = _gHead->first;
   entry.code = code;
   _gHead->first = entry.index;
   _gHead->count++;
}

//============================================================
// 弹出链表的首数据
//============================================================
TInt32 TSharedList::Shift(){
   MO_ASSERT(_pAllocator);
   MO_ASSERT(_gHead);
   MO_ASSERT(_gHead->first >= 0);
   // 弹出首节点
   TInt index = _gHead->first;
   SSharedListEntry& entry = _gEntries[index];
   _gHead->first = entry.next;
   if(_gHead->first < 0){
      _gHead->last = -1;
   }else{
      _gEntries[_gHead->first].prior = -1;
   }
   // 设置内容
   _gHead->count--;
   // 回收空闲节点
   TInt32 code = entry.code;
   _pAllocator->FreeEntry(entry);
   // 返回节点
   return code;
}

//============================================================
// 将数据压入尾位置
//============================================================
void TSharedList::Push(TInt32 code){
   MO_ASSERT(_pAllocator);
   MO_ASSERT(_gHead);
   // 收集一个未使用的节点
   SSharedListEntry& entry = _pAllocator->AllocEntry();
   // 将当前节点插入尾节点
   if(_gHead->last < 0){
      _gHead->first = entry.index;
   }else{
      _gEntries[_gHead->last].next = entry.index;
   }
   // 设置内容
   entry.prior = _gHead->last;
   entry.next = -1;
   entry.code = code;
   _gHead->last = entry.index;
   _gHead->count++;
}

//============================================================
// 弹出链表的尾数据
//============================================================
TInt TSharedList::Pop(){
   MO_ASSERT(_pAllocator);
   MO_ASSERT(_gHead);
   MO_ASSERT(_gHead->last >= 0);
   // 弹出尾节点
   TInt index = _gHead->last;
   SSharedListEntry& entry = _gEntries[index];
   _gHead->last = entry.prior;
   if(_gHead->last < 0){
      _gHead->first = 0;
   }else{
      _gEntries[_gHead->last].next = 0;
   }
   // 设置内容
   _gHead->count--;
   // 回收空闲节点
   TInt32 code = entry.code;
   _pAllocator->FreeEntry(entry);
   // 返回节点
   return code;
}

//============================================================
// 从链表上删除链表节点
//============================================================
TInt32 TSharedList::Delete(TInt index){
   MO_ASSERT(_pAllocator);
   MO_ASSERT(_gHead);
   // 获得节点
   SSharedListEntry& entry = _gEntries[index];
   TInt prior = entry.prior;
   TInt next = entry.next;
   // 处理前节点
   if(prior < 0){
      _gHead->first = next;
   }else{
      _gEntries[prior].next = next;
   }
   // 处理后节点
   if(next < 0){
      _gHead->last = prior;
   }else{
      _gEntries[next].prior = prior;
   }
   // 设置内容
   _gHead->count--;
   // 回收空闲节点
   TInt32 code = entry.code;
   _pAllocator->FreeEntry(entry);
   // 返回节点
   return code;
}

//============================================================
TInt32 TSharedList::Delete(TSharedListIterator& iterator){
   MO_ASSERT(_gEntries);
   TInt index = iterator._current;
   if(index >= 0){
      // 指针后移
      iterator._current  = _gEntries[index].next;
      // 删除位置
      Delete(index);
      return ETrue;
   }
   return EFalse;
}

//============================================================
// 从链表上删除链表节点
//============================================================
TBool TSharedList::Remove(TInt32 code){
   MO_ASSERT(_gHead);
   MO_ASSERT(_gEntries);
   TBool result = EFalse;
   TInt32 current = _gHead->first;
   while(current >= 0){
      SSharedListEntry& entry = _gEntries[current];
      TInt next = entry.next;
      if(entry.code == code){
         result = ETrue;
         Delete(entry.index);
      }
      current = next;
   }
   return result;
}

//============================================================
// 清空所有数据
//============================================================
void TSharedList::Clear(){
   MO_ASSERT(_pAllocator);
   MO_ASSERT(_gHead);
   // 释放全部列表
   _pAllocator->FreeEntries(_gHead);
   // 设置清空内容
   _gHead->count = 0;
   _gHead->first = -1;
   _gHead->last = -1;
}

MO_NAMESPACE_END
