#ifndef __MO_ENGINE2D_H__
#define __MO_ENGINE2D_H__

#ifndef __MO_E2_COMMON_H__
#include "MoE2Common.h"
#endif // __MO_E2_COMMON_H__

#ifndef __MO_E2_DISPLAY_H__
#include "MoE2Display.h"
#endif // __MO_E2_DISPLAY_H__

MO_NAMESPACE_BEGIN

MO_E2_DECLARE void MoEngine2dInitialize();
MO_E2_DECLARE void MoEngine2dStartup();
MO_E2_DECLARE void MoEngine2dShutdown();
MO_E2_DECLARE void MoEngine2dRelease();

MO_NAMESPACE_END

#endif //__MO_ENGINE2D_H__
