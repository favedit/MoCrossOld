#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderProgramSampler, FRenderInstance);

//============================================================
// <T>构造渲染器取样。</T>
//============================================================
FRenderProgramSampler::FRenderProgramSampler(){
   _statusUsed = EFalse;
   _slot = -1;
   _index = -1;
}

//============================================================
// <T>析构渲染器取样。</T>
//============================================================
FRenderProgramSampler::~FRenderProgramSampler(){
}

//============================================================
// <T>加载配置。</T>
//
// @param pConfig 配置节点
// @return 处理结果
//============================================================
TResult FRenderProgramSampler::LoadConfig(FXmlNode* pConfig){
   MO_CHECK(pConfig, return ENull);
   // 设置名称
   _name = pConfig->Get("name");
   // 设置关联
   _linker = pConfig->Get("linker");
   // 设置关联
   _source = pConfig->Get("source", NULL);
   return ESuccess;
}

//============================================================
// <T>获得内部运行信息。</T>
//
// @param pDump 输出缓冲
// @return 处理结果
//============================================================
TResult FRenderProgramSampler::Dump(MString* pDump, TBool detail){
   MO_CHECK(pDump, return ENull);
   pDump->AppendFormat("Sampler: name=%s, linker=%s, used=%d, slot=%d, index=%d", (TCharC*)_name, (TCharC*)_linker, _statusUsed, _slot, _index);
   return ESuccess;
}

MO_NAMESPACE_END
