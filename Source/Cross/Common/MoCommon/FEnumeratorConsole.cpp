#include "MoCmEnumerator.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEnumeratorConsole, FConsole);

//============================================================
// <T>构造枚举器控制台。</T>
//============================================================
FEnumeratorConsole::FEnumeratorConsole(){
}

//============================================================
// <T>析构枚举器控制台。</T>
//============================================================
FEnumeratorConsole::~FEnumeratorConsole(){
}

//============================================================
// <T>注册一个枚举器。</T>
//
// @param pEnumerator 枚举
// @return 处理结果
//============================================================
TResult FEnumeratorConsole::Register(FEnumerator* pEnumerator){
   MO_CHECK(pEnumerator, return ENull);
   TCharC* pName = pEnumerator->Name();
   MO_CHECK(pName, return ENull);
   // 检查重复
   FEnumerator* pFind = _enumerators.Find(pName);
   if(pFind != NULL){
      MO_FATAL(TC("Duplicate enumerator. (name=%s)"), pName);
      return EDuplicate;
   }
   // 设置内容
   _enumerators.Set(pName, pEnumerator);
   return ESuccess;
}

//============================================================
// <T>注销一个枚举器。</T>
//
// @param pEnumerator 枚举
// @return 处理结果
//============================================================
TResult FEnumeratorConsole::Unrgister(FEnumerator* pEnumerator){
   MO_CHECK(pEnumerator, return ENull);
   TCharC* pName = pEnumerator->Name();
   MO_CHECK(pName, return ENull);
   // 检查存在性
   FEnumerator* pFind = _enumerators.Find(pName);
   if(pFind == NULL){
      MO_FATAL(TC("Not exists enumerator. (name=%s)"), pName);
      return ENotExists;
   }
   // 设置内容
   _enumerators.Remove(pName);
   return ESuccess;
}

//============================================================
// <T>注销一个枚举器。</T>
//
// @param pEnumerator 枚举
// @return 处理结果
//============================================================
FEnumerator* FEnumeratorConsole::Sync(TCharC* pName){
   MO_CHECK(pName, return NULL);
   FEnumerator* pEnumerator = Find(pName);
   if(pEnumerator == NULL){
      // 创建枚举器
      pEnumerator = FEnumerator::InstanceCreate();
      pEnumerator->SetName(pName);
      Register(pEnumerator);
   }
   return pEnumerator;
}

MO_NAMESPACE_END
