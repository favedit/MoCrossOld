#include "MoFrContent.h"

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
// @return 处理结果
//============================================================
TResult FContentPipeline::Process(){
   return ESuccess;
}

MO_NAMESPACE_END
