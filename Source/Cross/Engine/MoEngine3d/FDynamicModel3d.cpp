#include "MoE3Model.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FDynamicModel3d, FDisplay3d);

//============================================================
// <T>构造实体3D模型。</T>
//============================================================
FDynamicModel3d::FDynamicModel3d(){
   _pModelRenderables = MO_CREATE(FModel3dRenderableCollection);
   _pGeometrys = MO_CREATE(FDynamicModel3dGeometryCollection);
}

//============================================================
// <T>析构实体3D模型。</T>
//============================================================
FDynamicModel3d::~FDynamicModel3d(){
   MO_DELETE(_pModelRenderables);
   MO_DELETE(_pRenderables);
}

//============================================================
// <T>根据材质同步一个几何体。</T>
//============================================================
FDynamicModel3dGeometry* FDynamicModel3d::SyncGeometryByRenderable(FModel3dRenderable* pRenderable){
   FMaterial* pMaterial = pRenderable->MaterialReference();
   // 查找几何体
   TInt count = _pGeometrys->Count();
   for(TInt n = 0; n < count; n++){
      FDynamicModel3dGeometry* pGeometry = _pGeometrys->Get(n);
      if(pGeometry->Material() == pMaterial){
         if(pGeometry->VertexStreams()->EqualsDescription(pRenderable->VertexStreams())){
            return pGeometry;
         }
      }
   }
   // 新建几何体
   FDynamicModel3dGeometry* pGeometry = FDynamicModel3dGeometry::InstanceCreate();
   pGeometry->SetMaterial(pMaterial);
   pGeometry->VertexStreams()->Assign(pRenderable->VertexStreams());
   _pGeometrys->Push(pGeometry);
   return pGeometry;
}

//============================================================
// <T>增加一个渲染对象。</T>
//============================================================
TResult FDynamicModel3d::PushRenderable(FModel3dRenderable* pRenderable){
   // 放入集合
   _pModelRenderables->Push(pRenderable);
   // 放入集合
   FMaterial* pMaterial = pRenderable->MaterialReference();
   FDynamicModel3dGeometry* pGeometry = SyncGeometryByRenderable(pRenderable);
   pGeometry->ModelRenderables()->Push(pRenderable);
   return ESuccess;
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源对象
// @return 处理结果
//============================================================
TResult FDynamicModel3d::Merge(){
   TInt count = _pGeometrys->Count();
   for(TInt n = 0; n < count; n++){
      FDynamicModel3dGeometry* pGeometry = _pGeometrys->Get(n);
      pGeometry->Merge();
   }
   return ESuccess;
}

MO_NAMESPACE_END
