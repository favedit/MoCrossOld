#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造显示缓冲池。</T>
//============================================================
FDisplayPool::FDisplayPool(){
}

//============================================================
// <T>析构显示缓冲池。</T>
//============================================================
FDisplayPool::~FDisplayPool(){
}

//============================================================
// <T>收集一个显示对象。</T>
//
// @return 显示对象
//============================================================
FDrawable* FDisplayPool::Alloc(){
   // 检查是否有剩余
   TBool hasFree = HasFreeItem();
   if(!hasFree){
      FDrawable* pDisplay = Create();
      Store(pDisplay);
   }
   // 收集一个对象
   return AllocFirst();
}

//============================================================
// <T>释放一个显示对象。</T>
//
// @param pDisplay 显示对象
//============================================================
void FDisplayPool::Free(FDrawable* pDisplay){
   FreeLast(pDisplay);
}

MO_NAMESPACE_END
