#include "MoEgDisplay.h"
#include "MoEgEngine.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造舞台控制台。</T>
//============================================================
FEngineConsole::FEngineConsole(){
   _pRegion = MO_CREATE(FRenderRegion);
}

//============================================================
// <T>析构舞台控制台。</T>
//============================================================
FEngineConsole::~FEngineConsole(){
   MO_DELETE(_pRegion);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FEngineConsole::Setup(){
   // 构造统计信息
   _statistics = FEngineStatistics::InstanceCreate();
   _statistics->Setup();
   // 注册屏幕变更大小事件
   FScreenDevice* pScreen = RDeviceManager::Instance().Find<FScreenDevice>();
   pScreen->ListenersResize().Register<FEngineConsole>(this, &FEngineConsole::OnResize);
   // 获得场景渲染器
   //_stageEffect = REffectManager::Instance().Find<FStageEffect>(TC("stage"));
   // 创建渲染对象
   _renderRectangle = FRenderRectangle::InstanceCreate();
   _renderRectangle->Setup();
   return ESuccess;
}

//============================================================
// <T>变更大小处理。</T>
//
// @param pEvent 事件
// @return 处理结果
//============================================================
TResult FEngineConsole::OnResize(SScreenResizeEvent* pEvent){
   TInt width = pEvent->size.width;
   TInt height = pEvent->size.height;
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   pRenderDevice->SetBackBuffer(width, height, 1);
   return ESuccess;
}

//============================================================
// <T>逻辑层处理。</T>
//
// @param pLayer 层对象
// @return 处理结果
//============================================================
TResult FEngineConsole::ProcessLayer(FDisplayLayer* pLayer){
   MO_CHECK(pLayer, return ENull);
   // 更新层矩阵
   SDrawableContext context;
   pLayer->UpdateAllMatrix(&context);
   //............................................................
   // 获得显示集合
   pLayer->FilterRegion(_pRegion);
   if(!_pRegion->TestRenderable()){
      return EContinue;
   }
   //............................................................
   // 设置可见区域
   FVisualRegion* pVisualRegion = pLayer->VisualRegion();
   _pRegion->SetVisualRegion(pVisualRegion);
   // 管道绘制
   FPipelinePass* pPass = _pRegion->ActivePass();
   pPass->DrawRegion(_pRegion);
   return ESuccess;
}

//============================================================
// <T>帧层处理。</T>
//
// @param pFrame 舞台层
// @return 处理结果
//============================================================
TResult FEngineConsole::ProcessFrame(FStageLayer* pFrame){
   MO_ASSERT(pFrame);
   // 获得设备
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   MO_CHECK(pRenderDevice, return ENull);
   //............................................................
   // 建立渲染区域
   pFrame->BuildRegion(_pRegion);
   //............................................................
   // 获得渲染通道
   FPipeline* pPipeline = RPipelineManager::Instance().ActivePipeline();
   _pRegion->SetActivePipeline(pPipeline);
   // 渲染过程集合处理
   FPipelinePassCollection* pPasses = pPipeline->Passes();
   TInt passCount = pPasses->Count();
   TInt passLast = passCount - 1;
   for(TInt n = 0; n < passCount; n++){
      FPipelinePass* pPass = pPasses->Get(n);
      if(n == passLast){
         _pRegion->SetOptionRenderTarget(ETrue);
         // 设置渲染目标
         FRenderTarget* pRenderTarget = pFrame->RenderTarget();
         pRenderDevice->SetRenderTarget(pRenderTarget);
         SFloatColor4 background = pRenderTarget ? pRenderTarget->BackgroundColor() : pFrame->BackgroundColor();
         pRenderDevice->Clear(background.red, background.green, background.blue, background.alpha);
      }else{
         _pRegion->SetOptionRenderTarget(EFalse);
      }
      _pRegion->SetActivePass(pPass);
      //............................................................
      // 处理所有层
      pPass->DrawBegin(_pRegion);
      FDisplayLayerCollection::TIteratorC layerIterator = pFrame->Layers()->IteratorC();
      while(layerIterator.Next()){
         FDisplayLayer* pLayer = *layerIterator;
         TResult layerResult = ProcessLayer(pLayer);
         if(layerResult == EContinue){
            continue;
         }else if(layerResult != ESuccess){
            return layerResult;
         }
      }
      pPass->DrawEnd(_pRegion);
   }
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FEngineConsole::Process(){
   // 获得统计器
   FStatistics* pFrameStatistics = _statistics->FrameStatistics();
   FStatistics* pFrameProcessBeforeStatistics = _statistics->FrameProcessBeforeStatistics();
   FStatistics* pFrameDrawStatistics = _statistics->FrameDrawStatistics();
   FStatistics* pFrameProcessAfterStatistics = _statistics->FrameProcessAfterStatistics();
   //............................................................
   // 检查激活的舞台
   FStage* pStage = RStageManager::Instance().ActiveStage();
   if(pStage == NULL){
      return EContinue;
   }
   pFrameStatistics->Begin();
   pStage->BuildRegion(_pRegion);
   GStageLayerPtrs& layers = pStage->Layers();
   //............................................................
   // 更新时间
   FTimerDevice* pTimerDevice = RDeviceManager::Instance().Find<FTimerDevice>();
   pTimerDevice->Update();
   //............................................................
   // 更新处理
   SProcessContext processContext;
   processContext.currentTick = pTimerDevice->CurrentTick();
   pStage->Update(&processContext);
   //............................................................
   // 处理帧进入
   SFrameEvent enterEvent(this);
   pFrameProcessBeforeStatistics->Begin();
   _listenersFrameEnter.Process(&enterEvent);
   pStage->ProcessBefore(&processContext);
   pFrameProcessBeforeStatistics->Finish();
   // 处理帧输入
   pStage->ProcessInput();
   // 处理帧逻辑
   SFrameEvent logicEvent(this);
   _listenersFrameLogic.Process(&logicEvent);
   pStage->ProcessLogic();
   //............................................................
   // 获得设备
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   MO_CHECK(pRenderDevice, return ENull);
   // 帧处理开始
   pRenderDevice->FrameBegin();
   // 绘制帧集合
   pFrameDrawStatistics->Begin();
   // 获得视角集合
   FRenderViewCollection::TIteratorC viewIterator = _pRegion->Views()->IteratorC();
   while(viewIterator.Next()){
      // 设置区域信息
      FRenderView* pView = *viewIterator;
      _pRegion->SetActiveView(pView);
      // 渲染过程集合处理
      GStageLayerPtrs::TIteratorC layerIterator = layers.IteratorC();
      while(layerIterator.Next()){
         FStageLayer* pFrame = *layerIterator;
         ProcessFrame(pFrame);
      }
   }
   // 显示内容
   pRenderDevice->Present();
   pFrameDrawStatistics->Finish();
   //............................................................
   // 帧处理结束
   pRenderDevice->FrameEnd();
   //............................................................
   // 处理帧离开
   SFrameEvent leaveEvent(this);
   pFrameProcessAfterStatistics->Begin();
   pStage->ProcessAfter(&processContext);
   _listenersFrameLeave.Process(&leaveEvent);
   pFrameProcessAfterStatistics->Finish();
   pFrameStatistics->Finish();
   return ESuccess;
}

MO_NAMESPACE_END
