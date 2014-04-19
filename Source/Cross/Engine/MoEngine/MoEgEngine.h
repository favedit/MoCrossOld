#ifndef __MO_EG_ENGINE_H__
#define __MO_EG_ENGINE_H__
//************************************************************

#ifndef __MO_EG_PLATFORM_H__
#include "MoEgPlatform.h"
#endif // __MO_EG_PLATFORM_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>引擎统计器。</T>
//============================================================
class MO_EG_DECLARE FEngineStatistics : public FStatistics
{
   MO_CLASS_DECLARE_INHERITS(FEngineStatistics, FStatistics);
protected:
   GPtr<FStatistics> _frameStatistics;
   GPtr<FStatistics> _frameProcessBeforeStatistics;
   GPtr<FStatistics> _frameDrawStatistics;
   GPtr<FStatistics> _frameProcessAfterStatistics;
public:
   FEngineStatistics();
   MO_ABSTRACT ~FEngineStatistics();
public:
   //------------------------------------------------------------
   // <T>获得帧统计。</T>
   MO_INLINE FStatistics* FrameStatistics(){
      return _frameStatistics;
   }
   //------------------------------------------------------------
   // <T>获得帧开始处理统计。</T>
   MO_INLINE FStatistics* FrameProcessBeforeStatistics(){
      return _frameProcessBeforeStatistics;
   }
   //------------------------------------------------------------
   // <T>获得帧统计。</T>
   MO_INLINE FStatistics* FrameDrawStatistics(){
      return _frameDrawStatistics;
   }
   //------------------------------------------------------------
   // <T>获得帧结束处理统计。</T>
   MO_INLINE FStatistics* FrameProcessAfterStatistics(){
      return _frameProcessAfterStatistics;
   }
public:
   TResult Setup();
};

//============================================================
// <T>引擎控制台。</T>
//============================================================
class MO_EG_DECLARE FEngineConsole : public FConsole{
protected:
   GPtr<FStageEffect> _stageEffect;
   GPtr<FRenderRectangle> _renderRectangle;
   FRenderRegion* _pRegion;
   // 帧进入监听集合
   TFrameListeners _listenersFrameEnter;
   // 帧逻辑监听集合
   TFrameListeners _listenersFrameLogic;
   // 帧离开监听集合
   TFrameListeners _listenersFrameLeave;
   // 统计信息
   GPtr<FEngineStatistics> _statistics;
public:
   FEngineConsole();
   MO_ABSTRACT ~FEngineConsole();
public:
   //------------------------------------------------------------
   // <T>获得帧进入监听集合。</T>
   MO_INLINE TFrameListeners& ListenersFrameEnter(){
      return _listenersFrameEnter;
   }
   //------------------------------------------------------------
   // <T>获得帧逻辑监听集合。</T>
   MO_INLINE TFrameListeners& ListenersFrameLogic(){
      return _listenersFrameLogic;
   }
   //------------------------------------------------------------
   // <T>获得帧进入监听集合。</T>
   MO_INLINE TFrameListeners& ListenersFrameLeave(){
      return _listenersFrameLeave;
   }
   //------------------------------------------------------------
   // <T>获得统计信息。</T>
   MO_INLINE FEngineStatistics* Statistics(){
      return _statistics;
   }
public:
   MO_ABSTRACT void Setup();
public:
   MO_ABSTRACT TResult OnResize(SResizeEvent* pEvent);
public:
   MO_ABSTRACT TResult ProcessLayer(FDisplayLayer* pLayer);
   MO_ABSTRACT TResult ProcessFrame(FStageFrame* pFrame);
   MO_ABSTRACT TResult Process();
};

//============================================================
// <T>引擎管理器。</T>
//============================================================
class MO_EG_DECLARE REngineManager : public RSingleton<FEngineConsole>{
};

MO_NAMESPACE_END
      
//************************************************************
#endif // __MO_EG_ENGINE_H__
