#include "MoPd11Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd11RenderIndexBuffer, FRenderVertexBuffer);

//============================================================
// <T>构造渲染索引缓冲。</T>
//============================================================
FPd11RenderIndexBuffer::FPd11RenderIndexBuffer(){
   MO_CLEAR(_piBuffer);
}

//============================================================
// <T>析构渲染索引缓冲。</T>
//============================================================
FPd11RenderIndexBuffer::~FPd11RenderIndexBuffer(){
   MO_RELEASE(_piBuffer);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderIndexBuffer::OnSetup(){
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
TResult FPd11RenderIndexBuffer::Upload(TByteC* pData, TInt length){
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
   descriptor.BindFlags = D3D11_BIND_INDEX_BUFFER;
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
TResult FPd11RenderIndexBuffer::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderIndexBuffer::Resume(){
   //// 生成编号
   //glGenBuffers(1, &_bufferId);
   //MO_FATAL_CHECK(_bufferId != 0, return EFailure,
   //      "Generate index buffer id failure. (buffer_id=%d)", _bufferId);
   //// 绑定编号
   //glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, _bufferId);
   //MO_FATAL_CHECK(_bufferId != 0, return EFailure,
   //      "Buffer id is invalid. (buffer_id=%d)", _bufferId);
   //// 上传数据
   //TInt length = _pDataStream->Length();
   //TByteC* pData = _pDataStream->MemoryC();
   //glBufferData(GL_ELEMENT_ARRAY_BUFFER, length, pData, GL_STATIC_DRAW);
   //_pDevice->CheckError("glBufferData", "Upload array buffer data. (buffer_id=%d, length=%d, data=0x%08X)", _bufferId, length, pData);
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderIndexBuffer::Dispose(){
   MO_RELEASE(_piBuffer);
   return ESuccess;
}

MO_NAMESPACE_END
