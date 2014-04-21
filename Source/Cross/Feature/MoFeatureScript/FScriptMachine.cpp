#include "MoScript.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScriptMachine, FInstance);

//============================================================
// <T>构造脚本工作器。</T>
//============================================================
FScriptMachine::FScriptMachine(){
   _pClassFactory = MO_CREATE(FClassFactory);
}

//============================================================
// <T>析构脚本工作器。</T>
//============================================================
FScriptMachine::~FScriptMachine(){
   MO_DELETE(_pClassFactory);
}

MO_NAMESPACE_END
