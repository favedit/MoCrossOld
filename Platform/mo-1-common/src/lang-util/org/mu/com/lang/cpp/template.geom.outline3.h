//============================================================
// <T>4x4{label}矩阵。</T>
//============================================================
struct S{type_name}Outline3{
public:
   S{type_name}Point3 min;
   S{type_name}Point3 max;
public:
   //------------------------------------------------------------
   // <T>构造4x4{label}矩阵。</T>
   S{type_name}Matrix3d(){
      Identity();
   }
public:
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE const {type}* MemoryC() const{
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
   TBool Equals(S{type_name}Matrix3d* pMatrix){
      return EqualsData(pMatrix->MemoryC());
   }
public:
   //------------------------------------------------------------
   // <T>单位化处理。</T>
   void Identity(){
      tx = ty = tz = {default};
      rx = ry = rz = {default};
      sx = sy = sz = {default2};
      for(TInt y = 0; y < 4; y++){
         for(TInt x = 0; x < 4; x++){
            data[y][x] = (x == y) ? {default2} : {default};
         }
      }
   }
   //------------------------------------------------------------
   // <T>设置平移内容。</T>
   MO_INLINE void Translate({type} x, {type} y, {type} z){
      tx = x;
      ty = y;
      tz = z;
   }
   //------------------------------------------------------------
   // <T>设置旋转内容。</T>
   MO_INLINE void Rotation({type} x, {type} y, {type} z){
      rx = x;
      ry = y;
      rz = z;
   }
   //------------------------------------------------------------
   // <T>设置缩放内容。</T>
   MO_INLINE void Scale({type} x, {type} y, {type} z){
      sx = x;
      sy = y;
      sz = z;
   }   
   //------------------------------------------------------------
   // <T>计算结果到矩阵内。</T>
   void Update(){
      {type} rsx = sin(rx);
      {type} rcx = cos(rx);
      {type} rsy = sin(ry);
      {type} rcy = cos(ry);
      {type} rsz = sin(rz);
      {type} rcz = cos(rz);
      data[0][0] = rcy * rcz * sx;
      data[0][1] = rcy * rsz * sx;
      data[0][2] = -rsy * sx;
      data[0][3] = {default};
      data[1][0] = (rsx * rsy * rcz - rcx * rsz) * sy;
      data[1][1] = (rsx * rsy * rsz + rcx * rcz) * sy;
      data[1][2] = rsx * rcy * sy;
      data[1][3] = {default};
      data[2][0] = (rcx * rsy * rcz + rsx * rsz) * sz;
      data[2][1] = (rcx * rsy * rsz - rsx * rcz) * sz;
      data[2][2] = rcx * rcy * sz;
      data[2][3] = {default};
      data[3][0] = tx;
      data[3][1] = ty;
      data[3][2] = tz;
      data[3][3] = {default2};
   }
public:
   //------------------------------------------------------------
   // <T>接收一个矩阵数据内容。</T>
   void AssignData(const {type}* pData){
      TInt n = 0;
      for(TInt y = 0; y < 4; y++){
         for(TInt x = 0; x < 4; x++){
            data[y][x] = pData[n++];
         }
      }
   }
   //------------------------------------------------------------
   // <T>接收一个矩阵数据内容。</T>
   void Assign(S{type_name}Matrix3d* pMatrix){
      MO_ASSERT(pMatrix);
      tx = pMatrix->tx;
      ty = pMatrix->ty;
      tz = pMatrix->tz;
      rx = pMatrix->rx;
      ry = pMatrix->ry;
      rz = pMatrix->rz;
      sx = pMatrix->sx;
      sy = pMatrix->sy;
      sz = pMatrix->sz;
      MO_LIB_MEMORY_COPY(data, sizeof(data), pMatrix->data, sizeof(data));
   }
   //------------------------------------------------------------
   // <T>追加一个矩阵数据内容。</T>
   void AppendData(TFloat* pData){
      // 矩阵计算
      {type} v00 = (data[0][0] * pData[0]) + (data[0][1] * pData[4]) + (data[0][2] * pData[ 8]) + (data[0][3] * pData[12]);
      {type} v01 = (data[0][0] * pData[1]) + (data[0][1] * pData[5]) + (data[0][2] * pData[ 9]) + (data[0][3] * pData[13]);
      {type} v02 = (data[0][0] * pData[2]) + (data[0][1] * pData[6]) + (data[0][2] * pData[10]) + (data[0][3] * pData[14]);
      {type} v03 = (data[0][0] * pData[3]) + (data[0][1] * pData[7]) + (data[0][2] * pData[11]) + (data[0][3] * pData[15]);
      {type} v04 = (data[1][0] * pData[0]) + (data[1][1] * pData[4]) + (data[1][2] * pData[ 8]) + (data[1][3] * pData[12]);
      {type} v05 = (data[1][0] * pData[1]) + (data[1][1] * pData[5]) + (data[1][2] * pData[ 9]) + (data[1][3] * pData[13]);
      {type} v06 = (data[1][0] * pData[2]) + (data[1][1] * pData[6]) + (data[1][2] * pData[10]) + (data[1][3] * pData[14]);
      {type} v07 = (data[1][0] * pData[3]) + (data[1][1] * pData[7]) + (data[1][2] * pData[11]) + (data[1][3] * pData[15]);
      {type} v08 = (data[2][0] * pData[0]) + (data[2][1] * pData[4]) + (data[2][2] * pData[ 8]) + (data[2][3] * pData[12]);
      {type} v09 = (data[2][0] * pData[1]) + (data[2][1] * pData[5]) + (data[2][2] * pData[ 9]) + (data[2][3] * pData[13]);
      {type} v10 = (data[2][0] * pData[2]) + (data[2][1] * pData[6]) + (data[2][2] * pData[10]) + (data[2][3] * pData[14]);
      {type} v11 = (data[2][0] * pData[3]) + (data[2][1] * pData[7]) + (data[2][2] * pData[11]) + (data[2][3] * pData[15]);
      {type} v12 = (data[3][0] * pData[0]) + (data[3][1] * pData[4]) + (data[3][2] * pData[ 8]) + (data[3][3] * pData[12]);
      {type} v13 = (data[3][0] * pData[1]) + (data[3][1] * pData[5]) + (data[3][2] * pData[ 9]) + (data[3][3] * pData[13]);
      {type} v14 = (data[3][0] * pData[2]) + (data[3][1] * pData[6]) + (data[3][2] * pData[10]) + (data[3][3] * pData[14]);
      {type} v15 = (data[3][0] * pData[3]) + (data[3][1] * pData[7]) + (data[3][2] * pData[11]) + (data[3][3] * pData[15]);
      // 复制内容
      data[0][0] = v00;
      data[0][1] = v01;
      data[0][2] = v02;
      data[0][3] = v03;
      data[1][0] = v04;
      data[1][1] = v05;
      data[1][2] = v06;
      data[1][3] = v07;
      data[2][0] = v08;
      data[2][1] = v09;
      data[2][2] = v10;
      data[2][3] = v11;
      data[3][0] = v12;
      data[3][1] = v13;
      data[3][2] = v14;
      data[3][3] = v15;
   }
   //------------------------------------------------------------
   // <T>追加一个矩阵数据内容。</T>
   void Append(S{type_name}Matrix3d* pMatrix){
      AppendData((TFloat*)pMatrix->data);
   }
public:
   //------------------------------------------------------------
   // <T>变换一个三维顶点数据。</T>
   S{type_name}Point3 TransformPoint3(S{type_name}Point3 point){
      S{type_name}Point3 result;
      result.x = (point.x * data[0][0]) + (point.y * data[1][0]) +(point.z * data[2][0]) + data[3][0];
      result.y = (point.x * data[0][1]) + (point.y * data[1][1]) +(point.z * data[2][1]) + data[3][1];
      result.z = (point.x * data[0][2]) + (point.y * data[1][2]) +(point.z * data[2][2]) + data[3][2];
      return result;
   }
   //------------------------------------------------------------
   // <T>变换一个三维顶点数据。</T>
   S{type_name}Point3 TransformPoint3({type} x, {type} y, {type} z){
      S{type_name}Point3 result;
      result.x = (x * data[0][0]) + (y * data[1][0]) +(z * data[2][0]) + data[3][0];
      result.y = (x * data[0][1]) + (y * data[1][1]) +(z * data[2][1]) + data[3][1];
      result.z = (x * data[0][2]) + (y * data[1][2]) +(z * data[2][2]) + data[3][2];
      return result;
   }
public:
   TResult Serialize(IDataOutput* pOutput);
   TResult Unserialize(IDataInput* pInput);
};