#include "MoEgDevice.h"
#include "MoEgEntity.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造精灵实体。</T>
//============================================================
FSpriteEntity::FSpriteEntity(){
   _speed = 0.0f;
   _lastTick = 0;
}

//============================================================
// <T>析构精灵实体。</T>
//============================================================
FSpriteEntity::~FSpriteEntity(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FSpriteEntity::Setup(){
   return ESuccess;
}

//============================================================
// <T>移动到目标处理。</T>
//
// @return 处理结果
//============================================================
TResult FSpriteEntity::MoveTarget(TFloat x, TFloat y, TFloat z){
   _targetSpace.location.Set(x, y, z);
   _direction.Set(_space.location, _targetSpace.location, ETrue);
   return ESuccess;
}

//============================================================
// <T>粒子处理。</T>
//
// @param data 粒子数据
// @return 处理结果
//============================================================
TResult FSpriteEntity::DoParticle(SParticleData& data){
   if(data.actionCd == EParticleAction_Move){
      _space.location.Assign(data.location);
   }
   return ESuccess;
}

//============================================================
// <T>更新处理。</T>
//
// @return 处理结果
//============================================================
TResult FSpriteEntity::Update(){
   TResult result = FEntity::Update();
   return result;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FSpriteEntity::Process(){
   TResult result = FEntity::Process();
   // 计算移动
   FTimerDevice* pTimer = RDeviceManager::Instance().Find<FTimerDevice>();
   TTimeTick currentTick = pTimer->CurrentTick();
   if(_lastTick != 0){
      if(_space.location != _targetSpace.location){
         TFloat distance = _space.location.Distance(_targetSpace.location);
         TFloat spanDistance = RTimeSpan::CalculateFloatSecond(_lastTick, currentTick) * _speed;
         if(spanDistance > distance){
            _space.location.Assign(_targetSpace.location);
         }else{
            SFloatVector3 distanceDirection = _direction.MulToVector3(spanDistance);
            _space.location.Add(distanceDirection.x, distanceDirection.y, distanceDirection.z);
         }
      }
   }
   _lastTick = currentTick;
   return result;
}

//============================================================
// <T>释放处理。</T>
//
// @return 处理结果
//============================================================
TResult FSpriteEntity::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
