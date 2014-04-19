#ifdef _MO_WINDOWS
#include <Windows.h>
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
#include <ctype.h>
#include <iconv.h>
#endif // _MO_LINUX
#ifdef _MO_ANDROID
// #include <dlfcn.h>
#endif // _MO_ANDROID
#include "MoCmString8.h"
#include "MoCmString.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>空字符串。</T>
//============================================================
TChar8C* RString8::EmptyPtr = "";
TString8 RString8::EmptyString;

//============================================================
// <T>获取ANSI字符串的长度。</T>
//
// @param pSource ANSI字符串
// @return 长度
//============================================================
TInt RString8::Length(TChar8C* pValue){
   MO_ASSERT(pValue);
   return strlen(pValue);
}

//============================================================
// <T>判断两个字符串是否相等。</T>
//
// @param pSource 比较源字符串
// @param pTarget 比较目标字符串
// @return 是否相等
//============================================================
TBool RString8::Equals(TChar8C* pSource, TChar8C* pTarget){
   // 长度都为0的时候认为相等
   TInt sourceLength = 0;
   if(pSource != NULL){
      sourceLength = MO_LIB_STRING_LENGTH8(pSource);
   }
   TInt targetLength = 0;
   if(pTarget != NULL){
      targetLength = MO_LIB_STRING_LENGTH8(pTarget);
   }
   if((sourceLength == 0) && (targetLength == 0)){
      return ETrue;
   }
   // 任何一方为空认为不相等
   if((pSource == NULL) || (pTarget == NULL)){
      return EFalse;
   }
   // 比较内容
   TInt result = MO_LIB_STRING_COMPARE8(pSource, pTarget);
   return (result == 0);
}

//============================================================
// <T>判断来源字符串中是否含有指定字符串。</T>
//
// @param pSource 来源字符串
// @param pValue 指定字符串
// @return 是否含有
//============================================================
TBool RString8::Constains(TChar8C* pSource, TChar8C* pValue){
   TInt sourceLength = Length(pSource);
   TInt valueLength = Length(pValue);
   TInt index = RChar8s::Find(pSource, sourceLength, pValue, valueLength);
   return (ENotFound != index);
}

//============================================================
// <T>查找来源字符串中指定字符串的索引位置。</T>
//
// @param pSource 来源字符串
// @param pValue 指定字符串
// @return 索引位置
//============================================================
TInt RString8::Find(TChar8C* pSource, TChar8C* pValue){
   TInt sourceLength = Length(pSource);
   TInt valueLength = Length(pValue);
   TInt index = RChar8s::Find(pSource, sourceLength, pValue, valueLength);
   return index;
}

//============================================================
// <T>获取ANSI字符串的哈希值。</T>
//
// @param pValues ANSI字符串
// @return 哈希值
//============================================================
THashCode RString8::MakeHashCode(TChar8C* pValues){
   MO_ASSERT(pValues);
   return RTypes<TChar8>::MakeHashCode(pValues, strlen(pValues));
}

//============================================================
// <T>获取ANSI字符串的不区分大小写的哈希值。</T>
//
// @param pValues ANSI字符串
// @return 哈希值
//============================================================
THashCode RString8::MakeNocaseHashCode(TChar8C* pValues){
   MO_ASSERT(pValues);
   THashCode hash = 0;
   TInt length = strlen(pValues);
   while(--length >= 0){
      hash += (hash << 4) + (hash << 3) + (hash << 2) + (hash << 1) + tolower(pValues[length]);
   }
   return hash;
}

//============================================================
// <T>获取ANSI字符串的哈希值。</T>
//
// @param ptr ANSI字符串指针
// @return 哈希值
//============================================================
TBool RString8::AllocCopy(TChar8C* pValues, TChar8** ppTarget){
   if(NULL != pValues){
      TSize length = strlen(pValues) + 1;
      TChar8* pAlloc = MO_STATIC_TYPES_ALLOC(TChar8, length);
      MO_ASSERT(pAlloc);
      MO_LIB_MEMORY_COPY(pAlloc, length, pValues, length);
      *ppTarget = pAlloc;
   }
   return ETrue;
}

