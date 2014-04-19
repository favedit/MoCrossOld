#include "MoCmXml.h"
#include "MoCmFormat.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FXmlBuilder, FConfigBuilder);

//============================================================
// <T>创建配置构建器。</T>
//============================================================
FXmlBuilder::FXmlBuilder(){
}

//============================================================
// <T>析构配置构建器。</T>
//============================================================
FXmlBuilder::~FXmlBuilder(){
}

//============================================================
// <T>设置名称。</T>
//
// @param pInstance 实例
// @param pName 名称
// @return 处理结果
//============================================================
TResult FXmlBuilder::SetName(FInstance* pInstance, TCharC* pName){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pName, return ENull);
   FXmlNode* pNode = pInstance->Convert<FXmlNode>();
   pNode->SetName(pName);
   return ESuccess;
}

//============================================================
// <T>设置内容。</T>
//
// @param pInstance 实例
// @param pText 内容
// @return 处理结果
//============================================================
TResult FXmlBuilder::SetText(FInstance* pInstance, TCharC* pText){
   MO_CHECK(pInstance, return ENull);
   if(pText != NULL){
      FXmlNode* pNode = pInstance->Convert<FXmlNode>();
      pNode->SetText(pText);
   }
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
TResult FXmlBuilder::Set(FInstance* pInstance, TCharC* pName, TCharC* pValue){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pName, return ENull);
   FXmlNode* pNode = pInstance->Convert<FXmlNode>();
   pNode->Set(pName, pValue);
   return ESuccess;
}

//============================================================
// <T>增加一个子设置节点。</T>
//
// @param pInstance 实例
// @param pChildInstance 子实例
// @return 处理结果
//============================================================
TResult FXmlBuilder::Push(FInstance* pInstance, FInstance* pChildInstance){
   MO_CHECK(pInstance, return ENull);
   MO_CHECK(pChildInstance, return ENull);
   FXmlNode* pNode = pInstance->Convert<FXmlNode>();
   FXmlNode* pChildNode = pChildInstance->Convert<FXmlNode>();
   pNode->Push(pChildNode);
   return ESuccess;
}

MO_NAMESPACE_END
