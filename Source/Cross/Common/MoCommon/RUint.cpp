#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>判断内容是否在范围中。</T>
//
// @param value 内容
// @param min 最小值
// @param max 最大值
// @return 是否在范围中
//============================================================
TUint RUint::InRange(TUint value, TUint min, TUint max){
   if(value < min){
      return min;
   }
   if(value >= max){
      return max - 1;
   }
   return value;
}

//============================================================
// <T>判断内容是否在范围中。</T>
//
// @param value 内容
// @param min 最小值
// @param max 最大值
// @return 是否在范围中
//============================================================
TUint RUint::InBetween(TUint value, TUint min, TUint max){
   if(value < min){
      return min;
   }
   if(value > max){
      return max;
   }
   return value;
}

//============================================================
// <T>将8位字符串变换为无符号整数,字符串空时产生例外。</T>
//
// @param pValue 字符串
// @return 无符号整数
//============================================================
TUint RUint::Parse8(TChar8C* pValue){
   return RNumber<TUint>::ParseUnsign<TChar8>(pValue);
}

//============================================================
// <T>将16位字符串变换为无符号整数,字符串空时产生例外。</T>
//
// @param pValue 字符串
// @return 无符号整数
//============================================================
TUint RUint::Parse16(TChar16C* pValue){
   return RNumber<TUint>::ParseUnsign<TChar16>(pValue);
}

//============================================================
// <T>将32位字符串变换为无符号整数,字符串空时产生例外。</T>
//
// @param pValue 字符串
// @return 无符号整数
//============================================================
TUint RUint::Parse32(TChar32C* pValue){
   return RNumber<TUint>::ParseUnsign<TChar32>(pValue);
}

//============================================================
// <T>将字符串变换为无符号整数,字符串空时产生例外。</T>
//
// @param pValue 字符串
// @return 无符号整数
//============================================================
TUint RUint::Parse(TCharC* pValue){
   return RNumber<TUint>::ParseUnsign<TChar>(pValue);
}

//============================================================
// <T>将字符串变换为无符号整数。</T>
//
// @param pValue 字符串
// @return 无符号整数
//============================================================
TUint RUint::ParseNvl(TCharC* pValue){
   return RNumber<TUint>::ParseUnsign<TChar>(pValue, 0);
}

//============================================================
// <T>将无符号整数变换为8位字符串。</T>
//
// @param value 无符号整数
// @param pBuffer 缓冲区
// @param capacity 缓冲区容量
// @return 字符串
//============================================================
TChar8C* RUint::ToString8(TUint value, TChar8* pBuffer, TInt capacity){
   return RNumber<TUint>::ToUnsignString<TChar8>(pBuffer, capacity, value);
}

//============================================================
// <T>将无符号整数变换为16位字符串。</T>
//
// @param value 无符号整数
// @param pBuffer 缓冲区
// @param capacity 缓冲区容量
// @return 字符串
//============================================================
TChar16C* RUint::ToString16(TUint value, TChar16* pBuffer, TInt capacity){
   return RNumber<TUint>::ToUnsignString<TChar16>(pBuffer, capacity, value);
}

//============================================================
// <T>将无符号整数变换为32位字符串。</T>
//
// @param value 无符号整数
// @param pBuffer 缓冲区
// @param capacity 缓冲区容量
// @return 字符串
//============================================================
TChar32C* RUint::ToString32(TUint value, TChar32* pBuffer, TInt capacity){
   return RNumber<TUint>::ToUnsignString<TChar32>(pBuffer, capacity, value);
}

//============================================================
// <T>将无符号整数变换为字符串。</T>
//
// @param value 无符号整数
// @param pBuffer 缓冲区
// @param capacity 缓冲区容量
// @return 字符串
//============================================================
TCharC* RUint::ToString(TUint value, TChar* pBuffer, TInt capacity){
   return RNumber<TUint>::ToUnsignString<TChar>(pBuffer, capacity, value);
}

//============================================================
// <T>将无符号整数变换为16进制字符串。</T>
//
// @param value 无符号整数
// @param pBuffer 缓冲区
// @param capacity 缓冲区容量
// @return 字符串
//============================================================
TCharC* RUint::ToHexString(TUint value, TChar* pBuffer, TInt capacity){
   MO_ASSERT(pBuffer);
   TInt n = -1;
   TChar* pResult = pBuffer;
   do{
      // 循环转换每一个数字，直到结束
      pResult[++n] = RByte::HEX_CHARS[value % 16];
      value /= 16;
   }while(value > 0);
   // 转换结束后字符串是翻的，计算出一半的长度
   TInt position = (n + 1) / 2;
   while(position-- > 0){
      // 将字符串的字符序翻转
      TChar temp = pResult[position];
      pResult[position] = pResult[n - position];
      pResult[n - position] = temp;
   }
   // 置结束符
   pResult[n + 1] = '\0';
   return pBuffer;
}

MO_NAMESPACE_END

