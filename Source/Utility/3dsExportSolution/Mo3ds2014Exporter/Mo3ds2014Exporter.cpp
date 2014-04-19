//============================================================
//   DYNAMIC LINK LIBRARY : Mo3DSExporter Project Overview
//------------------------------------------------------------
// 从3DS MAX中导出模型的动态链接库。
//------------------------------------------------------------
// Copyright From MAOCY
// 2010-05-02 (^o^)
//============================================================

#include <MoCommon.h>
#include "Mo3dsDefine.h"
#include "Mo3dsExporter.h"

MO_NAMESPACE_INCLUDE;

//============================================================
// <T>加载动态库。</T>
//
// @param hInstance 实例句柄
// @param reason 加载类型
// @param pReserved 保留字
// @return 类名
//============================================================
TBool APIENTRY DllMain(HMODULE hModule, TUint32 reason, TAny* pReserved){
	switch (reason){
      case DLL_PROCESS_ATTACH:
	   case DLL_THREAD_ATTACH:
	   case DLL_THREAD_DETACH:
      case DLL_PROCESS_DETACH:
         break;
	}
	return ETrue;
}

//============================================================
// <T>导出模型加载动态库。</T>
//
// @param hInstance 实例居柄
// @param reason 加载原因
// @param pReserved 保留
// @return 类名
//============================================================
MO_EXPORT TInt __DoExport(TCharC* pFileName, ExpInterface* piExpInterface, Interface* piInterface, TBool suppressPrompts, TUint32 options){
   MO_STATIC_INFO(TC("Export 3DS scene xml. (filename=%s)"), pFileName);
   // 解析所有节点
   M3dsExporter* pExporter = MO_CREATE(F3dsGameExporter, piExpInterface->theScene, piInterface->GetTime(), piInterface);
   try{
      pExporter->SetFileName(pFileName);
      pExporter->Convert();
      pExporter->SaveAs(pFileName, TC("GB2312"));
   }catch(TInt error){
      MO_STATIC_INFO(TC("Convert scene failure. (error=%d)"), error);
      MO_DELETE(pExporter);
      return IMPEXP_FAIL;
   }
   MO_DELETE(pExporter);
   return IMPEXP_SUCCESS;
}

//============================================================
// <T>加载动态库。</T>
//
// @param hInstance 实例居柄
// @param reason 加载原因
// @param pReserved 保留
// @return 类名
//============================================================
MO_EXPORT TInt DoExport(TCharC *pFileName, ExpInterface* piExpInterface, Interface* piInterface, TBool suppressPrompts, TUint32 options){
   TInt result = IMPEXP_SUCCESS;
   MO_STATIC_INFO(TC("Export scene start."));
   try{
      result = __DoExport(pFileName, piExpInterface, piInterface, suppressPrompts, options);
   }catch(TInt error){
      MO_STATIC_INFO(TC("Export scene failure. (filename=%s, error=%d)"), pFileName, error);
      result = IMPEXP_FAIL;
   }
   MO_STATIC_INFO(TC("Export scene success."));
   return result;
}
