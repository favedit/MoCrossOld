#include "MoCrDevice.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FDeviceConsole, FConsole);

//============================================================
// <T>构造设备控制台。</T>
//============================================================
FDeviceConsole::FDeviceConsole(){
   _statusPause = EFalse;
}

//============================================================
// <T>析构设备控制台。</T>
//============================================================
FDeviceConsole::~FDeviceConsole(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FDeviceConsole::Setup(){
   return ESuccess;
}

//============================================================
// <T>根据类型查找设备。</T>
//
// @param typeCd 类型
// @return 设备
//============================================================
FDevice* FDeviceConsole::Find(FClass* pClass){
   TInt count = _devices.Count();
   for(TInt n = 0; n < count; n++){
      FDevice* pDevice = _devices.Get(n);
      FClass* pFindClass = pDevice->GetClass();
      while(pFindClass != NULL){
         if(pFindClass == pClass){
            return pDevice;
         }
         pFindClass = pFindClass->ParentClass();
      }
   }
   return NULL;
}

//============================================================
// <T>注册一个设备。</T>
//
// @param pClass 设备类对象
// @param pInheritClass 继承类对象
// @return 处理结果
//============================================================
TResult FDeviceConsole::Register(FClass* pClass, FClass* pInheritClass){
   MO_CHECK(pClass, return ENull);
   // 根据类型设置设备
   FDevice* pDevice = pClass->InstanceInheritCreate<FDevice>();
   pDevice->SetInheritClass(pInheritClass);
   // 放入设备集合
   _devices.Push(pDevice);
   return ESuccess;
}

//============================================================
// <T>注册一个设备。</T>
//
// @param pClass 设备类对象
// @param pDevice 设备对象
// @return 处理结果
//============================================================
TResult FDeviceConsole::Register(FDevice* pDevice){
   MO_CHECK(pDevice, return ENull);
   // 放入设备集合
   _devices.Push(pDevice);
   return ESuccess;
}

//============================================================
// <T>注销一个设备。</T>
//
// @param pDevice 设备
// @return 注销的设备
//============================================================
FDevice* FDeviceConsole::Unregister(FClass* pClass){
   MO_CHECK(pClass, return NULL);
   // 移除设备
   FDevice* pDevice = Find(pClass);
   if(pDevice != NULL){
      _devices.Remove(pDevice);
   }
   return pDevice;
}

//============================================================
// <T>暂停处理。</T>
//
// @return 处理结果
//============================================================
TResult FDeviceConsole::Suspend(){
   MO_INFO(TC("Device suspend."));
   // 挂起处理
   _statusPause = ETrue;
   // 所有设备暂停
   TInt count = _devices.Count();
   for(TInt n = 0; n < count; n++){
      FDevice* pDevice = _devices.Get(n);
      pDevice->Suspend();
   }
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FDeviceConsole::Resume(){
   MO_INFO(TC("Device resume."));
   // 所有设备恢复
   TInt count = _devices.Count();
   for(TInt n = 0; n < count; n++){
      FDevice* pDevice = _devices.Get(n);
      pDevice->Resume();
   }
   // 恢复处理
   _statusPause = EFalse;
   return ESuccess;
}

//============================================================
// <T>暂停处理。</T>
//
// @param pause 暂停
// @return 处理结果
//============================================================
TResult FDeviceConsole::Pause(TBool pause){
   if(pause){
      Suspend();
   }else{
      Resume();
   }
   return ESuccess;
}

//============================================================
// <T>暂停反向处理。</T>
//
// @return 处理结果
//============================================================
TResult FDeviceConsole::PauseInvert(){
   Pause(!_statusPause);
   return ESuccess;
}

MO_NAMESPACE_END
