#include "MoCmType.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>判断字符串是否为数字。</T>
//
// @param pValue 字符串
// @return 是否为数字
//============================================================
TBool RInt32::Test(TCharC* pValue){
   return RNumber<TInt32>::IsSignInteger<TChar>(pValue);
}

//============================================================
// <T>解析十进制8位字符串为有符号32位整数。</T>
//
// @param pValue 十进制字符串
// @return 有符号32位整数
//============================================================
TInt32 RInt32::Parse8(TChar8C* pValue){
   return RNumber<TInt32>::ParseSign<TChar8>(pValue);
}

//============================================================
// <T>解析十进制16位字符串为有符号32位整数。</T>
//
// @param pValue 十进制字符串
// @return 有符号32位整数
//============================================================
TInt32 RInt32::Parse16(TChar16C* pValue){
   return RNumber<TInt32>::ParseSign<TChar16>(pValue);
}

//============================================================
// <T>解析十进制32位字符串为有符号32位整数。</T>
//
// @param pValue 十进制字符串
// @return 有符号32位整数
//============================================================
TInt32 RInt32::Parse32(TChar32C* pValue){
   return RNumber<TInt32>::ParseSign<TChar32>(pValue);
}

//============================================================
// <T>解析十进制字符串为有符号32位整数。</T>
//
// @param pValue 十进制字符串
// @return 有符号32位整数
//============================================================
TInt32 RInt32::Parse(TCharC* pValue){
   return RNumber<TInt32>::ParseSign<TChar>(pValue);
}

//============================================================
// <T>解析十进制字符串为有符号32位整数。</T>
//
// @param pValue 十进制字符串
// @return 有符号32位整数
//============================================================
TInt32 RInt32::ParseNvl(TCharC* pSource){
   if(NULL != pSource){
      return RNumber<TInt32>::ParseSign<TChar>(pSource, 0);
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
TInt32 RInt32::ParseHex(TCharC* pSource, TInt length){
   MO_ASSERT(pSource);
   TInt32 v0 = ((TInt32)RByte::HEX_BYTES[(TInt)pSource[0]]) << 28;
   TInt32 v1 = ((TInt32)RByte::HEX_BYTES[(TInt)pSource[1]]) << 24;
   TInt32 v2 = ((TInt32)RByte::HEX_BYTES[(TInt)pSource[2]]) << 20;
   TInt32 v3 = ((TInt32)RByte::HEX_BYTES[(TInt)pSource[3]]) << 16;
   TInt32 v4 = ((TInt32)RByte::HEX_BYTES[(TInt)pSource[4]]) << 12;
   TInt32 v5 = ((TInt32)RByte::HEX_BYTES[(TInt)pSource[5]]) <<  8;
   TInt32 v6 = ((TInt32)RByte::HEX_BYTES[(TInt)pSource[6]]) <<  4;
   TInt32 v7 = ((TInt32)RByte::HEX_BYTES[(TInt)pSource[7]])      ;
   return v0 | v1 | v2 | v3 | v4 | v5 | v6 | v7;
}

//============================================================
// <T>解析16进制字符串。</T>
//
// @param pSource 来源
// @param length 长度
// @return 内容
//============================================================
TInt32 RInt32::ParseHexNvl(TCharC* pSource, TInt length){
   if(NULL != pSource){
      return ParseHex(pSource, length);
   }
   return 0;
}

//============================================================
// <T>从有符号32位整数解析到十进制字符串。</T>
//
// @param pBuffer 缓冲字符串
// @param capacity 缓冲字符串容量
// @param value 有符号32位整数
// @return 十进制字符串    
//============================================================
TCharC* RInt32::ToString(TInt32 value, TChar* pBuffer, TInt capacity){
   MO_ASSERT(pBuffer);
   return RNumber<TInt32>::ToSignString<TChar, TUint32>(pBuffer, capacity, value);
}

//============================================================
// <T>将32位整数格式化为16进制字符串。</T>
//
// @param value 整数
// @param pBuffer 字符串缓冲
// @param capacity 字符串容量
//============================================================
TCharC* RInt32::ToHexString(TInt32 value, TChar* pBuffer, TInt capacity){
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
