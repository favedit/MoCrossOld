#include <fcntl.h>
#ifdef _MO_LINUX
#include <sys/socket.h>
#include <arpa/inet.h>
#endif
#include "MoCmNet.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造服务网路端口。</T>
//
// @return 服务网路端口
//============================================================
FNetUdpServerSocket::FNetUdpServerSocket(){
   RType<SNetSocketInfo>::Clear(&_info);
}

//============================================================
// <T>构造服务网路端口。</T>
//
// @param port 端口
// @return 服务网路端口
//============================================================
FNetUdpServerSocket::FNetUdpServerSocket(TInt port){
   RType<SNetSocketInfo>::Clear(&_info);
   _info.port = port;
}

//============================================================
// <T>析构服务网络链接。</T>
//============================================================
FNetUdpServerSocket::~FNetUdpServerSocket(){
   if(INVALID_SOCKET != _info.handle){
      Disconnect();
   }
}

//============================================================
SNetSocketInfo* FNetUdpServerSocket::Info(){
   return &_info;
}

//============================================================
// <T>绑定服务端口。</T>
//
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool FNetUdpServerSocket::Bind(){
   // 创建链接
   _info.handle = socket(AF_INET, SOCK_DGRAM, 0);
   if(INVALID_SOCKET == _info.handle){
      MO_PERROR(socket);
      return EFalse;
   }
   // 绑定链接
   struct sockaddr_in address;
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
#ifdef _MO_DEBUG
   // 设定重用
   TUint32 value = ETrue;
   if(EError == setsockopt(_info.handle, SOL_SOCKET, SO_REUSEADDR, (PChar8)&value, sizeof(TUint32))){
      MO_PERROR(setsockopt);
      return EFalse;
   }
#endif
   // 绑定链接
   TInt result = bind(_info.handle, (struct sockaddr*)&address, sizeof(struct sockaddr_in));
   if(SOCKET_ERROR == result){
      MO_ERROR(TC("Udp server socket bind failure. (host=%s:%d)"), (TCharC*)_host, _info.port);
      MO_PFATAL(socket);
      return EFalse;
   }
   MO_DEBUG(TC("Udp server socket bind success. (host=%s:%d)"), (TCharC*)_host, _info.port);
   return ETrue;
}

//============================================================
// <T>链接服务。</T>
//
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool FNetUdpServerSocket::Connect(){
   return Bind();
}

//============================================================
// <T>断开服务。</T>
//
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool FNetUdpServerSocket::Disconnect(){
   return Close();
}

MO_NAMESPACE_END
