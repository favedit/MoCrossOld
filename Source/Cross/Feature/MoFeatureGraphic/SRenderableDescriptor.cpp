#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造渲染标志。</T>
//============================================================
SRenderableDescriptor::SRenderableDescriptor(){
   Reset();
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult SRenderableDescriptor::Assign(SRenderableDescriptor* pFlags){
   MO_ASSERT(pFlags);
   // 设置配置
   //optionMaterial = pFlags->optionMaterial;
   //optionBinder = pFlags->optionBinder;
   //optionInstanced = pFlags->optionInstanced;
   //optionBoneScale = pFlags->optionBoneScale;
   //optionNormalScale = pFlags->optionNormalScale;
   //optionNormalFull = pFlags->optionNormalFull;
   //optionDepth = pFlags->optionDepth;
   //optionShadowSelf = pFlags->optionShadowSelf;
   //optionLightMap = pFlags->optionLightMap;
   //optionTransmittance = pFlags->optionTransmittance;
   // 设置顶点缓冲信息
   //MO_LIB_MEMORY_COPY(pFlags->vertexBuffers, sizeof(pFlags->vertexBuffers), vertexBuffers, sizeof(vertexBuffers));
   // 设置取样器信息
   //MO_LIB_MEMORY_COPY(pFlags->samplers, sizeof(pFlags->samplers), samplers, sizeof(samplers));
   return ESuccess;
}

//============================================================
// <T>重置处理。</T>
//
// @return 处理结果
//============================================================
TResult SRenderableDescriptor::Reset(){
   // 重置所有配置
   optionInstanced = EFalse;
   optionBinder = EFalse;
   optionMaterial = EFalse;
   //optionNormal = EFalse;
   //optionNormalFull = EFalse;
   //optionNormalScale = EFalse;
   //optionBoneScale = EFalse;
   //optionDepth = EFalse;
   optionShadow = EFalse;
   optionShadowSelf = EFalse;
   //optionLightMap = EFalse;
   //optionTransmittance = EFalse;
   // 重置所有支持
   supportVertexColor = EFalse;
   supportVertexCoord = EFalse;
   supportVertexNormal = EFalse;
   supportVertexNormalFull = EFalse;
   supportVertexBone = EFalse;
   // 设置顶点个数
   vertexCount = 0;
   // 设置骨头个数
   boneCount = 0;
   return ESuccess;
}

MO_NAMESPACE_END
