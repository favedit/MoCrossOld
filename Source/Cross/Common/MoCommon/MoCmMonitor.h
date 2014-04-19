#ifndef __MO_CM_MONITOR_H__
#define __MO_CM_MONITOR_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_THREAD_H__
#include "MoCmThread.h"
#endif // __MO_CM_THREAD_H__

#ifndef __MO_CM_SYSTEM_H__
#include "MoCmSystem.h"
#endif // __MO_CM_SYSTEM_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>定时器陷阱扑捉器。</T>
//============================================================
class FMonitorCatcher : public FCatcher{
public:
   FMonitorCatcher();
public:
   MO_OVERRIDE TBool Install();
};

//============================================================
// <T>监视器接口类。</T>
//
// @face
// @history 091210 MAOCY 创建
//============================================================
class MO_CM_DECLARE IMonitor{
public:
   MO_ABSTRACT ~IMonitor(){
   }
public:
   MO_VIRTUAL TBool IsName(TCharC* pName) const = 0;
   MO_VIRTUAL TCharC* Name() const = 0;
   MO_VIRTUAL TBool IsLock() const = 0;
   MO_VIRTUAL TInt64 Total() const = 0;
   MO_VIRTUAL TInt64 Count() const = 0;
   MO_VIRTUAL TTimeSpan Delay() const = 0;
   MO_VIRTUAL TTimeSpan Interval() const = 0;
   MO_VIRTUAL TInt64 ProcessCount() const = 0;
   MO_VIRTUAL TTimeSpan ProcessTimeout() const = 0;
   MO_VIRTUAL TDateTime CurrentDateTime() = 0;
   MO_VIRTUAL TTimeTick CurrentTimeTick() = 0;
   MO_VIRTUAL TTimeTick CurrentTimeSpan() = 0;
   MO_VIRTUAL FCatcher* Catcher() = 0;
   MO_VIRTUAL void SetCatcher(FCatcher* pCatcher) = 0;
public:
   MO_VIRTUAL TBool Test(TTimeTick currentTick) = 0;
public:
   MO_VIRTUAL TBool LoadConfig(FXmlNode* pConfig) = 0;
public:
   MO_VIRTUAL TResult Startup() = 0;
   MO_VIRTUAL TResult Shutdown() = 0;
public:
   MO_VIRTUAL TResult Execute(TTimeTick currentTick) = 0;
   MO_VIRTUAL TResult Process() = 0;
};

//============================================================
// <T>监视器基类。</T>
//
// @manager
// @history 100324 MAOCY 创建
//============================================================
class MO_CM_DECLARE FMonitor :
      public FObject,
      public IMonitor{
protected:
   TFsName _name;
   TBool _lock;
   TTimeSpan _delay;
   TTimeSpan _interval;
   TInt64 _total;
   TInt64 _count;
   TInt64 _processCount;
   TTimeSpan _processTimeout;
   TDateTime _currentDateTime;
   TTimeTick _currentTimeTick;
   TTimeSpan _currentTimeSpan;
   TTimeTick _lastTimeTick;
   TBool _started;
   TBool _needDelay;
   FCatcher* _pCatcher;
   TSpeedStatistics _statistics;
public:
   FMonitor();
   MO_ABSTRACT ~FMonitor();
public:
   MO_OVERRIDE TBool IsName(TCharC* pName) const;
   MO_OVERRIDE TCharC* Name() const;
   void SetName(TCharC* pName);
   MO_OVERRIDE TBool IsLock() const;
   MO_OVERRIDE TInt64 Total() const;
   void SetTotal(TInt64 total);
   MO_OVERRIDE TInt64 Count() const;
   MO_OVERRIDE TTimeSpan Delay() const;
   void SetDelay(TTimeSpan delay);
   MO_OVERRIDE TTimeSpan Interval() const;
   void SetInterval(TTimeSpan interval);
   MO_OVERRIDE TInt64 ProcessCount() const;
   void SetProcessCount(TInt64 count);
   MO_OVERRIDE TTimeSpan ProcessTimeout() const;
   void SetProcessTimeout(TTimeSpan timeout);
   MO_OVERRIDE TDateTime CurrentDateTime();
   MO_OVERRIDE TTimeTick CurrentTimeTick();
   MO_OVERRIDE TTimeTick CurrentTimeSpan();
   MO_OVERRIDE FCatcher* Catcher();
   MO_OVERRIDE void SetCatcher(FCatcher* pCatcher);
public:
   // ------------------------------------------------------------
   // <T>获得统计信息。</T>
   MO_INLINE TSpeedStatistics& Statistics(){
      return _statistics;
   }
public:
   MO_OVERRIDE TBool Test(TTimeTick currentTick);
public:
   MO_OVERRIDE TBool LoadConfig(FXmlNode* pConfig);
public:
   MO_OVERRIDE TResult Startup();
   MO_OVERRIDE TResult Execute(TTimeTick currentTick);
   MO_OVERRIDE TResult Shutdown();
};
// ------------------------------------------------------------
typedef MO_CM_DECLARE FList<IMonitor*> FMonitorList;
typedef MO_CM_DECLARE FLooper<IMonitor*> FMonitorLooper;

