#include "MoCrNetEpoll.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造EPOLL端口池。</T>
//
// @return EPOLL端口池
//============================================================
FNetEpoll::FNetEpoll(){
   _processSize = 0;
   _timeout = 0;
   _handle = 0;
   _count = 0;
#ifdef _MO_LINUX
#ifndef __CYGWIN__
   MO_CLEAR(_pEvents);
#endif
#endif
}

//============================================================
// <T>析构EPOLL端口池。</T>
//============================================================
FNetEpoll::~FNetEpoll(){
}

//============================================================
// <T>获得句柄。</T>
//
// @return 句柄
//============================================================
TInt FNetEpoll::Handle(){
   return _handle;
}

//============================================================
// <T>获得个数。</T>
//
// @return 个数
//============================================================
TInt FNetEpoll::Count(){
   return _count;
}

//============================================================
// <T>增加网络句柄的设置。</T>
//
// @param pSocket 网络端口
// @param flag 标志
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool FNetEpoll::Add(INetSocket* pSocket, TUint flag){
   // 获得句柄
   MO_ASSERT(pSocket);
#ifdef _MO_LINUX
#ifndef __CYGWIN__
   TSocket handle = pSocket->Handle();
   // 设置事件
   epoll_event event;
   RType<epoll_event>::Clear(&event);
   event.events = flag;
   event.data.ptr = pSocket;
   // 增加事件
   TInt result = epoll_ctl(_handle, EPOLL_CTL_ADD, handle, &event);
   if(result < 0){
      MO_ERROR("Epoll add socket error. (epoll=%d, socket=0x%08X, handle=%d)",
            _handle, pSocket, pSocket->Handle());
      MO_PFATAL(epoll_ctl);
      return EFalse;
   }
#endif
#endif
   _count++;
   return ETrue;
}

//============================================================
// <T>修改网络句柄的设置。</T>
//
// @param pSocket 网络端口
// @param flag 标志
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool FNetEpoll::Modify(INetSocket* pSocket, TUint flag){
#ifdef _MO_LINUX
   // 获得句柄
   MO_ASSERT(pSocket);
   // 设置事件
#ifndef __CYGWIN__
   TSocket handle = pSocket->Handle();
   epoll_event event;
   RType<epoll_event>::Clear(&event);
   event.events = flag;
   event.data.ptr = pSocket;
   // 修改事件
   TInt result = epoll_ctl(_handle, EPOLL_CTL_MOD, handle, &event);
   if(result < 0){
      MO_ERROR("Epoll modify socket error. (epoll=%d, socket=0x%08X, handle=%d)",
            _handle, pSocket, pSocket->Handle());
      MO_PFATAL(epoll_ctl);
      return EFalse;
   }
#endif
#endif
   return ETrue;
}

//============================================================
// <T>删除网络句柄的设置。</T>
//
// @param pSocket 网络端口
// @param flag 标志
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool FNetEpoll::Remove(INetSocket* pSocket, TUint flag){
#ifdef _MO_LINUX
   // 获得句柄
   MO_ASSERT(pSocket);
   // 设置事件
#ifndef __CYGWIN__
   TSocket handle = pSocket->Handle();
   epoll_event event;
   RType<epoll_event>::Clear(&event);
   event.events = flag;
   event.data.ptr = pSocket;
   // 删除事件
   TInt result = epoll_ctl(_handle, EPOLL_CTL_DEL, handle, &event);
   if(result < 0){
      MO_ERROR("Epoll remove socket error. (epoll=%d, socket=0x%08X, handle=%d)",
            _handle, pSocket, pSocket->Handle());
      MO_PFATAL(epoll_ctl);
      return EFalse;
   }
#endif
#endif
   _count--;
   return ETrue;
}

//============================================================
// <T>创建完成端口。</T>
//
// @param size 处理大小
// @return
//    <L value='ETrue'>成功</L>
//    <L value='EFalse'>失败</L>
//============================================================
TBool FNetEpoll::Create(TInt size){
#ifdef _MO_LINUX
   _processSize = size;
#ifndef __CYGWIN__
   _handle = epoll_create(size);
   if(EError == _handle){
      MO_PERROR(epoll_create);
      return EFalse;
   }
#endif
#endif
   return ETrue;
}

MO_NAMESPACE_END
