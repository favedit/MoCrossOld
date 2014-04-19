#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造渲染来源。</T>
//============================================================
FRenderSource::FRenderSource(){
}

//============================================================
// <T>析构渲染来源。</T>
//============================================================
FRenderSource::~FRenderSource(){
}

//============================================================
// <T>追加注释信息。</T>
//
// @param pFormat 格式
// @param ... 参数
// @return 处理结果
//============================================================
TResult FRenderSource::AppendCommet(TChar8C* pFormat, ...){
   // 检查参数
   MO_CHECK(pFormat, return ENull);
   // 追加内容
   va_list params;
   va_start(params, pFormat);
   AppendFormatParameters(pFormat, params);
   va_end(params);
   return ESuccess;
}

//============================================================
// <T>追加代码信息。</T>
//
// @param pFormat 格式
// @param ... 参数
// @return 处理结果
//============================================================
TResult FRenderSource::AppendSource(TChar8C* pFormat, ...){
   // 检查参数
   MO_CHECK(pFormat, return ENull);
   // 追加内容
   va_list params;
   va_start(params, pFormat);
   AppendFormatParameters(pFormat, params);
   va_end(params);
   return ESuccess;
}

//============================================================
// <T>析构渲染来源。</T>
//
// @return 字符串
//============================================================
TCharC* FRenderSource::Build(){
   return MemoryC();
}

MO_NAMESPACE_END
