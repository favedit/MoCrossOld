#ifndef __MO_EG_FOCUS_H__
#define __MO_EG_FOCUS_H__
//************************************************************

#ifndef __MO_EG_COMMON_H__
#include "MoEgCommon.h"
#endif // __MO_EG_COMMON_H__

#ifndef __MO_EG_CORE_H__
#include "MoEgCore.h"
#endif // __MO_EG_CORE_H__

#ifndef __MO_EG_DISPLAY_H__
#include "MoEgDisplay.h"
#endif // __MO_EG_DISPLAY_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>焦点测试器。</T>
//============================================================
class MO_EG_DECLARE FFocusTester : public FObject{
public:
   TBool _statusInRange;
   TBool _statusChildren;
   TInt _level;
   SIntPoint2 _location;
   FDrawableCollection* _pDrawables;
public:
   FFocusTester();
   MO_ABSTRACT ~FFocusTester();
public:
   //------------------------------------------------------------
   // <T>获得是否在范围内。</T>
   MO_INLINE TBool StatusInRange(){
      return _statusInRange;
   }
   //------------------------------------------------------------
   // <T>设置是否在范围内。</T>
   MO_INLINE void SetStatusInRange(TBool statusInRange){
      _statusInRange = statusInRange;
   }
   //------------------------------------------------------------
   // <T>获得是否查询子对象。</T>
   MO_INLINE TBool StatusChildren(){
      return _statusChildren;
   }
   //------------------------------------------------------------
   // <T>设置是否查询子对象。</T>
   MO_INLINE void SetStatusChildren(TBool statusChildren){
      _statusChildren = statusChildren;
   }
   //------------------------------------------------------------
   // <T>获得层级。</T>
   MO_INLINE TInt Level(){
      return _level;
   }
   //------------------------------------------------------------
   // <T>设置层级。</T>
   MO_INLINE void SetLevel(TInt level){
      _level = level;
   }
   //------------------------------------------------------------
   // <T>获得位置。</T>
   MO_INLINE SIntPoint2& Location(){
      return _location;
   }
   //------------------------------------------------------------
   // <T>获得可绘制对象集合。</T>
   MO_INLINE FDrawableCollection* Drawables(){
      return _pDrawables;
   }
public:
   //------------------------------------------------------------
   // <T>获得激活的对象。</T>
   MO_INLINE FDrawable* ActiveDrawable(){
      return _pDrawables->IsEmpty() ? NULL : _pDrawables->First();
   }
   //------------------------------------------------------------
   // <T>增加一个对象。</T>
   MO_INLINE void Push(FDrawable* pDrawable){
      _pDrawables->Push(pDrawable);
   }
   //------------------------------------------------------------
   // <T>清空内容。</T>
   MO_INLINE void TestReset(){
      _statusInRange = EFalse;
      _statusChildren = EFalse;
   }
   //------------------------------------------------------------
   // <T>清空内容。</T>
   MO_INLINE void Clear(){
      _level = 0;
      _pDrawables->Clear();
   }
};

//============================================================
// <T>焦点控制台。</T>
//============================================================
class MO_EG_DECLARE FFocusConsole : public FConsole{
public:
   SIntPoint2 _focusLocation;
   FDrawable* _pHoverDrawable;
   FDrawable* _pFocusDrawable;
   FDrawable* _pDragDrawable;
   SIntPoint2 _startLocation;
   SIntPoint2 _dragLocation;
   SIntPoint2 _dragDrawableLocation;
   FDrawableCollection* _pFocusDrawables;
   FFocusTester* _pTester;
public:
   FFocusConsole();
   MO_ABSTRACT ~FFocusConsole();
public:
   //------------------------------------------------------------
   // <T>获得焦点位置。</T>
   MO_INLINE SIntPoint2& FocusLocation(){
      return _focusLocation;
   }
   //------------------------------------------------------------
   // <T>获得热点对象。</T>
   MO_INLINE FDrawable* HoverDrawable(){
      return _pHoverDrawable;
   }
   //------------------------------------------------------------
   // <T>获得焦点对象。</T>
   MO_INLINE FDrawable* FocusDrawable(){
      return _pFocusDrawable;
   }
   //------------------------------------------------------------
   // <T>获得焦点对象集合。</T>
   MO_INLINE FDrawableCollection* FocusDrawables(){
      return _pFocusDrawables;
   }
protected:
   FFocusTester* TestPosition(TInt x, TInt y);
public:
   TResult OnMouseDown(SMouseEvent* pEvent);
   TResult OnMouseMove(SMouseEvent* pEvent);
   TResult OnMouseUp(SMouseEvent* pEvent);
public:
   TResult Setup();
   TResult HoverDrawable(FDrawable* pDrawable);
   TResult FocusDrawable(FDrawable* pDrawable);
   TResult DragDrawable(FDrawable* pDrawable, TInt x, TInt y);
   TResult Process(EMouseButton buttonCd, TInt x, TInt y);
};

//============================================================
// <T>焦点管理器。</T>
//============================================================
class MO_EG_DECLARE RFocusManager : public RSingleton<FFocusConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EG_FOCUS_H__
