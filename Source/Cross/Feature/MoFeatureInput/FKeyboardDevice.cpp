#include "MoFiKeyboard.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FKeyboardDevice, FDevice);

//============================================================
// <T>构造键盘设备。</T>
//============================================================
FKeyboardDevice::FKeyboardDevice(){
   _name = "keyboard.device";
   RTypes<TBool>::Clear(_status, MO_KEYBOARD_COUNT);
}

//============================================================
// <T>析构键盘设备。</T>
//============================================================
FKeyboardDevice::~FKeyboardDevice(){
}

//============================================================
// <T>测试按键是否落下。</T>
//
// @param keyCode 按键代码
// @return 是否落下
//============================================================
TBool FKeyboardDevice::TestKeyDown(TInt keyCode){
   TInt code = keyCode % MO_KEYBOARD_COUNT;
   return _status[code];
}

//============================================================
// <T>按键处理。</T>
//
// @param keyCode 按键代码
//============================================================
void FKeyboardDevice::DoKeyPress(TInt keyCode){
   // 分发处理
   SKeyboardEvent event(this, keyCode);
   _listenersKeyPress.Process(&event);
}

//============================================================
// <T>按键落下处理。</T>
//
// @param keyCode 按键代码
//============================================================
void FKeyboardDevice::DoKeyDown(TInt keyCode){
   // 设置内容
   TInt code = keyCode % MO_KEYBOARD_COUNT;
   _status[code] = ETrue;
   // 分发处理
   SKeyboardEvent event(this, keyCode);
   _listenersKeyDown.Process(&event);
}

//============================================================
// <T>按键抬起处理。</T>
//
// @param keyCode 按键代码
//============================================================
void FKeyboardDevice::DoKeyUp(TInt keyCode){
   // 设置内容
   TInt code = keyCode % MO_KEYBOARD_COUNT;
   _status[code] = EFalse;
   // 分发处理
   SKeyboardEvent event(this, keyCode);
   _listenersKeyUp.Process(&event);
}

MO_NAMESPACE_END
