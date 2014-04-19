#ifndef __MO_EG_CONTENT3D_SCENE_H__
#define __MO_EG_CONTENT3D_SCENE_H__
//************************************************************

#ifndef __MO_FR_COMMON_H__
#include "MoFrCommon.h"
#endif // __MO_FR_COMMON_H__

#ifndef __MO_FR_CONTENT3D_BASE_H__
#include "MoFrContent3dBase.h"
#endif // __MO_FR_CONTENT3D_BASE_H__

MO_NAMESPACE_BEGIN

//============================================================

//============================================================
// <T>场景技术过程。</T>
//============================================================
class MO_FR_DECLARE FRs3dScenePass : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dScenePass, FInstance);
protected:
   // 名称
   TString _name;
   // 渲染目标尺寸
   SIntSize2 _renderTargetSize;
public:
   FRs3dScenePass();
   MO_ABSTRACT ~FRs3dScenePass();
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtrs<FRs3dScenePass> GRs3dScenePassPtrs;

//============================================================
// <T>场景技术资源。</T>
//============================================================
class MO_FR_DECLARE FRs3dSceneTechnique : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dSceneTechnique, FInstance);
protected:
   // 名称
   TString _name;
   // 过程集合
   GRs3dScenePassPtrs _passes;
public:
   FRs3dSceneTechnique();
   MO_ABSTRACT ~FRs3dSceneTechnique();
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>场景视角资源。</T>
//============================================================
class MO_FR_DECLARE FRs3dSceneViewport : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dSceneViewport, FInstance);
protected:
   // 夹角
   TFloat _angle;
   // 近平面
   TFloat _near;
   // 远平面
   TFloat _far;
public:
   FRs3dSceneViewport();
   MO_ABSTRACT ~FRs3dSceneViewport();
public:
   //------------------------------------------------------------
   // <T>获得夹角。</T>
   MO_INLINE TFloat Angle(){
      return _angle;
   }
   //------------------------------------------------------------
   // <T>获得近平面。</T>
   MO_INLINE TFloat Near(){
      return _near;
   }
   //------------------------------------------------------------
   // <T>获得远平面。</T>
   MO_INLINE TFloat Far(){
      return _far;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>场景相机资源。</T>
//============================================================
class MO_FR_DECLARE FRs3dSceneCamera : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dSceneCamera, FInstance);
protected:
   // 类型名称
   TFsName _typeName;
   // 中心前距离
   TFloat _centerFront;
   // 中心后距离
   TFloat _centerBack;
   // 位置
   SFloatPoint3 _position;
   // 方向
   SFloatVector3 _direction;
   // 近焦平面
   TFloat _focalNear;
   // 远焦平面
   TFloat _focalFar;
   // 视角
   GPtr<FRs3dSceneViewport> _viewport;
public:
   FRs3dSceneCamera();
   MO_ABSTRACT ~FRs3dSceneCamera();
