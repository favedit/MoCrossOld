#ifndef __MO_FG_RENDER_H__
#define __MO_FG_RENDER_H__
//************************************************************

#ifndef __MO_FG_COMMON_H__
#include "MoFgCommon.h"
#endif // __MO_FG_COMMON_H__

#ifndef __MO_FG_CORE_H__
#include "MoFgCore.h"
#endif // __MO_FG_CORE_H__

#ifndef __MO_FG_BASE_H__
#include "MoFgBase.h"
#endif // __MO_FG_BASE_H__

#ifndef __MO_FG_VISUAL_H__
#include "MoFgVisual.h"
#endif // __MO_FG_VISUAL_H__

#define MO_EG_RENDER_STS_GROUP   0x0001
#define MO_EG_RENDER_STS_DEVICE  0x0001
#define MO_EG_CONST_RESERVE      12
#define MO_EG_CONST_MATRIX_MAX   1024
#define MO_EG_CONST_INSTANCE_MAX 256

MO_NAMESPACE_BEGIN

//============================================================
// <T>类定义。</T>
//============================================================
class FEffect;
class FRenderable;
class FRenderVertexStream;
class FRenderVertexBuffer;
class FRenderIndexBuffer;
class FRenderTexture;
class FRenderTextures;
class FRenderFlatTexture;
class FRenderCubeTexture;
class FRenderProgram;
class FRenderDevice;
class FPipeline;
class FPipelinePass;

//============================================================
// <T>渲染编号。</T>
//============================================================
union SRenderId{
public:
   TInt32 int32;
   TUint32 uint32;
   TInt64 int64;
   TUint64 uint64;
public:
   SRenderId(){
      uint64 = 0;
   }
};
typedef SRenderId TRenderId;

//============================================================
// <T>渲染能力。</T>
//============================================================
struct MO_FG_DECLARE FRenderCapability : FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderCapability, FInstance);
protected:
   TString _code;
   TString _vendor;
   TString _version;
   TString _shaderVertexVersion;
   TString _shaderFragmentVersion;
protected:
   TInt _vertexCountLimit;
   TInt _vertexConstLimit;
   TInt _vertexAttributeLimit;
   TInt _vertexTemporaryLimit;
   TInt _vertexOpcodeLimit;
   TInt _fragmentConstLimit;
   TInt _fragmentTemporaryLimit;
   TInt _fragmentOpcodeLimit;
   TInt _varyingLimit;
   TInt _samplerLimit;
   TInt _samplerSizeLimit;
   TInt _renderTargetLimit;
public:
   FRenderCapability();
   MO_ABSTRACT ~FRenderCapability();
public:
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
   // <T>获得提供商。</T>
   MO_INLINE TCharC* Vendor(){
      return _vendor;
   }
   //------------------------------------------------------------
   // <T>设置提供商。</T>
   MO_INLINE void SetVendor(TCharC* pVendor){
      _vendor = pVendor;
   }
   //------------------------------------------------------------
   // <T>获得版本。</T>
   MO_INLINE TCharC* Version(){
      return _version;
   }
   //------------------------------------------------------------
   // <T>设置版本。</T>
   MO_INLINE void SetVersion(TCharC* pVersion){
      _version = pVersion;
   }
   //------------------------------------------------------------
   // <T>获得顶点脚本版本。</T>
   MO_INLINE TCharC* ShaderVertexVersion(){
      return _shaderVertexVersion;
   }
   //------------------------------------------------------------
   // <T>设置顶点脚本版本。</T>
   MO_INLINE void SetShaderVertexVersion(TCharC* pShaderVertexVersion){
      _shaderVertexVersion = pShaderVertexVersion;
   }
   //------------------------------------------------------------
   // <T>获得像素脚本版本。</T>
   MO_INLINE TCharC* ShaderFragmentVersion(){
      return _shaderFragmentVersion;
   }
   //------------------------------------------------------------
   // <T>设置像素脚本版本。</T>
   MO_INLINE void SetShaderFragmentVersion(TCharC* pShaderFragmentVersion){
      _shaderFragmentVersion = pShaderFragmentVersion;
   }
public:
   //------------------------------------------------------------
   // <T>获得顶点个数限制。</T>
   MO_INLINE TInt VertexCountLimit(){
      return _vertexCountLimit;
   }
   //------------------------------------------------------------
   // <T>设置顶点个数限制。</T>
   MO_INLINE void SetVertexCountLimit(TInt vertexCountLimit){
      _vertexCountLimit = vertexCountLimit;
   }
   //------------------------------------------------------------
   // <T>获得顶点常量限制。</T>
   MO_INLINE TInt VertexConstLimit(){
      return _vertexConstLimit;
   }
   //------------------------------------------------------------
   // <T>设置顶点常量限制。</T>
   MO_INLINE void SetVertexConstLimit(TInt vertexConstLimit){
      _vertexConstLimit = vertexConstLimit;
   }
   //------------------------------------------------------------
   // <T>获得顶点属性限制。</T>
   MO_INLINE TInt VertexAttributeLimit(){
      return _vertexAttributeLimit;
   }
   //------------------------------------------------------------
   // <T>设置顶点属性限制。</T>
   MO_INLINE void SetVertexAttributeLimit(TInt vertexAttributeLimit){
      _vertexAttributeLimit = vertexAttributeLimit;
   }
   //------------------------------------------------------------
   // <T>获得顶点临时变量限制。</T>
   MO_INLINE TInt VertexTemporaryLimit(){
      return _vertexTemporaryLimit;
   }
   //------------------------------------------------------------
   // <T>设置顶点临时变量限制。</T>
   MO_INLINE void SetVertexTemporaryLimit(TInt vertexTemporaryLimit){
      _vertexTemporaryLimit = vertexTemporaryLimit;
   }
   //------------------------------------------------------------
   // <T>获得顶点代码限制。</T>
   MO_INLINE TInt VertexOpcodeLimit(){
      return _vertexOpcodeLimit;
   }
   //------------------------------------------------------------
   // <T>设置顶点代码限制。</T>
   MO_INLINE void SetVertexOpcodeLimit(TInt vertexOpcodeLimit){
      _vertexOpcodeLimit = vertexOpcodeLimit;
   }
   //------------------------------------------------------------
   // <T>获得像素常量限制。</T>
   MO_INLINE TInt FragmentConstLimit(){
      return _fragmentConstLimit;
   }
   //------------------------------------------------------------
   // <T>设置像素常量限制。</T>
   MO_INLINE void SetFragmentConstLimit(TInt fragmentConstLimit){
      _fragmentConstLimit = fragmentConstLimit;
   }
   //------------------------------------------------------------
   // <T>获得像素临时变量限制。</T>
   MO_INLINE TInt FragmentTemporaryLimit(){
      return _fragmentTemporaryLimit;
   }
   //------------------------------------------------------------
   // <T>设置像素临时变量限制。</T>
   MO_INLINE void SetFragmentTemporaryLimit(TInt fragmentTemporaryLimit){
      _fragmentTemporaryLimit = fragmentTemporaryLimit;
   }
   //------------------------------------------------------------
   // <T>获得像素代码限制。</T>
   MO_INLINE TInt FragmentOpcodeLimit(){
      return _fragmentOpcodeLimit;
   }
   //------------------------------------------------------------
   // <T>设置像素代码限制。</T>
   MO_INLINE void SetFragmentOpcodeLimit(TInt fragmentOpcodeLimit){
      _fragmentOpcodeLimit = fragmentOpcodeLimit;
   }
   //------------------------------------------------------------
   // <T>获得插值器限制。</T>
   MO_INLINE TInt VaryingLimit(){
      return _varyingLimit;
   }
   //------------------------------------------------------------
   // <T>设置插值器限制。</T>
   MO_INLINE void SetVaryingLimit(TInt varyingLimit){
      _varyingLimit = varyingLimit;
   }
   //------------------------------------------------------------
   // <T>获得采样器限制。</T>
   MO_INLINE TInt SamplerLimit(){
      return _samplerLimit;
   }
   //------------------------------------------------------------
   // <T>设置采样器限制。</T>
   MO_INLINE void SetSamplerLimit(TInt samplerLimit){
      _samplerLimit = samplerLimit;
   }
   //------------------------------------------------------------
   // <T>获得采样器尺寸限制。</T>
   MO_INLINE TInt SamplerSizeLimit(){
      return _samplerSizeLimit;
   }
   //------------------------------------------------------------
   // <T>设置采样器尺寸限制。</T>
   MO_INLINE void SetSamplerSizeLimit(TInt samplerSizeLimit){
      _samplerSizeLimit = samplerSizeLimit;
   }
   //------------------------------------------------------------
   // <T>获得渲染目标个数限制。</T>
   MO_INLINE TInt RenderTargetLimit(){
      return _renderTargetLimit;
   }
   //------------------------------------------------------------
   // <T>设置渲染目标个数限制。</T>
   MO_INLINE void SetRenderTargetLimit(TInt renderTargetLimit){
      _renderTargetLimit = renderTargetLimit;
   }
