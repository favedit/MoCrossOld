#include "MoCrNetIocp.h"

MO_NAMESPACE_BEGIN

#ifdef _MO_WINDOWS

//============================================================
// <T>构造重叠端口服务。</T>
//============================================================
FNetIocp::FNetIocp(){
   MO_CLEAR(_pHandle);
   _count = 0;
}

//============================================================
// <T>析构重叠端口服务。</T>
//============================================================
FNetIocp::~FNetIocp(){
}

//============================================================
// <T>获得句柄。</T>
//
// @return 句柄
//============================================================
TInt FNetIocp::Handle(){
   return 0;
}

//============================================================
// <T>获得个数。</T>
//
// @return 个数
//============================================================
TInt FNetIocp::Count(){
   return _count;
}

//============================================================
// <T>设置处理。</T>
//============================================================
void FNetIocp::Setup(){
   MO_INFO(TC("Iocp server setup."));
   // 获得最大线程数
   //SYSTEM_INFO systemInfo;
   //GetSystemInfo(&systemInfo);
   //TInt threadCount = MO_MIN(systemInfo.dwNumberOfProcessors, 1
   //TInt threadCount = 1;
   // 创建端口
   _pHandle = CreateIoCompletionPort(INVALID_HANDLE_VALUE, 0, 0, 0);
}

//============================================================
// <T>增加网络句柄的设置。</T>
//
// @param pSocket 网络端口
// @param flag 标志
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool FNetIocp::Add(INetSocket* pSocket, TUint flag){
   // 获得句柄
   MO_ASSERT(_pHandle);
   MO_ASSERT(pSocket);
   FNetIocpSocket* pIocpSocket = (FNetIocpSocket*)pSocket;
   TSocket handle = pIocpSocket->Handle();
   TAny* pHandle = CreateIoCompletionPort((TAny*)handle, _pHandle, (ULONG_PTR)pIocpSocket, 1); 
   _count++;
   return ETrue;
}

//============================================================
// <T>修改网络句柄的设置。</T>
//
// @param pSocket 网络端口
// @param flag 标志
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool FNetIocp::Modify(INetSocket* pSocket, TUint flag){
   return ETrue;
}

//============================================================
// <T>删除网络句柄的设置。</T>
//
// @param pSocket 网络端口
// @param flag 标志
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool FNetIocp::Remove(INetSocket* pSocket, TUint flag){
   _count--;
   return ETrue;
}

////============================================================
//// <T>获得网络服务类工厂。</T>
////
//// @return 类工厂
////============================================================
//INetServerFactory* FNetIocp::Factory(){
//   return _pFactory;
//}
//
////============================================================
//// <T>获得完成端口。</T>
////
//// @return 完成端口
////============================================================
//FCompletionPort* FNetIocp::CompletionPort(){
//   return _pCompletionPort;
//}
//
////============================================================
//// <T>获得服务链接。</T>
////
//// @return 服务链接
////============================================================
//FNetServerSocket* FNetIocp::ServerSocket(){
//   return _pServerSocket;
//}
//
////============================================================
//// <T>获得服务线程。</T>
////
//// @return 服务线程
////============================================================
//FThread* FNetIocp::ServerThread(){
//   return _pServerThread;
//}
//
////============================================================
//// <T>获得数据处理接口。</T>
////
//// @return 数据处理接口
////============================================================
//INetDataProcessor* FNetIocp::DataProcessor(){
//   return _pDataProcessor;
//}
//
////============================================================
//// <T>接收一个新的网络链接。</T>
////============================================================
//void FNetIocp::DoAccept(FNetSocket* pSocket){
//   _pSockets->Push((FNetIocpSocket*)pSocket);
//   // 追加到完成端口中
//   _pCompletionPort->Append((PPtr)pSocket->NativeSocket());
//}
//
////============================================================
//// <T>开始服务。</T>
////============================================================
//void FNetIocp::Start(){
//   // 创建服务链接
//   _pServerSocket = _pFactory->CreateServerSocket(this);
//   _pServerSocket->Bind(_port);
//   _pServerSocket->Listen();
//   MO_DEBUG(this, _T("Start net IOCP server. (port=%d, query=%d)"), _port, threadCount);
//   // 创建数据处理机
//   _pDataProcessor = _pFactory->CreateDataProcessor(this);
//   // 创建完成端口
//   _pCompletionPort = MO_CREATE(FCompletionPort, threadCount);
//   _pCompletionPort->Append((PPtr)_pServerSocket->NativeSocket());
//   // 创建服务线程
//   _pServerThread = (FNetIocpThread*)_pFactory->CreateServerThread(this);
//   _pServerThread->Start();
//   // 创建处理线程
//   while(--threadCount >= 0){
//      FNetIocpQueryThread* pThread = (FNetIocpQueryThread*)_pFactory->CreateQueryThread(this);
//      pThread->Start();
//   }
//}
//
////============================================================
//// <T>结束服务。</T>
////============================================================
//void FNetIocp::Stop(){
//}
//
////============================================================
//void FNetIocp::SetDataProcessor(INetDataProcessor* pDataProcessor){
//   _pDataProcessor = pDataProcessor;
//}

#endif // _MO_WINDOWS

MO_NAMESPACE_END
