//============================================================
// <T>LinuxX86平台定义。</T>
//============================================================
#ifndef __MO_CM_LINUX_H__
#define __MO_CM_LINUX_H__

#ifndef _STDARG_H
#include <stdarg.h>
#endif // _STDARG_H

#ifndef _CTYPE_H_
#include <ctype.h>
#endif // _CTYPE_H_

#ifndef _FCNTL_H
#include <fcntl.h>
#endif // _FCNTL_H

#ifndef _UNISTD_H_
#include <unistd.h>
#endif // _UNISTD_H_

#ifndef _WCHAR_H
#include <wchar.h>
#endif // _WCHAR_H

#ifndef _INC_ERRNO
#include <errno.h>
#endif // _INC_ERRNO

#ifndef _INC_STRING
#include <string.h>
#endif // _INC_STRING

#ifndef _INC_STDLIB
#include <stdlib.h>
#endif // _INC_STDLIB

#ifndef _INC_STDIO
#include <stdio.h>
#endif // _INC_STDIO

#ifndef _PTHREAD_H_
#include <pthread.h>
#endif // _PTHREAD_H_

//============================================================
// <T>环境头文件</T>
//============================================================
#ifndef __MO_CM_DEFINE_H__
#include "MoCmDefine.h"
#endif // __MO_CM_DEFINE_H__

//============================================================
// <T>编译宏定义</T>
//============================================================
/// @define 核心定义
#define _MO_SYS_LINUX

//============================================================
// <T>语言宏定义</T>
//============================================================
/// @define 压栈顺序描述符(按从右至左的顺序压参数入栈,由调用者把参数弹出栈。可不定参数。)
#define MO_CDECL
/// @define C函数参数压栈顺序描述符(按从右至左的顺序压参数入栈,由被调用者把参数弹出栈。)
#define MO_STDCALL
/// @define 压栈顺序描述符(头两个DWORD类型或者占更少字节的参数被放入ECX和EDX寄存器，其他剩下的参数按从右到左的顺序压入栈。由被调用者把参数弹出栈。)
#define MO_FASTCALL
/// @define 从其他文件将函数导入
#define MO_IMPORT
/// @define 将当前文件内的函数导出
#define MO_EXPORT
/// @define 禁止优化函数内部
#define MO_NAKED
/// @define 不产生初始化虚函数表
#define MO_NOVTABLE
/// @define 函数描述符
#define MO_EXTERN   extern
/// @define 定义导出C语言模式
#define MO_EXTERN_C extern "C"

//============================================================
// <T>标准函数定义。</T>
//============================================================
#define MO_LIB_MEMORY_COPY(D, S, V, L)           memcpy(D, V, L)
#define MO_LIB_MEMORY_MOVE(D, S, V, L)           memmove(D, V, L)

#define MO_LIB_TYPE_COPY(T, D, V)                memcpy(D, V, sizeof(T))
#define MO_LIB_TYPE_MOVE(T, D, V)                memmove(D, V, sizeof(T))

#define MO_LIB_TYPES_COPY(T, D, S, V, L)         memcpy(D, V, sizeof(T) * (L))
#define MO_LIB_TYPES_MOVE(T, D, S, V, L)         memmove(D, V, sizeof(T) * (L))

//============================================================
// <T>字符串函数定义。</T>
//============================================================
#define MO_LIB_STRING_LENGTH8(S)                 strlen(S)
#define MO_LIB_STRING_LENGTH16(S)                wcslen(S)
#define MO_LIB_STRING_LENGTH32(S)                wcslen(S)

#define MO_LIB_STRING_COPY8(D, S, V)             strcpy(D, V)
#define MO_LIB_STRING_COPY16(D, S, V)            wcscpy(D, V)
#define MO_LIB_STRING_COPY32(D, S, V)            wcscpy(D, V)

#define MO_LIB_STRING_COMPARE8(D, S)             strcmp(D, S)
#define MO_LIB_STRING_COMPARE16(D, S)            wcscmp(D, S)
#define MO_LIB_STRING_COMPARE32(D, S)            wcscmp(D, S)

#define MO_LIB_STRING_COMPARE_IGNORECASE8(D, S)  strcasecmp(D, S)
#ifdef _MO_ANDROID
#define MO_LIB_STRING_COMPARE_IGNORECASE16(D, S) wcscmp(D, S)
#define MO_LIB_STRING_COMPARE_IGNORECASE32(D, S) wcscmp(D, S)
#else
#define MO_LIB_STRING_COMPARE_IGNORECASE16(D, S) wcscasecmp(D, S)
#define MO_LIB_STRING_COMPARE_IGNORECASE32(D, S) wcscasecmp(D, S)
#endif // _MO_ANDROID

