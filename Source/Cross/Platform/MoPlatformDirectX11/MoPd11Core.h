#ifndef __MO_PD11_CORE_H__
#define __MO_PD11_CORE_H__
//************************************************************

#ifdef _MO_WINDOWS
#ifndef _WINDOWS_
#include <Windows.h>
#endif // _WINDOWS_
#endif // _MO_WINDOWS

#ifndef __dxgi_h__
#include <DXGI.h>
#endif // __dxgi_h__

#ifndef __d3d11_h__
#include <d3d11.h>
#endif // __d3d11_h__

#ifndef __D3DX11_H__
#include <d3dx11.h>
#endif // __D3DX11_H__

#ifndef __MO_PD11_COMMON_H__
#include "MoPd11Common.h"
#endif // __MO_PD11_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>设备工具。</T>
//============================================================
class MO_PD11_DECLARE RDirectX11{
public:
   static D3D11_FILL_MODE ConvertFillMode(ERenderFillMode fillCd);
   static D3D11_CULL_MODE ConvertCullMode(ERenderCullMode cullCd);
   static DXGI_FORMAT ConvertIndexStride(ERenderIndexStride strideCd);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_PD11_CORE_H__
