#ifndef __MO_ENGINE_OPENGL_H__
#define __MO_ENGINE_OPENGL_H__

#ifndef __MO_POE3_COMMON_H__
#include "MoPoe3Common.h"
#endif // __MO_POE3_COMMON_H__

#ifndef __MO_POE3_CORE_H__
#include "MoPoe3Core.h"
#endif // __MO_POE3_CORE_H__

#ifndef __MO_POE3_RENDER_H__
#include "MoPoe3Render.h"
#endif // __MO_POE3_RENDER_H__

MO_NAMESPACE_BEGIN

MO_POE3_DECLARE void MoEngineOpenGLInitialize();
MO_POE3_DECLARE void MoEngineOpenGLStartup();
MO_POE3_DECLARE void MoEngineOpenGLShutdown();
MO_POE3_DECLARE void MoEngineOpenGLRelease();

MO_NAMESPACE_END

#endif //__MO_ENGINE_OPENGL_H__
