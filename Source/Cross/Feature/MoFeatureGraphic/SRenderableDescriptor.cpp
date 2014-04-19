#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造渲染标志。</T>
//============================================================
SRenderableDescriptor::SRenderableDescriptor(){
   setuped = EFalse;
   Reset();
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
void SRenderableDescriptor::Assign(SRenderableDescriptor* pFlags){
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
   MO_LIB_MEMORY_COPY(pFlags->vertexBuffers, sizeof(pFlags->vertexBuffers), vertexBuffers, sizeof(vertexBuffers));
   // 设置取样器信息
   MO_LIB_MEMORY_COPY(pFlags->samplers, sizeof(pFlags->samplers), samplers, sizeof(samplers));
}

//============================================================
// <T>建立处理。</T>
//============================================================
void SRenderableDescriptor::Build(){
   // 支持顶点颜色技术
   if(vertexBuffers[ERenderVertexBuffer_Color]){
      supportVertexColor = ETrue;
   }
   // 支持纹理坐标技术
   if(vertexBuffers[ERenderVertexBuffer_Coord]){
      supportVertexCoord = ETrue;
   }
   // 支持法线技术
   if(vertexBuffers[ERenderVertexBuffer_Normal]){
      supportVertexNormal = ETrue;
   }
   // 支持全法线技术
   if(vertexBuffers[ERenderVertexBuffer_Normal] && vertexBuffers[ERenderVertexBuffer_Binormal] && vertexBuffers[ERenderVertexBuffer_Tangent]){
      supportVertexNormalFull = ETrue;
   }
   // 支持骨头技术
   if(vertexBuffers[ERenderVertexBuffer_BoneIndex] && vertexBuffers[ERenderVertexBuffer_BoneWeight]){
      supportVertexBone = ETrue;
   }
   //............................................................
   // 支持环境光技术
   supportAmbient = ETrue;
   // 支持漫反射技术
   if(supportVertexCoord && samplers[ERenderSampler_Diffuse]){
      supportDiffuse = ETrue;
   }
   // 支持法线技术
   if(supportVertexNormal){
      supportNormal = ETrue;
   }
   // 支持凹凸技术
   if(supportVertexCoord && supportVertexNormalFull && samplers[ERenderSampler_Normal]){
      supportBump = ETrue;
   }
   // 支持高光颜色技术
   if(supportVertexCoord && supportVertexNormal & samplers[ERenderSampler_SpecularColor]){
      supportSpecularColor = ETrue;
   }
   // 支持高光级别技术
   if(supportVertexCoord && supportVertexNormal & samplers[ERenderSampler_SpecularLevel]){
      supportSpecularLevel = ETrue;
   }
   // 支持环境光技术
   if(supportVertexNormal & samplers[ERenderSampler_Environment]){
      supportEnvironment = ETrue;
   }
   // 支持受光技术
   if(supportVertexCoord && samplers[ERenderSampler_Light]){
      supportLight = ETrue;
   }
   // 支持反射技术
   if(supportVertexCoord && samplers[ERenderSampler_Reflect]){
      supportReflect = ETrue;
   }
   // 支持折射技术
   if(supportVertexCoord && samplers[ERenderSampler_Refract]){
      supportRefract = ETrue;
   }
   // 支持发光技术
   if(supportVertexCoord && samplers[ERenderSampler_Emissive]){
      supportEmissive = ETrue;
   }
   // 支持高度技术
   if(supportVertexCoord && samplers[ERenderSampler_Height]){
      supportHeight = ETrue;
   }
   // 建立成功
   setuped = ETrue;
}

//============================================================
// <T>重置处理。</T>
//============================================================
void SRenderableDescriptor::Reset(){
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
   // 重置材质支持
   supportAlpha = EFalse;
   supportBump = EFalse;
   supportAmbient = EFalse;
   supportDiffuse = EFalse;
   supportNormal = EFalse;
   supportSpecularColor = EFalse;
   supportSpecularLevel = EFalse;
   supportReflect = EFalse;
   supportEnvironment = EFalse;
   supportLight = EFalse;
   supportReflect = EFalse;
   supportRefract = EFalse;
   supportEmissive = EFalse;
   supportHeight = EFalse;
   //supportDiffuseCamera = EFalse;
   //supportBumpCamera = EFalse;
   //supportSpecularCamera = EFalse;
   //supportSpecularCameraLevel = EFalse;
   // 设置顶点个数
   vertexCount = 0;
   // 设置骨头个数
   boneCount = 0;
   // 设置顶点缓冲信息
   RBools::Fill(vertexBuffers, ERenderVertexBuffer_Count, EFalse);
   // 设置取样器信息
   RBools::Fill(samplers, ERenderSampler_Count, EFalse);
}

MO_NAMESPACE_END
