//============================================================
// <T>WindowsX64平台定义。</T>
//============================================================
#if defined(_MO_WINDOWS) && defined(_MO_X64)
#ifndef __MO_CM_WINDOWS_64_H__
#define __MO_CM_WINDOWS_64_H__

//============================================================
// <T>环境头文件</T>
//============================================================
#ifndef __MO_CM_DEFINE_H__
#include "MoCmDefine.h"
#endif // __MO_CM_DEFINE_H__

#ifndef __MO_CM_WINDOWS_H__
#include "MoCmWindows.h"
#endif // __MO_CM_WINDOWS_H__

//============================================================
// <T>扩展函数定义。</T>
//============================================================
/// @define 产生例外
#define MO_RAISE_DEBUG() __debugbreak();
/// @define 产生例外退出
#define MO_RAISE_DOWN()  {TInt* pRaise = NULL; *pRaise = 0;}
/// @define 产生退出
#define MO_RAISE_EXIT(C) exit(C)

//============================================================
// <T>定长定义。</T>
//============================================================
#ifdef _MO_STACK_MIN
#define MO_FS_PACK_LENGTH       1024*64
#define MO_FS_SQL_MAX_LENGTH    1024*64
#define MO_FS_SPRINT_LENGTH     1024*64
#define MO_FS_LOGGER_LENGTH     1024*64
#define MO_FS_DUMP_LENGTH       1024*64
#define MO_FS_TRACK_LENGTH      1024*64
#else
#define MO_FS_PACK_LENGTH       1024*1024*2
#define MO_FS_SQL_MAX_LENGTH    1024*1024*2
#define MO_FS_SPRINT_LENGTH     1024*1024*2
#define MO_FS_LOGGER_LENGTH     1024*1024*2
#define MO_FS_DUMP_LENGTH       1024*1024*2
#define MO_FS_TRACK_LENGTH      1024*1024*2
#endif // _MO_STACK_MIN

MO_NAMESPACE_BEGIN

//============================================================
// <T>数据类型定义。</T>
//============================================================
/// @type 只读整数
typedef const __int64 TIntC;
/// @type 整数
typedef __int64 TInt;
/// @type 只读整数
typedef const int TInt32C;
/// @type 32位整数
typedef int TInt32;
/// @type 只读64位整数
typedef const __int64 TInt64C;
/// @type 64位整数
typedef __int64 TInt64;
/// @type 只读无符号整数
typedef const unsigned __int64 TUintC;
/// @type 无符号整数
typedef unsigned __int64 TUint;
/// @type 只读32位无符号整数
typedef const unsigned int TUint32C;
/// @type 32位无符号整数
typedef unsigned int TUint32;
/// @type 只读64位无符号整数
typedef const unsigned __int64 TUint64C;
/// @type 64位无符号整数
typedef unsigned __int64 TUint64;

/// @type 只读32位整数
typedef const long TLongC;
/// @type 32位整数
typedef long TLong;
/// @type 只读32位无符号整数
typedef const unsigned long TUlongC;
/// @type 32位无符号整数
typedef unsigned long TUlong;
typedef unsigned long TDword;

//============================================================
// <T>扩展类型定义。</T>
//============================================================
typedef HINSTANCE TInstance;
typedef TInt      TThreadProcessId;

MO_NAMESPACE_END

#endif // __MO_CM_WINDOWS_64_H__
#endif // _MO_WINDOWS && _MO_X64
