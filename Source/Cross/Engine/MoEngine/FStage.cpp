#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FStage, FInstance);

//============================================================
// <T>构造舞台。</T>
//============================================================
FStage::FStage(){
   _backgroundColor.Set(0.0f, 0.0f, 0.0f, 1.0f);
   _pViews = MO_CREATE(FRenderViewCollection);
   _pLights = MO_CREATE(FLightCollection);
   _pFrames = MO_CREATE(FStageFrameCollection);
}

//============================================================
// <T>析构舞台。</T>
//============================================================
FStage::~FStage(){
   MO_DELETE(_pViews);
   MO_DELETE(_pLights);
   MO_DELETE(_pFrames);
}

//============================================================
// <T>查找指定类型的舞台帧。</T>
//
// @param frameCd 帧类型
// @return 舞台帧
//============================================================
FStageFrame* FStage::FrameFind(EStageFrame frameCd){
   TInt count = _pFrames->Count();
   for(TInt n = 0; n < count; n++){
      FStageFrame* pFrame = _pFrames->Get(n);
      if(pFrame->FrameCd() == frameCd){
         return pFrame;
      }
   }
   return NULL;
}

//============================================================
// <T>清空所有舞台帧。</T>
//============================================================
void FStage::FrameClear(){
   _pFrames->Clear();
}

//============================================================
// <T>增加一个舞台帧。</T>
//
// @param pFrame 舞台帧
//============================================================
void FStage::FramePush(FStageFrame* pFrame){
   MO_ASSERT(pFrame);
   pFrame->SetStage(this);
   _pFrames->Push(pFrame);
}

//============================================================
// <T>移除一个舞台帧。</T>
//
// @param pFrame 舞台帧
//============================================================
void FStage::FrameRemove(FStageFrame* pFrame){
   MO_ASSERT(pFrame);
   _pFrames->Remove(pFrame);
   pFrame->SetStage(NULL);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FStage::Setup(){
   return ESuccess;
}

//============================================================
// <T>激活处理。</T>
//
// @return 处理结果
//============================================================
TResult FStage::Active(){
   TInt count = _pFrames->Count();
   for(TInt n = 0; n < count; n++){
      FStageFrame* pFrame = _pFrames->Get(n);
      pFrame->Active();
   }
   return ESuccess;
}

//============================================================
// <T>取消激活处理。</T>
//
// @return 处理结果
//============================================================
TResult FStage::Deactive(){
   TInt count = _pFrames->Count();
   for(TInt n = 0; n < count; n++){
      FStageFrame* pFrame = _pFrames->Get(n);
      pFrame->Deactive();
   }
   return ESuccess;
}

//============================================================
// <T>建立渲染区域。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FStage::BuildRegion(FRenderRegion* pRegion){
   MO_ASSERT(pRegion);
   // 设置相机集合
   if(!_pViews->IsEmpty()){
      pRegion->Views()->AssignPointer(_pViews);
   }
   //............................................................
   // 设置光源集合
   pRegion->SetDirectionalLight(_directionalLight);
   if(!_pLights->IsEmpty()){
      pRegion->Lights()->AssignPointer(_pLights);
   }
   return ESuccess;
}

//============================================================
// <T>功能前置处理。</T>
//
// @return 处理结果
//============================================================
TResult FStage::ProcessBefore(SProcessContext* pContext){
   // 处理监听
   SFrameEvent event(this);
   _listenersFrameEnter.Process(&event);
   // 处理所有帧开始
   TInt frameCount = _pFrames->Count();
   for(TInt n = 0; n < frameCount; n++){
      FStageFrame* pFrame = _pFrames->Get(n);
      pFrame->ProcessBefore(pContext);
   }
   return ESuccess;
}

//============================================================
// <T>输入处理。</T>
//
// @return 处理结果
//============================================================
TResult FStage::ProcessInput(){
   TInt frameCount = _pFrames->Count();
   for(TInt n = 0; n < frameCount; n++){
      FStageFrame* pFrame = _pFrames->Get(n);
      pFrame->ProcessInput();
   }
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FStage::ProcessLogic(){
   TInt frameCount = _pFrames->Count();
   for(TInt n = 0; n < frameCount; n++){
      FStageFrame* pFrame = _pFrames->Get(n);
      pFrame->ProcessLogic();
   }
   return ESuccess;
}

//============================================================
// <T>功能后置处理。</T>
//
// @return 处理结果
//============================================================
TResult FStage::ProcessAfter(SProcessContext* pContext){
   // 处理监听
   SFrameEvent event(this);
   _listenersFrameLeave.Process(&event);
   // 处理所有帧结束
   TInt frameCount = _pFrames->Count();
   for(TInt n = 0; n < frameCount; n++){
      FStageFrame* pFrame = _pFrames->Get(n);
      pFrame->ProcessAfter(pContext);
   }
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FStage::Suspend(){
   TInt count = _pFrames->Count();
   for(TInt n = 0; n < count; n++){
      FStageFrame* pFrame = _pFrames->Get(n);
      pFrame->Suspend();
   }
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FStage::Resume(){
   TInt count = _pFrames->Count();
   for(TInt n = 0; n < count; n++){
      FStageFrame* pFrame = _pFrames->Get(n);
      pFrame->Resume();
   }
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FStage::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
