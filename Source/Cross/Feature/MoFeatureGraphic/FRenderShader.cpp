#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_ABSTRACT_IMPLEMENT_INHERITS(FRenderShader, FRenderInstance);

//============================================================
// <T>ππ‘Ï‰÷»æ∆˜°£</T>
//============================================================
FRenderShader::FRenderShader(){
   _pSource = MO_CREATE(FRenderSource);
}

//============================================================
// <T>Œˆππ‰÷»æ∆˜°£</T>
//============================================================
FRenderShader::~FRenderShader(){
   MO_DELETE(_pSource);
}

MO_NAMESPACE_END
