//============================================================
// <T>线程锁管理类</T>
//============================================================
#ifndef __MO_CM_CLASS_H__
#define __MO_CM_CLASS_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_STRING_H__
#include "MoCmString.h"
#endif // __MO_CM_STRING_H__

#ifndef __MO_CM_DICTIONARY_H__
#include "MoCmDictionary.h"
#endif // __MO_CM_DICTIONARY_H__

#ifndef __MO_CM_CLASSBASE_H__
#include "MoCmClassBase.h"
#endif // __MO_CM_CLASSBASE_H__

#ifndef __MO_CM_SINGLETON_H__
#include "MoCmSingleton.h"
#endif // __MO_CM_SINGLETON_H__

#ifndef __MO_CM_MODULE_H__
#include "MoCmModule.h"
#endif // __MO_CM_MODULE_H__

#ifndef __MO_CM_PTRS_H__
#include "MoCmPtrs.h"
#endif // __MO_CM_PTRS_H__

#ifndef __MO_CM_PTR_LIST_H__
#include "MoCmPtrList.h"
#endif // __MO_CM_PTR_LIST_H__

#ifndef __MO_CM_PTR_LOOPER_H__
#include "MoCmPtrLooper.h"
#endif // __MO_CM_PTR_LOOPER_H__

#ifndef __MO_CM_PTR_DICTIONARY_H__
#include "MoCmPtrDictionary.h"
#endif // __MO_CM_PTR_DICTIONARY_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>类管理器。</T>
//
// @class
// @history 140301 MAOCY 创建
//============================================================
class MO_CM_DECLARE FClassConsole : public FConsole{
protected:
   TInt _looperLimit;
   TClassDictionary _classes;
   TClassLooper _looper;
public:
   FClassConsole();
   MO_ABSTRACT ~FClassConsole();
public:
   FClass* Register(FClass* pClass);
public:
   FClass* FindClass(TCharC* pClassName);
public:
   FInstance* InstanceCreate(TCharC* pClassName);
   FInstance* InstanceCreate(FClass* pClass);
   FInstance* InstanceAlloc(TCharC* pClassName);
   FInstance* InstanceAlloc(FClass* pClass);
   void InstanceFree(FInstance* pInstance);
   void InstanceDelete(FInstance* pInstance);
public:
   MO_ABSTRACT TResult Process();
public:
   void TrackActive();
   void TrackAll();
};

//============================================================
// <T>类管理器。</T>
//
// @class
// @history 140301 MAOCY 创建
//============================================================
#pragma data_seg("CommonShared")
class MO_CM_DECLARE RClassManager : public RSingleton<FClassConsole>{
public:
   static FClass* FindClass(TCharC* pClassName);
   static FClass* Register(FClass* pClass);
public:
   //------------------------------------------------------------
   // <T>注册类对象。</T>
   template<class T>
   static FClass* Register(TCharC* pClassName, TCharC* pParentName, TInt memorySize){
      FClass* pClass = new FClassTemplate<T>(pClassName, pParentName, memorySize);
      Register(pClass);
      return pClass;
   }
   //------------------------------------------------------------
   // <T>注册类对象。</T>
   template<class T>
   static FClass* RegisterAbstract(TCharC* pClassName, TCharC* pParentName, TInt memorySize){
      FClass* pClass = new FClassAbstractTemplate<T>(pClassName, pParentName, memorySize);
      Register(pClass);
      return pClass;
   }
public:
   static TBool TestInherit(FClass* pInstanceClass, FClass* pClass);
   static TBool TestInherit(FInstance* pInstance, FClass* pClass);
   static TResult CheckInherit(FClass* pInstanceClass, FClass* pClass);
   static TResult CheckInherit(FInstance* pInstance, FClass* pClass);
   static TResult CheckConvert(FClass* pInstanceClass, FClass* pClass);
   static TResult CheckConvert(FInstance* pInstance, FClass* pClass);
};
#pragma data_seg()

//============================================================
// <T>定义类实现。</T>
//============================================================
#define MO_CLASS_IMPLEMENT(T) \
FClass* T::_pClass = RClassManager::Register<T>(TC(#T), NULL, sizeof(T)); \
FClass* T::GetClass(){ \
   return _pClass; \
} \

//============================================================
// <T>定义类继承实现。</T>
//============================================================
#define MO_CLASS_IMPLEMENT_INHERITS(T, B) \
FClass* T::_pClass = RClassManager::Register<T>(TC(#T), TC(#B), sizeof(T)); \
FClass* T::_pParentClass = RClassManager::FindClass(TC(#B)); \
FClass* T::GetClass(){ \
   return _pClass; \
} \
FClass* T::GetParentClass(){ \
   return _pParentClass; \
} \

//============================================================
// <T>定义虚类实现。</T>
//============================================================
#define MO_CLASS_ABSTRACT_IMPLEMENT(T) \
FClass* T::_pClass = RClassManager::RegisterAbstract<T>(TC(#T), NULL, sizeof(T)); \
FClass* T::GetClass(){ \
   return _pClass; \
} \

//============================================================
// <T>定义虚类继承实现。</T>
//============================================================
#define MO_CLASS_ABSTRACT_IMPLEMENT_INHERITS(T, B) \
FClass* T::_pClass = RClassManager::RegisterAbstract<T>(TC(#T), TC(#B), sizeof(T)); \
FClass* T::_pParentClass = RClassManager::FindClass(TC(#B)); \
FClass* T::GetClass(){ \
   return _pClass; \
} \
FClass* T::GetParentClass(){ \
   return _pParentClass; \
} \

MO_NAMESPACE_END

#endif // __MO_CM_CLASS_H__
