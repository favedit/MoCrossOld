#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderStatistics, FStatistics);

//============================================================
// <T>构造渲染统计器。</T>
//============================================================
FRenderStatistics::FRenderStatistics(){
   Reset();
}

//============================================================
// <T>析构渲染统计器。</T>
//============================================================
FRenderStatistics::~FRenderStatistics(){
}

//============================================================
// <T>更新设置填充次数。</T>
//============================================================
void FRenderStatistics::UpdateOptionFillCount(){
   _optionFillCount++;
}

//============================================================
// <T>更新设置深度次数。</T>
//============================================================
void FRenderStatistics::UpdateOptionDeepCount(){
   _optionDeepCount++;
}

//============================================================
// <T>更新设置剪裁次数。</T>
//============================================================
void FRenderStatistics::UpdateOptionCullCount(){
   _optionCullCount++;
}

//============================================================
// <T>更新设置融合次数。</T>
//============================================================
void FRenderStatistics::UpdateOptionBlendCount(){
   _optionBlendCount++;
}

//============================================================
// <T>更新程序次数。</T>
//============================================================
void FRenderStatistics::UpdateProgramCount(){
   _programCount++;
}

//============================================================
// <T>更新程序常量次数。</T>
//
// @param constLength 常量长度
//============================================================
void FRenderStatistics::UpdateProgramCount(TInt constLength){
   _programConstLength += constLength;
   _programConstCount++;
}

//============================================================
// <T>更新绘制次数。</T>
//
// @param faceCount 面总数
//============================================================
void FRenderStatistics::UpdateDraw(TInt faceCount){
   _drawFaceCount += faceCount;
   _drawCount++;
}

//============================================================
// <T>更新顶点缓冲次数。</T>
//============================================================
void FRenderStatistics::UpdateVertexBufferCount(){
   _vertexBufferCount++;
}

//============================================================
// <T>更新取样器次数。</T>
//============================================================
void FRenderStatistics::UpdateSamplerCount(){
   _samplerCount++;
}

//============================================================
// <T>重置处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderStatistics::Reset(){
   TResult result = FStatistics::Reset();
   _optionFillCount = 0;
   _optionDeepCount = 0;
   _optionCullCount = 0;
   _optionBlendCount = 0;
   _programCount = 0;
   _programConstLength = 0;
   _programConstCount = 0;
   _vertexBufferCount = 0;
   _samplerCount = 0;
   _drawFaceCount = 0;
   _drawCount = 0;
   return result;
}

//============================================================
// <T>获得跟踪信息。</T>
//
// @return 处理结果
//============================================================
TResult FRenderStatistics::Dump(MString* pDump){
   pDump->AppendFormat("Render statistics info: draw_face_count=%d, draw_count=%d\n", _drawFaceCount, _drawCount);
   pDump->AppendFormat("   status: program=%d, fill=%d, deep=%d, cull=%d, blend=%d\n", _programCount, _optionFillCount, _optionDeepCount, _optionCullCount, _optionBlendCount);
   pDump->AppendFormat("   const : const_length=%d, const_length=%d\n", _programConstLength, _programConstCount);
   pDump->AppendFormat("   object: vertex_count=%d, sampler_count=%d", _vertexBufferCount, _samplerCount);
   return ESuccess;
}

MO_NAMESPACE_END
