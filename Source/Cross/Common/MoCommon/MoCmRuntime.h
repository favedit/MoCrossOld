//============================================================
// <T>基础类型定义</T>
//
// 类的首字母：
//   E -> 枚举类：一组数据定义。
//   S -> 结构类：结构定义。必须是struct定义的类。
//   R -> 引用类：含有静态函数，完成某类操作。不能新建实例，只能被静态调用函数。
//   I -> 接口类：不能新建，只能被对象当作接口使用，内部只能存在纯虚函数，不能含有任何处理。
//   M -> 管理类：不能新建，只能被对象当作接口使用，内部可以存在若干处理。
//   T -> 工具类：完全看作是类型操作。不许新建对象，可以任意复制，不需要释放操作。
//   F -> 功能类：必须使用(MO_CREATE)宏创建对象得到，不能直接定义使用，必须要释放。
//   H -> 句柄类：必须通过新建对象得到，不能直接定义使用，不能释放。
// 类的末字母：
//   C -> 只读类：一般为底层类用，不能新建，只能引用。
//
// 函数首字母：
//   C -> 回调函数：函数的定义。
// 函数末字母（可以自由组合）：
//   M -> 内存，函数内有内存收集和释放的操作。离开函数时，必须检查内存平衡性。
//   L -> 例外，函数内部可能产生例外，调用时必须进行例外处理。
//   C -> 只读，没有改变任何内部结构，没有任何内存操作，并不可能产生例外
//============================================================

#ifndef __MO_CM_RUNTIME_H__
#define __MO_CM_RUNTIME_H__

//============================================================
// 调试标志
#ifdef _MO_DEBUG
#ifndef _MO_DEBUG
#define _MO_DEBUG
#endif // _MO_DEBUG
#define _MO_MEMORY
#define _MO_LOGGER
#endif // _DEBUG
//............................................................
// Linux标志
#ifdef _MO_LINUX
#ifndef _MO_LINUX
#define _MO_LINUX
#endif // _MO_LINUX
#endif // _LINUX

//============================================================
// <T>包含平台： Windows X86</T>
//============================================================
#ifdef _MO_WINDOWS
#ifdef _MO_X86
#ifndef __MO_CM_WINDOWS_86_H__
#include "MoCmWindows86.h"
#endif // __MO_CM_WINDOWS_86_H__
#endif // _MO_X86
#endif // _MO_WINDOWS

//============================================================
// <T>包含平台： Windows X64</T>
//============================================================
#ifdef _MO_WINDOWS
#ifdef _MO_X64
#ifndef __MO_CM_WINDOWS_64_H__
#include "MoCmWindows64.h"
#endif // __MO_CM_WINDOWS_64_H__
#endif // _MO_X64
#endif // _MO_WINDOWS

//============================================================
// <T>包含平台： MinGW</T>
//============================================================
#ifdef _MO_MINGW
#ifdef _MO_X86
#ifndef __MO_CM_MINGW_H__
#include "MoCmMinGW.h"
#endif // __MO_CM_MINGW_H__
#endif // _MO_X86
#endif // _MO_MINGW

//============================================================
// <T>包含平台： Linux X86</T>
//============================================================
#ifdef _MO_LINUX
#ifdef _MO_X86
#ifndef __MO_CM_LINUX_32_H__
#include "MoCmLinux86.h"
#endif // __MO_CM_LINUX_32_H__
#endif // _MO_X86
#endif // _MO_LINUX

//============================================================
// <T>包含平台： Linux X64</T>
//============================================================
#ifdef _MO_LINUX
#ifdef _MO_X64
#ifndef __MO_CM_LINUX_64_H__
#include "MoCmLinux64.h"
#endif // __MO_CM_LINUX_64_H__
#endif // _MO_X64
#endif // _MO_LINUX

//============================================================
// <T>包含平台： Android (32bit)</T>
//============================================================
#ifdef _MO_ANDROID
#ifndef __MO_CM_ANDROID_H__
#include "MoCmAndroid.h"
#endif // __MO_CM_ANDROID_H__
#endif // _MO_ANDROID

//============================================================
// <T>包含平台： FlasCC (32bit)</T>
//============================================================
#ifdef _MO_FLASCC
#ifndef __MO_CM_FLASCC_H__
#include "MoCmFlasCC.h"
#endif // __MO_CM_FLASCC_H__
#endif // _MO_FLASCC

/// @define 导出定义
#ifdef _MO_CM_EXPORTS
#define MO_CM_DECLARE MO_EXPORT
#else
#define MO_CM_DECLARE MO_IMPORT
#endif

//============================================================
// <T>性能追踪。</T>
//============================================================
#define MO_PERFORMANCE_TRACK

//============================================================
// <T>编码定义。</T>
//============================================================
#define TS(S) TStringPtr(S)
//------------------------------------------------------------
#ifdef _UNICODE
#define TC(S) L##S
#ifndef _MO_UNICODE
#define _MO_UNICODE
#endif // _MO_UNICODE
#else
#define TC(S) S
#endif

//============================================================
// <T>内存管理定义。</T>
//============================================================
/// @define 特殊定义
#ifdef _MO_ANDROID
#define MO_TYPENAME(T)         #T
#else
#define MO_TYPENAME(T)         typeid(T).name()
#endif // _MO_ANDROID
//------------------------------------------------------------
/// @define 清除一个对象指针
#define MO_CLEAR(P)            P=NULL
//------------------------------------------------------------
/// @define 创建一个内存指针
#define MO_MEM_CREATE(T, ...)  new T(__VA_ARGS__)
/// @define 收集一个内存指针
#define MO_MEM_ALLOC(S)        malloc(S)
/// @define 释放一个内存指针
#define MO_MEM_FREE(P)         if(P){free(P);P=NULL;}
/// @define 删除一个内存指针
#define MO_MEM_DELETE(P)       if(P){delete P;P=NULL;}
//------------------------------------------------------------
/// @define 创建一个内存数组
#define MO_MEM_CREATE_ARRAY(T, ...)  new T(__VA_ARGS__)[]
/// @define 删除一个内存数组
#define MO_MEM_DELETE_ARRAY(P) if(P){delete[] P;P=NULL;}
//------------------------------------------------------------
/// @define 创建一个内存指针
#define MO_PTR_CREATE(T, ...)  new T(__VA_ARGS__)
/// @define 删除一个内存指针
#define MO_PTR_DELETE(P)       if(P){delete P;P=NULL;}
//------------------------------------------------------------
/// @define 释放一个对象接口
#define MO_PTR_RELEASE(P)      if(P){P->Release();P=NULL;}

//------------------------------------------------------------
/// @define 共享内存管理
#define MO_SHARE_CREATE(S, T, ...) new(S->MemoryC())T(__VA_ARGS__)
#define MO_SHARE_ALLOC(T, ...)     (T*)RShareMemory::Alloc(#T, sizeof(T), __FILE__, __LINE__)
#define MO_SHARE_FREE(P)           RShareMemory::Free(P)
#define MO_SHARE_DELETE(P)         P->Dispose();RShareMemory::Free(P)

//------------------------------------------------------------
/// @define 进程内存管理
#define MO_PROCESS_CREATE(T, ...) new (RProcessMemory::Alloc(#T, sizeof(T), __FILE__, __LINE__))T(__VA_ARGS__)
#define MO_PROCESS_ALLOC(T, ...)  (T*)RProcessMemory::Alloc(#T, sizeof(T), __FILE__, __LINE__)
#define MO_PROCESS_FREE(P)        RProcessMemory::Free(P)
#define MO_PROCESS_DELETE(P)      P->Dispose();RProcessMemory::Free(P)

//------------------------------------------------------------
/// @define 线程内存管理
#define MO_THREAD_CREATE(T, ...)  new (RThreadMemory::Alloc(#T, sizeof(T), __FILE__, __LINE__))T(__VA_ARGS__)
#define MO_THREAD_ALLOC(T, ...)   (T*)RThreadMemory::Alloc(#T, sizeof(T), __FILE__, __LINE__)
#define MO_THREAD_FREE(P)         RThreadMemory::Free(P)
#define MO_THREAD_DELETE(P)       P->Dispose();RThreadMemory::Free(P)

