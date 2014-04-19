#ifndef __MO_EG_ENTITY_H__
#define __MO_EG_ENTITY_H__
//************************************************************

#ifndef __MO_EG_COMMON_H__
#include "MoEgCommon.h"
#endif // __MO_EG_COMMON_H__

#ifndef __MO_EG_DISPLAY_H__
#include "MoEgDisplay.h"
#endif // __MO_EG_DISPLAY_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>实体类型。</T>
//============================================================
enum EEntityType{
   // 未知
   EEntityType_Unknown,
   // 实体
   EEntityType_Entity,
   // 静态
   EEntityType_Still,
   // 动态
   EEntityType_Sprite,
};
//------------------------------------------------------------
typedef TInt TEntityType;

//============================================================
// <T>实体。</T>
//============================================================
class MO_EG_DECLARE FEntity : public FManagedObject{
protected:
   TEntityType _typeCd;
   FDisplayLayer* _pLayer;
   SFloatSpace _space;
public:
   FEntity();
   MO_ABSTRACT ~FEntity();
public:
   //------------------------------------------------------------
   // <T>获得类型。</T>
   MO_INLINE TEntityType TypeCd(){
      return _typeCd;
   }
   //------------------------------------------------------------
   // <T>设置类型。</T>
   MO_INLINE void SetTypeCd(TEntityType typeCd){
      _typeCd = typeCd;
   }
   //------------------------------------------------------------
   // <T>获得层。</T>
   MO_INLINE FDisplayLayer* Layer(){
      return _pLayer;
   }
   //------------------------------------------------------------
   // <T>设置层。</T>
   MO_INLINE void SetLayer(FDisplayLayer* pLayer){
      _pLayer = pLayer;
   }
   //------------------------------------------------------------
   // <T>获得空间。</T>
   MO_INLINE SFloatSpace& Space(){
      return _space;
   }
   //------------------------------------------------------------
   // <T>获得坐标。</T>
   MO_INLINE SFloatPoint3& Location(){
      return _space.location;
   }
   //------------------------------------------------------------
   // <T>获得尺寸。</T>
   MO_INLINE SFloatSize3& Size(){
      return _space.size;
   }
   //------------------------------------------------------------
   // <T>获得旋转。</T>
   MO_INLINE SFloatVector3& Rotation(){
      return _space.rotation;
   }
public:
   MO_ABSTRACT TResult Setup();
public:
   MO_ABSTRACT TResult MoveTo(TFloat x, TFloat y);
   MO_ABSTRACT TResult FilterDrawable(FDrawableCollection* pDrawables);
public:
   MO_ABSTRACT TResult Update();
   MO_ABSTRACT TResult Process();
   MO_ABSTRACT TResult Dispose();
};
//------------------------------------------------------------
typedef MO_EG_DECLARE FObjects<FEntity*> FEntityCollection;
typedef MO_EG_DECLARE FList<FEntity*> FEntityList;

//============================================================
// <T>静态实体。</T>
//============================================================
class MO_EG_DECLARE FStillEntity : public FEntity{
public:
   FStillEntity();
   MO_ABSTRACT ~FStillEntity();
public:
   MO_OVERRIDE TResult Setup();
public:
   MO_OVERRIDE TResult Process();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
// <T>精灵实体。</T>
//============================================================
class MO_EG_DECLARE FSpriteEntity :
      public FEntity,
      public IParticleAble{
protected:
   SFloatSpace _targetSpace;
   SFloatVector3 _direction;
   TFloat _speed;
   TTimeTick _lastTick;
public:
   FSpriteEntity();
   MO_ABSTRACT ~FSpriteEntity();
public:
   //------------------------------------------------------------
   // <T>获得目标空间。</T>
   MO_INLINE SFloatSpace& TargetSpace(){
      return _targetSpace;
   }
   //------------------------------------------------------------
   // <T>获得目标坐标。</T>
   MO_INLINE SFloatPoint3& TargetLocation(){
      return _targetSpace.location;
   }
   //------------------------------------------------------------
   // <T>获得目标尺寸。</T>
   MO_INLINE SFloatSize3& TargetSize(){
      return _targetSpace.size;
   }
   //------------------------------------------------------------
   // <T>获得目标旋转。</T>
   MO_INLINE SFloatVector3& TargetRotation(){
      return _targetSpace.rotation;
   }
   //------------------------------------------------------------
   // <T>获得速度。</T>
   MO_INLINE TFloat Speed(){
      return _speed;
   }
   //------------------------------------------------------------
   // <T>获得速度。</T>
   MO_INLINE void SetSpeed(TFloat speed){
      _speed = speed;
   }
public:
   MO_OVERRIDE TResult Setup();
public:
   MO_ABSTRACT TResult MoveTarget(TFloat x = 0.0f, TFloat y = 0.0f, TFloat z = 0.0f);
   MO_OVERRIDE TResult DoParticle(SParticleData& data);
public:
   MO_OVERRIDE TResult Update();
   MO_OVERRIDE TResult Process();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
// <T>实体缓冲池。</T>
//============================================================
class MO_EG_DECLARE FEntityPool : public FPool<FEntity*>{
protected:
   TEntityType _typeCd;
   TCharC* _pClassName;
public:
   FEntityPool();
   MO_ABSTRACT ~FEntityPool();
public:
   //------------------------------------------------------------
   // <T>获得类型。</T>
   MO_INLINE TEntityType TypeCd(){
      return _typeCd;
   }
   //------------------------------------------------------------
   // <T>获得类名称。</T>
   MO_INLINE TCharC* ClassName(){
      return _pClassName;
   }
public:
   MO_VIRTUAL FEntity* Create() = 0;
public:
   FEntity* Alloc();
   void Free(FEntity* pControl);
};
//------------------------------------------------------------
typedef MO_EG_DECLARE FObjects<FEntityPool*> FEntityPoolCollection;

//==========================================================
// <T>实体缓冲池。</T>
//============================================================
#define MO_DEF_ENTITY_POOL(D, P, C, T, N) \
   class D P : public FEntityPool{ \
   public: \
      P(){ \
         _typeCd = T; \
         _pClassName = N; \
      } \
   public: \
      FEntity* Create(){ \
         return MO_CREATE(C); \
      } \
   }; \

//============================================================
// <T>实体控制台。</T>
//============================================================
class MO_EG_DECLARE FEntityConsole : public FConsole{
public:
   FEntityConsole();
   MO_ABSTRACT ~FEntityConsole();
public:
   MO_ABSTRACT TResult Setup();
   MO_ABSTRACT TResult Process();
};

//============================================================
// <T>实体管理器。</T>
//============================================================
class MO_EG_DECLARE REntityManager : public RSingleton<FEntityConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EG_ENTITY_H__
