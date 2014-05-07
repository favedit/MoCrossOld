#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param bufferCd 默认内容
// @return 枚举内容
//============================================================
ERenderShaderBuffer RRenderShaderBuffer::Parse(TCharC* pValue, ERenderShaderBuffer bufferCd){
   if(pValue != NULL){
      TFsCode code = pValue;
      code.ToLower();
      if(code.Equals("globalstatic")){
         return ERenderShaderBuffer_GlobalStatic;
      }else if(code.Equals("globaldynamic")){
         return ERenderShaderBuffer_GlobalDynamic;
      }else if(code.Equals("techniquestatic")){
         return ERenderShaderBuffer_TechniqueStatic;
      }else if(code.Equals("techniquedynamic")){
         return ERenderShaderBuffer_TechniqueDynamic;
      }else if(code.Equals("effectstatic")){
         return ERenderShaderBuffer_EffectStatic;
      }else if(code.Equals("effectdynamic")){
         return ERenderShaderBuffer_EffectDynamic;
      }else if(code.Equals("renderabledynamic")){
         return ERenderShaderBuffer_RenderableDynamic;
      }else if(code.Equals("renderablematerial")){
         return ERenderShaderBuffer_RenderableMaterial;
      }else{
         MO_STATIC_FATAL("Parse shader buffer type failure. (value=%s)", pValue);
      }
   }
   return bufferCd;
}

//============================================================
// <T>解析缓冲为分组缓冲。</T>
//
// @param bufferCd 缓冲内容
// @return 枚举内容
//============================================================
ERenderShaderBuffer RRenderShaderBuffer::ParseGroup(ERenderShaderBuffer bufferCd){
   switch(bufferCd){
      case ERenderShaderBuffer_Global:
      case ERenderShaderBuffer_GlobalStatic:
      case ERenderShaderBuffer_GlobalDynamic:
         return ERenderShaderBuffer_Global;
      case ERenderShaderBuffer_Technique:
      case ERenderShaderBuffer_TechniqueStatic:
      case ERenderShaderBuffer_TechniqueDynamic:
         return ERenderShaderBuffer_Technique;
      case ERenderShaderBuffer_Effect:
      case ERenderShaderBuffer_EffectStatic:
      case ERenderShaderBuffer_EffectDynamic:
         return ERenderShaderBuffer_Effect;
      case ERenderShaderBuffer_Renderable:
      case ERenderShaderBuffer_RenderableDynamic:
      case ERenderShaderBuffer_RenderableMaterial:
         return ERenderShaderBuffer_Renderable;
      default:
         MO_STATIC_FATAL("Parse shader buffer group failure. (buffer=%d)", bufferCd);
   }
   return ERenderShaderBuffer_Unknown;
}

//============================================================
// <T>解析缓冲为插槽位置。</T>
//
// @param bufferCd 缓冲类型
// @return 插槽位置
//============================================================
TInt RRenderShaderBuffer::ParseSlot(ERenderShaderBuffer bufferCd){
   switch(bufferCd){
      case ERenderShaderBuffer_GlobalStatic:
         return 0;
      case ERenderShaderBuffer_GlobalDynamic:
         return 1;
      case ERenderShaderBuffer_TechniqueStatic:
         return 2;
      case ERenderShaderBuffer_TechniqueDynamic:
         return 3;
      case ERenderShaderBuffer_EffectStatic:
         return 4;
      case ERenderShaderBuffer_EffectDynamic:
         return 5;
      case ERenderShaderBuffer_RenderableDynamic:
         return 6;
      case ERenderShaderBuffer_RenderableMaterial:
         return 7;
      default:
         MO_STATIC_FATAL("Parse shader buffer group failure. (buffer=%d)", bufferCd);
   }
   return ERenderShaderBuffer_Unknown;
}

//============================================================
// <T>格式化枚举内容为字符串。</T>
//
// @param bufferCd 枚举内容
// @return 字符串
//============================================================
TCharC* RRenderShaderBuffer::Format(ERenderShaderBuffer bufferCd){
   switch(bufferCd){
      case ERenderShaderBuffer_GlobalStatic:
         return "GlobalStatic";
      case ERenderShaderBuffer_GlobalDynamic:
         return "GlobalDynamic";
      case ERenderShaderBuffer_TechniqueStatic:
         return "TechniqueStatic";
      case ERenderShaderBuffer_TechniqueDynamic:
         return "TechniqueDynamic";
      case ERenderShaderBuffer_EffectStatic:
         return "EffectStatic";
      case ERenderShaderBuffer_EffectDynamic:
         return "EffectDynamic";
      case ERenderShaderBuffer_RenderableDynamic:
         return "RenderableDynamic";
      case ERenderShaderBuffer_RenderableMaterial:
         return "RenderableMaterial";
   }
   return "Unknown";
}

MO_NAMESPACE_END
