#ifdef _MO_LINUX
#include <unistd.h>
#endif
#include <MoCmStream.h>
#include <MoCmNet.h>
#include "MoCrNetConnection.h"

#define MO_MD_MESSAGE_WAIT_INTERVAL 1

MO_NAMESPACE_BEGIN

//============================================================
FNetMessageConnection::FNetMessageConnection(){
   _isConnected = EFalse;
   _isUdpConnected = EFalse;
   _pSocket = MO_CREATE(FNetClientSocket);
   _pUdpSocket = MO_CREATE(FNetUdpClientSocket);
   _pipeLength = 1024*32;
   _queueLength = 1024*64;
   MO_CLEAR(_pInputPipe);
   MO_CLEAR(_pInputQueue);
   MO_CLEAR(_pInputUdpQueue);
   MO_CLEAR(_pOutputPipe);
   MO_CLEAR(_pOuputQueue);
}

//============================================================
FNetMessageConnection::~FNetMessageConnection(){
   MO_DELETE(_pSocket);
   MO_DELETE(_pUdpSocket);
   MO_DELETE(_pInputPipe);
   MO_DELETE(_pInputQueue);
   MO_DELETE(_pInputUdpQueue);
   MO_DELETE(_pOutputPipe);
   MO_DELETE(_pOuputQueue);
};

//============================================================
TBool FNetMessageConnection::Create(){
   _pInputPipe = MO_CREATE(FPipe, _pipeLength);
   _pInputQueue = MO_CREATE(FQueue, _queueLength);
   _pInputUdpQueue = MO_CREATE(FQueue, _queueLength);
   _pOutputPipe = MO_CREATE(FPipe, _pipeLength);
   _pOuputQueue = MO_CREATE(FQueue, _queueLength);
   return ETrue;
}

//============================================================
TBool FNetMessageConnection::Connect(TCharC* pHost, TUint16 port){
   // 链接服务器
   _isConnected = _pSocket->Connect(pHost, port);
   return _isConnected;
}

//============================================================
TBool FNetMessageConnection::UdpConnect(TCharC* pHost, TUint16 port){
   // 链接服务器
   _isUdpConnected = _pUdpSocket->Connect(pHost, port);
   return _isUdpConnected;
}

//============================================================
TBool FNetMessageConnection::SetNonBlock(){
   MO_ASSERT(_isConnected);
   _pSocket->SetNonBlock();
   return ETrue;
}

//============================================================
TBool FNetMessageConnection::SetUdpNonBlock(){
   MO_ASSERT(_isUdpConnected);
   _pUdpSocket->SetNonBlock();
   return ETrue;
}

//============================================================
TBool FNetMessageConnection::Disconnect(){
   // 断开TCP链接
   _pSocket->Disconnect();
   _isConnected = EFalse;
   // 断开UDP链接
   _pUdpSocket->Disconnect();
   _isUdpConnected = EFalse;
   return ETrue;
}

//============================================================
TBool FNetMessageConnection::OnStartup(){
   return ETrue;
}

//============================================================
TBool FNetMessageConnection::AbandonReceiveMessages(){
   while(ETrue){
      // 判断是否存在长度数据
      TUint32 length = (TUint32)_pInputPipe->Length();
      if(length <= sizeof(SNetHead)){
         break;
      }
      // 测试是否含有完整消息
      TUint32 size = 0;;
      if(!_pInputPipe->TryPeek(&size, sizeof(TUint32))){
         break;
      }
      // 消息不够一个完整的
      if(length < size){
         break;
      }
      // 舍弃一个消息
      TByte readBuffer[MO_NETMESSAGE_MAXLENGTH];
      _pInputPipe->Read(readBuffer, size);
   }
   return ETrue;
}

//============================================================
// <T>读取网络数据，写入接收缓冲。如果缓冲中有完整消息，则读出写入消息管道。</T>
//
// @return 处理结果
//============================================================
TBool FNetMessageConnection::ProcessTcpReceive(){
   // 测试是否已经链接
   if(!_isConnected){
      return EFalse;
   }
   // 接收数据
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   TInt received = _pSocket->Receive(buffer, MO_NETMESSAGE_MAXLENGTH);
   if(-1 == received){
      _pSocket->Disconnect();
      return EFalse;
   }
   if(received > 0){
      MO_DEBUG(TC("Receive socket tcp data. (length=%d)"), received);
      TBool result = _pInputPipe->TryWrite(buffer, received);
      if(!result){
         // 舍弃已经接收的消息
         AbandonReceiveMessages();
         // 重新写入数据
         result = _pInputPipe->TryWrite(buffer, received);
         if(!result){
            MO_FATAL(TC("Input pipe buffer is full."));
         }
      }
   }
   // 判断是否存在长度数据
   TUint32 length = (TUint32)_pInputPipe->Length();
   if(length <= sizeof(SNetHead)){
      return EFalse;
   }
   // 测试是否含有完整消息
   TNetLength size = 0;;
   if(!_pInputPipe->TryPeek(&size, sizeof(TNetLength))){
      return EFalse;
   }
   // 消息不够一个完整的
   if(length < size){
      return EFalse;
   }
   // 测试消息管道是否能放下该消息
   TUint32 remain = (TUint32)_pInputQueue->Reamin();
   if(remain < size){
      return EFalse;
   }
   // 获得消息数据，写入消息管道
   TByte readBuffer[MO_NETMESSAGE_MAXLENGTH];
   TInt readed = _pInputPipe->Read(readBuffer, size);
   if(readed <= 0){
      MO_ERROR(TC("Read pipe failure. (size=%d)"), size);
      return EFalse;
   }
   // 获得网络头信息
   SNetHead* pHead = (SNetHead*)readBuffer;
   if(ENetProtocol_Message != pHead->protocol){
      MO_FATAL(TC("Unknown net head protocol. (protocol=%d)"), pHead->protocol);
      return EFalse;
   }
   // 存储消息
   return _pInputQueue->Push(readBuffer, readed);
}