public:
   //------------------------------------------------------------
   // <T>获得类型名称。</T>
   MO_INLINE TCharC* TypeName(){
      return _typeName;
   }
   //------------------------------------------------------------
   // <T>获得位置。</T>
   MO_INLINE SFloatPoint3& Position(){
      return _position;
   }
   //------------------------------------------------------------
   // <T>获得方向。</T>
   MO_INLINE SFloatVector3& Direction(){
      return _direction;
   }
   //------------------------------------------------------------
   // <T>获得近焦平面。</T>
   MO_INLINE TFloat FocalNear(){
      return _focalNear;
   }
   //------------------------------------------------------------
   // <T>获得远焦平面。</T>
   MO_INLINE TFloat FocalFar(){
      return _focalFar;
   }
   //------------------------------------------------------------
   // <T>获得视角。</T>
   MO_INLINE FRs3dSceneViewport* Viewport(){
      return _viewport;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>场景材质资源。</T>
//============================================================
class MO_FR_DECLARE FRs3dSceneMaterial : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dSceneMaterial, FInstance);
protected:
   // 名称
   TString _name;
   // 类型名称
   TString _label;
   // 类型名称
   TString _typeName;
   // 材质信息
   SMaterialInfo _info;
   // 时间间隔
   TInt _interval;
   // 旋转角度
   SFloatVector3 _rotation;
   // 主题代码
   //public var themeCode:String;
   // 高度设置
   SFloatColor4 _height;
   // 表面比率
   TFloat _surfaceRate;
   // 表面反射
   TFloat _surfaceReflect;
   // 表面亮度
   TFloat _surfaceBright;
   // 表面亮度级别
   TFloat _surfaceBrightLevel;
   // 表面粗糙
   TFloat _surfaceCoarse;
   // 表面粗糙级别
   TFloat _surfaceCoarseLevel;
   // 表面融合
   TFloat _surfaceMerge;
   // 表面强度
   TFloat _surfacePower;
   //// 阴影材质
   //public var shadowMaterial:SFloatColor4 = new SFloatColor4(1.0, 1.0, 1.0, 1.0);
   //// 阴影反材质
   //public var shadowMaterialInv:SFloatColor4 = new SFloatColor4(1.0, 1.0, 1.0, 1.0);
   ////............................................................
   //// 泛光开关
   //public var bloomEnable:Boolean;
   //// 泛光设置
   //public var bloom:SFloatColor4 = new SFloatColor4(1.0, 1.0, 0.0, 0.0);
   //// 柔光开关
   //public var glossinessEnable:Boolean;
   //// 柔光设置
   //public var glossiness:SFloatColor4 = new SFloatColor4(1.0, 0.0, 0.0, 0.0);
   //// 分层设置
   //public var layer:SFloatColor4 = new SFloatColor4(0, 0, 0, 0);
   //// 可见性
   //public var visible:SFloatColor4 = new SFloatColor4(1.0, 1.0, 1.0, 1.0);
   //// 阴影1
   //public var shadow1:SFloatColor4 = new SFloatColor4(1.0, 1.0, 1.0, 1.0);
   //// 阴影2
   //public var shadow2:SFloatColor4 = new SFloatColor4(1.0, 1.0, 1.0, 1.0);
   //// 阴影3
   //public var shadow3:SFloatColor4 = new SFloatColor4(1.0, 1.0, 1.0, 1.0);
   //// 阴影纹理
   //public var shadowCoord:SFloatColor4 = new SFloatColor4(1.0, 1.0, 1.0, 1.0);
   //// 特性标志
   //public var identityFlag:String;
   //// 特性码
   //public var identityCode:String;
public:
   FRs3dSceneMaterial();
   MO_ABSTRACT ~FRs3dSceneMaterial();
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
   // <T>获得材质信息。</T>
   MO_INLINE SMaterialInfo& Info(){
      return _info;
   }
public:
   //------------------------------------------------------------
   // <T>设置材质高度信息。</T>
   void SetHeightDepth(TFloat value){
      _height.red = value;
      _height.green = value / 5.0f; // EGeEffect.HeightStep;
      _height.blue = 1.0f / 5.0f; // EGeEffect.HeightStep; // (移动次数)
      _height.alpha = value * 2.0f; // (最大偏移)
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtrs<FRs3dSceneMaterial> GRs3dSceneMaterialPtrs;

//============================================================
// <T>场景光源资源。</T>
//============================================================
class MO_FR_DECLARE FRs3dSceneLight : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dSceneLight, FInstance);
protected:
   // 类型
   TFsName _typeName;
   // 配置跟踪
   TBool _optionTrack;
   // 阴影1
   SShadowInfo _shadow1;
   // 阴影2
   SShadowInfo _shadow2;
   // 阴影3
   SShadowInfo _shadow3;
   // 阴影环境最小
   TFloat _shadowAmbientMin;
   // 阴影环境最大
   TFloat _shadowAmbientMax;
   // 阴影环境厚度
   TFloat _shadowAmbientThick;
   // 阴影环境范围
   TFloat _shadowAmbientRange;
   // 阴影融合1基础
   TFloat _shadowMerge1Base;
   // 阴影融合1比率
   TFloat _shadowMerge1Rate;
   // 阴影融合2基础
   TFloat _shadowMerge2Base;
   // 阴影融合2基础
   TFloat _shadowMerge2Rate;
   // 场景材质
   GPtr<FRs3dSceneMaterial> _material;
   // 场景相机
   GPtr<FRs3dSceneCamera> _camera;
public:
   FRs3dSceneLight();
   MO_ABSTRACT ~FRs3dSceneLight();
public:
   //------------------------------------------------------------
   // <T>获得场景材质。</T>
   MO_INLINE FRs3dSceneMaterial* Material(){
      return _material;
   }
   //------------------------------------------------------------
   // <T>获得场景相机。</T>
   MO_INLINE FRs3dSceneCamera* Camera(){
      return _camera;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>场景技术资源。</T>
//============================================================
class MO_FR_DECLARE FRs3dSceneRegion : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dSceneRegion, FInstance);
protected:
   // 颜色
   SFloatColor4 _color;
   // 颜色级别
   SFloatColor4 _colorLevel;
   // 雾化信息
   SFogInfo _fog;
   // 边界信息
   SEdgeInfo _edge;
   // 平面信息
   SFaceInfo _face;
   // 相机
   GPtr<FRs3dSceneCamera> _camera;
   // 光源
   GPtr<FRs3dSceneLight> _light;
