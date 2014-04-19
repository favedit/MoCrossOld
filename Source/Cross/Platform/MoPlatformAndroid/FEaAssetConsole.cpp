#include "MoEaAsset.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEaAssetConsole, FAssetConsole);

//============================================================
// <T>构造资源控制台。</T>
//============================================================
FEaAssetConsole::FEaAssetConsole(){
   MO_CLEAR(_pManager);
}

//============================================================
// <T>析构资源控制台。</T>
//============================================================
FEaAssetConsole::~FEaAssetConsole(){
}

//============================================================
// <T>关联处理。</T>
//
// @param pEnvironment 环境
// @param assetManager 资产管理器
//============================================================
void FEaAssetConsole::Link(JNIEnv* pEnvironment, jobject assetManager){
   _pManager = AAssetManager_fromJava(pEnvironment, assetManager);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FEaAssetConsole::Setup(){
   RegisterSpace("asset", "Asset");
   return ESuccess;
}

//============================================================
// <T>打开一个资源目录。</T>
//
// @param pDirectory 目录名称
// @return 资源目录
//============================================================
FAssetDirectory* FEaAssetConsole::OpenDirectory(TCharC* pDirectory){
   MO_CHECK(pDirectory, return NULL);
   // 获得名称
   TFsFileName directory;
   TResult result = ParsePath(&directory, pDirectory);
   if(result != ESuccess){
      MO_FATAL("Parse file name failure. (directory=%s)", pDirectory);
      return NULL;
   }
   // 打开文件
   AAssetDir* pNative = AAssetManager_openDir(_pManager, pDirectory);
   MO_ERROR_CHECK(pNative, return NULL,
         "Open asset directory failure. (directory=%s)", (TCharC*)directory);
   // 创建资源
   FEaAssetDirectory* pAssetDirectory = MO_CREATE(FEaAssetDirectory);
   pAssetDirectory->SetNative(pNative);
   return pAssetDirectory;
}

//============================================================
// <T>打开一个资源。</T>
//
// @param pFileName 文件名称
// @return 资源
//============================================================
FAsset* FEaAssetConsole::OpenAsset(TCharC* pName){
   MO_CHECK(pName, return NULL);
   // 获得名称
   TFsFileName fileName;
   TResult result = ParsePath(&fileName, pName);
   if(result != ESuccess){
      MO_FATAL("Parse file name failure. (pName=%s)", pName);
      return NULL;
   }
   // 打开文件
   AAsset* pNative = AAssetManager_open(_pManager, fileName, AASSET_MODE_UNKNOWN);
   MO_ERROR_CHECK(pNative, return NULL,
         "Open asset stream failure. (file_name=%s)", (TCharC*)fileName);
   // 创建资源
   FEaAsset* pAssert = MO_CREATE(FEaAsset);
   pAssert->SetNative(pNative);
   return pAssert;
}

//============================================================
// <T>打开一个文本数据。</T>
//
// @param pSource 文本数据
// @param pName 名称
// @return 处理结果
//============================================================
TResult FEaAssetConsole::OpenAssetString(MString* pSource, TCharC* pName){
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
   // 打开文件
   AAsset* pNative = AAssetManager_open(_pManager, fileName, AASSET_MODE_UNKNOWN);
   MO_ERROR_CHECK(pNative, return EFailure,
         "Open asset stream failure. (file_name=%s)", (TCharC*)fileName);
   // 获得大小
   TInt length = AAsset_getLength(pNative);
   if(length == 0){
      AAsset_close(pNative);
      MO_ERROR("Open asset length is zero. (file_name=%s, length=%d)", (TCharC*)fileName, length);
      return EFailure;
   }
   // 加载数据
   pSource->ForceLength(length + 1);
   TChar* pMemory = pSource->Memory();
   TInt readLength = AAsset_read(pNative, pMemory, length);
   if(readLength <= 0 ){
      MO_ERROR("Read asset failure. (file_name=%s, read_length=%d)", (TCharC*)fileName, readLength);
      AAsset_close(pNative);
      return EFailure;
   }
   pSource->SetLength(length);
   // 关闭数据
   AAsset_close(pNative);
   MO_INFO("Load data success. (file_name=%s, length=%d)", (TCharC*)fileName, length);
   return ESuccess;
}

//============================================================
// <T>加载一个数据。</T>
//
// @param pData 数据
// @param pFileName 文件名称
// @return 处理结果
//============================================================
TResult FEaAssetConsole::OpenData(FBytes* pData, TCharC* pName){
   MO_CHECK(pData, return ENull);
   MO_CHECK(pName, return ENull);
   // 获得名称
   TFsFileName fileName;
   TResult result = ParsePath(&fileName, pName);
   if(result != ESuccess){
      MO_FATAL("Parse file name failure. (pName=%s)", pName);
      return result;
   }
   // 打开文件
   AAsset* pNative = AAssetManager_open(_pManager, fileName, AASSET_MODE_UNKNOWN);
   MO_ERROR_CHECK(pNative, return EFailure,
         "Open asset stream failure. (file_name=%s)", (TCharC*)fileName);
   // 获得大小
   TInt length = AAsset_getLength(pNative);
   if(length == 0){
      AAsset_close(pNative);
      MO_ERROR("Open asset length is zero. (file_name=%s, length=%d)", (TCharC*)fileName, length);
      return EFailure;
   }
   // 加载数据
   pData->ForceLength(length);
   TInt readLength = AAsset_read(pNative, pData->Memory(), length);
   if(readLength <= 0 ){
      MO_ERROR("Read asset failure. (file_name=%s, read_length=%d)", (TCharC*)fileName, readLength);
      AAsset_close(pNative);
      return EFailure;
   }
   // 关闭数据
   AAsset_close(pNative);
   MO_INFO("Load data success. (file_name=%s, length=%d)", (TCharC*)fileName, length);
   return ESuccess;
}

MO_NAMESPACE_END
