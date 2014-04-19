#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_ABSTRACT_IMPLEMENT_INHERITS(FRenderProgram, FRenderObject);

//============================================================
// <T>构造渲染程序。</T>
//============================================================
FRenderProgram::FRenderProgram(){
   MO_CLEAR(_pVertexShader);
   MO_CLEAR(_pFragmentShader);
}

//============================================================
// <T>析构渲染程序。</T>
//============================================================
FRenderProgram::~FRenderProgram(){
   MO_DELETE(_pVertexShader);
   MO_DELETE(_pFragmentShader);
}

//============================================================
// <T>生成顶点渲染程序。</T>
//
// @param pSource 渲染来源
// @return 处理结果
//============================================================
TResult FRenderProgram::MakeVertexSource(FRenderSource* pSource){
   return ESuccess;
}

//============================================================
// <T>生成像素渲染程序。</T>
//
// @param pSource 渲染来源
// @return 处理结果
//============================================================
TResult FRenderProgram::MakeFragmentSource(FRenderSource* pSource){
   return ESuccess;
}

MO_NAMESPACE_END
