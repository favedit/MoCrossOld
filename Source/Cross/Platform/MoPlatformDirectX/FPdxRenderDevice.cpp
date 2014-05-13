#include "MoPdxRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_ABSTRACT_IMPLEMENT_INHERITS(FPdxRenderDevice, FRenderDevice);

//============================================================
// <T>构造渲染设备。</T>
//============================================================
FPdxRenderDevice::FPdxRenderDevice(){
   MO_CLEAR(_windowHandle);
}

//============================================================
// <T>析构渲染设备。</T>
//============================================================
FPdxRenderDevice::~FPdxRenderDevice(){
}

MO_NAMESPACE_END
