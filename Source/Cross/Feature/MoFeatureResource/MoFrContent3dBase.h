#ifndef __MO_FR_CONTENT3D_BASE_H__
#define __MO_FR_CONTENT3D_BASE_H__
//************************************************************

#ifndef __MO_FR_COMMON_H__
#include "MoFrCommon.h"
#endif // __MO_FR_COMMON_H__

#ifndef __MO_FR_CONTENT_H__
#include "MoFrContent.h"
#endif // __MO_FR_CONTENT_H__

MO_NAMESPACE_BEGIN

//============================================================
class FRs3dThemeConsole;
class FRs3dTextureConsole;
class FRs3dMaterialConsole;
class FRs3dModelConsole;
class FRs3dTemplateConsole;
class FRs3dSceneConsole;

//============================================================
// <T>资源3D类型。</T>
//============================================================
enum EResource3d{
   EResource3d_Unknown   = 0,
   EResource3d_Texture   = 1,
};

//============================================================
// <T>渲染纹理类型。</T>
//============================================================
enum EContent3dTexture{
   EContent3dTexture_Unknown,
   EContent3dTexture_Flat2d,
   EContent3dTexture_Flat3d,
   EContent3dTexture_Cube,
};

//============================================================
// <T>渲染取样器类型。</T>
//============================================================
enum EContent3dSampler{
   EContent3dSampler_Unknown,
   EContent3dSampler_Ambient,
   EContent3dSampler_Diffuse,
   EContent3dSampler_Alpha,
   EContent3dSampler_Normal,
   EContent3dSampler_Height,
   EContent3dSampler_SpecularColor,
   EContent3dSampler_SpecularLevel,
   EContent3dSampler_TransmittanceColor,
   EContent3dSampler_TransmittanceLevel,
   EContent3dSampler_Light,
   EContent3dSampler_Reflect,
   EContent3dSampler_Refract,
   EContent3dSampler_Emissive,
   EContent3dSampler_Environment,
   EContent3dSampler_LayerMerge,
   EContent3dSampler_Layer1,
   EContent3dSampler_Layer2,
   EContent3dSampler_Layer3,
   EContent3dSampler_Layer4,
   EContent3dSampler_PackDiffuse,
   EContent3dSampler_PackNormal,
   EContent3dSampler_PackSpecular,
   EContent3dSampler_PackTransmittance,
   EContent3dSampler_PackLight,
   EContent3dSampler_Count,
};

//============================================================
// <T>渲染顶点格式。</T>
//============================================================
enum EContent3dVertexFormat{
   EContent3dVertexFormat_Unknown = 0,
   EContent3dVertexFormat_Float1 = 1,
   EContent3dVertexFormat_Float2 = 2,
   EContent3dVertexFormat_Float3 = 3,
   EContent3dVertexFormat_Float4 = 4,
   EContent3dVertexFormat_Byte4 = 5,
   EContent3dVertexFormat_ByteNormal4 = 6,
};

//============================================================
// <T>渲染索引宽度。</T>
//============================================================
enum EContent3dIndexStride{
   EContent3dIndexStride_Unknown = 0,
   EContent3dIndexStride_Uint16 = 1,
   EContent3dIndexStride_Uint32 = 2,
};

//============================================================
// <T>雾化信息。</T>
//============================================================
struct MO_FR_DECLARE SFogInfo{
public:
   // 近平面
   TFloat _near;
   // 远平面
   TFloat _far;
   // 比率
   TFloat _rate;
   // 衰减
   TFloat _attenuation;
   // 颜色
   SFloatColor4 _color;
public:
   //------------------------------------------------------------
   // <T>构造雾化信息。</T>
   SFogInfo(){
      _near = 0.0f;
      _far = 1.0f;
      _rate = 1.0f;
      _attenuation = 1.0f;
      _color.SetAll(1.0f);
   }
public:
   //------------------------------------------------------------
   // <T>从输入流中反序列化数据。</T>
   void Unserialize(IDataInput* pInput){
      _near = pInput->ReadFloat();
      _far = pInput->ReadFloat();
      _rate = pInput->ReadFloat();
      _attenuation = pInput->ReadFloat();
      _color.Unserialize(pInput);
   }
};

//============================================================
// <T>边界信息。</T>
//============================================================
struct MO_FR_DECLARE SEdgeInfo{
public:
   // 比率
   TFloat _rate;
   // 层级
   TFloat _level;
   // 宽度
   TFloat _width;
   // 颜色
   SFloatColor4 _color;
public:
   //------------------------------------------------------------
   // <T>构造边界信息。</T>
   SEdgeInfo(){
      _rate = 0.0f;
      _level = 1.0f;
      _width = 1.0f;
      _color.SetAll(1.0f);
   }
public:
   //------------------------------------------------------------
   // <T>从输入流中反序列化数据。</T>
   void Unserialize(IDataInput* pInput){
      _rate = pInput->ReadFloat();
      _level = pInput->ReadFloat();
      _width = pInput->ReadFloat();
      _color.Unserialize(pInput);
   }
};

