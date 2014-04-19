//============================================================
// <T>四元数。</T>
//============================================================
struct S{type_name}Quaternion{
public:
   {type} x;
   {type} y;
   {type} z;
   {type} w;
public:
   //------------------------------------------------------------
   // <T>构造四元数。</T>
   S{type_name}Quaternion(){
      x = 0;
      y = 0;
      z = 0;
      w = 1;
   }
   //------------------------------------------------------------
   // <T>构造四元数。</T>
   S{type_name}Quaternion({type} value){
      x = value;
      y = value;
      z = value;
      w = value;
   }
   //------------------------------------------------------------
   // <T>构造四元数。</T>
   S{type_name}Quaternion(const S{type_name}Quaternion& quaternion){
      x = quaternion.x;
      y = quaternion.y;
      z = quaternion.z;
      w = quaternion.w;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool operator==(const S{type_name}Quaternion& quaternion){
      return (x==quaternion.x)&&(y==quaternion.y)&&(z==quaternion.z)&&(w==quaternion.w);
   }
   //------------------------------------------------------------
   // <T>判断是否不相等。</T>
   MO_INLINE TBool operator!=(const S{type_name}Quaternion& quaternion){
      return (x!=quaternion.x)||(y!=quaternion.y)||(z!=quaternion.z)||(w!=quaternion.w);
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
   MO_INLINE void operator=(const S{type_name}Quaternion& quaternion){
      x = quaternion.x;
      y = quaternion.y;
      z = quaternion.z;
      w = quaternion.w;
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
   MO_INLINE void operator+=(const S{type_name}Quaternion& quaternion){
      x += quaternion.x;
      y += quaternion.y;
      z += quaternion.z;
      w += quaternion.w;
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
   MO_INLINE void operator-=(const S{type_name}Quaternion& quaternion){
      x -= quaternion.x;
      y -= quaternion.y;
      z -= quaternion.z;
      w -= quaternion.w;
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
      this->x = value;
      this->y = value;
      this->z = value;
      this->w = value;
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
   MO_INLINE void Set(const S{type_name}Quaternion& quaternion){
      x = quaternion.x;
      y = quaternion.y;
      z = quaternion.z;
      w = quaternion.w;
   }
public:
   //------------------------------------------------------------
   // <T>获得绝对值。</T>
   MO_INLINE {type} Absolute(){
      return sqrt((x * x) + (y * y) + (z * z) + (w * w));
   }
   //------------------------------------------------------------
   MO_INLINE void Normalize(){
      {type} v = Absolute();
      x /= v;
      y /= v;
      z /= v;
      w /= v;
   }
   //------------------------------------------------------------
   MO_INLINE void Increase(S{type_name}Quaternion& v){
      x += v.x;
      y += v.y;
      z += v.z;
      w += v.w;
   }
   //------------------------------------------------------------
   MO_INLINE void Subtract(S{type_name}Quaternion& v){
      x -= v.x;
      y -= v.y;
      z -= v.z;
      w -= v.w;
   }
   //------------------------------------------------------------
   MO_INLINE S{type_name}Quaternion Multiply(S{type_name}Quaternion& v){
      S{type_name}Quaternion r;
      r.x = (w * v.x) + (x * v.w) + (y * v.z) - (z * v.y);
      r.y = (w * v.y) + (y * v.w) + (z * v.x) - (x * v.z);
      r.z = (w * v.z) + (z * v.w) + (x * v.y) - (y * v.x);
      r.w = (w * v.w) - (x * v.x) - (y * v.y) - (z * v.z);
      return r;
   }
   //------------------------------------------------------------
   MO_INLINE void ToVector3(S{type_name}Vector3* pValue){
      pValue->x = x / w;
      pValue->y = y / w;
      pValue->z = z / w;
   }
};