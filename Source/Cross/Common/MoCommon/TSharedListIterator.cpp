#include "MoCmSharedList.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造共享链表节点收集器。</T>
//============================================================
TBool TSharedListIterator::Delete(){
   if((NULL != _pList) && (_current >= 0)){
      // 指针后移
      _skip = ETrue;
      _next = _pEntries[_current].next;
      // 删除位置
      _pList->Delete(_current);
      // 如果为空则不能继续
      if(_pList->IsEmpty()){
         _start = -1;
      }
      return ETrue;
   }
   return EFalse;
}

MO_NAMESPACE_END
