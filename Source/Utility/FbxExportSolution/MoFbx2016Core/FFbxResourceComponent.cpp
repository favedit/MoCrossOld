#include "MoFbxResource.h"

MO_NAMESPACE_BEGIN;

//============================================================
// <T>构造资源对象。</T>
//============================================================
FFbxResourceComponent::FFbxResourceComponent() {
}

//============================================================
// <T>析构资源对象。</T>
//============================================================
FFbxResourceComponent::~FFbxResourceComponent() {
}

//============================================================
// <T>序列化内部数据到输出流。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult FFbxResourceComponent::Serialize(IDataOutput * pOutput){
   FFbxResourceObject::Serialize(pOutput);
   pOutput->WriteString(_guid);
   pOutput->WriteString(_code);
   pOutput->WriteString(_label);
   return ESuccess;
}

//============================================================
// <T>保存内部数据到配置节点。</T>
//
// @param pConfig 配置节点
// @return 处理结果
//============================================================
TResult FFbxResourceComponent::SaveConfig(FXmlNode* pConfig){
   pConfig->SetName(_typeName);
   pConfig->SetNvl(TC("guid"), _guid);
   pConfig->SetNvl(TC("code"), _code);
   pConfig->SetNvl(TC("label"), _label);
   return ESuccess;
}

MO_NAMESPACE_END;