public:
   void Track();
};

//============================================================
// <T>渲染统计器。</T>
//============================================================
class MO_FG_DECLARE FRenderStatistics : public FStatistics
{
   MO_CLASS_DECLARE_INHERITS(FRenderStatistics, FStatistics);
protected:
   TSpeedStatistics _frameStatistics;
   TSpeedStatistics _frameDrawStatistics;
   TInt _optionFillCount;
   TInt _optionDeepCount;
   TInt _optionCullCount;
   TInt _optionBlendCount;
   TInt _programCount;
   TInt _programConstLength;
   TInt _programConstCount;
   TInt _vertexBufferCount;
   TInt _samplerCount;
   TInt _drawFaceCount;
   TInt _drawCount;
public:
   FRenderStatistics();
   MO_ABSTRACT ~FRenderStatistics();
public:
   //------------------------------------------------------------
   // <T>获得帧信息统计。</T>
   MO_INLINE TSpeedStatistics& FrameStatistics(){
      return _frameStatistics;
   }
   //------------------------------------------------------------
   // <T>获得帧绘制统计。</T>
   MO_INLINE TSpeedStatistics& FrameDrawStatistics(){
      return _frameDrawStatistics;
   }
   //------------------------------------------------------------
   // <T>获得填充次数。</T>
   MO_INLINE TInt OptionFillCount(){
      return _optionFillCount;
   }
   //------------------------------------------------------------
   // <T>获得深度次数。</T>
   MO_INLINE TInt OptionDeepCount(){
      return _optionDeepCount;
   }
   //------------------------------------------------------------
   // <T>获得剪裁次数。</T>
   MO_INLINE TInt OptionCullCount(){
      return _optionCullCount;
   }
   //------------------------------------------------------------
   // <T>获得融合次数。</T>
   MO_INLINE TInt OptionBlendCount(){
      return _optionBlendCount;
   }
   //------------------------------------------------------------
   // <T>获得程序次数。</T>
   MO_INLINE TInt ProgramCount(){
      return _programCount;
   }
   //------------------------------------------------------------
   // <T>获得程序常量长度。</T>
   MO_INLINE TInt ProgramConstLength(){
      return _programCount;
   }
   //------------------------------------------------------------
   // <T>获得程序常量次数。</T>
   MO_INLINE TInt ProgramConstCount(){
      return _programConstCount;
   }
   //------------------------------------------------------------
   // <T>获得顶点缓冲次数。</T>
   MO_INLINE TInt VertexBufferCount(){
      return _vertexBufferCount;
   }
   //------------------------------------------------------------
   // <T>获得取样器次数。</T>
   MO_INLINE TInt SamplerCount(){
      return _samplerCount;
   }
   //------------------------------------------------------------
   // <T>获得绘制面总数。</T>
   MO_INLINE TInt DrawFaceCount(){
      return _drawFaceCount;
   }
   //------------------------------------------------------------
   // <T>获得绘制次数。</T>
   MO_INLINE TInt DrawCount(){
      return _drawCount;
   }
public:
   void UpdateOptionFillCount();
   void UpdateOptionDeepCount();
   void UpdateOptionCullCount();
   void UpdateOptionBlendCount();
   void UpdateProgramCount();
   void UpdateProgramCount(TInt constLength);
   void UpdateVertexBufferCount();
   void UpdateSamplerCount();
   void UpdateDraw(TInt faceCount);
public:
   MO_OVERRIDE TResult Reset();
public:
   MO_OVERRIDE TResult Dump(MString* pDump);
};

//============================================================
// <T>渲染对象。</T>
//============================================================
class MO_FG_DECLARE FRenderObject : public FGraphicObject
{
   MO_CLASS_DECLARE_INHERITS(FRenderObject, FGraphicObject);
protected:
   FRenderDevice* _pDevice;
public:
   FRenderObject();
   MO_ABSTRACT ~FRenderObject();
public:
   //------------------------------------------------------------
   // <T>获得设备。</T>
   MO_INLINE FRenderDevice* Device(){
      return _pDevice;
   }
   //------------------------------------------------------------
   // <T>设置设备。</T>
   MO_INLINE void SetDevice(FRenderDevice* pDevice){
      _pDevice = pDevice;
   }
public:
   MO_ABSTRACT TResult Suspend();
   MO_ABSTRACT TResult Resume();
};

