#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderShaderAttribute, FRenderObject);

//============================================================
// <T>构造渲染器属性。</T>
//============================================================
FRenderShaderAttribute::FRenderShaderAttribute(){
   _index = -1;
   _slot = -1;
   _formatCd = ERenderShaderAttributeFormat_Unknown;
   _statusUsed = EFalse;
}

//============================================================
// <T>析构渲染器属性。</T>
//============================================================
FRenderShaderAttribute::~FRenderShaderAttribute(){
}

//============================================================
// <T>加载配置。</T>
//
// @param pConfig 配置节点
// @return 处理结果
//============================================================
TResult FRenderShaderAttribute::LoadConfig(FXmlNode* pConfig){
   MO_CHECK(pConfig, return ENull);
   // 设置名称
   _name = pConfig->Get("name");
   // 设置关联
   _linker = pConfig->Get("linker");
   // 设置格式
   TCharC* pFormat = pConfig->Get("format", NULL);
   if(pFormat != NULL){
      _formatCd = RRenderShaderAttributeFormat::Parse(pFormat);
   }
   return ESuccess;
}

MO_NAMESPACE_END
