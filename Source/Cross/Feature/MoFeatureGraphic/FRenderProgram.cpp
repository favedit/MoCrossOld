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
// <T>增加一个参数。</T>
//
// @param pParameter 渲染参数
// @return 处理结果
//============================================================
TResult FRenderProgram::BufferPush(FRenderShaderBuffer* pBuffer){
   MO_CHECK(pBuffer, return ENull);
   _buffers.Set(pBuffer->Name(), pBuffer);
   return ESuccess;
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
FRenderParameter* FRenderProgram::ParameterFind(ERenderShader shaderCd, TCharC* pName){
   FRenderParameter* pParameter = NULL;
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
TResult FRenderProgram::ParameterPush(FRenderParameter* pParameter){
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
FRenderAttribute* FRenderProgram::AttributeFind(TCharC* pName, TInt index){
   GRenderShaderAttributeDictionary::TIterator iterator = _attributes.IteratorC();
   while(iterator.Next()){
      FRenderAttribute* pAttribute = *iterator;
      if((index == -1) || (pAttribute->Index() == index)){
         if(RString::Equals(pAttribute->Name(), pName)){
            return pAttribute;
         }
      }
   }
   return NULL;
}

//============================================================
// <T>增加一个属性。</T>
//
// @param pAttribute 属性
// @return 处理结果
//============================================================
TResult FRenderProgram::AttributePush(FRenderAttribute* pAttribute){
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
TResult FRenderProgram::SamplerPush(FRenderSampler* pSampler){
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
   GRenderShaderBufferDictionary::TIterator iterator = _buffers.Iterator();
   while(iterator.Next()){
      FRenderShaderBuffer* pBuffer = *iterator;
      pBuffer->Update();
      _pDevice->BindShaderBuffer(pBuffer);
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