//============================================================
// <T>渲染实例对象。</T>
//============================================================
class MO_FG_DECLARE FRenderInstance : public FRenderObject
{
   MO_CLASS_DECLARE_INHERITS(FRenderInstance, FRenderObject);
protected:
   TRenderId _renderId;
public:
   FRenderInstance();
   MO_ABSTRACT ~FRenderInstance();
public:
   //------------------------------------------------------------
   // <T>获得渲染编号。</T>
   MO_INLINE TRenderId RenderId(){
      return _renderId;
   }
};

//============================================================
// <T>渲染顶点缓冲。</T>
//============================================================
class MO_FG_DECLARE FRenderVertexBuffer : public FRenderInstance
{
   MO_CLASS_ABSTRACT_DECLARE_INHERITS(FRenderVertexBuffer, FRenderInstance);
protected:
   TInt _stride;
   TInt _count;
   TInt _dataLength;
   FDataStream* _pDataStream;
public:
   FRenderVertexBuffer();
   MO_ABSTRACT ~FRenderVertexBuffer();
public:
   //------------------------------------------------------------
   // <T>获得顶点字节数。</T>
   MO_INLINE TInt Stride(){
      return _stride;
   }
   //------------------------------------------------------------
   // <T>设置顶点字节数。</T>
   MO_INLINE void SetStride(TInt stride){
      _stride = stride;
   }
   //------------------------------------------------------------
   // <T>获得顶点个数。</T>
   MO_INLINE TInt Count(){
      return _count;
   }
   //------------------------------------------------------------
   // <T>设置顶点个数。</T>
   MO_INLINE void SetCount(TInt count){
      _count = count;
   }
   //------------------------------------------------------------
   // <T>获得数据长度。</T>
   MO_INLINE TInt DataLength(){
      return _dataLength;
   }
   //------------------------------------------------------------
   // <T>获得数据流。</T>
   MO_INLINE FDataStream* DataStream(){
      return _pDataStream;
   }
   //------------------------------------------------------------
   // <T>获得数据内存。</T>
   MO_INLINE TAny* DataMemory(){
      TAny* pMemory = NULL;
      if(_pDataStream != NULL){
         pMemory = _pDataStream->Memory();
      }
      return pMemory;
   }
   //------------------------------------------------------------
   // <T>获得数据内存。</T>
   template <class T>
   MO_INLINE T* DataMemory(){
      T* pMemory = NULL;
      if(_pDataStream != NULL){
         pMemory = (T*)_pDataStream->Memory();
      }
      return pMemory;
   }
public:
   MO_VIRTUAL TResult Upload(TByteC* pData, TInt length) = 0;
public:
   MO_ABSTRACT TResult BuildData();
   MO_ABSTRACT TResult UploadData(TBool reserve = EFalse);
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FRenderVertexBuffer*> FRenderVertexBufferCollection;
typedef MO_FG_DECLARE GPtrs<FRenderVertexBuffer> GRenderVertexBufferPtrs;
typedef MO_FG_DECLARE GPtrLooper<FRenderVertexBuffer> GRenderVertexBufferLooper;

//============================================================
// <T>渲染顶点流信息。</T>
//============================================================
struct MO_FG_DECLARE SRenderVertexStreamInfo
{
public:
   ERenderVertexBuffer bufferCd;
   ERenderVertexFormat formatCd;
   TInt offset;
public:
   //------------------------------------------------------------
   // <T>构造渲染顶点流信息。</T>
   SRenderVertexStreamInfo(){
      bufferCd = ERenderVertexBuffer_Unknown;
      formatCd = ERenderVertexFormat_Unknown;
      offset = 0;
   }
};

//============================================================
// <T>渲染顶点流。</T>
//============================================================
class MO_FG_DECLARE FRenderVertexStream : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderVertexStream, FInstance);
protected:
   SRenderVertexStreamInfo _info;
   FRenderVertexBuffer* _pVertexBuffer;
public:
   FRenderVertexStream();
   MO_ABSTRACT ~FRenderVertexStream();
