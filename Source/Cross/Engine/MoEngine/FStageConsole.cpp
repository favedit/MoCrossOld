#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造舞台控制台。</T>
//============================================================
FStageConsole::FStageConsole(){
   MO_CLEAR(_pActiveStage);
}

//============================================================
// <T>析构舞台控制台。</T>
//============================================================
FStageConsole::~FStageConsole(){
}

//============================================================
// <T>配置处理。</T>
//============================================================
void FStageConsole::Setup(){
   // 创建显示处理器
   _displayProcessor = FProcessor::InstanceCreate();
   _displayProcessor->Startup();
   RProcessorManager::Instance().Register(_displayProcessor);
   // 创建渲染处理器
   _renderableProcessor = FProcessor::InstanceCreate();
   _renderableProcessor->Startup();
   RProcessorManager::Instance().Register(_renderableProcessor);
}

//============================================================
TResult FStageConsole::Shutdown(){
   _displayProcessor->Shutdown();
   _renderableProcessor->Shutdown();
   return ESuccess;
}

//============================================================
// <T>选择一个舞台。</T>
// <P>舞台为空表示清除激活舞台。</P>
//
// @param pStage 舞台
// @return 处理结果
//============================================================
TResult FStageConsole::SelectStage(FStage* pStage){
   // 检测是否相等
   if(_pActiveStage == pStage){
      return EContinue;
   }
   // 取消激活舞台
   if(_pActiveStage != NULL){
      _pActiveStage->Deactive();
      _pActiveStage = NULL;
   }
   // 激活选定舞台
   if(pStage != NULL){
      pStage->Active();
   }
   _pActiveStage = pStage;
   return ESuccess;
}

MO_NAMESPACE_END
