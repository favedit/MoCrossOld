public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE( TBool IsEmpty() ){
      return (NULL == _pEntry);
   }
   //------------------------------------------------------------
   // <T>重置到开始位置。</T>
   MO_INLINE( void Reset() ){
      MO_CLEAR(_pEntry);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE( TBool HasNext() ){
      return (NULL == _pEntry) ? (NULL != _pFirst) : (NULL != _pEntry->nextPtr);
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE( TBool HasPrior() ){
      return (NULL == _pEntry) ? (NULL != _pLast) : (NULL != _pEntry->priorPtr);
   }
   //------------------------------------------------------------
   //<T>移动到下一个位置。</T>
   MO_INLINE( TBool Next() ){
      if(NULL == _pEntry){
         _pEntry = _pFirst;
      }else{
         _pEntry = _pEntry->nextPtr;
      }
      return (NULL != _pEntry);
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE( TBool Prior() ){
      if(NULL == _pEntry){
         _pEntry = _pLast;
      }else{
         _pEntry = _pEntry->priorPtr;
      }
      return (NULL != _pEntry);
   }