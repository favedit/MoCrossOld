#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_ABSTRACT_IMPLEMENT_INHERITS(FRenderIndexBuffer, FRenderInstance);

//============================================================
// <T>构造渲染索引缓冲。</T>
//============================================================
FRenderIndexBuffer::FRenderIndexBuffer(){
   _strideCd = ERenderIndexStride_Uint16;
   _count = 0;
   _instanceStride = 0;
   _instanceCount = 0;
   _dataLength = 0;
   MO_CLEAR(_pDataStream);
}

//============================================================
// <T>析构渲染索引缓冲。</T>
//============================================================
FRenderIndexBuffer::~FRenderIndexBuffer(){
   MO_DELETE(_pDataStream);
}

//============================================================
// <T>析构渲染索引缓冲。</T>
//============================================================
TResult FRenderIndexBuffer::OnSetup(){
   // 设置数据长度
   if(_strideCd == ERenderIndexStride_Uint16){
      _dataLength = _count << 1;
   }else if(_strideCd == ERenderIndexStride_Uint32){
      _dataLength = _count << 2;
   }else{
      MO_FATAL("Unknown stride. (stride_cd=%d)", _strideCd);
   }
   return ESuccess;
}

//============================================================
// <T>配置数据处理。</T>
//
// @param count 索引个数
//============================================================
TResult FRenderIndexBuffer::BuildData(){
   // 创建数据流
   if(_pDataStream == NULL){
      _pDataStream = MO_CREATE(FDataStream);
   }
   // 保留数据长度
   _pDataStream->ForceLength(_dataLength);
   return ESuccess;
}

//============================================================
// <T>上传数据处理。</T>
//
// @param reserve 保留数据
//============================================================
TResult FRenderIndexBuffer::UploadData(TBool reserve){
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
