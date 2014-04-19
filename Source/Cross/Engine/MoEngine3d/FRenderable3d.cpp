#include "MoE3Display.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderable3d, FRenderable);

//============================================================
// <T>构造可渲染3D对象。</T>
//============================================================
FRenderable3d::FRenderable3d(){
}

//============================================================
// <T>析构可渲染3D对象。</T>
//============================================================
FRenderable3d::~FRenderable3d(){
}

//============================================================
// <T>查找动画。</T>
//
// @return 动画
//============================================================
FAnimation3d* FRenderable3d::FindAnimation(){
   MO_FATAL_UNSUPPORT();
   return NULL;
}

//============================================================
// <T>查找轨迹。</T>
//
// @return 轨迹
//============================================================
FRs3dTrack* FRenderable3d::FindTrack(){
   MO_FATAL_UNSUPPORT();
   return NULL;
}

//============================================================
// <T>计算渲染信息。</T>
//
// @param renderable 渲染信息
// @return 处理结果
//============================================================
TResult FRenderable3d::CalculateRenderable(SRenderable& renderable){
   SRenderableItem& item = renderable.Alloc();
   // 复制数据
   //item.location.Assign(_location);
   //item.size.Assign(_size);
   //item.coord.Assign(_coord);
   //item.groundColor.Assign(_groundColor);
   // 计算所有父矩阵
   //FComponent* pComponent = _pParent;
   //while(pComponent != NULL){
   //   if(pComponent->IsObject(EComponent_Drawable)){
   //      FDrawable* pDrawable = (FDrawable*)pComponent;
   //      renderable.matrix.Append(pDrawable->Matrix());
   //   }
   //   pComponent = pComponent->Parent();
   //}
   return ESuccess;
}

//============================================================
// <T>建立标志集合。</T>
//============================================================
TResult FRenderable3d::BuildFlags(){
   return ESuccess;
}

MO_NAMESPACE_END
