#ifndef __MO_CM_BIT_H__
#define __MO_CM_BIT_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_TYPE_H__
#include "MoCmType.h"
#endif // __MO_CM_TYPE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>位域工具类。</T>
//
// @tool
// @history 110629 MAOCY 创建
//============================================================
template <typename T>
struct TBitSet{
protected:
   T _value;
public:
   //------------------------------------------------------------
   // <T>构造位域工具类。</T>
   inline TBitSet(){
      _value = 0;
   }
   //------------------------------------------------------------
   // <T>构造位域工具类。</T>
   inline TBitSet(T value){
      _value = value;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   TBool operator==(T value){
      return (_value == value);
   }
   //------------------------------------------------------------
   // <T>判断是否不相等。</T>
   TBool operator!=(T value){
      return (_value != value);
   }
   //------------------------------------------------------------
   // <T>获得内容。</T>
   operator T(){
      return _value;
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   void operator=(T value){
      _value = value;
   }
public:
   //------------------------------------------------------------
   // <T>获得数据大小。</T>
   static inline TSize Size(){
      return sizeof(T);
   }
public:
   //------------------------------------------------------------
   // <T>获得内容。</T>
   inline T Get(){
      return _value;
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   inline void Set(){
      _value = ~0;
   }
   //------------------------------------------------------------
   // <T>翻转内容。</T>
   inline void Inverse(){
      _value = ~_value;
   }
   //------------------------------------------------------------
   // <T>修改内容。</T>
   inline void Modify(T value){
      _value = value;
   }
   //------------------------------------------------------------
   // <T>清除内容。</T>
   inline void Clear(){
      _value = 0;
   }
   //------------------------------------------------------------
   // <T>获得指定位内容。</T>
   inline TBool GetBit(TInt index){
      return _value & (1 << index);
   }
   //------------------------------------------------------------
   // <T>设置指定位内容。</T>
   inline void SetBit(TInt index){
      _value |= (1 << index);
   }
   //------------------------------------------------------------
   // <T>翻转指定位内容。</T>
   inline void InverseBit(TInt index){
      T flag = (1 << index);
      if(_value & flag){
         _value &= ~flag;
      }else{
         _value |= flag;
      }
   }
   //------------------------------------------------------------
   // <T>修改指定位内容。</T>
   inline void ModifyBit(TInt index, TBool value){
      T flag = (1 << index);
      if(value){
         _value |= flag;
      }else{
         _value &= ~flag;
      }
   }
   //------------------------------------------------------------
   // <T>清除指定位内容。</T>
   inline void ClearBit(TInt index){
      T flag = (1 << index);
      _value &= ~flag;
   }
   //------------------------------------------------------------
   // <T>获得内容指向。</T>
   inline T* Refer(){
      return &_value;
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_BIT_H__
