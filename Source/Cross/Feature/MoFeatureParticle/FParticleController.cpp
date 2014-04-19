#include "MoFtParticle.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FParticleController, FInstance);

//============================================================
// <T>构造粒子控制器。</T>
//============================================================
FParticleController::FParticleController(){
   _maxCount = 10000;
   _pParticles = MO_CREATE(FParticleVector);
}

//============================================================
// <T>析构粒子控制器。</T>
//============================================================
FParticleController::~FParticleController(){
   MO_DELETE(_pParticles);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FParticleController::Process(){
   // 获得粒子个数
   TInt particleCount = _pParticles->Count();
   if(particleCount == 0){
      return ESuccess;
   }
   //............................................................
   // 逻辑处理
   for(TInt n = 0; n < particleCount; n++){
      FParticle* pParticle = _pParticles->Get(n);
      pParticle->Process();
   }
   //............................................................
   // 筛除删除（处理过程中可能产生新的粒子）
   TInt index = 0;
   TInt count = _pParticles->Count();
   FParticle** ppParticle = _pParticles->Memory();
   for(TInt n = 0; n < count; n++){
      FParticle* pParticle = ppParticle[n];
      if(pParticle->IsStatusAlive()){
         // 保存对象
         ppParticle[index++] = pParticle;
      }else{
         // 释放对象
         pParticle->Free();
         // 释放例子
         RParticleManager::Instance().ParticleFree(pParticle);
      }
   }
   _pParticles->SetCount(index);
   return ESuccess;
}

MO_NAMESPACE_END
