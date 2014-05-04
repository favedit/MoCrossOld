#ifndef __MO_FG_BASE_H__
#define __MO_FG_BASE_H__
//************************************************************

#ifndef __MO_FG_COMMON_H__
#include "MoFgCommon.h"
#endif // __MO_FG_COMMON_H__

#ifndef __MO_FG_CORE_H__
#include "MoFgCore.h"
#endif // __MO_FG_CORE_H__

MO_NAMESPACE_BEGIN

//==========================================================
#define MO_RENDEROBJECT_SHADERPARAMETER "ShaderParameter"
#define MO_RENDEROBJECT_SHADERATTRIBUTE "ShaderAttribute"
#define MO_RENDEROBJECT_SHADERSAMPLER   "ShaderSampler"
#define MO_RENDEROBJECT_VERTEXBUFFER    "VertexBuffer"
#define MO_RENDEROBJECT_INDEXBUFFER     "IndexBuffer"
#define MO_RENDEROBJECT_TEXTURE2D       "Texture2d"
#define MO_RENDEROBJECT_TEXTURE3D       "Texture3d"
#define MO_RENDEROBJECT_TARGET          "Target"
#define MO_RENDEROBJECT_VERTEXSHADER    "VertexShader"
#define MO_RENDEROBJECT_FRAGMENTSHADER  "FragmentShader"
#define MO_RENDEROBJECT_PROGRAM         "Program"

//==========================================================
class FMaterial;
class FRenderable;
class FRenderTexture;
typedef MO_FG_DECLARE FObjects<FRenderTexture*> FRenderTextureCollection;
class FRenderVertexStreams;
class FRenderIndexBuffer;
class FEffect;
typedef MO_FG_DECLARE FObjects<FEffect*> FEffectCollection;
typedef MO_FG_DECLARE FDictionary<FEffect*> FEffectDictionary;
class FRenderDevice;

//==========================================================
typedef TInt32 TColor;

//============================================================
// <T>渲染设备类型。</T>
//============================================================
enum ERenderDevice{
   ERenderDevice_Unknown,
   ERenderDevice_DirectX9,
   ERenderDevice_DirectX10,
   ERenderDevice_DirectX11,
   ERenderDevice_Opgl2,
   ERenderDevice_Opgl3,
   ERenderDevice_Opgl4,
   ERenderDevice_OpglEs2,
   ERenderDevice_OpglEs3,
};

//============================================================
// <T>渲染脚本类型。</T>
//============================================================
enum ERenderScript{
   ERenderScript_Unknown,
   ERenderScript_Hlsl,
   ERenderScript_Glsl,
};

//============================================================
// <T>渲染面模式。</T>
//============================================================
enum ERenderFillMode{
   ERenderFillMode_Unknown,
   ERenderFillMode_Point,
   ERenderFillMode_Line,
   ERenderFillMode_Fill,
};

//============================================================
// <T>渲染面模式工具。</T>
//============================================================
class MO_FG_DECLARE RRenderFillMode{
public:
   static ERenderFillMode Parse(TCharC* pValue, ERenderFillMode fillModeCd = ERenderFillMode_Fill);
   static TCharC* Format(ERenderFillMode fillModeCd);
};

//============================================================
// <T>渲染深度模式。</T>
//============================================================
enum ERenderDepthMode{
   ERenderDepthMode_None,
   ERenderDepthMode_Equal,
   ERenderDepthMode_NotEqual,
   ERenderDepthMode_Less,
   ERenderDepthMode_LessEqual,
   ERenderDepthMode_Greater,
   ERenderDepthMode_GreaterEqual,
   ERenderDepthMode_Always,
};

//============================================================
// <T>渲染深度模式工具。</T>
//============================================================
class MO_FG_DECLARE RRenderDepthMode{
public:
   static ERenderDepthMode Parse(TCharC* pValue, ERenderDepthMode depthModeCd = ERenderDepthMode_None);
   static TCharC* Format(ERenderDepthMode depthModeCd);
};

