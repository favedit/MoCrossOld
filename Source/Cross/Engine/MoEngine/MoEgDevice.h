#ifndef __MO_EG_DEVICE_H__
#define __MO_EG_DEVICE_H__
//************************************************************

#ifndef __MO_EG_COMMON_H__
#include "MoEgCommon.h"
#endif // __MO_EG_COMMON_H__

#define MO_KEYBOARD_COUNT 256

MO_NAMESPACE_BEGIN

//============================================================
// <T>屏幕设备。</T>
//============================================================
class MO_EG_DECLARE FScreenDevice : public FDevice
{
   MO_CLASS_DECLARE_INHERITS(FScreenDevice, FDevice);
protected:
   // 尺寸
   SIntSize2 _size;
   // 区域
   SIntRectangle _rectangle;
   // 改变大小监听器集合
   TResizeListeners _listenersResize;
public:
   FScreenDevice();
   MO_ABSTRACT ~FScreenDevice();
public:
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SIntSize2& Size(){
      return _size;
   }
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SIntRectangle& Rectangle(){
      return _rectangle;
   }
   //------------------------------------------------------------
   // <T>获得改变大小监听器集合。</T>
   MO_INLINE TResizeListeners& ListenersResize(){
      return _listenersResize;
   }
public:
   MO_ABSTRACT TResult Setup();
   MO_ABSTRACT TResult Resize(TInt width, TInt height);
};

//============================================================
// <T>时间设备。</T>
//============================================================
class MO_EG_DECLARE FTimerDevice : public FDevice
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

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EG_DEVICE_H__
