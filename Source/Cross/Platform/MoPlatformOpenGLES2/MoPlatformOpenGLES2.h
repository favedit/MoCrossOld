#ifndef __MO_ENGINE_OPENGLES2_H__
#define __MO_ENGINE_OPENGLES2_H__

#ifndef __MO_POE2_PUBLIC_H__
#include "MoPoe2Public.h"
#endif // __MO_POE2_PUBLIC_H__

#ifndef __MO_POE2_COMMON_H__
#include "MoPoe2Common.h"
#endif // __MO_POE2_COMMON_H__

#ifndef __MO_POE2_CORE_H__
#include "MoPoe2Core.h"
#endif // __MO_POE2_CORE_H__

#ifndef __MO_POE2_RENDER_H__
#include "MoPoe2Render.h"
#endif // __MO_POE2_RENDER_H__

#ifndef __MO_POE2_TECHNIQUE_H__
#include "MoPoe2Technique.h"
#endif // __MO_POE2_TECHNIQUE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>平台OpenGL2功能。</T>
//============================================================
class MO_POE2_DECLARE FPlatformOpenGLES2Feature : public FFeature
{
   MO_CLASS_DECLARE_INHERITS(FPlatformOpenGLES2Feature, FFeature);
public:
   FPlatformOpenGLES2Feature();
   MO_ABSTRACT ~FPlatformOpenGLES2Feature();
public:
   MO_OVERRIDE TResult Construct();
   MO_OVERRIDE TResult Startup();
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Shutdown();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
MO_POE2_DECLARE void MoEngineOpenGLES2();

MO_NAMESPACE_END

#endif //__MO_ENGINE_OPENGLES2_H__
