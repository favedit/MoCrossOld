#ifndef __MO_ER_TECHNIQUE_H__
#define __MO_ER_TECHNIQUE_H__
//************************************************************

#ifndef __MO_ER_COMMON_H__
#include "MoErCommon.h"
#endif // __MO_ER_COMMON_H__

#ifndef __MO_ER_CORE_H__
#include "MoErCore.h"
#endif // __MO_ER_CORE_H__

#define MO_EG_EFFECT_CONST_MAXCNT     64
#define MO_EG_EFFECT_ATTRIBUTE_MAXCNT 32
#define MO_EG_EFFECT_SAMPLER_MAXCNT   16

MO_NAMESPACE_BEGIN

//============================================================
// <T>效果顶点常量。</T>
//============================================================
enum EEffectVertexConst{
   // 顶点常量
   EEffectConst_Vertex_Instance,
   EEffectConst_Vertex_ModelMatrix,
   EEffectConst_Vertex_ViewMatrix,
   EEffectConst_Vertex_ProjectionMatrix,
   EEffectConst_Vertex_ViewProjectionMatrix,
   EEffectConst_Vertex_ModelViewProjectionMatrix,
   EEffectConst_Vertex_BoneMatrix,
   EEffectConst_Vertex_CameraPosition,
   EEffectConst_Vertex_CameraDirection,
   EEffectConst_Vertex_LightPosition,
   EEffectConst_Vertex_LightDirection,
   // 像素常量
   EEffectConst_Fragment_CameraPosition,
   EEffectConst_Fragment_CameraDirection,
   EEffectConst_Fragment_LightPosition,
   EEffectConst_Fragment_LightDirection,
   // 材质常量
   EEffectConst_Fragment_ShadowMaterial,
   EEffectConst_Fragment_ShadowMaterialInv,
   EEffectConst_Fragment_Color,
   EEffectConst_Fragment_Alpha,
   EEffectConst_Fragment_AmbientColor,
   EEffectConst_Fragment_DiffuseColor,
   EEffectConst_Fragment_DiffuseViewColor,
   EEffectConst_Fragment_SpecularColor,
   EEffectConst_Fragment_Specular,
   EEffectConst_Fragment_SpecularViewColor,
   EEffectConst_Fragment_SpecularView,
   EEffectConst_Fragment_ReflectColor,

   EEffectConst_Vertex_ModelMat3,
   EEffectConst_Vertex_ModelMat4,
   EEffectConst_Vertex_ViewMat4,
   EEffectConst_Vertex_ProjectionMat4,
   EEffectConst_Vertex_MvpMat3,
   EEffectConst_Vertex_MvpMat4,
   EEffectConst_Vertex_LightModelMat4,
   EEffectConst_Vertex_LightViewMat4,
   EEffectConst_Vertex_LightProjectionMat4,
   EEffectConst_Vertex_LightMvMat4,
   EEffectConst_Vertex_LightMvpMat4,
   EEffectConst_Fragment_Camera,
   EEffectConst_Fragment_LightDepth,
   EEffectConst_Fragment_LightCamera,
   EEffectVertexConst_Count,
};

//============================================================
// <T>效果属性描述。</T>
//============================================================
struct SEffectAttributeDescriptor{
public:
   TInt code;
   TCharC* namePtr;
   ERenderVertexFormat formatCd;
   TInt bindIndex;
   TInt bindId;
public:
   SEffectAttributeDescriptor(){
      code = -1;
      MO_CLEAR(namePtr);
      formatCd = ERenderVertexFormat_Unknown;
      bindIndex = -1;
      bindId = -1;
   }
};

//============================================================
// <T>效果属性描述集合。</T>
//============================================================
class TEffectAttributeDescriptors : public TFixVector<SEffectAttributeDescriptor, MO_EG_EFFECT_ATTRIBUTE_MAXCNT>{
public:
   TEffectAttributeDescriptors();
public:
   void Register(TInt code, TCharC* pName, ERenderVertexFormat formatCd);
};

//============================================================
// <T>效果取样器描述。</T>
//============================================================
struct SEffectSamplerDescriptor{
public:
   TInt code;
   TCharC* namePtr;
   ERenderSampler samplerCd;
   TInt bindId;
   TInt index;
public:
   SEffectSamplerDescriptor(){
      code = -1;
      MO_CLEAR(namePtr);
      samplerCd = ERenderSampler_Unknown;
      bindId = -1;
      index = -1;
   }
};

//============================================================
// <T>效果取样器描述集合。</T>
//============================================================
class TEffectSamplerDescriptors : public TFixVector<SEffectSamplerDescriptor, MO_EG_EFFECT_SAMPLER_MAXCNT>{
public:
   TEffectSamplerDescriptors();
public:
   void Register(TInt code, TCharC* pName, ERenderSampler samplerCd);
   SEffectSamplerDescriptor* FindByBindId(TInt bindId);
};

