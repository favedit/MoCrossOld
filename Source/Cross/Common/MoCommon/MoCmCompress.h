#ifndef __MO_CM_COMPRESS_H__
#define __MO_CM_COMPRESS_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_STREAM_H__
#include "MoCmPipe.h"
#endif // __MO_CM_STREAM_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>压缩数据工具。</T>
//============================================================
class MO_CM_DECLARE RCompress{
public:
   static TBool Deflate(TByte* pOutput, TInt outputCapacity, TByteC* pInput, TInt inputlength, TInt* pLength);
   static TBool Inflate(TByte* pOutput, TInt outputCapacity, TByteC* pInput, TInt inputlength, TInt* pLength);
public:
   static TBool CompressRLE(TByte* pOutput, TInt outputCapacity, TByteC* pInput, TInt inputlength, TInt* pLength);
   static TBool UncompressRLE(TByte* pOutput, TInt outputCapacity, TByteC* pInput, TInt inputlength, TInt* pLength);
};

MO_NAMESPACE_END

#endif // __MO_CM_COMPRESS_H__