//============================================================
TBool RString8::AllocFree(TChar8* pValues){
   MO_FREE(pValues);
   return ETrue;
}

//============================================================
// <T>安全复制字符串到目标字符串。</T>
// <P>如果目标字符串或值字符串为空，则不进行复制。</P>
// <P>如果目标字符串长度不足，则只复制能复制的部分数据。</P>
// <P>复制后，末尾字符总是留空。</P>
//
// @param pTarget 目标字符串指针
// @param size 目标字符窜长度
// @param pValue 值字符串指针
// @return 复制长度
//============================================================
TInt RString8::SafeCopy(TChar8* pTarget, TSize size, TChar8C* pValue){
   if((NULL != pTarget) && (size > 0) && (NULL != pValue)){
      TSize length = strlen(pValue);
      if(length > 0){
         if(length >= size){
            length = size - 1;
         }
         memcpy(pTarget, pValue, length);
         pTarget[length] = 0;
      }else{
         pTarget[0] = 0;
      }
      return length;
   }
   return 0;
}

//============================================================
// <T>强制复制字符串到目标字符串。</T>
// <P>如果目标字符串为空，产生例外。</P>
// <P>如果值字符串为空，返回成功。</P>
// <P>如果目标字符串长度不足，产生例外。</P>
// <P>复制后，末尾字符总是留空。</P>
//
// @param pTarget 目标字符串指针
// @param size 目标字符窜长度
// @param pValue 值字符串指针
// @return 复制长度
//============================================================
TInt RString8::ForceCopy(TChar8* pTarget, TSize size, TChar8C* pValue){
   MO_ASSERT(pTarget);
   MO_ASSERT(size > 0);
   if(NULL != pValue){
      TSize length = strlen(pValue);
      if(length > 0){
         MO_ASSERT(length < size);
         memcpy(pTarget, pValue, length);
         pTarget[length] = 0;
         return length;
      }
   }
   return 0;
}

//============================================================
// <T>在一个字符串中，替换来源字符串为目标字符串。</T>
//
// @param str1 要查到的字符串
// @param str2 字符串的间隔
// @return 复制长度
//============================================================
TInt RString8::ReplaceAll(TChar8* pValue, TChar8* pSource, TChar8* pTarget, TChar8* pBuffer, TInt bufferLength){
   MO_ASSERT(pValue);
   MO_ASSERT(pSource);
   MO_ASSERT(pTarget);
   MO_ASSERT(pBuffer);
   TInt length = strlen(pValue);
   TInt sourceLength = strlen(pSource);
   TInt targetLength = strlen(pTarget);
   return RTypes<TChar8>::Replace(pValue, length, pSource, sourceLength, pTarget, targetLength, pBuffer, bufferLength);
}

//============================================================
// <T>获得将字符串转化为8位字符串所需要的长度</T>
//
// @param pValue 被转换字符串
// @return 所需要长度
//============================================================
TInt RString8::ConvertToString8(TChar8C* pValue){
   MO_ASSERT(pValue);
   return MO_LIB_STRING_LENGTH8(pValue);
}

//============================================================
// <T>将字符串转化为8位字符串</T>
//
// @param pTarget转换后的字符串
// @param size pTarget容量
// @param pValue 被转换字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RString8::ConvertToString8(TChar8* pTarget, TInt size, TChar8C* pValue){
   MO_ASSERT(pTarget);
   MO_ASSERT(pValue);
   TSize length = MO_LIB_STRING_LENGTH8(pValue);
   MO_LIB_MEMORY_COPY(pTarget, sizeof(TChar8) * length, pValue, sizeof(TChar8) * length);
   return length;
}

