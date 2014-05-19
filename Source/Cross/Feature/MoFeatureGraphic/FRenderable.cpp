#include "MoFgBase.h"
#include "MoFgVisual.h"
#include "MoFgRender.h"
#include "MoFgTechnique.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderable, FInstance);

//============================================================
// <T>构造可绘制对象。</T>
//============================================================
FRenderable::FRenderable(){
   _pVisualInfo = MO_CREATE(FVisualNode);
   _pVisualInfo->SetRenderable(this);
   _pTextures = MO_CREATE(FRenderTextureCollection);
   _pVertexStreams = MO_CREATE(FRenderVertexStreams);
   MO_CLEAR(_pIndexBuffer);
   MO_CLEAR(_pActiveEffect);
}

//============================================================
// <T>析构可绘制对象。</T>
//============================================================
FRenderable::~FRenderable(){
   MO_DELETE(_pVisualInfo);
   MO_DELETE(_pTextures);
   MO_DELETE(_pVertexStreams);
}

//============================================================
// <T>查找指定名称的效果器。</T>
//
// @param pName 名称
// @return 效果器
//============================================================
FRenderableEffect* FRenderable::EffectFind(TCharC* pName){
   TInt count = _effects.Count();
   for(TInt n = 0; n < count; n++){
      FRenderableEffect* pRenderableEffect = _effects.Get(n);
      if(pRenderableEffect != NULL){
         FEffect* pEffect = pRenderableEffect->Effect();
         MO_CHECK(pEffect, continue);
         if(pEffect->IsName(pName)){
            return pRenderableEffect;
         }
      }
   }
   return NULL;
}

//============================================================
// <T>绑定指定名称的效果器。</T>
//
// @param pEffect 效果器
// @return 处理结果
//============================================================
FRenderableEffect* FRenderable::EffectBind(FEffect* pEffect){
   FRenderableEffect* pRenderableEffect = FRenderableEffect::InstanceCreate();
   pRenderableEffect->SetEffect(pEffect);
   _effects.Push(pRenderableEffect);
   return pRenderableEffect;
}

//============================================================
// <T>计算模型矩阵。</T>
//
// @param matrix 模型矩阵
// @return 处理结果
//============================================================
TResult FRenderable::CalculateModelMatrix(SFloatMatrix3d& matrix){
   return ESuccess;
}

//============================================================
// <T>计算模型矩阵。</T>
//
// @param pMatrix 矩阵集合
// @param offset 位置
// @param count 容量
// @return 处理个数
//============================================================
TInt FRenderable::CalculateBoneMatrix(SFloatMatrix3d* pMatrix, TInt offset, TInt capacity){
   return 0;
}

//============================================================
// <T>建立标志集合。</T>
//============================================================
TResult FRenderable::BuildDescriptor(){
   // 设置骨头个数
   _descriptor.vertexCount = _pVertexStreams->VertexCount();
   // 设置属性集合
   FRenderVertexStreamCollection* pStreams = _pVertexStreams->Streams();
   TInt count = pStreams->Count();
   for(TInt n = 0; n < count; n++){
      FRenderVertexStream* pStream = pStreams->Get(n);
      TCharC* pCode = pStream->Code();
      TInt bufferCd = RRenderAttribute::Parse(pCode);
      _descriptor.vertexBuffers[bufferCd] = ETrue;
   }
   return ESuccess;
}

//============================================================
// <T>根据差值器类型查找渲染纹理。</T>
//
// @param samplerCode 差值器类型
// @return 渲染纹理
//============================================================
FRenderTexture* FRenderable::FindTexture(TInt samplerCode){
   TInt count = _pTextures->Count();
   for(TInt n = 0; n < count; n++){
      FRenderTexture* pTexture = _pTextures->Get(n);
      if(pTexture->SamplerCd() == samplerCode){
         return pTexture;
      }
   }
   return NULL;
}

//============================================================
// <T>根据差值器类型获得渲染纹理。</T>
//
// @param samplerCode 差值器类型
// @return 渲染纹理
//============================================================
FRenderTexture* FRenderable::GetTexture(TInt samplerCode){
   FRenderTexture* pTexture = FindTexture(samplerCode);
   MO_CHECK(pTexture, return NULL);
   return pTexture;
}

//============================================================
// <T>更新处理。</T>
//
// @param pContext 环境
// @return 处理结果
//============================================================
TResult FRenderable::Update(TAny* pContext){
   return ESuccess;
}

//============================================================
// <T>功能前置处理。</T>
//
// @param pContext 环境
// @return 处理结果
//============================================================
TResult FRenderable::ProcessBefore(TAny* pParameter){
   return ESuccess;
}

//============================================================
// <T>功能后置处理。</T>
//
// @param pContext 环境
// @return 处理结果
//============================================================
TResult FRenderable::ProcessAfter(TAny* pParameter){
   return ESuccess;
}

//============================================================
// <T>释放内容。</T>
//============================================================
TResult FRenderable::Free(){
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderable::Suspend(){
   _pActiveEffect = NULL;
   _effects.Clear();
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderable::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderable::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
