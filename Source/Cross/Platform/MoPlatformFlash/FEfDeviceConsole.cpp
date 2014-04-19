#include "MoEfRender.h"
#include "MoEfDevice.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造设备管理器。</T>
//============================================================
FEfDeviceConsole::FEfDeviceConsole(){
}

//============================================================
// <T>析构设备管理器。</T>
//============================================================
FEfDeviceConsole::~FEfDeviceConsole(){
}

//============================================================
// <T>配置处理。</T>
//============================================================
void FEfDeviceConsole::Setup(){
   // 创建键盘设备
   _pKeybord = MO_CREATE(FEfKeybord);
   // 创建舞台设备
   _pStage = MO_CREATE(FEfStage);
   _pStage->Setup();
   // 创建渲染设备
   _pRenderDevice = MO_CREATE(FEfRenderDevice);
   _pRenderDevice->Setup();
}

MO_NAMESPACE_END
