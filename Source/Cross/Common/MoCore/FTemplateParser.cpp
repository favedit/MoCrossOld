#include "MoCrTemplate.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplateParser, FConfigParser);

//============================================================
// <T>构造模板标签。</T>
//============================================================
FTemplateParser::FTemplateParser(){
   _optionTextNode = ETrue;
   _pFactory->Register(MO_CONFIG_ELEMENT_NODE, FTemplateTag::Class());
   _pFactory->Register(MO_CONFIG_ELEMENT_TEXT, FTemplateTagText::Class());
   _pFactory->Register(TC("write"), FTemplateTagWrite::Class());
   _pFactory->Register(TC("include"), FTemplateTagInclude::Class());
   _pFactory->Register(TC("true"), FTemplateTagTrue::Class());
   _pFactory->Register(TC("false"), FTemplateTagFalse::Class());
   _pFactory->Register(TC("counter"), FTemplateTagCounter::Class());
}

//============================================================
// <T>析构模板标签。</T>
//============================================================
FTemplateParser::~FTemplateParser(){
}

//============================================================
// <T>解析来源构成模板。</T>
//
// @return 模板
//============================================================
FTemplateSource* FTemplateParser::Load(TCharC* pSource){
   // 解析内容
   FTemplateTag* pRootTag = FTemplateTag::InstanceCreate();
   Parse(pRootTag, pSource);
   // 创建模板
   FTemplateSource* pTemplateSource = FTemplateSource::InstanceCreate();
   pTemplateSource->SetRootTag(pRootTag);
   return pTemplateSource;
}

MO_NAMESPACE_END
