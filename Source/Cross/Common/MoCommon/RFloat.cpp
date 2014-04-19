#include "MoCmType.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>判断字符串是不是数字。</T>
//
// @param pValue 字符串
// @return 是否为数字
//============================================================
TBool RFloat::IsFloat(TCharC* pValue){
   TInt length = RChars::Length(pValue);
   TInt count = 0;
   for(TInt n = 0; n < length; n++){
      TChar value = pValue[n];
      if(('.' == value) && (0 == count)){
         count++;
         continue;
      }else if((('-' == value) || ('+' == value)) && (0 == n)){
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
TFloat RFloat::InRange(TFloat value, TFloat min, TFloat max){
   if(value < min){
      return min;
   }
   if(value > max){
      return max;
   }
   return value;
}

//============================================================
// <T>将8位字符串变换为浮点数。</T>
//
// @param pValue 字符串
// @return 浮点数
//============================================================
TFloat RFloat::Parse8(TChar8C* pValue){
   MO_ASSERT(pValue);
   return (TFloat)atof(pValue);
}

//============================================================
// <T>将16位字符串变换为浮点数。</T>
//
// @param pValue 字符串
// @return 浮点数
//============================================================
TFloat RFloat::Parse16(TChar16C* pValue){
   MO_ASSERT(pValue);
#ifdef _MO_WINDOWS
   return (TFloat)_wtof(pValue);
#else
   return 0;
#endif // _MO_WINDOWS
}

//============================================================
// <T>将32位字符串变换为浮点数。</T>
//
// @param pValue 字符串
// @return 浮点数
//============================================================
TFloat RFloat::Parse32(TChar32C* pValue){
   MO_ASSERT(pValue);
#ifdef _MO_WINDOWS
   return (TFloat)_wtof(pValue);
#else
   return 0;
#endif // _MO_WINDOWS
}

//============================================================
// <T>将字符串变换为浮点数。</T>
//
// @param pValue 字符串
// @return 浮点数
//============================================================
TFloat RFloat::Parse(TCharC* pValue){
   MO_ASSERT(pValue);
#ifdef _UNICODE
   return (TFloat)_wtof(pValue);
#else
   return (TFloat)atof(pValue);
#endif // _UNICODE
}

//============================================================
// <T>将字符串变换为浮点数。</T>
//
// @param pValue 字符串
// @return 浮点数
//============================================================
TFloat RFloat::ParseNvl(TCharC* pValue){
#ifdef _UNICODE
   return (NULL == pValue) ? 0.0f : (TFloat)_wtof(pValue);
#else
   return (NULL == pValue) ? 0.0f : (TFloat)atof(pValue);
#endif
}

//============================================================
// <T>将16进制字符串变换为浮点数。</T>
//
// @param pValue 字符串
// @return 浮点数
//============================================================
TFloat RFloat::ParseHex(TCharC* pValue){
   MO_ASSERT(pValue);
   TByte result[sizeof(TFloat)];
   // 读取字符串
   TInt size = sizeof(TFloat);
   for(TInt n = 0; n < size; n++){
      // 读取高4位
      TChar hbyte = *pValue++;
      if(hbyte >= '0' && hbyte <= '9'){
         hbyte = hbyte - '0';
      }else if(hbyte >= 'a' && hbyte <= 'f'){
         hbyte = hbyte - 'a' + 10;
      }else if(hbyte >= 'A' && hbyte <= 'F'){
         hbyte = hbyte - 'A' + 10;
      }else{
         MO_THROW("Unknown float format.");
      }
      // 读取低4位
      TChar lbyte = *pValue++;
      if(lbyte >= '0' && lbyte <= '9'){
         lbyte = lbyte - '0';
      }else if(lbyte >= 'a' && lbyte <= 'f'){
         lbyte = lbyte - 'a' + 10;
      }else if(lbyte >= 'A' && lbyte <= 'F'){
         lbyte = lbyte - 'A' + 10;
      }else{
         MO_THROW("Unknown float format.");
      }
      // 设置内容
      result[n] = (hbyte << 4) + lbyte;
   }
   return *(TFloat*)result;
}

//============================================================
// <T>将16进制字符串变换为浮点数。</T>
//
// @param pValue 字符串
// @return 浮点数
//============================================================
TFloat RFloat::ParseHexNvl(TCharC* pValue){
   return (NULL == pValue) ? 0.0f : ParseHex(pValue);
}

//============================================================
// <T>将浮点数变换为8位字符串。</T>
//
// @param value 浮点数
// @param pBuffer 缓冲区
// @param capacity 缓冲容量
// @return 字符串
//============================================================
TChar8C* RFloat::ToString8(TFloat value, TChar8* pBuffer, TInt capacity){
   MO_LIB_STRING_FORMAT8(pBuffer, capacity, "%f", value);
   return pBuffer;
}

//============================================================
// <T>将浮点数变换为16位字符串。</T>
//
// @param value 浮点数
// @param pBuffer 缓冲区
// @param capacity 缓冲容量
// @return 字符串
//============================================================
TChar16C* RFloat::ToString16(TFloat value, TChar16* pBuffer, TInt capacity){
   // 格式化数据
   TChar8 buffer[MO_FS_FLOAT_LENGTH];
   MO_LIB_STRING_FORMAT8(buffer, MO_FS_FLOAT_LENGTH, "%f", value);
   // 复制内容
   for(TInt n = 0; n < MO_FS_FLOAT_LENGTH; n++){
      TChar16 value = buffer[n];
      pBuffer[n] = value;
      if(value == 0){
         break;
      }
   }
   return pBuffer;
}

//============================================================
// <T>将浮点数变换为32位字符串。</T>
//
// @param value 浮点数
// @param pBuffer 缓冲区
// @param capacity 缓冲容量
// @return 字符串
//============================================================
TChar32C* RFloat::ToString32(TFloat value, TChar32* pBuffer, TInt capacity){
   // 格式化数据
   TChar8 buffer[MO_FS_FLOAT_LENGTH];
   MO_LIB_STRING_FORMAT8(buffer, MO_FS_FLOAT_LENGTH, "%f", value);
   // 复制内容
   for(TInt n = 0; n < MO_FS_FLOAT_LENGTH; n++){
      TChar32 value = buffer[n];
      pBuffer[n] = value;
      if(value == 0){
         break;
      }
   }
   return pBuffer;
}

//============================================================
// <T>将浮点数变换为字符串。</T>
//
// @param value 浮点数
// @param pBuffer 缓冲区
// @param capacity 缓冲容量
// @return 字符串
//============================================================
TCharC* RFloat::ToString(TFloat value, TChar* pBuffer, TInt capacity){
#ifdef _MO_UNICODE
   // 格式化数据
   TChar8 buffer[MO_FS_FLOAT_LENGTH];
   MO_LIB_STRING_FORMAT8(buffer, MO_FS_FLOAT_LENGTH, "%f", value);
   // 复制内容
   for(TInt n = 0; n < MO_FS_FLOAT_LENGTH; n++){
      TChar value = buffer[n];
      pBuffer[n] = value;
      if(value == 0){
         break;
      }
   }
#else
   MO_LIB_STRING_FORMAT(pBuffer, capacity, TC("%f"), value);
#endif // _MO_UNICODE
   return pBuffer;
}

//============================================================
// <T>将浮点数变换为16进制字符串。</T>
//
// @param value 浮点数
// @param pBuffer 缓冲区
// @param capacity 缓冲容量
// @return 字符串
//============================================================
TCharC* RFloat::ToHexString(TFloat value, TChar* pBuffer, TInt capacity){
   TByte* pValue = (TByte*)&value;
   TChar* pWrite = pBuffer;
   // 输出字符串
   TInt size = sizeof(TFloat);
   for(TInt n = 0; n < size; n++){
      TChar ch = pValue[n];
      TInt byte = (ch >= 0) ? ch : 0x100 + ch;
      *pWrite++ = RByte::HEX_CHARS[byte >> 4];
      *pWrite++ = RByte::HEX_CHARS[byte & 0x0F];
   }
   // 设置结束字符
   *pWrite = 0;
   return pBuffer;
}

MO_NAMESPACE_END
