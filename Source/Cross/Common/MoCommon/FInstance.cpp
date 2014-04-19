#include "MoCmClassBase.h"
#include "MoCmClass.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT(FInstance);

//============================================================
// <T>构造实例对象。</T>
//============================================================
FInstance::FInstance(){
   _referCount = 0;
}

//============================================================
// <T>析构实例对象。</T>
//============================================================
FInstance::~FInstance(){
}

//============================================================
// <T>判断当前实例是否是指定类名称。</T>
//
// @param pClassName 类名称
// @return 是否为真
//============================================================
TBool FInstance::IsInstanceOf(TCharC* pClassName){
   FClass* pFindClass = RClassManager::Instance().FindClass(pClassName);
   return (_pClass == pFindClass);
}

//============================================================
// <T>判断当前实例是否是指定类对象。</T>
//
// @param pClass 类对象
// @return 是否为真
//============================================================
TBool FInstance::IsInstanceOf(FClass* pClass){
   FClass* pInstanceClass = GetClass();
   return RClassManager::TestInherit(pInstanceClass, pClass);
}

//============================================================
// <T>判断当前实例是否继承指定类名称。</T>
//
// @param pClassName 类名称
// @return 是否为真
//============================================================
TBool FInstance::IsInheritsFrom(TCharC* pClassName){
   return _pClass->IsInheritsFrom(pClassName);
}

//============================================================
// <T>判断当前实例是否继承指定类对象。</T>
//
// @param pClass 类对象
// @return 是否为真
//============================================================
TBool FInstance::IsInheritsFrom(FClass* pClass){
   return _pClass->IsInheritsFrom(pClass);
}

//============================================================
// <T>转换类型。</T>
//
// @param pClass 类对象
// @return 转换后指针
//============================================================
FInstance* FInstance::Convert(FClass* pClass){
#ifdef _MO_DEBUG
   RClassManager::CheckConvert(_pClass, pClass);
#endif // _MO_DEBUG
   return this;
}

MO_NAMESPACE_END
