#include "MoCrNetMessage.h"
#include "MoCrNetPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造共享网络管道。</T>
//============================================================
FSharedNetPipe::FSharedNetPipe(){
}

//============================================================
// <T>析构共享网络管道。</T>
//============================================================
FSharedNetPipe::~FSharedNetPipe(){
};

//============================================================
// <T>尝试写入消息。</T>
//
// @param pMessage 消息
// @return 处理结果
//============================================================
TBool FSharedNetPipe::TryWriteMessage(TNetMessage* pMessage){
   MO_ASSERT(pMessage);
   // 序列化路由数据
   TInt length;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pMessage->SerializeMessage(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_ERROR(TC("Serialize message failure."));
      return EFalse;
   }
   // 尝试写入数据
   if(!TryWrite(buffer, length)){
      MO_ERROR(TC("Write message failure. (length=%d)"), length);
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>尝试写入压缩消息。</T>
//
// @param pMessage 消息
// @return 处理结果
//============================================================
TBool FSharedNetPipe::TryWriteMessageCompress(TNetMessage* pMessage){
   MO_ASSERT(pMessage);
   // 序列化路由数据
   TInt length;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pMessage->CompressMessage(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_ERROR(TC("Serialize message failure."));
      return EFalse;
   }
   // 尝试写入数据
   if(!TryWrite(buffer, length)){
      MO_ERROR(TC("Write message failure. (length=%d)"), length);
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>尝试写入路由。</T>
//
// @param pMessage 消息
// @return 处理结果
//============================================================
TBool FSharedNetPipe::TryWriteRouter(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   // 序列化路由数据
   TInt length;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pRouter->SerializeRouter(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_ERROR(TC("Serialize router failure."));
      return EFalse;
   }
   // 尝试写入数据
   if(!TryWrite(buffer, length)){
      MO_ERROR(TC("Write router failure. (length=%d)"), length);
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>尝试写入压缩路由。</T>
//
// @param pMessage 消息
// @return 处理结果
//============================================================
TBool FSharedNetPipe::TryWriteRouterCompress(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   // 序列化路由数据
   TInt length;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(!pRouter->SerializeRouter(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
      MO_ERROR(TC("Serialize router failure."));
      return EFalse;
   }
   // 尝试写入数据
   if(!TryWrite(buffer, length)){
      MO_ERROR(TC("Write router failure. (length=%d)"), length);
      return EFalse;
   }
   return ETrue;
}

MO_NAMESPACE_END
