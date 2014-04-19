#include <fcntl.h>
#ifdef _MO_LINUX
#include <sys/socket.h>
#include <arpa/inet.h>
#endif
#include "MoCmNet.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化网路端口。</T>
//============================================================
MNetSocket::MNetSocket(){
   MO_CLEAR(_pConnection);
   _isConnected = EFalse;
   _isBlock = ETrue;
}

//============================================================
// <T>析构网路端口。</T>
//============================================================
MNetSocket::~MNetSocket(){
}

//============================================================
// <T>判断是否链接。</T>
//
// @return 是否链接
//============================================================
TBool MNetSocket::IsConnected(){
   return _isConnected;
}

//============================================================
// <T>获得网路链接。</T>
//
// @return 网路链接
//============================================================
INetConnection* MNetSocket::Connection(){
   return _pConnection;
}

//============================================================
// <T>设置链接。</T>
//
// @param pConnection 链接
//============================================================
void MNetSocket::SetConnection(INetConnection* pConnection){
   _pConnection = pConnection;
}

//============================================================
// <T>获得发送标志。</T>
//
// @return 发送标志
//============================================================
TBool MNetSocket::SendFlag(){
   return Info()->sendFlag;
}

//============================================================
// <T>设置发送标志。</T>
//
// @param flag 发送标志
//============================================================
void MNetSocket::SetSendFlag(TBool flag){
   Info()->sendFlag = flag;
}

//============================================================
// <T>获得网路端口句柄。</T>
//
// @return 端口句柄
//============================================================
TSocket MNetSocket::Handle(){
   return Info()->handle;
}

//============================================================
// <T>设置句柄。</T>
//
// @param handle 句柄
//============================================================
void MNetSocket::SetHandle(TSocket handle){
   Info()->handle = handle;
   _isConnected = ETrue;
}

//============================================================
// <T>获得网路主机名称。</T>
//
// @return 主机名称
//============================================================
TCharC* MNetSocket::Host(){
   return _host;
}

//============================================================
// <T>获得链接地址。</T>
//
// @return 链接地址
//============================================================
TUint32 MNetSocket::Ip(){
   return Info()->ip;
}

//============================================================
// <T>设置主机名称。</T>
//
// @param host 主机名称
//============================================================
void MNetSocket::SetHost(TCharC* pHost){
   _host.Assign(pHost);
}

//============================================================
// <T>获得链接端口。</T>
//
// @return 链接端口
//============================================================
TUint16 MNetSocket::Port(){
   return Info()->port;
}

//============================================================
// <T>设置端口。</T>
//
// @param port 端口
//============================================================
void MNetSocket::SetPort(TUint16 port){
   Info()->port = port;
}

//============================================================
// <T>获得创建时间。</T>
//
// @return 创建时间
//============================================================
TDateTime MNetSocket::CreateDateTime(){
   return Info()->createDateTime;
}

//============================================================
// <T>设置创建日期。</T>
//
// @param datetime 创建日期
//============================================================
void MNetSocket::SetCreateDateTime(TDateTime datetime){
   Info()->createDateTime = datetime;
}

//============================================================
// <T>设置允许重用句柄。</T>
//
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool MNetSocket::SetReuseAddress(TBool flag){
   SNetSocketInfo* pInfo = Info();
   TSocket handle = pInfo->handle;
   TUint32 value = flag;
   if(EError == setsockopt(handle, SOL_SOCKET, SO_REUSEADDR, (PChar8)&value, sizeof(TUint32))){
      MO_PERROR(setsockopt);
      return EFalse;
   }
   MO_NET_DEBUG(TC("Set reuse address. (handle=%d, host=%s:%d, flag=%d)"),
         handle, pInfo->host, pInfo->port, flag);
   return ETrue;
}