//============================================================
// <T>渲染剔除模式。</T>
//============================================================
enum ERenderCullMode{
   ERenderCullMode_None,
   ERenderCullMode_Front,
   ERenderCullMode_Back,
   ERenderCullMode_Both,
};

//============================================================
// <T>渲染剔除模式工具。</T>
//============================================================
class MO_FG_DECLARE RRenderCullMode{
public:
   static ERenderCullMode Parse(TCharC* pValue, ERenderCullMode cullModeCd = ERenderCullMode_Back);
   static TCharC* Format(ERenderCullMode cullModeCd);
};

//============================================================
// <T>渲染融合模式。</T>
//============================================================
enum ERenderBlendMode{
   ERenderBlendMode_None,
   ERenderBlendMode_SourceAlpha,
   ERenderBlendMode_OneMinusSourceAlpha,
};

//============================================================
// <T>渲染融合模式工具。</T>
//============================================================
class MO_FG_DECLARE RRenderBlendMode{
public:
   static ERenderBlendMode Parse(TCharC* pValue, ERenderBlendMode blendModeCd = ERenderBlendMode_None);
   static TCharC* Format(ERenderBlendMode blendModeCd);
};

//============================================================
// <T>渲染使用模式。</T>
//============================================================
enum ERenderUsage{
   ERenderUsage_Unknown,
   ERenderUsage_Read,
   ERenderUsage_Write,
   ERenderUsage_ReadWrite,
};

//============================================================
// <T>渲染顶点格式。</T>
//============================================================
enum ERenderVertexFormat{
   ERenderVertexFormat_Unknown = 0,
   ERenderVertexFormat_Float1 = 1,
   ERenderVertexFormat_Float2 = 2,
   ERenderVertexFormat_Float3 = 3,
   ERenderVertexFormat_Float4 = 4,
   ERenderVertexFormat_Byte4 = 5,
   ERenderVertexFormat_ByteNormal4 = 6,
};

//============================================================
// <T>渲染面模式工具。</T>
//============================================================
class MO_FG_DECLARE RRenderVertexFormat{
public:
   static ERenderVertexFormat Parse(TCharC* pValue, ERenderVertexFormat formatCd = ERenderVertexFormat_Unknown);
   static TCharC* Format(ERenderVertexFormat formatCd);
};

//============================================================
// <T>渲染顶点缓冲。</T>
//============================================================
enum ERenderVertexBuffer{
   ERenderVertexBuffer_Unknown = 0,
   ERenderVertexBuffer_Instance = 1,
   ERenderVertexBuffer_Position = 2,
   ERenderVertexBuffer_Color = 3,
   ERenderVertexBuffer_Coord = 4,
   ERenderVertexBuffer_CoordLight = 5,
   ERenderVertexBuffer_Normal = 6,
   ERenderVertexBuffer_Binormal = 7,
   ERenderVertexBuffer_Tangent = 8,
   ERenderVertexBuffer_BoneIndex = 9,
   ERenderVertexBuffer_BoneWeight = 10,
   ERenderVertexBuffer_Count = 11,
};

//============================================================
// <T>渲染顶点缓冲工具。</T>
//============================================================
class MO_FG_DECLARE RRenderVertexBuffer{
public:
   static ERenderVertexBuffer Parse(TCharC* pValue, ERenderVertexBuffer bufferCd = ERenderVertexBuffer_Unknown);
   static TCharC* Format(ERenderVertexBuffer bufferCd);
};

//============================================================
// <T>渲染索引宽度。</T>
//============================================================
enum ERenderIndexStride{
   ERenderIndexStride_Unknown = 0,
   ERenderIndexStride_Uint16 = 1,
   ERenderIndexStride_Uint32 = 2,
};

//============================================================
// <T>渲染程序类型。</T>
//============================================================
enum ERenderShader{
   ERenderShader_Unknown,
   ERenderShader_Vertex,
   ERenderShader_Fragment,
};

//============================================================
// <T>渲染程序类型工具。</T>
//============================================================
class MO_FG_DECLARE RRenderShader{
public:
   static ERenderShader Parse(TCharC* pValue, ERenderShader shaderCd = ERenderShader_Unknown);
   static TCharC* Format(ERenderShader shaderCd);
};

