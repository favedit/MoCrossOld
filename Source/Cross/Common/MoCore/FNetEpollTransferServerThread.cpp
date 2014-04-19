#include "MoCrNetTransferEpoll.h"

MO_NAMESPACE_BEGIN

#ifdef _MO_LINUX

//============================================================
// <T>构造网络链接监听线程实例。</T>
//
// @return 线程实例
//============================================================
FNetEpollTransferServerThread::FNetEpollTransferServerThread(){
}

//============================================================
// <T>析构网络链接监听线程实例。</T>
//============================================================
FNetEpollTransferServerThread::~FNetEpollTransferServerThread(){
   MO_DELETE(_pServerSocket);
   MO_DELETE(_pCatcher);
}
//============================================================
// <T>网络链接监听处理。</T>
//
// @return 执行结果
//============================================================
TInt FNetEpollTransferServerThread::Process(){
   MO_ASSERT(_pService);
   // 链接端口
   _pServerSocket->SetHost(_host.host);
   _pServerSocket->SetPort(_host.port);
   _pServerSocket->Connect();
   _pServerSocket->SetNonBlock();
   MO_DEBUG("Create tcp server. (host=%s:%d, handle=%d)",
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
            if(_pServerSocket->Accept(info)){
               infos.Push(info);
            }else{
               break;
            }
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

#endif // _MO_LINUX

MO_NAMESPACE_END
