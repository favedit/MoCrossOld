#ifndef __MO_CR_NET_TRANSFER_H__
#define __MO_CR_NET_TRANSFER_H__

#ifndef __MO_CM_THREAD_H__
#include <MoCmThread.h>
#endif // __MO_CM_THREAD_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

#ifndef __MO_CR_SERVICE_H__
#include "MoCrService.h"
#endif // __MO_CR_SERVICE_H__

#ifndef __MO_CR_NET_SOCKET_H__
#include "MoCrNetSocket.h"
#endif // __MO_CR_NET_SOCKET_H__

#ifndef __MO_CR_NET_PIPE_H__
#include "MoCrNetPipe.h"
#endif // __MO_CR_NET_PIPE_H__

#ifndef __MO_CR_NET_MESSAGE_H__
#include "MoCrNetMessage.h"
#endif // __MO_CR_NET_MESSAGE_H__

#ifdef _MO_WINDOWS
#ifndef __MO_CR_NET_EPOLL_H__
#include "MoCrNetEpoll.h"
#endif // __MO_CR_NET_EPOLL_H__
#endif // _MO_WINDOWS

#ifndef __MO_CR_NET_IOCP_H__
#include "MoCrNetIocp.h"
#endif // __MO_CR_NET_IOCP_H__

//============================================================
#define MO_MD_NTS_STREAM_MODE
#define MO_MD_NTS_SOCKETPOLL             1024
#define MO_MD_NTS_ACCEPT_COUNT           256
#define MO_MD_NTS_ACCEPT_INTERVAL        1000   // 微秒
#define MO_MD_NTS_EVENT_MAXCOUNT         4096
#define MO_MD_NTS_EVENT_TIMEOUT          5      // 毫秒
#define MO_MD_NTS_RECEIVE_INTERVAL_COUNT 1024
#define MO_MD_NTS_RECEIVE_INTERVAL       10     // 微秒
#define MO_MD_NTS_INPUT_INTERVAL_COUNT   4096
#define MO_MD_NTS_INPUT_INTERVAL         10     // 微秒
#define MO_MD_NTS_OUTPUT_INTERVAL_COUNT  4096
#define MO_MD_NTS_OUTPUT_INTERVAL        10     // 微秒
#define MO_MD_NTS_SEND_INTERVAL_COUNT    1024
#define MO_MD_NTS_SEND_INTERVAL          100    // 微秒
#define MO_MD_NTS_NOTIFY_WAKEUP_THREADS  8
#define MO_MD_NTS_NOTIFY_INTERVAL        100    // 微秒
#define MO_MD_NTS_NOTIFY_CPURATE         30     // 线程占用率
#define MO_MD_NTS_TARGET_MAXCOUNT        64
#define MO_MD_NTS_RECEIVE_PIPE_CAPACITY  1024*1024*4

MO_NAMESPACE_BEGIN

//============================================================
class FQueue;
class FSharedNetQueue;
class FNetTransferCommander;
class FNetTransferService;
typedef TFixVector<SNetSocketInfo, MO_MD_NTS_ACCEPT_COUNT> TFsNetSocketInfoVector;

//============================================================
struct SNetSocketNode{
    TDateTime created;
    FNetBufferedSocket* pSocket;
};

//============================================================
// <T>传输消息陷阱扑捉器。</T>
//============================================================
class MO_CR_DECLARE FNetTransferCatcher : public FCatcher{
public:
   FNetTransferCatcher();
public:
   MO_OVERRIDE TBool Install();
};

//============================================================
// <T>共享服务基类。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetMessageService : public FService{
protected:
   TInt _groupId;
   TInt _serverId;
   TFsLabel _label;
   TInt _workerCount;
public:
   FNetMessageService();
   MO_ABSTRACT ~FNetMessageService();
