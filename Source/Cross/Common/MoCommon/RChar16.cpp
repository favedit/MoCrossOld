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
TBool RChar16::IsAlphaNumber(TChar16 value){
#ifdef _MO_WINDOWS
   return iswalnum(value);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   return EFalse;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   return EFalse;
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
   return EFalse;
#endif // _MO_FLASCC
}

//============================================================
// <T>判断一个字符是否为英文字母或数字。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar16::IsAlpha(TChar16 value){
#ifdef _MO_WINDOWS
   return iswalpha(value);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   return EFalse;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   return EFalse;
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
   return EFalse;
#endif // _MO_FLASCC
}

//============================================================
// <T>判断一个字符是否为控制字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar16::IsControl(TChar16 value){
#ifdef _MO_WINDOWS
   return iswcntrl(value);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   return EFalse;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   return EFalse;
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
   return EFalse;
#endif // _MO_FLASCC
}

//============================================================
// <T>判断一个字符是否为数字字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar16::IsDigit(TChar16 value){
#ifdef _MO_WINDOWS
   return iswdigit(value);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   return EFalse;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   return EFalse;
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
   return EFalse;
#endif // _MO_FLASCC
}

//============================================================
// <T>判断一个字符是否为16进制字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar16::IsDigitX(TChar16 value){
#ifdef _MO_WINDOWS
   return iswxdigit(value);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   return EFalse;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   return EFalse;
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
   return EFalse;
#endif // _MO_FLASCC
}

//============================================================
// <T>判断一个字符是否为小写字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar16::IsLower(TChar16 value){
#ifdef _MO_WINDOWS
   return iswlower(value);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   return EFalse;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   return EFalse;
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
   return EFalse;
#endif // _MO_FLASCC
}

//============================================================
// <T>判断一个字符是否为大写字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar16::IsUpper(TChar16 value){
#ifdef _MO_WINDOWS
   return iswupper(value);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   return EFalse;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   return EFalse;
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
   return EFalse;
#endif // _MO_FLASCC
}

//============================================================
// <T>判断一个字符是否为可绘制字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar16::IsGraphics(TChar16 value){
#ifdef _MO_WINDOWS
   return iswgraph(value);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   return EFalse;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   return EFalse;
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
   return EFalse;
#endif // _MO_FLASCC
}

//============================================================
// <T>判断一个字符是否为可打印字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar16::IsPrint(TChar16 value){
#ifdef _MO_WINDOWS
   return iswprint(value);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   return EFalse;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   return EFalse;
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
   return EFalse;
#endif // _MO_FLASCC
}

//============================================================
// <T>判断一个字符是否为标点符号。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar16::IsPunct(TChar16 value){
#ifdef _MO_WINDOWS
   return iswpunct(value);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   return EFalse;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   return EFalse;
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
   return EFalse;
#endif // _MO_FLASCC
}

//============================================================
// <T>判断一个字符是否为空格字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar16::IsSpace(TChar16 value){
#ifdef _MO_WINDOWS
   return iswspace(value);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   return EFalse;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   return EFalse;
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
   return EFalse;
#endif // _MO_FLASCC
}

//============================================================
// <T>比较来源字符串和目标字符串大小。</T>
//
// @param pSource 来源字符串指针
// @param pTarget 目标字符串指针
// @return 字符串大小
//============================================================
TInt RChar16::CompareIgnoreCase(TChar16C* pSource, TChar16C* pTarget){
#ifdef _MO_WINDOWS
   return _wcsicmp(pSource, pTarget);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   return wcscmp(pSource, pTarget);
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   return wcscmp(pSource, pTarget);
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
   return EFalse;
#endif // _MO_FLASCC
}

//============================================================
// <T>将内部字符串转换为小写。</T>
//
// @param pSource 字符串指针
// @param length 字符串长度
//============================================================
void RChar16::ToLower(TChar16* pValues, TInt length){
#ifdef _MO_WINDOWS
   while(--length >= 0){
      pValues[length] = towlower(pValues[length]);
   }
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
#endif // _MO_LINUX
#ifdef _MO_ANDROID
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
#endif // _MO_FLASCC
}

