#ifdef _MO_LINUX
#include <libxml/parser.h>
#endif // _MO_LINUX
#include "MoCommon.h"
#include "MoCmSystem.h"
#include "MoCmSingleton.h"
#include "MoCmShared.h"
#include "MoCmSharedList.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化运行库。</T>
//============================================================
void MoInitialize(){
   // 初始化收集器
   RAllocator::Create();
   RMemory::LinkStorage(RAllocator::Storage());
   // 初始化唯一对象管理器
   RSingletonManager::SafeCreate();
   // 初始化程序
   RApplication::Create();
   RApplication::InstallCatcher();
   // 初始化日志管理器
   RLoggerManager::Create();
   RApplication::Instance().InterruptListeners()->Push(RLoggerManager::Instance().InterruptListener());
   RApplication::Instance().ReloadListeners()->Push(RLoggerManager::Instance().ReloadListener());
   RApplication::Instance().UnloadListeners()->Push(RLoggerManager::Instance().UnloadListener());
   RApplication::Instance().ShutdownListeners()->Push(RLoggerManager::Instance().ShutdownListener());
   // 初始化线程管理器
   RThreadManager::Create();
   // 初始化任务管理器
   RTaskManager::Create();
   // 初始化共享对象管理器
   RSharedManager::Create();
   // 初始捕捉器管理器
   RCatcherManager::Create();
   // 初始枚举管理器
   REnumeratorManager::Create();
   // 打开日志
   RLogger::Link(RLoggerManager::InstancePtr());
   MO_STATIC_INFO(TC("Common framework initialize."));
   // 输出环境信息
   MO_STATIC_INFO(TC(" - Environment. ( bool=%d,  char8=%d,   char16=%d,   char32=%d)"), sizeof(TBool),  sizeof(TChar8),  sizeof(TChar16),   sizeof(TChar32));
   MO_STATIC_INFO(TC(" - Environment. ( int8=%d,  int16=%d,    int32=%d,    int64=%d)"), sizeof(TUint8), sizeof(TUint16), sizeof(TUint32),   sizeof(TUint64));
   MO_STATIC_INFO(TC(" - Environment. (uint8=%d, uint16=%d,   uint32=%d,   uint64=%d)"), sizeof(TInt8),  sizeof(TInt16),  sizeof(TInt32),    sizeof(TInt64));
   MO_STATIC_INFO(TC(" - Environment. (float=%d, double=%d, datetime=%d, timetick=%d)"), sizeof(TFloat), sizeof(TDouble), sizeof(TDateTime), sizeof(TTimeTick));
   // 启动网络
   RNetSocket::Startup();
   //............................................................
#ifdef _MO_WINDOWS
   CoInitialize(NULL);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   xmlInitParser();
#endif // _MO_LINUX
   //............................................................
   // 启动监视器线程
   RMonitorManager::Create();
   RMonitorManager::Instance().Startup();
}

//============================================================
// <T>释放运行库。</T>
//============================================================
void MoRelease(){
   // 关闭监视器线程
   RMonitorManager::Instance().Shutdown();
   RMonitorManager::Destroy();
   //............................................................
   // 关闭网络
   RNetSocket::Cleanup();
   // 关闭日志
   MO_STATIC_INFO(TC("Common framework release."));
   RLogger::Link(NULL);
   // 释放捕捉器管理器
   RCatcherManager::Destroy();
   // 释放枚举管理器
   REnumeratorManager::Destroy();
   // 释放共享对象管理器
   RSharedManager::Destroy();
   // 释放任务管理器
   RTaskManager::Destroy();
   // 释放线程管理器
   RThreadManager::Destroy();
   // 释放程序
   RApplication::Destroy();
   // 释放日志管理器
   RLoggerManager::Destroy();
   // 释放唯一对象管理器
   RSingletonManager::Destroy();
   // 释放收集器
   RMemory::LinkStorage(NULL);
   RAllocator::Destroy();
   //............................................................
#ifdef _MO_WINDOWS
   CoUninitialize();
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   xmlCleanupParser();
#endif // _MO_LINUX
}

MO_NAMESPACE_END
