#ifndef __MO_FI_MOUSE_H__
#define __MO_FI_MOUSE_H__
//************************************************************

#ifndef __MO_FI_COMMON_H__
#include "MoFiCommon.h"
#endif // __MO_FI_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>鼠标按键定义。</T>
//============================================================
enum EMouseButton{
   EMouseButton_Left = 0x01,
   EMouseButton_Middle = 0x02,
   EMouseButton_Right = 0x04,
};
//------------------------------------------------------------
typedef TInt TMouseButtons;

//============================================================
// <T>鼠标事件。</T>
//============================================================
class SMouseEvent : public SEvent{
public:
   TMouseButtons buttons;
   SIntPoint2 position;
public:
   //------------------------------------------------------------
   // <T>构造鼠标事件。</T>
   SMouseEvent(TAny* pSender, TMouseButtons buttons, TInt x, TInt y) : SEvent(pSender){
      this->buttons = buttons;
      this->position.Set(x, y);
   }
};
//------------------------------------------------------------
typedef MO_FI_DECLARE TListeners<SMouseEvent> TMouseListeners;

//============================================================
// <T>鼠标设备。</T>
//============================================================
class MO_FI_DECLARE FMouseDevice : public FDevice
{
   MO_CLASS_DECLARE_INHERITS(FMouseDevice, FDevice);
protected:
   TBool _buttonLeftDown;
   TBool _buttonMiddleDown;
   TBool _buttonRightDown;
   SIntPoint2 _positionDown;
   SIntPoint2 _position;
   TMouseListeners _listenersClick;
   TMouseListeners _listenersDoubleClick;
   TMouseListeners _listenersMouseDown;
   TMouseListeners _listenersMouseMove;
   TMouseListeners _listenersMouseUp;
public:
   FMouseDevice();
   MO_ABSTRACT ~FMouseDevice();
public:
   //------------------------------------------------------------
   // <T>获得坐标。</T>
   MO_INLINE SIntPoint2& Position(){
      return _position;
   }
   //------------------------------------------------------------
   // <T>获得单击监听器集合。</T>
   MO_INLINE TMouseListeners& ListenersClick(){
      return _listenersClick;
   }
   //------------------------------------------------------------
   // <T>获得双击监听器集合。</T>
   MO_INLINE TMouseListeners& ListenersDoubleClick(){
      return _listenersDoubleClick;
   }
   //------------------------------------------------------------
   // <T>获得鼠标落下监听器集合。</T>
   MO_INLINE TMouseListeners& ListenersMouseDown(){
      return _listenersMouseDown;
   }
   //------------------------------------------------------------
   // <T>获得鼠标移动监听器集合。</T>
   MO_INLINE TMouseListeners& ListenersMouseMove(){
      return _listenersMouseMove;
   }
   //------------------------------------------------------------
   // <T>获得鼠标抬起监听器集合。</T>
   MO_INLINE TMouseListeners& ListenersMouseUp(){
      return _listenersMouseUp;
   }
   //------------------------------------------------------------
   // <T>获得鼠标落下后移动信息。</T>
   MO_INLINE SIntPoint2 CalculateMove(){
      return SIntPoint2(_position.x - _positionDown.x, _position.y - _positionDown.y);
   }
public:
   MO_ABSTRACT TResult Setup();
public:
   TBool TestMouseDownLeft();
   TBool TestMouseDownMiddle();
   TBool TestMouseDownRight();
   TBool TestMouseDown(TMouseButtons buttons);
public:
   MO_ABSTRACT void DoClick(TMouseButtons buttons, TInt x, TInt y);
   MO_ABSTRACT void DoDoubleClick(TMouseButtons buttons, TInt x, TInt y);
   MO_ABSTRACT void DoMouseDown(TMouseButtons buttons, TInt x, TInt y);
   MO_ABSTRACT void DoMouseMove(TMouseButtons buttons, TInt x, TInt y);
   MO_ABSTRACT void DoMouseUp(TMouseButtons buttons, TInt x, TInt y);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FI_MOUSE_H__
