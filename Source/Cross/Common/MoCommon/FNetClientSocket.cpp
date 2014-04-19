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
TBool FNetClientSocket::Connect(){
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
   }else{
#ifdef _UNICODE
#else
      address.sin_addr.s_addr = inet_addr((TCharC*)_host);
#endif
   }
   address.sin_port = htons(_info.port);
   memset(address.sin_zero, 0, sizeof(address.sin_zero));
   if(ESuccess != connect(handle, (struct sockaddr*)&address, sizeof(struct sockaddr_in))){
      MO_ERROR(TC("Connect socket error. (host=%s, port=%d)"), (TCharC*)_host, _info.port);
      MO_PFATAL(connect);
      // 关闭已经打开的链接句柄
#ifdef _MO_WINDOWS
      if(ESuccess != closesocket(handle)){
         MO_PFATAL(closesocket);
      }
#else
      if(ESuccess != close(handle)){
         MO_PERROR(close);
      }
#endif
      return EFalse;
   }
   _info.handle = handle;
   _isConnected = ETrue;
   return ETrue;
}

//============================================================
TBool FNetClientSocket::Connect(TCharC* pHost, TUint16 port){
   SetHost(pHost);
   SetPort(port);
   return Connect();
}

//============================================================
// <T>断开链接。</T>
//
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool FNetClientSocket::Disconnect(){
   _isConnected = EFalse;
   if(INVALID_SOCKET == _info.handle){
#ifdef _MO_WINDOWS
      closesocket(_info.handle);
#else
      close(_info.handle);
#endif
      _info.handle = INVALID_SOCKET;
      return ETrue;
   }
   return EFalse;
}

MO_NAMESPACE_END
