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
TBool RChar32::IsAlphaNumber(TChar32 value){
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
TBool RChar32::IsAlpha(TChar32 value){
#ifdef _MO_WINDOWS
   return iswalpha(value);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   return EFalse;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   return EFalse;
#endif // _MO_ANDROID
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
TBool RChar32::IsControl(TChar32 value){
#ifdef _MO_WINDOWS
   return iswcntrl(value);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   return EFalse;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   return EFalse;
#endif // _MO_ANDROID
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
TBool RChar32::IsDigit(TChar32 value){
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
// <T>判断一个字符是否为32进制字符。</T>
//
// @param value 字符
// @return
//    <L value='ETrue'>是</L>
//    <L value='EFalse'>否</L>
//============================================================
TBool RChar32::IsDigitX(TChar32 value){
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
TBool RChar32::IsLower(TChar32 value){
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
TBool RChar32::IsUpper(TChar32 value){
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
TBool RChar32::IsGraphics(TChar32 value){
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
TBool RChar32::IsPrint(TChar32 value){
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
TBool RChar32::IsPunct(TChar32 value){
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
TBool RChar32::IsSpace(TChar32 value){
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
TInt RChar32::CompareIgnoreCase(TChar32C* pSource, TChar32C* pTarget){
#ifdef _MO_WINDOWS
   return _wcsicmp(pSource, pTarget);
#else
   return wcscmp(pSource, pTarget);
#endif
}

//============================================================
// <T>将内部字符串转换为小写。</T>
//
// @param pSource 字符串指针
// @param length 字符串长度
//============================================================
void RChar32::ToLower(TChar32* pValues, TInt length){
#ifdef _MO_WINDOWS
   while(--length >= 0){
      pValues[length] = towlower(pValues[length]);
   }
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
#endif // _MO_LINUX
}

//============================================================
// <T>将内部字符串转换为大写。</T>
//
// @param pSource 字符串指针
// @param length 字符串长度
//============================================================
void RChar32::ToUpper(TChar32* pValues, TInt length){
#ifdef _MO_WINDOWS
   while(--length >= 0){
      pValues[length] = towupper(pValues[length]);
   }
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
#endif // _MO_LINUX
}

//============================================================
// <T>计算将32位字符串转化为8位字符串所需要的长度。</T>
//
// @param pValue 32位字符串
// @return 所需要长度
//============================================================
TInt RChar32::ConvertTo8(TChar32C* pValue){
   MO_ASSERT(pValue);
   TSize length = wcslen(pValue) + 1;
   return length;
}

//============================================================
// <T>将32位字符串转化为8位字符串。</T>
//
// @param pOutput 输出字符串
// @param size 输出字符串容量
// @param pValue 输入字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RChar32::ConvertTo8(TChar8* pOutput, TSize size, TChar32C* pValue){
   TInt result = 0;
#ifdef _MO_LINUX
   // 打开转换器
   iconv_t cd = iconv_open("ASCII", "WCHAR_T");
   if(iconv_t(-1) == cd){
      MO_STATIC_ERROR("iconv_open fail!");
   }
   // 转换字符串
   TChar8* pInput = (TChar8*)&pValue;
   TSize convertCount = 0;
   TSize length = wcslen(pValue);
   result = iconv(cd, &pInput, &length, &pOutput, &convertCount);
   if(-1 == result){
      MO_STATIC_ERROR("iconv error!");
   }
   pOutput[convertCount] = 0;
   // 关闭转换器
   iconv_close(cd);
#endif
   return result;
}

//============================================================
// <T>计算将32位字符串转化为16位字符串所需要的长度。</T>
//
// @param pValue 32位字符串
// @return 所需要长度
//============================================================
TInt RChar32::ConvertTo16(TChar32C* pValue){
   return 0;
}

//============================================================
// <T>将32位字符串转化为16位字符串。</T>
//
// @param pOutput 输出字符串
// @param size 输出字符串容量
// @param pValue 输入字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RChar32::ConvertTo16(TChar16* pOutput, TSize size, TChar32C* pValue){
   return 0;
}

MO_NAMESPACE_END
