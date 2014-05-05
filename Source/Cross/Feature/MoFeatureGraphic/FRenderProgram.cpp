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
// <T>根据名称查找缓冲。</T>
//
// @param pName 名称
// @return 缓冲对象
//============================================================
FRenderShaderBuffer* FRenderProgram::BufferFind(TCharC* pName){
   TInt count = _buffers.Count();
   for(TInt n = 0; n < count; n++){
      FRenderShaderBuffer* pBuffer = _buffers.Get(n);
      if(RString::Equals(pBuffer->Name(), pName)){
         return pBuffer;
      }
   }
   return NULL;
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
// <T>增加一个参数。</T>
//
// @param pParameter 渲染参数
// @return 处理结果
//============================================================
TResult FRenderProgram::ParameterPush(FRenderShaderParameter* pParameter){
   MO_CHECK(pParameter, return ENull);
   _parameters.Set(pParameter->Name(), pParameter);
   return ESuccess;
}

//============================================================
// <T>根据名称查找渲染属性。</T>
//
// @param pName 名称
// @param index 索引
// @return 渲染属性
//============================================================
FRenderShaderAttribute* FRenderProgram::AttributeFind(TCharC* pName, TInt index){
   //TInt count = _attributes.Count();
   //for(TInt n = 0; n < count; n++){
   //   FRenderShaderAttribute* pAttribute = _attributes.Get(n);
   //   if(RString::Equals(pAttribute->Name(), pName)){
   //      if((index == -1) || (pAttribute->Index() == index)){
   //         return pAttribute;
   //      }
   //   }
   //}
   return NULL;
}

//============================================================
// <T>增加一个属性。</T>
//
// @param pAttribute 属性
// @return 处理结果
//============================================================
TResult FRenderProgram::AttributePush(FRenderShaderAttribute* pAttribute){
   MO_CHECK(pAttribute, return ENull);
   _attributes.Set(pAttribute->Name(), pAttribute);
   return ESuccess;
}

//============================================================
// <T>增加一个取样。</T>
//
// @param pSampler 取样
// @return 处理结果
//============================================================
TResult FRenderProgram::SamplerPush(FRenderShaderSampler* pSampler){
   MO_CHECK(pSampler, return ENull);
   _samplers.Set(pSampler->Name(), pSampler);
   return ESuccess;
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

//============================================================
// <T>绘制开始处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderProgram::DrawBegin(){
   TInt count = _buffers.Count();
   for(TInt n = 0; n < count; n++){
      FRenderShaderBuffer* pBuffer = _buffers.Get(n);
      pBuffer->Update();
   }
   return ESuccess;
}

//============================================================
// <T>绘制结束处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderProgram::DrawEnd(){
   return ESuccess;
}

MO_NAMESPACE_END
