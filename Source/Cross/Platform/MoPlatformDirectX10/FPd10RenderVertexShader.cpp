#include "MoPd10Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd10RenderVertexShader, FRenderVertexShader);

//============================================================
// <T>构造渲染程序。</T>
//============================================================
FPd10RenderVertexShader::FPd10RenderVertexShader(){
   MO_CLEAR(_piData);
   MO_CLEAR(_piShader);
}

//============================================================
// <T>析构渲染程序。</T>
//============================================================
FPd10RenderVertexShader::~FPd10RenderVertexShader(){
   MO_RELEASE(_piData);
   MO_RELEASE(_piShader);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd10RenderVertexShader::Setup(){
   return ESuccess;
}

//============================================================
// <T>编译处理。</T>
//
// @param pSource 代码字符串
// @return 处理结果
//============================================================
TResult FPd10RenderVertexShader::Compile(TCharC* pSource){
   // 获得设备信息
   MO_CHECK(_pDevice, return ENull);
   FPd10RenderDevice* pRenderDevice = _pDevice->Convert<FPd10RenderDevice>();
   FRenderCapability* pCapability = pRenderDevice->Capability();
   TCharC* pShaderVersion = pCapability->ShaderVertexVersion();
   // 设置标志
   //TUint32 shaderFlags = D3DCOMPILE_ENABLE_STRICTNESS;
   TUint32 shaderFlags = 0;
#ifdef _MO_DEBUG
    shaderFlags |= D3DCOMPILE_DEBUG;
#endif // _MO_DEBUG
    // 上传代码
   TInt length = RString::Length(pSource);
   ID3D10Blob* piError = NULL;
   HRESULT shaderResult = S_OK;
   HRESULT dxResult = D3DX10CompileFromMemory(pSource, length, NULL, NULL, NULL, "main", pShaderVersion, shaderFlags, 0, NULL, &_piData, &piError, &shaderResult);
   if(FAILED(dxResult) || FAILED(shaderResult)){
      TCharC* pBuffer = (TCharC*)piError->GetBufferPointer();
      MO_ERROR("Compile from memory failure.\n%s", pBuffer);
      MO_RELEASE(piError);
      MO_FATAL("Compile failure.");
      return EFailure;
   }
   // 创建渲染器
   TAny* pData = _piData->GetBufferPointer();
   TInt dataSize = _piData->GetBufferSize();
   dxResult = pRenderDevice->NativeDevice()->CreateVertexShader(pData, dataSize, &_piShader);
   if(FAILED(dxResult)){
      MO_FATAL("Create vertex shader failure.");
      return EFailure;
   }
   MO_INFO("Create vertex shader success. (status=%d)\n%s", dxResult, pSource);
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd10RenderVertexShader::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd10RenderVertexShader::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd10RenderVertexShader::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
