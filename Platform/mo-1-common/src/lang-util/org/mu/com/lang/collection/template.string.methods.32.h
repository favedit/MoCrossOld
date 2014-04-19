public:
   //------------------------------------------------------------
   // <T>接收一个指定的8位字符串。 </T>
   void Assign8(TChar8C* pValue, TInt length = -1){
      Clear();
      Append8(pValue, length);
   }
   //------------------------------------------------------------
   // <T>接收一个指定的32位字符串。 </T>
   void Assign16(TChar16C* pValue, TInt length = -1){
      Clear();
      Append16(pValue, length);
   }
   //------------------------------------------------------------
   // <T>追加一个指定的8位字符串。 </T>
   void Append8(TChar8C* pValue, TInt length){
      TInt size = RChar8::ConvertTo32(pValue);
      if(size > 0){
         EnsureSize(_length + size);
         RChar8::ConvertTo32({memory} + _length, size, pValue);
         _length += size;
      }
   }
   //------------------------------------------------------------
   // <T>追加一个指定的16位字符串。 </T>
   void Append16(TChar16C* pValue, TInt length){
      TInt size = RChar16::ConvertTo32(pValue);
      if(size > 0){
         EnsureSize(_length + size);
         RChar16::ConvertTo32({memory} + _length, size, pValue);
         _length += size;
      }
   }
   //------------------------------------------------------------
   // <T>重复追加一个字符串到末尾。</T>
   void AppendRepeat(const {char}* pValue, TInt count){
      for(TInt n = 0; n < count; n++){
         Append(pValue);
      }
   }
   //------------------------------------------------------------
   // <T>追加一个空行。</T>
   void AppendLine(){
      Push('\n');
   }