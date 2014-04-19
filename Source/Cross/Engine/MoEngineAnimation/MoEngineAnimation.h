#ifndef __MO_ENGINE_ANIMATION_H__
#define __MO_ENGINE_ANIMATION_H__

#ifndef __MO_EA_COMMON_H__
#include "MoEaCommon.h"
#endif // __MO_EA_COMMON_H__

MO_NAMESPACE_BEGIN

MO_EA_DECLARE void MoEngineAnimationInitialize();
MO_EA_DECLARE void MoEngineAnimationStartup();
MO_EA_DECLARE void MoEngineAnimationShutdown();
MO_EA_DECLARE void MoEngineAnimationRelease();

MO_NAMESPACE_END

#endif //__MO_ENGINE_ANIMATION_H__
