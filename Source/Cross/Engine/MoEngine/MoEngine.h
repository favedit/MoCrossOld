#ifndef __MO_ENGINE_H__
#define __MO_ENGINE_H__

#ifndef __MO_EG_COMMON_H__
#include "MoEgCommon.h"
#endif // __MO_EG_COMMON_H__

#ifndef __MO_EG_CORE_H__
#include "MoEgCore.h"
#endif // __MO_EG_CORE_H__

#ifndef __MO_EG_PLATFORM_H__
#include "MoEgPlatform.h"
#endif // __MO_EG_PLATFORM_H__

#ifndef __MO_EG_ENGINE_H__
#include "MoEgEngine.h"
#endif // __MO_EG_ENGINE_H__

MO_NAMESPACE_BEGIN

MO_EG_DECLARE void MoEngineInitialize();
MO_EG_DECLARE void MoEngineStartup();
MO_EG_DECLARE void MoEngineShutdown();
MO_EG_DECLARE void MoEngineRelease();

MO_NAMESPACE_END

#endif //__MO_ENGINE_H__
