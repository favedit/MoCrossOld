#include "MoCrTemplate.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplateTagEquals, FTemplateTag);

//============================================================
// <T>构造模板相等逻辑标签。</T>
//============================================================
FTemplateTagEquals::FTemplateTagEquals(){
}

//============================================================
// <T>析构模板相等逻辑标签。</T>
//============================================================
FTemplateTagEquals::~FTemplateTagEquals(){
}

//============================================================
// <T>开始标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTagEquals::OnBegin(FTemplateContext* pContext){
   return ETemplateTagResult_None;
}

//============================================================
// <T>结束标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTagEquals::OnEnd(FTemplateContext* pContext){
   return ETemplateTagResult_None;
}

//============================================================
// <T>设置一个属性。</T>
//
// @param pName 属性名称
// @param pValue 属性内容
// @return 处理结果
//============================================================
TResult FTemplateTagEquals::SetAttribute(TCharC* pName, TCharC* pValue){
   if(RString::Equals(pName, TC("source"))){
      _source = pValue;
   }
   return ESuccess;
}

MO_NAMESPACE_END
