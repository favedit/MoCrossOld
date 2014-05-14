#include "MoCmClass.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>根据名称查找一个类对象。</T>
//
// @param pClassName 类名称
// @return 类对象
//============================================================
FClass* RClassManager::FindClass(TCharC* pClassName){
   FClassConsole* pClassConsole = SafeInstancePtr();
   return pClassConsole->FindClass(pClassName);
}

//============================================================
// <T>注册一个类对象。</T>
//
// @param pClass 类对象
// @return 类对象
//============================================================
FClass* RClassManager::Register(FClass* pClass){
   FClassConsole* pClassConsole = SafeInstancePtr();
   return pClassConsole->Register(pClass);
}

//============================================================
// <T>测试实例类对象是否是指定类冲派生。</T>
//
// @param pInstanceClass 实例类对象
// @param pClass 类对象
// @return 是否派生
//============================================================
TBool RClassManager::TestInherit(FClass* pInstanceClass, FClass* pClass){
   FClass* pFindClass = pInstanceClass;
   while(pFindClass != NULL){
      if(pFindClass == pClass){
         return ETrue;
      }
      pFindClass = pFindClass->ParentClass();
   }
   return EFalse;
}

//============================================================
// <T>测试实例类对象是否是指定类冲派生。</T>
//
// @param pInstanceClass 实例类对象
// @param pClass 类对象
// @return 是否派生
//============================================================
TBool RClassManager::TestInherit(FInstance* pInstance, FClass* pClass){
   MO_ASSERT(pInstance);
   FClass* pInstanceClass = pInstance->GetClass();
   MO_ASSERT(pInstanceClass);
   return TestInherit(pInstanceClass, pClass);
}

//============================================================
// <T>检查指定类是否从父类继承。</T>
//
// @param pInstanceClass 实例类对象
// @param pClass 类对象
// @return 处理结果
//============================================================
TResult RClassManager::CheckInherit(FClass* pInstanceClass, FClass* pClass){
   MO_CHECK(pInstanceClass, return ENull);
   MO_CHECK(pClass, return ENull);
   // 父类测试
   FClass* pFindClass = pInstanceClass;
   while(pFindClass != NULL){
      if(pFindClass == pClass){
         return ESuccess;
      }
      pFindClass = pFindClass->ParentClass();
   }
   // 测试失败
   MO_STATIC_FATAL(TC("Instance class is not inherit class. (instance_class=%s, class=%s)"),
      pInstanceClass->Name(), pClass->Name());
   return EFailure;
}

//============================================================
// <T>检查指定类是否从父类继承。</T>
//
// @param pInstanceClass 实例类对象
// @param pClass 类对象
// @return 处理结果
//============================================================
TResult RClassManager::CheckInherit(FInstance* pInstance, FClass* pClass){
   MO_ASSERT(pInstance);
   FClass* pInstanceClass = pInstance->GetClass();
   MO_ASSERT(pInstanceClass);
   return CheckInherit(pInstanceClass, pClass);
}

//============================================================
// <T>检查指定类是否可以转换。</T>
// <P>向父类转换安全，向子类只能保证有继承关系，转换有可能不安全。</P>
//
// @param pInstanceClass 实例类对象
// @param pClass 类对象
// @return 处理结果
//============================================================
TResult RClassManager::CheckConvert(FClass* pInstanceClass, FClass* pClass){
   MO_CHECK(pInstanceClass, return ENull);
   MO_CHECK(pClass, return ENull);
   // 父类测试
   FClass* pFindClass = pInstanceClass;
   while(pFindClass != NULL){
      if(pFindClass == pClass){
         return ESuccess;
      }
      pFindClass = pFindClass->ParentClass();
   }
   // 子类测试
   pFindClass = pClass;
   while(pFindClass != NULL){
      if(pFindClass == pInstanceClass){
         return ESuccess;
      }
      pFindClass = pFindClass->ParentClass();
   }
   // 测试失败
   MO_STATIC_FATAL(TC("Instance class can not convert target class. (instance_class=%s, class=%s)"),
      pInstanceClass->Name(), pClass->Name());
   return EFailure;
}

//============================================================
// <T>检查指定类是否可以转换。</T>
//
// @param pInstanceClass 实例类对象
// @param pClass 类对象
// @return 处理结果
//============================================================
TResult RClassManager::CheckConvert(FInstance* pInstance, FClass* pClass){
   MO_ASSERT(pInstance);
   FClass* pInstanceClass = pInstance->GetClass();
   MO_ASSERT(pInstanceClass);
   return CheckConvert(pInstanceClass, pClass);
}

MO_NAMESPACE_END