public:
   //------------------------------------------------------------
   // <T>获得描述信息。</T>
   MO_INLINE SRenderVertexStreamInfo& Info(){
      return _info;
   }
   //------------------------------------------------------------
   // <T>设置描述信息。</T>
   MO_INLINE void SetInfo(const SRenderVertexStreamInfo& info){
      _info = info;
   }
   //------------------------------------------------------------
   // <T>获得缓冲类型。</T>
   MO_INLINE ERenderVertexBuffer BufferCd(){
      return _info.bufferCd;
   }
   //------------------------------------------------------------
   // <T>设置顶点字节数。</T>
   MO_INLINE void SetBufferCd(ERenderVertexBuffer bufferCd){
      _info.bufferCd = bufferCd;
   }
   //------------------------------------------------------------
   // <T>获得格式类型。</T>
   MO_INLINE ERenderVertexFormat FormatCd(){
      return _info.formatCd;
   }
   //------------------------------------------------------------
   // <T>设置格式类型。</T>
   MO_INLINE void SetFormatCd(ERenderVertexFormat formatCd){
      _info.formatCd = formatCd;
   }
   //------------------------------------------------------------
   // <T>获得偏移位置。</T>
   MO_INLINE TInt Offset(){
      return _info.offset;
   }
   //------------------------------------------------------------
   // <T>设置偏移位置。</T>
   MO_INLINE void SetOffset(TInt offset){
      _info.offset = offset;
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
public:
   TInt Stride();
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FRenderVertexStream*> FRenderVertexStreamCollection;

//============================================================
// <T>渲染顶点流集合。</T>
//============================================================
class MO_FG_DECLARE FRenderVertexStreams : public FInstance
{
public:
   TInt _vertexCount;
   TInt _instanceSize;
   TInt _instanceCount;
   FRenderVertexBufferCollection* _pBuffers;
   FRenderVertexStreamCollection* _pStreams;
public:
   FRenderVertexStreams();
   MO_ABSTRACT ~FRenderVertexStreams();
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
   // <T>获得实例尺寸。</T>
   MO_INLINE TInt InstanceSize(){
      return _instanceSize;
   }
   //------------------------------------------------------------
   // <T>设置实例尺寸。</T>
   MO_INLINE void SetInstanceSize(TInt instanceSize){
      _instanceSize = instanceSize;
   }
   //------------------------------------------------------------
   // <T>获得实例个数。</T>
   MO_INLINE TInt InstanceCount(){
      return _instanceCount;
   }
   //------------------------------------------------------------
   // <T>设置实例个数。</T>
   MO_INLINE void SetInstanceCount(TInt instanceCount){
      _instanceCount = instanceCount;
   }
   //------------------------------------------------------------
   // <T>获得顶点缓冲集合。</T>
   MO_INLINE FRenderVertexBufferCollection* Buffers(){
      return _pBuffers;
   }
   //------------------------------------------------------------
   // <T>获得顶点流集合。</T>
   MO_INLINE FRenderVertexStreamCollection* Streams(){
      return _pStreams;
   }
public:
   TBool EqualsDescription(FRenderVertexStreams* pStream);
public:
   FRenderVertexStream* FindStream(ERenderVertexBuffer bufferCd);
   FRenderVertexStream* GetStream(ERenderVertexBuffer bufferCd);
   TResult PushStream(FRenderVertexStream* pStream);
public:
   TResult Assign(FRenderVertexStreams* pStreams);
};

//============================================================
// <T>渲染索引缓冲。</T>
//============================================================
class MO_FG_DECLARE FRenderIndexBuffer : public FRenderObject
{
   MO_CLASS_ABSTRACT_DECLARE_INHERITS(FRenderIndexBuffer, FRenderObject);
protected:
   ERenderIndexStride _strideCd;
   TInt _count;
   TInt _instanceStride;
   TInt _instanceCount;
   TInt _dataLength;
   FDataStream* _pDataStream;
public:
   FRenderIndexBuffer();
   MO_ABSTRACT ~FRenderIndexBuffer();
public:
   //------------------------------------------------------------
   // <T>获得索引宽度。</T>
   MO_INLINE ERenderIndexStride StrideCd(){
      return _strideCd;
   }
   //------------------------------------------------------------
   // <T>设置索引宽度。</T>
   MO_INLINE void SetStrideCd(ERenderIndexStride strideCd){
      _strideCd = strideCd;
   }
   //------------------------------------------------------------
   // <T>获得三角形个数。</T>
   MO_INLINE TInt Count(){
      return _count;
   }
   //------------------------------------------------------------
   // <T>设置三角形个数。</T>
   MO_INLINE void SetCount(TInt count){
      _count = count;
   }
   //------------------------------------------------------------
   // <T>获得实例宽度。</T>
   MO_INLINE TInt InstanceStride(){
      return _instanceStride;
   }
   //------------------------------------------------------------
   // <T>设置实例宽度。</T>
   MO_INLINE void SetInstanceStride(TInt instanceStride){
      _instanceStride = instanceStride;
   }
   //------------------------------------------------------------
   // <T>获得实例个数。</T>
   MO_INLINE TInt InstanceCount(){
      return _instanceCount;
   }
   //------------------------------------------------------------
   // <T>设置实例个数。</T>
   MO_INLINE void SetInstanceCount(TInt instanceCount){
      _instanceCount = instanceCount;
   }
   //------------------------------------------------------------
   // <T>获得数据长度。</T>
   MO_INLINE TInt DataLength(){
      return _dataLength;
   }
   //------------------------------------------------------------
   // <T>获得数据流。</T>
   MO_INLINE FDataStream* DataStream(){
      return _pDataStream;
   }
   //------------------------------------------------------------
   // <T>获得数据内存。</T>
   MO_INLINE TAny* DataMemory(){
      TAny* pMemory = NULL;
      if(_pDataStream != NULL){
         pMemory = _pDataStream->Memory();
      }
      return pMemory;
   }
   //------------------------------------------------------------
   // <T>获得数据内存。</T>
   template <class T>
   MO_INLINE T* DataMemory(){
      T* pMemory = NULL;
      if(_pDataStream != NULL){
         pMemory = (T*)_pDataStream->Memory();
      }
      return pMemory;
   }
public:
   MO_ABSTRACT TResult OnSetup();
public:
   MO_VIRTUAL TResult Upload(TByteC* pData, TInt count) = 0;
public:
   MO_ABSTRACT TResult BuildData();
   MO_ABSTRACT TResult UploadData(TBool reserve = EFalse);
};
//------------------------------------------------------------
typedef MO_FG_DECLARE GPtrs<FRenderIndexBuffer> GRenderIndexBufferPtrs;
typedef MO_FG_DECLARE GPtrLooper<FRenderIndexBuffer> GRenderIndexBufferPtrLooper;

//============================================================
// <T>渲染来源。</T>
//============================================================
class MO_FG_DECLARE FRenderSource : public FString{
public:
   FRenderSource();
   MO_ABSTRACT ~FRenderSource();
public:
   MO_ABSTRACT TResult AppendCommet(TChar8C* pFormat, ...);
   MO_ABSTRACT TResult AppendSource(TChar8C* pFormat, ...);
public:
   MO_ABSTRACT TCharC* Build();
};

//============================================================
// <T>渲染器参数。</T>
//============================================================
class MO_FG_DECLARE FRenderShaderParameter : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderShaderParameter, FInstance);
protected:
   TString _name;
public:
   FRenderShaderParameter();
   MO_ABSTRACT ~FRenderShaderParameter();
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
};
//------------------------------------------------------------
typedef MO_FG_DECLARE GPtrDictionary<FRenderShaderParameter> GRenderShaderParameterDictionary;

//============================================================
// <T>渲染器属性。</T>
//============================================================
class MO_FG_DECLARE FRenderShaderAttribute : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderShaderAttribute, FInstance);
protected:
   TString _name;
   TInt _index;
public:
   FRenderShaderAttribute();
   MO_ABSTRACT ~FRenderShaderAttribute();
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
   // <T>获得索引。</T>
   MO_INLINE TInt Index(){
      return _index;
   }
   //------------------------------------------------------------
   // <T>设置索引。</T>
   MO_INLINE void SetIndex(TInt index){
      _index = index;
   }
};
//------------------------------------------------------------
typedef MO_FG_DECLARE GPtrDictionary<FRenderShaderAttribute> GRenderShaderAttributeDictionary;

