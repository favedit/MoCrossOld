#ifndef __MO_EO_RENDER_H__
#define __MO_EO_RENDER_H__
//************************************************************

#ifdef _MO_WINDOWS
#ifndef _WINDOWS_
#include <Windows.h>
#endif // _WINDOWS_
#endif // _MO_WINDOWS

#ifdef _MO_WINDOWS
#ifndef __glew_h__
#include <gl\glew.h>
#endif // __glew_h__
#endif // _MO_WINDOWS

#ifdef _MO_ANDROID
#ifndef __gl2_h_
#include <GLES2\gl2.h>
#endif // __gl2_h_
#endif // _MO_ANDROID

#ifdef _MO_ANDROID
#ifndef __gl2ext_h_
#include <GLES2\gl2ext.h>
#endif // __gl2ext_h_
#endif // _MO_ANDROID

#ifndef __MO_EO_COMMON_H__
#include "MoEoCommon.h"
#endif // __MO_EO_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>设备工具。</T>
//============================================================
class MO_EO_DECLARE ROpenGLES2{
public:
   static GLenum ConvertBlendFactors(ERenderBlendMode blendCd);
   static GLenum ConvertIndexStride(ERenderIndexStride strideCd);
};

//============================================================
// <T>渲染顶点缓冲。</T>
//============================================================
class MO_EO_DECLARE FEoRenderVertexBuffer : public FRenderVertexBuffer
{
   MO_CLASS_DECLARE_INHERITS(FEoRenderVertexBuffer, FRenderVertexBuffer);
protected:
   GLuint _bufferId;
public:
   FEoRenderVertexBuffer();
   MO_ABSTRACT ~FEoRenderVertexBuffer();
public:
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE GLuint BufferId(){
      return _bufferId;
   }
public:
   MO_OVERRIDE TResult OnSetup();
public:
   MO_OVERRIDE TResult Upload(TByteC* pData, TInt length);
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
// <T>渲染索引缓冲。</T>
//============================================================
class MO_EO_DECLARE FEoRenderIndexBuffer : public FRenderIndexBuffer
{
   MO_CLASS_DECLARE_INHERITS(FEoRenderIndexBuffer, FRenderVertexBuffer);
protected:
   GLuint _bufferId;
public:
   FEoRenderIndexBuffer();
   MO_ABSTRACT ~FEoRenderIndexBuffer();
public:
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE GLuint BufferId(){
      return _bufferId;
   }
public:
   MO_OVERRIDE TResult OnSetup();
public:
   MO_OVERRIDE TResult Upload(TByteC* pMemory, TInt length);
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
// <T>顶点渲染器。</T>
//============================================================
class MO_EO_DECLARE FEoRenderVertexShader : public FRenderVertexShader
{
   MO_CLASS_DECLARE_INHERITS(FEoRenderVertexShader, FRenderVertexShader);
public:
   FEoRenderVertexShader();
   MO_ABSTRACT ~FEoRenderVertexShader();
public:
   MO_OVERRIDE TResult Setup();
public:
   MO_OVERRIDE TResult Compile(TCharC* pSource);
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
// <T>像素渲染器。</T>
//============================================================
class MO_EO_DECLARE FEoRenderFragmentShader : public FRenderFragmentShader
{
   MO_CLASS_DECLARE_INHERITS(FEoRenderFragmentShader, FRenderVertexShader);
public:
   FEoRenderFragmentShader();
   MO_ABSTRACT ~FEoRenderFragmentShader();
public:
   MO_OVERRIDE TResult Setup();
public:
   MO_OVERRIDE TResult Compile(TCharC* pSource);
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
// <T>渲染程序。</T>
//============================================================
class MO_EO_DECLARE FEoRenderProgram : public FRenderProgram
{
   MO_CLASS_DECLARE_INHERITS(FEoRenderProgram, FRenderProgram);
protected:
   GLuint _programId;
public:
   FEoRenderProgram();
   MO_ABSTRACT ~FEoRenderProgram();
public:
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE GLuint ProgramId(){
      return _programId;
   }
public:
   MO_OVERRIDE TInt FindDefine(TCharC* pCode);
   MO_OVERRIDE TInt FindAttribute(TCharC* pCode);
   MO_OVERRIDE TResult BindAttribute(TInt slot, TCharC* pCode);
public:
   MO_OVERRIDE TResult Setup();
   MO_OVERRIDE TResult Build();
   MO_OVERRIDE TResult Link();
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
// <T>渲染目标。</T>
//============================================================
class MO_EO_DECLARE FEoRenderTarget : public FRenderTarget
{
   MO_CLASS_DECLARE_INHERITS(FEoRenderTarget, FRenderTarget);
protected:
   GLuint _frameBufferId;
   GLuint _depthStencilId;
   GLuint _depthBufferId;
public:
   FEoRenderTarget();
   MO_ABSTRACT ~FEoRenderTarget();
public:
   //------------------------------------------------------------
   // <T>获得帧缓冲编号。</T>
   MO_INLINE GLuint FrameBufferId(){
      return _frameBufferId;
   }
   //------------------------------------------------------------
   // <T>获得深度模板编号。</T>
   MO_INLINE GLuint DepthStencilId(){
      return _depthStencilId;
   }
   //------------------------------------------------------------
   // <T>获得深度缓冲编号。</T>
   MO_INLINE GLuint DepthBufferId(){
      return _depthBufferId;
   }
public:
   MO_OVERRIDE TResult OnSetup();
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
// <T>渲染平面纹理。</T>
//============================================================
class MO_EO_DECLARE FEoRenderFlatTexture : public FRenderFlatTexture
{
   MO_CLASS_DECLARE_INHERITS(FEoRenderFlatTexture, FRenderFlatTexture);
protected:
   GLuint _textureId;
public:
   FEoRenderFlatTexture();
   MO_ABSTRACT ~FEoRenderFlatTexture();
public:
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE GLuint TextureId(){
      return _textureId;
   }
public:
   MO_OVERRIDE TResult OnSetup();
public:
   MO_OVERRIDE TResult Resize(TInt width, TInt height);
   MO_OVERRIDE TResult Unserialize(IDataInput* pInput);
   MO_OVERRIDE TResult Upload(TByteC* pData, TInt length);
   TResult LoadDataFile(TCharC* pFileName);
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
// <T>渲染CUBE纹理。</T>
//============================================================
class MO_EO_DECLARE FEoRenderCubeTexture : public FRenderCubeTexture
{
   MO_CLASS_DECLARE_INHERITS(FEoRenderCubeTexture, FRenderFlatTexture);
protected:
   GLuint _textureId;
public:
   FEoRenderCubeTexture();
   MO_ABSTRACT ~FEoRenderCubeTexture();
public:
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE GLuint TextureId(){
      return _textureId;
   }
public:
   MO_OVERRIDE TResult OnSetup();
public:
   MO_OVERRIDE TResult Resize(TInt size);
   MO_OVERRIDE TResult Unserialize(IDataInput* pInput);
   MO_OVERRIDE TResult Upload(TByteC* pData, TInt length);
   TResult LoadDataFile(TCharC* pFileName);
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
// <T>渲染设备。</T>
//============================================================
class MO_EO_DECLARE FEoRenderDevice : public FRenderDevice
{
   MO_CLASS_DECLARE_INHERITS(FEoRenderDevice, FRenderDevice);
protected:
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
   TInt _renderTextureActiveSlot;
   // 纹理信息
   TBool _optionTexture;
   GLint _vertexConstLimit;
   GLint _vertexAttributeLimit;
   GLint _fragmentConstLimit;
   GLint _varyingLimit;
   GLint _textureLimit;
   GLint _textureSizeLimit;
   GLint _textureTotalLimit;
   GLint _renderTargetLimit;
   // 顶点数据
   FBytes* _pVertexConsts;
   FBytes* _pFragmentConsts;
   // 关联顶点缓冲集合
   FRenderFlatTextureList* _pLinkFlatTextures;
   FRenderCubeTextureList* _pLinkCubeTextures;
public:
   FEoRenderDevice();
   MO_ABSTRACT ~FEoRenderDevice();
protected:
   TBool UpdateContext();
public:
   MO_OVERRIDE TResult Setup();
   MO_ABSTRACT TResult Suspend();
   MO_ABSTRACT TResult Resume();
public:
   MO_OVERRIDE TResult CheckError(TCharC* pCode, TCharC* pMessage, ...);
public:
   MO_OVERRIDE FRenderVertexBuffer* CreateVertexBuffer(FClass* pClass = NULL);
   MO_OVERRIDE FRenderIndexBuffer* CreateIndexBuffer(FClass* pClass = NULL);
   MO_OVERRIDE FRenderProgram* CreateProgrom(FClass* pClass = NULL);
   MO_OVERRIDE FRenderTarget* CreateRenderTarget(FClass* pClass = NULL);
   MO_OVERRIDE FRenderFlatTexture* CreateFlatTexture(FClass* pClass = NULL);
   MO_OVERRIDE FRenderCubeTexture* CreateCubeTexture(FClass* pClass = NULL);
public:
   MO_OVERRIDE TResult Clear(TFloat red = 0.0f, TFloat green = 0.0f, TFloat blue = 0.0f, TFloat alpha = 1.0f, TFloat depth = 1.0f);
   MO_OVERRIDE TResult SetBackBuffer(TInt width, TInt height, TInt antiAlias, TBool depthed = ETrue);
   MO_OVERRIDE TResult SetFillMode(ERenderFillMode fillModeCd);
   MO_OVERRIDE TResult SetDepthMode(TBool depth, ERenderDepthMode depthCd = ERenderDepthMode_None);
   MO_OVERRIDE TResult SetCullingMode(TBool cull, ERenderCullMode cullCd = ERenderCullMode_None);
   MO_OVERRIDE TResult SetBlendFactors(TBool blend, ERenderBlendMode sourceCd = ERenderBlendMode_None, ERenderBlendMode targetCd = ERenderBlendMode_None);
   MO_OVERRIDE TResult SetScissorRectangle(TInt left, TInt top, TInt width, TInt height);
   MO_OVERRIDE TResult SetRenderTarget(FRenderTarget* pRenderTarget = NULL);
   MO_OVERRIDE TResult SetProgram(FRenderProgram* pProgram);
   MO_OVERRIDE TResult BindConstData(ERenderShader shaderCd, TInt slot, ERenderShaderConstForamt formatCd, TAnyC* pData, TInt length);
   MO_OVERRIDE TResult BindConstFloat3(ERenderShader shaderCd, TInt slot, TFloat x = 0.0f, TFloat y = 0.0f, TFloat z = 0.0f);
   MO_OVERRIDE TResult BindConstFloat4(ERenderShader shaderCd, TInt slot, TFloat x = 0.0f, TFloat y = 0.0f, TFloat z = 0.0f, TFloat w = 1.0f);
   MO_OVERRIDE TResult BindConstMatrix3x3(ERenderShader shaderCd, TInt slot, const SFloatMatrix3d& matrix);
   MO_OVERRIDE TResult BindConstMatrix4x4(ERenderShader shaderCd, TInt slot, const SFloatMatrix3d& matrix);
   MO_OVERRIDE TResult BindVertexBuffer(TInt slot, FRenderVertexBuffer* pVertexBuffer, TInt offset, ERenderVertexFormat formatCd);
   MO_OVERRIDE TResult BindTexture(TInt slot, FRenderTexture* pTexture);
   MO_OVERRIDE TResult DrawTriangles(FRenderIndexBuffer* pIndexBuffer, TInt offset, TInt count);
   MO_OVERRIDE TResult Present();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EO_RENDER_H__
