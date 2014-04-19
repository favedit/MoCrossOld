#ifndef __MO_FI_TOUCH_H__
#define __MO_FI_TOUCH_H__
//************************************************************

#ifndef __MO_FI_COMMON_H__
#include "MoFiCommon.h"
#endif // __MO_FI_COMMON_H__

#ifndef __MO_FI_INPUT_H__
#include "MoFiInput.h"
#endif // __MO_FI_INPUT_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>触摸事件定义。</T>
//============================================================
enum ETouchEvent{
   ETouchEvent_Down  = 0x01,
   ETouchEvent_Movie = 0x02,
   ETouchEvent_Up    = 0x03,
};

//============================================================
// <T>触摸事件。</T>
//============================================================
class STouchEvent : public SInputEvent{
public:
   ETouchEvent touchCd;
   SIntPoint2 position;
public:
   //------------------------------------------------------------
   // <T>构造鼠标事件。</T>
   STouchEvent(TAny* pSender, ETouchEvent touchCd, TInt x, TInt y) : SInputEvent(pSender){
      this->touchCd = touchCd;
      this->position.Set(x, y);
   }
};
//------------------------------------------------------------
typedef MO_FI_DECLARE TListeners<STouchEvent> TTouchListeners;

//============================================================
// <T>触摸设备。</T>
//============================================================
class MO_FI_DECLARE FTouchDevice : public FDevice
{
   MO_CLASS_DECLARE_INHERITS(FTouchDevice, FDevice);
protected:
   TTouchListeners _listenersTouchDown;
   TTouchListeners _listenersTouchMove;
   TTouchListeners _listenersTouchUp;
public:
   FTouchDevice();
   MO_ABSTRACT ~FTouchDevice();
public:
   //------------------------------------------------------------
   // <T>获得鼠标落下监听器集合。</T>
   MO_INLINE TTouchListeners& ListenersTouchDown(){
      return _listenersTouchDown;
   }
   //------------------------------------------------------------
   // <T>获得鼠标移动监听器集合。</T>
   MO_INLINE TTouchListeners& ListenersTouchMove(){
      return _listenersTouchMove;
   }
   //------------------------------------------------------------
   // <T>获得鼠标抬起监听器集合。</T>
   MO_INLINE TTouchListeners& ListenersTouchUp(){
      return _listenersTouchUp;
   }
public:
   MO_ABSTRACT TResult Setup();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FI_TOUCH_H__
