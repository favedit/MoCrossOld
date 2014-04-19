#include "MoFrContent3dModel.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dVertexBuffer, FInstance);

//============================================================
// <T>构造资源3D顶点缓冲。</T>
//============================================================
FRs3dVertexBuffer::FRs3dVertexBuffer(){
   _count = 0;
   _stride = 0;
   _pData = MO_CREATE(FBytes);
}

//============================================================
// <T>析构资源3D顶点缓冲。</T>
//============================================================
FRs3dVertexBuffer::~FRs3dVertexBuffer(){
   MO_DELETE(_pData);
}

//============================================================
// <T>根据类型查找流数据。</T>
//
// @param bufferCd 缓冲类型
// @return 流数据
//============================================================
FRs3dVertexStream* FRs3dVertexBuffer::FindStream(EContent3dVertexBuffer bufferCd){
   TInt count = _streams.Count();
   for(TInt n = 0; n < count; n++){
      FRs3dVertexStream* pStream = _streams.Get(n);
      if(pStream->BufferCd() == bufferCd){
         return pStream;
      }
   }
   return NULL;
}

//============================================================
// <T>从输入流里反序列化信息内容。</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dVertexBuffer::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取顶点流集合
   TInt streamCount = pInput->ReadInt8();
   for(TInt n = 0; n < streamCount; n++){
      FRs3dVertexStream* pStream = FRs3dVertexStream::InstanceCreate();
      pStream->SetBuffer(this);
      pStream->Unserialize(pInput);
      _streams.Push(pStream);
   }
   // 读取顶点个数
   _count = pInput->ReadInt32();
   _stride = pInput->ReadUint8();
   // 读取数据
   _pData->ForceLength(_stride * _count);
   pInput->Read(_pData->Memory(), _pData->Length());
   return ESuccess;
}

//============================================================
// <T>清空内容。</T>
//============================================================
void FRs3dVertexBuffer::Clear(){
   _streams.Clear();
}

MO_NAMESPACE_END
