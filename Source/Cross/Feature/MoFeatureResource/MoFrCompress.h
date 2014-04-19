#ifndef __MO_FR_COMPRESS_H__
#define __MO_FR_COMPRESS_H__
//************************************************************

#ifndef __MO_FR_COMMON_H__
#include "MoFrCommon.h"
#endif // __MO_FR_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>LZMA压缩。</T>
//============================================================
class MO_FR_DECLARE RLzma{
public:
   static TInt CalculateDecodeLength(TByte* pData);
   static TInt DecodeData(TByte* pBuffer, TInt bufferLength, TByteC* pData, TInt dataLength);
   static TInt Decode(FBytes* pBuffer, TByte* pData, TInt dataLength);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FR_COMPRESS_H__
