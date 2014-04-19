#ifndef __MO_FEATURE_INPUT_H__
#define __MO_FEATURE_INPUT_H__

#ifndef __MO_FI_COMMON_H__
#include "MoFiCommon.h"
#endif // __MO_FI_COMMON_H__

#ifndef __MO_FI_INPUT_FEATURE_H__
#include "MoInputFeature.h"
#endif // __MO_FI_INPUT_FEATURE_H__

MO_NAMESPACE_BEGIN

MO_FI_DECLARE void MoFeatureInputInitialize();
MO_FI_DECLARE void MoFeatureInputStartup();
MO_FI_DECLARE void MoFeatureInputShutdown();
MO_FI_DECLARE void MoFeatureInputRelease();

MO_NAMESPACE_END

#endif //__MO_FEATURE_INPUT_H__
