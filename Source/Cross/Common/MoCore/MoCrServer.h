#ifndef __MO_CR_SERVER_H__
#define __MO_CR_SERVER_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>服务器命令。</T>
//
// @history 100311 MAOCY 创建
//============================================================
enum EServerCommand{
   EServerCommand_Reload,
   EServerCommand_Suspend,
   EServerCommand_Resume
};

//============================================================
// <T>服务器接口。</T>
//
// @history 100311 MAOCY 创建
//============================================================
class MO_CR_DECLARE IServer : public ISingleton{
public:
   MO_VIRTUAL TResult DoStatus(TInt status) = 0;
   MO_VIRTUAL TResult DoCommand(EServerCommand command) = 0;
public:
   MO_VIRTUAL TResult Startup() = 0;
   MO_VIRTUAL TResult Launch() = 0;
   MO_VIRTUAL TResult Reload() = 0;
   MO_VIRTUAL TResult Unload() = 0;
   MO_VIRTUAL TResult Suspend() = 0;
   MO_VIRTUAL TResult Resume() = 0;
   MO_VIRTUAL TResult Shutdown() = 0;
   MO_VIRTUAL TResult Destory() = 0;
};

//============================================================
// <T>服务器基类。</T>
//
// @history 100311 MAOCY 创建
//============================================================
class MO_CR_DECLARE FServer :
      public IServer,
      public MApplicationListeners{
protected:
   TInt _code;
   TFsName _name;
   TBool _stop;
   TBool _ready;
public:
   FServer();
   MO_ABSTRACT ~FServer();
public:
   MO_OVERRIDE TResult Construct();
   MO_OVERRIDE TResult Dispose();
   MO_OVERRIDE TResult Initialize();
   MO_OVERRIDE TResult Release();
public:
   MO_OVERRIDE ESingleton SingletonType();
   MO_OVERRIDE TInt Code();
   MO_OVERRIDE TCharC* Name();
   TBool IsStop() const;
   TBool IsReady() const;
public:
   MO_ABSTRACT TResult OnConstruct();
   MO_ABSTRACT TResult OnLoadConfig();
   MO_ABSTRACT TResult OnInitialize();
   MO_ABSTRACT TResult OnLoadModules();
   MO_ABSTRACT TResult OnLoadMonitors();
   MO_ABSTRACT TResult OnLoadServices();
   MO_ABSTRACT TResult OnStatus(TInt status);
   MO_ABSTRACT TResult OnCommand(EServerCommand command);
   MO_ABSTRACT TResult OnStartup();
   MO_ABSTRACT TResult OnProcess();
   MO_ABSTRACT TResult OnReload();
   MO_ABSTRACT TResult OnUnload();
   MO_ABSTRACT TResult OnSuspend();
   MO_ABSTRACT TResult OnResume();
   MO_ABSTRACT TResult OnShutdown();
   MO_ABSTRACT TResult OnDestory();
public:
   MO_OVERRIDE TResult DoStatus(TInt status);
   MO_OVERRIDE TResult DoCommand(EServerCommand command);
public:
   MO_OVERRIDE TResult Startup();
   MO_OVERRIDE TResult Launch();
   MO_OVERRIDE TResult Process();
   MO_OVERRIDE TResult Reload();
   MO_OVERRIDE TResult Unload();
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Shutdown();
   MO_OVERRIDE TResult Destory();
};

MO_NAMESPACE_END

#endif // __MO_CR_SERVER_H__
