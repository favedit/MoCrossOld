#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>判断字符串是不是数字。</T>
//
// @param pValue 字符串
// @return 是否为数字
//============================================================
TBool RInt::IsInteger(TCharC* pValue){
   TInt length = RString::Length(pValue);
   for(TInt n = 0; n < length; n++){
      TChar value = pValue[n];
      if((('-' == value) || ('+' == value)) && (0 == n)){
         continue;
      }else if((value >= '0') && (value <= '9')){
         continue;
      }
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>判断内容是否在范围中。</T>
//
// @param value 内容
// @param min 最小值
// @param max 最大值
// @return 是否在范围中
//============================================================
TInt RInt::InRange(TInt value, TInt min, TInt max){
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
TInt RInt::InBetween(TInt value, TInt min, TInt max){
   if(value < min){
      return min;
   }
   if(value > max){
      return max;
   }
   return value;
}

//============================================================
// <T>将8位字符串变换为整数。</T>
//
// @param pValue 字符串
// @return 整数
//============================================================
TInt RInt::Parse8(TChar8C* pValue){
   return RNumber<TInt>::ParseSign<TChar8>(pValue);
}

//============================================================
// <T>将16位字符串变换为整数。</T>
//
// @param pValue 字符串
// @return 整数
//============================================================
TInt RInt::Parse16(TChar16C* pValue){
   return RNumber<TInt>::ParseSign<TChar16>(pValue);
}

//============================================================
// <T>将32位字符串变换为整数。</T>
//
// @param pValue 字符串
// @return 整数
//============================================================
TInt RInt::Parse32(TChar32C* pValue){
   return RNumber<TInt>::ParseSign<TChar32>(pValue);
}

//============================================================
// <T>将字符串变换为整数。</T>
//
// @param pValue 字符串
// @return 整数
//============================================================
TInt RInt::Parse(TCharC* pValue){
   return RNumber<TInt>::ParseSign<TChar>(pValue);
}

//============================================================
// <T>将字符串变换为整数。</T>
//
// @param pValue 字符串
// @return 整数
//============================================================
TInt RInt::ParseNvl(TCharC* pValue){
   return RNumber<TInt>::ParseSign<TChar>(pValue, 0);
}

//============================================================
// <T>将数字格式化为8位字符串。</T>
//
// @param value 数字
// @param pBuffer 输出字符串
// @param capacity 输出长度
// @return 输出字符串
//============================================================
TChar8C* RInt::ToString8(TInt value, TChar8* pBuffer, TInt capacity){
   return RNumber<TInt>::ToSignString<TChar8, TUint>(pBuffer, capacity, value);
}

//============================================================
// <T>将数字格式化为16位字符串。</T>
//
// @param value 数字
// @param pBuffer 输出字符串
// @param capacity 输出长度
// @return 输出字符串
//============================================================
TChar16C* RInt::ToString16(TInt value, TChar16* pBuffer, TInt capacity){
   return RNumber<TInt>::ToSignString<TChar16, TUint>(pBuffer, capacity, value);
}

//============================================================
// <T>将数字格式化为32位字符串。</T>
//
// @param value 数字
// @param pBuffer 输出字符串
// @param capacity 输出长度
// @return 输出字符串
//============================================================
TChar32C* RInt::ToString32(TInt value, TChar32* pBuffer, TInt capacity){
   return RNumber<TInt>::ToSignString<TChar32, TUint>(pBuffer, capacity, value);
}

//============================================================
// <T>将数字格式化为字符串。</T>
//
// @param value 数字
// @param pBuffer 输出字符串
// @param capacity 输出长度
// @return 输出字符串
//============================================================
TCharC* RInt::ToString(TInt value, TChar* pBuffer, TInt capacity){
   return RNumber<TInt>::ToSignString<TChar, TUint>(pBuffer, capacity, value);
}

//============================================================
// <T>将数字格式化为16进制字符串。</T>
//
// @param value 数字
// @param pBuffer 输出字符串
// @param capacity 输出长度
// @return 输出字符串
//============================================================
TCharC* RInt::ToHexString(TInt value, TChar* pBuffer, TInt capacity){
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
   pResult[n + 1] = 0;
   return pBuffer;
}

//============================================================
// <T>格式化为内存大小。</T>
//
// @param size 尺寸
// @param pBuffer 输出字符串
// @param capacity 输出长度
// @return 输出字符串
//============================================================
TCharC* RInt::FormatCapacity(TInt64 size, TChar* pBuffer, TInt capacity){
   TFsCode format;
   // 格式化数据
   TInt gBytes = (TInt)(size >> 30);
   if(gBytes > 0){
      format.AppendFormat(TC("%2dG."), gBytes);
   }
   TInt mBytes = (TInt)(size >> 20) % 1024;
   if(gBytes || mBytes){
      if(gBytes){
         format.AppendFormat(TC("%04dM."), mBytes);
      }else{
         format.AppendFormat(TC("%4dM."), mBytes);
      }
   }
   TInt kBytes = (TInt)(size >> 10) % 1024;
   if(gBytes || mBytes || kBytes){
      if(gBytes || mBytes){
         format.AppendFormat(TC("%04dK."), kBytes);
      }else{
         format.AppendFormat(TC("%4dK."), kBytes);
      }
   }
   if(gBytes || mBytes || kBytes){
      format.AppendFormat(TC("%04dB"), size % 1024);
   }else{
      format.AppendFormat(TC("%4dB"), size % 1024);
   }
   // 复制数据
   TInt copied = MO_LIB_MIN(format.Length() + 1, capacity - 1);
   memcpy(pBuffer, (TCharC*)format, copied);
   return pBuffer;
}

//============================================================
// <T>计数一个整数有多少位。</T>
//
// @param value 整数
// @return 位数
//============================================================
TInt RInt::CountDigit(TInt value){
   TInt result = 0;
   while(value){
      ++result;
      value /= 10;
   }
   return result;
}
MO_NAMESPACE_END

