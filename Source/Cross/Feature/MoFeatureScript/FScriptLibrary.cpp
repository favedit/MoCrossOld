#include "MoScript.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScriptLibrary, FScriptObject);

//============================================================
// <T>构造脚本库。</T>
//============================================================
FScriptLibrary::FScriptLibrary(){
   MO_CLEAR(_pScriptMachine);
}

//============================================================
// <T>析构脚本库。</T>
//============================================================
FScriptLibrary::~FScriptLibrary(){
}

//============================================================
// <T>根据空间名称和类名查找类对象。</T>
//
// @param pSpaceName 空间名称
// @param pClassName 类名称
// @return 类对象
//============================================================
FScriptClass* FScriptLibrary::FindClass(TCharC* pSpaceName, TCharC* pClassName){
   // 生成名称
   TFsName name;
   name.Append(pSpaceName);
   name.Append(TC("."));
   name.Append(pClassName);
   // 查找类对象
   FScriptClass* pClass = FindClass(name);
   return pClass;
}

//============================================================
// <T>根据名称查找类对象。</T>
//
// @param pName 名称
// @return 类对象
//============================================================
FScriptClass* FScriptLibrary::FindClass(TCharC* pName){
   MO_CHECK(_pScriptMachine, return NULL);
   // 查找类对象
   FScriptClass* pClass = _classes.Find(pName);
   if(pClass != NULL){
      return pClass;
   }
   // 创建类对象
   pClass = _pScriptMachine->ClassFactory()->Create<FScriptClass>(MO_SCRIPT_CLASS);
   pClass->SetScriptLibrary(this);
   pClass->SetName(pName);
   pClass->Open();
   _classes.Set(pName, pClass);
   return pClass;
}

MO_NAMESPACE_END
