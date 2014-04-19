#include "MoE3Display.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造实体3D动画。</T>
//============================================================
FAnimation3dThread::FAnimation3dThread(){
   _looperLimit = 256;
   _pLooper = MO_CREATE(FAnimation3dLooper);
}

//============================================================
// <T>析构实体3D动画。</T>
//============================================================
FAnimation3dThread::~FAnimation3dThread(){
   MO_DELETE(_pLooper);
}

//============================================================
// <T>根据名称获得材质实例。</T>
//
// @param pName 名称
// @return 材质实例
//============================================================
TResult FAnimation3dThread::Register(FAnimation3d* pAnimation){
   _locker.Enter();
   _pLooper->Push(pAnimation);
   _locker.Leave();
   return ESuccess;
}

//============================================================
// <T>根据名称获得材质实例。</T>
//
// @param pName 名称
// @return 材质实例
//============================================================
TResult FAnimation3dThread::Unregister(FAnimation3d* pAnimation){
   _locker.Enter();
   _pLooper->Remove(pAnimation);
   _locker.Leave();
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FAnimation3dThread::Process(){
   // MO_INFO("Process looper limit. (limit=%d, count=%d)", _looperLimit, _pLooper->Count());
   while(!IsStop()){
      // 执行处理
      _locker.Enter();
      _pLooper->Record();
      for(TInt n = 0; n < _looperLimit; n++){
         FAnimation3d* pAnimation = _pLooper->Next();
         if(pAnimation == NULL){
            break;
         }
         if(!IsStop()){
            pAnimation->Process();
         }
      }
      _locker.Leave();
      // 休息处理
      SleepMicro(10);
   }
   return ESuccess;
}

//============================================================
// <T>清空处理。</T>
//
// @return 处理结果
//============================================================
TResult FAnimation3dThread::Clear(){
   _locker.Enter();
   _pLooper->Clear();
   _locker.Leave();
   return ESuccess;
}

MO_NAMESPACE_END
