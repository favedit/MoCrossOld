#ifndef __MO_FG_TECHNIQUE_H__
#define __MO_FG_TECHNIQUE_H__
//************************************************************

#ifndef __MO_FG_COMMON_H__
#include "MoFgCommon.h"
#endif // __MO_FG_COMMON_H__

#ifndef __MO_FG_BASE_H__
#include "MoFgBase.h"
#endif // __MO_FG_BASE_H__

#ifndef __MO_FG_RENDER_H__
#include "MoFgRender.h"
#endif // __MO_FG_RENDER_H__

#define MO_EG_EFFECT_CONST_MAXCNT     64
#define MO_EG_EFFECT_ATTRIBUTE_MAXCNT 32
#define MO_EG_EFFECT_SAMPLER_MAXCNT   16

MO_NAMESPACE_BEGIN

//============================================================
// <T>渲染描述信息。</T>
//============================================================
class MO_FG_DECLARE SEffectDescriptor{
public:
   // 名称
   TCharC* namePtr;
public:
   ERenderFillMode fillModeCd;
   TBool optionCullMode;
   ERenderCullMode cullModeCd;
   TBool optionDepthTest;
   ERenderDepthMode depthModeCd;
   TBool optionDepthWrite;
   TBool optionBlendMode;
   ERenderBlendMode blendSourceMode;
   ERenderBlendMode blendTargetMode;
   TBool optionAlphaTest;
public:
   // 支持实例绘制
   TBool supportInstance;
   // 支持顶点颜色
   TBool supportVertexColor;
   // 支持顶点纹理
   TBool supportVertexCoord;
   // 支持法线
   TBool supportVertexNormal;
   // 支持全法线
   TBool supportVertexNormalFull;
   // 支持骨骼技术
   TBool supportSkeleton;
public:
   // 支持透明技术
   TBool supportAlpha;
   // 支持凹凸技术
   TBool supportBump;
   // 支持环境色技术
   TBool supportAmbient;
   // 支持散射技术
   TBool supportDiffuse;
   // 支持环境散射技术
   TBool supportDiffuseView;
   // 支持高光颜色技术
   TBool supportSpecularColor;
   // 支持高光级别技术
   TBool supportSpecularLevel;
   // 支持高光相机技术
   TBool supportSpecularView;
   // 支持环境技术
   TBool supportEnvironment;
   // 支持收光技术
   TBool supportLight;
   // 支持反射技术
   TBool supportReflect;
   // 支持折射技术
   TBool supportRefract;
   // 支持发光技术
   TBool supportEmissive;
   // 支持高度技术
   TBool supportHeight;
public:
   SEffectDescriptor();
public:
   TResult LoadConfig(FXmlNode* pConfig);
};

//============================================================
// <T>渲染效果环境。</T>
//============================================================
class MO_FG_DECLARE FEffectContext : public FObject{
public:
   FEffectContext();
   MO_ABSTRACT ~FEffectContext();
};

//============================================================
// <T>渲染效果。</T>
//============================================================
class MO_FG_DECLARE FEffect : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FEffect, FInstance);
protected:
   TString _name;
   TString _flag;
   TString _vertexSource;
   TString _fragmentSource;
   SEffectDescriptor _descriptor;
   GPtr<FRenderProgram> _program;
   GPtr<FRenderDevice> _renderDevice;
public:
   FEffect();
   MO_ABSTRACT ~FEffect();
public:
   //------------------------------------------------------------
   // <T>判断是否指定名称。</T>
   MO_INLINE TBool IsName(TCharC* pName){
      return _name.Equals(pName);
   }
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>获得标志。</T>
   MO_INLINE TCharC* Flag(){
      return _flag;
   }
   //------------------------------------------------------------
   // <T>设置标志。</T>
   MO_INLINE void SetFlag(TCharC* pFlag){
      _flag = pFlag;
   }
   //------------------------------------------------------------
   // <T>获得顶点代码。</T>
   MO_INLINE TCharC* VertexSource(){
      return _vertexSource;
   }
   //------------------------------------------------------------
   // <T>设置顶点代码。</T>
   MO_INLINE void SetVertexSource(TCharC* pSource){
      _vertexSource = pSource;
   }
   //------------------------------------------------------------
   // <T>获得像素代码。</T>
   MO_INLINE TCharC* FragmentSource(){
      return _fragmentSource;
   }
   //------------------------------------------------------------
   // <T>设置像素代码。</T>
   MO_INLINE void SetFragmentSource(TCharC* pSource){
      _fragmentSource = pSource;
   }
   //------------------------------------------------------------
   // <T>获得渲染描述。</T>
   MO_INLINE SEffectDescriptor& Descriptor(){
      return _descriptor;
   }
   //------------------------------------------------------------
   // <T>获得程序。</T>
   MO_INLINE FRenderProgram* Program(){
      return _program;
   }
   //------------------------------------------------------------
   // <T>获得渲染设备。</T>
   MO_INLINE FRenderDevice* RenderDevice(){
      return _renderDevice;
   }
   //------------------------------------------------------------
   // <T>设置渲染设备。</T>
   MO_INLINE void SetRenderDevice(FRenderDevice* pRenderDevice){
      _renderDevice = pRenderDevice;
   }
public:
   MO_ABSTRACT TResult Setup();
   MO_ABSTRACT TResult Build();
