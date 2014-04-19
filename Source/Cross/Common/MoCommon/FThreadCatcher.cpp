#include "MoCmSystem.h"

#define MO_SY_STACK_TRACK_MAXCNT 64

MO_NAMESPACE_BEGIN

//============================================================
// <T>创建线程捕捉器。</T>
//============================================================
FThreadCatcher::FThreadCatcher(){
   _threadCode = 0;
   MO_CLEAR(_pActiveCatcher);
   MO_CLEAR(_pJumpCatcher);
   _pCatchers = MO_CREATE(FCatcherVector);
}

//============================================================
// <T>释放线程捕捉器。</T>
//============================================================
FThreadCatcher::~FThreadCatcher(){
   MO_DELETE(_pCatchers);
}

//============================================================
// <T>进入扑捉器的有效范围。</T>
//
// @param pCatcher 扑捉器
// @return 处理结果
//============================================================
TBool FThreadCatcher::Enter(FCatcher* pCatcher){
   _lockerActive.Enter();
   pCatcher->SetParent(_pActiveCatcher);
#ifdef _MO_DEBUG
   if(_pCatchers->Contains(pCatcher)){
      MO_FATAL(TC("Catcher is already exists. (catcher=0x%08X:%s)"), pCatcher, pCatcher->Name());
   }
#endif // _MO_DEBUG
   _pCatchers->Push(pCatcher);
   _pActiveCatcher = pCatcher;
   _lockerActive.Leave();
   return ETrue;
}

//============================================================
// <T>离开扑捉器的有效范围。</T>
//
// @param pCatcher 扑捉器
// @return 处理结果
//============================================================
TBool FThreadCatcher::Leave(FCatcher* pCatcher){
   _lockerActive.Enter();
   _pActiveCatcher = pCatcher->Parent();
   FCatcher* pEnterCatcher = _pCatchers->Pop();
   if(pCatcher != pEnterCatcher){
      MO_FATAL(TC("Enter and leave catcher is not same. (enter=0x%08X:%s, leave=0x%08X:%s)"),
            pEnterCatcher, pEnterCatcher->Name(), pCatcher, pCatcher->Name());
   }
   _lockerActive.Leave();
   return ETrue;
}

//============================================================
// <T>跟踪捕捉器。</T>
//
// @param pCatcher 捕捉器
//============================================================
void FThreadCatcher::Track(FCatcher* pCatcher, TInt code){
   TFsDump info;
#ifdef _MO_LINUX
   TCharC* pCodeName = strsignal(code);
#else
   TCharC* pCodeName = TC("Unknown");
#endif // _LINUX
   //............................................................
   info.Append(TC("System rais error."));
   info.Append(TC("\n-- Crash track info begin ----------------------------------"));
   if(NULL != pCatcher){
      info.AppendFormat(TC("\n Catch success. (name=%s@0x%08X, code=%d:%s, info=%s)"),
         pCatcher->Name(), pCatcher, code, pCodeName, pCatcher->Info());
   }else{
      info.AppendFormat(TC("\n Catch failure. (pointer=0x%08X, code=%d:%s)"),
         pCatcher, code, pCodeName);
   }
   //............................................................
   // 输出安装的捕捉器
   TInt countCatcher = _pCatchers->Count();
   for(TInt n = 0; n < countCatcher; n++){
      FCatcher* pFind  = _pCatchers->Get(n);
      info.AppendFormat(TC("\n   Installed (%2d = %s@0x%08X (jump_count=%4d) - %s)"),
         n++, pFind->Name(), pFind, pFind->JumpCount(), pFind->Info());
   }
   //............................................................
   info.Append(TC("\n Crash track info [ BS you begin]"));
   RSystem::FetchStack(&info);
   info.Append(TC("\n Crash track info [ BS you end]"));
   info.Append(TC("\n-- Crash track info end ------------------------------------"));
   MO_DUMP_STACK((TCharC*)info);
   RLoggerManager::Instance().Flush();
}

//============================================================
// <T>注销一个捕捉器。</T>
//
// @param code 代码
// @return 处理结果
//============================================================
TBool FThreadCatcher::Jump(TInt code){
   // 是否注册过激活捕捉器
   if(NULL == _pActiveCatcher){
      MO_ERROR(TC("Active catcher is not exists."));
      MO_THROW(TC("Uncatcher exception."));
      return EFalse;
   }
   // 检查上层
   _pJumpCatcher = _pActiveCatcher;
   while(NULL != _pJumpCatcher){
      if(_pJumpCatcher->IsRegistered(code)){
         break;
      }
      // 弹出顶层对象
      FCatcher* pCatcher = _pCatchers->Pop();
      if(pCatcher == _pJumpCatcher){
         MO_FATAL(TC("Pop catcher and jump not same. (jump_captcher=%s:0x%08X, captcher=%s:0x%08X)"),
               _pJumpCatcher->Name(), _pJumpCatcher, pCatcher->Name(), pCatcher);
      }
      _pJumpCatcher = _pJumpCatcher->Parent();
   }
   // 检查参数
   if(NULL == _pJumpCatcher){
      MO_FATAL(TC("Not any catcher catch this code. (code=%d)"), code);
   }
   _pActiveCatcher = _pJumpCatcher;
   // 跟踪捕捉器
   Track(_pJumpCatcher, code);
   // 开始跳转
   _lockerJump.Enter();
   _pJumpCatcher->Jump();
   return ETrue;
}

//============================================================
// <T>跳转结束处理。</T>
//
// @return 处理结果
//============================================================
TBool FThreadCatcher::JumpFinish(FCatcher* pCatcher){
   MO_ASSERT(pCatcher);
   // 检查是否存在激活
   if(NULL == _pJumpCatcher){
      MO_ERROR(TC("Catcher long jump failure, lost jump captcher. (jump_catcher=0x%08X, catcher=0x%08X)"), _pJumpCatcher, pCatcher);
      return EFalse;
   }
   // 检查跳转器
   if(_pJumpCatcher != pCatcher){
      MO_ERROR(TC("Catcher long jump failure, jump and active not same. (jump_catcher=%s@0x%08X, catcher=%s@0x%08X)"),
            _pJumpCatcher->Name(), _pJumpCatcher, pCatcher->Name(), pCatcher);
   }
   // 设置标志
   MO_WARN(TC("Trap catcher long jump success. (catcher=%s@0x%08X, jumping=%d, valid=%d)"),
         _pJumpCatcher->Name(), _pJumpCatcher, _pJumpCatcher->IsJumping(), _pJumpCatcher->IsValid());
   MO_CLEAR(_pJumpCatcher);
   // 跳转完成
   _lockerJump.Leave();
   return ETrue;
}

MO_NAMESPACE_END
