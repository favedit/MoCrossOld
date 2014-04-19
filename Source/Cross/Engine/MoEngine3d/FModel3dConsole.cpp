#include "MoE3Model.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造实体3D模型管理器。</T>
//============================================================
FModel3dConsole::FModel3dConsole(){
   _pModels = MO_CREATE(FModel3dDictionary);
   _pPools = MO_CREATE(FModel3dPoolSet);
   _pInstanceRenderables = MO_CREATE(FInstanceRenderableDictionary);
}

//============================================================
// <T>析构实体3D模型管理器。</T>
//============================================================
FModel3dConsole::~FModel3dConsole(){
   MO_DELETE(_pModels);
   MO_DELETE(_pPools);
   MO_DELETE(_pInstanceRenderables);
}

//============================================================
// <T>创建模型实体。</T>
//
// @param resourceId 资源编号
// @return 模型实体
//============================================================
FModel3d* FModel3dConsole::Create(TResourceId resourceId){
   //FRs3dTemplate* pTemplate = RResource3dManager::Instance().TemplateConsole()->Find(resourceId);
   //FRs3dTemplateRenderableCollection* pTplRenderables = pTemplate->Renderables();
   // 读取渲染对象集合
   FModel3d* pModel = MO_CREATE(FModel3d);
   //TInt count = pTplRenderables->Count();
   //for(TInt n = 0; n < count; n++){
   //   // 获得渲染对象模板
   //   FRs3dTemplateRenderable* pTplRenderable = pTplRenderables->Get(n);
   //   TResourceId modelId = pTplRenderable->ModelId();
   //   FRs3dModel* pTplModel = RResource3dManager::Instance().ModelConsole()->Find(modelId);
   //   TInt geometryIndex = pTplRenderable->GeometryIndex();
   //   FRs3dGeometry* pTplGeometry = pTplModel->Geometry(geometryIndex);
   //   TResourceId materialId = pTplRenderable->MaterialId();
   //   FRs3dMaterial* pTplMaterial = RResource3dManager::Instance().MaterialConsole()->Find(materialId);
   //   FRs3dMaterialTextureCollection* pTplTextures = pTplMaterial->Textures();

   //   FGeometry3d* pGeometry = MO_CREATE(FGeometry3d);

   //   TInt textureCount = pTplTextures->Count();
   //   for(TInt i = 0; i < textureCount; i++){
   //      FRs3dMaterialTexture* pTplTexture = pTplTextures->Get(n);
   //      TResourceId textureId = pTplTexture->TextureId();
   //      FRs3dTexture* pTexture = RResource3dManager::Instance().TextureConsole()->Open(textureId);
   //   }
   //   // 获得几何体
   //   //FRs3dTemplateRenderable* pRenderable = MO_CREATE(FRs3dTemplateRenderable);
   //   //pRenderable->Unserialize(pInput);
   //   //_pRenderables->Push(pRenderable);
   //}
   return pModel;
}

//============================================================
// <T>收集模型实体。</T>
//
// @param resourceId 资源编号
// @return 模型实体
//============================================================
FModel3d* FModel3dConsole::Alloc(TResourceId resourceId){
   // 获得缓冲池
   FModel3dPool* pPool = _pPools->Find(resourceId);
   if(pPool == NULL){
      pPool = MO_CREATE(FModel3dPool);
      _pPools->Set(resourceId, pPool);
   }
   // 获得对象
   if(!pPool->HasFreeItem()){
      FModel3d* pModel = Create(resourceId);
      pPool->Store(pModel);
   }
   return pPool->AllocFirst();
}

//============================================================
// <T>释放模型实体。</T>
//
// @param pModel 模型实体
//============================================================
void FModel3dConsole::Free(FModel3d* pModel){
}

//============================================================
FModel3d* FModel3dConsole::Load(TCharC* pName){
   MO_CHECK(pName, return NULL);
   FModel3d* pModel = _pModels->Find(pName);
   if(pModel == NULL){
      // 获得资源
      FRs3dModelConsole* pRsModelConsole = RResource3dManager::Instance().ModelConsole();
      FRs3dModel* pRsModel = pRsModelConsole->Find(pName);
      // 创建实体
      pModel = FModel3d::InstanceCreate();
      pModel->LoadResource(pRsModel);
      _pModels->Set(pName, pModel);
   }
   return pModel;
}

//============================================================
FInstanceRenderable* FModel3dConsole::LoadInstance(FRenderable* pRenderable, TInt instanceCount){
   TCharC* pTypeName = pRenderable->TypeName();
   MO_CHECK(pTypeName, return NULL);
   FInstanceRenderable* pInstanceRenderable = _pInstanceRenderables->Find(pTypeName);
   if(pInstanceRenderable == NULL){
      pInstanceRenderable = FInstanceRenderable::InstanceCreate();
      pInstanceRenderable->SetTypeName(pTypeName);
      pInstanceRenderable->SetRenderable(pRenderable);
      pInstanceRenderable->SetInstanceCount(instanceCount);
      pInstanceRenderable->Setup();
      _pInstanceRenderables->Set(pTypeName, pInstanceRenderable);
   }
   return pInstanceRenderable;
}

MO_NAMESPACE_END
