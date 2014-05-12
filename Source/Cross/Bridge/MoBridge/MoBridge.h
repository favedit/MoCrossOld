#ifndef __MO_BRIDGE_H__
#define __MO_BRIDGE_H__

#ifndef __MO_BG_COMMON_H__
#include "MoBgCommon.h"
#endif // __MO_BG_COMMON_H__

#ifndef __MO_BG_CORE_H__
#include "MoBgCore.h"
#endif // __MO_BG_CORE_H__

MO_NAMESPACE_BEGIN

//============================================================
EXTERN_C MO_BG_DECLARE void MoBridgeInitialize();
EXTERN_C MO_BG_DECLARE void MoBridgeRelease();

MO_NAMESPACE_END



#endif //__MO_BRIDGE_H__
