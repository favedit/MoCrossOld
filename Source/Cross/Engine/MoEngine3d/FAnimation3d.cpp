#include "MoE3Display.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造实体3D动画。</T>
//============================================================
FAnimation3d::FAnimation3d(){
   _baseTick = 0;
   _currentTick = 0;
   _lastTick = 0;
   _pBones = MO_CREATE(FBone3dCollection);
}

//============================================================
// <T>析构实体3D动画。</T>
//============================================================
FAnimation3d::~FAnimation3d(){
   MO_DELETE(_pBones);
}

//============================================================
FBone3d* FAnimation3d::FindBone(TInt boneId){
   TInt count = _pBones->Count();
   for(TInt n = 0; n < count; n++){
      FBone3d* pBone = _pBones->Get(n);
      if(pBone->BoneId() == boneId){
         return pBone;
      }
   }
   return NULL;
}

//============================================================
FBone3d* FAnimation3d::FindBone(TResourceId modelId, TInt boneId){
   TInt count = _pBones->Count();
   for(TInt n = 0; n < count; n++){
      FBone3d* pBone = _pBones->Get(n);
      if(pBone->ModelId() == modelId){
         if(pBone->BoneId() == boneId){
            return pBone;
         }
      }
   }
   return NULL;
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源对象
//============================================================
TResult FAnimation3d::LoadResource(FRs3dAnimation* pResource){
   MO_CHECK(pResource, return ENull);
   return ESuccess;
}

//============================================================
// <T>更新处理。</T>
//
// @return 处理结果
//============================================================
TResult FAnimation3d::Update(){
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FAnimation3d::Process(){
   // 获得时间
   FTimerDevice* pTimerDevice = RDeviceManager::Instance().Find<FTimerDevice>();
   TTimeTick currentTick = pTimerDevice->CurrentTick();
   if(_lastTick == 0){
      _lastTick = currentTick;
   }
   _currentTick = (currentTick - _lastTick + _baseTick) / 1000 * 6;
   // 计算间隔
   _locker.Enter();
   TInt count = _pBones->Count();
   for(TInt n = 0; n < count; n++){
      FBone3d* pBone = _pBones->Get(n);
      pBone->Update(_currentTick);
   }
   _locker.Leave();
   return ESuccess;
}

MO_NAMESPACE_END
