#include "MoPd9Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd9RenderVertexBuffer, FRenderVertexBuffer);

//============================================================
// <T>构造渲染顶点缓冲。</T>
//============================================================
FPd9RenderVertexBuffer::FPd9RenderVertexBuffer(){
   MO_CLEAR(_piBuffer);
}

//============================================================
// <T>析构渲染顶点缓冲。</T>
//============================================================
FPd9RenderVertexBuffer::~FPd9RenderVertexBuffer(){
   MO_RELEASE(_piBuffer);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderVertexBuffer::OnSetup(){
   TResult resultCd = ESuccess;
   MO_CHECK(_pDevice, return ENull);
   FPd9RenderDevice* pRenderDevice = _pDevice->Convert<FPd9RenderDevice>();
   //............................................................
   _dataLength = _stride * _count;
   if(_dataLength == 0){
      return EContinue;
   }
   return resultCd;
}

//============================================================
// <T>上传数据。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @return 处理结果
//============================================================
TResult FPd9RenderVertexBuffer::Upload(TByteC* pData, TInt length){
   // 检查参数
   MO_CHECK(pData, return ENull);
   MO_CHECK(length > 0, return EFailure);
   TResult resultCd = ESuccess;
   MO_CHECK(_pDevice, return ENull);
   FPd9RenderDevice* pRenderDevice = _pDevice->Convert<FPd9RenderDevice>();
   MO_RELEASE(_piBuffer);
   //............................................................
   // 创建缓冲
   HRESULT dxResult = pRenderDevice->NativeDevice()->CreateVertexBuffer(_dataLength, 0, 0, D3DPOOL_DEFAULT, &_piBuffer, NULL);
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
TResult FPd9RenderVertexBuffer::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderVertexBuffer::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderVertexBuffer::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
