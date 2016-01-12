#include "MoCrTemplate.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplateTagInclude, FTemplateTag);

//============================================================
// <T>构造模板包含逻辑标签。</T>
//============================================================
FTemplateTagInclude::FTemplateTagInclude(){
   MO_CLEAR(_pTemplate);
   MO_CLEAR(_pSource);
   MO_CLEAR(_pIncludeTags);
}

//============================================================
// <T>析构模板包含逻辑标签。</T>
//============================================================
FTemplateTagInclude::~FTemplateTagInclude(){
   MO_DELETE(_pTemplate);
   MO_DELETE(_pSource);
   MO_DELETE(_pIncludeTags);
}

//============================================================
// <T>开始标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTagInclude::OnBegin(FTemplateContext* pContext){
   _pTemplate->Parse(pContext, _segment);
   return ETemplateTagResult_None;
}

//============================================================
// <T>结束标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTagInclude::OnEnd(FTemplateContext* pContext){
   return ETemplateTagResult_None;
}

//============================================================
// <T>设置一个属性。</T>
//
// @param pName 属性名称
// @param pValue 属性内容
// @return 处理结果
//============================================================
TResult FTemplateTagInclude::SetAttribute(TCharC* pName, TCharC* pValue){
   if(RString::Equals(pName, TC("source"))){
      _source = pValue;
   }else if(RString::Equals(pName, TC("segment"))){
      _segment = pValue;
   }else{
      MO_FATAL(TC("Unknown attribute. (name=%s, value=%s)"), pName, pValue);
   }
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @param pBuilder 构建器
// @return 处理结果
//============================================================
TResult FTemplateTagInclude::Setup(FTemplateBuilder* pBuilder){
   // 检查参数
   MO_CHECK(pBuilder, return ENull);
   MO_CHECK(!_source.IsEmpty(), return EFailure);
   MO_CHECK(!_segment.IsEmpty(), return EFailure);
   // 解析内容
   TResult resultCd = FTemplateTag::Setup(pBuilder);
   FTemplateConsole* pConsole = pBuilder->Console();
   FTemplateContext* pContext = pBuilder->Context();
   FTemplate* pTemplate = pBuilder->Template();
   pTemplate->IncludeTags()->Push(this);
   // 加载来源
   MO_ASSERT(!_pTemplate);
   _pTemplate = pConsole->Load(pContext, _source);
   return resultCd;
}

MO_NAMESPACE_END
