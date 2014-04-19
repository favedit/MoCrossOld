#ifndef __MO_E2_DISPLAY_H__
#define __MO_E2_DISPLAY_H__
//************************************************************

#ifndef __MO_E2_COMMON_H__
#include "MoE2Common.h"
#endif // __MO_E2_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
class FDisplay2d;
typedef MO_E2_DECLARE FVector<FDisplay2d*> FDisplay2dVector;
typedef MO_E2_DECLARE FList<FDisplay2d*> FDisplay2dList;

//==========================================================
// <T>显示组件类型</T>
//==========================================================
enum EDisplayType{
   // 图片
   EDisplayType_Picture = 1,
   // 形状
   EDisplayType_Shape = 2,
   // 精灵
   EDisplayType_Sprite = 3,
   // 动画
   EDisplayType_Movie = 4,
};
//----------------------------------------------------------
typedef TInt TDisplayType;

//============================================================
struct SDisplayArgs{
};

//============================================================
// <T>显示对象。</T>
//============================================================
class MO_E2_DECLARE FDisplay2d :
      public FRenderable,
      public IParticleAble{
protected:
   // 类型
   TDisplayType _typeCd;
   // 重心
   SFloatPoint3 _gravityCenter;
   // 材质
   FMaterial* _pMaterial;
public:
   FDisplay2d();
   MO_ABSTRACT ~FDisplay2d();
public:
   //------------------------------------------------------------
   // <T>获得类型。</T>
   MO_INLINE TDisplayType TypeCd(){
      return _typeCd;
   }
   //------------------------------------------------------------
   // <T>获得重心。</T>
   MO_INLINE SFloatPoint3& GravityCenter(){
      return _gravityCenter;
   }
   //------------------------------------------------------------
   // <T>获得材质。</T>
   MO_OVERRIDE FMaterial* Material(){
      return _pMaterial;
   }
public:
   MO_OVERRIDE TAny* Convert(EComponent componentCd);
public:
   TResult SetMaterial(FMaterial* pMaterial);
public:
   MO_OVERRIDE TResult CalculateRenderable(SRenderable& renderable);
   MO_OVERRIDE TResult DoParticle(SParticleData& data);
   MO_OVERRIDE TResult Free();
};

//============================================================
// <T>跟踪显示对象。</T>
//============================================================
class MO_E2_DECLARE FTailDisplay2d : public FDisplay2d{
protected:
   // 是否使用跟踪
   TBool _optionTail;
   // 跟踪控制器
   FTailController* _pTailController;
public:
   FTailDisplay2d();
   MO_ABSTRACT ~FTailDisplay2d();
public:
   //------------------------------------------------------------
   // <T>获得配置是否使用跟踪。</T>
   MO_INLINE TBool OptionTail(){
      return _optionTail;
   }
   //------------------------------------------------------------
   // <T>获得跟踪控制器。</T>
   MO_INLINE FTailController* TailController(){
      return _pTailController;
   }
public:
   TResult SetOptionTail(TBool value);
   MO_OVERRIDE TResult CalculateRenderable(SRenderable& renderable);
   MO_OVERRIDE TResult DoParticle(SParticleData& data);
};

//============================================================
// <T>显示容器。</T>
//============================================================
class MO_E2_DECLARE FDisplay2dContainer : public FDisplay2d{
public:
   FDisplay2dContainer();
   MO_ABSTRACT ~FDisplay2dContainer();
};

//============================================================
// <T>位图对象。</T>
//============================================================
class MO_E2_DECLARE FPicture : public FTailDisplay2d{
protected:
   FPictureResource* _pResource;
public:
   FPicture();
   MO_ABSTRACT ~FPicture();
public:
   //------------------------------------------------------------
   // <T>获得位图。</T>
   MO_INLINE FPictureResource* Resource(){
      return _pResource;
   }
public:
   TResult SetResource(FPictureResource* pResource);
};

//============================================================
// <T>位图对象对象池。</T>
//============================================================
class MO_E2_DECLARE FPicturePool : public FDisplayPool{
public:
   //------------------------------------------------------------
   // <T>构造移动粒子对象池。</T>
   FPicturePool(){
      _typeCd = EDisplayType_Picture;
   }
public:
   //------------------------------------------------------------
   // <T>创建一个粒子对象。</T>
   MO_OVERRIDE FDrawable* Create(){
      //return MO_CREATE(FPicture);
      return NULL;
   }
};

//============================================================
// <T>显示形状。</T>
//============================================================
class MO_E2_DECLARE FShape : public FDisplay2d{
public:
   FShape();
   MO_ABSTRACT ~FShape();
};

