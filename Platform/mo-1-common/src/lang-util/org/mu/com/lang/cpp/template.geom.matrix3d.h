//============================================================
// <T>4x4{label}矩阵。</T>
//============================================================
struct MO_CM_DECLARE S{type_name}Matrix3d{
public:
   TBool dirty;
   {type} tx;
   {type} ty;
   {type} tz;
   {type} rx;
   {type} ry;
   {type} rz;
   {type} sx;
   {type} sy;
   {type} sz;
   {type} data[4][4];
public:
   //------------------------------------------------------------
   // <T>构造4x4{label}矩阵。</T>
   S{type_name}Matrix3d(){
      Identity();
   }
   //------------------------------------------------------------
   // <T>构造4x4{label}矩阵。</T>
   S{type_name}Matrix3d(const S{type_name}Matrix3d& matrix){
      Assign(matrix);
   }
public:
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE {type}C* MemoryC() const{
      return (const {type}*)data;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE {type}* Memory(){
      return ({type}*)data;
   }
public:
   //------------------------------------------------------------
   // <T>判断矩阵是否相等。</T>
   TBool EqualsData(const {type}* pData){
      TInt n = 0;
      for(TInt y = 0; y < 4; y++){
         for(TInt x = 0; x < 4; x++){
            if(data[y][x] != pData[n++]){
               return EFalse;
            }
         }
      }
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>判断矩阵是否相等。</T>
   MO_INLINE TBool Equals(S{type_name}Matrix3d* pMatrix){
      return EqualsData(pMatrix->MemoryC());
   }
public:
   //------------------------------------------------------------
   // <T>设置平移内容。</T>
   MO_INLINE void SetTranslate({type} x, {type} y, {type} z){
      tx = x;
      ty = y;
      tz = z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置平移内容。</T>
   MO_INLINE void SetTranslate(const S{type_name}Point3& value){
      tx = value.x;
      ty = value.y;
      tz = value.z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置旋转内容。</T>
   MO_INLINE void SetRotation({type} x, {type} y, {type} z){
      rx = x;
      ry = y;
      rz = z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置旋转内容。</T>
   MO_INLINE void SetRotation(const S{type_name}Vector3& value){
      rx = value.x;
      ry = value.y;
      rz = value.z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置缩放内容。</T>
   MO_INLINE void SetScale({type} x, {type} y, {type} z){
      sx = x;
      sy = y;
      sz = z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置缩放内容。</T>
   MO_INLINE void SetScale(const S{type_name}Vector3& value){
      sx = value.x;
      sy = value.y;
      sz = value.z;
      dirty = ETrue;
   }
public:
   //------------------------------------------------------------
   // <T>接收一个矩阵数据内容。</T>
   MO_INLINE void AssignData({type}C* pData){
      MO_ASSERT(pData);
      MO_LIB_MEMORY_COPY(data, sizeof(data), pData, sizeof(data));
   }
   //------------------------------------------------------------
   // <T>接收一个矩阵数据内容。</T>
   MO_INLINE void Assign(const S{type_name}Matrix3d& matrix){
      tx = matrix.tx;
      ty = matrix.ty;
      tz = matrix.tz;
      rx = matrix.rx;
      ry = matrix.ry;
      rz = matrix.rz;
      sx = matrix.sx;
      sy = matrix.sy;
      sz = matrix.sz;
      MO_LIB_MEMORY_COPY(data, sizeof(data), matrix.data, sizeof(data));
      dirty = EFalse;
   }
public:
   TResult AppendData({type}C* pData);
   TResult Append(const S{type_name}Matrix3d& matrix);
   TBool Invert();
   void UpdateForce();
   void Update();
public:
   void Identity();
   void Translate({type} x, {type} y, {type} z);
   void RotationX({type} value);
   void RotationY({type} value);
   void RotationZ({type} value);
   void Rotation({type} x, {type} y, {type} z);
   void Scale({type} x, {type} y, {type} z);
public:
   TResult Transform({type}* pOutput, {type}C* pInput, TInt count);
   S{type_name}Point3 TransformPoint3({type} x, {type} y, {type} z);
   S{type_name}Point3 TransformPoint3(const S{type_name}Point3& value);
   void TransformPoint3(S{type_name}Point3& output, const S{type_name}Point3& input);
   S{type_name}Vector3 TransformVector3({type} x, {type} y, {type} z);
   S{type_name}Vector3 TransformVector3(const S{type_name}Vector3& value);
   void TransformVector3(S{type_name}Vector3& output, const S{type_name}Vector3& input);
public:
   TResult SerializeData4x3(IDataOutput* pOutput, TBool transpose = EFalse);
   TResult UnserializeData4x3(IDataInput* pInput, TBool transpose = EFalse);
   TResult SerializeData4x4(IDataOutput* pOutput, TBool transpose = EFalse);
   TResult UnserializeData4x4(IDataInput* pInput, TBool transpose = EFalse);
   TResult Serialize(IDataOutput* pOutput);
   TResult Unserialize(IDataInput* pInput);
};