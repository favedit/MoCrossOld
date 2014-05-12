#include "MoBfRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FBfRenderIndexBuffer, FRenderVertexBuffer);

//============================================================
// <T>构造渲染索引缓冲。</T>
//============================================================
FBfRenderIndexBuffer::FBfRenderIndexBuffer(){
   MO_CLEAR(_piBuffer);
}

//============================================================
// <T>析构渲染索引缓冲。</T>
//============================================================
FBfRenderIndexBuffer::~FBfRenderIndexBuffer(){
   MO_RELEASE(_piBuffer);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FBfRenderIndexBuffer::OnSetup(){
   TResult resultCd = FRenderIndexBuffer::OnSetup();
   return resultCd;
}

//============================================================
// <T>上传数据。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @return 处理结果
//============================================================
TResult FBfRenderIndexBuffer::Upload(TByteC* pData, TInt length){
   // 检查参数
   MO_CHECK(pData, return ENull);
   MO_CHECK(length > 0, return EFailure);
   TResult resultCd = ESuccess;
   MO_CHECK(_pDevice, return ENull);
   FBfRenderDevice* pRenderDevice = _pDevice->Convert<FBfRenderDevice>();
   MO_RELEASE(_piBuffer);
   //............................................................
   // 设置描述
   D3D10_BUFFER_DESC descriptor = {0};
   descriptor.ByteWidth = _dataLength;
   descriptor.Usage = D3D10_USAGE_DEFAULT;
   descriptor.BindFlags = D3D10_BIND_INDEX_BUFFER;
   descriptor.CPUAccessFlags = 0;
   descriptor.MiscFlags = 0;
   // 设置数据
   D3D10_SUBRESOURCE_DATA data = {0};
   data.pSysMem = pData;
   data.SysMemPitch = 0;
   data.SysMemSlicePitch = 0;
   // 创建缓冲
   HRESULT dxResult = pRenderDevice->NativeDevice()->CreateBuffer(&descriptor, &data, &_piBuffer);
   if(FAILED(dxResult)){
      MO_FATAL("Create buffer failure.");
      return EFailure;
   }
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FBfRenderIndexBuffer::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FBfRenderIndexBuffer::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FBfRenderIndexBuffer::Dispose(){
   MO_RELEASE(_piBuffer);
   return ESuccess;
}

MO_NAMESPACE_END
