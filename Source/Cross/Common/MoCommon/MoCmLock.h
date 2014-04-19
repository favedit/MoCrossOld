//============================================================
// <T>线程锁管理类</T>
//============================================================
#ifndef __MO_CM_LOCK_H__
#define __MO_CM_LOCK_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>线程锁接口。</T>
//
// @tool
// @history 091211 MAOCY 创建
//============================================================
class MO_CM_DECLARE IThreadLocker{
public:
   //------------------------------------------------------------
   // <T>析构线程锁接口。</T>
   MO_ABSTRACT ~IThreadLocker(){
   }
public:
   MO_VIRTUAL void Enter() = 0;
   MO_VIRTUAL TBool TryEnter() = 0;
   MO_VIRTUAL void Leave() = 0;
};

//============================================================
// <T>线程临界段。</T>
//
// @tool
// @history 091211 MAOCY 创建
//============================================================
class MO_CM_DECLARE TThreadSection : public IThreadLocker{
protected:
   TBool _enable;
#ifdef _MO_WINDOWS
   CRITICAL_SECTION _section;
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   pthread_spinlock_t _section;
#endif // _MO_LINUX
public:
   TThreadSection();
   MO_ABSTRACT ~TThreadSection();
public:
   //------------------------------------------------------------
   // <T>允许处理。</T>
   MO_INLINE void Enable(TBool flag){
      _enable = flag;
   }
   //------------------------------------------------------------
   // <T>禁止处理。</T>
   MO_INLINE void Disable(){
      _enable = EFalse;
   }
public:
   MO_OVERRIDE void Enter();
   MO_OVERRIDE TBool TryEnter();
   MO_OVERRIDE void Leave();
};

//============================================================
// <T>进程同步锁。</T>
//
// @tool
// @history 091214 MAOCY 创建
//============================================================
class MO_CM_DECLARE TThreadMutex : public IThreadLocker{
protected:
   TCharC* _pName;
#ifdef _MO_SYS_WINDOWS
   CRITICAL_SECTION _section;
#endif // _MO_SYS_WINDOWS
#ifdef _MO_SYS_LINUX
   pthread_mutex_t _section;
#endif // _MO_SYS_LINUX
public:
   TThreadMutex();
   MO_ABSTRACT ~TThreadMutex() ;
public:
   MO_OVERRIDE void Enter();
   MO_OVERRIDE TBool TryEnter();
   MO_OVERRIDE void Leave();
};

//============================================================
// <T>线程条件。</T>
//
// @tool
// @history 100226 MAOCY 创建
//============================================================
class MO_CM_DECLARE TThreadCondition{
protected:
#ifdef _MO_SYS_LINUX
   pthread_mutex_t _mutex;
   pthread_cond_t _condition;
   pthread_condattr_t _attribute;
#endif
public:
   TThreadCondition();
   ~TThreadCondition();
public:
   void Lock();
   void Unlock();
   //------------------------------------------------------------
   void Wait();
   void Wait(TInt msecond);
   void Signal();
   void Notify();
   void Broadcast();
};

//============================================================
// 线程锁的重定义
#ifdef _MO_WINDOWS
typedef TThreadSection TThreadLocker;
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
typedef TThreadSection TThreadLocker;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
typedef TThreadMutex TThreadLocker;
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
typedef TThreadMutex TThreadLocker;
#endif // _MO_FLASCC

MO_NAMESPACE_END

#endif // __MO_CM_LOCK_H__
