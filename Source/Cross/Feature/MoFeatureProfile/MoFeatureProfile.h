#ifndef __MO_FEATURE_PROFILE_H__
#define __MO_FEATURE_PROFILE_H__

#ifndef __MO_FF_COMMON_H__
#include "MoFfCommon.h"
#endif // __MO_FF_COMMON_H__

MO_NAMESPACE_BEGIN

MO_FF_DECLARE void MoFeatureProfileInitialize();
MO_FF_DECLARE void MoFeatureProfileStartup();
MO_FF_DECLARE void MoFeatureProfileShutdown();
MO_FF_DECLARE void MoFeatureProfileRelease();

MO_NAMESPACE_END

#endif //__MO_FEATURE_PROFILE_H__
