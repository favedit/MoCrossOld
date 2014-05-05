#include "MoErCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造效果取样器描述集合。</T>
//============================================================
TEffectSamplerDescriptors::TEffectSamplerDescriptors(){
   _count = _capacity;
}

//============================================================
// <T>注册一个描述信息。</T>
//
// @param code 代码
// @param pName 名称
// @param samplerCd 取样类型
//============================================================
void TEffectSamplerDescriptors::Register(TInt code, TCharC* pName, ERenderSampler samplerCd){
   SEffectSamplerDescriptor descriptor;
   descriptor.code = code;
   descriptor.namePtr = pName;
   descriptor.samplerCd = samplerCd;
   Push(descriptor);
}

//============================================================
// <T>注册一个描述信息。</T>
//
// @param pLinker 关联信息
//============================================================
TResult TEffectSamplerDescriptors::Link(FRenderShaderSampler* pSampler){
   MO_CHECK(pSampler, return ENull);
   TCharC* pLinker = pSampler->Linker();
   // 解析内容
   ERenderSampler samplerCd = RRenderSampler::Parse(pLinker);
   ERenderSampler packCd = RRenderSampler::ParsePack(samplerCd);
   // 设置参数
   SEffectSamplerDescriptor& descriptor = _memory[packCd];
   descriptor.samplerPtr = pSampler;
   descriptor.code = samplerCd;
   descriptor.namePtr = pLinker;
   descriptor.samplerCd = samplerCd;
   descriptor.bindId = pSampler->Slot();
   return ESuccess;
}

//============================================================
SEffectSamplerDescriptor* TEffectSamplerDescriptors::FindByBindId(TInt bindId){
   for(TInt n = 0; n < MO_EG_EFFECT_SAMPLER_MAXCNT; n++){
      SEffectSamplerDescriptor& descriptor = _memory[n];
      if(descriptor.bindId == bindId){
         return &descriptor;
      }
   }
   return NULL;
}

MO_NAMESPACE_END
