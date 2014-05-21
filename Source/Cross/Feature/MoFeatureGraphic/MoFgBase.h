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
#define MO_RENDERENUM_PARAMETER         "EnumRenderParameter"
#define MO_RENDERENUM_ATTRIBUTE         "EnumRenderAttribute"
#define MO_RENDERENUM_SAMPLER           "EnumRenderSampler"

//==========================================================
#define MO_RENDEROBJECT_LAYOUT          "Layout"
#define MO_RENDEROBJECT_SHADERBUFFER    "ShaderBuffer"
#define MO_RENDEROBJECT_SHADERPARAMETER "ShaderParameter"
#define MO_RENDEROBJECT_SHADERATTRIBUTE "ShaderAttribute"
#define MO_RENDEROBJECT_SHADERSAMPLER   "ShaderSampler"
#define MO_RENDEROBJECT_PROGROM_LAYOUT  "ProgromLayout"
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
class FRenderVertexStream;
class FRenderVertexStreams;
class FRenderVertexBuffer;
class FRenderIndexBuffer;
class FRenderLayout;
class FEffect;
typedef MO_FG_DECLARE FObjects<FEffect*> FEffectCollection;
typedef MO_FG_DECLARE FDictionary<FEffect*> FEffectDictionary;
class FRenderProgramLayout;
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
   ERenderDevice_OpenGL2,
   ERenderDevice_OpenGL3,
   ERenderDevice_OpenGL4,
   ERenderDevice_OpenGLEs2,
   ERenderDevice_OpenGLEs3,
};

