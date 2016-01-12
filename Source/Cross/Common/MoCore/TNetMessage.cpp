#include <MoCmSystem.h>
#include "MoCrNetMessage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造消息对象。</T>
//
// @return 实体对象
//============================================================
TNetMessage::TNetMessage(){
   _netHead.SetProtocol(ENetProtocol_Message);
}

//============================================================
// <T>构造消息对象。</T>
//
// @param pMessage 消息对象
// @return 实体对象
//============================================================
TNetMessage::TNetMessage(TNetMessage* pMessage){
   Assign(pMessage);
}

//============================================================
// <T>获得占用大小。</T>
//
// @return 大小
//============================================================
TInt TNetMessage::Capacity(){
   TInt capacity = _netHead.Capacity();
   capacity += _messageHead.Capacity();
   capacity += _dataLength;
   return capacity;
}

//============================================================
// <T>获得消息描述对象。</T>
//
// @return 消息描述对象
//============================================================
TNetMessageInfo* TNetMessage::MessageInfo(){
   TInt code = MessageHead().Code();
   TNetMessageInfo* pMessageInfo = RNetMessageFactory::CodeInfo(code);
   return pMessageInfo;
}

//============================================================
// <T>接收另一个消息的全部内容。</T>
//
// @param pMessage 消息对象
//============================================================
TBool TNetMessage::Assign(TNetMessage* pMessage){
   MO_ASSERT(pMessage);
   // 设置数据头
   _netHead.Assign(pMessage->NetHead());
   _messageHead.Assign(pMessage->MessageHead());
   // 设置数据
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(pMessage->SerializeData(buffer, MO_NETMESSAGE_MAXLENGTH, &_dataLength)){
      TBool result = UnserializeData(buffer, MO_NETMESSAGE_MAXLENGTH, &_dataLength);
      if(result){
         return ETrue;
      }
   }
   return EFalse;
}

//============================================================
// <T>序列化消息数据到数据区。</T>
//
// @param pMemory 数据指针
// @param size 数据大小
// @param length 处理大小
//============================================================
TBool TNetMessage::SerializeMessage(TAny* pMemory, TInt size, TInt* length){
   MO_ASSERT(pMemory);
   TByte* pPtr = (TByte*)pMemory;
   // 获得头长度
   TInt netCapacity = _netHead.Capacity();
   TInt messageCapacity = _messageHead.Capacity();
   TNetSerial messageSerial = _messageHead.Serial();
   TNetTick messageTick = _messageHead.Tick();
   TInt headCapacity = netCapacity + messageCapacity;
   // 序列化数据
   TByte* pData = pPtr + headCapacity;
   TInt dataLength;
   SerializeData(pData, size - headCapacity, &dataLength);
   // 设置头信息
   TSize capacity = headCapacity + dataLength;
   TNetHash hash = CalculateHash(messageSerial, messageTick, pData, dataLength);
   _netHead.SetLength((TNetLength)capacity);
   _netHead.SetHash(hash);
   // 设置存储头信息
   TNetHead netHead(_netHead);
   netHead.SetProtocol(ENetProtocol_Message);
   // 序列化数据
   TInt offset = 0;
   offset += netHead.Serialize(pPtr);
   offset += _messageHead.Serialize(pPtr + offset);
   offset += dataLength;
   // 设置序列化后大小
   *length = offset;
   return ETrue;
}

//============================================================
// <T>反序列化数据区到消息数据。</T>
//
// @param pMemory 数据指针
// @param size 数据大小
// @param length 处理大小
//============================================================
TBool TNetMessage::UnserializeMessage(TAnyC* pMemory, TInt size, TInt* length){
   MO_ASSERT(pMemory);
   TByteC* pPtr = (TByteC*)pMemory;
   // 反序列化头数据
   TNetHead netHead;
   TInt netCapacity = netHead.Unserialize(pPtr);
   TInt messageCapacity = _messageHead.Unserialize(pPtr + netCapacity);
   TNetSerial messageSerial = _messageHead.Serial();
   TNetTick messageTick = _messageHead.Tick();
   TInt headCapacity = netCapacity + messageCapacity;
   // 设置网络头信息
   TNetLength netLength = _netHead.Length();
   _netHead.SetLength(netLength);
   _netHead.SetSign(netHead.Sign());
   _netHead.SetHash(netHead.Hash());
   // 复制数据
   TByteC* pData = pPtr + headCapacity;
   TInt dataLength = netLength - headCapacity;
   // 计算哈希
   TNetHash hash = CalculateHash(messageSerial, messageTick, pData, dataLength);
   if(_netHead.Hash() != hash){
      MO_ERROR(TC("Unserial message invalid hash. (head_hash=0x%08X, data_hash=0x%08X)"),
            _netHead.Hash(), hash);
      return EFalse;
   }
   // 设置返回
   UnserializeData(pData, size - headCapacity, &dataLength);
   *length = netLength;
   return ETrue;
}

