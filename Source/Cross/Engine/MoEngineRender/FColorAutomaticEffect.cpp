#include "MoErTechnique.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FColorAutomaticEffect, FAutomaticEffect);

//============================================================
// <T>构造颜色自动渲染器。</T>
//============================================================
FColorAutomaticEffect::FColorAutomaticEffect(){
   _descriptor.supportInstance = ETrue;
}

//============================================================
// <T>析构颜色自动渲染器。</T>
//============================================================
FColorAutomaticEffect::~FColorAutomaticEffect(){
}

//============================================================
TResult FColorAutomaticEffect::BindDescriptors(){
   TResult resultCd = FAutomaticEffect::BindDescriptors();
   // 变换参数
   RegisterParameter("VertexInstance",                       EEffectParameter_VertexInstance);
   RegisterParameter("VertexModelMatrix",                    EEffectParameter_VertexModelMatrix);
   RegisterParameter("VertexViewMatrix",                     EEffectParameter_VertexViewMatrix);
   RegisterParameter("VertexProjectionMatrix",               EEffectParameter_VertexProjectionMatrix);
   RegisterParameter("VertexViewProjectionMatrix",           EEffectParameter_VertexViewProjectionMatrix);
   RegisterParameter("VertexModelViewProjectionMatrix",      EEffectParameter_VertexModelViewProjectionMatrix);
   RegisterParameter("VertexBoneMatrix",                     EEffectParameter_VertexBoneMatrix);
   // 相机参数
   RegisterParameter("VertexCameraPosition",                 EEffectParameter_VertexCameraPosition);
   //RegisterParameter("VertexCameraDirection",                EEffectParameter_VertexCameraDirection);
   // 光源参数
   //RegisterParameter("VertexLightModelMatrix4x4",               EEffectParameter_VertexLightModelMatrix4x4);
   //RegisterParameter("VertexLightModelViewMatrix4x4",           EEffectParameter_VertexLightModelViewMatrix4x4);
   //RegisterParameter("VertexLightModelViewProjectionMatrix4x4", EEffectParameter_VertexLightModelViewProjectionMatrix4x4);
   //RegisterParameter("VertexLightPosition",                     EEffectParameter_VertexLightPosition);
   RegisterParameter("VertexLightDirection"              ,      EEffectParameter_VertexLightDirection);
   //............................................................
   //// 相机参数
   //RegisterParameter("FragmentCamera",                             EEffectParameter_FragmentCamera);
   RegisterParameter("FragmentCameraPosition",                     EEffectParameter_FragmentCameraPosition);
   //RegisterParameter("FragmentCameraDirection",                    EEffectParameter_FragmentCameraDirection);
   //// 光源参数
   //RegisterParameter("FragmentLightModelMatrix4x4",                EEffectParameter_FragmentLightModelMatrix4x4);
   //RegisterParameter("FragmentLightModelViewProjectrionMatrix4x4", EEffectParameter_FragmentLightModelViewProjectrionMatrix4x4);
   //RegisterParameter("FragmentLightPosition",                      EEffectParameter_FragmentLightPosition);
   RegisterParameter("FragmentLightDirection",                     EEffectParameter_FragmentLightDirection);
   RegisterParameter("FragmentLightDepth",                         EEffectParameter_FragmentLightDepth);
   // 材质参数
   RegisterParameter("FragmentColor",             EEffectParameter_FragmentColor);
   RegisterParameter("FragmentAlpha",             EEffectParameter_FragmentAlpha);
   RegisterParameter("FragmentAmbientColor",      EEffectParameter_FragmentAmbientColor);
   RegisterParameter("FragmentDiffuseColor",      EEffectParameter_FragmentDiffuseColor);
   RegisterParameter("FragmentDiffuseViewColor",  EEffectParameter_FragmentDiffuseViewColor);
   RegisterParameter("FragmentSpecularColor",     EEffectParameter_FragmentSpecularColor);
   RegisterParameter("FragmentSpecularRate",      EEffectParameter_FragmentSpecularRate);
   RegisterParameter("FragmentSpecularViewColor", EEffectParameter_FragmentSpecularViewColor);
   RegisterParameter("FragmentSpecularViewRate",  EEffectParameter_FragmentSpecularViewRate);
   RegisterParameter("FragmentReflectColor",      EEffectParameter_FragmentReflectColor);
   RegisterParameter("FragmentShadowMaterial",    EEffectParameter_FragmentShadowMaterial);
   RegisterParameter("FragmentShadowMaterialInv", EEffectParameter_FragmentShadowMaterialInv);
   return resultCd;
}

