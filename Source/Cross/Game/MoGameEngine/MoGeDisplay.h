#ifndef __MO_GE_DISPLAY_H__
#define __MO_GE_DISPLAY_H__
//************************************************************

#ifndef __MO_GE_COMMON_H__
#include "MoGeCommon.h"
#endif // __MO_GE_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>简单舞台对象。</T>
//============================================================
class MO_GE_DECLARE FGameStage : public FStage
{
   MO_CLASS_DECLARE_INHERITS(FGameStage, FStage);
protected:
   SIntSize2 _size;
protected:
   FDisplayLayer* _pSpriteLayer;
protected:
   FStageFrame* _pGroundFrame;
   FStageFrame* _pSceneFrame;
   FStageFrame* _pFaceFrame;
public:
   FGameStage();
   MO_ABSTRACT ~FGameStage();
public:
   //------------------------------------------------------------
   // <T>获得尺寸。</T>
   MO_INLINE SIntSize2& Size(){
      return _size;
   }
public:
   //------------------------------------------------------------
   // <T>获得精灵层。</T>
   MO_INLINE FDisplayLayer* SpriteLayer(){
      return _pSpriteLayer;
   }
public:
   //------------------------------------------------------------
   // <T>获得背景帧。</T>
   MO_INLINE FStageFrame* GroundFrame(){
      return _pGroundFrame;
   }
   //------------------------------------------------------------
   // <T>获得场景帧。</T>
   MO_INLINE FStageFrame* SceneFrame(){
      return _pSceneFrame;
   }
   //------------------------------------------------------------
   // <T>获得界面帧。</T>
   MO_INLINE FStageFrame* FaceFrame(){
      return _pFaceFrame;
   }
public:
   MO_OVERRIDE TResult Setup();
public:
   MO_OVERRIDE TResult ProcessInput();
};

//============================================================
// <T>游戏场景。</T>
//============================================================
class MO_GE_DECLARE FGameScene : public FScene3d
{
   MO_CLASS_DECLARE_INHERITS(FGameScene, FScene3d);
public:
   FGameScene();
   MO_ABSTRACT ~FGameScene();
public:
   MO_OVERRIDE TResult Setup();
public:
   MO_OVERRIDE TResult ProcessInput();
};
//------------------------------------------------------------
typedef MO_GE_DECLARE FDictionary<FGameScene*> FGameSceneDictionary;

//============================================================
// <T>游戏场景控制台。</T>
//============================================================
class MO_GE_DECLARE FGameSceneConsole : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FScene3dConsole, FConsole);
protected:
   FGameSceneDictionary* _pScenes;
public:
   FGameSceneConsole();
   MO_ABSTRACT ~FGameSceneConsole();
public:
   //------------------------------------------------------------
   // <T>获得模型集合。</T>
   MO_INLINE FGameSceneDictionary* Scenes(){
      return _pScenes;
   }
public:
   FGameScene* Load(TCharC* pName);
   TResult Free(FGameScene* pScene);
public:
   void Clear();
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
// <T>实体3D管理器。</T>
//============================================================
class MO_GE_DECLARE RGameSceneManager : public RSingleton<FGameSceneConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EG_DISPLAY_H__