//============================================================
// <T>渲染器。</T>
//============================================================
class MO_FG_DECLARE FRenderShader :
      public FRenderInstance,
      public IDispose
{
   MO_CLASS_ABSTRACT_DECLARE_INHERITS(FRenderShader, FRenderInstance);
protected:
   FRenderProgram* _pProgram;
   FRenderSource* _pSource;
   FRenderSource* _pCompileSource;
   GRenderShaderParameterDictionary _parameters;
   GRenderShaderAttributeDictionary _attributes;
public:
   FRenderShader();
   MO_ABSTRACT ~FRenderShader();
public:
   //------------------------------------------------------------
   // <T>获得程序。</T>
   MO_INLINE FRenderProgram* Program(){
      return _pProgram;
   }
   //------------------------------------------------------------
   // <T>设置程序。</T>
   MO_INLINE void SetProgram(FRenderProgram* pProgram){
      _pProgram = pProgram;
   }
   //------------------------------------------------------------
   // <T>获得脚本。</T>
   MO_INLINE FRenderSource* Source(){
      return _pSource;
   }
   //------------------------------------------------------------
   // <T>获得编译脚本。</T>
   MO_INLINE FRenderSource* CompileSource(){
      return _pCompileSource;
   }
   //------------------------------------------------------------
   // <T>获得渲染参数集合。</T>
   MO_INLINE GRenderShaderParameterDictionary& Parameters(){
      return _parameters;
   }
   //------------------------------------------------------------
   // <T>根据名称查找渲染参数。</T>
   MO_INLINE FRenderShaderParameter* ParameterFind(TCharC* pName){
      return _parameters.Find(pName);
   }
   //------------------------------------------------------------
   // <T>增加一个参数。</T>
   MO_INLINE TResult ParameterPush(FRenderShaderParameter* pParameter){
      MO_CHECK(pParameter, return ENull);
      _parameters.Set(pParameter->Name(), pParameter);
      return ESuccess;
   }
   //------------------------------------------------------------
   // <T>获得渲染属性集合。</T>
   MO_INLINE GRenderShaderAttributeDictionary& Attributes(){
      return _attributes;
   }
   //------------------------------------------------------------
   // <T>根据名称查找渲染属性。</T>
   MO_INLINE FRenderShaderAttribute* AttributeFind(TCharC* pName){
      return _attributes.Find(pName);
   }
   //------------------------------------------------------------
   // <T>增加一个属性。</T>
   MO_INLINE TResult AttributePush(FRenderShaderAttribute* pAttribute){
      MO_CHECK(pAttribute, return ENull);
      _attributes.Set(pAttribute->Name(), pAttribute);
      return ESuccess;
   }
public:
   MO_VIRTUAL TResult Setup() = 0;
   MO_VIRTUAL TResult Compile(TCharC* pSource) = 0;
public:
   TResult Build(TCharC* pSource);
};

//============================================================
// <T>顶点渲染器。</T>
//============================================================
class MO_FG_DECLARE FRenderVertexShader : public FRenderShader
{
   MO_CLASS_ABSTRACT_DECLARE_INHERITS(FRenderVertexShader, FRenderShader);
public:
   FRenderVertexShader();
   MO_ABSTRACT ~FRenderVertexShader();
};

//============================================================
// <T>像素渲染器。</T>
//============================================================
class MO_FG_DECLARE FRenderFragmentShader : public FRenderShader
{
   MO_CLASS_ABSTRACT_DECLARE_INHERITS(FRenderFragmentShader, FRenderShader);
public:
   FRenderFragmentShader();
   MO_ABSTRACT ~FRenderFragmentShader();
};

//============================================================
// <T>渲染脚本变换器。</T>
//============================================================
class MO_FG_DECLARE FRenderShaderTransformer : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderShaderTransformer, FInstance);
protected:
public:
   FRenderShaderTransformer();
   MO_ABSTRACT ~FRenderShaderTransformer();
public:
   MO_ABSTRACT TResult Convert(MString* pOutputScript, MString* pInputScript);
};
//------------------------------------------------------------
typedef MO_FG_DECLARE GPtr<FRenderShaderTransformer> GRenderShaderTransformerPtr;

//============================================================
// <T>渲染脚本优化器。</T>
//============================================================
class MO_FG_DECLARE FRenderShaderOptimizer : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderShaderOptimizer, FInstance);
protected:
public:
   FRenderShaderOptimizer();
   MO_ABSTRACT ~FRenderShaderOptimizer();
public:
   MO_ABSTRACT TResult Convert(MString* pOutputScript, MString* pInputScript);
};
//------------------------------------------------------------
typedef MO_FG_DECLARE GPtr<FRenderShaderOptimizer> GRenderShaderOptimizerPtr;

//============================================================
// <T>渲染程序。</T>
//============================================================
class MO_FG_DECLARE FRenderProgram :
      public FRenderObject,
      public IDispose
{
   MO_CLASS_ABSTRACT_DECLARE_INHERITS(FRenderProgram, FRenderObject);
protected:
   FRenderVertexShader* _pVertexShader;
   FRenderFragmentShader* _pFragmentShader;
   GRenderShaderParameterDictionary _parameters;
public:
   FRenderProgram();
   MO_ABSTRACT ~FRenderProgram();
public:
   //------------------------------------------------------------
   // <T>获得顶点渲染器。</T>
   MO_INLINE FRenderVertexShader* VertexShader(){
      return _pVertexShader;
   }
   //------------------------------------------------------------
   // <T>获得像素渲染器。</T>
   MO_INLINE FRenderFragmentShader* FragmentShader(){
      return _pFragmentShader;
   }
   //------------------------------------------------------------
   // <T>获得渲染参数集合。</T>
   MO_INLINE GRenderShaderParameterDictionary& Parameters(){
      return _parameters;
   }
   //------------------------------------------------------------
   // <T>根据名称查找渲染参数。</T>
   MO_INLINE FRenderShaderParameter* ParameterFind(TCharC* pName){
      return _parameters.Find(pName);
   }
public:
   GRenderShaderParameterDictionary& Parameters(ERenderShader shaderCd);
   FRenderShaderParameter* ParameterFind(ERenderShader shaderCd, TCharC* pName);
public:
   MO_ABSTRACT TResult MakeVertexSource(FRenderSource* pSource);
   MO_ABSTRACT TResult MakeFragmentSource(FRenderSource* pSource);
public:
   MO_VIRTUAL TInt FindDefine(TCharC* pCode) = 0;
   MO_VIRTUAL TInt FindAttribute(TCharC* pCode) = 0;
   MO_VIRTUAL TResult BindAttribute(TInt slot, TCharC* pCode) = 0;
public:
   MO_VIRTUAL TResult Setup() = 0;
   MO_VIRTUAL TResult Build() = 0;
   MO_VIRTUAL TResult Link() = 0;
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FVector<FRenderProgram*> FRenderProgramVector;
typedef MO_FG_DECLARE GPtrLooper<FRenderProgram> GRenderProgramPtrLooper;

//============================================================
// <T>渲染纹理。</T>
//============================================================
class MO_FG_DECLARE FRenderTexture : public FTexture
{
   MO_CLASS_DECLARE_INHERITS(FRenderTexture, FTexture);
protected:
   FRenderDevice* _pDevice;
   TInt _index;
   ERenderTexture _textureCd;
   ERenderTextureFormat _formatCd;
   ERenderSampler _samplerCd;
   ERenderTextureFilter _filterCd;
   ERenderTextureWrap _wrapCd;
   FBytes* _pData;
public:
   FRenderTexture();
   MO_ABSTRACT ~FRenderTexture();
public:
   //------------------------------------------------------------
   // <T>获得设备。</T>
   MO_INLINE FRenderDevice* Device(){
      return _pDevice;
   }
   //------------------------------------------------------------
   // <T>设置设备。</T>
   MO_INLINE void SetDevice(FRenderDevice* pDevice){
      _pDevice = pDevice;
   }
   //------------------------------------------------------------
   // <T>获得索引。</T>
   MO_INLINE TInt Index(){
      return _index;
   }
   //------------------------------------------------------------
   // <T>设置索引。</T>
   MO_INLINE void SetIndex(TInt index){
      _index = index;
   }
   //------------------------------------------------------------
   // <T>获得纹理类型。</T>
   MO_INLINE ERenderTexture TextureCd(){
      return _textureCd;
   }
   //------------------------------------------------------------
   // <T>获得纹理格式。</T>
   MO_INLINE ERenderTextureFormat FormatCd(){
      return _formatCd;
   }
   //------------------------------------------------------------
   // <T>设置纹理格式。</T>
   MO_INLINE void SetFormatCd(ERenderTextureFormat formatCd){
      _formatCd = formatCd;
   }
   //------------------------------------------------------------
   // <T>获得插值器类型。</T>
   MO_INLINE ERenderSampler SamplerCd(){
      return _samplerCd;
   }
   //------------------------------------------------------------
   // <T>设置插值器类型。</T>
   MO_INLINE void SetSamplerCd(ERenderSampler samplerCd){
      _samplerCd = samplerCd;
   }
   //------------------------------------------------------------
   // <T>获得纹理过滤。</T>
   MO_INLINE ERenderTextureFilter FilterCd(){
      return _filterCd;
   }
   //------------------------------------------------------------
   // <T>设置纹理过滤。</T>
   MO_INLINE void SetFilterCd(ERenderTextureFilter filterCd){
      _filterCd = filterCd;
   }
   //------------------------------------------------------------
   // <T>获得纹理展开。</T>
   MO_INLINE ERenderTextureWrap WrapCd(){
      return _wrapCd;
   }
   //------------------------------------------------------------
   // <T>设置纹理展开。</T>
   MO_INLINE void SetWrapCd(ERenderTextureWrap wrapCd){
      _wrapCd = wrapCd;
   }
public:
   MO_ABSTRACT TResult OnSetup();
   MO_ABSTRACT TResult Suspend();
   MO_ABSTRACT TResult Resume();
   MO_ABSTRACT TResult Upload(TByteC* pData, TInt length);
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FG_DECLARE GPtrLooper<FRenderTexture> GRenderTexturePtrLooper;

//============================================================
// <T>渲染纹理集合。</T>
//============================================================
class MO_FG_DECLARE FRenderTextures : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderTextures, FInstance);
protected:
   FRenderTextureCollection* _pTextures;
public:
   FRenderTextures();
   MO_ABSTRACT ~FRenderTextures();
public:
   //------------------------------------------------------------
   // <T>获得纹理集合。</T>
   MO_INLINE FRenderTextureCollection* Textures(){
      return _pTextures;
   }
public:
   TInt Count();
   FRenderTexture* Get(TInt index);
   void Push(FRenderTexture* pTexture);
public:
   FRenderTexture* FindTexture(ERenderSampler samplerCd);
   FRenderTexture* GetTexture(ERenderSampler samplerCd);
};

