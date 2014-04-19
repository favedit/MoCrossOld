#include "MoCmType.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>判断字符串是否为数字。</T>
//
// @param pSource 字符串
// @return 是否为数字
//============================================================
TBool RUint16::Test(TCharC* pSource){
   return RNumber<TUint16>::IsUnsignInteger<TChar>(pSource);
}

//============================================================
// <T>解析十进制8位字符串为无符号16位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号16位整数
//============================================================
TUint16 RUint16::Parse8(TChar8C* pSource){
   return RNumber<TUint16>::ParseUnsign<TChar8>(pSource);
}

//============================================================
// <T>解析十进制16位字符串为无符号16位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号16位整数
//============================================================
TUint16 RUint16::Parse16(TChar16C* pSource){
   return RNumber<TUint16>::ParseUnsign<TChar16>(pSource);
}

//============================================================
// <T>解析十进制32位字符串为无符号16位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号16位整数
//============================================================
TUint16 RUint16::Parse32(TChar32C* pSource){
   return RNumber<TUint16>::ParseUnsign<TChar32>(pSource);
}

//============================================================
// <T>解析十进制字符串为无符号16位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号16位整数
//============================================================
TUint16 RUint16::Parse(TCharC* pSource){
   return RNumber<TUint16>::ParseUnsign<TChar>(pSource);
}

//============================================================
// <T>解析十进制字符串为无符号16位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号16位整数
//============================================================
TUint16 RUint16::ParseNvl(TCharC* pSource, TUint16 defaultValue){
   if(NULL != pSource){
      return RNumber<TUint16>::ParseSign<TChar>(pSource, 0);
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
TUint16 RUint16::ParseHex(TCharC* pSource, TInt length){
   MO_ASSERT(pSource);
   TUint16 v0 = ((TUint16)RByte::HEX_BYTES[(TInt)pSource[0]]) << 12;
   TUint16 v1 = ((TUint16)RByte::HEX_BYTES[(TInt)pSource[1]]) <<  8;
   TUint16 v2 = ((TUint16)RByte::HEX_BYTES[(TInt)pSource[2]]) <<  4;
   TUint16 v3 = ((TUint16)RByte::HEX_BYTES[(TInt)pSource[3]])      ;
   return v0 | v1 | v2 | v3;
}

//============================================================
// <T>解析16进制字符串。</T>
//
// @param pSource 来源
// @param length 长度
// @return 内容
//============================================================
TUint16 RUint16::ParseHexNvl(TCharC* pSource, TInt length){
   if(NULL != pSource){
      return ParseHex(pSource, length);
   }
   return 0;
}

//============================================================
// <T>从无符号16位整数解析到十进制字符串。</T>
//
// @param pBuffer 缓冲字符串
// @param capacity 缓冲字符串容量
// @param value 无符号16位整数
// @return 十进制字符串    
//============================================================
TCharC* RUint16::ToString(TUint16 value, TChar* pBuffer, TInt capacity){
   MO_ASSERT(pBuffer);
   return RNumber<TUint16>::ToUnsignString<TChar>(pBuffer, capacity, value);
}

//============================================================
// <T>将16位无符号整数格式化为16进制字符串。</T>
//
// @param value 整数
// @param pBuffer 字符串缓冲
// @param capacity 字符串容量
//============================================================
TCharC* RUint16::ToHexString(TUint16 value, TChar* pBuffer, TInt capacity){
   pBuffer[0] = RByte::HEX_CHARS[(value >> 12) & 0x0F];
   pBuffer[1] = RByte::HEX_CHARS[(value >>  8) & 0x0F];
   pBuffer[2] = RByte::HEX_CHARS[(value >>  4) & 0x0F];
   pBuffer[3] = RByte::HEX_CHARS[(value      ) & 0x0F];
   return pBuffer;
}

MO_NAMESPACE_END
