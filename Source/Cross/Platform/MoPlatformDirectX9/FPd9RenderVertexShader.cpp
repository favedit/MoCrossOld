#include "MoPd9Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd9RenderVertexShader, FRenderVertexShader);

//============================================================
// <T>构造渲染程序。</T>
//============================================================
FPd9RenderVertexShader::FPd9RenderVertexShader(){
   _pBuffer = MO_CREATE(FPd9RenderShaderBuffer);
   _pBuffer->SetName("VertexBuffer");
   _pBuffer->SetLinker("VertexBuffer");
   MO_CLEAR(_piData);
   MO_CLEAR(_piShader);
   MO_CLEAR(_piTable);
}

//============================================================
// <T>析构渲染程序。</T>
//============================================================
FPd9RenderVertexShader::~FPd9RenderVertexShader(){
   MO_DELETE(_pBuffer);
   MO_RELEASE(_piData);
   MO_RELEASE(_piShader);
   MO_RELEASE(_piTable);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderVertexShader::Setup(){
   return ESuccess;
}

//============================================================
// <T>编译处理。</T>
//
// @param pSource 代码字符串
// @return 处理结果
//============================================================
TResult FPd9RenderVertexShader::Compile(TCharC* pSource){
   // 获得设备信息
   MO_CHECK(_pDevice, return ENull);
   FPd9RenderDevice* pRenderDevice = _pDevice->Convert<FPd9RenderDevice>();
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
   ID3DXBuffer* piError = NULL;
   HRESULT dxResult = D3DXCompileShader(pSource, length, NULL, NULL, "main", pShaderVersion, shaderFlags, &_piData, &piError, &_piTable);
   if(FAILED(dxResult)){
      TCharC* pBuffer = (TCharC*)piError->GetBufferPointer();
      MO_ERROR("Compile from memory failure.\n%s", pBuffer);
      MO_RELEASE(piError);
      MO_FATAL("Compile failure.");
      return EFailure;
   }
   // 创建渲染器
   TAny* pData = _piData->GetBufferPointer();
   TInt dataSize = _piData->GetBufferSize();
   dxResult = pRenderDevice->NativeDevice()->CreateVertexShader((const DWORD*)pData, &_piShader);
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
TResult FPd9RenderVertexShader::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderVertexShader::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderVertexShader::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
