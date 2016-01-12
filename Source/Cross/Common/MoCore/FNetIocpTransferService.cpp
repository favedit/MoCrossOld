#include "MoCrNetTransferIocp.h"

MO_NAMESPACE_BEGIN

#ifdef _MO_WINDOWS

//============================================================
// <T>构造重叠端口服务。</T>
//============================================================
FNetIocpTransferService::FNetIocpTransferService(){
}

//============================================================
// <T>析构重叠端口服务。</T>
//============================================================
FNetIocpTransferService::~FNetIocpTransferService(){
}

//============================================================
// <T>启动网络链接数据传输。</T>
//
// @return 处理结果
//============================================================
TResult FNetIocpTransferService::OnStartup(){
   // 创建链接监听线程
   //TInt serverCount = _config.hosts.Count();
   //for(TInt n = 0; n < serverCount; n++){
   // 创建服务线程
   FNetIocpTransferServerThread* pServerThread = MO_CREATE(FNetIocpTransferServerThread);
   pServerThread->SetService(this);
   //pServerThread->SetHost(_config.hosts[n]);
   SNetHost host;
   host.host = TC("*");
   host.port = 900;
   pServerThread->SetHost(host);
   _pServerThreads->Push(pServerThread);
   // 放入线程组
   _pThreadGroup->Push(pServerThread);
   //}
   // 创建链接数据等待线程
   _pReceiveThread = MO_CREATE(FNetIocpTransferReceiveThread);
   _pReceiveThread->SetService(this);
   _pThreadGroup->Push(_pReceiveThread);
   // 启动线程组
   _pThreadGroup->Start();
   return ESuccess;
}

//============================================================
// <T>增加一个网络链接</T>
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
TBool FNetIocpTransferService::AddSockets(TFsNetSocketInfoVector& infos){
   _section.Enter();
   TInt limitSocketCount = _config.limitSocketCount;
   TInt count = infos.Count();
   for(TInt n = 0; n < count; n++){
      // 获得网络信息
      SNetSocketInfo& info = infos.Get(n);
      // 检查限制
      if(limitSocketCount > 0){
         TInt socketCount = _pSocketsModule->Pool()->UsingCount();
         if(socketCount + 1 > limitSocketCount){
            MO_WARN(TC("Socket pool is full. (count=%d, limit=%d)"), socketCount, limitSocketCount);
            SendLimitNotify(info);
            continue;
         }
      }
      // 链接端口
      FNetBufferedSocket* pSocket = _pSocketsModule->LinkHandle(info.handle);
      // 获得链接对象失败，关闭句柄，拒绝链接
      if(NULL == pSocket){
         RNetSocket::Disconnect(info);
         continue;
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
      MO_DEBUG_INFO(TC("Add net socket. (socket=0x%08X, poll=0x%04X(%d), host=%s:%d, handle=%d, index=%d:%d)"),
         pSocket,
         pSocket->ReceivePool()->Handle(), pSocket->ReceivePool()->Count(),
         info.host, info.port, info.handle, pSocket->Index(), pSocket->Serial());
      // 发送端口链接消息
      SendConnectNotify(pSocket, info.host, info.port);
   }
   _section.Leave();
   return ETrue;
}

#endif // _MO_WINDOWS

MO_NAMESPACE_END
