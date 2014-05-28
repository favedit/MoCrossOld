#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderTarget, FRenderInstance);

//============================================================
// <T>构造渲染目标。</T>
//============================================================
FRenderTarget::FRenderTarget(){
   _optionDepth = EFalse;
   _optionStencil = EFalse;
   _size.Set(1024, 1024);
   _usageCd = ERenderUsage_ReadWrite;
   _textureCd = ERenderTexture_Unknown;
   _formatCd = ERenderTextureFormat_Unknown;
   _backgroundColor.Set(0.0f, 0.0f, 0.0f, 0.0f);
   _pTextures = MO_CREATE(FRenderTextureCollection);
}

//============================================================
// <T>析构渲染目标。</T>
//============================================================
FRenderTarget::~FRenderTarget(){
   MO_DELETE(_pTextures);
}

MO_NAMESPACE_END
