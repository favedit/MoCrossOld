#include "MoFgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FInstanceRenderable, FRenderable);

//============================================================
// <T>构造实例渲染对象。</T>
//============================================================
FInstanceRenderable::FInstanceRenderable(){
}

//============================================================
// <T>析构实例渲染对象。</T>
//============================================================
FInstanceRenderable::~FInstanceRenderable(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FInstanceRenderable::Setup(){
   MO_ASSERT(_renderable);
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   //............................................................
   // 计算最大实体个数
   TInt vertexCount = _renderable->VertexStreams()->VertexCount();
   TInt boneCount = _renderable->CalculateBoneMatrix(NULL, 0, 0);
   if(_instanceCount <= 0){
      _instanceCount = RRenderUtil::CalculateInstanceCount(vertexCount, boneCount);
   }
   MO_ASSERT(_instanceCount > 0);
   //............................................................
   // 创建索引缓冲
   TInt vertexTotal = vertexCount * _instanceCount;
   _pVertexStreams->SetVertexCount(vertexTotal);
   _pVertexStreams->SetInstanceSize(vertexCount);
   _pVertexStreams->SetInstanceCount(_instanceCount);
   // 创建实例编号缓冲
   FRenderVertexBuffer* pInstanceIndexBuffer = pRenderDevice->CreateVertexBuffer();
   pInstanceIndexBuffer->SetCount(vertexTotal);
   pInstanceIndexBuffer->SetStride(sizeof(TFloat));
   pInstanceIndexBuffer->Setup();
   pInstanceIndexBuffer->BuildData();
   TFloat* pInstanceIdWriter = pInstanceIndexBuffer->DataMemory<TFloat>();
   for(TInt n = 0; n < _instanceCount; n++){
      RFloats::Fill(pInstanceIdWriter, vertexCount, (TFloat)n);
      pInstanceIdWriter += vertexCount;
   }
   pInstanceIndexBuffer->UploadData();
   // 创建实例编号流
   FRenderVertexStream* pInstanceIdStream = FRenderVertexStream::InstanceCreate();
   pInstanceIdStream->SetCode("Instance");
   pInstanceIdStream->SetFormatCd(ERenderAttributeFormat_Float1);
   pInstanceIdStream->SetVertexBuffer(pInstanceIndexBuffer);
   _pVertexStreams->PushStream(pInstanceIdStream);
   // 创建属性流集合
   FRenderVertexStreams* pVertexStreams = _renderable->VertexStreams();
   FRenderVertexBufferCollection::TIteratorC bufferIterator = pVertexStreams->Buffers()->IteratorC();
   while(bufferIterator.Next()){
      // 获得顶点缓冲
      FRenderVertexBuffer* pVertexBuffer = *bufferIterator;
      TInt stride = pVertexBuffer->Stride();
      TByte* pVertexData = pVertexBuffer->DataMemory<TByte>();
      TInt vertexDataLength = pVertexBuffer->DataLength();
      // 创建顶点缓冲
      FRenderVertexBuffer* pInstanceVertexBuffer = pRenderDevice->CreateVertexBuffer();
      pInstanceVertexBuffer->SetCount(vertexTotal);
      pInstanceVertexBuffer->SetStride(stride);
      pInstanceVertexBuffer->Setup();
      pInstanceVertexBuffer->BuildData();
      TByte* pInstanceVertexWriter = pInstanceVertexBuffer->DataMemory<TByte>();
      for(TInt n = 0; n < _instanceCount; n++){
         RBytes::Copy(pInstanceVertexWriter, pVertexData, vertexDataLength);
         pInstanceVertexWriter += vertexDataLength;
      }
      // 创建顶点流
      FRenderVertexStreamCollection::TIteratorC iterator = pVertexStreams->Streams()->IteratorC();
      while(iterator.Next()){
         // 获得顶点流信息
         FRenderVertexStream* pVertexStream = *iterator;
         if(pVertexStream->VertexBuffer() != pVertexBuffer){
            continue;
         }
         TCharC* pBufferCode = pVertexStream->Code();
         ERenderAttributeFormat formatCd = pVertexStream->FormatCd();
         TInt offset = pVertexStream->Offset();
         // 创建渲染流
         FRenderVertexStream* pInstanceVertexStream = FRenderVertexStream::InstanceCreate();
         pInstanceVertexStream->SetCode(pBufferCode);
         pInstanceVertexStream->SetFormatCd(formatCd);
         pInstanceVertexStream->SetOffset(offset);
         pInstanceVertexStream->SetVertexBuffer(pInstanceVertexBuffer);
         _pVertexStreams->PushStream(pInstanceVertexStream);
         //// 创建顶点缓冲
         //if(bufferCd == ERenderAttribute_BoneIndex){
         //   // 重新计算骨头索引位置
         //   pInstanceVertexBuffer->SetStride(sizeof(TFloat) * 4);
         //   pInstanceVertexBuffer->Setup();
         //   pInstanceVertexBuffer->SetupData();
         //   TFloat boneIndex = 0.0f;
         //   TInt vertexUnitTotal = 4 * vertexCount;
         //   TFloat* pInstanceVertexWriter = pInstanceVertexBuffer->DataMemory<TFloat>();
         //   for(TInt n = 0; n < _instanceCount; n++){
         //      for(TInt i = 0; i < vertexUnitTotal; i++){
         //         *pInstanceVertexWriter++ = (TFloat)pVertexData[i] + boneIndex;
         //      }
         //      boneIndex += (TFloat)(boneCount);
         //   }
         //   formatCd = ERenderVertexFormat_Float4;
         //}else{
         //   // 复制数据
         //   pInstanceVertexBuffer->SetStride(stride);
         //   pInstanceVertexBuffer->Setup();
         //   pInstanceVertexBuffer->SetupData();
         //   TByte* pInstanceVertexWriter = pInstanceVertexBuffer->DataMemory<TByte>();
         //   for(TInt n = 0; n < _instanceCount; n++){
         //      for(TInt i = 0; i < vertexCount; i++){
         //      }
         //      RBytes::Copy(pInstanceVertexWriter, pVertexData, vertexDataLength);
         //      pInstanceVertexWriter += vertexDataLength;
         //   }
         //}
      }
      pInstanceVertexBuffer->UploadData();
   }
   //............................................................
   // 获得渲染对象顶点数据
   FRenderIndexBuffer* pIndexBuffer = _renderable->IndexBuffer();
   TInt indexCount = pIndexBuffer->Count();
   ERenderIndexStride indexStrideCd = pIndexBuffer->StrideCd();
   TUint16* pIndexData = pIndexBuffer->DataMemory<TUint16>();
   // 创建实例顶点寄存器
   TInt indexTotal = indexCount * _instanceCount;
   _pIndexBuffer = pRenderDevice->CreateIndexBuffer();
   _pIndexBuffer->SetCount(indexTotal);
   _pIndexBuffer->SetStrideCd(indexStrideCd);
   _pIndexBuffer->SetInstanceStride(indexCount);
   _pIndexBuffer->SetInstanceCount(_instanceCount);
   _pIndexBuffer->Setup();
   // 填充数据
   _pIndexBuffer->BuildData();
   TUint16 indexPosition = 0;
   TUint16* pInstanceIndexWriter = _pIndexBuffer->DataMemory<TUint16>();
   for(TInt n = 0; n < _instanceCount; n++){
      for(TInt i = 0; i < indexCount; i++){
         *pInstanceIndexWriter++ = indexPosition + pIndexData[i];
      }
      indexPosition += vertexCount;
   }
   _pIndexBuffer->UploadData();
   //............................................................
   // 设置材质
   _material = _renderable->Material();
   _pTextures->Assign(_renderable->Textures());
   MO_INFO("Build instance renderable. (type_name=%s, vertex_count=%d, index_count=%d, instance=%d)",
         (TCharC*)_typeName, vertexCount, indexCount, _instanceCount);
   return ESuccess;
}

MO_NAMESPACE_END