//============================================================
// <T>获得将字符串转化为16位字符串所需要的长度</T>
//
// @param pValue 被转换字符串
// @return 所需要长度
//============================================================
TInt RString8::ConvertToString16(TChar8C* pValue){
   MO_CHECK(pValue, return 0);
   TInt result = 0;
   TSize length = MO_LIB_STRING_LENGTH8(pValue);
#ifdef _MO_WINDOWS
   result = MultiByteToWideChar(CP_UTF8, 0, pValue, length, NULL, 0);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   result = sizeof(TChar16) * length;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   result = sizeof(TChar32) * length;
#endif // _MO_ANDROID
   return result;
}

//============================================================
// <T>将字符串转化为16位字符串</T>
//
// @param pTarget转换后的字符串
// @param size pTarget容量
// @param pValue 被转换字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RString8::ConvertToString16(TChar16* pTarget, TInt size, TChar8C* pValue){
   MO_CHECK(pTarget, return 0);
   MO_CHECK(pValue, return 0);
   TInt result = 0;
   TSize length = MO_LIB_STRING_LENGTH8(pValue);
#ifdef _MO_WINDOWS
   result = MultiByteToWideChar(CP_UTF8, 0, pValue, length, pTarget, size);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   iconv_t code = iconv_open("UTF-8", "GBK");
   if(NULL == code){
      MO_STATIC_FATAL("Iconv open failure.");
   }
   TChar8* pInput = (TChar8*)pValue;
   TSize inputLength = length;
   TChar8* pOutput = (TChar8*)pTarget;
   TSize outputLength = size;
   result = iconv(code, &pInput, &inputLength, &pOutput, &outputLength);
   if(-1 == result){
      MO_STATIC_FATAL("Iconv convert failure.");
      return -1;
   }
   iconv_close(code);
   pTarget[outputLength] = 0;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   for(TInt n = 0; n < length; n++){
      *pTarget++ = *pValue++;
   }
   pTarget[length] = 0;
   result = length;
   // g_lpdlIcuuc = dlopen("/system/lib/libicuuc.so", RTLD_LAZY);
#endif // _MO_ANDROID
   return result;
}

//============================================================
// <T>获得将字符串转化为32位字符串所需要的长度</T>
//
// @param pValue 被转换字符串
// @return 所需要长度
//============================================================
TInt RString8::ConvertToString32(TChar8C* pValue){
   MO_CHECK(pValue, return 0);
   TInt result = 0;
   TSize length = MO_LIB_STRING_LENGTH8(pValue);
#ifdef _MO_WINDOWS
   result = MultiByteToWideChar(CP_UTF8, 0, pValue, length, NULL, 0);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   result = sizeof(TChar16) * length;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   result = sizeof(TChar32) * length;
#endif // _MO_ANDROID
   return result;
}

//============================================================
// <T>将字符串转化为16位字符串</T>
//
// @param pTarget转换后的字符串
// @param size pTarget容量
// @param pValue 被转换字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RString8::ConvertToString32(TChar32* pTarget, TInt size, TChar8C* pValue){
   MO_CHECK(pTarget, return 0);
   MO_CHECK(pValue, return 0);
   TInt result = 0;
   TSize length = MO_LIB_STRING_LENGTH8(pValue);
#ifdef _MO_WINDOWS
   result = MultiByteToWideChar(CP_UTF8, 0, pValue, length, pTarget, size);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   iconv_t code = iconv_open("UTF-8", "GBK");
   if(NULL == code){
      MO_STATIC_FATAL("Iconv open failure.");
   }
   TChar8* pInput = (TChar8*)&pValue;
   TSize inputLength = length;
   TChar8* pOutput = (TChar8*)pTarget;
   TSize outputLength = size;
   result = iconv(code, &pInput, &inputLength, &pOutput, &outputLength);
   if(-1 == result){
      MO_STATIC_FATAL("Iconv convert failure.");
      return -1;
   }
   iconv_close(code);
   pTarget[outputLength] = 0;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   for(TInt n = 0; n < length; n++){
      *pTarget++ = *pValue++;
   }
   pTarget[length] = 0;
   result = length;
#endif // _MO_ANDROID
   return result;
}

MO_NAMESPACE_END