//============================================================
// <T>渲染平面纹理。</T>
//============================================================
class MO_FG_DECLARE FRenderFlatTexture : public FRenderTexture
{
   MO_CLASS_DECLARE_INHERITS(FRenderFlatTexture, FRenderTexture);
public:
   FRenderFlatTexture();
   MO_ABSTRACT ~FRenderFlatTexture();
public:
   MO_ABSTRACT TResult Resize(TInt width, TInt height);
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FList<FRenderFlatTexture*> FRenderFlatTextureList;

//============================================================
// <T>渲染CUBE纹理。</T>
//============================================================
class MO_FG_DECLARE FRenderCubeTexture : public FRenderTexture
{
   MO_CLASS_DECLARE_INHERITS(FRenderCubeTexture, FRenderTexture);
public:
   FRenderCubeTexture();
   MO_ABSTRACT ~FRenderCubeTexture();
public:
   MO_ABSTRACT TResult Resize(TInt size);
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FList<FRenderCubeTexture*> FRenderCubeTextureList;

//============================================================
// <T>渲染目标。</T>
//============================================================
class MO_FG_DECLARE FRenderTarget : public FRenderInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderTarget, FRenderInstance);
protected:
   TBool _optionDepth;
   TBool _optionStencil;
   SIntSize2 _size;
   ERenderUsage _usageCd;
   ERenderTexture _textureCd;
   ERenderTextureFormat _formatCd;
   SFloatColor4 _backgroundColor;
   FRenderTextureCollection* _pTextures;
public:
   FRenderTarget();
   MO_ABSTRACT ~FRenderTarget();
public:
   //------------------------------------------------------------
   // <T>获得支持深度。</T>
   MO_INLINE TBool OptionDepth(){
      return _optionDepth;
   }
   //------------------------------------------------------------
   // <T>设置支持深度。</T>
   MO_INLINE void SetOptionDepth(TBool optionDepth){
      _optionDepth = optionDepth;
   }
   //------------------------------------------------------------
   // <T>获得支持模板。</T>
   MO_INLINE TBool OptionStencil(){
      return _optionStencil;
   }
   //------------------------------------------------------------
   // <T>设置支持模板。</T>
   MO_INLINE void SetOptionStencil(TBool optionStencil){
      _optionStencil = optionStencil;
   }
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SIntSize2& Size(){
      return _size;
   }
   //------------------------------------------------------------
   // <T>获得使用方式。</T>
   MO_INLINE ERenderUsage UsageCd(){
      return _usageCd;
   }
   //------------------------------------------------------------
   // <T>设置使用方式。</T>
   MO_INLINE void SetUsageCd(ERenderUsage usageCd){
      _usageCd = usageCd;
   }
   //------------------------------------------------------------
   // <T>获得纹理格式。</T>
   MO_INLINE ERenderTexture TextureCd(){
      return _textureCd;
   }
   //------------------------------------------------------------
   // <T>设置纹理格式。</T>
   MO_INLINE void SetTextureCd(ERenderTexture textureCd){
      _textureCd = textureCd;
   }
   //------------------------------------------------------------
   // <T>获得格式类型。</T>
   MO_INLINE ERenderTextureFormat FormatCd(){
      return _formatCd;
   }
   //------------------------------------------------------------
   // <T>设置格式类型。</T>
   MO_INLINE void SetFormatCd(ERenderTextureFormat formatCd){
      _formatCd = formatCd;
   }
   //------------------------------------------------------------
   // <T>获得背景色。</T>
   MO_INLINE SFloatColor4& BackgroundColor(){
      return _backgroundColor;
   }
   //------------------------------------------------------------
   // <T>获得纹理集合。</T>
   MO_INLINE FRenderTextureCollection* Textures(){
      return _pTextures;
   }
public:
   MO_ABSTRACT TResult OnSetup();
   TResult Setup();
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FRenderTarget*> FRenderTargetCollection;
typedef MO_FG_DECLARE FList<FRenderTarget*> FRenderTargetList;
typedef MO_FG_DECLARE GPtrLooper<FRenderTarget> GRenderTargetPtrLooper;

//============================================================
// <T>渲染视角。</T>
//============================================================
class MO_FG_DECLARE FRenderView : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderView, FInstance);
protected:
   GPtr<FCamera> _camera;
   GPtr<FProjection> _projection;
   GPtr<FViewport> _viewPort;
   FRenderableCollection* _pRenderables;
   FRenderTargetCollection* _pRenderTargets;
