#include "MoPoRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPoRenderLayout, FRenderLayout);

//============================================================
// <T>构造渲染层信息。</T>
//============================================================
FPoRenderLayout::FPoRenderLayout(){
   _count = 0;
}

//============================================================
// <T>析构渲染层信息。</T>
//============================================================
FPoRenderLayout::~FPoRenderLayout(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderLayout::OnSetup(){
   MO_CHECK(_pRenderable, return ENull);
   TInt index = 0;
   GRenderShaderAttributePtrs& attributes = _pProgram->Attributes();
   TInt count = attributes.Count();
   for(TInt n = 0; n < count; n++){
      FRenderProgramAttribute* pAttribute = _pProgram->Attributes().Get(n);
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
         FPoRenderVertexBuffer* pVertexBuffer = pRenderableAttribute->GraphicsObject<FPoRenderVertexBuffer>();
         _pBuffers[index] = pVertexBuffer;
         _slots[index] = pAttribute->Slot();
         _offsets[index] = pRenderableAttribute->Offset();
         _formats[index] = pAttribute->FormatCd();
         index++;
      }
   }
   _count = index;
   return ESuccess;
}

MO_NAMESPACE_END
