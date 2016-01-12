#include "MoCrTemplate.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplateTagText, FTemplateTag);

//============================================================
// <T>构造模板文本标签。</T>
//============================================================
FTemplateTagText::FTemplateTagText(){
   _name = TC("text");
}

//============================================================
// <T>析构模板文本标签。</T>
//============================================================
FTemplateTagText::~FTemplateTagText(){
}

//============================================================
// <T>开始标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTagText::OnBegin(FTemplateContext* pContext){
   pContext->SourceWrite(_text);
   return ETemplateTagResult_Continue;
}

MO_NAMESPACE_END