//------------------------------------------------------------
/// @define 构造一个对象的实例
#ifdef _MO_MEMORY
#ifdef _MO_ANDROID
#define MO_CREATE(T, ...)           new (#T, __FILE__, __LINE__)T(__VA_ARGS__)
#else
#define MO_CREATE(T, ...)           new (typeid(T).name(), __FILE__, __LINE__)T(__VA_ARGS__)
#endif // _MO_ANDROID
#define MO_TYPE_CREATE(T, ...)      new T(__VA_ARGS__)
#else
#define MO_CREATE(T, ...)           new T(__VA_ARGS__)
#define MO_TYPE_CREATE(T, ...)      new T(__VA_ARGS__)
#endif
/// @define 收集一个对象的实例
#ifdef _MO_MEMORY
#define MO_ALLOC(S)                 RMemory::Alloc(NULL, NULL, S, __FILE__, __LINE__)
#define MO_TYPE_ALLOC(T)            (T*)RMemory::Alloc(MO_TYPENAME(this), MO_TYPENAME(T), sizeof(T), __FILE__, __LINE__)
#define MO_TYPES_ALLOC(T, S)        (T*)RMemory::Alloc(MO_TYPENAME(this), MO_TYPENAME(T), sizeof(T) * S, __FILE__, __LINE__)
#define MO_STATIC_ALLOC(S)          RMemory::Alloc(NULL, NULL, S, __FILE__, __LINE__)
#define MO_STATIC_TYPE_ALLOC(T)     (T*)RMemory::Alloc(NULL, MO_TYPENAME(T), sizeof(T), __FILE__, __LINE__)
#define MO_STATIC_TYPES_ALLOC(T, S) (T*)RMemory::Alloc(NULL, MO_TYPENAME(T), sizeof(T) * S, __FILE__, __LINE__)
#else
#define MO_ALLOC(S)                 malloc(S)
#define MO_TYPE_ALLOC(T)            (T*)malloc(sizeof(T))
#define MO_TYPES_ALLOC(T, S)        (T*)malloc(sizeof(T) * S)
#define MO_STATIC_ALLOC(S)          malloc(S)
#define MO_STATIC_TYPE_ALLOC(T)     (T*)malloc(sizeof(T))
#define MO_STATIC_TYPES_ALLOC(T, S) (T*)malloc(sizeof(T) * S)
#endif
/// @define 释放一个对象的实例
#ifdef _MO_MEMORY
#define MO_FREE(P)                  if(P){RMemory::Free(P);P=NULL;}
#else
#define MO_FREE(P)                  if(P){free(P);P=NULL;}
#endif
/// @define 析构一个对象的实例
#define MO_DELETE(P)                 if(P){delete P;P=NULL;}
#define MO_TYPE_DELETE(P)            if(P){delete P;P=NULL;}
/// @define 释放一个接口的实例
#define MO_RELEASE(P)                if(P){P->Release();P=NULL;}
#define MO_DESPOSE(P)                if(P){P->Despose();P=NULL;}
#define MO_DESTORY(P)                if(P){P->Destroy();P=NULL;}

//------------------------------------------------------------
/// @define 为调用函数设置一个内存陷阱，当离开时，如果发生内存泄露，马上产生例外
#ifdef _MO_MEMORY
#define MO_TRAP(M) {TTrapper t; M;}
#else
#define MO_TRAP(M) M
#endif // _MO_MEMORY

//============================================================
// <T>错误定义</T>
//============================================================
/// @define 转向错误处理块
#define MO_ERROR_GOTO()     goto ERROR_PROCESS
/// @define 开始一个错误处理块，一个函数只能有一个错误处理块
#define MO_ERROR_BEGIN      ERROR_PROCESS:{
/// @define 结束错误处理块
#define MO_ERROR_END        }

//============================================================
// <T>处理定义</T>
//============================================================
/// @define 是否成功
#define MO_RS_SUCCEEDED(r)     (((HRESULT)(r)) >= 0)
/// @define 是否失败
#define MO_RS_FAILED(r)        (((HRESULT)(r)) < 0)
//------------------------------------------------------------
/// @define 静态转换：该运算符把exdivssion转换为type-id类型，但没有运行时类型检查来保证转换的安全性。
#define MO_CAST_STATIC(value, type)      static_cast<type>(value)
/// @define 静态转换：该运算符把exdivssion转换成type-id类型的对象
#define MO_CAST_DYNAMIC(value, type)     dynamic_cast<type>(value)
/// @define 静态转换：type-id必须是一个指针、引用、算术类型、函数指针或者成员指针
#define MO_CAST_REINTERPRET(value, type) reinterpret_cast<type>(value)
/// @define 静态转换：该运算符用来修改类型的const或volatile属性
#define MO_CAST_CONST(value, type)       const_cast<type>(value)
//------------------------------------------------------------
/// @define 定义一个函数为内联函数
#define MO_INLINE inline
/// @define 定义一个函数为虚函数
#define MO_ABSTRACT virtual
/// @define 定义一个函数为纯虚函数
#define MO_VIRTUAL virtual
/// @define 定义一个函数为可回调的处理函数
#define MO_CALLBACK extern "C"
/// @define 定义一个函数为可覆盖的事件函数
#define MO_EVENT virtual
/// @define 定义一个函数为覆盖函数
#define MO_OVERRIDE
/// @define 定义一个函数为禁止构造函数
#define MO_EXPLCIT explicit
//------------------------------------------------------------
#ifdef _MO_WINDOWS
#define MO_INHERITS(M) using M
#else
#define MO_INHERITS(M)
#endif // _MO_WINDOWS
/// @define 显式声明从父类继承一个构造函数
#define MO_INHERIT_CONSTRUCT(M, P) M : P {};
/// @define 显式声明从父类继承一个函数
#define MO_INHERIT_METHOD(M, P) M { P; };
/// @define 显式声明从父类继承一个函数
#define MO_INHERIT_METHOD_RETURN(M, P, R) M { P; return R; };
/// @define 定义一个栈类
//------------------------------------------------------------
#ifdef _MO_DEBUG
#define MO_CLASS_STATCK(C) \
protected: \
   PPtr operator new(TUint size){throw EError_Unsupport;} \
   TAny operator delete(PPtr pMemory){throw EError_Unsupport;}
#else
#define MO_CLASS_STATCK(C)
#endif // _MO_DEBUG
/// @define 定义一个堆类
#ifdef _MO_DEBUG
#define MO_CLASS_HEAP(C) \
protected: \
   C(){throw EError_Unsupport;} \
   ~C(){throw EError_Unsupport;} \
   PPtr operator new(TUint size){throw EError_Unsupport;} \
   TAny operator delete(PPtr pMemory){throw EError_Unsupport;}
#else
#define MO_CLASS_HEAP(C)
#endif // _MO_DEBUG
//------------------------------------------------------------
/// @define 定义未实现的函数
#ifdef _MO_DEBUG
#define MO_METHOD_UNSUPPORT(M)     M{throw EError_Unsupport;}
#define MO_METHOD_UNSUPPORT_NEW    PPtr operator new(TUint size){throw EError_Unsupport;}
#define MO_METHOD_UNSUPPORT_DELETE TAny operator delete(PPtr pMemory){throw EError_Unsupport;}
#else
#define MO_METHOD_UNSUPPORT(M)
#define MO_METHOD_UNSUPPORT_NEW
#define MO_METHOD_UNSUPPORT_DELETE
#endif // _MO_DEBUG

//============================================================
// <T>系统定义</T>
//============================================================
#define MO_EXIT(code)   exit(code)
#define MO_PEXIT()      exit(errno)
//------------------------------------------------------------
/// @define 产生例外
#ifdef _MO_DEBUG
#define MO_THROW(s) MO_RAISE_DEBUG();MO_RAISE_DOWN();MO_RAISE_EXIT(0);
#else
#define MO_THROW(s) MO_RAISE_DOWN();MO_RAISE_EXIT(0);
#endif // _MO_DEBUG
//------------------------------------------------------------
/// @define 开始同步锁
#define MO_SYNCHRONIZED_BEGIN(L) L->Enter();
/// @define 结束同步锁
#define MO_SYNCHRONIZED_END(L)   L->Leave();

