#include <fcntl.h>
#ifdef _MO_WINDOWS
#undef SetNetPort
#else
#include <sys/socket.h>
#include <arpa/inet.h>
#endif
#include "MoCmNet.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FNetClientSocket, FInstance);

//============================================================
// <T>构造客户网路端口。</T>
//
// @return 客户网路端口
//============================================================
FNetClientSocket::FNetClientSocket(){
   RType<SNetSocketInfo>::Clear(&_info);
}

//============================================================
// <T>构造客户网路端口。</T>
//
// @param host 主机名称
// @param port 端口
// @return 客户网路端口
//============================================================
FNetClientSocket::FNetClientSocket(TCharC* pHost, TInt port){
   RType<SNetSocketInfo>::Clear(&_info);
   _host.Assign(pHost);
   _info.port = port;
}

//============================================================
// <T>析构客户网路端口。</T>
//============================================================
FNetClientSocket::~FNetClientSocket(){
   if(INVALID_SOCKET != Handle()){
      Disconnect();
   }
}

//============================================================
// <T>获得网络端口信息。</T>
//
// @return 网络端口信息
//============================================================
SNetSocketInfo* FNetClientSocket::Info(){
   return &_info;
}

//============================================================
// <T>链接指定服务器。</T>
//
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TResult FNetClientSocket::Connect(){
   // 创建链接
   TSocket handle = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP);
   if(INVALID_SOCKET == handle){
      MO_PERROR(socket);
      return EFalse;
   }
   // 绑定链接
   sockaddr_in address;
   address.sin_family = AF_INET;
   if(_host.IsEmpty()){
      address.sin_addr.s_addr = htonl(INADDR_ANY);
   }
#ifdef _UNICODE
   TFsCode8 host;
   host.Assign16(_host);
   if(_host.IsNotEmpty()){
      address.sin_addr.s_addr = inet_addr((TChar8C*)host);
   }
#else
   if(_host.IsNotEmpty()){
      address.sin_addr.s_addr = inet_addr((TCharC*)_host);
   }
#endif // _UNICODE
   address.sin_port = htons(_info.port);
   memset(address.sin_zero, 0, sizeof(address.sin_zero));
   if(ESuccess != connect(handle, (struct sockaddr*)&address, sizeof(struct sockaddr_in))){
      MO_ERROR(TC("Connect socket error. (host=%s, port=%d)"), (TCharC*)_host, _info.port);
      MO_PERROR(connect);
      // 关闭已经打开的链接句柄
#ifdef _MO_WINDOWS
      if(ESuccess != closesocket(handle)){
         MO_PERROR(closesocket);
      }
#else
      if(ESuccess != close(handle)){
         MO_PERROR(close);
      }
#endif // _MO_WINDOWS
      return EFailure;
   }
   // 设置信息
   _info.handle = handle;
   _isConnected = ETrue;
   return ESuccess;
}

//============================================================
// <T>链接指定服务器。</T>
//
// @param pHost 主机
// @param port 端口
// @return 处理结果
//============================================================
TResult FNetClientSocket::Connect(TCharC* pHost, TUint16 port){
   // 设置信息
   SetHost(pHost);
   SetPort(port);
   // 链接处理
   TResult result = Connect();
   return result;
}

//============================================================
// <T>断开链接。</T>
//
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TResult FNetClientSocket::Disconnect(){
   if(INVALID_SOCKET == _info.handle){
#ifdef _MO_WINDOWS
      closesocket(_info.handle);
#else
      close(_info.handle);
#endif // _MO_WINDOWS
      _info.handle = INVALID_SOCKET;
   }
   _isConnected = EFalse;
   return ESuccess;
}

MO_NAMESPACE_END
