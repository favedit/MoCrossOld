//============================================================
//   DYNAMIC LINK LIBRARY : Mo3DSExport Project Overview
//------------------------------------------------------------
// 从3DS MAX中导出模型的动态链接库。
// - 内部没有导出功能只是一个桥库。
// - 目的是在不需要重启3DS MAX程序的情况下，重新动态加载变更过的动态库。
//  导出功能由另一个DLL(Mo3DSExporter)提供。
//------------------------------------------------------------
// Copyright From MAOCY
// 2010-05-02 (^o^)
//============================================================

#include "Mo3dsDefine.h"
#include "Mo3dsExport.h"

MO_NAMESPACE_INCLUDE;

//============================================================
// <T>加载动态库。</T>
//
// @param hInstance 实例居柄
// @param reason 加载原因
// @param pReserved 保留
// @return 类名
//============================================================
TBool WINAPI DllMain(TInstance hInstance, TUint32 reason, TAny* pReserved){
   switch(reason){
      // 进程开始处理
      case DLL_PROCESS_ATTACH:{
         DisableThreadLibraryCalls(hInstance);
         MoInitialize();
         break;
      }
      // 线程开始处理
      case DLL_THREAD_ATTACH:
         break;
      // 线程结束处理
      case DLL_THREAD_DETACH:
         break;
      // 进程结束处理
      case DLL_PROCESS_DETACH:{
         MoRelease();
         break;
      }
   }
   return ETrue;
}

//============================================================
// <T>获得库描述。</T>
//
// @return 描述
//============================================================
MO_3E_DECLARE TCharC* LibDescription(){
   return MO_3E_LIB_DESCRIPTION;
}

//============================================================
// <T>获得类描述器的数量。</T>
//
// @return 数量
//============================================================
MO_3E_DECLARE TInt LibNumberClasses(){
   return 1;
}

//============================================================
// <T>获得指定索引的类描述器。</T>
//
// @return 类描述器
//============================================================
MO_3E_DECLARE ClassDesc* LibClassDesc(TInt index){
   switch(index){
   case 0:
      return &g_classDesc; 
   }
   return NULL;
}

//============================================================
// <T>获得库的版本。</T>
//
// @return 版本
//============================================================
MO_EXPORT TUint32 LibVersion(){
   return VERSION_3DSMAX;
}

//============================================================
// <T>获得是否可以自动延迟。</T>
//
// @return 自动延迟
//============================================================
MO_EXPORT TUint32 CanAutoDefer(){
   return ETrue;
}
