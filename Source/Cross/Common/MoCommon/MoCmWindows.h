//============================================================
// <T>Windows平台定义。</T>
//============================================================
#ifndef __MO_CM_WINDOWS_H__
#define __MO_CM_WINDOWS_H__

#ifndef _NEW_
#include <new>
#endif // _NEW_

#ifndef _TYPEINFO_
#include <typeinfo>
#endif // _TYPEINFO_

#ifndef _INC_ERRNO
#include <errno.h>
#endif // _INC_ERRNO

#ifndef _INC_MEMORY
#include <memory.h>
#endif // _INC_MEMORY

#ifndef _INC_STRING
#include <string.h>
#endif // _INC_STRING

#ifndef _INC_TIME
#include <time.h>
#endif // _INC_TIME

#ifndef _INC_IO
#include <io.h>
#endif // _INC_IO

#ifndef _INC_STDLIB
#include <stdlib.h>
#endif // _INC_STDLIB

#ifndef _INC_STDIO
#include <stdio.h>
#endif // _INC_STDIO

#ifndef _WINDOWS_
#include <Windows.h>
#endif // _WINDOWS_

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
#define _MO_SYS_WINDOWS
/// @define 函数名定义
#define __PRETTY_FUNCTION__ __FUNCTION__

//============================================================
// <T>语言宏定义</T>
//============================================================
/// @define 压栈顺序描述符(按从右至左的顺序压参数入栈,由调用者把参数弹出栈。可不定参数。)
#define MO_CDECL   __cdecl
/// @define C函数参数压栈顺序描述符(按从右至左的顺序压参数入栈,由被调用者把参数弹出栈。)
#define MO_STDCALL __stdcall
/// @define 压栈顺序描述符(头两个DWORD类型或者占更少字节的参数被放入ECX和EDX寄存器，其他剩下的参数按从右到左的顺序压入栈。由被调用者把参数弹出栈。)
#define MO_FASTCALL __fastcall
/// @define 从其他文件将函数导入
#define MO_IMPORT __declspec(dllimport)
/// @define 将当前文件内的函数导出
#define MO_EXPORT __declspec(dllexport)
/// @define 禁止优化函数内部
#define MO_NAKED __declspec(naked)
/// @define 不产生初始化虚函数表
#define MO_NOVTABLE __declspec(novtable)
/// @define 函数描述符
#define MO_EXTERN   extern
/// @define 定义导出C语言模式
#define MO_EXTERN_C extern "C"

//============================================================
// <T>标准函数定义。</T>
//============================================================
#define MO_LIB_MEMORY_COPY(D, S, V, L)            memcpy_s(D, S, V, L)
#define MO_LIB_MEMORY_MOVE(D, S, V, L)            memmove_s(D, S, V, L)

#define MO_LIB_TYPE_COPY(T, D, V)                 memcpy(D, V, sizeof(T))
#define MO_LIB_TYPE_MOVE(T, D, V)                 memmove(D, V, sizeof(T))

#define MO_LIB_TYPES_COPY(T, D, S, V, L)          memcpy_s(D, sizeof(T) * (S), V, sizeof(T) * (L))
#define MO_LIB_TYPES_MOVE(T, D, S, V, L)          memmove_s(D, sizeof(T) * (S), V, sizeof(T) * (L))

//============================================================
// <T>字符串函数定义。</T>
//============================================================
#define MO_LIB_STRING_LENGTH8(S)                  strlen(S)
#define MO_LIB_STRING_LENGTH16(S)                 wcslen(S)
#define MO_LIB_STRING_LENGTH32(S)                 wcslen(S)

#define MO_LIB_STRING_COMPARE8(D, S)              strcmp(D, S)
#define MO_LIB_STRING_COMPARE16(D, S)             wcscmp(D, S)
#define MO_LIB_STRING_COMPARE32(D, S)             wcscmp(D, S)

#define MO_LIB_STRING_COMPARE_IGNORECASE8(D, S)  _stricmp(D, S)
#define MO_LIB_STRING_COMPARE_IGNORECASE16(D, S) _wcsicmp(D, S)
#define MO_LIB_STRING_COMPARE_IGNORECASE32(D, S) _wcsicmp(D, S)

#define MO_LIB_STRING_COPY8(D, S, V)              strcpy_s(D, S, V)
#define MO_LIB_STRING_COPY16(D, S, V)             wcscpy_s(D, S, V)
#define MO_LIB_STRING_COPY32(D, S, V)             wcscpy_s(D, S, V)

#define MO_LIB_STRING_FORMAT_LENGTH8(F, ...)      _vscprintf(F, __VA_ARGS__)
#define MO_LIB_STRING_FORMAT_LENGTH16(F, ...)     _vscwprintf(F, __VA_ARGS__)
#define MO_LIB_STRING_FORMAT_LENGTH32(F, ...)     _vscwprintf(F, __VA_ARGS__)