//============================================================
// <T>渲染程序常量类型。</T>
//============================================================
enum ERenderShaderParameterFormat{
   ERenderShaderParameterFormat_Unknown,
   ERenderShaderParameterFormat_Float1,
   ERenderShaderParameterFormat_Float2,
   ERenderShaderParameterFormat_Float3,
   ERenderShaderParameterFormat_Float4,
   ERenderShaderParameterFormat_Float3x3,
   ERenderShaderParameterFormat_Float4x3,
   ERenderShaderParameterFormat_Float4x4,
};

//============================================================
// <T>渲染程序常量工具。</T>
//============================================================
class MO_FG_DECLARE RRenderShaderParameterFormat{
public:
   static ERenderShaderParameterFormat Parse(TCharC* pValue, ERenderShaderParameterFormat formatCd = ERenderShaderParameterFormat_Unknown);
   static TCharC* Format(ERenderShaderParameterFormat formatCd);
};

//============================================================
// <T>渲染程序属性类型。</T>
//============================================================
enum ERenderShaderAttributeFormat{
   ERenderShaderAttributeFormat_Unknown,
   ERenderShaderAttributeFormat_Float1,
   ERenderShaderAttributeFormat_Float2,
   ERenderShaderAttributeFormat_Float3,
   ERenderShaderAttributeFormat_Float4,
   ERenderShaderAttributeFormat_Byte4,
   ERenderShaderAttributeFormat_Byte4Normal,
};

//============================================================
// <T>渲染程序属性工具。</T>
//============================================================
class MO_FG_DECLARE RRenderShaderAttributeFormat{
public:
   static ERenderShaderAttributeFormat Parse(TCharC* pValue, ERenderShaderAttributeFormat formatCd = ERenderShaderAttributeFormat_Unknown);
   static TCharC* Format(ERenderShaderAttributeFormat formatCd);
};

//============================================================
// <T>渲染纹理类型。</T>
//============================================================
enum ERenderTexture{
   ERenderTexture_Unknown,
   ERenderTexture_Flat2d,
   ERenderTexture_Flat3d,
   ERenderTexture_Cube,
};

//============================================================
// <T>渲染取样器类型。</T>
//============================================================
enum ERenderSampler{
   ERenderSampler_Unknown,
   ERenderSampler_Ambient,
   ERenderSampler_Diffuse,
   ERenderSampler_Alpha,
   ERenderSampler_Normal,
   ERenderSampler_Height,
   ERenderSampler_SpecularColor,
   ERenderSampler_SpecularLevel,
   ERenderSampler_TransmittanceColor,
   ERenderSampler_TransmittanceLevel,
   ERenderSampler_Light,
   ERenderSampler_Reflect,
   ERenderSampler_Refract,
   ERenderSampler_Emissive,
   ERenderSampler_Environment,
   ERenderSampler_LayerMerge,
   ERenderSampler_Layer1,
   ERenderSampler_Layer2,
   ERenderSampler_Layer3,
   ERenderSampler_Layer4,
   ERenderSampler_PackDiffuse,
   ERenderSampler_PackNormal,
   ERenderSampler_PackSpecular,
   ERenderSampler_PackTransmittance,
   ERenderSampler_PackLight,
   ERenderSampler_LightDepth,
   ERenderSampler_Count,
};

//============================================================
// <T>渲染取样器类型工具。</T>
//============================================================
class MO_FG_DECLARE RRenderSampler{
public:
   static ERenderSampler Parse(TCharC* pValue, ERenderSampler samplerCd = ERenderSampler_Unknown);
   static TCharC* Format(ERenderSampler samplerCd);
};

//============================================================
// <T>渲染纹理格式。</T>
//============================================================
enum ERenderTextureFormat{
   ERenderTextureFormat_Unknown,
   ERenderTextureFormat_BGRA,
   ERenderTextureFormat_A8R8B8G8,
   ERenderTextureFormat_A32R32G32B32,
   ERenderTextureFormat_Depth,
};

