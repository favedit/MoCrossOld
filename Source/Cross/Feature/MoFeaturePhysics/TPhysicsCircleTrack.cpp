#include "MoFpPhysics.h"

MO_NAMESPACE_BEGIN

//============================================================
TPhysicsCircleTrack::TPhysicsCircleTrack(){
   _lastAngle = 0.0f;
}

//============================================================
TPhysicsCircleTrack::~TPhysicsCircleTrack(){
}

//============================================================
SFloatPoint2& TPhysicsCircleTrack::Center(){
   return _center;
}

//============================================================
TFloat TPhysicsCircleTrack::Radius(){
   return _radius;
}

//============================================================
void TPhysicsCircleTrack::SetRadius(TFloat gravity){
   _radius = gravity;
}

//============================================================
EHandDirection TPhysicsCircleTrack::HandDirection(){
   return _handDirection;
}

//============================================================
void TPhysicsCircleTrack::SetHandDirection(EHandDirection handDirection){
   _handDirection = handDirection;
}

//============================================================
TFloat TPhysicsCircleTrack::Speed(){
   return _speed;
}

//============================================================
void TPhysicsCircleTrack::SetSpeed(TFloat speed){
   _speed = speed;
}

//============================================================
TBool TPhysicsCircleTrack::Start(TFloat time){
   _startTime = time;
   return ETrue;
}

//============================================================
TBool TPhysicsCircleTrack::Calculate(TFloat time, SFloatPoint2& position, SFloatVector3& direction){
   TFloat deltaTime = time - _startTime;
   TFloat twoPi = 2 * MO_PI_FLOAT;
	TFloat oneCircleTime = twoPi * _radius / _speed;
	TFloat angleDelta = deltaTime / oneCircleTime;
   TFloat angle = 0;
   if(EHandDirection_Right == _handDirection){
      angle = _lastAngle + angleDelta;
   }else if(EHandDirection_Right == _handDirection){
      angle = _lastAngle - angleDelta;
   }
   while(angle > twoPi){
      angle -= twoPi;
   }
   while(angle < 0.0f){
      angle += twoPi;
   }
	position.x = _center.x + _radius * sin(angle);
	position.y = _center.y + _radius * cos(angle);
	/*
	SFloatVector2 path(position, _center);
	direction.x = 1.0f;
	direction.y = -(path.x / path.y);
	direction.Normalize();*/
	direction.x = 0.0f;
	direction.y = angle;
   direction.z = 0.0f;
   _lastAngle = angle;
	return ETrue;
}

MO_NAMESPACE_END
