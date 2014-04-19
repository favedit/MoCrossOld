#ifndef __MO_GAME_ENGINE_H__
#define __MO_GAME_ENGINE_H__

#ifndef __MO_GE_COMMON_H__
#include "MoGeCommon.h"
#endif // __MO_GE_COMMON_H__

#ifndef __MO_GE_DISPLAY_H__
#include "MoGeDisplay.h"
#endif // __MO_GE_DISPLAY_H__

MO_NAMESPACE_BEGIN

MO_GE_DECLARE void MoGameEngineInitialize();
MO_GE_DECLARE void MoGameEngineStartup();
MO_GE_DECLARE void MoGameEngineShutdown();
MO_GE_DECLARE void MoGameEngineRelease();

MO_NAMESPACE_END

#endif //__MO_GAME_ENGINE_H__
