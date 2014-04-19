#include <fcntl.h>
#ifdef _MO_WINDOWS
#include <Winsock2.h>
#else
#include <sys/socket.h>
#include <arpa/inet.h>
#endif
#include "MoCmNet.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>启动网络。</T>
//============================================================
void RNetSocket::Startup(){
#ifdef _MO_WINDOWS
   TUint16 wVersionRequested = MAKEWORD( 2, 2 );
   WSADATA wsaData;
   RMemory::Clear(&wsaData, sizeof(wsaData));
   MO_STATIC_DEBUG(TC("Socket environment startup (version=2.2)"));
   TInt error = WSAStartup(wVersionRequested, &wsaData);
   MO_ASSERT(0 == error);
#endif // _MO_WINDOWS
}

//============================================================
// <T>关闭网络。</T>
//============================================================
void RNetSocket::Cleanup(){
#ifdef _MO_WINDOWS
   MO_STATIC_DEBUG(TC("Socket environment cleanup."));
   WSACleanup();
#endif // _MO_WINDOWS
}

//============================================================
// <T>链接网络端口。</T>
//
// @param info 网络信息
// @return 处理结果
//============================================================
TBool RNetSocket::Connect(SNetSocketInfo& info){
   // 创建链接
   TSocket handle = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP);
   if(INVALID_SOCKET == handle){
      MO_STATIC_PERROR(socket);
      return EFalse;
   }
   MO_STATIC_DEBUG(TC("Connect net socket. (host=%s, port=%d)"), info.host, info.port);
   // 绑定链接
   sockaddr_in address;
   address.sin_family = AF_INET;
   address.sin_port = htons(info.port);
#ifdef _UNICODE
   TFsName8 host;
   // RString16::ConvertToString8((TChar8*)host, host.Size(), (TCharC*)info.host);
   address.sin_addr.s_addr = inet_addr((TChar8C*)host);
#else
   address.sin_addr.s_addr = inet_addr(info.host);
#endif // _UNICODE
   memset(address.sin_zero, 0, sizeof(address.sin_zero));
   if(ESuccess != connect(handle, (struct sockaddr*)&address, sizeof(sockaddr_in))){
      MO_STATIC_PERROR(connect);
      // 关闭已经打开的链接句柄
#ifdef _MO_WINDOWS
      if(ESuccess != closesocket(handle)){
         MO_STATIC_PERROR(close);
      }
#else
      if(ESuccess != close(handle)){
         MO_STATIC_PERROR(close);
      }
#endif // _MO_WINDOWS
      return EFalse;
   }
   // 设置属性
   TDateTime now = RDateTime::Current();
   info.handle = handle;
   info.sendFlag = ENetSocketReceiveFlag_Free;
   info.sendTotal = 0;
   info.receiveFlag = ENetSocketSendFlagFree;
   info.receiveTotal = 0;
   info.createDateTime = now;
   info.updateDateTime = now;
   return ETrue;
}

//============================================================
// <T>断开网络端口。</T>
//
// @param info 网络信息
// @return 处理结果
//============================================================
TBool RNetSocket::Disconnect(SNetSocketInfo& info){
   TUint32 handle = info.handle;
   // 关闭已经打开的链接句柄
   MO_STATIC_DEBUG(TC("Disconnect net socket. (handle=%d, host=%s:%d)"), handle, info.host, info.port);
#ifdef _MO_WINDOWS
   if(ESuccess != closesocket(handle)){
      MO_STATIC_PERROR(close);
   }
#else
   if(ESuccess != close(handle)){
      MO_STATIC_PERROR(close);
   }
#endif // _MO_WINDOWS
   return ETrue;
}

MO_NAMESPACE_END
