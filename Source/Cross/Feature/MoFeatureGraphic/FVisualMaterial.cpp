#include "MoFgBase.h"
#include "MoFgVisual.h"
#include "MoFgTechnique.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FVisualMaterial, FInstance);

//============================================================
// <T>构造可见区域。</T>
//============================================================
FVisualMaterial::FVisualMaterial(){
   MO_CLEAR(_pMaterial);
   _pVisuals = MO_CREATE(FVisualCollection);
}

//============================================================
// <T>析构可见区域。</T>
//============================================================
FVisualMaterial::~FVisualMaterial(){
   MO_DELETE(_pVisuals);
}

//============================================================
// <T>增加一个可视对象。</T>
//
// @param pVisual 可见节点
// @return 处理结果
//============================================================
TResult FVisualMaterial::Push(FVisualNode* pVisual){
   _pVisuals->Push(pVisual);
   return ESuccess;
}

//============================================================
// <T>渲染对象进行排序。</T>
//
// @param pSource 来源对象
// @param pTarget 目标对象
// @param pCondition 条件
// @return 比较结果
//============================================================
static TInt VisualMaterialRenderableComparer(FVisualNode* pSource, FVisualNode* pTarget, TAny* pCondition){
   // 获得数据
   FRenderable* pSourceRenderable = pSource->Renderable();
   FRenderable* pTargetRenderable = pTarget->Renderable();
   // 比较效果器
   FEffect* pSourceEffect = pSourceRenderable->ActiveEffect();
   MO_CHECK(pSourceEffect, return 0);
   FEffect* pTargetEffect = pTargetRenderable->ActiveEffect();
   MO_CHECK(pTargetEffect, return 0);
   if(pSourceEffect != pTargetEffect){
      return pSourceEffect - pTargetEffect;
   }
   // 比较网格（使用索引检查）
   FRenderIndexBuffer* pSourceIndexBuffer = pSourceRenderable->IndexBuffer();
   MO_CHECK(pSourceIndexBuffer, return 0);
   FRenderIndexBuffer* pTargetIndexBuffer = pTargetRenderable->IndexBuffer();
   MO_CHECK(pTargetIndexBuffer, return 0);
   if(pSourceIndexBuffer != pTargetIndexBuffer){
      return pSourceIndexBuffer - pTargetIndexBuffer;
   }
   return 0;
}

//============================================================
TResult FVisualMaterial::Process(){
   _pVisuals->SortValue(&VisualMaterialRenderableComparer);
   return ESuccess;
}

//============================================================
// <T>重置内容。</T>
//
// @return 处理结果
//============================================================
TResult FVisualMaterial::Reset(){
   _pVisuals->Clear();
   return ESuccess;
}

MO_NAMESPACE_END
