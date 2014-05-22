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
TResult FRenderProgram::BufferPush(FRenderProgramBuffer* pBuffer){
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
FRenderProgramParameter* FRenderProgram::ParameterFind(ERenderShader shaderCd, TCharC* pName){
   FRenderProgramParameter* pParameter = NULL;
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
// <T>根据名称查找一个参数。</T>
//
// @param pName 名称
// @return 渲染参数
//============================================================
FRenderProgramParameter* FRenderProgram::ParameterFindByName(TCharC* pName){
   GRenderShaderParameterDictionary::TIterator iterator = _parameters.Iterator();
   while(iterator.Next()){
      FRenderProgramParameter* pParameter = *iterator;
      if(RString::Equals(pParameter->Name(), pName)){
         return pParameter;
      }
   }
   return NULL;
}

//============================================================
// <T>增加一个参数。</T>
//
// @param pParameter 渲染参数
// @return 处理结果
//============================================================
TResult FRenderProgram::ParameterPush(FRenderProgramParameter* pParameter){
   MO_CHECK(pParameter, return ENull);
   TCharC* pLinker = pParameter->Linker();
   MO_CHECK(pLinker, return ENull);
   _parameters.Set(pLinker, pParameter);
   return ESuccess;
}

//============================================================
// <T>根据名称查找渲染属性。</T>
//
// @param pName 名称
// @param index 索引
// @return 渲染属性
//============================================================
FRenderProgramAttribute* FRenderProgram::AttributeFind(TCharC* pName, TInt index){
   GRenderShaderAttributeDictionary::TIterator iterator = _attributes.IteratorC();
   while(iterator.Next()){
      FRenderProgramAttribute* pAttribute = *iterator;
      if((index == -1) || (pAttribute->Index() == index)){
         if(RString::Equals(pAttribute->Name(), pName)){
            return pAttribute;
         }
      }
   }
   return NULL;
}

//============================================================
// <T>根据名称查找渲染属性。</T>
//
// @param pName 名称
// @param index 索引
// @return 渲染属性
//============================================================
FRenderProgramAttribute* FRenderProgram::AttributeFindByName(TCharC* pName){
   GRenderShaderAttributeDictionary::TIterator iterator = _attributes.IteratorC();
   while(iterator.Next()){
      FRenderProgramAttribute* pAttribute = *iterator;
      if(RString::Equals(pAttribute->Name(), pName)){
         return pAttribute;
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
TResult FRenderProgram::AttributePush(FRenderProgramAttribute* pAttribute){
   MO_CHECK(pAttribute, return ENull);
   TCharC* pLinker = pAttribute->Linker();
   MO_CHECK(pLinker, return ENull);
   _attributes.Set(pLinker, pAttribute);
   return ESuccess;
}

//============================================================
// <T>增加一个取样。</T>
//
// @param pSampler 取样
// @return 处理结果
//============================================================
TResult FRenderProgram::SamplerPush(FRenderProgramSampler* pSampler){
   MO_CHECK(pSampler, return ENull);
   TCharC* pLinker = pSampler->Linker();
   MO_CHECK(pLinker, return ENull);
   _samplers.Set(pLinker, pSampler);
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
      FRenderProgramBuffer* pBuffer = *iterator;
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
