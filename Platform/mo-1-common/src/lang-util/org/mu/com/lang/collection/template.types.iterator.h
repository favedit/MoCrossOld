public:
   //------------------------------------------------------------
   // <T>重置到开始位置。</T>
   MO_INLINE( void ResetFirst() ){
      _position = -1;
   }
   //------------------------------------------------------------
   // <T>重置到结束位置。</T>
   MO_INLINE( void ResetLast() ){
      _position = _{length};
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE( TBool HasNext() const ){
      TInt length = 0;
      if(NULL != _pLength){
         length = *_pLength;
      }
      if(length > 0){
         return (_position + 1) < length;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE( TBool HasPrior() const ){
      TInt length = 0;
      if(NULL != _pLength){
         length = *_pLength;
      }
      if(length > 0){
         return (_position - 1) >= 0;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>移动到下一个位置。</T>
   MO_INLINE( TBool Next() ){
      if((_position + 1) < _{length}){
         _position++;
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE( TBool Prior() ){
      if((_position - 1) >= 0){
         _position--;
         return ETrue;
      }
      return EFalse;
   }