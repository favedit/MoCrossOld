#ifndef __MO_CR_DEVICE_H__
#define __MO_CR_DEVICE_H__
//************************************************************

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>设备。</T>
//============================================================
class MO_CR_DECLARE FDevice : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FDevice, FInstance);
protected:
   TString _name;
   FClass* _pInheritClass;
public:
   FDevice();
   MO_ABSTRACT ~FDevice();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>获得继承类。</T>
   MO_INLINE FClass* InheritClass(){
      return _pInheritClass;
   }
   //------------------------------------------------------------
   // <T>设置继承类。</T>
   MO_INLINE void SetInheritClass(FClass* pInheritClass){
      _pInheritClass = pInheritClass;
   }
public:
   MO_ABSTRACT TResult Setup();
   MO_ABSTRACT TResult Suspend();
   MO_ABSTRACT TResult Resume();
};
//------------------------------------------------------------
typedef MO_CR_DECLARE GPtrs<FDevice> GDevicePtrs;

//============================================================
// <T>时间设备。</T>
//============================================================
class MO_CR_DECLARE FTimerDevice : public FDevice
{
   MO_CLASS_DECLARE_INHERITS(FTimerDevice, FDevice);
protected:
   TInt _frameCount;
   TTimeTick _startTick;
   TTimeTick _currentTick;
public:
   FTimerDevice();
   MO_ABSTRACT ~FTimerDevice();
public:
   //------------------------------------------------------------
   // <T>获得开始时刻。</T>
   MO_INLINE TTimeTick StartTick(){
      return _startTick;
   }
   //------------------------------------------------------------
   // <T>获得当前时刻。</T>
   MO_INLINE TTimeTick CurrentTick(){
      return _currentTick;
   }
   //------------------------------------------------------------
   // <T>获得间隔。</T>
   MO_INLINE TInt SpanTick(TTimeTick tick){
      return (TInt)(_currentTick - tick);
   }
   //------------------------------------------------------------
   // <T>获得间隔秒。</T>
   MO_INLINE TFloat SpanSecond(TTimeTick tick){
      return (TFloat)(_currentTick - tick) / 1000000.0f;
   }
public:
   MO_ABSTRACT TInt FramePerSecond();
public:
   MO_ABSTRACT TResult Setup();
   MO_ABSTRACT TResult Update();
};

//============================================================
// <T>设备控制台。</T>
//============================================================
class MO_CR_DECLARE FDeviceConsole : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FDeviceConsole, FConsole);
protected:
   // 状态暂停
   TBool _statusPause;
   // 设备集合
   GDevicePtrs _devices;
public:
   FDeviceConsole();
   MO_ABSTRACT ~FDeviceConsole();
public:
   //------------------------------------------------------------
   // <T>获得暂停状态。</T>
   MO_INLINE TBool StatusPause(){
      return _statusPause;
   }
   //------------------------------------------------------------
   // <T>获得设备集合。</T>
   MO_INLINE GDevicePtrs& Devices(){
      return _devices;
   }
public:
   MO_ABSTRACT TResult Setup();
public:
   FDevice* Find(FClass* pClass);
   //------------------------------------------------------------
   // <T>根据类型查找设备。</T>
   template <class T>
   T* Find(){
      return (T*)this->Find(T::Class());
   }
public:
   TResult Register(FClass* pClass, FClass* pInheritClass = NULL);
   TResult Register(FDevice* pDevice);
   FDevice* Unregister(FClass* pClass);
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_ABSTRACT TResult Pause(TBool pause);
   MO_ABSTRACT TResult PauseInvert();
};

//============================================================
// <T>设备管理器。</T>
//============================================================
class MO_CR_DECLARE RDeviceManager : public RSingleton<FDeviceConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_CR_DEVICE_H__
