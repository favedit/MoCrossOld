#include "MoCrTemplate.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplateTagWrite, FTemplateTag);

//============================================================
// <T>构造模板输出逻辑标签。</T>
//============================================================
FTemplateTagWrite::FTemplateTagWrite(){
}

//============================================================
// <T>析构模板输出逻辑标签。</T>
//============================================================
FTemplateTagWrite::~FTemplateTagWrite(){
}

//============================================================
// <T>开始标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTagWrite::OnBegin(FTemplateContext* pContext){
   MO_CHECK(pContext, return ETemplateTagResult_Failure);
   TCharC* pValue = pContext->Config()->Get(_source, NULL);
   if(pValue != NULL){
      pContext->SourceWrite(pValue);
   }
   return ETemplateTagResult_Continue;
}

//============================================================
// <T>设置一个属性。</T>
//
// @param pName 属性名称
// @param pValue 属性内容
// @return 处理结果
//============================================================
TResult FTemplateTagWrite::SetAttribute(TCharC* pName, TCharC* pValue){
   if(RString::Equals(pName, TC("source"))){
      _source = pValue;
   }
   return ESuccess;
}

MO_NAMESPACE_END
