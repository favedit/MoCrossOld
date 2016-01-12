#include "MoCrNetMessage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造消息缓冲。</T>
//
// @return 消息缓冲
//============================================================
TNetMessageBuffer::TNetMessageBuffer(){
   _pData = _buffer;
}

//============================================================
// <T>构造消息缓冲。</T>
//
// @return 消息缓冲
//============================================================
TNetMessageBuffer::TNetMessageBuffer(TAnyC* pMemory){
   Restore(pMemory);
}

//============================================================
void TNetMessageBuffer::AssignData(TByteC* pData, TInt length){
   MO_ASSERT(pData);
   if(length > 0){
      memcpy(_buffer, pData, length);
   }
   _dataLength = length;
}

//============================================================
void TNetMessageBuffer::Update(){
   TInt capacity = Capacity();
   TNetSerial messageSerial = _messageHead.Serial();
   TNetTick messageTick = _messageHead.Tick();
   TNetHash hash = CalculateHash(messageSerial, messageTick, _pData, _dataLength);
   _netHead.SetLength((TNetLength)capacity);
   _netHead.SetHash(hash);
}

//============================================================
// <T>序列化内部数据到数据区。</T>
//
// @param pMessage 消息对象
//============================================================
TBool TNetMessageBuffer::Serialize(TAny* pMemory, TInt size, TInt* length){
   MO_ASSERT(pMemory);
   TByte* pPtr = (TByte*)pMemory;
   // 获得头长度
   TInt netCapacity = _netHead.Capacity();
   TInt messageCapacity = _messageHead.Capacity();
   TNetSerial messageSerial = _messageHead.Serial();
   TNetTick messageTick = _messageHead.Tick();
   TInt headCapacity = netCapacity + messageCapacity;
   TSize capacity = headCapacity + _dataLength;
   // 设置头信息
   TNetHash hash = CalculateHash(messageSerial, messageTick, _pData, _dataLength);
   _netHead.SetLength((TNetLength)capacity);
   _netHead.SetHash(hash);
   // 序列头信息
   _netHead.Serialize(pPtr);
   _messageHead.Serialize(pPtr + headCapacity);
   // 序列化数据
   if(_dataLength > 0){
      memcpy(pPtr + headCapacity, _pData, _dataLength);
   }
   // 设置序列化后大小
   *length = capacity;
   return ETrue;
}

//============================================================
// <T>反序列化数据区到内部数据。</T>
//
// @param pMemory 数据指针
// @param size 数据长度
// @param [out] length 读取长度
//============================================================
TBool TNetMessageBuffer::Unserialize(TAnyC* pMemory, TInt size, TInt* length){
   MO_ASSERT(pMemory);
   TByteC* pPtr = (TByteC*)pMemory;
   // 反序列化头数据
   TInt offset = 0;
   offset += _netHead.Unserialize(pPtr);
   offset += _messageHead.Unserialize(pPtr + offset);
   TNetSerial messageSerial = _messageHead.Serial();
   TNetTick messageTick = _messageHead.Tick();
   // 获取数据
   TNetLength netLength = _netHead.Length();
   TByteC* pData = pPtr + offset;
   TInt dataLength = netLength - offset;
   // 检查哈希
   TNetHash hash = CalculateHash(messageSerial, messageTick, pData, dataLength);
   if(_netHead.Hash() != hash){
      MO_WARN(TC("Unserialize message invalid hash. (head_hash=0x%08X, data_hash=0x%08X)"),
            _netHead.Hash(), hash);
      return EFalse;
   }
   // 设置数据
   if(dataLength > 0){
      memcpy(_buffer, pData, dataLength);
   }
   _dataLength = dataLength;
   *length = netLength;
   return ETrue;
}

//============================================================
// <T>反序列化数据区到内部数据。</T>
//
// @param pMemory 数据指针
// @param size 数据长度
// @param [out] length 读取长度
//============================================================
TBool TNetMessageBuffer::UnserializeMask(TAnyC* pMemory, TInt size, TInt* length){
   MO_ASSERT(pMemory);
   TByteC* pPtr = (TByteC*)pMemory;
   // 反序列化头数据
   TInt offset = 0;
   offset += _netHead.Unserialize(pPtr);
   TNetLength netLength = _netHead.Length();
   TNetHash netHash = _netHead.Hash();
   offset += _messageHead.Unserialize(pPtr + offset);
   TNetSerial messageSerial = _messageHead.Serial();
   TNetTick messageTick = _messageHead.Tick();
   // 获取数据
   TByteC* pData = pPtr + offset;
   TInt dataLength = netLength - offset;
   // 还原数据
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   MaskData(buffer, MO_NETMESSAGE_MAXLENGTH, pData, dataLength, netHash);
   //TChar dump[MO_FS_DUMP_LENGTH];
   //MO_ERROR("Receive message data failure.\n%s",
   //      RByte::Dump(buffer, dataLength, dump, MO_FS_DUMP_LENGTH));
   // 检查哈希
   TNetHash hash = CalculateHash(messageSerial, messageTick, buffer, dataLength);
   if(netHash != hash){
      MO_WARN(TC("Unserialize message invalid hash. (head_hash=0x%08X, data_hash=0x%08X)"), netHash, hash);
      return EFalse;
   }
   // 设置数据
   if(dataLength > 0){
      memcpy(_buffer, buffer, dataLength);
   }
   _dataLength = dataLength;
   *length = netLength;
   return ETrue;
}

