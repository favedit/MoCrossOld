#ifndef __MO_POE3_RENDER_H__
#define __MO_POE3_RENDER_H__
//************************************************************

#ifndef __MO_POE3_COMMON_H__
#include "MoPoe3Common.h"
#endif // __MO_POE3_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>‰÷»æ…Ë±∏°£</T>
//============================================================
class MO_POE3_DECLARE FPoe3RenderDevice : public FPoRenderDevice
{
   MO_CLASS_DECLARE_INHERITS(FPoe3RenderDevice, FRenderDevice);
public:
   FPoe3RenderDevice();
   MO_ABSTRACT ~FPoe3RenderDevice();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_POE3_RENDER_H__
