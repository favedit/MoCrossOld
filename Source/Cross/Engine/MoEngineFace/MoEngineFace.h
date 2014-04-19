#ifndef __MO_ENGINE_FRAME_H__
#define __MO_ENGINE_FRAME_H__

#ifndef __MO_FM_PUBLIC_H__
#include "MoFmPublic.h"
#endif // __MO_EW_PUBLIC_H__

#ifndef __MO_FM_COMMON_H__
#include "MoFmCommon.h"
#endif // __MO_FM_COMMON_H__

#ifndef __MO_FM_CORE_H__
#include "MoFmCore.h"
#endif // __MO_FM_CORE_H__

#ifndef __MO_FM_FACE_H__
#include "MoFmFace.h"
#endif // __MO_FM_FACE_H__

#ifndef __MO_FM_RUNTIME_H__
#include "MoFmRuntime.h"
#endif // __MO_FM_RUNTIME_H__

MO_NAMESPACE_BEGIN

MO_FM_DECLARE void MoEngineFaceInitialize();
MO_FM_DECLARE void MoEngineFaceStartup();
MO_FM_DECLARE void MoEngineFaceShutdown();
MO_FM_DECLARE void MoEngineFaceRelease();

MO_NAMESPACE_END

#endif //__MO_ENGINE_FRAME_H__
