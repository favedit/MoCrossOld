#include "MoFtParticle.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造动画段。</T>
//============================================================
FParticleMove::FParticleMove(){
   _typeCd = EParticleType_Move;
   _speed = 0;
   _splitStep = 0;
   _acceleration = 0;
   _level = 0;
   _lastDistance = 0;
}

//============================================================
// <T>析构动画段。</T>
//============================================================
FParticleMove::~FParticleMove(){
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FParticleMove::OnSetup(){
   FParticle::OnSetup();
   _location = _startLocation;
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FParticleMove::OnProcess(){
   // 检查是否为空
   if(_pLinkers->IsEmpty()){
      _statusAlive = false;
      return EFalse;
   }
   //............................................................
   // 计算移动距离 (s*t + a*t^2)
   TFloat distance = _speed * _totalSpan + _acceleration * _totalSpan * _totalSpan;
   // 计算当前坐标
   TFloat x = _startLocation.x + distance * _direction.x;
   TFloat y = _startLocation.y + distance * _direction.y;
   // 分裂处理
   //if((distance - _lastDistance > _splitStep) && (_level < 4) && _pController->TestNotFull()){
   //   for(TInt n = -90; n < 90; n += 30){
   //      FParticleMove* pParticle = MO_CREATE(FParticleMove);
   //      pParticle->Assign(this);
   //      pParticle->SetLevel(_level + 1);
   //      pParticle->SetRotation(_rotation + n);
   //      pParticle->SetSpeed(_speed * 2);
   //      pParticle->StartLocation().Set(x, y);
   //      pParticle->SetAcceleration(_acceleration * 2);
   //      pParticle->Setup();
   //      _pController->Push(pParticle);
   //   }
   //   _lastDistance = distance;
   //   _level++;
   //}
   //............................................................
   // 数据修正
   _location.Set(x, y);
   _rotation += 1.0f;
   _angle = MO_PI_FLOAT / 180.0f * _rotation;
   _direction.x = cos(_angle);
   _direction.y = sin(_angle);
   //............................................................
   // 检测存活
   if(_lastDistance > 1024){
      _statusAlive = false;
   }else if((_location.x < 0) || (_location.y < 0)){
      _statusAlive = false;
   }else{
      //SIntSize2& stageSize = RDeviceManager::Instance().ScreenDevice()->Size();
      //if((_location.x > stageSize.width) || (_location.y > stageSize.height)){
      //   _statusAlive = false;
      //}
   }
   //............................................................
   // 设置数据
   SParticleData data;
   data.actionCd = EParticleAction_Move;
   data.size.Set(_size.width, _size.height);
   data.location.Set(_location.x, _location.y);
   data.rotation.z = _angle;
   Dispatch(data);
   return ESuccess;
}

MO_NAMESPACE_END
