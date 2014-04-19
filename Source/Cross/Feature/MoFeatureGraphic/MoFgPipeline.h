#ifndef __MO_FG_PIPELINE_H__
#define __MO_FG_PIPELINE_H__
//************************************************************

#ifndef __MO_FG_COMMON_H__
#include "MoFgCommon.h"
#endif // __MO_FG_COMMON_H__

#ifndef __MO_FG_VISUAL_H__
#include "MoFgVisual.h"
#endif // __MO_FG_VISUAL_H__

MO_NAMESPACE_BEGIN

//============================================================
class FRenderRegion;
class FPipelinePass;
class FPipeline;
class FPipelineConsole;

//============================================================
// <T>渲染管道过程。</T>
//============================================================
class MO_FG_DECLARE FPipelinePass : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FPipelinePass, FInstance);
protected:
   TString _name;
   FPipeline* _pPipeline;
   TTimeTick _lastSortTick;
   GPtr<FStatistics> _renderableActiveStatistics;
   GPtr<FStatistics> _renderableSortStatistics;
   GPtr<FStatistics> _renderableDrawStatistics;
public:
   FPipelinePass();
   MO_ABSTRACT ~FPipelinePass();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>获得渲染管道。</T>
   MO_INLINE FPipeline* Pipeline(){
      return _pPipeline;
   }
   //------------------------------------------------------------
   // <T>设置渲染管道。</T>
   MO_INLINE void SetPipeline(FPipeline* pPipeline){
      _pPipeline = pPipeline;
   }
public:
   MO_ABSTRACT TResult Setup();
public:
   MO_ABSTRACT TResult Resize(TInt width, TInt height);
   MO_ABSTRACT TResult DrawBegin(FRenderRegion* pRegion);
   MO_ABSTRACT TResult DrawRegion(FRenderRegion* pRegion);
   MO_ABSTRACT TResult DrawEnd(FRenderRegion* pRegion);
};
//------------------------------------------------------------
typedef FObjects<FPipelinePass*> FPipelinePassCollection;

//============================================================
// <T>渲染管道。</T>
//============================================================
class MO_FG_DECLARE FPipeline : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FPipeline, FInstance);
protected:
   TString _name;
   TBool _setuped;
   FPipelinePassCollection* _pPasses;
public:
   FPipeline();
   MO_ABSTRACT ~FPipeline();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>获得渲染过程集合。</T>
   MO_INLINE FPipelinePassCollection* Passes(){
      return _pPasses;
   }
public:
   MO_ABSTRACT TResult OnSetup();
   TResult Setup();
public:
   MO_ABSTRACT TResult Resize(TInt width, TInt height);
   MO_ABSTRACT TResult DrawBegin(FRenderRegion* pRegion);
   MO_ABSTRACT TResult DrawRegion(FRenderRegion* pRegion);
   MO_ABSTRACT TResult DrawEnd(FRenderRegion* pRegion);
};
//------------------------------------------------------------
typedef FObjects<FPipeline*> FPipelineCollection;
typedef FDictionary<FPipeline*> FPipelineDictionary;

//============================================================
// <T>渲染管道控制台。</T>
//============================================================
class MO_FG_DECLARE FPipelineConsole : public FConsole{
protected:
   FClassInstanceFactory* _pFactory;
   FPipeline* _pActivePipeline;
public:
   FPipelineConsole();
   MO_ABSTRACT ~FPipelineConsole();
public:
   //------------------------------------------------------------
   // <T>获得实例工厂。</T>
   MO_INLINE FClassInstanceFactory* Factory(){
      return _pFactory;
   }
   //------------------------------------------------------------
   // <T>获得激活管道。</T>
   MO_INLINE FPipeline* ActivePipeline(){
      return _pActivePipeline;
   }
public:
   MO_ABSTRACT FPipeline* SelectPipeline(TCharC* pName);
};

//============================================================
// <T>渲染管道管理器。</T>
//============================================================
class MO_FG_DECLARE RPipelineManager : public RSingleton<FPipelineConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FG_PIPELINE_H__
