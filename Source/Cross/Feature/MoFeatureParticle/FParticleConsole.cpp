#include "MoFtParticle.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造粒子控制器。</T>
//============================================================
FParticleConsole::FParticleConsole(){
   _pPools = MO_CREATE(FParticlePoolCollection);
   _pControllers = MO_CREATE(FParticleControllerCollection);
}

//============================================================
// <T>析构粒子控制器。</T>
//============================================================
FParticleConsole::~FParticleConsole(){
   MO_DELETE(_pPools);
   MO_DELETE(_pControllers);
}

//============================================================
// <T>收集一个指定类型的粒子对象。</T>
//
// @param typeCd 类型
// @return 粒子对象
//============================================================
FParticle* FParticleConsole::ParticleAlloc(TParticleType typeCd){
   FParticlePool* pPool = PoolFind(typeCd);
   MO_ASSERT(pPool);
   return pPool->Alloc();
}

//============================================================
// <T>释放一个指定类型的粒子对象。</T>
//
// @return 粒子对象
//============================================================
void FParticleConsole::ParticleFree(FParticle* pParticle){
   TParticleType typeCd = pParticle->TypeCd();
   FParticlePool* pPool = PoolFind(typeCd);
   MO_ASSERT(pPool);
   return pPool->Free(pParticle);
}

//============================================================
// <T>配置处理</T>
//============================================================
void FParticleConsole::Setup(){
   // 注册移动粒子缓冲池
   FParticleMovePool* pMovePool = MO_CREATE(FParticleMovePool);
   PoolRegister(pMovePool);
}

//============================================================
// <T>获得缓冲池</T>
//
// @param typecCd 类型
// @return 缓冲池
//============================================================
FParticlePool* FParticleConsole::PoolFind(TParticleType typeCd){
   TInt count = _pPools->Count();
   for(TInt n = 0; n < count; n++){
      FParticlePool* pPool = _pPools->Get(n);
      if(pPool->TypeCd() == typeCd){
         return pPool;
      }
   }
   return NULL;
}

//============================================================
// <T>注册缓冲池。</T>
//
// @param pPool 缓冲池
//============================================================
void FParticleConsole::PoolRegister(FParticlePool* pPool){
   _pPools->Push(pPool);
}

//============================================================
// <T>注销缓冲池。</T>
//
// @param pPool 缓冲池
//============================================================
void FParticleConsole::PoolUnregister(FParticlePool* pPool){
   _pPools->Remove(pPool);
}

//============================================================
// <T>释放处理。</T>
//
// @return 处理结果
//============================================================
TResult FParticleConsole::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
