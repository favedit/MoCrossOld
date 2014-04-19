#ifndef __MO_FG_VISUAL_H__
#define __MO_FG_VISUAL_H__
//************************************************************

#ifndef __MO_FG_COMMON_H__
#include "MoFgCommon.h"
#endif // __MO_FG_COMMON_H__

#ifndef __MO_FG_MATERIAL_H__
#include "MoFgMaterial.h"
#endif // __MO_FG_MATERIAL_H__

#ifndef __MO_FG_BASE_H__
#include "MoFgBase.h"
#endif // __MO_FG_BASE_H__

MO_NAMESPACE_BEGIN

//============================================================
class FVisualConsole;

//============================================================
// <T>可见节点。</T>
//============================================================
class MO_FG_DECLARE FVisualNode : public FRenderableInfo
{
   MO_CLASS_DECLARE_INHERITS(FVisualNode, FRenderableInfo);
public:
   FVisualNode();
   MO_ABSTRACT ~FVisualNode();
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FVector<FVisualNode*> FVisualCollection;

//============================================================
// <T>可见材质。</T>
//============================================================
class MO_FG_DECLARE FVisualMaterial : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FVisualMaterial, FInstance);
protected:
   FMaterial* _pMaterial;
   FVisualCollection* _pVisuals;
public:
   FVisualMaterial();
   MO_ABSTRACT ~FVisualMaterial();
public:
   //------------------------------------------------------------
   // <T>获得材质。</T>
   MO_INLINE FMaterial* Material(){
      return _pMaterial;
   }
   //------------------------------------------------------------
   // <T>设置材质。</T>
   MO_INLINE void SetMaterial(FMaterial* pMaterial){
      _pMaterial = pMaterial;
   }
   //------------------------------------------------------------
   // <T>获得可见集合。</T>
   MO_INLINE FVisualCollection* Visuals(){
      return _pVisuals;
   }
public:
   TResult Push(FVisualNode* pVisual);
   TResult Process();
   TResult Reset();
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FVisualMaterial*> FVisualMaterialCollection;

//============================================================
// <T>可见视角。</T>
//============================================================
class MO_FG_DECLARE FVisualView : public FInstance{
protected:
   FVisualCollection* _pVisuals;
public:
   FVisualView();
   MO_ABSTRACT ~FVisualView();
public:
   //------------------------------------------------------------
   // <T>获得可见集合。</T>
   MO_INLINE FVisualCollection* Visuals(){
      return _pVisuals;
   }
public:
   TResult Push(FVisualNode* pVisual);
};

//============================================================
// <T>可见区域。</T>
//============================================================
class MO_FG_DECLARE FVisualRegion : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FVisualRegion, FInstance);
protected:
   TThreadLocker _inputLocker;
   FVisualCollection* _pInputVisuals;
   FVisualCollection* _pVisuals;
   TThreadLocker _outputLocker;
   FVisualCollection* _pOutputVisuals;
   FVisualMaterial* _pAlphaMaterial;
   FVisualMaterialCollection* _pMaterials;
public:
   FVisualRegion();
   MO_ABSTRACT ~FVisualRegion();
public:
   //------------------------------------------------------------
   // <T>获得输入可见集合。</T>
   MO_INLINE FVisualCollection* InputVisuals(){
      return _pInputVisuals;
   }
   //------------------------------------------------------------
   // <T>获得输出可见集合。</T>
   MO_INLINE FVisualCollection* OutputVisuals(){
      return _pOutputVisuals;
   }
public:
   FVisualMaterial* FindMaterial(FMaterial* pMaterial);
public:
   TResult FetchVisuals(FRenderableCollection* pRenderables);
   TResult PushVisuals(FRenderableCollection* pRenderables);
public:
   MO_ABSTRACT TResult Process();
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FVisualRegion*> FVisualRegionCollection;

//============================================================
// <T>可见线程。</T>
//============================================================
class MO_FG_DECLARE FVisualThread : public FThread{
protected:
   TInt _interval;
   FVisualConsole* _pConsole;
public:
   FVisualThread();
   MO_ABSTRACT ~FVisualThread();
public:
   //------------------------------------------------------------
   // <T>获得可见控制台。</T>
   MO_INLINE FVisualConsole* Console(){
      return _pConsole;
   }
   //------------------------------------------------------------
   // <T>设置可见控制台。</T>
   MO_INLINE void SetConsole(FVisualConsole* pConsole){
      _pConsole = pConsole;
   }
public:
   MO_OVERRIDE TResult Process();
};

//============================================================
// <T>可见控制台。</T>
//============================================================
class MO_FG_DECLARE FVisualConsole : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FVisualConsole, FConsole);
protected:
   FVisualThread* _pThread;
   TThreadLocker _regionLocker;
   FVisualRegionCollection* _pRegions;
public:
   FVisualConsole();
   MO_ABSTRACT ~FVisualConsole();
public:
   TResult RegionRegister(FVisualRegion* pRegion);
   TResult RegionUnregister(FVisualRegion* pRegion);
public:
   MO_ABSTRACT TResult Startup();
   MO_ABSTRACT TResult Process();
   MO_ABSTRACT TResult Shutdown();
};

//============================================================
// <T>可见管理器。</T>
//============================================================
class MO_FG_DECLARE RVisualManager : public RSingleton<FVisualConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FG_VISUAL_H__
