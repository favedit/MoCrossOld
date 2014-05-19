#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderVertexStream, FInstance);

//============================================================
// <T>构造渲染顶点缓冲。</T>
//============================================================
FRenderVertexStream::FRenderVertexStream(){
   _formatCd = ERenderAttributeFormat_Unknown;
   _offset = 0;
   MO_CLEAR(_pVertexBuffer);
}

//============================================================
// <T>析构渲染顶点缓冲。</T>
//============================================================
FRenderVertexStream::~FRenderVertexStream(){
}

//============================================================
// <T>获得顶点宽度。</T>
//
// @return 宽度
//============================================================
TInt FRenderVertexStream::Stride(){
   TInt stride = 0;
   if(_pVertexBuffer != NULL){
      stride = _pVertexBuffer->Stride();
   }
   return stride;
}

MO_NAMESPACE_END
