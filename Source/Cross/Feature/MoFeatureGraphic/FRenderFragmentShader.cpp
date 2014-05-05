#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_ABSTRACT_IMPLEMENT_INHERITS(FRenderFragmentShader, FRenderShader);

//============================================================
// <T>构造舞台对象。</T>
//============================================================
FRenderFragmentShader::FRenderFragmentShader(){
   _shaderCd = ERenderShader_Fragment;
}

//============================================================
// <T>析构舞台对象。</T>
//============================================================
FRenderFragmentShader::~FRenderFragmentShader(){
}

MO_NAMESPACE_END
