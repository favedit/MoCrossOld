#ifndef __MO_FP_PHYSICS_H__
#define __MO_FP_PHYSICS_H__

#ifndef __MO_FP_COMMON_H__
#include "MoFpCommon.h"
#endif // __MO_FP_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
enum EHandDirection{
   EHandDirection_Left,
   EHandDirection_Right,
};

//============================================================
// <T>物理对象。</T>
//============================================================
class MO_FP_DECLARE TPhysics : public FObject{
protected:
   TBool _stop;
   TFloat _time;
public:
   TPhysics();
   MO_ABSTRACT ~TPhysics();
public:
   TBool IsStop();
public:
   MO_ABSTRACT TBool Start(TFloat time);
   MO_ABSTRACT TBool Stop();
};

//============================================================
// <T>表单对象。</T>
//============================================================
class MO_FP_DECLARE TPhysicsJump : public TPhysics{
protected:
   TFloat _gravity;
   TFloat _speed;
public:
   TPhysicsJump();
   MO_ABSTRACT ~TPhysicsJump();
public:
   TFloat Gravity();
   void SetGravity(TFloat gravity);
   TFloat Height(TFloat time);
public:
   void Jump(TFloat time, TFloat speed);
};

//============================================================
// <T>正方形轨迹跟踪。</T>
//============================================================
class MO_FP_DECLARE TPhysicsSquareTrack : public TPhysics{
protected:
   SFloatPoint2 _center;
   TFloat _radius;
   TFloat _speed;
   EHandDirection _handDirection;
public:
   TPhysicsSquareTrack();
   MO_ABSTRACT ~TPhysicsSquareTrack();
public:
   SFloatPoint2& Center();
   TFloat Radius();
   void SetRadius(TFloat gravity);
   EHandDirection HandDirection();
   void SetHandDirection(EHandDirection handDirection);
   TFloat Speed();
   void SetSpeed(TFloat speed);
public:
   MO_OVERRIDE TBool Start(TFloat time);
   TBool Calculate(TFloat time, SFloatPoint2& position, SFloatVector2& direction);
};

//============================================================
// <T>圆形轨迹跟踪。</T>
//============================================================
class MO_FP_DECLARE TPhysicsCircleTrack : public TPhysics{
protected:
   SFloatPoint2 _center;
   TFloat _radius;
   TFloat _speed;
   EHandDirection _handDirection;
   TFloat _startTime;
   TFloat _lastAngle;
public:
   TPhysicsCircleTrack();
   MO_ABSTRACT ~TPhysicsCircleTrack();
public:
   SFloatPoint2& Center();
   TFloat Radius();
   void SetRadius(TFloat gravity);
   EHandDirection HandDirection();
   void SetHandDirection(EHandDirection handDirection);
   TFloat Speed();
   void SetSpeed(TFloat speed);
public:
   MO_OVERRIDE TBool Start(TFloat time);
   TBool Calculate(TFloat time, SFloatPoint2& position, SFloatVector3& direction);
};

MO_NAMESPACE_END

#endif // __MO_FP_PHYSICS_H__
