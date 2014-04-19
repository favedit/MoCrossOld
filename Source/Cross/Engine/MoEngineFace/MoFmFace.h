#ifndef __MO_FM_FACE_H__
#define __MO_FM_FACE_H__

#ifndef __MO_FM_CONTROL_H__
#include "MoFmControl.h"
#endif // __MO_FM_CONTROL_H__

#ifndef __MO_FM_CONTAINER_H__
#include "MoFmContainer.h"
#endif // __MO_FM_CONTAINER_H__

#ifndef __MO_FM_FRAME_H__
#include "MoFmFrame.h"
#endif // __MO_FM_FRAME_H__

MO_NAMESPACE_BEGIN

MO_FM_DECLARE void MoEngineFrameInitialize();
MO_FM_DECLARE void MoEngineFrameStartup();
MO_FM_DECLARE void MoEngineFrameShutdown();
MO_FM_DECLARE void MoEngineFrameRelease();

MO_NAMESPACE_END

#endif //__MO_FM_FACE_H__
