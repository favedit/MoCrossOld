#ifndef __MO_EF_FACE_H__
#define __MO_EF_FACE_H__

#ifndef __MO_EF_CONTROL_H__
#include "MoEfControl.h"
#endif // __MO_EF_CONTROL_H__

#ifndef __MO_EF_CONTAINER_H__
#include "MoEfContainer.h"
#endif // __MO_EF_CONTAINER_H__

#ifndef __MO_EF_FRAME_H__
#include "MoEfFrame.h"
#endif // __MO_EF_FRAME_H__

MO_NAMESPACE_BEGIN

MO_EF_DECLARE void MoEngineFrameInitialize();
MO_EF_DECLARE void MoEngineFrameStartup();
MO_EF_DECLARE void MoEngineFrameShutdown();
MO_EF_DECLARE void MoEngineFrameRelease();

MO_NAMESPACE_END

#endif //__MO_EF_FACE_H__
