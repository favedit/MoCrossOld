#ifndef __MO_FR_LOADER_H__
#define __MO_FR_LOADER_H__
//************************************************************

#ifndef __MO_FR_COMMON_H__
#include "MoFrCommon.h"
#endif // __MO_FR_COMMON_H__

MO_NAMESPACE_BEGIN

class FResource;
class FFileLoader;
class FLoaderWorker;

//============================================================
// <T>加载器。</T>
//============================================================
class MO_FR_DECLARE FLoader : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FLoader, FInstance);
public:
   FLoader();
   MO_ABSTRACT ~FLoader();
public:
   MO_ABSTRACT TResult Process();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE FList<FLoader*> FLoaderList;

//============================================================
// <T>加载器监视器。</T>
//============================================================
class MO_FR_DECLARE FLoaderMonitor : public FMonitor
{
   MO_CLASS_DECLARE_INHERITS(FLoaderMonitor, FMonitor);
public:
   FLoaderMonitor();
   MO_ABSTRACT ~FLoaderMonitor();
public:
   MO_OVERRIDE TResult Process();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtr<FLoaderMonitor> GLoaderMonitorPtr;

//============================================================
// <T>加载器控制台。</T>
//============================================================
class MO_FR_DECLARE FLoaderConsole : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FLoaderConsole, FConsole);
protected:
   TThreadLocker _locker;
   GLoaderMonitorPtr _monitor;
   FLoaderList* _pWaitLoaders;
public:
   FLoaderConsole();
   MO_ABSTRACT ~FLoaderConsole();
public:
   //------------------------------------------------------------
   // <T>获得等待的加载器集合。</T>
   MO_INLINE FLoaderList* WaitLoaders(){
      return _pWaitLoaders;
   }
public:
   TResult Startup();
   TResult Shutdown();
public:
   TResult PushWaitLoader(FLoader* pLoader);
   FLoader* PopWaitLoader();
};

//============================================================
// <T>加载器管理器。</T>
//============================================================
class MO_FR_DECLARE RLoaderManager : public RSingleton<FLoaderConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FG_LOADER_H__
