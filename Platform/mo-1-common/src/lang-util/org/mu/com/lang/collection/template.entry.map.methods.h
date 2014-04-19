public:
   //------------------------------------------------------------
   // <T>当前哈希集合对象是否为空。</T>
   MO_INLINE( TBool IsEmpty() const ){
      return (0 == _count);
   }
   //------------------------------------------------------------
   // <T>获得数据个数。</T>
   MO_INLINE( TInt Count() const ){
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得首位置的只读链表迭代器。</T>
   MO_INLINE( TIterator IteratorC() ){
      return TIterator(&_atom, _pEntryAllocator, _pFirst);
   }
   //------------------------------------------------------------
   // <T>获得首位置的链表迭代器。</T>
   MO_INLINE( TIterator Iterator() ){
      return TIterator(&_atom, _pEntryAllocator, _pFirst);
   }
public:
   //------------------------------------------------------------
   // <T>判断指定名称是否存在。</T>
   TBool Contains(const {name_type}* pName) const{
      return (NULL != InnerFindByName(pName));
   }
   //------------------------------------------------------------
   // <T>判断指定内容是否存在。</T>
   TBool ContainsValue(const {value_type} value) const{
      return (NULL != InnerFindByValue(value));
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   const V Find(const {name_type}* pName) const{
      SEntry* pEntry = InnerFindByName(pName);
      if(NULL == pEntry){
         return (V)NULL;
      }
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   const V GetC(const {name_type}* pName) const{
      SEntry* pEntry = InnerFindByName(pName);
      MO_ASSERT(pEntry);
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   const V GetC(const {name_type}* pName, const {value_type} value) const{
      SEntry* pEntry = InnerFindByName(pName);
      if(NULL == pEntry){
         return value;
      }
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   V& Get(const {name_type}* pName){
      SEntry* pEntry = InnerFindByName(pName);
      MO_ASSERT(pEntry);
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得指定数据的首个名称。</T>
   const {name_type}* SearchFirst(const {value_type} value) const{
      SEntry* pEntry = InnerFindByValue(value);
      MO_ASSERT(pEntry);
      return pEntry->namePtr->MemoryC();
   }
   //------------------------------------------------------------
   // <T>获得指定数据的首个名称。</T>
   const {name_type}* SearchFirst(const {value_type} value, const {name_type}* pName) const{
      SEntry* pEntry = InnerFindByValue(value);
      if(NULL == pEntry){
         return pName;
      }
      return pEntry->namePtr->MemoryC();
   }
   //------------------------------------------------------------
   // <T>获得指定数据的个数。</T>
   TInt SearchCount(const {value_type} value) const{
      TInt count = 0;
      if(_count > 0){
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->value == value){
               count++;
            }
            pEntry = pEntry->nextPtr;
         }
      }
      return count;
   }
   //------------------------------------------------------------
   // <T>获得指定数据的名称集合。</T>
   TInt Search(const V value, const {name_type}** ppNames, TInt capacity) const{
      TInt count = 0;
      if(_count > 0){
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->value == value){
               MO_ASSERT(count < capacity);
               ppNames[count++] = pEntry->namePtr->MemoryC();
            }
            pEntry = pEntry->nextPtr;
         }
      }
      return count;
   }
public:
   //------------------------------------------------------------
   // <T>确保当前对象可以容纳指定大小的数据。</T>
   // 当哈希表是旧表的8倍时开始扩充，扩大2倍。</P>
   void EnsureSize(TInt size = MO_OBJECT_CAPACITY){
      if(NULL == _ppEntities) {
         _count = MO_LG_MAX(size, MO_OBJECT_CAPACITY);
         // 第一次新建时，生成哈希表
         _ppEntities = _pMemoryAllocator->Alloc(_count);
         RTypes<SEntry*>::Clear(_ppEntities, _count);
      } else if (size > (_count << 3)) {
         // 扩充内存时处理
         TInt capacity = _count + ((MO_LG_MAX(_count, size)) >> 1);
         // 当总数大于节点列表长度8倍时，重新扩充节点列表
         SEntry** ppEntries = _pMemoryAllocator->Alloc(capacity);
         RTypes<SEntry*>::Clear(_ppEntities, capacity);
         // 循环取出旧的节点列表内容，移到新的节点列表上
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            // 将当前节点存储到新建的节点列表上去
            TInt index = pEntry->hash % capacity;
            pEntry->linkPtr = ppEntries[index];
            ppEntries[index] = pEntry;
            // 获得保存的下一个节点指针
            pEntry = pEntry->nextPtr;
         }
         // 释放旧节点内存
         _pMemoryAllocator->Free(_ppEntities);
         // 保存新的节点列表
         _ppEntities = ppEntries;
         _count = capacity;
      }
   }
   //------------------------------------------------------------
   // <T>接收源哈希表内的全部数据。</T>
   void Assign(const MDictionary{type}<{value_type}>& attributes){
      Clear();
      Append(attributes);
   }
   //------------------------------------------------------------
   // <T>追加源哈希表内的全部数据。</T>
   void Append(const MDictionary{type}<T, V>& attributes){
      SEntry* pEntry = attributes._pFirst;
      while(NULL != pEntry){
         Set(pEntry->namePtr->MemoryC(), pEntry->value);
         pEntry = pEntry->nextPtr;
      }
   }
   //------------------------------------------------------------
   // <T>根据名称设置数据。</T>
   void Set(const {name_type}* pName, {value_type} value){
      THashCode hash = MakeHashCode(pName);
      TInt index = hash % _count;
      // 查找数据出现的位置
      SEntry* pEntry = _ppEntities[index];
      while(NULL != pEntry) {
         if(pEntry->hash == hash){
            if(pEntry->IsName(pName)){
               pEntry->value = value;
               return;
            }
         }
         pEntry = pEntry->linkPtr;
      }
      // 检查是否需要扩展内存
      EnsureSize(_count + 1);
      // 如果名称不存在，创建新入口对象
      pEntry = InnerAllocEntry();
      index = hash % _count;
      pEntry->hash = hash;
      pEntry->linkPtr = _ppEntities[index];
      pEntry->namePtr->Assign(pName);
      pEntry->value = value;
      // 追加到链表尾部
      _ppEntities[index] = pEntry;
      _atom.EntryPush(pEntry);
   }
   //------------------------------------------------------------
   // <T>移除指定名称的数据。</T>
   V Remove(const T* pName){
      V value = NULL;
      // 计算哈希值
      THashCode hash = RTypeChar<T>::MakeHashCode(pName);
      TInt index = hash % _count;
      // 查找数据出现的位置
      SEntry* pPrior = _ppEntities[index];
      SEntry* pEntry = pPrior;
      while(NULL != pEntry){
         if(pEntry->hash == hash){
            if(pEntry->IsName(pName)){
               // 设置输出内容
               value = pEntry->value;
               // 从链表上移除节点
               if(pEntry == pPrior){
                  // 当前对象是第一个对象时
                  _ppEntities[index] = pEntry->linkPtr;
               }else{
                  // 当前对象不是第一个对象时
                  pPrior->linkPtr = pEntry->linkPtr;
               }
               // 删除当前节点
               _atom.EntryDelete(pEntry);
               break;
            }
         }
         pPrior = pEntry;
         pEntry = pEntry->linkPtr;
      }
      return value;
   }
   //------------------------------------------------------------
   // <T>清除资源。</T>
   void Clear(){
      // 释放节点
      if(_count > 0){
         InnerEntryFree(_pFirst, _pLast);
      }
      // 重置数据
      InnerClear();
   }
   //------------------------------------------------------------
   // <T>释放所有资源。</T>
   void Release(){
      // 释放节点
      if(_count > 0){
         InnerEntryFree(_pFirst, _pLast);
      }
      // 删除所有节点
      InnerDelete(_pFree, ETrue);
      // 释放节点数组
      InnerFree(_ppEntities);
   }
public:
   //------------------------------------------------------------
   // <T>获得对象运行时信息。</T>
   void Dump(MString{type} dump) const{
      dump.Append(TC("MDictionary{type}{"));
      //dump.AppendFormat("%d", _count);
      if(_count > 0){
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            //dump.Append(*pEntry->pName);
            dump.Append(TC("="));
            //dump.Append(*pEntry->pValue);
            // 查找下一个
            pEntry = pEntry->nextPtr;
            if(NULL != pEntry){
               dump.Append(',');
            }
         }
      }
   }