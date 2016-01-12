#ifdef _MO_LINUX
#include <unistd.h>
#endif
#include <MoCmFormat.h>
#include "MoCrNetEpoll.h"
#include "MoCrNetPipe.h"
#include "MoCrNetTransfer.h"

#define MO_TAG_PROPERTY  TC("Property")
#define MO_PTR_NAME      TC("name")
#define MO_VAL_SHARE_KEY TC("share_key")

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络通讯服务的实例。</T>
//
// @return 网络通讯服务的实例Clo
//============================================================s
FNetTransferService::FNetTransferService(){
   _name = TC("Service.Transfer");
   // 设置协议
   _config.protocol = ENetProtocol_Router;
#ifdef _MO_WINDOWS
   // 创建接收完成端口缓冲池
   FNetIocp* pEpoll = MO_CREATE(FNetIocp);
   pEpoll->Setup();
   _pReceivePool = pEpoll;
   MO_CLEAR(_pSendPool);
#endif
#ifdef _MO_LINUX
   // 创建接收完成端口缓冲池
   FNetEpoll* pEpoll = MO_CREATE(FNetEpoll);
   pEpoll->Create(MO_MD_NTS_SOCKETPOLL);
   _pReceivePool = pEpoll;
   // 创建发送完成端口缓冲池
   FNetEpoll* pSendEpoll = MO_CREATE(FNetEpoll);
   pSendEpoll->Create(MO_MD_NTS_SOCKETPOLL);
   _pSendPool = pSendEpoll;
#endif
   // 初始化数据
   _pServerThreads = MO_CREATE(FNetTransferServerThreadVector);
   MO_CLEAR(_pSocketsModule);
   MO_CLEAR(_pQueueStorage);
   MO_CLEAR(_pInputQueue);
   // 初始化线程
   MO_CLEAR(_pReceiveThread);
   MO_CLEAR(_pSendThread);
   _pReceiveNodeQueue = MO_CREATE(FQueue, MO_MD_NTS_RECEIVE_PIPE_CAPACITY);
   _pThreadGroup = MO_CREATE(FThreadGroup);
   _currentReceiveIndex = 0;
}

//============================================================
// <T>析构网络通讯服务的实例。</T>
//============================================================
FNetTransferService::~FNetTransferService(){
   MO_DELETE(_pReceivePool);
   MO_DELETE(_pSendPool);
   MO_DELETE(_pServerThreads);
   MO_DELETE(_pReceiveNodeQueue);
   MO_DELETE(_pThreadGroup);
}

