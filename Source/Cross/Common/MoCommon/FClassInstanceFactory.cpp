#include "MoCmClassBase.h"
#include "MoCmClass.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FClassInstanceFactory, FInstance);

//============================================================
// <T>构造实例类工厂。</T>
//============================================================
FClassInstanceFactory::FClassInstanceFactory(){
   MO_CLEAR(_pDefaultClass);
   _pClasses = MO_CREATE(FClassDictionary);
   _pInstances = MO_CREATE(FInstanceDictionary);
}

//============================================================
// <T>析构实例类工厂。</T>
//============================================================
FClassInstanceFactory::~FClassInstanceFactory(){
   MO_DELETE(_pClasses);
   MO_DELETE(_pInstances);
}

//============================================================
// <T>注册一个类对象。</T>
//
// @param pName 名称
// @param pClass 类对象
// @return 处理结果
//============================================================
TResult FClassInstanceFactory::Register(TCharC* pName, FClass* pClass){
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
TResult FClassInstanceFactory::Unregister(TCharC* pName){
   MO_ASSERT(pName);
   _pClasses->Remove(pName);
   return ESuccess;
}

//============================================================
// <T>查找一个实例。</T>
//
// @param pName 名称
// @return 实例
//============================================================
FInstance* FClassInstanceFactory::Find(TCharC* pName){
   // 查找实例
   FInstance* pInstance = NULL;
   if(pName != NULL){
      pInstance = _pInstances->Find(pName);
   }
   if(pInstance == NULL){
      FClass* pClass = _pClasses->Find(pName);
      if(pClass != NULL){
         pInstance = pClass->InstanceCreate();
         _pInstances->Set(pName, pInstance);
      }
   }
   // 使用默认实例
   if(pInstance == NULL){
      if(_defaultInstance.IsValid()){
         pInstance = _defaultInstance;
      }else if(_pDefaultClass != NULL){
         _defaultInstance = _pDefaultClass->InstanceCreate();
         pInstance = _defaultInstance;
      }
   }
   return pInstance;
}

//============================================================
// <T>收集一个实例。</T>
//
// @param pName 名称
// @return 实例
//============================================================
FInstance* FClassInstanceFactory::Get(TCharC* pName){
   MO_CHECK(pName, return NULL);
   FInstance* pInstance = Find(pName);
   MO_FATAL_CHECK(pInstance, return NULL, TC("Can't find insance. (name=%s)"), pName);
   return pInstance;
}

//============================================================
// <T>根据名称释放一个实例。</T>
//
// @param pName 名称
//============================================================
TResult FClassInstanceFactory::Free(TCharC* pName){
   MO_CHECK(pName, return ENull);
   FInstance* pInstance = _pInstances->Find(pName);
   if(pInstance != NULL){
      _pInstances->Remove(pName);
      MO_DELETE(pInstance);
   }
   return ESuccess;
}

//============================================================
// <T>获得运行信息。</T>
//
// @param pDump 运行内容
// @param capacity 容量
// @return 运行信息
//============================================================
TCharC* FClassInstanceFactory::Dump(TChar* pDump, TInt capacity){
   MO_ASSERT(pDump);
   MO_ASSERT(capacity > 0);
   TStringRefer dump(pDump, capacity);
   dump.AppendFormat(TC("count=%d, instance=%d"), _pClasses->Count(), _pInstances->Count());
   return pDump;
}

MO_NAMESPACE_END
