#include "MoCmSystem.h"
#include "MoCmThread.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>捕捉器处理函数。</T>
//============================================================
void FCatcherConsole_Process(TInt32 code){
   RCatcherManager::Instance().Jump(code);
}

//============================================================
// <T>创建捕捉器控制台。</T>
//============================================================
FCatcherConsole::FCatcherConsole(){
#ifdef _MO_LINUX
   RTypes<SCatcher>::Clear(_catchers, MO_TRAP_ACTION_MAXCNT);
#endif // _LINUX
   _pThreadCatchers = MO_CREATE(FThreadCatcherVector);
}

//============================================================
// <T>释放捕捉器控制台。</T>
//============================================================
FCatcherConsole::~FCatcherConsole(){
   MO_DELETE(_pThreadCatchers);
}

//============================================================
// <T>用指定代码注册捕捉器。</T>
//
// @param code 代码
// @return 处理结果
//============================================================
TBool FCatcherConsole::IsRegistered(TInt code){
   MO_ASSERT_RANGE(code, 0, MO_TRAP_ACTION_MAXCNT);
   TBool result = EFalse;
   //............................................................
   // 检查是否已经安装
   _lockerInstall.Enter();
#ifdef _MO_LINUX
   result = _catchers[code].installed;
#endif // _LINUX
   _lockerInstall.Leave();
   return result;
}

//============================================================
// <T>用指定代码注册捕捉器。</T>
//
// @param code 代码
// @return 处理结果
//============================================================
TBool FCatcherConsole::Register(TInt code){
   MO_ASSERT_RANGE(code, 0, MO_TRAP_ACTION_MAXCNT);
   TBool result = EFalse;
   //............................................................
   // 检查是否已经安装
   _lockerInstall.Enter();
#ifdef _MO_LINUX
   SCatcher& catcher = _catchers[code];
   if(catcher.installed){
      MO_ERROR("Has already install catcher. (code=%2d:%s)", code, strsignal(code));
   }else{
      // 安装处理器
      struct sigaction& action = catcher.source;
      action.sa_handler = FCatcherConsole_Process;
      action.sa_flags = 0;
      sigemptyset(&action.sa_mask);
      TInt actionResult = sigaction(code, &action, &catcher.origin);
      if(ESuccess != actionResult){
         MO_PFATAL(sigaction);
         result = EFalse;
      }else{
         catcher.installed = ETrue;
         result = ETrue;
      }
      MO_INFO("Install catcher action. (code=%2d:%s, handle=0x%08X, mask=0x%08X, flags=0x%08X, result=%d)",
            code, strsignal(code), action.sa_handler, action.sa_mask, action.sa_flags, actionResult);
   }
#endif // _LINUX
   _lockerInstall.Leave();
   return result;
}

//============================================================
// <T>用指定代码注销捕捉器。</T>
//
// @param code 代码
// @return 处理结果
//============================================================
TBool FCatcherConsole::Unregister(TInt code){
   MO_ASSERT_RANGE(code, 0, MO_TRAP_ACTION_MAXCNT);
   TBool result = EFalse;
   //............................................................
   // 检查是否已经安装
   _lockerInstall.Enter();
#ifdef _MO_LINUX
   SCatcher& catcher = _catchers[code];
   if(catcher.installed){
      // 卸载处理器
      TInt actionResult = sigaction(code, &catcher.origin, NULL);
      if(ESuccess != actionResult){
         MO_PFATAL(sigaction);
         result = EFalse;
      }else{
         catcher.installed = EFalse;
         result = ETrue;
      }
      MO_INFO("Uninstall catcher action. (code=%2d:%s, result=%d)", code, strsignal(code), actionResult);
   }else{
      MO_ERROR("Not install any catcher. (code=%2d:%s)", code, strsignal(code));
   }
#endif // _LINUX
   _lockerInstall.Leave();
   return result;
}

//============================================================
// <T>查找指定线程的捕捉器。</T>
//
// @param threadCode 线程代码
// @return 捕捉器
//============================================================
FThreadCatcher* FCatcherConsole::FindThreadCatcher(TInt threadCode){
   FThreadCatcher* pThreadCatcher = NULL;
   _locker.Enter();
   TInt count = _pThreadCatchers->Count();
   for(TInt n = 0; n < count; n++){
      FThreadCatcher* pFind = _pThreadCatchers->Get(n);
      if(pFind->ThreadCode() == threadCode){
         pThreadCatcher = pFind;
         break;
      }
   }
   _locker.Leave();
   return pThreadCatcher;
}

//============================================================
// <T>同步指定线程的捕捉器。</T>
//
// @param threadCode 线程代码
// @return 捕捉器
//============================================================
FThreadCatcher* FCatcherConsole::SyncThreadCatcher(TInt threadCode){
   FThreadCatcher* pThreadCatcher = FindThreadCatcher(threadCode);
   if(NULL == pThreadCatcher){
      pThreadCatcher = MO_CREATE(FThreadCatcher);
      pThreadCatcher->SetThreadCode(threadCode);
      _locker.Enter();
      _pThreadCatchers->Push(pThreadCatcher);
      _locker.Leave();
   }
   return pThreadCatcher;
}

//============================================================
// <T>获得当前线程的捕捉器。</T>
//
// @return 捕捉器
//============================================================
FThreadCatcher* FCatcherConsole::CurrentThreadCatcher(){
   TInt threadCode = RThread::CurrentCode();
   FThreadCatcher* pThreadCatcher = SyncThreadCatcher(threadCode);
   return pThreadCatcher;
}

//============================================================
// <T>注销捕捉器。</T>
//
// @param pCatcher 捕捉器
//============================================================
TBool FCatcherConsole::Enter(FCatcher* pCatcher){
   FThreadCatcher* pThreadCatcher = CurrentThreadCatcher();
   MO_ASSERT(pThreadCatcher);
   return pThreadCatcher->Enter(pCatcher);
}

//============================================================
// <T>注销捕捉器。</T>
//
// @param pCatcher 捕捉器
//============================================================
TBool FCatcherConsole::Leave(FCatcher* pCatcher){
   FThreadCatcher* pThreadCatcher = CurrentThreadCatcher();
   MO_ASSERT(pThreadCatcher);
   return pThreadCatcher->Leave(pCatcher);
}

//============================================================
// <T>跳转处理。</T>
//
// @param code 代码
// @return 处理结果
//============================================================
TBool FCatcherConsole::Jump(TInt code){
   FThreadCatcher* pThreadCatcher = CurrentThreadCatcher();
   MO_ASSERT(pThreadCatcher);
   return pThreadCatcher->Jump(code);
}

//============================================================
// <T>跳转完成处理。</T>
//
// @param pCatcher 捕捉器
// @return 处理结果
//============================================================
TBool FCatcherConsole::JumpFinish(FCatcher* pCatcher){
   FThreadCatcher* pThreadCatcher = CurrentThreadCatcher();
   MO_ASSERT(pThreadCatcher);
   return pThreadCatcher->JumpFinish(pCatcher);
}

MO_NAMESPACE_END
