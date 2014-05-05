#include "MoErShadowPipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FShadowColorAutomaticEffect, FColorAutomaticEffect);

//============================================================
// <T>构造阴影颜色自动渲染器。</T>
//============================================================
FShadowColorAutomaticEffect::FShadowColorAutomaticEffect(){
}

//============================================================
// <T>析构阴影颜色自动渲染器。</T>
//============================================================
FShadowColorAutomaticEffect::~FShadowColorAutomaticEffect(){
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FShadowColorAutomaticEffect::OnSetup(){
   FAutomaticEffect::OnSetup();
   //// 注册顶点常量
   //_constDescriptors.Register(ERenderShader_Vertex,   EEffectParameter_VertexModelMat3,            "vc_model_mat3");
   //_constDescriptors.Register(ERenderShader_Vertex,   EEffectParameter_VertexModelMat4,            "vc_model_mat4");
   //_constDescriptors.Register(ERenderShader_Vertex,   EEffectParameter_VertexMvpMat3,              "vc_mvp_mat3");
   //_constDescriptors.Register(ERenderShader_Vertex,   EEffectParameter_VertexMvpMat4,              "vc_mvp_mat4");
   //_constDescriptors.Register(ERenderShader_Vertex,   EEffectParameter_VertexLightMvMat4,          "vc_light_mv_mat4");
   //_constDescriptors.Register(ERenderShader_Vertex,   EEffectParameter_VertexLightMvpMat4,         "vc_light_mvp_mat4");
   //_constDescriptors.Register(ERenderShader_Vertex,   EEffectParameter_VertexCameraPosition,       "vc_camera_position");
   //_constDescriptors.Register(ERenderShader_Vertex,   EEffectParameter_VertexLightDirection,       "vc_light_direction");
   //// 注册像素常量
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentCamera,             "fc_camera");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentCameraPosition,     "fc_camera_position");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentLightDepth,         "fc_light_depth");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentLightCamera,        "fc_light_camera");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentLightDirection,     "fc_light_direction");
   //// 注册材质常量
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentShadowMaterial,     "fc_shadow_material");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentShadowMaterialInv,  "fc_shadow_material_inv");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentColor,              "fc_color");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentAlpha,              "fc_alpha");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentAmbientColor,       "fc_ambient_color");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentDiffuseColor,       "fc_diffuse_color");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentDiffuseViewColor,   "fc_diffuse_view_color");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentSpecularColor,      "fc_specular_color");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentSpecular,           "fc_specular");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentSpecularViewColor,  "fc_specular_view_color");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentSpecularView,       "fc_specular_view");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectParameter_FragmentReflectColor,       "fc_reflect_color");
   return ESuccess;
}

