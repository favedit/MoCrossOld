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
   MO_CLEAR(_pEffects);
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
FEffect* FRenderable::EffectFind(TCharC* pName){
   FEffect* pEffect = NULL;
   if(_pEffects != NULL){
      TInt count = _pEffects->Count();
      for(TInt n = 0; n < count; n++){
         FEffect* pFind = _pEffects->Get(n);
         if(pFind->IsName(pName)){
            pEffect = pFind;
            break;
         }
      }
   }
   return pEffect;
}

//============================================================
// <T>绑定指定名称的效果器。</T>
//
// @param pEffect 效果器
// @return 处理结果
//============================================================
TResult FRenderable::EffectBind(FEffect* pEffect){
   if(_pEffects == NULL){
      _pEffects = MO_CREATE(FEffectCollection);
   }
   _pEffects->Push(pEffect);
   return ESuccess;
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
      ERenderVertexBuffer bufferCd = (ERenderVertexBuffer)pStream->BufferCd();
      _descriptor.vertexBuffers[bufferCd] = ETrue;
   }
   return ESuccess;
}

//============================================================
// <T>根据差值器类型查找渲染纹理。</T>
//
// @param samplerCd 差值器类型
// @return 渲染纹理
//============================================================
FRenderTexture* FRenderable::FindTexture(ERenderSampler samplerCd){
   TInt count = _pTextures->Count();
   for(TInt n = 0; n < count; n++){
      FRenderTexture* pTexture = _pTextures->Get(n);
      if(pTexture->SamplerCd() == samplerCd){
         return pTexture;
      }
   }
   return NULL;
}

//============================================================
// <T>根据差值器类型获得渲染纹理。</T>
//
// @param samplerCd 差值器类型
// @return 渲染纹理
//============================================================
FRenderTexture* FRenderable::GetTexture(ERenderSampler samplerCd){
   FRenderTexture* pTexture = FindTexture(samplerCd);
   MO_CHECK(pTexture, return NULL);
   return pTexture;
}

//============================================================
// <T>功能前置处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderable::ProcessBefore(TAny* pParameter){
   return ESuccess;
}

//============================================================
// <T>功能后置处理。</T>
//
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
   if(_pEffects != NULL){
      _pEffects->Clear();
   }
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
