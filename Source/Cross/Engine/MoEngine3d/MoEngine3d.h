#ifndef __MO_ENGINE3D_H__
#define __MO_ENGINE3D_H__

#ifndef __MO_E3_COMMON_H__
#include "MoE3Common.h"
#endif // __MO_E3_COMMON_H__

#ifndef __MO_E3_DISPLAY_H__
#include "MoE3Display.h"
#endif // __MO_E3_DISPLAY_H__

#ifndef __MO_E3_INSTANCE_H__
#include "MoE3Instance.h"
#endif // __MO_E3_INSTANCE_H__

MO_NAMESPACE_BEGIN

MO_E3_DECLARE void MoEngine3dInitialize();
MO_E3_DECLARE void MoEngine3dStartup();
MO_E3_DECLARE void MoEngine3dShutdown();
MO_E3_DECLARE void MoEngine3dRelease();

MO_NAMESPACE_END

#endif //__MO_ENGINE3D_H__
