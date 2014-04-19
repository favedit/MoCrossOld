#include "MoFmCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造顶层容器。</T>
//============================================================
FUiFrame::FUiFrame(){
   _code = 0;
   _objectCd |= EComponent_Renderable;
}

//============================================================
// <T>析构顶层容器。</T>
//============================================================
FUiFrame::~FUiFrame(){
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
//============================================================
TResult FUiFrame::OnUnserialize(IDataInput* pInput){
   FUiControlContainer::OnUnserialize(pInput);
   _code = pInput->ReadInt32();
   _statusVisible = EFalse;
   _statusDirty = ETrue;
   return ESuccess;
}

//============================================================
// <T>绘制处理。</T>
//
// @return 处理结果
//============================================================
TResult FUiFrame::OnPaint(){
   TResult result = FUiControlContainer::OnPaint();
   // 绘制处理
   FBitmapCanvas* pCanvas = Canvas();
   FFont* pDefaultFont = RFontManager::Instance().DefaultFont();
   pCanvas->Clear(_backColor);
   pCanvas->Update();
   return result;
}

//============================================================
// <T>显示处理。</T>
//
// @return 处理结果
//============================================================
TResult FUiFrame::OnShow(){
   return ESuccess;
}

//============================================================
// <T>隐藏处理。</T>
//
// @return 处理结果
//============================================================
TResult FUiFrame::OnHide(){
   return ESuccess;
}

//============================================================
// <T>显示处理。</T>
//
// @return 处理结果
//============================================================
TResult FUiFrame::Show(){
   // 检查状态
   if(_statusVisible){
      return ESuccess;
   }
   // 获得舞台
   FStage* pStage = RStageManager::Instance().ActiveStage();
   if(pStage == NULL){
      return EFailure;
   }
   // 获得界面舞台
   //FDisplayLayer* pLayer = pStage->LayerFind(EDisplayLayer_Frame);
   //if(pLayer == NULL){
   //   return EFailure;
   //}
   //// 计算位置
   //SIntPoint2 location;
   //SIntSize2 size((TInt)_size.width, (TInt)_size.height);
   //SIntRectangle& screenRectangle = RDeviceManager::Instance().ScreenDevice()->Rectangle();
   //TResult result = RUiUtil::CalculateDock(&location, &screenRectangle, &size, _dockCd, &_dockPadding);
   //if(result == ESuccess){
   //   SetLocationInt2(location.x, location.y);
   //}
   // 显示处理
   //OnShow();
   // 加入显示层中
   //pLayer->DisplayPush(this);
   _statusVisible = ETrue;
   //return result;
   return ESuccess;
}

//============================================================
// <T>隐藏处理。</T>
//
// @return 处理结果
//============================================================
TResult FUiFrame::Hide(){
   // 检查状态
   if(!_statusVisible){
      return ESuccess;
   }
   // 获得舞台
   FStage* pStage = RStageManager::Instance().ActiveStage();
   if(pStage == NULL){
      return EFailure;
   }
   // 获得界面舞台
   //FDisplayLayer* pLayer = pStage->LayerFind(EDisplayLayer_Frame);
   //if(pLayer == NULL){
   //   return EFailure;
   //}
   //// 隐藏处理
   //OnHide();
   // 从显示层中移除
   //pLayer->ChildRemove(this);
   _statusVisible = EFalse;
   return ESuccess;
}

MO_NAMESPACE_END
