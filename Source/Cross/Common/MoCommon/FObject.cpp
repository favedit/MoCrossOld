#include "MoCmInterface.h"
#include "MoCmMemory.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造对象基类。</T>
//============================================================
FObject::FObject(){
}

//============================================================
// <T>析构对象基类。</T>
//============================================================
FObject::~FObject(){
}

//============================================================
// <T>收集一块内存。</T>
//
// @param size 大小
// @return 内存指针
//============================================================
TAny* FObject::operator new(TSize size){
   return RAllocator::TypeAlloc(size);
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
TAny* FObject::operator new(TSize size, TChar8C* pClassName, TChar8C* pFileName, TInt lineNumber){
   return RAllocator::TypeAlloc(pClassName, size, pFileName, lineNumber);
}

//============================================================
// <T>收集内存创建对象。</T>
//
// @param size 大小
// @param pMemory 内存指针
// @return 内存指针
//============================================================
TAny* FObject::operator new(TSize size, TAny* pMemory){
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
TAny* FObject::operator new(TSize size, TAny* pMemory, TChar8C* pClassName, TChar8C* pFileName, TInt lineNumber){
   return pMemory;
}

//============================================================
// <T>释放一块内存。</T>
//
// @param pMemory 内存指针
//============================================================
void FObject::operator delete(TAny* pMemory){
   RAllocator::TypeFree(pMemory);
}

//============================================================
// <T>释放对象内存。</T>
//
// @param pMemory 内存指针
// @param pAlloc 收集内存
//============================================================
void FObject::operator delete(TAny* pMemory, TAny* pAlloc){
}

//============================================================
// <T>释放对象内存。</T>
//
// @param pMemory 内存指针
// @param pClassName 类名称
// @param pFileName 文件名称
// @param lineNumber 行号
//============================================================
void FObject::operator delete(TAny* pMemory, TChar8C* pClassName, TChar8C* pFileName, TInt lineNumber){
   RAllocator::TypeFree(pMemory);
}

//============================================================
// <T>获得类的哈希值。</T>
//
// @return 哈希值
//============================================================
THashCode FObject::HashCode() const{
   TInt hashcode = (TInt)this;
   return (THashCode)hashcode;
}

MO_NAMESPACE_END
