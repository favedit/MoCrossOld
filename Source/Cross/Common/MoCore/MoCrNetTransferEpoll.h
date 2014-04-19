#ifndef __MO_CR_NET_TRANSFER_EPOLL_H__
#define __MO_CR_NET_TRANSFER_EPOLL_H__
//------------------------------------------------------------
#ifndef __MO_COMMON_H__
#include <MoCommon.h>
#endif // __MO_COMMON_H__

#ifndef __MO_CR_NET_TRANSFER_H__
#include "MoCrNetTransfer.h"
#endif // __MO_CR_NET_TRANSFER_H__

MO_NAMESPACE_BEGIN

#ifdef _MO_LINUX

//============================================================
// <T>网络链接监听线程。</T>
//============================================================
class MO_CR_DECLARE FNetEpollTransferServerThread : public FNetTransferServerThread{
public:
   FNetEpollTransferServerThread();
   MO_ABSTRACT ~FNetEpollTransferServerThread();
public:
   MO_OVERRIDE TInt Process();
};

//============================================================
// <T>网络数据接收线程。</T>
//============================================================
class MO_CR_DECLARE FNetEpollTransferReceiveThread : public FNetTransferReceiveThread{
public:
   FNetEpollTransferReceiveThread();
   MO_ABSTRACT ~FNetEpollTransferReceiveThread();
public:
   MO_OVERRIDE TInt Process();
};

//============================================================
// <T>网络数据发送线程。</T>
//============================================================
class MO_CR_DECLARE FNetEpollTransferSendThread : public FNetTransferSendThread{
public:
   FNetEpollTransferSendThread();
   MO_ABSTRACT ~FNetEpollTransferSendThread();
public:
   MO_OVERRIDE TInt Process();
};

//============================================================
// <T>网络传输服务</T>
//
// @history 100312 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetEpollTransferService : public FNetTransferService{
public:
   FNetEpollTransferService();
   MO_ABSTRACT( ~FNetEpollTransferService() );
};

#endif // _MO_LINUX

MO_NAMESPACE_END

#endif // __MO_CR_NET_TRANSFER_EPOLL_H__
