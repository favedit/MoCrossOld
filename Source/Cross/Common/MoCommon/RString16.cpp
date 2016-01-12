#include "MoCmString16.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>空字符串。</T>
//============================================================
TChar16C* RString16::EmptyPtr = L"";
TString16 RString16::EmptyString;

//============================================================
// <T>获取字符串的长度。</T>
//
// @param pValue 字符串
// @return 长度
//============================================================
TInt RString16::Length(TChar16C* pValue){
   return (pValue != NULL) ? wcslen(pValue) : 0;
}

//============================================================
// <T>判断来源字符串中是否含有指定字符串。</T>
//
// @param pSource 来源字符串
// @param pValue 指定字符串
// @return 是否含有
//============================================================
TBool RString16::Constains(TChar16C* pSource, TChar16C* pValue){
   TInt sourceLength = Length(pSource);
   TInt valueLength = Length(pValue);
   TInt index = RChar16s::Find(pSource, sourceLength, pValue, valueLength);
   return (ENotFound != index);
}

//============================================================
// <T>判断两个字符串是否相等。</T>
//
// @param pSource 来源字符串
// @param pTarget 目标字符串
// @return 是否相等
//============================================================
TBool RString16::Equals(TChar16C* pSource, TChar16C* pTarget){
   // 长度都为0的时候认为相等
   TInt sourceLength = 0;
   if(pSource != NULL){
      sourceLength = MO_LIB_STRING_LENGTH16(pSource);
   }
   TInt targetLength = 0;
   if(pTarget != NULL){
      targetLength = MO_LIB_STRING_LENGTH16(pTarget);
   }
   if((sourceLength == 0) && (targetLength == 0)){
      return ETrue;
   }
   // 任何一方为空认为不相等
   if((pSource == NULL) || (pTarget == NULL)){
      return EFalse;
   }
   // 比较内容
   TInt result = MO_LIB_STRING_COMPARE16(pSource, pTarget);
   return (result == 0);
}

//============================================================
// <T>查找来源字符串中指定字符串的索引位置。</T>
//
// @param pSource 来源字符串
// @param pValue 指定字符串
// @return 索引位置
//============================================================
TInt RString16::Find(TChar16C* pSource, TChar16C* pValue){
   TInt sourceLength = Length(pSource);
   TInt valueLength = Length(pValue);
   TInt index = RChar16s::Find(pSource, sourceLength, pValue, valueLength);
   return index;
}

//============================================================
// <T>计算字符串的哈希值。</T>
//
// @param pValue 字符串
// @return 哈希值
//============================================================
THashCode RString16::MakeHashCode(TChar16C* pValue){
   THashCode code = 0;
   if(pValue != NULL){
      TInt length = wcslen(pValue);
      code = RTypes<TChar16>::MakeHashCode(pValue, length);
   }
   return code;
}

//============================================================
// <T>计算字符串的不区分大小写的哈希值。</T>
//
// @param pValue 字符串
// @return 哈希值
//============================================================
THashCode RString16::MakeNocaseHashCode(TChar16C* pValue){
   THashCode code = 0;
   if(pValue != NULL){
      TInt length = wcslen(pValue);
      while(--length >= 0){
         code += (code << 4) + (code << 3) + (code << 2) + (code << 1) + tolower(pValue[length]);
      }
   }
   return code;
}

