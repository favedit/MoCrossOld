public:
   //------------------------------------------------------------
   // <T>接收一个指定的16位字符串。 </T>
   void Assign16(TChar16C* pValue, TInt length = -1){
      Clear();
      Append16(pValue, length);
   }
   //------------------------------------------------------------
   // <T>接收一个指定的32位字符串。 </T>
   void Assign32(TChar32C* pValue, TInt length = -1){
      Clear();
      Append32(pValue, length);
   }
   //------------------------------------------------------------
   // <T>接收一个指定的UTF8位字符串。 </T>
   void AssignUtf8(TChar16C* pValue, TInt length = -1){
      Clear();
      AppendUtf8(pValue, length);
   }
   //------------------------------------------------------------
   // <T>追加一个指定的16位字符串。 </T>
   void Append16(TChar16C* pValue, TInt length = -1){
      TInt size = RChar16::ConvertTo8(pValue);
      if(size > 0){
         EnsureSize(_length + size);
         RChar16::ConvertTo8({memory} + _length, size, pValue);
         _length += size;
      }
   }
   //------------------------------------------------------------
   // <T>追加一个指定的32位字符串。 </T>
   void Append32(TChar32C* pValue, TInt length){
      TInt size = RChar32::ConvertTo8(pValue);
      if(size > 0){
         EnsureSize(_length + size);
         RChar32::ConvertTo8({memory} + _length, size, pValue);
         _length += size;
      }
   }
   //------------------------------------------------------------
   // <T>追加一个指定的UTF8位字符串。 </T>
   void AppendUtf8(TChar16C* pValue, TInt length){
      TInt size = RChar16::ConvertToUtf8(pValue);
      if(size > 0){
         EnsureSize(_length + size);
         RChar16::ConvertToUtf8({memory} + _length, size, pValue);
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