//============================================================
// <T>渲染设备工具。</T>
//============================================================
class MO_FG_DECLARE RRenderDevice{
public:
   static ERenderDevice Parse(TCharC* pValue, ERenderDevice deviceCd = ERenderDevice_Unknown);
   static TCharC* Format(ERenderDevice deviceCd);
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
// <T>渲染脚本工具。</T>
//============================================================
class MO_FG_DECLARE RRenderScript{
public:
   static ERenderScript Parse(TCharC* pValue, ERenderScript scriptCd = ERenderScript_Unknown);
   static TCharC* Format(ERenderScript scriptCd);
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

////============================================================
//// <T>渲染顶点缓冲。</T>
////============================================================
//enum ERenderAttribute{
//   ERenderAttribute_Unknown = 0,
//   ERenderAttribute_Instance = 1,
//   ERenderAttribute_Position = 2,
//   ERenderAttribute_Color = 3,
//   ERenderAttribute_Coord = 4,
//   ERenderAttribute_CoordLight = 5,
//   ERenderAttribute_Normal = 6,
//   ERenderAttribute_Binormal = 7,
//   ERenderAttribute_Tangent = 8,
//   ERenderAttribute_BoneIndex = 9,
//   ERenderAttribute_BoneWeight = 10,
//   ERenderAttribute_Count = 11,
//};
//
////============================================================
//// <T>渲染顶点缓冲工具。</T>
////============================================================
//class MO_FG_DECLARE FRenderProgramAttributeEnumerator : public FEnumerator{
//public:
//   TResult Construct();
//};
//
////============================================================
//// <T>渲染取样器类型工具。</T>
////============================================================
//class MO_FG_DECLARE RRenderAttribute : public REnumerator<FRenderProgramAttributeEnumerator>{
//};

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
// <T>渲染程序缓冲类型。</T>
//============================================================
enum ERenderShaderBuffer{
   ERenderShaderBuffer_Unknown,
   ERenderShaderBuffer_Global,
   ERenderShaderBuffer_GlobalStatic,
   ERenderShaderBuffer_GlobalDynamic,
   ERenderShaderBuffer_Technique,
   ERenderShaderBuffer_TechniqueStatic,
   ERenderShaderBuffer_TechniqueDynamic,
   ERenderShaderBuffer_Effect,
   ERenderShaderBuffer_EffectStatic,
   ERenderShaderBuffer_EffectDynamic,
   ERenderShaderBuffer_Renderable,
   ERenderShaderBuffer_RenderableDynamic,
   ERenderShaderBuffer_RenderableMaterial,
   ERenderShaderBuffer_Count,
};

//============================================================
// <T>渲染程序缓冲工具。</T>
//============================================================
class MO_FG_DECLARE RRenderShaderBuffer{
public:
   static ERenderShaderBuffer Parse(TCharC* pValue, ERenderShaderBuffer bufferCd = ERenderShaderBuffer_Unknown);
   static ERenderShaderBuffer ParseGroup(ERenderShaderBuffer bufferCd);
   static TInt ParseSlot(ERenderShaderBuffer bufferCd);
   static TCharC* Format(ERenderShaderBuffer formatCd);
};

//============================================================
// <T>渲染程序常量类型。</T>
//============================================================
enum ERenderParameterFormat{
   ERenderParameterFormat_Unknown,
   ERenderParameterFormat_Float1,
   ERenderParameterFormat_Float2,
   ERenderParameterFormat_Float3,
   ERenderParameterFormat_Float4,
   ERenderParameterFormat_Float3x3,
   ERenderParameterFormat_Float4x3,
   ERenderParameterFormat_Float4x4,
};

//============================================================
// <T>渲染程序常量工具。</T>
//============================================================
class MO_FG_DECLARE RRenderParameterFormat{
public:
   static ERenderParameterFormat Parse(TCharC* pValue, ERenderParameterFormat formatCd = ERenderParameterFormat_Unknown);
   static TCharC* Format(ERenderParameterFormat formatCd);
};

//============================================================
// <T>渲染程序属性类型。</T>
//============================================================
enum ERenderAttributeFormat{
   ERenderAttributeFormat_Unknown,
   ERenderAttributeFormat_Float1,
   ERenderAttributeFormat_Float2,
   ERenderAttributeFormat_Float3,
   ERenderAttributeFormat_Float4,
   ERenderAttributeFormat_Byte4,
   ERenderAttributeFormat_Byte4Normal,
};

//============================================================
// <T>渲染程序属性工具。</T>
//============================================================
class MO_FG_DECLARE RRenderAttributeFormat{
public:
   static ERenderAttributeFormat Parse(TCharC* pValue, ERenderAttributeFormat formatCd = ERenderAttributeFormat_Unknown);
   static TCharC* Format(ERenderAttributeFormat formatCd);
   static TInt CalculateSize(ERenderAttributeFormat formatCd);
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

////============================================================
//// <T>渲染取样器类型。</T>
////============================================================
//enum ERenderSampler{
//   ERenderSampler_Unknown,
//   ERenderSampler_Ambient,
//   ERenderSampler_Diffuse,
//   ERenderSampler_Alpha,
//   ERenderSampler_Normal,
//   ERenderSampler_Height,
//   ERenderSampler_SpecularColor,
//   ERenderSampler_SpecularLevel,
//   ERenderSampler_TransmittanceColor,
//   ERenderSampler_TransmittanceLevel,
//   ERenderSampler_Light,
//   ERenderSampler_Reflect,
//   ERenderSampler_Refract,
//   ERenderSampler_Emissive,
//   ERenderSampler_Environment,
//   ERenderSampler_LayerMerge,
//   ERenderSampler_Layer1,
//   ERenderSampler_Layer2,
//   ERenderSampler_Layer3,
//   ERenderSampler_Layer4,
//   ERenderSampler_PackDiffuse,
//   ERenderSampler_PackNormal,
//   ERenderSampler_PackSpecular,
//   ERenderSampler_PackTransmittance,
//   ERenderSampler_PackLight,
//   ERenderSampler_LightDepth,
//   ERenderSampler_Count,
//};
//
////============================================================
//// <T>渲染取样器类型枚举。</T>
////============================================================
//class MO_FG_DECLARE FRenderProgramSamplerEnumerator : public FEnumerator{
//public:
//   TResult Construct();
//public:
//   TInt ParsePack(TInt samplerCd);
//};
//
////============================================================
//// <T>渲染取样器类型工具。</T>
////============================================================
//class MO_FG_DECLARE RRenderSampler : public REnumerator<FRenderProgramSamplerEnumerator>{
//public:
//   //------------------------------------------------------------
//   // <T>根据取样器获得打包取样器。</T>
//   static TInt ParsePack(TInt samplerCd){
//      return _pInstance->ParsePack(samplerCd);
//   }
//};

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
   // 配置标志
   TBool setuped;
public:
   // 配置实体
   TBool optionInstanced;
   // 配置绑定器
   TBool optionBinder;
   // 配置材质
   TBool optionMaterial;
   // 配置法线
   TBool optionNormal;
   // 配置法线完整 (是否含有副法线和切线)
   TBool optionNormalFull;
   // 配置法线缩放 (是否使用法线缩放)
   TBool optionNormalScale;
   // 配置骨骼缩放
   TBool optionBoneScale;
   // 配置深度
   TBool optionDepth;
   // 配置阴影
   TBool optionShadow;
   // 配置自阴影
   TBool optionShadowSelf;
   // 配置光源贴图
   TBool optionLightMap;
   // 配置透射
   TBool optionTransmittance;
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
   // 支持凹凸相机技术
   TBool supportViewBump;
   // 支持环境技术
   TBool supportAmbient;
   // 支持散射技术
   TBool supportDiffuse;
   // 支持环境散射技术
   TBool supportViewDiffuse;
   // 支持法线技术
   TBool supportNormal;
   // 支持高光技术
   TBool supportSpecularColor;
   // 支持高光级别技术
   TBool supportSpecularLevel;
   // 支持高光相机技术
   TBool supportViewSpecular;
   // 支持高光相机级别技术
   TBool supportViewSpecularLevel;
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
public:
   // 顶点个数
   TBool vertexCount;
   // 骨头个数
   TBool boneCount;
public:
   // 顶点缓冲
   //TBool vertexBuffers[ERenderAttribute_Count];
   // 取样器集合
   //TBool samplers[ERenderSampler_Count];
public:
   SRenderableDescriptor();
public:
   //------------------------------------------------------------
   // <T>测试是否含有指定缓冲。</T>
   //MO_INLINE TBool ContainsBuffer(ERenderAttribute bufferCd){
   //   return vertexBuffers[bufferCd];
   //}
   //------------------------------------------------------------
   // <T>测试是否含有指定取样器。</T>
   //MO_INLINE TBool ContainsSampler(TCharC* pTextureCode){
   //   return samplers[samplerCd];
   //}
public:
   TResult Assign(SRenderableDescriptor* pFlag);
   TResult Build();
   TResult Reset();
};

////============================================================
//// <T>渲染项目信息。</T>
////============================================================
//struct MO_FG_DECLARE SRenderableItem{
//public:
//   // 坐标
//   SFloatPoint3 location;
//   // 尺寸
//   SFloatSize3 size;
//   // 旋转
//   SFloatVector3 rotation;
//   // 背景颜色
//   SFloatColor4 groundColor;
//   // 纹理坐标
//   SFloatCoord coord;
//   // 矩阵
//   SFloatMatrix3d matrix;
//public:
//   //------------------------------------------------------------
//   // <T>构造浮点数颜色。</T>
//   MO_INLINE SRenderableItem(){
//   }
//};
////------------------------------------------------------------
//typedef MO_FG_DECLARE TFixVector<SRenderableItem, 64> SRenderableItems;
//
////============================================================
//// <T>渲染信息。</T>
////============================================================
//struct MO_FG_DECLARE SRenderable{
//public:
//   SRenderableItems items;
//public:
//   //------------------------------------------------------------
//   // <T>构造浮点数颜色。</T>
//   MO_INLINE SRenderable(){
//   }
//public:
//   //------------------------------------------------------------
//   // <T>收集一个项目。</T>
//   MO_INLINE SRenderableItem& Alloc(){
//      TInt index = items.Count();
//      items.SetCount(index + 1);
//      return items.Get(index);
//   }
//   //------------------------------------------------------------
//   // <T>增加要给项目。</T>
//   MO_INLINE void Push(SRenderableItem& item){
//      items.Push(item);
//   }
//};

//============================================================
// <T>渲染配置信息。</T>
//============================================================
class MO_FG_DECLARE SRenderableOptions{
public:
   // 配置实例
   TBool optionInstanced;
   // 配置绑定器
   TBool optionBinder;
   // 配置材质
   TBool optionMaterial;
   // 配置法线
   TBool optionNormal;
   // 配置法线完整 (是否含有副法线和切线)
   TBool optionNormalFull;
   // 配置法线缩放 (是否使用法线缩放)
   TBool optionNormalScale;
   // 配置骨骼缩放
   TBool optionBoneScale;
   // 配置深度
   TBool optionDepth;
   // 配置阴影
   TBool optionShadow;
   // 配置自阴影
   TBool optionShadowSelf;
   // 配置光源贴图
   TBool optionLightMap;
   // 配置透射
   TBool optionTransmittance;
public:
   SRenderableOptions();
};

//============================================================
// <T>渲染对象属性。</T>
//============================================================
class MO_FG_DECLARE FRenderableAttribute : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderableAttribute, FInstance);
protected:
   // 有效性
   TBool _valid;
   // 代码
   TString _code;
   // 数据格式
   ERenderAttributeFormat _formatCd;
   // 数据缓冲中的偏移位置
   TInt _offset;
   // 顶点数据缓冲
   FRenderVertexBuffer* _pVertexBuffer;
   //TInt _slot;
public:
   FRenderableAttribute();
   MO_ABSTRACT ~FRenderableAttribute();
public:
   //------------------------------------------------------------
   // <T>判断是否有效。</T>
   MO_INLINE TBool IsValid(){
      return _valid;
   }
   //------------------------------------------------------------
   // <T>判断是否指定代码。</T>
   MO_INLINE TBool IsCode(TCharC* pCode){
      return _code.Equals(pCode);
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
   // <T>获得属性类型。</T>
   MO_INLINE ERenderAttributeFormat FormatCd(){
      return _formatCd;
   }
   //------------------------------------------------------------
   // <T>设置属性类型。</T>
   MO_INLINE void SetFormatCd(ERenderAttributeFormat formatCd){
      _formatCd = formatCd;
   }
   //------------------------------------------------------------
   // <T>获得偏移。</T>
   MO_INLINE TInt Offset(){
      return _offset;
   }
   //------------------------------------------------------------
   // <T>设置偏移。</T>
   MO_INLINE void SetOffset(TInt offset){
      _offset = offset;
   }
   //------------------------------------------------------------
   // <T>获得顶点缓冲。</T>
   MO_INLINE FRenderVertexBuffer* VertexBuffer(){
      return _pVertexBuffer;
   }
   //------------------------------------------------------------
   // <T>设置顶点缓冲。</T>
   MO_INLINE void SetVertexBuffer(FRenderVertexBuffer* pVertexBuffer){
      _pVertexBuffer = pVertexBuffer;
   }
   ////------------------------------------------------------------
   //// <T>获得插槽。</T>
   //MO_INLINE TInt Slot(){
   //   return _slot;
   //}
   ////------------------------------------------------------------
   //// <T>设置插槽。</T>
   //MO_INLINE void SetSlot(TInt slot){
   //   _slot = slot;
   //}
public:
   TBool CheckValid();
};
//------------------------------------------------------------
typedef MO_FG_DECLARE GPtr<FRenderableAttribute> GRenderableAttributePtr;
typedef MO_FG_DECLARE GPtrs<FRenderableAttribute> GRenderableAttributePtrs;

//============================================================
// <T>渲染对象数据。</T>
//============================================================
class MO_FG_DECLARE FRenderableData : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderableData, FInstance);
protected:
   // 顶点个数
   TInt _vertexCount;
   // 属性集合
   GRenderableAttributePtrs _attributes;
   // 索引流
   FRenderIndexBuffer* _pIndexBuffer;