//============================================================
// <T>加载配置信息。</T>
//
// @param pConfig 配置信息
// @return 处理结果
//============================================================
TResult FNetTransferService::LoadConfig(FXmlNode* pConfig){
   TXmlNodeIteratorC iterator = pConfig->NodeIteratorC();
   while(iterator.Next()){
      FXmlNode* pNode = *iterator;
      if(pNode->IsName(TC("Property"))){
         // 读取属性
         if(pNode->IsAttribute(MO_PTR_NAME, TC("mode"))){
            // 读取工作方式
            TCharC* pMode = pNode->Text();
            if(RString::Equals(TC("multipool"), pMode)){
               MO_DEBUG(TC("Load module(%s) property. (mode=%s)"), (TCharC*)_name, TC("multipool"));
               _config.multiMode = ETrue;
            }
         }else if(pNode->IsAttribute(MO_PTR_NAME, TC("protocol"))){
            // 读取工作方式
            TCharC* pProtocol = pNode->Text();
            if(RString::Equals(TC("message"), pProtocol)){
               MO_DEBUG(TC("Load module(%s) property. (protocol=%s)"), (TCharC*)_name, TC("message"));
               _config.protocol = ENetProtocol_Message;
            }else if(RString::Equals(TC("router"), pProtocol)){
               MO_DEBUG(TC("Load module(%s) property. (protocol=%s)"), (TCharC*)_name, TC("router"));
               _config.protocol = ENetProtocol_Router;
            }
         }else if(pNode->IsAttribute(MO_PTR_NAME, TC("link_type"))){
            // 读取共享锁关联方式
            TCharC* pLinkType = pNode->Text();
            if(RString::Equals(TC("create"), pLinkType)){
               MO_DEBUG(TC("Load module(%s) property. (link_type=%s)"), (TCharC*)_name, TC("create"));
            }else if(RString::Equals(TC("connect"), pLinkType)){
               MO_DEBUG(TC("Load module(%s) property. (link_type=%s)"), (TCharC*)_name, TC("connect"));
            }
         }else if(pNode->IsAttribute(MO_PTR_NAME, TC("data_check"))){
            // 读取传输检查方式
            _config.dataCheck = pNode->TextAsBool();
            MO_DEBUG(TC("Load module(%s) property. (data_check=%d)"), (TCharC*)_name, _config.dataCheck);
         }else if(pNode->IsAttribute(MO_PTR_NAME, TC("data_compress"))){
            // 读取传输压缩方式
            _config.dataCompress = pNode->TextAsBool();
            MO_DEBUG(TC("Load module(%s) property. (data_compress=%d)"), (TCharC*)_name, _config.dataCompress);
         }else if(pNode->IsAttribute(MO_PTR_NAME, TC("data_mask"))){
            // 读取传输加密方式
            _config.dataMask = pNode->TextAsBool();
            MO_DEBUG(TC("Load module(%s) property. (data_mask=%d)"), (TCharC*)_name, _config.dataMask);
         }else if(pNode->IsAttribute(MO_PTR_NAME, MO_VAL_SHARE_KEY)){
            // 读取共享锁文件路径
            pNode->GetText(_config.shareKeyFile, MO_FS_FILENAME_LENGTH);
            MO_DEBUG(TC("Load module(%s) property. (share_key=%s)"), (TCharC*)_name, _config.shareKeyFile);
         }else if(pNode->IsAttribute(MO_PTR_NAME, TC("udp_support"))){
            // 读取是否支持广播
            _config.udpSupport = RBool::IsTrue(pNode->Text());
            MO_DEBUG(TC("Load module(%s) property. (udp_support=%d)"), (TCharC*)_name, _config.udpSupport);
         }else if(pNode->IsAttribute(MO_PTR_NAME, TC("udp_host"))){
            // 读取广播主机名称
            pNode->GetText(_config.udpHost, sizeof(_config.udpHost));
            MO_DEBUG(TC("Load module(%s) property. (udp_host=%s)"), (TCharC*)_name, _config.udpHost);
         }else if(pNode->IsAttribute(MO_PTR_NAME, TC("udp_port"))){
            // 读取广播主机端口
            _config.udpPort = (TUint16)pNode->TextAsInt();
            MO_DEBUG(TC("Load module(%s) property. (udp_port=%d)"), (TCharC*)_name, _config.udpPort);
         }else if(pNode->IsAttribute(MO_PTR_NAME, TC("limit_socket_count"))){
            // 读取网络链接限制
            _config.limitSocketCount = pNode->TextAsInt();
            MO_DEBUG(TC("Load module(%s) property. (limit_socket_count=%d)"), (TCharC*)_name, _config.limitSocketCount);
         }else if(pNode->IsAttribute(MO_PTR_NAME, TC("limit_client_count"))){
            // 读取客户端数量限制
            _config.limitClientCount = pNode->TextAsInt();
            MO_DEBUG(TC("Load module(%s) property. (limit_socket_count=%d)"), (TCharC*)_name, _config.limitSocketCount);
         }else if(pNode->IsAttribute(MO_PTR_NAME, TC("receive_first_timeout"))){
            // 读取网络首次接收超时
            _config.receiveFirstTimeout = pNode->TextAsInt() * 1000;
            MO_DEBUG(TC("Load module(%s) property. (receive_first_timeout=%d)"), (TCharC*)_name, _config.receiveFirstTimeout);
         }else if(pNode->IsAttribute(MO_PTR_NAME, TC("receive_timeout"))){
            // 读取网络接收超时
            _config.receiveTimeout = pNode->TextAsInt() * 1000;
            MO_DEBUG(TC("Load module(%s) property. (receive_timeout=%d)"), (TCharC*)_name, _config.receiveTimeout);
         }else if(pNode->IsAttribute(MO_PTR_NAME, TC("send_timeout"))){
            // 读取网络发送超时
            _config.sendTimeout = pNode->TextAsInt() * 1000;
            MO_DEBUG(TC("Load module(%s) property. (send_timeout=%d)"), (TCharC*)_name, _config.sendTimeout);
         }else if(pNode->IsAttribute(MO_PTR_NAME, TC("receive_thread_count"))){
            // 读取接收线程总数
            _config.receiveThreadCount = pNode->TextAsInt();
            MO_DEBUG(TC("Load module(%s) property. (receive_thread_count=%d)"), (TCharC*)_name, _config.receiveThreadCount);
         }
      }else if(pNode->IsName(TC("Host"))){
         // 读取网络连接设置
         SNetHost host;
         host.host = pNode->Get(TC("host"));
         host.port = (TUint16)pNode->GetAsInt(TC("port"));
         _config.hosts.Push(host);
         MO_DEBUG(TC("Load module(%s) property. (host=%s, port=%d)"), (TCharC*)_name, (TCharC*)host.host, host.port);
      }
   }
   // 设置关联数据
   if(_pSocketsModule != NULL){
      _pSocketsModule->SetSendTimeout(_config.sendTimeout);
      _pSocketsModule->SetReceiveTimeout(_config.receiveTimeout);
   }
   return ESuccess;
}

