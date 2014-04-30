#include "MoEoRender.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>转换合成方式。</T>
//
// @param blendCd 合成方式
// @return 处理结果
//============================================================
GLenum ROpenGLES2::ConvertBlendFactors(ERenderBlendMode blendCd){
   switch(blendCd){
      case ERenderBlendMode_SourceAlpha:
         return GL_SRC_ALPHA;
      case ERenderBlendMode_OneMinusSourceAlpha:
         return GL_ONE_MINUS_SRC_ALPHA;
      default:
         break;
   }
   MO_STATIC_FATAL("Convert blend factors failure. (blend=%d)", blendCd);
   return 0;
}

//============================================================
// <T>转换索引宽度类型。</T>
//
// @param strideCd 索引宽度
// @return 处理结果
//============================================================
GLenum ROpenGLES2::ConvertIndexStride(ERenderIndexStride strideCd){
   switch(strideCd){
      case ERenderIndexStride_Uint16:
         return GL_UNSIGNED_SHORT;
      case ERenderIndexStride_Uint32:
         return GL_UNSIGNED_INT;
   }
   MO_STATIC_FATAL("Convert index stride failure. (stride=%d)", strideCd);
   return 0;
}

MO_NAMESPACE_END
