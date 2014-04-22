#include "MoFrContentPipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FContentPipeline, FInstance);

//============================================================
// <T>构造内容管道。</T>
//============================================================
FContentPipeline::FContentPipeline(){
}

//============================================================
// <T>析构内容管道。</T>
//============================================================
FContentPipeline::~FContentPipeline(){
}

//============================================================
// <T>数据处理。</T>
//
// @param pLoader 加载器
// @return 处理结果
//============================================================
TResult FContentPipeline::Process(FContentLoader* pLoader){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

MO_NAMESPACE_END
