#include "MoFrContent3dScene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dSceneDisplay, FInstance);

//============================================================
// <T>构造资源场景动画。</T>
//============================================================
FRs3dSceneDisplay::FRs3dSceneDisplay(){
}

//============================================================
// <T>析构资源场景动画。</T>
//============================================================
FRs3dSceneDisplay::~FRs3dSceneDisplay(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dSceneDisplay::Unserialize(IDataInput* pInput){
   // 读取来源
   _source.UnserializeAutomatic(pInput);
   _optionMergeVertex = RGeomFlag::ToBoolean(pInput->ReadInt8());
   _optionMergeMaterial = RGeomFlag::ToBoolean(pInput->ReadInt8());
   // 读取矩阵
   _matrix.Unserialize(pInput);
   // 读取动画集合
   TInt movieCount = pInput->ReadInt32();
   for(TInt n = 0; n < movieCount; n++){
      FRs3dSceneMovie* pMovie = FRs3dSceneMovie::InstanceCreate();
      pMovie->Unserialize(pInput);
      _movies.Push(pMovie);
   }
   // 读取材质集合
   TInt materialCount = pInput->ReadInt32();
   for(TInt n = 0; n < materialCount; n++){
      FRs3dSceneMaterial* pMaterial = FRs3dSceneMaterial::InstanceCreate();
      pMaterial->Unserialize(pInput);
      _materials.Push(pMaterial);
   }
   // 读取渲染集合
   TInt renderableCount = pInput->ReadInt32();
   for(TInt n = 0; n < renderableCount; n++){
      FRs3dSceneRenderable* pRenderable = FRs3dSceneRenderable::InstanceCreate();
      pRenderable->Unserialize(pInput);
      _renderables.Push(pRenderable);
   }
   return ESuccess;
}

MO_NAMESPACE_END
