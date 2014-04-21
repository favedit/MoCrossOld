#include "MoPsmScript.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FMonoClass, FScriptClass);

//============================================================
// <T>构造脚本类对象。</T>
//============================================================
FMonoClass::FMonoClass(){
   MO_CLEAR(_pMonoClass);
}

//============================================================
// <T>析构脚本类对象。</T>
//============================================================
FMonoClass::~FMonoClass(){
}

//============================================================
// <T>创建实例对象。</T>
//
// @return 实例对象
//============================================================
FScriptInstance* FMonoClass::CreateInstance(){
   FMonoMachine* pMachine = (FMonoMachine*)_pScriptLibrary->ScriptMachine();
   // 执行函数
   MonoObject* pMonoObject = mono_object_new(pMachine->NativeDomain(), _pMonoClass);
   mono_runtime_object_init(pMonoObject);
   // 创建对象
   FMonoInstance* pInstance = FMonoInstance::InstanceCreate();
   pInstance->SetScriptClass(this);
   pInstance->SetNativeObject(pMonoObject);
   return pInstance;
}

//============================================================
// <T>打开处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonoClass::Open(){
   TResult resultCd = FScriptClass::Open();
   // 获得类
   MO_CHECK(_pScriptLibrary, return ENull);
   FMonoLibrary* pLibrary = _pScriptLibrary->Convert<FMonoLibrary>();
   _pMonoClass = mono_class_from_name(pLibrary->NativeImage(), _spaceName, _className);
   MO_CHECK(_pMonoClass, return NULL);
   return resultCd;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonoClass::Close(){
   TResult resultCd = FScriptClass::Close();
   MO_CLEAR(_pMonoClass);
   return resultCd;
}

MO_NAMESPACE_END