////============================================================
//// <T>获取ANSI字符串的哈希值。</T>
////
//// @param ptr ANSI字符串指针
//// @return 哈希值
////============================================================
//TBool RString16::AllocCopy(TChar16C* pValues, TChar16** ppTarget){
//   if(NULL != pValues){
//      TSize length = wcslen(pValues) + 1;
//      TChar16* pAlloc = RTypeMemory<TChar16>::Alloc(length);
//      MO_ASSERT(pAlloc);
//      MO_LIB_MEMCPY(pAlloc, length, pValues, length);
//      *ppTarget = pAlloc;
//   }
//   return ETrue;
//}
//
////============================================================
//TBool RString16::AllocFree(TChar16* pValues){
//   RTypeMemory<TChar16>::Free(pValues);
//   return ETrue;
//}

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
TInt RString16::SafeCopy(TChar16* pTarget, TSize size, TChar8C* pValue){
   if((NULL != pTarget) && (size > 0) && (NULL != pValue)){
      TSize length = strlen(pValue);
      if(length > 0){
         if(length >= size){
            length = size - 1;
         }
         for(TInt n = 0; n < length; n++){
            *pTarget++ = *pValue++;
         }
         *pTarget = 0;
      }else{
         pTarget[0] = 0;
      }
      return length;
   }
   return 0;
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
TInt RString16::SafeCopy(TChar16* pTarget, TSize size, TChar16C* pValue){
   if((NULL != pTarget) && (size > 0) && (NULL != pValue)){
      TSize length = wcslen(pValue);
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

////============================================================
//// <T>强制复制字符串到目标字符串。</T>
//// <P>如果目标字符串为空，产生例外。</P>
//// <P>如果值字符串为空，返回成功。</P>
//// <P>如果目标字符串长度不足，产生例外。</P>
//// <P>复制后，末尾字符总是留空。</P>
////
//// @param pTarget 目标字符串指针
//// @param size 目标字符窜长度
//// @param pValue 值字符串指针
//// @return 复制长度
////============================================================
//TInt RString16::ForceCopy(TChar16* pTarget, TSize size, TChar16C* pValue){
//   MO_ASSERT(pTarget);
//   MO_ASSERT(size > 0);
//   if(NULL != pValue){
//      TSize length = wcslen(pValue);
//      if(length > 0){
//         MO_ASSERT(length < size);
//         memcpy(pTarget, pValue, length);
//         pTarget[length] = 0;
//         return length;
//      }
//   }
//   return 0;
//}
//
////============================================================
//// <T>在一个字符串中，替换来源字符串为目标字符串。</T>
////
//// @param str1 要查到的字符串
//// @param str2 字符串的间隔
//// @return 复制长度
////============================================================
//TInt RString16::ReplaceAll(TChar16* pValue, TChar16* pSource, TChar16* pTarget, TChar16* pBuffer, TInt bufferLength){
//   MO_ASSERT(pValue);
//   MO_ASSERT(pSource);
//   MO_ASSERT(pTarget);
//   MO_ASSERT(pBuffer);
//   TInt length = wcslen(pValue);
//   TInt sourceLength = wcslen(pSource);
//   TInt targetLength = wcslen(pTarget);
//   return RTypes<TChar16>::ReplaceAll(pValue, length, pSource, sourceLength, pTarget, targetLength, pBuffer, bufferLength);
//}

//============================================================
// <T>获得将字符串转化为8位字符串所需要的长度</T>
//
// @param pValue 被转换字符串
// @return 所需要长度
//============================================================
TInt RString16::ConvertToString8(TChar16C* pValue){
   MO_ASSERT(pValue);
   TSize length = MO_LIB_STRING_LENGTH16(pValue);
#ifdef _MO_WINDOWS
   TInt result = WideCharToMultiByte(CP_ACP, 0, pValue, length, NULL, 0, NULL, NULL);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   TInt result = sizeof(TChar16) * length;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   TInt result = length;
#endif // _MO_ANDROID
   return result;
}

//============================================================
// <T>将字符串转化为8位字符串</T>
//
// @param pTarget转换后的字符串
// @param size pTarget容量
// @param pValue 被转换字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RString16::ConvertToString8(TChar8* pTarget, TInt size, TChar16C* pValue){
   MO_ASSERT(pTarget);
   MO_ASSERT(pValue);
   TSize length = MO_LIB_STRING_LENGTH16(pValue);
#ifdef _MO_WINDOWS
   TInt result = WideCharToMultiByte(CP_ACP, 0, pValue, length, pTarget, size, NULL, NULL);
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   iconv_t code = iconv_open("GBK", "UTF-8");
   if(NULL == code){
      MO_STATIC_FATAL("Iconv open failure.");
   }
   TChar8* pInput = (TChar8*)pValue;
   TSize inputLength = length;
   TSize outputLength = size;
   TInt result = iconv(code, &pInput, &inputLength, &pTarget, &outputLength);
   if(-1 == result){
      MO_STATIC_FATAL("Iconv convert failure.");
      return -1;
   }
   iconv_close(code);
   pTarget[outputLength] = 0;
#endif // _MO_LINUX
#ifdef _MO_ANDROID
   TInt result = length;
   // g_lpdlIcuuc = dlopen("/system/lib/libicuuc.so", RTLD_LAZY);
#endif // _MO_ANDROID
   return result;
}

//============================================================
// <T>获得将字符串转化为16位字符串所需要的长度</T>
//
// @param pValue 被转换字符串
// @return 所需要长度
//============================================================
TInt RString16::ConvertToString16(TChar16C* pValue){
   MO_ASSERT(pValue);
   return MO_LIB_STRING_LENGTH16(pValue);
}

//============================================================
// <T>将字符串转化为16位字符串</T>
//
// @param pTarget转换后的字符串
// @param size pTarget容量
// @param pValue 被转换字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RString16::ConvertToString16(TChar16* pTarget, TInt size, TChar16C* pValue){
   MO_ASSERT(pTarget);
   MO_ASSERT(pValue);
   TSize length = MO_LIB_STRING_LENGTH16(pValue);
   MO_LIB_MEMORY_COPY(pTarget, sizeof(TChar16) * length, pValue, sizeof(TChar16) * length);
   return length;
}

//============================================================
// <T>获得将字符串转化为32位字符串所需要的长度</T>
//
// @param pValue 被转换字符串
// @return 所需要长度
//============================================================
TInt RString16::ConvertToString32(TChar16C* pValue){
   return 0;
}

//============================================================
// <T>将字符串转化为16位字符串</T>
//
// @param pTarget转换后的字符串
// @param size pTarget容量
// @param pValue 被转换字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RString16::ConvertToString32(TChar32* pTarget, TInt size, TChar16C* pValue){
   return 0;
}

MO_NAMESPACE_END
