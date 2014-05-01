#ifndef __MO_ENGINE_DirectX11_H__
#define __MO_ENGINE_DirectX11_H__

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

MO_PD11_DECLARE void MoEngineDirectX11Initialize();
MO_PD11_DECLARE void MoEngineDirectX11Startup();
MO_PD11_DECLARE void MoEngineDirectX11Shutdown();
MO_PD11_DECLARE void MoEngineDirectX11Release();

MO_NAMESPACE_END

#endif //__MO_ENGINE_DirectX11_H__