public:
   FRenderView();
   MO_ABSTRACT ~FRenderView();
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
   // <T>获得投影。</T>
   MO_INLINE FProjection* Projection(){
      return _projection;
   }
   //------------------------------------------------------------
   // <T>设置投影。</T>
   MO_INLINE void SetProjection(FProjection* pProjection){
      _projection = pProjection;
   }
   //------------------------------------------------------------
   // <T>获得视角。</T>
   MO_INLINE FViewport* Viewport(){
      return _viewPort;
   }
   //------------------------------------------------------------
   // <T>设置视角。</T>
   MO_INLINE void SetViewport(FViewport* pViewPort){
      _viewPort = pViewPort;
   }
   //------------------------------------------------------------
   // <T>获得渲染集合。</T>
   MO_INLINE FRenderableCollection* Renderables(){
      return _pRenderables;
   }
   //------------------------------------------------------------
   // <T>获得渲染目标集合。</T>
   MO_INLINE FRenderTargetCollection* RenderTargets(){
      return _pRenderTargets;
   }
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FRenderView*> FRenderViewCollection;

//============================================================
// <T>渲染区域。</T>
//============================================================
class MO_FG_DECLARE FRenderRegion : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRenderRegion, FInstance);
protected:
   TBool _optionRenderTarget;
   GPtr<FRenderView> _activeView;
   FPipeline* _pActivePipeline;
   FPipelinePass* _pActivePass;
   FRenderViewCollection* _pViews;
   GPtr<FDirectionalLight> _directionalLight;
   FLightCollection* _pLights;
   FRenderableCollection* _pRenderables;
   FRenderableCollection* _pVisibleRenderables;
   FRenderTextureCollection* _pTextures;
   FVisualRegion* _pVisualRegion;
public:
   FRenderRegion();
   MO_ABSTRACT ~FRenderRegion();
public:
   //------------------------------------------------------------
   // <T>获得配置渲染目标。</T>
   MO_INLINE TBool OptionRenderTarget(){
      return _optionRenderTarget;
   }
   //------------------------------------------------------------
   // <T>设置配置渲染目标。</T>
   MO_INLINE void SetOptionRenderTarget(TBool optionRenderTarget){
      _optionRenderTarget = optionRenderTarget;
   }
   //------------------------------------------------------------
   // <T>获得激活视角。</T>
   MO_INLINE FRenderView* ActiveView(){
      return _activeView;
   }
   //------------------------------------------------------------
   // <T>设置激活视角。</T>
   MO_INLINE void SetActiveView(FRenderView* pActiveView){
      _activeView = pActiveView;
   }
   //------------------------------------------------------------
   // <T>获得激活管道。</T>
   MO_INLINE FPipeline* ActivePipeline(){
      return _pActivePipeline;
   }
   //------------------------------------------------------------
   // <T>设置激活管道。</T>
   MO_INLINE void SetActivePipeline(FPipeline* pPipeline){
      _pActivePipeline = pPipeline;
   }
   //------------------------------------------------------------
   // <T>获得激活渲染过程。</T>
   MO_INLINE FPipelinePass* ActivePass(){
      return _pActivePass;
   }
   //------------------------------------------------------------
   // <T>设置激活渲染过程。</T>
   MO_INLINE void SetActivePass(FPipelinePass* pActivePass){
      _pActivePass = pActivePass;
   }
   //------------------------------------------------------------
   // <T>获得视角集合。</T>
   MO_INLINE FRenderViewCollection* Views(){
      return _pViews;
   }
   //------------------------------------------------------------
   // <T>获得方向光源。</T>
   MO_INLINE FDirectionalLight* DirectionalLight(){
      return _directionalLight;
   }
   //------------------------------------------------------------
   // <T>设置方向光源。</T>
   MO_INLINE void SetDirectionalLight(FDirectionalLight* pDirectionalLight){
      _directionalLight = pDirectionalLight;
   }
   //------------------------------------------------------------
   // <T>获得光源集合。</T>
   MO_INLINE FLightCollection* Lights(){
      return _pLights;
   }
   //------------------------------------------------------------
   // <T>获得渲染集合。</T>
   MO_INLINE FRenderableCollection* Renderables(){
      return _pRenderables;
   }
   //------------------------------------------------------------
   // <T>获得可见渲染集合。</T>
   MO_INLINE FRenderableCollection* VisibleRenderables(){
      return _pVisibleRenderables;
   }
   //------------------------------------------------------------
   // <T>获得纹理集合。</T>
   MO_INLINE FRenderTextureCollection* Textures(){
      return _pTextures;
   }
   //------------------------------------------------------------
   // <T>获得可见区域。</T>
   MO_INLINE FVisualRegion* VisualRegion(){
      return _pVisualRegion;
   }
   //------------------------------------------------------------
   // <T>设置可见区域。</T>
   MO_INLINE void SetVisualRegion(FVisualRegion* pVisualRegion){
      _pVisualRegion = pVisualRegion;
   }
public:
   TBool TestRenderable();
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FRenderRegion*> FRenderRegionCollection;

