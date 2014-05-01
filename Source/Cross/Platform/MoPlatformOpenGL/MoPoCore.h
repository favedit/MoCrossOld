#ifndef __MO_PO_CORE_H__
#define __MO_PO_CORE_H__
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

#ifndef __MO_PO_COMMON_H__
#include "MoPoCommon.h"
#endif // __MO_PO_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>设备工具。</T>
//============================================================
class MO_PO_DECLARE ROpenGL{
public:
   static GLenum ConvertFillMode(ERenderFillMode fillCd);
   static GLenum ConvertCullMode(ERenderCullMode cullCd);
   static GLenum ConvertDepthMode(ERenderDepthMode depthCd);
   static GLenum ConvertBlendFactors(ERenderBlendMode blendCd);
   static GLenum ConvertIndexStride(ERenderIndexStride strideCd);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_PO_CORE_H__
