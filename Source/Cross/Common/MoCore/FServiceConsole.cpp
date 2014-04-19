#include "MoCrService.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造服务控制台。</T>
//
// @return 服务控制台
//============================================================
FServiceConsole::FServiceConsole(){
   _pServices = MO_CREATE(FServiceList);
}

//============================================================
// <T>析构服务控制台。</T>
//============================================================
FServiceConsole::~FServiceConsole(){
   MO_DELETE(_pServices);
}

//============================================================
// <T>获得服务列表。</T>
//
// @return 共享模块列表
//============================================================
FServiceList* FServiceConsole::Services(){
   return _pServices;
}

//============================================================
// <T>注册服务。</T>
//
// @param pModule 共享模块
//============================================================
void FServiceConsole::Register(IService* pService){
   _pServices->Push(pService);
}

//============================================================
// <T>注销服务。</T>
//
// @param pModule 共享模块
//============================================================
void FServiceConsole::Unregister(IService* pService){
   _pServices->Remove(pService);
}

MO_NAMESPACE_END
