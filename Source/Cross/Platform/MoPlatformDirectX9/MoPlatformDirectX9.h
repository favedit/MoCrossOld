#ifndef __MO_ENGINE_DIRECTX9_H__
#define __MO_ENGINE_DIRECTX9_H__

#ifndef __MO_PD9_COMMON_H__
#include "MoPd9Common.h"
#endif // __MO_PD9_COMMON_H__

#ifndef __MO_PD9_CORE_H__
#include "MoPd9Core.h"
#endif // __MO_PD9_CORE_H__

#ifndef __MO_PD9_RENDER_H__
#include "MoPd9Render.h"
#endif // __MO_PD9_RENDER_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>平台DirectX9功能。</T>
//============================================================
class MO_PD9_DECLARE FPlatformDirectX9Feature : public FFeature
{
   MO_CLASS_DECLARE_INHERITS(FPlatformDirectX9Feature, FFeature);
public:
   FPlatformDirectX9Feature();
   MO_ABSTRACT ~FPlatformDirectX9Feature();
public:
   MO_OVERRIDE TResult Construct();
   MO_OVERRIDE TResult Startup();
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Shutdown();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
MO_PD9_DECLARE void MoEngineDirectX9();

//============================================================
MO_PD9_DECLARE TResult SetupFeature();

MO_NAMESPACE_END

#endif //__MO_ENGINE_DIRECTX9_H__
