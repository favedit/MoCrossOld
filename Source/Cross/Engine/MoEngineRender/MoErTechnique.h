#ifndef __MO_ER_TECHNIQUE_H__
#define __MO_ER_TECHNIQUE_H__
//************************************************************

#ifndef __MO_ER_COMMON_H__
#include "MoErCommon.h"
#endif // __MO_ER_COMMON_H__

#ifndef __MO_ER_CORE_H__
#include "MoErCore.h"
#endif // __MO_ER_CORE_H__

MO_NAMESPACE_BEGIN

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
   FRenderShaderParameterCollection* _pParameters;
   FRenderShaderAttributeCollection* _pAttributes;
   FRenderShaderSamplerCollection* _pSamplers;
   SEffectDescriptor _dynamicDescriptor;
public:
   FAutomaticEffect();
   MO_ABSTRACT ~FAutomaticEffect();
public:
   //------------------------------------------------------------
   // <T>获得参数集合。</T>
   MO_INLINE FRenderShaderParameterCollection* Parameters(){
      return _pParameters;
   }
   //------------------------------------------------------------
   // <T>获得属性集合。</T>
   MO_INLINE FRenderShaderAttributeCollection* Attributes(){
      return _pAttributes;
   }
   //------------------------------------------------------------
   // <T>获得取样器集合。</T>
   MO_INLINE FRenderShaderSamplerCollection* Samplers(){
      return _pSamplers;
   }
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
