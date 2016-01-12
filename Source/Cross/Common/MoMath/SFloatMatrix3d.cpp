#include "MoMtMatrix.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>追加一个矩阵数据内容。</T>
//
// @param pData 数据
// @return 处理结果
//============================================================
TResult SFloatMatrix3d::AppendData(TFloatC* pData){
   // 检查参数
   MO_CHECK(pData, return ENull);
   // 矩阵计算
   TFloat v00 = (data[0][0] * pData[0]) + (data[0][1] * pData[4]) + (data[0][2] * pData[ 8]) + (data[0][3] * pData[12]);
   TFloat v01 = (data[0][0] * pData[1]) + (data[0][1] * pData[5]) + (data[0][2] * pData[ 9]) + (data[0][3] * pData[13]);
   TFloat v02 = (data[0][0] * pData[2]) + (data[0][1] * pData[6]) + (data[0][2] * pData[10]) + (data[0][3] * pData[14]);
   TFloat v03 = (data[0][0] * pData[3]) + (data[0][1] * pData[7]) + (data[0][2] * pData[11]) + (data[0][3] * pData[15]);
   TFloat v04 = (data[1][0] * pData[0]) + (data[1][1] * pData[4]) + (data[1][2] * pData[ 8]) + (data[1][3] * pData[12]);
   TFloat v05 = (data[1][0] * pData[1]) + (data[1][1] * pData[5]) + (data[1][2] * pData[ 9]) + (data[1][3] * pData[13]);
   TFloat v06 = (data[1][0] * pData[2]) + (data[1][1] * pData[6]) + (data[1][2] * pData[10]) + (data[1][3] * pData[14]);
   TFloat v07 = (data[1][0] * pData[3]) + (data[1][1] * pData[7]) + (data[1][2] * pData[11]) + (data[1][3] * pData[15]);
   TFloat v08 = (data[2][0] * pData[0]) + (data[2][1] * pData[4]) + (data[2][2] * pData[ 8]) + (data[2][3] * pData[12]);
   TFloat v09 = (data[2][0] * pData[1]) + (data[2][1] * pData[5]) + (data[2][2] * pData[ 9]) + (data[2][3] * pData[13]);
   TFloat v10 = (data[2][0] * pData[2]) + (data[2][1] * pData[6]) + (data[2][2] * pData[10]) + (data[2][3] * pData[14]);
   TFloat v11 = (data[2][0] * pData[3]) + (data[2][1] * pData[7]) + (data[2][2] * pData[11]) + (data[2][3] * pData[15]);
   TFloat v12 = (data[3][0] * pData[0]) + (data[3][1] * pData[4]) + (data[3][2] * pData[ 8]) + (data[3][3] * pData[12]);
   TFloat v13 = (data[3][0] * pData[1]) + (data[3][1] * pData[5]) + (data[3][2] * pData[ 9]) + (data[3][3] * pData[13]);
   TFloat v14 = (data[3][0] * pData[2]) + (data[3][1] * pData[6]) + (data[3][2] * pData[10]) + (data[3][3] * pData[14]);
   TFloat v15 = (data[3][0] * pData[3]) + (data[3][1] * pData[7]) + (data[3][2] * pData[11]) + (data[3][3] * pData[15]);
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
   return ESuccess;
}

//============================================================
// <T>追加一个矩阵数据内容。</T>
//
// @param matrix 矩阵
// @return 处理结果
//============================================================
TResult SFloatMatrix3d::Append(const SFloatMatrix3d& matrix){
   return AppendData((TFloatC*)matrix.data);
}

