#include "MoPd11Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd11RenderProgram, FRenderProgram);

//============================================================
// <T>构造渲染程序。</T>
//============================================================
FPd11RenderProgram::FPd11RenderProgram(){
   MO_CLEAR(_piInputLayout);
}

//============================================================
// <T>析构渲染程序。</T>
//============================================================
FPd11RenderProgram::~FPd11RenderProgram(){
   MO_RELEASE(_piInputLayout);
}

//============================================================
// <T>根据代码查找定义索引。</T>
//
// @param pCode 代码
// @return 定义索引
//============================================================
TInt FPd11RenderProgram::FindDefine(TCharC* pCode){
   MO_ASSERT(pCode);
   //GLint slot = glGetUniformLocation(_programId, pCode);
   //_pDevice->CheckError("glGetUniformLocation", "Bind uniform location. (program_id=%d, code=%s)", _programId, pCode);
   //return slot;
   return -1;
}

//============================================================
// <T>根据代码查找属性索引。</T>
//
// @param pCode 代码
// @return 属性索引
//============================================================
TInt FPd11RenderProgram::FindAttribute(TCharC* pCode){
   MO_ASSERT(pCode);
   //GLint slot = glGetAttribLocation(_programId, pCode);
   //_pDevice->CheckError("glGetAttribLocation", "Find attribute location. (program_id=%d, code=%s)", _programId, pCode);
   //return slot;
   return -1;
}

