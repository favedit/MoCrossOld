#ifndef __MO_FEATURE_SOUND_H__
#define __MO_FEATURE_SOUND_H__

#ifndef __MO_FS_COMMON_H__
#include "MoFsCommon.h"
#endif // __MO_FS_COMMON_H__

MO_NAMESPACE_BEGIN

MO_FS_DECLARE void MoFeatureSoundInitialize();
MO_FS_DECLARE void MoFeatureSoundStartup();
MO_FS_DECLARE void MoFeatureSoundShutdown();
MO_FS_DECLARE void MoFeatureSoundRelease();

MO_NAMESPACE_END

#endif //__MO_FEATURE_SOUND_H__
