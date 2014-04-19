#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_ABSTRACT_IMPLEMENT_INHERITS(FRenderVertexBuffer, FRenderInstance);

//============================================================
// <T>构造渲染顶点缓冲。</T>
//============================================================
FRenderVertexBuffer::FRenderVertexBuffer(){
   _stride = 0;
   _count = 0;
   _dataLength = 0;
   MO_CLEAR(_pDataStream);
}

//============================================================
// <T>析构渲染顶点缓冲。</T>
//============================================================
FRenderVertexBuffer::~FRenderVertexBuffer(){
   MO_DELETE(_pDataStream);
}

//============================================================
// <T>配置数据处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderVertexBuffer::BuildData(){
   // 创建数据流
   if(_pDataStream == NULL){
      _pDataStream = MO_CREATE(FDataStream);
   }
   // 设置属性
   _dataLength = _stride * _count;
   // 保留数据长度
   _pDataStream->ForceLength(_dataLength);
   return ESuccess;
}

//============================================================
// <T>上传数据处理。</T>
//
// @param reserve 保留数据
// @return 处理结果
//============================================================
TResult FRenderVertexBuffer::UploadData(TBool reserve){
   MO_ASSERT(_pDataStream);
   // 上传数据
   TByteC* pData = _pDataStream->MemoryC();
   Upload(pData, _dataLength);
   // 清空缓冲
   if(!reserve){
      //MO_DELETE(_pDataStream);
   }
   return ESuccess;
}

MO_NAMESPACE_END
