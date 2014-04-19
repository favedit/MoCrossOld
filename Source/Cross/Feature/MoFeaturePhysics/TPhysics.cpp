#include "MoFpPhysics.h"

MO_NAMESPACE_BEGIN

//============================================================
TPhysics::TPhysics(){
   _stop = ETrue;
   _time = 0.0f;
}

//============================================================
TPhysics::~TPhysics(){
}

//============================================================
TBool TPhysics::IsStop(){
   return _stop;
}

//============================================================
TBool TPhysics::Start(TFloat time){
   _time = time;
   _stop = EFalse;
   return ETrue;
}

//============================================================
TBool TPhysics::Stop(){
   _stop = ETrue;
   return ETrue;
}

MO_NAMESPACE_END
