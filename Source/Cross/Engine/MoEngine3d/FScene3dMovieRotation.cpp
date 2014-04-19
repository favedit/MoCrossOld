#include "MoE3Scene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FScene3dMovieRotation, FInstance);

//============================================================
// <T>构造动画。</T>
//============================================================
FScene3dMovieRotation::FScene3dMovieRotation(){
   _lastTick = 0;
}

//============================================================
// <T>析构动画。</T>
//============================================================
FScene3dMovieRotation::~FScene3dMovieRotation(){
}

//============================================================
// <T>加载资源。</T>
//
// @param pResource 资源对象
// @return 处理结果
//============================================================
TResult FScene3dMovieRotation::LoadResource(FRs3dSceneMovie* pResource){
   TResult resultCd = FScene3dMovie::LoadResource(pResource);
   _rotation.x = pResource->Rotation().x * MO_PI_FLOAT / 180.0f;
   _rotation.y = pResource->Rotation().y * MO_PI_FLOAT / 180.0f;
   _rotation.z = pResource->Rotation().z * MO_PI_FLOAT / 180.0f;
   return resultCd;
}

//============================================================
// <T>处理动画。</T>
//
// @param matrix 矩阵
// @return 处理结果
//============================================================
TResult FScene3dMovieRotation::Process(SFloatMatrix3d& matrix){
   FTimerDevice* pTimerDevice = RDeviceManager::Instance().Find<FTimerDevice>();
   if(_lastTick == 0){
      _lastTick = pTimerDevice->CurrentTick();
   }
   TTimeTick currentTick = pTimerDevice->CurrentTick();
   TTimeSpan span = currentTick - _lastTick;
   if(span > _interval){
      // 数据处理
      SFloatMatrix3d rotationMatrix;
      rotationMatrix.SetRotation(_rotation);
      rotationMatrix.UpdateForce();
      matrix.Append(rotationMatrix);
      _lastTick = currentTick;
   }
   return ESuccess;
}

MO_NAMESPACE_END
