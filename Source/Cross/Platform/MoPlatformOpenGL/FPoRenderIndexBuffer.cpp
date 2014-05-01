#include "MoPoRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPoRenderIndexBuffer, FRenderVertexBuffer);

//============================================================
// <T>构造渲染索引缓冲。</T>
//============================================================
FPoRenderIndexBuffer::FPoRenderIndexBuffer(){
   _bufferId = 0;
}

//============================================================
// <T>析构渲染索引缓冲。</T>
//============================================================
FPoRenderIndexBuffer::~FPoRenderIndexBuffer(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderIndexBuffer::OnSetup(){
   TResult resultCd = FRenderIndexBuffer::OnSetup();
   glGenBuffers(1, &_bufferId);
   MO_FATAL_CHECK(_bufferId != 0, return EFailure,
         "Generate index buffer id failure. (buffer_id=%d)", _bufferId);
   glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, _bufferId);
   return resultCd;
}

//============================================================
// <T>上传数据。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @return 处理结果
//============================================================
TResult FPoRenderIndexBuffer::Upload(TByteC* pData, TInt length){
   // 检查参数
   MO_CHECK(pData, return ENull);
   MO_CHECK(length > 0, return EFailure);
   // 检查编号
   MO_FATAL_CHECK(_bufferId != 0, return EFailure,
         "Buffer id is invalid. (buffer_id=%d)", _bufferId);
   // 上传数据
   glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, _bufferId);
   glBufferData(GL_ELEMENT_ARRAY_BUFFER, length, pData, GL_STATIC_DRAW);
   _pDevice->CheckError("glBufferData", "Upload array buffer data. (buffer_id=%d, length=%d, data=0x%08X)", _bufferId, length, pData);
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderIndexBuffer::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderIndexBuffer::Resume(){
   // 生成编号
   glGenBuffers(1, &_bufferId);
   MO_FATAL_CHECK(_bufferId != 0, return EFailure,
         "Generate index buffer id failure. (buffer_id=%d)", _bufferId);
   // 绑定编号
   glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, _bufferId);
   MO_FATAL_CHECK(_bufferId != 0, return EFailure,
         "Buffer id is invalid. (buffer_id=%d)", _bufferId);
   // 上传数据
   TInt length = _pDataStream->Length();
   TByteC* pData = _pDataStream->MemoryC();
   glBufferData(GL_ELEMENT_ARRAY_BUFFER, length, pData, GL_STATIC_DRAW);
   _pDevice->CheckError("glBufferData", "Upload array buffer data. (buffer_id=%d, length=%d, data=0x%08X)", _bufferId, length, pData);
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderIndexBuffer::Dispose(){
   if(_bufferId != 0){
      glDeleteBuffers(_dataLength, &_bufferId);
      _count = 0;
      _dataLength = 0;
      _bufferId = 0;
   }
   return ESuccess;
}

MO_NAMESPACE_END
