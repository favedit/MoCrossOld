protected:
   //------------------------------------------------------------
   // <T>收集堆内存。</T>
   MO_INLINE( SEntry* InnerEntryAlloc() ){
      SEntry* pEntry = NULL;
      // 收集未使用的节点
      if(NULL != _pFree){
         pEntry = _pFree;
         _pFree = _pFree->nextPtr;
      }
      // 如果没有未使用的，则创建一个入口对象
      if(NULL == pEntry){
         pEntry = InnerEntryCreate();
         MO_ASSERT(pEntry);
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>收集堆内存。</T>
   MO_INLINE( void InnerEntryFree(SEntry* pFirst, SEntry* pLast = NULL) ){
      MO_ASSERT(pFirst);
      if(NULL == pLast){
         pFirst->nextPtr = _pFree;
      }else{
         pLast->nextPtr = _pFree;
      }
      _pFree = pFirst;
   }
   //------------------------------------------------------------
   // <T>将链表节点压入首位置。</T>
   MO_INLINE( SEntry* InnerEntryGet(TInt index) const ){
      MO_ASSERT_RANGE(index, 0, _count);
      // 获得第一个
      if(0 == index){
         return this->_pFirst;
      }
      // 获得指定位置数据
      SEntry* pEntry = this->_pFirst;
      while(--index >= 0){
         pEntry = pEntry->nextPtr;
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>将链表节点压入首位置。</T>
   MO_INLINE( void InnerEntryUnshift(SEntry* pEntry) ){
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
   MO_INLINE( SEntry* InnerEntryShift() ){
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
   MO_INLINE( void InnerEntryPush(SEntry* pEntry) ){
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
   MO_INLINE( SEntry* InnerEntryPop() ){
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
   MO_INLINE( void InnerEntryInsert(SEntry* pPrior, SEntry* pEntry) ){
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
   MO_INLINE( void InnerEntryRemove(SEntry* pEntry) ){
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
   //------------------------------------------------------------
   // <T>清空链表所有节点。</T>
   MO_INLINE( void InnerClear() ){
      // 释放链表
      if(_count > 0){
         InnerEntryFree(_pFirst, _pLast);
      }
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
   }
   //------------------------------------------------------------
   // <T>释放链表所有节点。</T>
   MO_INLINE( void InnerRelease() ){
      // 释放链表
      if(_count > 0){
         InnerEntryFree(_pFirst, _pLast);
      }
      // 释放资源
      while(NULL != _pFree){
         SEntry* pNext = _pFree->nextPtr;
         InnerEntryDelete(_pFree);
         _pFree = pNext;
      }
   }