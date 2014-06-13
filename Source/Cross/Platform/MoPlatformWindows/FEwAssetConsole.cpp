#include "MoEwAsset.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEwAssetConsole, FAssetConsole);

//============================================================
// <T>构造资源控制台。</T>
//============================================================
FEwAssetConsole::FEwAssetConsole(){
}

//============================================================
// <T>析构资源控制台。</T>
//============================================================
FEwAssetConsole::~FEwAssetConsole(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FEwAssetConsole::Setup(){
   // 获得应用路径
   TCharC* pApplicationPath = RApplication::Instance().Parameters()->FindValue("-application");
   SetDirectory(pApplicationPath);
   // 注册资源路径
   TFsPath path;
   path.AssignFormat("%s/Asset", pApplicationPath);
   path.Replace('\\', '/');
   RegisterSpace("asset", path);
   // 注册脚本路径
   path.AssignFormat("%s/Script", pApplicationPath);
   path.Replace('\\', '/');
   RegisterSpace("script", path);
   //............................................................
   // 注册渲染器路径
   TCharC* pSystemPath = RApplication::Instance().Parameters()->FindValue("-system");
   if(pSystemPath == NULL){
      pSystemPath = pApplicationPath;
   }
   path.AssignFormat("%s/Shader", pSystemPath);
   path.Replace('\\', '/');
   RegisterSpace("shader", path);
   return ESuccess;
}

//============================================================
// <T>打开一个资源目录。</T>
//
// @param pDirectory 目录名称
// @return 资源目录
//============================================================
FAssetDirectory* FEwAssetConsole::OpenDirectory(TCharC* pDirectory){
   return NULL;
}

//============================================================
// <T>打开一个资源。</T>
//
// @param pName 名称
// @return 资源
//============================================================
FAsset* FEwAssetConsole::OpenAsset(TCharC* pName){
   return NULL;
}

//============================================================
// <T>打开一个文本数据。</T>
//
// @param pSource 文本数据
// @param pName 名称
// @return 处理结果
//============================================================
TResult FEwAssetConsole::OpenAssetString(MString* pSource, TCharC* pName){
   // 检查参数
   MO_CHECK(pSource, return ENull);
   MO_CHECK(pName, return ENull);
   // 获得名称
   TFsFileName fileName;
   TResult result = ParsePath(&fileName, pName);
   if(result != ESuccess){
      MO_FATAL("Parse file name failure. (pName=%s)", pName);
      return result;
   }
   // 测试文件是否存在
   if(!RFile::ExistFile(fileName)){
      MO_FATAL("Asset file is not exists. (file_name=%s)", (TCharC*)fileName);
      return EFailure;
   }
   // 打开文件
   FFileString* pFile = MO_CREATE(FFileString);
   pFile->LoadFile(fileName);
   TInt length = pFile->Length();
   // 接收数据
   pSource->ForceLength(length + 1);
   pSource->Assign((TCharC*)pFile->MemoryC(), length);
   // 释放文件
   MO_DELETE(pFile);
   MO_INFO("Load asset string success. (name=%s, length=%d)", pName, length);
   return ESuccess;
}

//============================================================
// <T>打开一个资源数据。</T>
//
// @param pData 数据
// @param pName 名称
// @return 资源流
//============================================================
TResult FEwAssetConsole::OpenData(FByteStream* pData, TCharC* pName){
   // 检查参数
   MO_CHECK(pData, return ENull);
   MO_CHECK(pName, return ENull);
   // 获得名称
   TFsFileName fileName;
   TResult result = ParsePath(&fileName, pName);
   if(result != ESuccess){
      MO_FATAL("Parse file name failure. (pName=%s)", pName);
      return result;
   }
   // 测试文件是否存在
   if(!RFile::ExistFile(fileName)){
      MO_FATAL("Asset file is not exists. (file_name=%s)", (TCharC*)fileName);
      return EFailure;
   }
   // 打开文件
   FByteFile* pFile = MO_CREATE(FByteFile);
   pFile->LoadFile(fileName);
   TInt length = pFile->Length();
   // 接收数据
   pData->Assign(pFile->MemoryC(), length);
   // 释放文件
   MO_DELETE(pFile);
   MO_INFO("Load asset stream success. (name=%s, length=%d)", pName, length);
   return result;
}

MO_NAMESPACE_END
