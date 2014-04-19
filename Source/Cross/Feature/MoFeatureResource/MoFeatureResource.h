#ifndef __MO_FEATURE_RESOURCE_H__
#define __MO_FEATURE_RESOURCE_H__

#ifndef __MO_FR_COMMON_H__
#include "MoFrCommon.h"
#endif // __MO_FR_COMMON_H__

#ifndef __MO_FR_LOADER_H__
#include "MoFrLoader.h"
#endif // __MO_FR_LOADER_H__

#ifndef __MO_FR_CONTENT2D_H__
#include "MoFrContent2d.h"
#endif // __MO_FR_CONTENT2D_H__

#ifndef __MO_FR_CONTENT3D_H__
#include "MoFrContent3d.h"
#endif // __MO_FR_CONTENT3D_H__

MO_NAMESPACE_BEGIN

MO_FR_DECLARE void MoFeatureResourceInitialize();
MO_FR_DECLARE void MoFeatureResourceStartup();
MO_FR_DECLARE void MoFeatureResourceShutdown();
MO_FR_DECLARE void MoFeatureResourceRelease();

MO_NAMESPACE_END

#endif //__MO_FEATURE_RESOURCE_H__
