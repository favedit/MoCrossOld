#ifndef __MO_ENGINE_FRAME_H__
#define __MO_ENGINE_FRAME_H__

#ifndef __MO_EF_COMMON_H__
#include "MoEfCommon.h"
#endif // __MO_EF_COMMON_H__

#ifndef __MO_EF_CORE_H__
#include "MoEfCore.h"
#endif // __MO_EF_CORE_H__

#ifndef __MO_EF_FACE_H__
#include "MoEfFace.h"
#endif // __MO_EF_FACE_H__

#ifndef __MO_EF_RUNTIME_H__
#include "MoEfRuntime.h"
#endif // __MO_EF_RUNTIME_H__

MO_NAMESPACE_BEGIN

MO_EF_DECLARE void MoEngineFaceInitialize();
MO_EF_DECLARE void MoEngineFaceStartup();
MO_EF_DECLARE void MoEngineFaceShutdown();
MO_EF_DECLARE void MoEngineFaceRelease();

MO_NAMESPACE_END

#endif //__MO_ENGINE_FRAME_H__
