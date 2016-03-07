#include "MoFbxResource.h"

MO_NAMESPACE_BEGIN;

//============================================================
// <T>构造资源数据流。</T>
//============================================================
FFbxResStream::FFbxResStream(){
   _typeName = TC("Stream");
   _pData = MO_CREATE(FByteStream);
}

//============================================================
// <T>析构资源数据流。</T>
//============================================================
FFbxResStream::~FFbxResStream(){
   MO_DELETE(_pData);
}

//============================================================
// <T>序列化内部数据到输出流。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult FFbxResStream::Serialize(IDataOutput * pOutput){
   FFbxResourceObject::Serialize(pOutput);
   // 输出属性
   pOutput->WriteString(_code);
   pOutput->WriteUint8((TUint8)_elementDataCd);
   pOutput->WriteUint8((TUint8)_elementCount);
   pOutput->WriteBool(EFalse);
   pOutput->WriteUint8((TUint8)_dataStride);
   pOutput->WriteUint32((TUint32)_dataCount);
   // 输出数据
   pOutput->Write(_pData->MemoryC(), _pData->Length());
   return ESuccess;
}

MO_NAMESPACE_END;
