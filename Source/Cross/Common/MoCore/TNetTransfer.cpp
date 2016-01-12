#include "MoCrNetMessage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络传输器。</T>
//============================================================
TNetTransfer::TNetTransfer(){
   _netHead.SetProtocol(ENetProtocol_Transfer);
}

//============================================================
// <T>构造网络传输器。</T>
//
// @param pMessage 消息
//============================================================
TNetTransfer::TNetTransfer(TNetMessage* pMessage){
   _netHead.SetProtocol(ENetProtocol_Transfer);
   LinkMessage(pMessage);
}

//============================================================
// <T>构造网络传输器。</T>
//
// @param pRouter 路由器
//============================================================
TNetTransfer::TNetTransfer(TNetRouter* pRouter){
   _netHead.SetProtocol(ENetProtocol_Transfer);
   LinkRouter(pRouter);
}

//============================================================
// <T>析构网络传输器。</T>
//============================================================
TNetTransfer::~TNetTransfer(){
}

//============================================================
// <T>构造网络传输器。</T>
//
// @param pMessage 消息
// @param modeCd 模式类型
// @param pRouter 路由器
//============================================================
TNetTransfer::TNetTransfer(TNetMessage* pMessage, EMode modeCd, TNetRouter* pRouter){
   _netHead.SetProtocol(ENetProtocol_Transfer);
   LinkMessage(pMessage);
   switch(modeCd){
      case EMode_Unknown:
         break;
      case EMode_Continue:
         ContinueFrom(pRouter);
         break;
      case EMode_Response:
         ResponseFrom(pRouter);
         break;
   }
}

//============================================================
// <T>构造网络传输器。</T>
//
// @param pMessage 消息
// @param modeCd 模式类型
// @param pTransfer 传输器
//============================================================
TNetTransfer::TNetTransfer(TNetMessage* pMessage, EMode modeCd, TNetTransfer* pTransfer){
   _netHead.SetProtocol(ENetProtocol_Transfer);
   LinkMessage(pMessage);
   switch(modeCd){
      case EMode_Unknown:
         break;
      case EMode_Continue:
         ContinueFrom(pTransfer);
         break;
      case EMode_Response:
         ResponseFrom(pTransfer);
         break;
   }
}

//============================================================
// <T>获得容量。</T>
//
// @return 容量
//============================================================
TInt TNetTransfer::Capacity(){
   TInt netCapacity = _netHead.Capacity();
   TInt messageCapacity = _messageHead.Capacity();
   TInt routerCapacity = _routerHead.Capacity();
   TInt transferCapacity = _transferHead.Capacity();
   TInt capacity = netCapacity + messageCapacity + routerCapacity + transferCapacity + _dataLength;
   return capacity;
}

//============================================================
// <T>从指定路由器继续应答路由数据。</T>
//
// @param pRouter 路由对象
//============================================================
void TNetTransfer::ContinueFrom(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   _messageHead.Continue(pRouter->MessageHead());
   _routerHead.Continue(pRouter->RouterHead());
}

//============================================================
// <T>从指定传输器数据继续应答路由数据。</T>
//
// @param pTransfer 传输器
//============================================================
void TNetTransfer::ContinueFrom(TNetTransfer* pTransfer){
   MO_ASSERT(pTransfer);
   _messageHead.Continue(pTransfer->MessageHead());
   _routerHead.Continue(pTransfer->RouterHead());
   _transferHead.Assign(pTransfer->TransferHead());
}

//============================================================
// <T>从指定路由器生成应答路由数据。</T>
//
// @param pRouter 路由器
//============================================================
void TNetTransfer::ResponseFrom(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   _messageHead.Response(pRouter->MessageHead());
   _routerHead.Response(pRouter->RouterHead());
}

//============================================================
// <T>从指定传输器数据生成应答传输数据。</T>
//
// @param pTransfer 传输器
//============================================================
void TNetTransfer::ResponseFrom(TNetTransfer* pTransfer){
   MO_ASSERT(pTransfer);
   _messageHead.Response(pTransfer->MessageHead());
   _routerHead.Response(pTransfer->RouterHead());
   _transferHead.Assign(pTransfer->TransferHead());
}

