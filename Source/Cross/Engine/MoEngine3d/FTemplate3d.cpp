#include "MoE3Template.h"
#include "MoE3Instance.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplate3d, FDisplay3d);

//============================================================
// <T>构造实体3D模型。</T>
//============================================================
FTemplate3d::FTemplate3d(){
   MO_CLEAR(_pTemplateResource);
   MO_CLEAR(_pDynamicModel);
   _pAnimation = MO_CREATE(FAnimation3d);
   _pTemplateRenderables = MO_CREATE(FTemplate3dRenderableCollection);
}

//============================================================
// <T>析构实体3D模型。</T>
//============================================================
FTemplate3d::~FTemplate3d(){
   MO_DELETE(_pDynamicModel);
   MO_DELETE(_pAnimation);
   MO_DELETE(_pTemplateRenderables);
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源对象
//============================================================
TResult FTemplate3d::LoadResource(FRs3dTemplate* pResource){
   MO_CHECK(pResource, return ENull);
   // 设置属性
   _pTemplateResource = pResource;
   TBool techniqueMerge = RTechniqueManager::Instance().Capability().optionMerge;
   //............................................................
   // 读取渲染对象集合
   FRs3dTemplateRenderableCollection* pRenderableResources = pResource->Renderables();
   MO_CHECK(pRenderableResources, return EFailure);
   TInt renderableCount = pRenderableResources->Count();
   for(TInt n = 0; n < renderableCount; n++){
      FRs3dTemplateRenderable* pRenderableResource = pRenderableResources->Get(n);
      // 获得材质
      TCharC* pMaterialName = pRenderableResource->MaterialName();
      FMaterial3d* pMaterial = RInstance3dManager::Instance().MaterialConsole()->Load(pMaterialName);
      MO_FATAL_CHECK(pMaterial, return EFailure, "Material is not exists. (material=%s)", pMaterialName);
      // 创建获得渲染对象模板
      FTemplate3dRenderable* pRenderable = FTemplate3dRenderable::InstanceCreate();
      pRenderable->SetMaterialName(pMaterialName);
      pRenderable->SetMaterialReference(pMaterial);
      pRenderable->SetTemplate(this);
      pRenderable->SetAnimation(_pAnimation);
      pRenderable->LoadResource(pRenderableResource);
      pRenderable->BuildFlags();
      pRenderable->UpdateMaterial(NULL);
      // 合并处理
      TBool optionMerge = pRenderableResource->OptionMerge();
      if(techniqueMerge && optionMerge){
         if(_pDynamicModel == NULL){
            _pDynamicModel = FDynamicModel3d::InstanceCreate();
         }
         _pDynamicModel->PushRenderable(pRenderable);
      }else{
         RenderablePush(pRenderable);
      }
   }
   //............................................................
   // 建立合并模型
   if(_pDynamicModel != NULL){
      _pDynamicModel->Merge();
      // 建立渲染对象集合
      FDynamicModel3dGeometryCollection::TIteratorC geometryIterator = _pDynamicModel->Geometrys()->IteratorC();
      while(geometryIterator.Next()){
         FDynamicModel3dGeometry* pGeometry = *geometryIterator;
         GDynamicRenderablePtrs::TIteratorC renderableIterator = pGeometry->DynamicRenderables().IteratorC();
         while(renderableIterator.Next()){
            RenderablePush(*renderableIterator);
         }
      }
   }
   //............................................................
   // 建立骨头集合
   FRs3dModelCollection* pRsModels = pResource->Models();
   MO_CHECK(pRenderableResources, return EFailure);
   TInt modelCount = pRsModels ->Count();
   for(TInt n = 0; n < modelCount; n++){
      FRs3dModel* pRsModel = pRsModels ->Get(n);
      TResourceId modelId = pRsModel->Handle();
      FRs3dSkeleton* pRsSkeleton = pRsModel->Skeleton();
      TSetIteratorC<TBoneId, FRs3dBone*> iterator = pRsSkeleton->Bones()->IteratorC();
      while(iterator.Next()){
         FRs3dBone* pRsBone = *iterator;
         FBone3d* pBone = MO_CREATE(FBone3d);
         pBone->SetAnimation(_pAnimation);
         pBone->SetModelId(modelId);
         pBone->LoadResource(pRsBone);
         _pAnimation->Bones()->Push(pBone);
      }
   }
   //............................................................
   // 为渲染对象分配骨头
   if(!_renderables.IsEmpty()){
      renderableCount = _renderables.Count();
      for(TInt n = 0; n < renderableCount; n++){
         FRenderable* pRenderable = _renderables.Get(n);
         if(pRenderable->GetClass() == FTemplate3dRenderable::Class()){
            FTemplate3dRenderable* pTemplateRenderable = (FTemplate3dRenderable*)pRenderable;
            FBone3dCollection* pBones = pTemplateRenderable->Bones();
            TResourceId modelId = pTemplateRenderable->ModelResource()->Handle();
            TVectorIteratorC<TBoneId> iterator = pTemplateRenderable->GeometryResource()->BoneIds()->IteratorC();
            while(iterator.Next()){
               TBoneId boneId = *iterator;
               FBone3d* pBone = _pAnimation->FindBone(modelId, boneId);
               MO_ERROR_CHECK(pBone, continue, "Animation find bone is not exists. (model_id=%d, bone_id=%d)", modelId, boneId);
               pBones->Push(pBone);
            }
         }
      }
   }
   MO_DEBUG("Load template resource success. (code=%d, model_count=%d, renderable_count=%d)",
         pResource->Code(), modelCount, renderableCount);
   return ESuccess;
}

//============================================================
// <T>更新处理。</T>
//
// @return 处理结果
//============================================================
TResult FTemplate3d::Update(){
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FTemplate3d::ProcessBefore(SProcessContext* pContext){
   return ESuccess;
}

//============================================================
// <T>加载处理。</T>
//
// @return 处理结果
//============================================================
TResult FTemplate3d::LoadProcess(){
   return ESuccess;
}

//============================================================
// <T>异步逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FTemplate3d::AnsyProcess(){
   //............................................................
   // 为渲染对象分配骨头
   //TInt renderableCount = _pRenderables->Count();
   //for(TInt n = 0; n < renderableCount; n++){
   //   FTemplate3dRenderable* pRenderable = (FTemplate3dRenderable*)_pRenderables->Get(n);
   //   //pRenderable->AnsyProcess();
   //}
   // 动画处理
   if(_pAnimation != NULL){
      _pAnimation->Process();
   }
   return ESuccess;
}

MO_NAMESPACE_END
