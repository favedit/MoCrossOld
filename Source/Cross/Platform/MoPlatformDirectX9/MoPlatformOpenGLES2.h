#ifndef __MO_ENGINE_OpenGLES2_H__
#define __MO_ENGINE_OpenGLES2_H__

#ifndef __MO_EO_PUBLIC_H__
#include "MoEoPublic.h"
#endif // __MO_EO_PUBLIC_H__

#ifndef __MO_EO_COMMON_H__
#include "MoEoCommon.h"
#endif // __MO_EO_COMMON_H__

#ifndef __MO_EO_CORE_H__
#include "MoEoCore.h"
#endif // __MO_EO_CORE_H__
#ifndef __MO_EO_RENDER_H__
#include "MoEoRender.h"
#endif // __MO_EO_RENDER_H__

#ifndef __MO_EO_TECHNIQUE_H__
#include "MoEoTechnique.h"
#endif // __MO_EO_TECHNIQUE_H__

MO_NAMESPACE_BEGIN

MO_EO_DECLARE void MoEngineOpenGLES2Initialize();
MO_EO_DECLARE void MoEngineOpenGLES2Startup();
MO_EO_DECLARE void MoEngineOpenGLES2Shutdown();
MO_EO_DECLARE void MoEngineOpenGLES2Release();

MO_NAMESPACE_END

#endif //__MO_ENGINE_OpenGLES2_H__
