#include "MoCmClassBase.h"
#include "MoCmClass.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造类对象。</T>
//============================================================
FClass::FClass(){
   _looperLimit = 64;
   MO_CLEAR(_pName);
   MO_CLEAR(_pParentClassName);
   MO_CLEAR(_pParentClass);
   _memorySize = 0;
   _activeCount = 0;
   _allocTotal = 0;
}

//============================================================
// <T>析构类对象。</T>
//============================================================
FClass::~FClass(){
}

//============================================================
// <T>获得父类对象。</T>
//
// @return 类对象
//============================================================
FClass* FClass::ParentClass(){
   if(_pParentClass == NULL){
      if(_pParentClassName != NULL){
         _pParentClass = RClassManager::Instance().FindClass(_pParentClassName);
      }
   }
   return _pParentClass;
}

//============================================================
// <T>判断当前实例是否继承指定类名称。</T>
//
// @param pClassName 类名称
// @return 是否为真
//============================================================
TBool FClass::IsInheritsFrom(TCharC* pClassName){
   FClass* pFindClass = RClassManager::Instance().FindClass(pClassName);
   return IsInheritsFrom(pFindClass);
}

//============================================================
// <T>判断当前实例是否继承指定类对象。</T>
//
// @param pClass 类对象
// @return 是否为真
//============================================================
TBool FClass::IsInheritsFrom(FClass* pClass){
   MO_ASSERT(pClass);
   FClass* pFindClass = this;
   while(pFindClass != NULL){
      if(pFindClass == pClass){
         return ETrue;
      }
      pFindClass = pFindClass->ParentClass();
   }
   return EFalse;
}

//============================================================
// <T>创建一个实例，并且保证实例是从指定父类对象继承。</T>
//
// @param pClass 父类对象
// @return 实例
//============================================================
FInstance* FClass::InstanceInheritCreate(FClass* pClass){
   FInstance* pInstance = InstanceCreate();
   RClassManager::CheckInherit(pInstance, pClass);
   return pInstance;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FClass::Process(){
   _looper.Record();
   for(TInt n = 0; n < _looperLimit; n++){
      FInstance* pInstance = _looper.Next();
      if(pInstance == NULL){
         break;
      }
      // 释放唯一应用对象
      if(pInstance->ReferCount() == 1){
         _looper.RemoveCurrent();
         pInstance->ReferDecrease();
      }
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
TCharC* FClass::Dump(TChar* pDump, TInt capacity){
   TStringRefer dump(pDump, capacity);
   dump.AppendFormat(TC("name=%s"), _pName);
   // 父类信息
   if(_pParentClass != NULL){
      dump.Append(TC(" parent=("));
      FClass* pParentClass = _pParentClass;
      while(pParentClass != NULL){
         dump.Append(pParentClass->Name());
         pParentClass = pParentClass->ParentClass();
         if(pParentClass != NULL){
            dump.Append(TC("->"));
         }
      }
      dump.Append(TC(")"));
   }
   dump.AppendFormat(TC(" size=%d, count=%lld/%lld"), _memorySize, _activeCount, _allocTotal);
   return pDump;
}

MO_NAMESPACE_END
