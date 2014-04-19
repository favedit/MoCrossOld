#include "MoEgDecoder.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造位图资源工作器。</T>
//============================================================
FBitmapDecoder::FBitmapDecoder(){
   _pOutputStream = MO_CREATE(FByteStream);
}

//============================================================
// <T>析构位图资源工作器。</T>
//============================================================
FBitmapDecoder::~FBitmapDecoder(){
   MO_DELETE(_pOutputStream);
}

//============================================================
// <T>获取数据。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TBool FBitmapDecoder::Fetch(IOutput* pOutput){
   if(EDecoderResult_Success == _resultCd){
      TInt outputLength = _pOutputStream->Length();
      TInt length = pOutput->Write(_pOutputStream->MemoryC(), outputLength);
      if(length == outputLength){
         return ETrue;
      }
   }
   return EFalse;
}

MO_NAMESPACE_END