//============================================================
// <T>计算当前矩阵的逆矩阵。</T>
//
// @return 处理结果
//============================================================
TBool SFloatMatrix3d::Invert(){
   // 计算矩阵
   TFloat invert[4][4];
   invert[0][0] =  data[1][1] * data[2][2] * data[3][3] -
                   data[1][1] * data[2][3] * data[3][2] -
                   data[2][1] * data[1][2] * data[3][3] +
                   data[2][1] * data[1][3] * data[3][2] +
                   data[3][1] * data[1][2] * data[2][3] -
                   data[3][1] * data[1][3] * data[2][2];
   invert[1][0] = -data[1][0] * data[2][2] * data[3][3] +
                   data[1][0] * data[2][3] * data[3][2] +
                   data[2][0] * data[1][2] * data[3][3] -
                   data[2][0] * data[1][3] * data[3][2] -
                   data[3][0] * data[1][2] * data[2][3] +
                   data[3][0] * data[1][3] * data[2][2];
   invert[2][0] =  data[1][0] * data[2][1] * data[3][3] -
                   data[1][0] * data[2][3] * data[3][1] -
                   data[2][0] * data[1][1] * data[3][3] +
                   data[2][0] * data[1][3] * data[3][1] +
                   data[3][0] * data[1][1] * data[2][3] -
                   data[3][0] * data[1][3] * data[2][1];
   invert[3][0] = -data[1][0] * data[2][1] * data[3][2] +
                   data[1][0] * data[2][2] * data[3][1] +
                   data[2][0] * data[1][1] * data[3][2] -
                   data[2][0] * data[1][2] * data[3][1] -
                   data[3][0] * data[1][1] * data[2][2] +
                   data[3][0] * data[1][2] * data[2][1];
   invert[0][1] = -data[0][1] * data[2][2] * data[3][3] +
                   data[0][1] * data[2][3] * data[3][2] +
                   data[2][1] * data[0][2] * data[3][3] -
                   data[2][1] * data[0][3] * data[3][2] -
                   data[3][1] * data[0][2] * data[2][3] +
                   data[3][1] * data[0][3] * data[2][2];
   invert[1][1] =  data[0][0] * data[2][2] * data[3][3] -
                   data[0][0] * data[2][3] * data[3][2] -
                   data[2][0] * data[0][2] * data[3][3] +
                   data[2][0] * data[0][3] * data[3][2] +
                   data[3][0] * data[0][2] * data[2][3] -
                   data[3][0] * data[0][3] * data[2][2];
   invert[2][1] = -data[0][0] * data[2][1] * data[3][3] +
                   data[0][0] * data[2][3] * data[3][1] +
                   data[2][0] * data[0][1] * data[3][3] -
                   data[2][0] * data[0][3] * data[3][1] -
                   data[3][0] * data[0][1] * data[2][3] +
                   data[3][0] * data[0][3] * data[2][1];
   invert[3][1] =  data[0][0] * data[2][1] * data[3][2] -
                   data[0][0] * data[2][2] * data[3][1] -
                   data[2][0] * data[0][1] * data[3][2] +
                   data[2][0] * data[0][2] * data[3][1] +
                   data[3][0] * data[0][1] * data[2][2] -
                   data[3][0] * data[0][2] * data[2][1];
   invert[0][2] =  data[0][1] * data[1][2] * data[3][3] -
                   data[0][1] * data[1][3] * data[3][2] -
                   data[1][1] * data[0][2] * data[3][3] +
                   data[1][1] * data[0][3] * data[3][2] +
                   data[3][1] * data[0][2] * data[1][3] -
                   data[3][1] * data[0][3] * data[1][2];
   invert[1][2] = -data[0][0] * data[1][2] * data[3][3] +
                   data[0][0] * data[1][3] * data[3][2] +
                   data[1][0] * data[0][2] * data[3][3] -
                   data[1][0] * data[0][3] * data[3][2] -
                   data[3][0] * data[0][2] * data[1][3] +
                   data[3][0] * data[0][3] * data[1][2];
   invert[2][2] =  data[0][0] * data[1][1] * data[3][3] -
                   data[0][0] * data[1][3] * data[3][1] -
                   data[1][0] * data[0][1] * data[3][3] +
                   data[1][0] * data[0][3] * data[3][1] +
                   data[3][0] * data[0][1] * data[1][3] -
                   data[3][0] * data[0][3] * data[1][1];
   invert[3][2] = -data[0][0] * data[1][1] * data[3][2] +
                   data[0][0] * data[1][2] * data[3][1] +
                   data[1][0] * data[0][1] * data[3][2] -
                   data[1][0] * data[0][2] * data[3][1] -
                   data[3][0] * data[0][1] * data[1][2] +
                   data[3][0] * data[0][2] * data[1][1];
   invert[0][3] = -data[0][1] * data[1][2] * data[2][3] +
                   data[0][1] * data[1][3] * data[2][2] +
                   data[1][1] * data[0][2] * data[2][3] -
                   data[1][1] * data[0][3] * data[2][2] -
                   data[2][1] * data[0][2] * data[1][3] +
                   data[2][1] * data[0][3] * data[1][2];
   invert[1][3] =  data[0][0] * data[1][2] * data[2][3] -
                   data[0][0] * data[1][3] * data[2][2] -
                   data[1][0] * data[0][2] * data[2][3] +
                   data[1][0] * data[0][3] * data[2][2] +
                   data[2][0] * data[0][2] * data[1][3] -
                   data[2][0] * data[0][3] * data[1][2];
   invert[2][3] = -data[0][0] * data[1][1] * data[2][3] +
                   data[0][0] * data[1][3] * data[2][1] +
                   data[1][0] * data[0][1] * data[2][3] -
                   data[1][0] * data[0][3] * data[2][1] -
                   data[2][0] * data[0][1] * data[1][3] +
                   data[2][0] * data[0][3] * data[1][1];
   invert[3][3] =  data[0][0] * data[1][1] * data[2][2] -
                   data[0][0] * data[1][2] * data[2][1] -
                   data[1][0] * data[0][1] * data[2][2] +
                   data[1][0] * data[0][2] * data[2][1] +
                   data[2][0] * data[0][1] * data[1][2] -
                   data[2][0] * data[0][2] * data[1][1];
   // 计算内容
   TFloat rate = data[0][0] * invert[0][0] + data[0][1] * invert[1][0] + data[0][2] * invert[2][0] + data[0][3] * invert[3][0];
   if(rate == 0.0f){
     return EFalse;
   }
   rate = 1.0f / rate;
   // 设置内容
   for(TInt y = 0; y < 4; y++){
      for(TInt x = 0; x < 4; x++){
        data[y][x] = invert[y][x] * rate;
      }
   }
   return ETrue;
}

