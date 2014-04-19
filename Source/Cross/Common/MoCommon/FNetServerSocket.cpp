#include <fcntl.h>
#ifdef _MO_LINUX
#include <sys/socket.h>
#include <arpa/inet.h>
#endif
#include "MoCmNet.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FNetServerSocket, FInstance);

//============================================================
// <T>构造服务网路端口。</T>
//
// @return 服务网路端口
//============================================================
FNetServerSocket::FNetServerSocket(){
   RType<SNetSocketInfo>::Clear(&_info);
}

//============================================================
// <T>构造服务网路端口。</T>
//
// @param port 端口
// @return 服务网路端口
//============================================================
FNetServerSocket::FNetServerSocket(TInt port){
   RType<SNetSocketInfo>::Clear(&_info);
   _info.port = port;
}

//============================================================
// <T>析构服务网络链接。</T>
//============================================================
FNetServerSocket::~FNetServerSocket(){
   if(INVALID_SOCKET != _info.handle){
      Disconnect();
   }
}

//============================================================
// <T>获得网络端口信息。</T>
//
// @return 网络端口信息
//============================================================
SNetSocketInfo* FNetServerSocket::Info(){
   return &_info;
}

//============================================================
// <T>绑定服务端口。</T>
//
// @return 处理结果
//============================================================
TResult FNetServerSocket::Bind(){
   // 创建链接
   _info.handle = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
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
// @return 处理结果
//============================================================
TResult FNetServerSocket::Listen(){
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
// @return 处理结果
//============================================================
TResult FNetServerSocket::Connect(){
   TResult result = Bind();
   if(result == ESuccess){
      result = Listen();
   }
   return result;
}

//============================================================
// <T>接收客户链接。</T>
//
// @param pInfo 接收信息
// @return 处理结果
//============================================================
TResult FNetServerSocket::Accept(SNetAcceptInfo* pInfo){
   MO_CHECK(pInfo, return ENull);
   // 接收端口
   sockaddr_in address;
#ifdef _MO_WINDOWS
   int size = sizeof(struct sockaddr_in);
#else
   socklen_t size = sizeof(struct sockaddr_in);
#endif
   TInt handle = accept(_info.handle, (struct sockaddr*)&address, &size);
   if(handle > 0){
      // 成功打开链接
      pInfo->handle = handle;
      pInfo->ip = address.sin_addr.s_addr;
      pInfo->port = address.sin_port;
      TChar8C* pHost = inet_ntoa(address.sin_addr);
#ifdef _MO_UNICODE
      TFsCode host;
      host.Assign8(pHost);
      MO_LIB_STRING_COPY(pInfo->host, host.Length() + 1, host.MemoryC());
#else
      MO_LIB_STRING_COPY(pInfo->host, sizeof(pInfo->host), inet_ntoa(address.sin_addr));
#endif // _MO_UNICODE
      return ESuccess;
   }else{
      // 错误处理
      if(_isBlock){
         MO_PFATAL(accept);
         return EFailure;
      }
   }
   return ESuccess;
}

//============================================================
// <T>断开服务。</T>
//
// @return 处理结果
//============================================================
TResult FNetServerSocket::Disconnect(){
   return Close();
}

MO_NAMESPACE_END
