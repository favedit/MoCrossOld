#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderLayout, FRenderObject);

//============================================================
// <T>构造渲染布局。</T>
//============================================================
FRenderLayout::FRenderLayout(){
   MO_CLEAR(_pProgram);
}

//============================================================
// <T>析构渲染布局。</T>
//============================================================
FRenderLayout::~FRenderLayout(){
}

//============================================================
// <T>增加一个元素。</T>
//============================================================
TResult FRenderLayout::Push(FRenderLayoutElement* pElement){
   MO_CHECK(pElement, return ENull);
   _elements.Push(pElement);
   return ESuccess;
}

MO_NAMESPACE_END
