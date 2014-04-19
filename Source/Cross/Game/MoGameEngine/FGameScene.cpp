#include "MoGeDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FGameScene, FScene3d);

//============================================================
// <T>构造实体3D模型。</T>
//============================================================
FGameScene::FGameScene(){
}

//============================================================
// <T>析构实体3D模型。</T>
//============================================================
FGameScene::~FGameScene(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FGameScene::Setup(){
   TResult resultCd = FScene3d::Setup();
   return resultCd;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FGameScene::ProcessInput(){
   // 检查激活视角
   if(!_activeView){
      return EContinue;
   }
   FCamera* pCamera = _activeView->Camera();
   MO_CHECK(pCamera, return ENull);
   // 键盘设备
   FKeyboardDevice* pKeyboardDevice = RDeviceManager::Instance().Find<FKeyboardDevice>();
   TFloat rateMove = 1.0f;
   TFloat angleRate = 0.015f;
   // Forward
   if(pKeyboardDevice->TestKeyDown(EKeyCode_W)){
      pCamera->DoWalk(rateMove);
   }
   // Back
   if(pKeyboardDevice->TestKeyDown(EKeyCode_S)){
      pCamera->DoWalk(-rateMove);
   }
   // Rotation Left
   if(pKeyboardDevice->TestKeyDown(EKeyCode_A)){
      pCamera->DoYaw(angleRate);
   }
   // Rotation Right
   if(pKeyboardDevice->TestKeyDown(EKeyCode_D)){
      pCamera->DoYaw(-angleRate);
   }
   // Down
   if(pKeyboardDevice->TestKeyDown(EKeyCode_Q)){
      pCamera->DoFly(-rateMove);
   }
   // Up
   if(pKeyboardDevice->TestKeyDown(EKeyCode_E)){
      pCamera->DoFly(rateMove);
   }
   // Rotation Down
   if(pKeyboardDevice->TestKeyDown(EKeyCode_Z)){
      pCamera->DoPitch(-rateMove);
   }
   // Rotation Up
   if(pKeyboardDevice->TestKeyDown(EKeyCode_W)){
      pCamera->DoPitch(rateMove);
   }
   pCamera->Update();
   return ESuccess;
}

MO_NAMESPACE_END
