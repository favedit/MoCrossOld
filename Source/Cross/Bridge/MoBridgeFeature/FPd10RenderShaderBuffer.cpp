#include "MoBfRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FBfRenderShaderBuffer, FRenderShaderBuffer);

//============================================================
// <T>构造渲染器缓冲。</T>
//============================================================
FBfRenderShaderBuffer::FBfRenderShaderBuffer(){
   MO_CLEAR(_piBuffer);
}

//============================================================
// <T>析构渲染器缓冲。</T>
//============================================================
FBfRenderShaderBuffer::~FBfRenderShaderBuffer(){
   MO_RELEASE(_piBuffer);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FBfRenderShaderBuffer::OnSetup(){
   TResult resultCd = FRenderShaderBuffer::OnSetup();
   MO_CHECK(_pDevice, return ENull);
   MO_CHECK(_dataLength > 0, return EOutRange);
   FBfRenderDevice* pRenderDevice = _pDevice->Convert<FBfRenderDevice>();
   // 设置描述
   D3D10_BUFFER_DESC descriptor = {0};
   descriptor.ByteWidth = _dataLength;
   descriptor.Usage = D3D10_USAGE_DEFAULT;
   descriptor.BindFlags = D3D10_BIND_CONSTANT_BUFFER;
   descriptor.CPUAccessFlags = 0;
   descriptor.MiscFlags = 0;
   // 设置数据
   D3D10_SUBRESOURCE_DATA data = {0};
   data.pSysMem = _pData->Memory();
   data.SysMemPitch = 0;
   data.SysMemSlicePitch = 0;
   // 创建缓冲
   HRESULT dxResult = pRenderDevice->NativeDevice()->CreateBuffer(&descriptor, &data, &_piBuffer);
   if(FAILED(dxResult)){
      MO_FATAL("Create buffer failure.");
      return EFailure;
   }
   return resultCd;
}

//============================================================
// <T>提交处理。</T>
//
// @return 处理结果
//============================================================
TResult FBfRenderShaderBuffer::Commit(){
   MO_CHECK(_pDevice, return ENull);
   TResult resultCd = ESuccess;
   FBfRenderDevice* pRenderDevice = _pDevice->Convert<FBfRenderDevice>();
   //............................................................
   // 更新数据
   TByte* pData = _pData->Memory();
   TInt length = _pData->Length();
   pRenderDevice->NativeDevice()->UpdateSubresource(_piBuffer, 0, NULL, pData, 0, 0);
   //MO_DEBUG("Update sub resource. (name=%s, memory=0x%08X, length=%d)", (TCharC*)_name, pData, length);
   //GetErrorInfo();
   return resultCd;
}

//============================================================
// <T>绑定处理。</T>
//
// @return 处理结果
//============================================================
TResult FBfRenderShaderBuffer::Bind(){
   // 检查是否变更
   if(!_statusChanged){
      return EContinue;
   }
   //............................................................
   MO_CHECK(_pDevice, return ENull);
   MO_CHECK(_slot >= 0, return EOutRange);
   TResult resultCd = ESuccess;
   FBfRenderDevice* pRenderDevice = _pDevice->Convert<FBfRenderDevice>();
   //............................................................
   // 更新数据
   if((_groupCd == ERenderShaderBuffer_Global) || (_groupCd == ERenderShaderBuffer_Technique) || (_groupCd == ERenderShaderBuffer_Effect)){
      pRenderDevice->NativeDevice()->VSSetConstantBuffers(_slot, 1, &_piBuffer);
      pRenderDevice->NativeDevice()->PSSetConstantBuffers(_slot, 1, &_piBuffer);
   }else if(_groupCd == ERenderShaderBuffer_Renderable){
      // 更新显示相关
      if(_shaderCd == ERenderShader_Vertex){
         pRenderDevice->NativeDevice()->VSSetConstantBuffers(_slot, 1, &_piBuffer);
      }else if(_shaderCd == ERenderShader_Fragment){
         pRenderDevice->NativeDevice()->PSSetConstantBuffers(_slot, 1, &_piBuffer);
      }else{
         MO_FATAL("Render shader type is unknown. (shader=%d)", _shaderCd);
      }
   }else{
      MO_FATAL("Render shader group is unknown. (group=%d)", _groupCd);
   }
   return resultCd;
}

MO_NAMESPACE_END
