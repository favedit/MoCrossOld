#ifndef __MO_ER_CORE_H__
#define __MO_ER_CORE_H__
//************************************************************

#ifndef __MO_ER_COMMON_H__
#include "MoErCommon.h"
#endif // __MO_ER_COMMON_H__

#define MO_ER_EFFECT_PARAMETER_MAXCNT 256
#define MO_ER_EFFECT_ATTRIBUTE_MAXCNT 32
#define MO_ER_EFFECT_SAMPLER_MAXCNT   32

MO_NAMESPACE_BEGIN

//============================================================
// <T>效果参数缓冲。</T>
//============================================================
enum EEffectParameterBuffer{
   EEffectParameterBuffer_Unknown,
   // 全局缓冲
   EEffectParameterBuffer_Global,
   // 效果缓冲
   EEffectParameterBuffer_EffectCamera,
   EEffectParameterBuffer_EffectLight,
   // 渲染缓冲
   EEffectParameterBuffer_RenderTramsform,
   EEffectParameterBuffer_RenderMaterial,
   EEffectParameterBuffer_Count,
};

//============================================================
// <T>效果参数工具。</T>
//============================================================
class MO_ER_DECLARE REffectParameterBuffer{
public:
   static TResult Parse(TCharC* pValue, EEffectParameterBuffer& bufferCd, TInt& slot);
   static TCharC* Format(EEffectParameterBuffer bufferCd);
};

//============================================================
// <T>效果参数。</T>
//============================================================
enum EEffectParameter{
   // 未知
   EEffectParameter_Unknown,
   // 变换参数
   EEffectParameter_VertexInstance,
   EEffectParameter_VertexModelMatrix3x3,
   EEffectParameter_VertexModelMatrix4x4,
   EEffectParameter_VertexViewMatrix4x4,
   EEffectParameter_VertexProjectionMatrix4x4,
   EEffectParameter_VertexViewProjectionMatrix4x4,
   EEffectParameter_VertexModelViewProjectionMatrix3x3,
   EEffectParameter_VertexModelViewProjectionMatrix4x4,
   EEffectParameter_VertexBoneMatrix4x3,
   EEffectParameter_VertexBoneMatrix4x4,
   // 相机参数
   EEffectParameter_VertexCameraPosition,
   EEffectParameter_VertexCameraDirection,
   // 光源参数
   EEffectParameter_VertexLightModelMatrix4x4,
   EEffectParameter_VertexLightModelViewMatrix4x4,
   EEffectParameter_VertexLightModelViewProjectionMatrix4x4,
   EEffectParameter_VertexLightPosition,
   EEffectParameter_VertexLightDirection,
   //............................................................
   // 相机参数
   EEffectParameter_FragmentCamera,
   EEffectParameter_FragmentCameraPosition,
   EEffectParameter_FragmentCameraDirection,
   // 光源参数
   EEffectParameter_FragmentLightModelMatrix4x4,
   EEffectParameter_FragmentLightModelViewProjectrionMatrix4x4,
   EEffectParameter_FragmentLightPosition,
   EEffectParameter_FragmentLightDirection,
   EEffectParameter_FragmentLightDepth,
   // 材质参数
   EEffectParameter_FragmentShadowMaterial,
   EEffectParameter_FragmentShadowMaterialInv,
   EEffectParameter_FragmentColor,
   EEffectParameter_FragmentAlpha,
   EEffectParameter_FragmentVertexColor,
   EEffectParameter_FragmentAmbientColor,
   EEffectParameter_FragmentDiffuseColor,
   EEffectParameter_FragmentDiffuseViewColor,
   EEffectParameter_FragmentSpecularColor,
   EEffectParameter_FragmentSpecular,
   EEffectParameter_FragmentSpecularViewColor,
   EEffectParameter_FragmentSpecularView,
   EEffectParameter_FragmentReflectColor,
   //............................................................
   EEffectParameter_Count,
};

//============================================================
// <T>效果参数工具。</T>
//============================================================
class MO_ER_DECLARE REffectParameter{
public:
   static TResult Parse(TCharC* pValue, EEffectParameter& parameterCd, ERenderShader& shaderCd, ERenderShaderParameterFormat& formatCd);
   static TCharC* Format(EEffectParameter parameterCd);
};

//============================================================
// <T>效果属性。</T>
//============================================================
enum EEffectAttribute{
   EEffectAttribute_Unknown,
   EEffectAttribute_Instance,
   EEffectAttribute_Position,
   EEffectAttribute_Color,
   EEffectAttribute_Coord,
   EEffectAttribute_CoordLight,
   EEffectAttribute_Normal,
   EEffectAttribute_Binormal,
   EEffectAttribute_Tangent,
   EEffectAttribute_BoneIndex,
   EEffectAttribute_BoneWeight,
   EEffectAttribute_Count,
};

//============================================================
// <T>效果属性工具。</T>
//============================================================
class MO_ER_DECLARE REffectAttribute{
public:
   static TResult Parse(TCharC* pValue, EEffectAttribute& attributeCd, ERenderShaderAttributeFormat& formatCd);
   static TCharC* Format(EEffectAttribute attributeCd);
};

//============================================================
// <T>效果取样器类型。</T>
//============================================================
enum EEffectSampler{
   EEffectSampler_Diffuse,
   EEffectSampler_Normal,
   EEffectSampler_Specular,
   EEffectSampler_Light,
   EEffectSampler_Environment,
   EEffectSampler_LightDepth,
   EEffectSampler_Count,
};

//============================================================
// <T>效果取样器类型工具。</T>
//============================================================
class MO_ER_DECLARE REffectSampler{
public:
   static EEffectSampler Parse(TCharC* pValue, EEffectSampler samplerCd = EEffectSampler_Diffuse);
   static TCharC* Format(EEffectSampler samplerCd);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_ER_CORE_H__
