#include "MoErPipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FSimplePipeline, FPipeline);

//============================================================
// <T>构造简单渲染管道。</T>
//============================================================
FSimplePipeline::FSimplePipeline(){
   _name = "simple";
}

//============================================================
// <T>析构简单渲染管道。</T>
//============================================================
FSimplePipeline::~FSimplePipeline(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FSimplePipeline::OnSetup(){
   TResult result = FPipeline::OnSetup();
   // 创建颜色渲染过程
   FSimpleColorPass* pPass = FSimpleColorPass::InstanceCreate();
   pPass->SetPipeline(this);
   pPass->Setup();
   _pPasses->Push(pPass);
   return result;
}

MO_NAMESPACE_END
