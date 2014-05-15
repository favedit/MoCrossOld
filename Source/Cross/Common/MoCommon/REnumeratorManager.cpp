#include "MoCmEnumerator.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>根据名称查找一个枚举器。</T>
//
// @param pName 名称
// @return 枚举器
//============================================================
FEnumerator* REnumeratorManager::Find(TCharC* pName){
   return _pInstance->Find(pName);
}

MO_NAMESPACE_END