//============================================================
// <T>强制更新数据。</T>
//============================================================
void SFloatMatrix3d::UpdateForce(){
   TFloat rsx = (TFloat)sin(rx);
   TFloat rcx = (TFloat)cos(rx);
   TFloat rsy = (TFloat)sin(ry);
   TFloat rcy = (TFloat)cos(ry);
   TFloat rsz = (TFloat)sin(rz);
   TFloat rcz = (TFloat)cos(rz);
   data[0][0] = rcy * rcz * sx;
   data[0][1] = rcy * rsz * sx;
   data[0][2] = -rsy * sx;
   data[0][3] = 0.0f;
   data[1][0] = (rsx * rsy * rcz - rcx * rsz) * sy;
   data[1][1] = (rsx * rsy * rsz + rcx * rcz) * sy;
   data[1][2] = rsx * rcy * sy;
   data[1][3] = 0.0f;
   data[2][0] = (rcx * rsy * rcz + rsx * rsz) * sz;
   data[2][1] = (rcx * rsy * rsz - rsx * rcz) * sz;
   data[2][2] = rcx * rcy * sz;
   data[2][3] = 0.0f;
   data[3][0] = tx;
   data[3][1] = ty;
   data[3][2] = tz;
   data[3][3] = 1.0f;
}

//============================================================
// <T>更新数据。</T>
//============================================================
void SFloatMatrix3d::Update(){
   if(dirty){
      UpdateForce();
      dirty = EFalse;
   }
}

//============================================================
// <T>单位化处理。</T>
//============================================================
void SFloatMatrix3d::Identity(){
   tx = ty = tz = 0.0f;
   rx = ry = rz = 0.0f;
   sx = sy = sz = 1.0f;
   for(TInt y = 0; y < 4; y++){
      for(TInt x = 0; x < 4; x++){
         data[y][x] = (x == y) ? 1.0f : 0.0f;
      }
   }
   dirty = EFalse;
}