public:
   FRenderableData();
   MO_ABSTRACT ~FRenderableData();
public:
   //------------------------------------------------------------
   // <T>获得顶点个数。</T>
   MO_INLINE TInt VertexCount(){
      return _vertexCount;
   }
   //------------------------------------------------------------
   // <T>设置顶点个数。</T>
   MO_INLINE void SetVertexCount(TInt vertexCount){
      _vertexCount = vertexCount;
   }
   //------------------------------------------------------------
   // <T>获得属性集合。</T>
   MO_INLINE GRenderableAttributePtrs& Attributes(){
      return _attributes;
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
public:
   FRenderableAttribute* AttributeFind(TCharC* pCode);
   FRenderableAttribute* AttributeGet(TCharC* pCode);
   TResult AttributePush(FRenderableAttribute* pAttribute);
   TResult AttributeRemove(FRenderableAttribute* pAttribute);
public:
   TResult Assign(FRenderableData* pData);
};
//------------------------------------------------------------
typedef MO_FG_DECLARE GPtr<FRenderableData> GRenderableDataPtr;

//============================================================
// <T>渲染对象取样器。</T>
//============================================================
class MO_FG_DECLARE FRenderableSampler : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderableSampler, FInstance);
protected:
   TString _code;
   TInt _slot;
   FRenderTexture* _pTexture;
