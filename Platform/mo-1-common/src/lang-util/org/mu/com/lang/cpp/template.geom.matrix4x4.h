//============================================================
// <T>4x4{label}矩阵。</T>
//============================================================
struct MO_CM_DECLARE S{type_name}Matrix4x4{
public:
   {type} data[4][4];
public:
   //------------------------------------------------------------
   // <T>构造4x4{label}矩阵。</T>
   S{type_name}Matrix4x4(){
      Identity();
   }
   //------------------------------------------------------------
   // <T>构造4x4{label}矩阵。</T>
   S{type_name}Matrix4x4(const S{type_name}Matrix4x4& matrix){
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
   MO_INLINE TBool Equals(S{type_name}Matrix4x4* pMatrix){
      return EqualsData(pMatrix->MemoryC());
   }
public:
   //------------------------------------------------------------
   // <T>单位化处理。</T>
   void Identity(){
      for(TInt y = 0; y < 4; y++){
         for(TInt x = 0; x < 4; x++){
            data[y][x] = (x == y) ? {default2} : {default};
         }
      }
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
   MO_INLINE void Assign(const S{type_name}Matrix4x4& matrix){
      MO_LIB_MEMORY_COPY(data, sizeof(data), matrix.data, sizeof(data));
   }
   //------------------------------------------------------------
   // <T>追加一个矩阵数据内容。</T>
   void AppendData({type}C* pData){
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
   MO_INLINE void Append(const S{type_name}Matrix4x4& matrix){
      AppendData(({type}C*)matrix.data);
   }
public:
   //------------------------------------------------------------
   // <T>变换一个三维顶点数据。</T>
   S{type_name}Point3 TransformPoint3(const S{type_name}Point3 point){
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
   TResult SerializeData(IDataOutput* pOutput);
   TResult UnserializeData(IDataInput* pInput);
};