//============================================================
// <T>平移内容。</T>
//
// @param x X坐标
// @param y Y坐标
// @param z Z坐标
//============================================================
void SFloatMatrix3d::Translate(TFloat x, TFloat y, TFloat z){
   TFloat data[4][4];
   data[0][0] = 1;
   data[0][1] = 0;
   data[0][2] = 0;
   data[0][3] = 0;
   data[1][0] = 0;
   data[1][1] = 1;
   data[1][2] = 0;
   data[1][3] = 0;
   data[2][0] = 0;
   data[2][1] = 0;
   data[2][2] = 1;
   data[2][3] = 0;
   data[3][0] = x;
   data[3][1] = y;
   data[3][2] = z;
   data[3][3] = 1;
   AppendData((TFloatC*)data);
}

//============================================================
// <T>X轴旋转内容。</T>
//  1    0   0 0
//  0  cos sin 0
//  0 -sin cos 0
//  0    0   0 1 
//
// @param value 弧度
//============================================================
void SFloatMatrix3d::RotationX(TFloat value){
   // 计算旋转
   TFloat rs = (TFloat)sin(value);
   TFloat rc = (TFloat)cos(value);
   // 追加内容
   TFloat data[4][4];
   data[0][0] = 1;
   data[0][1] = 0;
   data[0][2] = 0;
   data[0][3] = 0;
   data[1][0] = 0;
   data[1][1] = rc;
   data[1][2] = rs;
   data[1][3] = 0;
   data[2][0] = 0;
   data[2][1] = -rs;
   data[2][2] = rc;
   data[2][3] = 0;
   data[3][0] = 0;
   data[3][1] = 0;
   data[3][2] = 0;
   data[3][3] = 1;
   AppendData((TFloatC*)data);
}

//============================================================
// <T>Y轴旋转内容。</T>
//  cos   0  sin  0
//  0     1    0  0
//  -sin  0  cos  0
//  0     0    0  1 
//
// @param value 弧度
//============================================================
void SFloatMatrix3d::RotationY(TFloat value){
   // 计算旋转
   TFloat rs = (TFloat)sin(value);
   TFloat rc = (TFloat)cos(value);
   // 追加内容
   TFloat data[4][4];
   data[0][0] = rc;
   data[0][1] = 0;
   data[0][2] = rs;
   data[0][3] = 0;
   data[1][0] = 0;
   data[1][1] = 1;
   data[1][2] = 0;
   data[1][3] = 0;
   data[2][0] = -rs;
   data[2][1] = 0;
   data[2][2] = rc;
   data[2][3] = 0;
   data[3][0] = 0;
   data[3][1] = 0;
   data[3][2] = 0;
   data[3][3] = 1;
   AppendData((TFloatC*)data);
}

//============================================================
// <T>Z轴旋转内容。</T>
//  cos  sin  0 0
//  -sin cos  1 0
//  0      0  1 0
//  0      0  0 1 
//
// @param value 弧度
//============================================================
void SFloatMatrix3d::RotationZ(TFloat value){
   // 计算旋转
   TFloat rs = (TFloat)sin(value);
   TFloat rc = (TFloat)cos(value);
   // 追加内容
   TFloat data[4][4];
   data[0][0] = rc;
   data[0][1] = rs;
   data[0][2] = 0;
   data[0][3] = 0;
   data[1][0] = -rs;
   data[1][1] = rc;
   data[1][2] = 1;
   data[1][3] = 0;
   data[2][0] = 0;
   data[2][1] = 0;
   data[2][2] = 1;
   data[2][3] = 0;
   data[3][0] = 0;
   data[3][1] = 0;
   data[3][2] = 0;
   data[3][3] = 1;
   AppendData((TFloatC*)data);
}

