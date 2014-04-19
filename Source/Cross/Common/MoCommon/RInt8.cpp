#include "MoCmType.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>判断字符串是否为数字。</T>
//
// @param pSource 字符串
// @return 是否为数字
//============================================================
TBool RInt8::Test(TCharC* pSource){
   return RNumber<TInt8>::IsSignInteger<TChar>(pSource);
}

//============================================================
// <T>解析十进制8位字符串为有符号8位整数。</T>
//
// @param pSource 十进制字符串
// @return 有符号8位整数
//============================================================
TInt8 RInt8::Parse8(TChar8C* pSource){
   return RNumber<TInt8>::ParseSign<TChar8>(pSource);
}

//============================================================
// <T>解析十进制16位字符串为有符号8位整数。</T>
//
// @param pSource 十进制字符串
// @return 有符号8位整数
//============================================================
TInt8 RInt8::Parse16(TChar16C* pSource){
   return RNumber<TInt8>::ParseSign<TChar16>(pSource);
}

//============================================================
// <T>解析十进制32位字符串为有符号8位整数。</T>
//
// @param pSource 十进制字符串
// @return 有符号8位整数
//============================================================
TInt8 RInt8::Parse32(TChar32C* pSource){
   return RNumber<TInt8>::ParseSign<TChar32>(pSource);
}

//============================================================
// <T>解析十进制字符串为有符号8位整数。</T>
//
// @param pSource 十进制字符串
// @param defaultValue 默认值
// @return 有符号8位整数
//============================================================
TInt8 RInt8::Parse(TCharC* pSource, TInt8 defaultValue){
   return RNumber<TInt8>::ParseSign<TChar>(pSource, defaultValue);
}

//============================================================
// <T>解析10进制字符串。</T>
//
// @param pSource 来源
// @return 内容
//============================================================
TInt8 RInt8::ParseNvl(TCharC* pSource){
   if(NULL != pSource){
      return RNumber<TInt8>::ParseSign<TChar>(pSource, 0);
   }
   return 0;
}

//============================================================
// <T>解析10进制字符串。</T>
//
// @param pSource 来源
// @param length 长度
// @return 内容
//============================================================
TInt8 RInt8::Parse2(TCharC* pSource){
   MO_ASSERT(pSource);
   TInt8 v0 = (pSource[0] - '0') * 10;
   TInt8 v1 = (pSource[1] - '0');
   return v0 + v1;
}

//============================================================
// <T>解析16进制字符串。</T>
//
// @param pSource 来源
// @param length 长度
// @return 内容
//============================================================
TInt8 RInt8::ParseHex(TCharC* pSource, TInt length){
   MO_ASSERT(pSource);
   TInt8 v0 = ((TInt8)RByte::HEX_BYTES[(TInt)pSource[0]]) << 4;
   TInt8 v1 = ((TInt8)RByte::HEX_BYTES[(TInt)pSource[1]])     ;
   return v0 | v1;
}

//============================================================
// <T>解析16进制字符串。</T>
//
// @param pSource 来源
// @param length 长度
// @return 内容
//============================================================
TInt8 RInt8::ParseHexNvl(TCharC* pSource, TInt length){
   if(NULL != pSource){
      return ParseHex(pSource, length);
   }
   return 0;
}

//============================================================
// <T>将8位整数格式化为10进制字符串。</T>
//
// @param value 整数
// @param pBuffer 字符串缓冲
// @param capacity 字符串容量
//============================================================
TCharC* RInt8::ToString(TInt8 value, TChar* pBuffer, TInt capacity){
   MO_ASSERT(pBuffer);
   return RNumber<TInt8>::ToSignString<TChar, TUint8>(pBuffer, capacity, value);
}

//============================================================
// <T>将8位整数格式化为10进制2字节长的字符串。</T>
//
// @param value 整数
// @param pBuffer 字符串缓冲
// @param capacity 字符串容量
//============================================================
TCharC* RInt8::ToString2(TInt8 value, TChar* pBuffer, TInt capacity){
   MO_ASSERT(pBuffer);
   MO_ASSERT(capacity >= 2);
   pBuffer[0] = (value / 10) + '0';
   pBuffer[1] = (value % 10) + '0';
   return pBuffer;
}

//============================================================
// <T>将8位整数格式化为16进制字符串。</T>
//
// @param value 整数
// @param pBuffer 字符串缓冲
// @param capacity 字符串容量
//============================================================
TCharC* RInt8::ToHexString(TInt8 value, TChar* pBuffer, TInt capacity){
   pBuffer[0] = RByte::HEX_CHARS[(value >> 4) & 0x0F];
   pBuffer[1] = RByte::HEX_CHARS[(value     ) & 0x0F];
   return pBuffer;
}

MO_NAMESPACE_END