//============================================================
// <T>系统日志定义</T>
//============================================================
#define MO_LOGGER_TYPE_SYSTEM 0
#define MO_LOGGER_TYPE_NET    1
#define MO_LOGGER_TYPE_STACK  3
#define MO_LOGGER_TYPE_ERROR  4

/// @define 控制日志的输出块的开始
#define MO_LOGGER_BEGIN       if(true){
/// @define 控制日志的输出块的结束始
#define MO_LOGGER_END         }
//------------------------------------------------------------
/// @define 输出信息
#define MO_PRINT(S) printf("%s\n--> %s\n--> %s(%d)\n", S, __PRETTY_FUNCTION__, __FILE__, __LINE__)
//------------------------------------------------------------
/// @define 输出一个调试信息
#ifdef _MO_LOGGER
#define MO_STATIC_DEBUG_BEGIN(M) RLogger::Error(MO_LOGGER_TYPE_SYSTEM, NULL, __PRETTY_FUNCTION__, "----- %s - begin ------------------------------", #M)
#define MO_STATIC_DEBUG(M, ...)  RLogger::Debug(MO_LOGGER_TYPE_SYSTEM, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#define MO_STATIC_DEBUG_END(M)   RLogger::Error(MO_LOGGER_TYPE_SYSTEM, NULL, __PRETTY_FUNCTION__, "----- %s - end --------------------------------", #M)
#define MO_DEBUG_BEGIN(M)        RLogger::Error(MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, "----- %s - begin ------------------------------", #M)
#define MO_DEBUG(M, ...)         RLogger::Debug(MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#define MO_DEBUG_END(M)          RLogger::Error(MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, "----- %s - end --------------------------------", #M)
#define MO_DEBUG_DEEP(T, M, ...) RLogger::Debug(MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, T, M, ##__VA_ARGS__)
#define MO_TRACK_DEFINE(M)       M
#define MO_TRACK(T, M, ...)      RLogger::Info(MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, T, M, ##__VA_ARGS__)
#define MO_STATIC_TRACK(M, ...)  RLogger::Info (MO_LOGGER_TYPE_SYSTEM, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#else
#define MO_STATIC_DEBUG_BEGIN(M)
#define MO_STATIC_DEBUG(M, ...)
#define MO_STATIC_DEBUG_END(M)
#define MO_DEBUG_BEGIN(M)
#define MO_DEBUG(M, ...)
#define MO_DEBUG_END(M)
#define MO_DEBUG_DEEP(T, M, ...)
#define MO_TRACK_DEFINE(M)
#define MO_TRACK(T, M, ...)
#define MO_STATIC_TRACK(M, ...)
#endif // _MO_LOGGER
//------------------------------------------------------------
#define MO_STATIC_INFO(M, ...)              RLogger::Info (MO_LOGGER_TYPE_SYSTEM, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#define MO_STATIC_INFO_CHECK(R, P, M, ...)  if(!(R)){RLogger::Info (MO_LOGGER_TYPE_SYSTEM, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__); P;}
#define MO_STATIC_WARN(M, ...)              RLogger::Warn (MO_LOGGER_TYPE_SYSTEM, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#define MO_STATIC_WARN_CHECK(R, P, M, ...)  if(!(R)){RLogger::Warn (MO_LOGGER_TYPE_SYSTEM, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__); P;}
#define MO_STATIC_ERROR(M, ...)             RLogger::Error(MO_LOGGER_TYPE_ERROR, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#define MO_STATIC_ERROR_CHECK(R, P, M, ...) if(!(R)){RLogger::Error(MO_LOGGER_TYPE_ERROR, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__); P;}
#ifdef _MO_DEBUG
#define MO_STATIC_FATAL(M, ...)             RLogger::Fatal(MO_LOGGER_TYPE_STACK, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__);MO_THROW("Fatal")
#define MO_STATIC_FATAL_CHECK(R, P, M, ...) if(!(R)){RLogger::Fatal(MO_LOGGER_TYPE_STACK, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__);MO_THROW("Fatal"); P;}
#else
#define MO_STATIC_FATAL(M, ...)             RLogger::Fatal(MO_LOGGER_TYPE_STACK, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#define MO_STATIC_FATAL_CHECK(R, P, M, ...) if(!(R)){RLogger::Fatal(MO_LOGGER_TYPE_STACK, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__); P;}
#endif // _MO_DEBUG
#define MO_STATIC_DUMP(M, ...)              RLogger::Crash(MO_LOGGER_TYPE_STACK, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
//------------------------------------------------------------
#define MO_INFO(M, ...)              RLogger::Info (MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#define MO_INFO_CHECK(R, P, M, ...)  if(!(R)){RLogger::Info (MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__); P;}
#define MO_WARN(M, ...)              RLogger::Warn (MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#define MO_WARN_CHECK(R, P, M, ...)  if(!(R)){RLogger::Warn (MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__); P;}
#define MO_ERROR(M, ...)             RLogger::Error(MO_LOGGER_TYPE_ERROR, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#define MO_ERROR_CHECK(R, P, M, ...) if(!(R)){RLogger::Error(MO_LOGGER_TYPE_ERROR, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__); P;}
#ifdef _MO_DEBUG
#define MO_FATAL(M, ...)             RLogger::Fatal(MO_LOGGER_TYPE_STACK, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__);MO_THROW("Fatal");
#define MO_FATAL_CHECK(R, P, M, ...) if(!(R)){RLogger::Fatal(MO_LOGGER_TYPE_STACK, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__);MO_THROW("Fatal"); P;}
#else
#define MO_FATAL(M, ...)             RLogger::Fatal(MO_LOGGER_TYPE_ERROR, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#define MO_FATAL_CHECK(R, P, M, ...) if(!(R)){RLogger::Fatal(MO_LOGGER_TYPE_ERROR, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__); P;}
#endif // _MO_DEBUG
#define MO_STACK(M, ...)             RLogger::Crash(MO_LOGGER_TYPE_STACK, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#define MO_DUMP_STACK(M, ...)        RLogger::Crash(MO_LOGGER_TYPE_STACK, NULL, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
//------------------------------------------------------------
#ifdef _MO_DEBUG
#define MO_DEBUG_INFO(M, ...)  RLogger::Info (MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#define MO_DEBUG_WARN(M, ...)  RLogger::Warn (MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#define MO_DEBUG_ERROR(M, ...) RLogger::Error(MO_LOGGER_TYPE_ERROR, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__)
#define MO_DEBUG_FATAL(M, ...) RLogger::Fatal(MO_LOGGER_TYPE_STACK, this, __PRETTY_FUNCTION__, M, ##__VA_ARGS__);MO_THROW("Fatal")
#else
#define MO_DEBUG_INFO(M, ...)
#define MO_DEBUG_WARN(M, ...)
#define MO_DEBUG_ERROR(M, ...)
#define MO_DEBUG_FATAL(M, ...)
#endif // _MO_DEBUG
//------------------------------------------------------------
#define MO_FATAL_UNSUPPORT() MO_STATIC_FATAL(TC("Call unsupport method."));
#define MO_FATAL_ABSTRACT()  MO_STATIC_FATAL(TC("Call abstract method."));
//------------------------------------------------------------
#ifdef _MO_WINDOWS
#define MO_STATIC_PWARN(M)  {TChar8 __mo_perror[1024];strerror_s(__mo_perror,8192,errno);RLogger::Warn( MO_LOGGER_TYPE_SYSTEM, NULL, __PRETTY_FUNCTION__, TC("Call method '%s' error.(%d=%s) => %s(%d)"), TC(#M), errno, __mo_perror, __FILE__, __LINE__);}
#define MO_STATIC_PERROR(M) {TChar8 __mo_perror[1024];strerror_s(__mo_perror,8192,errno);RLogger::Error(MO_LOGGER_TYPE_SYSTEM, NULL, __PRETTY_FUNCTION__, TC("Call method '%s' error.(%d=%s) => %s(%d)"), TC(#M), errno, __mo_perror, __FILE__, __LINE__);}
#define MO_STATIC_PFATAL(M) {TChar8 __mo_perror[1024];strerror_s(__mo_perror,8192,errno);RLogger::Fatal(MO_LOGGER_TYPE_SYSTEM, NULL, __PRETTY_FUNCTION__, TC("Call method '%s' error.(%d=%s) => %s(%d)"), TC(#M), errno, __mo_perror, __FILE__, __LINE__);MO_THROW("Fatal");}
#define MO_PWARN(M)         {TChar8 __mo_perror[1024];strerror_s(__mo_perror,8192,errno);RLogger::Warn(MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, TC("Call method '%s' error.(%d=%s) => %s(%d)"), TC(#M), errno, __mo_perror, __FILE__, __LINE__);}
#define MO_PERROR(M)        {TChar8 __mo_perror[1024];strerror_s(__mo_perror,8192,errno);RLogger::Error(MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, TC("Call method '%s' error.(%d=%s) => %s(%d)"), TC(#M), errno, __mo_perror, __FILE__, __LINE__);}
#define MO_PFATAL(M)        {TChar8 __mo_perror[1024];strerror_s(__mo_perror,8192,errno);RLogger::Fatal(MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, TC("Call method '%s' error.(%d=%s) => %s(%d)"), TC(#M), errno, __mo_perror, __FILE__, __LINE__);MO_THROW("Fatal");}
#else
#define MO_STATIC_PWARN(M)  RLogger::Warn( MO_LOGGER_TYPE_SYSTEM, NULL, __PRETTY_FUNCTION__, "Call method '"#M"' error.(%d=%s) => %s(%d)", errno, strerror(errno), __FILE__, __LINE__)
#define MO_STATIC_PERROR(M) RLogger::Error(MO_LOGGER_TYPE_SYSTEM, NULL, __PRETTY_FUNCTION__, "Call method '"#M"' error.(%d=%s) => %s(%d)", errno, strerror(errno), __FILE__, __LINE__)
#define MO_STATIC_PFATAL(M) RLogger::Fatal(MO_LOGGER_TYPE_SYSTEM, NULL, __PRETTY_FUNCTION__, "Call method '"#M"' fatal.(%d=%s) => %s(%d)", errno, strerror(errno), __FILE__, __LINE__);MO_THROW("Fatal")
#define MO_PWARN(M)         RLogger::Warn(MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, "Call method '"#M"' error.(%d=%s) => %s(%d)", errno, strerror(errno), __FILE__, __LINE__)
#define MO_PERROR(M)        RLogger::Error(MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, "Call method '"#M"' error.(%d=%s) => %s(%d)", errno, strerror(errno), __FILE__, __LINE__)
#define MO_PFATAL(M)        RLogger::Fatal(MO_LOGGER_TYPE_SYSTEM, this, __PRETTY_FUNCTION__, "Call method '"#M"' fatal.(%d=%s) => %s(%d)", errno, strerror(errno), __FILE__, __LINE__);MO_THROW("Fatal")
#endif // _MO_WINDOWS

