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
TResult FDepthAutomaticEffect::OnSetup(){
   FAutomaticEffect::OnSetup();
   // 注册常量集合
   _constDescriptors.Register(ERenderShader_Vertex,   EEffectConst_Vertex_ModelMatrix,          "vc_model_matrix");
   _constDescriptors.Register(ERenderShader_Vertex,   EEffectConst_Vertex_ViewProjectionMatrix, "vc_view_projection_matrix");
   _constDescriptors.Register(ERenderShader_Vertex,   EEffectConst_Vertex_CameraPosition,       "vc_camera_position");
   _constDescriptors.Register(ERenderShader_Vertex,   EEffectConst_Vertex_LightDirection,       "vc_light_direction");
   _constDescriptors.Register(ERenderShader_Fragment, EEffectConst_Fragment_CameraPosition,     "fc_camera_position");
   _constDescriptors.Register(ERenderShader_Fragment, EEffectConst_Fragment_LightDirection,     "fc_light_direction");
   _constDescriptors.Register(ERenderShader_Fragment, EEffectConst_Fragment_Color,              "fc_color");
   _constDescriptors.Register(ERenderShader_Fragment, EEffectConst_Fragment_Alpha,              "fc_alpha");
   _constDescriptors.Register(ERenderShader_Fragment, EEffectConst_Fragment_AmbientColor,       "fc_ambient_color");
   _constDescriptors.Register(ERenderShader_Fragment, EEffectConst_Fragment_DiffuseColor,       "fc_diffuse_color");
   _constDescriptors.Register(ERenderShader_Fragment, EEffectConst_Fragment_SpecularColor,      "fc_specular_color");
   _constDescriptors.Register(ERenderShader_Fragment, EEffectConst_Fragment_Specular,           "fc_specular");
   _constDescriptors.Register(ERenderShader_Fragment, EEffectConst_Fragment_ReflectColor,       "fc_reflect_color");
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
   BindConstMatrix4x4(EEffectConst_Vertex_ModelMatrix, &modelMatrix);
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
   BindConstMatrix4x4(EEffectConst_Vertex_ModelMatrix, _modelMatrixs, count);
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
   BindConstMatrix4x4(EEffectConst_Vertex_ViewProjectionMatrix, &_vpMatrix);
   BindConstPosition3(EEffectConst_Vertex_CameraPosition, pCamera->Position());
   BindConstPosition3(EEffectConst_Fragment_CameraPosition, pCamera->Position());
   FDirectionalLight* pDirectionalLight = pRegion->DirectionalLight();
   if(pDirectionalLight != NULL){
      BindConstVector3(EEffectConst_Vertex_LightDirection, pDirectionalLight->Direction());
      BindConstVector3(EEffectConst_Fragment_LightDirection, pDirectionalLight->Direction());
   }
   //............................................................
   // 设置材质
   FRenderable* pRenderable = pRegion->VisibleRenderables()->Get(offset);
   FMaterial* pMaterial = pRenderable->Material();
   TCharC* pMaerialName = pMaterial->Name();
   BindConstFloat4(EEffectConst_Fragment_Color,         pMaterial->Color().min, pMaterial->Color().max, pMaterial->Color().rate, pMaterial->Color().merge);
   BindConstFloat4(EEffectConst_Fragment_Alpha,         pMaterial->Alpha().base, pMaterial->Alpha().rate, pMaterial->Alpha().level, pMaterial->Alpha().merge);
   BindConstColor4(EEffectConst_Fragment_AmbientColor,  pMaterial->AmbientColor());
   BindConstColor4(EEffectConst_Fragment_DiffuseColor,  pMaterial->DiffuseColor());
   BindConstColor4(EEffectConst_Fragment_SpecularColor, pMaterial->SpecularColor());
   BindConstFloat4(EEffectConst_Fragment_Specular,      pMaterial->SpecularInfo().base, pMaterial->SpecularInfo().rate, pMaterial->SpecularInfo().average, pMaterial->SpecularInfo().shadow);
   BindConstColor4(EEffectConst_Fragment_ReflectColor,  pMaterial->ReflectColor());
   //............................................................
   TResult resultCd = FAutomaticEffect::DrawGroup(pRegion, offset, count);
   //............................................................
   pRenderDevice->SetProgram(NULL);
   return resultCd;
}

MO_NAMESPACE_END
