#ifndef __MO_CR_MESSAGE_H__
#define __MO_CR_MESSAGE_H__

#ifndef __MO_CM_SHARED_H__
#include <MoCmShared.h>
#endif // __MO_CM_SHARED_H__

#ifndef __MO_CM_SYSTEM_H__
#include <MoCmSystem.h>
#endif // __MO_CM_SYSTEM_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

#ifndef __MO_CR_NET_MESSAGE_H__
#include "MoCrNetMessage.h"
#endif // __MO_CR_NET_MESSAGE_H__

#ifndef __MO_CR_NET_LINKER_H__
#include "MoCrNetLinker.h"
#endif // __MO_CR_NET_LINKER_H__

#ifndef __MO_CR_NET_CONNECTION_H__
#include "MoCrNetConnection.h"
#endif // __MO_CR_NET_CONNECTION_H__

#define MO_ARG_HOME             "-home"
#define MO_ARG_CONFIG           "-config"
#define MO_ARG_LOGGER           "-logger"
#define MO_ARG_MEMORY           "-memory"
#define MO_ARG_MEMORY_CREATE    "create"
#define MO_ARG_MEMORY_CONNECT   "connect"
#define MO_LOGGER_DEBUG         "debug"
#define MO_LOGGER_INFO          "info"
#define MO_LOGGER_ERROR         "error"
#define MO_LOGGER_WARN          "warn"
#define MO_LOGGER_FATAL         "fatal"
#define MO_MD_SERVER_SHAREDCODE 80

MO_NAMESPACE_BEGIN

//============================================================
template class MO_CR_DECLARE TFsNetString<MO_FS_LABEL_LENGTH>;

//============================================================
// <T>网络链接结构结构定义。</T>
//
// @struct
//============================================================
struct MO_CR_DECLARE SNetSocketClient{
public:
   // @property 编号
   TInt32 id;
   // @property 查询标志
   TBool queried;
   // @property 链接标志
   TBool connected;
   // @property 注册标志
   TBool registered;
   // @property 主机名称
   TFsNetString<MO_FS_LABEL_LENGTH> name;
   // @property 主机地址
   TFsNetString<MO_FS_LABEL_LENGTH> host;
   // @property 端口
   TUint16 port;
   // @property 远程主机地址
   TFsNetString<MO_FS_LABEL_LENGTH> remoteHost;
   // @property 远程端口
   TUint16 remotePort;
   // @property 网络地址（网关地址）
   SNetTarget target;
   // @property 端口地址
   SNetSocketTarget socketTarget;
   // @property 广播地址
   TFsNetString<MO_FS_LABEL_LENGTH> udpHost;
   // @property 广播端口
   TUint16 udpPort;
   // @property 广播网络目标
   SNetTarget udpTarget;
};

