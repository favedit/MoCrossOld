#include "MoFmRuntime.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造运行状态栏。</T>
//============================================================
FFmRuntimeBar::FFmRuntimeBar(){
   MO_CLEAR(_pInfoLabel);
   MO_CLEAR(_pWindowButton);
   _lastRefreshTick = 0;
}

//============================================================
// <T>析构运行状态栏。</T>
//============================================================
FFmRuntimeBar::~FFmRuntimeBar(){
}

//============================================================
// <T>弹出运行信息窗口。</T>
//
// @param pEvent 事件
// @return 处理结果
//============================================================
TResult FFmRuntimeBar::OnRuntimeWindowClick(SMouseEvent* pEvent){
   FUiFrame* pFrame = RFaceManager::Instance().FrameConsole()->Find(EFmRuntime_RuntimeWindow);
   if(pFrame->StatusVisible()){
      pFrame->Hide();
   }else{
      pFrame->Show();
   }
   return ESuccess;
}

//============================================================
// <T>配置后置处理。</T>
//
// @return 处理结果
//============================================================
TResult FFmRuntimeBar::OnSetupAfter(){
   TResult result = FUiBar::OnSetupAfter();
   //_pInfoLabel = (FUiLabel*)ChildGet("labInfo");
   //_pWindowButton = (FUiButton*)ChildGet("btnRuntimeWindow");
   //_pWindowButton->ListenersClick().Register<FFmRuntimeBar>(this, &FFmRuntimeBar::OnRuntimeWindowClick);
   return result;
}

//============================================================
// <T>逻辑前置处理。</T>
//
// @return 处理结果
//============================================================
TResult FFmRuntimeBar::OnProcessBefore(){
   //TResult result = FUiBar::OnProcessBefore();
   RefreshStatus();
   //return result;
   return ESuccess;
}

//============================================================
// <T>刷新状态。</T>
//
// @return 处理结果
//============================================================
TResult FFmRuntimeBar::RefreshStatus(){
   // 检查间隔时间
   //FTimerDevice* pTimerDevice = RDeviceManager::Instance().Find<FTimerDevice>();
   //TTimeTick currentTick = pTimerDevice->CurrentTick();
   //if(currentTick - _lastRefreshTick < 1000000){
   //   return EContinue;
   //}
   //// 获得舞台信息
   //FStage* pStage = RStageManager::Instance().ActiveStage();
   //FDisplayLayer* pSpriteLayer = pStage->LayerFind(EDisplayLayer_Sprite);
   //TInt spriteCount = 0;
   //TInt particleCount = 0;
   //if(pSpriteLayer != NULL){
   //   //spriteCount = pSpriteLayer->ChildCount();
   //   particleCount = pSpriteLayer->ParticleController()->ParticleCount();
   //}
   // 获得显卡统计信息
   //FRenderStatistics* pRenderStatistics = (FRenderStatistics*)RStatisticsManager::Instance().StatisticsFind(MoRenderStatisticsDevice);
   //TInt drawFaceCount = pRenderStatistics->DrawFaceCount();
   //TInt drawCount = pRenderStatistics->DrawCount();
   //TInt optionDeepCount = pRenderStatistics->OptionDeepCount();
   //TInt optionBlendCount = pRenderStatistics->OptionBlendCount();
   //TInt vertexBufferCount = pRenderStatistics->VertexBufferCount();
   //TInt programCount = pRenderStatistics->ProgramCount();
   //TInt samplerCount = pRenderStatistics->SamplerCount();
   //FTechnique* pTechnique = RTechniqueManager::Instance().FindTechnique(ERenderTechnique_2d);
   //FEffect* pEffect = pTechnique->FindFEffect(ERenderEffect_2dTexture);
   //// 生成内容字符串
   //TFsText info;
   //info.AppendFormat("Display: frame=%d, draw_count=%d, draw_face=%d, sprite=%d, particle=%d", pTimerDevice->FramePerSecond(), drawCount, drawFaceCount, spriteCount, particleCount);
   //info.AppendFormat("\nGPU: option=(D:%d,B:%d) program=(P:%d, V:%d, S:%d)", optionDeepCount, optionBlendCount, programCount, vertexBufferCount, samplerCount);
   ////info.AppendFormat(L"\nEntity=%d", pSpriteLayer->EntityCount());
   ////info.AppendFormat("\nTechnique: limit_count=%d, count=%d", pDisplayTextureTechnique->LimitCount(), pDisplayTextureTechnique->Count());
   ////FGmMapStage* pMapStage = (FGmMapStage*)RStageManager::Instance().ActiveStage();
   ////FGmMapSpriteLayer* pSpriteLayer = pMapStage->SpriteLayer();
   ////FDisplayTextureTechnique* pDisplayTextureTechnique = (FDisplayTextureTechnique*)RTechniqueManager::Instance().FindTechnique(ETechnique_DisplayTexture);
   //TWideFixString<MO_FS_TEXT_LENGTH> infoSource;
   //infoSource.Assign8(info);
   //// _pInfoLabel->Font().fix = ETrue;
   //_pInfoLabel->SetText(infoSource);
   //_lastRefreshTick = currentTick;
   return ESuccess;
}

//============================================================
// <T>显示处理。</T>
//
// @return 处理结果
//============================================================
TResult FFmRuntimeBar::Show(){
   // 设置尺寸
   //SIntSize2& size = RDeviceManager::Instance().Screen()->Size();
   //_size.width = size.width - 4;
   //_pInfoLabel->Size().width = size.width - 100;
   // 显示处理
   return FUiBar::Show();
}

MO_NAMESPACE_END
