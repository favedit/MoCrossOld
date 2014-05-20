#include "MoPd9Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd9RenderShaderBuffer, FRenderProgramBuffer);

//============================================================
// <T>构造渲染器缓冲。</T>
//============================================================
FPd9RenderShaderBuffer::FPd9RenderShaderBuffer(){
   //MO_CLEAR(_piBuffer);
}

//============================================================
// <T>析构渲染器缓冲。</T>
//============================================================
FPd9RenderShaderBuffer::~FPd9RenderShaderBuffer(){
   //MO_RELEASE(_piBuffer);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderShaderBuffer::OnSetup(){
   TResult resultCd = FRenderProgramBuffer::OnSetup();
   return resultCd;
}

//============================================================
// <T>提交处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderShaderBuffer::Commit(){
   MO_CHECK(_pDevice, return ENull);
   TResult resultCd = ESuccess;
   FPd9RenderDevice* pRenderDevice = _pDevice->Convert<FPd9RenderDevice>();
   //............................................................
   // 更新数据
   TByte* pData = _pData->Memory();
   TInt length = _pData->Length();
   TInt count = length >> 4;
   if(count == 0){
      return ESuccess;
   }
   // 更新显示相关
   if(_shaderCd == ERenderShader_Vertex){
      HRESULT dxResult = pRenderDevice->NativeDevice()->SetVertexShaderConstantF(0, (TFloatC*)pData, count);
      if(FAILED(dxResult)){
         MO_FATAL("Set vertex shader constant failure.");
      }
   }else if(_shaderCd == ERenderShader_Fragment){
      HRESULT dxResult = pRenderDevice->NativeDevice()->SetPixelShaderConstantF(0, (TFloatC*)pData, count);
      if(FAILED(dxResult)){
         MO_FATAL("Set pixel shader constant failure.");
      }
   }else{
      MO_FATAL("Render shader type is unknown. (shader=%d)", _shaderCd);
   }
   //MO_DEBUG("Update sub resource. (name=%s, memory=0x%08X, length=%d)", (TCharC*)_name, pData, length);
   return resultCd;
}

//============================================================
// <T>绑定处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderShaderBuffer::Bind(){
   return ESuccess;
}

MO_NAMESPACE_END
