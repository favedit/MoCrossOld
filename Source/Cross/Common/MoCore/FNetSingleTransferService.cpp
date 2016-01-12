#include "MoCrNetTransferSingle.h"

MO_NAMESPACE_BEGIN

#ifdef _MO_WINDOWS

//============================================================
// <T>构造重叠端口服务。</T>
//============================================================
FNetSingleTransferService::FNetSingleTransferService(){
}

//============================================================
// <T>析构重叠端口服务。</T>
//============================================================
FNetSingleTransferService::~FNetSingleTransferService(){
}

//============================================================
// <T>启动网络链接数据传输。</T>
//
// @return 处理结果
//============================================================
TResult FNetSingleTransferService::OnStartup(){
   // 创建链接监听线程
   TInt serverCount = _config.hosts.Count();
   for(TInt n = 0; n < serverCount; n++){
      // 创建服务线程
      FNetSingleTransferServerThread* pServerThread = MO_CREATE(FNetSingleTransferServerThread);
      pServerThread->SetService(this);
      pServerThread->SetHost(_config.hosts[n]);
      _pServerThreads->Push(pServerThread);
      // 放入线程组
      _pThreadGroup->Push(pServerThread);
   }
   // 创建链接数据发送线程
   _pSendThread = MO_CREATE(FNetSingleTransferSendThread);
   _pSendThread->SetService(this);
   _pThreadGroup->Push(_pSendThread);
   // 启动线程组
   _pThreadGroup->Start();
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
FNetBufferedSocket* FNetSingleTransferService::InnerAddSocket(SNetSocketInfo& info){
   TInt limitSocketCount = _config.limitSocketCount;
   // 检查限制
   if(limitSocketCount > 0){
      TInt socketCount = _pSocketsModule->Pool()->UsingCount();
      if(socketCount + 1 > limitSocketCount){
         MO_WARN(TC("Socket pool is full. (count=%d, limit=%d)"), socketCount, limitSocketCount);
         SendLimitNotify(info);
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
   // 关联链接
   _pSocketsModule->OpenSocket(pSocket);
   MO_DEBUG_INFO(TC("Add net socket. (socket=0x%08X, host=%s:%d, handle=%d, index=%d:%d)"),
         pSocket, info.host, info.port, info.handle, pSocket->Index(), pSocket->Serial());
   // 启动接收线程
   FNetSingleSocketReceiveThread* pReceiveThread = MO_CREATE(FNetSingleSocketReceiveThread);
   pReceiveThread->SetService(this);
   pReceiveThread->SetSocket(pSocket);
   pReceiveThread->Start();
   // 启动发送线程
   FNetSingleSocketSendThread* pSendThread = MO_CREATE(FNetSingleSocketSendThread);
   pSendThread->SetService(this);
   pSendThread->SetSocket(pSocket);
   pSendThread->Start();
   // 发送端口链接消息
   SendConnectNotify(pSocket, info.host, info.port);
   return pSocket;
}

#endif // _MO_WINDOWS

MO_NAMESPACE_END
