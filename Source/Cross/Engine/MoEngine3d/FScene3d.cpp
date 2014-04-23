#include "MoE3Scene.h"
#include "MoE3Instance.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScene3d, FStage);

//============================================================
// <T>构造实体3D模型。</T>
//============================================================
FScene3d::FScene3d(){
}

//============================================================
// <T>析构实体3D模型。</T>
//============================================================
FScene3d::~FScene3d(){
}

//============================================================
// <T>加载技术资源。</T>
//
// @param pResource 资源对象
// @return 处理结果
//============================================================
TResult FScene3d::LoadTechniqueResource(FRs3dSceneTechnique* pResource){
   MO_CHECK(pResource, return ENull);
   return ESuccess;
}

//============================================================
// <T>加载区域资源。</T>
//
// @param pResource 资源对象
// @return 处理结果
//============================================================
TResult FScene3d::LoadRegionResource(FRs3dSceneRegion* pResource){
   MO_CHECK(pResource, return ENull);
   // 设置颜色
   _sceneFrame->BackgroundColor().Assign(pResource->Color());
   FScreenDevice* pScreenDevice = RDeviceManager::Instance().Find<FScreenDevice>();
   SIntSize2& screenSize = pScreenDevice->Size();
   //............................................................
   FRs3dSceneCamera* pCameraResource = pResource->Camera();
   FRs3dSceneViewport* pViewportResource = pCameraResource->Viewport();
   // 加载投影
   FPerspectiveProjection* pProjection = FPerspectiveProjection::InstanceCreate();
   pProjection->Size().Set(screenSize.width, screenSize.height);
   pProjection->SetZ(pViewportResource->Near(), pViewportResource->Far());
   pProjection->SetAngle(pViewportResource->Angle());
   pProjection->Update();
   // 加载相机
   FCamera* pCamera = FPerspectiveCamera::InstanceCreate();
   pCamera->Position().Assign(pCameraResource->Position());
   pCamera->Direction().Assign(pCameraResource->Direction());
   pCamera->SetProjection(pProjection);
   pCamera->Update();
   // 设置视角
   FViewport* pViewport = FViewport::InstanceCreate();
   pViewport->Set(0, 0, screenSize.width, screenSize.height);
   // 设置视角
   FRenderView* pView = FRenderView::InstanceCreate();
   pView->SetCamera(pCamera);
   pView->SetViewport(pViewport);
   _activeView = pView;
   _pViews->Push(pView);
   //............................................................
   FRs3dSceneLight* pLightResource = pResource->Light();
   FRs3dSceneCamera* pLightCameraResource = pLightResource->Camera();
   FRs3dSceneViewport* pLightViewportResource = pLightCameraResource->Viewport();
   // 设置光源投影
   FPerspectiveProjection* pLightProjection = FPerspectiveProjection::InstanceCreate();
   pLightProjection->Size().Set(1024, 1024);
   pLightProjection->SetZ(pLightViewportResource->Near(), pLightViewportResource->Far());
   pLightProjection->SetAngle(pLightViewportResource->Angle());
   pLightProjection->Update();
   // 设置光源相机
   FCamera* pLightCamera = FPerspectiveCamera::InstanceCreate();
   pLightCamera->Position().Assign(pLightCameraResource->Position());
   pLightCamera->Direction().Assign(pLightCameraResource->Direction());
   pLightCamera->SetProjection(pLightProjection);
   pLightCamera->Update();
   // 设置光源视角
   FViewport* pLightViewport = FViewport::InstanceCreate();
   pLightViewport->Set(0, 0, 1024, 1024);
   // 设置材质
   FRs3dSceneMaterial* pLightMaterialResource = pLightResource->Material();
   GPtr<FScene3dMaterial> lightMaterial = FScene3dMaterial::InstanceCreate();
   lightMaterial->LoadSceneResource(pLightMaterialResource);
   // 设置光源
   FDirectionalLight* pLight = FDirectionalLight::InstanceCreate();
   pLight->SetCamera(pLightCamera);
   pLight->SetViewport(pLightViewport);
   pLight->Direction().Assign(pLightCamera->Direction());
   pLight->SetMaterial(lightMaterial);
   SetDirectionalLight(pLight);
   return ESuccess;
}

