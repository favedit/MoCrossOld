#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param blendModeCd 默认内容
// @return 枚举内容
//============================================================
ERenderSampler RRenderSampler::Parse(TCharC* pValue, ERenderSampler samplerCd){
   if(pValue != NULL){
      TFsCode code = pValue;
      code.ToLower();
      if(code.Equals("ambient")){
         return ERenderSampler_Ambient;
      }else if(code.Equals("diffuse")){
         return ERenderSampler_Diffuse;
      }else if(code.Equals("alpha")){
         return ERenderSampler_Alpha;
      }else if(code.Equals("normal")){
         return ERenderSampler_Normal;
      }else if(code.Equals("height")){
         return ERenderSampler_Height;
      }else if(code.Equals("specularcolor")){
         return ERenderSampler_SpecularColor;
      }else if(code.Equals("specularlevel")){
         return ERenderSampler_SpecularLevel;
      }else if(code.Equals("transmittancecolor")){
         return ERenderSampler_TransmittanceColor;
      }else if(code.Equals("transmittancelevel")){
         return ERenderSampler_TransmittanceLevel;
      }else if(code.Equals("light")){
         return ERenderSampler_Light;
      }else if(code.Equals("reflect")){
         return ERenderSampler_Reflect;
      }else if(code.Equals("refract")){
         return ERenderSampler_Refract;
      }else if(code.Equals("emissive")){
         return ERenderSampler_Emissive;
      }else if(code.Equals("environment")){
         return ERenderSampler_Environment;
      }else if(code.Equals("layermerge")){
         return ERenderSampler_LayerMerge;
      }else if(code.Equals("layer1")){
         return ERenderSampler_Layer1;
      }else if(code.Equals("layer2")){
         return ERenderSampler_Layer2;
      }else if(code.Equals("layer3")){
         return ERenderSampler_Layer3;
      }else if(code.Equals("layer4")){
         return ERenderSampler_Layer4;
      }else if(code.Equals("packdiffuse")){
         return ERenderSampler_PackDiffuse;
      }else if(code.Equals("packnormal")){
         return ERenderSampler_PackNormal;
      }else if(code.Equals("packspecular")){
         return ERenderSampler_PackSpecular;
      }else if(code.Equals("packtransmittance")){
         return ERenderSampler_PackTransmittance;
      }else if(code.Equals("packlight")){
         return ERenderSampler_PackLight;
      }else if(code.Equals("lightdepth")){
         return ERenderSampler_LightDepth;
      }else{
         MO_STATIC_FATAL("Parse render sampler failure. (value=%s)", pValue);
      }
   }
   return samplerCd;
}

//============================================================
// <T>格式化枚举内容为字符串。</T>
//
// @param blendModeCd 枚举内容
// @return 字符串
//============================================================
TCharC* RRenderSampler::Format(ERenderSampler samplerCd){
   switch(samplerCd){
      case ERenderSampler_Ambient:
         return "Ambient";
      case ERenderSampler_Diffuse:
         return "Diffuse";
      case ERenderSampler_Alpha:
         return "Alpha";
      case ERenderSampler_Normal:
         return "Normal";
      case ERenderSampler_Height:
         return "Height";
      case ERenderSampler_SpecularColor:
         return "SpecularColor";
      case ERenderSampler_SpecularLevel:
         return "SpecularLevel";
      case ERenderSampler_TransmittanceColor:
         return "TransmittanceColor";
      case ERenderSampler_TransmittanceLevel:
         return "TransmittanceLevel";
      case ERenderSampler_Light:
         return "Light";
      case ERenderSampler_Reflect:
         return "Reflect";
      case ERenderSampler_Refract:
         return "Refract";
      case ERenderSampler_Emissive:
         return "Emissive";
      case ERenderSampler_Environment:
         return "Environment";
      case ERenderSampler_LayerMerge:
         return "LayerMerge";
      case ERenderSampler_Layer1:
         return "Layer1";
      case ERenderSampler_Layer2:
         return "Layer2";
      case ERenderSampler_Layer3:
         return "Layer3";
      case ERenderSampler_Layer4:
         return "Layer4";
      case ERenderSampler_PackDiffuse:
         return "PackDiffuse";
      case ERenderSampler_PackNormal:
         return "PackNormal";
      case ERenderSampler_PackSpecular:
         return "PackSpecular";
      case ERenderSampler_PackTransmittance:
         return "PackTransmittance";
      case ERenderSampler_PackLight:
         return "PackLight";
      case ERenderSampler_LightDepth:
         return "LightDepth";
      default:
         break;
   }
   return "Unknown";
}

MO_NAMESPACE_END