//============================================================
// <T>渲染效果。</T>
//============================================================
class MO_ER_DECLARE FAutomaticEffect : public FEffect
{
   MO_CLASS_DECLARE_INHERITS(FAutomaticEffect, FEffect);
protected:
   GPtr<FXmlNode> _config;
   SMatrix3d _mvpMatrix;
   SMatrix3d _vpMatrix;
   SMatrix3d _lightVpMatrix;
   TEffectParameterDescriptors _parameterDescriptors;
   TEffectAttributeDescriptors _attributeDescriptors;
   TEffectSamplerDescriptors _samplerDescriptors;
   SEffectDescriptor _dynamicDescriptor;
public:
   FAutomaticEffect();
   MO_ABSTRACT ~FAutomaticEffect();
public:
   MO_OVERRIDE TResult BindDescriptors();
   MO_OVERRIDE TResult LinkDescriptors();
public:
   TResult BindConstPosition3(TInt bindCd, SFloatPoint3& point);
   TResult BindConstVector3(TInt bindCd, SFloatVector3& vector);
   TResult BindConstFloat4(TInt bindCd, TFloat x, TFloat y, TFloat z, TFloat w);
   TResult BindConstColor4(TInt bindCd, const SFloatColor4& color);
   TResult BindConstMatrix3x3(TInt bindCd, SFloatMatrix3d* pMatrix, TInt count = 1);
   TResult BindConstMatrix4x3(TInt bindCd, SFloatMatrix3d* pMatrix, TInt count = 1);
   TResult BindConstMatrix4x4(TInt bindCd, SFloatMatrix3d* pMatrix, TInt count = 1);
   TResult BindAttributeDescriptors(FRenderable* pRenderable);
   TResult UnbindAttributeDescriptors(FRenderable* pRenderable);
   TResult BindSampler(TInt bindCd, FRenderTexture* pTexture);
   TResult BindSamplerDescriptors(FRenderable* pRenderable);
public:
   MO_ABSTRACT TResult OnSetup();
   MO_OVERRIDE TResult Build();
public:
   MO_OVERRIDE TResult LoadConfig(FXmlNode* pConfig);
   MO_OVERRIDE TResult BuildDescripter(SRenderableDescriptor& renderableDescriptor);
   MO_OVERRIDE TResult BuildTemplate(SRenderableDescriptor& renderableDescriptor, MString* pCode, FTemplateContext* pTemplateContext);
public:
   MO_ABSTRACT TResult SetMaterialOption(FMaterial* pMaterial);
public:
   MO_ABSTRACT TResult DrawRenderable(FRenderRegion* pRegion, FRenderable* pRenderable);
   MO_ABSTRACT TResult DrawInstanceRenderable(FRenderRegion* pRegion, FInstanceRenderable* pInstanceRenderable, TInt offset, TInt count);
   MO_ABSTRACT TResult DrawGroup(FRenderRegion* pRegion, TInt offset, TInt count);
};

//============================================================
// <T>颜色自动渲染器。</T>
//============================================================
class MO_ER_DECLARE FColorAutomaticEffect : public FAutomaticEffect
{
   MO_CLASS_DECLARE_INHERITS(FColorAutomaticEffect, FAutomaticEffect);
protected:
   SFloatMatrix3d _modelMatrixs[MO_EG_CONST_MATRIX_MAX];
public:
   FColorAutomaticEffect();
   MO_ABSTRACT ~FColorAutomaticEffect();
public:
   MO_OVERRIDE TResult OnSetup();
public:
   MO_OVERRIDE TResult DrawRenderable(FRenderRegion* pRegion, FRenderable* pRenderable);
   MO_OVERRIDE TResult DrawInstanceRenderable(FRenderRegion* pRegion, FInstanceRenderable* pInstanceRenderable, TInt offset, TInt count);
   MO_OVERRIDE TResult DrawGroup(FRenderRegion* pRegion, TInt offset, TInt count);
};

//============================================================
// <T>深度颜色自动渲染器。</T>
//============================================================
class MO_ER_DECLARE FDepthAutomaticEffect : public FAutomaticEffect
{
   MO_CLASS_DECLARE_INHERITS(FDepthAutomaticEffect, FAutomaticEffect);
protected:
   SFloatMatrix3d _modelMatrixs[MO_EG_CONST_MATRIX_MAX];
public:
   FDepthAutomaticEffect();
   MO_ABSTRACT ~FDepthAutomaticEffect();
public:
   MO_OVERRIDE TResult OnSetup();
public:
   MO_OVERRIDE TResult DrawRenderable(FRenderRegion* pRegion, FRenderable* pRenderable);
   MO_OVERRIDE TResult DrawInstanceRenderable(FRenderRegion* pRegion, FInstanceRenderable* pInstanceRenderable, TInt offset, TInt count);
   MO_OVERRIDE TResult DrawGroup(FRenderRegion* pRegion, TInt offset, TInt count);
};

//============================================================
// <T>简单自动渲染器。</T>
//============================================================
class MO_ER_DECLARE FBlurEffect : public FAutomaticEffect
{
   MO_CLASS_DECLARE_INHERITS(FBlurEffect, FAutomaticEffect);
public:
   FBlurEffect();
   MO_ABSTRACT ~FBlurEffect();
public:
   MO_OVERRIDE TResult OnSetup();
public:
   MO_OVERRIDE TResult Draw(FRenderable* pRenderable);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_ER_TECHNIQUE_H__