//============================================================
// <T>绘制处理。</T>
//============================================================
TResult FColorAutomaticEffect::DrawRenderable(FRenderRegion* pRegion, FRenderable* pRenderable){
   MO_CHECK(pRegion, return ENull);
   MO_CHECK(pRenderable, return ENull);
   //............................................................
   // 计算模型矩阵
   SMatrix3d modelMatrix;
   pRenderable->CalculateModelMatrix(modelMatrix);
   modelMatrix.Append(pRenderable->Matrix());
   BindConstMatrix4x4(EEffectParameter_VertexModelMatrix, &modelMatrix);
   BindConstMatrix4x4(EEffectParameter_VertexModelViewProjectionMatrix, &modelMatrix);
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
TResult FColorAutomaticEffect::DrawInstanceRenderable(FRenderRegion* pRegion, FInstanceRenderable* pInstanceRenderable, TInt offset, TInt count){
   MO_CHECK(pRegion, return ENull);
   MO_CHECK(pInstanceRenderable, return ENull);
   FRenderableCollection* pRenderables = pRegion->Renderables();
   for(TInt n = 0; n < count; n++){
      FRenderable* pRenderable = pRenderables->Get(offset + n);
      SFloatMatrix3d& renderableMatrix = pRenderable->Matrix();
      _modelMatrixs[n].Assign(renderableMatrix);
   }
   BindConstMatrix4x4(EEffectParameter_VertexModelMatrix, _modelMatrixs, count);
   //............................................................
   // 设定属性集合
   BindAttributeDescriptors(pInstanceRenderable);
   // 设置纹理集合
   BindSamplerDescriptors(pInstanceRenderable);
   //............................................................
   // 设置索引流，绘制三角形
   FRenderIndexBuffer* pIndexBuffer = pInstanceRenderable->IndexBuffer();
   _renderDevice->DrawInstanceTriangles(pIndexBuffer, 0, 1);
   //............................................................
   UnbindAttributeDescriptors(pInstanceRenderable);
   return ESuccess;
}

//============================================================
// <T>绘制处理。</T>
//============================================================
TResult FColorAutomaticEffect::DrawGroup(FRenderRegion* pRegion, TInt offset, TInt count){
   MO_CHECK(pRegion, return ENull);
   // 设置设备状态
   _renderDevice->SetProgram(_program);
   //............................................................
   // 计算变换矩阵
   FRenderView* pView = pRegion->ActiveView();
   FCamera* pCamera = pView->Camera();
   SFloatMatrix3d& viewMatrix = pCamera->Matrix();
   _vpMatrix.Assign(viewMatrix);
   FProjection* pProjection = pCamera->Projection();
   SFloatMatrix3d& projectionMatrix = pProjection->Matrix();
   _vpMatrix.Append(projectionMatrix);
   //............................................................
   // 设置常量
   SFloatPoint3& cameraPosition = pCamera->Position();
   BindConstMatrix4x4(EEffectParameter_VertexViewMatrix, &viewMatrix);
   BindConstMatrix4x4(EEffectParameter_VertexProjectionMatrix, &projectionMatrix);
   BindConstMatrix4x4(EEffectParameter_VertexViewProjectionMatrix, &_vpMatrix);
   BindConstPosition3(EEffectParameter_VertexCameraPosition, cameraPosition);
   BindConstPosition3(EEffectParameter_FragmentCameraPosition, cameraPosition);
   FDirectionalLight* pDirectionalLight = pRegion->DirectionalLight();
   if(pDirectionalLight != NULL){
      SFloatVector3& lightDirection = pDirectionalLight->Direction();
      BindConstVector3(EEffectParameter_VertexLightDirection, lightDirection);
      BindConstVector3(EEffectParameter_FragmentLightDirection, lightDirection);
   }
   //............................................................
   // 设置材质
   FRenderable* pRenderable = pRegion->VisibleRenderables()->Get(offset);
   FMaterial* pMaterial = pRenderable->Material();
   TCharC* pMaerialName = pMaterial->Name();
   SMaterialColorInfo& color = pMaterial->Color();
   SMaterialAlphaInfo& alpha = pMaterial->Alpha();
   SMaterialSpecularInfo& specularInfo = pMaterial->SpecularInfo();
   SMaterialSpecularInfo& specularViewInfo = pMaterial->SpecularViewInfo();
   BindConstFloat4(EEffectParameter_FragmentColor,             color.min, color.max, color.rate, color.merge);
   BindConstFloat4(EEffectParameter_FragmentAlpha,             alpha.base, alpha.rate, alpha.level, alpha.merge);
   BindConstColor4(EEffectParameter_FragmentAmbientColor,      pMaterial->AmbientColor());
   BindConstColor4(EEffectParameter_FragmentDiffuseColor,      pMaterial->DiffuseColor());
   BindConstColor4(EEffectParameter_FragmentSpecularColor,     pMaterial->SpecularColor());
   BindConstFloat4(EEffectParameter_FragmentSpecularRate,      specularInfo.base, specularInfo.rate, specularInfo.average, specularInfo.shadow);
   BindConstColor4(EEffectParameter_FragmentSpecularViewColor, pMaterial->SpecularViewColor());
   BindConstFloat4(EEffectParameter_FragmentSpecularViewRate,  specularViewInfo.base, specularViewInfo.rate, specularViewInfo.average, specularViewInfo.shadow);
   BindConstColor4(EEffectParameter_FragmentReflectColor,      pMaterial->ReflectColor());
   //............................................................
   TResult resultCd = FAutomaticEffect::DrawGroup(pRegion, offset, count);
   //............................................................
   _renderDevice->SetProgram(NULL);
   return resultCd;
}

MO_NAMESPACE_END
