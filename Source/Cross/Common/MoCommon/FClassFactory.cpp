#include "MoCmClassBase.h"
#include "MoCmClass.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FClassFactory, FInstance);

//============================================================
// <T>构造类工厂。</T>
//============================================================
FClassFactory::FClassFactory(){
   _pClasses = MO_CREATE(FClassDictionary);
}

//============================================================
// <T>析构类工厂。</T>
//============================================================
FClassFactory::~FClassFactory(){
   MO_DELETE(_pClasses);
}

//============================================================
// <T>注册一个类对象。</T>
//
// @param pName 名称
// @param pClass 类对象
// @return 处理结果
//============================================================
TResult FClassFactory::Register(FClass* pClass){
   MO_ASSERT(pClass);
   _pClasses->Set(pClass->Name(), pClass);
   return ESuccess;
}

//============================================================
// <T>注册一个类对象。</T>
//
// @param pName 名称
// @param pClass 类对象
// @return 处理结果
//============================================================
TResult FClassFactory::Register(TCharC* pName, FClass* pClass){
   MO_ASSERT(pName);
   MO_ASSERT(pClass);
   _pClasses->Set(pName, pClass);
   return ESuccess;
}

//============================================================
// <T>注销一个类对象。</T>
//
// @param pName 名称
// @return 处理结果
//============================================================
TResult FClassFactory::Unregister(TCharC* pName){
   MO_ASSERT(pName);
   _pClasses->Remove(pName);
   return ESuccess;
}

//============================================================
// <T>判断是否含有指定名称。</T>
//
// @param pName 名称
// @return 是否含有
//============================================================
TBool FClassFactory::Contains(TCharC* pName){
   MO_ASSERT(pName);
   return _pClasses->Contains(pName);
}

//============================================================
// <T>创建一个实例。</T>
//
// @param pName 名称
// @return 实例
//============================================================
FInstance* FClassFactory::Create(TCharC* pName){
   MO_ASSERT(pName);
   FInstance* pInstance = NULL;
   FClass* pClass = _pClasses->Find(pName);
   if(pClass != NULL){
      pInstance = pClass->InstanceCreate();
   }
   return pInstance;
}

//============================================================
// <T>收集一个实例。</T>
//
// @param pName 名称
// @return 实例
//============================================================
FInstance* FClassFactory::Alloc(TCharC* pName){
   MO_ASSERT(pName);
   FInstance* pInstance = NULL;
   FClass* pClass = _pClasses->Find(pName);
   if(pClass != NULL){
      pInstance = pClass->InstanceAlloc();
   }
   return pInstance;
}

//============================================================
// <T>释放一个实例。</T>
//
// @param pClassName 类名称
// @return 实例
//============================================================
void FClassFactory::Free(FInstance* pInstance){
   MO_ASSERT(pInstance);
   pInstance->Class()->InstanceFree(pInstance);
}

//============================================================
// <T>删除一个实例。</T>
//
// @param pClassName 类名称
// @return 实例
//============================================================
void FClassFactory::Delete(FInstance* pInstance){
   MO_ASSERT(pInstance);
   pInstance->Class()->InstanceDelete(pInstance);
}

//============================================================
// <T>获得运行信息。</T>
//
// @param pDump 运行内容
// @param capacity 容量
// @return 运行信息
//============================================================
TCharC* FClassFactory::Dump(TChar* pDump, TInt capacity){
   MO_ASSERT(pDump);
   MO_ASSERT(capacity > 0);
   TStringRefer dump(pDump, capacity);
   dump.AppendFormat(TC("count=%d"), _pClasses->Count());
   return pDump;
}

MO_NAMESPACE_END
