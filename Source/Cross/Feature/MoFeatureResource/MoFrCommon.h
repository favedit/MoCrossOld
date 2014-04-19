#ifndef __MO_FR_COMMON_H__
#define __MO_FR_COMMON_H__
//************************************************************

#ifndef __MO_FR_PUBLIC_H__
#include "MoFrPublic.h"
#endif // __MO_FR_PUBLIC_H__

#ifndef __MO_FR_COMPRESS_H__
#include "MoFrCompress.h"
#endif // __MO_FR_COMPRESS_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>资源句柄。</T>
//============================================================
struct SResourceHandle{
public:
   TResourceId id;
   TCharC* namePtr;
   FInstance* instancePtr;
public:
   //------------------------------------------------------------
   // <T>构造资源句柄。</T>
   MO_INLINE SResourceHandle(){
      id = 0;
      MO_CLEAR(namePtr);
      MO_CLEAR(instancePtr);
   }
public:
   //------------------------------------------------------------
   // <T>转换为编号。</T>
   operator TResourceId(){
      return id;
   }
   //------------------------------------------------------------
   // <T>转换为编号。</T>
   operator TCharC*(){
      return namePtr;
   }
public:
   //------------------------------------------------------------
   // <T>设置内容。</T>
   void Set(TResourceId resourceId = 0, TCharC* pName = NULL, FInstance* pInstance = NULL){
      id = resourceId;
      namePtr = pName;
      instancePtr = pInstance;
   }
};
//------------------------------------------------------------
typedef SResourceHandle TResourceHandle;

//============================================================
// <T>几何标志。</T>
//============================================================
enum EGeomFlag{
   // 继承
   Inherit = 0,
   // 否
   No = 1,
   // 是
   Yes = 2,
};

//============================================================
// <T>几何标志工具。</T>
//============================================================
struct MO_FR_DECLARE RGeomFlag{
public:
   //============================================================
   // <T>获得整数。</T>
   //============================================================
   static TInt ToInteger(TBool value){
      return value ? Yes : Inherit;
   }

   //============================================================
   // <T>获得布尔值。</T>
   //
   // @param pv:value 内容
   // @param pd:default 默认
   //============================================================
   static TBool ToBoolean(TInt value, TBool defaultValue = EFalse){
      if(value == 0){
         return defaultValue;
      }
      return (Yes == value);
   }
};

MO_NAMESPACE_END
      
//************************************************************
#endif // __MO_FR_COMMON_H__
