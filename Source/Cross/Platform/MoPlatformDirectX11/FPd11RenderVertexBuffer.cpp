#include "MoPd11Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd11RenderVertexBuffer, FRenderVertexBuffer);

//============================================================
// <T>构造渲染顶点缓冲。</T>
//============================================================
FPd11RenderVertexBuffer::FPd11RenderVertexBuffer(){
   MO_CLEAR(_piBuffer);
}

//============================================================
// <T>析构渲染顶点缓冲。</T>
//============================================================
FPd11RenderVertexBuffer::~FPd11RenderVertexBuffer(){
   MO_RELEASE(_piBuffer);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderVertexBuffer::OnSetup(){
   TResult resultCd = ESuccess;
   MO_CHECK(_pDevice, return ENull);
   FPd11RenderDevice* pRenderDevice = _pDevice->Convert<FPd11RenderDevice>();
   //............................................................
   if(_dataLength == 0){
      return EContinue;
   }
   //............................................................
   //// 创建缓冲
   //D3D11_BUFFER_DESC description;
   //RType<D3D11_BUFFER_DESC>::Clear(&description);
   //description.BindFlags = D3D11_BIND_VERTEX_BUFFER;
   //description.ByteWidth = _dataLength;
   //description.CPUAccessFlags = 0;
   //description.MiscFlags = 0;
   //description.Usage = D3D11_USAGE_STAGING;
   //HRESULT dxResult = pRenderDevice->NativeDevice()->CreateBuffer(&description, NULL, &_piBuffer);
   //if(FAILED(dxResult)){
   //   MO_FATAL("Create buffer failure.");
   //   return EFailure;
   //}
   return resultCd;
}

//============================================================
// <T>上传数据。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @return 处理结果
//============================================================
TResult FPd11RenderVertexBuffer::Upload(TByteC* pData, TInt length){
   // 检查参数
   MO_CHECK(pData, return ENull);
   MO_CHECK(length > 0, return EFailure);
   TResult resultCd = ESuccess;
   MO_CHECK(_pDevice, return ENull);
   FPd11RenderDevice* pRenderDevice = _pDevice->Convert<FPd11RenderDevice>();
   MO_RELEASE(_piBuffer);
   //............................................................
   // 设置描述
   D3D11_BUFFER_DESC descriptor = {0};
   descriptor.ByteWidth = _dataLength;
   descriptor.Usage = D3D11_USAGE_DEFAULT;
   descriptor.BindFlags = D3D11_BIND_VERTEX_BUFFER;
   descriptor.CPUAccessFlags = 0;
   descriptor.MiscFlags = 0;
   descriptor.StructureByteStride = 0;
   // 设置数据
   D3D11_SUBRESOURCE_DATA data = {0};
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
TResult FPd11RenderVertexBuffer::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderVertexBuffer::Resume(){
   //// 生成编号
   //glGenBuffers(1, &_bufferId);
   //MO_FATAL_CHECK(_bufferId != 0, return EFailure,
   //      "Generate vertex buffer id failure. (buffer_id=%d)", _bufferId);
   //// 绑定编号
   //glBindBuffer(GL_ARRAY_BUFFER, _bufferId);
   //_pDevice->CheckError("glBindBuffer", "Bind array buffer. (buffer_id=%d)", _bufferId);
   //// 上传数据
   //TInt length = _pDataStream->Length();
   //TByteC* pData = _pDataStream->MemoryC();
   //glBufferData(GL_ARRAY_BUFFER, length, pData, GL_STATIC_DRAW);
   //_pDevice->CheckError("glBufferData", "Upload array buffer data. (buffer_id=%d, length=%d, data=0x%08X)", _bufferId, length, pData);
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderVertexBuffer::Dispose(){
   //if(_bufferId != 0){
   //   TInt size = _stride * _count;
   //   glDeleteBuffers(size, &_bufferId);
   //   _bufferId = 0;
   //}
   return ESuccess;
}

MO_NAMESPACE_END
