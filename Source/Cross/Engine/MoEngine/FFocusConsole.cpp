#include "MoEgDevice.h"
#include "MoEgFocus.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造焦点控制台。</T>
//============================================================
FFocusConsole::FFocusConsole(){
   MO_CLEAR(_pHoverDrawable);
   MO_CLEAR(_pFocusDrawable);
   MO_CLEAR(_pDragDrawable);
   _pFocusDrawables = MO_CREATE(FDrawableCollection);
   _pTester = MO_CREATE(FFocusTester);
}

//============================================================
// <T>析构焦点控制台。</T>
//============================================================
FFocusConsole::~FFocusConsole(){
   MO_DELETE(_pFocusDrawables);
   MO_DELETE(_pTester);
}

//============================================================
// <T>根据位置测试处理。</T>
//
// @param x 横坐标
// @param y 纵坐标
// @return 测试器
//============================================================
FFocusTester* FFocusConsole::TestPosition(TInt x, TInt y){
   // 检查参数
   FStage* pStage = RStageManager::Instance().ActiveStage();
   if(pStage == NULL){
      return NULL;
   }
   // 测试数据
   _pTester->Clear();
   _pTester->Location().Set(x, y);
   //pStage->FocusTest(_pTester);
   return _pTester;
}

//============================================================
// <T>鼠标落下处理。</T>
//
// @param pEvent 事件对象
// @return 处理结果
//============================================================
TResult FFocusConsole::OnMouseDown(SMouseEvent* pEvent){
   // 检测焦点信息
   TInt x = pEvent->position.x;
   TInt y = pEvent->position.y;
   FFocusTester* pTester = FFocusConsole::TestPosition(x, y);
   if(pTester == NULL){
      return ESuccess;
   }
   //............................................................
   // 设置属性
   _startLocation.Set(x, y);
   //............................................................
   // 鼠标落下事件
   FDrawable* pDrawable = pTester->ActiveDrawable();
   if(pDrawable != NULL){
      //IMouseable* pMouseable = (IMouseable*)pDrawable->Convert(EComponent_Mouseable);
      //if(pMouseable != NULL){
      //   pMouseable->MouseDown(pEvent);
      //}
   }
   return ESuccess;
}

//============================================================
// <T>鼠标移动处理。</T>
//
// @param pEvent 事件对象
// @return 处理结果
//============================================================
TResult FFocusConsole::OnMouseMove(SMouseEvent* pEvent){
   // 检测焦点信息
   TInt x = pEvent->position.x;
   TInt y = pEvent->position.y;
   FFocusTester* pTester = FFocusConsole::TestPosition(x, y);
   if(pTester == NULL){
      return ESuccess;
   }
   //............................................................
   // 鼠标落下事件
   FDrawable* pDrawable = pTester->ActiveDrawable();
   if(pDrawable != NULL){
      //IMouseable* pMouseable = (IMouseable*)pDrawable->Convert(EComponent_Mouseable);
      //if(pMouseable != NULL){
      //   pMouseable->MouseMove(pEvent);
      //}
   }
   // 热点选择处理
   HoverDrawable(pDrawable);
   //............................................................
   // 拖拽处理
   if(_pDragDrawable != NULL){
      TInt cx = x - _startLocation.x;
      TInt cy = y - _startLocation.y;
      _pDragDrawable->Location().x = (TFloat)(_dragDrawableLocation.x + cx);
      _pDragDrawable->Location().y = (TFloat)(_dragDrawableLocation.y + cy);
   }
   return ESuccess;
}

//============================================================
// <T>鼠标抬起处理。</T>
//
// @param pEvent 事件对象
// @return 处理结果
//============================================================
TResult FFocusConsole::OnMouseUp(SMouseEvent* pEvent){
   // 检测焦点信息
   TInt x = pEvent->position.x;
   TInt y = pEvent->position.y;
   FFocusTester* pTester = FFocusConsole::TestPosition(x, y);
   if(pTester == NULL){
      return ESuccess;
   }
   //............................................................
   // 鼠标抬起事件
   FDrawable* pDrawable = pTester->ActiveDrawable();
   if(pDrawable != NULL){
      //IMouseable* pMouseable = (IMouseable*)pDrawable->Convert(EComponent_Mouseable);
      //if(pMouseable != NULL){
      //   pMouseable->MouseUp(pEvent);
      //}
   }
   //............................................................
   // 停止拖拽处理
   MO_CLEAR(_pDragDrawable);
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FFocusConsole::Setup(){
   FMouseDevice* pMouseDevice = RDeviceManager::Instance().Find<FMouseDevice>();
   pMouseDevice->ListenersMouseDown().Register<FFocusConsole>(this, &FFocusConsole::OnMouseDown);
   pMouseDevice->ListenersMouseMove().Register<FFocusConsole>(this, &FFocusConsole::OnMouseMove);
   pMouseDevice->ListenersMouseUp().Register<FFocusConsole>(this, &FFocusConsole::OnMouseUp);
   return ESuccess;
}

//============================================================
// <T>一个可绘制对象获得热点。</T>
//
// @return 处理结果
//============================================================
TResult FFocusConsole::HoverDrawable(FDrawable* pDrawable){
   // 检查改变
   if(_pHoverDrawable == pDrawable){
      return ESuccess;
   }
   // 取消上个热点对象激活
   if(_pHoverDrawable != NULL){
      //IHoverable* pHoverable = (IHoverable*)_pHoverDrawable->Convert(EComponent_Hoverable);
      //if(pHoverable != NULL){
      //   pHoverable->HoverLeave();
      //}
      //_pHoverDrawable = NULL;
   }
   // 激活当前热点对象
   if(pDrawable != NULL){
      //IHoverable* pHoverable = (IHoverable*)pDrawable->Convert(EComponent_Hoverable);
      //if(pHoverable != NULL){
      //   pHoverable->HoverEnter();
      //   _pHoverDrawable = pDrawable;
      //}
   }
   return ESuccess;
}

//============================================================
// <T>一个可绘制对象获得焦点。</T>
//
// @return 处理结果
//============================================================
TResult FFocusConsole::FocusDrawable(FDrawable* pDrawable){
   MO_CHECK(pDrawable, return ENull);
   return ESuccess;
}

//============================================================
TResult FFocusConsole::DragDrawable(FDrawable* pDrawable, TInt x, TInt y){
   MO_CHECK(pDrawable, return ENull);
   if(_pDragDrawable == pDrawable){
      return EContinue;
   }
   _pDragDrawable = pDrawable;
   _dragLocation.Set(x, y);
   _dragDrawableLocation.Set((TInt)_pDragDrawable->Location().x, (TInt)_pDragDrawable->Location().y);
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FFocusConsole::Process(EMouseButton buttonCd, TInt x, TInt y){
   return ESuccess;
}

MO_NAMESPACE_END