//============================================================
// <T>读取网络数据，写入接收缓冲。如果缓冲中有完整消息，则读出写入消息管道。</T>
//
// @return 处理结果
//============================================================
TBool FNetMessageConnection::ProcessUdpReceive(){
   // 测试是否已经链接
   if(!_isUdpConnected){
      return EFalse;
   }
   // 接收数据
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   TInt received = _pUdpSocket->Receive(buffer, MO_NETMESSAGE_MAXLENGTH);
   // 错误处理
   if(-1 == received){
      _pSocket->Disconnect();
      return EFalse;
   }
   // 未接收到消息
   if(0 == received){
      return EFalse;
   }
   MO_DEBUG(TC("Receive socket udp data. (length=%d)"), received);
   // 测试消息管道是否能放下该消息
   TInt remain = _pInputUdpQueue->Reamin();
   if(remain < received){
      // 放弃前面的消息
      _pInputUdpQueue->Reset();
   }
   // 获得网络头信息
   SNetHead* pHead = (SNetHead*)buffer;
   if(ENetProtocol_Message != pHead->protocol){
      MO_FATAL(TC("Unknown net head protocol. (protocol=%d)"), pHead->protocol);
      return EFalse;
   }
   // 存储消息
   return _pInputUdpQueue->Push(buffer, received);
}

//============================================================
// <T>读取网络数据，写入接收缓冲。如果缓冲中有完整消息，则读出写入消息管道。</T>
//
// @return 处理结果
//============================================================
TBool FNetMessageConnection::ProcessReceive(){
   TBool result = ProcessTcpReceive();
   result |= ProcessUdpReceive();
   return result;
}

