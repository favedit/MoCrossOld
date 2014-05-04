#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param bufferCd 默认内容
// @return 枚举内容
//============================================================
ERenderVertexBuffer RRenderVertexBuffer::Parse(TCharC* pValue, ERenderVertexBuffer bufferCd){
   if(pValue != NULL){
      TFsCode code = pValue;
      code.ToLower();
      if(code.Equals("instance")){
         return ERenderVertexBuffer_Instance;
      }else if(code.Equals("position")){
         return ERenderVertexBuffer_Position;
      }else if(code.Equals("color")){
         return ERenderVertexBuffer_Color;
      }else if(code.Equals("coord")){
         return ERenderVertexBuffer_Coord;
      }else if(code.Equals("normal")){
         return ERenderVertexBuffer_Normal;
      }else if(code.Equals("binormal")){
         return ERenderVertexBuffer_Binormal;
      }else if(code.Equals("tangent")){
         return ERenderVertexBuffer_Tangent;
      }else if(code.Equals("boneindex")){
         return ERenderVertexBuffer_BoneIndex;
      }else if(code.Equals("boneweight")){
         return ERenderVertexBuffer_BoneWeight;
      }else{
         MO_STATIC_FATAL("Parse vertex buffer failure. (value=%s)", pValue);
      }
   }
   return bufferCd;
}

//============================================================
// <T>格式化枚举内容为字符串。</T>
//
// @param bufferCd 枚举内容
// @return 字符串
//============================================================
TCharC* RRenderVertexBuffer::Format(ERenderVertexBuffer bufferCd){
   switch(bufferCd){
      case ERenderVertexBuffer_Instance:
         return "Instance";
      case ERenderVertexBuffer_Position:
         return "Position";
      case ERenderVertexBuffer_Color:
         return "Color";
      case ERenderVertexBuffer_Coord:
         return "Coord";
      case ERenderVertexBuffer_Normal:
         return "Normal";
      case ERenderVertexBuffer_Binormal:
         return "Binormal";
      case ERenderVertexBuffer_Tangent:
         return "Tangent";
      case ERenderVertexBuffer_BoneIndex:
         return "BoneIndex";
      case ERenderVertexBuffer_BoneWeight:
         return "BoneWeight";
      default:
         break;
   }
   return "Unknown";
}

MO_NAMESPACE_END