//============================================================
INetSocketPool* FNetTransferService::SocketPool(){
   return _pReceivePool;
}

//============================================================
INetSocketPool* FNetTransferService::SendPool(){
   return _pSendPool;
}

//============================================================
FQueue* FNetTransferService::ReceiveNodeQueue(){
    return _pReceiveNodeQueue;
}

//============================================================
void FNetTransferService::SetSocketsModule(FNetBufferedSocketModule* pStorage){
   MO_ASSERT(pStorage);
   _pSocketsModule = pStorage;
}

//============================================================
void FNetTransferService::SetQueueStorage(FSharedNetQueueStorage* pStorage){
   MO_ASSERT(pStorage);
   _pQueueStorage = pStorage;
   _pInputQueue = pStorage->Connection()->InputQueue();
}

//============================================================
// <T>启动网络链接数据传输。</T>
//============================================================
TResult FNetTransferService::OnStartup(){
   // 创建链接监听线程
   TInt serverCount = _config.hosts.Count();
   for(TInt n = 0; n < serverCount; n++){
      // 创建服务线程
      FNetTransferServerThread* pServerThread = MO_CREATE(FNetTransferServerThread);
      pServerThread->SetService(this);
      pServerThread->SetHost(_config.hosts[n]);
      _pServerThreads->Push(pServerThread);
      // 放入线程组
      _pThreadGroup->Push(pServerThread);
   }
   // 创建链接数据等待线程
   _pReceiveThread = MO_CREATE(FNetTransferReceiveThread);
   _pReceiveThread->SetService(this);
   _pThreadGroup->Push(_pReceiveThread);
   // 创建链接数据发送线程
   _pSendThread = MO_CREATE(FNetTransferSendThread);
   _pSendThread->SetService(this);
   _pThreadGroup->Push(_pSendThread);
   // 启动线程组
   _pThreadGroup->Start();
   return ESuccess;
}

//============================================================
// <T>停止网络链接数据传输。</T>
//============================================================
TResult FNetTransferService::OnShutdown(){
   // 关闭线程组
   _pThreadGroup->Stop();
   // 关闭监听
   TInt count = _pServerThreads->Count();
   for(TInt n = 0; n < count; n++){
      FNetTransferServerThread* pServerThread = _pServerThreads->Get(n);
      pServerThread->Socket()->Disconnect();
   }
   return ESuccess;
}

