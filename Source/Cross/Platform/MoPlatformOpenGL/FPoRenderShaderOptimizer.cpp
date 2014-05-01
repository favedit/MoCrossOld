#include "MoPoRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPoRenderShaderOptimizer, FRenderShaderOptimizer);

//============================================================
// <T>构造渲染脚本优化器。</T>
//============================================================
FPoRenderShaderOptimizer::FPoRenderShaderOptimizer(){
}

//============================================================
// <T>析构渲染脚本优化器。</T>
//============================================================
FPoRenderShaderOptimizer::~FPoRenderShaderOptimizer(){
}

//============================================================
// <T>转换脚本。</T>
//
// @param pOutputScript 输出脚本
// @param pInputScript 输入脚本
// @return 处理结果
//============================================================
TResult FPoRenderShaderOptimizer::Convert(MString* pOutputScript, MString* pInputScript){
   pOutputScript->AssignPointer(pInputScript);
   return ESuccess;
}

MO_NAMESPACE_END
