#include "MoErCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param samplerCd 默认内容
// @return 枚举内容
//============================================================
TResult REffectParameterBuffer::Parse(TCharC* pValue, EEffectParameterBuffer& bufferCd, TInt& slot){
   if(pValue != NULL){
      TFsCode code = pValue;
      code.ToLower();
      if(code.Equals("global")){
         bufferCd = EEffectParameterBuffer_Global;
         slot = 0;
      }else if(code.Equals("effectcamera")){
         bufferCd = EEffectParameterBuffer_EffectCamera;
         slot = 1;
      }else if(code.Equals("effectlight")){
         bufferCd = EEffectParameterBuffer_EffectLight;
         slot = 2;
      }else if(code.Equals("rendertramsform")){
         bufferCd = EEffectParameterBuffer_RenderTramsform;
         slot = 3;
      }else if(code.Equals("rendermaterial")){
         bufferCd = EEffectParameterBuffer_RenderMaterial;
         slot = 4;
      }else{
         MO_STATIC_FATAL("Parse effect parameter buffer failure. (value=%s)", pValue);
      }
   }
   return ESuccess;
}

//============================================================
// <T>格式化枚举内容为字符串。</T>
//
// @param samplerCd 枚举内容
// @return 字符串
//============================================================
TCharC* REffectParameterBuffer::Format(EEffectParameterBuffer samplerCd){
   switch(samplerCd){
      case EEffectParameterBuffer_Global:
         return "Global";
      case EEffectParameterBuffer_EffectCamera:
         return "EffectCamera";
      case EEffectParameterBuffer_EffectLight:
         return "EffectLight";
      case EEffectParameterBuffer_RenderTramsform:
         return "RenderTramsform";
      case EEffectParameterBuffer_RenderMaterial:
         return "RenderMaterial";
      default:
         break;
   }
   return "Unknown";
}

MO_NAMESPACE_END
