#include "MoCrTimer.h"

MO_NAMESPACE_BEGIN

//============================================================
TFloat RTimerManager::CurrentTime(){
   return _pInstance->CurrentTime();
}

//============================================================
TFloat RTimerManager::LastTime(){
   return _pInstance->LastTime();
}

//============================================================
TFloat RTimerManager::DeltaTime(){
   return _pInstance->DeltaTime();
}

//============================================================
void RTimerManager::Update(){
   _pInstance->Update();
}

//============================================================
void RTimerManager::Reset(){
   _pInstance->Reset();
}

MO_NAMESPACE_END
