#ifndef __MO_EG_CORE_H__
#define __MO_EG_CORE_H__
//************************************************************

#ifndef __MO_EG_PROCESSOR_H__
#include "MoEgProcessor.h"
#endif // __MO_EG_PROCESSOR_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>画板接口。</T>
//============================================================
class MO_EG_DECLARE ICanvas{
public:
   //------------------------------------------------------------
   // <T>析构画板接口。</T>
   MO_ABSTRACT ~ICanvas(){
   }
public:
   MO_VIRTUAL TResult Update() = 0;
};

//============================================================
// <T>可获得鼠标接口。</T>
//============================================================
class MO_EG_DECLARE IMouseable{
public:
   //------------------------------------------------------------
   // <T>析构可获得鼠标接口。</T>
   MO_ABSTRACT ~IMouseable(){
   }
public:
   MO_VIRTUAL TResult MouseDown(SMouseEvent* pEvent) = 0;
   MO_VIRTUAL TResult MouseMove(SMouseEvent* pEvent) = 0;
   MO_VIRTUAL TResult MouseUp(SMouseEvent* pEvent) = 0;
};

//============================================================
// <T>可获得焦点接口。</T>
//============================================================
class MO_EG_DECLARE IFocusable{
public:
   //------------------------------------------------------------
   // <T>析构可获得焦点接口。</T>
   MO_ABSTRACT ~IFocusable(){
   }
public:
   MO_VIRTUAL TResult FocusEnter() = 0;
   MO_VIRTUAL TResult FocusLeave() = 0;
};

//============================================================
// <T>可获得热点接口。</T>
//============================================================
class MO_EG_DECLARE IHoverable{
public:
   //------------------------------------------------------------
   // <T>析构可获得热点接口。</T>
   MO_ABSTRACT ~IHoverable(){
   }
public:
   MO_VIRTUAL TResult HoverEnter() = 0;
   MO_VIRTUAL TResult HoverLeave() = 0;
};

//============================================================
// <T>可拖拽接口。</T>
//============================================================
class MO_EG_DECLARE IDragable{
public:
   //------------------------------------------------------------
   // <T>析构可拖拽接口。</T>
   MO_ABSTRACT ~IDragable(){
   }
public:
   MO_VIRTUAL TResult DragBegin() = 0;
   MO_VIRTUAL TResult DragMove() = 0;
   MO_VIRTUAL TResult DragEnd() = 0;
};

//============================================================
// <T>显示动画命令类型。</T>
//============================================================
enum EMovieAction{
   EMovieAction_Play,
   EMovieAction_Visible,
   EMovieAction_Dispose,
};

//============================================================
// <T>显示动画命令。</T>
//============================================================
struct MO_EG_DECLARE SMovieAction{
public:
   // 命令类型
   EMovieAction actionCd;
   // 可见性
   TBool visible;
   // 方向
   TInt directionCd;
   // 循环次数
   TInt loop;
   // 速率
   TFloat rate;
public:
   //------------------------------------------------------------
   // <T>构造显示动画命令。</T>
   SMovieAction(){
   }
};
//------------------------------------------------------------
typedef MO_EG_DECLARE TList<SMovieAction> TMovieActionList;

MO_NAMESPACE_END
      
//************************************************************
#endif // __MO_EG_CORE_H__
