#include "MoFrContentPipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FContentAssetPipeline, FContentPipeline);

//============================================================
// <T>构造内容资源管道。</T>
//============================================================
FContentAssetPipeline::FContentAssetPipeline(){
}

//============================================================
// <T>析构内容资源管道。</T>
//============================================================
FContentAssetPipeline::~FContentAssetPipeline(){
}

//============================================================
// <T>数据处理。</T>
//
// @return 处理结果
//============================================================
TResult FContentAssetPipeline::Process(FContentLoader* pLoader){
   MO_CHECK(pLoader, return ENull);
   TCharC* pAssetName = pLoader->AssetName();
   MO_CHECK(pAssetName, return ENull);
   // 打开资源
   GAssetStreamPtr stream = RAssetManager::Instance().OpenAssetStream(pAssetName);
   MO_ERROR_CHECK(stream.IsValid(), return EFailure, "Open stream failure. (asset=%s)", pAssetName);
   // 读取数据
   FContent* pContent = pLoader->Content();
   pContent->Unserialize(stream);
   pContent->Complete();
   return ESuccess;
}

MO_NAMESPACE_END
