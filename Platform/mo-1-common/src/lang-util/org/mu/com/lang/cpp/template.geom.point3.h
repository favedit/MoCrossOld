//============================================================
// <T>三维{label}坐标。</T>
//============================================================
struct S{type_name}Point3{
public:
   {type} x;
   {type} y;
   {type} z;
public:
   //------------------------------------------------------------
   // <T>构造三维{label}坐标。</T>
   S{type_name}Point3(){
      x = {default};
      y = {default};
      z = {default};
   }
   //------------------------------------------------------------
   // <T>构造三维{label}坐标。</T>
   S{type_name}Point3({type} value){
      x = value;
      y = value;
      z = value;
   }
   //------------------------------------------------------------
   // <T>构造三维{label}坐标。</T>
   S{type_name}Point3({type} px, {type} py, {type} pz){
      x = px;
      y = py;
      z = pz;
   }
   //------------------------------------------------------------
   // <T>构造三维{label}坐标。</T>
   S{type_name}Point3(const S{type_name}Point3& value){
      x = value.x;
      y = value.y;
      z = value.z;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool operator==(const S{type_name}Point3& value){
      return (x == value.x) && (y == value.y) && (z == value.z);
   }
   //------------------------------------------------------------
   // <T>判断是否不相等。</T>
   MO_INLINE TBool operator!=(const S{type_name}Point3& value){
      return (x != value.x) || (y != value.y) || (z != value.z);
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=({type} value){
      x = value;
      y = value;
      z = value;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=(const S{type_name}Point3& value){
      x = value.x;
      y = value.y;
      z = value.z;
   }
public:
   //------------------------------------------------------------
   // <T>相加处理。</T>
   MO_INLINE void operator+=({type} value){
      x += value;
      y += value;
      z += value;
   }
   //------------------------------------------------------------
   // <T>相加处理。</T>
   MO_INLINE void operator+=(const S{type_name}Point3& value){
      x += value.x;
      y += value.y;
      z += value.z;
   }
   //------------------------------------------------------------
   // <T>相减处理。</T>
   MO_INLINE void operator-=({type} value){
      x -= value;
      y -= value;
      z -= value;
   }
   //------------------------------------------------------------
   // <T>相减处理。</T>
   MO_INLINE void operator-=(const S{type_name}Point3& value){
      x -= value.x;
      y -= value.y;
      z -= value.z;
   }
   //------------------------------------------------------------
   // <T>相乘处理。</T>
   MO_INLINE void operator*=({type} value){
      x *= value;
      y *= value;
      z *= value;
   }
   //------------------------------------------------------------
   // <T>相除处理。</T>
   MO_INLINE void operator/=({type} value){
      x /= value;
      y /= value;
      z /= value;
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Set({type} xValue = {default}, {type} yValue = {default}, {type} zValue = {default}){
      x = xValue;
      y = yValue;
      z = zValue;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Assign(const S{type_name}Point3& value){
      x = value.x;
      y = value.y;
      z = value.z;
   }
public:
   //------------------------------------------------------------
   // <T>相加处理。</T>
   MO_INLINE void Add({type} value){
      x += value;
      y += value;
      z += value;
   }
   //------------------------------------------------------------
   // <T>相加处理。</T>
   MO_INLINE void Add(const S{type_name}Point3& value){
      x += value.x;
      y += value.y;
      z += value.z;
   }
   //------------------------------------------------------------
   // <T>相减处理。</T>
   MO_INLINE void Sub({type} value){
      x -= value;
      y -= value;
      z -= value;
   }
   //------------------------------------------------------------
   // <T>相减处理。</T>
   MO_INLINE void Sub(const S{type_name}Point3& value){
      x -= value.x;
      y -= value.y;
      z -= value.z;
   }
   //------------------------------------------------------------
   // <T>获得最小点处理。</T>
   MO_INLINE void MergeMin(const S{type_name}Point3& source, const S{type_name}Point3& target){
      x = MO_MIN(source.x, target.x);
      y = MO_MIN(source.y, target.y);
      z = MO_MIN(source.z, target.z);
   }
   //------------------------------------------------------------
   // <T>获得最大点处理。</T>
   MO_INLINE void MergeMax(const S{type_name}Point3& source, const S{type_name}Point3& target){
      x = MO_MAX(source.x, target.x);
      y = MO_MAX(source.y, target.y);
      z = MO_MAX(source.z, target.z);
   }
public:
   //------------------------------------------------------------
   // <T>获得绝对值。</T>
   TFloat Absolute(){
      return sqrt((x * x) + (y * y) + (z * z));
   }
   //------------------------------------------------------------
   // <T>单位化处理。</T>
   void Normalize(){
      TFloat v = Absolute();
      x /= v;
      y /= v;
      z /= v;
   }
   //------------------------------------------------------------
   // <T>重置数据。</T>
   void Reset(){
      x = {default};
      y = {default};
      z = {default};
   }
public:
   TBool Parse(TCharC* pValue);
   TCharC* Format(TChar* pBuffer, TInt capacity);
   TCharC* ToString(TChar* pBuffer, TInt capacity);
};