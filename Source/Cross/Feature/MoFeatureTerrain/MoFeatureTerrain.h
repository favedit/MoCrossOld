#ifndef __MO_FEATURE_TERRAIN_H__
#define __MO_FEATURE_TERRAIN_H__

#ifndef __MO_FT_COMMON_H__
#include "MoFtCommon.h"
#endif // __MO_FT_COMMON_H__

MO_NAMESPACE_BEGIN

MO_FT_DECLARE void MoFeatureTerrainInitialize();
MO_FT_DECLARE void MoFeatureTerrainStartup();
MO_FT_DECLARE void MoFeatureTerrainShutdown();
MO_FT_DECLARE void MoFeatureTerrainRelease();

MO_NAMESPACE_END

#endif //__MO_FEATURE_TERRAIN_H__
