#include "MoFmContainer.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造分页控件。</T>
//============================================================
FUiPageControl::FUiPageControl(){
   _controlCd = EControlType_PageControl;
   MO_CLEAR(_pActivePage);
}

//============================================================
// <T>析构分页控件。</T>
//============================================================
FUiPageControl::~FUiPageControl(){
}

//============================================================
// <T>配置后置处理。</T>
//
// @return 处理结果
//============================================================
TResult FUiPageControl::OnSetupAfter(){
   // 父配置后置处理
   TResult result = FUiControlContainer::OnSetupAfter();
   // 子节点集合处理
   //TInt count = ChildCount();
   //for(TInt n = 0; n < count; n++){
   //   FUiPage* pPage = (FUiPage*)ControlGet(n, EControlType_Page);
   //   if(pPage != NULL){
   //      // 默认设置首个页面为激活页面
   //      if(_pActivePage == NULL){
   //         _pActivePage = pPage;
   //         continue;
   //      }
   //      // 有设置的时候以设置默认为准
   //      if(pPage->OptionDefault()){
   //         _pActivePage = pPage;
   //         break;
   //      }
   //   }
   //}
   // 选中页面
   OnSelectPage(_pActivePage);
   return result;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FUiPageControl::OnUnserialize(IDataInput* pInput){
   // 父信息反序列化
   TResult result = FUiControlContainer::OnUnserialize(pInput);
   return result;
}

//============================================================
// <T>焦点测试处理。</T>
//
// @param pTester 测试信息
// @return 处理结果
//============================================================
TResult FUiPageControl::OnFocusTest(FFocusTester* pTester){
   SIntRectangle rectangle;
   CalculateRectangle(&rectangle);
   SIntPoint2& location = pTester->Location();
   if(rectangle.TestInRange(location.x, location.y)){
      pTester->SetStatusInRange(ETrue);
      pTester->SetStatusChildren(ETrue);
   }
   return ESuccess;
}

//============================================================
// <T>选择页面处理。</T>
//
// @param pPage 页面
// @return 处理结果
//============================================================
TResult FUiPageControl::OnSelectPage(FUiPage* pPage){
   // 设置其他页面
   //TInt count = ChildCount();
   //for(TInt n = 0; n < count; n++){
   //   FUiPage* pFindPage = (FUiPage*)ControlGet(n, EControlType_Page);
   //   if(pFindPage != NULL){
   //      pFindPage->SetVisible(pFindPage == pPage);
   //   }
   //}
   // 设置激活页面
   _pActivePage = pPage;
   return ESuccess;
}

//============================================================
// <T>选择页面处理。</T>
//
// @param pPage 页面
// @return 处理结果
//============================================================
TResult FUiPageControl::SelectPage(FUiPage* pPage){
   TResult result = ESuccess;
   if(_pActivePage != pPage){
      result = OnSelectPage(pPage);
   }
   return result;
}

MO_NAMESPACE_END
