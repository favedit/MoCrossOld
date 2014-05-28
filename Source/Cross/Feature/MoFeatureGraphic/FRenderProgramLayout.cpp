#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderProgramLayout, FRenderInstance);

//============================================================
// <T>构造渲染布局。</T>
//============================================================
FRenderProgramLayout::FRenderProgramLayout(){
   MO_CLEAR(_pProgram);
   MO_CLEAR(_pRenderable);
}

//============================================================
// <T>析构渲染布局。</T>
//============================================================
FRenderProgramLayout::~FRenderProgramLayout(){
}

//============================================================
// <T>增加一个元素。</T>
//============================================================
TResult FRenderProgramLayout::Push(FRenderProgramLayoutElement* pElement){
   MO_CHECK(pElement, return ENull);
   _elements.Push(pElement);
   return ESuccess;
}

MO_NAMESPACE_END
