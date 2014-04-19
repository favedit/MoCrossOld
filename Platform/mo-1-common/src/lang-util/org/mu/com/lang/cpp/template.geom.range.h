//============================================================
// <T>范围。</T>
//============================================================
struct MO_CM_DECLARE S{type_name}Range{
public:
   {type} min;
   {type} max;
public:
   //------------------------------------------------------------
   // <T>构造范围。</T>
   S{type_name}Range(){
      min = {default};
      max = {default};
   }
   //------------------------------------------------------------
   // <T>构造范围。</T>
   S{type_name}Range({type} value){
      min = value;
      max = value;
   }
   //------------------------------------------------------------
   // <T>构造范围。</T>
   S{type_name}Range({type} minValue, {type} maxValue){
      min = minValue;
      max = maxValue;
   }
   //------------------------------------------------------------
   // <T>构造范围。</T>
   S{type_name}Range(const S{type_name}Range& value){
      min = value.min;
      max = value.max;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool operator==(const S{type_name}Range& value){
      return (min == value.min) && (max == value.max);
   }
   //------------------------------------------------------------
   // <T>判断是否不相等。</T>
   MO_INLINE TBool operator!=(const S{type_name}Range& value){
      return (min != value.min) || (max != value.max);
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=({type} value){
      min = value;
      max = value;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=(const S{type_name}Range& value){
      min = value.min;
      max = value.max;
   }
public:
   //------------------------------------------------------------
   // <T>相加处理。</T>
   MO_INLINE void operator+=(const S{type_name}Range& value){
      min += value.min;
      max += value.max;
   }
   //------------------------------------------------------------
   // <T>相减处理。</T>
   MO_INLINE void operator-=(const S{type_name}Range& value){
      min -= value.min;
      max -= value.max;
   }
   //------------------------------------------------------------
   // <T>相乘处理。</T>
   MO_INLINE void operator*=({type} value){
      min *= value;
      max *= value;
   }
   //------------------------------------------------------------
   // <T>相除处理。</T>
   MO_INLINE void operator/=({type} value){
      MO_ASSERT(0 != value);
      min /= value;
      max /= value;
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Set({type} value){
      min = value;
      max = value;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Set({type} minValue, {type} maxValue){
      min = minValue;
      max = maxValue;
   }
public:
   TBool Parse(TCharC* pValue);
   TCharC* Format(TChar* pBuffer, TInt capacity);
   TCharC* ToString(TChar* pBuffer, TInt capacity);
};