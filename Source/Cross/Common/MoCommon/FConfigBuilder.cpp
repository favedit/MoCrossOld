#include "MoCmConfig.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FConfigBuilder, FInstance);

//============================================================
// <T>创建配置构建器。</T>
//============================================================
FConfigBuilder::FConfigBuilder(){
}

//============================================================
// <T>析构配置构建器。</T>
//============================================================
FConfigBuilder::~FConfigBuilder(){
}

//============================================================
// <T>设置名称。</T>
//
// @param pInstance 实例
// @param pName 名称
// @return 处理结果
//============================================================
TResult FConfigBuilder::SetName(FInstance* pInstance, TCharC* pName){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>设置内容。</T>
//
// @param pInstance 实例
// @param pText 内容
// @return 处理结果
//============================================================
TResult FConfigBuilder::SetText(FInstance* pInstance, TCharC* pText){
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
TResult FConfigBuilder::Set(FInstance* pInstance, TCharC* pName, TCharC* pValue){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>增加一个子设置节点。</T>
//
// @param pInstance 实例
// @param pChildInstance 子实例
// @return 处理结果
//============================================================
TResult FConfigBuilder::Push(FInstance* pInstance, FInstance* pChildInstance){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @param pInstance 实例
// @return 处理结果
//============================================================
TResult FConfigBuilder::Setup(FInstance* pInstance){
   return ESuccess;
}

MO_NAMESPACE_END
