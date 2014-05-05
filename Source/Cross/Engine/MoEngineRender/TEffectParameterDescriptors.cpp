#include "MoErCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造效果参数描述集合。</T>
//============================================================
TEffectParameterDescriptors::TEffectParameterDescriptors(){
   _count = _capacity;
}

//============================================================
// <T>注册一个参数信息。</T>
//
// @param pParameter 参数
//============================================================
TResult TEffectParameterDescriptors::Link(FRenderShaderParameter* pParameter){
   MO_CHECK(pParameter, return ENull);
   TCharC* pLinker = pParameter->Linker();
   // 解析内容
   EEffectParameter parameterCd = EEffectParameter_Unknown;
   ERenderShader shaderCd = ERenderShader_Unknown;
   ERenderShaderParameterFormat formatCd = ERenderShaderParameterFormat_Unknown;
   REffectParameter::Parse(pLinker, parameterCd, shaderCd, formatCd);
   // 设置参数
   SEffectParameterDescriptor& descriptor = _memory[parameterCd];
   descriptor.parameterPtr = pParameter;
   descriptor.code = parameterCd;
   descriptor.namePtr = pLinker;
   descriptor.bindId = -1;
   descriptor.shaderCd = shaderCd;
   return ESuccess;
}

MO_NAMESPACE_END
