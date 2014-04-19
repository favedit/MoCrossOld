#ifndef __MO_FR_CONTENT3D_TEMPLATE_H__
#define __MO_FR_CONTENT3D_TEMPLATE_H__
//************************************************************

#ifndef __MO_FR_COMMON_H__
#include "MoFrCommon.h"
#endif // __MO_FR_COMMON_H__

#ifndef __MO_FR_CONTENT3D_BASE_H__
#include "MoFrContent3dBase.h"
#endif // __MO_FR_CONTENT3D_BASE_H__

MO_NAMESPACE_BEGIN

//============================================================
class FRs3dModelVector;

//============================================================
// <T>资源3D模板渲染对象。</T>
//============================================================
class MO_FR_DECLARE FRs3dTemplateRenderable : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dTemplateRenderable, FInstance);
protected:
   // 模型名称
   TFsName _modelName;
   // 几何索引
   TInt _geometryIndex;
   // 材质名称
   TFsName _materialName;
   // 实体配置
   TBool _optionInstanced;
   // 实体个数
   TInt _instanceCount;
   // 动态配置
   TBool _optionDynamic;
   // 融合配置
   TBool _optionMerge;
   // 骨骼缩放配置
   TBool _optionBoneScale;
   // 选择配置
   TBool _optionSelect;
   // 可见配置
   TBool _optionVisible;
   // 地面配置
   TBool _optionGround;
   // 变换矩阵
   SFloatMatrix3d _matrix;
public:
   FRs3dTemplateRenderable();
   MO_ABSTRACT ~FRs3dTemplateRenderable();
public:
   //------------------------------------------------------------
   // <T>获得模型资源编号。</T>
   MO_INLINE TCharC* ModelName(){
      return _modelName;
   }
   //------------------------------------------------------------
   // <T>获得几何体索引。</T>
   MO_INLINE TInt GeometryIndex(){
      return _geometryIndex;
   }
   //------------------------------------------------------------
   // <T>获得材质资源编号。</T>
   MO_INLINE TCharC* MaterialName(){
      return _materialName;
   }
   //------------------------------------------------------------
   // <T>获得实体配置。</T>
   MO_INLINE TBool OptionInstanced(){
      return _optionInstanced;
   }
   //------------------------------------------------------------
   // <T>获得实体个数。</T>
   MO_INLINE TBool InstanceCount(){
      return _instanceCount;
   }
   //------------------------------------------------------------
   // <T>获得动态配置。</T>
   MO_INLINE TBool OptionDynamic(){
      return _optionDynamic;
   }
   //------------------------------------------------------------
   // <T>获得融合配置。</T>
   MO_INLINE TBool OptionMerge(){
      return _optionMerge;
   }
   //------------------------------------------------------------
   // <T>获得骨骼缩放配置。</T>
   MO_INLINE TBool OptionBoneScale(){
      return _optionBoneScale;
   }
   //------------------------------------------------------------
   // <T>获得选择配置。</T>
   MO_INLINE TBool OptionSelect(){
      return _optionSelect;
   }
   //------------------------------------------------------------
   // <T>获得可见配置。</T>
   MO_INLINE TBool OptionVisible(){
      return _optionVisible;
   }
   //------------------------------------------------------------
   // <T>获得地面配置。</T>
   MO_INLINE TBool OptionGround(){
      return _optionGround;
   }
   //------------------------------------------------------------
   // <T>获得变换矩阵。</T>
   MO_INLINE SFloatMatrix3d& Matrix(){
      return _matrix;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE FVector<FRs3dTemplateRenderable*> FRs3dTemplateRenderableCollection;

//============================================================
// <T>资源3D模板。</T>
//============================================================
class MO_FR_DECLARE FRs3dTemplate : public FResource3d{
protected:
   FRs3dModelCollection* _pModels;
   FRs3dTemplateRenderableCollection* _pRenderables;
public:
   FRs3dTemplate();
   MO_ABSTRACT ~FRs3dTemplate();
public:
   //------------------------------------------------------------
   // <T>获得模型集合。</T>
   MO_INLINE FRs3dModelCollection* Models(){
      return _pModels;
   }
   //------------------------------------------------------------
   // <T>获得渲染集合。</T>
   MO_INLINE FRs3dTemplateRenderableCollection* Renderables(){
      return _pRenderables;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
   void RenderableClear();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE FSet<TResourceId, FRs3dTemplate*> FRs3dTemplateSet;
typedef MO_FR_DECLARE FDictionary<FRs3dTemplate*> FRs3dTemplateDictionary;

//============================================================
// <T>资源3D模板管理器。</T>
//============================================================
class MO_FR_DECLARE FRs3dTemplateConsole : public FConsole{
protected:
   FRs3dTemplateDictionary* _pTemplates;
public:
   FRs3dTemplateConsole();
   MO_ABSTRACT ~FRs3dTemplateConsole();
public:
   //------------------------------------------------------------
   // <T>获得模型集合。</T>
   MO_INLINE FRs3dTemplateDictionary* Templates(){
      return _pTemplates;
   }
public:
   MO_ABSTRACT FRs3dTemplate* Load(TCharC* pName);
public:
   FRs3dTemplate* Find(TCharC* pName);
public:
   void Clear();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FR_CONTENT3D_TEMPLATE_H__