public:
   FRenderableSampler();
   MO_ABSTRACT ~FRenderableSampler();
public:
   //------------------------------------------------------------
   // <T>判断是否指定代码。</T>
   MO_INLINE TBool IsCode(TCharC* pCode){
      return _code.Equals(pCode);
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
   // <T>获得插槽。</T>
   MO_INLINE TInt Slot(){
      return _slot;
   }
   //------------------------------------------------------------
   // <T>设置插槽。</T>
   MO_INLINE void SetSlot(TInt slot){
      _slot = slot;
   }
   //------------------------------------------------------------
   // <T>获得纹理。</T>
   MO_INLINE FRenderTexture* Texture(){
      return _pTexture;
   }
   //------------------------------------------------------------
   // <T>设置纹理。</T>
   MO_INLINE void SetName(FRenderTexture* _pTexture){
      _pTexture = _pTexture;
   }
};
//------------------------------------------------------------
typedef MO_FG_DECLARE GPtr<FRenderableSampler> GRenderableSamplerPtr;
typedef MO_FG_DECLARE GPtrs<FRenderableSampler> GRenderableSamplerPtrs;

//============================================================
// <T>渲染对象效果。</T>
//============================================================
struct MO_FG_DECLARE FRenderableEffect : FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderableEffect, FInstance);
public:
   FEffect* _pEffect;
   FRenderLayout* _pLayout;
