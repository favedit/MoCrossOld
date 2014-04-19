//============================================================
// <T>LinuxX64平台定义。</T>
//============================================================
#if defined(_MO_LINUX) && defined(_MO_X64)
#ifndef __MO_CM_LINUX_64_H__
#define __MO_CM_LINUX_64_H__

//============================================================
// <T>环境头文件</T>
//============================================================
#ifndef _NEW_
#include <new>
#endif // _NEW_

#ifndef _TYPEINFO_
#include <typeinfo>
#endif // _TYPEINFO_

#ifndef _MALLOC_H
#include <malloc.h>
#endif // _MALLOC_H

#ifndef _INC_MEMORY
#include <memory.h>
#endif // _INC_MEMORY

#ifndef _LIBICONV_H
#include <iconv.h>
#endif // _LIBICONV_H

#ifndef _SYSCALL_H
#ifndef __CYGWIN__
#include <sys/syscall.h>
#endif // __CYGWIN__
#endif // _SYSCALL_H

#ifndef _SYS_EPOLL_H
#ifndef __CYGWIN__
#include <sys/epoll.h>
#endif // __CYGWIN__
#endif // _SYS_EPOLL_H

#ifndef _SYS_SOCKET_H
#include <sys/socket.h>
#endif // _SYS_SOCKET_H

#ifndef _ARPA_INET_H
#include <arpa/inet.h>
#endif // _ARPA_INET_H

#ifndef __MO_CM_DEFINE_H__
#include "MoCmDefine.h"
#endif // __MO_CM_DEFINE_H__

#ifndef __MO_CM_LINUX_H__
#include "MoCmLinux.h"
#endif // __MO_CM_LINUX_H__

//============================================================
// <T>定长定义。</T>
//============================================================
#ifdef __CYGWIN__
#define MO_FS_PACK_LENGTH       1024*256
#define MO_FS_SQL_MAX_LENGTH    1024*256
#define MO_FS_SPRINT_LENGTH     1024*256
#define MO_FS_LOGGER_LENGTH     1024*256
#define MO_FS_DUMP_LENGTH       1024*256
#define MO_FS_TRACK_LENGTH      1024*256
#else
#define MO_FS_PACK_LENGTH       1024*1024*4
#define MO_FS_SQL_MAX_LENGTH    1024*1024*4
#define MO_FS_SPRINT_LENGTH     1024*1024*4
#define MO_FS_LOGGER_LENGTH     1024*1024*4
#define MO_FS_DUMP_LENGTH       1024*1024*4
#define MO_FS_TRACK_LENGTH      1024*1024*4
#endif // __CYGWIN__

//============================================================
// <T>扩展函数定义。</T>
//============================================================
/// @define 产生例外
#define MO_RAISE_DEBUG()       __asm__ __volatile__ ("int $3");
/// @define 产生例外退出
#define MO_RAISE_DOWN()        {TInt* pRaise = NULL; *pRaise = 0;}
/// @define 产生退出
#define MO_RAISE_EXIT(C)       exit(C)
/// @define 收集一个对齐内存指针
#define MO_ALIGNED_ALLOC(S, T) memalign(T, S)

MO_NAMESPACE_BEGIN
      
//============================================================
// <T>数据类型定义。</T>
//============================================================
/// @type 只读整数
typedef const long int TIntC;
/// @type 整数
typedef long int TInt;
/// @type 只读32位整数
typedef const int TInt32C;
/// @type 32位整数
typedef int TInt32;
/// @type 只读64位整数
typedef const long long TInt64C;
/// @type 64位整数
typedef long long TInt64;
/// @type 128位整数
typedef struct{
   long long lower;
   long long high;
} TInt128;
/// @type 只读无符号整数
typedef const unsigned long int TUintC;
/// @type 无符号整数
typedef unsigned long int TUint;
/// @type 只读32位无符号整数
typedef const unsigned int TUint32C;
/// @type 32位无符号整数
typedef unsigned int TUint32;
/// @type 只读64位无符号整数
typedef const unsigned long long TUint64C;
/// @type 64位无符号整数
typedef unsigned long long TUint64;
/// @type 128位无符号整数
// typedef long double TUint128;

//============================================================
// <T>扩展类型定义。</T>
//============================================================
typedef TInt      TInstance;
typedef pid_t     TThreadProcessId;

MO_NAMESPACE_END

#endif // __MO_CM_LINUX_64_H__
#endif // _MO_LINUX && _MO_X64
