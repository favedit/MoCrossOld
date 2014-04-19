#include "MoE3Scene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScene3dDisplay, FTemplate3d);

//============================================================
// <T>构造实体3D显示对象。</T>
//============================================================
FScene3dDisplay::FScene3dDisplay(){
}

//============================================================
// <T>析构实体3D显示对象。</T>
//============================================================
FScene3dDisplay::~FScene3dDisplay(){
}

//============================================================
// <T>加载显示资源。</T>
//
// @param pResource 资源对象
// @return 处理结果
//============================================================
TResult FScene3dDisplay::LoadSceneResource(FRs3dSceneDisplay* pResource){
   MO_CHECK(pResource, return ENull);
   _resource = pResource;
   // 设置矩阵
   _matrixModel.Assign(pResource->Matrix());
   // 设置材质集合
   GRs3dSceneMaterialPtrs::TIteratorC materialIterator = pResource->Materials().IteratorC();
   while(materialIterator.Next()){
      FRs3dSceneMaterial* pRsMaterial = *materialIterator;
      TCharC* pMaterialName = pRsMaterial->Name();
      // 创建材质
      FScene3dMaterial* pMaterial = FScene3dMaterial::InstanceCreate();
      pMaterial->LoadSceneResource(pRsMaterial);
      _materials.Set(pMaterialName, pMaterial);
   }
   // 加载动画集合
   GRs3dSceneMoviePtrs::TIteratorC moviesIterator = _resource->Movies().IteratorC();
   while(moviesIterator.Next()){
      FRs3dSceneMovie* pMovieResource = *moviesIterator;
      if(RString::Equals(pMovieResource->TypeName(), "rotation")){
         FScene3dMovie* pMovie = FScene3dMovieRotation::InstanceCreate();
         pMovie->LoadResource(pMovieResource);
         _movies.Push(pMovie);
      }
   }
   return ESuccess;
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源对象
//============================================================
TResult FScene3dDisplay::LoadResource(FRs3dTemplate* pResource){
   MO_CHECK(pResource, return ENull);
   TResult resultCd = FTemplate3d::LoadResource(pResource);
   // 读取渲染对象集合
   if(_pRenderables != NULL){
      FRenderableCollection::TIteratorC iterator = _pRenderables->IteratorC();
      while(iterator.Next()){
         FTemplate3dRenderable* pRenderable = (*iterator)->Convert<FTemplate3dRenderable>();
         TCharC* pMaterialName = pRenderable->Material()->Name();
         FMaterial* pMaterialReference = _materials.Find(pMaterialName);
         if(pMaterialReference != NULL){
            pRenderable->SetMaterialReference(pMaterialReference);
         }
      }
   }
   return resultCd;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FScene3dDisplay::ProcessBefore(SProcessContext* pContext){
   if(!_movies.IsEmpty()){
      TInt movieCount = _movies.Count();
      for(TInt n = 0; n < movieCount; n++){
         FScene3dMovie* pMovie = _movies.Get(n);
         pMovie->Process(_matrixTransform);
      }
   }
   return ESuccess;
}

MO_NAMESPACE_END
