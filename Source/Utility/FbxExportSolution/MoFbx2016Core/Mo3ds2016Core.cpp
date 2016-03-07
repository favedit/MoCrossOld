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

#include "MoFbxDefine.h"
#include "MoFbxParser.h"

MO_NAMESPACE_INCLUDE;

//============================================================
// <T>加载动态库。</T>
//
// @param hInstance 实例居柄
// @param reason 加载原因
// @param pReserved 保留
// @return 类名
//============================================================
/*TBool WINAPI DllMain(TInstance hInstance, TUint32 reason, TAny* pReserved){
   switch(reason){
      // 进程开始处理
      case DLL_PROCESS_ATTACH:{
         DisableThreadLibraryCalls(hInstance);
         //MoInitialize();
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
         //MoRelease();
         break;
      }
   }
   return ETrue;
}
*/