#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param fillModeCd 默认内容
// @return 枚举内容
//============================================================
ERenderFillMode RRenderFillMode::Parse(TCharC* pValue, ERenderFillMode fillModeCd){
   if(pValue != NULL){
      TFsCode code = pValue;
      code.ToLower();
      if(code.Equals("point")){
         return ERenderFillMode_Point;
      }else if(code.Equals("line")){
         return ERenderFillMode_Line;
      }else if(code.Equals("fill")){
         return ERenderFillMode_Fill;
         MO_STATIC_FATAL("Parse fill mode failure. (value=%s)", pValue);
      }
   }
   return fillModeCd;
}

//============================================================
// <T>格式化枚举内容为字符串。</T>
//
// @param fillModeCd 枚举内容
// @return 字符串
//============================================================
TCharC* RRenderFillMode::Format(ERenderFillMode fillModeCd){
   switch (fillModeCd){
      case ERenderFillMode_Point:
         return "Point";
      case ERenderFillMode_Line:
         return "Line";
      case ERenderFillMode_Fill:
         return "Fill";
      default:
         break;
   }
   return "Unknown";
}

MO_NAMESPACE_END
