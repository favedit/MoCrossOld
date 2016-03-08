#include "MoFbxResource.h"

MO_NAMESPACE_BEGIN;

//============================================================
// <T>构造资源对象。</T>
//============================================================
FFbxResourceObject::FFbxResourceObject() {
   _version = 1;
}

//============================================================
// <T>析构资源对象。</T>
//============================================================
FFbxResourceObject::~FFbxResourceObject() {
}

//============================================================
// <T>序列化内部数据到输出流。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult FFbxResourceObject::Serialize(IDataOutput * pOutput){
   pOutput->WriteString(_typeName);
   pOutput->WriteInt32((TInt32)_version);
   return ESuccess;
}

MO_NAMESPACE_END;
