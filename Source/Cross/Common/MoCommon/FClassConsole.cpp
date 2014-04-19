#include "MoCmClass.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造类控制台。</T>
//============================================================
FClassConsole::FClassConsole(){
   _looperLimit = 16;
}

//============================================================
// <T>析构类控制台。</T>
//============================================================
FClassConsole::~FClassConsole(){
}

//============================================================
// <T>注册类对象。</T>
//
// @param pClass 类对象
// @return 类对象
//============================================================
FClass* FClassConsole::Register(FClass* pClass){
   MO_ASSERT(pClass);
   // 检查类名称
   TCharC* pClassName = pClass->Name();
   if(_classes.Contains(pClassName)){
      MO_STATIC_FATAL(TC("Class is already exists. (class_name=%s)"), pClassName);
   }
   // 保存类对象
   _classes.Set(pClassName, pClass);
   _looper.Push(pClass);
   return pClass;
}

//============================================================
// <T>查找类对象。</T>
//
// @param pClassName 类名称
// @return 类对象
//============================================================
FClass* FClassConsole::FindClass(TCharC* pClassName){
   FClass* pClass = NULL;
   if(pClassName != NULL){
      pClass = _classes.Find(pClassName);
   }
   return pClass;
}

//============================================================
// <T>创建类实例。</T>
//
// @param pClassName 类名称
// @return 类实例
//============================================================
FInstance* FClassConsole::InstanceCreate(TCharC* pClassName){
   FClass* pClass = FindClass(pClassName);
   return InstanceCreate(pClass);
}

//============================================================
// <T>创建类实例。</T>
//
// @param pClass 类对象
// @return 类实例
//============================================================
FInstance* FClassConsole::InstanceCreate(FClass* pClass){
   MO_ASSERT(pClass);
   FInstance* pInstance = pClass->InstanceCreate();
   return pInstance;
}

//============================================================
// <T>收集类实例。</T>
//
// @param pClassName 类名称
// @return 类实例
//============================================================
FInstance* FClassConsole::InstanceAlloc(TCharC* pClassName){
   FClass* pClass = FindClass(pClassName);
   return InstanceCreate(pClass);
}

//============================================================
// <T>收集类实例。</T>
//
// @param pClass 类对象
// @return 类实例
//============================================================
FInstance* FClassConsole::InstanceAlloc(FClass* pClass){
   MO_ASSERT(pClass);
   FInstance* pInstance = pClass->InstanceAlloc();
   return pInstance;
}

//============================================================
// <T>释放类实例。</T>
//
// @param pInstance 类实例
//============================================================
void FClassConsole::InstanceFree(FInstance* pInstance){
   MO_ASSERT(pInstance);
   FClass* pClass = pInstance->GetClass();
   pClass->InstanceFree(pInstance);
}

//============================================================
// <T>删除类实例。</T>
//
// @param pInstance 类实例
//============================================================
void FClassConsole::InstanceDelete(FInstance* pInstance){
   MO_ASSERT(pInstance);
   FClass* pClass = pInstance->GetClass();
   pClass->InstanceDelete(pInstance);
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FClassConsole::Process(){
   _looper.Record();
   for(TInt n = 0; n < _looperLimit; n++){
      FClass* pClass = _looper.Next();
      if(pClass == NULL){
         break;
      }
      pClass->Process();
   }
   return ESuccess;
}

//============================================================
// <T>跟踪激活信息。</T>
//============================================================
void FClassConsole::TrackActive(){
   TInt count = _classes.Count();
   MO_DEBUG(TC("Class track. (count=%d)"));
   TDictionaryIteratorC<FClass*> iterator = _classes.IteratorC();
   while(iterator.Next()){
      FClass* pClass = *iterator;
      if(pClass->ActiveCount() > 0){
         TFsDump dump;
         pClass->Dump(dump.Memory(), dump.Size());
         MO_DEBUG(TC("   %s"), (TCharC*)dump);
      }
   }
}

//============================================================
// <T>跟踪全部信息。</T>
//============================================================
void FClassConsole::TrackAll(){
   TInt count = _classes.Count();
   MO_DEBUG(TC("Class track. (count=%d)"));
   TDictionaryIteratorC<FClass*> iterator = _classes.IteratorC();
   while(iterator.Next()){
      FClass* pClass = *iterator;
      TFsDump dump;
      pClass->Dump(dump.Memory(), dump.Size());
      MO_DEBUG(TC("   %s"), (TCharC*)dump);
   }
}

MO_NAMESPACE_END
