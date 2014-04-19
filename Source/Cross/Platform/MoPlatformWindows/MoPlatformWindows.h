#ifndef __MO_PLATFORM_WINDOWS_H__
#define __MO_PLATFORM_WINDOWS_H__

#ifndef __MO_EW_PUBLIC_H__
#include "MoEwPublic.h"
#endif // __MO_EW_PUBLIC_H__

#ifndef __MO_EW_COMMON_H__
#include "MoEwCommon.h"
#endif // __MO_EW_COMMON_H__

#ifndef __MO_EW_CORE_H__
#include "MoEwCore.h"
#endif // __MO_EW_CORE_H__

#ifndef __MO_EW_WINDOW_H__
#include "MoEwWindow.h"
#endif // __MO_EW_WINDOW_H__

MO_NAMESPACE_BEGIN

MO_EW_DECLARE void MoPlatformWindowsInitialize();
MO_EW_DECLARE void MoPlatformWindowsStartup();
MO_EW_DECLARE void MoPlatformWindowsShutdown();
MO_EW_DECLARE void MoPlatformWindowsRelease();

MO_NAMESPACE_END

#endif //__MO_PLATFORM_WINDOWS_H__
