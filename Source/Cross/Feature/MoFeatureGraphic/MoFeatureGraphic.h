#ifndef __MO_FEATURE_GRAPHIC_H__
#define __MO_FEATURE_GRAPHIC_H__

#ifndef __MO_FG_COMMON_H__
#include "MoFgCommon.h"
#endif // __MO_FG_COMMON_H__

#ifndef __MO_FG_CORE_H__
#include "MoFgCore.h"
#endif // __MO_FG_CORE_H__

#ifndef __MO_FG_GRAPHIC_H__
#include "MoFgGraphic.h"
#endif // __MO_FG_GRAPHIC_H__

#ifndef __MO_FG_BITMAP_H__
#include "MoFgBitmap.h"
#endif // __MO_FG_BITMAP_H__

#ifndef __MO_FG_DISPLAY_H__
#include "MoFgDisplay.h"
#endif // __MO_FG_DISPLAY_H__

MO_NAMESPACE_BEGIN

MO_FG_DECLARE void MoFeatureGraphicsInitialize();
MO_FG_DECLARE void MoFeatureGraphicsStartup();
MO_FG_DECLARE void MoFeatureGraphicsShutdown();
MO_FG_DECLARE void MoFeatureGraphicsRelease();

MO_NAMESPACE_END

#endif //__MO_FEATURE_GRAPHIC_H__
