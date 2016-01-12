#include <MoCmSystem.h>
#include "MoCrNetMessage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造路由对象。</T>
//
// @return 实体对象
//============================================================
TNetRouter::TNetRouter(){
   _pData = _buffer;
   _netHead.SetProtocol(ENetProtocol_Router);
}

//============================================================
// <T>构造路由对象。</T>
//
// @param pMessage 消息对象
// @return 实体对象
//============================================================
TNetRouter::TNetRouter(TNetMessage* pMessage){
   _pData = _buffer;
   LinkMessage(pMessage);
}

//============================================================
// <T>析构路由对象。</T>
//============================================================
TNetRouter::~TNetRouter(){
}

//============================================================
// <T>获得占用大小。</T>
//
// @return 大小
//============================================================
TInt TNetRouter::Capacity(){
   TInt netCapacity = _netHead.Capacity();
   TInt messageCapacity = _messageHead.Capacity();
   TInt routerCapacity = _routerHead.Capacity();
   TInt capacity = netCapacity + messageCapacity + routerCapacity + _dataLength;
   return capacity;
}

//============================================================
// <T>接收另一个消息的全部内容。</T>
//
// @param pMessage 消息对象
//============================================================
void TNetRouter::LinkMessage(TNetMessage* pMessage){
   MO_ASSERT(pMessage);
   // 设置网络头信息
   TNetHead& netHead = pMessage->NetHead();
   _netHead.SetSign(netHead.Sign());
   _netHead.SetHash(netHead.Hash());
   // 设置消息头信息
   _messageHead.Assign(pMessage->MessageHead());
   // 设置消息信息
   TNetMessageInfo* pMessageInfo = pMessage->MessageInfo();
   if(NULL != pMessageInfo){
      _routerHead.Source().SetServerType((TUint8)pMessageInfo->SourceTerminal());
      _routerHead.Target().SetServerType((TUint8)pMessageInfo->TargetTerminal());
   }
   // 设置数据
   pMessage->SerializeData(_buffer, MO_NETMESSAGE_MAXLENGTH, &_dataLength);
}

//============================================================
// <T>从指定路由数据继续应答路由数据。</T>
//
// @param pRouter 路由对象
//============================================================
void TNetRouter::ContinueFrom(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   _messageHead.Continue(pRouter->MessageHead());
   _routerHead.Continue(pRouter->RouterHead());
}

//============================================================
// <T>从指定路由数据生成应答路由数据。</T>
//
// @param pRouter 路由对象
//============================================================
void TNetRouter::ResponseFrom(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   _messageHead.Response(pRouter->MessageHead());
   _routerHead.Response(pRouter->RouterHead());
}

//============================================================
// <T>接收另一个路由的全部内容。</T>
//
// @param pRouter 路由对象
//============================================================
TBool TNetRouter::Assign(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   // 设置头信息
   _netHead.Assign(pRouter->NetHead());
   _messageHead.Assign(pRouter->MessageHead());
   _routerHead.Assign(pRouter->RouterHead());
   // 设置数据
   _dataLength = pRouter->DataLength();
   if(pRouter->HasData()){
      memcpy(_buffer, pRouter->DataC(), _dataLength);
   }
   return ETrue;
}

//============================================================
// <T>存储消息数据到路由数据。</T>
//
// @return 处理结果
//============================================================
TBool TNetRouter::StoreMessage(TNetMessageBuffer& buffer){
   // 设置头信息
   TNetHead& netHead = buffer.NetHead();
   netHead.SetLength(_netHead.Length());
   netHead.SetSign(_netHead.Sign());
   netHead.SetHash(_netHead.Hash());
   buffer.MessageHead().Assign(_messageHead);
   // 设置数据
   if(_dataLength > 0){
      buffer.AssignData(_pData, _dataLength);
   }
   return ETrue;
}

