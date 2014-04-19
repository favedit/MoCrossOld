#include "MoCrNetMessage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>获得消息名称。</T>
//
// @return 消息名称
//============================================================
TNetTransferHead::TNetTransferHead(){
   RType<SNetTransferHead>::Clear(&_data);
}

//============================================================
void TNetTransferHead::Assign(TNetTransferHead& head){
   _data = head.Data();
}

//============================================================
// <T>序列化内部数据到数据区。</T>
//
// @param pMessage 消息对象
//============================================================
TInt TNetTransferHead::Serialize(TAny* pMemory){
   memcpy(pMemory, &_data, sizeof(SNetTransferHead));
   return sizeof(SNetTransferHead);
}

//============================================================
// <T>反序列化数据区到内部数据。</T>
//
// @param pMessage 消息对象
//============================================================
TInt TNetTransferHead::Unserialize(TAnyC* pMemory){
   memcpy(&_data, pMemory, sizeof(SNetTransferHead));
   return sizeof(SNetTransferHead);
}

//============================================================
// <T>将内容打包成为一个属性字符串。</T>
//
// @param pPack 打包字符串
// @return 处理结果
//============================================================
TBool TNetTransferHead::Pack(MPack* pPack){
   MO_FATAL_UNSUPPORT();
   return EFalse;
}

//============================================================
// <T>将一个打包字符串解包为内容。</T>
//
// @param pPack 打包字符串
// @return 处理结果
//============================================================
TBool TNetTransferHead::Unpack(MPack* pPack){
   MO_FATAL_UNSUPPORT();
   return EFalse;
}

//============================================================
TCharC* TNetTransferHead::Dump(TChar* pDump, TSize length){
   return pDump;
}

MO_NAMESPACE_END
