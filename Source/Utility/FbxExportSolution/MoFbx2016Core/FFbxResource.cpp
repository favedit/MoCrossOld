#include "MoFbxResource.h"

MO_NAMESPACE_BEGIN;

//============================================================
// <T>构造资源对象。</T>
//============================================================
FFbxResource::FFbxResource() {
}

//============================================================
// <T>析构资源对象。</T>
//============================================================
FFbxResource::~FFbxResource() {
}

//============================================================
// <T>序列化内部数据到输出流。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult FFbxResource::Serialize(IDataOutput * pOutput){
   FFbxResourceComponent::Serialize(pOutput);
   return ESuccess;
}

//============================================================
// <T>保存内部数据到配置节点。</T>
//
// @param pConfig 配置节点
// @return 处理结果
//============================================================
TResult FFbxResource::SaveConfig(FXmlNode* pConfig){
   FFbxResourceComponent::SaveConfig(pConfig);
   return ESuccess;
}

MO_NAMESPACE_END;
