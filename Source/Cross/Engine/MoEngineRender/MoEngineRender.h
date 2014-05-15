#ifndef __MO_ENGINE_RENDER_H__
#define __MO_ENGINE_RENDER_H__

#ifndef __MO_ER_COMMON_H__
#include "MoErCommon.h"
#endif // __MO_ER_COMMON_H__

#ifndef __MO_ER_TECHNIQUE_H__
#include "MoErTechnique.h"
#endif // __MO_ER_TECHNIQUE_H__

#ifndef __MO_ER_PIPELINE_H__
#include "MoErPipeline.h"
#endif // __MO_ER_PIPELINE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>ÒýÇæäÖÈ¾¹¦ÄÜ¡£</T>
//============================================================
class MO_ER_DECLARE FEngineRenderFeature : public FFeature
{
   MO_CLASS_DECLARE_INHERITS(FEngineRenderFeature, FFeature);
public:
   FEngineRenderFeature();
   MO_ABSTRACT ~FEngineRenderFeature();
public:
   MO_OVERRIDE TResult Construct();
   MO_OVERRIDE TResult Startup();
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Shutdown();
   MO_OVERRIDE TResult Dispose();
};

MO_ER_DECLARE void MoEngineRender();

MO_NAMESPACE_END

#endif //__MO_ENGINE_RENDER_H__
