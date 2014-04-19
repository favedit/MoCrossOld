#ifndef __MO_CR_NET_TRANSFER_SINGLE_H__
#define __MO_CR_NET_TRANSFER_SINGLE_H__
//------------------------------------------------------------
#ifndef __MO_COMMON_H__
#include <MoCommon.h>
#endif // __MO_COMMON_H__

#ifndef __MO_CR_NET_TRANSFER_H__
#include "MoCrNetTransfer.h"
#endif // __MO_CR_NET_TRANSFER_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>网络链接监听线程。</T>
//============================================================
class MO_CR_DECLARE FNetSingleTransferServerThread : public FNetTransferServerThread{
public:
   FNetSingleTransferServerThread();
   MO_ABSTRACT ~FNetSingleTransferServerThread();
public:
   MO_OVERRIDE TResult Process();
};

//============================================================
// <T>网络数据发送线程。</T>
//============================================================
class MO_CR_DECLARE FNetSingleTransferSendThread : public FNetTransferSendThread{
public:
   FNetSingleTransferSendThread();
   MO_ABSTRACT ~FNetSingleTransferSendThread();
public:
   MO_OVERRIDE TResult Process();
};

//============================================================
// <T>网络数据接收线程。</T>
//============================================================
class MO_CR_DECLARE FNetSingleSocketReceiveThread : public FNetTransferReceiveThread{
protected:
   FNetBufferedSocket* _pSocket;
public:
   FNetSingleSocketReceiveThread();
   MO_ABSTRACT ~FNetSingleSocketReceiveThread();
public:
   //------------------------------------------------------------
   // <T>获得网络链接。</T>
   MO_INLINE FNetBufferedSocket* Socket(){
      return _pSocket;
   }
   //------------------------------------------------------------
   // <T>设置网络链接。</T>
   MO_INLINE void SetSocket(FNetBufferedSocket* pSocket){
      _pSocket = pSocket;
   }
public:
   MO_OVERRIDE TResult Process();
};

//============================================================
// <T>网络数据发送线程。</T>
//============================================================
class MO_CR_DECLARE FNetSingleSocketSendThread : public FNetTransferSendThread{
protected:
   FNetBufferedSocket* _pSocket;
public:
   FNetSingleSocketSendThread();
   MO_ABSTRACT ~FNetSingleSocketSendThread();
public:
   //------------------------------------------------------------
   // <T>获得网络链接。</T>
   MO_INLINE FNetBufferedSocket* Socket(){
      return _pSocket;
   }
   //------------------------------------------------------------
   // <T>设置网络链接。</T>
   MO_INLINE void SetSocket(FNetBufferedSocket* pSocket){
      _pSocket = pSocket;
   }
public:
   MO_OVERRIDE TResult Process();
};

//============================================================
// <T>网络传输服务</T>
//
// @history 100312 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetSingleTransferService : public FNetTransferService{
public:
   FNetSingleTransferService();
   MO_ABSTRACT( ~FNetSingleTransferService() );
public:
   MO_OVERRIDE TResult OnStartup();
protected:
   MO_OVERRIDE FNetBufferedSocket* InnerAddSocket(SNetSocketInfo& info);
public:
   TBool SendLimitNotify(SNetSocketInfo& info){return EFalse;}
   TBool SendConnectNotify(FNetBufferedSocket* pSocket, TCharC* pHost, TUint16 port){return EFalse;}
   TBool SendDisconnectNotify(FNetBufferedSocket* pSocket, TCharC* pHost, TUint16 port){return EFalse;}
   TBool SendInvalidUnknownNotify(FNetBufferedSocket* pSocket){return EFalse;}
   TBool SendInvalidVersionNotify(FNetBufferedSocket* pSocket){return EFalse;}
   TBool SendInvalidSerialNotify(FNetBufferedSocket* pSocket){return EFalse;}
   TBool SendInvalidUnserialNotify(FNetBufferedSocket* pSocket){return EFalse;}
   TBool SendInvalidFullNotify(FNetBufferedSocket* pSocket){return EFalse;}
};

MO_NAMESPACE_END

#endif // __MO_CR_NET_TRANSFER_SINGLE_H__
