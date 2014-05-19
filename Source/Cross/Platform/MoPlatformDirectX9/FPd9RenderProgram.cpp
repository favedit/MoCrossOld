#include "MoPd9Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd9RenderProgram, FRenderProgram);

//============================================================
// <T>构造渲染程序。</T>
//============================================================
FPd9RenderProgram::FPd9RenderProgram(){
}

//============================================================
// <T>析构渲染程序。</T>
//============================================================
FPd9RenderProgram::~FPd9RenderProgram(){
}

//============================================================
// <T>根据代码查找定义索引。</T>
//
// @param pCode 代码
// @return 定义索引
//============================================================
TInt FPd9RenderProgram::FindDefine(TCharC* pCode){
   MO_ASSERT(pCode);
   return -1;
}

//============================================================
// <T>根据代码查找属性索引。</T>
//
// @param pCode 代码
// @return 属性索引
//============================================================
TInt FPd9RenderProgram::FindAttribute(TCharC* pCode){
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
TResult FPd9RenderProgram::BindAttribute(TInt slot, TCharC* pCode){
   MO_ASSERT(slot >= 0);
   MO_ASSERT(pCode);
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderProgram::Setup(){
   MO_CHECK(_pDevice, return ENull);
   FPd9RenderDevice* pRenderDevice = _pDevice->Convert<FPd9RenderDevice>();
   // 创建顶点渲染器
   FPd9RenderVertexShader* pVertexShader = FPd9RenderVertexShader::InstanceCreate();
   pVertexShader->SetDevice(_pDevice);
   pVertexShader->SetProgram(this);
   pVertexShader->Setup();
   _pVertexShader = pVertexShader;
   // 创建像素渲染器
   FPd9RenderFragmentShader* pFragmentShader = FPd9RenderFragmentShader::InstanceCreate();
   pFragmentShader->SetDevice(_pDevice);
   pFragmentShader->SetProgram(this);
   pFragmentShader->Setup();
   _pFragmentShader = pFragmentShader;
   MO_INFO("Create program success.");
   return ESuccess;
}

//============================================================
// <T>构建处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderProgram::BuildShader(FRenderShader* pShader, FPd9RenderShaderBuffer* pBuffer, ID3DXConstantTable* piTable){
   MO_CHECK(pShader, return ENull);
   MO_CHECK(piTable, return ENull);
   MO_CHECK(_pDevice, return ENull);
   FPd9RenderDevice* pRenderDevice = _pDevice->Convert<FPd9RenderDevice>();
   ERenderShader shaderCd = pShader->ShaderCd();
   //............................................................
   // 获得描述
   D3DXCONSTANTTABLE_DESC shaderDescriptor = {0};
   piTable->SetDefaults(pRenderDevice->NativeDevice());
   HRESULT dxResult = piTable->GetDesc(&shaderDescriptor);
   if(FAILED(dxResult)){
      MO_FATAL("Get reflect shader description failure.");
      return EFailure;
   }
   //............................................................
   // 获得常量缓冲
   TInt constLength = 0;
   TUint constCount = 0;
   D3DXCONSTANT_DESC constDescriptors[256] = {0};
   TInt constantCount = shaderDescriptor.Constants;
   for(TInt constantIndex = 0; constantIndex < constantCount; constantIndex++){
      D3DXHANDLE handle = piTable->GetConstant(NULL, constantIndex);
      dxResult = piTable->GetConstantDesc(handle, constDescriptors, &constCount);
      if(FAILED(dxResult)){
         MO_FATAL("Get constant descriptor failure.");
         return EFailure;
      }
      if(constCount == 1){
         D3DXCONSTANT_DESC& constDescriptor = constDescriptors[0];
         if((constDescriptor.RegisterSet == D3DXRS_BOOL) || (constDescriptor.RegisterSet == D3DXRS_INT4) || (constDescriptor.RegisterSet == D3DXRS_FLOAT4)){
            // 设置参数
            FPd9RenderShaderParameter* pParameter = (FPd9RenderShaderParameter*)ParameterFind(constDescriptor.Name);
            if(pParameter == NULL){
               MO_FATAL("Shader parameter is not found. (name=%s)", constDescriptor.Name);
            }else{
               pParameter->SetStatusUsed(ETrue);
               pParameter->SetBuffer(pBuffer);
               pParameter->SetSlot(sizeof(TFloat) * 4 * constDescriptor.RegisterIndex);
               pParameter->SetSize(constDescriptor.Bytes);
               // 计算最大位置
               TInt offset = sizeof(TFloat) * 4 * constDescriptor.RegisterIndex + constDescriptor.Bytes;
               if(offset > constLength){
                  constLength = offset;
               }
            }
         }else if(constDescriptor.RegisterSet == D3DXRS_SAMPLER){
            // 设置取样器
            FRenderSampler* pSampler = SamplerFind(constDescriptor.Name);
            if(pSampler == NULL){
               MO_FATAL("Shader sampler bound is not found. (name=%s)", constDescriptor.Name);
            }else{
               pSampler->SetStatusUsed(ETrue);
               pSampler->SetSlot(constDescriptor.RegisterIndex);
            }
         }else{
            MO_FATAL_UNSUPPORT();
         }
      }else{
         MO_FATAL_UNSUPPORT();
      }
   }
   //............................................................
   // 修正属性流定义
   GRenderShaderAttributeDictionary::TIterator attributeIterator = _attributes.Iterator();
   while(attributeIterator.Next()){
      FRenderAttribute* pAttribute = *attributeIterator;
      pAttribute->SetStatusUsed(ETrue);
   }
   //............................................................
   // 配置缓冲
   pBuffer->SetDevice(_pDevice);
   pBuffer->SetStatusUsed(ETrue);
   pBuffer->SetShaderCd(shaderCd);
   pBuffer->SetDataLength(constLength);
   pBuffer->Setup();
   BufferPush(pBuffer);
   return ESuccess;
}

//============================================================
// <T>构建处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderProgram::Build(){
   MO_CHECK(_pDevice, return ENull);
   TResult resultCd = ESuccess;
   FPd9RenderDevice* pRenderDevice = _pDevice->Convert<FPd9RenderDevice>();
   //............................................................
   // 建立顶点渲染器
   FPd9RenderVertexShader* pVertexShader = _pVertexShader->Convert<FPd9RenderVertexShader>();
   BuildShader(pVertexShader, pVertexShader->Buffer(), pVertexShader->NativeTable());
   //// 建立像素渲染器
   FPd9RenderFragmentShader* pFragmentShader = _pFragmentShader->Convert<FPd9RenderFragmentShader>();
   BuildShader(pFragmentShader, pFragmentShader->Buffer(), pFragmentShader->NativeTable());
   return resultCd;
}

