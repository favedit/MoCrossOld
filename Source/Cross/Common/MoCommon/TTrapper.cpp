#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>建立陷阱扑捉对象。</T>
//============================================================
TTrapper::TTrapper(){
   _pThreadTrap = RTrap::Static().Link();
   _pTrap = _pThreadTrap->CurrentTrap();
}

//============================================================
// <T>释放陷阱扑捉对象。</T>
//============================================================
TTrapper::~TTrapper(){
   _pTrap->Check();
   RTrap::Static().Unlink(_pThreadTrap);
}

MO_NAMESPACE_END