//============================================================
// <T>平面信息。</T>
//============================================================
struct MO_FR_DECLARE SFaceInfo{
public:
   // 范围
   TFloat _range;
   // 限制
   TFloat _limit;
   // 比率
   TFloat _rate;
public:
   //------------------------------------------------------------
   // <T>构造平面信息。</T>
   SFaceInfo(){
      _range = 0.0f;
      _limit = 1.0f;
      _rate = 1.0f;
   }
public:
   //------------------------------------------------------------
   // <T>从输入流中反序列化数据。</T>
   void Unserialize(IDataInput* pInput){
      _range = pInput->ReadFloat();
      _limit = pInput->ReadFloat();
      _rate = pInput->ReadFloat();
   }
};

//============================================================
// <T>阴影信息。</T>
//============================================================
struct MO_FR_DECLARE SShadowInfo{
public:
   // 基础
   TFloat base;
   // 比率
   TFloat rate;
   // 层级
   TFloat level;
   // 范围
   TFloat range;
public:
   //------------------------------------------------------------
   // <T>构造阴影信息。</T>
   SShadowInfo(){
      base = 0.00000005f;
      rate = 0.5f;
      level = 0.000004f;
      range = 0.0f;
   }
public:
   //------------------------------------------------------------
   // <T>从输入流中反序列化数据。</T>
   void Unserialize(IDataInput* pInput){
      base = pInput->ReadFloat();
      rate = pInput->ReadFloat();
      level = pInput->ReadFloat();
      range = pInput->ReadFloat();
   }
};

//============================================================
// <T>材质信息。</T>
//============================================================
struct MO_FR_DECLARE SMaterialInfo{
public:
   // 材质代码
   TFsName code;
   // 材质标签
   TWideString label;
   // 效果名称
   TFsName effectName;
   // 变换名称
   TFsName transformName;
   // 设置光源
   TBool optionLight;
   // 设置合并
   TBool optionMerge;
   // 设置排序
   TBool optionSort;
   // 排序级别
   TInt sortLevel;
   // 设置透明
   TBool optionAlpha;
   // 设置深度
   TBool optionDepth;
   // 设置比较
   TFsName optionCompare;
   // 设置双面
   TBool optionDouble;
   // 设置影子
   TBool optionShadow;
   // 设置自阴影
   TBool optionShadowSelf;
   // 设置动态
   TBool optionDynamic;
   // 设置透射
   TBool optionTransmittance;
   // 设置不透明度
   TBool optionOpacity;
   // 设置法线缩放 (未确定)
   TBool optionNormalScale;
   // 设置边线 (未确定)
   TBool optionBorder;
   // 设置高度 (未确定)
   TBool optionHeight;
   // 总体强度
   TFloat powerRate;
   // 总体衰减
   TFloat powerAttenuation;
   // 总体融合
   TFloat powerMerge;
   // 总体阴影
   TFloat powerShadow;
   // 纹理位置X
   TFloat coordX;
   // 纹理位置Y
   TFloat coordY;
   // 纹理宽度比率
   TFloat coordRateWidth;
   // 纹理高度比率
   TFloat coordRateHeight;
   // 颜色最小
   TFloat colorMin;
   // 颜色最大
   TFloat colorMax;
   // 颜色比率
   TFloat colorRate;
   // 颜色融合
   TFloat colorMerge;
   // 透明基础
   TFloat alphaBase;
   // 透明比率
   TFloat alphaRate;
   // 透明级别
   TFloat alphaLevel;
   // 透明合并
   TFloat alphaMerge;
   // 环境光颜色 (RGB=颜色，A=强度)
   SFloatColor4 ambientColor;
   // 环境光阴影
   TFloat ambientShadow;
   // 散射光颜色 (RGB=颜色，A=强度)
   SFloatColor4 diffuseColor;
   // 散射光阴影
   TFloat diffuseShadow;
   // 散射光相机颜色 (RGB=颜色，A=强度)
   SFloatColor4 diffuseViewColor;
   // 散射光视角阴影
   TFloat diffuseViewShadow;
   // 高光颜色 (RGB=颜色，A=强度)
   SFloatColor4 specularColor;
   // 高光基础
   TFloat specularBase;
   // 高光比率
   TFloat specularRate;
   // 高光平均
   TFloat specularAverage;
   // 高光阴影
   TFloat specularShadow;
   // 高光相机颜色 (RGB=颜色，A=强度)
   SFloatColor4 specularViewColor;
   // 视角高光基础
   TFloat specularViewBase;
   // 视角高光比率
   TFloat specularViewRate;
   // 视角高光平均
   TFloat specularViewAverage;
   // 视角高光阴影
   TFloat specularViewShadow;
   // 反射颜色 (RGB=颜色，A=强度)
   SFloatColor4 reflectColor;
   // 反射融合
   TFloat reflectMerge;
   // 反射阴影
   TFloat reflectShadow;
   // 折射正面颜色 (RGB=颜色，A=强度)
   SFloatColor4 refractFrontColor;
   // 折射正面融合
   TFloat refractFrontMerge;
   // 折射正面阴影
   TFloat refractFrontShadow;
   // 折射背面颜色 (RGB=颜色，A=强度)
   SFloatColor4 refractBackColor;
   // 折射背面融合
   TFloat refractBackMerge;
   // 折射背面阴影
   TFloat refractBackShadow;
   // 不透明颜色 (RGB=颜色，A=强度)
   SFloatColor4 opacityColor;
   // 不透明比率
   TFloat opacityRate;
   // 不透明透明
   TFloat opacityAlpha;
   // 不透明深度
   TFloat opacityDepth;
   // 不透明透射
   TFloat opacityTransmittance;
   // 自发光颜色
   SFloatColor4 emissiveColor;
   // 自发光设置
   SFloatColor4 emissive;
public:
   SMaterialInfo();
   MO_ABSTRACT ~SMaterialInfo();
public:
   void AssignOption(SMaterialInfo* pMaterial);
   void AssignLight(SMaterialInfo* pMaterial);
   void Unserialize(IDataInput* pInput);
   void Reset();
};