//============================================================
// <T>增加一个网络链接。</T>
// <P>网络链接有效内容：
//     - handle: 句柄
//     - ip:     地址
//     - port:   端口
//     - host:   主机
//     - index:  索引
//     - serial: 序列
// </P>
//
// @param info 网络链接信息
//============================================================
FNetBufferedSocket* FNetTransferService::InnerAddSocket(SNetSocketInfo& info){
   TInt limitSocketCount = _config.limitSocketCount;
   // 检查限制
   if(limitSocketCount > 0){
      TInt socketCount = _pSocketsModule->Pool()->UsingCount();
      if(socketCount + 1 > limitSocketCount){
         MO_WARN(TC("Socket pool is full. (count=%d, limit=%d)"), socketCount, limitSocketCount);
         _pCommander->SendLimitNotify(info);
         return NULL;
      }
   }
   // 链接端口
   FNetBufferedSocket* pSocket = _pSocketsModule->LinkHandle(info.handle);
   // 获得链接对象失败，关闭句柄，拒绝链接
   if(NULL == pSocket){
      RNetSocket::Disconnect(info);
      return NULL;
   }
   // 设置信息
   pSocket->SetInfo(&info);
   pSocket->SetNonBlock();
   pSocket->SetSendBufferSize(0);
   // 增加到共享端口
   pSocket->SetReceivePool(_pReceivePool);
   pSocket->SetSendPool(_pSendPool);
   // 关联链接
   _pSocketsModule->OpenSocket(pSocket);
   MO_DEBUG_INFO(TC("Add net socket. (socket=0x%08X, poll=0x%04X(%d)|0x%04X(%d), host=%s:%d, handle=%d, index=%d:%d)"),
         pSocket,
         pSocket->ReceivePool()->Handle(), pSocket->ReceivePool()->Count(),
         pSocket->SendPool()->Handle(), pSocket->SendPool()->Count(),
         info.host, info.port, info.handle, pSocket->Index(), pSocket->Serial());
   // 发送端口链接消息
   _pCommander->SendConnectNotify(pSocket, info.host, info.port);
   return pSocket;
}

//============================================================
// <T>增加一个网络链接。</T>
//
// @param info 网络链接信息
//============================================================
FNetBufferedSocket* FNetTransferService::AddSocket(SNetSocketInfo& info){
   _section.Enter();
   FNetBufferedSocket* pSocket = InnerAddSocket(info);
   _section.Leave();
   return pSocket;
}

//============================================================
// <T>增加多个网络链接。</T>
//
// @param infos 网络链接集合
//============================================================
TBool FNetTransferService::AddSockets(TFsNetSocketInfoVector& infos){
   TBool result = ETrue;
   _section.Enter();
   TInt count = infos.Count();
   for(TInt n = 0; n < count; n++){
      // 获得网络信息
      SNetSocketInfo& info = infos.Get(n);
      FNetBufferedSocket* pSocket = InnerAddSocket(info);
      if(NULL == pSocket){
         result = EFalse;
         break;;
      }
   }
   _section.Leave();
   return result;
}

//============================================================
// <T>关闭通讯端口，并且发送一个断开通知</T>
//
// @param pSocket 端口
// @return 处理结果
//============================================================
TBool FNetTransferService::CloseSocketWithNotify(FNetBufferedSocket* pSocket){
   MO_ASSERT(pSocket);
   // 获得链接消息
   TCharC* pHost = pSocket->Host();
   TUint port = pSocket->Port();
   TInt handle = pSocket->Handle();
   // 关闭链接
   _section.Enter();
   TBool connected = pSocket->IsConnected();
   if(connected){
      // 发送端口关闭消息
      _pCommander->SendDisconnectNotify(pSocket, pHost, (TUint16)port);
      // 断开链接
      TInt receivePoolHandle = 0;
      TInt receivePoolCount = 0;
      INetSocketPool* pReceivePool = pSocket->ReceivePool();
      if(pReceivePool != NULL){
         receivePoolHandle = pReceivePool->Handle();
         receivePoolCount = pReceivePool->Count();
      }
      TInt sendPoolHandle = 0;
      TInt sendPoolCount = 0;
      INetSocketPool* pSendPool = pSocket->SendPool();
      if(pSendPool != NULL){
         sendPoolHandle = pSendPool->Handle();
         sendPoolCount = pSendPool->Count();
      }
      MO_DEBUG_INFO(TC("Remove client soclet. (socket=0x%08X, poll=0x%04X(%d)|0x%04X(%d), host=%s:%d, handle=%d(%s))"),
            pSocket, receivePoolHandle, receivePoolCount, sendPoolHandle, sendPoolCount,
            pHost, port, handle, TFsDateTime(pSocket->CreateDateTime()).Format());
      _pSocketsModule->CloseSocket(pSocket);
   }
   _section.Leave();
   return EFalse;
}

