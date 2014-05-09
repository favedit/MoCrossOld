#include "MoPd9Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd9RenderIndexBuffer, FRenderVertexBuffer);

//============================================================
// <T>构造渲染索引缓冲。</T>
//============================================================
FPd9RenderIndexBuffer::FPd9RenderIndexBuffer(){
   MO_CLEAR(_piBuffer);
}

//============================================================
// <T>析构渲染索引缓冲。</T>
//============================================================
FPd9RenderIndexBuffer::~FPd9RenderIndexBuffer(){
   MO_RELEASE(_piBuffer);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderIndexBuffer::OnSetup(){
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
TResult FPd9RenderIndexBuffer::Upload(TByteC* pData, TInt length){
   // 检查参数
   MO_CHECK(pData, return ENull);
   MO_CHECK(length > 0, return EFailure);
   TResult resultCd = ESuccess;
   MO_CHECK(_pDevice, return ENull);
   FPd9RenderDevice* pRenderDevice = _pDevice->Convert<FPd9RenderDevice>();
   MO_RELEASE(_piBuffer);
   //............................................................
   // 创建缓冲
   HRESULT dxResult = pRenderDevice->NativeDevice()->CreateIndexBuffer(_dataLength, 0, D3DFMT_INDEX16, D3DPOOL_DEFAULT, &_piBuffer, NULL);
   if(FAILED(dxResult)){
      MO_FATAL("Create buffer failure.");
      return EFailure;
   }
   // 上传数据
   TByte* pUpload = NULL;
   dxResult = _piBuffer->Lock( 0, 0, (TAny**)&pUpload, 0);
   if(FAILED(dxResult)){
      MO_FATAL("Lock buffer failure.");
      return EFailure;
   }
   MO_LIB_MEMORY_COPY(pUpload, _dataLength, pData, _dataLength);
   _piBuffer->Unlock();
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderIndexBuffer::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderIndexBuffer::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderIndexBuffer::Dispose(){
   //MO_RELEASE(_piBuffer);
   return ESuccess;
}

MO_NAMESPACE_END
