#include "MoCrNetMessage.h"
#include "MoCrNetPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网络缓冲队列。</T>
//============================================================
FNetBufferedPipe::FNetBufferedPipe(){
}

//============================================================
// <T>析构网络缓冲队列。</T>
//============================================================
FNetBufferedPipe::~FNetBufferedPipe(){
};

//============================================================
// <T>获得管道的长度。</T>
//
// @return 数据长度
//============================================================
TInt FNetBufferedPipe::Length(){
   return FBufferedPipe::Length();
}

//============================================================
// <T>从管道内获得一个数据。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @param pLength 读取长度
// @return 处理结果
//============================================================
EStreamResult FNetBufferedPipe::Peek(TAny* pData, TInt length, TInt* pLength){
   return FBufferedPipe::Peek(pData, length, pLength);
}

//============================================================
// <T>从管道内读取一个数据。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @param pLength 读取长度
// @return 处理结果
//============================================================
EStreamResult FNetBufferedPipe::Read(TAny* pData, TInt length, TInt* pLength){
   return FBufferedPipe::Read(pData, length, pLength);
}

//============================================================
// <T>向管道内写入一个数据。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @param pLength 读取长度
// @return 处理结果
//============================================================
EStreamResult FNetBufferedPipe::Write(TAnyC* pData, TInt length, TInt* pLength){
   return FBufferedPipe::Write(pData, length, pLength);
}

//============================================================
// <T>写入一个消息。</T>
//
// @param pMessage 消息
// @return 处理结果
//============================================================
TBool FNetBufferedPipe::WriteMessage(TNetMessage* pMessage){
   MO_ASSERT(pMessage);
   // 序列化路由数据
   TInt length = 0;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pMessage->SerializeMessage(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_ERROR(TC("Serialize message failure."));
      return EFalse;
   }
   // 尝试写入数据
   TInt lengthWrite = 0;
   if(EStreamResult_Success != FBufferedPipe::Write(buffer, length, &lengthWrite)){
      MO_ERROR(TC("Write message failure. (length=%d)"), length);
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>写入一个压缩消息。</T>
//
// @param pMessage 消息
// @return 处理结果
//============================================================
TBool FNetBufferedPipe::WriteMessageCompress(TNetMessage* pMessage){
   MO_ASSERT(pMessage);
   // 序列化路由数据
   TInt length = 0;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pMessage->CompressMessage(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_ERROR(TC("Serialize message failure."));
      return EFalse;
   }
   // 尝试写入数据
   TInt lengthWrite = 0;
   if(EStreamResult_Success != FBufferedPipe::Write(buffer, length, &lengthWrite)){
      MO_ERROR(TC("Write message failure. (length=%d)"), length);
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>写入一个路由。</T>
//
// @param pMessage 消息
// @return 处理结果
//============================================================
TBool FNetBufferedPipe::WriteRouter(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   // 序列化路由数据
   TInt length = 0;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pRouter->SerializeRouter(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_ERROR(TC("Serialize router failure."));
      return EFalse;
   }
   // 尝试写入数据
   TInt lengthWrite = 0;
   if(EStreamResult_Success != FBufferedPipe::Write(buffer, length, &lengthWrite)){
      MO_ERROR(TC("Write router failure. (length=%d)"), length);
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>写入一个压缩路由。</T>
//
// @param pMessage 消息
// @return 处理结果
//============================================================
TBool FNetBufferedPipe::WriteRouterCompress(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   // 序列化路由数据
   TInt length = 0;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pRouter->SerializeRouter(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_ERROR(TC("Serialize router failure."));
      return EFalse;
   }
   // 尝试写入数据
   TInt lengthWrite = 0;
   if(EStreamResult_Success != FBufferedPipe::Write(buffer, length, &lengthWrite)){
      MO_ERROR(TC("Write router failure. (length=%d)"), length);
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>重置数据。</T>
//
// @return 处理结果
//============================================================
TBool FNetBufferedPipe::Reset(){
   return FBufferedPipe::Reset();
}

MO_NAMESPACE_END