public:
   //------------------------------------------------------------
   // <T>获得分组编号。</T>
   MO_INLINE TInt GroupId(){
      return _groupId;
   }
   //------------------------------------------------------------
   // <T>获得服务编号。</T>
   MO_INLINE TInt ServerId(){
      return _serverId;
   }
   //------------------------------------------------------------
   // <T>获得标签编号。</T>
   MO_INLINE TCharC* Label(){
      return _label;
   }
   //------------------------------------------------------------
   // <T>获得工作器个数。</T>
   MO_INLINE TInt WorkerCount(){
      return _workerCount;
   }
public:
   MO_OVERRIDE TResult LoadConfig(FXmlNode* pConfig);
public:
   MO_ABSTRACT TResult OnUnknownRouter(TNetRouter* pRouter);
public:
   MO_ABSTRACT TResult RegisterAllProcessors();
   MO_ABSTRACT TResult UnregisterAllProcessors();
public:
   MO_ABSTRACT TResult ProcessTransfer(TNetTransfer* pTransfer);
   MO_ABSTRACT TResult DispatchTransfer(TNetTransfer* pTransfer);
};

//============================================================
// <T>网络链接监听线程。</T>
//
// @history 100312 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetTransferServerThread : public FThread{
protected:
   FNetTransferService* _pService;
   SNetHost  _host;
   FNetTransferCatcher* _pCatcher;
   FNetServerSocket* _pServerSocket;
public:
   FNetTransferServerThread();
   MO_ABSTRACT ~FNetTransferServerThread();
public:
   void SetService(FNetTransferService* pService);
   SNetHost& Host();
   void SetHost(const SNetHost& host);
   FNetServerSocket* Socket();
public:
   MO_OVERRIDE TResult Process();
};
//------------------------------------------------------------
typedef MO_CR_DECLARE FVector<FNetTransferServerThread*> FNetTransferServerThreadVector;

//============================================================
// <T>网络数据接收线程。</T>
//
// @history 100312 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetTransferReceiveThread : public FThread{
protected:
   FNetTransferService* _pService;
   FNetTransferCommander* _pCommander;
   INetQueue* _pInputQueue;
   TBool _dataCheck;
   TBool _dataCompress;
   TBool _dataMask;
   TInt64 _receiveTotal;
   TUint64 _click;
   FNetTransferCatcher* _pCatcher;
#ifdef _MO_LINUX
   pid_t _threadId;
#endif
public:
   FNetTransferReceiveThread();
   MO_ABSTRACT ~FNetTransferReceiveThread();
public:
   //------------------------------------------------------------
   // <T>获得接收总数。</T>
   MO_INLINE TInt64 ReceiveTotal(){
      return _receiveTotal;
   }
public:
   void SetService(FNetTransferService* pService);
   void NotifyWake();
#ifdef _MO_LINUX
   pid_t ThreadId();
#endif
public:
   TInt ReceiveMessage(FNetBufferedSocket* pSocket, TByteC* pBuffer, TInt size);
   TInt ReceiveRouter(FNetBufferedSocket* pSocket, TByteC* pBuffer, TInt size);
   TInt ReceiveFirstData(FNetBufferedSocket* pSocket);
   TInt ReceiveData(FNetBufferedSocket* pSocket);
public:
   MO_OVERRIDE TResult Process();
};

//============================================================
// <T>网络数据发送线程。</T>
//
// @history 100312 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetTransferSendThread : public FThread{
protected:
   FNetTransferService* _pService;
   FNetBufferedSocketModule* _pSocketsModule;
   FNetUdpServerSocket* _pUdpServerSocket;
   INetQueue* _pOutputQueue;
   ENetProtocol _protocol;
   TBool _dataCompress;
   TInt64 _sendTotal;
   FNetTransferCatcher* _pCatcher;
#ifdef _MO_LINUX
   pid_t _threadId;
   TUint64 _click;
#endif
public:
   FNetTransferSendThread();
   MO_ABSTRACT ~FNetTransferSendThread();
public:
   //------------------------------------------------------------
   // <T>获得发送总数。</T>
   MO_INLINE TInt64 SendTotal(){
      return _sendTotal;
   }
public:
   void SetService(FNetTransferService* pService);
#ifdef _MO_LINUX
   void NotifyWake();
   void NotifyWait();
   pid_t ThreadId();
