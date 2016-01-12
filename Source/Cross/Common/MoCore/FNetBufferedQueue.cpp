#include "MoCrNetMessage.h"
#include "MoCrNetPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络缓冲队列。</T>
//============================================================
FNetBufferedQueue::FNetBufferedQueue(){
}

//============================================================
// <T>析构网络缓冲队列。</T>
//============================================================
FNetBufferedQueue::~FNetBufferedQueue(){
};

//============================================================
// <T>获得总数。</T>
//
// @return 总数
//============================================================
TInt FNetBufferedQueue::Count(){
   return FBufferedQueue::Count();
}

//============================================================
// <T>测试是否能写入指定长度的数据。</T>
//
// @param length 数据长度
// @return 压入是否成功
//============================================================
TBool FNetBufferedQueue::TestPushAble(TInt length){
   return FBufferedQueue::TestPushAble(length);
}

//============================================================
// <T>测试是否能从管道内弹出一个数据。</T>
//
// @return 读出数据的长度，为0表示没有读出有效数据，为-1表示读出缓冲不够
//============================================================
TBool FNetBufferedQueue::TestPopAble(){
   return FBufferedQueue::TestPopAble();
}

//============================================================
// <T>将一个完整信息写入管道。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @return 压入是否成功
//============================================================
TBool FNetBufferedQueue::Push(TAnyC* pData, TInt length){
   return FBufferedQueue::Push(pData, length);
}

//============================================================
// <T>从管道内弹出一个完整的数据。</T>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 读出数据的长度，为0表示没有读出有效数据，为-1表示读出缓冲不够
//============================================================
TInt FNetBufferedQueue::Pop(TAny* pData, TInt capacity){
   return FBufferedQueue::Pop(pData, capacity);
}

//============================================================
// <T>向管道内堵塞式压入一个完整的数据。</T>
//
// @param pData 数据指针
// @param size 数据长度
// @return 压入数据是否成功，为False时当前线程会堵塞
//============================================================
TBool FNetBufferedQueue::BlockedPush(TAnyC* pData, TInt length){
   return FBufferedQueue::BlockedPush(pData, length);
}

//============================================================
// <T>从管道内堵塞式弹出一个完整的数据。</T>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 读出数据的长度，为0时当前线程会堵塞
//============================================================
TInt FNetBufferedQueue::BlockedPop(TAny* pData, TInt capacity){
   return FBufferedQueue::BlockedPop(pData, capacity);
}

//============================================================
// <T>压入一个消息。</T>
//
// @param pMessage 消息对象
// @return  处理结果
//============================================================
TBool FNetBufferedQueue::PushMessage(TNetMessage* pMessage){
   MO_ASSERT(pMessage);
   // 序列化路由数据
   TInt length;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pMessage->Serialize(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_FATAL(TC("Message serialize failure."));
   }
   // 将数据放入管道
   return FBufferedQueue::Push(buffer, length);
}

//============================================================
// <T>压入一个路由。</T>
//
// @param pRouter 路由对象
// @return  处理结果
//============================================================
TBool FNetBufferedQueue::PushRouter(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   // 序列化路由数据
   TInt length;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pRouter->Serialize(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_FATAL(TC("Router serialize failure."));
   }
   // 将数据放入管道
   return FBufferedQueue::Push(buffer, length);
}

//============================================================
// <T>压入一个消息。</T>
//
// @param pMessage 消息对象
// @return  处理结果
//============================================================
TBool FNetBufferedQueue::PushTransfer(TNetTransfer* pTransfer){
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
   TInt bufferLength = *(TInt32*)(TAny*)buffer;
   if(bufferLength > MO_NETMESSAGE_MAXLENGTH){
      MO_FATAL(TC("Write message length is error. (memory=0x%08X, block_length=%d)"), buffer, bufferLength);
   }
   if(length > MO_NETMESSAGE_MAXLENGTH){
      MO_FATAL(TC("Write data length is error. (memory=0x%08X, data_length=%d)"), buffer, length);
   }
   if(bufferLength != length){
      MO_FATAL(TC("Write data length is not equals. (memory=0x%08X, block_length=%d, data_length=%d)"), buffer, bufferLength, length);
   }
   // 将数据放入管道
   return FBufferedQueue::Push(buffer, length);
}

//============================================================
// <T>获得队列信息。</T>
//
// @param pInfo 队列信息
//============================================================
TBool FNetBufferedQueue::FetchInfo(SQueueInfo* pInfo){
   return FBufferedQueue::FetchInfo(pInfo);
}

//============================================================
// <T>输出内部信息。</T>
//============================================================
void FNetBufferedQueue::Dump(){
   FBufferedQueue::Dump();
}

MO_NAMESPACE_END
