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
         pEntry = this->EntryCreate();
      }
      return pEntry;
   }
   //------------------------------------------------------------
   // <T>释放一个被使用的节点。</T>
   MO_INLINE void EntryFree(SEntry* pEntry){
      MO_ASSERT(pEntry);
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