public:
   FRs3dSceneRegion();
   MO_ABSTRACT ~FRs3dSceneRegion();
public:
   //------------------------------------------------------------
   // <T>获得颜色。</T>
   MO_INLINE SFloatColor4& Color(){
      return _color;
   }
   //------------------------------------------------------------
   // <T>获得相机。</T>
   MO_INLINE FRs3dSceneCamera* Camera(){
      return _camera;
   }
   //------------------------------------------------------------
   // <T>获得光源。</T>
   MO_INLINE FRs3dSceneLight* Light(){
      return _light;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>资源场景动画。</T>
//============================================================
class MO_FR_DECLARE FRs3dSceneMovie : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dSceneMovie, FInstance);
protected:
   // 类型名称
   TString _typeName;
   // 时间间隔
   TInt _interval;
   // 旋转角度
   SFloatVector3 _rotation;
public:
   FRs3dSceneMovie();
   MO_ABSTRACT ~FRs3dSceneMovie();
public:
   //------------------------------------------------------------
   // <T>获得类型名称。</T>
   MO_INLINE TCharC* TypeName(){
      return _typeName;
   }
   //------------------------------------------------------------
   // <T>获得间隔。</T>
   MO_INLINE TInt Interval(){
      return _interval;
   }
   //------------------------------------------------------------
   // <T>获得旋转。</T>
   MO_INLINE SFloatVector3& Rotation(){
      return _rotation;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtrs<FRs3dSceneMovie> GRs3dSceneMoviePtrs;

//============================================================
// <T>场景渲染资源。</T>
//============================================================
class MO_FR_DECLARE FRs3dSceneRenderable : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dSceneRenderable, FInstance);
protected:
   // 类型名称
   TFsName _name;
public:
   FRs3dSceneRenderable();
   MO_ABSTRACT ~FRs3dSceneRenderable();
public:
   //------------------------------------------------------------
   // <T>获得类型名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtrs<FRs3dSceneRenderable> GRs3dSceneRenderablePtrs;

//============================================================
// <T>资源场景信息。</T>
//============================================================
class MO_FR_DECLARE FRs3dSceneDisplay : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dSceneDisplay, FInstance);
protected:
   // 来源
   TFsName _source;
   // 合并顶点标志
   TBool _optionMergeVertex;
   // 合并材质标志
   TBool _optionMergeMaterial;
   // 矩阵
   SFloatMatrix3d _matrix;
   // 动画集合
   GRs3dSceneMoviePtrs _movies;
   // 材质集合
   GRs3dSceneMaterialPtrs _materials;
   // 渲染集合
   GRs3dSceneRenderablePtrs _renderables;
public:
   FRs3dSceneDisplay();
   MO_ABSTRACT ~FRs3dSceneDisplay();
public:
   //------------------------------------------------------------
   // <T>获得来源。</T>
   MO_INLINE TCharC* Source(){
      return _source;
   }
   //------------------------------------------------------------
   // <T>获得合并顶点标志。</T>
   MO_INLINE TBool OptionMergeVertex(){
      return _optionMergeVertex;
   }
   //------------------------------------------------------------
   // <T>获得合并材质标志。</T>
   MO_INLINE TBool OptionMergeMaterial(){
      return _optionMergeMaterial;
   }
   //------------------------------------------------------------
   // <T>获得矩阵。</T>
   MO_INLINE SFloatMatrix3d& Matrix(){
      return _matrix;
   }
   //------------------------------------------------------------
   // <T>获得动画集合。</T>
   MO_INLINE GRs3dSceneMoviePtrs& Movies(){
      return _movies;
   }
   //------------------------------------------------------------
   // <T>获得材质集合。</T>
   MO_INLINE GRs3dSceneMaterialPtrs& Materials(){
      return _materials;
   }
   //------------------------------------------------------------
   // <T>获得渲染集合。</T>
   MO_INLINE GRs3dSceneRenderablePtrs& Renderables(){
      return _renderables;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtrs<FRs3dSceneDisplay> GRs3dSceneDisplayPtrs;

//============================================================
// <T>场景天空资源。</T>
//============================================================
class MO_FR_DECLARE FRs3dSceneSky : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dSceneSky, FInstance);
protected:
   // 名称
   TString _name;
   // 类型名称
   TString _typeName;
   // 显示集合
   GRs3dSceneDisplayPtrs _displays;
