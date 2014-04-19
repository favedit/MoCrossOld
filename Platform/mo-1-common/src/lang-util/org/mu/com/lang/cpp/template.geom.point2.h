//============================================================
// <T>二维{label}坐标。</T>
//============================================================
struct MO_CM_DECLARE S{type_name}Point2{
public:
   {type} x;
   {type} y;
public:
   //------------------------------------------------------------
   // <T>构造二维{label}坐标。</T>
   S{type_name}Point2(){
      x = {default};
      y = {default};
   }
   //------------------------------------------------------------
   // <T>构造二维{label}坐标。</T>
   S{type_name}Point2({type} value){
      x = value;
      y = value;
   }
   //------------------------------------------------------------
   // <T>构造二维{label}坐标。</T>
   S{type_name}Point2({type} px, {type} py){
      x = px;
      y = py;
   }
   //------------------------------------------------------------
   // <T>构造二维{label}坐标。</T>
   S{type_name}Point2(const S{type_name}Point2& value){
      x = value.x;
      y = value.y;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool operator==(const S{type_name}Point2& value){
      return (x == value.x) && (y == value.y);
   }
   //------------------------------------------------------------
   // <T>判断是否不相等。</T>
   MO_INLINE TBool operator!=(const S{type_name}Point2& value){
      return (x != value.x) || (y != value.y);
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=({type} value){
      x = value;
      y = value;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=(const S{type_name}Point2& value){
      x = value.x;
      y = value.y;
   }
public:
   //------------------------------------------------------------
   // <T>相加处理。</T>
   MO_INLINE void operator+=(const S{type_name}Point2& value){
      x += value.x;
      y += value.y;
   }
   //------------------------------------------------------------
   // <T>相减处理。</T>
   MO_INLINE void operator-=(const S{type_name}Point2& value){
      x -= value.x;
      y -= value.y;
   }
   //------------------------------------------------------------
   // <T>相乘处理。</T>
   MO_INLINE void operator*=({type} value){
      x *= value;
      y *= value;
   }
   //------------------------------------------------------------
   // <T>相除处理。</T>
   MO_INLINE void operator/=({type} value){
      MO_ASSERT({default} != value);
      x /= value;
      y /= value;
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Set({type} value){
      x = value;
      y = value;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Set({type} px, {type} py){
      x = px;
      y = py;
   }
public:
   TBool Parse(TCharC* pValue);
   TCharC* Format(TChar* pBuffer, TInt capacity);
   TCharC* ToString(TChar* pBuffer, TInt capacity);
};