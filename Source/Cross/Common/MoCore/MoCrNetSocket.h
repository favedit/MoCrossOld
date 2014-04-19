#ifndef __MO_CR_NET_SOCKET_H__
#define __MO_CR_NET_SOCKET_H__

#ifndef __MO_CM_NET_H__
#include <MoCmNet.h>
#endif // __MO_CM_NET_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

#ifndef __MO_CR_MODULE_H__
#include "MoCrModule.h"
#endif // __MO_CR_MODULE_H__

#ifndef __MO_CR_NET_PIPE_H__
#include "MoCrNetPipe.h"
#endif // __MO_CR_NET_PIPE_H__

#define MO_SVC_SOCKET_RECEIVE 1024*64
#define MO_SVC_SOCKET_SEND    1024*64

MO_NAMESPACE_BEGIN

//============================================================
typedef TUint32 TNetSerial;
typedef TUint32 TNetTick;

//============================================================
class FSharedNetPipe;
class FNetBufferedQueueConnection;

//============================================================
// <T>共享网络链接信息。</T>
//
// @history 100309 MAOCY 创建
//============================================================
struct MO_CR_DECLARE SNetBufferedSocket : public SNetSocketInfo{
   TUint16   index;                       // 端口索引
   TUint16   serial;                      // 端口序列
   TUint32   receiveCount;                // 接收次数
   TUint32   receiveSerial;               // 接收序列
   TTimeTick receiveTick;                 // 接收时刻
   TUint32   receiveBufferSize;           // 接收缓冲尺寸
   TUint32   sendCount;                   // 发送次数
   TTimeTick sendTick;                    // 发送时刻
   TUint32   sendSerial;                  // 发送序列
   TUint32   sendBufferSize;              // 发送缓冲尺寸
   TBool     udpSupport;                  // 是否支持广播协议
   TChar     udpHost[MoNetHostLength];    // 广播主机
   TUint32   udpAddress;                  // 广播地址
   TUint16   udpPort;                     // 广播端口
};

//============================================================
// <T>共享网络链接。</T>
//
// @history 100309 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetBufferedSocket :
      public FObject,
      public MNetSocket{
protected:
   SNetBufferedSocket _info;
   TBool _processing;
   INetSocketPool* _pReceivePool;
   INetSocketPool* _pSendPool;
   FNetBufferedPipe* _pInputPipe;
   FNetBufferedPipe* _pOutputPipe;
public:
   FNetBufferedSocket();
   MO_ABSTRACT ~FNetBufferedSocket();
public:
   MO_OVERRIDE SNetSocketInfo* Info();
public:
   void LinkAllocator(TInt capacity, FBufferedPipeBlockAllocator* pAllocator);
public:
   //------------------------------------------------------------
   // <T>获得网络信息。</T>
   MO_INLINE SNetBufferedSocket* NetInfo(){
      return &_info;
   }
   //------------------------------------------------------------
   // <T>获得索引。</T>
   MO_INLINE TUint16 Index(){
      return _info.index;
   }
   //------------------------------------------------------------
   // <T>设置索引。</T>
   MO_INLINE void SetIndex(TUint16 index){
      _info.index = index;
   }
   //------------------------------------------------------------
   // <T>获得序列。</T>
   MO_INLINE TUint16 Serial(){
      return _info.serial;
   }
   //------------------------------------------------------------
   // <T>设置序列。</T>
   MO_INLINE void SetSerial(TUint16 serial){
      _info.serial = serial;
   }
   //------------------------------------------------------------
   // <T>获得UDP协议支持。</T>
   MO_INLINE TBool IsUdpSupport(){
      return _info.udpSupport;
   }
   //------------------------------------------------------------
   // <T>设置UDP协议支持。</T>
   MO_INLINE void SetUdpSupport(TBool udpSupport){
      _info.udpSupport = udpSupport;
   }
   //------------------------------------------------------------
   // <T>获得UDP地址。</T>
   MO_INLINE TUint32 UdpAddress(){
      return _info.udpAddress;
   }
   //------------------------------------------------------------
   // <T>设置UDP地址。</T>
   MO_INLINE void SetUdpAddress(TUint32 udpAddress){
      _info.udpAddress = udpAddress;
   }
   //------------------------------------------------------------
   // <T>获得UDP端口。</T>
   MO_INLINE TUint16 UdpPort(){
      return _info.udpPort;
   }
   //------------------------------------------------------------
   // <T>设置UDP地址。</T>
   MO_INLINE void SetUdpPort(TUint16 udpPort){
      _info.udpPort = udpPort;
   }
   //------------------------------------------------------------
   // <T>获得接收序列。</T>
   MO_INLINE TNetSerial ReceiveSerial(){
      return _info.receiveSerial;
   }
   //------------------------------------------------------------
   // <T>设置接收序列。</T>
   MO_INLINE void SetReceiveSerial(TNetSerial serial){
      _info.receiveSerial = serial;
   }
   //------------------------------------------------------------
   // <T>获得接收时刻。</T>
   MO_INLINE TNetTick ReceiveTick(){
      return (TNetTick)_info.receiveTick;
   }
   //------------------------------------------------------------
   // <T>获得接收时刻。</T>
   MO_INLINE void SetReceiveTick(TNetTick tick){
      _info.receiveTick = tick;
   }
public:
   //------------------------------------------------------------
   // <T>获得是否在执行中。</T>
   MO_INLINE TBool IsProcessing(){
      return _processing;
   }
   //------------------------------------------------------------
   // <T>获得接收缓冲。</T>
   MO_INLINE INetSocketPool* ReceivePool(){
      return _pReceivePool;
   }
   //------------------------------------------------------------
   // <T>设置接收缓冲。</T>
   MO_INLINE void SetReceivePool(INetSocketPool* pPool){
      _pReceivePool = pPool;
   }
   //------------------------------------------------------------
   // <T>获得发送缓冲。</T>
   MO_INLINE INetSocketPool* SendPool(){
      return _pSendPool;
   }
   //------------------------------------------------------------
   // <T>设置发送缓冲。</T>
   MO_INLINE void SetSendPool(INetSocketPool* pPool){
      _pSendPool = pPool;
   }
   //------------------------------------------------------------
   // <T>获得输出管道。</T>
   MO_INLINE INetPipe* InputPipe(){
      return _pInputPipe;
   }
   //------------------------------------------------------------
   // <T>获得输入管道。</T>
   MO_INLINE INetPipe* OutputPipe(){
      return _pOutputPipe;
   }
public:
   void SetInfo(SNetSocketInfo* pInfo);
public:
   TInt DoBufferReceive();
   TInt DoBufferSend();
public:
   TBool Update();
public:
   MO_ABSTRACT TBool Startup();
   MO_ABSTRACT TBool Shutdown();
};
//------------------------------------------------------------
typedef MO_CR_DECLARE FVector<FNetBufferedSocket*> FNetBufferedSocketVector;
typedef MO_CR_DECLARE FSet<TUint32, FNetBufferedSocket*> FNetBufferedSocketSet;
typedef MO_CR_DECLARE FStoragePool<FNetBufferedSocket*> FNetBufferedSocketPool;

