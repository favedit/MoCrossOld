#ifndef __MO_FM_FRAME_H__
#define __MO_FM_FRAME_H__
//************************************************************

#ifndef __MO_FM_COMMON_H__
#include "MoFmCommon.h"
#endif // __MO_FM_COMMON_H__

#ifndef __MO_FM_CORE_H__
#include "MoFmCore.h"
#endif // __MO_FM_CORE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>¶¥²ãÍ£¿¿À¸¡£</T>
//============================================================
class MO_FM_DECLARE FUiBar : public FUiFrame{
public:
   FUiBar();
   MO_ABSTRACT ~FUiBar();
};
//------------------------------------------------------------
MO_DEF_CONTROL_POOL(MO_FM_DECLARE, FUiBarPool, FUiBar, EControlType_Bar, NULL);

//============================================================
// <T>¶¥²ã±íµ¥¡£</T>
//============================================================
class MO_FM_DECLARE FUiForm : public FUiFrame{
public:
   FUiForm();
   MO_ABSTRACT ~FUiForm();
};
//------------------------------------------------------------
MO_DEF_CONTROL_POOL(MO_FM_DECLARE, FUiFormPool, FUiForm, EControlType_Form, NULL);

//============================================================
// <T>¶¥²ã´°¿Ú¡£</T>
//============================================================
class MO_FM_DECLARE FUiWindow :
      public FUiFrame,
      public IMouseable,
      public IDragable{
protected:
   TInt _titleHeight;
public:
   FUiWindow();
   MO_ABSTRACT ~FUiWindow();
public:
   MO_OVERRIDE TResult OnPaint();
public:
   MO_OVERRIDE TResult PaintTitle(FUiCanvas* pCanvas, SIntRectangle* pRectangle);
   MO_OVERRIDE TResult PaintBody(FUiCanvas* pCanvas, SIntRectangle* pRectangle);
public:
   MO_OVERRIDE TResult MouseDown(SMouseEvent* pEvent);
   MO_OVERRIDE TResult MouseMove(SMouseEvent* pEvent);
   MO_OVERRIDE TResult MouseUp(SMouseEvent* pEvent);
public:
   MO_OVERRIDE TResult DragBegin();
   MO_OVERRIDE TResult DragMove();
   MO_OVERRIDE TResult DragEnd();
};
//------------------------------------------------------------
MO_DEF_CONTROL_POOL(MO_FM_DECLARE, FUiWindowPool, FUiWindow, EControlType_Window, NULL);

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FM_FRAME_H__