//============================================================
// <T>断言定义</T>
//============================================================
/// @define 检查结果是否为真，如果为假，则产生例外
#define MO_ASSERT(R)                  if(!(R)){MO_STATIC_FATAL(TC("Assert result is invalid."));MO_RAISE_DOWN();}
#define MO_CHECK(R, P)                if(!(R)){MO_STATIC_FATAL(TC("Check result is invalid. (%s)"), TC(#R)); P;}
/// @define 检查内容是否在指定范围内，如果为假，则产生例外
#define MO_ASSERT_RANGE(R, S, E)      if((R < S) || (R >= E)){MO_STATIC_FATAL(TC("Range is invalid. (begin=%d, end=%d, current=%d)"), S, E, R);MO_RAISE_DOWN();}
#define MO_CHECK_RANGE(R, S, E, P)    if((R < S) || (R >= E)){MO_STATIC_FATAL(TC("Range is invalid. (begin=%d, end=%d, current=%d)"), S, E, R); P;}
/// @define 检查内容是否在指定范围内(包含边界)，如果为假，则产生例外
#define MO_ASSERT_BETWEEN(R, S, E)    if((R < S) || (R > E)){MO_STATIC_FATAL(TC("Between is invalid. (begin=%d, end=%d, current=%d)"), S, E, R);MO_RAISE_DOWN();}
#define MO_CHECK_BETWEEN(R, S, E, P)  if((R < S) || (R > E)){MO_STATIC_FATAL(TC("Between is invalid. (begin=%d, end=%d, current=%d)"), S, E, R); P;}
/// @define 检查错误编号是否成功，如果为假，则产生例外
#define MO_ASSERT_ERRNO(R)            if(0 != (R)){MO_STATIC_FATAL(TC("Error code is invalid. (code=%d)"), R);MO_RAISE_DOWN();}
#define MO_CHECK_ERRNO(R, P)          if(0 != (R)){MO_STATIC_FATAL(TC("Error code is invalid. (code=%d)"), R); P;}
/// @define 检查一个指针是否为合法指针，如果为空，则产生例外
#define MO_ASSERT_POINTER(R)          if(NULL == (R)){MO_STATIC_FATAL(TC("Pointer is invalid."));MO_RAISE_DOWN();}
#define MO_CHECK_POINTER(R, P)        if((R) == NULL){MO_STATIC_FATAL(TC("Check pointer is null. (pointer=%s)"), TC(#R)); P;}

//============================================================
// <T>代码定义</T>
//============================================================
/// @define 自动获得函数
#define MO_SOURCE_GETTER(T, N, V) \
   MO_INLINE T N() { \
      return V; \
   } \
/// @define 自动设置函数
#define MO_SOURCE_SETTER(T, N, V) \
   MO_INLINE void Set##N(T value) { \
      V = value; \
   }
/// @define 自动获得设置函数
#define MO_SOURCE_GETSET(T, N, V) \
   MO_INLINE T N() { \
      return V; \
   } \
   MO_INLINE void Set##N(T value) { \
      V = value; \
   }

//============================================================
// <T>基本常量定义</T>
//============================================================
/// @define 内存块默认收集大小
#define MO_MEMORY_CAPACITY        64
#define MO_MEMORY_EXTEND_CAPACITY 4096
/// @define 对象集默认收集大小
#define MO_OBJECT_CAPACITY        16
#define MO_OBJECT_EXTEND_CAPACITY 1024
/// @define 内存块默认收集大小
#define MO_MEMORY_BLOCK           4096

//============================================================
// <T>跟踪常量定义</T>
//============================================================
#ifdef _MO_DEBUG
#define MO_TRACK_ITEMS_MAXCOUNT 16
#else
#define MO_TRACK_ITEMS_MAXCOUNT 16
#endif // _MO_DEBUG

//============================================================
// <T>常数常量定义</T>
//============================================================
#define MO_INT_MAXLENGTH      32
#define MO_FLOAT_MAXLENGTH    128
#define MO_DATE_MAXLENGTH     32
#define MO_TIME_MAXLENGTH     32
#define MO_DATETIME_MAXLENGTH 64
#define MO_TIMESPAN_MAXLENGTH 64
#define MO_ERROR_MAXLENGTH    1024*4
#define MO_DUMP_INDENT        TC("   ")
#define MO_DUMP_MAXLENGTH     1024*64

