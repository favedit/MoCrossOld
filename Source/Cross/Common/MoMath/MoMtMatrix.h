#ifndef __MO_MT_MATRIX_H__
#define __MO_MT_MATRIX_H__

#ifndef _MATH_H
#include <math.h>
#endif // _MATH_H

#ifndef __MO_MT_COMMON_H__
#include "MoMtCommon.h"
#endif // __MO_MT_COMMON_H__

#ifndef __MO_MT_POINT_H__
#include "MoMtPoint.h"
#endif // __MO_MT_POINT_H__

#ifndef __MO_MT_VECTOR_H__
#include "MoMtVector.h"
#endif // __MO_MT_VECTOR_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>4x4浮点数矩阵。</T>
//============================================================
struct MO_MT_DECLARE SFloatMatrix4x4{
public:
   TFloat data[4][4];
public:
   //------------------------------------------------------------
   // <T>构造4x4浮点数矩阵。</T>
   SFloatMatrix4x4(){
      Identity();
   }
   //------------------------------------------------------------
   // <T>构造4x4浮点数矩阵。</T>
   SFloatMatrix4x4(const SFloatMatrix4x4& matrix){
      Assign(matrix);
   }
public:
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE TFloatC* MemoryC() const{
      return (const TFloat*)data;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE TFloat* Memory(){
      return (TFloat*)data;
   }
public:
   //------------------------------------------------------------
   // <T>判断矩阵是否相等。</T>
   TBool EqualsData(const TFloat* pData){
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
   MO_INLINE TBool Equals(SFloatMatrix4x4* pMatrix){
      return EqualsData(pMatrix->MemoryC());
   }
public:
   //------------------------------------------------------------
   // <T>单位化处理。</T>
   void Identity(){
      for(TInt y = 0; y < 4; y++){
         for(TInt x = 0; x < 4; x++){
            data[y][x] = (x == y) ? 1.0f : 0.0f;
         }
      }
   }
public:
   //------------------------------------------------------------
   // <T>接收一个矩阵数据内容。</T>
   MO_INLINE void AssignData(TFloatC* pData){
      MO_ASSERT(pData);
      MO_LIB_MEMORY_COPY(data, sizeof(data), pData, sizeof(data));
   }
   //------------------------------------------------------------
   // <T>接收一个矩阵数据内容。</T>
   MO_INLINE void Assign(const SFloatMatrix4x4& matrix){
      MO_LIB_MEMORY_COPY(data, sizeof(data), matrix.data, sizeof(data));
   }
   //------------------------------------------------------------
   // <T>追加一个矩阵数据内容。</T>
   void AppendData(TFloatC* pData){
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
   }
   //------------------------------------------------------------
   // <T>追加一个矩阵数据内容。</T>
   MO_INLINE void Append(const SFloatMatrix4x4& matrix){
      AppendData((TFloatC*)matrix.data);
   }
public:
   //------------------------------------------------------------
   // <T>变换一个三维顶点数据。</T>
   SFloatPoint3 TransformPoint3(const SFloatPoint3 point){
      SFloatPoint3 result;
      result.x = (point.x * data[0][0]) + (point.y * data[1][0]) +(point.z * data[2][0]) + data[3][0];
      result.y = (point.x * data[0][1]) + (point.y * data[1][1]) +(point.z * data[2][1]) + data[3][1];
      result.z = (point.x * data[0][2]) + (point.y * data[1][2]) +(point.z * data[2][2]) + data[3][2];
      return result;
   }
   //------------------------------------------------------------
   // <T>变换一个三维顶点数据。</T>
   SFloatPoint3 TransformPoint3(TFloat x, TFloat y, TFloat z){
      SFloatPoint3 result;
      result.x = (x * data[0][0]) + (y * data[1][0]) +(z * data[2][0]) + data[3][0];
      result.y = (x * data[0][1]) + (y * data[1][1]) +(z * data[2][1]) + data[3][1];
      result.z = (x * data[0][2]) + (y * data[1][2]) +(z * data[2][2]) + data[3][2];
      return result;
   }
public:
   TResult SerializeData(IDataOutput* pOutput);
   TResult UnserializeData(IDataInput* pInput);
};

//============================================================
// <T>4x4双浮点数矩阵。</T>
//============================================================
struct MO_MT_DECLARE SDoubleMatrix4x4{
public:
   TDouble data[4][4];
public:
   //------------------------------------------------------------
   // <T>构造4x4双浮点数矩阵。</T>
   SDoubleMatrix4x4(){
      Identity();
   }
   //------------------------------------------------------------
   // <T>构造4x4双浮点数矩阵。</T>
   SDoubleMatrix4x4(const SDoubleMatrix4x4& matrix){
      Assign(matrix);
   }
public:
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE TDoubleC* MemoryC() const{
      return (const TDouble*)data;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE TDouble* Memory(){
      return (TDouble*)data;
   }
public:
   //------------------------------------------------------------
   // <T>判断矩阵是否相等。</T>
   TBool EqualsData(const TDouble* pData){
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
   MO_INLINE TBool Equals(SDoubleMatrix4x4* pMatrix){
      return EqualsData(pMatrix->MemoryC());
   }
public:
   //------------------------------------------------------------
   // <T>单位化处理。</T>
   void Identity(){
      for(TInt y = 0; y < 4; y++){
         for(TInt x = 0; x < 4; x++){
            data[y][x] = (x == y) ? 1 : 0;
         }
      }
   }
public:
   //------------------------------------------------------------
   // <T>接收一个矩阵数据内容。</T>
   MO_INLINE void AssignData(TDoubleC* pData){
      MO_ASSERT(pData);
      MO_LIB_MEMORY_COPY(data, sizeof(data), pData, sizeof(data));
   }
   //------------------------------------------------------------
   // <T>接收一个矩阵数据内容。</T>
   MO_INLINE void Assign(const SDoubleMatrix4x4& matrix){
      MO_LIB_MEMORY_COPY(data, sizeof(data), matrix.data, sizeof(data));
   }
   //------------------------------------------------------------
   // <T>追加一个矩阵数据内容。</T>
   void AppendData(TDoubleC* pData){
      // 矩阵计算
      TDouble v00 = (data[0][0] * pData[0]) + (data[0][1] * pData[4]) + (data[0][2] * pData[ 8]) + (data[0][3] * pData[12]);
      TDouble v01 = (data[0][0] * pData[1]) + (data[0][1] * pData[5]) + (data[0][2] * pData[ 9]) + (data[0][3] * pData[13]);
      TDouble v02 = (data[0][0] * pData[2]) + (data[0][1] * pData[6]) + (data[0][2] * pData[10]) + (data[0][3] * pData[14]);
      TDouble v03 = (data[0][0] * pData[3]) + (data[0][1] * pData[7]) + (data[0][2] * pData[11]) + (data[0][3] * pData[15]);
      TDouble v04 = (data[1][0] * pData[0]) + (data[1][1] * pData[4]) + (data[1][2] * pData[ 8]) + (data[1][3] * pData[12]);
      TDouble v05 = (data[1][0] * pData[1]) + (data[1][1] * pData[5]) + (data[1][2] * pData[ 9]) + (data[1][3] * pData[13]);
      TDouble v06 = (data[1][0] * pData[2]) + (data[1][1] * pData[6]) + (data[1][2] * pData[10]) + (data[1][3] * pData[14]);
      TDouble v07 = (data[1][0] * pData[3]) + (data[1][1] * pData[7]) + (data[1][2] * pData[11]) + (data[1][3] * pData[15]);
      TDouble v08 = (data[2][0] * pData[0]) + (data[2][1] * pData[4]) + (data[2][2] * pData[ 8]) + (data[2][3] * pData[12]);
      TDouble v09 = (data[2][0] * pData[1]) + (data[2][1] * pData[5]) + (data[2][2] * pData[ 9]) + (data[2][3] * pData[13]);
      TDouble v10 = (data[2][0] * pData[2]) + (data[2][1] * pData[6]) + (data[2][2] * pData[10]) + (data[2][3] * pData[14]);
      TDouble v11 = (data[2][0] * pData[3]) + (data[2][1] * pData[7]) + (data[2][2] * pData[11]) + (data[2][3] * pData[15]);
      TDouble v12 = (data[3][0] * pData[0]) + (data[3][1] * pData[4]) + (data[3][2] * pData[ 8]) + (data[3][3] * pData[12]);
      TDouble v13 = (data[3][0] * pData[1]) + (data[3][1] * pData[5]) + (data[3][2] * pData[ 9]) + (data[3][3] * pData[13]);
      TDouble v14 = (data[3][0] * pData[2]) + (data[3][1] * pData[6]) + (data[3][2] * pData[10]) + (data[3][3] * pData[14]);
      TDouble v15 = (data[3][0] * pData[3]) + (data[3][1] * pData[7]) + (data[3][2] * pData[11]) + (data[3][3] * pData[15]);
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
   MO_INLINE void Append(const SDoubleMatrix4x4& matrix){
      AppendData((TDoubleC*)matrix.data);
   }
public:
   //------------------------------------------------------------
   // <T>变换一个三维顶点数据。</T>
   SDoublePoint3 TransformPoint3(const SDoublePoint3 point){
      SDoublePoint3 result;
      result.x = (point.x * data[0][0]) + (point.y * data[1][0]) +(point.z * data[2][0]) + data[3][0];
      result.y = (point.x * data[0][1]) + (point.y * data[1][1]) +(point.z * data[2][1]) + data[3][1];
      result.z = (point.x * data[0][2]) + (point.y * data[1][2]) +(point.z * data[2][2]) + data[3][2];
      return result;
   }
   //------------------------------------------------------------
   // <T>变换一个三维顶点数据。</T>
   SDoublePoint3 TransformPoint3(TDouble x, TDouble y, TDouble z){
      SDoublePoint3 result;
      result.x = (x * data[0][0]) + (y * data[1][0]) +(z * data[2][0]) + data[3][0];
      result.y = (x * data[0][1]) + (y * data[1][1]) +(z * data[2][1]) + data[3][1];
      result.z = (x * data[0][2]) + (y * data[1][2]) +(z * data[2][2]) + data[3][2];
      return result;
   }
public:
   TResult SerializeData(IDataOutput* pOutput);
   TResult UnserializeData(IDataInput* pInput);
};

//============================================================
// <T>3x3浮点数矩阵。</T>
//============================================================
struct SFloatMatrix2d{
public:
   TFloat m[3][3];
public:
   //------------------------------------------------------------
   // <T>构造3x3浮点数矩阵。</T>
   SFloatMatrix2d(){
      Reset();
   }
public:
   //------------------------------------------------------------
   SFloatMatrix2d operator*(SFloatMatrix2d& v){
      return Multiply(v);
   }
public:
   //------------------------------------------------------------
   void Reset(){
      for(TInt j=0; j<3; j++){
         for(TInt i=0; i<3; i++){
            m[j][i] = 0;
         }
      }
   }
public:
   //------------------------------------------------------------
   // <T>获得矩阵的模。</T>
   TFloat Absolute(){
      TFloat v = 0;
      for(TInt j=0; j<3; j++){
         for(TInt i=0; i<3; i++){
            v += m[j][i] * m[j][i];
         }
      }
      return (TFloat)sqrt(v);
   }
   //------------------------------------------------------------
   // <T>点乘(内积)。</T>
   void Cross(SFloatMatrix2d& v){
   }
   //------------------------------------------------------------
   // <T>叉乘(外积)。</T>
   SFloatPoint3 Multiply(SFloatPoint3& v){
      SFloatPoint3 r;
      r.x = v.x*m[0][0] + v.y*m[0][1] + v.z*m[0][2];
      r.y = v.x*m[1][0] + v.y*m[1][1] + v.z*m[1][2];
      r.z = v.x*m[2][0] + v.y*m[2][1] + v.z*m[2][2];
      return r;
   }
   //------------------------------------------------------------
   // <T>叉乘(外积)。</T>
   SFloatMatrix2d Multiply(SFloatMatrix2d& v){
      SFloatMatrix2d r;
      for(TInt j = 0; j < 3; j++){
         for(TInt i = 0; i < 3; i++){
            for(TInt k = 0; k < 3; k++){
               r.m[j][i] += m[j][k] * v.m[k][i];
            }
         }
      }
      return r;
   }
   //------------------------------------------------------------
   // <T>单位化矩阵。</T>
   void Normalize(){
      TFloat v = Absolute();
      for(TInt j = 0; j < 3; j++){
         for(TInt i = 0; i < 3; i++){
            m[j][i] /= v;
         }
      }
   }
   //------------------------------------------------------------
   TFloat ToValue(){
      // 加数部分
      TFloat a1 = m[0][0] * m[1][1] * m[2][2];
      TFloat a2 = m[0][1] * m[1][2] * m[2][0];
      TFloat a3 = m[0][2] * m[1][0] * m[2][1];
      // 减数部分
      TFloat b1 = m[0][0] * m[1][2] * m[2][1];
      TFloat b2 = m[0][1] * m[1][0] * m[2][2];
      TFloat b3 = m[0][2] * m[1][1] * m[2][0];
      // 结果部分
      TFloat v = (a1 + a2 + a3) - (b1 + b2 + b3);
      return v;
   }
};

//============================================================
// <T>3x3双浮点数矩阵。</T>
//============================================================
struct SDoubleMatrix2d{
public:
   TDouble m[3][3];
public:
   //------------------------------------------------------------
   // <T>构造3x3双浮点数矩阵。</T>
   SDoubleMatrix2d(){
      Reset();
   }
public:
   //------------------------------------------------------------
   SDoubleMatrix2d operator*(SDoubleMatrix2d& v){
      return Multiply(v);
   }
public:
   //------------------------------------------------------------
   void Reset(){
      for(TInt j=0; j<3; j++){
         for(TInt i=0; i<3; i++){
            m[j][i] = 0;
         }
      }
   }
public:
   //------------------------------------------------------------
   // <T>获得矩阵的模。</T>
   TDouble Absolute(){
      TDouble v = 0;
      for(TInt j=0; j<3; j++){
         for(TInt i=0; i<3; i++){
            v += m[j][i] * m[j][i];
         }
      }
      return sqrt(v);
   }
   //------------------------------------------------------------
   // <T>点乘(内积)。</T>
   void Cross(SDoubleMatrix2d& v){
   }
   //------------------------------------------------------------
   // <T>叉乘(外积)。</T>
   SDoublePoint3 Multiply(SDoublePoint3& v){
      SDoublePoint3 r;
      r.x = v.x*m[0][0] + v.y*m[0][1] + v.z*m[0][2];
      r.y = v.x*m[1][0] + v.y*m[1][1] + v.z*m[1][2];
      r.z = v.x*m[2][0] + v.y*m[2][1] + v.z*m[2][2];
      return r;
   }
   //------------------------------------------------------------
   // <T>叉乘(外积)。</T>
   SDoubleMatrix2d Multiply(SDoubleMatrix2d& v){
      SDoubleMatrix2d r;
      for(TInt j = 0; j < 3; j++){
         for(TInt i = 0; i < 3; i++){
            for(TInt k = 0; k < 3; k++){
               r.m[j][i] += m[j][k] * v.m[k][i];
            }
         }
      }
      return r;
   }
   //------------------------------------------------------------
   // <T>单位化矩阵。</T>
   void Normalize(){
      TDouble v = Absolute();
      for(TInt j = 0; j < 3; j++){
         for(TInt i = 0; i < 3; i++){
            m[j][i] /= v;
         }
      }
   }
   //------------------------------------------------------------
   TDouble ToValue(){
      // 加数部分
      TDouble a1 = m[0][0] * m[1][1] * m[2][2];
      TDouble a2 = m[0][1] * m[1][2] * m[2][0];
      TDouble a3 = m[0][2] * m[1][0] * m[2][1];
      // 减数部分
      TDouble b1 = m[0][0] * m[1][2] * m[2][1];
      TDouble b2 = m[0][1] * m[1][0] * m[2][2];
      TDouble b3 = m[0][2] * m[1][1] * m[2][0];
      // 结果部分
      TDouble v = (a1 + a2 + a3) - (b1 + b2 + b3);
      return v;
   }
};

//============================================================
// <T>4x4浮点数矩阵。</T>
//============================================================
struct MO_MT_DECLARE SFloatMatrix3d{
public:
   TBool dirty;
   TFloat tx;
   TFloat ty;
   TFloat tz;
   TFloat rx;
   TFloat ry;
   TFloat rz;
   TFloat sx;
   TFloat sy;
   TFloat sz;
   TFloat data[4][4];
public:
   //------------------------------------------------------------
   // <T>构造4x4浮点数矩阵。</T>
   SFloatMatrix3d(){
      Identity();
   }
   //------------------------------------------------------------
   // <T>构造4x4浮点数矩阵。</T>
   SFloatMatrix3d(const SFloatMatrix3d& matrix){
      Assign(matrix);
   }
public:
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE TFloatC* MemoryC() const{
      return (const TFloat*)data;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE TFloat* Memory(){
      return (TFloat*)data;
   }
public:
   //------------------------------------------------------------
   // <T>判断矩阵是否相等。</T>
   TBool EqualsData(const TFloat* pData){
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
   MO_INLINE TBool Equals(SFloatMatrix3d* pMatrix){
      return EqualsData(pMatrix->MemoryC());
   }
public:
   //------------------------------------------------------------
   // <T>设置平移内容。</T>
   MO_INLINE void SetTranslate(TFloat x, TFloat y, TFloat z){
      tx = x;
      ty = y;
      tz = z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置平移内容。</T>
   MO_INLINE void SetTranslate(const SFloatPoint3& value){
      tx = value.x;
      ty = value.y;
      tz = value.z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置旋转内容。</T>
   MO_INLINE void SetRotation(TFloat x, TFloat y, TFloat z){
      rx = x;
      ry = y;
      rz = z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置旋转内容。</T>
   MO_INLINE void SetRotation(const SFloatVector3& value){
      rx = value.x;
      ry = value.y;
      rz = value.z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置缩放内容。</T>
   MO_INLINE void SetScale(TFloat x, TFloat y, TFloat z){
      sx = x;
      sy = y;
      sz = z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置缩放内容。</T>
   MO_INLINE void SetScale(const SFloatVector3& value){
      sx = value.x;
      sy = value.y;
      sz = value.z;
      dirty = ETrue;
   }
public:
   //------------------------------------------------------------
   // <T>接收一个矩阵数据内容。</T>
   MO_INLINE void AssignData(TFloatC* pData){
      MO_ASSERT(pData);
      MO_LIB_MEMORY_COPY(data, sizeof(data), pData, sizeof(data));
   }
   //------------------------------------------------------------
   // <T>接收一个矩阵数据内容。</T>
   MO_INLINE void Assign(const SFloatMatrix3d& matrix){
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
   TResult AppendData(TFloatC* pData);
   TResult Append(const SFloatMatrix3d& matrix);
   TBool Invert();
   void UpdateForce();
   void Update();
public:
   void Identity();
   void Translate(TFloat x, TFloat y, TFloat z);
   void RotationX(TFloat value);
   void RotationY(TFloat value);
   void RotationZ(TFloat value);
   void Rotation(TFloat x, TFloat y, TFloat z);
   void Scale(TFloat x, TFloat y, TFloat z);
public:
   TResult Transform(TFloat* pOutput, TFloatC* pInput, TInt count);
   SFloatPoint3 TransformPoint3(TFloat x, TFloat y, TFloat z);
   SFloatPoint3 TransformPoint3(const SFloatPoint3& value);
   void TransformPoint3(SFloatPoint3& output, const SFloatPoint3& input);
   SFloatVector3 TransformVector3(TFloat x, TFloat y, TFloat z);
   SFloatVector3 TransformVector3(const SFloatVector3& value);
   void TransformVector3(SFloatVector3& output, const SFloatVector3& input);
public:
   TResult SerializeData4x3(IDataOutput* pOutput, TBool transpose = EFalse);
   TResult UnserializeData4x3(IDataInput* pInput, TBool transpose = EFalse);
   TResult SerializeData4x4(IDataOutput* pOutput, TBool transpose = EFalse);
   TResult UnserializeData4x4(IDataInput* pInput, TBool transpose = EFalse);
   TResult Serialize(IDataOutput* pOutput);
   TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>4x4双浮点数矩阵。</T>
//============================================================
struct MO_MT_DECLARE SDoubleMatrix3d{
public:
   TBool dirty;
   TDouble tx;
   TDouble ty;
   TDouble tz;
   TDouble rx;
   TDouble ry;
   TDouble rz;
   TDouble sx;
   TDouble sy;
   TDouble sz;
   TDouble data[4][4];
public:
   //------------------------------------------------------------
   // <T>构造4x4双浮点数矩阵。</T>
   SDoubleMatrix3d(){
      Identity();
   }
   //------------------------------------------------------------
   // <T>构造4x4双浮点数矩阵。</T>
   SDoubleMatrix3d(const SDoubleMatrix3d& matrix){
      Assign(matrix);
   }
public:
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE TDoubleC* MemoryC() const{
      return (const TDouble*)data;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE TDouble* Memory(){
      return (TDouble*)data;
   }
public:
   //------------------------------------------------------------
   // <T>判断矩阵是否相等。</T>
   TBool EqualsData(const TDouble* pData){
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
   MO_INLINE TBool Equals(SDoubleMatrix3d* pMatrix){
      return EqualsData(pMatrix->MemoryC());
   }
public:
   //------------------------------------------------------------
   // <T>设置平移内容。</T>
   MO_INLINE void SetTranslate(TDouble x, TDouble y, TDouble z){
      tx = x;
      ty = y;
      tz = z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置平移内容。</T>
   MO_INLINE void SetTranslate(const SDoublePoint3& value){
      tx = value.x;
      ty = value.y;
      tz = value.z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置旋转内容。</T>
   MO_INLINE void SetRotation(TDouble x, TDouble y, TDouble z){
      rx = x;
      ry = y;
      rz = z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置旋转内容。</T>
   MO_INLINE void SetRotation(const SDoubleVector3& value){
      rx = value.x;
      ry = value.y;
      rz = value.z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置缩放内容。</T>
   MO_INLINE void SetScale(TDouble x, TDouble y, TDouble z){
      sx = x;
      sy = y;
      sz = z;
      dirty = ETrue;
   }
   //------------------------------------------------------------
   // <T>设置缩放内容。</T>
   MO_INLINE void SetScale(const SDoubleVector3& value){
      sx = value.x;
      sy = value.y;
      sz = value.z;
      dirty = ETrue;
   }
public:
   //------------------------------------------------------------
   // <T>接收一个矩阵数据内容。</T>
   MO_INLINE void AssignData(TDoubleC* pData){
      MO_ASSERT(pData);
      MO_LIB_MEMORY_COPY(data, sizeof(data), pData, sizeof(data));
   }
   //------------------------------------------------------------
   // <T>接收一个矩阵数据内容。</T>
   MO_INLINE void Assign(const SDoubleMatrix3d& matrix){
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
   TResult AppendData(TDoubleC* pData);
   TResult Append(const SDoubleMatrix3d& matrix);
   TBool Invert();
   void UpdateForce();
   void Update();
public:
   void Identity();
   void Translate(TDouble x, TDouble y, TDouble z);
   void RotationX(TDouble value);
   void RotationY(TDouble value);
   void RotationZ(TDouble value);
   void Rotation(TDouble x, TDouble y, TDouble z);
   void Scale(TDouble x, TDouble y, TDouble z);
public:
   TResult Transform(TDouble* pOutput, TDoubleC* pInput, TInt count);
   SDoublePoint3 TransformPoint3(TDouble x, TDouble y, TDouble z);
   SDoublePoint3 TransformPoint3(const SDoublePoint3& value);
   void TransformPoint3(SDoublePoint3& output, const SDoublePoint3& input);
   SDoubleVector3 TransformVector3(TDouble x, TDouble y, TDouble z);
   SDoubleVector3 TransformVector3(const SDoubleVector3& value);
   void TransformVector3(SDoubleVector3& output, const SDoubleVector3& input);
public:
   TResult SerializeData4x3(IDataOutput* pOutput, TBool transpose = EFalse);
   TResult UnserializeData4x3(IDataInput* pInput, TBool transpose = EFalse);
   TResult SerializeData4x4(IDataOutput* pOutput, TBool transpose = EFalse);
   TResult UnserializeData4x4(IDataInput* pInput, TBool transpose = EFalse);
   TResult Serialize(IDataOutput* pOutput);
   TResult Unserialize(IDataInput* pInput);
};

MO_NAMESPACE_END

#endif // __MO_MT_MATRIX_H__
