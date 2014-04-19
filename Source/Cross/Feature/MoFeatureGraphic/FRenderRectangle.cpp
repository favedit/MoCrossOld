#include "MoFgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderRectangle, FRenderable);

//============================================================
// <T>构造实体3D几何体。</T>
//============================================================
FRenderRectangle::FRenderRectangle(){
   _location.Set(-1.0f, -1.0f);
   _size.Set(2.0f, 2.0f);
   MO_CLEAR(_pVertexBuffer);
   //MO_CLEAR(_pIndexBuffer);
   //_pMaterial = MO_CREATE(FMaterial3d);
}

//============================================================
// <T>析构实体3D几何体。</T>
//============================================================
FRenderRectangle::~FRenderRectangle(){
   //MO_DELETE(_pVertexBuffer);
   //MO_DELETE(_pIndexBuffer);
   //MO_DELETE(_pMaterial);
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源对象
//============================================================
TResult FRenderRectangle::Setup(){
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   // 计算坐标
   TFloat xMin = _location.x;
   TFloat xMax = _location.x + _size.width;
   TFloat yMin = _location.y;
   TFloat yMax = _location.y + _size.height;
   // 创建顶点缓冲
   //_pVertexStreams
   MO_ASSERT(!_pVertexBuffer);
   _pVertexBuffer = pRenderDevice->CreateVertexBuffer();
   _pVertexBuffer->SetStride(sizeof(TFloat) * 5);
   _pVertexBuffer->SetCount(4);
   _pVertexBuffer->Setup();
   _pVertexBuffer->BuildData();
   TFloat* pVertexWriter = (TFloat*)_pVertexBuffer->DataStream()->Memory();
   *pVertexWriter++ = xMin; *pVertexWriter++ = yMax; *pVertexWriter++ = 0.0f; *pVertexWriter++ = 0.0f; *pVertexWriter++ = 1.0f;
   *pVertexWriter++ = xMax; *pVertexWriter++ = yMax; *pVertexWriter++ = 0.0f; *pVertexWriter++ = 1.0f; *pVertexWriter++ = 1.0f;
   *pVertexWriter++ = xMax; *pVertexWriter++ = yMin; *pVertexWriter++ = 0.0f; *pVertexWriter++ = 1.0f; *pVertexWriter++ = 0.0f;
   *pVertexWriter++ = xMin; *pVertexWriter++ = yMin; *pVertexWriter++ = 0.0f; *pVertexWriter++ = 0.0f; *pVertexWriter++ = 0.0f;
   _pVertexBuffer->UploadData(EFalse);
   // 设置流
   //FRenderVertexStream* pStream = MO_CREATE(FRenderVertexStream);
   //pStream->SetVertexBuffer(_pVertexBuffer);
   // 创建索引缓冲
   MO_ASSERT(!_pIndexBuffer);
   _pIndexBuffer = pRenderDevice->CreateIndexBuffer();
   _pIndexBuffer->SetCount(6);
   _pIndexBuffer->Setup();
   _pIndexBuffer->BuildData();
   TUint16* pIndexWriter = (TUint16*)_pIndexBuffer->DataStream()->Memory();
   *pIndexWriter++ = 0; *pIndexWriter++ = 1; *pIndexWriter++ = 2;
   *pIndexWriter++ = 0; *pIndexWriter++ = 2; *pIndexWriter++ = 3;
   _pIndexBuffer->UploadData(EFalse);
   return ESuccess;
}

MO_NAMESPACE_END