//============================================================
// <T>反序列化数据区到内部数据。</T>
//
// @param pMemory 数据指针
// @param size 数据长度
// @param [out] length 读取长度
//============================================================
TBool TNetMessageBuffer::UnserializeUncheck(TAnyC* pMemory, TInt size, TInt* length){
   MO_ASSERT(pMemory);
   TByteC* pPtr = (TByteC*)pMemory;
   // 反序列化头数据
   TInt offset = 0;
   offset += _netHead.Unserialize(pPtr);
   offset += _messageHead.Unserialize(pPtr + offset);
   // 获取数据
   TNetLength netLength = _netHead.Length();
   TByteC* pData = pPtr + offset;
   TInt dataLength = netLength - offset;
   // 设置数据
   if(dataLength > 0){
      memcpy(_buffer, pData, dataLength);
   }
   _dataLength = dataLength;
   *length = netLength;
   return ETrue;
}

//============================================================
// <T>压缩内部数据到数据区。</T>
//
// @param pMemory 数据指针
// @param size 数据长度
// @param [out] length 读取长度
//============================================================
TBool TNetMessageBuffer::Compress(TAny* pMemory, TInt size, TInt* pLength){
   MO_ASSERT(pMemory);
   TByte* pPtr = (TByte*)pMemory;
   // 获得头长度
   TInt netCapacity = _netHead.Capacity();
   TInt messageCapacity = _messageHead.Capacity();
   TNetSerial messageSerial = _messageHead.Serial();
   TNetTick messageTick = _messageHead.Tick();
   TInt headCapacity = netCapacity + messageCapacity;
   TSize capacity = headCapacity + _dataLength;
   // 设置头信息
   TNetHash hash = CalculateHash(messageSerial, messageTick, _pData, _dataLength);
   _netHead.SetLength((TNetLength)capacity);
   _netHead.SetHash(hash);
   // 序列头信息
   _netHead.Serialize(pPtr);
   _messageHead.Serialize(pPtr + headCapacity);
   // 压缩数据
   TInt length = 0;
   if(!RCompress::CompressRLE(pPtr + headCapacity, size, _pData, _dataLength, &length)){
      return EFalse;
   }
   // 设置序列化后大小
   *pLength = headCapacity + length;
   return ETrue;
}

//============================================================
// <T>解压缩数据区到内部数据。</T>
//
// @param pMemory 数据指针
// @param size 数据长度
// @param [out] length 读取长度
//============================================================
TBool TNetMessageBuffer::Uncompress(TAnyC* pMemory, TInt size, TInt* pLength, TBool masked, TBool checked){
   MO_ASSERT(pMemory);
   TByteC* pPtr = (TByteC*)pMemory;
   // 反序列化头数据
   TInt offset = 0;
   offset += _netHead.Unserialize(pPtr);
   offset += _messageHead.Unserialize(pPtr + offset);
   TNetHash netHash = _netHead.Hash();
   TBool compressed = _netHead.SignCompress();
   TNetSerial messageSerial = _messageHead.Serial();
   TNetTick messageTick = _messageHead.Tick();
   // 获取数据
   TNetLength netLength = _netHead.Length();
   TByteC* pData = pPtr + offset;
   TInt dataLength = netLength - offset;
   // 检查数据长度
   if((dataLength < 0) || (dataLength > MO_NETMESSAGE_MAXLENGTH)){
      return EFalse;
   }
   // 数据压缩
   if(compressed){
      // 解压缩数据
      TInt length = 0;
      if(!RCompress::Inflate(_buffer, MO_NETMESSAGE_MAXLENGTH, pData, dataLength, &length)){
         MO_WARN(TC("Inflate message failure. (data_ptr=0x%08X, data_length=0x%08X)"), pData, dataLength);
         return EFalse;
      }
      _dataLength = length;
   }else{
      MO_LIB_MEMORY_COPY(_buffer, MO_NETMESSAGE_MAXLENGTH, pData, dataLength);
      _dataLength = dataLength;
   }
   // 还原数据
   if(masked){
      MaskData(_buffer, MO_NETMESSAGE_MAXLENGTH, _buffer, _dataLength, netHash);
   }
   // 检查哈希
   if(checked){
      TNetHash hash = CalculateHash(messageSerial, messageTick, _buffer, _dataLength);
      if(netHash != hash){
         MO_WARN(TC("Unserialize message invalid hash. (head_hash=0x%08X, data_hash=0x%08X)"), netHash, hash);
         return EFalse;
      }
   }
   *pLength = _dataLength;
   return ETrue;
}

//============================================================
TCharC* TNetMessageBuffer::Dump(TChar* pDump, TSize capacity){
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

MO_NAMESPACE_END
