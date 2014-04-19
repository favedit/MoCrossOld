#include "MoFiMouse.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FMouseDevice, FDevice);

//============================================================
// <T>构造鼠标设备。</T>
//============================================================
FMouseDevice::FMouseDevice(){
   _name = "mouse.device";
   _buttonLeftDown = EFalse;
   _buttonMiddleDown = EFalse;
   _buttonRightDown = EFalse;
}

//============================================================
// <T>析构鼠标设备。</T>
//============================================================
FMouseDevice::~FMouseDevice(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FMouseDevice::Setup(){
   return ESuccess;
}

//============================================================
// <T>测试鼠标落下。</T>
//
// @param buttons 按键集合
// @return 是否落下
//============================================================
TBool FMouseDevice::TestMouseDownLeft(){
   return TestMouseDown(EMouseButton_Left);
}

//============================================================
// <T>测试鼠标落下。</T>
//
// @param buttons 按键集合
// @return 是否落下
//============================================================
TBool FMouseDevice::TestMouseDownMiddle(){
   return TestMouseDown(EMouseButton_Middle);
}

//============================================================
// <T>测试鼠标落下。</T>
//
// @param buttons 按键集合
// @return 是否落下
//============================================================
TBool FMouseDevice::TestMouseDownRight(){
   return TestMouseDown(EMouseButton_Right);
}

//============================================================
// <T>测试鼠标落下。</T>
//
// @param buttons 按键集合
// @return 是否落下
//============================================================
TBool FMouseDevice::TestMouseDown(TMouseButtons buttons){
   TBool result = EFalse;
   // 测试左键
   if((buttons & EMouseButton_Left) == EMouseButton_Left){
      if(!_buttonLeftDown){
         return EFalse;
      }
      result = ETrue;
   }
   // 测试中键
   if((buttons & EMouseButton_Middle) == EMouseButton_Middle){
      if(!_buttonMiddleDown){
         return EFalse;
      }
      result = ETrue;
   }
   // 测试右键
   if((buttons & EMouseButton_Right) == EMouseButton_Right){
      if(!_buttonRightDown){
         return EFalse;
      }
      result = ETrue;
   }
   return result;
}

//============================================================
// <T>单击处理。</T>
//
// @param buttons 按键
// @param x 横坐标
// @param y 纵坐标
//============================================================
void FMouseDevice::DoClick(TMouseButtons buttons, TInt x, TInt y){
   // 设置内容
   _position.Set(x, y);
   // 分发处理
   SMouseEvent event(this, buttons, x, y);
   _listenersClick.Process(&event);
}

//============================================================
// <T>双击处理。</T>
//
// @param buttons 按键
// @param x 横坐标
// @param y 纵坐标
//============================================================
void FMouseDevice::DoDoubleClick(TMouseButtons buttons, TInt x, TInt y){
   // 设置内容
   _position.Set(x, y);
   // 分发处理
   SMouseEvent event(this, buttons, x, y);
   _listenersDoubleClick.Process(&event);
}

//============================================================
// <T>鼠标落下处理。</T>
//
// @param buttons 按键
// @param x 横坐标
// @param y 纵坐标
//============================================================
void FMouseDevice::DoMouseDown(TMouseButtons buttons, TInt x, TInt y){
   // 设置按键
   if((buttons & EMouseButton_Left) == EMouseButton_Left){
      _buttonLeftDown = ETrue;
   }
   if((buttons & EMouseButton_Middle) == EMouseButton_Middle){
      _buttonMiddleDown = ETrue;
   }
   if((buttons & EMouseButton_Right) == EMouseButton_Right){
      _buttonRightDown = ETrue;
   }
   // 设置内容
   _positionDown.Set(x, y);
   _position.Set(x, y);
   // 分发处理
   SMouseEvent event(this, buttons, x, y);
   _listenersMouseDown.Process(&event);
}

//============================================================
// <T>鼠标移动处理。</T>
//
// @param buttons 按键
// @param x 横坐标
// @param y 纵坐标
//============================================================
void FMouseDevice::DoMouseMove(TMouseButtons buttons, TInt x, TInt y){
   // 设置内容
   _position.Set(x, y);
   // 分发处理
   SMouseEvent event(this, buttons, x, y);
   _listenersMouseMove.Process(&event);
}

//============================================================
// <T>鼠标抬起处理。</T>
//
// @param buttons 按键
// @param x 横坐标
// @param y 纵坐标
//============================================================
void FMouseDevice::DoMouseUp(TMouseButtons buttons, TInt x, TInt y){
   // 设置按键
   if((buttons & EMouseButton_Left) == EMouseButton_Left){
      _buttonLeftDown = EFalse;
   }
   if((buttons & EMouseButton_Middle) == EMouseButton_Middle){
      _buttonMiddleDown = EFalse;
   }
   if((buttons & EMouseButton_Right) == EMouseButton_Right){
      _buttonRightDown = EFalse;
   }
   // 设置内容
   _position.Set(x, y);
   // 分发处理
   SMouseEvent event(this, buttons, x, y);
   _listenersMouseUp.Process(&event);
}

MO_NAMESPACE_END
