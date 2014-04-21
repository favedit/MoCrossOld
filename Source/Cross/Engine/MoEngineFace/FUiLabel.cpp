#include "MoFmControl.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造标签控件。</T>
//============================================================
FUiLabel::FUiLabel(){
   _controlCd = EControlType_Label;
   _textAlignCd = EControlTextAlign_None;
}

//============================================================
// <T>析构标签控件。</T>
//============================================================
FUiLabel::~FUiLabel(){
}

//============================================================
// <T>设置文本对齐方式。</T>
//
// @param textAlignCd 文本对齐方式
// @return 处理结果
//============================================================
TResult FUiLabel::SetTextAlignCd(EControlTextAlign textAlignCd){
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
TResult FUiLabel::SetText(TWideCharC* pText){
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
TResult FUiLabel::OnUnserialize(IDataInput* pInput){
   // 父信息反序列化
   TResult result = FUiControl::OnUnserialize(pInput);
   // 读取属性
   _font.Unserialize(pInput);
   _textAlignCd = (EControlTextAlign)pInput->ReadUint8();
   _text.UnserializeAutomatic(pInput);
   return result;
}

//============================================================
// <T>绘制处理。</T>
//
// @return 处理结果
//============================================================
TResult FUiLabel::OnPaint(){
   // 绘制处理
   FBitmapCanvas* pCanvas = Canvas();
   // 计算区域
   SIntRectangle rectangle;
   CalculateClientRectangle(&rectangle);
   pCanvas->ClientRectangle().Assign(rectangle);
   pCanvas->ClipRectangle().Assign(rectangle);
   // 清空背景
   pCanvas->Clear(_backColor);
   // 绘制文字
   if(!_text.IsEmpty()){
      SFontInfo fontInfo;
      _font.SaveInfo(fontInfo);
      FFont* pDefaultFont = RFontManager::Instance().DefaultFont();
      pDefaultFont->CalculateWideSize(&fontInfo, (TWideCharC*)_text);
      TInt top = (rectangle.height - fontInfo.height) >> 1;
      SIntRectangle textRectangle(rectangle.left, rectangle.top + top, rectangle.width, rectangle.height - top);
      //pDefaultFont->DrawWideText(pCanvas, &textRectangle, &fontInfo, (TWideCharC*)_text);
   }
   // 更新内容
   pCanvas->Update();
   return ESuccess;
}

MO_NAMESPACE_END
