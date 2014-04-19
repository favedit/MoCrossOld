#ifndef __MO_ENGINE_OPENGL_H__
#define __MO_ENGINE_OPENGL_H__

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

MO_EO_DECLARE void MoEngineOpenGLInitialize();
MO_EO_DECLARE void MoEngineOpenGLStartup();
MO_EO_DECLARE void MoEngineOpenGLShutdown();
MO_EO_DECLARE void MoEngineOpenGLRelease();

MO_NAMESPACE_END

#endif //__MO_ENGINE_OPENGL_H__
