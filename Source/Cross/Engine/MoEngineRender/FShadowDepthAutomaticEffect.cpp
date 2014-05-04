#include "MoErShadowPipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FShadowDepthAutomaticEffect, FAutomaticEffect);

//============================================================
// <T>构造阴影深度自动渲染器。</T>
//============================================================
FShadowDepthAutomaticEffect::FShadowDepthAutomaticEffect(){
}

//============================================================
// <T>析构阴影深度自动渲染器。</T>
//============================================================
FShadowDepthAutomaticEffect::~FShadowDepthAutomaticEffect(){
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FShadowDepthAutomaticEffect::OnSetup(){
   TResult resultCd = FAutomaticEffect::OnSetup();
   // 注册常量集合
   //_constDescriptors.Register(ERenderShader_Vertex, EEffectConst_Vertex_ModelMat4, "vc_model_mat4");
   //_constDescriptors.Register(ERenderShader_Vertex, EEffectConst_Vertex_MvpMat4, "vc_mvp_mat4");
   //_constDescriptors.Register(ERenderShader_Fragment, EEffectConst_Fragment_Camera, "fc_camera");
   return resultCd;
}

//============================================================
// <T>绘制处理。</T>
//
// @param pRegion 渲染区域
// @param pRenderable 渲染对象
//============================================================
TResult FShadowDepthAutomaticEffect::DrawRenderable(FRenderRegion* pRegion, FRenderable* pRenderable){
   MO_ASSERT(pRegion);
   MO_ASSERT(pRenderable);
   FRenderView* pView = pRegion->ActiveView();
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
   modelViewMatrix.Append(pLightCamera->Matrix());
   // 计算MVP矩阵
   SMatrix3d matrixMvp;
   matrixMvp.Assign(modelMatrix);
   matrixMvp.Append(_vpMatrix);
   //............................................................
   // 设置常量
   BindConstMatrix4x4(EEffectConst_Vertex_ModelMat4, &modelViewMatrix);
   BindConstMatrix4x4(EEffectConst_Vertex_MvpMat4, &matrixMvp);
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
TResult FShadowDepthAutomaticEffect::DrawGroup(FRenderRegion* pRegion, TInt offset, TInt count){
   MO_CHECK(pRegion, return ENull);
   // 计算变换矩阵
   FRenderView* pView = pRegion->ActiveView();
   FDirectionalLight* pLight = pRegion->DirectionalLight();
   FCamera* pLightCamera = pLight->Camera();
   _vpMatrix.Assign(pLightCamera->Matrix());
   FProjection* pLightProjection = pLightCamera->Projection();
   _vpMatrix.Append(pLightProjection->Matrix());
   // 设置设备状态
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   pRenderDevice->SetProgram(_program);
   //............................................................
   TFloat lightZnear = pLightProjection->Znear();
   TFloat lightZfar = pLightProjection->Zfar();
   BindConstFloat4(EEffectConst_Fragment_Camera, 0.0f, 0.0f, lightZnear, 1.0f / lightZfar);
   //............................................................
   FAutomaticEffect::DrawGroup(pRegion, offset, count);
   //............................................................
   // 关闭程序
   pRenderDevice->SetProgram(NULL);
   return ESuccess;
}

MO_NAMESPACE_END