//============================================================
// <T>将内部字符串转换为大写。</T>
//
// @param pSource 字符串指针
// @param length 字符串长度
//============================================================
void RChar16::ToUpper(TChar16* pValues, TInt length){
#ifdef _MO_WINDOWS
   while(--length >= 0){
      pValues[length] = towupper(pValues[length]);
   }
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
#endif // _MO_LINUX
#ifdef _MO_ANDROID
#endif // _MO_ANDROID
#ifdef _MO_FLASCC
#endif // _MO_FLASCC
}

//============================================================
// <T>计算将16位字符串转化为8位字符串所需要的长度。</T>
//
// @param pValue 16位字符串
// @return 所需要长度
//============================================================
TInt RChar16::ConvertTo8(TChar16C* pValue){
   MO_ASSERT(pValue);
   TSize length = wcslen(pValue);
#ifdef _MO_WINDOWS
   TSize tempLen = length;
   WideCharToMultiByte(CP_ACP, 0, pValue, tempLen, NULL, 0, NULL, FALSE);
#else
   MO_STATIC_FATAL("Unimplemented.");
#endif // _MO_WINDOWS
   return length;
}

//============================================================
// <T>将16位字符串转化为8位字符串。</T>
//
// @param pOutput 输出字符串
// @param size 输出字符串容量
// @param pValue 输入字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RChar16::ConvertTo8(TChar8* pOutput, TSize size, TChar16C* pValue){
   MO_ASSERT(pOutput);
   MO_ASSERT(pValue);
#ifdef _MO_WINDOWS
   TSize length = wcslen(pValue);
   return WideCharToMultiByte(CP_ACP, 0, pValue, length, pOutput, size, NULL, FALSE);
#else
   MO_STATIC_FATAL("Unimplemented.");
#endif // _MO_WINDOWS
   return 0;
}

//============================================================
// <T>计算将16位字符串转化为32位字符串所需要的长度。</T>
//
// @param pValue 16位字符串
// @return 所需要长度
//============================================================
TInt RChar16::ConvertTo32(TChar16C* pValue){
   return 0;
}

//============================================================
// <T>将16位字符串转化为32位字符串。</T>
//
// @param pOutput 输出字符串
// @param size 输出字符串容量
// @param pValue 输入字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RChar16::ConvertTo32(TChar32* pOutput, TSize size, TChar16C* pValue){
   return 0;
}

//============================================================
// <T>计算将16位字符串转化为UTF8位字符串所需要的长度。</T>
//
// @param pValue 16位字符串
// @return 所需要长度
//============================================================
TInt RChar16::ConvertToUtf8(TChar16C* pValue){
   MO_ASSERT(pValue);
   TSize length = wcslen(pValue);
#ifdef _MO_WINDOWS
   TSize tempLen = length;
   WideCharToMultiByte(CP_UTF8, 0, pValue, tempLen, NULL, 0, NULL, FALSE);
#else
   MO_STATIC_FATAL("Unimplemented.");
#endif // _MO_WINDOWS
   return length;
}

//============================================================
// <T>将16位字符串转化为UTF8位字符串。</T>
//
// @param pOutput 输出字符串
// @param size 输出字符串容量
// @param pValue 输入字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RChar16::ConvertToUtf8(TChar8* pOutput, TSize size, TChar16C* pValue){
   MO_ASSERT(pOutput);
   MO_ASSERT(pValue);
#ifdef _MO_WINDOWS
   TSize length = wcslen(pValue);
   return WideCharToMultiByte(CP_UTF8, 0, pValue, length, pOutput, size, NULL, FALSE);
#else
   MO_STATIC_FATAL("Unimplemented.");
#endif // _MO_WINDOWS
   return 0;
}

MO_NAMESPACE_END
