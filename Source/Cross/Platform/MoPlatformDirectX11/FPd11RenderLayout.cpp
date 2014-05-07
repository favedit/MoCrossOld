#include "MoPd11Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd11RenderLayout, FRenderLayout);

//============================================================
// <T>构造渲染层信息。</T>
//============================================================
FPd11RenderLayout::FPd11RenderLayout(){
   _count = 0;
   MO_CLEAR(_piInputLayout);
}

//============================================================
// <T>析构渲染层信息。</T>
//============================================================
FPd11RenderLayout::~FPd11RenderLayout(){
   MO_RELEASE(_piInputLayout);
}

//============================================================
FRenderLayoutElement* FPd11RenderLayout::FindByAttribute(FRenderShaderAttribute* pAttribute){
   TInt count = _elements.Count();
   for(TInt n = 0; n < count; n++){
      FRenderLayoutElement* pElement = _elements.Get(n);
      if(pElement->Attribute() == pAttribute){
         return pElement;
      }
   }
   return NULL;
}

//============================================================
TResult FPd11RenderLayout::OnSetup(){
   MO_CHECK(_pDevice, return ENull);
   TInt position = 0;
   TInt index = 0;
   FPd11RenderDevice* pRenderDevice = _pDevice->Convert<FPd11RenderDevice>();
   FRenderVertexStreams* pVertexStreams = _pRenderable->VertexStreams();
   GRenderShaderAttributeDictionary::TIterator iterator = _pProgram->Attributes().IteratorC();
   while(iterator.Next()){
      FRenderShaderAttribute* pAttribute = *iterator;
      if(!pAttribute->IsStatusUsed()){
         continue;
      }
      ERenderVertexBuffer bufferCd = (ERenderVertexBuffer)pAttribute->Code();
      ERenderShaderAttributeFormat formatCd = pAttribute->FormatCd();
      FRenderVertexStream* pStream = pVertexStreams->FindStream(bufferCd);
      if(pStream != NULL){
         FPd11RenderVertexBuffer* pVertexBuffer = pStream->VertexBuffer()->Convert<FPd11RenderVertexBuffer>();
         FRenderLayoutElement* pElement = FRenderLayoutElement::InstanceCreate();
         pElement->SetAttribute(pAttribute);
         pElement->SetStream(pStream);
         Push(pElement);
         // 设置信息
         D3D11_INPUT_ELEMENT_DESC inputElement = {0};
         inputElement.SemanticName = pAttribute->Name();
         inputElement.SemanticIndex = pAttribute->Index();
         inputElement.InputSlotClass = D3D11_INPUT_PER_VERTEX_DATA;
         inputElement.Format = RDirectX11::ConvertAttrbuteFormat(formatCd) ;
         inputElement.InputSlot = 0;
         inputElement.AlignedByteOffset = position;
         _inputElements.Push(inputElement);
         // 修正信息
         _piBuffer[index] = pVertexBuffer->NativeBuffer();
         _strides[index] = pStream->Stride();
         _offsets[index] = pStream->Offset();
         position += RRenderShaderAttributeFormat::CalculateSize(formatCd);
      }else{
         FRenderLayoutElement* pElement = FRenderLayoutElement::InstanceCreate();
         pElement->SetAttribute(pAttribute);
         pElement->SetStream(NULL);
         Push(pElement);
         // 设置信息
         D3D11_INPUT_ELEMENT_DESC inputElement = {0};
         inputElement.SemanticName = pAttribute->Name();
         inputElement.SemanticIndex = pAttribute->Index();
         inputElement.InputSlotClass = D3D11_INPUT_PER_VERTEX_DATA;
         inputElement.Format = RDirectX11::ConvertAttrbuteFormat(formatCd) ;
         inputElement.InputSlot = 0;
         inputElement.AlignedByteOffset = position;
         _inputElements.Push(inputElement);
         // 修正信息
         _piBuffer[index] = NULL;
         _strides[index] = 0;
         _offsets[index] = 0;
         position += RRenderShaderAttributeFormat::CalculateSize(formatCd);
      }
      index++;
   }
   _count = index;
   //............................................................
   //FPd11RenderVertexShader* pVertexShader = _pProgram->VertexShader()->Convert<FPd11RenderVertexShader>();
   //ID3D10Blob* piShaderData = pVertexShader->NativeData();
   //// 创建输入层次
   //HRESULT dxResult = pRenderDevice->NativeDevice()->CreateInputLayout(
   //      _inputElements.Memory(), _inputElements.Length(),
   //      piShaderData->GetBufferPointer(), piShaderData->GetBufferSize(),
   //      &_piInputLayout);
   //if(FAILED(dxResult)){
   //   MO_FATAL("Create input layout failure.");
   //   return EFailure;
   //}
   return ESuccess;
}

MO_NAMESPACE_END