//============================================================
// <T>根据代码绑定属性到指定插槽位置。</T>
//
// @param slot 插槽
// @param pCode 代码
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::BindAttribute(TInt slot, TCharC* pCode){
   MO_ASSERT(slot >= 0);
   MO_ASSERT(pCode);
   //MO_ASSERT(_programId != 0);
   //glBindAttribLocation(_programId, slot, pCode);
   //TResult resultCd = _pDevice->CheckError("glBindAttribLocation", "Bind attribute location. (program_id=%d, slot=%d, code=%s)", _programId, slot, pCode);
   //return resultCd;
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::Setup(){
   MO_CHECK(_pDevice, return ENull);
   FPd11RenderDevice* pRenderDevice = _pDevice->Convert<FPd11RenderDevice>();
   // 创建顶点渲染器
   FPd11RenderVertexShader* pVertexShader = FPd11RenderVertexShader::InstanceCreate();
   pVertexShader->SetDevice(_pDevice);
   pVertexShader->SetProgram(this);
   pVertexShader->Setup();
   _pVertexShader = pVertexShader;
   // 创建像素渲染器
   FPd11RenderFragmentShader* pFragmentShader = FPd11RenderFragmentShader::InstanceCreate();
   pFragmentShader->SetDevice(_pDevice);
   pFragmentShader->SetProgram(this);
   pFragmentShader->Setup();
   _pFragmentShader = pFragmentShader;
   MO_INFO("Create program success.");
   return ESuccess;
}

//============================================================
// <T>从代码中查找缓冲。</T>
//
// @param pCode 代码
// @return 缓冲对象
//============================================================
FPd11RenderShaderBuffer* FPd11RenderProgram::FindBuffer(TCharC* pName){
   TInt count = _buffers.Count();
   for(TInt n = 0; n < count; n++){
      FPd11RenderShaderBuffer* pBuffer = _buffers.Get(n);
      if(RString::Equals(pBuffer->Name(), pName)){
         return pBuffer;
      }
   }
   return NULL;
}

//============================================================
// <T>构建处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::BuildShader(FRenderShader* pShader, ID3D10Blob* piData){
   MO_CHECK(pShader, return ENull);
   MO_CHECK(piData, return ENull);
   MO_CHECK(_pDevice, return ENull);
   FPd11RenderDevice* pRenderDevice = _pDevice->Convert<FPd11RenderDevice>();
   //............................................................
   // 获得数据
   TAny* pData = piData->GetBufferPointer();
   TInt dataLength = piData->GetBufferSize();
   // 获得反射接口
   ID3D11ShaderReflection* piReflection = NULL;
   HRESULT dxResult = D3DReflect(pData, dataLength, IID_ID3D11ShaderReflection, (TAny**)&piReflection);
   if(FAILED(dxResult)){
      MO_FATAL("Reflect shader failure.");
      return EFailure;
   }
   // 获得描述
   D3D11_SHADER_DESC shaderDescriptor;
   dxResult = piReflection->GetDesc(&shaderDescriptor);
   if(FAILED(dxResult)){
      MO_FATAL("Get reflect shader description failure.");
      return EFailure;
   }
   //............................................................
   // 获得常量缓冲
   TInt constantBufferCount = shaderDescriptor.ConstantBuffers;
   for(TInt constantBufferIndex = 0; constantBufferIndex < constantBufferCount; constantBufferIndex++){
      ID3D11ShaderReflectionConstantBuffer* piConstantBuffer = piReflection->GetConstantBufferByIndex(constantBufferIndex);
      MO_CHECK(piConstantBuffer, continue);
      // 获得缓冲信息
      D3D11_SHADER_BUFFER_DESC bufferDescriptor;
      dxResult = piConstantBuffer->GetDesc(&bufferDescriptor);
      if(FAILED(dxResult)){
         MO_FATAL("Get buffer description failure.");
         return EFailure;
      }
      // 创建缓冲
      FPd11RenderShaderBuffer* pBuffer = FPd11RenderShaderBuffer::InstanceCreate();
      pBuffer->SetDevice(pRenderDevice);
      pBuffer->SetName(bufferDescriptor.Name);
      pBuffer->SetDataLength(bufferDescriptor.Size);
      pBuffer->Setup();
      _buffers.Push(pBuffer);
      //............................................................
      // 获得参数信息
      TInt variableCount = bufferDescriptor.Variables;
      for(TInt n = 0; n < variableCount; n++){
         ID3D11ShaderReflectionVariable* piVariable = piConstantBuffer->GetVariableByIndex(n);
         // 创建变量信息
         D3D11_SHADER_VARIABLE_DESC variableDescriptor;
         dxResult = piVariable->GetDesc(&variableDescriptor);
         if(FAILED(dxResult)){
            MO_FATAL("Get variable description failure.");
            return EFailure;
         }
         ID3D11ShaderReflectionType* piVariableType = piVariable->GetType();
         D3D11_SHADER_TYPE_DESC typeDescriptor;
         dxResult = piVariableType->GetDesc(&typeDescriptor);
         if(FAILED(dxResult)){
            MO_FATAL("Get variable type description failure.");
            return EFailure;
         }
         // 创建参数
         FPd11RenderShaderParameter* pParameter = FPd11RenderShaderParameter::InstanceCreate();
         pParameter->SetBuffer(pBuffer);
         pParameter->LinkNative(piVariable);
         pShader->ParameterPush(pParameter);
      }
   }
   //............................................................
   // 获得输入描述
   TInt attributeCount = shaderDescriptor.InputParameters;
   if(attributeCount > 0){
      for(TInt attributeIndex = 0; attributeIndex < attributeCount; attributeIndex++){
         // 获得描述信息
         D3D11_SIGNATURE_PARAMETER_DESC attributeDescriptor;
         dxResult = piReflection->GetInputParameterDesc(attributeIndex, &attributeDescriptor);
         if(FAILED(dxResult)){
            MO_FATAL("Get attribute description failure.");
            return EFailure;
         }
         ERenderShaderAttributeFormat formatCd = RDirectX11::ParseAttrbuteFormat(attributeDescriptor.ComponentType, attributeDescriptor.Mask);
         // 创建属性
         //FPd11RenderShaderAttribute* pAttribute = FPd11RenderShaderAttribute::InstanceCreate();
         //pAttribute->SetName(attributeDescriptor.SemanticName);
         //pAttribute->SetIndex(attributeDescriptor.SemanticIndex);
         //pAttribute->SetFormatCd(formatCd);
         //pShader->AttributePush(pAttribute);
      }
   }
   //............................................................
   // 设定所有绑定点
   TInt boundCount = shaderDescriptor.BoundResources;
   for(TInt boundIndex = 0; boundIndex < boundCount; boundIndex++){
      D3D11_SHADER_INPUT_BIND_DESC bindDescriptor;
      dxResult = piReflection->GetResourceBindingDesc(boundIndex, &bindDescriptor);
      if(FAILED(dxResult)){
         MO_FATAL("Get resource binding description failure.");
         return EFailure;
      }
      if(bindDescriptor.Type == D3D_SIT_CBUFFER){
         FPd11RenderShaderBuffer* pBuffer = FindBuffer(bindDescriptor.Name);
         MO_CHECK(pBuffer, continue);
         pBuffer->SetSolt(bindDescriptor.BindPoint);
      }
   }
   MO_RELEASE(piReflection);
   return ESuccess;
}

