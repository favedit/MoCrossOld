#include "MoEgDevice.h"
#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造跟踪控制器。</T>
//============================================================
FTailController::FTailController(){
   _limit = 16;
   _interval = 0;
   _lastTick = 0;
}

//============================================================
// <T>析构跟踪控制器。</T>
//============================================================
FTailController::~FTailController(){
}

//============================================================
// <T>增加一个跟踪信息。</T>
//
// @param info 跟踪信息
// @return 处理结果
//============================================================
TResult FTailController::Push(STailInfo& info){
   // 检查时间间隔
   FTimerDevice* pTimerDevice = RDeviceManager::Instance().Find<FTimerDevice>();
   TTimeTick currentTick = pTimerDevice->CurrentTick();
   if(_lastTick == 0){
      _lastTick = currentTick;
   }else{
      if(currentTick - _lastTick < _interval){
         return ESuccess;
      }
      _lastTick = currentTick;
   }
   // 检查是否已经到达极限
   TInt count = _infos.Count();
   if(count > _limit){
      _infos.Pop();
   }
   // 从开始位置插入
   _infos.Unshift(info);
   return ESuccess;
}

//============================================================
// <T>清空跟踪集合。</T>
//
// @return 处理结果
//============================================================
TResult FTailController::Reset(){
   _lastTick = 0;
   _infos.Clear();
   return ESuccess;
}

MO_NAMESPACE_END
