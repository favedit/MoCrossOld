#include "MoEgDecoder.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造资源工作器。</T>
//============================================================
FDecoder::FDecoder(){
   _code = 0;
   _resultCd = EDecoderResult_Unknown;
}

//============================================================
// <T>析构资源工作器。</T>
//============================================================
FDecoder::~FDecoder(){
   MO_TRACK(_tester.Record(), TC("Release decoder. (code=%d, result=%d)"), _code, _resultCd);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FDecoder::UnserializeInfo(IDataInput* pInput){
   return ETrue;
}

//============================================================
// <T>从输入流里反序列化数据内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FDecoder::UnserializeData(IDataInput* pInput){
   return ETrue;
}

//============================================================
// <T>完成处理。</T>
//
// @return 处理结果
//============================================================
TBool FDecoder::Complete(){
   return ETrue;
}

//============================================================
// <T>获取数据。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TBool FDecoder::Fetch(IOutput* pOutput){
   MO_ASSERT(pOutput);
   return ETrue;
}

//============================================================
// <T>完成处理。</T>
//
// @return 处理结果
//============================================================
TBool FDecoder::Decode(IOutput* pOutput){
   Process();
   Complete();
   return Fetch(pOutput);
}

MO_NAMESPACE_END