#define MO_LIB_STRING_FORMAT_LENGTH8(F, ...)    _vscprintf(F, __VA_ARGS__)
#define MO_LIB_STRING_FORMAT_LENGTH16(F, ...)   _vscwprintf(F, __VA_ARGS__)
#define MO_LIB_STRING_FORMAT_LENGTH32(F, ...)   _vscwprintf(F, __VA_ARGS__)

#define MO_LIB_STRING_FORMAT8(D, S, F, ...)      sprintf(D, F, __VA_ARGS__)
#define MO_LIB_STRING_FORMAT16(D, S, F, ...)     swprintf(D, F, __VA_ARGS__)
#define MO_LIB_STRING_FORMAT32(D, S, F, ...)     swprintf(D, F, __VA_ARGS__)

#define MO_LIB_STRING_NFORMAT8(D, S, F, ...)     vsnprintf(D, S, F, __VA_ARGS__)
#define MO_LIB_STRING_NFORMAT16(D, S, F, ...)    vswprintf(D, S, F, __VA_ARGS__)
#define MO_LIB_STRING_NFORMAT32(D, S, F, ...)    vswprintf(D, S, F, __VA_ARGS__)

#define MO_LIB_STRING_PRINT8(D, S, F, ...)       printf(F, __VA_ARGS__)
#define MO_LIB_STRING_PRINT16(D, S, F, ...)      wprintf(F, __VA_ARGS__)
#define MO_LIB_STRING_PRINT32(D, S, F, ...)      wprintf(F, __VA_ARGS__)

//------------------------------------------------------------
#ifdef _UNICODE
#define MO_LIB_STRING_LENGTH                     MO_LIB_STRING_LENGTH32
#define MO_LIB_STRING_COMPARE                    MO_LIB_STRING_COMPARE32
#define MO_LIB_STRING_COMPARE_IGNORECASE         MO_LIB_STRING_COMPARE_IGNORECASE32
#define MO_LIB_STRING_COPY                       MO_LIB_STRING_COPY32
#define MO_LIB_STRING_FORMAT_LENGTH              MO_LIB_STRING_FORMAT_LENGTH16
#define MO_LIB_STRING_FORMAT                     MO_LIB_STRING_FORMAT32
#define MO_LIB_STRING_NFORMAT                    MO_LIB_STRING_NFORMAT32
#define MO_LIB_STRING_PRINT                      MO_LIB_STRING_PRINT32
#else
#define MO_LIB_STRING_LENGTH                     MO_LIB_STRING_LENGTH8
#define MO_LIB_STRING_COMPARE                    MO_LIB_STRING_COMPARE8
#define MO_LIB_STRING_COMPARE_IGNORECASE         MO_LIB_STRING_COMPARE_IGNORECASE8
#define MO_LIB_STRING_COPY                       MO_LIB_STRING_COPY8
#define MO_LIB_STRING_FORMAT_LENGTH              MO_LIB_STRING_FORMAT_LENGTH8
#define MO_LIB_STRING_FORMAT                     MO_LIB_STRING_FORMAT8
#define MO_LIB_STRING_NFORMAT                    MO_LIB_STRING_NFORMAT8
#define MO_LIB_STRING_PRINT                      MO_LIB_STRING_PRINT8
#endif // _UNICODE

//============================================================
// <T>扩展函数定义。</T>
//============================================================
/// @define 收集一个页对齐内存指针
#define MO_ALIGNED_PAGE_ALLOC(S) valloc(S)
/// @define 释放一个对齐内存指针
#define MO_ALIGNED_FREE(P)       free(P)
/// @define 休眠处理（豪秒）
#define MO_LIB_SLEEP(L)          usleep(1000*L)
/// @define 休眠处理（微妙）
#define MO_LIB_MICRO_SLEEP(L)    usleep(L)
/// @define 空闲处理
#define MO_LIB_IDLE()            sched_yield()

//============================================================
// <T>扩展定义。</T>
//============================================================
#define MO_FILE_SPLITTER       /
#define MO_FILE_SPLITTER_CHAR '/;'
#define MO_FILE_SPLITTER_STR  "/"

#endif // __MO_CM_LINUX_H__