//============================================================
// <T>加载显示资源。</T>
//
// @param pResource 资源对象
// @return 处理结果
//============================================================
TResult FScene3d::LoadDisplayResource(FDisplayLayer* pLayer, FRs3dSceneDisplay* pResource){
   MO_CHECK(pResource, return ENull);
   TCharC* pSource = pResource->Source();
   // 加载场景资源
   FScene3dDisplay* pDisplay = FScene3dDisplay::InstanceCreate();
   pDisplay->LoadSceneResource(pResource);
   // 加载渲染集合
   RInstance3dManager::Instance().TemplateConsole()->Load(pDisplay, pSource);
   //............................................................
   // 读取渲染对象集合
   FMaterial* pLightMaterial = _directionalLight->Material();
   if(pDisplay->Renderables() != NULL){
      GRenderablePtrs::TIteratorC iterator = pDisplay->Renderables().IteratorC();
      while(iterator.Next()){
         FTemplate3dRenderable* pRenderable = (*iterator)->Convert<FTemplate3dRenderable>();
         pRenderable->UpdateMaterial(pLightMaterial);
      }
   }
   // 放入集合
   pLayer->DisplayPush(pDisplay);
   return ESuccess;
}

//============================================================
// <T>加载天空资源。</T>
//
// @param pResource 资源对象
// @return 处理结果
//============================================================
TResult FScene3d::LoadSkyResource(FRs3dSceneSky* pResource){
   MO_CHECK(pResource, return ENull);
   GRs3dSceneDisplayPtrs::TIteratorC displayIterator = pResource->Displays().IteratorC();
   while(displayIterator.Next()){
      FRs3dSceneDisplay* pDisplay = *displayIterator;
      TResult resultCd = LoadDisplayResource(_sceneFrame->MapLayer(), pDisplay);
      if(resultCd != ESuccess){
         return resultCd;
      }
   }
   return ESuccess;
}

//============================================================
// <T>加载地图资源。</T>
//
// @param pResource 资源对象
// @return 处理结果
//============================================================
TResult FScene3d::LoadMapResource(FRs3dSceneMap* pResource){
   MO_CHECK(pResource, return ENull);
   GRs3dSceneDisplayPtrs::TIteratorC displayIterator = pResource->Displays().IteratorC();
   while(displayIterator.Next()){
      FRs3dSceneDisplay* pDisplay = *displayIterator;
      TResult resultCd = LoadDisplayResource(_sceneFrame->MapLayer(), pDisplay);
      if(resultCd != ESuccess){
         return resultCd;
      }
   }
   return ESuccess;
}

//============================================================
// <T>加载空间资源。</T>
//
// @param pResource 资源对象
// @return 处理结果
//============================================================
TResult FScene3d::LoadSpaceResource(FRs3dSceneSpace* pResource){
   MO_CHECK(pResource, return ENull);
   GRs3dSceneDisplayPtrs::TIteratorC displayIterator = pResource->Displays().IteratorC();
   while(displayIterator.Next()){
      FRs3dSceneDisplay* pDisplay = *displayIterator;
      TResult resultCd = LoadDisplayResource(_sceneFrame->SpaceLayer(), pDisplay);
      if(resultCd != ESuccess){
         return resultCd;
      }
   }
   return ESuccess;
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源对象
//============================================================
TResult FScene3d::LoadResource(FRs3dScene* pResource){
   MO_CHECK(pResource, return ENull);
   // 设置属性
   _pResource = pResource;
   // 加载技术资源
   FRs3dSceneTechnique* pTechniqueResource = pResource->Technique();
   LoadTechniqueResource(pTechniqueResource);
   // 加载区域资源
   FRs3dSceneRegion* pRegionResource = pResource->Region();
   LoadRegionResource(pRegionResource);
   // 加载天空资源
   FRs3dSceneSky* pSkyResource = pResource->Sky();
   LoadSkyResource(pSkyResource);
   // 加载地图资源
   FRs3dSceneMap* pMapResource = pResource->Map();
   LoadMapResource(pMapResource);
   // 加载空间资源
   FRs3dSceneSpace* pSpaceResource = pResource->Space();
   LoadSpaceResource(pSpaceResource);
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FScene3d::Setup(){
   TResult resultCd = FStage::Setup();
   // 构件场景帧
   _sceneFrame = FScene3dFrame::InstanceCreate();
   _sceneFrame->Setup();
   _layers.Push(_sceneFrame);
   return resultCd;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FScene3d::Suspend(){
   return FStage::Suspend();
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FScene3d::Resume(){
   return FStage::Resume();
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FScene3d::Dispose(){
   return FStage::Dispose();
}

MO_NAMESPACE_END
