#include "MoCrNetTransferSingle.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络链接监听线程实例。</T>
//
// @return 线程实例
//============================================================
FNetSingleTransferServerThread::FNetSingleTransferServerThread(){
}

//============================================================
// <T>析构网络链接监听线程实例。</T>
//============================================================
FNetSingleTransferServerThread::~FNetSingleTransferServerThread(){
   MO_DELETE(_pServerSocket);
   MO_DELETE(_pCatcher);
}
//============================================================
// <T>网络链接监听处理。</T>
//
// @return 处理结果
//============================================================
TResult FNetSingleTransferServerThread::Process(){
   MO_ASSERT(_pService);
   // 链接端口
   _pServerSocket->SetHost(_host.host);
   _pServerSocket->SetPort(_host.port);
   _pServerSocket->Connect();
   MO_DEBUG(TC("Create tcp server. (host=%s:%d, handle=%d)"), (TCharC*)_host.host, _host.port, _pServerSocket->Handle());
   // 安装扑捉器
   _pCatcher->Install();
   // 监听输入端口
   while(!IsStop()){
      _pCatcher->Enter();
      if(MO_TRAP_CATCHER(_pCatcher)){
         _pCatcher->Recorded();
         //............................................................
         // 接收多个请求
         SNetSocketInfo info;
         RType<SNetSocketInfo>::Clear(&info);
         //if(_pServerSocket->Accept(info)){
         //   _pService->AddSocket(info);
         //}else{
         //   MO_FATAL("Accep socket failre.");
         //}
      }else{
         _pCatcher->JumpFinish();
      }
      _pCatcher->Leave();
   }
   return ESuccess;
}

MO_NAMESPACE_END