//============================================================
// <T>渲染纹理过滤。</T>
//============================================================
enum ERenderTextureFilter{
   ERenderTextureFilter_None,
   ERenderTextureFilter_Nearest,
   ERenderTextureFilter_Linear,
};

//============================================================
// <T>渲染纹理展开。</T>
//============================================================
enum ERenderTextureWrap{
   ERenderTextureWrap_None,
   ERenderTextureWrap_Clamp,
   ERenderTextureWrap_Repeat,
};

//============================================================
// <T>效果顶点属性。</T>
//============================================================
enum EEffectVertexAttribute{
   EEffectVertexAttribute_Unknown,
   EEffectVertexAttribute_Position,
   EEffectVertexAttribute_Color,
   EEffectVertexAttribute_Coord,
   EEffectVertexAttribute_Normal,
   EEffectVertexAttribute_Binormal,
   EEffectVertexAttribute_Tangent,
   EEffectVertexAttribute_BoneIndex,
   EEffectVertexAttribute_BoneWeight,
   EEffectVertexAttribute_Count,
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
class MO_FG_DECLARE REffectSampler{
public:
   static EEffectSampler Parse(TCharC* pValue, EEffectSampler samplerCd = EEffectSampler_Diffuse);
   static TCharC* Format(EEffectSampler samplerCd);
};

//============================================================
// <T>渲染信息。</T>
//============================================================
class MO_FG_DECLARE FRenderableInfo : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderableInfo, FInstance);
public:
   FRenderable* _pRenderable;
public:
   FRenderableInfo();
   MO_ABSTRACT ~FRenderableInfo();
public:
   //------------------------------------------------------------
   // <T>获得渲染对象。</T>
   MO_INLINE FRenderable* Renderable(){
      return _pRenderable;
   }
   //------------------------------------------------------------
   // <T>设置渲染对象。</T>
   MO_INLINE void SetRenderable(FRenderable* pRenderable){
      _pRenderable = pRenderable;
   }
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FRenderableInfo*> FRenderableInfoCollection;

//============================================================
// <T>渲染标志。</T>
//============================================================
class MO_FG_DECLARE SRenderableDescriptor{
public:
   TBool setuped;
public:
   // 配置实体
   TBool optionInstanced;
   // 配置绑定器
   TBool optionBinder;
   // 配置材质
   TBool optionMaterial;
   //// 配置法线
   //TBool optionNormal;
   //// 配置法线完整 (是否含有副法线和切线)
   //TBool optionNormalFull;
   //// 配置法线缩放 (是否使用法线缩放)
   //TBool optionNormalScale;
   //// 配置骨骼缩放
   //TBool optionBoneScale;
   //// 配置深度
   //TBool optionDepth;
   // 配置阴影
   TBool optionShadow;
   // 配置自阴影
   TBool optionShadowSelf;
   //// 配置光源贴图
   //TBool optionLightMap;
   //// 配置透射
   //TBool optionTransmittance;
public:
   // 支持顶点颜色技术
   TBool supportVertexColor;
   // 支持顶点纹理技术
   TBool supportVertexCoord;
   // 支持法线技术
   TBool supportVertexNormal;
   // 支持副法线技术
   TBool supportVertexNormalFull;
   // 支持骨头技术
   TBool supportVertexBone;
public:
   // 支持透明技术
   TBool supportAlpha;
   // 支持凹凸技术
   TBool supportBump;
   // 支持环境技术
   TBool supportAmbient;
   // 支持散射技术
   TBool supportDiffuse;
   // 支持法线技术
   TBool supportNormal;
   // 支持高光技术
   TBool supportSpecularColor;
   // 支持高光级别技术
   TBool supportSpecularLevel;
   // 支持环境技术
   TBool supportEnvironment;
   // 支持受光光技术
   TBool supportLight;
   // 支持反射技术
   TBool supportReflect;
   // 支持折射技术
   TBool supportRefract;
   // 支持发光技术
   TBool supportEmissive;
   // 支持高度技术
   TBool supportHeight;
   //// 支持环境散射技术
   //TBool supportDiffuseCamera;
   //// 支持凹凸相机技术
   //TBool supportBumpCamera;
   //// 支持高光相机技术
   //TBool supportSpecularCamera;
   //// 支持高光相机级别技术
   //TBool supportSpecularCameraLevel;
public:
   // 顶点个数
   TBool vertexCount;
   // 骨头个数
   TBool boneCount;
public:
   // 顶点缓冲
   TBool vertexBuffers[ERenderVertexBuffer_Count];
   // 取样器集合
   TBool samplers[ERenderSampler_Count];
public:
   SRenderableDescriptor();
public:
   //------------------------------------------------------------
   // <T>测试是否含有指定缓冲。</T>
   MO_INLINE TBool ContainsBuffer(ERenderVertexBuffer bufferCd){
      return vertexBuffers[bufferCd];
   }
   //------------------------------------------------------------
   // <T>测试是否含有指定取样器。</T>
   MO_INLINE TBool ContainsSampler(ERenderSampler samplerCd){
      return samplers[samplerCd];
   }
public:
   void Assign(SRenderableDescriptor* pFlag);
   void Build();
   void Reset();
};

