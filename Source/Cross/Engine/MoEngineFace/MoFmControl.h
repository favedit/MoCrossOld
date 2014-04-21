#ifndef __MO_FM_CONTROL_H__
#define __MO_FM_CONTROL_H__
//************************************************************

#ifndef __MO_FM_COMMON_H__
#include "MoFmCommon.h"
#endif // __MO_FM_COMMON_H__

#ifndef __MO_FM_CORE_H__
#include "MoFmCore.h"
#endif // __MO_FM_CORE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>静态标签控件。</T>
//============================================================
class MO_FM_DECLARE FUiStaticLabel : public FUiControl{
protected:
   SUiFont _font;
   EControlTextAlign _textAlignCd;
   TWideString _text;
public:
   FUiStaticLabel();
   MO_ABSTRACT ~FUiStaticLabel();
public:
   //------------------------------------------------------------
   // <T>获得字体。</T>
   MO_INLINE SUiFont& Font(){
      return _font;
   }
   //------------------------------------------------------------
   // <T>获得文本对齐方式。</T>
   MO_INLINE EControlTextAlign TextAlignCd(){
      return _textAlignCd;
   }
   //------------------------------------------------------------
   // <T>获得文本内容。</T>
   MO_INLINE TWideCharC* Text(){
      return _text;
   }
public:
   TResult SetTextAlignCd(EControlTextAlign textAlignCd);
   TResult SetText(TWideCharC* pText);
public:
   MO_OVERRIDE TResult OnUnserialize(IDataInput* pInput);
   MO_OVERRIDE TResult OnPaint();
};
//------------------------------------------------------------
MO_DEF_CONTROL_POOL(MO_FM_DECLARE, FUiStaticLabelPool, FUiStaticLabel, EControlType_StaticLabel, NULL);

//============================================================
// <T>标签控件。</T>
//============================================================
class MO_FM_DECLARE FUiLabel : public FUiControl{
protected:
   SUiFont _font;
   EControlTextAlign _textAlignCd;
   TWideString _text;
public:
   FUiLabel();
   MO_ABSTRACT ~FUiLabel();
public:
   //------------------------------------------------------------
   // <T>获得字体。</T>
   MO_INLINE SUiFont& Font(){
      return _font;
   }
   //------------------------------------------------------------
   // <T>获得文本对齐方式。</T>
   MO_INLINE EControlTextAlign TextAlignCd(){
      return _textAlignCd;
   }
   //------------------------------------------------------------
   // <T>获得文本内容。</T>
   MO_INLINE TWideCharC* Text(){
      return _text;
   }
public:
   TResult SetTextAlignCd(EControlTextAlign textAlignCd);
   TResult SetText(TWideCharC* pText);
public:
   MO_OVERRIDE TResult OnUnserialize(IDataInput* pInput);
   MO_OVERRIDE TResult OnPaint();
};
//------------------------------------------------------------
MO_DEF_CONTROL_POOL(MO_FM_DECLARE, FUiLabelPool, FUiLabel, EControlType_Label, NULL);

//============================================================
// <T>静态标签控件。</T>
//============================================================
class MO_FM_DECLARE FUiRadio : public FUiControl{
protected:
   SUiFont _font;
   EControlTextAlign _textAlignCd;
   TWideString _text;
public:
   FUiRadio();
   MO_ABSTRACT ~FUiRadio();
public:
   //------------------------------------------------------------
   // <T>获得字体。</T>
   MO_INLINE SUiFont& Font(){
      return _font;
   }
   //------------------------------------------------------------
   // <T>获得文本对齐方式。</T>
   MO_INLINE EControlTextAlign TextAlignCd(){
      return _textAlignCd;
   }
   //------------------------------------------------------------
   // <T>获得文本内容。</T>
   MO_INLINE TWideCharC* Text(){
      return _text;
   }
public:
   TResult SetTextAlignCd(EControlTextAlign textAlignCd);
   TResult SetText(TWideCharC* pText);
public:
   MO_OVERRIDE TResult OnUnserialize(IDataInput* pInput);
   MO_OVERRIDE TResult OnPaint();
};
//------------------------------------------------------------
MO_DEF_CONTROL_POOL(MO_FM_DECLARE, FUiRadioPool, FUiRadio, EControlType_Radio, NULL);

//============================================================
// <T>静态标签控件。</T>
//============================================================
class MO_FM_DECLARE FUiCheck : public FUiControl{
protected:
   SUiFont _font;
   EControlTextAlign _textAlignCd;
   TWideString _text;
   TWideString _dataTrue;
   TWideString _dataFalse;
public:
   FUiCheck();
   MO_ABSTRACT ~FUiCheck();
public:
   //------------------------------------------------------------
   // <T>获得字体。</T>
   MO_INLINE SUiFont& Font(){
      return _font;
   }
   //------------------------------------------------------------
   // <T>获得文本对齐方式。</T>
   MO_INLINE EControlTextAlign TextAlignCd(){
      return _textAlignCd;
   }
   //------------------------------------------------------------
   // <T>获得文本内容。</T>
   MO_INLINE TWideCharC* Text(){
      return _text;
   }
public:
   TResult SetTextAlignCd(EControlTextAlign textAlignCd);
   TResult SetText(TWideCharC* pText);
public:
   MO_OVERRIDE TResult OnUnserialize(IDataInput* pInput);
   MO_OVERRIDE TResult OnPaint();
};
//------------------------------------------------------------
MO_DEF_CONTROL_POOL(MO_FM_DECLARE, FUiCheckPool, FUiCheck, EControlType_Check, NULL);

