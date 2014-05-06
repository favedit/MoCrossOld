#include "MoCmThread.h"
#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>执行线程处理(WINDOW)。</T>
//
// @param pParam 参数指针
// @return 处理结果
//============================================================
#ifdef _MO_SYS_WINDOWS
TDword MO_STDCALL __MO_THREAD_CALLBACK(TAny* pParam){
   // 获得线程对象
   FThread* pThread = (FThread*)pParam;
   MO_ASSERT(pThread);
   // 执行线程处理
   TInt result = EFailure;
   if(pThread->OnStart()){
      try{
         MO_TRAP( result = pThread->Process() );
      }catch(TInt code){
         pThread->OnCancel(code);
      }
      pThread->OnFinish(result);
   }
   // 释放线程资源，如果存在线程组，则有线程组负责释放线程。
   if(NULL == pThread->ThreadGroup()){
      MO_PTR_DELETE(pThread);
   }
   return result;
}
#endif // _MO_SYS_WINDOWS

//============================================================
// <T>执行线程处理(LINUX)。</T>
//
// @param pParam 参数指针
// @return 处理结果
//============================================================
#ifdef _MO_SYS_LINUX
TAny* MO_STDCALL __MO_THREAD_CALLBACK(TAny* pParam){
   // 获得线程对象
   FThread* pThread = (FThread*)pParam;
   MO_ASSERT(pThread);
   // 将自己放入线程管理器里
   RThreadManager::Instance().Register(pThread);
   // 执行线程处理
   TInt result = EFailure;
   if(pThread->OnStart()){
#ifdef _MO_ANDROID
      result = pThread->Process();
#else
      try{
         MO_TRAP( result = pThread->Process() );
      }catch(TInt code){
         pThread->OnCancel(code);
      }
#endif // _MO_ANDROID
      pThread->OnFinish(result);
   }
   // 释放线程资源
   pthread_exit((TAny*)result);
   MO_PTR_DELETE(pThread);
   return (TAny*)result;
}
#endif // _MO_SYS_LINUX

//============================================================
// <T>启动线程处理。</T>
//============================================================
FThread::FThread(){
   _typeCd = EThread_Thread;
   _flag = 0;
   _index = 1;
   _code = TC("WRK");
   _name = TC("Work Thread");
   _handle = 0;
   _status = EThreadStatus_Initialize;
   // 清空线程组
   MO_CLEAR(_pThreadGroup);
   // 构造线程陷阱
   _pThreadTrap = MO_PTR_CREATE(FThreadTrap);
   // 构造线程内存
   _pThreadMemory = MO_PTR_CREATE(FThreadMemory);
#ifdef _MO_LINUX
   // 初始化互斥锁
   pthread_mutex_init(&_mutex, NULL);
   // 初始化条件变量
   pthread_cond_init(&_condition, NULL);
#endif
}

//============================================================
// <T>终止线程处理。</T>
//============================================================
FThread::~FThread(){
#ifdef _MO_WINDOWS
   // 关闭线程句柄
   if(NULL != _handle){
      CloseHandle(_handle);
      _handle = NULL;
   }
#else
   pthread_mutex_destroy(&_mutex);
   pthread_cond_destroy(&_condition);
#endif
   // 释放陷阱
   MO_PTR_DELETE(_pThreadTrap);
   // 释放内存
   MO_PTR_DELETE(_pThreadMemory);
   // 将自己从线程管理器里注销
   if(NULL != RThreadManager::InstancePtr()){
      RThreadManager::Instance().Unregister(this);
   }
}

//============================================================
// <T>获得线程优先级。</T>
//
// @return 线程优先级
//============================================================
TThreadHandle FThread::Handle() const{
   return _handle;
}

//============================================================
// <T>获得线程优先级。</T>
//
// @return 线程优先级
//============================================================
TThreadId FThread::ThreadId() const{
#ifdef _MO_WINDOWS
   return _threadId;
#else
   return _handle;
#endif
}

//============================================================
// <T>获得线程优先级。</T>
//
// @return 线程优先级
//============================================================
EThreadPriority FThread::Priority() const{
   return _priority;
}

//============================================================
// <T>设置线程优先级。</T>
//
// @param priority 线程优先级
//============================================================
void FThread::SetPriority(EThreadPriority priority){
   _priority = priority;
}

//============================================================
// <T>判断是否线程状态。</T>
//
// @param status 状态
// @return 是否线程状态
//============================================================
TBool FThread::IsStatus(EThreadStatus status) const{
   return (_status == status);
}

//============================================================
// <T>获得线程状态。</T>
//
// @return 线程状态
//============================================================
EThreadStatus FThread::Status() const{
   return _status;
}

