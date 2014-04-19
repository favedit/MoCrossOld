#include "MoCmRuntime.h"

MO_NAMESPACE_BEGIN

//------------------------------------------------------------
// 存储器
IMemoryStorage* RMemory::_pStorage = NULL;

//============================================================
// <T>获得存储器。</T>
//
// @return 存储器
//============================================================
IMemoryStorage* RMemory::Storage(){
   return _pStorage;
}

//============================================================
// <T>关联存储器。</T>
//
// @param pStorage 存储器
//============================================================
void RMemory::LinkStorage(IMemoryStorage* pStorage){
   _pStorage = pStorage;
}

//============================================================
// <T>存储器收集内存。</T>
// <P>每块内存前会多一个描述指针位置，指针为空表示没有描述信息。</P>
//
// @param pOwnerName 拥有者名称
// @param pTypeName 类型名称
// @param size 大小
// @param pFileName 文件名称
// @param lineNumber 行号
// @return 内存指针
//============================================================
TAny* RMemory::Alloc(TChar8C* pOwnerName, TChar8C* pTypeName, TSize size, TChar8C* pFileName, TInt lineNumber){
#ifdef _MO_DEBUG
   if(NULL != _pStorage){
      return _pStorage->Alloc(pOwnerName, pTypeName, (TInt)size, pFileName, lineNumber);
   }else{
      TAny* pAlloc = malloc(sizeof(TAny*) + size);
      *(TInt*)pAlloc = 0;
      return (TByte*)pAlloc + sizeof(TAny*);
   }
#else
   return malloc(size);
#endif // _DEBUG
}

//============================================================
// <T>存储器释放内存。</T>
//
// @param pMemory 内存指针
//============================================================
void RMemory::Free(TAny* pMemory){
   MO_CHECK(pMemory, return);
#ifdef _MO_DEBUG
   if(NULL != _pStorage){
      _pStorage->Free(pMemory);
   }else{
      TAny* pAlloc = (TByte*)pMemory - sizeof(TAny*);
      free(pAlloc);
   }
#else
   free(pMemory);
#endif // _DEBUG
}

//============================================================
// <T>填充指定的内存区域。</T>
//
// @param pMemory 待填充的内存
// @param value 填充值
// @param size 指定大小
//============================================================
void RMemory::Fill(TAny* pMemory, TUint size, TInt value){
   MO_ASSERT(pMemory);
   memset(pMemory, value, size);
}

//============================================================
// <T>复制内存区域到指定位置。</T>
//
// @param pSource 源内存
// @param pTarget 目标内存
// @param size 指定大小
//============================================================
void RMemory::Copy(const TAny* pSource, TAny* pTarget, TUint size){
   MO_ASSERT(pSource);
   MO_ASSERT(pTarget);
   memcpy(pTarget, pSource, size);
}

//============================================================
// <T>重置指定的内存区域。</T>
// <P>当前内存内全部用0进行填充。</P>
//
// @param pMemory 待填充的内存
// @param size 指定大小
//============================================================
void RMemory::Clear(TAny* pMemory, TUint size){
   MO_ASSERT(pMemory);
   memset(pMemory, 0, size);
}

MO_NAMESPACE_END
