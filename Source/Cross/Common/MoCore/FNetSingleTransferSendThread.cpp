#include "MoCrNetTransferSingle.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络数据发送线程实例。</T>
//
// @return 线程实例
//============================================================
FNetSingleTransferSendThread::FNetSingleTransferSendThread(){
}

//============================================================
// <T>析构网络数据发送线程实例。</T>
//============================================================
FNetSingleTransferSendThread::~FNetSingleTransferSendThread(){
}

//============================================================
// <T>网络数据发送处理。</T>
// <P>收到网络来的消息，根据目标集合：
// <P> － 目标Server收到网络来的消息，根据目标集合，拆分成多个路由包，发往不同的服务器。。</P>
// <P>收到网络来的消息，根据目标集合，拆分成多个路由包，发往不同的服务器。。</P>
//
// @return 处理结果
//============================================================
TResult FNetSingleTransferSendThread::Process(){
   // 获得设置信息
   MO_ASSERT(_pService);
   STransferServiceConfig& config = _pService->Config();
   _protocol = config.protocol;
   _dataCompress = config.dataCompress;
   TInt receiveFirstTimeout = config.receiveFirstTimeout;
   TInt receiveTimeout = config.receiveTimeout;
   TInt sendTimeout = config.sendTimeout;
   // 获得链接管理模块
   _pSocketsModule = _pService->SocketsModule();
   MO_ASSERT(_pSocketsModule);
   // 获得主输出队列
   _pOutputQueue = _pService->QueueStorage()->Connection()->OutputQueue();
   MO_ASSERT(_pOutputQueue);
   // 处理消息
   TInt loop = 0;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   TNetTransfer transfer;
   TInt timeout = MO_MD_NTS_EVENT_TIMEOUT;
   while(!IsStop()){
      TInt readCount = 0;
      TInt readTotal = 0;
      TDateTime tick = RDateTime::Current();
      // 处理输入消息流
      for(TInt n = 0; n < MO_MD_NTS_EVENT_MAXCOUNT; n++){
         // 读出一个消息，分布到链接输出管道上
         TInt readed = _pOutputQueue->Pop(buffer, MO_NETMESSAGE_MAXLENGTH);
         if(0 == readed){
            break;
         } 
         readCount++;
         readTotal += readed;
         // 读取管道消息
         TInt length;
         if(transfer.Unserialize(buffer, readed, &length)){
            // 获取消息类型
            TInt targetType = transfer.RouterHead().TargetType();
            // 处理消息
            if(ENetTerminal_ServerTransfer == targetType){
               // 处理传输控制消息
               _pService->ProcessTransfer(&transfer);
            }else{
               // 发送消息给目标
               ProcessTarget(&transfer);
            }
         }else{
            MO_ERROR(TC("Router unserialize failure. (length=%d)"), length);
         }
      }
      // 未读取到数据和未发送数据的情况下，休眠处理
      if(0 == readCount){
         MO_LIB_MICRO_SLEEP(MO_MD_NTS_NOTIFY_INTERVAL);
      }
   }
   return ESuccess;
}

MO_NAMESPACE_END
