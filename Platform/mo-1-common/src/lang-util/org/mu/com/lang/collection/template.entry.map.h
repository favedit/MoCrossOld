protected:
   //------------------------------------------------------------
   // <T>查找指定名称的索引位置。</T>
   MO_INLINE( SEntry* InnerFindByName(const {char}* pName) const ){
      // 数据存在时
      if(_count > 0){
         THashCode hash = RChar{type}::MakeHashCode(pName);
         TInt index = hash % _entryCount;
         // 查找名称的索引位置
         SEntry* pEntry = _ppEntities[index];
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
   MO_INLINE( SEntry* InnerFindByValue(const V value) const ){
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