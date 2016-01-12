#include "MoCrTemplate.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplateTagCounter, FTemplateTag);

//============================================================
// <T>构造模板计数器标签。</T>
//============================================================
FTemplateTagCounter::FTemplateTagCounter(){
   _operator = TC("add");
   _value = TC("1");
}

//============================================================
// <T>析构模板计数器标签。</T>
//============================================================
FTemplateTagCounter::~FTemplateTagCounter(){
}

//============================================================
// <T>开始标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTagCounter::OnBegin(FTemplateContext* pContext){
   MO_CHECK(pContext, return ETemplateTagResult_Failure);
   TCharC* pSource = pContext->Config()->Get(_source, NULL);
   if(pSource != NULL){
      // 计算数据
      TInt source = RInt::Parse(pSource);
      TInt value = RInt::Parse(_value);
      if(RString::Equals((TCharC*)_operator, TC("add"))){
         source += value;
      }else if(RString::Equals((TCharC*)_operator, TC("sub"))){
         source -= value;
      }else if(RString::Equals((TCharC*)_operator, TC("mul"))){
         source *= value;
      }else if(RString::Equals((TCharC*)_operator, TC("div"))){
         source /= value;
      }else{
         MO_FATAL(TC("Unknown operator. (operator=%s)"), (TCharC*)_operator);
      }
      // 写入内容
      TFsText sourceValue;
      sourceValue.AppendInt(source);
      pContext->SourceWrite(sourceValue);
      // 修正原始内容
      pContext->Config()->Set(_source, sourceValue);
      // 重新定义内容
      if(!_define.IsEmpty()){
         pContext->Config()->Set(_define, sourceValue);
      }
   }
   return ETemplateTagResult_Continue;
}

//============================================================
// <T>结束标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTagCounter::OnEnd(FTemplateContext* pContext){
   return ETemplateTagResult_None;
}

//============================================================
// <T>设置一个属性。</T>
//
// @param pName 属性名称
// @param pValue 属性内容
// @return 处理结果
//============================================================
TResult FTemplateTagCounter::SetAttribute(TCharC* pName, TCharC* pValue){
   if(RString::Equals(pName, TC("source"))){
      _source = pValue;
   }else if(RString::Equals(pName, TC("operator"))){
      _operator = pValue;
   }else if(RString::Equals(pName, TC("value"))){
      _value = pValue;
   }else if(RString::Equals(pName, TC("define"))){
      _define = pValue;
   }
   return ESuccess;
}

MO_NAMESPACE_END
