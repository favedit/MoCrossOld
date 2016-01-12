//============================================================
// <T>网络消息定义。</T>
// <P>1 - 数据基础：</P>
// <P>SNetPackage：网络数据包</P>
// <P>TNetTypes：网络数据类型，简单类型的集合。</P>
// <P>TNetObjects：网络对象集合，每一个对象要被序列化和反序列化。</P>
// <P>2 - 网络基础：</P>
// <P>TNetData：网络数据。</P>
// <P> - TNetHead：网络数据头信息。</P>
// <P>   - SNetHead：网络数据头数据结构定义</P>
// <P> - MemoryC：携带数据块。</P>
// <P>TNetMessage：网络消息，由一个头信息和一个消息体构成。</P>
// <P> - TNetMessageHead：消息头信息。</P>
// <P>   - SNetMessageHead：消息头数据结构定义</P>
// <P> - MemoryC：携带消息体。</P>
// <P>TNetRouter：消息路由，帮助消息跨越服务器传送。</P>
// <P> - TNetRouterHead：消息路由头信息。</P>
// <P>   - SNetRouterHead：消息路由头数据结构定义</P>
// <P> - MemoryC：携带消息体，是一个TNetMessage，里面包含自己的头和体。</P>
// <P>TNetTransfer：消息传输，帮助消息跨越服务器进程传送。</P>
// <P> - TNetTransferHead：消息传输头信息。</P>
// <P>   - SNetTransferHead：消息传输头数据结构定义</P>
// <P> - MemoryC：携带消息体，是一个TNetMessage，里面包含自己的头和体。</P>
//
// @history 100318 MAOCY 创建
//============================================================
#ifndef __MO_CR_NET_MESSAGE_H__
#define __MO_CR_NET_MESSAGE_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

#ifndef __MO_CR_CONSTANT_H__
#include "MoCrConstant.h"
#endif // __MO_CR_CONSTANT_H__

#ifndef __MO_CR_NET_SOCKET_H__
#include "MoCrNetSocket.h"
#endif // __MO_CR_NET_SOCKET_H__

#ifndef __MO_CR_NET_COLLECTION_H__
#include "MoCrNetCollection.h"
#endif // __MO_CR_NET_COLLECTION_H__

//============================================================
/// @define 消息定义
#define MO_NET_SIGN_COMPRESS             0x0001
#define MO_NET_SIGN_MASK                 0x0002
#define MO_NETMESSAGE_COUNT              0x10000
#define MO_NETMESSAGE_COMPRESS_LIMIT     64
#define MO_NETMESSAGE_MAXLENGTH          1024*512
#define MO_NETMESSAGE_MAXTARGET          256
#define MO_NETMESSAGE_BYTEDUMP_MAXLENGTH 1024
#define MO_NETROUTER_MAXTARGET           16
#define MO_NETROUTER_FLAG_INVALID        0x0001
#define MO_NETROUTER_FLAG_DISCONNECT     0x0002
#define MO_FS_SOCKETTARGET_MAXLEN        64

MO_NAMESPACE_BEGIN

//============================================================
typedef TUint32 TNetLength;
typedef TUint32 TNetHash;
typedef TUint32 TNetGenerator;
typedef TUint32 TNetSessionId;
typedef TUint16 TNetPort;
typedef TUint16 TNetVersion;

