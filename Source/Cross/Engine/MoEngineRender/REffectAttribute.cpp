#include "MoErCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param attributeCd 属性类型
// @param formatCd 格式类型
// @return 枚举内容
//============================================================
TResult REffectAttribute::Parse(TCharC* pValue, EEffectAttribute& attributeCd, ERenderShaderAttributeFormat& formatCd){
   MO_CHECK(pValue, return ENull);
   TFsCode code = pValue;
   code.ToLower();
   if(code.Equals("unknown")){
      attributeCd = EEffectAttribute_Unknown;
      formatCd = ERenderShaderAttributeFormat_Float4;
   }else if(code.Equals("instance")){
      attributeCd = EEffectAttribute_Instance;
      formatCd = ERenderShaderAttributeFormat_Float1;
   }else if(code.Equals("position")){
      attributeCd = EEffectAttribute_Position;
      formatCd = ERenderShaderAttributeFormat_Float3;
   }else if(code.Equals("color")){
      attributeCd = EEffectAttribute_Color;
      formatCd = ERenderShaderAttributeFormat_Byte4Normal;
   }else if(code.Equals("coord")){
      attributeCd = EEffectAttribute_Coord;
      formatCd = ERenderShaderAttributeFormat_Float2;
   }else if(code.Equals("normal")){
      attributeCd = EEffectAttribute_Normal;
      formatCd = ERenderShaderAttributeFormat_Byte4Normal;
   }else if(code.Equals("binormal")){
      attributeCd = EEffectAttribute_Binormal;
      formatCd = ERenderShaderAttributeFormat_Byte4Normal;
   }else if(code.Equals("tangent")){
      attributeCd = EEffectAttribute_Tangent;
      formatCd = ERenderShaderAttributeFormat_Byte4Normal;
   }else if(code.Equals("boneindex")){
      attributeCd = EEffectAttribute_BoneIndex;
      formatCd = ERenderShaderAttributeFormat_Byte4;
   }else if(code.Equals("boneweight")){
      attributeCd = EEffectAttribute_BoneWeight;
      formatCd = ERenderShaderAttributeFormat_Byte4Normal;
   }else{
      MO_STATIC_FATAL("Parse effect parameter failure. (value=%s)", pValue);
      return EFailure;
   }
   return ESuccess;
}

//============================================================
// <T>格式化枚举内容为字符串。</T>
//
// @param attributeCd 枚举内容
// @return 字符串
//============================================================
TCharC* REffectAttribute::Format(EEffectAttribute attributeCd){
   switch(attributeCd){
      case EEffectAttribute_Instance:
         return "Instance";
      case EEffectAttribute_Position:
         return "Position";
      case EEffectAttribute_Color:
         return "Color";
      case EEffectAttribute_Coord:
         return "Coord";
      case EEffectAttribute_Normal:
         return "Normal";
      case EEffectAttribute_Binormal:
         return "Binormal";
      case EEffectAttribute_Tangent:
         return "Tangent";
      case EEffectAttribute_BoneIndex:
         return "BoneIndex";
      case EEffectAttribute_BoneWeight:
         return "BoneWeight";
   }
   return "Unknown";
}

MO_NAMESPACE_END
