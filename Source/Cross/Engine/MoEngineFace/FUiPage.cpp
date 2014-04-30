#include "MoEfContainer.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造页控件。</T>
//============================================================
FUiPage::FUiPage(){
   _controlCd = EControlType_Page;
   _optionDefault = EFalse;
}

//============================================================
// <T>析构页控件。</T>
//============================================================
FUiPage::~FUiPage(){
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FUiPage::OnUnserialize(IDataInput* pInput){
   // 父信息反序列化
   TResult result = FUiControlContainer::OnUnserialize(pInput);
   // 读取属性
   _optionDefault = pInput->ReadBool();
   return result;
}

MO_NAMESPACE_END