//============================================================
class MO_CR_DECLARE FSharedNetSocketClient :
      public FBase,
      public MShared,
      public MInstancedPoolObject,
      public MNetSocketLinker{
public:
   SNetSocketClient* _gClient;
public:
   FSharedNetSocketClient();
   MO_ABSTRACT ~FSharedNetSocketClient();
public:
   static TSize CalculateCapacity();
   MO_OVERRIDE void OnSharedInitialize();
   MO_OVERRIDE void OnSharedLink(TShareSegment& segment);
   MO_OVERRIDE TSize SharedCapacity();
public:
   //------------------------------------------------------------
   MO_INLINE SNetSocketClient* NetClient(){
      return _gClient;
   }
   //------------------------------------------------------------
   MO_INLINE TInt32 NetId(){
      return _gClient->id;
   }
   //------------------------------------------------------------
   MO_INLINE void SetNetId(TInt32 id){
      _gClient->id = id;
   }
   //------------------------------------------------------------
   MO_INLINE TBool IsNetQueried(){
      return _gClient->queried;
   }
   //------------------------------------------------------------
   MO_INLINE void SetNetQueried(TBool queried){
      _gClient->queried = queried;
   }
   //------------------------------------------------------------
   MO_INLINE TBool IsNetConnected(){
      return _gClient->connected;
   }
   //------------------------------------------------------------
   MO_INLINE void SetNetConnected(TBool connected){
      _gClient->connected = connected;
   }
   //------------------------------------------------------------
   MO_INLINE TBool IsNetRegistered(){
      return _gClient->registered;
   }
   //------------------------------------------------------------
   MO_INLINE void SetNetRegistered(TBool registered){
      _gClient->registered = registered;
   }
   //------------------------------------------------------------
   MO_INLINE TCharC* NetName(){
      return _gClient->name;
   }
   //------------------------------------------------------------
   MO_INLINE void SetNetName(TCharC* pName){
      _gClient->name = pName;
   }
   //------------------------------------------------------------
   MO_INLINE TCharC* NetHost(){
      return _gClient->host;
   }
   //------------------------------------------------------------
   MO_INLINE void SetNetHost(TCharC* pHost){
      _gClient->host.Set(pHost);
   }
   //------------------------------------------------------------
   MO_INLINE TUint NetPort(){
      return _gClient->port;
   }
   //------------------------------------------------------------
   MO_INLINE void SetNetPort(TUint port){
      _gClient->port = (TUint16)port;
   }
   //------------------------------------------------------------
   MO_INLINE TCharC* NetRemoteHost(){
      return _gClient->remoteHost;
   }
   //------------------------------------------------------------
   MO_INLINE void SetNetRemoteHost(TCharC* pRemoteHost){
      _gClient->remoteHost.Set(pRemoteHost);
   }
   //------------------------------------------------------------
   MO_INLINE TUint NetRemotePort(){
      return _gClient->remotePort;
   }
   //------------------------------------------------------------
   MO_INLINE void SetNetRemotePort(TUint remotePort){
      _gClient->remotePort = (TUint16)remotePort;
   }
   //------------------------------------------------------------
   MO_INLINE SNetTarget& NetTarget(){
      return _gClient->target;
   }
   //------------------------------------------------------------
   MO_INLINE void SetNetTarget(const SNetTarget& target){
      _gClient->target = target;
   }
   //------------------------------------------------------------
   MO_INLINE SNetSocketTarget& NetSocketTarget(){
      return _gClient->socketTarget;
   }
   //------------------------------------------------------------
   MO_INLINE void SetNetSocketTarget(const SNetSocketTarget& target){
      _gClient->socketTarget = target;
   }
   //------------------------------------------------------------
   MO_INLINE SNetTarget& NetUdpTarget(){
      return _gClient->udpTarget;
   }
   //------------------------------------------------------------
   MO_INLINE TUint NetUdpPort(){
      return _gClient->udpPort;
   }
   //------------------------------------------------------------
   MO_INLINE void SetNetUdpPort(TUint udpPort){
      _gClient->udpPort = (TUint16)udpPort;
      _gClient->udpTarget.Reset();
      // _gClient->udpTarget.SetObjectIndex(udpPort);
   }
public:
   MO_OVERRIDE TBool OnSocketConnect();
   MO_OVERRIDE TBool OnSocketDisconnect();
public:
   MO_ABSTRACT void Reset();
public:
   MO_ABSTRACT void LinkTransfer(TNetTransfer* pTransfer);
   void Unlink();
   TCharC* DumpSocket(TChar* pDump, TSize capacity);
};

//============================================================
// <T>共享客户端模块。</T>
//============================================================
template <typename T>
class FSharedClientModule : public FSharedModule{
protected:
   T* _pItem;
public:
   //------------------------------------------------------------
   FSharedClientModule(){
      _pItem = MO_CREATE(T);
   }
   //------------------------------------------------------------
   MO_ABSTRACT ~FSharedClientModule(){
      MO_DELETE(_pItem);
   }
public:
   //------------------------------------------------------------
   // <T>获得对象。</T>
   T* Get(){
      return _pItem;
   }
   //------------------------------------------------------------
   MO_ABSTRACT T* LinkTransfer(TNetTransfer* pTransfer){
      MO_ASSERT(pTransfer);
      // 获得链接对应的网关服务器
      SNetSocketLinker* pLinker = RNetSocketLinkerModule::Instance().LinkTransfer(pTransfer);
      if(!pLinker->HasLinker()){
         // 收集用户
         _pItem->LinkTransfer(pTransfer);
         pLinker->Link(_pItem);
      }
      return _pItem;
   }
public:
   //------------------------------------------------------------
   // <T>输出共享内存分配信息。</T>
   void DumpShared(){
      TChar format[MO_MEMORY_FORMATLENGTH];
      TInt capacity = T::CalculateCapacity();
      TInt total = capacity;
      MO_INFO(MO_DUMP_SHARED_FMT "%4d + %4d",
            (TCharC*)this->_name,
            RInt::FormatCapacity(total, format, MO_MEMORY_FORMATLENGTH),
            0, capacity);
   }
};