//============================================================
// <T>编辑控件。</T>
//============================================================
class MO_FM_DECLARE FUiNumber : public FUiControl{
protected:
   SUiFont _font;
   EControlTextAlign _textAlignCd;
   TWideString _text;
public:
   FUiNumber();
   MO_ABSTRACT ~FUiNumber();
public:
   //------------------------------------------------------------
   // <T>获得字体。</T>
   MO_INLINE SUiFont& Font(){
      return _font;
   }
   //------------------------------------------------------------
   // <T>获得文本对齐方式。</T>
   MO_INLINE EControlTextAlign TextAlignCd(){
      return _textAlignCd;
   }
   //------------------------------------------------------------
   // <T>获得文本内容。</T>
   MO_INLINE TWideCharC* Text(){
      return _text;
   }
public:
   TResult SetTextAlignCd(EControlTextAlign textAlignCd);
   TResult SetText(TWideCharC* pText);
public:
   MO_OVERRIDE TResult OnUnserialize(IDataInput* pInput);
   MO_OVERRIDE TResult OnPaint();
};
//------------------------------------------------------------
MO_DEF_CONTROL_POOL(MO_FM_DECLARE, FUiNumberPool, FUiNumber, EControlType_Number, NULL);

//============================================================
// <T>编辑控件。</T>
//============================================================
class MO_FM_DECLARE FUiEdit : public FUiControl{
protected:
   SUiFont _font;
   EControlTextAlign _textAlignCd;
   TWideString _text;
public:
   FUiEdit();
   MO_ABSTRACT ~FUiEdit();
public:
   //------------------------------------------------------------
   // <T>获得字体。</T>
   MO_INLINE SUiFont& Font(){
      return _font;
   }
   //------------------------------------------------------------
   // <T>获得文本对齐方式。</T>
   MO_INLINE EControlTextAlign TextAlignCd(){
      return _textAlignCd;
   }
   //------------------------------------------------------------
   // <T>获得文本内容。</T>
   MO_INLINE TWideCharC* Text(){
      return _text;
   }
public:
   TResult SetTextAlignCd(EControlTextAlign textAlignCd);
   TResult SetText(TWideCharC* pText);
public:
   MO_OVERRIDE TResult OnUnserialize(IDataInput* pInput);
   MO_OVERRIDE TResult OnPaint();
};
//------------------------------------------------------------
MO_DEF_CONTROL_POOL(MO_FM_DECLARE, FUiEditPool, FUiEdit, EControlType_Edit, NULL);

//============================================================
// <T>标签控件。</T>
//============================================================
class MO_FM_DECLARE FUiButton :
      public FUiControl,
      public IMouseable,
      public IHoverable{
protected:
   TBool _optionHand;
   TBool _statusDown;
   TBool _statusHover;
   EControlTextAlign _textAlignCd;
   SUiFont _font;
   TWideString _text;
   TInt _validInterval;
   SUiPicture _groundPicture;
   SUiBorder _borderHoverOuter;
   SUiBorder _borderHoverInner;
   TColor _backHoverColor;
   SUiBorder _borderSelectOuter;
   SUiBorder _borderSelectInner;
   TColor _backSelectColor;
   TMouseListeners _listenersClick;
   TAnsiString _onClickControl;
public:
   FUiButton();
   MO_ABSTRACT ~FUiButton();
public:
   //------------------------------------------------------------
   // <T>获得配置手游标。</T>
   MO_INLINE TBool OptionHand(){
      return _optionHand;
   }
   //------------------------------------------------------------
   // <T>设置配置手游标。</T>
   MO_INLINE void SetOptionHand(TBool optionHand){
      _optionHand = optionHand;
   }
   //------------------------------------------------------------
   // <T>获得字体。</T>
   MO_INLINE SUiFont& Font(){
      return _font;
   }
   //------------------------------------------------------------
   // <T>获得文本。</T>
   MO_INLINE TWideCharC* Text(){
      return _text;
   }
   //------------------------------------------------------------
   // <T>获得有效间隔。</T>
   MO_INLINE TInt ValidInterval(){
      return _validInterval;
   }
   //------------------------------------------------------------
   // <T>设置配有效间隔。</T>
   MO_INLINE void SetValidInterval(TInt validInterval){
      _validInterval = validInterval;
   }
   //------------------------------------------------------------
   // <T>获得背景图片。</T>
   MO_INLINE SUiPicture& GroundPicture(){
      return _groundPicture;
   }
   //------------------------------------------------------------
   // <T>获得点击监听器。</T>
   MO_INLINE TMouseListeners& ListenersClick(){
      return _listenersClick;
   }
   //------------------------------------------------------------
   // <T>获得点击控件。</T>
   MO_INLINE TCharC* OnClickControl(){
      return _onClickControl;
   }
public:
   TResult SetTextAlignCd(EControlTextAlign textAlignCd);
   TResult SetText(TWideCharC* pText);
public:
   MO_OVERRIDE TResult OnUnserialize(IDataInput* pInput);
   MO_OVERRIDE TResult OnPaint();
public:
   MO_OVERRIDE TResult MouseDown(SMouseEvent* pEvent);
   MO_OVERRIDE TResult MouseMove(SMouseEvent* pEvent);
   MO_OVERRIDE TResult MouseUp(SMouseEvent* pEvent);
public:
   MO_OVERRIDE TResult HoverEnter();
   MO_OVERRIDE TResult HoverLeave();
};
//------------------------------------------------------------
MO_DEF_CONTROL_POOL(MO_FM_DECLARE, FUiButtonPool, FUiButton, EControlType_Button, NULL);

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FM_CONTROL_H__
