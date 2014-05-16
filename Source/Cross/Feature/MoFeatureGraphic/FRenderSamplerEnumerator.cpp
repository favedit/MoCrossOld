#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderSamplerEnumerator::Construct(){
   Register(ERenderSampler_Unknown,            "Unknown");
   Register(ERenderSampler_Ambient,            "Ambient");
   Register(ERenderSampler_Diffuse,            "Diffuse");
   Register(ERenderSampler_Alpha,              "Alpha");
   Register(ERenderSampler_Normal,             "Normal");
   Register(ERenderSampler_Height,             "Height");
   Register(ERenderSampler_SpecularColor,      "SpecularColor");
   Register(ERenderSampler_SpecularLevel,      "SpecularLevel");
   Register(ERenderSampler_TransmittanceColor, "TransmittanceColor");
   Register(ERenderSampler_TransmittanceLevel, "TransmittanceLevel");
   Register(ERenderSampler_Light,              "Light");
   Register(ERenderSampler_Reflect,            "Reflect");
   Register(ERenderSampler_Refract,            "Refract");
   Register(ERenderSampler_Emissive,           "Emissive");
   Register(ERenderSampler_Environment,        "Environment");
   Register(ERenderSampler_LayerMerge,         "LayerMerge");
   Register(ERenderSampler_Layer1,             "Layer1");
   Register(ERenderSampler_Layer2,             "Layer2");
   Register(ERenderSampler_Layer3,             "Layer3");
   Register(ERenderSampler_Layer4,             "Layer4");
   Register(ERenderSampler_PackDiffuse,        "PackDiffuse");
   Register(ERenderSampler_PackNormal,         "PackNormal");
   Register(ERenderSampler_PackSpecular,       "PackSpecular");
   Register(ERenderSampler_PackTransmittance,  "PackTransmittance");
   Register(ERenderSampler_PackLight,          "PackLight");
   Register(ERenderSampler_LightDepth,         "LightDepth");
   return ESuccess;
}

//============================================================
// <T>根据取样器获得打包取样器。</T>
//
// @param samplerCd 枚举内容
// @return 枚举内容
//============================================================
TInt FRenderSamplerEnumerator::ParsePack(TInt samplerCd){
   switch(samplerCd){
      case ERenderSampler_Diffuse:
      case ERenderSampler_Alpha:
      case ERenderSampler_PackDiffuse:
         break;
      case ERenderSampler_Normal:
      case ERenderSampler_Height:
      case ERenderSampler_PackNormal:
         break;
      case ERenderSampler_SpecularColor:
      case ERenderSampler_SpecularLevel:
      case ERenderSampler_PackSpecular:
         break;
      case ERenderSampler_TransmittanceColor:
      case ERenderSampler_TransmittanceLevel:
      case ERenderSampler_PackTransmittance:
         break;
      case ERenderSampler_Light:
      case ERenderSampler_Reflect:
      case ERenderSampler_Refract:
      case ERenderSampler_Emissive:
      case ERenderSampler_PackLight:
         break;
   }
   return samplerCd;
}

MO_NAMESPACE_END
