#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_ABSTRACT_IMPLEMENT_INHERITS(FRenderProgram, FRenderInstance);

//============================================================
// <T>构造渲染程序。</T>
//============================================================
FRenderProgram::FRenderProgram(){
}

//============================================================
// <T>析构渲染程序。</T>
//============================================================
FRenderProgram::~FRenderProgram(){
}

//============================================================
// <T>根据名称查找一个程序缓冲。</T>
//
// @param pName 名称
// @return 程序缓冲
//============================================================
FRenderProgramBuffer* FRenderProgram::BufferFindByName(TCharC* pName){
   TInt count = _buffers.Count();
   for(TInt n = 0; n < count; n++){
      FRenderProgramBuffer* pBuffer = _buffers.Get(n);
      if(RString::Equals(pBuffer->Name(), pName)){
         return pBuffer;
      }
   }
   return NULL;
}

//============================================================
// <T>根据关联名称查找一个程序缓冲。</T>
//
// @param pLinker 关联名称
// @return 程序缓冲
//============================================================
FRenderProgramBuffer* FRenderProgram::BufferFindByLinker(TCharC* pLinker){
   TInt count = _buffers.Count();
   for(TInt n = 0; n < count; n++){
      FRenderProgramBuffer* pBuffer = _buffers.Get(n);
      if(RString::Equals(pBuffer->Linker(), pLinker)){
         return pBuffer;
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
TResult FRenderProgram::BufferPush(FRenderProgramBuffer* pBuffer){
   MO_CHECK(pBuffer, return ENull);
   TCharC* pLinker = pBuffer->Linker();
   MO_CHECK(pLinker, return ENull);
   FRenderProgramBuffer* pFind = BufferFindByLinker(pLinker);
   if(pFind != NULL){
      MO_FATAL("Program buffer is already exists. (name=%s, linker=%s)", pBuffer->Name(), pBuffer->Linker());
      return EDuplicate;
   }
   _buffers.Push(pBuffer);
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
      return _vertexShader->Parameters();
   }else if(shaderCd == ERenderShader_Fragment){
      return _fragmentShader->Parameters();
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
      pParameter = _vertexShader->ParameterFind(pName);
   }else if(shaderCd == ERenderShader_Fragment){
      pParameter = _fragmentShader->ParameterFind(pName);
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
   GRenderProgramAttributeDictionary::TIterator iterator = _attributes.IteratorC();
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
// @return 渲染属性
//============================================================
FRenderProgramAttribute* FRenderProgram::AttributeFindByName(TCharC* pName){
   GRenderProgramAttributeDictionary::TIterator iterator = _attributes.IteratorC();
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
// <T>根据名称查找取样器。</T>
//
// @param pName 名称
// @return 取样器
//============================================================
FRenderProgramSampler* FRenderProgram::SamplerFindByName(TCharC* pName){
   TInt count = _samplers.Count();
   for(TInt n = 0; n < count; n++){
      FRenderProgramSampler* pSampler = _samplers.Get(n);
      if(RString::Equals(pSampler->Name(), pName)){
         return pSampler;
      }
   }
   return NULL;
}

//============================================================
// <T>根据名称查找取样器。</T>
//
// @param pLinker 关联名称
// @return 取样器
//============================================================
FRenderProgramSampler* FRenderProgram::SamplerFindByLinker(TCharC* pLinker){
   TInt count = _samplers.Count();
   for(TInt n = 0; n < count; n++){
      FRenderProgramSampler* pSampler = _samplers.Get(n);
      if(RString::Equals(pSampler->Linker(), pLinker)){
         return pSampler;
      }
   }
   return NULL;
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
   FRenderProgramBuffer* pFind = BufferFindByLinker(pLinker);
   if(pFind != NULL){
      MO_FATAL("Program sampler is already exists. (name=%s, linker=%s)", pSampler->Name(), pSampler->Linker());
      return EDuplicate;
   }
   _samplers.Push(pSampler);
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
      FRenderProgramBuffer* pBuffer = _buffers.Get(n);
      // 更新处理
      pBuffer->Update();
      // 绑定处理
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

//============================================================
// <T>跟踪处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderProgram::Track(){
   TString dump;
   dump.Append("Program\n");
   //............................................................
   // 缓冲信息
   TInt bufferCount = _buffers.Count();
   dump.AppendFormat("Buffer (count=%d)\n", bufferCount);
   for(TInt n = 0; n < bufferCount; n++){
      FRenderProgramBuffer* pBuffer = _buffers.Get(n);
      dump.AppendFormat("   Buffer: name=%s, linker=%s\n", pBuffer->Name(), pBuffer->Linker());
   }
   //............................................................
   // 取样器信息
   //TInt attributeCount = _attributes.Count();
   //dump.AppendFormat("Attribute (count=%d)\n", attributeCount);
   //for(TInt n = 0; n < attributeCount; n++){
   //   FRenderProgramAttribute * pAttribute = _attributes.Get(n);
   //   dump.AppendFormat("   %s = 0x%08X\n", pAttribute->Code(), pAttribute->Slot());
   //}
   //............................................................
   // 取样器信息
   TInt samplerCount = _samplers.Count();
   dump.AppendFormat("Sampler (count=%d)\n", samplerCount);
   for(TInt n = 0; n < samplerCount; n++){
      FRenderProgramSampler* pSampler = _samplers.Get(n);
      dump.Append("   ");
      pSampler->Dump(&dump);
      dump.Append("\n");
   }
   MO_INFO(dump);
   return ESuccess;
}

MO_NAMESPACE_END
