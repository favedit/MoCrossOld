#include "MoFgRender.h"
#include "MoFgTechnique.h"
#include "MoFgPipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPipelinePass, FInstance);

//============================================================
// <T>构造渲染管道过程。</T>
//============================================================
FPipelinePass::FPipelinePass(){
   MO_CLEAR(_pPipeline);
   _lastSortTick = 0;
}

//============================================================
// <T>析构渲染管道过程。</T>
//============================================================
FPipelinePass::~FPipelinePass(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPipelinePass::Setup(){
   // 注册统计器
   _renderableActiveStatistics = RStatisticsManager::Instance().SyncByName("pipeline.pass.renderable.active");
   _renderableSortStatistics = RStatisticsManager::Instance().SyncByName("pipeline.pass.renderable.sort");
   _renderableDrawStatistics = RStatisticsManager::Instance().SyncByName("pipeline.pass.renderable.draw");
   return ESuccess;
}

//============================================================
// <T>变更大小。</T>
//
// @return 处理结果
//============================================================
TResult FPipelinePass::Resize(TInt width, TInt height){
   return ESuccess;
}

//============================================================
// <T>绘制开始处理。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FPipelinePass::DrawBegin(FRenderRegion* pRegion){
   MO_CHECK(pRegion, return ENull);
   return ESuccess;
}

//============================================================
// <T>绘制区域处理。</T>
//
// @return 处理结果
//============================================================
TResult FPipelinePass::DrawRegion(FRenderRegion* pRegion){
   MO_CHECK(pRegion, return ENull);
   // 获得渲染集合
   FRenderableCollection* pRenderables = pRegion->Renderables();
   //............................................................
   TFsName spaceName;
   spaceName.AppendFormat("%s.%s.", _pPipeline->Name(), (TCharC*)_name);
   //............................................................
   // 获得渲染对象的渲染器
   _renderableActiveStatistics->Begin();
   TInt count = pRenderables->Count();
   for(TInt n = 0; n < count; n++){
      FRenderable* pRenderable = pRenderables->Get(n);
      FMaterial* pMaterial = pRenderable->Material();
      //pRenderable->UpdateMatrix();
      // 获得渲染效果
      TFsName effectName = spaceName.MemoryC();
      effectName.Append(pMaterial->EffectName());
      FEffect* pEffect = pRenderable->EffectFind(effectName);
      if(pEffect == NULL){
         pEffect = REffectManager::Instance().Find(effectName, pRenderable);
         pRenderable->EffectBind(pEffect);
      }
      pRenderable->SetActiveEffect(pEffect);
   }
   _renderableActiveStatistics->Finish();
   //............................................................
   // 获得已经处理好的可见渲染对象集合
   FRenderableCollection* pVisibleRenderables = pRegion->VisibleRenderables();
   FVisualRegion* pVisualRegion = pRegion->VisualRegion();
   pVisualRegion->FetchVisuals(pVisibleRenderables);
   pVisualRegion->PushVisuals(pRenderables);
   //............................................................
   // 根据效果类型进行分组
   _renderableDrawStatistics->Begin();
   TInt visibleCount = pVisibleRenderables->Count();
   for(TInt n = 0; n < visibleCount; ){
      // 获得分组
      TInt groupBegin = n;
      TInt groupEnd = visibleCount;
      FRenderable* pGroupRenderable = pVisibleRenderables->Get(groupBegin);
      FEffect* pGroupEffect = pGroupRenderable->ActiveEffect();
      for(TInt i = n; i < visibleCount; i++){
         FRenderable* pRenderable = (FRenderable*)pVisibleRenderables->Get(i);
         FEffect* pEffect = pRenderable->ActiveEffect();
         if(pGroupEffect != pEffect){
            groupEnd = i;
            break;
         }
         n++;
      }
      // 绘制当前渲染组
      pGroupEffect->DrawGroup(pRegion, groupBegin, groupEnd - groupBegin);
   }
   _renderableDrawStatistics->Finish();
   return ESuccess;
}

//============================================================
// <T>绘制结束处理。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FPipelinePass::DrawEnd(FRenderRegion* pRegion){
   MO_CHECK(pRegion, return ENull);
   return ESuccess;
}

MO_NAMESPACE_END
