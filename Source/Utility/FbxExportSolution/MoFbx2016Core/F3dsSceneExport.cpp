#include "MoFbxCore.h"

//============================================================
// <T>回调器定义。</T>
//============================================================
/*
MO_EXTERN_C{
   TInt Exporter();
   typedef TInt (*CallBackExporter)(TCharC* pFileName, ExpInterface* piExpInterface, Interface* piInterface, TBool suppressPrompts, TUint32 options);
}

//============================================================
// <T>构造场景导出器。</T>
//
// @return 实例指针
//============================================================
PPtr F3dsSceneExport::Construct(){
   return this;
}

//============================================================
// <T>析构场景导出器。</T>
//============================================================
void F3dsSceneExport::Dispose(){
}

//============================================================
// <T>获得支持的扩展名个数。</T>
//
// @return 扩展名个数
//============================================================
TInt32 F3dsSceneExport::ExtCount(){
   return 1;
}

//============================================================
// <T>获得指定索引的的扩展名。</T>
//
// @return 扩展名个数
//============================================================
TCharC* F3dsSceneExport::Ext(TInt32 index){
   switch(index) {
   case 0:
      return MO_3E_EXT;
   }
   return MO_3E_EMPTY;
}

//============================================================
// <T>获得概要描述。</T>
//
// @return 概要描述
//============================================================
TCharC* F3dsSceneExport::ShortDesc(){
   return MO_3E_DESC_SHORT;
}

//============================================================
// <T>获得详细描述。</T>
//
// @return 详细描述
//============================================================
TCharC* F3dsSceneExport::LongDesc(){
   return MO_3E_DESC_LONG;
}

//============================================================
// <T>获得作者名称。</T>
//
// @return 作者名称
//============================================================
TCharC* F3dsSceneExport::AuthorName(){
   return MO_3E_AUTHOR;
}

//============================================================
// <T>获得版权内容。</T>
//
// @return 版权内容
//============================================================
TCharC* F3dsSceneExport::CopyrightMessage(){
   return MO_3E_COPYRIGHT;
}

//============================================================
// <T>获得附带内容1。</T>
//
// @return 附带内容
//============================================================
TCharC* F3dsSceneExport::OtherMessage1(){
   return MO_3E_EMPTY;
}

//============================================================
// <T>获得附带内容2。</T>
//
// @return 附带内容
//============================================================
TCharC* F3dsSceneExport::OtherMessage2(){
   return MO_3E_EMPTY;
}

//============================================================
// <T>获得当前版本。</T>
//
// @return 当前版本
//============================================================
TUint32 F3dsSceneExport::Version(){
   return MO_3E_VERSION;
}

//============================================================
// <T>导出处理。</T>
//
// @return 处理结果
// @return 处理结果
//============================================================
TInt32 F3dsSceneExport::DoExport(TCharC* pFileName, ExpInterface* piExpInterface, Interface* piInterface, TInt32 suppressPrompts, TUlong options){
   MO_INFO(TC("3DS scene export start."));
   // 动态加载导出库
   HMODULE hModule = LoadLibraryEx(MO_3E_LIBRARY, NULL, 0);
   MO_INFO(TC("Find Mo3dsExporter library. (handle=0x%08X)"), hModule);
   if(NULL == hModule){
      MO_INFO(TC("3DS scene export failure. (Module is null)"));
      return IMPEXP_FAIL;
   }
   // 获得函数指针
   CallBackExporter pfExporter = (CallBackExporter)GetProcAddress(hModule, "DoExport");
   MO_INFO(TC("Find DoExport method. (handle=0x%08X)"), pfExporter);
   if(NULL == pfExporter){
      // 返回结果
      TBool check = FreeLibrary(hModule);
      if(!check){
         MO_INFO(TC("Free library failure. (result=%d)"), check);
      }
      MO_INFO(TC("3DS scene export failure. (Method is null)"));
      return IMPEXP_FAIL;
   }
   // 调用函数处理
   TInt result = pfExporter(pFileName, piExpInterface, piInterface, suppressPrompts, options);
   // 释放导出库
   TBool check = FreeLibrary(hModule);
   if(!check){
      MO_INFO(TC("Free library failure. (handle=0x%08X, result=%d)"), hModule, check);
   }
   MO_INFO(TC("Free library success. (handle=0x%08X, result=%d)"), hModule, check);
   // 返回结果
   MO_INFO(TC("3DS scene export success."));
   return (TInt32)result;
}

//============================================================
// <T>显示设置项。</T>
//
// @param ext 扩展名索引
// @param options 设置内容
// @return 处理结果
//============================================================
TBool F3dsSceneExport::SupportsOptions(TInt ext, TUint32 options){
   return (SCENE_EXPORT_SELECTED == options) ? TRUE : FALSE;
}

//============================================================
// <T>显示关于。</T>
//============================================================
void F3dsSceneExport::ShowAbout(HWND hWnd){
}
*/
