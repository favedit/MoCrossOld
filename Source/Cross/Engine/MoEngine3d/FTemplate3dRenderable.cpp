#include "MoE3Material.h"
#include "MoE3Model.h"
#include "MoE3Template.h"
#include "MoE3Instance.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplate3dRenderable, FModel3dRenderable);

//============================================================
// <T>构造实体3D几何体。</T>
//============================================================
FTemplate3dRenderable::FTemplate3dRenderable(){
   _material = FMaterial3d::InstanceCreate();
   MO_CLEAR(_pTemplate);
   MO_CLEAR(_pModel);
   MO_CLEAR(_pGeometry);
   MO_CLEAR(_pAnimation);
   _pBones = MO_CREATE(FBone3dCollection);
}

//============================================================
// <T>析构实体3D几何体。</T>
//============================================================
FTemplate3dRenderable::~FTemplate3dRenderable(){
   MO_DELETE(_pBones);
}

//============================================================
// <T>计算模型矩阵。</T>
//
// @param matrix 模型矩阵
// @return 处理结果
//============================================================
TResult FTemplate3dRenderable::CalculateModelMatrix(SFloatMatrix3d& matrix){
   //_locker.Enter();
   // 计算矩阵
   FRs3dGeometry* pGeometryResource = _pGeometry->Resource();
   FRs3dTrack* pTrack = pGeometryResource->Track();
   if(pTrack->HasFrame()){
      // 获得时间
      FAnimation3d* pAnimation = _pTemplate->Animation();
      TTimeTick currentTick = pAnimation->CurrentTick();
      // 计算帧信息
      SRs3dFrameInfo info;
      pTrack->CalculateFrameInfo(info, currentTick);
      info.Update();
      // 追加变换
      matrix.Assign(pTrack->MatrixInvert());
      matrix.Append(info.matrix);
   }
   //_locker.Leave();
   return ESuccess;
}

//============================================================
// <T>计算模型矩阵。</T>
//
// @param pMatrix 矩阵集合
// @param offset 位置
// @param count 容量
// @return 处理个数
//============================================================
TInt FTemplate3dRenderable::CalculateBoneMatrix(SFloatMatrix3d* pMatrix, TInt offset, TInt capacity){
   // 检查骨骼对象
   if(_pBones == NULL){
      MO_FATAL("Bone is null.");
      return 0;
   }
   // 计算骨头个数
   //_pAnimation->Locker().Enter();
   TInt boneCount = _pBones->Count();
   TInt count = boneCount;
   if(capacity > 0){
      count = MO_LIB_MIN(boneCount, capacity);
   }
   if(pMatrix != NULL){
      // 设置骨骼矩阵
      for(TInt n = 0; n < count; n++){
         FBone3d* pBone = _pBones->Get(n);
         SFloatMatrix3d& boneMatrix = pBone->Matrix();
         SFloatMatrix3d& matrix = pMatrix[offset + n];
         matrix.Assign(boneMatrix);
      }
   }
   //_pAnimation->Locker().Leave();
   return count;
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源对象
//============================================================
TResult FTemplate3dRenderable::LoadResource(FRs3dTemplateRenderable* pResource){
   MO_CHECK(pResource, return ENull);
   //............................................................
   // 加载模型
   TCharC* pModelName = pResource->ModelName();
   _pModel = RInstance3dManager::Instance().ModelConsole()->Load(pModelName);
   MO_FATAL_CHECK(_pModel, return EFailure,
         "Model is not exists. (model=%s)", pModelName);
   //............................................................
   // 加载网格
   TInt geometryIndex = pResource->GeometryIndex();
   _pGeometry = _pModel->Geometrys()->Get(geometryIndex);
   MO_FATAL_CHECK(_pGeometry, return EFailure,
         "Model geometry is not exists. (model=%s, geometry_index=%d)", pModelName, geometryIndex);
   SetGeometry(_pGeometry->Geometry());
   //............................................................
   // 加载材质
   FMaterial3d* pMaterial = _materialReference->Convert<FMaterial3d>();
   GMaterial3dTexturePtrs& materialTextures = pMaterial->MaterialTextures();
   if(!materialTextures.IsEmpty()){
      GMaterial3dTexturePtrs::TIteratorC iterator = materialTextures.IteratorC();
      while(iterator.Next()){
         FMaterial3dTexture* pTexture = *iterator;
         FRs3dMaterialTexture* pTextureResource = pTexture->Resource();
         // 获得属性
         TCharC* pCode = pTextureResource->Code();
         TCharC* pPackCode = pTextureResource->PackCode();
         FRenderTexture* pRenderTexture = pTexture->RenderTexture();
         //pRenderTexture->SetOwner(this);
         // 增加取样器
         FRenderableSampler* pSampler = FRenderableSampler::InstanceCreate();
         pSampler->SetCode(pCode);
         pSampler->SetPackCode(pPackCode);
         pSampler->SetGraphicsObject(pRenderTexture);
         SamplerPush(pSampler);
      }
   }
   _material->AssignOption(pMaterial);
   //............................................................
   // 设置数据
   _modelModel.Assign(pResource->Matrix());
   //............................................................
   TFsName typeName;
   typeName.AppendFormat("%s|%d", pModelName, geometryIndex);
   SetTypeName(typeName);
   return ESuccess;
}

//============================================================
// <T>建立标志。</T>
//
// @处理结果
//============================================================
TResult FTemplate3dRenderable::BuildDescriptor(){
   //// 设置骨头个数
   //_descriptor.vertexCount = _pVertexStreams->VertexCount();
   //if(_pBones != NULL){
   //   _descriptor.boneCount = _pBones->Count();
   //}
   //// 设置属性集合
   //FRenderVertexStreamCollection* pStreams = _pVertexStreams->Streams();
   //TInt count = pStreams->Count();
   //for(TInt n = 0; n < count; n++){
   //   FRenderVertexStream* pStream = pStreams->Get(n);
   //   //TInt bufferCd = RRenderAttribute::Parse(pStream->Code());
   //   //_descriptor.vertexBuffers[bufferCd] = ETrue;
   //}
   //// 设置材质集合
   //FMaterial3d* pMaterial = _material->Convert<FMaterial3d>();
   //pMaterial->BuildDescriptor(_descriptor);
   return ESuccess;
}

//============================================================
// <T>建立材质。</T>
//
// @return 处理结果
//============================================================
TResult FTemplate3dRenderable::UpdateMaterial(FMaterial* pLightMaterial){
   _material->AssignValue(_materialReference);
   if(pLightMaterial != NULL){
      _material->Merge(pLightMaterial);
   }
   return ESuccess;
}

//============================================================
TResult FTemplate3dRenderable::AnsyProcess(){
   //_locker.Enter();
   //// 计算矩阵
   //FRs3dGeometry* pGeometryResource = _pGeometry->Resource();
   //FRs3dTrack* pTrack = pGeometryResource->Track();
   //if(!pTrack->Frames()->IsEmpty()){
   //   // 获得时间
   //   FAnimation3d* pAnimation = _pTemplate->Animation();
   //   TTimeTick currentTick = pAnimation->CurrentTick();
   //   // 计算帧信息
   //   SRs3dFrameInfo info;
   //   pTrack->CalculateFrameInfo(info, currentTick);
   //   info.Update();
   //   // 追加变换
   //   _frameMatrix.Assign(pTrack->MatrixInvert());
   //   _frameMatrix.Append(info.matrix);
   //}
   ////_matrix.Assign(_frameMatrix);
   ////_matrix.Append(_modelModel);
   //_locker.Leave();
   return ESuccess;
}

MO_NAMESPACE_END
