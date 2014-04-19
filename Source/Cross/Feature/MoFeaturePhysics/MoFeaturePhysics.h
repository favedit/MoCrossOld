#ifndef __MO_FEATURE_PHYSICS_H__
#define __MO_FEATURE_PHYSICS_H__

#ifndef __MO_FP_COMMON_H__
#include "MoFpCommon.h"
#endif // __MO_FP_COMMON_H__

MO_NAMESPACE_BEGIN

MO_FP_DECLARE void MoFeaturePhysicsInitialize();
MO_FP_DECLARE void MoFeaturePhysicsStartup();
MO_FP_DECLARE void MoFeaturePhysicsShutdown();
MO_FP_DECLARE void MoFeaturePhysicsRelease();

MO_NAMESPACE_END

#endif // __MO_FEATURE_PHYSICS_H__
