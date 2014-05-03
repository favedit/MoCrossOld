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
// <T>根据类型获得渲染参数集合。</T>
//
// @param shaderCd 渲染类型
// @return 渲染参数集合
//============================================================
GRenderShaderParameterDictionary& FRenderProgram::Parameters(ERenderShader shaderCd){
   if(shaderCd == ERenderShader_Vertex){
      return _pVertexShader->Parameters();
   }else if(shaderCd == ERenderShader_Fragment){
      return _pFragmentShader->Parameters();
   }
   return _parameters;
}

//============================================================
// <T>根据类型获得渲染参数。</T>
//
// @param shaderCd 渲染类型
// @return 渲染参数
//============================================================
FRenderShaderParameter* FRenderProgram::ParameterFind(ERenderShader shaderCd, TCharC* pName){
   FRenderShaderParameter* pParameter = NULL;
   if(shaderCd == ERenderShader_Vertex){
      pParameter = _pVertexShader->ParameterFind(pName);
   }else if(shaderCd == ERenderShader_Fragment){
      pParameter = _pFragmentShader->ParameterFind(pName);
   }else{
      pParameter = _parameters.Find(pName);
   }
   return pParameter;
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
