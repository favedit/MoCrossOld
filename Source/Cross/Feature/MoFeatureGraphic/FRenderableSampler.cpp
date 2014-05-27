#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderableSampler, FInstance);

//============================================================
// <T>构造渲染对象取样器。</T>
//============================================================
FRenderableSampler::FRenderableSampler(){
   _slot = -1;
}

//============================================================
// <T>析构渲染对象取样器。</T>
//============================================================
FRenderableSampler::~FRenderableSampler(){
}

MO_NAMESPACE_END