#endif // _LINUX
public:
   TInt SendTcpMessage(FNetBufferedSocket* pSocket, TNetMessage* pMessage);
   TInt SendTcpRouter(FNetBufferedSocket* pSocket, TNetRouter* pRouter);
   TInt SendUdpMessage(FNetBufferedSocket* pSocket, TNetMessage* pMessage);
public:
   TInt ProcessTarget(TNetTransfer* pTransfer);
   MO_OVERRIDE TResult Process();
};

//============================================================
enum ENetTransferLinkType{
   ENetTransferLinkType_Creat,   // 创建
   ENetTransferLinkType_Connect, // 连接
};

//============================================================
// <T>网络通讯服务设定。</T>
//
// @history 100309 MAOCY 创建
//============================================================
struct MO_CR_DECLARE STransferServiceConfig{
public:
   TBool multiMode;
   ENetProtocol protocol;
   ENetTransferLinkType linkType;
   TBool dataCheck;
   TBool dataCompress;
   TBool dataMask;
   TShareKey shareKey;
   TChar shareKeyFile[MO_FS_FILENAME_LENGTH];
   SNetHostVector hosts;
   TChar host[64];
   TUint16 port;
   TBool udpSupport;
   TChar udpHost[64];
   TUint16 udpPort;
   TInt limitSocketCount;
   TInt limitClientCount;
   TInt receiveFirstTimeout;
   TInt receiveTimeout;
   TInt sendTimeout;
   TInt receiveThreadCount;
public:
   //------------------------------------------------------------
   STransferServiceConfig(){
      multiMode = EFalse;
      protocol = ENetProtocol_Unknown;
      linkType = ENetTransferLinkType_Creat;
      dataCheck = ETrue;
      dataCompress = ETrue;
      dataMask = ETrue;
      shareKey = 0;
      shareKeyFile[0] = 0;
      host[0] = 0;
      port = 0;
      udpSupport = EFalse;
      udpHost[0] = 0;
      udpPort = 0;
      limitSocketCount = 0;
      receiveFirstTimeout = 0;
      receiveTimeout = 0;
      sendTimeout = 0;
      receiveThreadCount = 0;
   }
};

//============================================================
// <T>网络传输命令处理器。</T>
//============================================================
class MO_CR_DECLARE FNetTransferCommander : public FObject{
protected:
   FNetTransferService* _pService;
public:
   FNetTransferCommander();
   MO_ABSTRACT ~FNetTransferCommander();
public:
   //------------------------------------------------------------
   // <T>获得线网络传输服务。</T>
   MO_INLINE FNetTransferService* Service(){
      return _pService;
   }
   //------------------------------------------------------------
   // <T>设置线网络传输服务。</T>
   MO_INLINE void SetService(FNetTransferService* pService){
      _pService = pService;
   }
public:
   MO_VIRTUAL TBool SendLimitNotify(SNetSocketInfo& info) = 0;
   MO_VIRTUAL TBool SendConnectNotify(FNetBufferedSocket* pSocket, TCharC* pHost, TUint16 port) = 0;
   MO_VIRTUAL TBool SendDisconnectNotify(FNetBufferedSocket* pSocket, TCharC* pHost, TUint16 port) = 0;
   MO_VIRTUAL TBool SendInvalidUnknownNotify(FNetBufferedSocket* pSocket) = 0;
   MO_VIRTUAL TBool SendInvalidVersionNotify(FNetBufferedSocket* pSocket) = 0;
   MO_VIRTUAL TBool SendInvalidSerialNotify(FNetBufferedSocket* pSocket) = 0;
   MO_VIRTUAL TBool SendInvalidUnserialNotify(FNetBufferedSocket* pSocket) = 0;
   MO_VIRTUAL TBool SendInvalidFullNotify(FNetBufferedSocket* pSocket) = 0;
public:
   MO_VIRTUAL TBool ProcessTransfer(TNetTransfer* pTransfer) = 0;
};

