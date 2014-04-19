public:
   //------------------------------------------------------------
   // <T>追加一个内容到当前链表中。</T>
   MO_INLINE( void operator+=(T value) ){
      Push(value);
   }
   //------------------------------------------------------------
   // <T>追加一个链表到当前链表中。</T>
   MO_INLINE( void operator+=(const MList<T>* pList) ){
      Append(pList);
   }
   //------------------------------------------------------------
   // <T>从当前链表中删除一个内容。</T>
   MO_INLINE( void operator-=(T value) ){
      Remove(value);
   }
public:
   //------------------------------------------------------------
   // <T>获得是否为空。</T>
   MO_INLINE( TBool IsEmpty() const ){
      return (0 == _count);
   }
   //------------------------------------------------------------
   // <T>获得总数。</T>
   MO_INLINE( TInt Count() const ){
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得首位置的链表迭代器。</T>
   MO_INLINE( const TIteratorC IteratorC() const ){
      return TIteratorC(_pFirst, _pLast, NULL);
   }
   //------------------------------------------------------------
   // <T>获得首位置的链表迭代器。</T>
   MO_INLINE( TIterator Iterator() ){
      return TIterator(_pFirst, _pLast, NULL);
   }
public:
   //------------------------------------------------------------
   // <T>判断当前链表和指定链表是否相等。</T>
   TBool Equals(const MList<T>* pList) const{
      MO_ASSERT(pList);
      // 比较数量
      if(_count != pList->Count()){
         return EFalse;
      }
      // 比较所有项目
      SEntry* pEntry = _pFirst;
      TIteratorC iterator = pList->IteratorC();
      while((NULL != pEntry) && iterator.Next()){
         if(!iterator.Equals(pEntry->value)){
            return EFalse;
         }
         pEntry = pEntry->nextPtr;
      }
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>从当前数组中是否含有指定数据。</T>
   TBool Contains(T value) const{
      if(_count > 0){
         // 比较所有项目
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->value == value){
               return ETrue;
            }
            pEntry = pEntry->nextPtr;
         }
      }
      return EFalse;
   }
public:
   //------------------------------------------------------------
   // <T>获得首位置的数据。</T>
   T First() const{
      return (NULL != _pFirst) ? _pFirst->value : NULL;
   }
   //------------------------------------------------------------
   // <T>获得尾位置的数据。</T>
   T Last() const{
      return (NULL != _pLast) ? _pLast->value : NULL;
   }
   //------------------------------------------------------------
   // <T>获得指定位置的数据。</T>
   T Get(TInt index) const{
      SEntry* pEntry = InnerEntryGet(index);
      MO_ASSERT(pEntry);
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>在当前链表中查找指定数据。</T>
   TIteratorC FindC(T value){
      if(_count > 0){
         // 比较所有项目
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->value == value){
               return TIteratorC(_pFirst, _pLast);
            }
            pEntry = pEntry->nextPtr;
         }
      }
      return TIteratorC();
   }
   //------------------------------------------------------------
   // <T>在当前链表中查找指定数据。</T>
   TIterator Find(T value){
      if(_count > 0){
         // 比较所有项目
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->value == value){
               return TIterator(_pFirst, _pLast);
            }
            pEntry = pEntry->nextPtr;
         }
      }
      return TIterator();
   }
public:
   //------------------------------------------------------------
   // <T>接受一个链表到当前链表中。</T>
   void Assign(const MList<T>* pList){
      MO_ASSERT(pList);
      Clear();
      Append(pList);
   }
   //------------------------------------------------------------
   // <T>接受一个链表到当前链表中。</T>
   void Assign(TIterator iterator){
      Clear();
      Append(iterator);
   }
   //------------------------------------------------------------
   // <T>追加一个链表到当前链表中。</T>
   void Append(const MList<T>* pList){
      MO_ASSERT(pList);
      SEntry* pEntry = pList->_pFirst;
      while(NULL != pEntry){
         Push(pEntry->value);
         pEntry = pEntry->nextPtr;
      }
   }
   //------------------------------------------------------------
   // <T>追加一个链表到当前链表中。</T>
   void Append(TIterator iterator){
      while(iterator.Next()){
         Push(*iterator);
      }
   }
   //------------------------------------------------------------
   // <T>将数据压入首位置。</T>
   void Unshift(T value){
      // 收集一个未使用得节点
      SEntry* pEntry = InnerEntryAlloc();
      // 将节点压入尾部
      pEntry->value = value;
      // 压入首节点
      InnerEntryUnshift(pEntry);
   }
   //------------------------------------------------------------
   // <T>弹出链表的首数据。</T>
   T Shift(){
      // 弹出首节点
      SEntry* pEntry = InnerEntryShift();
      MO_ASSERT(pEntry);
      // 获得内容
      T value = pEntry->value;
      // 释放节点
      InnerEntryFree(pEntry);
      return value;
   }
   //------------------------------------------------------------
   // <T>将数据压入尾位置。</T>
   void Push(T value){
      // 收集一个未使用得节点
      SEntry* pEntry = InnerEntryAlloc();
      // 设置数据内容
      pEntry->value = value;
      // 将节点压入尾部
      InnerEntryPush(pEntry);
   }
   //------------------------------------------------------------
   // <T>弹出链表的尾数据。</T>
   T Pop(){
      // 弹出尾节点
      SEntry* pEntry = InnerEntryPop();
      MO_ASSERT(pEntry);
      // 获得数据内容
      T value = pEntry->value;
      // 释放节点
      InnerEntryFree(pEntry);
      return value;
   }
   //------------------------------------------------------------
   // <T>从链表上删除数据。</T>
   void Remove(T value){
      SEntry* pEntry = _pFirst;
      while(NULL != pEntry){
         SEntry* pNext = pEntry->nextPtr;
         if(pEntry->value == value){
            InnerEntryRemove(pEntry);
            InnerEntryFree(pEntry);
         }
         pEntry = pNext;
      }
   }
   //------------------------------------------------------------
   // <T>使用排序器对集合对象进行排序</T>
   void Sort(HComparer hComparer, TAny* pParams = NULL){
      MO_ASSERT(hComparer);
      if(_count > 0){
         if(MO_CM_LIST_SORT_THRESHOLD > _count){
            RList<SEntry, T>::InsertSort(_pFirst, _pLast, hComparer, pParams);
         } else{
            RList<SEntry, T>::QuickSort(_pFirst, _pLast, hComparer, pParams);
         }
      }
   }
   //------------------------------------------------------------
   // <T>复制对象到外部数组。</T>
   TInt CopyTo(T* pBuffer, TInt capacity) const{
      TInt count = MO_LG_MIN(_count, capacity);
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
   //------------------------------------------------------------
   // <T>清空所有数据。</T>
   void Clear(){
      InnerClear();
   }
   //------------------------------------------------------------
   // <T>释放一个被使用的节点。</T>
   void Release(){
      InnerRelease();
   }