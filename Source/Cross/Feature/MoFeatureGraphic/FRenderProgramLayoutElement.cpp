#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderProgramLayoutElement, FRenderInstance);

//============================================================
// <T>构造渲染布局元素。</T>
//============================================================
FRenderProgramLayoutElement::FRenderProgramLayoutElement(){
   MO_CLEAR(_pAttribute);
   MO_CLEAR(_pStream);
}

//============================================================
// <T>析构渲染布局元素。</T>
//============================================================
FRenderProgramLayoutElement::~FRenderProgramLayoutElement(){
}

MO_NAMESPACE_END
