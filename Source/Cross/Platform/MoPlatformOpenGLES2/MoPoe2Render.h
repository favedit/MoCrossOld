#ifndef __MO_POE2_RENDER_H__
#define __MO_POE2_RENDER_H__
//************************************************************

#ifdef _MO_WINDOWS
#ifndef _WINDOWS_
#include <Windows.h>
#endif // _WINDOWS_
#endif // _MO_WINDOWS

#ifdef _MO_WINDOWS
#ifndef __glew_h__
#include <gl\glew.h>
#endif // __glew_h__
#endif // _MO_WINDOWS

#ifdef _MO_ANDROID
#ifndef __gl2_h_
#include <GLES2\gl2.h>
#endif // __gl2_h_
#endif // _MO_ANDROID

#ifdef _MO_ANDROID
#ifndef __gl2ext_h_
#include <GLES2\gl2ext.h>
#endif // __gl2ext_h_
#endif // _MO_ANDROID

#ifndef __MO_POE2_COMMON_H__
#include "MoPoe2Common.h"
#endif // __MO_POE2_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>‰÷»æ…Ë±∏°£</T>
//============================================================
class MO_POE2_DECLARE FPoe2RenderDevice : public FPoRenderDevice
{
   MO_CLASS_DECLARE_INHERITS(FPoe2RenderDevice, FPoRenderDevice);
public:
   FPoe2RenderDevice();
   MO_ABSTRACT ~FPoe2RenderDevice();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_POE2_RENDER_H__
