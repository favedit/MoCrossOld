#include "MoPd9Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd9RenderLayout, FRenderLayout);

//============================================================
// <T>构造渲染层信息。</T>
//============================================================
FPd9RenderLayout::FPd9RenderLayout(){
   //_count = 0;
   //MO_CLEAR(_piInputLayout);
}

//============================================================
// <T>析构渲染层信息。</T>
//============================================================
FPd9RenderLayout::~FPd9RenderLayout(){
   //MO_RELEASE(_piInputLayout);
}

//============================================================
FRenderLayoutElement* FPd9RenderLayout::FindByAttribute(FRenderShaderAttribute* pAttribute){
   TInt count = _elements.Count();
   for(TInt n = 0; n < count; n++){
      FRenderLayoutElement* pElement = _elements.Get(n);
      if(pElement->Attribute() == pAttribute){
         return pElement;
      }
   }
   return NULL;
}

TInt GetFvF(TInt index){
   switch(index){
      case 0:
         return D3DFVF_XYZW;
      case 1:
         return D3DFVF_TEX0;
      case 2:
         return D3DFVF_TEX1;
      case 3:
         return D3DFVF_TEX2;
      case 4:
         return D3DFVF_TEX3;
      case 5:
         return D3DFVF_TEX4;
      case 6:
         return D3DFVF_TEX5;
      case 7:
         return D3DFVF_TEX6;
      case 8:
         return D3DFVF_TEX7;
      case 9:
         return D3DFVF_TEX8;
   }
   return 0;
}

//============================================================
TResult FPd9RenderLayout::OnSetup(){
   MO_CHECK(_pDevice, return ENull);
   TInt position = 0;
   TInt index = 0;
   FPd9RenderDevice* pRenderDevice = _pDevice->Convert<FPd9RenderDevice>();
   FRenderVertexStreams* pVertexStreams = _pRenderable->VertexStreams();
   GRenderShaderAttributeDictionary::TIterator iterator = _pProgram->Attributes().IteratorC();
   TInt fvf1 = 0;
   TInt fvf2 = 0;
   while(iterator.Next()){
      FRenderShaderAttribute* pAttribute = *iterator;
      //if(!pAttribute->IsStatusUsed()){
      //   continue;
      //}
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
         FPd9RenderVertexBuffer* pVertexBuffer = pStream->VertexBuffer()->Convert<FPd9RenderVertexBuffer>();
         _total = pVertexBuffer->Count();
         _piBuffer[index] = pVertexBuffer->NativeBuffer();
         _strides[index] = pStream->Stride();
         _offsets[index] = pStream->Offset();
         if(index == 0){
            fvf1 = GetFvF(index);
         }else{
            fvf2 = GetFvF(index);
         }
      }else{
         _piBuffer[index] = NULL;
         _strides[index] = 0;
         _offsets[index] = 0;
      }
      index++;
      position += RRenderShaderAttributeFormat::CalculateSize(formatCd);
   }
   _formatCd = fvf1 + fvf2;
   _count = index;
   return ESuccess;
}

MO_NAMESPACE_END
