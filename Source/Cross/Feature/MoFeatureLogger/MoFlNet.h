#ifndef __MO_FL_NET_H__
#define __MO_FL_NET_H__

#ifndef __MO_FL_COMMON_H__
#include "MoFlCommon.h"
#endif // __MO_FL_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
#pragma pack(1)
struct SNetLoggerInfo{
public:
   TUint32 length;
   TUint8 type;
   TUint8 level;
   TUint64 tick;
};
#pragma pack()

//============================================================
// <T>网络日志写出器。</T>
//
// @class
//============================================================
class MO_FL_DECLARE FNetLoggerWriter :
      public FInstance,
      public ILoggerWriter
{
   MO_CLASS_DECLARE_INHERITS(FNetLoggerWriter, FInstance);
protected:
   TInt _code;
   TThreadSection _section;
   FBytes* _pData;
   FByteStream* _pBuffer;
   FNetClientSocket* _pSocket;
public:
   FNetLoggerWriter();
   MO_ABSTRACT( ~FNetLoggerWriter() );
public:
   //------------------------------------------------------------
   // <T>获得主机。</T>
   MO_INLINE TCharC* Host(){
      return _pSocket->Host();
   }
   //------------------------------------------------------------
   // <T>设置主机。</T>
   MO_INLINE void SetHost(TCharC* pHost){
      _pSocket->SetHost(pHost);
   }
   //------------------------------------------------------------
   // <T>获得端口。</T>
   MO_INLINE TInt Port(){
      return _pSocket->Port();
   }
   //------------------------------------------------------------
   // <T>获得端口。</T>
   MO_INLINE void SetPort(TInt port){
      _pSocket->SetPort(port);
   }
public:
   MO_OVERRIDE TResult Open();
   MO_OVERRIDE TResult Create();
   MO_OVERRIDE TInt Code();
   MO_OVERRIDE TResult Write(TDateTime time, TCharC* pMessage, TInt length);
   MO_OVERRIDE TResult Close();
public:
   MO_OVERRIDE TResult Refresh();
   MO_OVERRIDE TResult Flush();
};
//------------------------------------------------------------
typedef MO_FL_DECLARE GPtrs<FNetLoggerWriter> GNetLoggerWriterPtrs;

//============================================================
// <T>网络日志控制台。</T>
//
// @history 140413 MAOCY 创建
//============================================================
class MO_FL_DECLARE FNetLoggerConsole : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FNetLoggerConsole, FConsole);
protected:
   GNetLoggerWriterPtrs _writers;
public:
   FNetLoggerConsole();
   MO_ABSTRACT ~FNetLoggerConsole();
public:
   //------------------------------------------------------------
   // <T>获得输出器集合。</T>
   MO_INLINE GNetLoggerWriterPtrs& Writers(){
      return _writers;
   }
public:
   void Register(FNetLoggerWriter* pWriter);
   void Unregister(FNetLoggerWriter* pWriter);
public:
   MO_OVERRIDE TResult Startup();
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Shutdown();
};

MO_NAMESPACE_END

#endif // __MO_FL_NET_H__
