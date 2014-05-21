#include "MoE3Model.h"
#include "MoE3Material.h"
#include "MoE3Instance.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FModel3dGeometry, FRenderable3d);

//============================================================
// <T>构造实体3D几何体。</T>
//============================================================
FModel3dGeometry::FModel3dGeometry(){
   MO_CLEAR(_pModel);
   MO_CLEAR(_pResource);
   MO_CLEAR(_pMaterialResource);
   _material = MO_CREATE(FMaterial3d);
   _pBones = MO_CREATE(FBone3dCollection);
}

//============================================================
// <T>析构实体3D几何体。</T>
//============================================================
FModel3dGeometry::~FModel3dGeometry(){
   MO_DELETE(_pBones);
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源对象
//============================================================
TResult FModel3dGeometry::LoadResource(FRs3dGeometry* pResource){
   MO_CHECK(pResource, return ENull);
   _pResource = pResource;
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   //............................................................
   FRenderableData* pRenderableData = FRenderableData::InstanceCreate();
   SetData(pRenderableData);
   //............................................................
   // 获得顶点数据
   FRs3dVertexBuffer* pRsVertexBuffer = pResource->VertexBuffer();
   TInt vertexCount = pRsVertexBuffer->Count();
   TInt vertexStride = pRsVertexBuffer->Stride();
   FBytes* pVertexData = pRsVertexBuffer->Data();
   // 创建顶点缓冲
   FRenderVertexBuffer* pVertexBuffer = pRenderDevice->CreateVertexBuffer();
   pVertexBuffer->SetOwner(this);
   pVertexBuffer->SetCount(vertexCount);
   pVertexBuffer->SetStride(vertexStride);
   pVertexBuffer->Setup();
   pVertexBuffer->BuildData();
   pVertexBuffer->Upload(pVertexData->MemoryC(), pVertexData->Length());
   pVertexBuffer->DataStream()->Assign(pVertexData->MemoryC(), pVertexData->Length());
   // 创建流集合
   GRs3dVertexStreamPtrs& rsVertexStreams = pRsVertexBuffer->Streams();
   TInt vertexStreamCount = rsVertexStreams.Count();
   for(int n = 0; n < vertexStreamCount; n++){
      // 获得顶点流信息
      FRs3dVertexStream* pRsVertexStream = rsVertexStreams.Get(n);
      TCharC* pBufferCode = pRsVertexStream->Code();
      ERenderAttributeFormat formatCd = (ERenderAttributeFormat)pRsVertexStream->FormatCd();
      TInt offset = pRsVertexStream->Offset();
      // 创建渲染对象属性
      FRenderableAttribute* pAttribute = FRenderableAttribute::InstanceCreate();
      pAttribute->SetCode(pBufferCode);
      pAttribute->SetFormatCd(formatCd);
      pAttribute->SetOffset(offset);
      pAttribute->SetVertexBuffer(pVertexBuffer);
      pRenderableData->AttributePush(pAttribute);
   }
   pRenderableData->SetVertexCount(vertexCount);
   //............................................................
   // 获得索引数据
   FRs3dIndexBuffer* pRsIndexBuffer = pResource->IndexBuffer();
   ERenderIndexStride strideCd = (ERenderIndexStride)pRsIndexBuffer->StrideCd();
   TInt indexCount = pRsIndexBuffer->Count();
   FBytes* pIndexData = pRsIndexBuffer->Data();
   // 创建索引缓冲
   FRenderIndexBuffer* pIndexBuffer = pRenderDevice->CreateIndexBuffer();
   pIndexBuffer->SetOwner(this);
   pIndexBuffer->SetCount(indexCount);
   pIndexBuffer->SetStrideCd(strideCd);
   pIndexBuffer->Setup();
   // 设置数据
   pIndexBuffer->BuildData();
   pIndexBuffer->Upload(pIndexData->MemoryC(), pIndexData->Length());
   pIndexBuffer->DataStream()->Assign(pIndexData->MemoryC(), pIndexData->Length());
   pRenderableData->SetIndexBuffer(pIndexBuffer);
   //............................................................
   // 设置材质
   TCharC* pMaterialName = pResource->MaterialName();
   _material = RInstance3dManager::Instance().MaterialConsole()->Load(pMaterialName);
   return ESuccess;
}

MO_NAMESPACE_END