//============================================================
// <T>共享客户端集合模块。</T>
//============================================================
template <typename T>
class FSharedClientCollectionModule :
      public FSharedModule,
      public MAllocatorPool<T>{
protected:
   TInt _capacity;
public:
   //------------------------------------------------------------
   FSharedClientCollectionModule(){
      _capacity = 0;
   }
   //------------------------------------------------------------
   MO_ABSTRACT ~FSharedClientCollectionModule(){
      MAllocatorPool<T>::InnerReleaseAll();
   }
public:
   //------------------------------------------------------------
   // <T>获得对象的容量。</T>
   TInt Capacity(){
      return _capacity;
   }
public:
   //------------------------------------------------------------
   // <T>根据索引位置，获得对象。</T>
   MO_ABSTRACT T FindByIndex(TInt index){
      return this->_pStorage->Nvl(index, NULL);
   }
   //------------------------------------------------------------
   // <T>根据链接端口，查找对象。</T>
   MO_ABSTRACT T FindByPort(TInt port){
      SNetSocketLinker* pLinker = RNetSocketLinkerModule::Instance().Get(port);
      if(!pLinker->IsConnected()){
         return NULL;
      }
      return (T)pLinker->Get();
   }
   //------------------------------------------------------------
   // <T>根据消息对象，查找对象。</T>
   MO_ABSTRACT T FindByRouter(TNetRouter* pRouter){
      MO_ASSERT(pRouter);
      TUint16 index = pRouter->RouterHead().Origin().ObjectIndex();
      SNetSocketLinker* pLinker = RNetSocketLinkerModule::Instance().Get(index);
      if(!pLinker->IsConnected()){
         return NULL;
      }
      return (T)pLinker->Get();
   }
public:
   //------------------------------------------------------------
   // <T>释放对象。</T>
   MO_ABSTRACT T Alloc(){
      return this->InnerAlloc();
   }
   //------------------------------------------------------------
   // <T>释放对象。</T>
   MO_ABSTRACT T Alloc(TInt index){
      return this->InnerAlloc(index);
   }
   //------------------------------------------------------------
   // <T>释放对象。</T>
   MO_ABSTRACT TBool Free(T pItem){
      MO_ASSERT(pItem);
      // 重置数据
      pItem->Reset();
      // 释放对象
      this->InnerFree(pItem);
      return ETrue;
   }
public:
   //------------------------------------------------------------
   MO_ABSTRACT T LinkRouter(TNetRouter* pRouter){
      MO_ASSERT(pRouter);
      TUint index = pRouter->RouterHead().Origin().ObjectIndex();
      // 获得链接对应的网络链接
      SNetSocketLinker* pLinker = RNetSocketLinkerModule::Instance().Get(index);
      if(!pLinker->HasLinker()){
         // 收集对象
         //T pItem = Alloc(index);
         //pItem->LinkRouter(pRouter);
         // 关联对象
         //pLinker->Link(pItem);
      }
      return (T)pLinker->Get();
   }
   //------------------------------------------------------------
   MO_ABSTRACT T LinkTransfer(TNetTransfer* pTransfer){
      MO_ASSERT(pTransfer);
      TUint index = pTransfer->TransferHead().Socket().Index();
      // 获得链接对应的网络链接
      SNetSocketLinker* pLinker = RNetSocketLinkerModule::Instance().Get(index);
      if(!pLinker->HasLinker()){
         // 收集对象
         T pItem = Alloc();
         pItem->LinkTransfer(pTransfer);
         // 关联对象
         pLinker->Link(pItem);
      }
      return (T)pLinker->Get();
   }
   //------------------------------------------------------------
   MO_ABSTRACT T LinkTransfer(TNetTransfer* pTransfer, TInt index){
      MO_ASSERT(pTransfer);
      TUint port = pTransfer->RouterHead().SessionId();
      // 获得链接对应的网络链接
      SNetSocketLinker* pLinker = RNetSocketLinkerModule::Instance().Get(port);
      if(!pLinker->HasLinker()){
         // 收集对象
         T pItem = Alloc(index);
         pItem->LinkTransfer(pTransfer);
         // 关联对象
         pLinker->Link(pItem);
      }
      return (T)pLinker->Get();
   }
   //------------------------------------------------------------
   MO_ABSTRACT TBool Link(T pItem){
      MO_ASSERT(pItem);
      return ETrue;
   }
   //------------------------------------------------------------
   MO_ABSTRACT TBool Unlink(T pItem){
      MO_ASSERT(pItem);
      // 断开网络链接
      TUint port = pItem->NetPort();
      SNetSocketLinker* pLinker = RNetSocketLinkerModule::Instance().Get(port);
      MO_ASSERT(pLinker->Get() == pItem);
      return pLinker->Unlink();
   }
};

