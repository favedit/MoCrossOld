#include "MoCrTemplate.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplateTag, FInstance);

//============================================================
// <T>构造模板标签。</T>
//============================================================
FTemplateTag::FTemplateTag(){
   _nowrapLeft = EFalse;
   _nowrapRight = EFalse;
   MO_CLEAR(_pChildren);
}

//============================================================
// <T>析构模板标签。</T>
//============================================================
FTemplateTag::~FTemplateTag(){
   if(_pChildren != NULL){
      TInt count = _pChildren->Count();
      for(TInt n = 0; n < count; n++){
         FTemplateTag* pTag = _pChildren->Get(n);
         MO_DELETE(pTag);
      }
      _pChildren->Clear();
   }
   MO_DELETE(_pChildren);
}

//============================================================
// <T>开始标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTag::OnBegin(FTemplateContext* pContext){
   return ETemplateTagResult_Include;
}

//============================================================
// <T>循环标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTag::OnLoop(FTemplateContext* pContext){
   return ETemplateTagResult_Stop;
}
 
//============================================================
// <T>结束标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTag::OnEnd(FTemplateContext* pContext){
   return ETemplateTagResult_Stop;
}

//============================================================
// <T>设置一个属性。</T>
//
// @param pName 属性名称
// @param pValue 属性内容
// @return 处理结果
//============================================================
TResult FTemplateTag::SetAttribute(TCharC* pName, TCharC* pValue){
   return ESuccess;
}

//============================================================
// <T>增加一个子标签。</T>
//
// @param pTag 子标签
// @return 处理结果
//============================================================
TResult FTemplateTag::Push(FTemplateTag* pTag){
   if(_pChildren == NULL){
      _pChildren = MO_CREATE(FTemplateTagCollection);
   }
   _pChildren->Push(pTag);
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @param pBuilder 构建器
// @return 处理结果
//============================================================
TResult FTemplateTag::Setup(FTemplateBuilder* pBuilder){
   MO_CHECK(pBuilder, return ENull);
   return ESuccess;
}

MO_NAMESPACE_END