//============================================================
// <T>序列化路由数据到数据区。</T>
//
// @param pMemory 数据指针
// @param size 数据大小
// @param length 处理大小
//============================================================
TBool TNetRouter::SerializeRouter(TAny* pMemory, TInt size, TInt* length){
   MO_ASSERT(pMemory);
   TByte* pPtr = (TByte*)pMemory;
   // 获取头大小
   TInt netCapacity = _netHead.Capacity();
   TInt messageCapacity = _messageHead.Capacity();
   TNetSerial messageSerial = _messageHead.Serial();
   TNetTick messageTick = _messageHead.Tick();
   TInt routerCapacity = _routerHead.Capacity();
   TInt headCapacity = netCapacity + messageCapacity + routerCapacity;
   TInt capacity = headCapacity + _dataLength;
   TByte* pData = pPtr + headCapacity;
   // 设置头信息
   TNetHash hash = CalculateHash(messageSerial, messageTick, _pData, _dataLength);
   _netHead.SetLength((TNetLength)capacity);
   _netHead.SetProtocol(ENetProtocol_Router);
   _netHead.SetHash(hash);
   // 序列化头数据
   TInt offset = 0;
   offset += _netHead.Serialize(pPtr);
   offset += _messageHead.Serialize(pPtr + offset);
   offset += _routerHead.Serialize(pPtr + offset);
   if(_dataLength > 0){
      memcpy(pData, _pData, _dataLength);
   }
   // 设置序列化后大小
   *length = capacity;
   return ETrue;
}

//============================================================
// <T>反序列化数据区到路由数据。</T>
//
// @param pMemory 数据指针
// @param size 数据大小
// @param length 处理大小
//============================================================
TBool TNetRouter::UnserializeRouter(TAnyC* pMemory, TInt size, TInt* length){
   MO_ASSERT(pMemory);
   TByteC* pPtr = (TByteC*)pMemory;
   // 反序列化头数据
   TInt offset = 0;
   offset += _netHead.Unserialize(pPtr);
   TUint protocol = _netHead.Protocol();
   TNetSerial messageSerial = 0;
   TNetTick messageTick = 0;
   if(ENetProtocol_Message == (ENetProtocol_Message & protocol)){
      offset += _messageHead.Unserialize(pPtr + offset);
      messageSerial = _messageHead.Serial();
      messageTick = _messageHead.Tick();
   }
   if(ENetProtocol_Router == (ENetProtocol_Router & protocol)){
      offset += _routerHead.Unserialize(pPtr + offset);
   }
   TNetLength capacity = _netHead.Length();
   // 复制数据
   TByteC* pData = pPtr + offset;
   TInt dataLength = capacity - offset;
   // 验证哈希
   TNetHash hash = CalculateHash(messageSerial, messageTick, pData, dataLength);
   if(_netHead.Hash() != hash){
      MO_ERROR(TC("Unserialize router invalid hash. (head_hash=0x%08X, data_hash=0x%08X)"),
            _netHead.Hash(), hash);
      return EFalse;
   }
   // 设置返回
   if(dataLength > 0){
      memcpy(_buffer, pData, dataLength);
   }
   _dataLength = dataLength;
   *length = capacity;
   return ETrue;
}

//============================================================
// <T>序列化路由数据到数据区。</T>
//
// @param pMemory 数据指针
// @param size 数据大小
// @param length 处理大小
//============================================================
TBool TNetRouter::Serialize(TAny* pMemory, TInt size, TInt* length){
   return SerializeRouter(pMemory, size, length);
}

//============================================================
// <T>反序列化数据区到路由数据。</T>
//
// @param pMemory 数据指针
// @param size 数据大小
// @param length 处理大小
//============================================================
TBool TNetRouter::Unserialize(TAnyC* pMemory, TInt size, TInt* length){
   return UnserializeRouter(pMemory, size, length);
}

