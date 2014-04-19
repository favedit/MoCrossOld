//============================================================
// <T>共通定义。</T>
//============================================================
#ifndef __MO_EG_PUBLIC_H__
#define __MO_EG_PUBLIC_H__

#ifndef __MO_COMMON_H__
#include <MoCommon.h>
#endif // __MO_COMMON_H__

#ifndef __MO_CORE_H__
#include <MoCore.h>
#endif // __MO_CORE_H__

#ifndef __MO_MATH_H__
#include <MoMath.h>
#endif // __MO_MATH_H__

#ifndef __MO_FEATURE_INPUT_H__
#include <MoFeatureInput.h>
#endif // __MO_FEATURE_INPUT_H__

#ifndef __MO_FEATURE_GRAPHIC_H__
#include <MoFeatureGraphic.h>
#endif // __MO_FEATURE_GRAPHIC_H__

#ifndef __MO_FEATURE_RESOURCE_H__
#include <MoFeatureResource.h>
#endif // __MO_FEATURE_RESOURCE_H__

#ifndef __MO_FEATURE_PARTICLE_H__
#include <MoFeatureParticle.h>
#endif // __MO_FEATURE_PARTICLE_H__

//============================================================
/// @define 导出定义
#ifdef _MO_EG_EXPORTS
#define MO_EG_DECLARE MO_EXPORT
#else
#define MO_EG_DECLARE MO_IMPORT
#endif // _MO_EG_EXPORTS

MO_NAMESPACE_BEGIN

//============================================================
// <T>实例编号。</T>
//============================================================
struct SInstanceId{
public:
   TInt32 code;
   TCharC* namePtr;
   FInstance* instancePtr;
public:
   //------------------------------------------------------------
   // <T>构造实例编号。</T>
   MO_INLINE SInstanceId(){
      code = 0;
      MO_CLEAR(namePtr);
      MO_CLEAR(instancePtr);
   }
   //------------------------------------------------------------
   // <T>构造实例编号。</T>
   MO_INLINE SInstanceId(const SInstanceId& instanceId){
      code = 0;
      MO_CLEAR(namePtr);
      MO_CLEAR(instancePtr);
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool operator == (const SInstanceId& instanceId){
      if(code != instanceId.code){
         return EFalse;
      }
      if(namePtr != instanceId.namePtr){
         return EFalse;
      }
      if(instancePtr != instanceId.instancePtr){
         return EFalse;
      }
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>判断是否不相等。</T>
   MO_INLINE TBool operator != (const SInstanceId& instanceId){
      if(code == instanceId.code){
         if(namePtr == instanceId.namePtr){
            if(instancePtr == instanceId.instancePtr){
               return EFalse;
            }
         }
      }
      return ETrue;
   }
public:
   //------------------------------------------------------------
   // <T>赋值处理。</T>
   MO_INLINE void operator = (const SInstanceId& instanceId){
      code = instanceId.code;
      namePtr = instanceId.namePtr;
      instancePtr = instanceId.instancePtr;
   }
};
//------------------------------------------------------------
typedef SInstanceId TInstanceId;

MO_NAMESPACE_END

#endif // __MO_EG_PUBLIC_H__
