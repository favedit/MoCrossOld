#include "MoCmType.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>判断字符串是否为数字。</T>
//
// @param pSource 字符串
// @return 是否为数字
//============================================================
TBool RUint8::Test(TCharC* pSource){
   return RNumber<TUint8>::IsUnsignInteger<TChar>(pSource);
}

//============================================================
// <T>解析十进制8位字符串为无符号8位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号8位整数
//============================================================
TUint8 RUint8::Parse8(TChar8C* pSource){
   return RNumber<TUint8>::ParseUnsign<TChar8>(pSource);
}

//============================================================
// <T>解析十进制16位字符串为无符号8位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号8位整数
//============================================================
TUint8 RUint8::Parse16(TChar16C* pSource){
   return RNumber<TUint8>::ParseUnsign<TChar16>(pSource);
}

//============================================================
// <T>解析十进制32位字符串为无符号8位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号8位整数
//============================================================
TUint8 RUint8::Parse32(TChar32C* pSource){
   return RNumber<TUint8>::ParseUnsign<TChar32>(pSource);
}

//============================================================
// <T>解析十进制字符串为无符号8位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号8位整数
//============================================================
TUint8 RUint8::Parse(TCharC* pSource){
   return RNumber<TUint8>::ParseUnsign<TChar>(pSource);
}

//============================================================
// <T>解析十进制字符串为无符号8位整数。</T>
//
// @param pSource 十进制字符串
// @return 无符号8位整数
//============================================================
TUint8 RUint8::ParseNvl(TCharC* pSource){
   if(NULL != pSource){
      return RNumber<TUint8>::ParseUnsign<TChar>(pSource, 0);
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
TUint8 RUint8::ParseHex(TCharC* pSource, TInt length){
   MO_ASSERT(pSource);
   TUint8 v0 = ((TUint8)RByte::HEX_BYTES[(TInt)pSource[0]]) << 4;
   TUint8 v1 = ((TUint8)RByte::HEX_BYTES[(TInt)pSource[1]])     ;
   return v0 | v1;
}

//============================================================
// <T>解析16进制字符串。</T>
//
// @param pSource 来源
// @param length 长度
// @return 内容
//============================================================
TUint8 RUint8::ParseHexNvl(TCharC* pSource, TInt length){
   if(NULL != pSource){
      return ParseHex(pSource, length);
   }
   return 0;
}

//============================================================
TCharC* RUint8::ToString(TUint8 value, TChar* pBuffer, TInt capacity){
   MO_ASSERT(pBuffer);
   return RNumber<TUint8>::ToUnsignString<TChar>(pBuffer, capacity, value);
}

//============================================================
// <T>将8位无符号整数格式化为16进制字符串。</T>
//
// @param value 整数
// @param pBuffer 字符串缓冲
// @param capacity 字符串容量
//============================================================
TCharC* RUint8::ToHexString(TUint8 value, TChar* pBuffer, TInt capacity){
   pBuffer[0] = RByte::HEX_CHARS[(value >>  4) & 0x0F];
   pBuffer[1] = RByte::HEX_CHARS[(value      ) & 0x0F];
   return pBuffer;
}

MO_NAMESPACE_END
