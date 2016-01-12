#include <MoCmSystem.h>
#include "MoCrNetMessage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络数据头信息。</T>
//============================================================
TNetData::TNetData(){
   _netHead.SetProtocol(ENetProtocol_Data);
   MO_CLEAR(_pData);
   _dataLength = 0;
}

//============================================================
// <T>析构网络数据头信息。</T>
//============================================================
TNetData::~TNetData(){
}

//============================================================
// <T>计算数据部分的哈希值。</T>
//
// @param pMessage 消息对象
//============================================================
TNetHash TNetData::CalculateHash(TNetSerial serial, TNetTick date, TByteC* pMemory, TInt length){
   TInt32 hash = serial;
   // 检查内存
   if(NULL != pMemory){
      if(length > 0){
         // 计算哈希
         for(TInt n = 0; n < length; n++){
            hash = (31 * hash) + pMemory[n];
         }
      }
   }
   TNetHash result = (TNetHash)hash;
   result = result ^ date;
   return result;
}

//============================================================
// <T>计算数据部分的遮挡。</T>
//
// @param pDate 数据内容
// @param capacity 容量
// @param pInput 输入
// @param length 长度
// @param hash 哈希
//============================================================
TBool TNetData::MaskData(TByte* pDate, TInt capacity, TByteC* pInput, TInt length, TNetHash hash){
   // 计算内容
   TByte v1 = (hash >>  0) & 0xFF;
   TByte v2 = (hash >>  8) & 0xFF;
   TByte v3 = (hash >> 16) & 0xFF;
   TByte v4 = (hash >> 24) & 0xFF;
   // 逐字节遮盖
   for(TInt n = 0; n < length; n++){
      // 选择数据
      TByte v = 0;
      switch(n & 0x03){
         case 0:
            v = v1;
            break;
         case 1:
            v = v3;
            break;
         case 2:
            v = v2;
            break;
         case 3:
            v = v4;
            break;
      }
      // 遮盖字节
      pDate[n] = v ^ pInput[n];
   }
   return ETrue;
}

//============================================================
// <T>获得容量。</T>
//
// @return 容量
//============================================================
TInt TNetData::Capacity(){
   TSize capacity = _netHead.Capacity();
   capacity += _dataLength;
   return capacity;
}

//============================================================
// <T>接收指定消息内的所有数据。</T>
//
// @param pMessage 消息对象
//============================================================
TBool TNetData::Assign(TNetData* pData){
   MO_ASSERT(pData);
   // 复制头信息
   _netHead.Assign(pData->NetHead());
   // 设置数据
   _pData = pData->DataC();
   _dataLength = pData->DataLength();
   return ETrue;
}

//============================================================
TBool TNetData::SerializeData(TAny* pMemory, TInt size, TInt* length){
   MO_ASSERT(pMemory);
   TByte* pPtr = (TByte*)pMemory;
   if((NULL != _pData) && (_dataLength > 0)){
      memcpy(pPtr, _pData, _dataLength);
   }
   *length = _dataLength;
   return ETrue;
}

//============================================================
TBool TNetData::UnserializeData(TAnyC* pMemory, TInt size, TInt* length){
   MO_ASSERT(pMemory);
   _pData = (TByte*)pMemory;
   _dataLength = size;
   *length = _dataLength;
   return ETrue;
}

//============================================================
// <T>序列化内部数据到数据区。</T>
//
// @param pMemory 数据指针
// @param size 数据大小
// @param [out] length 数据长度
// @return 处理结果
//============================================================
TBool TNetData::Serialize(TAny* pMemory, TInt size, TInt* length){
   TByte* pPtr = (TByte*)pMemory;
   TInt capacity = Capacity();
   MO_ASSERT(capacity <= size);
   // 序列化头信息
   _netHead.SetLength((TNetLength)capacity);
   TInt offset = _netHead.Serialize(pPtr);
   // 序列化数据
   SerializeData(pPtr + offset, size - offset, &_dataLength);
   *length = capacity;
   return ETrue;
}

//============================================================
// <T>反序列化数据区到内部数据。</T>
//
// @param pMemory 数据指针
// @param size 数据大小
// @param [out] length 数据长度
// @return 处理结果
//============================================================
TBool TNetData::Unserialize(TAnyC* pMemory, TInt size, TInt* length){
   MO_ASSERT(pMemory);
   TByteC* pPtr = (TByteC*)pMemory;
   // 反序化头信息
   _netHead.Unserialize(pPtr);
   TInt headCapacity = _netHead.Capacity();
   TInt capacity = _netHead.Length();
   MO_ASSERT(capacity <= size);
   // 反序化数据
   UnserializeData(pPtr + headCapacity, capacity - headCapacity, &_dataLength);
   *length = capacity;
   return ETrue;
}

//============================================================
TInt TNetData::Store(TAny* pMemory){
   TInt length = 0;
   Serialize(pMemory, MO_NETMESSAGE_MAXLENGTH, &length);
   return length;
}

//============================================================
TInt TNetData::Restore(TAnyC* pMemory){
   TInt length = 0;
   Unserialize(pMemory, MO_NETMESSAGE_MAXLENGTH, &length);
   return length;
}

//============================================================
TCharC* TNetData::DumpData(TChar* pDump, TSize capacity){
   return pDump;
}

//============================================================
// <T>获得网络数据块的数据内容信息。</T>
//
// @param pDump 输出信息指针
// @param capacity 输出信息大小
// @return 处理结果
//============================================================
TCharC* TNetData::DumpMemory(TChar* pDump, TSize capacity){
   // 序列化数据
   TInt length;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!Serialize(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      return pDump;
   }
   // 生成调试信息
   TInt size = MO_LIB_MIN(length, MO_NETMESSAGE_BYTEDUMP_MAXLENGTH);
   RByte::Dump(buffer, size, pDump, capacity);
   return pDump;
}

//============================================================
TCharC* TNetData::Dump(TChar* pDump, TSize capacity){
   // 获取网络信息
   TNetLength length = _netHead.Length();
   TUint16 protocol = _netHead.Protocol();
   TNetHash hash = _netHead.Hash();
   // 生成数据信息
   TFsTrack dataTrack;
   DumpData(dataTrack.Memory(), dataTrack.Size());
   // 生成信息
   MO_LIB_STRING_FORMAT(pDump, capacity,
      TC("Net     : length=0x%04X(%d), protocol=%d, hash=0x%08X\n"
         "--------------------------------------------------------------------------------\n"
         "%s"),
         length, length, protocol, hash,
         (TCharC*)dataTrack);
   return pDump;
}

MO_NAMESPACE_END
