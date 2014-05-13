#ifndef __MO_BF_FEATURE_GRAPHIC_H__
#define __MO_BF_FEATURE_GRAPHIC_H__
//************************************************************

#ifndef __MO_BG_COMMON_H__
#include "MoBgCommon.h"
#endif // __MO_BG_COMMON_H__

#ifndef __MO_BG_CORE_H__
#include "MoBgCore.h"
#endif // __MO_BG_CORE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>‰÷»æ…Ë±∏°£</T>
//============================================================
class MO_BG_DECLARE FBfRenderDevice : public FRenderDevice
{
public:
   FBfRenderDevice();
   MO_ABSTRACT ~FBfRenderDevice();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_BF_FEATURE_GRAPHIC_H__