//============================================================
// <T>压缩消息数据到数据区。</T>
//
// @param pMemory 数据指针
// @param size 数据大小
// @param length 处理大小
//============================================================
TBool TNetMessage::CompressMessage(TAny* pMemory, TInt size, TInt* pLength){
   MO_ASSERT(pMemory);
   TByte* pPtr = (TByte*)pMemory;
   // 获得头长度
   TInt netCapacity = _netHead.Capacity();
   TInt messageCapacity = _messageHead.Capacity();
   TNetSerial messageSerial = _messageHead.Serial();
   TNetTick messageTick = _messageHead.Tick();
   TInt headCapacity = netCapacity + messageCapacity;
   // 序列化数据
   TByte data[MO_NETMESSAGE_MAXLENGTH];
   TInt dataLength = 0;
   SerializeData(data, size - headCapacity, &dataLength);
   // 压缩数据
   TInt compressLength = 0;
   TByte* pData = pPtr + headCapacity;
   RCompress::CompressRLE(pData, size - headCapacity, data, dataLength, &compressLength);
   // 设置头信息
   TSize capacity = headCapacity + compressLength;
   TNetHash hash = CalculateHash(messageSerial, messageTick, pData, compressLength);
   _netHead.SetLength((TNetLength)capacity);
   _netHead.SetHash(hash);
   // 设置存储头信息
   TNetHead netHead(_netHead);
   netHead.SetProtocol(ENetProtocol_Message);
   // 序列化数据
   TInt offset = 0;
   offset += netHead.Serialize(pPtr);
   offset += _messageHead.Serialize(pPtr + offset);
   offset += compressLength;
   // 设置序列化后大小
   *pLength = offset;
   return ETrue;
}

//============================================================
// <T>序列化消息数据到数据区。</T>
//
// @param pMemory 数据指针
// @param size 数据大小
// @param length 处理大小
//============================================================
TBool TNetMessage::Serialize(TAny* pMemory, TInt size, TInt* length){
   return SerializeMessage(pMemory, size, length);
}

//============================================================
// <T>反序列化数据区到消息数据。</T>
//
// @param pMemory 数据指针
// @param size 数据大小
// @param length 处理大小
//============================================================
TBool TNetMessage::Unserialize(TAnyC* pMemory, TInt size, TInt* length){
   return UnserializeMessage(pMemory, size, length);
}

//============================================================
// <T>获得当前内部状态信息。</T>
//
// @param pDump 输出信息指针
// @param capacity 输出信息大小
// @return 处理结果
//============================================================
TCharC* TNetMessage::DumpMessage(TChar* pDump, TSize capacity){
   // 获取网络信息
   TNetLength length = _netHead.Length();
   TUint16 protocol = _netHead.Protocol();
   TNetHash hash = _netHead.Hash();
   // 获取消息信息
   TUint8 type = _messageHead.Type();
   TUint8 command = _messageHead.Command();
   TUint16 code = _messageHead.Code();
   TNetMessageInfo* pMessageInfo = MessageInfo();
   TUint16 sourceTerminal = ENetTerminal_Unknown;
   TUint16 targetTerminal = ENetTerminal_Unknown;
   if(NULL != pMessageInfo){
      sourceTerminal = (TUint16)pMessageInfo->SourceTerminal();
      targetTerminal = (TUint16)pMessageInfo->TargetTerminal();
   }
   TCharC* pSourceTerminal = REnumNetTerminal::ToString((ENetTerminal)sourceTerminal);
   TCharC* pTargetTerminal = REnumNetTerminal::ToString((ENetTerminal)targetTerminal);
   // 生成数据信息
   TFsTrack dataTrack;
   TCharC* pDataTrack = RNetMessageFactory::DumpData(this, &dataTrack);
   // 生成信息
   TCharC* pName = RNetMessageFactory::CodeName(code);
   MO_LIB_STRING_FORMAT(pDump, capacity,
      TC("[ %s ]\n"
         "--------------------------------------------------------------------------------\n"
         "-- Net     : length=0x%04X(%d), protocol=%d, hash=0x%08X\n"
         "-- Message : code=(%02X:%04X), command=%d, source=%s(0x%04X), target=%s(0x%04X)\n"
         "--------------------------------------------------------------------------------\n"
         "%s"),
         pName,
         length, length, protocol, hash,
         type, code, (TUint8)command, pSourceTerminal, sourceTerminal, pTargetTerminal, targetTerminal,
         pDataTrack);
   return pDump;
}

//============================================================
// <T>获得消息的数据内容信息。</T>
//
// @param pDump 输出信息指针
// @param capacity 输出信息大小
// @return 处理结果
//============================================================
TCharC* TNetMessage::DumpMessageMemory(TChar* pDump, TSize capacity){
   // 序列化数据
   TInt length;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!SerializeMessage(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      return pDump;
   }
   // 生成调试信息
   TInt size = MO_LIB_MIN(length, MO_NETMESSAGE_BYTEDUMP_MAXLENGTH);
   RByte::Dump(buffer, size, pDump, capacity);
   return pDump;
}

//============================================================
// <T>获得当前内部状态信息。</T>
//
// @param pDump 输出信息指针
// @param capacity 输出信息大小
// @return 处理结果
//============================================================
TCharC* TNetMessage::Dump(TChar* pDump, TSize capacity){
   return DumpMessage(pDump, capacity);
}

MO_NAMESPACE_END
