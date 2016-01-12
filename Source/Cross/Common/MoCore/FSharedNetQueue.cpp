#include "MoCrNetMessage.h"
#include "MoCrNetPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
FSharedNetQueue::FSharedNetQueue(){
}

//============================================================
FSharedNetQueue::~FSharedNetQueue(){
};

//============================================================
// <T>压入一个消息。</T>
//
// @param pMessage 消息对象
// @return  处理结果
//============================================================
TBool FSharedNetQueue::PushMessage(TNetMessage* pMessage){
   MO_ASSERT(pMessage);
   // 序列化路由数据
   TInt length;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pMessage->Serialize(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_FATAL(TC("Message serialize failure."));
      return EFalse;
   }
   // 将数据放入管道
   return Push(buffer, length);
}

//============================================================
// <T>压入一个消息。</T>
//
// @param pMessage 消息对象
// @return  处理结果
//============================================================
TBool FSharedNetQueue::PushRouter(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   // 序列化路由数据
   TInt length;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pRouter->Serialize(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_FATAL(TC("Router serialize failure."));
      return EFalse;
   }
   // 将数据放入管道
   return Push(buffer, length);
}

//============================================================
// <T>压入一个消息。</T>
//
// @param pMessage 消息对象
// @return  处理结果
//============================================================
TBool FSharedNetQueue::PushTransfer(TNetTransfer* pTransfer){
   MO_ASSERT(pTransfer);
   // 序列化路由数据
   TInt length = 0;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pTransfer->Serialize(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_FATAL(TC("Transfer serialize failure. (message_code=%d)"), pTransfer->MessageHead().Code());
      return EFalse;
   }
   if(length <= 0){
      MO_FATAL(TC("Transfer serialize failure. (message_code=%d, length=%d)"), pTransfer->MessageHead().Code(), length);
      return EFalse;
   }
   // 消息检查
   TInt blockLength = *(TInt32*)(TAny*)buffer;
   if(blockLength > MO_NETMESSAGE_MAXLENGTH){
      MO_FATAL(TC("Write message length is error. (memory=0x%08X, block_length=%d)"), buffer, blockLength);
   }
   if(length > MO_NETMESSAGE_MAXLENGTH){
      MO_FATAL(TC("Write data length is error. (memory=0x%08X, data_length=%d)"), buffer, length);
   }
   if(blockLength != length){
      MO_FATAL(TC("Write data length is not equals. (memory=0x%08X, block_length=%d, data_length=%d)"), buffer, blockLength, length);
   }
   // 将数据放入管道
   return Push(buffer, length);
}

//============================================================
// <T>压入一个消息。</T>
//
// @param pMessage 消息对象
// @return  处理结果
//============================================================
TBool FSharedNetQueue::TryPushTransfer(TNetTransfer* pTransfer){
   MO_ASSERT(pTransfer);
   // 序列化路由数据
   TInt length = 0;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pTransfer->Serialize(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_WARN(TC("Transfer serialize failure. (message_code=%d)"), pTransfer->MessageHead().Code());
      return EFalse;
   }
   if(length <= 0){
      MO_WARN(TC("Transfer serialize failure. (message_code=%d, length=%d)"), pTransfer->MessageHead().Code(), length);
      return EFalse;
   }
   // 消息检查
   TInt blockLength = *(TInt32*)(TAny*)buffer;
   if(blockLength > MO_NETMESSAGE_MAXLENGTH){
      MO_WARN(TC("Write message length is error. (memory=0x%08X, block_length=%d)"), buffer, blockLength);
   }
   if(length > MO_NETMESSAGE_MAXLENGTH){
      MO_WARN(TC("Write data length is error. (memory=0x%08X, data_length=%d)"), buffer, length);
   }
   if(blockLength != length){
      MO_WARN(TC("Write data length is not equals. (memory=0x%08X, block_length=%d, data_length=%d)"), buffer, blockLength, length);
   }
   // 将数据放入管道
   return TryPush(buffer, length);
}

//============================================================
// <T>以堵塞方式压入一个消息。</T>
//
// @param pMessage 消息对象
// @return  处理结果
//============================================================
TBool FSharedNetQueue::BlockedPushMessage(TNetMessage* pMessage){
   MO_ASSERT(pMessage);
   TBool result = EFalse;
   // 服务线程和收取线程可能同时操作，需要同步锁
   _lockPush.Enter();
   result = PushMessage(pMessage);
   _lockPush.Leave();
   // 根据结果处理
   if(result){
      return ETrue;
   }
   return EFalse;
}

//============================================================
// <T>以堵塞方式压入一个消息。</T>
//
// @param pMessage 消息对象
// @return  处理结果
//============================================================
TBool FSharedNetQueue::BlockedPushRouter(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   TBool result = EFalse;
   // 服务线程和收取线程可能同时操作，需要同步锁
   _lockPush.Enter();
   result = PushRouter(pRouter);
   _lockPush.Leave();
   // 根据结果处理
   if(result){
      return ETrue;
   }
   return EFalse;
}

//============================================================
// <T>以堵塞方式压入一个消息。</T>
//
// @param pMessage 消息对象
// @return  处理结果
//============================================================
TBool FSharedNetQueue::BlockedPushTransfer(TNetTransfer* pTransfer){
   MO_ASSERT(pTransfer);
   TBool result = EFalse;
   // 服务线程和收取线程可能同时操作，需要同步锁
   _lockPush.Enter();
   result = PushTransfer(pTransfer);
   _lockPush.Leave();
   // 根据结果处理
   if(result){
      return ETrue;
   }
   return EFalse;
}

//============================================================
// <T>以堵塞方式压入一个管道数据。</T>
//
// @param pMessage 消息对象
// @return  处理结果
//============================================================
//TBool FSharedNetQueue::BlockedPushPipe(FSharedNetPipe* pPipe){
//   // 获得管道信息
//   TByte* pMemory;
//   TInt total, length, first, last;
//   FSharedQueue::InnerGetInfo(&pMemory, &total, &length, &first, &last);
//   // 测试数据是否可以一次写入
//   TByte* pRead;
//   TInt rtotal, rlength, rfirst, rlast;
//   pPipe->GetInfo(&pRead, &rtotal, &rlength, &rfirst, &rlast);
//   pRead += rfirst;
//   TInt capacity = *(TInt32*)pRead;
//   if(capacity > rlength){
//      return EFalse;
//   }
//   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
//   if(rfirst > rlast) {
//       pPipe->TryRead(buffer, capacity);
//       pRead = buffer;
//   }
//   TInt fill = MO_ALIGN_4(capacity + sizeof(TInt32));
//   TInt remain = (first <= last) ? total - last : first - last;
//   // 向内存上直接复制数据
//   TBool result = EFalse;
//   if(remain >= (fill + MoPipeReserveLength)){
//      pMemory += last;
//      *(TInt32*)pMemory = capacity;
//      memcpy(pMemory + sizeof(TInt32), pRead, capacity);
//      InnerSetInfo(first, last +  fill);
//      result = ETrue;
//   }else{
//      result = FSharedQueue::Push(pRead, capacity);
//   }
//   if(result){
//      if(buffer != pRead){
//         pPipe->FollowRead(capacity);
//      }
//      _futex.Wake();
//   }
//   return result;
//}

MO_NAMESPACE_END
