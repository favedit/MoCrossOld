#include "MoErTechnique.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造效果取样器描述集合。</T>
//============================================================
TEffectSamplerDescriptors::TEffectSamplerDescriptors(){
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
