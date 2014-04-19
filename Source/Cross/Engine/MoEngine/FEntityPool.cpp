#include "MoEgEntity.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造粒子缓冲池。</T>
//============================================================
FEntityPool::FEntityPool(){
   _typeCd = EEntityType_Unknown;
}

//============================================================
// <T>析构粒子缓冲池。</T>
//============================================================
FEntityPool::~FEntityPool(){
}

//============================================================
// <T>收集一个粒子对象。</T>
//
// @return 粒子对象
//============================================================
FEntity* FEntityPool::Alloc(){
   // 检查是否有剩余
   TBool hasFree = HasFreeItem();
   if(!hasFree){
      FEntity* pEntity = Create();
      Store(pEntity);
   }
   // 收集一个对象
   return AllocFirst();
}

//============================================================
// <T>释放一个粒子对象。</T>
//
// @param pParticle 粒子对象
//============================================================
void FEntityPool::Free(FEntity* pEntity){
   FreeLast(pEntity);
}

MO_NAMESPACE_END
