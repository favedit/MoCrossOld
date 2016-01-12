#ifdef _MO_WINDOWS
#include <WinSock2.h>
#endif // _MO_WINDOWS
#include "MoCrNetIocp.h"

MO_NAMESPACE_BEGIN

#ifdef _MO_WINDOWS

//============================================================
// <T>构造服务网路端口。</T>
//
// @return 服务网路端口
//============================================================
FNetIocpServerSocket::FNetIocpServerSocket(){
}
//============================================================
// <T>析构服务网络链接。</T>
//============================================================
FNetIocpServerSocket::~FNetIocpServerSocket(){
}

//============================================================
// <T>绑定服务端口。</T>
//
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TResult FNetIocpServerSocket::Bind(){
   // 创建链接
   _info.handle = (TUint32)::WSASocket(AF_INET, SOCK_STREAM, IPPROTO_IP, NULL, 0, WSA_FLAG_OVERLAPPED);
   if(INVALID_SOCKET == _info.handle){
      MO_PERROR(socket);
      return EFailure;
   }
   // 绑定链接
   struct sockaddr_in address;
   address.sin_family = AF_INET;
   if(_host.IsEmpty()){
      address.sin_addr.s_addr = htonl(INADDR_ANY);
   }else if(_host.Equals(TC("*"))){
      address.sin_addr.s_addr = htonl(INADDR_ANY);
   }else{
#ifdef _UNICODE
#else
      address.sin_addr.s_addr = inet_addr((TCharC*)_host);
#endif
   }   
   address.sin_port = htons(_info.port);
   memset(address.sin_zero, 0, sizeof(address.sin_zero));
   // 设定重用
   TUint32 value = ETrue;
   if(EError == setsockopt(_info.handle, SOL_SOCKET, SO_REUSEADDR, (PChar8)&value, sizeof(TUint32))){
      MO_PERROR(setsockopt);
      return EFailure;
   }
   // 绑定链接
   TInt result = bind(_info.handle, (struct sockaddr*)&address, sizeof(struct sockaddr_in));
   if(SOCKET_ERROR == result){
      MO_ERROR(TC("Tcp server socket bind failure. (host=%s:%d)"), (TCharC*)_host, _info.port);
      MO_PFATAL(socket);
      return EFailure;
   }
   MO_DEBUG(TC("Tcp server socket bind success. (host=%s:%d)"), (TCharC*)_host, _info.port);
   return ESuccess;
}

//============================================================
// <T>监听服务端口。</T>
//
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TResult FNetIocpServerSocket::Listen(){
   TInt result = listen(_info.handle, SOMAXCONN);
   if(SOCKET_ERROR == result){
      MO_PERROR(socket);
      return EFailure;
   }
   MO_DEBUG(TC("Tcp server socket listen success. (host=%s:%d)"), (TCharC*)_host, _info.port);
   return ESuccess;
}

//============================================================
// <T>链接服务。</T>
//
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TResult FNetIocpServerSocket::Connect(){
   TResult result = EFailure;
   if(Bind() == ESuccess){
      result = Listen();
   }
   return result;
}

//============================================================
// <T>接收客户链接。</T>
//
// @return 客户链接
//============================================================
TResult FNetIocpServerSocket::Accept(SNetSocketInfo& info){
   sockaddr_in address;
#ifdef _MO_WINDOWS
   int size = sizeof(struct sockaddr_in);
#else
   socklen_t size = sizeof(struct sockaddr_in);
#endif
   TInt handle = accept(_info.handle, (struct sockaddr*)&address, &size);
   if(handle > 0){
      // 成功打开链接
      info.handle = (TUint32)handle;
      info.ip = address.sin_addr.s_addr;
      info.port = address.sin_port;
      TChar8C* pAddress = inet_ntoa(address.sin_addr);
      RString::SafeCopy(info.host, sizeof(info.host), pAddress);
      return ETrue;
   }else{
      // 错误处理
      if(_isBlock){
         MO_PFATAL(accept);
      }
   }
   return EFalse;
}

//============================================================
// <T>断开服务。</T>
//
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TResult FNetIocpServerSocket::Disconnect(){
   return Close();
}

#endif // _MO_WINDOWS

MO_NAMESPACE_END
