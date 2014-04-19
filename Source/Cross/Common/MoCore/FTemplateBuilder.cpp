#include "MoCrTemplate.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplateBuilder, FConfigBuilder);

//============================================================
// <T>创建配置构建器。</T>
//============================================================
FTemplateBuilder::FTemplateBuilder(){
   MO_CLEAR(_pConsole);
   MO_CLEAR(_pContext);
   MO_CLEAR(_pTemplate);
}

//============================================================
// <T>析构配置构建器。</T>
//============================================================
FTemplateBuilder::~FTemplateBuilder(){
}

//============================================================
// <T>设置名称。</T>
//
// @param pInstance 实例
// @param pName 名称
// @return 处理结果
//============================================================
TResult FTemplateBuilder::SetName(FInstance* pInstance, TCharC* pName){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pName, return ENull);
   FTemplateTag* pTag = pInstance->Convert<FTemplateTag>();
   pTag->SetName(pName);
   return ESuccess;
}

//============================================================
// <T>设置内容。</T>
//
// @param pInstance 实例
// @param pText 内容
// @return 处理结果
//============================================================
TResult FTemplateBuilder::SetText(FInstance* pInstance, TCharC* pText){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pText, return ENull);
   FTemplateTagText* pTag = pInstance->Convert<FTemplateTagText>();
   pTag->SetText(pText);
   return ESuccess;
}

//============================================================
// <T>设置属性名称和内容。</T>
//
// @param pInstance 实例
// @param pName 属性名称
// @param pValue 属性内容
// @return 处理结果
//============================================================
TResult FTemplateBuilder::Set(FInstance* pInstance, TCharC* pName, TCharC* pValue){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pName, return ENull);
   FTemplateTag* pTag = pInstance->Convert<FTemplateTag>();
   pTag->SetAttribute(pName, pValue);
   return ESuccess;
}

//============================================================
// <T>增加一个子设置节点。</T>
//
// @param pInstance 实例
// @param pChildInstance 子实例
// @return 处理结果
//============================================================
TResult FTemplateBuilder::Push(FInstance* pInstance, FInstance* pChildInstance){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pChildInstance, return ENull);
   FTemplateTag* pTag = pInstance->Convert<FTemplateTag>();
   FTemplateTag* pChildTag = pChildInstance->Convert<FTemplateTag>();
   pTag->Push(pChildTag);
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @param pInstance 实例
// @return 处理结果
//============================================================
TResult FTemplateBuilder::Setup(FInstance* pInstance){
   MO_CHECK(pInstance, return ENull);
   FTemplateTag* pTag = pInstance->Convert<FTemplateTag>();
   pTag->Setup(this);
   return ESuccess;
}

MO_NAMESPACE_END