//============================================================
// <T>渲染项目信息。</T>
//============================================================
struct MO_FG_DECLARE SRenderableItem{
public:
   // 坐标
   SFloatPoint3 location;
   // 尺寸
   SFloatSize3 size;
   // 旋转
   SFloatVector3 rotation;
   // 背景颜色
   SFloatColor4 groundColor;
   // 纹理坐标
   SFloatCoord coord;
   // 矩阵
   SFloatMatrix3d matrix;
public:
   //------------------------------------------------------------
   // <T>构造浮点数颜色。</T>
   MO_INLINE SRenderableItem(){
   }
};
//------------------------------------------------------------
typedef MO_FG_DECLARE TFixVector<SRenderableItem, 64> SRenderableItems;

//============================================================
// <T>渲染信息。</T>
//============================================================
struct MO_FG_DECLARE SRenderable{
public:
   SRenderableItems items;
public:
   //------------------------------------------------------------
   // <T>构造浮点数颜色。</T>
   MO_INLINE SRenderable(){
   }
public:
   //------------------------------------------------------------
   // <T>收集一个项目。</T>
   MO_INLINE SRenderableItem& Alloc(){
      TInt index = items.Count();
      items.SetCount(index + 1);
      return items.Get(index);
   }
   //------------------------------------------------------------
   // <T>增加要给项目。</T>
   MO_INLINE void Push(SRenderableItem& item){
      items.Push(item);
   }
};

//============================================================
// <T>可渲染对象。</T>
//============================================================
class MO_FG_DECLARE FRenderable : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderable, FInstance);
protected:
   // 名称
   TString _name;
   // 类型名称
   TString _typeName;
   // 变换矩阵
   SMatrix3d _matrix;
   // 标志集合
   SRenderableDescriptor _descriptor;
   // 材质
   GPtr<FMaterial> _material;
   // 材质
   GPtr<FMaterial> _materialReference;
   // 渲染信息
   FRenderableInfo* _pVisualInfo;
   // 纹理集合
   FRenderTextureCollection* _pTextures;
   // 顶点流集合
   FRenderVertexStreams* _pVertexStreams;
   // 索引流
   FRenderIndexBuffer* _pIndexBuffer;
   // 激活效果器
   FEffect* _pActiveEffect;
   // 效果器集合
   FEffectCollection* _pEffects;
