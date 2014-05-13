#ifndef __MO_PDX_RENDER_H__
#define __MO_PDX_RENDER_H__
//************************************************************

#ifndef __MO_PDX_COMMON_H__
#include "MoPdxCommon.h"
#endif // __MO_PDX_COMMON_H__

#ifndef __MO_PDX_CORE_H__
#include "MoPdxCore.h"
#endif // __MO_PDX_CORE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>渲染设备。</T>
//============================================================
class MO_PDX_DECLARE FPdxRenderDevice : public FRenderDevice
{
   MO_CLASS_ABSTRACT_DECLARE_INHERITS(FPdxRenderDevice, FRenderDevice);
protected:
   // 窗口指针
   HWND _windowHandle;
public:
   FPdxRenderDevice();
   MO_ABSTRACT ~FPdxRenderDevice();
public:
   //------------------------------------------------------------
   // <T>获得窗口句柄。</T>
   MO_INLINE HWND WindowHandle(){
      return _windowHandle;
   }
   //------------------------------------------------------------
   // <T>设置窗口句柄。</T>
   MO_INLINE void SetWindowHandle(HWND handle){
      _windowHandle = handle;
   }
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_PDX_RENDER_H__
