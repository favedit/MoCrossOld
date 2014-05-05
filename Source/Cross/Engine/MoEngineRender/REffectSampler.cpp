#include "MoErCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param samplerCd 默认内容
// @return 枚举内容
//============================================================
EEffectSampler REffectSampler::Parse(TCharC* pValue, EEffectSampler samplerCd){
   if(pValue != NULL){
      TFsCode code = pValue;
      code.ToLower();
      if(code.Equals("diffuse")){
         return EEffectSampler_Diffuse;
      }else if(code.Equals("normal")){
         return EEffectSampler_Normal;
      }else if(code.Equals("specular")){
         return EEffectSampler_Specular;
      }else if(code.Equals("light")){
         return EEffectSampler_Light;
      }else if(code.Equals("environment")){
         return EEffectSampler_Environment;
      }else if(code.Equals("lightdepth")){
         return EEffectSampler_LightDepth;
      }else{
         MO_STATIC_FATAL("Parse effect sampler failure. (value=%s)", pValue);
      }
   }
   return samplerCd;
}

//============================================================
// <T>格式化枚举内容为字符串。</T>
//
// @param samplerCd 枚举内容
// @return 字符串
//============================================================
TCharC* REffectSampler::Format(EEffectSampler samplerCd){
   switch(samplerCd){
      case EEffectSampler_Diffuse:
         return "Diffuse";
      case EEffectSampler_Normal:
         return "Normal";
      case EEffectSampler_Specular:
         return "Specular";
      case EEffectSampler_Light:
         return "Light";
      case EEffectSampler_Environment:
         return "Environment";
      case EEffectSampler_LightDepth:
         return "LightDepth";
      default:
         break;
   }
   return "Unknown";
}

MO_NAMESPACE_END
