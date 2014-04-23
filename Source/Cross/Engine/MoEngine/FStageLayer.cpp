#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FStageLayer, FInstance);

//============================================================
// <T>构造舞台对象。</T>
//============================================================
FStageLayer::FStageLayer(){
   _backgroundColor.Set(0.0f, 0.0f, 0.0f, 1.0f);
   _pLayers = MO_CREATE(FDisplayLayerCollection);
}

//============================================================
// <T>析构舞台对象。</T>
//============================================================
FStageLayer::~FStageLayer(){
   MO_DELETE(_pLayers);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageLayer::Setup(){
   return ESuccess;
}

//============================================================
// <T>查找指定类型的显示层。</T>
//
// @param layerCd 层类型
// @return 显示层
//============================================================
FDisplayLayer* FStageLayer::LayerFind(TCharC* pName){
   TInt count = _pLayers->Count();
   for(TInt n = 0; n < count; n++){
      FDisplayLayer* pLayer = _pLayers->Get(n);
      if(RString::Equals(pLayer->Name(), pName)){
         return pLayer;
      }
   }
   return NULL;
}

//============================================================
// <T>增加一个显示层。</T>
//
// @param pLayer 显示层
// @return 处理结果
//============================================================
TResult FStageLayer::LayerPush(FDisplayLayer* pLayer){
   MO_CHECK(pLayer, return ENull);
   pLayer->SetStageLayer(this);
   _pLayers->Push(pLayer);
   return ESuccess;
}

//============================================================
// <T>移除一个层。</T>
//
// @param pLayer 显示层
// @return 处理结果
//============================================================
TResult FStageLayer::LayerRemove(FDisplayLayer* pLayer){
   MO_CHECK(pLayer, return ENull);
   _pLayers->Remove(pLayer);
   pLayer->SetStageLayer(NULL);
   return ESuccess;
}

//============================================================
// <T>清空所有显示层。</T>
//
// @return 处理结果
//============================================================
TResult FStageLayer::LayerClear(){
   _pLayers->Clear();
   return ESuccess;
}

//============================================================
// <T>焦点测试处理。</T>
//
// @param pTester 测试信息
// @return 处理结果
//============================================================
TResult FStageLayer::FocusTest(FFocusTester* pTester){
   TInt layerCount = _pLayers->Count();
   for(TInt n = 0; n < layerCount; n++){
      FDisplayLayer* pLayer = _pLayers->Get(n);
      pLayer->FocusTest(pTester);
   }
   return ESuccess;
}

//============================================================
// <T>激活处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageLayer::Active(){
   return ESuccess;
}

//============================================================
// <T>取消激活处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageLayer::Deactive(){
   return ESuccess;
}

//============================================================
// <T>建立渲染区域。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FStageLayer::BuildRegion(FRenderRegion* pRegion){
   MO_ASSERT(pRegion);
   return ESuccess;
}

//============================================================
// <T>更新处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageLayer::Update(SProcessContext* pContext){
   TInt layerCount = _pLayers->Count();
   for(TInt n = 0; n < layerCount; n++){
      FDisplayLayer* pLayer = _pLayers->Get(n);
      pLayer->Update(pContext);
   }
   return ESuccess;
}

//============================================================
// <T>功能前置处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageLayer::ProcessBefore(SProcessContext* pContext){
   TInt layerCount = _pLayers->Count();
   for(TInt n = 0; n < layerCount; n++){
      FDisplayLayer* pLayer = _pLayers->Get(n);
      pLayer->ProcessBefore(pContext);
   }
   return ESuccess;
}

//============================================================
// <T>输入处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageLayer::ProcessInput(){
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageLayer::ProcessLogic(){
   return ESuccess;
}

//============================================================
// <T>功能后置处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageLayer::ProcessAfter(SProcessContext* pContext){
   TInt layerCount = _pLayers->Count();
   for(TInt n = 0; n < layerCount; n++){
      FDisplayLayer* pLayer = _pLayers->Get(n);
      pLayer->ProcessAfter(pContext);
   }
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageLayer::Suspend(){
   TInt layerCount = _pLayers->Count();
   for(TInt n = 0; n < layerCount; n++){
      FDisplayLayer* pLayer = _pLayers->Get(n);
      pLayer->Suspend();
   }
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageLayer::Resume(){
   TInt layerCount = _pLayers->Count();
   for(TInt n = 0; n < layerCount; n++){
      FDisplayLayer* pLayer = _pLayers->Get(n);
      pLayer->Resume();
   }
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageLayer::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
