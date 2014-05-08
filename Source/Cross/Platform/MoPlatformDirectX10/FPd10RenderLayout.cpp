#include "MoPd10Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd10RenderLayout, FRenderLayout);

//============================================================
// <T>构造渲染层信息。</T>
//============================================================
FPd10RenderLayout::FPd10RenderLayout(){
   _count = 0;
   MO_CLEAR(_piInputLayout);
}

//============================================================
// <T>析构渲染层信息。</T>
//============================================================
FPd10RenderLayout::~FPd10RenderLayout(){
   MO_RELEASE(_piInputLayout);
}

//============================================================
FRenderLayoutElement* FPd10RenderLayout::FindByAttribute(FRenderShaderAttribute* pAttribute){
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
TResult FPd10RenderLayout::OnSetup(){
   MO_CHECK(_pDevice, return ENull);
   TInt position = 0;
   TInt index = 0;
   FPd10RenderDevice* pRenderDevice = _pDevice->Convert<FPd10RenderDevice>();
   FRenderVertexStreams* pVertexStreams = _pRenderable->VertexStreams();
   GRenderShaderAttributeDictionary::TIterator iterator = _pProgram->Attributes().IteratorC();
   while(iterator.Next()){
      FRenderShaderAttribute* pAttribute = *iterator;
      if(!pAttribute->IsStatusUsed()){
         continue;
      }
      //............................................................
      ERenderVertexBuffer bufferCd = (ERenderVertexBuffer)pAttribute->Code();
      ERenderShaderAttributeFormat formatCd = pAttribute->FormatCd();
      FRenderVertexStream* pStream = pVertexStreams->FindStream(bufferCd);
      FRenderLayoutElement* pElement = FRenderLayoutElement::InstanceCreate();
      pElement->SetAttribute(pAttribute);
      pElement->SetStream(pStream);
      Push(pElement);
      //............................................................
      // 设置缓冲信息
      if(pStream != NULL){
         FPd10RenderVertexBuffer* pVertexBuffer = pStream->VertexBuffer()->Convert<FPd10RenderVertexBuffer>();
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
