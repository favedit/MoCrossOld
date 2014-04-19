#include "MoCmSingleton.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造唯一对象控制台。</T>
//
// @return 控制台
//============================================================
FSingletonConsole::FSingletonConsole(){
   _pSingletons = MO_PTR_CREATE(FSingletonList);
}

//============================================================
// <T>析构唯一对象控制台。</T>
//============================================================
FSingletonConsole::~FSingletonConsole(){
   MO_PTR_DELETE(_pSingletons);
}

//============================================================
// <T>获得唯一对象列表。</T>
//
// @return 唯一对象列表
//============================================================
FSingletonList* FSingletonConsole::Singletons(){
   return _pSingletons;
}

//============================================================
// <T>注册唯一对象。</T>
//
// @param pSingleton 唯一对象列表
//============================================================
void FSingletonConsole::Register(ISingleton* pSingleton){
   _pSingletons->Push(pSingleton);
}

//============================================================
// <T>注销唯一对象列表。</T>
//
// @param pSingleton 唯一对象列表
//============================================================
void FSingletonConsole::Unregister(ISingleton* pSingleton){
   _pSingletons->Remove(pSingleton);
}

MO_NAMESPACE_END
