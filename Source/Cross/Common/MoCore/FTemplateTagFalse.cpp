#include "MoCrTemplate.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplateTagFalse, FTemplateTag);

//============================================================
// <T>构造模板假逻辑标签。</T>
//============================================================
FTemplateTagFalse::FTemplateTagFalse(){
}

//============================================================
// <T>析构模板假逻辑标签。</T>
//============================================================
FTemplateTagFalse::~FTemplateTagFalse(){
}

//============================================================
// <T>开始标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTagFalse::OnBegin(FTemplateContext* pContext){
   TBool flag = pContext->Config()->GetAsBool(_source);
   if(!flag){
      return ETemplateTagResult_Include;
   }
   return ETemplateTagResult_Continue;
}

//============================================================
// <T>结束标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTagFalse::OnEnd(FTemplateContext* pContext){
   return ETemplateTagResult_None;
}

//============================================================
// <T>设置一个属性。</T>
//
// @param pName 属性名称
// @param pValue 属性内容
// @return 处理结果
//============================================================
TResult FTemplateTagFalse::SetAttribute(TCharC* pName, TCharC* pValue){
   if(RString::Equals(pName, TC("source"))){
      _source = pValue;
   }
   return ESuccess;
}

MO_NAMESPACE_END
