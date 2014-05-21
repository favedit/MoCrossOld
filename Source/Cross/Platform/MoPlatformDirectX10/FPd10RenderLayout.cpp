#include "MoPd10Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd10RenderLayout, FRenderProgramLayout);

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
FRenderProgramLayoutElement* FPd10RenderLayout::FindByAttribute(FRenderProgramAttribute* pAttribute){
   TInt count = _elements.Count();
   for(TInt n = 0; n < count; n++){
      FRenderProgramLayoutElement* pElement = _elements.Get(n);
      if(pElement->Attribute() == pAttribute){
         return pElement;
      }
   }
   return NULL;
}

//============================================================
TResult FPd10RenderLayout::OnSetup(){
   MO_CHECK(_pDevice, return ENull);
   TInt index = 0;
   FPd10RenderDevice* pRenderDevice = _pDevice->Convert<FPd10RenderDevice>();
   FRenderableData* pRenderableData = _pRenderable->Data();
   GRenderShaderAttributeDictionary::TIterator iterator = _pProgram->Attributes().IteratorC();
   while(iterator.Next()){
      FRenderProgramAttribute* pAttribute = *iterator;
      if(!pAttribute->IsStatusUsed()){
         continue;
      }
      //............................................................
      TCharC* pLinker = pAttribute->Linker();
      ERenderAttributeFormat formatCd = pAttribute->FormatCd();
      FRenderableAttribute* pRenderableAttribute = pRenderableData->AttributeFind(pLinker);
      //FRenderProgramLayoutElement* pElement = FRenderProgramLayoutElement::InstanceCreate();
      //pElement->SetAttribute(pAttribute);
      //pElement->SetStream(pStream);
      //Push(pElement);
      //............................................................
      // 设置缓冲信息
      if(pRenderableAttribute != NULL){
         FPd10RenderVertexBuffer* pVertexBuffer = pRenderableAttribute->VertexBuffer()->Convert<FPd10RenderVertexBuffer>();
         _piBuffer[index] = pVertexBuffer->NativeBuffer();
         _strides[index] = pVertexBuffer->Stride();
         _offsets[index] = pRenderableAttribute->Offset();
      }else{
         _piBuffer[index] = NULL;
         _strides[index] = 0;
         _offsets[index] = 0;
      }
      index++;
   }
   _count = index;
   return ESuccess;
}

MO_NAMESPACE_END
