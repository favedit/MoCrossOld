#include "MoScript.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScriptClass, FScriptObject);

//============================================================
// <T>构造脚本类对象。</T>
//============================================================
FScriptClass::FScriptClass(){
   MO_CLEAR(_pScriptLibrary);
}

//============================================================
// <T>析构脚本类对象。</T>
//============================================================
FScriptClass::~FScriptClass(){
}

//============================================================
// <T>获得脚本工作器。</T>
//
// @return 脚本工作器
//============================================================
FScriptMachine* FScriptClass::ScriptMachine(){
   MO_CHECK(_pScriptLibrary, return NULL);
   return _pScriptLibrary->ScriptMachine();
}

//============================================================
// <T>设置名称。</T>
//
// @param pName 设置名称
//============================================================
void FScriptClass::SetName(TCharC* pName){
   _name = pName;
   // 分割类名
   TInt splitter = _name.LastIndexOf(TC('.'));
   if(splitter != ENotFound){
      _spaceName = _name.LeftStrC(splitter);
      _className = _name.MidStrC(splitter + 1);
   }else{
      _spaceName.Clear();
      _className = _name;
   }
}

//============================================================
// <T>根据名称查找字段。</T>
//
// @param pName 名称
// @return 字段
//============================================================
FScriptField* FScriptClass::FindField(TCharC* pName){
   MO_CHECK(_pScriptLibrary, return NULL);
   // 查找字段对象
   FScriptField* pField = _fields.Find(pName);
   if(pField != NULL){
      return pField;
   }
   // 创建字段对象
   pField = _pScriptLibrary->ScriptMachine()->ClassFactory()->Create<FScriptField>(MO_SCRIPT_FIELD);
   pField->SetScriptClass(this);
   pField->SetName(pName);
   pField->Open();
   _fields.Set(pName, pField);
   return pField;
}

//============================================================
// <T>根据名称查找属性。</T>
//
// @param pName 名称
// @return 属性
//============================================================
FScriptProperty* FScriptClass::FindProperty(TCharC* pName){
   MO_CHECK(_pScriptLibrary, return NULL);
   // 查找类对象
   FScriptProperty* pProperty = _properties.Find(pName);
   if(pProperty != NULL){
      return pProperty;
   }
   // 创建类对象
   pProperty = _pScriptLibrary->ScriptMachine()->ClassFactory()->Create<FScriptProperty>(MO_SCRIPT_PROPERTY);
   pProperty->SetScriptClass(this);
   pProperty->SetName(pName);
   pProperty->Open();
   _properties.Set(pName, pProperty);
   return pProperty;
}

//============================================================
// <T>根据描述查找函数。</T>
//
// @param pDescriptor 描述
// @return 属性
//============================================================
FScriptMethod* FScriptClass::FindMethod(TCharC* pDescriptor){
   MO_CHECK(_pScriptLibrary, return NULL);
   // 查找类对象
   FScriptMethod* pMethod = _methods.Find(pDescriptor);
   if(pMethod != NULL){
      return pMethod;
   }
   // 创建类对象
   pMethod = _pScriptLibrary->ScriptMachine()->ClassFactory()->Create<FScriptMethod>(MO_SCRIPT_METHOD);
   pMethod->SetScriptClass(this);
   pMethod->SetDescriptor(pDescriptor);
   pMethod->Open();
   _methods.Set(pDescriptor, pMethod);
   return pMethod;
}

//============================================================
// <T>创建实例对象。</T>
//
// @return 实例对象
//============================================================
FScriptInstance* FScriptClass::CreateInstance(){
   MO_FATAL_UNSUPPORT();
   return NULL;
}

MO_NAMESPACE_END