//------------------------------------------------------------
// 固定长度字符
#define MO_FS_NUMBER_LENGTH     40
#define MO_FS_INTEGER_LENGTH    40
#define MO_FS_INTEGER64_LENGTH  60
#define MO_FS_FLOAT_LENGTH      40
#define MO_FS_DOUBLE_LENGTH     80
#define MO_FS_TIMETICK_LENGTH   40
#define MO_FS_DATETIME_LENGTH   40
#define MO_FS_CODE_LENGTH       128
#define MO_FS_NAME_LENGTH       128
#define MO_FS_LABEL_LENGTH      256
#define MO_FS_FILENAME_LENGTH   256
#define MO_FS_TEXT_LENGTH       4096
#define MO_FS_PATH_LENGTH       2048
#define MO_FS_NOTE_LENGTH       1024*4
#define MO_FS_COMMAND_LENGTH    1024*16
#define MO_FS_SQL_LENGTH        1024*256

//------------------------------------------------------------
// 字符常量
#define MO_CP_ACP      0
#define MO_CPS_ASCII   "ASCII"
#define MO_CPS_UTF8    "UTF-8"
#define MO_CPS_GB2312  "GB2312"
#define MO_CPS_UCS4    "UCS-4"
#define MO_CPS_UNICODE "UNICODE"

//------------------------------------------------------------
// 时间常量
#define MO_DATA_DAY_US     86400000000ull
#define MO_DATA_DAY_MS     86400000ull
#define MO_DATA_DAY_SECOND 86400
#define MO_DATA_DAY_HOUR   24

//------------------------------------------------------------
// 数学常量
#define MO_PI       3.14159265358979323846
#define MO_PI_FLOAT 3.141593f

MO_NAMESPACE_BEGIN

//============================================================
// <T>对象类型定义。</T>
//============================================================
enum EObject{
   /// @enum 枚举类型
   EEnum,
   /// @enum 工具类（栈对象）
   ETools,
   /// @enum 对象类型（堆对象）
   EClass,
   /// @enum 自由句柄类
   EHandle,
   /// @enum 静态引用类
   EReference,
   /// @enum 控制台类
   EConsole,
   /// @enum 管理器类
   EManager
};

//============================================================
// <T>数据类型定义。</T>
//============================================================
enum EType{
   /// @enum 任意
   EType_Any = 0,
   /// @enum 布尔值（真假）
   EType_Bool = 1,
   /// @enum 有符号8位整数
   EType_Int8 = 2,
   /// @enum 有符号16位整数
   EType_Int16 = 3,
   /// @enum 有符号32位整数
   EType_Int32 = 4,
   /// @enum 有符号64位整数
   EType_Int64 = 5,
   /// @enum 变长有符号整数
   EType_Int = 6,
   /// @enum 无符号8位整数
   EType_Uint8 = 7,
   /// @enum 无符号16位整数
   EType_Uint16 = 8,
   /// @enum 无符号32位整数
   EType_Uint32 = 9,
   /// @enum 无符号64位整数
   EType_Uint64 = 10,
   /// @enum 变长无符号整数
   EType_Uint = 11,
   /// @enum 单精度浮点数 (32位)
   EType_Float = 12,
   /// @enum 双精度浮点数 (64位)
   EType_Double = 13,
   /// @enum 时间刻度 (64位 - 微秒)
   EType_TimeTick = 14,
   /// @enum 时间段 (64位 - 微秒)
   EType_TimeSpan = 15,
   /// @enum 日期时间 (64位 - 微妙)
   EType_DateTime = 16,
   /// @enum 8位字符
   EType_Char8 = 17,
   /// @enum 16位字符
   EType_Char16 = 18,
   /// @enum 32位字符
   EType_Char32 = 19,
   /// @enum 变长字符
   EType_Char = 20,
   /// @enum 变长字符指针
   EType_String = 21,
   /// @enum 枚举值（多选一）
   EType_Enum = 22,
   /// @enum 集合值（多选多）
   EType_Set = 23,
   /// @enum 对象指针
   EType_Ptr = 24,
   /// @enum 指定类型
   EType_Type = 25,
   /// @enum 结构
   EType_Struct = 26,
   /// @enum 对象
   EType_Object = 27,
   /// @enum 字节集合
   EType_Bytes = 28,
   /// @enum 对象集合
   EType_Objects = 29,
   /// @enum 类对象
   EType_Class = 30,
   /// @enum 字段对象
   EType_Field = 31,
   /// @enum 属性对象
   EType_Property = 32,
   /// @enum 函数对象
   EType_Method = 33,
};

//============================================================
// <T>布尔定义。</T>
//============================================================
enum EBoolean{
   // @member 真
   ETrue = true,
   // @member 假
   EFalse = false
};

//============================================================
// <T>修改方式。</T>
//============================================================
enum EModify{
   // @member 无操作
   EModify_None,
   // @member 获取
   EModify_Get,
   // @member 设置
   EModify_Set,
   // @member 修改
   EModify_Mod,
};

//============================================================
// <T>成功值定义。</T>
//============================================================
enum EResult{
   // @member 成功
   ESuccess = 0,
   // @member 继续
   EContinue = 1,
   // @member 释放
   ERelease = 1,
   // @member 失败
   EFailure = -1,
   // @member 致命错误
   EFatal = -2,
   // @member 错误
   EError = -3,
   // @member 警告
   EWarn = -4,
   // @member 信息
   EInfo = -5,
   // @member 内存错误
   EMemory = -6,
   // @member 空错误
   ENull = -7,
   // @member 不存在错误
   EExists  = -8,
   // @member 存在错误
   ENotExists  = -9,
   // @member 不存在错误
   EOutRange = -10,
   // @member 重复错误
   EDuplicate  = -11,
   // @member 未支持错误
   EUnsupport = -12,
   // @member 非法错误
   EInvalid = -13,
};

//============================================================
enum EFoundResult{
   // @member 未找到
   ENotFound = -1
};

//============================================================
// <T>错误值定义。</T>
//============================================================
enum ESystemError{
   /// @enum 系统未知例外
   EError_Unknown = -1,
   /// @enum 系统校验例外
   EError_Assert = -2,
   /// @enum 内存不错错误
   EError_NoMemory = -3,
   /// @enum 内存泄露错误
   EError_MemoryLeak = -4,
   /// @enum 系统校验例外
   EError_Unsupport = -5,
   /// @enum 范围溢出错误
   EError_OutRange = -6,
   /// @enum 对象重复
   EError_Duplicate = -7,
   /// @enum 未找到
   EError_NotFound = -8
};

//============================================================
// <T>警告值定义。</T>
//============================================================
enum EWarn{
};

//============================================================
// <T>提示值定义。</T>
//============================================================
enum EInfo{
};

