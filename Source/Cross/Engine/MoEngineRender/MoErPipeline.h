#ifndef __MO_ER_PIPELINE_H__
#define __MO_ER_PIPELINE_H__
//************************************************************

#ifndef __MO_ER_COMMON_H__
#include "MoErCommon.h"
#endif // __MO_ER_COMMON_H__

#ifndef __MO_ER_SIMPLE_PIPELINE_H__
#include "MoErSimplePipeline.h"
#endif // __MO_ER_SIMPLE_PIPELINE_H__

#ifndef __MO_ER_SHADOW_PIPELINE_H__
#include "MoErShadowPipeline.h"
#endif // __MO_ER_SHADOW_PIPELINE_H__

#ifndef __MO_ER_DEFERRED_PIPELINE_H__
#include "MoErDeferredPipeline.h"
#endif // __MO_ER_DEFERRED_PIPELINE_H__

MO_NAMESPACE_BEGIN

//============================================================
class FRectangle3d;

//============================================================
// <T>渲染管道过程。</T>
//============================================================
class MO_ER_DECLARE FRenderPipelinePass : public FPipelinePass
{
   MO_CLASS_DECLARE_INHERITS(FRenderPipelinePass, FPipelinePass);
protected:
public:
   FRenderPipelinePass();
   MO_ABSTRACT ~FRenderPipelinePass();
public:
   MO_ABSTRACT TResult DrawRegion(FRenderRegion* pRegion);
};

//============================================================
// <T>简单颜色渲染过程。</T>
//============================================================
class MO_ER_DECLARE FBlurPass : public FPipelinePass
{
   MO_CLASS_DECLARE_INHERITS(FBlurPass, FPipelinePass);
protected:
   GPtr<FEffect> _effect;
   GPtr<FRenderTexture> _inputTexture;
   GPtr<FRenderRectangle> _renderRectangle;
public:
   FBlurPass();
   MO_ABSTRACT ~FBlurPass();
public:
   //------------------------------------------------------------
   // <T>影子深度渲染过程。</T>
   MO_INLINE FRenderTexture* InputTexture(){
      return _inputTexture;
   }
   //------------------------------------------------------------
   // <T>影子深度渲染过程。</T>
   MO_INLINE void SetInputTexture(FRenderTexture* pTexture){
      _inputTexture = pTexture;
   }
public:
   MO_OVERRIDE TResult Setup();
   TResult DrawRegion(FRenderRegion* pRegion);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_ER_PIPELINE_H__