//============================================================
// <T>发送一个网络消息。</T>
//
// @param pMessage 消息对象
// @return 处理结果
//============================================================
TBool FNetMessageConnection::PushMessage(TNetMessage* pMessage){
   MO_ASSERT(pMessage);
   // 检查是否已经链接
   if(!_isConnected){
      return EFalse;
   }
   // 序列化消息
   TInt length;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(pMessage->Serialize(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
#ifdef _MO_DEBUG
      TChar dump[MO_FS_DUMP_LENGTH];
      TChar format[MO_FS_DUMP_LENGTH];
      MO_STATIC_DEBUG(TC("Send tcp net message.\n%s%s"),
            pMessage->Dump(dump, MO_FS_DUMP_LENGTH),
            pMessage->DumpMemory(format, MO_FS_DUMP_LENGTH));
#endif
      // 发送消息
      TInt result = _pSocket->Send(buffer, length);
      if(EError == result){
         _pSocket->Disconnect();
         return EFalse;
      }
      return ETrue;
   }
   return EFalse;
}

//============================================================
// <T>发送一个网络消息。</T>
//
// @param pMessage 消息对象
// @return 处理结果
//============================================================
TBool FNetMessageConnection::PushUdpMessage(TNetMessage* pMessage){
   MO_ASSERT(pMessage);
   // 检查是否已经链接
   if(!_isUdpConnected){
      return EFalse;
   }
   // 序列化消息
   TInt length;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(pMessage->Serialize(buffer, MO_NETMESSAGE_MAXLENGTH, &length)){
#ifdef _MO_DEBUG
      TChar dump[MO_FS_DUMP_LENGTH];
      TChar format[MO_FS_DUMP_LENGTH];
      MO_STATIC_DEBUG(TC("Send udp net message.\n%s%s"),
            pMessage->Dump(dump, MO_FS_DUMP_LENGTH),
            pMessage->DumpMemory(format, MO_FS_DUMP_LENGTH));
#endif
      // 发送消息
      TInt result = _pUdpSocket->Send(buffer, length);
      if(EError == result){
         _pUdpSocket->Disconnect();
         return EFalse;
      }
      return ETrue;
   }
   return EFalse;
}

//============================================================
// <T>弹出一个TCP消息。</T>
//============================================================
TBool FNetMessageConnection::PopupMessage(TNetMessage* pMessage){
   // 检查是否已经链接
   if(!_isConnected){
      return EFalse;
   }
   // 弹出消息
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   TInt length = _pInputQueue->Pop(buffer, MO_NETMESSAGE_MAXLENGTH);
   if(length > 0){
      // 反序列化消息
      TInt msgLength;
      if(pMessage->Unserialize(buffer, length, &msgLength)){
#ifdef _MO_DEBUG
         // 输出消息信息
         TChar dump[MO_FS_DUMP_LENGTH];
         TChar format[MO_FS_DUMP_LENGTH];
         MO_DEBUG(TC("Receive net message.\n%s%s"),
               pMessage->Dump(dump, MO_FS_DUMP_LENGTH),
               pMessage->DumpMemory(format, MO_FS_DUMP_LENGTH));
#endif
      }
      return ETrue;
   }
   return EFalse;
}

//============================================================
// <T>弹出一个UDP消息。</T>
//============================================================
TBool FNetMessageConnection::PopupUdpMessage(TNetMessage* pMessage){
   // 检查是否已经链接
   if(!_isConnected){
      return EFalse;
   }
   // 弹出消息
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   TInt length = _pInputUdpQueue->Pop(buffer, MO_NETMESSAGE_MAXLENGTH);
   if(length > 0){
      // 反序列化消息
      TInt msgLength;
      if(pMessage->Unserialize(buffer, length, &msgLength)){
#ifdef _MO_DEBUG
         // 输出消息信息
         TChar dump[MO_FS_DUMP_LENGTH];
         TChar format[MO_FS_DUMP_LENGTH];
         MO_DEBUG(TC("Receive net message.\n%s%s"),
               pMessage->Dump(dump, MO_FS_DUMP_LENGTH),
               pMessage->DumpMemory(format, MO_FS_DUMP_LENGTH));
#endif
      }
      return ETrue;
   }
   return EFalse;
}

//============================================================
// <T>一直等到读取到消息返回。</T>
//============================================================
TBool FNetMessageConnection::WaitMessage(TNetMessage* pMessage){
   // 检查是否已经链接
   if(!_isConnected){
      return EFalse;
   }
   // 接收到第一个消息
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   while(ETrue){
      // 弹出消息
      TInt length = _pInputQueue->Pop(buffer, MO_NETMESSAGE_MAXLENGTH);
      if(length > 0){
         // 反序列化消息
         TInt msgLength;
         if(pMessage->Unserialize(buffer, length, &msgLength)){
#ifdef _MO_DEBUG
            // 输出消息信息
            TChar dump[MO_FS_DUMP_LENGTH];
            TChar format[MO_FS_DUMP_LENGTH];
            MO_DEBUG(TC("Receive net message.\n%s%s"),
                  pMessage->Dump(dump, MO_FS_DUMP_LENGTH),
                  pMessage->DumpMemory(format, MO_FS_DUMP_LENGTH));
#endif
            break;
         }
      }
      MO_LIB_SLEEP(MO_MD_MESSAGE_WAIT_INTERVAL);
   }
   return ETrue;
}

//============================================================
// <T>一直等到读取到消息返回。</T>
//============================================================
TBool FNetMessageConnection::ProcessWaitMessage(TNetMessage* pMessage){
   // 接收到第一个消息
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   while(ETrue){
      // 检查是否已经链接
      if(!_isConnected){
         return EFalse;
      }
      // 接收消息
      ProcessReceive();
      // 弹出消息
      TInt length = _pInputQueue->Pop(buffer, MO_NETMESSAGE_MAXLENGTH);
      if(length > 0){
         // 反序列化消息
         TInt msgLength;
         if(pMessage->Unserialize(buffer, length, &msgLength)){
#ifdef _MO_DEBUG
            // 输出消息信息
            TChar dump[MO_FS_DUMP_LENGTH];
            TChar format[MO_FS_DUMP_LENGTH];
            MO_DEBUG(TC("Receive net message.\n%s%s"),
                  pMessage->Dump(dump, MO_FS_DUMP_LENGTH),
                  pMessage->DumpMemory(format, MO_FS_DUMP_LENGTH));
#endif
            break;
         }
      }
      MO_LIB_SLEEP(1);
   }
   return ETrue;
}

//============================================================
TBool FNetMessageConnection::DoConnect(){
   //TNmCrConnectRequest request;
   //return PushMessage(&request);
   return ETrue;
}

//============================================================
TBool FNetMessageConnection::DoUdpConnect(){
   //TNmCrUdpConnectRequest request;
   //request.SetClientId(_clientId);
   //return PushUdpMessage(&request);
   return ETrue;
}

MO_NAMESPACE_END
