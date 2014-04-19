#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderCubeTexture, FRenderTexture);

//============================================================
// <T>构造舞台对象。</T>
//============================================================
FRenderCubeTexture::FRenderCubeTexture(){
   _textureCd = ERenderTexture_Cube;
}

//============================================================
// <T>析构舞台对象。</T>
//============================================================
FRenderCubeTexture::~FRenderCubeTexture(){
}

//============================================================
// <T>改变大小。</T>
//
// @param size 大小
// @return 处理结果
//============================================================
TResult FRenderCubeTexture::Resize(TInt size){
   return ESuccess;
}

MO_NAMESPACE_END