//============================================================
// <T>设置旋转内容。</T>
//  1    0   0 0
//  0  cos sin 0
//  0 -sin cos 0
//  0    0   0 1 
//
// @param x X弧度
// @param y Y弧度
// @param z Z弧度
//============================================================
void SFloatMatrix3d::Rotation(TFloat x, TFloat y, TFloat z){
   // 计算旋转
   TFloat rsx = (TFloat)sin(x);
   TFloat rcx = (TFloat)cos(x);
   TFloat rsy = (TFloat)sin(y);
   TFloat rcy = (TFloat)cos(y);
   TFloat rsz = (TFloat)sin(z);
   TFloat rcz = (TFloat)cos(z);
   // 追加内容
   TFloat data[4][4];
   data[0][0] = rcy * rcz;
   data[0][1] = rcy * rsz;
   data[0][2] = -rsy;
   data[0][3] = 0;
   data[1][0] = rsx * rsy * rcz - rcx * rsz;
   data[1][1] = rsx * rsy * rsz + rcx * rcz;
   data[1][2] = rsx * rcy;
   data[1][3] = 0;
   data[2][0] = rcx * rsy * rcz + rsx * rsz;
   data[2][1] = rcx * rsy * rsz - rsx * rcx;
   data[2][2] = rcx * rcy;
   data[2][3] = 0;
   data[3][0] = 0;
   data[3][1] = 0;
   data[3][2] = 0;
   data[3][3] = 1;
   AppendData((TFloatC*)data);
}

//============================================================
// <T>设置缩放内容。</T>
//
// @param x X比例
// @param y Y比例
// @param z Z比例
//============================================================
void SFloatMatrix3d::Scale(TFloat x, TFloat y, TFloat z){
   TFloat data[4][4];
   data[0][0] = x;
   data[0][1] = 0;
   data[0][2] = 0;
   data[0][3] = 0;
   data[1][0] = 0;
   data[1][1] = y;
   data[1][2] = 0;
   data[1][3] = 0;
   data[2][0] = 0;
   data[2][1] = 0;
   data[2][2] = z;
   data[2][3] = 0;
   data[3][0] = 0;
   data[3][1] = 0;
   data[3][2] = 0;
   data[3][3] = 1;
   AppendData((TFloatC*)data);
}



//============================================================
// <T>变换一组三维顶点数据。</T>
// <P>每个点包含X,Y,X三个维度。</P>
//
// @param pOutput 输出数据
// @param pInput 输入数据
// @param count 点总数
// @return 处理结果
//============================================================
TResult SFloatMatrix3d::Transform(TFloat* pOutput, TFloatC* pInput, TInt count){
   // 检查参数
   MO_CHECK(pOutput, return ENull);
   MO_CHECK(pInput, return ENull);
   MO_CHECK(count > 0, return EFailure);
   // 处理数据
   for(TInt n = 0; n < count; n++){
      TInt index = (n << 1) + n; // = 3 * n
      pOutput[index    ] = (pInput[index] * data[0][0]) + (pInput[index + 1] * data[1][0]) +(pInput[index + 2] * data[2][0]) + data[3][0];
      pOutput[index + 1] = (pInput[index] * data[0][1]) + (pInput[index + 1] * data[1][1]) +(pInput[index + 2] * data[2][1]) + data[3][1];
      pOutput[index + 2] = (pInput[index] * data[0][2]) + (pInput[index + 1] * data[1][2]) +(pInput[index + 2] * data[2][2]) + data[3][2];
   }
   return ESuccess;
}

//============================================================
// <T>变换一个三维顶点数据。</T>
//
// @param x X坐标
// @param y Y坐标
// @param z Z坐标
// @return 变换后三维顶点
//============================================================
SFloatPoint3 SFloatMatrix3d::TransformPoint3(TFloat x, TFloat y, TFloat z){
   SFloatPoint3 result;
   result.x = (x * data[0][0]) + (y * data[1][0]) +(z * data[2][0]) + data[3][0];
   result.y = (x * data[0][1]) + (y * data[1][1]) +(z * data[2][1]) + data[3][1];
   result.z = (x * data[0][2]) + (y * data[1][2]) +(z * data[2][2]) + data[3][2];
   return result;
}

//============================================================
// <T>变换一个三维顶点数据。</T>
//
// @param value 三维顶点
// @return 变换后三维顶点
//============================================================
SFloatPoint3 SFloatMatrix3d::TransformPoint3(const SFloatPoint3& value){
   SFloatPoint3 result;
   result.x = (value.x * data[0][0]) + (value.y * data[1][0]) +(value.z * data[2][0]) + data[3][0];
   result.y = (value.x * data[0][1]) + (value.y * data[1][1]) +(value.z * data[2][1]) + data[3][1];
   result.z = (value.x * data[0][2]) + (value.y * data[1][2]) +(value.z * data[2][2]) + data[3][2];
   return result;
}

