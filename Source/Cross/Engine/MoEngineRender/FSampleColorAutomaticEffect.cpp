#include "MoErSimplePipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FSampleColorAutomaticEffect, FColorAutomaticEffect);

//============================================================
// <T>构造渲染效果。</T>
//============================================================
FSampleColorAutomaticEffect::FSampleColorAutomaticEffect(){
   _descriptor.supportInstance = ETrue;
}

//============================================================
// <T>析构渲染效果。</T>
//============================================================
FSampleColorAutomaticEffect::~FSampleColorAutomaticEffect(){
}

MO_NAMESPACE_END
