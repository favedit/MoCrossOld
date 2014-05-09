#include "MoPd9Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd9RenderShaderBuffer, FRenderShaderBuffer);

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
   TResult resultCd = FRenderShaderBuffer::OnSetup();
   //MO_CHECK(_pDevice, return ENull);
   //MO_CHECK(_dataLength > 0, return EOutRange);
   //FPd9RenderDevice* pRenderDevice = _pDevice->Convert<FPd9RenderDevice>();
   //// 设置描述
   //D3D9_BUFFER_DESC descriptor = {0};
   //descriptor.ByteWidth = _dataLength;
   //descriptor.Usage = D3D9_USAGE_DEFAULT;
   //descriptor.BindFlags = D3D9_BIND_CONSTANT_BUFFER;
   //descriptor.CPUAccessFlags = 0;
   //descriptor.MiscFlags = 0;
   //// 设置数据
   //D3D9_SUBRESOURCE_DATA data = {0};
   //data.pSysMem = _pData->Memory();
   //data.SysMemPitch = 0;
   //data.SysMemSlicePitch = 0;
   //// 创建缓冲
   //HRESULT dxResult = pRenderDevice->NativeDevice()->CreateBuffer(&descriptor, &data, &_piBuffer);
   //if(FAILED(dxResult)){
   //   MO_FATAL("Create buffer failure.");
   //   return EFailure;
   //}
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
