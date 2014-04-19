#include "MoCmMemory.h"

MO_NAMESPACE_BEGIN

//============================================================
FMemoryLockAllocator::FMemoryLockAllocator(){
}

//============================================================
FMemoryLockAllocator::~FMemoryLockAllocator(){
}

//============================================================
// <T>收集一块定长的内存。</T>
//
// @param size 内存大小
// @return 内存指针
//============================================================
TAny* FMemoryLockAllocator::Alloc(TInt size){
   MO_ASSERT(size > 0);
   _locker.Enter();
   TAny* pMemory = FMemoryAllocator::Alloc(size);
   _locker.Leave();
   return pMemory;
}

//============================================================
// <T>收集一块指定大小的内存。</T>
//
// @param pOwnerName 拥有者名称
// @param pTypeName 类型名称
// @param size 内存大小
// @param pFileName 文件名称
// @param lineNumber 文件行数
// @return 内存指针
//============================================================
TAny* FMemoryLockAllocator::Alloc(TCharC* pOwnerName, TCharC* pTypeName, TInt size, TChar8C* pFileName, TInt lineNumber){
   _locker.Enter();
   TAny* pMemory = FMemoryAllocator::Alloc(pOwnerName, pTypeName, size, pFileName, lineNumber);
   _locker.Leave();
   return pMemory;
}

//============================================================
// <T>释放内存。</T>
//
// @param pMemory 内存指针
//============================================================
void FMemoryLockAllocator::Free(TAny* pMemory){
   _locker.Enter();
   FMemoryAllocator::Free(pMemory);
   _locker.Leave();
}

MO_NAMESPACE_END