//============================================================
// <T>关联消息信息。</T>
//
// @param pMessage 消息信息
//============================================================
void TNetTransfer::LinkMessage(TNetMessage* pMessage){
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
// <T>关联路由信息。</T>
//
// @param pRouter 路由信息
//============================================================
void TNetTransfer::LinkRouter(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   // 设置头信息
   TNetHead& netHead = pRouter->NetHead();
   _netHead.SetSign(netHead.Sign());
   _netHead.SetHash(netHead.Hash());
   _messageHead.Assign(pRouter->MessageHead());
   _routerHead.Assign(pRouter->RouterHead());
   // 设置数据
   pRouter->SerializeData(_buffer, MO_NETMESSAGE_MAXLENGTH, &_dataLength);
}

//============================================================
// <T>接收路由信息。</T>
//
// @param pRouter 路由信息
//============================================================
TBool TNetTransfer::Assign(TNetTransfer* pRouter){
   MO_ASSERT(pRouter);
   // 设置头信息
   _netHead.Assign(pRouter->NetHead());
   _messageHead.Assign(pRouter->MessageHead());
   _routerHead.Assign(pRouter->RouterHead());
   _transferHead.Assign(pRouter->TransferHead());
   // 设置数据
   _dataLength = pRouter->DataLength();
   if(pRouter->HasData()){
      memcpy(_buffer, pRouter->DataC(), _dataLength);
   }
   return ETrue;
}

//============================================================
// <T>序列化传输数据到数据区。</T>
//
// @param pMemory 数据指针
// @param size 数据大小
// @param length 处理大小
//============================================================
TBool TNetTransfer::Serialize(TAny* pMemory, TInt size, TInt* length){
   MO_ASSERT(pMemory);
   TByte* pPtr = (TByte*)pMemory;
   // 获取头大小
   TInt netCapacity = _netHead.Capacity();
   TInt messageCapacity = _messageHead.Capacity();
   TNetSerial messageSerial = _messageHead.Serial();
   TNetTick messageTick = _messageHead.Tick();
   TInt routerCapacity = _routerHead.Capacity();
   TInt transferCapacity = _transferHead.Capacity();
   TInt headCapacity = netCapacity + messageCapacity + routerCapacity + transferCapacity;
   TInt capacity = headCapacity + _dataLength;
   TByte* pData = pPtr + headCapacity;
   // 设置头信息
   TNetHash hash = CalculateHash(messageSerial, messageTick, _pData, _dataLength);
   _netHead.SetLength((TNetLength)capacity);
   _netHead.SetProtocol(ENetProtocol_Transfer);
   _netHead.SetHash(hash);
   // 序列化头数据
   TInt offset = 0;
   offset += _netHead.Serialize(pPtr);
   offset += _messageHead.Serialize(pPtr + offset);
   offset += _routerHead.Serialize(pPtr + offset);
   offset += _transferHead.Serialize(pPtr + offset);
   if(_dataLength > 0){
      memcpy(pData, _pData, _dataLength);
   }
   // 设置序列化后大小
   MO_ASSERT(capacity <= size);
   *length = capacity;
   return ETrue;
}

//============================================================
// <T>反序列化数据区到传输数据。</T>
//
// @param pMemory 数据指针
// @param size 数据大小
// @param length 处理大小
//============================================================
TBool TNetTransfer::Unserialize(TAnyC* pMemory, TInt size, TInt* length){
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
   if(ENetProtocol_Transfer == (ENetProtocol_Transfer & protocol)){
      offset += _transferHead.Unserialize(pPtr + offset);
   }
   _netHead.SetProtocol(ENetProtocol_Transfer);
   TInt capacity = _netHead.Length();
   // 复制数据
   TByteC* pData = pPtr + offset;
   TInt dataLength = capacity - offset;
   // 验证哈希
   TNetHash hash = CalculateHash(messageSerial, messageTick, pData, dataLength);
   if(_netHead.Hash() != hash){
      MO_ERROR(TC("Unserialize transfer invalid hash. (head_hash=0x%08X, data_hash=0x%08X)"), _netHead.Hash(), hash);
      return EFalse;
   }
   // 设置返回
   if(dataLength > 0){
      memcpy(_buffer, pData, dataLength);
   }
   _dataLength = dataLength;
   // 设置反序列化后大小
   if(capacity != size){
      MO_ERROR(TC("Unserialize transfer invalid length. (size=%d, length=%d)"), size, capacity);
      return EFalse;
   }
   *length = capacity;
   return ETrue;
}

//============================================================
// <T>获得当前内部状态信息。</T>
//
// @param pDump 输出信息指针
// @param capacity 输出信息大小
// @return 处理结果
//============================================================
TCharC* TNetTransfer::DumpTransfer(TChar* pDump, TSize capacity){
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
   // 获取路由信息
   TUint32 handle = _transferHead.Handle();
   TUint16 port = _transferHead.Port();
   TUint16 index = _transferHead.Socket().Index();
   TUint16 serial = _transferHead.Socket().Serial();
   // 生成数据信息
   TFsTrack dataTrack;
   TCharC* pDataTrack = RNetMessageFactory::DumpData(this, &dataTrack);
   // 生成信息
   MO_LIB_STRING_FORMAT(pDump, capacity,
      TC("[ %s ] - 0x%08X:%d\n"
         "--------------------------------------------------------------------------------\n"
         "-- Net      : length=0x%04X(%d), protocol=%d, hash=0x%04X\n"
         "-- Message  : code=(%02X:%04X), command=%d, sender=%s(0x%02X), target=%s(0x%02X)\n"
         "-- Router   : orgin=%s(%02X-%02X-%08X), source=%s(%02X-%02X-%08X), target=%s(%02X-%02X-%08X), targets=%" MO_FMT_INT "[%s]\n"
         "-- Transfer : handle=%d, port=%d, index=%d, serial=%d\n"
         "--------------------------------------------------------------------------------\n"
         "%s"),
         pName, sessionId, sessionId,
         length, length, protocol, hash,
         type, code, command, pSourceType, sourceType, pTargetType, targetType,
         pOriginTypeCode, origin.GroupId(), origin.ServerId(), origin.ObjectHandle(),
         pSourceTypeCode, source.GroupId(), source.ServerId(), source.ObjectHandle(),
         pTargetTypeCode, target.GroupId(), target.ServerId(), target.ObjectHandle(),
         count, (TCharC*)targetDump,
         handle, port, index, serial,
         pDataTrack);
   return pDump;
}

//============================================================
// <T>获得当前内部简单状态信息。</T>
//
// @param pDump 输出信息指针
// @param capacity 输出信息大小
// @return 处理结果
//============================================================
TCharC* TNetTransfer::DumpSimple(TChar* pDump, TSize capacity){
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
   // 获取路由信息
   TUint32 handle = _transferHead.Handle();
   TUint16 port = _transferHead.Port();
   TUint16 index = _transferHead.Socket().Index();
   TUint16 serial = _transferHead.Socket().Serial();
   // 生成信息
   MO_LIB_STRING_FORMAT(pDump, capacity,
      TC("[ %s ] - 0x%08X:%d\n"
         "--------------------------------------------------------------------------------\n"
         "-- Net      : length=0x%04X(%d), protocol=%d, hash=0x%04X\n"
         "-- Message  : code=(%02X:%04X), command=%d, sender=%s(0x%02X), target=%s(0x%02X)\n"
         "-- Router   : orgin=%s(%02X-%02X-%08X), source=%s(%02X-%02X-%08X), target=%s(%02X-%02X-%08X), targets=%" MO_FMT_INT "[%s]\n"
         "-- Transfer : handle=%d, port=%d, index=%d, serial=%d\n"),
         pName, sessionId, sessionId,
         length, length, protocol, hash,
         type, code, command, pSourceType, sourceType, pTargetType, targetType,
         pOriginTypeCode, origin.GroupId(), origin.ServerId(), origin.ObjectHandle(),
         pSourceTypeCode, source.GroupId(), source.ServerId(), source.ObjectHandle(),
         pTargetTypeCode, target.GroupId(), target.ServerId(), target.ObjectHandle(),
         count, (TCharC*)targetDump,
         handle, port, index, serial);
   return pDump;
}

//============================================================
// <T>获得当前内部状态信息。</T>
//
// @param pDump 输出信息指针
// @param capacity 输出信息大小
// @return 处理结果
//============================================================
TCharC* TNetTransfer::Dump(TChar* pDump, TSize capacity){
   return DumpTransfer(pDump, capacity);
}

MO_NAMESPACE_END
