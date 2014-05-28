#include "MoFgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FDynamicRenderable, FRenderable);

//============================================================
// <T>构造动态渲染对象。</T>
//============================================================
FDynamicRenderable::FDynamicRenderable(){
   _vertexTotal = 0;
   _indexTotal = 0;
}

//============================================================
// <T>析构动态渲染对象。</T>
//============================================================
FDynamicRenderable::~FDynamicRenderable(){
}

//============================================================
// <T>根据顶点缓冲信息获得一个顶点缓冲。</T>
//
// @param pBuffer 顶点缓冲
// @return 顶点缓冲
//============================================================
FRenderVertexBuffer* FDynamicRenderable::SyncVertexBuffer(FRenderVertexBuffer* pBuffer){
   FRenderVertexBuffer* pMergeVertexBuffer = NULL;
   //if(!_pVertexStreams->Buffers()->IsEmpty()){
   //   pMergeVertexBuffer = _pVertexStreams->Buffers()->Get(0);
   //}
   //// 创建顶点流
   //if(pMergeVertexBuffer == NULL){
   //   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   //   TInt stride = pBuffer->Stride();
   //   // 创建顶点缓冲
   //   pMergeVertexBuffer = pRenderDevice->CreateVertexBuffer();
   //   pMergeVertexBuffer->SetStride(stride);
   //   pMergeVertexBuffer->SetCount(_vertexTotal);
   //   pMergeVertexBuffer->Setup();
   //   pMergeVertexBuffer->BuildData();
   //   _pVertexStreams->Buffers()->Push(pMergeVertexBuffer);
   //}
   return pMergeVertexBuffer;
}

//============================================================
// <T>根据顶点流信息获得一个顶点缓冲。</T>
//
// @param pBuffer 顶点流
// @return 顶点流
//============================================================
FRenderVertexStream* FDynamicRenderable::SyncVertexStream(FRenderVertexStream* pStream){
   //TCharC* pCode = pStream->Code();
   //FRenderVertexStream* pMergeVertexStream = _pVertexStreams->FindStream(pCode);
   //// 创建顶点流
   //if(pMergeVertexStream == NULL){
   //   // 查找顶点缓冲
   //   FRenderVertexBuffer* pVertexBuffer = pStream->VertexBuffer();
   //   FRenderVertexBuffer* pMergeVertexBuffer = SyncVertexBuffer(pVertexBuffer);
   //   // 创建流缓冲
   //   pMergeVertexStream = FRenderVertexStream::InstanceCreate();
   //   pMergeVertexStream->SetCode(pStream->Code());
   //   pMergeVertexStream->SetFormatCd(pStream->FormatCd());
   //   pMergeVertexStream->SetVertexBuffer(pMergeVertexBuffer);
   //   _pVertexStreams->PushStream(pMergeVertexStream);
   //}
   //return pMergeVertexStream;
   return NULL;
}

//============================================================
// <T>测试指定渲染对象是否可以被当前渲染对象合并。</T>
//
// @param pRenderable 渲染对象
// @return 是否可以被合并
//============================================================
TBool FDynamicRenderable::TestMergeable(FRenderable* pRenderable){
   MO_CHECK(pRenderable, return ENull);
   // 计算常量缓冲限制
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   TInt mergeLimit = RRenderUtil::CalculateMergeLimit();
   TInt renderableCount = _renderables.Count() + 1;
   if(renderableCount > mergeLimit){
      return EFalse;
   }
   // 计算顶点流限制
   TInt vertexCountLimit = pRenderDevice->Capability()->VertexCountLimit();
   //FRenderVertexStreams* pVertexStreams = pRenderable->VertexStreams();
   //TInt vertexCount = pVertexStreams->VertexCount();
   //TInt vertexTotal = _vertexTotal + vertexCount;
   //if(vertexTotal > vertexCountLimit){
   //   return EFalse;
   //}
   return ETrue;
}

