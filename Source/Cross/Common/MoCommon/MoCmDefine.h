//============================================================
// <T>共通定义。</T>
//============================================================
#ifndef __MO_CM_DEFINE_H__
#define __MO_CM_DEFINE_H__

//============================================================
// @define 标志定义
#ifdef _MO_DEBUG
#define _MO_FG_ASSERT
#define _MO_FG_CHECK
#define _MO_FG_MEMORY
#define _MO_FG_LOGGER
#else
#define _MO_FG_CHECK
#define _MO_FG_TRAP
#endif // _MO_DEBUG

//============================================================
// @define 命名空间定义
#define MO_NAMESPACE          MO
#define MO_NAMESPACE_BEGIN    namespace MO_NAMESPACE{
#define MO_NAMESPACE_END      }
#define MO_NAMESPACE_USING(N) using namespace N
#define MO_NAMESPACE_INCLUDE  MO_NAMESPACE_USING(MO_NAMESPACE);

//============================================================
// <T>标准函数定义。</T>
//============================================================
#define MO_LIB_MEMORY_CLEAR(D, L)      memset(D, 0, L)
#define MO_LIB_MEMORY_COMPARE(S, D, L) memcmp(S, D, L)
#define MO_LIB_TYPE_CLEAR(T, V)        memset(V, 0, sizeof(T))
#define MO_LIB_TYPES_CLEAR(T, V, L)    memset(V, 0, sizeof(T) * (L))

#endif // __MO_CM_DEFINE_H__