//============================================================
// <T>监视器刷新接口类。</T>
//
// @face
//============================================================
class MO_CM_DECLARE IMonitorTrigger{
public:
   MO_ABSTRACT ~IMonitorTrigger(){
   }
public:
   MO_VIRTUAL TResult TriggerRefresh(TTimeTick currentTick) = 0;
};

//============================================================
// <T>监视器触发器。</T>
//
// @class
//============================================================
class MO_CM_DECLARE FMonitorTrigger : public FMonitor{
protected:
   IMonitorTrigger* _pTrigger;
public:
   FMonitorTrigger();
   MO_ABSTRACT ~FMonitorTrigger();
public:
   //------------------------------------------------------------
   // <T>获得触发器。</T>
   MO_INLINE IMonitorTrigger* Trigger(){
      return _pTrigger;
   }
   //------------------------------------------------------------
   // <T>设置触发器。</T>
   MO_INLINE void SetTrigger(IMonitorTrigger* pTrigger){
      _pTrigger = pTrigger;
   }
public:
   MO_OVERRIDE TResult Process();
};

//============================================================
// <T>内存陷阱块定义。</T>
//============================================================
class MO_CM_DECLARE FMonitorMachine : public FObject{
protected:
   TTimeSpan _interval;
   TTimeTick _trackTick;
   IThreadLocker* _pLocker;
   FMonitorCatcher* _pCatcher;
   FMonitorList* _pMonitors;
   FMonitorLooper* _pLooper;
public:
   FMonitorMachine();
   MO_ABSTRACT ~FMonitorMachine();
public:
   //------------------------------------------------------------
   // <T>获得间隔。</T>
   MO_INLINE TTimeSpan Interval(){
      return _interval;
   }
   //------------------------------------------------------------
   // <T>设置间隔。</T>
   MO_INLINE void SetInterval(TTimeSpan interval){
      _interval = interval;
   }
   //------------------------------------------------------------
   // <T>获得同步器。</T>
   MO_INLINE IThreadLocker* Section(){
      return _pLocker;
   }
   //------------------------------------------------------------
   // <T>设置同步器。</T>
   MO_INLINE void SetLocker(IThreadLocker* pLocker){
      _pLocker = pLocker;
   }
   //------------------------------------------------------------
   // <T>获得监视器集合。</T>
   MO_INLINE FMonitorList* Monitors(){
      return _pMonitors;
   }
public:
   IMonitor* FindByName(TCharC* pName);
public:
   TBool LoadConfig(FXmlNode* pConfig);
public:
   void Register(IMonitor* pMonitor);
   void Unregister(IMonitor* pMonitor);
public:
   void Startup();
   void Execute();
   void Process();
};

//============================================================
// <T>监视器线程。</T>
//============================================================
class MO_CM_DECLARE FMonitorThread : public FThread{
protected:
   FMonitorMachine* _pMachine;
public:
   FMonitorThread(FMonitorMachine* pMachine);
public:
   MO_OVERRIDE TResult Process();
};

//============================================================
// <T>监视器控制台。</T>
//============================================================
class MO_CM_DECLARE FMonitorConsole : public FConsole{
protected:
   TThreadLocker _locker;
   FMonitorThread* _pThread;
   FMonitorMachine* _pMachine;
public:
   FMonitorConsole();
   MO_ABSTRACT ~FMonitorConsole();
public:
   FMonitorMachine* Machine();
   TTimeSpan Interval();
   void SetInterval(TTimeSpan interval);
   void Register(IMonitor* pMonitor);
   void Unregister(IMonitor* pMonitor);
   void Startup();
   void Shutdown();
   void Execute();
};
//------------------------------------------------------------
class MO_CM_DECLARE RMonitorManager : public RSingleton<FMonitorConsole>{
};

MO_NAMESPACE_END

#endif // __MO_CM_MONITOR_H__
