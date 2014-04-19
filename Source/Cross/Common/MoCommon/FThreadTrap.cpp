#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>创建线程陷阱对象。</T>
//============================================================
FThreadTrap::FThreadTrap(TInt threadId){
   _threadId = threadId;
   MO_CLEAR(_pUsed);
   MO_CLEAR(_pUnused);
}

//============================================================
// <T>释放线程陷阱对象。</T>
//============================================================
FThreadTrap::~FThreadTrap(){
   // 删除所有已使用的链表对象
   while(NULL != _pUsed){
      FTrap* pUsed = _pUsed;
      _pUsed = _pUsed->Parent();
      delete pUsed;
   }
   // 删除所有未使用的链表对象
   while(NULL != _pUnused){
      FTrap* pUsed = _pUnused;
      _pUnused = _pUnused->Parent();
      delete pUsed;
   }
}

//============================================================
// <T>获得线程标识。</T>
//
// @return 线程标识
//============================================================
TInt FThreadTrap::ThreadId(){
   return _threadId;
}

//============================================================
// <T>是否为空。</T>
//
// @return 是否为空
//============================================================
TBool FThreadTrap::IsEmpty(){
   return (NULL == _pUsed);
}

//============================================================
// <T>获得当前陷阱对象。</T>
//
// @return 当前陷阱对象
//============================================================
FTrap* FThreadTrap::CurrentTrap(){
   return _pUsed;
}

//============================================================
// <T>压入陷阱对象。</T>
//============================================================
void FThreadTrap::Push(){
   // 获得一个可用的实例
   FTrap* pTrap = NULL;
   if(NULL == _pUnused){
      pTrap = new FTrap();
   }else{
      pTrap = _pUnused;
      _pUnused = pTrap->Parent();
   }
   // 压入使用中的队列
   pTrap->SetParent(_pUsed);
   _pUsed = pTrap;
}

//============================================================
// <T>弹出陷阱对象。</T>
//============================================================
void FThreadTrap::Pop(){
   MO_ASSERT(_pUsed);
   // 获得当前使用的实例
   FTrap* pTrap = _pUsed;
   _pUsed = pTrap->Parent();
   // 压入未使用的队列
   pTrap->SetParent(_pUnused);
   _pUnused = pTrap;
}

MO_NAMESPACE_END
