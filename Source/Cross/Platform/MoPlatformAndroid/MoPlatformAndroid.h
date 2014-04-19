#ifndef __MO_PLATFORM_ANDROID_H__
#define __MO_PLATFORM_ANDROID_H__

#ifndef __MO_EA_PUBLIC_H__
#include "MoEaPublic.h"
#endif // __MO_EA_PUBLIC_H__

#ifndef __MO_EA_COMMON_H__
#include "MoEaCommon.h"
#endif // __MO_EA_COMMON_H__

#ifndef __MO_EA_CORE_H__
#include "MoEaCore.h"
#endif // __MO_EA_CORE_H__

MO_NAMESPACE_BEGIN

MO_EA_DECLARE void MoPlatformAndroidInitialize();
MO_EA_DECLARE void MoPlatformAndroidStartup();
MO_EA_DECLARE void MoPlatformAndroidShutdown();
MO_EA_DECLARE void MoPlatformAndroidRelease();

MO_NAMESPACE_END

#endif //__MO_PLATFORM_ANDROID_H__
