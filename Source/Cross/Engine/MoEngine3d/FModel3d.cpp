#include "MoE3Model.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FModel3d, FDisplay3d);

//============================================================
// <T>构造实体3D模型。</T>
//============================================================
FModel3d::FModel3d(){
   _pGeometrys = MO_CREATE(FModel3dGeometryCollection);
   _pAnimation = MO_CREATE(FAnimation3d);
}

//============================================================
// <T>析构实体3D模型。</T>
//============================================================
FModel3d::~FModel3d(){
   MO_DELETE(_pRenderables);
   MO_DELETE(_pAnimation);
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源对象
// @return 处理结果
//============================================================
TResult FModel3d::LoadResource(FRs3dModel* pResource){
   MO_ASSERT(pResource);
   _pResource = pResource;
   // 建立渲染对象集合
   GRs3dGeometryPtrs& geometrys = pResource->Geometrys();
   TInt count = geometrys.Count();
   for(TInt n = 0; n < count; n++){
      FRs3dGeometry* pRsGeometry = geometrys.Get(n);
      // 获得渲染对象模板
      FModel3dGeometry* pGeometry = FModel3dGeometry::InstanceCreate();
      pGeometry->LoadResource(pRsGeometry);
      _pGeometrys->Push(pGeometry);
   }
   return ESuccess;
}

MO_NAMESPACE_END
