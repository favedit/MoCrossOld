#ifndef __MO_FG_LIGHT_H__
#define __MO_FG_LIGHT_H__
//************************************************************

#ifndef __MO_FG_COMMON_H__
#include "MoFgCommon.h"
#endif // __MO_FG_COMMON_H__

#ifndef __MO_FG_CAMERA_H__
#include "MoFgCamera.h"
#endif // __MO_FG_CAMERA_H__

#ifndef __MO_FG_MATERIAL_H__
#include "MoFgMaterial.h"
#endif // __MO_FG_MATERIAL_H__

MO_NAMESPACE_BEGIN

//============================================================
class FLight;

//============================================================
// <T>光源阴影信息。</T>
//============================================================
struct MO_FG_DECLARE SLightShadow{
public:
   // 基础
   TFloat _base;
   // 比率
   TFloat _rate;
   // 层级
   TFloat _level;
   // 范围
   TFloat _range;
public:
   //------------------------------------------------------------
   // <T>构造光源阴影信息。</T>
   SLightShadow(){
      _base = 0.00000005f;
      _rate = 0.5f;
      _level = 0.000004f;
      _range = 0.0f;
   }
public:
   //------------------------------------------------------------
   // <T>从输入流中反序列化信息。</T>
   void Unserialize(IDataInput* pInput){
      _base = pInput->ReadFloat();
      _rate = pInput->ReadFloat();
      _level = pInput->ReadFloat();
      _range = pInput->ReadFloat();
   }
};

//============================================================
// <T>光源。</T>
//============================================================
class MO_FG_DECLARE FLight : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FLight, FInstance);
protected:
   // 中心点
   SFloatPoint3 _center;
   // 类型
   //TInt _typeCd;
   // 设置跟踪
   //TBool _optionTrack;
   // 阴影信息1
   //SLightShadow _shadow1;
   // 阴影信息2
   //SLightShadow _shadow2;
   // 阴影信息3
   //SLightShadow _shadow3;
   // 阴影环境最小
   //TFloat shadowAmbientMin;
   // 阴影环境最大
   //TFloat shadowAmbientMax;
   // 阴影环境厚度
   //TFloat shadowAmbientThick;
   // 阴影环境范围
   //TFloat shadowAmbientRange;
   // 阴影融合1基础
   //TFloat shadowMerge1Base;
   // 阴影融合1比率
   //TFloat shadowMerge1Rate;
   // 阴影融合2基础
   //TFloat shadowMerge2Base;
   // 阴影融合2基础
   //TFloat shadowMerge2Rate;
   // 材质信息
   // TFloat material:FGeLightMaterial = new FGeLightMaterial(); 
public:
   FLight();
   MO_ABSTRACT ~FLight();
public:
   //------------------------------------------------------------
   // <T>获得中心点。</T>
   MO_INLINE SFloatPoint3& Center(){
      return _center;
   }
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FLight*> FLightCollection;
typedef MO_FG_DECLARE FList<FLight*> FLightList;

//============================================================
// <T>方向光源。</T>
//============================================================
class MO_FG_DECLARE FDirectionalLight : public FLight
{
   MO_CLASS_DECLARE_INHERITS(FDirectionalLight, FLight);
protected:
   GPtr<FCamera> _camera;
   GPtr<FViewport> _viewport;
   SFloatVector3 _direction;
   GPtr<FMaterial> _material;
public:
   FDirectionalLight();
   MO_ABSTRACT ~FDirectionalLight();
public:
   //------------------------------------------------------------
   // <T>获得相机。</T>
   MO_INLINE FCamera* Camera(){
      return _camera;
   }
   //------------------------------------------------------------
   // <T>设置相机。</T>
   MO_INLINE void SetCamera(FCamera* pCamera){
      _camera = pCamera;
   }
   //------------------------------------------------------------
   // <T>获得视角。</T>
   MO_INLINE FViewport* Viewport(){
      return _viewport;
   }
   //------------------------------------------------------------
   // <T>设置视角。</T>
   MO_INLINE void SetViewport(FViewport* pViewport){
      _viewport = pViewport;
   }
   //------------------------------------------------------------
   // <T>获得方向光源。</T>
   MO_INLINE SFloatVector3& Direction(){
      return _direction;
   }
   //------------------------------------------------------------
   // <T>获得材质。</T>
   MO_INLINE FMaterial* Material(){
      return _material;
   }
   //------------------------------------------------------------
   // <T>设置材质。</T>
   MO_INLINE void SetMaterial(FMaterial* pMaterial){
      _material = pMaterial;
   }
};

//============================================================
// <T>点光源。</T>
//============================================================
class MO_FG_DECLARE FPointLight : public FLight
{
   MO_CLASS_DECLARE_INHERITS(FPointLight, FLight);
public:
   FPointLight();
   MO_ABSTRACT ~FPointLight();
};

//============================================================
// <T>聚焦光源。</T>
//============================================================
class MO_FG_DECLARE FSpotLight : public FLight
{
   MO_CLASS_DECLARE_INHERITS(FSpotLight, FLight);
public:
   FSpotLight();
   MO_ABSTRACT ~FSpotLight();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FG_LIGHT_H__
