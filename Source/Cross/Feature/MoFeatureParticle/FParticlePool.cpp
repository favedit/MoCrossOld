#include "MoFtParticle.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造粒子缓冲池。</T>
//============================================================
FParticlePool::FParticlePool(){
   _typeCd = 0;
}

//============================================================
// <T>析构粒子缓冲池。</T>
//============================================================
FParticlePool::~FParticlePool(){
}

//============================================================
// <T>收集一个粒子对象。</T>
//
// @return 粒子对象
//============================================================
FParticle* FParticlePool::Alloc(){
   // 检查是否有剩余
   TBool hasFree = HasFreeItem();
   if(!hasFree){
      FParticle* pParticle = Create();
      Store(pParticle);
   }
   // 收集一个对象
   return AllocFirst();
}

//============================================================
// <T>释放一个粒子对象。</T>
//
// @param pParticle 粒子对象
//============================================================
void FParticlePool::Free(FParticle* pParticle){
   FreeLast(pParticle);
}

MO_NAMESPACE_END