//============================================================
// <T>服务逻辑基类。</T>
//
// @history 100311 MAOCY 创建
//============================================================
class MO_CR_DECLARE FMessageLogic : public FConsole{
public:
   FNetTransferProcessor* _pProcessor;
   FNetMessageStatisticsMachine* _pStatisticsMachine;
public:
   FMessageLogic();
   MO_ABSTRACT ~FMessageLogic();
public:
   //------------------------------------------------------------
   // <T>获得消息处理机。</T>
   MO_INLINE FNetTransferProcessor* TransferProcessor(){
      return _pProcessor;
   }
   //------------------------------------------------------------
   // <T>获得消息统计机。</T>
   MO_INLINE FNetMessageStatisticsMachine* StatisticsMachine(){
      if(NULL == _pStatisticsMachine){
         _pStatisticsMachine = MO_CREATE(FNetMessageStatisticsMachine);
         _pStatisticsMachine->SetName(_name);
      }
      return _pStatisticsMachine;
   }
public:
   TBool OnUnknownRouter(TNetRouter* pRouter);
public:
   //------------------------------------------------------------
   // <T>注册一个路由处理。</T>
   template <typename T>
   TBool RegisterRouter(T* pOwner, TInt code, TInt command, TCharC* pInvokeName, TBool (T::*pProcesser)(TNetRouter* pRouter)){
      return _pProcessor->RegisterRouter<T>(pOwner, code, command, pInvokeName, pProcesser);
   }
   //------------------------------------------------------------
   // <T>注册一个路由处理。</T>
   template <typename T>
   TBool RegisterRouter(T* pOwner, TInt code, TInt command, TCharC* pInvokeName, TBool (T::*pProcesser)(FObject*, TNetRouter* pRouter)){
      return _pProcessor->RegisterRouter<T>(pOwner, code, command, pInvokeName, pProcesser);
   }
   //------------------------------------------------------------
   // <T>注册一个传输处理。</T>
   template <typename T>
   TBool RegisterTransfer(T* pOwner, TInt code, TInt command, TCharC* pInvokeName, TBool (T::*pProcesser)(TNetTransfer* pTransfer)){
      return _pProcessor->RegisterTransfer<T>(pOwner, code, command, pInvokeName, pProcesser);
   }
   //------------------------------------------------------------
   // <T>注册一个传输处理。</T>
   template <typename T>
   TBool RegisterTransfer(T* pOwner, TInt code, TInt command, TCharC* pInvokeName, TBool (T::*pProcesser)(FObject*, TNetTransfer* pTransfer)){
      return _pProcessor->RegisterTransfer<T>(pOwner, code, command, pInvokeName, pProcesser);
   }
public:
   //------------------------------------------------------------
   // <T>注册一个路由处理到全部监听。</T>
   template <typename T>
   TBool RegisterRouterAll(T* pOwner, TInt command, TCharC* pInvokeName, TBool (T::*pProcesser)(TNetRouter* pRouter)){
      return _pProcessor->RegisterRouterAll<T>(pOwner, command, pInvokeName, pProcesser);
   }
   //------------------------------------------------------------
   // <T>注册一个路由处理到全部监听。</T>
   template <typename T>
   TBool RegisterRouterAll(T* pOwner, TInt command, TCharC* pInvokeName, TBool (T::*pProcesser)(FObject*, TNetRouter* pRouter)){
      return _pProcessor->RegisterRouterAll<T>(pOwner, command, pInvokeName, pProcesser);
   }
   //------------------------------------------------------------
   // <T>注册一个传输处理到全部监听。</T>
   template <typename T>
   TBool RegisterTransferAll(T* pOwner, TInt command, TCharC* pInvokeName, TBool (T::*pProcesser)(TNetTransfer* pTransfer)){
      return _pProcessor->RegisterTransferAll<T>(pOwner, command, pInvokeName, pProcesser);
   }
   //------------------------------------------------------------
   // <T>注册一个传输处理到全部监听。</T>
   template <typename T>
   TBool RegisterTransferAll(T* pOwner, TInt command, TCharC* pInvokeName, TBool (T::*pProcesser)(FObject*, TNetTransfer* pTransfer)){
      return _pProcessor->RegisterTransferAll<T>(pOwner, command, pInvokeName, pProcesser);
   }
public:
   MO_ABSTRACT TBool RegisterAllProcessors();
   MO_ABSTRACT TBool UnregisterAllProcessors();
public:
   MO_ABSTRACT TBool ProcessTransfer(TNetTransfer* pTransfer);
   MO_ABSTRACT TBool DispatchTransfer(TNetTransfer* pTransfer);
};

//============================================================
// <T>打断监听器。</T>
//============================================================
class MO_CR_DECLARE FInterruptListener : public FListener{
public:
   MO_OVERRIDE TBool Process();
};

MO_NAMESPACE_END

#endif // __MO_CR_MESSAGE_H__
