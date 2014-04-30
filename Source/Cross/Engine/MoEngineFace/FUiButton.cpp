#include "MoEfControl.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造控件对象。</T>
//============================================================
FUiButton::FUiButton(){
   _controlCd = EControlType_Button;
   _optionHand = EFalse;
   _statusDown = EFalse;
   _statusHover = EFalse;
   _validInterval = 0;
   _textAlignCd = EControlTextAlign_None;
}

//============================================================
// <T>析构控件对象。</T>
//============================================================
FUiButton::~FUiButton(){
}

//============================================================
// <T>设置文本对齐方式。</T>
//
// @param textAlignCd 文本对齐方式
// @return 处理结果
//============================================================
TResult FUiButton::SetTextAlignCd(EControlTextAlign textAlignCd){
   // 检查数据
   if(_textAlignCd == textAlignCd){
      return ESuccess;
   }
   // 设置内容
   _textAlignCd = textAlignCd;
   return Dirty();
}

//============================================================
// <T>设置文本内容。</T>
//
// @param pText 文本内容
// @return 处理结果
//============================================================
TResult FUiButton::SetText(TWideCharC* pText){
   // 检查数据
   if(_text.Equals(pText)){
      return ESuccess;
   }
   // 设置内容
   _text = pText;
   return Dirty();
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FUiButton::OnUnserialize(IDataInput* pInput){
   // 父信息反序列化
   TResult result = FUiControl::OnUnserialize(pInput);
   // 读取属性
   _optionHand = pInput->ReadBool();
   _font.Unserialize(pInput);
   _text.UnserializeAutomatic(pInput);
   _validInterval = pInput->ReadUint16();
   // 读取背景
   if(TestFlag(EControlFlag_LayerGround)){
      _groundPicture.Unserialize(pInput);
   }
   // 读取热点信息
   _borderHoverOuter.Unserialize(pInput);
   _borderHoverInner.Unserialize(pInput);
   _backHoverColor = pInput->ReadInt32();
   // 读取选中信息
   _borderSelectOuter.Unserialize(pInput);
   _borderSelectInner.Unserialize(pInput);
   _backSelectColor = pInput->ReadInt32();
   // 获得点击控件
   _onClickControl.Unserialize(pInput);
   return result;
}

//============================================================
// <T>绘制处理。</T>
//
// @return 处理结果
//============================================================
TResult FUiButton::OnPaint(){
   TResult result = FUiControl::OnPaint();
   //// 绘制处理
   //FUiCanvas* pCanvas = Canvas();
   //// 计算区域
   //SIntRectangle rectangle;
   //CalculateClientRectangle(&rectangle);
   //pCanvas->ClientRectangle().Assign(rectangle);
   //pCanvas->ClipRectangle().Assign(rectangle);
   //// 清空背景
   //if(_statusDown){
   //   pCanvas->Clear(_backSelectColor, &rectangle);
   //}else if(_statusHover){
   //   pCanvas->Clear(_backHoverColor, &rectangle);
   //}else{
   //   pCanvas->Clear(_backColor, &rectangle);
   //}
   //// 绘制底图
   //if(_groundPicture.IsValid()){
   //   FPictureResource* pPictureResource = (FPictureResource*)RResourceManager::Instance().Find(_groundPicture.resourceId);
   //   //pCanvas->DrawBitmap(pPictureResource->Bitmap(), ETrue, 0, 0);
   //   //TInt right = pPictureResource->Size().width;
   //   //TInt bottom = pPictureResource->Size().height;
   //   //SIntRectangle sourceRectangle(0, 0, right, bottom);
   //   //SIntRectangle targetRectangle(0, 0, (TInt)Size().width, (TInt)Size().height);
   //   //pCanvas->DrawBitmap(pPictureResource->Bitmap(),ETrue,&sourceRectangle,&targetRectangle);
   //}
   ////............................................................
   //if(_statusDown){
   //   // 绘制外边框
   //   if(!_borderSelectOuter.IsEmpty()){
   //      pCanvas->DrawBorder(&rectangle, &_borderSelectOuter);
   //      rectangle.Shrink(_borderSelectOuter.left.width, _borderSelectOuter.top.width, _borderSelectOuter.right.width, _borderSelectOuter.bottom.width);
   //   }
   //   // 绘制内边框
   //   if(!_borderHoverInner.IsEmpty()){
   //      pCanvas->DrawBorder(&rectangle, &_borderHoverInner);
   //      rectangle.Shrink(_borderHoverInner.left.width, _borderHoverInner.top.width, _borderHoverInner.right.width, _borderHoverInner.bottom.width);
   //   }
   //}else if(_statusHover){
   //   // 绘制外边框
   //   if(!_borderHoverOuter.IsEmpty()){
   //      pCanvas->DrawBorder(&rectangle, &_borderHoverOuter);
   //      rectangle.Shrink(_borderHoverOuter.left.width, _borderHoverOuter.top.width, _borderHoverOuter.right.width, _borderHoverOuter.bottom.width);
   //   }
   //   // 绘制内边框
   //   if(!_borderHoverInner.IsEmpty()){
   //      pCanvas->DrawBorder(&rectangle, &_borderHoverInner);
   //      rectangle.Shrink(_borderHoverInner.left.width, _borderHoverInner.top.width, _borderHoverInner.right.width, _borderHoverInner.bottom.width);
   //   }
   //}else{
   //   // 绘制外边框
   //   if(!_borderOuter.IsEmpty()){
   //      pCanvas->DrawBorder(&rectangle, &_borderOuter);
   //      rectangle.Shrink(_borderOuter.left.width, _borderOuter.top.width, _borderOuter.right.width, _borderOuter.bottom.width);
   //   }
   //   // 绘制内边框
   //   if(!_borderInner.IsEmpty()){
   //      pCanvas->DrawBorder(&rectangle, &_borderInner);
   //      rectangle.Shrink(_borderInner.left.width, _borderInner.top.width, _borderInner.right.width, _borderInner.bottom.width);
   //   }
   //}
   ////............................................................
   //// 绘制文字
   //if(!_text.IsEmpty()){
   //   SFontInfo fontInfo;
   //   fontInfo.color = _font.color;
   //   FFont* pDefaultFont = RFontManager::Instance().DefaultFont();
   //   pDefaultFont->CalculateWideSize(&fontInfo, (TWideCharC*)_text);
   //   TInt left = (rectangle.width - fontInfo.width) >> 1;
   //   TInt top = (rectangle.height - fontInfo.height) >> 1;
   //   SIntRectangle textRectangle(rectangle.left + left, rectangle.top + top, rectangle.width - left, rectangle.height - top);
   //   //pDefaultFont->DrawWideText(pCanvas, &textRectangle, &fontInfo, (TWideCharC*)_text);
   //}
   //// 更新内容
   //pCanvas->Update();
   return result;
}

//============================================================
// <T>鼠标落下处理。</T>
//
// @param pEvent 鼠标事件
// @return 处理结果
//============================================================
TResult FUiButton::MouseDown(SMouseEvent* pEvent){
   _statusDown = ETrue;
   // 分发事件
   SMouseEvent event(this, pEvent->buttons, pEvent->position.x, pEvent->position.y);
   _listenersClick.Process(&event);
   return Dirty();
}

//============================================================
// <T>鼠标移动处理。</T>
//
// @param pEvent 鼠标事件
// @return 处理结果
//============================================================
TResult FUiButton::MouseMove(SMouseEvent* pEvent){
   return ESuccess;
}

//============================================================
// <T>鼠标抬起处理。</T>
//
// @param pEvent 鼠标事件
// @return 处理结果
//============================================================
TResult FUiButton::MouseUp(SMouseEvent* pEvent){
   _statusDown = EFalse;
   return Dirty();
}

//============================================================
// <T>热点进入处理。</T>
//
// @return 处理结果
//============================================================
TResult FUiButton::HoverEnter(){
   _statusHover = ETrue;
   Dirty();
   return ESuccess;
}

//============================================================
// <T>热点离开处理。</T>
//
// @return 处理结果
//============================================================
TResult FUiButton::HoverLeave(){
   _statusHover = EFalse;
   _statusDown = EFalse;
   Dirty();
   return ESuccess;
}

MO_NAMESPACE_END