public:
   FRenderable();
   MO_ABSTRACT ~FRenderable();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_OVERRIDE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_OVERRIDE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>获得类型名称。</T>
   MO_OVERRIDE TCharC* TypeName(){
      return _typeName;
   }
   //------------------------------------------------------------
   // <T>设置类型名称。</T>
   MO_OVERRIDE void SetTypeName(TCharC* pTypeName){
      _typeName = pTypeName;
   }
   //------------------------------------------------------------
   // <T>获得矩阵。</T>
   MO_OVERRIDE SMatrix3d& Matrix(){
      return _matrix;
   }
   //------------------------------------------------------------
   // <T>获得标志集合。</T>
   MO_OVERRIDE SRenderableDescriptor& Descriptor(){
      return _descriptor;
   }
   //------------------------------------------------------------
   // <T>获得可见信息。</T>
   MO_OVERRIDE FRenderableInfo* VisualInfo(){
      return _pVisualInfo;
   }
   //------------------------------------------------------------
   // <T>设置可见信息。</T>
   MO_INLINE void SetVisualInfo(FRenderableInfo* pVisualInfo){
      _pVisualInfo = pVisualInfo;
   }
   //------------------------------------------------------------
   // <T>获得引用材质。</T>
   MO_OVERRIDE FMaterial* MaterialReference(){
      return _materialReference;
   }
   //------------------------------------------------------------
   // <T>设置引用材质。</T>
   MO_INLINE void SetMaterialReference(FMaterial* pMaterialReference){
      _materialReference = pMaterialReference;
   }
   //------------------------------------------------------------
   // <T>获得材质。</T>
   MO_OVERRIDE FMaterial* Material(){
      return _material;
   }
   //------------------------------------------------------------
   // <T>设置材质。</T>
   MO_INLINE void SetMaterial(FMaterial* pMaterial){
      _material = pMaterial;
   }
   //------------------------------------------------------------
   // <T>获得纹理集合。</T>
   MO_INLINE FRenderTextureCollection* Textures(){
      return _pTextures;
   }
   //------------------------------------------------------------
   // <T>获得顶点流集合。</T>
   MO_INLINE FRenderVertexStreams* VertexStreams(){
      return _pVertexStreams;
   }
   //------------------------------------------------------------
   // <T>设置顶点流集合。</T>
   MO_INLINE void SetVertexStreams(FRenderVertexStreams* pVertexStreams){
      _pVertexStreams = pVertexStreams;
   }
   //------------------------------------------------------------
   // <T>获得索引流。</T>
   MO_INLINE FRenderIndexBuffer* IndexBuffer(){
      return _pIndexBuffer;
   }
   //------------------------------------------------------------
   // <T>设置索引流。</T>
   MO_INLINE void SetIndexBuffer(FRenderIndexBuffer* pIndexBuffer){
      _pIndexBuffer = pIndexBuffer;
   }
   //------------------------------------------------------------
   // <T>获得激活效果器。</T>
   MO_OVERRIDE FEffect* ActiveEffect(){
      return _pActiveEffect;
   }
   //------------------------------------------------------------
   // <T>设置激活效果器。</T>
   MO_INLINE void SetActiveEffect(FEffect* pEffect){
      _pActiveEffect = pEffect;
   }
   //------------------------------------------------------------
   // <T>获得效果器集合。</T>
   MO_OVERRIDE FEffectCollection* Effects(){
      return _pEffects;
   }
public:
   FEffect* EffectFind(TCharC* pName);
   TResult EffectBind(FEffect* pEffect);
public:
   MO_ABSTRACT TResult CalculateModelMatrix(SFloatMatrix3d& matrix);
   MO_ABSTRACT TInt CalculateBoneMatrix(SFloatMatrix3d* pMatrix, TInt offset = 0, TInt capacity = 0);
   MO_ABSTRACT TResult BuildDescriptor();
   FRenderTexture* FindTexture(ERenderSampler samplerCd);
   FRenderTexture* GetTexture(ERenderSampler samplerCd);
public:
   MO_ABSTRACT TResult Update(TAny* pParameter = NULL);
   MO_ABSTRACT TResult ProcessBefore(TAny* pParameter = NULL);
   MO_ABSTRACT TResult ProcessAfter(TAny* pParameter = NULL);
   MO_ABSTRACT TResult Free();
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Dispose();
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FRenderable*> FRenderableCollection;
typedef MO_FG_DECLARE GPtrs<FRenderable> GRenderablePtrs;

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FG_BASE_H__