//============================================================
// <T>变换一个三维顶点数据。</T>
//
// @param input 输入顶点
// @param output 输出顶点
// @return 变换后三维顶点
//============================================================
void SFloatMatrix3d::TransformPoint3(SFloatPoint3& output, const SFloatPoint3& input){
   output.x = (input.x * data[0][0]) + (input.y * data[1][0]) +(input.z * data[2][0]) + data[3][0];
   output.y = (input.x * data[0][1]) + (input.y * data[1][1]) +(input.z * data[2][1]) + data[3][1];
   output.z = (input.x * data[0][2]) + (input.y * data[1][2]) +(input.z * data[2][2]) + data[3][2];
}

//============================================================
// <T>变换一个三维方向数据。</T>
//
// @param x X坐标
// @param y Y坐标
// @param z Z坐标
// @return 变换后三维方向
//============================================================
SFloatVector3 SFloatMatrix3d::TransformVector3(TFloat x, TFloat y, TFloat z){
   SFloatVector3 result;
   result.x = (x * data[0][0]) + (y * data[1][0]) +(z * data[2][0]) + data[3][0];
   result.y = (x * data[0][1]) + (y * data[1][1]) +(z * data[2][1]) + data[3][1];
   result.z = (x * data[0][2]) + (y * data[1][2]) +(z * data[2][2]) + data[3][2];
   return result;
}

//============================================================
// <T>变换一个三维方向数据。</T>
//
// @param value 三维方向
// @return 变换后三维方向
//============================================================
SFloatVector3 SFloatMatrix3d::TransformVector3(const SFloatVector3& value){
   SFloatVector3 result;
   result.x = (value.x * data[0][0]) + (value.y * data[1][0]) +(value.z * data[2][0]) + data[3][0];
   result.y = (value.x * data[0][1]) + (value.y * data[1][1]) +(value.z * data[2][1]) + data[3][1];
   result.z = (value.x * data[0][2]) + (value.y * data[1][2]) +(value.z * data[2][2]) + data[3][2];
   return result;
}

//============================================================
// <T>变换一个三维方向数据。</T>
//
// @param input 输入方向
// @param output 输出方向
// @return 变换后三维方向
//============================================================
void SFloatMatrix3d::TransformVector3(SFloatVector3& output, const SFloatVector3& input){
   output.x = (input.x * data[0][0]) + (input.y * data[1][0]) +(input.z * data[2][0]) + data[3][0];
   output.y = (input.x * data[0][1]) + (input.y * data[1][1]) +(input.z * data[2][1]) + data[3][1];
   output.z = (input.x * data[0][2]) + (input.y * data[1][2]) +(input.z * data[2][2]) + data[3][2];
}

//============================================================
// <T>序列化数据内容到输出流内。</T>
//
// @param pOutput 输出流
// @param transpose 转置
// @return 处理结果
//============================================================
TResult SFloatMatrix3d::SerializeData4x3(IDataOutput* pOutput, TBool transpose){
   if(transpose){
      TFloat result[4][3];
      result[0][0] = data[0][0];
      result[0][1] = data[1][0];
      result[0][2] = data[2][0];
      result[0][3] = data[3][0];
      result[1][0] = data[0][1];
      result[1][1] = data[1][1];
      result[1][2] = data[2][1];
      result[1][3] = data[3][1];
      result[2][0] = data[0][2];
      result[2][1] = data[1][2];
      result[2][2] = data[2][2];
      result[2][3] = data[3][2];
      pOutput->Write(result, sizeof(TFloat) * 12);
   }else{
      pOutput->Write(data, sizeof(TFloat) * 12);
   }
   return ESuccess;
}

