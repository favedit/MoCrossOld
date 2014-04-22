#ifndef __MO_E3_TEMPLATE_H__
#define __MO_E3_TEMPLATE_H__
//************************************************************

#ifndef __MO_E3_COMMON_H__
#include "MoE3Common.h"
#endif // __MO_E3_COMMON_H__

#ifndef __MO_E3_DISPLAY_H__
#include "MoE3Display.h"
#endif // __MO_E3_DISPLAY_H__

#ifndef __MO_E3_MODEL_H__
#include "MoE3Model.h"
#endif // __MO_E3_MODEL_H__

MO_NAMESPACE_BEGIN

//============================================================
class FModel3d;
class FModel3dGeometry;
class FTemplate3d;

//============================================================
// <T>实体3D几何体。</T>
//============================================================
class MO_E3_DECLARE FTemplate3dRenderable : public FModel3dRenderable
{
   MO_CLASS_DECLARE_INHERITS(FTemplate3dRenderable, FModel3dRenderable);
protected:
   TThreadLocker _locker;
   FTemplate3d* _pTemplate;
   SMaterialInfo _materialInfo;
   FModel3d* _pModel;
   FModel3dGeometry* _pGeometry;
   FAnimation3d* _pAnimation;
   FBone3dCollection* _pBones;
   SFloatMatrix3d _modelModel;
   SFloatMatrix3d _frameMatrix;
public:
   FTemplate3dRenderable();
   MO_ABSTRACT ~FTemplate3dRenderable();
public:
   //------------------------------------------------------------
   // <T>获得模板对象。</T>
   MO_INLINE FTemplate3d* Template(){
      return _pTemplate;
   }
   //------------------------------------------------------------
   // <T>设置模板对象。</T>
   MO_INLINE void SetTemplate(FTemplate3d* pTemplate){
      _pTemplate = pTemplate;
   }
   //------------------------------------------------------------
   // <T>获得模型。</T>
   MO_INLINE FModel3d* Model(){
      return _pModel;
   }
   //------------------------------------------------------------
   // <T>获得模型资源。</T>
   MO_INLINE FRs3dModel* ModelResource(){
      return (_pModel != NULL) ? _pModel->Resource() : NULL;
   }
   //------------------------------------------------------------
   // <T>获得几何体。</T>
   MO_INLINE FModel3dGeometry* Geometry(){
      return _pGeometry;
   }
   //------------------------------------------------------------
   // <T>获得几何体资源。</T>
   MO_INLINE FRs3dGeometry* GeometryResource(){
      return (_pGeometry != NULL) ? _pGeometry->Resource() : NULL;
   }
   ////------------------------------------------------------------
   //// <T>获得材质资源。</T>
   //MO_INLINE FRs3dMaterial* MaterialResource(){
   //   return _pMaterialResource;
   //}
   ////------------------------------------------------------------
   //// <T>获得材质。</T>
   //MO_INLINE FMaterial3d* Material3d(){
   //   return _pMaterial3d;
   //}
   //------------------------------------------------------------
   // <T>获得动画。</T>
   MO_INLINE FAnimation3d* Animation(){
      return _pAnimation;
   }
   //------------------------------------------------------------
   // <T>设置动画。</T>
   MO_INLINE void SetAnimation(FAnimation3d* pAnimation){
      _pAnimation = pAnimation;
   }
   //------------------------------------------------------------
   // <T>获得骨头集合。</T>
   MO_INLINE FBone3dCollection* Bones(){
      return _pBones;
   }
public:
   MO_OVERRIDE TResult CalculateModelMatrix(SFloatMatrix3d& matrix);
   MO_OVERRIDE TInt CalculateBoneMatrix(SFloatMatrix3d* pMatrix, TInt offset, TInt capacity);
   TResult LoadResource(FRs3dTemplateRenderable* pResource);
   MO_OVERRIDE TResult BuildDescriptor();
   MO_OVERRIDE TResult UpdateMaterial(FMaterial* pLightMaterial);
   TResult AnsyProcess();
};
//------------------------------------------------------------
typedef MO_E3_DECLARE FVector<FTemplate3dRenderable*> FTemplate3dRenderableCollection;

//============================================================
// <T>实体3D模型。</T>
//============================================================
class MO_E3_DECLARE FTemplate3d :
      public FDisplay3d,
      public IProcessor
{
   MO_CLASS_DECLARE_INHERITS(FTemplate3d, FDisplay3d);
protected:
   FRs3dTemplate* _pTemplateResource;
   FAnimation3d* _pAnimation;
   FDynamicModel3d* _pDynamicModel;
   FTemplate3dRenderableCollection* _pTemplateRenderables;
public:
   FTemplate3d();
   MO_ABSTRACT ~FTemplate3d();
public:
   //------------------------------------------------------------
   // <T>获得模板资源。</T>
   MO_INLINE FRs3dTemplate* TemplateResource(){
      return _pTemplateResource;
   }
   //------------------------------------------------------------
   // <T>获得动画。</T>
   MO_INLINE FAnimation3d* Animation(){
      return _pAnimation;
   }
public:
   MO_ABSTRACT TResult LoadResource(FRs3dTemplate* pResource);
public:
   MO_OVERRIDE TResult Update();
   MO_OVERRIDE TResult ProcessBefore(SProcessContext* pContext);
   MO_OVERRIDE TResult LoadProcess();
   MO_OVERRIDE TResult AnsyProcess();
};
//------------------------------------------------------------
typedef MO_E3_DECLARE FLooper<FTemplate3d*> FTemplate3dLooper;
typedef MO_E3_DECLARE FPool<FTemplate3d*> FTemplate3dPool;
typedef MO_E3_DECLARE FDictionary<FTemplate3dPool*> FTemplate3dPoolDictionary;

//============================================================
// <T>实体3D模型管理器。</T>
//============================================================
class MO_E3_DECLARE FTemplate3dConsole :
      public FConsole,
      public IMonitorTrigger{
protected:
   TInt _processLimit;
   GMonitorTriggerPtr _trigger;
   FTemplate3dLooper* _pLooper;
   FTemplate3dPoolDictionary* _pPools;
public:
   FTemplate3dConsole();
   MO_ABSTRACT ~FTemplate3dConsole();
public:
   //------------------------------------------------------------
   // <T>获得模型集合。</T>
   MO_INLINE FTemplate3dPoolDictionary* Pools(){
      return _pPools;
   }
public:
   FTemplate3d* Create(TCharC* pName);
   FTemplate3d* Alloc(TCharC* pName);
   TResult Load(FTemplate3d* pTemplate, TCharC* pName);
   TResult Free(FTemplate3d* pTemplate);
public:
   MO_OVERRIDE TResult TriggerRefresh(TTimeTick currentTick);
public:
   MO_OVERRIDE TResult Startup();
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Shutdown();
   MO_OVERRIDE TResult Clear();
   MO_OVERRIDE TResult Dispose();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_E3_TEMPLATE_H__
