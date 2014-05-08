#ifndef __MO_ENGINE_DIRECTX10_H__
#define __MO_ENGINE_DIRECTX10_H__

#ifndef __MO_PD10_COMMON_H__
#include "MoPd10Common.h"
#endif // __MO_PD10_COMMON_H__

#ifndef __MO_PD10_CORE_H__
#include "MoPd10Core.h"
#endif // __MO_PD10_CORE_H__

#ifndef __MO_PD10_RENDER_H__
#include "MoPd10Render.h"
#endif // __MO_PD10_RENDER_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>平台DirectX10功能。</T>
//============================================================
class MO_PD10_DECLARE FPlatformDirectX10Feature : public FFeature
{
   MO_CLASS_DECLARE_INHERITS(FPlatformDirectX10Feature, FFeature);
public:
   FPlatformDirectX10Feature();
   MO_ABSTRACT ~FPlatformDirectX10Feature();
public:
   MO_OVERRIDE TResult Construct();
   MO_OVERRIDE TResult Startup();
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Shutdown();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
MO_PD10_DECLARE void MoEngineDirectX10();

MO_NAMESPACE_END

#endif //__MO_ENGINE_DIRECTX10_H__