//============================================================
// <T>判断线程是否停止。</T>
//
// @return 是否停止
//============================================================
TBool FThread::IsStop() const{
   if(EThreadStatus_Running == _status){
      return EFalse;
   }else if(EThreadStatus_Suspend == _status){
      return EFalse;
   }else if(EThreadStatus_Wait == _status){
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>获得线程组。</T>
//
// @return 线程组
//============================================================
FThreadGroup* FThread::ThreadGroup(){
   return _pThreadGroup;
}

//============================================================
// <T>设置线程组。</T>
//
// @param pThreadGroup 线程组
//============================================================
void FThread::SetThreadGroup(FThreadGroup* pThreadGroup){
   _pThreadGroup = pThreadGroup;
}

//============================================================
// <T>获得线程陷阱。</T>
//
// @return 线程优先级
//============================================================
FThreadTrap* FThread::ThreadTrap(){
   return _pThreadTrap;
}

//============================================================
// <T>获得线程内存。</T>
//
// @return 线程优先级
//============================================================
FThreadMemory* FThread::ThreadMemory(){
   return _pThreadMemory;
}

//============================================================
// <T>线程开始处理。</T>
//============================================================
TBool FThread::OnStart(){
   _status = EThreadStatus_Running;
#ifdef _MO_LINUX
#ifndef __CYGWIN__
   TInt threadPid = syscall(__NR_gettid);
   MO_INFO(TC("Execute thread start (name=%s, handle=%lu, tid=%d)"),
         (TCharC*)_name, _handle, threadPid);
#endif
#else
   MO_INFO(TC("Execute thread start (name=%s, handle=%lu)"),
         (TCharC*)_name, _handle);
#endif
   return ETrue;
}

//============================================================
// <T>线程取消处理。</T>
//============================================================
TBool FThread::OnCancel(TInt code){
   _status = EThreadStatus_Cancel;
   MO_ERROR(TC("Execute thread cancel (name=%s, code=%d)"), (TCharC*)_name, code);
   return ETrue;
}

//============================================================
// <T>线程结束处理。</T>
//============================================================
TBool FThread::OnFinish(TInt result){
   _status = EThreadStatus_Finish;
   MO_INFO(TC("Execute thread finish (name=%s, result=%d)"), (TCharC*)_name, result);
   return ETrue;
}

//============================================================
// <T>启动线程处理。</T>
//
// @return 处理结果
//============================================================
TResult FThread::Start(){
#ifdef _MO_SYS_WINDOWS
   MO_ASSERT(NULL == _handle);
   // 创建等待线程
   _handle = CreateThread(NULL, 0, __MO_THREAD_CALLBACK, this, CREATE_SUSPENDED, &_threadId);
   MO_ASSERT(_handle);
   // 将自己放入线程管理器里
   RThreadManager::Instance().Register(this);
   // 开始执行线程
   ResumeThread(_handle);
#endif // _MO_SYS_WINDOWS
#ifdef _MO_SYS_LINUX
   TInt result = pthread_create(&_handle, NULL, __MO_THREAD_CALLBACK, this);
   if(ESuccess != result){
      MO_PFATAL("pthread_create");
   }
#endif // _MO_SYS_LINUX
   return ESuccess;
}

//============================================================
// <T>放弃执行一次。</T>
//
// @return 处理结果
//============================================================
TResult FThread::Idle(){
   MO_LIB_IDLE();
   return ESuccess;
}

//============================================================
// <T>挂起线程处理。</T>
//
// @return 处理结果
//============================================================
TResult FThread::Suspend(){
   MO_ASSERT(_handle);
   MO_DEBUG(TC("Thread suspend begin.(thread=0x%08X)"), this);
   _status = EThreadStatus_Suspend;
#ifdef _MO_WINDOWS
   SuspendThread(_handle);
#else
   pthread_mutex_lock(&_mutex);
   pthread_cond_wait(&_condition, &_mutex);
   pthread_mutex_unlock(&_mutex);
#endif
   MO_DEBUG(TC("Thread suspend end.(thread=0x%08X)"), this);
   return ESuccess;
}

//============================================================
// <T>继续线程处理。</T>
//
// @return 处理结果
//============================================================
TResult FThread::Resume(){
   MO_ASSERT(_handle);
   MO_DEBUG(TC("Thread resume begin.(thread=0x%08X)"), this);
#ifdef _MO_WINDOWS
   ResumeThread(_handle);
#else
   pthread_mutex_lock(&_mutex);
   pthread_cond_signal(&_condition);
   pthread_mutex_unlock(&_mutex);
#endif
   _status = EThreadStatus_Running;
   MO_DEBUG(TC("Thread resume end.(thread=0x%08X)"), this);
   return ESuccess;
}

//============================================================
// <T>等待其他线程结束。</T>
//
// @return 处理结果
//============================================================
TResult FThread::Join(IThread* pThread){
#ifdef _MO_WINDOWS
   MO_ASSERT(_handle);
   WaitForSingleObject(pThread->Handle(), INFINITE);
#else
   TAny* pResult;
   pthread_join(pThread->Handle(), (TAny**)&pResult);
#endif
   return ESuccess;
}

//============================================================
// <T>等待其他线程结束。</T>
//============================================================
TAny* FThread::Wait(){
   // 停止时直接返回
   MO_ASSERT(_handle);
   TAny* pResult = NULL;
#ifdef _MO_WINDOWS
   WaitForSingleObject(_handle, INFINITE);
#else
   pthread_join(_handle, (TAny**)&pResult);
#endif // _MO_WINDOWS
   return pResult;
}

//============================================================
// <T>停止线程处理。</T>
// <P>只是一个标志位变更操作。</P>
//
// @return 处理结果
//============================================================
TResult FThread::Stop(){
   MO_ASSERT(_handle);
   _status = EThreadStatus_Stop;
   return ESuccess;
}

//============================================================
// <T>停止线程处理。</T>
//
// @return 处理结果
//============================================================
TResult FThread::Terminate(){
#ifdef _MO_WINDOWS
   MO_ASSERT(_handle);
   TerminateThread(_handle, 0);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   pthread_cancel(_handle);
#endif // _MO_LINUX
#ifdef _MO_FLASCC
   pthread_cancel(_handle);
#endif // _MO_FLASCC
#ifdef _MO_ANDROID
   // MO_FATAL_UNSUPPORT();
#endif // _MO_ANDROID
   return ESuccess;
}

//============================================================
// <T>当前线程睡眠处理。</T>
//
// @param interval 间隔
//============================================================
void FThread::Sleep(TInt interval){
   MO_LIB_SLEEP(interval);
}

//============================================================
// <T>当前线程睡眠处理。</T>
//
// @param interval 间隔
//============================================================
void FThread::SleepMicro(TInt interval){
   MO_LIB_MICRO_SLEEP(interval);
}

MO_NAMESPACE_END
