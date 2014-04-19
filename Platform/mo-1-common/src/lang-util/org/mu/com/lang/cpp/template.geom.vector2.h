//============================================================
// <T>二维{label}矢量。</T>
//============================================================
struct S{type_name}Vector2{
public:
   {type} x;
   {type} y;
public:
   //------------------------------------------------------------
   // <T>构造二维{label}矢量。</T>
   S{type_name}Vector2(){
      x = {default};
      y = {default};
   }
   //------------------------------------------------------------
   // <T>构造二维{label}矢量。</T>
   S{type_name}Vector2({type} value){
      x = value;
      y = value;
   }
   //------------------------------------------------------------
   // <T>构造二维{label}矢量。</T>
   S{type_name}Vector2({type} px, {type} py){
      x = px;
      y = py;
   }
   //------------------------------------------------------------
   // <T>构造二维{label}矢量。</T>
   S{type_name}Vector2(const S{type_name}Vector2& value){
      x = value.x;
      y = value.y;
   }
   //------------------------------------------------------------
   // <T>构造二维{label}矢量。</T>
   S{type_name}Vector2(const S{type_name}Point2& source, const S{type_name}Point2& target){
      x = source.x - target.x;
      y = source.y - target.y;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool operator ==(const S{type_name}Vector2& value){
      return ((x == value.x) && (y == value.y));
   }
   //------------------------------------------------------------
   // <T>判断是否不相等。</T>
   MO_INLINE TBool operator !=(const S{type_name}Vector2& value){
      return ((x != value.x) || (y != value.y));
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=({type} value){
      x = y = value;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=(const S{type_name}Vector2& value){
      x = value.x;
      y = value.y;
   }
public:
   //------------------------------------------------------------
   // <T>相加处理。</T>
   MO_INLINE void operator+=({type} value){
      x += value;
      y += value;
   }
   //------------------------------------------------------------
   // <T>相加处理。</T>
   MO_INLINE void operator+=(const S{type_name}Vector2& value){
      x += value.x;
      y += value.y;
   }
   //------------------------------------------------------------
   // <T>相减处理。</T>
   MO_INLINE void operator-=({type} value){
      x -= value;
      y -= value;
   }
   //------------------------------------------------------------
   // <T>相减处理。</T>
   MO_INLINE void operator-=(const S{type_name}Vector2& value){
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
      x /= value;
      y /= value;
   }
public:
   //------------------------------------------------------------
   {type} Absolute(){
      return sqrt((x * x) + (y * y));
   }
   //------------------------------------------------------------
   void Increase(S{type_name}Vector2& v){
      x += v.x;
      y += v.y;
   }
   //------------------------------------------------------------
   void Subtract(S{type_name}Vector2& v){
      x -= v.x;
      y -= v.y;
   }
   //------------------------------------------------------------
   void Normalize(){
      {type} value = Absolute();
      x /= value;
      y /= value;
   }
public:
   TBool Parse(TCharC* pValue);
   TCharC* Format(TChar* pBuffer, TInt capacity);
   TCharC* ToString(TChar* pBuffer, TInt capacity);
};