//============================================================
// <T>构建处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::Build(){
   MO_CHECK(_pDevice, return ENull);
   TResult resultCd = ESuccess;
   FPd11RenderDevice* pRenderDevice = _pDevice->Convert<FPd11RenderDevice>();
   //............................................................
   // 建立顶点渲染器
   FPd11RenderVertexShader* pVertexShader = _pVertexShader->Convert<FPd11RenderVertexShader>();
   BuildShader(pVertexShader, pVertexShader->NativeData());
   // 建立像素渲染器
   FPd11RenderFragmentShader* pFragmentShader = _pFragmentShader->Convert<FPd11RenderFragmentShader>();
   BuildShader(pFragmentShader, pFragmentShader->NativeData());
   return resultCd;
}

//============================================================
// <T>关联处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::Link(){
   MO_CHECK(_pDevice, return ENull);
   TResult resultCd = ESuccess;
   FPd11RenderDevice* pRenderDevice = _pDevice->Convert<FPd11RenderDevice>();
   //............................................................
   // 获得数据
   FPd11RenderVertexShader* pVertexShader = _pVertexShader->Convert<FPd11RenderVertexShader>();
   ID3D10Blob* piShaderData = pVertexShader->NativeData();
   //............................................................
   // 创建输入描述
   //MO_D3D11_INPUT_ELEMENT_DESC_ARRAY inputElements;
   //GRenderShaderAttributePtrs::TIterator attributeIterator = pVertexShader->Attributes().Iterator();
   //while(attributeIterator.Next()){
   //   FRenderShaderAttribute* pAttribute = *attributeIterator;
   //   D3D11_INPUT_ELEMENT_DESC inputElement;
   //   RType<D3D11_INPUT_ELEMENT_DESC>::Clear(&inputElement);
   //   inputElement.SemanticName = pAttribute->Name();
   //   inputElement.SemanticIndex = pAttribute->Index();
   //   inputElement.Format = RDirectX11::ConvertAttrbuteFormat(pAttribute->FormatCd()) ;
   //   inputElement.InputSlotClass = D3D11_INPUT_PER_VERTEX_DATA;
   //   inputElements.Push(inputElement);
   //}
   //// 创建输入层次
   //HRESULT dxResult = pRenderDevice->NativeDevice()->CreateInputLayout(
   //      inputElements.Memory(), inputElements.Length(),
   //      piShaderData->GetBufferPointer(), piShaderData->GetBufferSize(),
   //      &_piInputLayout);
   //if(FAILED(dxResult)){
   //   MO_FATAL("Create input layout failure.");
   //   return EFailure;
   //}
   //............................................................
   MO_INFO("Link program success.");
   return resultCd;
}

//============================================================
// <T>设置常量的数据内容。</T>
//
// @param pName 名称
// @param pData 数据
// @param length 长度
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::SetConstVariable(ERenderShader shaderCd, TCharC* pName, TAnyC* pData, TInt length){
   // 查找参数
   FPd11RenderShaderParameter* pParameter = NULL;
   if(shaderCd == ERenderShader_Vertex){
      FPd11RenderVertexShader* pVertexShader = _pVertexShader->Convert<FPd11RenderVertexShader>();
      pParameter = (FPd11RenderShaderParameter*)pVertexShader->ParameterFind(pName);
   }else if(shaderCd == ERenderShader_Fragment){
      FPd11RenderFragmentShader* pFragmentShader = _pVertexShader->Convert<FPd11RenderFragmentShader>();
      pParameter = (FPd11RenderShaderParameter*)pFragmentShader->ParameterFind(pName);
   }
   if(pParameter == NULL){
      MO_FATAL("Find parameter failure. (parameter_name=%s)", pName);
      return EFailure;
   }
   // 设置内容
   FPd11RenderShaderBuffer* pBuffer = pParameter->Buffer();
   //pBuffer->Set
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderProgram::Dispose(){
   // 释放资源
   MO_RELEASE(_piInputLayout);
   // 释放程序
   MO_DELETE(_pVertexShader);
   MO_DELETE(_pFragmentShader);
   return ESuccess;
}

MO_NAMESPACE_END
