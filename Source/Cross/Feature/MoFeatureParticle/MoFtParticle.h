#ifndef __MO_FT_PARTICLE_H__
#define __MO_FT_PARTICLE_H__
//************************************************************

#ifndef __MO_FT_COMMON_H__
#include "MoFtCommon.h"
#endif // __MO_FT_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
class FParticle;
class FParticleController;

//============================================================
// <T>粒子类型。</T>
//============================================================
enum EParticleType{
   EParticleType_Move = 1,
};
//------------------------------------------------------------
typedef TInt TParticleType;

//============================================================
// <T>粒子命令。</T>
//============================================================
enum EParticleAction{
   EParticleAction_Move = 0,
   EParticleAction_Free = 1,
};

//============================================================
// <T>粒子数据。</T>
//============================================================
struct SParticleData{
   EParticleAction actionCd;
   SFloatPoint3 location;
   SFloatSize3 size;
   SFloatVector3 rotation;
};

//============================================================
// <T>粒子接口。</T>
//============================================================
class MO_FT_DECLARE IParticleAble{
public:
   MO_VIRTUAL TResult DoParticle(SParticleData& SParticleData) = 0;
};
//------------------------------------------------------------
typedef FObjects<IParticleAble*> FParticleCollection;

//============================================================
// <T>粒子对象。</T>
//============================================================
class MO_FT_DECLARE FParticle : public FObject{
protected:
   // 类型
   TParticleType _typeCd;
   // 控制器
   FParticleController* _pController;
   // 渲染对象
   FParticleCollection* _pLinkers;
   // 存活状态
   TBool _statusAlive;
   // 位置
   SFloatPoint2 _location;
   // 方向
   SFloatVector2 _direction;
   // 尺寸
   SFloatSize2 _size;
   // 旋转方向
   TFloat _rotation;
   // 旋转角度
   TFloat _angle;
   // 延时长度
   TFloat _delay;
   // 启动时刻
   TTimeTick _startTick;
   // 当前时刻
   TTimeTick _currentTick;
   // 最后时刻
   TTimeTick _lastTick;
   // 当前时段（单位秒）
   TFloat _currentSpan;
   // 总共时段（单位秒）
   TFloat _totalSpan;
public:
   FParticle();
   MO_ABSTRACT ~FParticle();
public:
   //------------------------------------------------------------
   // <T>获得类型。</T>
   MO_INLINE TParticleType TypeCd(){
      return _typeCd;
   }
   //------------------------------------------------------------
   // <T>获得控制器。</T>
   MO_INLINE FParticleController* Controller(){
      return _pController;
   }
   //------------------------------------------------------------
   // <T>获得关联对象集合。</T>
   MO_INLINE FParticleCollection* Linkers(){
      return _pLinkers;
   }
   //------------------------------------------------------------
   // <T>获得是否还存活。</T>
   MO_INLINE TBool IsStatusAlive(){
      return _statusAlive;
   }
   //------------------------------------------------------------
   // <T>获得位置。</T>
   MO_INLINE SFloatPoint2& Location(){
      return _location;
   }
   //------------------------------------------------------------
   // <T>获得方向。</T>
   MO_INLINE SFloatVector2& Direction(){
      return _direction;
   }
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SFloatSize2& Size(){
      return _size;
   }
   //------------------------------------------------------------
   // <T>获得旋转方向。</T>
   MO_INLINE TFloat Rotation(){
      return _rotation;
   }
   //------------------------------------------------------------
   // <T>设置旋转方向。</T>
   MO_INLINE void SetRotation(TFloat rotation){
      _rotation = rotation;
   }
public:
   MO_ABSTRACT TResult OnSetup();
   MO_ABSTRACT TResult OnProcess();
   MO_ABSTRACT TResult OnFree();
public:
   MO_ABSTRACT TResult Setup();
   MO_ABSTRACT TResult Assign(FParticle* pParticle);
   TResult LinkerPush(IParticleAble* pParticleAble);
   TResult LinkerRemove(IParticleAble* pParticleAble);
   TResult LinkerClear();
   TResult Dispatch(SParticleData& data);
   MO_ABSTRACT TResult Process();
   MO_ABSTRACT TResult Free();
};
//------------------------------------------------------------
typedef FVector<FParticle*> FParticleVector;
typedef FList<FParticle*> FParticleList;

//============================================================
// <T>粒子缓冲池。</T>
//============================================================
class MO_FT_DECLARE FParticlePool : public FPool<FParticle*>{
protected:
   TParticleType _typeCd;
public:
   FParticlePool();
   MO_ABSTRACT ~FParticlePool();
public:
   //------------------------------------------------------------
   // <T>获得类型。</T>
   MO_INLINE TParticleType TypeCd(){
      return _typeCd;
   }
public:
   MO_VIRTUAL FParticle* Create() = 0;
public:
   FParticle* Alloc();
   void Free(FParticle* pParticle);
};
//------------------------------------------------------------
typedef FVector<FParticlePool*> FParticlePoolCollection;

