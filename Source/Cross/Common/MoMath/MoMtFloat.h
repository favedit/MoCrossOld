#ifndef __MO_MT_FLOAT_H__
#define __MO_MT_FLOAT_H__

#ifndef _MATH_H
#include <math.h>
#endif // _MATH_H

#ifndef __MO_MT_COMMON_H__
#include "MoMtCommon.h"
#endif // __MO_MT_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>四维浮点数。</T>
//============================================================
struct MO_MT_DECLARE SFloat4{
public:
   TFloat data[4];
public:
   //------------------------------------------------------------
   // <T>构造四维浮点数。</T>
   MO_INLINE SFloat4(TFloat value1 = 0.0f, TFloat value2 = 0.0f, TFloat value3 = 0.0f, TFloat value4 = 0.0f){
      data[0] = value1;
      data[1] = value2;
      data[2] = value3;
      data[3] = value4;
   }
public:
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void operator=(const SFloat4& value){
      data[0] = value.data[0];
      data[1] = value.data[1];
      data[2] = value.data[2];
      data[3] = value.data[3];
   }
public:
   //------------------------------------------------------------
   // <T>相加处理。</T>
   MO_INLINE void operator+=(const SFloat4& value){
      data[0] += value.data[0];
      data[1] += value.data[1];
      data[2] += value.data[2];
      data[3] += value.data[3];
   }
   //------------------------------------------------------------
   // <T>相减处理。</T>
   MO_INLINE void operator-=(const SFloat4& value){
      data[0] -= value.data[0];
      data[1] -= value.data[1];
      data[2] -= value.data[2];
      data[3] -= value.data[3];
   }
   //------------------------------------------------------------
   // <T>相乘处理。</T>
   MO_INLINE void operator*=(const SFloat4& value){
      data[0] *= value.data[0];
      data[1] *= value.data[1];
      data[2] *= value.data[2];
      data[3] *= value.data[3];
   }
   //------------------------------------------------------------
   // <T>相除处理。</T>
   MO_INLINE void operator/=(const SFloat4& value){
      data[0] /= value.data[0];
      data[1] /= value.data[1];
      data[2] /= value.data[2];
      data[3] /= value.data[3];
   }
public:
   //------------------------------------------------------------
   // <T>判断内容是否为空。</T>
   MO_INLINE TBool IsEmpty(){
      return (data[0] == 0.0f) && (data[1] == 0.0f) && (data[2] == 0.0f) && (data[3] == 0.0f);
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void SetAll(TFloat value){
      data[0] = value;
      data[1] = value;
      data[2] = value;
      data[3] = value;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Set(TFloat value1, TFloat value2, TFloat value3, TFloat value4){
      data[0] = value1;
      data[1] = value2;
      data[2] = value3;
      data[3] = value4;
   }
   //------------------------------------------------------------
   // <T>接收数据内容。</T>
   MO_INLINE void Assign(const SFloat4& value){
      data[0] = value.data[0];
      data[1] = value.data[1];
      data[2] = value.data[2];
      data[3] = value.data[3];
   }
public:
   //------------------------------------------------------------
   // <T>重置数据。</T>
   MO_INLINE void Reset(){
      data[0] = 0.0f;
      data[1] = 0.0f;
      data[2] = 0.0f;
      data[3] = 0.0f;
   }
public:
   TResult Serialize(IDataOutput* pOutput);
   TResult Unserialize(IDataInput* pInput);
public:
   TResult Parse(TCharC* pValue);
   TCharC* Format(TChar* pBuffer, TInt capacity);
   TCharC* ToString(TChar* pBuffer, TInt capacity);
};

MO_NAMESPACE_END

#endif // __MO_MT_FLOAT_H__
