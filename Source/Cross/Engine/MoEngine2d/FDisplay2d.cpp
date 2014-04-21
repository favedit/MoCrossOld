#include "MoE2Display.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FDisplay2d, FRenderable);

//============================================================
// <T>构造显示对象。</T>
//============================================================
FDisplay2d::FDisplay2d(){
   _typeCd = 0;
}

//============================================================
// <T>析构显示对象。</T>
//============================================================
FDisplay2d::~FDisplay2d(){
}

//============================================================
// <T>设置材质。</T>
//
// @param pMaterial 材质
// @return 处理结果
//============================================================
TResult FDisplay2d::SetMaterial(FMaterial* pMaterial){
   _pMaterial = pMaterial;
   return ESuccess;
}

//============================================================
// <T>计算渲染信息。</T>
//
// @param renderable 渲染信息
// @return 处理结果
//============================================================
TResult FDisplay2d::CalculateRenderable(SRenderable& renderable){
   SRenderableItem& item = renderable.Alloc();
   //// 设置坐标
   //item.location.Assign(_location);
   //// 设置尺寸
   //item.size.Assign(_size);
   //// 设置方向
   //item.rotation.Assign(_rotation);
   //// 设置纹理
   //item.coord.Assign(_coord);
   //// 设置背景颜色
   //item.groundColor.Assign(_groundColor);
   //// 设置内容
   //item.matrix.Identity();
   //item.matrix.Translate(-_gravityCenter.x, -_gravityCenter.y, -_gravityCenter.z);
   //item.matrix.Rotation(_rotation.x, _rotation.y, _rotation.z);
   //renderable.matrix.UpdateForce();
   return ESuccess;
}

//============================================================
// <T>粒子处理。</T>
//
// @param data 粒子数据
// @return 处理结果
//============================================================
TResult FDisplay2d::DoParticle(SParticleData& data){
   // 移动处理
   //if(data.actionCd == EParticleAction_Move){
   //   _location.Assign(data.location);
   //   // _size.Assign(data.size);
   //   _rotation.Assign(data.rotation);
   //}
   //// 释放处理
   //if(data.actionCd == EParticleAction_Free){
   //   Free();
   //}
   return ESuccess;
}

//============================================================
// <T>释放处理。</T>
//
// @return 处理结果
//============================================================
TResult FDisplay2d::Free(){
   // 脱离父组件
   //RemoveFromParent();
   // 释放自己
   RDisplay2dManager::Instance().DisplayFree(this);
   return ESuccess;
}

MO_NAMESPACE_END
