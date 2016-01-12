#ifdef _MO_LINUX
#include <unistd.h>
#endif
#include "MoCrNetTransfer.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络链接监听线程实例。</T>
//
// @return 线程实例
//============================================================
FNetTransferServerThread::FNetTransferServerThread(){
   _code = TC("STV");
   _name = TC("Thread.Transfer.Server");
   _pServerSocket = MO_CREATE(FNetServerSocket);
   _pCatcher = MO_CREATE(FNetTransferCatcher);
   MO_CLEAR(_pService);
}

//============================================================
// <T>析构网络链接监听线程实例。</T>
//============================================================
FNetTransferServerThread::~FNetTransferServerThread(){
   MO_DELETE(_pServerSocket);
   MO_DELETE(_pCatcher);
}

//============================================================
// <T>设置传输服务。</T>
//
// @param pService 传输服务
//============================================================
void FNetTransferServerThread::SetService(FNetTransferService* pService){
   MO_ASSERT(pService);
   _pService = pService;
}

//============================================================
// <T>获得主机。</T>
//
// @return 主机
//============================================================
SNetHost& FNetTransferServerThread::Host(){
   return _host;
}

//============================================================
// <T>设置主机。</T>
//
// @param host 主机
//============================================================
void FNetTransferServerThread::SetHost(const SNetHost& host){
   _host = host;
}

//============================================================
// <T>获得端口。</T>
//
// @return 端口
//============================================================
FNetServerSocket* FNetTransferServerThread::Socket(){
   return _pServerSocket;
}

//============================================================
// <T>网络链接监听处理。</T>
//
// @return 处理结果
//============================================================
TResult FNetTransferServerThread::Process(){
   MO_ASSERT(_pService);
   // 链接端口
   _pServerSocket->SetHost(_host.host);
   _pServerSocket->SetPort(_host.port);
   _pServerSocket->Connect();
   _pServerSocket->SetNonBlock();
   MO_DEBUG(TC("Create tcp server. (host=%s:%d, handle=%d)"),
         (TCharC*)_host.host, _host.port, _pServerSocket->Handle());
   // 设置信息
   TFsNetSocketInfoVector infos;
   TInt infoSize = infos.Size();
   // 安装扑捉器
   _pCatcher->Install();
   // 监听输入端口
   while(!IsStop()){
      _pCatcher->Enter();
      if(MO_TRAP_CATCHER(_pCatcher)){
         _pCatcher->Recorded();
         //............................................................
         // 接收多个请求
         infos.Clear();
         for(TInt n = 0; n < infoSize; n++){
            SNetSocketInfo info;
            RType<SNetSocketInfo>::Clear(&info);
            //if(_pServerSocket->Accept(info)){
            //   infos.Push(info);
            //}else{
            //   break;
            //}
         }
         // 增加网络链接
         if(!infos.IsEmpty()){
            _pService->AddSockets(infos);
         }else{
            MO_LIB_MICRO_SLEEP(MO_MD_NTS_ACCEPT_INTERVAL);
         }
      }else{
         _pCatcher->JumpFinish();
      }
      _pCatcher->Leave();
   }
   return ESuccess;
}

MO_NAMESPACE_END
