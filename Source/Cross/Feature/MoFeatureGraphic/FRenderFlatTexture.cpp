#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderFlatTexture, FRenderTexture);

//============================================================
// <T>构造舞台对象。</T>
//============================================================
FRenderFlatTexture::FRenderFlatTexture(){
   _textureCd = ERenderTexture_Flat2d;
}

//============================================================
// <T>析构舞台对象。</T>
//============================================================
FRenderFlatTexture::~FRenderFlatTexture(){
}

//============================================================
// <T>改变大小。</T>
//
// @param width 宽度
// @param height 高度
// @return 处理结果
//============================================================
TResult FRenderFlatTexture::Resize(TInt width, TInt height){
   return ESuccess;
}

MO_NAMESPACE_END
