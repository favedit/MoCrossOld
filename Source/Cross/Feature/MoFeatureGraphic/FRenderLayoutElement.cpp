#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderLayoutElement, FRenderObject);

//============================================================
// <T>构造渲染布局元素。</T>
//============================================================
FRenderLayoutElement::FRenderLayoutElement(){
   MO_CLEAR(_pAttribute);
   MO_CLEAR(_pStream);
}

//============================================================
// <T>析构渲染布局元素。</T>
//============================================================
FRenderLayoutElement::~FRenderLayoutElement(){
}

MO_NAMESPACE_END