public:
   MO_ABSTRACT TResult LoadConfig(FXmlNode* pConfig);
   MO_ABSTRACT TResult BuildDescripter(SRenderableDescriptor& renderableDescriptor);
   MO_ABSTRACT TResult BuildTemplate(SRenderableDescriptor& renderableDescriptor, MString* pCode, FTemplateContext* pTemplateContext);
public:
   MO_ABSTRACT TResult Resize(TInt width, TInt height);
   MO_ABSTRACT TResult UpdateBegin();
   MO_ABSTRACT TResult WriteRenderable(FRenderable* pRenderable);
   MO_ABSTRACT TResult UpdateEnd();
   MO_ABSTRACT TResult DrawRenderable(FRenderRegion* pRegion, FRenderable* pRenderable);
   MO_ABSTRACT TResult DrawGroup(FRenderRegion* pRegion, TInt offset, TInt count);
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FDictionary<FEffect*> FEffectDictionary;
typedef MO_FG_DECLARE GPtrDictionary<FEffect> GEffectPtrDictionary;

//============================================================
// <T>场景渲染器。</T>
//============================================================
class MO_FG_DECLARE FStageEffect : public FEffect
{
   MO_CLASS_DECLARE_INHERITS(FStageEffect, FEffect);
public:
   FStageEffect();
   MO_ABSTRACT ~FStageEffect();
public:
   MO_OVERRIDE TResult OnSetup();
public:
   MO_OVERRIDE TResult Resize(TInt width, TInt height);
   MO_OVERRIDE TResult Draw(FRenderable* pRenderable);
};

//============================================================
// <T>渲染效果控制台。</T>
//============================================================
class MO_FG_DECLARE FEffectConsole : public FConsole{
protected:
   FClassFactory* _pFactory;
   GEffectPtrDictionary _effects;
   GEffectPtrDictionary _templateEffects;
public:
   FEffectConsole();
   MO_ABSTRACT ~FEffectConsole();
public:
   //------------------------------------------------------------
   // <T>获得类工厂。</T>
   MO_INLINE FClassFactory* Factory(){
      return _pFactory;
   }
   //------------------------------------------------------------
   // <T>获得效果字典。</T>
   MO_INLINE GEffectPtrDictionary& Effects(){
      return _effects;
   }
protected:
public:
   FEffect* Build(TCharC* pName, FRenderable* pRenderable = NULL);
   FEffect* FindTemplate(TCharC* pName);
   FEffect* Find(TCharC* pName, FRenderable* pRenderable = NULL);
public:
   //------------------------------------------------------------
   // <T>根据名称和渲染信息获得一个效果器。</T>
   template <class T>
   T* Find(TCharC* pName, FRenderable* pRenderable = NULL){
      FEffect* pEffect = Find(pName, pRenderable);
      return pEffect->Convert<T>();
   }
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
};

//============================================================
// <T>渲染效果管理器。</T>
//============================================================
class MO_FG_DECLARE REffectManager : public RSingleton<FEffectConsole>{
};

//============================================================
// <T>渲染技术过程。</T>
//============================================================
class MO_FG_DECLARE FTechniquePass : public FObject{
public:
   FTechniquePass();
   MO_ABSTRACT ~FTechniquePass();
};

//============================================================
// <T>渲染技术环境。</T>
//============================================================
class MO_FG_DECLARE FTechniqueContext : public FObject{
public:
   FTechniqueContext();
   MO_ABSTRACT ~FTechniqueContext();
};

//============================================================
// <T>渲染技术。</T>
//============================================================
class MO_FG_DECLARE FTechnique : public FObject{
protected:
   TString _name;
   FEffectCollection* _pEffects;
   FRenderProgram* _pProgram;
public:
   FTechnique();
   MO_ABSTRACT ~FTechnique();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>获得程序。</T>
   MO_INLINE FRenderProgram* Program(){
      return _pProgram;
   }
public:
   //MO_ABSTRACT FEffect* FindFEffect(ERenderEffect typeCd);
public:
   MO_ABSTRACT TResult Setup();
public:
   MO_ABSTRACT TResult Resize(TInt width, TInt height);
   MO_ABSTRACT TResult Draw(FRenderRegion* pRegion, TInt offset, TInt count);
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FTechnique*> FTechniqueCollection;
typedef MO_FG_DECLARE FList<FTechnique*> FTechniqueList;

//============================================================
// <T>渲染技术能力。</T>
//============================================================
struct STechniqueCapability{
public:
   TBool optionInstance;
   TBool optionMerge;
public:
   //------------------------------------------------------------
   // <T>构造渲染技术能力。</T>
   STechniqueCapability(){
      optionInstance = EFalse;
      optionMerge = EFalse;
   }
};

//============================================================
// <T>渲染技术控制台。</T>
//============================================================
class MO_FG_DECLARE FTechniqueConsole : public FConsole{
protected:
   STechniqueCapability _capability;
   FTechniqueCollection* _pTechniques;
public:
   FTechniqueConsole();
   MO_ABSTRACT ~FTechniqueConsole();
public:
   //------------------------------------------------------------
   // <T>获得渲染技术能力。</T>
   MO_INLINE STechniqueCapability& Capability(){
      return _capability;
   }
   //------------------------------------------------------------
   // <T>获得技术集合。</T>
   MO_INLINE FTechniqueCollection* Techniques(){
      return _pTechniques;
   }
public:
   MO_ABSTRACT void Setup();
};

//============================================================
// <T>渲染技术管理器。</T>
//============================================================
class MO_FG_DECLARE RTechniqueManager : public RSingleton<FTechniqueConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FG_TECHNIQUE_H__
