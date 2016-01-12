#include "MoCrNetLinker.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造端口链接器基础类。</T>
//============================================================
MNetSocketLinker::MNetSocketLinker(){
   _socketType = -1;
}

//============================================================
// <T>析构端口链接器基础类。</T>
//============================================================
MNetSocketLinker::~MNetSocketLinker(){
}

//============================================================
// <T>网络链接处理。</T>
//
// @return 处理结果
//============================================================
TBool MNetSocketLinker::OnSocketConnect(){
   MO_DEBUG(TC("Socket linker connected. (type=%s)"), SocketName());
   return ETrue;
}

//============================================================
// <T>网络断开处理。</T>
//
// @return 处理结果
//============================================================
TBool MNetSocketLinker::OnSocketDisconnect(){
   MO_DEBUG(TC("Socket linker disconnected. (type=%s)"), SocketName());
   return ETrue;
}

//============================================================
// <T>获得端口名称。</T>
//
// @return 名称
//============================================================
TCharC* MNetSocketLinker::SocketName(){
   return TC("SocketLinker");
}

//============================================================
// <T>获得端口类型。</T>
//
// @return 类型
//============================================================
TInt MNetSocketLinker::SocketType(){
   return _socketType;
}

MO_NAMESPACE_END