//============================================================
// <T>共同基础类型定义。</T>
//============================================================
/// @type 只读任意数据
typedef const void TAnyC;
/// @type 任意数据
typedef void TAny;
/// @type 只读字节
typedef const unsigned char TByteC;
/// @type 字节
typedef unsigned char TByte;
/// @type 只读ANSI字符
typedef const char TChar8C;
typedef const unsigned char TUchar8C;
/// @type ANSI字符
typedef char TChar8;
typedef unsigned char TUchar8;
/// @type 只读UNICODE字符
typedef const wchar_t TChar16C;
/// @type 只读UNICODE字符
typedef const wchar_t TChar32C;
/// @type UNICODE字符
typedef wchar_t TChar16;
/// @type UNICODE字符
typedef wchar_t TChar32;
/// @type 只读8位整数
typedef const char TInt8C;
/// @type 8位整数
typedef char TInt8;
/// @type 只读16位整数
typedef const short TInt16C;
/// @type 16位整数
typedef short TInt16;
/// @type 只读8位无符号整数
typedef const unsigned char TUint8C;
/// @type 8位无符号整数
typedef unsigned char TUint8;
/// @type 只读16位无符号整数
typedef const unsigned short TUint16C;
/// @type 16位无符号整数
typedef unsigned short TUint16;
/// @type 只读浮点数
typedef const float TFloatC;
/// @type 浮点数 (4字节：符号1bit、指数8bit、尾数23bit)
typedef float TFloat;
/// @type 只读双精度数
typedef const double TDoubleC;
/// @type 双精度数 (8字节：符号1bit、指数11bit、尾数52bit)
typedef double TDouble;
//------------------------------------------------------------
/// @type 只读字符
#if _UNICODE
typedef TChar16C TCharC;
#else
typedef TChar8C TCharC;
typedef TUchar8C TUcharC;
#endif
/// @type 字符
#if _UNICODE
typedef TChar16 TChar;
#else
typedef TChar8 TChar;
#endif
//------------------------------------------------------------
/// @type 只读任意数据指针
typedef TAnyC* PPtrC;
/// @type 任意数据指针
typedef TAny* PPtr;
/// @type 只读字节指针
typedef TByteC* PByteC;
/// @type 字节指针
typedef TByte* PByte;
/// @type 只读ANSI字符指针
typedef TChar8C* PChar8C;
/// @type ANSI字符指针
typedef TChar8* PChar8;
/// @type 只读UNICODE字符指针
typedef TChar16C* PChar16C;
/// @type UNICODE字符指针
typedef TChar16* PChar16;
/// @type 只读字符指针
typedef TCharC* PCharC;
/// @type 字符指针
typedef TChar* PChar;
/// @type 只读整数指针
typedef TIntC* PIntC;
/// @type 整数指针
typedef TInt* PInt;
/// @type 只读8位整数指针
typedef TInt8C* PInt8C;
/// @type 8位整数指针
typedef TInt8* PInt8;
/// @type 只读16位整数指针
typedef TInt16C* PInt16C;
/// @type 16位整数指针
typedef TInt16* PInt16;
/// @type 只读32位整数指针
typedef TInt32C* PInt32C;
/// @type 32位整数指针
typedef TInt32* PInt32;
/// @type 只读64位整数指针
typedef TInt64C* PInt64C;
/// @type 64位整数指针
typedef TInt64* PInt64;
/// @type 只读无符号整数指针
typedef TUintC* PUintC;
/// @type 无符号整数指针
typedef TUint* PUint;
/// @type 只读8位无符号整数指针
typedef TUint8C* PUint8C;
/// @type 8位无符号整数指针
typedef TUint8* PUint8;
/// @type 只读16位无符号整数指针
typedef TUint16C* PUint16C;
/// @type 16位无符号整数指针
typedef TUint16* PUint16;
/// @type 只读32位无符号整数指针
typedef TUint32C* PUint32C;
/// @type 32位无符号整数指针
typedef TUint32* PUint32;
/// @type 只读64位无符号整数指针
typedef TUint64C* PUint64C;
/// @type 64位无符号整数指针
typedef TUint64* PUint64;
/// @type 只读浮点数指针
typedef TFloatC* PFloatC;
/// @type 浮点数指针
typedef TFloat* PFloat;
/// @type 只读双精度数指针
typedef TDoubleC* PDoubleC;
/// @type 双精度数指针
typedef TDouble* PDouble;

//============================================================
// <T>延伸数据类型定义。</T>
//============================================================
/// @type 布尔值
typedef TInt TBool;
/// @type 数据索引
typedef TInt32 TIndex;
/// @type 数据长度
typedef TInt32 TLength;
/// @type 数据位置
typedef TUint32 TOffset;
/// @type 数据大小
typedef size_t TSize;
/// @type 日期 (秒)
typedef TUint32 TDate;
/// @type 时间 (秒)
typedef TUint32 TTime;
/// @type 刻度 (秒)
typedef TUint32 TTick;
/// @type 时段 (秒)
typedef TUint32 TSpan;
/// @type 日期时间 (微妙 10^-6)
typedef TUint64 TDateTime;
/// @type 时间段 (微妙 10^-6)
typedef TInt64 TTimeSpan;
/// @type 时间刻度 (微妙 10^-6)
typedef TInt64 TTimeTick;
/// @type 哈希散列数据(无符号64位整数)
typedef TUint64 THashCode;
/// @type 枚举内容
typedef TInt TEnum;
/// @type 执行结果
typedef TInt32 TResult;
//------------------------------------------------------------
/// @type 128位无符号整数基础
struct SUint128Base{
public:
   TUint64 high;
   TUint64 lower;
public:
   //------------------------------------------------------------
   // <T>判断相等操作。</T>
   MO_INLINE TBool operator==(const SUint128Base& value){
      return (high == value.high) && (lower == value.lower);
   };
   //------------------------------------------------------------
   // <T>判断不相等操作。</T>
   MO_INLINE TBool operator!=(const SUint128Base& value){
      return (high != value.high) || (lower != value.lower);
   };
public:
   //------------------------------------------------------------
   // <T>判断是否为空。</T>
   MO_INLINE TBool IsEmpty(){
      return (high == 0) && (lower == 0);
   };
   //------------------------------------------------------------
   // <T>判断是否为空。</T>
   MO_INLINE TBool IsNotEmpty(){
      return (high != 0) || (lower != 0);
   };
   //------------------------------------------------------------
   // <T>清空处理。</T>
   MO_INLINE void Clear(){
      high = 0;
      lower = 0;
   };
};
//------------------------------------------------------------
/// @type 128位无符号整数
struct SUint128 : public SUint128Base{
public:
   //------------------------------------------------------------
   // <T>构造数据。</T>
   SUint128(){
      high = 0;
      lower = 0;
   }
   //------------------------------------------------------------
   // <T>构造数据。</T>
   SUint128(TInt value){
#ifdef _MO_DEBUG
      if(0 != value){
         MO_THROW("Invalid value");
      }
#endif
      high = value;
      lower = value;
   }
public:
   //------------------------------------------------------------
   friend TBool operator==(TInt value1, const SUint128& value2){
#ifdef _MO_DEBUG
      if(0 != value1){
         MO_THROW("Invalid value");
      }
#endif
      return (value2.high == (TUint64)value1) && (value2.lower == (TUint64)value1);
   }
   //------------------------------------------------------------
   friend TBool operator!=(TInt value1, const SUint128& value2){
#ifdef _MO_DEBUG
      if(0 != value1){
         MO_THROW("Invalid value");
      }
#endif
      return (value2.high != (TUint64)value1) || (value2.lower != (TUint64)value1);
   }
public:
   //------------------------------------------------------------
   // <T>判断相等操作（TODO:临时）。</T>
   MO_INLINE void operator=(TInt value){
#ifdef _MO_DEBUG
      if(0 != value){
         MO_THROW("Invalid value");
      }
#endif
      high = (TUint64)value;
      lower = (TUint64)value;
   };
public:
   //------------------------------------------------------------
   // <T>判断相等操作（TODO:临时）。</T>
   MO_INLINE TBool operator==(TInt64 value){
#ifdef _MO_DEBUG
      if(0 != value){
         MO_THROW("Invalid value");
      }
#endif
      return (high == (TUint64)value) && (lower == (TUint64)value);
   };
   //------------------------------------------------------------
   // <T>判断相等操作（TODO:临时）。</T>
   MO_INLINE TBool operator!=(TInt64 value){
#ifdef _MO_DEBUG
      if(0 != value){
         MO_THROW("Invalid value");
      }
#endif
      return (high != (TUint64)value) || (lower != (TUint64)value);
   };
public:
   //------------------------------------------------------------
   // <T>判断相等操作。</T>
   MO_INLINE TBool operator==(const SUint128& value){
      return (high == value.high) && (lower == value.lower);
   };
   //------------------------------------------------------------
   // <T>判断不相等操作。</T>
   MO_INLINE TBool operator!=(const SUint128& value){
      return (high != value.high) || (lower != value.lower);
   };
};
typedef SUint128 TUint128;

//============================================================
/// @type 句柄
typedef struct SHandle{
   /// @attribute 句柄类型
   TEnum typeCd;
   /// @attribute 句柄索引
   TIndex Index;
   /// @attribute 标志信息
   TUint Flag;
} THandle;
//------------------------------------------------------------
/// @type 资源句柄
typedef struct SResource{
   /// @attribute 资源类型
   TUint Type;
   /// @attribute 资源索引
   TUint Index;
   /// @attribute 标志信息
   TUint Flag;
} TResource;

