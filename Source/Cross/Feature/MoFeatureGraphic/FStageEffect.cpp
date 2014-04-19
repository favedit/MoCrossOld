#include "MoFgTechnique.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FStageEffect, FEffect);

//============================================================
// <T>构造场景渲染器。</T>
//============================================================
FStageEffect::FStageEffect(){
}

//============================================================
// <T>析构场景渲染器。</T>
//============================================================
FStageEffect::~FStageEffect(){
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FStageEffect::OnSetup(){
   return ESuccess;
}

//============================================================
// <T>变更大小。</T>
//
// @return 处理结果
//============================================================
TResult FStageEffect::Resize(TInt width, TInt height){
   return ESuccess;
}

//============================================================
// <T>绘制处理。</T>
//============================================================
TResult FStageEffect::Draw(FRenderable* pRenderable){
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