//============================================================
// <T>显示形状对象池。</T>
//============================================================
class MO_E2_DECLARE FShapePool : public FDisplayPool{
public:
   //------------------------------------------------------------
   // <T>构造移动粒子对象池。</T>
   FShapePool(){
      _typeCd = EDisplayType_Shape;
   }
public:
   //------------------------------------------------------------
   // <T>创建一个粒子对象。</T>
   MO_OVERRIDE FDrawable* Create(){
      //return MO_CREATE(FShape);
      return NULL;
   }
};

//============================================================
// <T>显示精灵。</T>
//============================================================
class MO_E2_DECLARE FSprite : public FDisplay2dContainer{
public:
   FSprite();
   MO_ABSTRACT ~FSprite();
};

//============================================================
// <T>显示精灵对象池。</T>
//============================================================
class MO_E2_DECLARE FSpritePool : public FDisplayPool{
public:
   //------------------------------------------------------------
   // <T>构造移动粒子对象池。</T>
   FSpritePool(){
      _typeCd = EDisplayType_Sprite;
   }
public:
   //------------------------------------------------------------
   // <T>创建一个粒子对象。</T>
   MO_OVERRIDE FDrawable* Create(){
      //return MO_CREATE(FSprite);
      return NULL;
   }
};

//============================================================
// <T>显示动画。</T>
//============================================================
class MO_E2_DECLARE FMovie : public FDisplay2d{
protected:
   FAnimationResource* _pResource;
   TFloat _rate;
   TMovieActionList _actions;
   TDateTime _startTick;
   TDateTime _lastTick;
   FAnimationClip* _pActiveClip;
   FAnimationFrame* _pActiveFrame;
   TBool _hasActiveAction;
   SMovieAction _activeAction;
public:
   FMovie();
   MO_ABSTRACT ~FMovie();
public:
   //------------------------------------------------------------
   // <T>获得位图。</T>
   MO_INLINE FAnimationResource* Resource(){
      return _pResource;
   }
   //------------------------------------------------------------
   // <T>获得激活剪辑。</T>
   MO_INLINE FAnimationClip* ActiveClip(){
      return _pActiveClip;
   }
   //------------------------------------------------------------
   // <T>获得激活帧。</T>
   MO_INLINE FAnimationFrame* ActiveFrame(){
      return _pActiveFrame;
   }
public:
   MO_OVERRIDE TResult CalculateRenderable(SRenderable& renderable);
public:
   void LoadResource(FAnimationResource* pResource);
public:
   MO_ABSTRACT TResult PlayClear();
   MO_ABSTRACT TResult Play(SMovieAction* pAction);
   MO_ABSTRACT TResult ProcessPlay();
   MO_OVERRIDE TResult Process();
};

//============================================================
// <T>位图对象对象池。</T>
//============================================================
class MO_E2_DECLARE FMoviePool : public FDisplayPool{
public:
   //------------------------------------------------------------
   // <T>构造移动粒子对象池。</T>
   FMoviePool(){
      _typeCd = EDisplayType_Movie;
   }
public:
   //------------------------------------------------------------
   // <T>创建一个粒子对象。</T>
   MO_OVERRIDE FDrawable* Create(){
      //return MO_CREATE(FMovie);
      return NULL;
   }
};

//============================================================
// <T>显示对象控制台。</T>
//============================================================
class MO_E2_DECLARE FDisplay2dConsole : public FConsole{
protected:
   // 显示缓冲池集合
   FDisplayPoolCollection* _pPools;
public:
   FDisplay2dConsole();
   MO_ABSTRACT ~FDisplay2dConsole();
public:
   //------------------------------------------------------------
   // <T>获得显示缓冲池集合。</T>
   MO_INLINE FDisplayPoolCollection* Pools(){
      return _pPools;
   }
public:
   FDisplay2d* DisplayAlloc(TDisplayType typeCd);
   void DisplayFree(FDisplay2d* pDisplay);
public:
   MO_ABSTRACT void Setup();
   MO_ABSTRACT FDisplayPool* PoolFind(TDisplayType typeCd);
   MO_ABSTRACT void PoolRegister(FDisplayPool* pPool);
   MO_ABSTRACT void PoolUnregister(FDisplayPool* pPool);
   MO_ABSTRACT TResult Dispose();
};

//============================================================
// <T>顶层容器管理器。</T>
//============================================================
class MO_E2_DECLARE RDisplay2dManager : public RSingleton<FDisplay2dConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_E2_DISPLAY_H__