//============================================================
// <T>增加一个渲染对象。</T>
//
// @param pRenderable 渲染对象
// @return 处理结果
//============================================================
TResult FDynamicRenderable::Push(FRenderable* pRenderable){
   //MO_CHECK(pRenderable, return ENull);
   //// 检查是否可以合并
   //TBool mergeable = TestMergeable(pRenderable);
   //MO_CHECK(mergeable, return EFailure);
   //// 修正数量
   //FRenderVertexStreams* pVertexStreams = pRenderable->VertexStreams();
   //TInt vertexCount = pVertexStreams->VertexCount();
   //_vertexTotal += vertexCount;
   //TInt indexCount = pRenderable->IndexBuffer()->Count();
   //_indexTotal += indexCount;
   //// 设置信息
   //if(_renderables.IsEmpty()){
   //   _material = pRenderable->Material();
   //   _materialReference = pRenderable->MaterialReference();
   //   _pTextures->Assign(pRenderable->Textures());
   //}
   //// 放入合并池
   //_renderables.Push(pRenderable);
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FDynamicRenderable::Setup(){
   //FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   //_pVertexStreams->SetVertexCount(_vertexTotal);
   ////............................................................
   //// 创建实例顶点寄存器
   //_pIndexBuffer = pRenderDevice->CreateIndexBuffer();
   //_pIndexBuffer->SetCount(_indexTotal);
   //_pIndexBuffer->Setup();
   //_pIndexBuffer->BuildData();
   ////............................................................
   //TInt vertexTotal = 0;
   //TInt indexTotal = 0;
   //TInt renderableCount = _renderables.Count();
   //for(TInt n = 0; n < renderableCount; n++){
   //   FRenderable* pRenderable = _renderables.Get(n);
   //   FRenderVertexStreams* pVertexStreams = pRenderable->VertexStreams();
   //   TInt vertexCount = pVertexStreams->VertexCount();
   //   SFloatMatrix3d& renderableMatrix = pRenderable->Matrix();
   //   FRenderVertexBufferCollection::TIteratorC bufferIterator = pVertexStreams->Buffers()->IteratorC();
   //   //............................................................
   //   // 顶点变换矩阵
   //   SFloatMatrix3d modelMatrix;
   //   modelMatrix.Assign(renderableMatrix);
   //   while(bufferIterator.Next()){
   //      // 获得顶点数据
   //      FRenderVertexBuffer* pVertexBuffer = *bufferIterator;
   //      TInt vertexStride = pVertexBuffer->Stride();
   //      TInt vertexDataLength = pVertexBuffer->DataLength();
   //      TByte* pVertexData = pVertexBuffer->DataMemory<TByte>();
   //      // 写入顶点数据
   //      FRenderVertexBuffer* pMergeVertexBuffer = SyncVertexBuffer(pVertexBuffer);
   //      MO_ASSERT(pMergeVertexBuffer->Stride() == vertexStride);
   //      TByte* pMergeVertexData = pMergeVertexBuffer->DataMemory<TByte>();
   //      pMergeVertexData += vertexStride * vertexTotal;
   //      RBytes::Copy(pMergeVertexData, pVertexData, vertexDataLength);
   //      // 获得流缓冲
   //      FRenderVertexStreamCollection::TIteratorC streamIterator = pVertexStreams->Streams()->IteratorC();
   //      while(streamIterator.Next()){
   //         FRenderVertexStream* pVertexStream = *streamIterator;
   //         TCharC* pBufferCode = pVertexStream->Code();
   //         TInt vertexOffset = pVertexStream->Offset();
   //         FRenderVertexStream* pMergeVertexStream = SyncVertexStream(pVertexStream);
   //         FRenderVertexBuffer* pMergeVertexBuffer = pMergeVertexStream->VertexBuffer();
   //         if(RString::Equals(pBufferCode, "Position")){
   //            // 换算位置为世界位置
   //            for(TInt i = 0; i < vertexCount; i++){
   //               TFloat* pVertexDataReader = (TFloat*)(pVertexData + vertexStride * i + vertexOffset);
   //               TFloat* pMergePositionWriter = (TFloat*)(pMergeVertexData + vertexStride * i + vertexOffset);
   //               SFloatPoint3 modelPosition = modelMatrix.TransformPoint3(pVertexDataReader[0], pVertexDataReader[1], pVertexDataReader[2]);
   //               *pMergePositionWriter++ = modelPosition.x;
   //               *pMergePositionWriter++ = modelPosition.y;
   //               *pMergePositionWriter++ = modelPosition.z;
   //            }
   //         }
   //      }
   //   }
   //   //............................................................
   //   // 获得渲染对象索引数据
   //   FRenderIndexBuffer* pIndexBuffer = pRenderable->IndexBuffer();
   //   TInt indexCount = pIndexBuffer->Count();
   //   TUint16* pIndexData = pIndexBuffer->DataMemory<TUint16>();
   //   // 填充索引数据
   //   TUint16* pMergeIndexWriter = _pIndexBuffer->DataMemory<TUint16>();
   //   pMergeIndexWriter += indexTotal;
   //   for(TInt i = 0; i < indexCount; i++){
   //      *pMergeIndexWriter++ = vertexTotal + pIndexData[i];
   //   }
   //   //............................................................
   //   vertexTotal += vertexCount;
   //   indexTotal += indexCount;
   //}
   //FRenderVertexStreamCollection::TIteratorC streamIterator = _pVertexStreams->Streams()->IteratorC();
   //while(streamIterator.Next()){
   //   FRenderVertexStream* pVertexStream = *streamIterator;
   //   pVertexStream->VertexBuffer()->UploadData();
   //}
   //_pIndexBuffer->UploadData();
   ////............................................................
   //// 设置材质
   //MO_INFO("Build dynamic renderable. (count=%d, vertex_total=%d, index_total=%d)",
   //      _renderables.Count(), vertexTotal, indexTotal);
   return ESuccess;
}

MO_NAMESPACE_END
