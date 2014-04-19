#include "MoFpPhysics.h"

MO_NAMESPACE_BEGIN

//============================================================
TPhysicsSquareTrack::TPhysicsSquareTrack(){
}

//============================================================
TPhysicsSquareTrack::~TPhysicsSquareTrack(){
}

//============================================================
SFloatPoint2& TPhysicsSquareTrack::Center(){
   return _center;
}

//============================================================
TFloat TPhysicsSquareTrack::Radius(){
   return _radius;
}

//============================================================
void TPhysicsSquareTrack::SetRadius(TFloat gravity){
   _radius = gravity;
}

//============================================================
EHandDirection TPhysicsSquareTrack::HandDirection(){
   return _handDirection;
}

//============================================================
void TPhysicsSquareTrack::SetHandDirection(EHandDirection handDirection){
   _handDirection = handDirection;
}

//============================================================
TFloat TPhysicsSquareTrack::Speed(){
   return _speed;
}

//============================================================
void TPhysicsSquareTrack::SetSpeed(TFloat speed){
   _speed = speed;
}

//============================================================
TBool TPhysicsSquareTrack::Start(TFloat time){
   _time = time;
   _stop = EFalse;
   return ETrue;
}

//============================================================
TBool TPhysicsSquareTrack::Calculate(TFloat time, SFloatPoint2& position, SFloatVector2& direction){
   TFloat side = _radius * 2;
   TFloat length = _speed * (time - _time);
   TInt temp = (TInt)(length / side);
   TInt result = temp % 4;
   TFloat distance = length - side * temp;
   TFloat x, y;
   if(EHandDirection_Right == _handDirection){
      switch (result){
         case 0 :{
            x = _center.x - side * 0.5f + distance;
            y = _center.y - side * 0.5f;
            position.Set(x, y);
            direction.x = 1;
            direction.y = 0;
            break;
         }
         case 1 :{
            x = _center.x + side * 0.5f;
            y = _center.y - side * 0.5f + distance;
            position.Set(x, y);
            direction.x = 0;
            direction.y = 1;
            break;
         }
         case 2 :{
            x = _center.x + side * 0.5f - distance;
            y = _center.y + side * 0.5f;
            position.Set(x, y);
            direction.x = -1;
            direction.y = 0;
            break;
         }
         case 3 :{
            x = _center.x - side * 0.5f;
            y = _center.y + side * 0.5f - distance;
            position.Set(x, y);
            direction.x = 0;
            direction.y = -1;
            break;
         }
         default:
            break;
      }
   }else if(EHandDirection_Left == _handDirection){
      switch (result){
         case 0 :{
            x = _center.x - side * 0.5f;
            y = _center.y - side * 0.5f + distance;
            position.Set(x, y);
            direction.x = 0;
            direction.y = 1;
            break;
         }
         case 1 :{
            x = _center.x - side * 0.5f + distance;
            y = _center.y + side * 0.5f;
            position.Set(x, y);
            direction.x = 1;
            direction.y = 0;
            break;
         }
         case 2 :{
            x = _center.x + side * 0.5f;
            y = _center.y + side * 0.5f - distance;
            position.Set(x, y);
            direction.x = 0;
            direction.y = -1;
            break;
         }
         case 3 :{
            x = _center.x + side * 0.5f - distance;
            y = _center.y - side * 0.5f;
            position.Set(x, y);
            direction.x = -1;
            direction.y = 0;
            break;
         }
         default:
            break;
      }
   }
   return ETrue;
}

MO_NAMESPACE_END
