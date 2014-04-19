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

MO_ER_DECLARE void MoEngineRenderInitialize();
MO_ER_DECLARE void MoEngineRenderStartup();
MO_ER_DECLARE void MoEngineRenderShutdown();
MO_ER_DECLARE void MoEngineRenderRelease();

MO_NAMESPACE_END

#endif //__MO_ENGINE_RENDER_H__
