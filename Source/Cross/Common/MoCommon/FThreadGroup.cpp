#include "MoCmThread.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造线程组。</T>
//============================================================
FThreadGroup::FThreadGroup(){
   _pThreads = MO_CREATE(FThreadList);
}

//============================================================
FThreadGroup::~FThreadGroup(){
   if(!_pThreads->IsEmpty()){
      TListIteratorC<FThread*> iterator = _pThreads->IteratorC();
      while(iterator.Next()){
         FThread* pThread = *iterator;
         MO_DELETE(pThread);
      }
   }
   MO_DELETE(_pThreads);
}

//============================================================
FThreadList* FThreadGroup::Threads(){
   return _pThreads;
}

//============================================================
TBool FThreadGroup::Push(FThread* pThread){
   MO_ASSERT(pThread);
   pThread->SetThreadGroup(this);
   _locker.Enter();
   _pThreads->Push(pThread);
   _locker.Leave();
   return ETrue;
}

//============================================================
TBool FThreadGroup::Remove(FThread* pThread){
   MO_ASSERT(pThread);
   pThread->SetThreadGroup(NULL);
   _locker.Enter();
   _pThreads->Remove(pThread);
   _locker.Leave();
   return ETrue;
}

//============================================================
TBool FThreadGroup::Start(){
   if(!_pThreads->IsEmpty()){
      TListIteratorC<FThread*> iterator = _pThreads->IteratorC();
      while(iterator.Next()){
         iterator->Start();
      }
   }
   return ETrue;
}

//============================================================
TBool FThreadGroup::Suspend(){
   if(!_pThreads->IsEmpty()){
      TListIteratorC<FThread*> iterator = _pThreads->IteratorC();
      while(iterator.Next()){
         iterator->Suspend();
      }
   }
   return ETrue;
}

//============================================================
TBool FThreadGroup::Resume(){
   if(!_pThreads->IsEmpty()){
      TListIteratorC<FThread*> iterator = _pThreads->IteratorC();
      while(iterator.Next()){
         iterator->Resume();
      }
   }
   return ETrue;
}

//============================================================
TBool FThreadGroup::Wait(){
   if(!_pThreads->IsEmpty()){
      TListIteratorC<FThread*> iterator = _pThreads->IteratorC();
      while(iterator.Next()){
         iterator->Wait();
      }
   }
   return ETrue;
}

//============================================================
TBool FThreadGroup::Stop(){
   if(!_pThreads->IsEmpty()){
      TListIteratorC<FThread*> iterator = _pThreads->IteratorC();
      while(iterator.Next()){
         iterator->Stop();
      }
   }
   return ETrue;
}

MO_NAMESPACE_END
