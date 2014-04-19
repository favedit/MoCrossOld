#include "MoErSimplePipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FSimpleColorPass, FPipelinePass);

//============================================================
// <T>构造简单颜色渲染过程。</T>
//============================================================
FSimpleColorPass::FSimpleColorPass(){
   _name = "color";
}

//============================================================
// <T>析构简单颜色渲染过程。</T>
//============================================================
FSimpleColorPass::~FSimpleColorPass(){
}

//============================================================
// <T>绘制区域处理。</T>
//
// @return 处理结果
//============================================================
TResult FSimpleColorPass::DrawRegion(FRenderRegion* pRegion){
   FPipelinePass::DrawRegion(pRegion);
   return ESuccess;
}

MO_NAMESPACE_END
