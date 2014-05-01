#ifndef __MO_ENGINE_OpenGLES2_H__
#define __MO_ENGINE_OpenGLES2_H__

#ifndef __MO_PD9_PUBLIC_H__
#include "MoPd9Public.h"
#endif // __MO_PD9_PUBLIC_H__

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

MO_PD9_DECLARE void MoEngineDirectX9Initialize();
MO_PD9_DECLARE void MoEngineDirectX9Startup();
MO_PD9_DECLARE void MoEngineDirectX9Shutdown();
MO_PD9_DECLARE void MoEngineDirectX9Release();

MO_NAMESPACE_END

#endif //__MO_ENGINE_OpenGLES2_H__
