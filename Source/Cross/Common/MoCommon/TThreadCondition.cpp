#include "MoCmLock.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>创建线程事件。</T>
//============================================================
TThreadCondition::TThreadCondition(){
#ifdef _MO_WINDOWS
#else
   // 创建锁
   TInt result = pthread_mutex_init(&_mutex, NULL);
   if(ESuccess != result){
      // EBUSY：该实现已检测到系统尝试重新初始化 mp 所引用的对象，即以前进行过初始化但 尚未销毁的互斥锁。
      // EINVAL：mattr 属性值无效。互斥锁尚未修改。
      // EFAULT：mp 所指向的互斥锁的地址无效。
      MO_PFATAL(pthread_mutex_init);
   }
   // 创建条件
   result = pthread_cond_init(&_condition, NULL);
   if(ESuccess != result){
      // EINVAL：cattr 指定的值无效。
      // EBUSY：条件变量处于使用状态。
      // EAGAIN：必要的资源不可用。
      // ENOMEM：内存不足，无法初始化条件变量。
      MO_PFATAL(pthread_cond_init);
   }
#endif // _MO_WINDOWS
}

//============================================================
// <T>释放线程事件。</T>
//============================================================
TThreadCondition::~TThreadCondition(){
#ifdef _MO_WINDOWS
#else
   TInt result = pthread_cond_destroy(&_condition);
   if(ESuccess != result){
      // EINVAL：cv 指定的值无效。
      MO_PFATAL(pthread_cond_destroy);
   }
#endif // _MO_WINDOWS
}

//============================================================
// <T>加锁。</T>
//============================================================
void TThreadCondition::Lock(){
#ifdef _MO_WINDOWS
#else
   TInt result = pthread_mutex_lock(&_mutex);
   if(ESuccess != result){
      MO_PFATAL(pthread_mutex_lock);
   }
#endif // _MO_WINDOWS
}

//============================================================
// <T>解锁。</T>
//============================================================
void TThreadCondition::Unlock(){
#ifdef _MO_WINDOWS
#else
   TInt result = pthread_mutex_unlock(&_mutex);
   if(ESuccess != result){
      MO_PFATAL(pthread_mutex_unlock);
   }
#endif // _MO_WINDOWS
}

//============================================================
// <T>等待解锁。</T>
//============================================================
void TThreadCondition::Wait(){
#ifdef _MO_WINDOWS
#else
   TInt result = pthread_cond_wait(&_condition, &_mutex);
   if(ESuccess != result){
      // EINVAL：cv 或 mp 指定的值无效。
      MO_PFATAL(pthread_cond_wait);
   }
#endif // _MO_WINDOWS
}

//============================================================
// <T>等待解锁。</T>
//============================================================
void TThreadCondition::Wait(TInt msecond){
#ifdef _MO_WINDOWS
#else
   time_t now = time(NULL);
   timespec abstime;
   abstime.tv_nsec = now + (msecond/1000);
   abstime.tv_nsec = (msecond % 1000) * 1000 * 1000;
   TInt result = pthread_cond_timedwait(&_condition, &_mutex, &abstime);
   if(ESuccess != result){
      // EINVAL：cv 或 abstime 指向的地址非法。
      // ETIMEDOUT：abstime 指定的时间已过。
      MO_PFATAL(pthread_cond_timedwait);
   }
#endif // _MO_WINDOWS
}

//============================================================
// <T>发送解锁信号。</T>
//============================================================
void TThreadCondition::Signal(){
#ifdef _MO_WINDOWS
#else
   TInt result = pthread_cond_signal(&_condition);
   if(ESuccess != result){
      // EINVAL：cv 指向的地址非法。
      MO_PFATAL(pthread_cond_signal);
   }
#endif // _MO_WINDOWS
}

//============================================================
// <T>通知解除锁。</T>
//============================================================
void TThreadCondition::Notify(){
   // 锁定
   Lock();
   // 继续
   Signal();
   // 解除锁定
   Unlock();
}

//============================================================
// <T>广播解除所有锁。</T>
//============================================================
void TThreadCondition::Broadcast(){
#ifdef _MO_WINDOWS
#else
   TInt result = pthread_cond_broadcast(&_condition);
   if(ESuccess != result){
      // EINVAL：cv 指示的地址非法。
      MO_PFATAL(pthread_cond_broadcast);
   }
#endif // _MO_WINDOWS
}

MO_NAMESPACE_END
