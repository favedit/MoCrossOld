#include "MoFrContent.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FContentObject, FInstance);

//============================================================
// <T>构造内容。</T>
//============================================================
FContentObject::FContentObject(){
   _version = 1;
}

//============================================================
// <T>析构内容。</T>
//============================================================
FContentObject::~FContentObject(){
}

MO_NAMESPACE_END