//============================================================
// <T>简单数据对象。</T>
// <P>可以表示数据是否为空。</P>
//============================================================
template <typename T>
struct SValue{
protected:
   TBool _null;
   T _value;
public:
   //------------------------------------------------------------
   // 构建空数据对象。
   SValue() {
      _null = ETrue;
   }
   //------------------------------------------------------------
   // 构建指定内容的空数据对象。
   SValue(T value) {
      _null = EFalse;
      _value = value;
   }
public:
   //------------------------------------------------------------
   // 获得数据内容。
   MO_INLINE operator const T() const{
      return _value;
   }
public:
   //------------------------------------------------------------
   // 判断数据内容是否为空。
   MO_INLINE TBool IsNull() const{
      return _null;
   }
   //------------------------------------------------------------
   // 获得数据内容。
   MO_INLINE T Get() const{
      return _value;
   }
   //------------------------------------------------------------
   // 设置数据内容为指定数据内容。
   MO_INLINE void Set(T value) {
      _null = EFalse;
      _value = value;
   }
   //------------------------------------------------------------
   // 设置数据内容为空。
   MO_INLINE void Clear() {
      _null = ETrue;
   }
};
//------------------------------------------------------------
typedef SValue<TBool> SBool;
typedef SValue<TByte> SByte;
typedef SValue<TChar> SChar;
typedef SValue<TChar8> SChar8;
typedef SValue<TChar16> SChar16;
typedef SValue<TInt> SInt;
typedef SValue<TInt8> SInt8;
typedef SValue<TInt16> SInt16;
typedef SValue<TInt32> SInt32;
typedef SValue<TInt64> SInt64;
typedef SValue<TUint> SUint;
typedef SValue<TUint8> SUint8;
typedef SValue<TUint16> SUint16;
typedef SValue<TUint32> SUint32;
typedef SValue<TUint64> SUint64;
typedef SValue<TFloat> SFloat;
typedef SValue<TDouble> SDouble;

//============================================================
// 强制转换：对象指针强制转换的模板函数
//============================================================
template<class S, class T>
T MO_CAST_FORCE(S value){
   union{
      T Target;
      S Source;
   } _transfer;
   _transfer.Source = value;
   return _transfer.Target;
}

//============================================================
// <T>内存信息。</T>
//============================================================
struct MO_CM_DECLARE SMemoryInfo{
public:
   TInt64 lengthAlloc;
   TInt64 lengthFree;
};

//============================================================
// <T>内存控制台接口。</T>
//
// @face
// @history 130331 MAOCY 创建
//============================================================
class MO_CM_DECLARE IMemoryStorage{
public:
   //------------------------------------------------------------
   // <T>析构内存控制台接口。</T>
   MO_ABSTRACT ~IMemoryStorage(){
   }
public:
   MO_VIRTUAL TBool FetchInfo(SMemoryInfo* pInfo) = 0;
public:
   MO_VIRTUAL TBool IsAble() = 0;
   MO_VIRTUAL void Enable() = 0;
   MO_VIRTUAL void Disable(TBool detail = EFalse) = 0;
public:
   MO_VIRTUAL TAny* Alloc(TChar8C* pOwnerName, TChar8C* pTypeName, TInt size, TChar8C* pFileName = NULL, TInt lineNumber = 0) = 0;
   MO_VIRTUAL void Free(TAny* pMemory) = 0;
public:
   MO_VIRTUAL void Reset() = 0;
   MO_VIRTUAL void Dump(TBool detail = EFalse) = 0;
};

//============================================================
// <T>内存管理工具类。</T>
//============================================================
class MO_CM_DECLARE RMemory{
protected:
   static IMemoryStorage* _pStorage;
public:
   static IMemoryStorage* Storage();
   static void LinkStorage(IMemoryStorage* pStorage);
public:
   static TAny* Alloc(TChar8C* pOwnerName, TChar8C* pTypeName, TSize size, TChar8C* pFileName = NULL, TInt lineNumber = 0);
   static void Free(TAny* pMemory);
public:
   static void Fill(TAny* pMemory, TUint size, TInt value);
   static void Copy(const TAny* pSource, TAny* pTarget, TUint size);
   static void Clear(TAny* pMemory, TUint size);
};

//============================================================
// <T>日志级别。</T>
//
// @enum
// @history 091204 MAOCY 创建
//============================================================
enum ELoggerLevel{
   ELoggerLevel_Debug,
   ELoggerLevel_Info,
   ELoggerLevel_Warn,
   ELoggerLevel_Error,
   ELoggerLevel_Fatal,
   ELoggerLevel_Crash,
};

//============================================================
// <T>日志控制台接口。</T>
//
// @class
// @history 091209 MAOCY 创建
//============================================================
class MO_CM_DECLARE ILoggerConsole{
public:
   //------------------------------------------------------------
   // <T>日志控制台接口。</T>
   MO_ABSTRACT ~ILoggerConsole(){
   }
public:
   MO_VIRTUAL TBool Output(TInt type, ELoggerLevel level, TAnyC* pSender, TCharC* pMethod, TTimeTick start, TCharC* pMessage, va_list& params) = 0;
};

//============================================================
// <T>日志管理类。</T>
//
// @class
// @history 091209 MAOCY 创建
//============================================================
class MO_CM_DECLARE RLogger{
protected:
   static ILoggerConsole* _pConsole;
public:
   static void Link(ILoggerConsole* pConsole);
   static ILoggerConsole* Console();
public:
   static void Output(TInt type, ELoggerLevel level, TAnyC* pSender, TChar8C* pMethod, TTimeTick start, TCharC* pMessage, va_list& params);
   //------------------------------------------------------------
   static void Debug(TInt type, TAnyC* pSender, TChar8C* pMethod, TCharC* pMessage, ...);
   static void Debug(TInt type, TAnyC* pSender, TChar8C* pMethod, TTimeTick start, TCharC* pMessage, ...);
   static void Info (TInt type, TAnyC* pSender, TChar8C* pMethod, TCharC* pMessage, ...);
   static void Info (TInt type, TAnyC* pSender, TChar8C* pMethod, TTimeTick start, TCharC* pMessage, ...);
   static void Warn (TInt type, TAnyC* pSender, TChar8C* pMethod, TCharC* pMessage, ...);
   static void Warn (TInt type, TAnyC* pSender, TChar8C* pMethod, TTimeTick start, TCharC* pMessage, ...);
   static void Error(TInt type, TAnyC* pSender, TChar8C* pMethod, TCharC* pMessage, ...);
   static void Error(TInt type, TAnyC* pSender, TChar8C* pMethod, TTimeTick start, TCharC* pMessage, ...);
   static void Fatal(TInt type, TAnyC* pSender, TChar8C* pMethod, TCharC* pMessage, ...);
   static void Fatal(TInt type, TAnyC* pSender, TChar8C* pMethod, TTimeTick start, TCharC* pMessage, ...);
   static void Crash(TInt type, TAnyC* pSender, TChar8C* pMethod, TCharC* pMessage, ...);
   static void Crash(TInt type, TAnyC* pSender, TChar8C* pMethod, TTimeTick start, TCharC* pMessage, ...);
};