//============================================================
// <T>网络传输服务</T>
//
// @history 100312 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetTransferService :
      public FNetMessageService,
      public IStatisticsTrigger{
protected:
   TThreadSection _section;
   STransferServiceConfig _config;
   INetSocketPool* _pReceivePool;
   INetSocketPool* _pSendPool;
   FNetBufferedSocketModule* _pSocketsModule;
   FSharedNetQueueStorage* _pQueueStorage;
   INetQueue* _pInputQueue;
   FNetTransferServerThreadVector* _pServerThreads;
   FNetTransferReceiveThread* _pReceiveThread;
   FNetTransferSendThread* _pSendThread;
   FThreadGroup* _pThreadGroup;
   TInt _sendSocketCount;
   FQueue* _pReceiveNodeQueue;
   TInt _currentReceiveIndex;
   FNetTransferCommander* _pCommander;
public:
   FNetTransferService();
   MO_ABSTRACT ~FNetTransferService();
public:
   //------------------------------------------------------------
   // <T>获得线程同步锁。</T>
   MO_INLINE TThreadSection& Section(){
      return _section;
   }
   //------------------------------------------------------------
   // <T>获得服务设置信息。</T>
   MO_INLINE STransferServiceConfig& Config(){
      return _config;
   }
   //------------------------------------------------------------
   // <T>获得端口存储模块。</T>
   MO_INLINE FNetBufferedSocketModule* SocketsModule(){
      return _pSocketsModule;
   }
   //------------------------------------------------------------
   // <T>获得队列存储器。</T>
   MO_INLINE FSharedNetQueueStorage* QueueStorage(){
      return _pQueueStorage;
   }
   //------------------------------------------------------------
   // <T>获得传输服务线程集合。</T>
   MO_INLINE FNetTransferServerThreadVector* ServerThreads(){
      return _pServerThreads;
   };
   //------------------------------------------------------------
   // <T>获得传输接收线程。</T>
   MO_INLINE FNetTransferReceiveThread* ReceiveThread(){
      return _pReceiveThread;
   }
   //------------------------------------------------------------
   // <T>获得传输发送线程。</T>
   MO_INLINE FNetTransferSendThread* SendThread(){
      return _pSendThread;
   }
   //------------------------------------------------------------
   // <T>获得命令处理器。</T>
   MO_INLINE FNetTransferCommander* Commander(){
      return _pCommander;
   }
   //------------------------------------------------------------
   // <T>设置命令处理器。</T>
   MO_INLINE void SetCommander(FNetTransferCommander* pCommander){
      _pCommander = pCommander;
      _pCommander->SetService(this);
   }
public:
   MO_OVERRIDE TResult LoadConfig(FXmlNode* pConfig);
protected:
   MO_ABSTRACT FNetBufferedSocket* InnerAddSocket(SNetSocketInfo& info);
public:
   MO_ABSTRACT FNetBufferedSocket* AddSocket(SNetSocketInfo& info);
   MO_ABSTRACT TBool AddSockets(TFsNetSocketInfoVector& infos);
   TBool CloseSocketWithNotify(FNetBufferedSocket* pSocket);
   TBool CloseSocketWithoutNotify(FNetBufferedSocket* pSocket);
   INetSocketPool* SocketPool();
   INetSocketPool* SendPool();
   FQueue* ReceiveNodeQueue();
public:
   void SetSocketsModule(FNetBufferedSocketModule* pModule);
   void SetQueueStorage(FSharedNetQueueStorage* pStorage);
public:
   MO_OVERRIDE TResult OnStartup();
   MO_OVERRIDE TResult OnShutdown();
public:
   TBool SendSocketRouter(FNetBufferedSocket* pSocket, TNetRouter* pRouter);
   TBool SendSocketTransfer(FNetBufferedSocket* pSocket, TNetTransfer* pTransfer);
public:
   MO_OVERRIDE TResult ProcessTransfer(TNetTransfer* pTransfer);
public:
   TBool Wait();
public:
   MO_OVERRIDE TResult StatisticsRefresh();
};

MO_NAMESPACE_END

#endif // __MO_CR_NET_TRANSFER_H__
