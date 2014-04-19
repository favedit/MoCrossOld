#include "MoCmSystem.h"
#include "MoCmThread.h"

#define MO_CM_CATCHER_INFO TC("None")

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造捕捉器。</T>
//============================================================
FCatcher::FCatcher(){
   _name = TC("Unknown");
   _pInfo = MO_CM_CATCHER_INFO;
   MO_CLEAR(_pParent);
   _installed = EFalse;
   _recorded = EFalse;
   _valid = ETrue;
   _jumping = EFalse;
   _jumpCount = 0;
#ifdef _MO_LINUX
   memset(&_jmpbuf, 0, sizeof(jmp_buf));
#endif // _LINUX
   RBools::Clear(_actions, MO_TRAP_ACTION_MAXCNT);
}

//============================================================
// <T>析构捕捉器。</T>
//============================================================
FCatcher::~FCatcher(){
}

//============================================================
// <T>是否注册了代码。</T>
//
// @param code 代码
//============================================================
TBool FCatcher::IsRegistered(TInt code){
   MO_ASSERT_RANGE(code, 0, MO_TRAP_ACTION_MAXCNT);
   return _actions[code];
}

//============================================================
// <T>注册代码。</T>
//
// @param code 代码
//============================================================
TBool FCatcher::Register(TInt code){
   MO_ASSERT_RANGE(code, 0, MO_TRAP_ACTION_MAXCNT);
   TBool result = EFalse;
   if(_actions[code] == EFalse){
      _actions[code] = ETrue;
      // 管理器中安装捕捉器
      if(!RCatcherManager::Instance().IsRegistered(code)){
         result = RCatcherManager::Instance().Register(code);
      }else{
         result = ETrue;
      }
   }
   return result;
}

//============================================================
// <T>注销代码。</T>
//
// @param code 代码
//============================================================
TBool FCatcher::Unregister(TInt code){
   MO_ASSERT_RANGE(code, 0, MO_TRAP_ACTION_MAXCNT);
   TBool result = EFalse;
   if(_actions[code] == ETrue){
      _actions[code] = EFalse;
      // 管理器中卸载捕捉器
      if(RCatcherManager::Instance().IsRegistered(code)){
         result = RCatcherManager::Instance().Unregister(code);
      }else{
         result = ETrue;
      }
   }
   return result;
}

//============================================================
// <T>安装处理。</T>
//============================================================
TBool FCatcher::Install(){
   return ETrue;
}

//============================================================
// <T>卸载处理。</T>
//============================================================
TBool FCatcher::Uninstall(){
   return ETrue;
}

//============================================================
// <T>进入捕捉范围处理。</T>
//
// @return 处理结果
//============================================================
TBool FCatcher::Enter(){
   // 设置标志
   _valid = ETrue;
   _recorded = EFalse;
   _jumping = EFalse;
   // 进入处理
   TBool result = RCatcherManager::Instance().Enter(this);
   return result;
}

//============================================================
// <T>离开捕捉范围处理。</T>
//
// @return 处理结果
//============================================================
TBool FCatcher::Leave(){
   // 离开处理
   TBool result = RCatcherManager::Instance().Leave(this);
   // 设置标志
   _valid = EFalse;
   _recorded = EFalse;
   _jumping = EFalse;
   return result;
}

//============================================================
// <T>记录当前运行信息。</T>
//
// @return 是否可执行
//============================================================
TBool FCatcher::Record(){
   TBool valid = ETrue;
#ifdef _MO_LINUX
   // 存储运行信息
   TInt result = sigsetjmp(_jmpbuf, 1);
   if(0 == result){
      // 安装跳转器
      if(_jumping){
         valid = EFalse;
         MO_ERROR("Already in jumping, install captcher failure. (catcher=%s@0x%08X, jumping=%d, result=%d)",
               (TCharC*)_name, this, _jumping, result);
      }else{
         _recorded = ETrue;
      }
   }else{
      // 从其他地发跳转后 (this指针不再是自己)
      RCatcherManager::Instance().JumpFinish(this);
      valid = EFalse;
   }
#endif // _LINUX
   return valid;
}

//============================================================
// <T>记录当前运行信息。</T>
//
// @return 是否可执行
//============================================================
TBool FCatcher::Recorded(){
   _recorded = ETrue;
   return ETrue;
}

//============================================================
// <T>跳转到记录运行信息。</T>
//============================================================
TBool FCatcher::Jump(){
   // 检查是否记录
   if(!_recorded){
      MO_WARN(TC("Trap catcher is not record. (catcher=%s@0x%08X, valid=%d, record=%d, info=%s)"),
            (TCharC*)_name, this, _valid, _recorded, _pInfo);
      return EFalse;
   }
   // 设置标志
   _jumping = ETrue;
   _valid = EFalse;
   MO_WARN(TC("Trap catcher long jump start. (catcher=%s@0x%08X, valid=%d, jumping=%d, info=%s)"),
         (TCharC*)_name, this, _valid, _jumping, _pInfo);
#ifdef _MO_LINUX
   // 跳转处理
   siglongjmp(_jmpbuf, 1);
#endif // _LINUX
   return ETrue;
}

//============================================================
// <T>结束处理。</T>
//============================================================
TBool FCatcher::JumpFinish(){
   // 检查是否正常捕捉
   if(!_jumping){
      MO_ERROR(TC("Catcher long jump failure, unknown active jumper. (catcher=%s@0x%08X, jumping=%d, valid=%d)"),
            (TCharC*)_name, this, _jumping, _valid);
      return EFalse;
   }
   // 设置标志
   _jumping = EFalse;
   _jumpCount++;
   // 跳转完成
   return RCatcherManager::Instance().JumpFinish(this);
}

MO_NAMESPACE_END
