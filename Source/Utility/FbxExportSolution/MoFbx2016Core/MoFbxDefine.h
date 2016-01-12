#pragma once

#include <fbxsdk.h>
#include <MoCommon.h>
#include <MoMath.h>

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
/// @define 导出定义
#ifdef _MO_FBX_EXPORTS
#define MO_FBX_DECLARE MO_EXPORT
#else
#define MO_FBX_DECLARE MO_IMPORT
#endif

//============================================================
MO_NAMESPACE_USING(FBXSDK_NAMESPACE);