public:
   FRenderableEffect();
   MO_ABSTRACT ~FRenderableEffect();
public:
   //------------------------------------------------------------
   // <T>获得效果器。</T>
   MO_INLINE FEffect* Effect(){
      return _pEffect;
   }
   //------------------------------------------------------------
   // <T>设置效果器。</T>
   MO_INLINE void SetEffect(FEffect* pEffect){
      _pEffect = pEffect;
   }
   //------------------------------------------------------------
   // <T>获得布局。</T>
   MO_INLINE FRenderLayout* Layout(){
      return _pLayout;
   }
   //------------------------------------------------------------
   // <T>设置布局。</T>
   MO_INLINE void SetLayout(FRenderLayout* pLayout){
      _pLayout = pLayout;
   }
};
//------------------------------------------------------------
typedef MO_FG_DECLARE GPtrs<FRenderableEffect> GRenderableEffectPtrs;

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
   // 渲染信息
   FRenderableInfo* _pVisualInfo;
   // 材质
   GMaterialPtr _material;
   // 引用材质
   GMaterialPtr _materialReference;
   // 数据
   GRenderableDataPtr _data;
   // 取样集合
   GRenderableSamplerPtrs _samplers;
   // 激活效果器
   FRenderableEffect* _pActiveEffect;
   // 效果器集合
   GRenderableEffectPtrs _effects;
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
   // <T>获得数据。</T>
   MO_INLINE FRenderableData* Data(){
      return _data;
   }
   //------------------------------------------------------------
   // <T>设置数据。</T>
   MO_INLINE void SetData(FRenderableData* pData){
      return _data = pData;
   }
   //------------------------------------------------------------
   // <T>获得属性集合。</T>
   MO_INLINE GRenderableAttributePtrs& Attributes(){
      MO_ASSERT(_data);
      return _data->Attributes();
   }
   //------------------------------------------------------------
   // <T>获得索引流。</T>
   MO_INLINE FRenderIndexBuffer* IndexBuffer(){
      MO_ASSERT(_data);
      return _data->IndexBuffer();
   }
   //------------------------------------------------------------
   // <T>设置索引流。</T>
   MO_INLINE void SetIndexBuffer(FRenderIndexBuffer* pIndexBuffer){
      MO_ASSERT(_data);
      _data->SetIndexBuffer(pIndexBuffer);
   }
   //------------------------------------------------------------
   // <T>获得取样集合。</T>
   MO_INLINE GRenderableSamplerPtrs& Samplers(){
      return _samplers;
   }
   //------------------------------------------------------------
   // <T>获得激活效果器。</T>
   MO_OVERRIDE FRenderableEffect* ActiveEffect(){
      return _pActiveEffect;
   }
   //------------------------------------------------------------
   // <T>设置激活效果器。</T>
   MO_INLINE void SetActiveEffect(FRenderableEffect* pEffect){
      _pActiveEffect = pEffect;
   }
   //------------------------------------------------------------
   // <T>获得效果器集合。</T>
   MO_OVERRIDE GRenderableEffectPtrs& Effects(){
      return _effects;
   }
public:
   FRenderableAttribute* AttributeFind(TCharC* pCode);
   FRenderableAttribute* AttributeGet(TCharC* pCode);
   FRenderableSampler* SamplerFind(TCharC* pCode);
   FRenderableSampler* SamplerGet(TCharC* pCode);
   TResult SamplerPush(FRenderableSampler* pSampler);
   TResult SamplerRemove(FRenderableSampler* pSampler);
   FRenderableEffect* EffectFind(TCharC* pName);
   FRenderableEffect* EffectBind(FEffect* pEffect);
public:
   MO_ABSTRACT TResult CalculateModelMatrix(SFloatMatrix3d& matrix);
   MO_ABSTRACT TInt CalculateBoneMatrix(SFloatMatrix3d* pMatrix, TInt offset = 0, TInt capacity = 0);
   MO_ABSTRACT TResult BuildDescriptor();
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
