#ifndef __MO_PD9_CORE_H__
#define __MO_PD9_CORE_H__
//************************************************************

#ifdef _MO_WINDOWS
#ifndef _WINDOWS_
#include <Windows.h>
#endif // _WINDOWS_
#endif // _MO_WINDOWS

#ifndef __dxgi_h__
#include <DXGI.h>
#endif // __dxgi_h__

#ifndef _D3D9_H_
#include <d3d9.h>
#endif // _D3D9_H_

#ifndef __D3DX9_H__
#include <d3dx9.h>
#endif // __D3DX9_H__

#ifndef _DXERR_H_
#include <DxErr.h>
#endif // _DXERR_H_

#ifndef __D3DCOMPILER_H__
#include <D3Dcompiler.h>
#endif // __D3DCOMPILER_H__

#ifndef __MO_PD9_COMMON_H__
#include "MoPd9Common.h"
#endif // __MO_PD9_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>设备工具。</T>
//============================================================
class MO_PD9_DECLARE RDirectX9{
public:
   //static D3D9_FILL_MODE ConvertFillMode(ERenderFillMode fillCd);
   //static D3D9_CULL_MODE ConvertCullMode(ERenderCullMode cullCd);
   //static DXGI_FORMAT ConvertIndexStride(ERenderIndexStride strideCd);
public:
   //static ERenderAttributeFormat ParseAttrbuteFormat(D3D_REGISTER_COMPONENT_TYPE componentType, TInt mask);
   static D3DDECLTYPE ConvertAttrbuteFormat(ERenderAttributeFormat formatCd);
   static TResult ConvertAttrbuteUsage(TInt index, D3DDECLUSAGE* pUsageCd, TInt* pUsageIndex);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_PD9_CORE_H__
