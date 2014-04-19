#pragma once

// Windows Header
#include <windows.h>
// 3DS Max headers
#include <Max.h>
#include <iskin.h>
#include <bitmap.h>
#include <Stdmat.h>
#include <CS\Phyexp.h>
#include <CS\BIPEXP.H>
// IGame headers
#include <IGame/IGame.h>
#include <IGame/IGameProperty.h>
#include <IGame/IGameControl.h>
#include <IGame/IGameModifier.h>
#include <IGame/IConversionManager.h>
#include <IGame/IGameError.h>
#include <IGame/IGameFX.h>
// Microbject headers
#include <MoCommon.h>

//============================================================
/// @define 导出定义
#ifdef _MO_3E_EXPORTS
#define MO_3E_DECLARE MO_EXPORT
#else
#define MO_3E_DECLARE MO_IMPORT
#endif