public:
   FRs3dSceneSky();
   MO_ABSTRACT ~FRs3dSceneSky();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>获得类型名称。</T>
   MO_INLINE TCharC* TypeName(){
      return _typeName;
   }
   //------------------------------------------------------------
   // <T>获得显示集合。</T>
   MO_INLINE GRs3dSceneDisplayPtrs& Displays(){
      return _displays;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>资源场景信息。</T>
//============================================================
class MO_FR_DECLARE FRs3dSceneMap : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dSceneMap, FInstance);
protected:
   // 名称
   TString _name;
   // 类型名称
   TString _typeName;
   // 显示集合
   GRs3dSceneDisplayPtrs _displays;
public:
   FRs3dSceneMap();
   MO_ABSTRACT ~FRs3dSceneMap();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>获得类型名称。</T>
   MO_INLINE TCharC* TypeName(){
      return _typeName;
   }
   //------------------------------------------------------------
   // <T>获得显示集合。</T>
   MO_INLINE GRs3dSceneDisplayPtrs& Displays(){
      return _displays;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>场景空间资源。</T>
//============================================================
class MO_FR_DECLARE FRs3dSceneSpace : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dSceneSpace, FInstance);
protected:
   // 名称
   TString _name;
   // 类型名称
   TString _typeName;
   // 显示集合
   GRs3dSceneDisplayPtrs _displays;
public:
   FRs3dSceneSpace();
   MO_ABSTRACT ~FRs3dSceneSpace();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>获得类型名称。</T>
   MO_INLINE TCharC* TypeName(){
      return _typeName;
   }
   //------------------------------------------------------------
   // <T>获得显示集合。</T>
   MO_INLINE GRs3dSceneDisplayPtrs& Displays(){
      return _displays;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>资源场景信息。</T>
//============================================================
class MO_FR_DECLARE FRs3dScene : public FResource3d
{
   MO_CLASS_DECLARE_INHERITS(FRs3dScene, FResource3d);
protected:
   // 主题代码
   TFsName _themeCode;
   // 主题
   // TFsName theme:FGeRsTheme;
   // 技术
   GPtr<FRs3dSceneTechnique> _technique;
   // 区域
   GPtr<FRs3dSceneRegion> _region;
   // 天空
   GPtr<FRs3dSceneSky> _sky;
   // 地图
   GPtr<FRs3dSceneMap> _map;
   // 空间
   GPtr<FRs3dSceneSpace> _space;
public:
   FRs3dScene();
   MO_ABSTRACT ~FRs3dScene();
public:
   //------------------------------------------------------------
   // <T>获得技术。</T>
   MO_INLINE FRs3dSceneTechnique* Technique(){
      return _technique;
   }
   //------------------------------------------------------------
   // <T>获得区域。</T>
   MO_INLINE FRs3dSceneRegion* Region(){
      return _region;
   }
   //------------------------------------------------------------
   // <T>获得天空。</T>
   MO_INLINE FRs3dSceneSky* Sky(){
      return _sky;
   }
   //------------------------------------------------------------
   // <T>获得地图。</T>
   MO_INLINE FRs3dSceneMap* Map(){
      return _map;
   }
   //------------------------------------------------------------
   // <T>获得空间。</T>
   MO_INLINE FRs3dSceneSpace* Space(){
      return _space;
   }
public:
   //MO_ABSTRACT TBool TestReady();
   //MO_ABSTRACT TBool TestValid();
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
//public:
//   MO_ABSTRACT TResult OnOpen();
//   MO_ABSTRACT TResult OnClose();
//public:
//   TResult Open();
//   TResult Close();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtrDictionary<FRs3dScene> GRs3dScenePtrDictionary;

//============================================================
// <T>资源场景控制台。</T>
//============================================================
class MO_FR_DECLARE FRs3dSceneConsole : public FObject{
protected:
   GRs3dScenePtrDictionary _scenes;
public:
   FRs3dSceneConsole();
   MO_ABSTRACT ~FRs3dSceneConsole();
public:
   //------------------------------------------------------------
   // <T>获得场景集合。</T>
   MO_INLINE GRs3dScenePtrDictionary& Scenes(){
      return _scenes;
   }
public:
   MO_ABSTRACT FRs3dScene* Load(TCharC* pName);
public:
   FRs3dScene* Find(TCharC* pName);
   FRs3dScene* Open(TCharC* pName);
public:
   void Clear();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EG_CONTENT3D_SCENE_H__
