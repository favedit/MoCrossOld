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
}

//============================================================
// <T>析构舞台。</T>
//============================================================
FStage::~FStage(){
   MO_DELETE(_pViews);
   MO_DELETE(_pLights);
}

//============================================================
// <T>查找指定名称的舞台层。</T>
//
// @param pName 名称
// @return 舞台层
//============================================================
FStageLayer* FStage::LayerFind(TCharC* pName){
   TInt count = _layers.Count();
   for(TInt n = 0; n < count; n++){
      FStageLayer* pFrame = _layers.Get(n);
      if(RString::Equals(pFrame->Name(), pName)){
         return pFrame;
      }
   }
   return NULL;
}

//============================================================
// <T>增加一个舞台层。</T>
//
// @param pLayer 舞台层
//============================================================
TResult FStage::LayerPush(FStageLayer* pLayer){
   MO_ASSERT(pLayer);
   pLayer->SetStage(this);
   _layers.Push(pLayer);
   return ESuccess;
}

//============================================================
// <T>移除一个舞台层。</T>
//
// @param pLayer 舞台层
// @return 处理结果
//============================================================
TResult FStage::LayerRemove(FStageLayer* pLayer){
   MO_ASSERT(pLayer);
   _layers.Remove(pLayer);
   pLayer->SetStage(NULL);
   return ESuccess;
}

//============================================================
// <T>清空舞台层集合。</T>
//
// @return 处理结果
//============================================================
TResult FStage::LayerClear(){
   _layers.Clear();
   return ESuccess;
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
   TInt count = _layers.Count();
   for(TInt n = 0; n < count; n++){
      FStageLayer* pFrame = _layers.Get(n);
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
   TInt count = _layers.Count();
   for(TInt n = 0; n < count; n++){
      FStageLayer* pFrame = _layers.Get(n);
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
      pRegion->Views()->Assign(_pViews);
   }
   //............................................................
   // 设置光源集合
   pRegion->SetDirectionalLight(_directionalLight);
   if(!_pLights->IsEmpty()){
      pRegion->Lights()->Assign(_pLights);
   }
   return ESuccess;
}

//============================================================
// <T>更新处理。</T>
//
// @return 处理结果
//============================================================
TResult FStage::Update(SProcessContext* pContext){
   TInt frameCount = _layers.Count();
   for(TInt n = 0; n < frameCount; n++){
      FStageLayer* pFrame = _layers.Get(n);
      pFrame->Update(pContext);
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
   TInt frameCount = _layers.Count();
   for(TInt n = 0; n < frameCount; n++){
      FStageLayer* pFrame = _layers.Get(n);
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
   TInt frameCount = _layers.Count();
   for(TInt n = 0; n < frameCount; n++){
      FStageLayer* pFrame = _layers.Get(n);
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
   TInt frameCount = _layers.Count();
   for(TInt n = 0; n < frameCount; n++){
      FStageLayer* pFrame = _layers.Get(n);
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
   TInt frameCount = _layers.Count();
   for(TInt n = 0; n < frameCount; n++){
      FStageLayer* pFrame = _layers.Get(n);
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
   TInt count = _layers.Count();
   for(TInt n = 0; n < count; n++){
      FStageLayer* pFrame = _layers.Get(n);
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
   TInt count = _layers.Count();
   for(TInt n = 0; n < count; n++){
      FStageLayer* pFrame = _layers.Get(n);
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
