#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderProgramAttribute, FRenderInstance);

//============================================================
// <T>构造渲染器属性。</T>
//============================================================
FRenderProgramAttribute::FRenderProgramAttribute(){
   _index = -1;
   _slot = -1;
   _formatCd = ERenderAttributeFormat_Unknown;
   _statusUsed = EFalse;
}

//============================================================
// <T>析构渲染器属性。</T>
//============================================================
FRenderProgramAttribute::~FRenderProgramAttribute(){
}

//============================================================
// <T>加载配置。</T>
//
// @param pConfig 配置节点
// @return 处理结果
//============================================================
TResult FRenderProgramAttribute::LoadConfig(FXmlNode* pConfig){
   MO_CHECK(pConfig, return ENull);
   // 设置名称
   _name = pConfig->Get("name");
   // 设置关联
   _linker = pConfig->Get("linker");
   // 设置格式
   TCharC* pFormat = pConfig->Get("format", NULL);
   if(pFormat != NULL){
      _formatCd = RRenderAttributeFormat::Parse(pFormat);
   }
   return ESuccess;
}

//============================================================
// <T>获得内部运行信息。</T>
//
// @param pDump 输出缓冲
// @return 处理结果
//============================================================
TResult FRenderProgramAttribute::Dump(MString* pDump){
   MO_CHECK(pDump, return ENull);
   pDump->AppendFormat("Buffer: name=%s, linker=%s, slot=%d", (TCharC*)_name, (TCharC*)_linker, _slot);
   return ESuccess;
}

MO_NAMESPACE_END
