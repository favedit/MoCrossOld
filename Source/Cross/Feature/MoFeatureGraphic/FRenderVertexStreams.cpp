#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造渲染顶点缓冲。</T>
//============================================================
FRenderVertexStreams::FRenderVertexStreams(){
   _vertexCount = 0;
   _instanceSize = 0;
   _instanceCount = 0;
   _pBuffers = MO_CREATE(FRenderVertexBufferCollection);
   _pStreams = MO_CREATE(FRenderVertexStreamCollection);
}

//============================================================
// <T>析构渲染顶点缓冲。</T>
//============================================================
FRenderVertexStreams::~FRenderVertexStreams(){
   MO_DELETE(_pBuffers);
   MO_DELETE(_pStreams);
}

//============================================================
// <T>判断描述信息相等。</T>
//
// @param pStream 顶点流
// @return 是否相等
//============================================================
TBool FRenderVertexStreams::EqualsDescription(FRenderVertexStreams* pStreams){
   TInt count = _pStreams->Count();
   if(pStreams->Streams()->Count() != count){
      return EFalse;
   }
   for(TInt n = 0; n < count; n++){
      FRenderVertexStream* pSourceStream = pStreams->Streams()->Get(n);
      FRenderVertexStream* pStream = _pStreams->Get(n);
      if(pStream->BufferCd() != pSourceStream->BufferCd()){
         return EFalse;
      }
      if(pStream->FormatCd() != pSourceStream->FormatCd()){
         return EFalse;
      }
      if(pStream->Stride() != pSourceStream->Stride()){
         return EFalse;
      }
   }
   return ETrue;
}

//============================================================
// <T>根据类型查找流数据。</T>
//
// @param bufferCd 流类型
// @return 流数据
//============================================================
FRenderVertexStream* FRenderVertexStreams::FindStream(ERenderVertexBuffer bufferCd){
   TInt count = _pStreams->Count();
   for(TInt n = 0; n < count; n++){
      FRenderVertexStream* pStream = _pStreams->Get(n);
      if(pStream->BufferCd() == bufferCd){
         return pStream;
      }
   }
   return NULL;
}

//============================================================
// <T>根据类型获得流数据。</T>
//
// @param bufferCd 流类型
// @return 流数据
//============================================================
FRenderVertexStream* FRenderVertexStreams::GetStream(ERenderVertexBuffer bufferCd){
   FRenderVertexStream* pStream = FindStream(bufferCd);
   MO_CHECK(pStream, return NULL);
   return pStream;
}

//============================================================
// <T>增加一个顶点流。</T>
//
// @param pStream 顶点流
// @return 处理结果
//============================================================
TResult FRenderVertexStreams::PushStream(FRenderVertexStream* pStream){
   // 放入顶点流
   MO_CHECK(pStream, return ENull);
   _pStreams->Push(pStream);
   // 放入顶点缓冲
   FRenderVertexBuffer* pBuffer = pStream->VertexBuffer();
   MO_CHECK(pBuffer, return ENull);
   _pBuffers->PushUnique(pBuffer);
   return ESuccess;
}

//============================================================
// <T>接收流数据集合。</T>
//
// @param pStreams 流类型集合
// @return 处理结果
//============================================================
TResult FRenderVertexStreams::Assign(FRenderVertexStreams* pStreams){
   MO_CHECK(pStreams, return ENull);
   _vertexCount = pStreams->VertexCount();
   _instanceSize = pStreams->InstanceSize();
   _instanceCount = pStreams->InstanceCount();
   _pBuffers->Assign(pStreams->Buffers());
   _pStreams->Assign(pStreams->Streams());
   return ESuccess;
}

MO_NAMESPACE_END
