//============================================================
// <T>四维{label}坐标。</T>
//============================================================
struct S{type_name}Point4{
public:
   {type} x;
   {type} y;
   {type} z;
   {type} w;
public:
   //------------------------------------------------------------
   // <T>构造三维{label}坐标。</T>
   S{type_name}Point4(){
      x = {default};
      y = {default};
      z = {default};
      w = {default};
   }
   //------------------------------------------------------------
   // <T>构造三维{label}坐标。</T>
   S{type_name}Point4({type} value){
      x = value;
      y = value;
      z = value;
      w = value;
   }
   //------------------------------------------------------------
   // <T>构造三维{label}坐标。</T>
   S{type_name}Point4({type} px, {type} py, {type} pz, {type} pw){
      x = px;
      y = py;
      z = pz;
      w = pw;
   }
   //------------------------------------------------------------
   // <T>构造三维{label}坐标。</T>
   S{type_name}Point4(const S{type_name}Point4& value){
      x = value.x;
      y = value.y;
      z = value.z;
      w = value.w;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool operator==(const S{type_name}Point4& value){
      return (x == value.x) && (y == value.y) && (z == value.z) && (w == value.w);
   }
   //------------------------------------------------------------
   // <T>判断是否不相等。</T>
   MO_INLINE TBool operator!=(const S{type_name}Point4& value){
      return (x != value.x) || (y != value.y) || (z != value.z) || (w != value.w);
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=({type} value){
      x = value;
      y = value;
      z = value;
      w = value;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=(const S{type_name}Point4& value){
      x = value.x;
      y = value.y;
      z = value.z;
      w = value.w;
   }
public:
   //------------------------------------------------------------
   // <T>相加处理。</T>
   MO_INLINE void operator+=({type} value){
      x += value;
      y += value;
      z += value;
      w += value;
   }
   //------------------------------------------------------------
   // <T>相加处理。</T>
   MO_INLINE void operator+=(const S{type_name}Point4& value){
      x += value.x;
      y += value.y;
      z += value.z;
      w += value.w;
   }
   //------------------------------------------------------------
   // <T>相减处理。</T>
   MO_INLINE void operator-=({type} value){
      x -= value;
      y -= value;
      z -= value;
      w -= value;
   }
   //------------------------------------------------------------
   // <T>相减处理。</T>
   MO_INLINE void operator-=(const S{type_name}Point4& value){
      x -= value.x;
      y -= value.y;
      z -= value.z;
      w -= value.z;
   }
   //------------------------------------------------------------
   // <T>相乘处理。</T>
   MO_INLINE void operator*=({type} value){
      x *= value;
      y *= value;
      z *= value;
      w *= value;
   }
   //------------------------------------------------------------
   // <T>相除处理。</T>
   MO_INLINE void operator/=({type} value){
      x /= value;
      y /= value;
      z /= value;
      w /= value;
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Set({type} value){
      x = value;
      y = value;
      z = value;
      w = value;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Set({type} px, {type} py, {type} pz, {type} pw){
      x = px;
      y = py;
      z = pz;
      w = pw;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Set(const S{type_name}Point4& value){
      x = value.x;
      y = value.y;
      z = value.z;
      w = value.w;
   }
public:
   //------------------------------------------------------------
   // <T>相加处理。</T>
   MO_INLINE void Add({type} value){
      x += value;
      y += value;
      z += value;
      w += value;
   }
   //------------------------------------------------------------
   // <T>相加处理。</T>
   MO_INLINE void Add(const S{type_name}Point4& value){
      x += value.x;
      y += value.y;
      z += value.z;
      w += value.w;
   }
   //------------------------------------------------------------
   // <T>相减处理。</T>
   MO_INLINE void Sub({type} value){
      x -= value;
      y -= value;
      z -= value;
      w -= value;
   }
   //------------------------------------------------------------
   // <T>相减处理。</T>
   MO_INLINE void Sub(const S{type_name}Point4& value){
      x -= value.x;
      y -= value.y;
      z -= value.z;
      w -= value.w;
   }
   //------------------------------------------------------------
   // <T>获得最小点处理。</T>
   MO_INLINE void MergeMin(const S{type_name}Point4& source, const S{type_name}Point4& target){
      x = MO_MIN(source.x, target.x);
      y = MO_MIN(source.y, target.y);
      z = MO_MIN(source.z, target.z);
      w = MO_MIN(source.w, target.w);
   }
   //------------------------------------------------------------
   // <T>获得最大点处理。</T>
   MO_INLINE void MergeMax(const S{type_name}Point4& source, const S{type_name}Point4& target){
      x = MO_MAX(source.x, target.x);
      y = MO_MAX(source.y, target.y);
      z = MO_MAX(source.z, target.z);
      w = MO_MAX(source.w, target.w);
   }
public:
   //------------------------------------------------------------
   // <T>获得绝对值。</T>
   TFloat Absolute(){
      return sqrt((x * x) + (y * y) + (z * z) + (w * w));
   }
   //------------------------------------------------------------
   // <T>单位化处理。</T>
   void Normalize(){
      TFloat v = Absolute();
      x /= v;
      y /= v;
      z /= v;
      w /= v;
   }
   //------------------------------------------------------------
   // <T>重置数据。</T>
   void Reset(){
      x = {default};
      y = {default};
      z = {default};
      w = {default};
   }
public:
   TBool Parse(TCharC* pValue);
   TCharC* Format(TChar* pBuffer, TInt capacity);
   TCharC* ToString(TChar* pBuffer, TInt capacity);
};