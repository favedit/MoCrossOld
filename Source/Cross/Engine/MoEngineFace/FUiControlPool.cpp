#include "MoFmCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造粒子缓冲池。</T>
//============================================================
FUiControlPool::FUiControlPool(){
   _typeCd = EControlType_Unknown;
}

//============================================================
// <T>析构粒子缓冲池。</T>
//============================================================
FUiControlPool::~FUiControlPool(){
}

//============================================================
// <T>收集一个粒子对象。</T>
//
// @return 粒子对象
//============================================================
FUiControl* FUiControlPool::Alloc(){
   // 检查是否有剩余
   TBool hasFree = HasFreeItem();
   if(!hasFree){
      FUiControl* pControl = Create();
      Store(pControl);
   }
   // 收集一个对象
   return AllocFirst();
}

//============================================================
// <T>释放一个粒子对象。</T>
//
// @param pParticle 粒子对象
//============================================================
void FUiControlPool::Free(FUiControl* pControl){
   FreeLast(pControl);
}

MO_NAMESPACE_END
