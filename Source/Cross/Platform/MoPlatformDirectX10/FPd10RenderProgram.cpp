#include "MoPd10Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd10RenderProgram, FRenderProgram);

//============================================================
// <T>构造渲染程序。</T>
//============================================================
FPd10RenderProgram::FPd10RenderProgram(){
   MO_CLEAR(_piInputLayout);
}

//============================================================
// <T>析构渲染程序。</T>
//============================================================
FPd10RenderProgram::~FPd10RenderProgram(){
   MO_RELEASE(_piInputLayout);
}

//============================================================
// <T>根据代码查找定义索引。</T>
//
// @param pCode 代码
// @return 定义索引
//============================================================
TInt FPd10RenderProgram::FindDefine(TCharC* pCode){
   MO_ASSERT(pCode);
   return -1;
}

//============================================================
// <T>根据代码查找属性索引。</T>
//
// @param pCode 代码
// @return 属性索引
//============================================================
TInt FPd10RenderProgram::FindAttribute(TCharC* pCode){
   MO_ASSERT(pCode);
   return -1;
}

//============================================================
// <T>根据代码绑定属性到指定插槽位置。</T>
//
// @param slot 插槽
// @param pCode 代码
// @return 处理结果
//============================================================
TResult FPd10RenderProgram::BindAttribute(TInt slot, TCharC* pCode){
   MO_ASSERT(slot >= 0);
   MO_ASSERT(pCode);
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd10RenderProgram::Setup(){
   MO_CHECK(_pDevice, return ENull);
   FPd10RenderDevice* pRenderDevice = _pDevice->Convert<FPd10RenderDevice>();
   // 创建顶点渲染器
   FPd10RenderVertexShader* pVertexShader = FPd10RenderVertexShader::InstanceCreate();
   pVertexShader->SetDevice(_pDevice);
   pVertexShader->SetProgram(this);
   pVertexShader->Setup();
   _vertexShader = pVertexShader;
   // 创建像素渲染器
   FPd10RenderFragmentShader* pFragmentShader = FPd10RenderFragmentShader::InstanceCreate();
   pFragmentShader->SetDevice(_pDevice);
   pFragmentShader->SetProgram(this);
   pFragmentShader->Setup();
   _fragmentShader = pFragmentShader;
   MO_INFO("Create program success.");
   return ESuccess;
}

//============================================================
// <T>构建处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd10RenderProgram::BuildShader(FRenderShader* pShader, ID3D10Blob* piData){
   MO_CHECK(pShader, return ENull);
   MO_CHECK(piData, return ENull);
   MO_CHECK(_pDevice, return ENull);
   FPd10RenderDevice* pRenderDevice = _pDevice->Convert<FPd10RenderDevice>();
   ERenderShader shaderCd = pShader->ShaderCd();
   //............................................................
   // 获得数据
   TAny* pData = piData->GetBufferPointer();
   TInt dataLength = piData->GetBufferSize();
   // 获得反射接口
   ID3D10ShaderReflection* piReflection = NULL;
   HRESULT dxResult = D3D10ReflectShader(pData, dataLength, &piReflection);
   if(FAILED(dxResult)){
      MO_FATAL("Reflect shader failure.");
      return EFailure;
   }
   // 获得描述
   D3D10_SHADER_DESC shaderDescriptor = {0};
   dxResult = piReflection->GetDesc(&shaderDescriptor);
   if(FAILED(dxResult)){
      MO_FATAL("Get reflect shader description failure.");
      return EFailure;
   }
   //............................................................
   // 获得常量缓冲
   TInt constantBufferCount = shaderDescriptor.ConstantBuffers;
   for(TInt constantBufferIndex = 0; constantBufferIndex < constantBufferCount; constantBufferIndex++){
      ID3D10ShaderReflectionConstantBuffer* piConstantBuffer = piReflection->GetConstantBufferByIndex(constantBufferIndex);
      MO_CHECK(piConstantBuffer, continue);
      // 获得缓冲信息
      D3D10_SHADER_BUFFER_DESC bufferDescriptor = {0};
      dxResult = piConstantBuffer->GetDesc(&bufferDescriptor);
      if(FAILED(dxResult)){
         MO_FATAL("Get buffer description failure.");
         return EFailure;
      }
      // 创建缓冲
      FPd10RenderShaderBuffer* pBuffer = (FPd10RenderShaderBuffer*)BufferFindByName(bufferDescriptor.Name);
      pBuffer->SetStatusUsed(ETrue);
      pBuffer->SetShaderCd(shaderCd);
      pBuffer->SetDataLength(bufferDescriptor.Size);
      pBuffer->Setup();
      //............................................................
      // 获得参数信息
      TInt variableCount = bufferDescriptor.Variables;
      for(TInt n = 0; n < variableCount; n++){
         ID3D10ShaderReflectionVariable* piVariable = piConstantBuffer->GetVariableByIndex(n);
         // 创建变量信息
         D3D10_SHADER_VARIABLE_DESC variableDescriptor = {0};
         dxResult = piVariable->GetDesc(&variableDescriptor);
         if(FAILED(dxResult)){
            MO_FATAL("Get variable description failure.");
            return EFailure;
         }
         ID3D10ShaderReflectionType* piVariableType = piVariable->GetType();
         D3D10_SHADER_TYPE_DESC typeDescriptor;
         dxResult = piVariableType->GetDesc(&typeDescriptor);
         if(FAILED(dxResult)){
            MO_FATAL("Get variable type description failure.");
            return EFailure;
         }
         // 创建参数
         FPd10RenderShaderParameter* pParameter = (FPd10RenderShaderParameter*)ParameterFindByName(variableDescriptor.Name);
         //MO_CHECK(pParameter, continue);
         if(pParameter == NULL){
            MO_FATAL("Shader parameter is not found. (name=%s)", variableDescriptor.Name);
         }else{
            //pParameter->SetShader(pShader);
            pParameter->SetBuffer(pBuffer);
            pParameter->LinkNative(piVariable);
         }
      }
   }
   //............................................................
   // 获得输入描述
   TInt attributeCount = shaderDescriptor.InputParameters;
   if((shaderCd == ERenderShader_Vertex) && (attributeCount > 0)){
      TInt usingIndex = 0;
      for(TInt attributeIndex = 0; attributeIndex < attributeCount; attributeIndex++){
         // 获得描述信息
         D3D10_SIGNATURE_PARAMETER_DESC attributeDescriptor = {0};
         dxResult = piReflection->GetInputParameterDesc(attributeIndex, &attributeDescriptor);
         if(FAILED(dxResult)){
            MO_FATAL("Get attribute description failure.");
            return EFailure;
         }
         // 查找属性
         TFsName attributeName;
         attributeName.AppendFormat("%s%d", attributeDescriptor.SemanticName, attributeDescriptor.SemanticIndex);
         FRenderProgramAttribute* pAttribute = AttributeFindByName(attributeName);
         if(pAttribute == NULL){
            pAttribute = AttributeFindByName(attributeDescriptor.SemanticName);
         }
         if(pAttribute == NULL){
            MO_WARN("Shader attribute is not found. (name=%s)", attributeDescriptor.SemanticName);
         }else{
            // 设置内容
            pAttribute->SetStatusUsed(ETrue);
            pAttribute->SetName(attributeDescriptor.SemanticName);
            pAttribute->SetIndex(attributeDescriptor.SemanticIndex);
            pAttribute->SetSlot(usingIndex++);
            // MO_INFO("Build attribute. (name=%s - %d)", attributeDescriptor.SemanticName, attributeDescriptor.SemanticIndex);
         }
      }
   }
   //............................................................
   // 设定所有绑定点
   TInt boundCount = shaderDescriptor.BoundResources;
   for(TInt boundIndex = 0; boundIndex < boundCount; boundIndex++){
      D3D10_SHADER_INPUT_BIND_DESC bindDescriptor = {0};
      dxResult = piReflection->GetResourceBindingDesc(boundIndex, &bindDescriptor);
      if(FAILED(dxResult)){
         MO_FATAL("Get resource binding description failure.");
         return EFailure;
      }
      TCharC* pBindName = bindDescriptor.Name;
      if(bindDescriptor.Type == D3D_SIT_CBUFFER){
         FPd10RenderShaderBuffer* pBuffer = (FPd10RenderShaderBuffer*)BufferFindByName(pBindName);
         MO_CHECK(pBuffer, continue);
         pBuffer->SetSlot(bindDescriptor.BindPoint);
      }
      if(bindDescriptor.Type == D3D_SIT_TEXTURE){
         FRenderProgramSampler* pSampler = SamplerFindByName(pBindName);
         if(pSampler == NULL){
            MO_ERROR("Shader sampler bound is not found. (name=%s)", pBindName);
         }else{
            pSampler->SetStatusUsed(ETrue);
            pSampler->SetSlot(bindDescriptor.BindPoint);
         }
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
TResult FPd10RenderProgram::Build(){
   MO_CHECK(_pDevice, return ENull);
   TResult resultCd = ESuccess;
   FPd10RenderDevice* pRenderDevice = _pDevice->Convert<FPd10RenderDevice>();
   //............................................................
   // 建立顶点渲染器
   FPd10RenderVertexShader* pVertexShader = _vertexShader->Convert<FPd10RenderVertexShader>();
   BuildShader(pVertexShader, pVertexShader->NativeData());
   // 建立像素渲染器
   FPd10RenderFragmentShader* pFragmentShader = _fragmentShader->Convert<FPd10RenderFragmentShader>();
   BuildShader(pFragmentShader, pFragmentShader->NativeData());
   return resultCd;
}

//============================================================
// <T>关联处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd10RenderProgram::Link(){
   MO_CHECK(_pDevice, return ENull);
   TResult resultCd = ESuccess;
   FPd10RenderDevice* pRenderDevice = _pDevice->Convert<FPd10RenderDevice>();
   //............................................................
   // 获得数据
   FPd10RenderVertexShader* pVertexShader = _vertexShader->Convert<FPd10RenderVertexShader>();
   ID3D10Blob* piShaderData = pVertexShader->NativeData();
   //............................................................
   // 创建输入描述
   TInt position = 0;
   TInt count = _attributes.Count();
   for(TInt n = 0; n < count; n++){
      FRenderProgramAttribute* pAttribute = _attributes.Get(n);
      if(pAttribute->IsStatusUsed()){
         ERenderAttributeFormat formatCd = pAttribute->FormatCd();
         D3D10_INPUT_ELEMENT_DESC inputElement;
         RType<D3D10_INPUT_ELEMENT_DESC>::Clear(&inputElement);
         inputElement.SemanticName = pAttribute->Name();
         inputElement.SemanticIndex = pAttribute->Index();
         inputElement.Format = RDirectX10::ConvertAttrbuteFormat(formatCd) ;
         inputElement.AlignedByteOffset = position;
         inputElement.InputSlot = pAttribute->Slot();
         inputElement.AlignedByteOffset = 0;
         inputElement.InputSlotClass = D3D10_INPUT_PER_VERTEX_DATA;
         inputElements.Push(inputElement);
         position += RRenderAttributeFormat::CalculateSize(formatCd);
      }
   }
   // 创建输入层次
   HRESULT dxResult = pRenderDevice->NativeDevice()->CreateInputLayout(
         inputElements.Memory(), inputElements.Length(),
         piShaderData->GetBufferPointer(), piShaderData->GetBufferSize(),
         &_piInputLayout);
   if(FAILED(dxResult)){
      MO_FATAL("Create input layout failure.");
      return EFailure;
   }
   //............................................................
   MO_INFO("Link program success.");
   return resultCd;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd10RenderProgram::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd10RenderProgram::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd10RenderProgram::Dispose(){
   // 释放资源
   MO_RELEASE(_piInputLayout);
   return ESuccess;
}

MO_NAMESPACE_END
