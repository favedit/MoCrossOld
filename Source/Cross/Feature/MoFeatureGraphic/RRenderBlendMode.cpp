#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param blendModeCd 默认内容
// @return 枚举内容
//============================================================
ERenderBlendMode RRenderBlendMode::Parse(TCharC* pValue, ERenderBlendMode blendModeCd){
   if(pValue != NULL){
      TFsCode code = pValue;
      code.ToLower();
      if(code.Equals("none")){
         return ERenderBlendMode_None;
      }else if(code.Equals("sourcealpha")){
         return ERenderBlendMode_SourceAlpha;
      }else if(code.Equals("oneminussourcealpha")){
         return ERenderBlendMode_OneMinusSourceAlpha;
      }else{
         MO_STATIC_FATAL("Parse blend mode failure. (value=%s)", pValue);
      }
   }
   return blendModeCd;
}

//============================================================
// <T>格式化枚举内容为字符串。</T>
//
// @param blendModeCd 枚举内容
// @return 字符串
//============================================================
TCharC* RRenderBlendMode::Format(ERenderBlendMode blendModeCd){
   switch(blendModeCd){
      case ERenderBlendMode_None:
         return "None";
      case ERenderBlendMode_SourceAlpha:
         return "SourceAlpha";
      case ERenderBlendMode_OneMinusSourceAlpha:
         return "OneMinusSourceAlpha";
      default:
         break;
   }
   return "Unknown";
}

MO_NAMESPACE_END
