#ifndef __MO_BG_RENDER_H__
#define __MO_BG_RENDER_H__
//************************************************************

#ifndef __MO_BG_COMMON_H__
#include "MoBfCommon.h"
#endif // __MO_BG_COMMON_H__

#ifndef __MO_BG_CORE_H__
#include "MoBfCore.h"
#endif // __MO_BG_CORE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>渲染层信息。</T>
//============================================================
class MO_BG_DECLARE FBfRenderLayout : public FRenderLayout
{
   MO_CLASS_DECLARE_INHERITS(FBfRenderLayout, FRenderLayout);
protected:
   TInt _count;
   MO_D3D10_INPUT_ELEMENT_DESC_ARRAY _inputElements;
   ID3D10InputLayout* _piInputLayout;
   ID3D10Buffer* _piBuffer[MO_INPUT_ELEMENT_MAXCNT];
   UINT _strides[MO_INPUT_ELEMENT_MAXCNT];
   UINT _offsets[MO_INPUT_ELEMENT_MAXCNT];
public:
   FBfRenderLayout();
   MO_ABSTRACT ~FBfRenderLayout();
public:
   //------------------------------------------------------------
   // <T>获得本地输入层次。</T>
   MO_INLINE ID3D10InputLayout* NativeInputLayout(){
      return _piInputLayout;
   }
   //------------------------------------------------------------
   // <T>获得总数。</T>
   MO_INLINE TInt Count(){
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得数据。</T>
   MO_INLINE ID3D10Buffer** Buffer(){
      return _piBuffer;
   }
   //------------------------------------------------------------
   // <T>获得宽度。</T>
   MO_INLINE UINT* Stride(){
      return _strides;
   }
   //------------------------------------------------------------
   // <T>获得位置。</T>
   MO_INLINE UINT* Offset(){
      return _offsets;
   }
public:
   FRenderLayoutElement* FindByAttribute(FRenderShaderAttribute* pAttribute);
   MO_OVERRIDE TResult OnSetup();
};
//------------------------------------------------------------
typedef MO_BG_DECLARE FObjects<FBfRenderLayout*> FBfRenderLayoutCollection;

//============================================================
// <T>渲染顶点缓冲。</T>
//============================================================
class MO_BG_DECLARE FBfRenderVertexBuffer : public FRenderVertexBuffer
{
   MO_CLASS_DECLARE_INHERITS(FBfRenderVertexBuffer, FRenderVertexBuffer);
protected:
   ID3D10Buffer* _piBuffer;
public:
   FBfRenderVertexBuffer();
   MO_ABSTRACT ~FBfRenderVertexBuffer();
public:
   //------------------------------------------------------------
   // <T>获得本地缓冲。</T>
   MO_INLINE ID3D10Buffer* NativeBuffer(){
      return _piBuffer;
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
class MO_BG_DECLARE FBfRenderIndexBuffer : public FRenderIndexBuffer
{
   MO_CLASS_DECLARE_INHERITS(FBfRenderIndexBuffer, FRenderVertexBuffer);
protected:
   ID3D10Buffer* _piBuffer;
public:
   FBfRenderIndexBuffer();
   MO_ABSTRACT ~FBfRenderIndexBuffer();
public:
   //------------------------------------------------------------
   // <T>获得本地缓冲。</T>
   MO_INLINE ID3D10Buffer* NativeBuffer(){
      return _piBuffer;
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
// <T>渲染器缓冲。</T>
//============================================================
class MO_BG_DECLARE FBfRenderShaderBuffer : public FRenderShaderBuffer
{
   MO_CLASS_DECLARE_INHERITS(FBfRenderShaderBuffer, FRenderShaderBuffer);
protected:
   ID3D10Buffer* _piBuffer;
public:
   FBfRenderShaderBuffer();
   MO_ABSTRACT ~FBfRenderShaderBuffer();
public:
   //------------------------------------------------------------
   // <T>获得本地缓冲。</T>
   MO_INLINE ID3D10Buffer* NativeiBuffer(){
      return _piBuffer;
   }
   //------------------------------------------------------------
   // <T>设置本地缓冲。</T>
   MO_INLINE void SetNativeiBuffer(ID3D10Buffer* piBuffer){
      _piBuffer = piBuffer;
   }
public:
   MO_OVERRIDE TResult OnSetup();
public:
   MO_OVERRIDE TResult Commit();
   MO_OVERRIDE TResult Bind();
};

//============================================================
// <T>渲染器参数。</T>
//============================================================
class MO_BG_DECLARE FBfRenderShaderParameter : public FRenderShaderParameter
{
   MO_CLASS_DECLARE_INHERITS(FBfRenderShaderParameter, FRenderShaderParameter);
protected:
   ID3D10ShaderReflectionVariable* _piVariable;
public:
   FBfRenderShaderParameter();
   MO_ABSTRACT ~FBfRenderShaderParameter();
public:
   //------------------------------------------------------------
   // <T>设置缓冲。</T>
   MO_INLINE void NativeVariable(FBfRenderShaderBuffer* pBuffer){
      _buffer = pBuffer;
   }
   //------------------------------------------------------------
   // <T>获得本地变量。</T>
   MO_INLINE ID3D10ShaderReflectionVariable* NativeVariable(){
      return _piVariable;
   }
public:
   TResult LinkNative(ID3D10ShaderReflectionVariable* piVariable);
};

//============================================================
// <T>顶点渲染器。</T>
//============================================================
class MO_BG_DECLARE FBfRenderVertexShader : public FRenderVertexShader
{
   MO_CLASS_DECLARE_INHERITS(FBfRenderVertexShader, FRenderVertexShader);
protected:
   ID3D10Blob* _piData;
   ID3D10VertexShader* _piShader;
public:
   FBfRenderVertexShader();
   MO_ABSTRACT ~FBfRenderVertexShader();
public:
   //------------------------------------------------------------
   // <T>获得本地数据。</T>
   MO_INLINE ID3D10Blob* NativeData(){
      return _piData;
   }
   //------------------------------------------------------------
   // <T>获得本地渲染器。</T>
   MO_INLINE ID3D10VertexShader* NativeShader(){
      return _piShader;
   }
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
class MO_BG_DECLARE FBfRenderFragmentShader : public FRenderFragmentShader
{
   MO_CLASS_DECLARE_INHERITS(FBfRenderFragmentShader, FRenderFragmentShader);
protected:
   ID3D10Blob* _piData;
   ID3D10PixelShader* _piShader;
public:
   FBfRenderFragmentShader();
   MO_ABSTRACT ~FBfRenderFragmentShader();
public:
   //------------------------------------------------------------
   // <T>获得本地数据。</T>
   MO_INLINE ID3D10Blob* NativeData(){
      return _piData;
   }
   //------------------------------------------------------------
   // <T>获得本地渲染器。</T>
   MO_INLINE ID3D10PixelShader* NativeShader(){
      return _piShader;
   }
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
class MO_BG_DECLARE FBfRenderProgram : public FRenderProgram
{
   MO_CLASS_DECLARE_INHERITS(FBfRenderProgram, FRenderProgram);
protected:
   ID3D10InputLayout* _piInputLayout;
   MO_D3D10_INPUT_ELEMENT_DESC_ARRAY inputElements;
public:
   FBfRenderProgram();
   MO_ABSTRACT ~FBfRenderProgram();
public:
   //------------------------------------------------------------
   // <T>获得本地输入层次。</T>
   MO_INLINE ID3D10InputLayout* NativeInputLayout(){
      return _piInputLayout;
   }
public:
   MO_OVERRIDE TInt FindDefine(TCharC* pCode);
   MO_OVERRIDE TInt FindAttribute(TCharC* pCode);
   MO_OVERRIDE TResult BindAttribute(TInt slot, TCharC* pCode);
protected:
   TResult BuildShader(FRenderShader* pShader, ID3D10Blob* piData);
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
class MO_BG_DECLARE FBfRenderTarget : public FRenderTarget
{
   MO_CLASS_DECLARE_INHERITS(FBfRenderTarget, FRenderTarget);
protected:
   ID3D10RenderTargetView* _piRenderTarget;
   ID3D10DepthStencilView* _piDepthStencil;
public:
   FBfRenderTarget();
   MO_ABSTRACT ~FBfRenderTarget();
public:
   //------------------------------------------------------------
   // <T>获得本地渲染目标。</T>
   MO_INLINE ID3D10RenderTargetView* NativeRenderTarget(){
      return _piRenderTarget;
   }
   //------------------------------------------------------------
   // <T>设置本地渲染目标。</T>
   MO_INLINE void SetNativeRenderTarget(ID3D10RenderTargetView* piRenderTarget){
      _piRenderTarget = piRenderTarget;
   }
   //------------------------------------------------------------
   // <T>获得本地深度缓冲。</T>
   MO_INLINE ID3D10DepthStencilView* NativeDepthStencil(){
      return _piDepthStencil;
   }
   //------------------------------------------------------------
   // <T>设置本地深度缓冲。</T>
   MO_INLINE void SetNativeDepthStencil(ID3D10DepthStencilView* piDepthStencil){
      _piDepthStencil = piDepthStencil;
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
class MO_BG_DECLARE FBfRenderFlatTexture : public FRenderFlatTexture
{
   MO_CLASS_DECLARE_INHERITS(FBfRenderFlatTexture, FRenderFlatTexture);
protected:
   ID3D10Texture2D* _piTexture;
   ID3D10ShaderResourceView* _piView;
   ID3D10SamplerState* _piState;
public:
   FBfRenderFlatTexture();
   MO_ABSTRACT ~FBfRenderFlatTexture();
public:
   //------------------------------------------------------------
   // <T>获得本地纹理。</T>
   MO_INLINE ID3D10Texture2D* NativeTexture(){
      return _piTexture;
   }
   //------------------------------------------------------------
   // <T>获得本地视图。</T>
   MO_INLINE ID3D10ShaderResourceView* NativeView(){
      return _piView;
   }
   //------------------------------------------------------------
   // <T>获得取样器状态。</T>
   MO_INLINE ID3D10SamplerState* NativeState(){
      return _piState;
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
class MO_BG_DECLARE FBfRenderCubeTexture : public FRenderCubeTexture
{
   MO_CLASS_DECLARE_INHERITS(FBfRenderCubeTexture, FRenderFlatTexture);
protected:
   ID3D10Texture2D* _piTexture;
   ID3D10ShaderResourceView* _piView;
   ID3D10SamplerState* _piState;
public:
   FBfRenderCubeTexture();
   MO_ABSTRACT ~FBfRenderCubeTexture();
public:
   //------------------------------------------------------------
   // <T>获得本地纹理。</T>
   MO_INLINE ID3D10Texture2D* NativeTexture(){
      return _piTexture;
   }
   //------------------------------------------------------------
   // <T>获得本地视图。</T>
   MO_INLINE ID3D10ShaderResourceView* NativeView(){
      return _piView;
   }
   //------------------------------------------------------------
   // <T>获得取样器状态。</T>
   MO_INLINE ID3D10SamplerState* NativeState(){
      return _piState;
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
class MO_BG_DECLARE FBfRenderDevice : public FRenderDevice
{
   MO_CLASS_DECLARE_INHERITS(FBfRenderDevice, FRenderDevice);
protected:
   D3D10_DRIVER_TYPE _driverType;
   // 纹理信息
   TBool _optionTexture;
   // 关联顶点缓冲集合
   FRenderFlatTextureList* _pLinkFlatTextures;
   FRenderCubeTextureList* _pLinkCubeTextures;
   // 接口指针
   HWND _windowHandle;
   IDXGISwapChain* _piSwapChain;
   ID3D10Device* _piDevice;
   GPtr<FBfRenderTarget> _defaultRenderTarget;
   ID3D10RasterizerState* _piRasterizerState;
   ID3D10BlendState* _piBlendEnableState;
   ID3D10BlendState* _piBlendDisableState;
public:
   FBfRenderDevice();
   MO_ABSTRACT ~FBfRenderDevice();
protected:
   TBool UpdateContext();
public:
   //------------------------------------------------------------
   // <T>获得窗口句柄。</T>
   MO_INLINE HWND WindowHandle(){
      return _windowHandle;
   }
   //------------------------------------------------------------
   // <T>设置窗口句柄。</T>
   MO_INLINE void SetWindowHandle(HWND handle){
      _windowHandle = handle;
   }
   //------------------------------------------------------------
   // <T>获得本地交换链。</T>
   MO_INLINE IDXGISwapChain* NativeSwapChain(){
      return _piSwapChain;
   }
   //------------------------------------------------------------
   // <T>获得本地设备。</T>
   MO_INLINE ID3D10Device* NativeDevice(){
      return _piDevice;
   }
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
   MO_OVERRIDE TResult SetLayout(FRenderLayout* pLayout);
   MO_OVERRIDE TResult BindConstData(ERenderShader shaderCd, TInt slot, ERenderShaderParameterFormat formatCd, TAnyC* pData, TInt length);
   MO_OVERRIDE TResult BindConstFloat3(ERenderShader shaderCd, TInt slot, TFloat x = 0.0f, TFloat y = 0.0f, TFloat z = 0.0f);
   MO_OVERRIDE TResult BindConstFloat4(ERenderShader shaderCd, TInt slot, TFloat x = 0.0f, TFloat y = 0.0f, TFloat z = 0.0f, TFloat w = 1.0f);
   MO_OVERRIDE TResult BindConstMatrix3x3(ERenderShader shaderCd, TInt slot, const SFloatMatrix3d& matrix);
   MO_OVERRIDE TResult BindConstMatrix4x4(ERenderShader shaderCd, TInt slot, const SFloatMatrix3d& matrix);
   MO_OVERRIDE TResult BindShaderBuffer(FRenderShaderBuffer* pBuffer);
   MO_OVERRIDE TResult BindVertexBuffer(TInt slot, FRenderVertexBuffer* pVertexBuffer, TInt offset, ERenderVertexFormat formatCd);
   MO_OVERRIDE TResult BindTexture(TInt slot, FRenderTexture* pTexture);
   MO_OVERRIDE TResult DrawTriangles(FRenderIndexBuffer* pIndexBuffer, TInt offset, TInt count);
   MO_OVERRIDE TResult Present();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_BG_RENDER_H__
