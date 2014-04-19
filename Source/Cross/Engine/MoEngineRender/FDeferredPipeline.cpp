#include "MoErPipeline.h"

MO_NAMESPACE_BEGIN
   
MO_CLASS_IMPLEMENT_INHERITS(FDeferredPipeline, FPipeline);

//============================================================
// <T>构造延迟渲染管道。</T>
//============================================================
FDeferredPipeline::FDeferredPipeline(){
   _name = "deferred";
}

//============================================================
// <T>析构延迟渲染管道。</T>
//============================================================
FDeferredPipeline::~FDeferredPipeline(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FDeferredPipeline::OnSetup(){
   TResult result = FPipeline::OnSetup();
   return result;
}

MO_NAMESPACE_END
