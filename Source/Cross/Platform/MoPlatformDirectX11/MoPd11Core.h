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

#ifndef __D3D11SHADER_H__
#include <D3D11Shader.h>
#endif // __D3D11SHADER_H__

#ifndef __D3DCOMPILER_H__
#include <D3Dcompiler.h>
#endif // __D3DCOMPILER_H__

#ifndef __MO_PD11_COMMON_H__
#include "MoPd11Common.h"
#endif // __MO_PD11_COMMON_H__

MO_NAMESPACE_BEGIN

#define MO_INPUT_ELEMENT_MAXCNT 64

//============================================================
// <T>类型定义。</T>
typedef MO_PD11_DECLARE TFixArray<D3D11_INPUT_ELEMENT_DESC, MO_INPUT_ELEMENT_MAXCNT> MO_D3D11_INPUT_ELEMENT_DESC_ARRAY;

//============================================================
// <T>设备工具。</T>
//============================================================
class MO_PD11_DECLARE RDirectX11{
public:
   static D3D11_FILL_MODE ConvertFillMode(ERenderFillMode fillCd);
   static D3D11_CULL_MODE ConvertCullMode(ERenderCullMode cullCd);
   static DXGI_FORMAT ConvertIndexStride(ERenderIndexStride strideCd);
public:
   static ERenderAttributeFormat ParseAttrbuteFormat(D3D_REGISTER_COMPONENT_TYPE componentType, TInt mask);
   static DXGI_FORMAT ConvertAttrbuteFormat(ERenderAttributeFormat formatCd);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_PD11_CORE_H__