//============================================================
// <T>移动粒子对象。</T>
//============================================================
class MO_FT_DECLARE FParticleMove : public FParticle{
protected:
   // 开始位置
   SFloatPoint2 _startLocation;
   // 结束位置
   SFloatPoint2 _stopLocation;
   // 初始速度
   TFloat _speed;
   // 分裂距离
   TFloat _splitStep;
   // 加速度
   TFloat _acceleration;
   // 迭代层级
   TInt _level;
   // 最后距离
   TFloat _lastDistance;
public:
   FParticleMove();
   MO_ABSTRACT ~FParticleMove();
public:
   //------------------------------------------------------------
   // <T>获得开始位置。</T>
   MO_INLINE SFloatPoint2& StartLocation(){
      return _startLocation;
   }
   //------------------------------------------------------------
   // <T>获得开始位置。</T>
   MO_INLINE SFloatPoint2& StopLocation(){
      return _stopLocation;
   }
   //------------------------------------------------------------
   // <T>获得初始速度。</T>
   MO_INLINE TFloat Speed(){
      return _speed;
   }
   //------------------------------------------------------------
   // <T>设置初始速度。</T>
   MO_INLINE void SetSpeed(TFloat speed){
      _speed = speed;
   }
   //------------------------------------------------------------
   // <T>获得加速度。</T>
   MO_INLINE TFloat Acceleration(){
      return _acceleration;
   }
   //------------------------------------------------------------
   // <T>设置加速度。</T>
   MO_INLINE void SetAcceleration(TFloat acceleration){
      _acceleration = acceleration;
   }
   //------------------------------------------------------------
   // <T>获得迭代层级。</T>
   MO_INLINE TInt Level(){
      return _level;
   }
   //------------------------------------------------------------
   // <T>设置迭代层级。</T>
   MO_INLINE void SetLevel(TInt level){
      _level = level;
   }
public:
   MO_OVERRIDE TResult OnSetup();
   MO_OVERRIDE TResult OnProcess();
};

//============================================================
// <T>移动粒子对象池。</T>
//============================================================
class MO_FT_DECLARE FParticleMovePool : public FParticlePool{
public:
   //------------------------------------------------------------
   // <T>构造移动粒子对象池。</T>
   FParticleMovePool(){
      _typeCd = EParticleType_Move;
   }
public:
   //------------------------------------------------------------
   // <T>创建一个粒子对象。</T>
   MO_OVERRIDE FParticle* Create(){
      return MO_CREATE(FParticleMove);
   }
};

//============================================================
// <T>粒子控制器。</T>
//============================================================
class MO_FT_DECLARE FParticleController : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FParticleController, FInstance);
protected:
   // 最大个数
   TInt _maxCount;
   // 粒子集合
   FParticleVector* _pParticles;
public:
   FParticleController();
   MO_ABSTRACT ~FParticleController();
public:
   //------------------------------------------------------------
   // <T>测试是否达未达到上限。</T>
   MO_INLINE TBool TestNotFull(){
      return _pParticles->Count() < _maxCount;
   }
   //------------------------------------------------------------
   // <T>获得粒子个数。</T>
   MO_INLINE TInt ParticleCount(){
      return _pParticles->Count();
   }
   //------------------------------------------------------------
   // <T>获得粒子集合。</T>
   MO_INLINE FParticleVector* Particles(){
      return _pParticles;
   }
   //------------------------------------------------------------
   // <T>增加一个粒子。</T>
   MO_INLINE void Push(FParticle* pParticle){
      _pParticles->Push(pParticle);
   }
public:
   MO_ABSTRACT TResult Process();
};
//------------------------------------------------------------
typedef FVector<FParticleController*> FParticleControllerCollection;

//============================================================
// <T>粒子控制台。</T>
//============================================================
class MO_FT_DECLARE FParticleConsole : public FConsole{
protected:
   // 粒子缓冲池集合
   FParticlePoolCollection* _pPools;
   // 粒子控制器集合
   FParticleControllerCollection* _pControllers;
public:
   FParticleConsole();
   MO_ABSTRACT ~FParticleConsole();
public:
   //------------------------------------------------------------
   // <T>获得粒子缓冲池集合。</T>
   MO_INLINE FParticlePoolCollection* Pools(){
      return _pPools;
   }
   //------------------------------------------------------------
   // <T>获得控制器集合。</T>
   MO_INLINE FParticleControllerCollection* Controllers(){
      return _pControllers;
   }
public:
   FParticle* ParticleAlloc(TParticleType typeCd);
   void ParticleFree(FParticle* pParticle);
public:
   MO_ABSTRACT void Setup();
   MO_ABSTRACT FParticlePool* PoolFind(TParticleType typeCd);
   MO_ABSTRACT void PoolRegister(FParticlePool* pPool);
   MO_ABSTRACT void PoolUnregister(FParticlePool* pPool);
   MO_ABSTRACT TResult Dispose();
};

//============================================================
// <T>粒子管理器。</T>
//============================================================
class MO_FT_DECLARE RParticleManager : public RSingleton<FParticleConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FT_PARTICLE_H__
