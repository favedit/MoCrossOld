#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造焦点测试器。</T>
//============================================================
FFocusTester::FFocusTester(){
   _pDrawables = MO_CREATE(FDrawableCollection);
}

//============================================================
// <T>析构焦点控制台。</T>
//============================================================
FFocusTester::~FFocusTester(){
   MO_DELETE(_pDrawables);
}

MO_NAMESPACE_END
