#include "MoFpPhysics.h"

MO_NAMESPACE_BEGIN

//============================================================
TPhysicsJump::TPhysicsJump(){
   //_gravity = 9.80665f;
   _gravity = 9.80665f * 1.5f;
   _time = 0.0f;
   _speed = 0.0f;
   _stop = ETrue;
}

//============================================================
TPhysicsJump::~TPhysicsJump(){
}

//============================================================
TFloat TPhysicsJump::Gravity(){
   return _gravity;
}

//============================================================
void TPhysicsJump::SetGravity(TFloat gravity){
   _gravity = gravity;
}

//============================================================
TFloat TPhysicsJump::Height(TFloat time){
   TFloat tick = time - _time;
   TFloat height = _speed * tick - 0.5f * _gravity * tick * tick;
   if(height < 0.0f){
      height = 0.0f;
      _stop = ETrue;
   }
   return height;
}

//============================================================
void TPhysicsJump::Jump(TFloat time, TFloat speed){
   _stop = EFalse;
   _time = time;
   _speed = speed;
}

MO_NAMESPACE_END
