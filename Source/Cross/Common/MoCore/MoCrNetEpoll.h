#ifndef __MO_CR_NET_EPOLL_H__
#define __MO_CR_NET_EPOLL_H__
//------------------------------------------------------------
#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_THREAD_H__
#include "MoCmThread.h"
#endif // __MO_CM_THREAD_H__

#ifndef __MO_CM_NET_H__
#include "MoCmNet.h"
#endif // __MO_CM_NET_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

//------------------------------------------------------------
#ifdef _MO_LINUX
#ifndef __CYGWIN__
#include <sys/epoll.h>
#endif
#endif // _MO_LINUX

#define MO_NET_PROCESS_COUNT 2

MO_NAMESPACE_BEGIN

//============================================================
// EPOLLOUT：表示对应的文件描述符可以写；
// EPOLLPRI：表示对应的文件描述符有紧急的数据可读；
// EPOLLERR：表示对应的文件描述符发生错误；
// EPOLLHUP：表示对应的文件描述符被挂断；
// EPOLLET：表示对应的文件描述符有事件发生；
class FNetEpollConnection;

//============================================================
// <T>EPOLL端口池。</T>
//
// @history 091231 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetEpoll :
      public FObject,
      public INetSocketPool{
protected:
   TInt _processSize;
   TInt _timeout;
   TInt _handle;
   TInt _count;
#ifdef _MO_LINUX
#ifndef __CYGWIN__
   epoll_event* _pEvents;
#endif // __CYGWIN__
#endif // _MO_LINUX
public:
   FNetEpoll();
   MO_ABSTRACT ~FNetEpoll();
public: // Inherits: INetSocketPool
   MO_OVERRIDE TInt Handle();
   MO_OVERRIDE TInt Count();
   MO_OVERRIDE TBool Add(INetSocket* pSocket, TUint flag);
   MO_OVERRIDE TBool Modify(INetSocket* pSocket, TUint flag);
   MO_OVERRIDE TBool Remove(INetSocket* pSocket, TUint flag);
public:
   TBool Create(TInt size);
};

MO_NAMESPACE_END

#endif // __MO_CR_NET_EPOLL_H__
