#include "MoCrNetTransferSingle.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络数据发送线程实例。</T>
//
// @return 线程实例
//============================================================
FNetSingleSocketSendThread::FNetSingleSocketSendThread(){
   MO_CLEAR(_pSocket);
}

//============================================================
// <T>析构网络数据发送线程实例。</T>
//============================================================
FNetSingleSocketSendThread::~FNetSingleSocketSendThread(){
}

//============================================================
// <T>网络数据发送处理。</T>
// <P>收到网络来的消息，根据目标集合：
// <P> － 目标Server收到网络来的消息，根据目标集合，拆分成多个路由包，发往不同的服务器。。</P>
// <P>收到网络来的消息，根据目标集合，拆分成多个路由包，发往不同的服务器。。</P>
//
// @return 处理结果
//============================================================
TResult FNetSingleSocketSendThread::Process(){
   // 获得设置信息
   MO_ASSERT(_pService);
   STransferServiceConfig& config = _pService->Config();
   _protocol = config.protocol;
   _dataCompress = config.dataCompress;
   TInt receiveFirstTimeout = config.receiveFirstTimeout;
   TInt receiveTimeout = config.receiveTimeout;
   TInt sendTimeout = config.sendTimeout;
   // 获得发送完成端口
   MO_ASSERT(_pSocket);
   // 获得链接管理模块
   _pSocketsModule = _pService->SocketsModule();
   MO_ASSERT(_pSocketsModule);
   // 获得主输出队列
   _pOutputQueue = _pService->QueueStorage()->Connection()->OutputQueue();
   MO_ASSERT(_pOutputQueue);
   // 处理消息
   TInt loop = 0;
   TNetTransfer transfer;
   TInt timeout = MO_MD_NTS_EVENT_TIMEOUT;
   while(!IsStop()){
      TDateTime tick = RDateTime::Current();
      // 检查超时
      SNetSocketInfo* pInfo = _pSocket->Info();
      if(receiveFirstTimeout > 0){
         if(pInfo->receiveDateTime == pInfo->createDateTime){
            TTimeTick firstTick = tick - pInfo->receiveDateTime;
            if(firstTick > receiveFirstTimeout){
               MO_WARN(TC("Epoll socket disconnect first timeout. (socket=0x%08X, current_tick=0x%16X, receive_tick=0x%16X, first_timeout=%d, first_tick=%d)"),
                     _pSocket, tick, pInfo->receiveDateTime, receiveFirstTimeout, firstTick);
               _pService->CloseSocketWithNotify(_pSocket);
               break;
            }
         }
      }
      if(receiveTimeout > 0){
         TTimeTick receiveTick = tick - pInfo->receiveDateTime;
         if(receiveTick > receiveTimeout){
            MO_WARN(TC("Epoll socket receive timeout. (socket=0x%08X, current_tick=0x%16X, receive_tick=0x%16X, receive_timeout=%d, receive_tick=%d)"),
                  _pSocket, tick, pInfo->receiveDateTime, receiveTimeout, receiveTick);
            _pService->CloseSocketWithNotify(_pSocket);
            break;
         }
      }
      if(sendTimeout > 0){
         TTimeTick sendTick = tick - pInfo->sendDateTime;
         if(sendTick > sendTimeout){
            MO_WARN(TC("Epoll socket send timeout. (socket=0x%08X, current_tick=0x%16X, send_tick=0x%16X, send_timeout=%d, send_tick=%d)"),
                  _pSocket, tick, pInfo->sendDateTime, sendTimeout, sendTick);
            _pService->CloseSocketWithNotify(_pSocket);
            break;
         }
      }
      // 发送消息数据
      TInt length = _pSocket->DoBufferSend();
      if(length > 0){
         _sendTotal += length;
         loop++;
      }else if(-1 == length){
         // 关闭链接
         MO_INFO(TC("Epoll socket receive disconnect. (socket=0x%08X, length=%d)"), _pSocket, length);
         _pService->CloseSocketWithNotify(_pSocket);
         break;
      }
      // 未读取到数据和未发送数据的情况下，休眠处理
      if(0 == length){
         MO_LIB_MICRO_SLEEP(MO_MD_NTS_NOTIFY_INTERVAL);
      }
   }
   return ESuccess;
}

MO_NAMESPACE_END
