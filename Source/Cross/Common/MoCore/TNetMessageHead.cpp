#include "MoCrNetMessage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>获得消息名称。</T>
//
// @return 消息名称
//============================================================
TNetMessageHead::TNetMessageHead(){
   RType<SNetMessageHead>::Clear(&_data);
}

//============================================================
TCharC* TNetMessageHead::CodeName(){
   return RNetMessageFactory::CodeName(_data.code);
}

//============================================================
void TNetMessageHead::Continue(TNetMessageHead& head){
   _data.command = head.Command();
}

//============================================================
void TNetMessageHead::Response(TNetMessageHead& head){
   _data.command = head.Command();
}

//============================================================
// <T>接收指定消息内的所有数据。</T>
//
// @param pMessage 消息对象
//============================================================
void TNetMessageHead::Assign(TNetMessageHead& head){
   _data = head.Data();
}

//============================================================
// <T>序列化内部数据到数据区。</T>
//
// @param pMessage 消息对象
//============================================================
TInt TNetMessageHead::Serialize(TAny* pMemory){
   memcpy(pMemory, &_data, sizeof(SNetMessageHead));
   return sizeof(SNetMessageHead);
}

//============================================================
// <T>反序列化数据区到内部数据。</T>
//
// @param pMessage 消息对象
//============================================================
TInt TNetMessageHead::Unserialize(TAnyC* pMemory){
   memcpy(&_data, pMemory, sizeof(SNetMessageHead));
   return sizeof(SNetMessageHead);
}

MO_NAMESPACE_END