//============================================================
// <T>绘制处理。</T>
//============================================================
TResult FShadowColorAutomaticEffect::DrawRenderable(FRenderRegion* pRegion, FRenderable* pRenderable){
   MO_ASSERT(pRegion);
   MO_ASSERT(pRenderable);
   FRenderView* pView = pRegion->ActiveView();
   FCamera* pCamera = pView->Camera();
   FDirectionalLight* pLight = pRegion->DirectionalLight();
   FCamera* pLightCamera = pLight->Camera();
   FProjection* pLightProjection = pLightCamera->Projection();
   SFloatMatrix3d& renderableMatrix = pRenderable->Matrix();
   // 获得设备
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   //............................................................
   // 模型矩阵
   SMatrix3d modelMatrix;
   pRenderable->CalculateModelMatrix(modelMatrix);
   modelMatrix.Append(renderableMatrix);
   // 计算MVP矩阵
   SMatrix3d modelViewMatrix;
   modelViewMatrix.Assign(modelMatrix);
   modelViewMatrix.Append(pCamera->Matrix());
   // 计算MVP矩阵
   SMatrix3d matrixMvp;
   matrixMvp.Assign(modelMatrix);
   matrixMvp.Append(_vpMatrix);
   //............................................................
   // 计算MVP矩阵
   SMatrix3d lightMvMatrix;
   lightMvMatrix.Assign(modelMatrix);
   lightMvMatrix.Append(pLightCamera->Matrix());
   // 计算MVP矩阵
   SMatrix3d lightMvpMatrix;
   lightMvpMatrix.Assign(modelMatrix);
   lightMvpMatrix.Append(_lightVpMatrix);
   //............................................................
   // 设置常量
   BindConstMatrix3x3(EEffectParameter_VertexModelMatrix3x3, &modelMatrix);
   BindConstMatrix4x4(EEffectParameter_VertexModelMatrix4x4, &modelMatrix);
   BindConstMatrix4x4(EEffectParameter_VertexModelViewProjectionMatrix4x4, &matrixMvp);
   BindConstMatrix4x4(EEffectParameter_VertexLightModelViewMatrix4x4, &lightMvMatrix);
   BindConstMatrix4x4(EEffectParameter_VertexLightModelViewProjectionMatrix4x4, &lightMvpMatrix);
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
// <T>绘制处理。</T>
//============================================================
TResult FShadowColorAutomaticEffect::DrawGroup(FRenderRegion* pRegion, TInt offset, TInt count){
   MO_CHECK(pRegion, return ENull);
   // 计算相机信息
   FRenderTexture* pLightDepthTexture =  pRegion->Textures()->Get(0);
   FRenderView* pView = pRegion->ActiveView();
   FCamera* pCamera = pView->Camera();
   _vpMatrix.Assign(pCamera->Matrix());
   FProjection* pProjection = pCamera->Projection();
   _vpMatrix.Append(pProjection->Matrix());
   //............................................................
   // 设置光源信息
   FDirectionalLight* pLight = pRegion->DirectionalLight();
   TFloat ambientShadow = pLight->Material()->AmbientShadow();
   TFloat diffuseShadow = pLight->Material()->DiffuseShadow();
   TFloat specularShadow = pLight->Material()->SpecularShadow();
   TFloat specularViewShadow = pLight->Material()->SpecularViewShadow();
   FCamera* pLightCamera = pLight->Camera();
   SFloatVector3& lightDirection = pLightCamera->Direction();
   _lightVpMatrix.Assign(pLightCamera->Matrix());
   FProjection* pLightProjection = pLightCamera->Projection();
   _lightVpMatrix.Append(pLightProjection->Matrix());
   // 设置设备状态
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   pRenderDevice->SetProgram(_program);
   //............................................................
   // 设置相机信息
   BindConstPosition3(EEffectParameter_VertexCameraPosition, pCamera->Position());
   BindConstPosition3(EEffectParameter_FragmentCameraPosition, pCamera->Position());
   // 设置光源信息
   BindConstVector3(EEffectParameter_VertexLightDirection, lightDirection);
   BindConstVector3(EEffectParameter_FragmentLightDirection, lightDirection);
   // 设置光源深度
   SIntSize2& depthSize = pLightDepthTexture->Size();
   TFloat lightZnear = pLightProjection->Znear();
   TFloat lightZfar = pLightProjection->Zfar();
   BindConstFloat4(EEffectParameter_FragmentLightDepth, -1.0f / (TFloat)depthSize.width, 1.0f / (TFloat)depthSize.width, lightZnear, 1.0f / lightZfar);
   //............................................................
   // 设置材质
   FRenderable* pRenderable = pRegion->VisibleRenderables()->Get(offset);
   FMaterial* pMaterial = pRenderable->Material();
   BindConstFloat4(EEffectParameter_FragmentShadowMaterial,    ambientShadow, diffuseShadow, specularShadow, specularViewShadow);
   BindConstFloat4(EEffectParameter_FragmentShadowMaterialInv, 1.0f - ambientShadow, 1.0f - diffuseShadow, 1.0f - specularShadow, 1.0f - specularViewShadow);
   BindConstFloat4(EEffectParameter_FragmentColor,             pMaterial->Color().min, pMaterial->Color().max, pMaterial->Color().rate, pMaterial->Color().merge);
   BindConstFloat4(EEffectParameter_FragmentAlpha,             pMaterial->Alpha().base, pMaterial->Alpha().rate, pMaterial->Alpha().level, pMaterial->Alpha().merge);
   BindConstColor4(EEffectParameter_FragmentAmbientColor,      pMaterial->AmbientColor());
   BindConstColor4(EEffectParameter_FragmentDiffuseColor,      pMaterial->DiffuseColor());
   BindConstColor4(EEffectParameter_FragmentDiffuseViewColor,  pMaterial->DiffuseViewColor());
   BindConstColor4(EEffectParameter_FragmentSpecularColor,     pMaterial->SpecularColor());
   BindConstFloat4(EEffectParameter_FragmentSpecular,          pMaterial->SpecularInfo().base, pMaterial->SpecularInfo().rate, pMaterial->SpecularInfo().average, pMaterial->SpecularInfo().shadow);
   BindConstColor4(EEffectParameter_FragmentSpecularViewColor, pMaterial->SpecularViewColor());
   BindConstFloat4(EEffectParameter_FragmentSpecularView,      pMaterial->SpecularViewInfo().base, pMaterial->SpecularViewInfo().rate, pMaterial->SpecularViewInfo().average, pMaterial->SpecularViewInfo().shadow);
   BindConstColor4(EEffectParameter_FragmentReflectColor,      pMaterial->ReflectColor());
   //............................................................
   // 设置纹理集合
   BindSampler(ERenderSampler_LightDepth, pLightDepthTexture);
   //............................................................
   FAutomaticEffect::DrawGroup(pRegion, offset, count);
   //............................................................
   // 关闭程序
   pRenderDevice->SetProgram(NULL);
   return ESuccess;
}

MO_NAMESPACE_END
