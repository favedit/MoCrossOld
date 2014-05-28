#include "MoErTechnique.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FBlurEffect, FAutomaticEffect);

//============================================================
// <T>构造渲染效果。</T>
//============================================================
FBlurEffect::FBlurEffect(){
}

//============================================================
// <T>析构渲染效果。</T>
//============================================================
FBlurEffect::~FBlurEffect(){
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FBlurEffect::Setup(){
   // 注册属性集合
   //_attributeDescriptors.Register(ERenderVertexBuffer_Position, "va_position", ERenderVertexFormat_Float4);
   //_attributeDescriptors.Register(ERenderVertexBuffer_Color,    "va_color",    ERenderVertexFormat_ByteNormal4);
   // 注册纹理集合
   //_samplerDescriptors.Register(EEffectSampler_Diffuse, "fs_diffuse", ERenderSampler_PackDiffuse);
   return ESuccess;
}

//============================================================
// <T>绘制处理。</T>
//============================================================
TResult FBlurEffect::Draw(FRenderable* pRenderable){
   //MO_ASSERT(pRenderable);
   //SRenderableContent content;
   //pRenderable->FetchRenderableContent(content);
   //SFloatMatrix3d& renderableMatrix = pRenderable->Matrix();
   //// 获得设备
   //FRenderDevice* pRenderDevice = RDeviceManager::Instance().RenderDevice();
   ////............................................................
   //// 设定属性集合
   //BindAttributeDescriptors(pRenderable);
   //// 设置纹理集合
   //BindSamplerDescriptors(pRenderable);
   ////............................................................
   //// 设置索引流，绘制三角形
   //FRenderIndexBuffer* pIndexBuffer = content.indexBufferPtr;
   //pRenderDevice->DrawTriangles(pIndexBuffer, 0, pIndexBuffer->Count());
   return ESuccess;
}

MO_NAMESPACE_END
