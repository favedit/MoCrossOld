#include "MoErSimplePipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FSampleColorSkeletonEffect, FColorAutomaticEffect);

//============================================================
// <T>构造阴影颜色自动渲染器。</T>
//============================================================
FSampleColorSkeletonEffect::FSampleColorSkeletonEffect(){
   _descriptor.supportInstance = ETrue;
   _descriptor.supportSkeleton = ETrue;
}

//============================================================
// <T>析构阴影颜色自动渲染器。</T>
//============================================================
FSampleColorSkeletonEffect::~FSampleColorSkeletonEffect(){
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FSampleColorSkeletonEffect::OnSetup(){
   FColorAutomaticEffect::OnSetup();
   // 注册常量集合
   //_constDescriptors.Register(ERenderShader_Vertex, EEffectConst_Vertex_BoneMatrix, "vc_bone_matrix");
   return ESuccess;
}

//============================================================
// <T>建立模板信息。</T>
//
// @param renderableDescriptor 渲染描述
// @param pCode 代码
// @param pTemplateContext 模板环境
// @return 处理结果
//============================================================
TResult FSampleColorSkeletonEffect::BuildTemplate(SRenderableDescriptor& renderableDescriptor, MString* pCode, FTemplateContext* pTemplateContext){
   FAutomaticEffect::BuildTemplate(renderableDescriptor, pCode, pTemplateContext);
   // 计算最大实例个数
   TInt vertexCount = renderableDescriptor.vertexCount;
   TInt boneCount = renderableDescriptor.boneCount;
   TInt instanceCount = RRenderUtil::CalculateInstanceCount(vertexCount, boneCount);
   TInt instanceBoneCount = boneCount * instanceCount;
   // 设置信息
   if(_dynamicDescriptor.supportInstance && pTemplateContext){
      pTemplateContext->DefineInt("instance.count", instanceCount);
      pTemplateContext->DefineInt("instance.bone.count", instanceBoneCount * 3);
   }else if(pTemplateContext){
      pTemplateContext->DefineInt("bone.count", instanceBoneCount);
   }
   return ESuccess;
}

//============================================================
// <T>绘制处理。</T>
//============================================================
TResult FSampleColorSkeletonEffect::DrawRenderable(FRenderRegion* pRegion, FRenderable* pRenderable){
   MO_CHECK(pRegion, return ENull);
   MO_CHECK(pRenderable, return ENull);
   //............................................................
   // 计算模型矩阵
   SFloatMatrix3d modelMatrix;
   pRenderable->CalculateModelMatrix(modelMatrix);
   modelMatrix.Append(pRenderable->Matrix());
   BindConstMatrix4x4(EEffectConst_Vertex_ModelMatrix, &modelMatrix);
   // 计算骨骼矩阵
   TInt boneCount = pRenderable->CalculateBoneMatrix(_boneMatrixs);
   BindConstMatrix4x3(EEffectConst_Vertex_BoneMatrix, (SFloatMatrix3d*)&_boneMatrixs, boneCount);
   //............................................................
   // 设定属性集合
   BindAttributeDescriptors(pRenderable);
   // 设置纹理集合
   BindSamplerDescriptors(pRenderable);
   //............................................................
   // 设置索引流，绘制三角形
   FRenderIndexBuffer* pIndexBuffer = pRenderable->IndexBuffer();
   _renderDevice->DrawTriangles(pIndexBuffer, 0, pIndexBuffer->Count());
   return ESuccess;
}

//============================================================
TResult FSampleColorSkeletonEffect::DrawInstanceRenderable(FRenderRegion* pRegion, FInstanceRenderable* pInstanceRenderable, TInt offset, TInt count){
   MO_CHECK(pRegion, return ENull);
   MO_CHECK(pInstanceRenderable, return ENull);
   FRenderableCollection* pRenderables = pRegion->VisibleRenderables();
   TInt boneMatrixCount = 0;
   for(TInt n = 0; n < count; n++){
      FRenderable* pRenderable = pRenderables->Get(offset + n);
      SFloatMatrix3d& renderableMatrix = pRenderable->Matrix();
      // 计算模型矩阵
      SFloatMatrix3d modelMatrix;
      pRenderable->CalculateModelMatrix(modelMatrix);
      modelMatrix.Append(renderableMatrix);
      _modelMatrixs[n].Assign(modelMatrix);
      // 计算骨骼矩阵
      TInt boneCount = pRenderable->CalculateBoneMatrix(_boneMatrixs, boneMatrixCount, MO_EG_CONST_MATRIX_MAX - boneMatrixCount);
      boneMatrixCount += boneCount;
   }
   //............................................................
   BindConstMatrix4x4(EEffectConst_Vertex_ModelMatrix, (SFloatMatrix3d*)&_modelMatrixs, count);
   BindConstMatrix4x3(EEffectConst_Vertex_BoneMatrix, (SFloatMatrix3d*)&_boneMatrixs, boneMatrixCount);
   //............................................................
   // 设定属性集合
   BindAttributeDescriptors(pInstanceRenderable);
   // 设置纹理集合
   BindSamplerDescriptors(pInstanceRenderable);
   //............................................................
   // 设置索引流，绘制三角形
   FRenderIndexBuffer* pIndexBuffer = pInstanceRenderable->IndexBuffer();
   _renderDevice->DrawInstanceTriangles(pIndexBuffer, 0, count);
   return ESuccess;
}

MO_NAMESPACE_END
