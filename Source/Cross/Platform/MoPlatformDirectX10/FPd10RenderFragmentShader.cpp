#include "MoPd10Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd10RenderFragmentShader, FRenderVertexShader);

//============================================================
// <T>构造渲染程序。</T>
//============================================================
FPd10RenderFragmentShader::FPd10RenderFragmentShader(){
   MO_CLEAR(_piData);
   MO_CLEAR(_piShader);
}

//============================================================
// <T>析构渲染程序。</T>
//============================================================
FPd10RenderFragmentShader::~FPd10RenderFragmentShader(){
   MO_RELEASE(_piData);
   MO_RELEASE(_piShader);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd10RenderFragmentShader::Setup(){
   //_renderId.uint32 = glCreateShader(GL_FRAGMENT_SHADER);
   //TResult resultCd = _pDevice->CheckError("glCreateShader", "Create fragment shader failure. (shader_id=%d)", _renderId.uint32);
   //return resultCd;
   return ESuccess;
}

//============================================================
// <T>编译处理。</T>
//
// @param pSource 代码字符串
// @return 处理结果
//============================================================
TResult FPd10RenderFragmentShader::Compile(TCharC* pSource){
   // 获得设备信息
   MO_CHECK(_pDevice, return ENull);
   FPd10RenderDevice* pRenderDevice = _pDevice->Convert<FPd10RenderDevice>();
   FRenderCapability* pCapability = pRenderDevice->Capability();
   TCharC* pShaderVersion = pCapability->ShaderFragmentVersion();
   // 上传代码
   TInt length = RString::Length(pSource);
   ID3D10Blob* piError = NULL;
   HRESULT shaderResult = S_OK;
   HRESULT dxResult = D3DX10CompileFromMemory(pSource, length, NULL, NULL, NULL, "main", pShaderVersion, 0, 0, NULL, &_piData, &piError, &shaderResult);
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
   dxResult = pRenderDevice->NativeDevice()->CreatePixelShader(pData, dataSize, &_piShader);
   if(FAILED(dxResult)){
      MO_FATAL("Create fragment shader failure.");
      return EFailure;
   }
   MO_INFO("Create fragment shader success. (status=%d)\n%s", dxResult, pSource);
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd10RenderFragmentShader::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd10RenderFragmentShader::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd10RenderFragmentShader::Dispose(){
   //TResult resultCd = ESuccess;
   //if(_renderId.uint32 != 0){
   //   glDeleteShader(_renderId.uint32);
   //   resultCd = _pDevice->CheckError("glCreateShader", "Delete fragment shader failure. (shader_id=%d)", _renderId.uint32);
   //   _renderId.uint32 = 0;
   //}
   //return resultCd;
   return ESuccess;
}

MO_NAMESPACE_END
