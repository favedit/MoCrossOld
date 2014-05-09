#ifndef __MO_PD9_RENDER_H__
#define __MO_PD9_RENDER_H__
//************************************************************

#ifndef __MO_PD9_COMMON_H__
#include "MoPd9Common.h"
#endif // __MO_PD9_COMMON_H__

#ifndef __MO_PD9_CORE_H__
#include "MoPd9Core.h"
#endif // __MO_PD9_CORE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>渲染层信息。</T>
//============================================================
class MO_PD9_DECLARE FPd9RenderLayout : public FRenderLayout
{
   MO_CLASS_DECLARE_INHERITS(FPd9RenderLayout, FRenderLayout);
protected:
   TInt _count;
   IDirect3DVertexBuffer9* _piBuffer[MO_INPUT_ELEMENT_MAXCNT];
   UINT _strides[MO_INPUT_ELEMENT_MAXCNT];
   UINT _offsets[MO_INPUT_ELEMENT_MAXCNT];
public:
   FPd9RenderLayout();
   MO_ABSTRACT ~FPd9RenderLayout();
public:
   //------------------------------------------------------------
   // <T>获得总数。</T>
   MO_INLINE TInt Count(){
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得数据。</T>
   MO_INLINE IDirect3DVertexBuffer9** Buffer(){
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
typedef MO_PD9_DECLARE FObjects<FPd9RenderLayout*> FPd9RenderLayoutCollection;

//============================================================
// <T>渲染顶点缓冲。</T>
//============================================================
class MO_PD9_DECLARE FPd9RenderVertexBuffer : public FRenderVertexBuffer
{
   MO_CLASS_DECLARE_INHERITS(FPd9RenderVertexBuffer, FRenderVertexBuffer);
protected:
   IDirect3DVertexBuffer9* _piBuffer;
public:
   FPd9RenderVertexBuffer();
   MO_ABSTRACT ~FPd9RenderVertexBuffer();
public:
   //------------------------------------------------------------
   // <T>获得本地缓冲。</T>
   MO_INLINE IDirect3DVertexBuffer9* NativeBuffer(){
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
class MO_PD9_DECLARE FPd9RenderIndexBuffer : public FRenderIndexBuffer
{
   MO_CLASS_DECLARE_INHERITS(FPd9RenderIndexBuffer, FRenderVertexBuffer);
protected:
   IDirect3DIndexBuffer9* _piBuffer;
public:
   FPd9RenderIndexBuffer();
   MO_ABSTRACT ~FPd9RenderIndexBuffer();
public:
   //------------------------------------------------------------
   // <T>获得本地缓冲。</T>
   MO_INLINE IDirect3DIndexBuffer9* NativeBuffer(){
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
class MO_PD9_DECLARE FPd9RenderShaderBuffer : public FRenderShaderBuffer
{
   MO_CLASS_DECLARE_INHERITS(FPd9RenderShaderBuffer, FRenderShaderBuffer);
protected:
   //ID3D9Buffer* _piBuffer;
public:
   FPd9RenderShaderBuffer();
   MO_ABSTRACT ~FPd9RenderShaderBuffer();
public:
   ////------------------------------------------------------------
   //// <T>获得本地缓冲。</T>
   //MO_INLINE ID3D9Buffer* NativeiBuffer(){
   //   return _piBuffer;
   //}
   ////------------------------------------------------------------
   //// <T>设置本地缓冲。</T>
   //MO_INLINE void SetNativeiBuffer(ID3D9Buffer* piBuffer){
   //   _piBuffer = piBuffer;
   //}
public:
   MO_OVERRIDE TResult OnSetup();
public:
   MO_OVERRIDE TResult Commit();
   MO_OVERRIDE TResult Bind();
};

//============================================================
// <T>渲染器参数。</T>
//============================================================
class MO_PD9_DECLARE FPd9RenderShaderParameter : public FRenderShaderParameter
{
   MO_CLASS_DECLARE_INHERITS(FPd9RenderShaderParameter, FRenderShaderParameter);
protected:
   //ID3D9ShaderReflectionVariable* _piVariable;
public:
   FPd9RenderShaderParameter();
   MO_ABSTRACT ~FPd9RenderShaderParameter();
public:
   ////------------------------------------------------------------
   //// <T>设置缓冲。</T>
   //MO_INLINE void NativeVariable(FPd9RenderShaderBuffer* pBuffer){
   //   _buffer = pBuffer;
   //}
   ////------------------------------------------------------------
   //// <T>获得本地变量。</T>
   //MO_INLINE ID3D9ShaderReflectionVariable* NativeVariable(){
   //   return _piVariable;
   //}
public:
   //TResult LinkNative(ID3D9ShaderReflectionVariable* piVariable);
};

//============================================================
// <T>顶点渲染器。</T>
//============================================================
class MO_PD9_DECLARE FPd9RenderVertexShader : public FRenderVertexShader
{
   MO_CLASS_DECLARE_INHERITS(FPd9RenderVertexShader, FRenderVertexShader);
protected:
   FPd9RenderShaderBuffer* _pBuffer;
   ID3DXBuffer* _piData;
   IDirect3DVertexShader9* _piShader;
   ID3DXConstantTable* _piTable;
public:
   FPd9RenderVertexShader();
   MO_ABSTRACT ~FPd9RenderVertexShader();
public:
   //------------------------------------------------------------
   // <T>获得数据。</T>
   MO_INLINE FPd9RenderShaderBuffer* Buffer(){
      return _pBuffer;
   }
   //------------------------------------------------------------
   // <T>获得本地数据。</T>
   MO_INLINE ID3DXBuffer* NativeData(){
      return _piData;
   }
   //------------------------------------------------------------
   // <T>获得本地渲染器。</T>
   MO_INLINE IDirect3DVertexShader9* NativeShader(){
      return _piShader;
   }
   //------------------------------------------------------------
   // <T>获得本地渲染器。</T>
   MO_INLINE ID3DXConstantTable* NativeTable(){
      return _piTable;
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
class MO_PD9_DECLARE FPd9RenderFragmentShader : public FRenderFragmentShader
{
   MO_CLASS_DECLARE_INHERITS(FPd9RenderFragmentShader, FRenderFragmentShader);
protected:
   FPd9RenderShaderBuffer* _pBuffer;
   ID3DXBuffer* _piData;
   IDirect3DPixelShader9* _piShader;
   ID3DXConstantTable* _piTable;
public:
   FPd9RenderFragmentShader();
   MO_ABSTRACT ~FPd9RenderFragmentShader();
public:
   //------------------------------------------------------------
   // <T>获得数据。</T>
   MO_INLINE FPd9RenderShaderBuffer* Buffer(){
      return _pBuffer;
   }
   //------------------------------------------------------------
   // <T>获得本地数据。</T>
   MO_INLINE ID3DXBuffer* NativeData(){
      return _piData;
   }
   //------------------------------------------------------------
   // <T>获得本地渲染器。</T>
   MO_INLINE IDirect3DPixelShader9* NativeShader(){
      return _piShader;
   }
   //------------------------------------------------------------
   // <T>获得本地渲染器。</T>
   MO_INLINE ID3DXConstantTable* NativeTable(){
      return _piTable;
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
class MO_PD9_DECLARE FPd9RenderProgram : public FRenderProgram
{
   MO_CLASS_DECLARE_INHERITS(FPd9RenderProgram, FRenderProgram);
protected:
   //ID3D9InputLayout* _piInputLayout;
   //MO_D3D9_INPUT_ELEMENT_DESC_ARRAY inputElements;
public:
   FPd9RenderProgram();
   MO_ABSTRACT ~FPd9RenderProgram();
public:
   ////------------------------------------------------------------
   //// <T>获得本地输入层次。</T>
   //MO_INLINE ID3D9InputLayout* NativeInputLayout(){
   //   return _piInputLayout;
   //}
public:
   MO_OVERRIDE TInt FindDefine(TCharC* pCode);
   MO_OVERRIDE TInt FindAttribute(TCharC* pCode);
   MO_OVERRIDE TResult BindAttribute(TInt slot, TCharC* pCode);
protected:
   TResult BuildShader(FRenderShader* pShader, FPd9RenderShaderBuffer* pBuffer, ID3DXConstantTable* piTable);
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
class MO_PD9_DECLARE FPd9RenderTarget : public FRenderTarget
{
   MO_CLASS_DECLARE_INHERITS(FPd9RenderTarget, FRenderTarget);
protected:
   //ID3D9RenderTargetView* _piRenderTarget;
   //ID3D9DepthStencilView* _piDepthStencil;
public:
   FPd9RenderTarget();
   MO_ABSTRACT ~FPd9RenderTarget();
public:
   ////------------------------------------------------------------
   //// <T>获得本地渲染目标。</T>
   //MO_INLINE ID3D9RenderTargetView* NativeRenderTarget(){
   //   return _piRenderTarget;
   //}
   ////------------------------------------------------------------
   //// <T>设置本地渲染目标。</T>
   //MO_INLINE void SetNativeRenderTarget(ID3D9RenderTargetView* piRenderTarget){
   //   _piRenderTarget = piRenderTarget;
   //}
   ////------------------------------------------------------------
   //// <T>获得本地深度缓冲。</T>
   //MO_INLINE ID3D9DepthStencilView* NativeDepthStencil(){
   //   return _piDepthStencil;
   //}
   ////------------------------------------------------------------
   //// <T>设置本地深度缓冲。</T>
   //MO_INLINE void SetNativeDepthStencil(ID3D9DepthStencilView* piDepthStencil){
   //   _piDepthStencil = piDepthStencil;
   //}
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
class MO_PD9_DECLARE FPd9RenderFlatTexture : public FRenderFlatTexture
{
   MO_CLASS_DECLARE_INHERITS(FPd9RenderFlatTexture, FRenderFlatTexture);
protected:
   IDirect3DTexture9* _piTexture;
public:
   FPd9RenderFlatTexture();
   MO_ABSTRACT ~FPd9RenderFlatTexture();
public:
   //------------------------------------------------------------
   // <T>获得本地纹理。</T>
   MO_INLINE IDirect3DTexture9* NativeTexture(){
      return _piTexture;
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
class MO_PD9_DECLARE FPd9RenderCubeTexture : public FRenderCubeTexture
{
   MO_CLASS_DECLARE_INHERITS(FPd9RenderCubeTexture, FRenderFlatTexture);
protected:
   IDirect3DCubeTexture9* _piTexture;
public:
   FPd9RenderCubeTexture();
   MO_ABSTRACT ~FPd9RenderCubeTexture();
public:
   //------------------------------------------------------------
   // <T>获得本地纹理。</T>
   MO_INLINE IDirect3DCubeTexture9* NativeTexture(){
      return _piTexture;
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
class MO_PD9_DECLARE FPd9RenderDevice : public FRenderDevice
{
   MO_CLASS_DECLARE_INHERITS(FPd9RenderDevice, FRenderDevice);
protected:
   HWND _windowHandle;
   IDirect3D9* _piDirect3d;
   IDirect3DDevice9* _piDevice;
   //// 纹理信息
   //TBool _optionTexture;
   //// 关联顶点缓冲集合
   //FRenderFlatTextureList* _pLinkFlatTextures;
   //FRenderCubeTextureList* _pLinkCubeTextures;
   //// 接口指针
   //IDXGISwapChain* _piSwapChain;
   //ID3D9Device* _piDevice;
   //GPtr<FPd9RenderTarget> _defaultRenderTarget;
public:
   FPd9RenderDevice();
   MO_ABSTRACT ~FPd9RenderDevice();
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
   // <T>获得本地设备。</T>
   MO_INLINE IDirect3DDevice9* NativeDevice(){
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
#endif // __MO_PD9_RENDER_H__
