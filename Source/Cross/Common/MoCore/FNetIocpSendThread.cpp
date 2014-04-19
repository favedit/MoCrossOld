#include "MoCrNetIocp.h"

MO_NAMESPACE_BEGIN

#ifdef _MO_WINDOWS

////============================================================
//// <T>构造完成端口线程。</T>
////============================================================
//FNetIocpSendThread::FNetIocpSendThread(INetServer* pNetServer){
//   _pNetServer = pNetServer;
//}
//
////============================================================
//// <T>处理线程。</T>
////
//// @return 处理结果
////============================================================
//TInt FNetIocpSendThread::Process(){
//   MO_INFO(_T("Iocp send thread process."));
//   FNetIocpServer* pNetServer = (FNetIocpServer*)_pNetServer;
//   FCompletionPort* pCompletionPort = pNetServer->CompletionPort();
//   INetDataProcessor* pDataProcessor = pNetServer->DataProcessor();
//   PPtr pHandle = pCompletionPort->Handle();
//   // 循环处理
//   while(!_stop){
//      TUint32 transdBytes = 0;
//      TBool result = GetQueuedCompletionStatus(pHandle, &transdBytes, (PULONG_PTR)&_completionKey, &_pOverlap, INFINITE);
//      MO_DEBUG(this, _T("Get queued completion status. (result=%d)"), result);
//      // 继续处理
//      if(EFalse == result){
//         continue;
//      }
//      // 处理事件
//      if(NULL != pDataProcessor){
//         _pIocpOverlap = CONTAINING_RECORD(_pOverlap, SIocpOverlapped, Overlapped);
//         switch(_pIocpOverlap->OperateCode){
//            case ENetIocpOperate_Read:
//               // 处理读数据事件
//               pDataProcessor->DoRead(_pIocpOverlap->pSocket, _pIocpOverlap->Buffer, transdBytes);
//              break;
//            case ENetIocpOperate_Write:
//               // 处理写数据事件
//               pDataProcessor->DoWrite(_pIocpOverlap->pSocket, _pIocpOverlap->Buffer, transdBytes);
//               break;
//         }
//      }
//   }
//   return ESuccess;
//}

#endif // _MO_WINDOWS

MO_NAMESPACE_END