//============================================================
// <T>关联处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderProgram::Link(){
   MO_CHECK(_pDevice, return ENull);
   TResult resultCd = ESuccess;
   FPd9RenderDevice* pRenderDevice = _pDevice->Convert<FPd9RenderDevice>();
   //............................................................
   // 获得数据
   //FPd9RenderVertexShader* pVertexShader = _pVertexShader->Convert<FPd9RenderVertexShader>();
   //ID3D9Blob* piShaderData = pVertexShader->NativeData();
   ////............................................................
   //// 创建输入描述
   //TInt position = 0;
   //GRenderShaderAttributeDictionary::TIterator attributeIterator = _attributes.Iterator();
   //while(attributeIterator.Next()){
   //   FRenderAttribute* pAttribute = *attributeIterator;
   //   if(pAttribute->IsStatusUsed()){
   //      ERenderAttributeFormat formatCd = pAttribute->FormatCd();
   //      D3D9_INPUT_ELEMENT_DESC inputElement;
   //      RType<D3D9_INPUT_ELEMENT_DESC>::Clear(&inputElement);
   //      inputElement.SemanticName = pAttribute->Name();
   //      inputElement.SemanticIndex = pAttribute->Index();
   //      inputElement.Format = RDirectX9::ConvertAttrbuteFormat(formatCd) ;
   //      inputElement.AlignedByteOffset = position;
   //      inputElement.InputSlot = pAttribute->Slot();
   //      inputElement.AlignedByteOffset = 0;
   //      inputElement.InputSlotClass = D3D9_INPUT_PER_VERTEX_DATA;
   //      inputElements.Push(inputElement);
   //      position += RRenderAttributeFormat::CalculateSize(formatCd);
   //   }
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
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderProgram::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderProgram::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderProgram::Dispose(){
   // 释放程序
   MO_DELETE(_pVertexShader);
   MO_DELETE(_pFragmentShader);
   return ESuccess;
}

MO_NAMESPACE_END
