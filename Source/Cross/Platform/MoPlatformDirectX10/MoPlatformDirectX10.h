#ifndef __MO_ENGINE_DirectX10_H__
#define __MO_ENGINE_DirectX10_H__

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

MO_PD10_DECLARE void MoEngineDirectX10Initialize();
MO_PD10_DECLARE void MoEngineDirectX10Startup();
MO_PD10_DECLARE void MoEngineDirectX10Shutdown();
MO_PD10_DECLARE void MoEngineDirectX10Release();

MO_NAMESPACE_END

#endif //__MO_ENGINE_DirectX10_H__