//============================================================
// <T>指定类型的只读数据指针。</T>
// <P>该类会保证当前数据指针内容在任何时候不会被修改。</P>
//
// @template
//============================================================
template <typename T>
class TPtrC{
protected:
   // @attribute 只读数据指针
   const T* _pMemory;
   // @attribute 数据长度
   TSize _length;
protected:
   //------------------------------------------------------------
   // <T>构造只读指针对象。</T>
   TPtrC(){
   }
public:
   //------------------------------------------------------------
   // <T>构造只读指针对象。</T>
   TPtrC(const T* pMemory, TSize length){
      Set(pMemory, length);
   }
   //------------------------------------------------------------
   // <T>构造只读指针对象。</T>
   TPtrC(const TPtrC<T>& ptr){
      Set(ptr);
   }
public:
   //------------------------------------------------------------
   // <T>重载指针复制操作。</T>
   void operator=(const TPtrC<T>& ptr){
      Set(ptr);
   }
   //------------------------------------------------------------
   // <T>获取指定索引位置的数据。</T>
   const T* operator[](TInt index) const{
      MO_ASSERT_RANGE(index, 0, _length);
      return _pMemory[index];
   }
public:
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE const T* MemoryC() const{
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得数据长度。</T>
   MO_INLINE TSize Length() const{
      return _length;
   }
public:
   //------------------------------------------------------------
   // <T>判断内容是否为空。</T>
   MO_INLINE TBool IsEmpty() const{
      return (0 == _length);
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Set(const T* pMemory, TSize length){
      MO_ASSERT(pMemory);
      _pMemory = pMemory;
      _length = length;
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Set(const TPtrC<T>& ptr){
      _pMemory = ptr._pMemory;
      _length = ptr._length;
   }
};
//------------------------------------------------------------
typedef TPtrC<TAny>    TAnyPtrC;
typedef TPtrC<TBool>   TBoolPtrC;
typedef TPtrC<TByte>   TBytePtrC;
typedef TPtrC<TChar>   TCharPtrC;
typedef TPtrC<TChar8>  TChar8PtrC;
typedef TPtrC<TChar16> TChar16PtrC;
typedef TPtrC<TChar32> TChar32PtrC;
typedef TPtrC<TInt>    TIntPtrC;
typedef TPtrC<TInt8>   TInt8PtrC;
typedef TPtrC<TInt16>  TInt16PtrC;
typedef TPtrC<TInt32>  TInt32PtrC;
typedef TPtrC<TInt64>  TInt64PtrC;
typedef TPtrC<TUint>   TUintPtrC;
typedef TPtrC<TUint8>  TUint8PtrC;
typedef TPtrC<TUint16> TUint16PtrC;
typedef TPtrC<TUint32> TUint32PtrC;
typedef TPtrC<TUint64> TUint64PtrC;
typedef TPtrC<TFloat>  TFloatPtrC;
typedef TPtrC<TDouble> TDoublePtrC;

//============================================================
// <T>指定类型的数据指针。</T>
// <P>外面可以改动数据指针的内容。</P>
//
// @template
//============================================================
template <typename T>
class TPtr{
protected:
   // @attribute 只读数据指针
   T* _pMemory;
   // @attribute 数据长度
   TInt* _pLength;
   // @attribute 数据容量
   TInt _capacity;
protected:
   //------------------------------------------------------------
   // <T>构造指针对象。</T>
   TPtr(){
   }
public:
   //------------------------------------------------------------
   // <T>构造引用指针。</T>
   TPtr(T* pMemory, TInt* pLength){
      Set(pMemory, pLength);
   }
   //------------------------------------------------------------
   // <T>构造引用指针。</T>
   TPtr(T* pMemory, TInt* pLength, TInt capacity){
      Set(pMemory, pLength, capacity);
   }
   //------------------------------------------------------------
   // <T>构造引用指针。</T>
   TPtr(const TPtr<T>& ptr){
      Set(ptr);
   }
public:
   //------------------------------------------------------------
   // <T>重载指针复制操作。</T>
   void operator=(const TPtr<T>& ptr) {
      Set(ptr);
   }
   //------------------------------------------------------------
   // <T>获取指定索引位置的数据。</T>
   T operator[](TInt index){
      MO_ASSERT(_pMemory);
      MO_ASSERT_RANGE(index, 0, *_pLength);
      return _pMemory[index];
   }
public:
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE const T* MemoryC() const{
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE T* Memory() const{
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE TInt Length(){
      return *_pLength;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE void SetLength(TInt length){
      MO_ASSERT_RANGE(length, 0, _capacity);
      *_pLength = length;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE TSize Capacity(){
      return _capacity;
   }
public:
   //------------------------------------------------------------
   // <T>判断内容是否为空。</T>
   MO_INLINE TBool IsEmpty() const{
      return (0 == *_pLength);
   }
   //------------------------------------------------------------
   // <T>设置数据内容。</T>
   MO_INLINE void Set(T* pMemory, TInt* pLength){
      MO_ASSERT(pMemory);
      MO_ASSERT(pLength);
      _pMemory = pMemory;
      _pLength = pLength;
      _capacity = *pLength;
   }
   //------------------------------------------------------------
   // <T>设置指针内容。</T>
   MO_INLINE void Set(T* pMemory, TInt* pLength, TInt capacity){
      MO_ASSERT(pMemory);
      MO_ASSERT(pLength);
      _pMemory = pMemory;
      _pLength = pLength;
      _capacity = capacity;
   }
   //------------------------------------------------------------
   // <T>设置指针内容。</T>
   MO_INLINE void Set(const TPtr<T>& ptr){
      _pMemory = ptr._pMemory;
      _pLength = ptr._pLength;
      _capacity = ptr._capacity;
   }
};
//------------------------------------------------------------
typedef TPtr<TAny> TAnyPtr;
typedef TPtr<TBool> TBoolPtr;
typedef TPtr<TByte> TBytePtr;
typedef TPtr<TChar> TCharPtr;
typedef TPtr<TChar8> TChar8Ptr;
typedef TPtr<TChar16> TChar16Ptr;
typedef TPtr<TChar32> TChar32Ptr;
typedef TPtr<TInt> TIntPtr;
typedef TPtr<TInt8> TInt8Ptr;
typedef TPtr<TInt16> TInt16Ptr;
typedef TPtr<TInt32> TInt32Ptr;
typedef TPtr<TInt64> TInt64Ptr;
typedef TPtr<TUint> TUintPtr;
typedef TPtr<TUint8> TUint8Ptr;
typedef TPtr<TUint16> TUint16Ptr;
typedef TPtr<TUint32> TUint32Ptr;
typedef TPtr<TUint64> TUint64Ptr;
typedef TPtr<TFloat> TFloatPtr;
typedef TPtr<TDouble> TDoublePtr;

//============================================================
// <T>自动对象指针。</T>
// <P>变量命名：psXXX</P>
//
// @template
//============================================================
template <typename T>
class TSharedPtr{
protected:
   T* _pObject;
   TInt _refer;
protected:
   //------------------------------------------------------------
   // <T>构造指针。</T>
   TSharedPtr(){
      _pObject = NULL;
      _refer = 0;
   }
   //------------------------------------------------------------
   // <T>构造指针。</T>
   TSharedPtr(T* pObject){
      _pObject = pObject;
      _refer = 0;
   }
   //------------------------------------------------------------
   // <T>析构构造指针。</T>
   ~TSharedPtr(){
      Decrease();
   }
public:
   //------------------------------------------------------------
   // <T>获取对象指针。</T>
   MO_INLINE T& operator *(){
      return _pObject;
   }
   //------------------------------------------------------------
   // <T>获取对象指针。</T>
   MO_INLINE T operator->(){
      MO_ASSERT(_pObject);
      return _pObject;
   }
   //------------------------------------------------------------
   // <T>获取对象指针。</T>
   MO_INLINE void operator=(TSharedPtr& ptr){
      _pObject = *ptr;
      _refer = ptr.Refer();
      ptr.Reset();
   }
public:
   //------------------------------------------------------------
   // <T>获得引用次数。</T>
   TInt Refer(){
      return _refer;
   }
   //------------------------------------------------------------
   // <T>增加引用次数。</T>
   void Increase(){
      _refer++;
   }
   //------------------------------------------------------------
   // <T>减少引用次数。</T>
   void Decrease(){
      _refer--;
      if(_refer <= 0){
         //MO_DELETE(_pObject);
      }
   }
   //------------------------------------------------------------
   // <T>重置对象。</T>
   void Reset(){
      _pObject = NULL;
      _refer++;
   }
   //------------------------------------------------------------
   // <T>强制释放对象。</T>
   void Release(){
      if(NULL != _pObject){
         //MO_DELETE(_pObject);
      }
      _pObject = NULL;
      _refer++;
   }
};

//============================================================
// <P>变量命名：pwXXX</P>
template <typename T>
class TWeekPtr{
};

//============================================================
// <P>变量命名：ptXXX</P>
template <typename T>
class TIntrusivePtr{
};

MO_NAMESPACE_END

#endif // __MO_CM_RUNTIME_H__
