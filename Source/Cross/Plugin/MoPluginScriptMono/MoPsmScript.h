#ifndef __MO_PSM_SCRIPT_H__
#define __MO_PSM_SCRIPT_H__
//************************************************************

#ifndef _MONO_JIT_JIT_H_
#include <mono/jit/jit.h>
#endif // _MONO_JIT_JIT_H_

#ifndef _MONONET_METADATA_ASSEMBLY_H_ 
#include <mono/metadata/assembly.h>
#endif // _MONONET_METADATA_ASSEMBLY_H_ 

#ifndef __MONO_DEBUG_HELPERS_H__
#include <mono/metadata/debug-helpers.h>
#endif // __MONO_DEBUG_HELPERS_H__

#ifndef __MO_PSM_COMMON_H__
#include "MoPsmCommon.h"
#endif // __MO_PSM_COMMON_H__

MO_NAMESPACE_BEGIN

class FResource;
class FFileLoader;
class FLoaderWorker;

#ifdef _MO_WINDOWS
typedef HMODULE TModuleHandle;
#endif // _MO_WINDOWS
#ifdef _MO_ANDROID
#include "dlfcn.h"
typedef void* TModuleHandle;
#endif // _MO_ANDROID

//============================================================
// <T>脚本版本。</T>
//============================================================
enum EMonoScriptVersion{
   EMonoScriptVersion_v20,
   EMonoScriptVersion_v40,
   EMonoScriptVersion_v45,
};

//============================================================
// <T>脚本实例。</T>
//============================================================
class MO_PSM_DECLARE FMonoInstance : public FScriptInstance
{
   MO_CLASS_DECLARE_INHERITS(FMonoInstance, FScriptInstance);
protected:
   MonoObject* _pMonoObject;
public:
   FMonoInstance();
   MO_ABSTRACT ~FMonoInstance();
public:
   //------------------------------------------------------------
   // <T>获得本地对象。</T>
   MO_INLINE MonoObject* NativeObject(){
      return _pMonoObject;
   }
   //------------------------------------------------------------
   // <T>设置本地对象。</T>
   MO_INLINE void SetNativeObject(MonoObject* pObject){
      _pMonoObject = pObject;
   }
};

//============================================================
// <T>脚本字段。</T>
//============================================================
class MO_PSM_DECLARE FMonoField : public FScriptField
{
   MO_CLASS_DECLARE_INHERITS(FMonoField, FScriptField);
protected:
   MonoClassField* _pMonoField;
public:
   FMonoField();
   MO_ABSTRACT ~FMonoField();
public:
   //------------------------------------------------------------
   // <T>获得本地字段。</T>
   MO_INLINE MonoClassField* NativeField(){
      return _pMonoField;
   }
public:
   MO_OVERRIDE TResult Get(FScriptInstance* pInstance, TAny* pValue);
   MO_OVERRIDE TResult Set(FScriptInstance* pInstance, TAny* pValue);
   MO_OVERRIDE TResult GetString(FScriptInstance* pInstance, MString* pValue);
   MO_OVERRIDE TResult SetString(FScriptInstance* pInstance, TCharC* pValue);
public:
   MO_OVERRIDE TResult Open();
   MO_OVERRIDE TResult Close();
};

//============================================================
// <T>脚本属性。</T>
//============================================================
class MO_PSM_DECLARE FMonoProperty : public FScriptProperty
{
   MO_CLASS_DECLARE_INHERITS(FMonoProperty, FScriptProperty);
protected:
   MonoProperty* _pMonoProperty;
public:
   FMonoProperty();
   MO_ABSTRACT ~FMonoProperty();
public:
   //------------------------------------------------------------
   // <T>获得本地属性。</T>
   MO_INLINE MonoProperty* NativeProperty(){
      return _pMonoProperty;
   }
public:
   MO_OVERRIDE TResult Get(FScriptInstance* pInstance, TAny* pValue);
   MO_OVERRIDE TResult Set(FScriptInstance* pInstance, TAny* pValue);
   MO_OVERRIDE TResult GetString(FScriptInstance* pInstance, MString* pValue);
   MO_OVERRIDE TResult SetString(FScriptInstance* pInstance, TCharC* pValue);
public:
   MO_OVERRIDE TResult Open();
   MO_OVERRIDE TResult Close();
};
//------------------------------------------------------------
typedef MO_PSM_DECLARE GPtrs<FScriptField> GScriptFieldPtrs;

//============================================================
// <T>脚本函数。</T>
//============================================================
class MO_PSM_DECLARE FMonoMethod : public FScriptMethod
{
   MO_CLASS_DECLARE_INHERITS(FMonoMethod, FScriptMethod);
protected:
   MonoMethod* _pMonoMethod;
public:
   FMonoMethod();
   MO_ABSTRACT ~FMonoMethod();
public:
   //------------------------------------------------------------
   // <T>获得函数。</T>
   MO_INLINE MonoMethod* NativeMethod(){
      return _pMonoMethod;
   }
public:
   MO_OVERRIDE TResult InvokeParamaters(FScriptInstance* pInstance, TAny** ppResult, TInt paramCount, TAny** pParameters);
public:
   MO_OVERRIDE TResult Open();
   MO_OVERRIDE TResult Close();
};

//============================================================
// <T>类对象。</T>
//============================================================
class MO_PSM_DECLARE FMonoClass : public FScriptClass
{
   MO_CLASS_DECLARE_INHERITS(FMonoClass, FScriptClass);
protected:
   MonoClass* _pMonoClass;
public:
   FMonoClass();
   MO_ABSTRACT ~FMonoClass();
public:
   //------------------------------------------------------------
   // <T>获得映像。</T>
   MO_INLINE MonoClass* NativeClass(){
      return _pMonoClass;
   }
public:
   MO_OVERRIDE FScriptInstance* CreateInstance();
public:
   MO_OVERRIDE TResult Open();
   MO_OVERRIDE TResult Close();
};

//============================================================
// <T>脚本库。</T>
//============================================================
class MO_PSM_DECLARE FMonoLibrary : public FScriptLibrary
{
   MO_CLASS_DECLARE_INHERITS(FMonoLibrary, FScriptLibrary);
protected:
   MonoImage* _pMonoImage;
public:
   FMonoLibrary();
   MO_ABSTRACT ~FMonoLibrary();
public:
   //------------------------------------------------------------
   // <T>获得映像。</T>
   MO_INLINE MonoImage* NativeImage(){
      return _pMonoImage;
   }
public:
   MO_OVERRIDE TResult Open();
   MO_OVERRIDE TResult Close();
};

//============================================================
// <T>脚本工作器。</T>
//============================================================
class MO_PSM_DECLARE FMonoMachine : public FScriptMachine
{
   MO_CLASS_DECLARE_INHERITS(FMonoMachine, FInstance);
protected:
   MonoDomain* _pMonoDomain;
protected:
   TModuleHandle _handle;
public:
   FMonoMachine();
   MO_ABSTRACT ~FMonoMachine();
public:
   //------------------------------------------------------------
   // <T>获得主域。</T>
   MO_INLINE MonoDomain* NativeDomain(){
      return _pMonoDomain;
   }
public:
   MO_ABSTRACT FMonoLibrary* OpenLibrary(TCharC* pName);
   MO_ABSTRACT TResult SetupClassFactory();
   MO_ABSTRACT TResult Setup();
   MO_ABSTRACT TResult Close();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_PSM_SCRIPT_H__
