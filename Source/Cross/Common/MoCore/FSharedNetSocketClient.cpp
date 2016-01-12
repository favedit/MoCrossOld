#include "MoCrNet.h"
#include "MoCrMessage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造共享网络链接的客户端。</T>
//
// @return 服务器的实例
//============================================================
FSharedNetSocketClient::FSharedNetSocketClient(){
   MO_CLEAR(_gClient);
}

//============================================================
// <T>析构共享网络链接的客户端。</T>
//============================================================
FSharedNetSocketClient::~FSharedNetSocketClient(){
}

//============================================================
// <T>计算共享内存尺寸。</T>
//
// @return 共享内存尺寸
//============================================================
TSize FSharedNetSocketClient::CalculateCapacity(){
   return sizeof(SNetSocketClient);
}

//============================================================
// <T>初始化共享内存。</T>
//============================================================
void FSharedNetSocketClient::OnSharedInitialize(){
   RType<SNetSocketClient>::Clear(_gClient);
   _gClient->id = -1;
}

//============================================================
// <T>分配共享内存。</T>
//
// @param segment 共享内存段
//============================================================
void FSharedNetSocketClient::OnSharedLink(TShareSegment& segment){
   _gClient = segment.TypeAlloc<SNetSocketClient>();
}

//============================================================
// <T>获得共享内存尺寸。</T>
//
// @return 共享内存尺寸
//============================================================
TSize FSharedNetSocketClient::SharedCapacity(){
   return sizeof(SNetSocketClient);
}

//============================================================
// <T>网络链接处理。</T>
//
// @return 处理结果
//============================================================
TBool FSharedNetSocketClient::OnSocketConnect(){
   return MNetSocketLinker::OnSocketConnect();
}

//============================================================
// <T>网络断开处理。</T>
//
// @return 处理结果
//============================================================
TBool FSharedNetSocketClient::OnSocketDisconnect(){
   return MNetSocketLinker::OnSocketDisconnect();
}

//============================================================
// <T>关联链接。</T>
//
// @param pTransfer 传输对象
//============================================================
void FSharedNetSocketClient::LinkTransfer(TNetTransfer* pTransfer){
   MO_ASSERT(pTransfer);
   TNetTransferHead& head = pTransfer->TransferHead();
   _gClient->connected = ETrue;
   _gClient->socketTarget.Set(head.Socket());
}

//============================================================
// <T>重置数据。</T>
//============================================================
void FSharedNetSocketClient::Reset(){
   RType<SNetSocketClient>::Clear(_gClient);
}

//============================================================
// <T>断开链接。</T>
//============================================================
void FSharedNetSocketClient::Unlink(){
   RType<SNetSocketClient>::Clear(_gClient);
}

//============================================================
// <T>获得运行时信息。</T>
//
// @param pDump 输出数据
// @param capacity 输出长度
// @return 运行信息
//============================================================
TCharC* FSharedNetSocketClient::DumpSocket(TChar* pDump, TSize capacity){
   TStringRefer dump(pDump, capacity);
   dump.AppendFormat(TC("host=%s:%d, socket=%d:%d"),
         (TCharC*)_gClient->host, _gClient->port,
         _gClient->socketTarget.Index(), _gClient->socketTarget.Serial());
   return (TCharC*)dump;
}

MO_NAMESPACE_END
