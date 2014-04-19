#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FStageFrame, FInstance);

//============================================================
// <T>构造舞台对象。</T>
//============================================================
FStageFrame::FStageFrame(){
   _frameCd = EStageFrame_Unknown;
   _backgroundColor.Set(0.0f, 0.0f, 0.0f, 1.0f);
   _pLayers = MO_CREATE(FDisplayLayerCollection);
}

//============================================================
// <T>析构舞台对象。</T>
//============================================================
FStageFrame::~FStageFrame(){
   MO_DELETE(_pLayers);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageFrame::Setup(){
   return ESuccess;
}

//============================================================
// <T>查找指定类型的显示层。</T>
//
// @param layerCd 层类型
// @return 显示层
//============================================================
FDisplayLayer* FStageFrame::LayerFind(EDisplayLayer layerCd){
   TInt count = _pLayers->Count();
   for(TInt n = 0; n < count; n++){
      FDisplayLayer* pLayer = _pLayers->Get(n);
      if(pLayer->LayerCd() == layerCd){
         return pLayer;
      }
   }
   return NULL;
}

//============================================================
// <T>清空所有显示层。</T>
//============================================================
void FStageFrame::LayerClear(){
   _pLayers->Clear();
}

//============================================================
// <T>增加一个显示层。</T>
//
// @param pLayer 显示层
//============================================================
void FStageFrame::LayerPush(FDisplayLayer* pLayer){
   MO_ASSERT(pLayer);
   pLayer->SetStageFrame(this);
   _pLayers->Push(pLayer);
}

//============================================================
// <T>移除一个层。</T>
//
// @param pLayer 显示层
//============================================================
void FStageFrame::LayerRemove(FDisplayLayer* pLayer){
   MO_ASSERT(pLayer);
   _pLayers->Remove(pLayer);
   pLayer->SetStageFrame(NULL);
}

//============================================================
// <T>焦点测试处理。</T>
//
// @param pTester 测试信息
// @return 处理结果
//============================================================
TResult FStageFrame::FocusTest(FFocusTester* pTester){
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
TResult FStageFrame::Active(){
   return ESuccess;
}

//============================================================
// <T>取消激活处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageFrame::Deactive(){
   return ESuccess;
}

//============================================================
// <T>建立渲染区域。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FStageFrame::BuildRegion(FRenderRegion* pRegion){
   MO_ASSERT(pRegion);
   return ESuccess;
}

//============================================================
// <T>功能前置处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageFrame::ProcessBefore(SProcessContext* pContext){
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
TResult FStageFrame::ProcessInput(){
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageFrame::ProcessLogic(){
   return ESuccess;
}

//============================================================
// <T>功能后置处理。</T>
//
// @return 处理结果
//============================================================
TResult FStageFrame::ProcessAfter(SProcessContext* pContext){
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
TResult FStageFrame::Suspend(){
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
TResult FStageFrame::Resume(){
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
TResult FStageFrame::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