//============================================================
// <T>渲染设备。</T>
//============================================================
class MO_FG_DECLARE FRenderDevice : public FDevice
{
   MO_CLASS_ABSTRACT_DECLARE_INHERITS(FRenderDevice, FDevice);
protected:
   // 渲染能力
   FRenderCapability* _pCapability;
   // 填充模式
   ERenderFillMode _fillModeCd;
   // 深度信息
   TBool _optionDepth;
   ERenderDepthMode _depthModeCd;
   // 剪裁信息
   TBool _optionCull;
   ERenderCullMode _cullModeCd;
   // 混合信息
   TBool _statusBlend;
   ERenderBlendMode _blendSourceCd;
   ERenderBlendMode _blendTargetCd;
   // 程序信息
   FRenderProgram* _pProgram;
   GRenderVertexBufferPtrs _vertexBuffers;
   FRenderTextureCollection* _pTextures;
   // 激活信息
   FRenderProgram* _pActiveProgram;
   FRenderTarget* _pActiveRenderTarget;
   FBytes* _pActiveVertexData;
   FBytes* _pActiveFragmentData;
   GRenderVertexBufferPtrs _activeVertexBuffers;
   FRenderTextureCollection* _pActiveTextures;
   // 缓冲数据
   FBytes* _pVertexConsts;
   FBytes* _pFragmentConsts;
   // 存储信息
   GRenderProgramPtrLooper _storagePrograms;
   GRenderVertexBufferLooper _storageVertexBuffers;
   GRenderIndexBufferPtrLooper _storageIndexBuffers;
   GRenderTexturePtrLooper _storageTextures;
   GRenderTargetPtrLooper _storageTargets;
   // 脚本处理器
   GRenderShaderTransformerPtr _shaderTransformer;
   GRenderShaderOptimizerPtr _shaderOptimizer;
   // 统计信息
   GPtr<FRenderStatistics> _statistics;
   // 绘制效率统计
   GStatisticsPtr _renderDrawStatistics;
public:
   FRenderDevice();
   MO_ABSTRACT ~FRenderDevice();
public:
   //------------------------------------------------------------
   // <T>获得渲染能力。</T>
   MO_INLINE FRenderCapability* Capability(){
      return _pCapability;
   }
   //------------------------------------------------------------
   // <T>获得统计信息。</T>
   MO_INLINE FRenderStatistics* Statistics(){
      return _statistics;
   }
   //------------------------------------------------------------
   // <T>获得渲染程序。</T>
   MO_INLINE FRenderProgram* Program(){
      return _pProgram;
   }
   //------------------------------------------------------------
   // <T>获得脚本变换器。</T>
   MO_INLINE FRenderShaderTransformer* ShaderTransformer(){
      return _shaderTransformer;
   }
   //------------------------------------------------------------
   // <T>获得脚本优化器。</T>
   MO_INLINE FRenderShaderOptimizer* ShaderOptimizer(){
      return _shaderOptimizer;
   }
public:
   MO_ABSTRACT TResult Setup();
   MO_ABSTRACT TResult Suspend();
   MO_ABSTRACT TResult Resume();
   MO_ABSTRACT TResult FrameBegin();
   MO_ABSTRACT TResult FrameEnd();
public:
   TBool UpdateConsts(ERenderShader shaderCd, TInt slot, TAnyC* pData, TInt length);
   MO_VIRTUAL TResult CheckError(TCharC* pCode, TCharC* pMessage, ...) = 0;
public:
   MO_VIRTUAL FRenderVertexBuffer* CreateVertexBuffer(FClass* pClass = NULL) = 0;
   MO_VIRTUAL FRenderIndexBuffer* CreateIndexBuffer(FClass* pClass = NULL) = 0;
   MO_VIRTUAL FRenderProgram* CreateProgrom(FClass* pClass = NULL) = 0;
   MO_VIRTUAL FRenderTarget* CreateRenderTarget(FClass* pClass = NULL) = 0;
   MO_VIRTUAL FRenderFlatTexture* CreateFlatTexture(FClass* pClass = NULL) = 0;
   MO_VIRTUAL FRenderCubeTexture* CreateCubeTexture(FClass* pClass = NULL) = 0;
public:
   MO_VIRTUAL TResult Clear(TFloat red = 0.0f, TFloat green = 0.0f, TFloat blue = 0.0f, TFloat alpha = 1.0f, TFloat depth = 1.0f) = 0;
   MO_VIRTUAL TResult SetBackBuffer(TInt width, TInt height, TInt antiAlias, TBool depthed = ETrue) = 0;
   MO_VIRTUAL TResult SetFillMode(ERenderFillMode fillModeCd) = 0;
   MO_VIRTUAL TResult SetDepthMode(TBool depth, ERenderDepthMode depthCd = ERenderDepthMode_None) = 0;
   MO_VIRTUAL TResult SetCullingMode(TBool cull, ERenderCullMode cullCd = ERenderCullMode_None) = 0;
   MO_VIRTUAL TResult SetBlendFactors(TBool blend, ERenderBlendMode sourceCd = ERenderBlendMode_None, ERenderBlendMode targetCd = ERenderBlendMode_None) = 0;
   MO_VIRTUAL TResult SetScissorRectangle(TInt left, TInt top, TInt width, TInt height) = 0;
   MO_VIRTUAL TResult SetRenderTarget(FRenderTarget* pRenderTarget = NULL) = 0;
   MO_VIRTUAL TResult SetProgram(FRenderProgram* pProgram) = 0;
   MO_VIRTUAL TResult BindConstData(ERenderShader shaderCd, TInt slot, ERenderShaderConstForamt formatCd, TAnyC* pData, TInt length) = 0;
   MO_VIRTUAL TResult BindConstFloat3(ERenderShader shaderCd, TInt slot, TFloat x = 0.0f, TFloat y = 0.0f, TFloat z = 0.0f) = 0;
   MO_VIRTUAL TResult BindConstFloat4(ERenderShader shaderCd, TInt slot, TFloat x = 0.0f, TFloat y = 0.0f, TFloat z = 0.0f, TFloat w = 1.0f) = 0;
   MO_VIRTUAL TResult BindConstMatrix3x3(ERenderShader shaderCd, TInt slot, const SFloatMatrix3d& matrix) = 0;
   MO_VIRTUAL TResult BindConstMatrix4x4(ERenderShader shaderCd, TInt slot, const SFloatMatrix3d& matrix) = 0;
   MO_VIRTUAL TResult BindVertexBuffer(TInt slot, FRenderVertexBuffer* pVertexBuffer, TInt offset, ERenderVertexFormat formatCd) = 0;
   MO_VIRTUAL TResult BindTexture(TInt slot, FRenderTexture* pTexture) = 0;
   MO_VIRTUAL TResult DrawTriangles(FRenderIndexBuffer* pIndexBuffer, TInt offset, TInt count) = 0;
   MO_VIRTUAL TResult Present() = 0;
public:
   MO_ABSTRACT TResult BindConstMatrix4x3(ERenderShader shaderCd, TInt slot, const SFloatMatrix3d* pMatrix, TInt count);
   MO_ABSTRACT TResult BindConstMatrix4x4(ERenderShader shaderCd, TInt slot, const SFloatMatrix3d* pMatrix, TInt count);
public:
   MO_ABSTRACT TResult BindVertexStream(TInt slot, FRenderVertexStream* pStream);
public:
   MO_ABSTRACT TResult DrawInstanceTriangles(FRenderIndexBuffer* pIndexBuffer, TInt offset, TInt count);
};

//============================================================
// <T>渲染工具。</T>
//============================================================
class MO_FG_DECLARE RRenderUtil
{
public:
   static TInt CalculateMergeLimit();
   static TInt CalculateInstanceCount(TInt vertexCount, TInt boneCount);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FG_RENDER_H__
