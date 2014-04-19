#include "MoCmMonitor.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造定时器陷阱扑捉器。</T>
//============================================================
FMonitorCatcher::FMonitorCatcher(){
   _name = TC("monitor.trap.catcher");
}

//============================================================
// <T>安装定时器陷阱扑捉器。</T>
//============================================================
TBool FMonitorCatcher::Install(){
#ifdef _MO_TRAP
   // 扑捉非法操作例外
   Register(SIGILL);
   // 扑捉非法调试例外
   Register(SIGTRAP);
   // 扑捉非法地址例外
   Register(SIGBUS);
   // 扑捉浮点数例外
   Register(SIGFPE);
   // 扑捉非法内存例外
   Register(SIGSEGV);
#endif // _MO_TRAP
   return ETrue;
}

MO_NAMESPACE_END
