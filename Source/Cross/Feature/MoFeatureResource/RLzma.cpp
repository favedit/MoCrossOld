#include <LzmaDec.h>
#include <LzmaLib.h>
#include "MoFrCompress.h"

MO_NAMESPACE_BEGIN

#define MO_LZMA_PROP_COUNT 5

//============================================================
#pragma pack(1)
struct SLzmaContent{
   TByte  properties[MO_LZMA_PROP_COUNT];
   TInt64 length;
   TByte  data[1];
};
#pragma pack()

static void *SzAlloc(void *p, size_t size) { p = p; return malloc(size); }
static void SzFree(void *p, void *address) { p = p; free(address); }
static ISzAlloc g_Alloc = { SzAlloc, SzFree };

//============================================================
// <T>获得解压后长度。</T>
//
// @param pData 数据
// @return 解压后长度
//============================================================
TInt RLzma::CalculateDecodeLength(TByte* pData){
   // 检查参数
   if(NULL == pData){
      return -1;
   }
   //............................................................
   // 读取头信息
   SLzmaContent* pContent = (SLzmaContent*)pData;
   TInt length = (TInt)pContent->length;
   //............................................................
   // 返回内容
   return length;
}

//============================================================
// <T>解压操作。</T>
//
// @param pInput 输入流
// @param pOutput 输出流
// @return 长度
//============================================================
TInt RLzma::DecodeData(TByte* pBuffer, TInt bufferLength, TByteC* pData, TInt dataLength){
#ifdef _MO_DEBUG
   TSpeedTest speed;
#endif
   // 检查参数
   if(NULL == pBuffer){
      return EFalse;
   }
   if(NULL == pData){
      return EFalse;
   }
   //............................................................
   // 读取头信息
   SLzmaContent* pContent = (SLzmaContent*)pData;
   TByte* pProperties = (TByte*)pContent->properties;
   SizeT sourceLength = dataLength;
   SizeT targetLength = (SizeT)pContent->length;
   TByte* pSourceData = (TByte*)pContent->data;
   // LZMA解压
   ELzmaStatus statusCd;
   SRes resultCd = LzmaDecode(pBuffer, &targetLength, pSourceData, &sourceLength, pProperties, MO_LZMA_PROP_COUNT, LZMA_FINISH_END, &statusCd, &g_Alloc);
   if(SZ_OK == resultCd){
      MO_STATIC_TRACK(speed.Record(), TC("Lzma decompress. (input_ptr=0x%08X, input_length=%d, output_ptr=0x%08X, output_length=%d, decode_length=%d->%d, result_cd=%d)"),
         pData, dataLength, pBuffer, bufferLength, sourceLength, targetLength, resultCd);
   }else{
      MO_STATIC_ERROR(TC("Lzma decompress. (input_ptr=0x%08X, input_length=%d, output_ptr=0x%08X, output_length=%d, decode_length=%d->%d, result_cd=%d)"),
         pData, dataLength, pBuffer, bufferLength, sourceLength, targetLength, resultCd);
   }
   //............................................................
   // 返回内容
   return resultCd;
}

//============================================================
// <T>解压操作。</T>
//
// @param pInput 输入流
// @param pOutput 输出流
// @return 长度
//============================================================
TInt RLzma::Decode(FBytes* pBuffer, TByte* pData, TInt dataLength){
#ifdef _MO_DEBUG
   TSpeedTest speed;
#endif
   // 检查参数
   if(NULL == pBuffer){
      return EFailure;
   }
   if(NULL == pData){
      return EFailure;
   }
   //............................................................
   // 设置缓冲
   TInt targetLength = CalculateDecodeLength(pData);
   pBuffer->ForceSize(targetLength);
   // LZMA解压
   TInt result = DecodeData(pBuffer->Memory(), targetLength, pData, dataLength);
   //............................................................
   // 返回内容
   return result;
}

MO_NAMESPACE_END
