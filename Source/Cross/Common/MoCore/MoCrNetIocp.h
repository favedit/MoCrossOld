#ifndef __MO_CR_NET_IOCP_H__
#define __MO_CR_NET_IOCP_H__
//------------------------------------------------------------
#ifndef __MO_COMMON_H__
#include <MoCommon.h>
#endif // __MO_COMMON_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

#ifndef __MO_CR_NET_SOCKET_H__
#include "MoCrNetSocket.h"
#endif // __MO_CR_NET_SOCKET_H__

MO_NAMESPACE_BEGIN

#ifdef _MO_WINDOWS

class FNetIocpSocket;

////============================================================
//#define MO_IOCP_BUFFER_MAX_SIZE 8192

//============================================================
// <T>完成端口操作类型枚举。</T>
//
// @history 091218 MAOCY 创建
//============================================================
enum ENetIocpOperate{
   ENetIocpOperate_Accept = 1,
   ENetIocpOperate_Read = 2,
   ENetIocpOperate_Write = 3,
};

//============================================================
// <T>完成端口查询信息。</T>
//============================================================
struct SIocpData{
   // 预定义头
   OVERLAPPED Overlapped;
   // 端口句柄
   FNetIocpSocket* pSocket;
   char *buff; // I/O操作使用的缓冲区 
   int nLen; // buff缓冲区（使用的）大小 
   ULONG nSequenceNumber; // 此I/O的序列号 
   ENetIocpOperate OperateCode; // 操作类型 
   SIocpData *pNextData; 
   //TByte Buffer[MO_IOCP_BUFFER_MAX_SIZE];
   //WSABUF WsaBuffer;
   //TInt TotalBytes;
   //TInt SendBytes;
   ////ENetIocpOperate OperateCode;
   //PPtr ClientSocket;
   //FNetSocket* pSocket;
};

//============================================================
// <T>完成端口的客户网络链接。</T>
//
// @history 091218 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetIocpSocket : public FNetBufferedSocket{
protected:
   SIocpData _data;
public:
   FNetIocpSocket();
public:
   SIocpData& Data();
public:
   MO_OVERRIDE TBool Startup();
   MO_OVERRIDE TBool Shutdown();
};
//------------------------------------------------------------
typedef MO_CR_DECLARE FList<FNetIocpSocket*> FNetIocpSocketList;

//============================================================
// <T>网络服务端链接。</T>
//
// @class
// @source MNetSocket.cpp
// @history 091231 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetIocpServerSocket : public FNetServerSocket{
public:
   FNetIocpServerSocket();
   MO_ABSTRACT ~FNetIocpServerSocket();
public:
   MO_OVERRIDE TResult Bind();
   MO_OVERRIDE TResult Listen();
   MO_OVERRIDE TResult Connect();
   MO_OVERRIDE TResult Accept(SNetSocketInfo& info);
   MO_OVERRIDE TResult Disconnect();
};

//============================================================
// <T>网络完成端口服务器。</T>
//
// @history 091218 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetIocp :
      public FObject,
      public INetSocketPool{
protected:
   TAny* _pHandle;
   TInt _count;
public:
   FNetIocp();
   MO_ABSTRACT ~FNetIocp();
public:
   void Setup();
public:
   TAny* NativeHandle(){
      return _pHandle;
   }
public: // Inherits: INetSocketPool
   MO_OVERRIDE TInt Handle();
   MO_OVERRIDE TInt Count();
   MO_OVERRIDE TBool Add(INetSocket* pSocket, TUint flag);
   MO_OVERRIDE TBool Modify(INetSocket* pSocket, TUint flag);
   MO_OVERRIDE TBool Remove(INetSocket* pSocket, TUint flag);
};

////============================================================
//// <T>网络完成端口服务器。</T>
////
//// @history 091218 MAOCY 创建
////============================================================
//class MO_CR_DECLARE FNetIocpServerThread :
//      public FThread{
//protected:
//   INetServer* _pNetServer;
//public:
//   MO_OVERRIDE( FNetIocpServerThread(INetServer* pNetServer) );
//public:
//   MO_OVERRIDE( TInt Process() );
//};
//
////============================================================
//// <T>完成端口访问线程。</T>
////
//// @history 091217 MAOCY 创建
////============================================================
//class MO_CR_DECLARE FNetIocpQueryThread :
//      public FThread{
//protected:
//   INetServer* _pNetServer;
//public:
//   MO_OVERRIDE( FNetIocpQueryThread(INetServer* pNetServer) );
//public:
//   void SetServer(INetServer* pNetServer);
//   MO_OVERRIDE( TInt Process() );
//};
//
////============================================================
//// <T>完成端口访问线程。</T>
////
//// @history 091217 MAOCY 创建
////============================================================
//class MO_CR_DECLARE FNetIocpSendThread :
//      public FThread{
//protected:
//   INetServer* _pNetServer;
//   //PULONG_PTR _completionKey;
//   //OVERLAPPED* _pOverlap;
//   SIocpOverlapped* _pIocpOverlap;
//public:
//   MO_OVERRIDE( FNetIocpSendThread(INetServer* pNetServer) );
//public:
//   void SetServer(INetServer* pNetServer);
//   MO_OVERRIDE( TInt Process() );
//};

#endif // _MO_WINDOWS

MO_NAMESPACE_END

#endif // __MO_CR_NET_IOCP_H__
