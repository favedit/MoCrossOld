#ifndef __MO_CR_WORKER_H__
#define __MO_CR_WORKER_H__
//************************************************************

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>资源工作器类型。</T>
//============================================================
enum EWorkerStatus{
   EWorkerStatus_Unknown     = 0,
   EWorkerStatus_Wait        = 1,
   EWorkerStatus_Processiong = 2,
   EWorkerStatus_Finish      = 3,
};

//============================================================
// <T>工作器。</T>
//============================================================
class MO_CR_DECLARE FWorker : public FObject{
protected:
   TFsName _name;
   EWorkerStatus _statusCd;
public:
   FWorker();
   MO_ABSTRACT ~FWorker();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>获得状态。</T>
   MO_INLINE EWorkerStatus StatusCd(){
      return _statusCd;
   }
   //------------------------------------------------------------
   // <T>设置状态。</T>
   MO_INLINE void SetStatusCd(EWorkerStatus statusCd){
      _statusCd = statusCd;
   }
public:
   MO_VIRTUAL TResult OnProcess() = 0;
   MO_ABSTRACT TResult OnSuccess();
   MO_ABSTRACT TResult OnFailure();
public:
   MO_ABSTRACT TResult Process();
};
//------------------------------------------------------------
typedef MO_CR_DECLARE FVector<FWorker*> FWorkerVector;
typedef MO_CR_DECLARE FList<FWorker*> FWorkerList;

//============================================================
// <T>工作器线程。</T>
//============================================================
class MO_CR_DECLARE FWorkerThread : public FThread{
protected:
   FWorkerVector* _pWorkers;
public:
   FWorkerThread();
   MO_ABSTRACT ~FWorkerThread();
public:
   MO_OVERRIDE TResult Process();
};

//============================================================
// <T>工作器控制台。</T>
//============================================================
class MO_CR_DECLARE FWorkerConsole : public FConsole{
protected:
   TThreadMutex _mutex;
   FWorkerThread* _pThread;
   FWorkerVector* _pWorkers;
public:
   FWorkerConsole();
   MO_ABSTRACT ~FWorkerConsole();
public:
   //------------------------------------------------------------
   // <T>获得工作器线程。</T>
   MO_INLINE FWorkerThread* Thread(){
      return _pThread;
   }
   //------------------------------------------------------------
   // <T>获得工作器集合。</T>
   MO_INLINE FWorkerVector* Workers(){
      return _pWorkers;
   }
public:
   void Startup();
   void Shutdown();
public:
   void FetchWorkers(FWorkerVector* pWorkers);
public:
   void Register(FWorker* pWorker);
   void Unregister(FWorker* pWorker);
public:
   void Refresh();
};

//============================================================
// <T>工作器管理器。</T>
//============================================================
class MO_CR_DECLARE RWorkerManager : public RSingleton<FWorkerConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_CR_WORKER_H__
