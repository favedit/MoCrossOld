#ifndef __MO_CR_NET_LINKER_H__
#define __MO_CR_NET_LINKER_H__

#ifndef __MO_CM_NET_H__
#include <MoCmNet.h>
#endif // __MO_CM_NET_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

#ifndef __MO_CR_MODULE_H__
#include "MoCrModule.h"
#endif // __MO_CR_MODULE_H__

MO_NAMESPACE_BEGIN

//============================================================
class TNetRouter;
class TNetTransfer;

//============================================================
// <T>端口链接器接口。</T>
//
// @history 120413 MAOCY 创建
//============================================================
class MO_CR_DECLARE INetSocketLinker{
public:
   MO_ABSTRACT ~INetSocketLinker(){
   }
public:
   MO_VIRTUAL TBool OnSocketConnect() = 0;
   MO_VIRTUAL TBool OnSocketDisconnect() = 0;
public:
   MO_VIRTUAL TInt SocketType() = 0;
};

//============================================================
// <T>端口链接器基础类。</T>
//
// @history 120413 MAOCY 创建
//============================================================
class MO_CR_DECLARE MNetSocketLinker : public INetSocketLinker{
protected:
   TInt _socketType;
   TInt _port;
public:
   MNetSocketLinker();
   MO_ABSTRACT ~MNetSocketLinker() = 0;
public:
   MO_OVERRIDE TBool OnSocketConnect() = 0;
   MO_OVERRIDE TBool OnSocketDisconnect() = 0;
public:
   MO_ABSTRACT TCharC* SocketName() = 0;
   MO_OVERRIDE TInt SocketType() = 0;
};

//============================================================
// <T>端口链接器。</T>
//
// @history 100312 MAOCY 创建
//============================================================
class MO_CR_DECLARE SNetSocketLinker{
protected:
   TBool _connected;
   INetSocketLinker* _pLinker;
public:
   //------------------------------------------------------------
   // <T>构造端口链接器。</T>
   SNetSocketLinker(){
      _connected = EFalse;
      MO_CLEAR(_pLinker);
   }
public:
   //------------------------------------------------------------
   // <T>获得是否链接。</T>
   MO_INLINE TBool IsConnected(){
      return _connected;
   }
   //------------------------------------------------------------
   // <T>设置是否链接。</T>
   MO_INLINE void SetConnected(TBool connected){
      _connected = connected;
   }
   //------------------------------------------------------------
   // <T>判断是否含有网络关联器。</T>
   MO_INLINE TBool HasLinker(){
      return (NULL != _pLinker);
   }
   //------------------------------------------------------------
   // <T>获得网络关联器。</T>
   MO_INLINE INetSocketLinker* Get(){
      return _pLinker;
   }
public:
   //------------------------------------------------------------
   // <T>连接网络关联器。</T>
   TBool Link(INetSocketLinker* pLinker){
      MO_ASSERT(pLinker);
      // 设置属性
      _connected = ETrue;
      // 关连链接
      TBool result = pLinker->OnSocketConnect();
      _pLinker = pLinker;
      return result;
   }
   //------------------------------------------------------------
   // <T>断开网络关联器。</T>
   TBool Unlink(){
      // 设置属性
      _connected = EFalse;
      // 断开链接
      TBool result = EFalse;
      if(NULL != _pLinker){
         result = _pLinker->OnSocketDisconnect();
      }
      MO_CLEAR(_pLinker);
      return result;
   }
};

//============================================================
// <T>端口链接存储器。</T>
// <P>使用端口进行索引。</P>
//
// @history 100312 MAOCY 创建
// @history 100722 MAOCY 使用端口进行索引
//============================================================
class MO_CR_DECLARE FNetSocketLinkerStorage : public FModule{
protected:
   SNetSocketLinker _linkers[MO_NET_SOCKET_MAX_COUNT];
public:
   FNetSocketLinkerStorage();
   MO_ABSTRACT ~FNetSocketLinkerStorage();
public:
   SNetSocketLinker* Get(TInt index);
public:
   SNetSocketLinker* Link(TInt index);
   SNetSocketLinker* LinkTransfer(TNetTransfer* pTransfer);
   SNetSocketLinker* Unlink(TInt index);
   SNetSocketLinker* UnlinkTransfer(TNetTransfer* pTransfer);
};

//============================================================
// <T>端口链接存储模块。</T>
//
// @history 100312 MAOCY 创建
//============================================================
class MO_CR_DECLARE FNetSocketLinkerModule : public FNetSocketLinkerStorage{
public:
   FNetSocketLinkerModule();
   MO_ABSTRACT( ~FNetSocketLinkerModule() );
};

//============================================================
// <T>端口链接存储管理器。</T>
//============================================================
class MO_CR_DECLARE RNetSocketLinkerModule : public RModuleSingleton<FNetSocketLinkerModule>{
};

MO_NAMESPACE_END

#endif // __MO_CR_NET_LINKER_H__
