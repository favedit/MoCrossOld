#include "MoErTechnique.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FDepthAutomaticEffect, FAutomaticEffect);

//============================================================
// <T>构造渲染效果。</T>
//============================================================
FDepthAutomaticEffect::FDepthAutomaticEffect(){
   _descriptor.supportInstance = ETrue;
}

//============================================================
// <T>析构渲染效果。</T>
//============================================================
FDepthAutomaticEffect::~FDepthAutomaticEffect(){
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FDepthAutomaticEffect::Setup(){
   FAutomaticEffect::Setup();
   // 注册常量集合
   //_constDescriptors.Register(ERenderShader_Vertex,   EEffectParameter_VertexModelMatrix,          "vc_model_matrix");
   //_constDescriptors.Register(ERenderShader_Vertex,   EEffectParameter_VertexViewProjectionMatrix, "vc_view_projection_matrix");
   //_constDescriptors.Register(ERenderShader_Vertex,   EEffectParameter_VertexCameraPosition,       "vc_camera_position");
   //_constDescriptors.Register(ERenderShader_Vertex,   EEffectParameter_VertexLightDirection,       "vc_light_direction");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentCameraPosition,     "fc_camera_position");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentLightDirection,     "fc_light_direction");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentColor,              "fc_color");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentAlpha,              "fc_alpha");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentAmbientColor,       "fc_ambient_color");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentDiffuseColor,       "fc_diffuse_color");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentSpecularColor,      "fc_specular_color");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentSpecular,           "fc_specular");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentReflectColor,       "fc_reflect_color");
   return ESuccess;
}

//============================================================
// <T>绘制处理。</T>
//============================================================
TResult FDepthAutomaticEffect::DrawRenderable(FRenderRegion* pRegion, FRenderable* pRenderable){
   MO_ASSERT(pRenderable);
   SFloatMatrix3d& renderableMatrix = pRenderable->Matrix();
   // 获得设备
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   //............................................................
   // 模型矩阵
   SMatrix3d modelMatrix;
   pRenderable->CalculateModelMatrix(modelMatrix);
   modelMatrix.Append(renderableMatrix);
   //............................................................
   // 设置常量
   BindConstMatrix4x4(EEffectParameter_VertexModelMatrix4x4, &modelMatrix);
   //............................................................
   // 设定属性集合
   BindAttributeDescriptors(pRenderable);
   // 设置纹理集合
   BindSamplerDescriptors(pRenderable);
   //............................................................
   // 设置索引流，绘制三角形
   FRenderIndexBuffer* pIndexBuffer = pRenderable->IndexBuffer();
   pRenderDevice->DrawTriangles(pIndexBuffer, 0, pIndexBuffer->Count());
   return ESuccess;
}

//============================================================
TResult FDepthAutomaticEffect::DrawInstanceRenderable(FRenderRegion* pRegion, FInstanceRenderable* pInstanceRenderable, TInt offset, TInt count){
   MO_CHECK(pRegion, return ENull);
   MO_CHECK(pInstanceRenderable, return ENull);
   // 获得设备
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   //............................................................
   FRenderableCollection* pRenderables = pRegion->Renderables();
   for(TInt n = 0; n < count; n++){
      FRenderable* pRenderable = pRenderables->Get(offset + n);
      SFloatMatrix3d& renderableMatrix = pRenderable->Matrix();
      _modelMatrixs[n].Assign(renderableMatrix);
   }
   BindConstMatrix4x4(EEffectParameter_VertexModelMatrix4x4, _modelMatrixs, count);
   //............................................................
   // 设定属性集合
   BindAttributeDescriptors(pInstanceRenderable);
   // 设置纹理集合
   BindSamplerDescriptors(pInstanceRenderable);
   //............................................................
   // 设置索引流，绘制三角形
   FRenderIndexBuffer* pIndexBuffer = pInstanceRenderable->IndexBuffer();
   pRenderDevice->DrawInstanceTriangles(pIndexBuffer, 0, count);
   return ESuccess;
}

//============================================================
// <T>绘制处理。</T>
//============================================================
TResult FDepthAutomaticEffect::DrawGroup(FRenderRegion* pRegion, TInt offset, TInt count){
   MO_CHECK(pRegion, return ENull);
   // 设置设备状态
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   pRenderDevice->SetProgram(_program);
   //............................................................
   // 计算变换矩阵
   FRenderView* pView = pRegion->ActiveView();
   FCamera* pCamera = pView->Camera();
   _vpMatrix.Assign(pCamera->Matrix());
   FProjection* pProjection = pCamera->Projection();
   _vpMatrix.Append(pProjection->Matrix());
   //............................................................
   // 设置常量
   BindConstMatrix4x4(EEffectParameter_VertexViewProjectionMatrix4x4, &_vpMatrix);
   BindConstPosition3(EEffectParameter_VertexCameraPosition, pCamera->Position());
   BindConstPosition3(EEffectParameter_FragmentCameraPosition, pCamera->Position());
   FDirectionalLight* pDirectionalLight = pRegion->DirectionalLight();
   if(pDirectionalLight != NULL){
      BindConstVector3(EEffectParameter_VertexLightDirection, pDirectionalLight->Direction());
      BindConstVector3(EEffectParameter_FragmentLightDirection, pDirectionalLight->Direction());
   }
   //............................................................
   // 设置材质
   FRenderable* pRenderable = pRegion->VisibleRenderables()->Get(offset);
   FMaterial* pMaterial = pRenderable->Material();
   TCharC* pMaerialName = pMaterial->Name();
   BindConstFloat4(EEffectParameter_FragmentColor,         pMaterial->Color().min, pMaterial->Color().max, pMaterial->Color().rate, pMaterial->Color().merge);
   BindConstFloat4(EEffectParameter_FragmentAlpha,         pMaterial->Alpha().base, pMaterial->Alpha().rate, pMaterial->Alpha().level, pMaterial->Alpha().merge);
   BindConstColor4(EEffectParameter_FragmentAmbientColor,  pMaterial->AmbientColor());
   BindConstColor4(EEffectParameter_FragmentDiffuseColor,  pMaterial->DiffuseColor());
   BindConstColor4(EEffectParameter_FragmentSpecularColor, pMaterial->SpecularColor());
   BindConstFloat4(EEffectParameter_FragmentSpecular,      pMaterial->SpecularInfo().base, pMaterial->SpecularInfo().rate, pMaterial->SpecularInfo().average, pMaterial->SpecularInfo().shadow);
   BindConstColor4(EEffectParameter_FragmentReflectColor,  pMaterial->ReflectColor());
   //............................................................
   TResult resultCd = FAutomaticEffect::DrawGroup(pRegion, offset, count);
   //............................................................
   pRenderDevice->SetProgram(NULL);
   return resultCd;
}

MO_NAMESPACE_END