#define MO_LIB_STRING_FORMAT8(D, S, F, ...)       sprintf_s(D, S, F, __VA_ARGS__)
#define MO_LIB_STRING_FORMAT16(D, S, F, ...)      swprintf_s(D, S, F, __VA_ARGS__)
#define MO_LIB_STRING_FORMAT32(D, S, F, ...)      swprintf_s(D, S, F, __VA_ARGS__)

#define MO_LIB_STRING_NFORMAT8(D, S, F, ...)      vsprintf_s(D, S, F, __VA_ARGS__)
#define MO_LIB_STRING_NFORMAT16(D, S, F, ...)     vswprintf_s(D, S, F, __VA_ARGS__)
#define MO_LIB_STRING_NFORMAT32(D, S, F, ...)     vswprintf_s(D, S, F, __VA_ARGS__)

#define MO_LIB_STRING_PRINT8(F, ...)              printf(F, __VA_ARGS__)
#define MO_LIB_STRING_PRINT16(F, ...)             wprintf(F, __VA_ARGS__)
#define MO_LIB_STRING_PRINT32(F, ...)             wprintf(F, __VA_ARGS__)

#define MO_LIB_FILE_OPEN8(H, F, V)                fopen_s(H, F, V)
#define MO_LIB_FILE_OPEN16(H, F, V)              _wfopen_s(H, F, V)
#define MO_LIB_FILE_OPEN32(H, F, V)              _wfopen_s(H, F, V)

//------------------------------------------------------------
#ifdef _UNICODE
#define MO_LIB_STRING_LENGTH                      MO_LIB_STRING_LENGTH16
#define MO_LIB_STRING_COMPARE                     MO_LIB_STRING_COMPARE16
#define MO_LIB_STRING_COMPARE_IGNORECASE          MO_LIB_STRING_COMPARE_IGNORECASE16
#define MO_LIB_STRING_COPY                        MO_LIB_STRING_COPY16
#define MO_LIB_STRING_FORMAT_LENGTH               MO_LIB_STRING_FORMAT_LENGTH16
#define MO_LIB_STRING_FORMAT                      MO_LIB_STRING_FORMAT16
#define MO_LIB_STRING_NFORMAT                     MO_LIB_STRING_NFORMAT16
#define MO_LIB_STRING_PRINT                       MO_LIB_STRING_PRINT16
#define MO_LIB_FILE_OPEN                          MO_LIB_FILE_OPEN16
#else
#define MO_LIB_STRING_LENGTH                      MO_LIB_STRING_LENGTH8
#define MO_LIB_STRING_COMPARE                     MO_LIB_STRING_COMPARE8
#define MO_LIB_STRING_COMPARE_IGNORECASE          MO_LIB_STRING_COMPARE_IGNORECASE8
#define MO_LIB_STRING_COPY                        MO_LIB_STRING_COPY8
#define MO_LIB_STRING_FORMAT_LENGTH               MO_LIB_STRING_FORMAT_LENGTH8
#define MO_LIB_STRING_FORMAT                      MO_LIB_STRING_FORMAT8
#define MO_LIB_STRING_NFORMAT                     MO_LIB_STRING_NFORMAT8
#define MO_LIB_STRING_PRINT                       MO_LIB_STRING_PRINT8
#define MO_LIB_FILE_OPEN                          MO_LIB_FILE_OPEN8
#endif // _UNICODE

//============================================================
// <T>扩展函数定义。</T>
//============================================================
/// @define 收集一个对齐内存指针
#define MO_ALIGNED_ALLOC(S, T)   _aligned_malloc(S, T)
/// @define 收集一个页对齐内存指针
#define MO_ALIGNED_PAGE_ALLOC(S) _aligned_malloc(S, 1024*4)
/// @define 释放一个对齐内存指针
#define MO_ALIGNED_FREE(P)       _aligned_free(P)
/// @define 休眠处理（豪秒）
#define MO_LIB_SLEEP(L)         ::Sleep((TUint32)L)
/// @define 休眠处理（微妙）
#define MO_LIB_MICRO_SLEEP(L)   ::Sleep(MO_LIB_MAX((TUint32)(L/1000), 1))
/// @define 空闲处理
#define MO_LIB_IDLE()           ::SwitchToThread()

//============================================================
// <T>扩展定义。</T>
//============================================================
#define MO_FILE_SPLITTER       \
#define MO_FILE_SPLITTER_CHAR '\\'
#define MO_FILE_SPLITTER_STR  "\\"

#endif // __MO_CM_WINDOWS_H__