//============================================================
// <T>共享网络链接管理模块。</T>
//
// @history 100309 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetBufferedSocketModule :
      public FModule,
      public IMonitorTrigger,
      public IStatisticsTrigger{
protected:
   TInt _socketCount;
   TInt _socketCapacity;
   TInt _socketReceiveBuffer;
   TInt _socketSendBuffer;
   TInt64 _socketSerial;
   TInt _blockCapacity;
   FNetBufferedSocketSet* _pHandles;
   TThreadSection _sectionSet;
   FNetBufferedSocketPool* _pPool;
   TThreadSection _sectionPool;
   TTimeSpan _sendTimeout;
   TTimeSpan _receiveTimeout;
public:
   FNetBufferedSocketModule();
   MO_ABSTRACT ~FNetBufferedSocketModule();
public:
   //------------------------------------------------------------
   // <T>获得句柄集合。</T>
   MO_INLINE FNetBufferedSocketSet* Handles(){
      return _pHandles;
   }
   //------------------------------------------------------------
   // <T>获得缓冲集合。</T>
   MO_INLINE FNetBufferedSocketPool* Pool(){
      return _pPool;
   }
   //------------------------------------------------------------
   // <T>获得分块容量。</T>
   MO_INLINE TInt BlockCapacity(){
      return _blockCapacity;
   }
   //------------------------------------------------------------
   // <T>获得分块容量。</T>
   MO_INLINE void SetBlockCapacity(TInt blockCapacity){
      _blockCapacity = blockCapacity;
   }
   //------------------------------------------------------------
   // <T>获得容量。</T>
   MO_INLINE TInt Capacity(){
      return _socketCount;
   }
   //------------------------------------------------------------
   // <T>获得容量。</T>
   MO_INLINE void SetCapacity(TInt capacity){
      _socketCount = capacity;
   }
   //------------------------------------------------------------
   // <T>获得发送超时。</T>
   MO_INLINE TTimeSpan SendTimeout(){
      return _sendTimeout;
   }
   //------------------------------------------------------------
   // <T>设置发送超时。</T>
   MO_INLINE void SetSendTimeout(TTimeSpan sendTimeout){
      _sendTimeout = sendTimeout;
   }
   //------------------------------------------------------------
   // <T>获得接收超时。</T>
   MO_INLINE TTimeSpan ReceiveTimeout(){
      return _receiveTimeout;
   }
   //------------------------------------------------------------
   // <T>设置接收超时。</T>
   MO_INLINE void SetReceiveTimeout(TTimeSpan receiveTimeout){
      _receiveTimeout = receiveTimeout;
   }
public:
   MO_OVERRIDE TResult OnLoadConfig(FXmlNode* pConfig);
   MO_OVERRIDE TResult OnInitialize();
public:
   FNetBufferedSocket* LinkHandle(TUint32 handle);
   FNetBufferedSocket* FindHandle(TUint32 handle);
   FNetBufferedSocket* FindIndex(TUint16 index);
public:
   void OpenSocket(FNetBufferedSocket* pSocket);
   void CloseSocket(FNetBufferedSocket* pSocket);
public:
   MO_OVERRIDE TResult TriggerRefresh(TTimeTick currentTick);
   MO_OVERRIDE TResult StatisticsRefresh();
};
//------------------------------------------------------------
class MO_CR_DECLARE RNetSocketsModule : public RModuleSingleton<FNetBufferedSocketModule>{
};

//============================================================
// <T>共享网络链接管理模块。</T>
//
// @history 100413 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetClientBufferedSocketModule : public FNetBufferedSocketModule{
public:
   FNetClientBufferedSocketModule();
   MO_ABSTRACT ~FNetClientBufferedSocketModule();
};
//------------------------------------------------------------
class MO_CR_DECLARE RNetClientSocketsModule : public RModuleSingleton<FNetClientBufferedSocketModule>{
};

MO_NAMESPACE_END

#endif // __MO_CR_NET_SOCKET_H__
