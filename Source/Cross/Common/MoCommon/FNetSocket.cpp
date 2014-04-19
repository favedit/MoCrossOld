#include <fcntl.h>
#ifdef _MO_LINUX
#include <arpa/inet.h>
#include <unistd.h>
#endif
#include "MoCmNet.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FNetSocket, FInstance);

//============================================================
// <T>构造网路端口。</T>
//============================================================
FNetSocket::FNetSocket(){
   RType<SNetSocketInfo>::Clear(&_info);
}

//============================================================
// <T>构造网路端口。</T>
//
// @param host 主机名称
// @param port 端口
// @param handle 句柄
//============================================================
FNetSocket::FNetSocket(TCharC* pHost, TInt port, TSocket handle){
   _host.Assign(pHost);
   _info.handle = handle;
   _info.port = port;
   _isConnected = ETrue;
}

//============================================================
// <T>析构网路端口。</T>
//============================================================
FNetSocket::~FNetSocket(){
}

//============================================================
// <T>获得网络端口信息。</T>
//
// @return 网络端口信息
//============================================================
SNetSocketInfo* FNetSocket::Info(){
   return &_info;
}

//============================================================
// <T>接收端口数据。</T>
//
// @param pInfo 接收信息
// @return 处理结果
//============================================================
TResult FNetSocket::Accept(SNetAcceptInfo* pInfo){
   MO_CHECK(pInfo, return ENull);
   _host.Assign(pInfo->host);
   _info.handle = pInfo->handle;
   _info.port = pInfo->port;
   _isConnected = ETrue;
   return ESuccess;
}

MO_NAMESPACE_END
