#include "MoFtParticle.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造粒子对象。</T>
//============================================================
FParticle::FParticle(){
   _typeCd = 0;
   MO_CLEAR(_pController);
   _pLinkers = MO_CREATE(FParticleCollection);
   _statusAlive = EFalse;
   _rotation = 0;
   _angle = 0;
   _delay = 0;
   _startTick = 0;
   _currentTick = 0;
   _lastTick = 0;
   _currentSpan = 0;
   _totalSpan = 0;
}

//============================================================
// <T>析构粒子对象。</T>
//============================================================
FParticle::~FParticle(){
   MO_DELETE(_pLinkers);
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FParticle::OnSetup(){
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FParticle::OnProcess(){
   return ESuccess;
}

//============================================================
// <T>释放处理。</T>
//
// @return 处理结果
//============================================================
TResult FParticle::OnFree(){
   SParticleData data;
   data.actionCd = EParticleAction_Free;
   Dispatch(data);
   _pLinkers->Clear();
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FParticle::Setup(){
   _angle = MO_PI_FLOAT / 180 * _rotation;
   _direction.x = cos(_angle);
   _direction.y = sin(_angle);
   _startTick = RTimeTick::Current();
   _lastTick = 0;
   _currentSpan = 0;
   _totalSpan = 0;
   _statusAlive = true;
   return OnSetup();
}

//============================================================
// <T>接收处理。</T>
//
// @param pParticle 粒子对象
// @return 处理结果
//============================================================
TResult FParticle::Assign(FParticle* pParticle){
   _pController = pParticle->Controller();
   _size = pParticle->Size();
   return ESuccess;
}

//============================================================
// <T>增加一个关联对象。</T>
//
// @param pParticleAble 关联对象
// @return 处理结果
//============================================================
TResult FParticle::LinkerPush(IParticleAble* pParticleAble){
   _pLinkers->Push(pParticleAble);
   return ESuccess;
}

//============================================================
// <T>移除一个关联对象。</T>
//
// @param pParticleAble 关联对象
// @return 处理结果
//============================================================
TResult FParticle::LinkerRemove(IParticleAble* pParticleAble){
   _pLinkers->Remove(pParticleAble);
   return ESuccess;
}

//============================================================
// <T>清空关联对象。</T>
//
// @return 处理结果
//============================================================
TResult FParticle::LinkerClear(){
   _pLinkers->Clear();
   return ESuccess;
}

//============================================================
// <T>分发处理。</T>
//
// @param data 粒子数据
// @return 处理结果
//============================================================
TResult FParticle::Dispatch(SParticleData& data){
   TInt count = _pLinkers->Count();
   for(TInt n = 0; n < count; n++){
      IParticleAble* pParticle = _pLinkers->Get(n);
      pParticle->DoParticle(data);
   }
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FParticle::Process(){
   _currentTick = RTimeTick::Current();
   // 检查时刻
   _totalSpan = _currentTick - _startTick - _delay;
   if(_totalSpan < 0){
      return true;
   }
   _totalSpan /= 1000;
   // 逻辑处理
   if(_lastTick != 0){
      _currentSpan = (TFloat)((_currentTick - _lastTick) / 1000);
   }
   TResult result = OnProcess();
   _lastTick = _currentTick; 
   return result;
}

//============================================================
// <T>释放处理。</T>
//
// @return 处理结果
//============================================================
TResult FParticle::Free(){
   return OnFree();
}

MO_NAMESPACE_END
