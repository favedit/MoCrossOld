#include "MoCrNetConnection.h"

MO_NAMESPACE_BEGIN

//============================================================
FNetMessageConsole::FNetMessageConsole(){
   _pConnections = MO_CREATE(FNetMessageConnectionList);
   _pReceiveThread = MO_CREATE(FNetMessageReceiveThread);
   _pHanldess = MO_CREATE(FNetMessageHandlesVector, MO_NETMESSAGE_COUNT);
   _pHanldess->SetCount(MO_NETMESSAGE_COUNT);
}

//============================================================
FNetMessageConsole::~FNetMessageConsole(){
   // 删除所有链接
   if(!_pConnections->IsEmpty()){
      TListIteratorC<FNetMessageConnection*> iterator = _pConnections->IteratorC();
      while(iterator.Next()){
         FNetMessageConnection* pConnection = *iterator;
         MO_DELETE(pConnection);
      }
   }
   MO_DELETE(_pConnections);
   // 删除所有处理器集合
   if(!_pHanldess->IsEmpty()){
      TVectorIteratorC<FNetMessageHandles*> iterator = _pHanldess->IteratorC();
      while(iterator.Next()){
         FNetMessageHandles* pHandles = *iterator;
         MO_DELETE(pHandles);
      }
   }
   MO_DELETE(_pHanldess);
};

//============================================================
FNetMessageConnectionList* FNetMessageConsole::Connections(){
   return _pConnections;
}

//============================================================
FNetMessageReceiveThread* FNetMessageConsole::ReceiveThread(){
   return _pReceiveThread;
}

//============================================================
TBool FNetMessageConsole::ConnectionRegister(FNetMessageConnection* pConnection){
   MO_ASSERT(pConnection);
   MO_ASSERT(!_pConnections->Contains(pConnection));
   _pConnections->Push(pConnection);
   _pReceiveThread->Register(pConnection);
   return ETrue;
}

//============================================================
TBool FNetMessageConsole::ConnectionUnregister(FNetMessageConnection* pConnection){
   MO_ASSERT(pConnection);
   MO_ASSERT(_pConnections->Contains(pConnection));
   _pConnections->Remove(pConnection);
   _pReceiveThread->Unregister(pConnection);
   return ETrue;
}

//============================================================
TBool FNetMessageConsole::MessageUnregister(TInt code){
   FNetMessageHandles* pHandles = _pHanldess->Get(code);
   if(NULL == pHandles){
      return EFalse;
   }
   _pHanldess->Set(code, NULL);
   MO_DELETE(pHandles);
   return ETrue;
}

//============================================================
TBool FNetMessageConsole::Startup(){
   _pReceiveThread->Start();
   return ETrue;
}

//============================================================
TBool FNetMessageConsole::ProcessConnection(FNetMessageConnection* pConnection){
   /*TNetMessage message;
   TByte buffer[MO_NETMESSAGE_MAXLENGTH];
   if(pConnection->PopupMessage(&message, buffer, MO_NETMESSAGE_MAXLENGTH)){
      // 处理消息
      TInt code = message.MessageHead().Code();
      FNetRouterHandles* pProcessors = _pProcessorss->Get(code);
      if(NULL != pProcessors){
         // 有注册的消息处理器情况
         pProcessors->Process(pConnection, &message);
      }else{
         // 未处理消息
         TChar dump[MO_DUMP_MAXLENGTH];
         TChar track[MO_DUMP_MAXLENGTH];
         MO_ERROR("Unknown message. (%s)\n%s",
               message.Dump(dump, MO_DUMP_MAXLENGTH),
               RNetLogicMessage::Track(&message, track, MO_DUMP_MAXLENGTH));
      }
      return ETrue;
   }*/
   return EFalse;
}

//============================================================
TBool FNetMessageConsole::ProcessConnectionAll(FNetMessageConnection* pConnection){
   TBool result = EFalse;
   while(ProcessConnection(pConnection)){
      result = ETrue;
   }
   return result;
}

//============================================================
TBool FNetMessageConsole::Process(){
   if(!_pConnections->IsEmpty()){
      TListIteratorC<FNetMessageConnection*> iterator = _pConnections->IteratorC();
      while(iterator.Next()){
         ProcessConnection(*iterator);
      }
   }
   return ETrue;
}

//============================================================
TBool FNetMessageConsole::ProcessAll(){
   if(!_pConnections->IsEmpty()){
      TListIteratorC<FNetMessageConnection*> iterator = _pConnections->IteratorC();
      while(iterator.Next()){
         ProcessConnectionAll(*iterator);
      }
   }
   return ETrue;
}

//============================================================
TBool FNetMessageConsole::Shutdown(){
   _pReceiveThread->Stop();
   return ETrue;
}

MO_NAMESPACE_END
