#include "MoErPipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderPipelinePass, FPipelinePass);

//============================================================
// <T>构造渲染管道过程。</T>
//============================================================
FRenderPipelinePass::FRenderPipelinePass(){
   MO_CLEAR(_pPipeline);
}

//============================================================
// <T>析构渲染管道过程。</T>
//============================================================
FRenderPipelinePass::~FRenderPipelinePass(){
}

//============================================================
// <T>绘制区域处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderPipelinePass::DrawRegion(FRenderRegion* pRegion){
   FPipelinePass::DrawRegion(pRegion);
   return ESuccess;
}

MO_NAMESPACE_END
