#include "MoCrNetMessage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>获得消息名称。</T>
//
// @return 消息名称
//============================================================
TNetRouterHead::TNetRouterHead(){
   RType<SNetRouterHead>::Clear(&_data);
   _targets.Clear();
}

//============================================================
void TNetRouterHead::Continue(TNetRouterHead& head){
   _data.Flag = head.Flag();
   _data.Origin.Set(head.Origin());
   _data.Source.Set(head.Source());
   _data.Target.Set(head.Target());
   _data.Serial = head.Serial();
}

//============================================================
void TNetRouterHead::Response(TNetRouterHead& head){
   _data.Flag = head.Flag();
   _data.Origin.Set(head.Origin());
   _data.Source.Set(head.Target());
   _data.Target.Set(head.Source());
   _data.Serial = head.Serial();
}

//============================================================
void TNetRouterHead::Assign(TNetRouterHead& head){
   _data = head.Data();
   _targets.Assign(head.Targets());
}

//============================================================
// <T>序列化内部数据到数据区。</T>
//
// @param pMessage 消息对象
//============================================================
TInt TNetRouterHead::Serialize(TAny* pMemory){
   TByte* pPtr = (TByte*)pMemory;
   TInt offset = sizeof(SNetRouterHead);
   memcpy(pMemory, &_data, sizeof(SNetRouterHead));
   offset += _targets.Serialize(pPtr + offset);
   return offset;
}

//============================================================
// <T>反序列化数据区到内部数据。</T>
//
// @param pMessage 消息对象
//============================================================
TInt TNetRouterHead::Unserialize(TAnyC* pMemory){
   TByte* pPtr = (TByte*)pMemory;
   TInt offset = sizeof(SNetRouterHead);
   memcpy(&_data, pMemory, sizeof(SNetRouterHead));
   offset += _targets.Unserialize(pPtr + offset);
   return offset;
}

//============================================================
TCharC* TNetRouterHead::Dump(TChar* pDump, TSize length){
   return pDump;
}

MO_NAMESPACE_END