//============================================================
// <T>网络目标（64位）。</T>
// 高[32Bit]：目标服务器信息
//  - Flag      [ 8Bit]  = 256   标志位
//  - Type      [ 8Bit]  = 256   类型(从1开始)
//  - Groupid   [ 8Bit]  = 256   目标服务器组(从0开始)
//  - ServerId  [ 8Bit]  = 256   目标服务器编号(从0开始)
// 低[32Bit]：句柄信息
//  - NetIndex  [ 16Bit] = 65535 目标句柄索引(从0开始)
//  - NetSerial [ 16Bit] = 65535 目标句柄序列(从0开始)
//============================================================
#pragma pack(2)
struct MO_CR_DECLARE SNetSocketTarget{
public:
   union{
      TUint32 handle;
      struct{
         TUint16 index;
         TUint16 serial;
      } items;
   } Data;
public:
   //------------------------------------------------------------
   // <T>构造网络目标。</T>
   SNetSocketTarget(){
      Data.handle = 0;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool operator==(SNetSocketTarget& value){
      return Equals(value);
   }
   //------------------------------------------------------------
   // <T>判断是否不等。</T>
   MO_INLINE TBool operator!=(SNetSocketTarget& value){
      return !Equals(value);
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void operator=(TUint32 handle){
      Data.handle = handle;
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void operator=(const SNetSocketTarget& target){
      Data.handle = target.Data.handle;
   }
public:
   //------------------------------------------------------------
   // <T>获得索引。</T>
   MO_INLINE TUint16 Index() const{
      return Data.items.index;
   }
   //------------------------------------------------------------
   // <T>设置索引。</T>
   MO_INLINE void SetIndex(TUint16 index){
      Data.items.index = index;
   }
   //------------------------------------------------------------
   // <T>获得序列。</T>
   MO_INLINE TUint16 Serial() const{
      return Data.items.serial;
   }
   //------------------------------------------------------------
   // <T>设置序列。</T>
   MO_INLINE void SetSerial(TUint16 serial){
      Data.items.serial = serial;
   }
public:
   //------------------------------------------------------------
   // <T>获得句柄。</T>
   MO_INLINE TUint32 Get(){
      return Data.handle;
   }
   //------------------------------------------------------------
   // <T>设置句柄。</T>
   MO_INLINE void Set(TUint32 handle){
      Data.handle = handle;
   }
   //------------------------------------------------------------
   // <T>设置索引和序列。</T>
   MO_INLINE void Set(TUint16 index, TUint16 serial){
      Data.items.index = index;
      Data.items.serial = serial;
   }
   //------------------------------------------------------------
   // <T>设置目标。</T>
   MO_INLINE void Set(SNetSocketTarget target){
      Data.handle = target.Data.handle;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool Equals(SNetSocketTarget& value){
      return (Data.handle == value.Data.handle);
   }
   //------------------------------------------------------------
   // <T>重置内容。</T>
   MO_INLINE void Reset(){
      Data.handle = 0;
   }
public:
   //------------------------------------------------------------
   // <T>获得显示字符串。</T>
   TCharC* ToDisplay(TChar* pValue, TSize capacity){
      TStringRefer refer(pValue, capacity);
      refer.AssignFormat(TC("%04X:%04X"), Data.items.index, Data.items.serial);
      return pValue;
   }
   //------------------------------------------------------------
   // <T>获得跟踪字符串。</T>
   TCharC* Track(TChar* pTrack, TSize capacity, TInt level){
      TStringRefer track(pTrack, capacity);
      track.AppendFormat(TC("%d:%d"), Data.items.index, Data.items.serial);
      return pTrack;
   }
};
#pragma pack()

//============================================================
// <T>网络目标字符串。</T>
//============================================================
class MO_CR_DECLARE TFsNetSocketTarget : public TFixString<MO_FS_SOCKETTARGET_MAXLEN>{
protected:
   SNetSocketTarget _value;
public:
   TFsNetSocketTarget();
   TFsNetSocketTarget(const SNetSocketTarget& value);
public:
   void operator=(const TFsNetSocketTarget& value);
   void operator=(TCharC* pValue);
   void operator=(const SNetSocketTarget& value);
   void operator=(const TStringPtrC& value);
public:
   SNetSocketTarget Value() const;
   void SetValue(SNetSocketTarget value);
   TCharC* Format();
};

//============================================================
// <T>网络目标（64位）。</T>
// 高[32Bit]：目标服务器句柄
//  - ServerHandle    [32Bit] 服务器句柄
//     - GroupId      [16Bit] = 0-65535 服务器组号 (从1开始,0为无效)
//     - Server       [16Bit]
//        - Type      [ 8Bit] = 0-255   服务器类型 (从0开始)
//        - Id        [ 8Bit] = 0-255   服务器编号 (从1开始,0为唯一)
// 低[32Bit]：目标对象句柄
//  - ObjectHandle    [32Bit] = 对象句柄，同时作为SessionId
//     - Index        [16Bit] = 0-65535 索引位置 (从0开始，由Socket的索引位置生成)
//     - Serial       [16Bit] = 0-65535 序列(Socket验证使用，确认为同一个客户端)
//============================================================
typedef TUint64 TNetTargetHandle;
#pragma pack(2)
struct MO_CR_DECLARE SNetTarget{
public:
   //------------------------------------------------------------
   // 数据定义
   union{
      TUint64 Handle;
      struct{
         TUint32 ServerHandle;
         TUint32 ObjectHandle;
      } Handles;
      struct{
         TUint16 GroupId;
         TUint8  ServerType;
         TUint8  ServerId;
         TUint16 ObjectIndex;
         TUint16 ObjectSerial;
      } Items;
   } Data;
public:
   //------------------------------------------------------------
   // <T>构造网络目标。</T>
   SNetTarget(){
      Data.Handle = 0;
   }
   //------------------------------------------------------------
   // <T>构造网络目标。</T>
   SNetTarget(const SNetTarget& target){
      Data.Handle = target.Data.Handle;
   }
   //------------------------------------------------------------
   // <T>构造网络目标。</T>
   SNetTarget(TNetTargetHandle handle){
      Data.Handle = handle;
   }
   //------------------------------------------------------------
   // <T>构造网络目标。</T>
   SNetTarget(TUint16 groupId, TUint8 serverType, TUint8 serverId){
      Data.Items.GroupId = groupId;
      Data.Items.ServerType = serverType;
      Data.Items.ServerId = serverId;
      Data.Handles.ObjectHandle = 0;
   }
   //------------------------------------------------------------
   // <T>构造网络目标。</T>
   SNetTarget(TUint8 groupId, TUint8 serverType, TUint8 serverId, TUint16 objectHandle){
      Data.Items.GroupId = groupId;
      Data.Items.ServerType = serverType;
      Data.Items.ServerId = serverId;
      Data.Handles.ObjectHandle = objectHandle;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool operator==(const SNetTarget& value){
      return Equals(value);
   }
   //------------------------------------------------------------
   // <T>判断是否不等。</T>
   MO_INLINE TBool operator!=(const SNetTarget& value){
      return !Equals(value);
   }
   //------------------------------------------------------------
   // <T>赋值处理。</T>
   MO_INLINE void operator=(const SNetTarget& value){
      Data.Handle = value.Data.Handle;
   }
public:
   //------------------------------------------------------------
   // <T>获得句柄。</T>
   MO_INLINE TNetTargetHandle Handle() const{
      return Data.Handle;
   }
   //------------------------------------------------------------
   // <T>设置句柄。</T>
   MO_INLINE void SetHandle(TNetTargetHandle handle){
      Data.Handle = handle;
   }
   //------------------------------------------------------------
   // <T>获得服务器句柄。</T>
   MO_INLINE TUint32 ServerHandle() const{
      return Data.Handles.ServerHandle;
   }
   //------------------------------------------------------------
   // <T>设置服务器句柄。</T>
   MO_INLINE void SetServerHandle(TUint32 handle){
      Data.Handles.ServerHandle = handle;
   }
   //------------------------------------------------------------
   // <T>获得对象句柄。</T>
   MO_INLINE TUint32 ObjectHandle() const{
      return Data.Handles.ObjectHandle;
   }
   //------------------------------------------------------------
   // <T>设置对象句柄。</T>
   MO_INLINE void SetObjectHandle(TUint32 handle){
      Data.Handles.ObjectHandle = handle;
   }
   //------------------------------------------------------------
   // <T>获得分组句柄。</T>
   MO_INLINE TUint16 GroupId() const{
      return Data.Items.GroupId;
   }
   //------------------------------------------------------------
   // <T>设置分组句柄。</T>
   MO_INLINE void SetGroupId(TUint16 groupId){
      Data.Items.GroupId = groupId;
   }
   //------------------------------------------------------------
   // <T>获得服务器类型。</T>
   MO_INLINE TUint8 ServerType() const{
      return Data.Items.ServerType;
   }
   //------------------------------------------------------------
   // <T>设置服务器类型。</T>
   MO_INLINE void SetServerType(TUint8 type){
      Data.Items.ServerType = type;
   }
   //------------------------------------------------------------
   // <T>获得服务器编号。</T>
   MO_INLINE TUint8 ServerId() const{
      return Data.Items.ServerId;
   }
   //------------------------------------------------------------
   // <T>设置服务器编号。</T>
   MO_INLINE void SetServerId(TUint8 serverId){
      Data.Items.ServerId = serverId;
   }
   //------------------------------------------------------------
   // <T>获得对象索引。</T>
   MO_INLINE TUint16 ObjectIndex() const{
      return Data.Items.ObjectIndex;
   }
   //------------------------------------------------------------
   // <T>设置对象索引。</T>
   MO_INLINE void SetObjectIndex(TUint16 index){
      Data.Items.ObjectIndex = index;
   }
   //------------------------------------------------------------
   // <T>获得对象索引。</T>
   MO_INLINE TUint8 ObjectSerial() const{
      return (TUint8)Data.Items.ObjectSerial;
   }
   //------------------------------------------------------------
   // <T>设置对象索引。</T>
   MO_INLINE void SetObjectSerial(TUint16 serial){
      Data.Items.ObjectSerial = serial;
   }
public:
   //------------------------------------------------------------
   // <T>获得句柄。</T>
   MO_INLINE TNetTargetHandle Get(){
      return Data.Handle;
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void Set(TNetTargetHandle handle){
      Data.Handle = handle;
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void Set(const SNetTarget& target){
      Data.Handle = target.Data.Handle;
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void Set(TUint32 serverHandle, TUint32 objectHandle){
      Data.Handles.ServerHandle = serverHandle;
      Data.Handles.ObjectHandle = objectHandle;
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void Set(TUint16 groupId, TUint8 serverType, TUint8 serverId, TUint32 objectHandle){
      Data.Items.GroupId = groupId;
      Data.Items.ServerType = serverType;
      Data.Items.ServerId = serverId;
      Data.Handles.ObjectHandle = objectHandle;
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void Set(TUint16 groupId, TUint8 serverType, TUint8 serverId, TUint16 objectIndex, TUint16 objectSerial){
      Data.Items.GroupId = groupId;
      Data.Items.ServerType = serverType;
      Data.Items.ServerId = serverId;
      Data.Items.ObjectIndex = objectIndex;
      Data.Items.ObjectSerial = objectSerial;
   }
   //------------------------------------------------------------
   // <T>设置服务。</T>
   MO_INLINE void SetServer(SNetTarget& target){
      Data.Handles.ServerHandle = target.Data.Handles.ServerHandle;
      Data.Handles.ObjectHandle = 0;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool Equals(const SNetTarget& value){
      return (Data.Handle == value.Data.Handle);
   }
   //------------------------------------------------------------
   // <T>重置内容。</T>
   MO_INLINE void Reset(){
      Data.Handle = 0;
   }
public:
   //------------------------------------------------------------
   // <T>显示内容。</T>
   TCharC* ToDisplay(TChar* pValue, TSize capacity){
      TStringRefer refer(pValue, capacity);
      refer.AppendFormat(TC("(%d)-(%d,%d)-(%d,%d)"),
            Data.Items.GroupId, Data.Items.ServerType, Data.Items.ServerId,
            Data.Items.ObjectIndex, Data.Items.ObjectSerial);
      return pValue;
   }
   //------------------------------------------------------------
   // <T>跟踪内容。</T>
   TCharC* Track(TChar* pTrack, TSize capacity){
      TStringRefer track(pTrack, capacity);
      track.AppendFormat(TC("(%d)-(%d,%d)-(%d,%d)"),
            Data.Items.GroupId, Data.Items.ServerType, Data.Items.ServerId,
            Data.Items.ObjectIndex, Data.Items.ObjectSerial);
      return pTrack;
   }
};
#pragma pack()

//============================================================
// <T>网络目标集合</T>
//
// @history 100318 MAOCY 创建
//============================================================
class MO_CR_DECLARE TNetTargets{
protected:
   TUint16 _count;
   TByte _targets[sizeof(SNetTarget) * MO_NETMESSAGE_MAXTARGET];
public:
   //------------------------------------------------------------
   // <T>构造网络目标集合。</T>
   TNetTargets(){
      _count = 0;
   }
   //------------------------------------------------------------
   // <T>构造网络目标集合。</T>
   TNetTargets(const TNetTargets& targets){
      Assign(targets);
   }
public:
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE const SNetTarget& operator[](TInt index) const{
      MO_ASSERT_RANGE(index, 0, MO_NETMESSAGE_MAXTARGET);
      SNetTarget* pTargets = (SNetTarget*)_targets;
      return pTargets[index];
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE SNetTarget& operator[](TInt index){
      MO_ASSERT_RANGE(index, 0, MO_NETMESSAGE_MAXTARGET);
      SNetTarget* pTargets = (SNetTarget*)_targets;
      return pTargets[index];
   }
public:
   //------------------------------------------------------------
   // <T>判断是否为空。</T>
   MO_INLINE TBool IsEmpty(){
      return (0 == _count);
   }
   //------------------------------------------------------------
   // <T>测试是否为满。</T>
   MO_INLINE TBool IsFull(){
      return (MO_NETMESSAGE_MAXTARGET == _count);
   }
   //------------------------------------------------------------
   // <T>获得数据长度。</T>
   MO_INLINE TInt Count(){
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得目标指针。</T>
   MO_INLINE SNetTarget* Targets(){
      return (SNetTarget*)_targets;
   }
   //------------------------------------------------------------
   // <T>获得当前容积。</T>
   MO_INLINE TSize Capacity(){
      return sizeof(TUint16) + sizeof(SNetTarget) * _count;
   }
   //------------------------------------------------------------
   // <T>设置单个目标。</T>
   MO_INLINE void Assign(const TNetTargets& targets){
      _count = targets._count;
      MO_LIB_MEMORY_COPY(_targets, sizeof(SNetTarget) * MO_NETMESSAGE_MAXTARGET, targets._targets, sizeof(SNetTarget) * _count);
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE SNetTarget& Get(TInt index){
      MO_ASSERT_RANGE(index, 0, MO_NETMESSAGE_MAXTARGET);
      SNetTarget* pTargets = (SNetTarget*)_targets;
      return pTargets[index];
   }
   //------------------------------------------------------------
   // <T>设置单个目标。</T>
   MO_INLINE void Set(const SNetTarget& target){
      SNetTarget* pTargets = (SNetTarget*)_targets;
      pTargets[0] = target;
      _count = 1;
   }
   //------------------------------------------------------------
   // <T>增加目标。</T>
   MO_INLINE void Push(const SNetTarget& target){
      SNetTarget* pTargets = (SNetTarget*)_targets;
      pTargets[_count++] = target;
   }
   //------------------------------------------------------------
   // <T>清除数据。</T>
   MO_INLINE void Clear(){
      _count = 0;
   }
public:
   //------------------------------------------------------------
   // <T>获得是否含有指定服务器句柄。</T>
   TBool ContainsServerHandle(TUint32 ServerHandle){
      SNetTarget* pTargets = (SNetTarget*)_targets;
      for(TInt n = 0; n<_count; n++){
         if(pTargets[n].Data.Handles.ServerHandle == ServerHandle){
            return ETrue;
         }
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>获取全部唯一主机</T>
   void FetchServers(TNetTargets& servers){
      SNetTarget* pTargets = (SNetTarget*)_targets;
      for(TInt n = 0; n < _count; n++){
         SNetTarget& target = pTargets[n];
         TUint32 serverHandle = target.ServerHandle();
         if(!servers.ContainsServerHandle(serverHandle)){
            servers.Push(target);
         }
      }
   }
   //------------------------------------------------------------
   // <T>获取全部指定主机的网络目标列表</T>
   void FetchServerTargets(TNetTargets& servers, TUint32 serverHandle){
      SNetTarget* pTargets = (SNetTarget*)_targets;
      for(TInt n = 0; n < _count; n++){
         SNetTarget& target = pTargets[n];
         if(target.ServerHandle() == serverHandle){
            servers.Push(target);
         }
      }
   }
public:
   //------------------------------------------------------------
   // <T>序列化内部数据到数据区。</T>
   TInt Serialize(TAny* pMemory){
      TByte* pPtr = (TByte*)pMemory;
      // 设置总数
      *(TUint16*)pPtr = _count;
      // 设置数据
      TInt total = 0;
      if(_count > 0){
         total = sizeof(SNetTarget) * _count;
         MO_LIB_MEMORY_COPY(pPtr + sizeof(TUint16), total, _targets, total);
      }
      return sizeof(TUint16) + total;
   }
   //------------------------------------------------------------
   // <T>反序列化数据区到内部数据。</T>
   TInt Unserialize(TAnyC* pMemory){
      TByteC* pPtr = (TByteC*)pMemory;
      // 获得总数
      _count = *(TUint16*)pPtr;
      if(_count > MO_NETMESSAGE_MAXTARGET){
         MO_DEBUG_FATAL(TC("Unserialize net targets not enough memory. (size=%d, count=%d)"), MO_NETMESSAGE_MAXTARGET, this->_count);
         return -1;
      }
      // 复制数据
      TInt total = 0;
      if(_count > 0){
         total = sizeof(SNetTarget) * _count;
         MO_LIB_MEMORY_COPY(_targets, total, pPtr + sizeof(TUint16), total);
      }
      return sizeof(TUint16) + total;
   }
   //------------------------------------------------------------
   // <T>重置内部数据。</T>
   void Reset(){
      _count = 0;
   }
};

//============================================================
// <T>网络数据头定义。</T>
// <P>6字节。</P>
//
// @history 100402 MAOCY 创建
//============================================================
#pragma pack(1)
struct MO_CR_DECLARE SNetHead{
   TNetLength length;   // 数据长度 (包含自身长度)
   TUint8     protocol; // 封包类型 (Data/Message/Router/Transfer/Package)
   TUint8     sign;     // 封包命令
   TNetHash   hash;     // 数据签名
};
#pragma pack()

//============================================================
// <T>网络数据头信息</T>
//
// @history 101014 MAOCY 创建
//============================================================
class MO_CR_DECLARE TNetHead{
protected:
   SNetHead _data;
public:
   TNetHead();
public:
   //------------------------------------------------------------
   // <T>获得数据内容。</T>
   MO_INLINE SNetHead& Data(){
      return _data;
   }
   //------------------------------------------------------------
   // <T>获得头大小。</T>
   MO_INLINE TInt Capacity(){
      return sizeof(SNetHead);
   }
public:
   //------------------------------------------------------------
   // <T>获得长度。</T>
   MO_INLINE TNetLength Length(){
      return _data.length;
   }
   //------------------------------------------------------------
   // <T>设置长度。</T>
   MO_INLINE void SetLength(TNetLength length){
      _data.length = length;
   }
   //------------------------------------------------------------
   // <T>获得协议。</T>
   MO_INLINE TUint8 Protocol(){
      return _data.protocol;
   }
   //------------------------------------------------------------
   // <T>设置协议。</T>
   MO_INLINE void SetProtocol(TUint8 protocol){
      _data.protocol = protocol;
   }
   //------------------------------------------------------------
   // <T>获得标志。</T>
   MO_INLINE TUint8 Sign(){
      return _data.sign;
   }
   //------------------------------------------------------------
   // <T>设置标志。</T>
   MO_INLINE void SetSign(TUint8 sign){
      _data.sign = sign;
   }
   //------------------------------------------------------------
   // <T>获得压缩标志。</T>
   MO_INLINE TBool SignCompress(){
      return (_data.sign & MO_NET_SIGN_COMPRESS);
   }
   //------------------------------------------------------------
   // <T>设置压缩标志。</T>
   MO_INLINE void SetSignCompress(TBool compress){
      if(compress){
         _data.sign |= MO_NET_SIGN_COMPRESS;
      }else{
         _data.sign &= ~MO_NET_SIGN_COMPRESS;
      }
   }
   //------------------------------------------------------------
   // <T>获得遮挡标志。</T>
   MO_INLINE TBool SignMask(){
      return (_data.sign & MO_NET_SIGN_MASK);
   }
   //------------------------------------------------------------
   // <T>设置遮挡标志。</T>
   MO_INLINE void SetSignMask(TBool mask){
      if(mask){
         _data.sign |= MO_NET_SIGN_MASK;
      }else{
         _data.sign &= ~MO_NET_SIGN_MASK;
      }
   }
   //------------------------------------------------------------
   // <T>获得哈希值。</T>
   MO_INLINE TNetHash Hash(){
      return _data.hash;
   }
   //------------------------------------------------------------
   // <T>设置哈希值。</T>
   MO_INLINE void SetHash(TNetHash hash){
      _data.hash = hash;
   }
public:
   void Assign(TNetHead& head);
   TInt Serialize(TAny* pMemory);
   TInt Unserialize(TAnyC* pMemory);
};

//============================================================
// <T>网络数据头信息</T>
//
// @history 101014 MAOCY 创建
//============================================================
class MO_CR_DECLARE TNetData{
protected:
   TNetHead _netHead;
   TByteC* _pData;
   TInt _dataLength;
public:
   TNetData();
   MO_ABSTRACT ~TNetData();
public:
   //------------------------------------------------------------
   // <T>获得网络头信息。</T>
   MO_INLINE TNetHead& NetHead(){
      return _netHead;
   }
   //------------------------------------------------------------
   // <T>获得数据长度。</T>
   MO_INLINE TBool HasData(){
      return (NULL != _pData) && (_dataLength > 0);
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE TByteC* DataC() const{
      return _pData;
   }
   //------------------------------------------------------------
   // <T>获得数据长度。</T>
   MO_INLINE TInt DataLength(){
      return _dataLength;
   }
protected:
   static TNetHash CalculateHash(TNetSerial serial, TNetTick date, TByteC* pMemory, TInt length);
   static TBool MaskData(TByte* pDate, TInt capacity, TByteC* pInput, TInt length, TNetHash hash);
public:
   MO_ABSTRACT TInt Capacity();
public:
   MO_ABSTRACT TBool Assign(TNetData* pData);
public:
   MO_ABSTRACT TBool SerializeData(TAny* pMemory, TInt size, TInt* length);
   MO_ABSTRACT TBool UnserializeData(TAnyC* pMemory, TInt size, TInt* length);
   MO_ABSTRACT TBool Serialize(TAny* pMemory, TInt size, TInt* length);
   MO_ABSTRACT TBool Unserialize(TAnyC* pMemory, TInt size, TInt* length);
public:
   TInt Store(TAny* pMemory);
   TInt Restore(TAnyC* pMemory);
public:
   MO_ABSTRACT TCharC* DumpData(TChar* pDump, TSize capacity);
   MO_ABSTRACT TCharC* DumpMemory(TChar* pDump, TSize capacity);
   MO_ABSTRACT TCharC* Dump(TChar* pDump, TSize capacity);
};

//============================================================
// <T>网络数据缓冲。</T>
//
// @history 110516 MAOCY 创建
//============================================================
class MO_CR_DECLARE TNetBuffer{
protected:
   TByte _buffer[MO_NETMESSAGE_MAXLENGTH];
public:
   //------------------------------------------------------------
   // <T>获得数据。</T>
   MO_INLINE operator TByte*(){
      return _buffer;
   }
public:
   //------------------------------------------------------------
   // <T>获得内存。</T>
   MO_INLINE TByte* Memory(){
      return _buffer;
   }
   //------------------------------------------------------------
   // <T>获得尺寸。</T>
   MO_INLINE TSize Size(){
      return MO_NETMESSAGE_MAXLENGTH;
   }
};

//============================================================
// <T>网络消息头信息的定义。</T>
//
// @history 101020 MAOCY 创建
//============================================================
class MO_CR_DECLARE TNetMessageInfo{
public:
   TCharC* _pName;          // 名称
   TUint32 _sign;           // 标志 (TCP/UDP/THREAD)
   TNetVersion _version;    // 版本
   TUint32 _sourceTerminal; // 源终端类型
   TUint32 _targetTerminal; // 源终端类型
   TUint32 _serviceCd;      // 服务类型
public:
   //------------------------------------------------------------
   // <T>构造网络消息头信息的定义。</T>
   TNetMessageInfo(TCharC* pName, TUint32 sign, TNetVersion version, TUint32 sourceTerminal, TUint32 targetTerminal, TUint32 serviceCd){
      _pName = pName;
      _sign = sign;
      _version = version;
      _sourceTerminal = sourceTerminal;
      _targetTerminal = targetTerminal;
      _serviceCd = serviceCd;
   }
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _pName;
   }
   //------------------------------------------------------------
   // <T>获得标志。</T>
   MO_INLINE TUint32 Sign(){
      return _sign;
   }
   //------------------------------------------------------------
   // <T>获得版本。</T>
   MO_INLINE TNetVersion Version(){
      return _version;
   }
   //------------------------------------------------------------
   // <T>获得终端来源。</T>
   MO_INLINE TUint32 SourceTerminal(){
      return _sourceTerminal;
   }
   //------------------------------------------------------------
   // <T>获得终端目标。</T>
   MO_INLINE TUint32 TargetTerminal(){
      return _targetTerminal;
   }
   //------------------------------------------------------------
   // <T>获得服务类型。</T>
   MO_INLINE TUint32 ServiceCd(){
      return _serviceCd;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否支持TCP协议。</T>
   MO_INLINE TBool IsTcpSupport(){
      TUint32 sign = 1 << ENetMessageSign_Tcp;
      return _sign & sign;
   }
   //------------------------------------------------------------
   // <T>判断是否支持UDP协议。</T>
   MO_INLINE TBool IsUdpSupport(){
      TUint32 sign = 1 << ENetMessageSign_Udp;
      return _sign & sign;
   }
   //------------------------------------------------------------
   // <T>判断是否支持线程。</T>
   MO_INLINE TBool IsThreadSupport(){
      TUint32 sign = 1 << ENetMessageSign_Thread;
      return _sign & sign;
   }
};

//============================================================
// <T>网络消息头信息的定义。</T>
// <P>4字节。</P>
//
// @history 100401 MAOCY 创建
//============================================================
#pragma pack(2)
struct MO_CR_DECLARE SNetMessageHead{
   TUint8  type;        // 消息类型（Request/Response/Notify）
   TUint8  command;     // 消息命令
   TUint16 code;        // 消息代码
   TNetVersion version; // 消息版本
   TNetSerial serial;   // 消息序列
   TNetTick tick;       // 消息时间
};
#pragma pack()

//============================================================
// <T>网络消息头信息</T>
//
// @history 100401 MAOCY 创建
//============================================================
class MO_CR_DECLARE TNetMessageHead{
protected:
   SNetMessageHead _data;
public:
   TNetMessageHead();
public:
   //------------------------------------------------------------
   // <T>获得数据内容。</T>
   MO_INLINE SNetMessageHead& Data(){
      return _data;
   }
   //------------------------------------------------------------
   // <T>获得消息大小。</T>
   MO_INLINE TSize Capacity(){
      return sizeof(SNetMessageHead);
   }
public:
   //------------------------------------------------------------
   // <T>获得类型。</T>
   MO_INLINE TUint8 Type(){
      return _data.type;
   }
   //------------------------------------------------------------
   // <T>设置类型。</T>
   MO_INLINE void SetType(TUint8 type){
      _data.type = type;
   }
   //------------------------------------------------------------
   // <T>获得命令。</T>
   MO_INLINE TUint8 Command(){
      return _data.command;
   }
   //------------------------------------------------------------
   // <T>设置命令。</T>
   MO_INLINE void SetCommand(TUint8 command){
      _data.command = command;
   }
   //------------------------------------------------------------
   // <T>获得分组。</T>
   MO_INLINE TUint16 CodeGroup(){
      return _data.code & 0xFF00;
   }
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE TUint16 Code(){
      return _data.code;
   }
   //------------------------------------------------------------
   // <T>设置代码。</T>
   MO_INLINE void SetCode(TUint16 code){
      _data.code = code;
   }
   //------------------------------------------------------------
   // <T>获得版本。</T>
   MO_INLINE TNetVersion Version(){
      return _data.version;
   }
   //------------------------------------------------------------
   // <T>设置版本。</T>
   MO_INLINE void SetVersion(TNetVersion version){
      _data.version = version;
   }
   //------------------------------------------------------------
   // <T>获得序列。</T>
   MO_INLINE TNetSerial Serial(){
      return _data.serial;
   }
   //------------------------------------------------------------
   // <T>设置序列。</T>
   MO_INLINE void SetSerial(TNetSerial serial){
      _data.serial = serial;
   }
   //------------------------------------------------------------
   // <T>获得时刻。</T>
   MO_INLINE TNetTick Tick(){
      return _data.tick;
   }
   //------------------------------------------------------------
   // <T>设置时刻。</T>
   MO_INLINE void SetTick(TNetTick tick){
      _data.tick = tick;
   }
public:
   TCharC* CodeName();
public:
   void Continue(TNetMessageHead& head);
   void Response(TNetMessageHead& head);
public:
   void Assign(TNetMessageHead& head);
   TInt Serialize(TAny* pMemory);
   TInt Unserialize(TAnyC* pMemory);
};

//============================================================
// <T>网络消息。</T>
//
// @history 100401 MAOCY 创建
//============================================================
class MO_CR_DECLARE TNetMessage : public TNetData{
protected:
   TNetMessageHead _messageHead;
public:
   TNetMessage();
   TNetMessage(TNetMessage* pMessage);
   MO_ABSTRACT ~TNetMessage(){
   }
public:
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void operator=(TNetMessage* pMessage){
      Assign(pMessage);
   }
public:
   //------------------------------------------------------------
   // <T>获得消息信息。</T>
   MO_INLINE TNetMessageHead& MessageHead(){
      return _messageHead;
   }
public:
   MO_OVERRIDE TInt Capacity();
   MO_ABSTRACT TNetMessageInfo* MessageInfo();
public:
   MO_OVERRIDE TBool Assign(TNetMessage* pMessage);
public:
   TBool SerializeMessage(TAny* pMemory, TInt size, TInt* pLength);
   TBool UnserializeMessage(TAnyC* pMemory, TInt size, TInt* pLength);
   TBool CompressMessage(TAny* pMemory, TInt size, TInt* pLength);
   MO_OVERRIDE TBool Serialize(TAny* pMemory, TInt size, TInt* pLength);
   MO_OVERRIDE TBool Unserialize(TAnyC* pMemory, TInt size, TInt* pLength);
public:
   TCharC* DumpMessage(TChar* pDump, TSize capacity);
   TCharC* DumpMessageMemory(TChar* pDump, TSize capacity);
   MO_OVERRIDE TCharC* Dump(TChar* pDump, TSize capacity);
};

//============================================================
// <T>网络消息。</T>
//
// @history 100401 MAOCY 创建
//============================================================
class MO_CR_DECLARE TNetMessageBuffer : public TNetMessage{
protected:
   TByte _buffer[MO_NETMESSAGE_MAXLENGTH];
public:
   TNetMessageBuffer();
   TNetMessageBuffer(TAnyC* pMemory);
   MO_ABSTRACT ~TNetMessageBuffer(){
   }
public:
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void operator=(TAnyC* pMemory){
      Restore(pMemory);
   }
public:
   //------------------------------------------------------------
   // <T>获得内存。</T>
   MO_INLINE TByte* Memory(){
      return _buffer;
   }
public:
   void AssignData(TByteC* pData, TInt length);
   void Update();
public:
   MO_OVERRIDE TBool Serialize(TAny* pMemory, TInt size, TInt* length);
   MO_OVERRIDE TBool Unserialize(TAnyC* pMemory, TInt size, TInt* length);
   TBool UnserializeMask(TAnyC* pMemory, TInt size, TInt* length);
   TBool UnserializeUncheck(TAnyC* pMemory, TInt size, TInt* length);
   TBool Compress(TAny* pMemory, TInt size, TInt* length);
   TBool Uncompress(TAnyC* pMemory, TInt size, TInt* length, TBool masked = EFalse, TBool checked = ETrue);
public:
   MO_OVERRIDE TCharC* Dump(TChar* pDump, TSize capacity);
};

//============================================================
// <T>网络路由结构。</T>
// <P>20字节。</P>
//
//  - flag      [16Bit]      路由标志
//  - origin    [32Bit]      网络原始来源
//  - source    [32Bit]      网络来源
//  - target    [32Bit]      网络目标
//  - serial    [32Bit]      路由序列
//  - targets   [32Bitx1024] 发送目标列表
// @history 091220 MAOCY 创建
//============================================================
#pragma pack(2)
struct MO_CR_DECLARE SNetRouterHead{
   TUint16    Flag;
   SNetTarget Origin;
   SNetTarget Source;
   SNetTarget Target;
   TNetSerial Serial;
};
#pragma pack()

//============================================================
class MO_CR_DECLARE TNetRouterHead{
protected:
   SNetRouterHead _data;
   TNetTargets _targets;
public:
   TNetRouterHead();
public:
   //------------------------------------------------------------
   // <T>获得数据内容。</T>
   MO_INLINE SNetRouterHead& Data(){
      return _data;
   }
   //------------------------------------------------------------
   // <T>获得消息大小。</T>
   MO_INLINE TSize Capacity(){
      return sizeof(SNetRouterHead) + _targets.Capacity();
   }
public:
   //------------------------------------------------------------
   // <T>获得标志。</T>
   MO_INLINE TUint16 Flag(){
      return _data.Flag;
   }
   //------------------------------------------------------------
   // <T>设置标志。</T>
   MO_INLINE void SetFlag(TUint16 flag){
      _data.Flag = flag;
   }
   //------------------------------------------------------------
   // <T>获得非法标志。</T>
   MO_INLINE TBool FlagInvalid(){
      return (_data.Flag & MO_NETROUTER_FLAG_INVALID);
   }
   //------------------------------------------------------------
   // <T>设置非法标志。</T>
   MO_INLINE void SetFlagInvalid(TBool invalid){
      if(invalid){
         _data.Flag |= MO_NETROUTER_FLAG_INVALID;
      }else{
         _data.Flag &= ~MO_NETROUTER_FLAG_INVALID;
      }
   }
   //------------------------------------------------------------
   // <T>获得断开标志。</T>
   MO_INLINE TBool FlagDisconnect(){
      return (_data.Flag & MO_NETROUTER_FLAG_DISCONNECT);
   }
   //------------------------------------------------------------
   // <T>设置断开标志。</T>
   MO_INLINE void SetFlagDisconnect(TBool disconnect){
      if(disconnect){
         _data.Flag |= MO_NETROUTER_FLAG_DISCONNECT;
      }else{
         _data.Flag &= ~MO_NETROUTER_FLAG_DISCONNECT;
      }
   }
   //------------------------------------------------------------
   // <T>获得网络来源。</T>
   MO_INLINE SNetTarget& Origin(){
      return _data.Origin;
   }
   //------------------------------------------------------------
   // <T>设置网络来源。</T>
   MO_INLINE void SetOrigin(SNetTarget& origin){
      _data.Origin = origin;
   }
   //------------------------------------------------------------
   // <T>获得会话编号。</T>
   MO_INLINE TUint32 SessionId(){
      return _data.Origin.ObjectHandle();
   }
   //------------------------------------------------------------
   // <T>设置会话编号。</T>
   MO_INLINE void SetSessionId(TUint32 sessionId){
      _data.Origin.SetObjectHandle(sessionId);
   }
   //------------------------------------------------------------
   // <T>获得网络来源。</T>
   MO_INLINE SNetTarget& Source(){
      return _data.Source;
   }
   //------------------------------------------------------------
   // <T>设置网络来源。</T>
   MO_INLINE void SetSource(SNetTarget& source){
      _data.Source = source;
   }
   //------------------------------------------------------------
   // <T>获得来源类型。</T>
   MO_INLINE TUint8 SourceType(){
      return _data.Source.ServerType();
   }
   //------------------------------------------------------------
   // <T>设置来源类型。</T>
   MO_INLINE void SetSourceType(TUint8 type){
      _data.Source.SetServerType(type);
   }
   //------------------------------------------------------------
   // <T>获得网络目标。</T>
   MO_INLINE SNetTarget& Target(){
      return _data.Target;
   }
   //------------------------------------------------------------
   // <T>设置网络目标。</T>
   MO_INLINE void SetTarget(SNetTarget& target){
      _data.Target = target;
   }
   //------------------------------------------------------------
   // <T>获得网络目标类型。</T>
   MO_INLINE TUint8 TargetType(){
      return _data.Target.ServerType();
   }
   //------------------------------------------------------------
   // <T>设置网络目标类型。</T>
   MO_INLINE void SetTargetType(TUint8 type){
      _data.Target.SetServerType(type);
   }
   //------------------------------------------------------------
   // <T>获得序列。</T>
   MO_INLINE TNetSerial Serial(){
      return _data.Serial;
   }
   //------------------------------------------------------------
   // <T>设置序列。</T>
   MO_INLINE void SetSerial(TNetSerial serial){
      _data.Serial = serial;
   }
   //------------------------------------------------------------
   // <T>获得网络目标集合。</T>
   MO_INLINE TNetTargets& Targets(){
      return _targets;
   }
public:
   void Continue(TNetRouterHead& head);
   void Response(TNetRouterHead& head);
public:
   void Assign(TNetRouterHead& head);
   TInt Serialize(TAny* pMemory);
   TInt Unserialize(TAnyC* pMemory);
public:
   TCharC* Dump(TChar* pDump, TSize length);
};

//============================================================
// <T>网络消息环境。</T>
//
// @history 091220 MAOCY 创建
//============================================================
class MO_CR_DECLARE TNetRouter : public TNetMessage{
protected:
   TNetRouterHead _routerHead;
   TByte _buffer[MO_NETMESSAGE_MAXLENGTH];
public:
   TNetRouter();
   TNetRouter(TNetMessage* pMessage);
   MO_ABSTRACT ~TNetRouter();
public:
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void operator=(TNetMessage* pMessage){
      LinkMessage(pMessage);
   }
public:
   //------------------------------------------------------------
   // <T>获得路由头。</T>
   MO_INLINE TNetRouterHead& RouterHead(){
      return _routerHead;
   }
   //------------------------------------------------------------
   // <T>获得网络目标集合。</T>
   MO_INLINE TNetTargets& Targets(){
      return _routerHead.Targets();
   }
public:
   MO_OVERRIDE TInt Capacity();
public:
   void LinkMessage(TNetMessage* pMessage);
   void ContinueFrom(TNetRouter* pRouter);
   void ResponseFrom(TNetRouter* pRouter);
public:
   MO_OVERRIDE TBool Assign(TNetRouter* pRouter);
   TBool StoreMessage(TNetMessageBuffer& buffer);
   TBool SerializeRouter(TAny* pMemory, TInt size, TInt* length);
   TBool UnserializeRouter(TAnyC* pMemory, TInt size, TInt* length);
   MO_OVERRIDE TBool Serialize(TAny* pMemory, TInt size, TInt* length);
   MO_OVERRIDE TBool Unserialize(TAnyC* pMemory, TInt size, TInt* length);
public:
   TCharC* DumpRouter(TChar* pDump, TSize capacity);
   TCharC* DumpRouterMemory(TChar* pDump, TSize capacity);
   MO_OVERRIDE TCharC* Dump(TChar* pDump, TSize capacity);
};

//============================================================
// <T>网络传输结构。</T>
//
//  - Handle [32Bit] 网络句柄
//  - Ip     [32Bit] 地址
//  - Port   [16Bit] 端口
//  - socket [32Bit] 网络链接
// @history 091220 MAOCY 创建
//============================================================
#pragma pack(2)
struct MO_CR_DECLARE SNetTransferHead{
   TUint32 Handle;
   TUint32 Ip;
   TUint16 Port;
   SNetSocketTarget socket;
};
#pragma pack()

//============================================================
#pragma pack(2)
class MO_CR_DECLARE TNetTransferHead{
protected:
   SNetTransferHead _data;
public:
   TNetTransferHead();
public:
   //------------------------------------------------------------
   // <T>获得数据内容。</T>
   MO_INLINE SNetTransferHead& Data(){
      return _data;
   }
   //------------------------------------------------------------
   // <T>获得消息大小。</T>
   MO_INLINE TSize Capacity(){
      return sizeof(SNetTransferHead);
   }
public:
   //------------------------------------------------------------
   // <T>获得句柄。</T>
   MO_INLINE TUint32 Handle(){
      return _data.Handle;
   }
   //------------------------------------------------------------
   // <T>设置句柄。</T>
   MO_INLINE void SetHandle(TUint32 handle){
      _data.Handle = handle;
   }
   //------------------------------------------------------------
   // <T>获得网络地址。</T>
   MO_INLINE TUint32 Ip(){
      return _data.Ip;
   }
   //------------------------------------------------------------
   // <T>设置网络地址。</T>
   MO_INLINE void SetIp(TUint32 ip){
      _data.Ip = ip;
   }
   //------------------------------------------------------------
   // <T>获得网络端口。</T>
   MO_INLINE TUint16 Port(){
      return _data.Port;
   }
   //------------------------------------------------------------
   // <T>设置网络端口。</T>
   MO_INLINE void SetPort(TUint16 port){
      _data.Port = port;
   }
   //------------------------------------------------------------
   // <T>获得网络主机。</T>
   MO_INLINE void SetHost(TUint32 ip, TUint16 port){
      _data.Ip = ip;
      _data.Port = port;
   }
   //------------------------------------------------------------
   // <T>获得网络目标。</T>
   MO_INLINE SNetSocketTarget& Socket(){
      return _data.socket;
   }
   //------------------------------------------------------------
   // <T>获得网络索引。</T>
   MO_INLINE TUint16 SocketIndex(){
      return _data.socket.Index();
   }
   //------------------------------------------------------------
   // <T>设置网络目标。</T>
   MO_INLINE void SetSocket(TUint16 index, TUint16 serial){
      _data.socket.Set(index, serial);
   }
   //------------------------------------------------------------
   // <T>设置网络目标。</T>
   MO_INLINE void SetSocket(SNetSocketTarget socket){
      _data.socket = socket;
   }
   //------------------------------------------------------------
   // <T>设置网络句柄。</T>
   MO_INLINE void SetSocketHandle(TUint32 handle){
      _data.socket.Set(handle);
   }
public:
   void Assign(TNetTransferHead& head);
   TInt Serialize(TAny* pMemory);
   TInt Unserialize(TAnyC* pMemory);
   TBool Pack(MPack* pPack);
   TBool Unpack(MPack* pPack);
public:
   //------------------------------------------------------------
   // <T>重置内容。</T>
   MO_INLINE void Reset(){
      _data.Handle = 0;
      _data.Ip = 0;
      _data.Port = 0;
      _data.socket.Reset();      
   }
public:
   TCharC* Dump(TChar* pDump, TSize length);
public:
   //------------------------------------------------------------
   // <T>跟踪信息。</T>
   MO_INLINE TChar* Track(MString* pTrack, TInt level){
      return NULL;
   }
};
#pragma pack()

//============================================================
// <T>网络传输器。</T>
//
// @history 091220 MAOCY 创建
//============================================================
class MO_CR_DECLARE TNetTransfer : public TNetRouter{
public:
   enum EMode{
      EMode_Unknown,
      EMode_Continue,
      EMode_Response,
   };
protected:
   TNetTransferHead _transferHead;
public:
   TNetTransfer();
   TNetTransfer(TNetMessage* pMessage);
   TNetTransfer(TNetMessage* pMessage, EMode modeCd, TNetRouter* pRouter);
   TNetTransfer(TNetMessage* pMessage, EMode modeCd, TNetTransfer* pTransfer);
   TNetTransfer(TNetRouter* pRouter);
   MO_ABSTRACT ~TNetTransfer();
public:
   //------------------------------------------------------------
   // <T>设置网络消息。</T>
   MO_INLINE void operator=(TNetMessage* pMessage){
      LinkMessage(pMessage);
   }
   //------------------------------------------------------------
   // <T>设置网络路由。</T>
   MO_INLINE void operator=(TNetRouter* pRouter){
      LinkRouter(pRouter);
   }
public:
   //------------------------------------------------------------
   // <T>获得传输头。</T>
   MO_INLINE TNetTransferHead& TransferHead(){
      return _transferHead;
   }
public:
   MO_OVERRIDE TInt Capacity();
public:
   void ContinueFrom(TNetRouter* pRouter);
   void ContinueFrom(TNetTransfer* pTransfer);
   void ResponseFrom(TNetRouter* pRouter);
   void ResponseFrom(TNetTransfer* pTransfer);
   void LinkMessage(TNetMessage* pMessage);
   void LinkRouter(TNetRouter* pRouter);
public:
   MO_OVERRIDE TBool Assign(TNetTransfer* pRouter);
   MO_OVERRIDE TBool Serialize(TAny* pMemory, TInt size, TInt* length);
   MO_OVERRIDE TBool Unserialize(TAnyC* pMemory, TInt size, TInt* length);
public:
   TCharC* DumpTransfer(TChar* pDump, TSize capacity);
   MO_OVERRIDE TCharC* DumpSimple(TChar* pDump, TSize capacity);
   MO_OVERRIDE TCharC* Dump(TChar* pDump, TSize capacity);
};

//============================================================
// <T>网络数据包定义。</T>
// <P>从客户端解包后，所有服务器内，管道内的首结构。</P>
//
// @history 100401 MAOCY 创建
//============================================================
struct MO_CR_DECLARE SNetPackageHead{
   TUint16 type;
   TUint16 code;
};

//============================================================
// <T>网络包头定义。</T>
//============================================================
class MO_CR_DECLARE TNetPackageHead{
   SNetPackageHead _data;
};

//============================================================
// <T>网络包定义。</T>
//============================================================
class MO_CR_DECLARE TNetPackage : public TNetData{
public:
   TNetPackageHead _head;
public:
   //------------------------------------------------------------
   // <T>析构网络包定义。</T>
   MO_ABSTRACT ~TNetPackage(){
   }
};

//============================================================
// <T>消息工厂接口。</T>
//============================================================
class MO_CR_DECLARE INetMessageFactory{
public:
   //------------------------------------------------------------
   // <T>析构消息工厂接口。</T>
   MO_ABSTRACT ~INetMessageFactory(){
   }
public:
   MO_VIRTUAL TCharC* CodeName(TInt code) = 0;
   MO_VIRTUAL TNetMessageInfo* CodeInfo(TInt code) = 0;
   MO_VIRTUAL TCharC* DumpData(TNetMessage* pMessage, MString* pTrack) = 0;
};

//============================================================
// <T>消息工厂。</T>
//============================================================
class MO_CR_DECLARE RNetMessageFactory{
protected:
   static INetMessageFactory* _pFactory;
public:
   //------------------------------------------------------------
   // <T>获得消息工厂。</T>
   static INetMessageFactory* Factory(){
      return _pFactory;
   }
   //------------------------------------------------------------
   // <T>设置消息工厂。</T>
   static void SetFactory(INetMessageFactory* pFactory){
      MO_ASSERT(pFactory);
      _pFactory = pFactory;
   }
public:
   //------------------------------------------------------------
   // <T>根据消息代码获得消息名称。</T>
   static TCharC* CodeName(TInt code){
      return _pFactory->CodeName(code);
   }
   //------------------------------------------------------------
   // <T>根据消息代码获得消息信息。</T>
   static TNetMessageInfo* CodeInfo(TInt code){
      return _pFactory->CodeInfo(code);
   }
   //------------------------------------------------------------
   // <T>获得消息内容。</T>
   static TCharC* DumpData(TNetMessage* pMessage, MString* pTrack){
      return _pFactory->DumpData(pMessage, pTrack) ;
   }
};

MO_NAMESPACE_END

#endif // __MO_CR_NET_MESSAGE_H__
