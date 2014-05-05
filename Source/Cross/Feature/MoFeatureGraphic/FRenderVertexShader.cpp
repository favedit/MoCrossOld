#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_ABSTRACT_IMPLEMENT_INHERITS(FRenderVertexShader, FRenderShader);

//============================================================
// <T>构造舞台对象。</T>
//============================================================
FRenderVertexShader::FRenderVertexShader(){
   _shaderCd = ERenderShader_Vertex;
}

//============================================================
// <T>析构舞台对象。</T>
//============================================================
FRenderVertexShader::~FRenderVertexShader(){
}

MO_NAMESPACE_END
