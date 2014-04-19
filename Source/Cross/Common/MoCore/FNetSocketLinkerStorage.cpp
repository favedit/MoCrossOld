#include "MoCrNetMessage.h"
#include "MoCrNetLinker.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>往消息管道内输出一个消息。</T>
//
// @return 执行结果
//============================================================
FNetSocketLinkerStorage::FNetSocketLinkerStorage(){
}

//============================================================
// <T>往消息管道内输出一个消息。</T>
//
// @return 执行结果
//============================================================
FNetSocketLinkerStorage::~FNetSocketLinkerStorage(){
}

//============================================================
// <T>根据索引获得网络链接器。</T>
//
// @param index 索引
// @return 网络链接器
//============================================================
SNetSocketLinker* FNetSocketLinkerStorage::Get(TInt index){
   MO_ASSERT_RANGE(index, 0, MO_NET_SOCKET_MAX_COUNT);
   return &_linkers[index];
}

//============================================================
// <T>关联指定索引的网络链接器。</T>
//
// @param index 索引
// @return 网络链接器
//============================================================
SNetSocketLinker* FNetSocketLinkerStorage::Link(TInt index){
   // 获得网络链接器
   MO_ASSERT_RANGE(index, 0, MO_NET_SOCKET_MAX_COUNT);
   SNetSocketLinker& linker = _linkers[index];
   // 设置链接状态
   linker.SetConnected(ETrue);
   return &linker;
}

//============================================================
// <T>关联指定传输对象的网络链接器。</T>
//
// @param pTransfer 传输对象
// @return 网络链接器
//============================================================
SNetSocketLinker* FNetSocketLinkerStorage::LinkTransfer(TNetTransfer* pTransfer){
   // 获得网络链接器
   MO_ASSERT(pTransfer);
   TInt index = pTransfer->TransferHead().Socket().Index();
   // 链接关联
   return Link(index);
}

//============================================================
// <T>断开指定端口的网络链接器。</T>
//
// @param port 端口
// @return 网络链接器
//============================================================
SNetSocketLinker* FNetSocketLinkerStorage::Unlink(TInt index){
   // 获得网络链接器
   MO_ASSERT_RANGE(index, 0, MO_NET_SOCKET_MAX_COUNT);
   SNetSocketLinker& linker = _linkers[index];
   // 设置链接状态
   linker.SetConnected(EFalse);
   return &linker;
}

//============================================================
// <T>断开指定传输对象的网络链接器。</T>
//
// @param pTransfer 传输对象
// @return 网络链接器
//============================================================
SNetSocketLinker* FNetSocketLinkerStorage::UnlinkTransfer(TNetTransfer* pTransfer){
   // 获得索引位置
   MO_ASSERT(pTransfer);
   TInt index = pTransfer->TransferHead().Socket().Index();
   // 断开关联
   return Unlink(index);
}

MO_NAMESPACE_END
