#include "MoCmMemory.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造内存原子实例。</T>
//
// @param pAllocator 内存收集器
//============================================================
SMemoryEntry::SMemoryEntry(IAllocator* pAllocator){
   // 初始化结构
   this->pAllocator = pAllocator;
   MO_CLEAR(pPrior);
   MO_CLEAR(pNext);
   MO_CLEAR(pAlloc);
   MO_CLEAR(pMemory);
   size = 0;
   // 初始化位置
   MO_CLEAR(pTypeName);
   MO_CLEAR(pFileName);
   fileLine = 0;
   // 初始化跟踪
   allocCount = 0;
   freeCount = 0;
   //createDateTime = RDateTime::Current();
   usedDateTime = 0;
}

//============================================================
// <T>析构内存原子实例。</T>
//============================================================
SMemoryEntry::~SMemoryEntry(){
}

//============================================================
// <T>设置类名。</T>
//
// @param pClassName 类名
//============================================================
void SMemoryEntry::SetTypeName(TCharC* pTypeName){
   this->pTypeName = pTypeName;
}

//============================================================
// <T>设置文件信息。</T>
//
// @param pFileName 文件名称
// @param line 文件行号
//============================================================
void SMemoryEntry::SetFileInfo(TChar8C* pFileName, TInt fileLine){
   this->pFileName = pFileName;
   this->fileLine = fileLine;
}

//============================================================
// <T>关联内存实例。</T>
//
// @param size 内存大小
// @param pMemory 已经收集的内存
//============================================================
void SMemoryEntry::Link(TByte* pData, TUint size){
   // 初始化变量
   size = size;
   // 收集对齐内存
   pAlloc = (TInt*)pData;
   // 第一个位置存储当前对象指针
   pAlloc[0] = (TInt)this;
   // 第二个位置为要收集的内存
   pMemory = pAlloc + 1;
}

//============================================================
// <T>收集内存。</T>
//============================================================
void SMemoryEntry::Alloc(){
   ++allocCount;
   //usedDateTime = RDateTime::Current();
}

//============================================================
// <T>释放内存。</T>
//============================================================
void SMemoryEntry::Free(){
   ++freeCount;
}

MO_NAMESPACE_END
