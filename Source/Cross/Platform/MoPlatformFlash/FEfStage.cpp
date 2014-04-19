#include "MoEfDevice.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造舞台对象。</T>
//============================================================
FEfStage::FEfStage(){
}

//============================================================
// <T>析构舞台对象。</T>
//============================================================
FEfStage::~FEfStage(){
}

//============================================================
// <T>配置处理。</T>
//============================================================
void FEfStage::Setup(){
   _pStage = internal::get_Stage();
   _pStage->scaleMode = flash::display::StageScaleMode::NO_SCALE;
   _pStage->align = flash::display::StageAlign::TOP_LEFT;
   _pStage->frameRate = 60;
}

MO_NAMESPACE_END
