#ifndef __MO_FI_KEYBOARD_H__
#define __MO_FI_KEYBOARD_H__
//************************************************************

#ifndef __MO_FI_COMMON_H__
#include "MoFiCommon.h"
#endif // __MO_FI_COMMON_H__

#define MO_KEYBOARD_COUNT 256

MO_NAMESPACE_BEGIN

//============================================================
// <T>键盘按键定义。</T>
//============================================================
enum EKeyCode{
   EKeyCode_Space = 32,
   EKeyCode_A = 65,
   EKeyCode_B = 66,
   EKeyCode_C = 67,
   EKeyCode_D = 68,
   EKeyCode_E = 69,
   EKeyCode_F = 70,
   EKeyCode_G = 71,
   EKeyCode_H = 72,
   EKeyCode_I = 73,
   EKeyCode_J = 74,
   EKeyCode_K = 75,
   EKeyCode_L = 76,
   EKeyCode_M = 77,
   EKeyCode_N = 78,
   EKeyCode_O = 79,
   EKeyCode_P = 80,
   EKeyCode_Q = 81,
   EKeyCode_R = 82,
   EKeyCode_S = 83,
   EKeyCode_T = 84,
   EKeyCode_U = 85,
   EKeyCode_V = 86,
   EKeyCode_W = 87,
   EKeyCode_X = 88,
   EKeyCode_Y = 89,
   EKeyCode_Z = 90,
};
//------------------------------------------------------------
typedef TInt TKeyCode;

//============================================================
// <T>键盘事件。</T>
//============================================================
class SKeyboardEvent : public SEvent{
public:
   TInt keyCode;
public:
   //------------------------------------------------------------
   // <T>构造键盘事件。</T>
   SKeyboardEvent(TAny* pSender, TInt keyCode) : SEvent(pSender){
      this->keyCode = keyCode;
   }
};
//------------------------------------------------------------
typedef MO_FI_DECLARE TListeners<SKeyboardEvent> TKeyboardListeners;

//============================================================
// <T>键盘设备。</T>
//============================================================
class MO_FI_DECLARE FKeyboardDevice : public FDevice
{
   MO_CLASS_DECLARE_INHERITS(FKeyboardDevice, FDevice);
protected:
   TBool _status[MO_KEYBOARD_COUNT];
   TKeyboardListeners _listenersKeyPress;
   TKeyboardListeners _listenersKeyDown;
   TKeyboardListeners _listenersKeyUp;
public:
   FKeyboardDevice();
   MO_ABSTRACT ~FKeyboardDevice();
public:
   //------------------------------------------------------------
   // <T>获得按键监听器集合。</T>
   MO_INLINE TKeyboardListeners& ListenersKeyPress(){
      return _listenersKeyPress;
   }
   //------------------------------------------------------------
   // <T>获得按键落下监听器集合。</T>
   MO_INLINE TKeyboardListeners& ListenersKeyDown(){
      return _listenersKeyDown;
   }
   //------------------------------------------------------------
   // <T>获得按键抬起监听器集合。</T>
   MO_INLINE TKeyboardListeners& ListenersKeyUp(){
      return _listenersKeyUp;
   }
public:
   TBool TestKeyDown(TInt keyCode);
public:
   MO_ABSTRACT void DoKeyPress(TInt keyCode);
   MO_ABSTRACT void DoKeyDown(TInt keyCode);
   MO_ABSTRACT void DoKeyUp(TInt keyCode);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FI_KEYBOARD_H__
