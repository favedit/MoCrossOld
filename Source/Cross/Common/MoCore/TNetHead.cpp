#include "MoCrNetMessage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络数据头。</T>
//============================================================
TNetHead::TNetHead(){
   RType<SNetHead>::Clear(&_data);
}

//============================================================
// <T>接收头信息。</T>
//
// @param head 头信息
//============================================================
void TNetHead::Assign(TNetHead& head){
   _data = head.Data();
}

//============================================================
// <T>序列化内部数据到数据区。</T>
//
// @param pMemory 数据指针
// @return 序列化后数据大小
//============================================================
TInt TNetHead::Serialize(TAny* pMemory){
   memcpy(pMemory, &_data, sizeof(SNetHead));
   return sizeof(SNetHead);
}

//============================================================
// <T>反序列化数据区到内部数据。</T>
//
// @param pMessage 消息对象
// @return 反序列化后数据大小
//============================================================
TInt TNetHead::Unserialize(TAnyC* pMemory){
   memcpy(&_data, pMemory, sizeof(SNetHead));
   return sizeof(SNetHead);
}

MO_NAMESPACE_END
