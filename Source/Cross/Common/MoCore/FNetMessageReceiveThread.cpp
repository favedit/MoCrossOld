#ifdef _MO_LINUX
#include <unistd.h>
#endif
#include "MoCrNetConnection.h"

MO_NAMESPACE_BEGIN

//============================================================
FNetMessageReceiveThread::FNetMessageReceiveThread(){
   _pConnections = MO_CREATE(FNetMessageConnectionList);
}

//============================================================
FNetMessageReceiveThread::~FNetMessageReceiveThread(){
   MO_DELETE(_pConnections);
};

//============================================================
FNetMessageConnectionList* FNetMessageReceiveThread::Connections(){
   return _pConnections;
}

//============================================================
TBool FNetMessageReceiveThread::Register(FNetMessageConnection* pConnection){
   MO_ASSERT(pConnection);
   MO_ASSERT(!_pConnections->Contains(pConnection));
   _pConnections->Push(pConnection);
   return ETrue;
}

//============================================================
TBool FNetMessageReceiveThread::Unregister(FNetMessageConnection* pConnection){
   MO_ASSERT(pConnection);
   MO_ASSERT(_pConnections->Contains(pConnection));
   _pConnections->Remove(pConnection);
   return ETrue;
}

//============================================================
TBool FNetMessageReceiveThread::ProcessStart(){
   if(!_pConnections->IsEmpty()){
      TListIteratorC<FNetMessageConnection*> iterator = _pConnections->IteratorC();
      while(iterator.Next()){
         iterator->OnStartup();
      }
   }
   return ETrue;
}

//============================================================
TBool FNetMessageReceiveThread::ProcessReceive(){
   if(!_pConnections->IsEmpty()){
      TListIteratorC<FNetMessageConnection*> iterator = _pConnections->IteratorC();
      while(!IsStop() && iterator.Next()){
         iterator->ProcessReceive();
      }
   }
   return ETrue;
}

//============================================================
TResult FNetMessageReceiveThread::Process(){
   // 处理链接启动
   ProcessStart();
   // 处理链接接收数据
   while(!IsStop()){
      ProcessReceive();
      MO_LIB_SLEEP(1);
   }
   return ESuccess;
}

//============================================================
TResult FNetMessageReceiveThread::Stop(){
   _pConnections->Clear();
   return ESuccess;
}

MO_NAMESPACE_END
