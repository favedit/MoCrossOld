#ifndef __MO_ER_DEFERRED_PIPELINE_H__
#define __MO_ER_DEFERRED_PIPELINE_H__
//************************************************************

#ifndef __MO_ER_COMMON_H__
#include "MoErCommon.h"
#endif // __MO_ER_COMMON_H__

#ifndef __MO_ER_TECHNIQUE_H__
#include "MoErTechnique.h"
#endif // __MO_ER_TECHNIQUE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>渲染效果。</T>
//============================================================
class MO_ER_DECLARE FDeferredAutomaticEffect : public FEffect{
public:
   FDeferredAutomaticEffect();
   MO_ABSTRACT ~FDeferredAutomaticEffect();
};

//============================================================
// <T>延迟渲染管道。</T>
//============================================================
class MO_ER_DECLARE FDeferredPipeline : public FPipeline
{
   MO_CLASS_DECLARE_INHERITS(FDeferredPipeline, FPipeline);
public:
   FDeferredPipeline();
   MO_ABSTRACT ~FDeferredPipeline();
public:
   MO_OVERRIDE TResult OnSetup();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_ER_DEFERRED_PIPELINE_H__
