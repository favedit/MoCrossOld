#include "MoCmType.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>判断一个字符是否为英文字母或数字。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar8::IsAlphaNumber(TChar8 value){
   return isalnum(value);
}

//============================================================
// <T>判断一个字符是否为英文字母或数字。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar8::IsAlpha(TChar8 value){
   return isalpha(value);
}

//============================================================
// <T>判断一个字符是否为控制字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar8::IsControl(TChar8 value){
   return iscntrl(value);
}

//============================================================
// <T>判断一个字符是否为数字字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar8::IsDigit(TChar8 value){
   return isdigit(value);
}

//============================================================
// <T>判断一个字符是否为16进制字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar8::IsDigitX(TChar8 value){
   return isxdigit(value);
}

//============================================================
// <T>判断一个字符是否为小写字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar8::IsLower(TChar8 value){
   return islower(value);
}

//============================================================
// <T>判断一个字符是否为大写字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar8::IsUpper(TChar8 value){
   return isupper(value);
}

//============================================================
// <T>判断一个字符是否为可绘制字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar8::IsGraphics(TChar8 value){
   return isgraph(value);
}

//============================================================
// <T>判断一个字符是否为可打印字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar8::IsPrint(TChar8 value){
   return isprint(value);
}

//============================================================
// <T>判断一个字符是否为标点符号。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar8::IsPunct(TChar8 value){
   return ispunct(value);
}

//============================================================
// <T>判断一个字符是否为空格字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar8::IsSpace(TChar8 value){
   return isspace(value);
}

//============================================================
// <T>比较来源字符串和目标字符串大小。</T>
//
// @param pSource 来源字符串指针
// @param pTarget 目标字符串指针
// @return 字符串大小
//============================================================
TInt RChar8::CompareIgnoreCase(TChar8C* pSource, TChar8C* pTarget){
#ifdef _MO_WINDOWS
   return _stricmp(pSource, pTarget);
#else
   return strcasecmp(pSource, pTarget);
#endif
}

//============================================================
// <T>将内部字符串转换为小写。</T>
//
// @param pSource 字符串指针
// @param length 字符串长度
//============================================================
void RChar8::ToLower(TChar8* pValues, TInt length){
   while(--length >= 0){
      pValues[length] = tolower(pValues[length]);
   }
}

//============================================================
// <T>将内部字符串转换为大写。</T>
//
// @param pSource 字符串指针
// @param length 字符串长度
//============================================================
void RChar8::ToUpper(TChar8* pValues, TInt length){
   while(--length >= 0){
      pValues[length] = toupper(pValues[length]);
   }
}

//============================================================
// <T>计算将8位字符串转化为16位字符串所需要的长度。</T>
//
// @param pValue 8位字符串
// @return 所需要长度
//============================================================
TInt RChar8::ConvertTo16(TChar8C* pValue){
   MO_ASSERT(pValue);
   TInt length = 0;
#ifdef _MO_WINDOWS
   length = MultiByteToWideChar(CP_ACP, 0, pValue, -1, NULL, 0);
#endif
   return length;
}

//============================================================
// <T>将8位字符串转化为16位字符串。</T>
//
// @param pOutput 输出字符串
// @param size 输出字符串容量
// @param pValue 输入字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RChar8::ConvertTo16(TChar16* pOutput, TSize size, TChar8C* pValue){
   MO_ASSERT(pOutput);
   MO_ASSERT(pValue);
   TInt length = 0;
#ifdef _MO_WINDOWS
   length = MultiByteToWideChar(CP_ACP, 0, pValue, -1, pOutput, size);
#endif
   return length;
}

//============================================================
// <T>计算将8位字符串转化为32位字符串所需要的长度。</T>
//
// @param pValue 8位字符串
// @return 所需要长度
//============================================================
TInt RChar8::ConvertTo32(TChar8C* pValue){
   MO_ASSERT(pValue);
   TSize result = 0;
#ifdef _MO_LINUX
   result = strlen(pValue) + 1;
#endif // _MO_LINUX
   return result;
}

//============================================================
// <T>将8位字符串转化为32位字符串。</T>
//
// @param pOutput 输出字符串
// @param size 输出字符串容量
// @param pValue 输入字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RChar8::ConvertTo32(TChar32* pOutput, TSize size, TChar8C* pValue){
   TUint result = 0;
#ifdef _MO_LINUX
//   TUint inLength = strlen(pValue);
//   result = inLength + 1;
//   TUint tempLength = inLength * 4; // iconv最后一个参数表示pOut的字节数，所以要乘以4
//   MO_ASSERT(size >= outLength);
//   TChar8* pIn = (TChar8*)&pValue;
//   TChar32* pOut = pOutput;
//   iconv_t cd = iconv_open("WCHAR_T", "ASSII");
//   if(iconv_t(-1) == cd){
//      MO_STATIC_ERROR("iconv_open fail!");
//   }
//   result = iconv(cd, &pIn, &inLength, (TChar8**)&pOut, &tempLength);
//   if(-1 == result){
//      MO_STATIC_ERROR("iconv error!");
//      return -1;
//   }
//   pOutput[outLength] = 0;
//   iconv_close(cd);
#endif // _MO_LINUX
   return result;
}

//============================================================
// <T>计算将UTF8位字符串转化为16位字符串所需要的长度。</T>
//
// @param pValue 8位字符串
// @return 所需要长度
//============================================================
TInt RChar8::ConvertToUtf8(TChar8C* pValue){
   MO_ASSERT(pValue);
   TInt length = 0;
#ifdef _MO_WINDOWS
   length = MultiByteToWideChar(CP_UTF8, 0, pValue, -1, NULL, 0);
#endif
   return length;
}

//============================================================
// <T>将UTF8位字符串转化为16位字符串。</T>
//
// @param pOutput 输出字符串
// @param size 输出字符串容量
// @param pValue 输入字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RChar8::ConvertToUtf8(TChar16* pOutput, TSize size, TChar8C* pValue){
   MO_ASSERT(pOutput);
   MO_ASSERT(pValue);
   TInt length = 0;
#ifdef _MO_WINDOWS
   length = MultiByteToWideChar(CP_UTF8, 0, pValue, -1, pOutput, size);
#endif
   return length;
}

MO_NAMESPACE_END
