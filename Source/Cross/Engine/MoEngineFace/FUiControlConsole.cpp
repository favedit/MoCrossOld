#include "MoFmCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造控件对象控制台。</T>
//============================================================
FUiControlConsole::FUiControlConsole(){
   _pPools = MO_CREATE(FUiControlPoolCollection);
}

//============================================================
// <T>析构显示对象控制台。</T>
//============================================================
FUiControlConsole::~FUiControlConsole(){
   MO_DELETE(_pPools);
}

//============================================================
// <T>配置处理</T>
//
// @return 处理结果
//============================================================
TResult FUiControlConsole::Setup(){
   return ESuccess;
}

//============================================================
// <T>获得缓冲池</T>
//
// @param typecCd 类型
// @param pClassName 类名称
// @return 缓冲池
//============================================================
FUiControlPool* FUiControlConsole::PoolFind(EControlType typeCd, TCharC* pClassName){
   TInt count = _pPools->Count();
   for(TInt n = 0; n < count; n++){
      FUiControlPool* pPool = _pPools->Get(n);
      if(pPool->TypeCd() != typeCd){
         continue;
      }
      if(pClassName != NULL){
         if(!RString::Equals(pPool->ClassName(), pClassName)){
            continue;
         }
      }
      return pPool;
   }
   return NULL;
}

//============================================================
// <T>注册缓冲池。</T>
//
// @param pPool 缓冲池
// @return 处理结果
//============================================================
TResult FUiControlConsole::PoolRegister(FUiControlPool* pPool){
   _pPools->Push(pPool);
   return ESuccess;
}

//============================================================
// <T>注销缓冲池。</T>
//
// @param pPool 缓冲池
// @return 处理结果
//============================================================
TResult FUiControlConsole::PoolUnregister(FUiControlPool* pPool){
   _pPools->Remove(pPool);
   return ESuccess;
}

//============================================================
// <T>收集一个指定类型的粒子对象。</T>
//
// @param typeCd 类型
// @param pClassName 类名称
// @return 粒子对象
//============================================================
FUiControl* FUiControlConsole::Alloc(EControlType typeCd, TCharC* pClassName){
   FUiControlPool* pPool = PoolFind(typeCd, pClassName);
   MO_FATAL_CHECK(pPool, return NULL,
         "Alloc control failure. (type_cd=%d, class_name=%s)", typeCd, pClassName);
   FUiControl* pControl = pPool->Alloc();
   MO_CHECK(pControl, return NULL);
   return pControl;
}

//============================================================
// <T>释放一个指定类型的粒子对象。</T>
//
// @return 粒子对象
//============================================================
TResult FUiControlConsole::Free(FUiControl* pControl){
   MO_CHECK(pControl, return ENull);
   // 获得属性
   EControlType controlCd = pControl->ControlCd();
   TCharC* pClassName = pControl->ClassName();
   //pControl->Cla
   FUiControlPool* pPool = PoolFind(controlCd, pClassName);
   MO_FATAL_CHECK(pPool, return ENull,
         "Alloc control failure. (type_cd=%d, class_name=%s)", controlCd, pClassName);
   pPool->Free(pControl);
   return ESuccess;
}

//============================================================
// <T>释放处理。</T>
//
// @return 处理结果
//============================================================
TResult FUiControlConsole::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
