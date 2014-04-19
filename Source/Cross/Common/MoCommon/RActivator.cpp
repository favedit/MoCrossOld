#include "MoCmMemory.h"

MO_NAMESPACE_BEGIN

//============================================================
TThreadLocker RActivator::_locker;

//============================================================
// <T>初始化资源。</T>
//============================================================
void RActivator::Initialize(){
}

//============================================================
// <T>释放资源。</T>
//============================================================
void RActivator::Release(){
}

//============================================================
// <T>创建一个对象的实例。</T>
//
// @param pClassName 类名称
// @param size 对象大小
// @param pFileName 编译文件
// @param line 编译行数
// @return 对象实例指针
//============================================================
TAny* RActivator::Create(TChar8C* pClassName, TInt size, TChar8C* pFileName, TInt line){
   // 获得当前线程的陷阱接口
   //FThreadTrap* pThreadTrap = RThread::Static().Console()->Current()->ThreadTrap();
   //FTrap* pTrap = pThreadTrap->CurrentTrap();
   // 收集指定大小的内存
   //return pTrap->Alloc(pClassName, size, pFileName, line);
   return NULL;
}

//============================================================
// <T>移除一个对象的实例。</T>
//
// @param pObject 对象实例指针
//============================================================
void RActivator::Remove(TAny* pObject){
   // 获得当前线程的陷阱接口
   //FThreadTrap* pThreadTrap = RThread::Static().Console()->Current()->ThreadTrap();
   //FTrap* pTrap = pThreadTrap->CurrentTrap();
   // 释放指定大小的内存
   //pTrap->Remove(pObject);
}

//============================================================
// <T>删除一个对象的实例。</T>
//
// @param pObject 对象实例指针
//============================================================
void RActivator::Destroy(TAny* pObject){
   // 获得当前线程的陷阱接口
   //FTrap* pTrap = RTrap::Static().CurrentTrap();
   // 释放指定大小的内存
   //pTrap->Free(pObject);
}

//============================================================
// <T>资源加锁。</T>
//============================================================
void RActivator::Lock(){
   _locker.Enter();
}

//============================================================
// <T>资源解锁。</T>
//============================================================
void RActivator::Unlock(){
   _locker.Leave();
}

MO_NAMESPACE_END
