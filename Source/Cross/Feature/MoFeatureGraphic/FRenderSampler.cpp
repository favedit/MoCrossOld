#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderSampler, FRenderObject);

//============================================================
// <T>构造渲染器取样。</T>
//============================================================
FRenderSampler::FRenderSampler(){
   _code = -1;
   _statusUsed = EFalse;
   _slot = -1;
   _samplerCode = ERenderSampler_Unknown;
   _packCode = ERenderSampler_Unknown;
}

//============================================================
// <T>析构渲染器取样。</T>
//============================================================
FRenderSampler::~FRenderSampler(){
}

//============================================================
// <T>加载配置。</T>
//
// @param pConfig 配置节点
// @return 处理结果
//============================================================
TResult FRenderSampler::LoadConfig(FXmlNode* pConfig){
   MO_CHECK(pConfig, return ENull);
   // 设置名称
   _name = pConfig->Get("name");
   // 设置关联
   _linker = pConfig->Get("linker");
   // 设置关联
   _source = pConfig->Get("source", NULL);
   // 解析内容
   _samplerCode = RRenderSampler::Parse(_linker);
   _packCode = RRenderSampler::ParsePack(_samplerCode);
   return ESuccess;
}

MO_NAMESPACE_END
