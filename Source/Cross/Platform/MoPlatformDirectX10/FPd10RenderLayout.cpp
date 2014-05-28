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
TResult FPd10RenderLayout::OnSetup(){
   MO_CHECK(_pDevice, return ENull);
   FPd10RenderDevice* pRenderDevice = _pDevice->Convert<FPd10RenderDevice>();
   FRenderableGeometry* pRenderableGeometry = _pRenderable->Geometry();
   TInt count = _pProgram->Attributes().Count();
   for(TInt n = 0; n < count; n++){
      FRenderProgramAttribute* pAttribute = _pProgram->Attributes().Get(n);
      if(!pAttribute->IsStatusUsed()){
         continue;
      }
      //............................................................
      TCharC* pLinker = pAttribute->Linker();
      ERenderAttributeFormat formatCd = pAttribute->FormatCd();
      FRenderableAttribute* pRenderableAttribute = pRenderableGeometry->AttributeFind(pLinker);
      //FRenderProgramLayoutElement* pElement = FRenderProgramLayoutElement::InstanceCreate();
      //pElement->SetAttribute(pAttribute);
      //pElement->SetStream(pStream);
      //Push(pElement);
      //............................................................
      // 设置缓冲信息
      if(pRenderableAttribute != NULL){
         FPd10RenderVertexBuffer* pVertexBuffer = pRenderableAttribute->GraphicsObject<FPd10RenderVertexBuffer>();
         _piBuffer[n] = pVertexBuffer->NativeBuffer();
         _strides[n] = pVertexBuffer->Stride();
         _offsets[n] = pRenderableAttribute->Offset();
      }else{
         _piBuffer[n] = NULL;
         _strides[n] = 0;
         _offsets[n] = 0;
      }
   }
   _count = count;
   return ESuccess;
}

MO_NAMESPACE_END
