#include "MoCmNet.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造网路端口。</T>
//============================================================
FNetSocketStorage::FNetSocketStorage(){
   RTypes<FNetSocket*>::Clear(_pSockets, MoNetSocketCount);
}

//============================================================
FNetSocketStorage::~FNetSocketStorage(){
}

//============================================================
INetSocket* FNetSocketStorage::AtomCreate(){
   FNetSocket* pSocket = NULL;
   _locker.Enter();
   pSocket = MO_CREATE(FNetSocket);
   _locker.Leave();
   return pSocket;
}

//============================================================
void FNetSocketStorage::AtomDelete(INetSocket* pSocket){
   _locker.Enter();
   MO_DELETE(pSocket);
   _locker.Leave();
}

//============================================================
INetSocket* FNetSocketStorage::Get(TSocket handle){
   return NULL;
}

//============================================================
INetSocket* FNetSocketStorage::Find(TSocket handle){
   return NULL;
}

MO_NAMESPACE_END