//============================================================
// <T>关闭通讯端口，并且发送一个断开通知</T>
//
// @param pSocket 端口
// @return 处理结果
//============================================================
TBool FNetTransferService::CloseSocketWithoutNotify(FNetBufferedSocket* pSocket){
   MO_ASSERT(pSocket);
   // 获得链接消息
   TCharC* pHost = pSocket->Host();
   TUint port = pSocket->Port();
   TInt handle = pSocket->Handle();
   // 关闭链接
   _section.Enter();
   TBool connected = pSocket->IsConnected();
   if(connected){
      // 断开链接
      MO_DEBUG_INFO(TC("Remove client soclet. (socket=0x%08X, poll=0x%04X|0x%04X, host=%s:%d, handle=%d(%s))"),
            pSocket, pSocket->ReceivePool()->Handle(), pSocket->SendPool()->Handle(),
            pHost, port, handle, TFsDateTime(pSocket->CreateDateTime()).Format());
      _pSocketsModule->CloseSocket(pSocket);
   }
   _section.Leave();
   return EFalse;
}

//============================================================
// <T>发送一个网络路由。</T>
//
// @param pSocket 网络端口
// @param pRouter 网络路由
// @return 处理结果
//============================================================
TBool FNetTransferService::SendSocketRouter(FNetBufferedSocket* pSocket, TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   // 发送消息
   TNetTransfer transfer(pRouter);
   TNetTransferHead& head = transfer.TransferHead();
   if(NULL != pSocket){
      head.SetHandle(pSocket->Handle());
      head.SetHost(pSocket->Ip(), pSocket->Port());
      head.SetSocket(pSocket->Index(), pSocket->Serial());
   }
   if(!_pInputQueue->PushRouter(&transfer)){
#ifdef _MO_DEBUG
      TChar dump[MO_FS_DUMP_LENGTH];
      TChar format[MO_FS_DUMP_LENGTH];
      MO_FATAL(TC("Send transfer socket message failure.\n%s%s"),
            pRouter->Dump(dump, MO_FS_DUMP_LENGTH),
            pRouter->DumpMemory(format, MO_FS_DUMP_LENGTH));
#endif
      return EFalse;
   }
   MO_DEBUG(TC("Send socket router. (length=%d)"), transfer.NetHead().Length());
   return ETrue;
}

//============================================================
// <T>发送一个网络传输。</T>
//
// @param pSocket 网络端口
// @param pTransfer 网络传输
// @return 处理结果
//============================================================
TBool FNetTransferService::SendSocketTransfer(FNetBufferedSocket* pSocket, TNetTransfer* pTransfer){
   MO_ASSERT(pTransfer);
   // 发送消息
   TNetTransferHead& head = pTransfer->TransferHead();
   if(NULL != pSocket){
      head.SetHandle(pSocket->Handle());
      head.SetHost(pSocket->Ip(), pSocket->Port());
      head.SetSocket(pSocket->Index(), pSocket->Serial());
   }
   if(!_pInputQueue->PushRouter(pTransfer)){
#ifdef _MO_DEBUG
      TChar dump[MO_FS_DUMP_LENGTH];
      TChar format[MO_FS_DUMP_LENGTH];
      MO_FATAL(TC("Send transfer socket message failure.\n%s%s"),
            pTransfer->Dump(dump, MO_FS_DUMP_LENGTH),
            pTransfer->DumpMemory(format, MO_FS_DUMP_LENGTH));
#endif
      return EFalse;
   }
   MO_DEBUG(TC("Send socket transfer. (length=%d)"), pTransfer->NetHead().Length());
   return ETrue;
}

//============================================================
// <T>处理一个网络传输。</T>
//
// @param pTransfer 网络传输
// @return 处理结果
//============================================================
TResult FNetTransferService::ProcessTransfer(TNetTransfer* pTransfer){
   return _pCommander->ProcessTransfer(pTransfer);
}

//============================================================
// <T>等待服务结束处理。</T>
//
// @return 处理结果
//============================================================
TBool FNetTransferService::Wait(){
   _pThreadGroup->Wait();
   return ETrue;
}

//============================================================
// <T>统计刷新。</T>
//
// @return 处理结果
//============================================================
TResult FNetTransferService::StatisticsRefresh(){
   TFsDump dump;
   if(_pSendThread != NULL){
      dump.AppendFormat(TC("\n   Send    total=%24s"), RInt::FormatCapacity(_pSendThread->SendTotal(), TFsCode(), TFsCode::Size()));
   }
   if(_pReceiveThread != NULL){
      dump.AppendFormat(TC("\n   Receive total=%24s"), RInt::FormatCapacity(_pReceiveThread->ReceiveTotal(), TFsCode(), TFsCode::Size()));
   }
   MO_INFO(TC("Transfer service statistics."), (TCharC*)dump);
   return ESuccess;
}

MO_NAMESPACE_END