//============================================================
// <T>设置立刻结束。</T>
//
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool MNetSocket::SetDontLinger(TBool flag){
   SNetSocketInfo* pInfo = Info();
   TSocket handle = pInfo->handle;
   struct linger value;
   value.l_onoff = EFalse;
   value.l_linger = 0;
   if(EError == setsockopt(handle, SOL_SOCKET, SO_LINGER, (PChar8)&value, sizeof(struct linger))){
      MO_PERROR(setsockopt);
      return EFalse;
   }
   MO_NET_DEBUG(TC("Set don't linger. (handle=%d, host=%s:%d, flag=%d)"),
         handle, pInfo->host, pInfo->port, flag);
   return ETrue;
}

//============================================================
// <T>设置数据允许逗留数据。</T>
//
// @param flag 允许逗留
// @param time 逗留时间
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool MNetSocket::SetLinger(TBool flag, TInt timeout){
   SNetSocketInfo* pInfo = Info();
   TSocket handle = pInfo->handle;
   struct linger value;
   value.l_onoff = (TUint16)flag;
   value.l_linger = timeout;
   if(EError == setsockopt(handle, SOL_SOCKET, SO_LINGER, (PChar8)&value, sizeof(struct linger))){
      MO_PERROR(setsockopt);
      return EFalse;
   }
   MO_NET_DEBUG(TC("Set linger. (handle=%d, host=%s:%d, flag=%d, timeout=%d)"),
         handle, pInfo->host, pInfo->port, flag, timeout);
   return ETrue;
}

//============================================================
// <T>设置发送超时。</T>
//
// @param timeout 超时(微秒数：1/1000000秒)
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool MNetSocket::SetSendTimeout(TInt timeout){
   SNetSocketInfo* pInfo = Info();
   TSocket handle = pInfo->handle;
   struct timeval value;
   value.tv_sec = timeout / 1000000;
   value.tv_usec = timeout % 1000000;
   if(EError == setsockopt(handle, SOL_SOCKET, SO_SNDTIMEO, (PChar8)&value, sizeof(struct timeval))){
      MO_PERROR(setsockopt);
      return EFalse;
   }
   MO_NET_DEBUG(TC("Set send timeout. (handle=%d, host=%s:%d, timeout=%d)"),
         handle, pInfo->host, pInfo->port, timeout);
   return ETrue;
}

//============================================================
// <T>设置接收超时。</T>
//
// @param timeout 超时(1/1000秒)
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool MNetSocket::SetReceiveTimeout(TInt timeout){
   SNetSocketInfo* pInfo = Info();
   TSocket handle = pInfo->handle;
   struct timeval value;
   value.tv_sec = timeout / 1000000;
   value.tv_usec = timeout % 1000000;
   if(EError == setsockopt(handle, SOL_SOCKET, SO_RCVTIMEO, (PChar8)&value, sizeof(struct timeval))){
      MO_PERROR(setsockopt);
      return EFalse;
   }
   MO_NET_DEBUG(TC("Set receive timeout. (handle=%d, host=%s:%d, timeout=%d)"),
         handle, pInfo->host, pInfo->port, timeout);
   return ETrue;
}

//============================================================
// <T>设置发送缓冲区大小。</T>
//
// @param size 缓冲区大小
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool MNetSocket::SetSendBufferSize(TInt size){
   SNetSocketInfo* pInfo = Info();
   TSocket handle = pInfo->handle;
   TUint32 value = size;
   TInt result = setsockopt(handle, SOL_SOCKET, SO_SNDBUF, (PChar8)&value, sizeof(TUint32));
   if(result < 0){
      MO_PERROR(setsockopt);
      return EFalse;
   }
   MO_NET_DEBUG(TC("Set send buffer size. (handle=%d, host=%s:%d, size=%d)"),
         handle, pInfo->host, pInfo->port, size);
   return ETrue;
}