//============================================================
// <T>资源3D。</T>
//============================================================
class MO_FR_DECLARE FResource3d : public FContent
{
   MO_CLASS_DECLARE_INHERITS(FResource3d, FContent);
protected:
   EResource3d _typeCd;
   TResourceHandle _handle;
   TString _code;
   TWideString _label;
   TFsName _name;
   TInt _timeout;
   TBool _statusOpen;
   TBool _statusReady;
public:
   FResource3d();
   MO_ABSTRACT ~FResource3d();
public:
   //------------------------------------------------------------
   // <T>获得类型。</T>
   MO_INLINE EResource3d TypeCd(){
      return _typeCd;
   }
   //------------------------------------------------------------
   // <T>设置类型。</T>
   MO_INLINE void SetTypeCd(EResource3d typeCd){
      _typeCd = typeCd;
   }
   //------------------------------------------------------------
   // <T>获得句柄。</T>
   MO_INLINE TResourceHandle& Handle(){
      return _handle;
   }
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE TCharC* Code(){
      return _code;
   }
   //------------------------------------------------------------
   // <T>设置代码。</T>
   MO_INLINE void SetCode(TCharC* pCode){
      _code = pCode;
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
   // <T>获得超时。</T>
   MO_INLINE TInt Timeout(){
      return _timeout;
   }
   //------------------------------------------------------------
   // <T>设置超时。</T>
   MO_INLINE void SetTimeout(TInt timeout){
      _timeout = timeout;
   }
public:
   MO_ABSTRACT TBool TestReady();
   MO_ABSTRACT TBool TestValid();
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
public:
   MO_ABSTRACT TResult OnOpen();
   MO_ABSTRACT TResult OnClose();
public:
   TResult Open();
   TResult Close();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE FVector<FResource3d*> FResource3dVector;
typedef MO_FR_DECLARE FList<FResource3d*> FResource3dList;
//typedef MO_FR_DECLARE FSet<TResourceId, FResource3d*> FResource3dSet;

//============================================================
// <T>资源3D控制台。</T>
//============================================================
class MO_FR_DECLARE FResource3dConsole : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FResource3dConsole, FInstance);
protected:
   FRs3dThemeConsole* _pThemeConsole;
   FRs3dTextureConsole* _pTextureConsole;
   FRs3dMaterialConsole* _pMaterialConsole;
   FRs3dModelConsole* _pModelConsole;
   FRs3dTemplateConsole* _pTemplateConsole;
   FRs3dSceneConsole* _pSceneConsole;
public:
   FResource3dConsole();
   MO_ABSTRACT ~FResource3dConsole();
public:
   //------------------------------------------------------------
   // <T>获得主题管理器。</T>
   MO_INLINE FRs3dThemeConsole* ThemeConsole(){
      return _pThemeConsole;
   }
   //------------------------------------------------------------
   // <T>获得模型管理器。</T>
   MO_INLINE FRs3dTextureConsole* TextureConsole(){
      return _pTextureConsole;
   }
   //------------------------------------------------------------
   // <T>获得材质管理器。</T>
   MO_INLINE FRs3dMaterialConsole* MaterialConsole(){
      return _pMaterialConsole;
   }
   //------------------------------------------------------------
   // <T>获得模型管理器。</T>
   MO_INLINE FRs3dModelConsole* ModelConsole(){
      return _pModelConsole;
   }
   //------------------------------------------------------------
   // <T>获得模板管理器。</T>
   MO_INLINE FRs3dTemplateConsole* TemplateConsole(){
      return _pTemplateConsole;
   }
   //------------------------------------------------------------
   // <T>获得场景管理器。</T>
   MO_INLINE FRs3dSceneConsole* SceneConsole(){
      return _pSceneConsole;
   }
public:
   MO_ABSTRACT TResult Setup();
   MO_OVERRIDE TResult Startup();
   MO_OVERRIDE TResult Shutdown();
};

//============================================================
// <T>资源3D管理器。</T>
//============================================================
class MO_FR_DECLARE RResource3dManager : public RSingleton<FResource3dConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FR_CONTENT3D_BASE_H__
