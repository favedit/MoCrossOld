#include "MoCmType.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>判断字符串是否为数字。</T>
//
// @param pSource 字符串
// @return 是否为数字
//============================================================
TBool RUint32::Test(TCharC* pSource){
   return RNumber<TUint32>::IsUnsignInteger<TChar>(pSource);
}

//============================================================
// <T>解析十进制8位字符串为无符号32位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号32位整数
//============================================================
TUint32 RUint32::Parse8(TChar8C* pSource){
   return RNumber<TUint32>::ParseUnsign<TChar8>(pSource);
}

//============================================================
// <T>解析十进制16位字符串为无符号32位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号32位整数
//============================================================
TUint32 RUint32::Parse16(TChar16C* pSource){
   return RNumber<TUint32>::ParseUnsign<TChar16>(pSource);
}

//============================================================
// <T>解析十进制32位字符串为无符号32位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号32位整数
//============================================================
TUint32 RUint32::Parse32(TChar32C* pSource){
   return RNumber<TUint32>::ParseUnsign<TChar32>(pSource);
}

//============================================================
// <T>解析十进制字符串为无符号32位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号32位整数
//============================================================
TUint32 RUint32::Parse(TCharC* pSource){
   return RNumber<TUint32>::ParseUnsign<TChar>(pSource);
}

//============================================================
// <T>解析十进制字符串为无符号32位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号32位整数
//============================================================
TUint32 RUint32::ParseNvl(TCharC* pSource){
   if(NULL != pSource){
      return RNumber<TUint32>::ParseUnsign<TChar>(pSource, 0);
   }
   return 0;
}

//============================================================
// <T>解析16进制字符串。</T>
//
// @param pSource 来源
// @param length 长度
// @return 内容
//============================================================
TUint32 RUint32::ParseHex(TCharC* pSource, TInt length){
   MO_ASSERT(pSource);
   TUint32 v0 = ((TUint32)RByte::HEX_BYTES[(TInt)pSource[0]]) << 28;
   TUint32 v1 = ((TUint32)RByte::HEX_BYTES[(TInt)pSource[1]]) << 24;
   TUint32 v2 = ((TUint32)RByte::HEX_BYTES[(TInt)pSource[2]]) << 20;
   TUint32 v3 = ((TUint32)RByte::HEX_BYTES[(TInt)pSource[3]]) << 16;
   TUint32 v4 = ((TUint32)RByte::HEX_BYTES[(TInt)pSource[4]]) << 12;
   TUint32 v5 = ((TUint32)RByte::HEX_BYTES[(TInt)pSource[5]]) <<  8;
   TUint32 v6 = ((TUint32)RByte::HEX_BYTES[(TInt)pSource[6]]) <<  4;
   TUint32 v7 = ((TUint32)RByte::HEX_BYTES[(TInt)pSource[7]])      ;
   return v0 | v1 | v2 | v3 | v4 | v5 | v6 | v7;
}

//============================================================
// <T>解析16进制字符串。</T>
//
// @param pSource 来源
// @param length 长度
// @return 内容
//============================================================
TUint32 RUint32::ParseHexNvl(TCharC* pSource, TInt length){
   if(NULL != pSource){
      return ParseHex(pSource, length);
   }
   return 0;
}

//============================================================
// <T>从无符号32位整数解析到十进制字符串。</T>
//
// @param pBuffer 缓冲字符串
// @param capacity 缓冲字符串容量
// @param value 无符号32位整数
// @return 十进制字符串    
//============================================================
TCharC* RUint32::ToString(TUint32 value, TChar* pBuffer, TInt capacity){
   MO_ASSERT(pBuffer);
   return RNumber<TUint32>::ToUnsignString<TChar>(pBuffer, capacity, value);
}

//============================================================
// <T>将32位无符号整数格式化为16进制字符串。</T>
//
// @param value 整数
// @param pBuffer 字符串缓冲
// @param capacity 字符串容量
//============================================================
TCharC* RUint32::ToHexString(TUint32 value, TChar* pBuffer, TInt capacity){
   pBuffer[0] = RByte::HEX_CHARS[(value >> 28) & 0x0F];
   pBuffer[1] = RByte::HEX_CHARS[(value >> 24) & 0x0F];
   pBuffer[2] = RByte::HEX_CHARS[(value >> 20) & 0x0F];
   pBuffer[3] = RByte::HEX_CHARS[(value >> 16) & 0x0F];
   pBuffer[4] = RByte::HEX_CHARS[(value >> 12) & 0x0F];
   pBuffer[5] = RByte::HEX_CHARS[(value >>  8) & 0x0F];
   pBuffer[6] = RByte::HEX_CHARS[(value >>  4) & 0x0F];
   pBuffer[7] = RByte::HEX_CHARS[(value      ) & 0x0F];
   return pBuffer;
}

MO_NAMESPACE_END
