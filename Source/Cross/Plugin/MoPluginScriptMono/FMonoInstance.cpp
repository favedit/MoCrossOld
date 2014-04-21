#include "MoPsmScript.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FMonoInstance, FScriptInstance);

//============================================================
// <T>构造脚本实例。</T>
//============================================================
FMonoInstance::FMonoInstance(){
   MO_CLEAR(_pMonoObject);
}

//============================================================
// <T>析构脚本实例。</T>
//============================================================
FMonoInstance::~FMonoInstance(){
}


MO_NAMESPACE_END
