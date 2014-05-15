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

//============================================================
// <T>Í¼ÐÎ¹¦ÄÜ¡£</T>
//============================================================
class MO_FG_DECLARE FFeatureGraphicFeature : public FFeature
{
   MO_CLASS_DECLARE_INHERITS(FFeatureGraphicFeature, FFeature);
public:
   FFeatureGraphicFeature();
   MO_ABSTRACT ~FFeatureGraphicFeature();
public:
   MO_OVERRIDE TResult Construct();
   MO_OVERRIDE TResult Startup();
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Shutdown();
   MO_OVERRIDE TResult Dispose();
};

MO_FG_DECLARE void MoFeatureGraphicsInitialize();
MO_FG_DECLARE void MoFeatureGraphicsStartup();
MO_FG_DECLARE void MoFeatureGraphicsShutdown();
MO_FG_DECLARE void MoFeatureGraphicsRelease();

MO_NAMESPACE_END

#endif //__MO_FEATURE_GRAPHIC_H__
