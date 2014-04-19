#include "MoFgRender.h"
#include "MoFgTechnique.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造渲染技术。</T>
//============================================================
FTechnique::FTechnique(){
   _pEffects = MO_CREATE(FEffectCollection);
   MO_CLEAR(_pProgram);
}

//============================================================
// <T>析构渲染技术。</T>
//============================================================
FTechnique::~FTechnique(){
   MO_DELETE(_pEffects);
   MO_DELETE(_pProgram);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FTechnique::Setup(){
   return ESuccess;
}

//============================================================
//FEffect* FTechnique::FindFEffect(ERenderEffect typeCd){
//   TInt count = _pEffects->Count();
//   for(TInt n = 0; n < count; n++){
//      FEffect* pEffect = _pEffects->Get(n);
//      if(pEffect->TypeCd() == typeCd){
//         return pEffect;
//      }
//   }
//   return NULL;
//}

//============================================================
// <T>变更大小。</T>
//
// @return 处理结果
//============================================================
TResult FTechnique::Resize(TInt width, TInt height){
   TInt count = _pEffects->Count();
   for(TInt n = 0; n < count; n++){
      FEffect* pEffect = _pEffects->Get(n);
      pEffect->Resize(width, height);
   }
   return ESuccess;
}

//============================================================
// <T>绘制处理。</T>
//
// @return 处理结果
//============================================================
TResult FTechnique::Draw(FRenderRegion* pRegion, TInt offset, TInt count){
   //MO_CHECK(pRegion, return ENull);
   //FRenderableCollection* pRenderables = pRegion->Renderables();
   //TInt offsetEnd = offset + count;
   ////............................................................
   //// 建立效果器
   ////for(TInt n = offset; n < offsetEnd; ){
   ////   FRenderable* pRenderable = (FRenderable*)pRenderables->Get(n);
   ////   FEffect* pEffect = pRenderable->EffectFind(_name);
   ////   if(pEffect == NULL){
   ////      SEffectDescriptor descriptor;
   ////      // REffectManager::Instance().BuildAutomatic();
   ////   }
   ////}
   ////............................................................
   //// 根据渲染类型进行分组
   //for(TInt n = offset; n < offsetEnd; ){
   //   // 获得分组
   //   TInt groupBegin = n;
   //   TInt groupEnd = offsetEnd;
   //   FRenderable* pGroupRenderable = pRenderables->Get(groupBegin);
   //   FMaterial* pGroupMaterial = pGroupRenderable->Material();
   //   ERenderEffect effectCd = pGroupMaterial->EffectCd();
   //   for(TInt i = n; i < offsetEnd; i++){
   //      FRenderable* pRenderable = pRenderables->Get(i);
   //      FMaterial* pMaterial = pRenderable->Material();
   //      if(pGroupMaterial->Compare(pMaterial) != 0){
   //         groupEnd = i;
   //         break;
   //      }
   //      n++;
   //   }
   //   //............................................................
   //   // 绘制当前组
   //   FEffect* pEffect = FindFEffect(effectCd);
   //   pEffect->DrawGroup(pRegion, groupBegin, groupEnd - groupBegin);
   //}
   return ESuccess;
}

MO_NAMESPACE_END
