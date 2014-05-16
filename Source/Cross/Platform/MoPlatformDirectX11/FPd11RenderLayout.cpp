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
      //............................................................
      TInt bufferCd = pAttribute->Code();
      ERenderShaderAttributeFormat formatCd = pAttribute->FormatCd();
      FRenderVertexStream* pStream = pVertexStreams->FindStream(bufferCd);
      FRenderLayoutElement* pElement = FRenderLayoutElement::InstanceCreate();
      pElement->SetAttribute(pAttribute);
      pElement->SetStream(pStream);
      Push(pElement);
      //............................................................
      // 设置缓冲信息
      if(pStream != NULL){
         FPd11RenderVertexBuffer* pVertexBuffer = pStream->VertexBuffer()->Convert<FPd11RenderVertexBuffer>();
         _piBuffer[index] = pVertexBuffer->NativeBuffer();
         _strides[index] = pStream->Stride();
         _offsets[index] = pStream->Offset();
      }else{
         _piBuffer[index] = NULL;
         _strides[index] = 0;
         _offsets[index] = 0;
      }
      index++;
      position += RRenderShaderAttributeFormat::CalculateSize(formatCd);
   }
   _count = index;
   return ESuccess;
}

MO_NAMESPACE_END
