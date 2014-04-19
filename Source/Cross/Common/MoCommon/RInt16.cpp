#include "MoCmType.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>判断字符串是否为数字。</T>
//
// @param pSource 字符串
// @return 是否为数字
//============================================================
TBool RInt16::Test(TCharC* pSource){
   return RNumber<TInt16>::IsSignInteger<TChar>(pSource);
}

//============================================================
// <T>解析十进制8位字符串为有符号16位整数。</T>
//
// @param pSource 十进制字符串
// @return 有符号16位整数
//============================================================
TInt16 RInt16::Parse8(TChar8C* pSource){
   return RNumber<TInt16>::ParseSign<TChar8>(pSource);
}

//============================================================
// <T>解析十进制16位字符串为有符号16位整数。</T>
//
// @param pSource 十进制字符串
// @return 有符号16位整数
//============================================================
TInt16 RInt16::Parse16(TChar16C* pSource){
   return RNumber<TInt16>::ParseSign<TChar16>(pSource);
}

//============================================================
// <T>解析十进制32位字符串为有符号16位整数。</T>
//
// @param pSource 十进制字符串
// @return 有符号16位整数
//============================================================
TInt16 RInt16::Parse32(TChar32C* pSource){
   return RNumber<TInt16>::ParseSign<TChar32>(pSource);
}

//============================================================
// <T>解析十进制字符串为有符号16位整数。</T>
//
// @param pSource 十进制字符串
// @param defaultValue 默认内容
// @return 有符号16位整数
//============================================================
TInt16 RInt16::Parse(TCharC* pSource, TInt16 defaultValue){
   return RNumber<TInt16>::ParseSign<TChar>(pSource, defaultValue);
}

//============================================================
// <T>解析十进制字符串为有符号16位整数。</T>
//
// @param pSource 十进制字符串
// @return 有符号16位整数
//============================================================
TInt16 RInt16::ParseNvl(TCharC* pSource){
   if(NULL != pSource){
      return RNumber<TInt16>::ParseSign<TChar>(pSource, 0);
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
TInt16 RInt16::ParseHex(TCharC* pSource, TInt length){
   MO_ASSERT(pSource);
   TInt16 v0 = ((TInt16)RByte::HEX_BYTES[(TInt)pSource[0]]) << 12;
   TInt16 v1 = ((TInt16)RByte::HEX_BYTES[(TInt)pSource[1]]) <<  8;
   TInt16 v2 = ((TInt16)RByte::HEX_BYTES[(TInt)pSource[2]]) <<  4;
   TInt16 v3 = ((TInt16)RByte::HEX_BYTES[(TInt)pSource[3]])      ;
   return v0 | v1 | v2 | v3;
}

//============================================================
// <T>解析16进制字符串。</T>
//
// @param pSource 来源
// @param length 长度
// @return 内容
//============================================================
TInt16 RInt16::ParseHexNvl(TCharC* pSource, TInt length){
   if(NULL != pSource){
      return ParseHex(pSource, length);
   }
   return 0;
}

//============================================================
// <T>16位整数操作的引用类。</T>
//============================================================
TCharC* RInt16::ToString(TInt16 value, TChar* pBuffer, TInt capacity){
   MO_ASSERT(pBuffer);
   return RNumber<TInt16>::ToSignString<TChar, TUint16>(pBuffer, capacity, value);
}

//============================================================
// <T>将16位整数格式化为16进制字符串。</T>
//
// @param value 整数
// @param pBuffer 字符串缓冲
// @param capacity 字符串容量
//============================================================
TCharC* RInt16::ToHexString(TInt16 value, TChar* pBuffer, TInt capacity){
   pBuffer[0] = RByte::HEX_CHARS[(value >> 12) & 0x0F];
   pBuffer[1] = RByte::HEX_CHARS[(value >>  8) & 0x0F];
   pBuffer[2] = RByte::HEX_CHARS[(value >>  4) & 0x0F];
   pBuffer[3] = RByte::HEX_CHARS[(value      ) & 0x0F];
   return pBuffer;
}

MO_NAMESPACE_END