//============================================================
// <T>获得当前内部状态信息。</T>
//
// @param pDump 输出信息指针
// @param capacity 输出信息大小
// @return 处理结果
//============================================================
TCharC* TNetRouter::DumpRouter(TChar* pDump, TSize capacity){
   // 获取网络信息
   TNetLength length = _netHead.Length();
   TUint16 protocol = _netHead.Protocol();
   TNetHash hash = _netHead.Hash();
   // 获取消息信息
   TUint8 type = _messageHead.Type();
   TUint8 command = _messageHead.Command();
   TUint16 code = _messageHead.Code();
   TCharC* pName = RNetMessageFactory::CodeName(code);
   // 获取Orgin信息
   SNetTarget& origin = _routerHead.Origin();
   TUint8 originType = origin.ServerType();
   TCharC* pOriginTypeCode = REnumNetTerminal::ToCode((ENetTerminal)originType);
   // 获取Source信息
   SNetTarget& source = _routerHead.Source();
   TUint8 sourceType = source.ServerType();
   TCharC* pSourceType = REnumNetTerminal::ToString((ENetTerminal)sourceType);
   TCharC* pSourceTypeCode = REnumNetTerminal::ToCode((ENetTerminal)sourceType);
   // 获取Target信息
   SNetTarget& target = _routerHead.Target();
   TUint8 targetType = target.ServerType();
   TCharC* pTargetType = REnumNetTerminal::ToString((ENetTerminal)targetType);
   TCharC* pTargetTypeCode = REnumNetTerminal::ToCode((ENetTerminal)targetType);
   // 获得会话编号
   TNetSessionId sessionId = _routerHead.SessionId();
   // 获取目标集合
   TFsDump targetDump;
   TNetTargets& targets = _routerHead.Targets();
   TInt count = targets.Count();
   for(TInt n = 0; n < count; n++){
      if(n > 0){
         targetDump.Append(',');
      }
      SNetTarget& netTarget = targets[n];
      targetDump.AppendFormat(TC("(%02X-%02X-%08X)"),
            netTarget.GroupId(), netTarget.ServerId(), netTarget.ObjectHandle());
   }
   // 生成数据信息
   TFsTrack dataTrack;
   TCharC* pDataTrack = RNetMessageFactory::DumpData(this, &dataTrack);
   // 生成信息
   MO_LIB_STRING_FORMAT(pDump, capacity,
         "[ %s ] - 0x%08X:%d\n"
         "--------------------------------------------------------------------------------\n"
         "-- Net     : length=0x%04X(%d), protocol=%d, hash=0x%04X\n"
         "-- Message : code=(%02X:%04X), command=%d, sender=%s(0x%02X), target=%s(0x%02X)\n"
         "-- Router  : orgin=%s(%02X-%02X-%08X), source=%s(%02X-%02X-%08X), target=%s(%02X-%02X-%08X), targets=%" MO_FMT_INT "[%s]\n"
         "--------------------------------------------------------------------------------\n"
         "%s",
         pName, sessionId, sessionId,
         length, length, protocol, hash,
         type, code, command, pSourceType, sourceType, pTargetType, targetType,
         pOriginTypeCode, origin.GroupId(), origin.ServerId(), origin.ObjectHandle(),
         pSourceTypeCode, source.GroupId(), source.ServerId(), source.ObjectHandle(),
         pTargetTypeCode, target.GroupId(), target.ServerId(), target.ObjectHandle(),
         count, (TCharC*)targetDump,
         pDataTrack);
   return pDump;
}

//============================================================
// <T>获得路由数据的数据内容信息。</T>
//
// @param pDump 输出信息指针
// @param capacity 输出信息大小
// @return 处理结果
//============================================================
TCharC* TNetRouter::DumpRouterMemory(TChar* pDump, TSize capacity){
   // 序列化数据
   TInt length;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!SerializeRouter(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
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
TCharC* TNetRouter::Dump(TChar* pDump, TSize capacity){
   return DumpRouter(pDump, capacity);
}

MO_NAMESPACE_END
