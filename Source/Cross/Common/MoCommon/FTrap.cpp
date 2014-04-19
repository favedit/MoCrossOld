#include "MoCmSystem.h"
#include <stdio.h>

MO_NAMESPACE_BEGIN

//============================================================
// <T>创建陷阱对象。</T>
//============================================================
FTrap::FTrap(){
   MO_CLEAR(_pParent);
   _pBlocks = MO_PTR_CREATE(FTrapBlockSet);
}

//============================================================
// <T>释放陷阱对象。</T>
//============================================================
FTrap::~FTrap(){
   MO_PTR_DELETE(_pBlocks);
}

//============================================================
// <T>获得父陷阱对象。</T>
//
// @return 父陷阱对象
//============================================================
FTrap* FTrap::Parent(){
   return _pParent;
}

//============================================================
// <T>设置父陷阱对象。</T>
//
// @param pParent 父陷阱对象
//============================================================
void FTrap::SetParent(FTrap* pParent){
   _pParent = pParent;
}

//============================================================
// <T>收集内存块。</T>
//
// @return 已收集的内存
//============================================================
TAny* FTrap::Alloc(TInt size){
   return Alloc(NULL, size, NULL, 0);
}

//============================================================
// <T>收集内存块。</T>
//
// @param pClassName 类名称
// @param size 对象大小
// @param pFileName 编译文件
// @param line 编译行数
// @return 对象实例指针
//============================================================
TAny* FTrap::Alloc(TChar8C* pClassName, TInt size, TChar8C* pFileName, TInt line){
   // 创建内存结构
   FTrapBlock* pBlock = new FTrapBlock();
   pBlock->pClassName = pClassName;
   pBlock->pMemory = MO_ALLOC(size);
   pBlock->Size = size;
   pBlock->pFileName = pFileName;
   pBlock->Line = line;
   // 加入链表
   _pBlocks->Set((TInt)pBlock->pMemory, pBlock);
   return pBlock->pMemory;
}

//============================================================
// <T>删除内存块。</T>
//
// @param pMemory 内存指针
//============================================================
void FTrap::Remove(TAny* pMemory){
   FTrapBlock* pBlock = _pBlocks->Get((TInt)pMemory);
   if(NULL != pBlock){
      _pBlocks->Set((TInt)pMemory, NULL);
      MO_PTR_DELETE(pBlock);
   }
}

//============================================================
// <T>释放内存块。</T>
//
// @param pMemory 内存指针
//============================================================
void FTrap::Free(TAny* pMemory){
   FTrapBlock* pBlock = _pBlocks->Get((TInt)pMemory);
   if(NULL != pBlock){
      _pBlocks->Set((TInt)pMemory, NULL);
      MO_PTR_DELETE(pBlock);
   }
   MO_FREE(pMemory);
}

//============================================================
// <T>检查内存块。</T>
// <P>如果含有未回收的内存块，则打印出内存泄漏错误。</P>
//============================================================
void FTrap::Check(){
   if(!_pBlocks->IsEmpty()){
      // 检查未释放的指针
      TBool hasLeak = EFalse;
      TSetIteratorC<TInt, FTrapBlock*> iterator = _pBlocks->IteratorC();
      while(iterator.Next()){
         FTrapBlock* pBlock = iterator.Value();
         if(NULL != pBlock){
            hasLeak = ETrue;
            //MO_FATAL(TC("Memory leak. (instance=%s@0x%08X, size=%08d, file=%s(%d))"),
            //      (TCharC*)TFsName(pBlock->pClassName), pBlock->pMemory, pBlock->Size, (TCharC*)TFsFileName(pBlock->pFileName), pBlock->Line);
         }
      }
      // 如果有内存泄露，则抛出错误。
      if(hasLeak){
         MO_FATAL(TC("Has memory leak."));
      }
   }
}

MO_NAMESPACE_END
