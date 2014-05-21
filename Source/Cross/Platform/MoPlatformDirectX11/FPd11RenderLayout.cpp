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
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderLayout::OnSetup(){
   MO_CHECK(_pRenderable, return ENull);
   TInt index = 0;
   GRenderShaderAttributeDictionary::TIterator iterator = _pProgram->Attributes().IteratorC();
   while(iterator.Next()){
      FRenderProgramAttribute* pAttribute = *iterator;
      // 检查使用中
      if(!pAttribute->IsStatusUsed()){
         continue;
      }
      // 获得渲染属性
      TCharC* pLinker = pAttribute->Linker();
      FRenderableAttribute* pRenderableAttribute = _pRenderable->AttributeFind(pLinker);
      MO_CHECK(pRenderableAttribute->CheckValid(), continue);
      //............................................................
      // 设置缓冲信息
      if(pRenderableAttribute != NULL){
         FPd11RenderVertexBuffer* pVertexBuffer = pRenderableAttribute->VertexBuffer()->Convert<FPd11RenderVertexBuffer>();
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
