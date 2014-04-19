#ifndef __MO_EG_NET_H__
#define __MO_EG_NET_H__

#include "MoEgCommon.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>���緢���̡߳�</T>
//============================================================
class FNetSendThread : public FThread{
protected:
public:
   FNetSendThread();
   MO_ABSTRACT ~FNetSendThread();
public:
   MO_OVERRIDE TResult Process();
};

//============================================================
// <T>��������̡߳�</T>
//============================================================
class FNetReceiveThread : public FThread{
protected:
public:
   FNetReceiveThread();
   MO_ABSTRACT ~FNetReceiveThread();
public:
   MO_OVERRIDE TResult Process();
};

//============================================================
// <T>�������̨��</T>
//============================================================
class FNetConsole : public FConsole{
protected:
   TBool _netInitializer;
   FNetClientSocket* _pSocket;
public:
   FNetConsole();
   MO_ABSTRACT ~FNetConsole();
public:
   void Startup();
   void Shutdown();
public:
   TBool Connection();
   TBool Send();
   TBool Receive();
};
//------------------------------------------------------------
typedef RSingleton<FNetConsole> RNetManager;

MO_NAMESPACE_END

#endif // __MO_EG_NET_H__
