#ifndef __MO_ENGINE_DIRECTX11_H__
#define __MO_ENGINE_DIRECTX11_H__

#ifndef __MO_PD11_COMMON_H__
#include "MoPd11Common.h"
#endif // __MO_PD11_COMMON_H__

#ifndef __MO_PD11_CORE_H__
#include "MoPd11Core.h"
#endif // __MO_PD11_CORE_H__

#ifndef __MO_PD11_RENDER_H__
#include "MoPd11Render.h"
#endif // __MO_PD11_RENDER_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>平台DirectX11功能。</T>
//============================================================
class MO_PD11_DECLARE FPlatformDirectX11Feature : public FFeature
{
   MO_CLASS_DECLARE_INHERITS(FPlatformDirectX11Feature, FFeature);
public:
   FPlatformDirectX11Feature();
   MO_ABSTRACT ~FPlatformDirectX11Feature();
public:
   MO_OVERRIDE TResult Construct();
   MO_OVERRIDE TResult Startup();
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Shutdown();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
MO_PD11_DECLARE void MoEngineDirectX11();

MO_NAMESPACE_END

#endif //__MO_ENGINE_DIRECTX11_H__