//============================================================
// <T>从输入流里反序列化数据内容。</T>
//
// @param pInput 输入流
// @param transpose 转置
// @return 处理结果
//============================================================
TResult SFloatMatrix3d::UnserializeData4x3(IDataInput* pInput, TBool transpose){
   if(transpose){
      TFloat result[4][3];
      pInput->Read(result, sizeof(TFloat) * 12);
      data[0][0] = result[0][0];
      data[0][1] = result[1][0];
      data[0][2] = result[2][0];
      data[0][3] = result[3][0];
      data[1][0] = result[0][1];
      data[1][1] = result[1][1];
      data[1][2] = result[2][1];
      data[1][3] = result[3][1];
      data[2][0] = result[0][2];
      data[2][1] = result[1][2];
      data[2][2] = result[2][2];
      data[2][3] = result[3][2];
   }else{
      pInput->Read(data, sizeof(TFloat) * 12);
   }
   return ESuccess;
}

//============================================================
// <T>序列化数据内容到输出流内。</T>
//
// @param pOutput 输出流
// @param transpose 转置
// @return 处理结果
//============================================================
TResult SFloatMatrix3d::SerializeData4x4(IDataOutput* pOutput, TBool transpose){
   if(transpose){
      TFloat result[4][4];
      result[0][0] = data[0][0];
      result[0][1] = data[1][0];
      result[0][2] = data[2][0];
      result[0][3] = data[3][0];
      result[1][0] = data[0][1];
      result[1][1] = data[1][1];
      result[1][2] = data[2][1];
      result[1][3] = data[3][1];
      result[2][0] = data[0][2];
      result[2][1] = data[1][2];
      result[2][2] = data[2][2];
      result[2][3] = data[3][2];
      result[3][0] = data[0][3];
      result[3][1] = data[1][3];
      result[3][2] = data[2][3];
      result[3][3] = data[3][3];
      pOutput->Write(result, sizeof(TFloat) * 16);
   }else{
      pOutput->Write(data, sizeof(TFloat) * 16);
   }
   return ESuccess;
}

//============================================================
// <T>从输入流里反序列化数据内容。</T>
//
// @param pInput 输入流
// @param transpose 转置
// @return 处理结果
//============================================================
TResult SFloatMatrix3d::UnserializeData4x4(IDataInput* pInput, TBool transpose){
   if(transpose){
      TFloat result[4][4];
      pInput->Read(result, sizeof(TFloat) * 16);
      data[0][0] = result[0][0];
      data[0][1] = result[1][0];
      data[0][2] = result[2][0];
      data[0][3] = result[3][0];
      data[1][0] = result[0][1];
      data[1][1] = result[1][1];
      data[1][2] = result[2][1];
      data[1][3] = result[3][1];
      data[2][0] = result[0][2];
      data[2][1] = result[1][2];
      data[2][2] = result[2][2];
      data[2][3] = result[3][2];
      data[3][0] = result[0][3];
      data[3][1] = result[1][3];
      data[3][2] = result[2][3];
      data[3][3] = result[3][3];
   }else{
      pInput->Read(data, sizeof(TFloat) * 16);
   }
   return ESuccess;
}

//============================================================
// <T>序列化数据内容到输出流内。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult SFloatMatrix3d::Serialize(IDataOutput* pOutput){
   // 写入平移信息
   pOutput->WriteFloat(tx);
   pOutput->WriteFloat(ty);
   pOutput->WriteFloat(tz);
   // 写入旋转弧度
   pOutput->WriteFloat(rx);
   pOutput->WriteFloat(ry);
   pOutput->WriteFloat(rz);
   // 读取缩放信息
   pOutput->WriteFloat(sx);
   pOutput->WriteFloat(sy);
   pOutput->WriteFloat(sz);
   return ESuccess;
}

//============================================================
// <T>从输入流里反序列化数据内容。</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SFloatMatrix3d::Unserialize(IDataInput* pInput){
   // 读取平移信息
   tx = pInput->ReadFloat();
   ty = pInput->ReadFloat();
   tz = pInput->ReadFloat();
   // 读取旋转弧度
   rx = pInput->ReadFloat();
   ry = pInput->ReadFloat();
   rz = pInput->ReadFloat();
   // 读取缩放信息
   sx = pInput->ReadFloat();
   sy = pInput->ReadFloat();
   sz = pInput->ReadFloat();
   // 更新处理
   UpdateForce();
   return ESuccess;
}

MO_NAMESPACE_END
