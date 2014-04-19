#include "MoCrModule.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造共享模块控制台。</T>
//
// @return 共享模块控制台
//============================================================
FSharedModuleConsole::FSharedModuleConsole(){
   _pModules = MO_CREATE(FSharedModuleList);
}

//============================================================
// <T>析构共享模块控制台。</T>
//============================================================
FSharedModuleConsole::~FSharedModuleConsole(){
   MO_DELETE(_pModules);
}

//============================================================
// <T>获得共享模块列表。</T>
//
// @return 共享模块列表
//============================================================
FSharedModuleList* FSharedModuleConsole::Modules(){
   return _pModules;
}

//============================================================
// <T>注册共享模块。</T>
//
// @param pModule 共享模块
//============================================================
void FSharedModuleConsole::Register(FSharedModule* pModule){
   _pModules->Push(pModule);
}

//============================================================
// <T>注销共享模块。</T>
//
// @param pModule 共享模块
//============================================================
void FSharedModuleConsole::Unregister(FSharedModule* pModule){
   _pModules->Remove(pModule);
}

MO_NAMESPACE_END
