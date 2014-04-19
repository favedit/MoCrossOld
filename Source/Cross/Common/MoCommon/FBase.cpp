#include "MoCmInterface.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>收集内存创建对象。</T>
//
// @param size 大小
// @return 内存指针
//============================================================
TAny* FBase::operator new(TSize size){
   return MO_MEM_ALLOC(size);
}

//============================================================
// <T>收集内存创建对象。</T>
//
// @param size 大小
// @param pClassName 类名称
// @param pFileName 文件名称
// @param lineNumber 行号
// @return 内存指针
//============================================================
TAny* FBase::operator new(TSize size, TChar8C* pClassName, TChar8C* pFileName, TInt lineNumber){
   return MO_MEM_ALLOC(size);
}

//============================================================
// <T>收集内存创建对象。</T>
//
// @param size 大小
// @param pMemory 内存指针
// @return 内存指针
//============================================================
TAny* FBase::operator new(TSize size, TAny* pMemory){
   return pMemory;
}

//============================================================
// <T>收集内存创建对象。</T>
//
// @param size 大小
// @param pMemory 内存指针
// @param pClassName 类名称
// @param pFileName 文件名称
// @param lineNumber 行号
// @return 内存指针
//============================================================
TAny* FBase::operator new(TSize size, TAny* pMemory, TChar8C* pClassName, TChar8C* pFileName, TInt lineNumber){
   return pMemory;
}

//============================================================
// <T>释放对象内存。</T>
//
// @param pMemory 内存指针
//============================================================
void FBase::operator delete(TAny* pMemory){
   MO_MEM_FREE(pMemory);
}

//============================================================
// <T>释放对象内存。</T>
//
// @param pMemory 内存指针
// @param pAlloc 收集内存
//============================================================
void FBase::operator delete(TAny* pMemory, TAny* pAlloc){
}

//============================================================
// <T>释放对象内存。</T>
//
// @param pMemory 内存指针
// @param pClassName 类名称
// @param pFileName 文件名称
// @param lineNumber 行号
//============================================================
void FBase::operator delete(TAny* pMemory, TChar8C* pClassName, TChar8C* pFileName, TInt lineNumber){
   MO_MEM_FREE(pMemory);
}

MO_NAMESPACE_END