//============================================================
// <T>设置接收缓冲区大小。</T>
//
// @param size 缓冲区大小
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool MNetSocket::SetReceiveBufferSize(TInt size){
   SNetSocketInfo* pInfo = Info();
   TSocket handle = pInfo->handle;
   TUint32 value = size;
   if(EError == setsockopt(handle, SOL_SOCKET, SO_RCVBUF, (PChar8)&value, sizeof(TUint32))){
      MO_PERROR(setsockopt);
      return EFalse;
   }
   MO_NET_DEBUG(TC("Set receive buffer size. (handle=%d, host=%s:%d, size=%d)"),
         handle, pInfo->host, pInfo->port, size);
   return ETrue;
}

//============================================================
// <T>设置非堵塞方式。</T>
//
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool MNetSocket::SetNonBlock(){
   SNetSocketInfo* pInfo = Info();
   TSocket handle = pInfo->handle;
#ifdef _MO_WINDOWS
   TDword argp = 1;
   ioctlsocket(handle, FIONBIO, &argp);
#else
   fcntl(handle, F_SETFL, O_NONBLOCK);
#endif // _MO_WINDOWS
   _isBlock = EFalse;
   MO_NET_DEBUG(TC("Set none block. (handle=%d, host=%s:%d)"),
         handle, pInfo->host, pInfo->port);
   return ETrue;
}

//============================================================
// <T>接收数据。</T>
//
// @param pBuffer 数据指针
// @param length 数据长度
// @return 接收长度
//============================================================
TInt MNetSocket::Receive(TByte* pBuffer, TInt length){
   SNetSocketInfo* pInfo = Info();
   TInt result = recv(pInfo->handle, (TChar8*)pBuffer, length, 0);
   if(result < 0){
      TInt code = errno;
      if(ESuccess == code){
         return 0;
      }else if(EINTR == code){
         return 0;
      }else if(EAGAIN == code){
         return 0;
      }else if(EBADF == code){
         // TODO:
         return 0;
      }else if(ESuccess != code){
         MO_ERROR(TC("Receive data failure. (handle=%d, host=%s:%d)"),
               pInfo->handle, pInfo->host, pInfo->port);
      }
   }else if(result > 0){
       pInfo->receiveTotal += result;
       pInfo->receiveDateTime = RDateTime::Current();
   }
   return result;
}

//============================================================
// <T>发送数据。</T>
//
// @param pBuffer 数据指针
// @param length 数据长度
// @return 发送长度
//============================================================
TInt MNetSocket::Send(TByteC* pBuffer, TInt length){
   MO_ASSERT(pBuffer);
   MO_ASSERT(length > 0);
   SNetSocketInfo* pInfo = Info();
   TSocket handle = pInfo->handle;
   TInt result = send(handle, (TChar8C*)pBuffer, length, 0);
   if(result < 0){
      // 错误处理
      TInt code = errno;
      if((EINTR == code) || (EAGAIN == code) || (EWOULDBLOCK == code)){
         // 不处理
      }else if((ENOTSOCK == code) || (ENOENT == code) || (EPIPE == code) ||
            (ECONNRESET == code) || (EDESTADDRREQ == code) || (EACCES == code)){
         // 失败情况
         MO_ERROR(TC("Send data failure. (handle=%d, host=%s:%d, error_code=%d)"),
               handle, pInfo->host, pInfo->port, code);
      }else{
         MO_ERROR(TC("Send data error. (handle=%d, host=%s:%d, error_code=%d)"),
               handle, pInfo->host, pInfo->port, code);
      }
   }else if(result > 0){
       pInfo->sendTotal += result;
       pInfo->sendDateTime = RDateTime::Current();
   }
   return result;
}

//============================================================
// <T>关闭链接。</T>
//
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool MNetSocket::Close(){
   _isConnected = EFalse;
   TSocket handle = Handle();
   if(INVALID_SOCKET != handle){
#ifdef _MO_WINDOWS
      closesocket(handle);
#else
      TInt result = close(handle);
      if(0 != result){
         MO_PERROR(close);
      }
#endif
      handle = INVALID_SOCKET;
      return ETrue;
   }
   return EFalse;
}

MO_NAMESPACE_END
