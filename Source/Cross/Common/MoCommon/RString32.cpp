#include "MoCmString32.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>空字符串。</T>
//============================================================
TChar32C* RString32::EmptyPtr = L"";
TString32 RString32::EmptyString;

//============================================================
// <T>获取ANSI字符串的长度。</T>
//
// @param pSource ANSI字符串
// @return 长度
//============================================================
TInt RString32::Length(TChar32C* pValues){
   MO_ASSERT(pValues);
   return wcslen(pValues);
}

//============================================================
// <T>判断来源字符串中是否含有指定字符串。</T>
//
// @param pSource 来源字符串
// @param pValue 指定字符串
// @return 是否含有
//============================================================
TBool RString32::Constains(TChar32C* pSource, TChar32C* pValue){
   TInt sourceLength = Length(pSource);
   TInt valueLength = Length(pValue);
   TInt index = RChar32s::Find(pSource, sourceLength, pValue, valueLength);
   return (ENotFound != index);
}

//============================================================
// <T>判断两个字符串是否相等。</T>
//
// @param pSource 来源字符串
// @param pTarget 目标字符串
// @return 是否相等
//============================================================
TBool RString32::Equals(TChar32C* pSource, TChar32C* pTarget){
   // 长度都为0的时候认为相等
   TInt sourceLength = 0;
   if(pSource != NULL){
      sourceLength = MO_LIB_STRING_LENGTH32(pSource);
   }
   TInt targetLength = 0;
   if(pTarget != NULL){
      targetLength = MO_LIB_STRING_LENGTH32(pTarget);
   }
   if((sourceLength == 0) && (targetLength == 0)){
      return ETrue;
   }
   // 任何一方为空认为不相等
   if((pSource == NULL) || (pTarget == NULL)){
      return EFalse;
   }
   // 比较内容
   TInt result = MO_LIB_STRING_COMPARE32(pSource, pTarget);
   return (result == 0);
}

//============================================================
// <T>查找来源字符串中指定字符串的索引位置。</T>
//
// @param pSource 来源字符串
// @param pValue 指定字符串
// @return 索引位置
//============================================================
TInt RString32::Find(TChar32C* pSource, TChar32C* pValue){
   TInt sourceLength = Length(pSource);
   TInt valueLength = Length(pValue);
   TInt index = RChar32s::Find(pSource, sourceLength, pValue, valueLength);
   return index;
}

////============================================================
//// <T>获取ANSI字符串的哈希值。</T>
////
//// @param pValues ANSI字符串
//// @return 哈希值
////============================================================
//THashCode RString32::MakeHashCode(TChar32C* pValues){
//   MO_ASSERT(pValues);
//   return RTypes<TChar32>::MakeHashCode(pValues, wcslen(pValues));
//}
//
////============================================================
//// <T>获取ANSI字符串的不区分大小写的哈希值。</T>
////
//// @param pValues ANSI字符串
//// @return 哈希值
////============================================================
//THashCode RString32::MakeNocaseHashCode(TChar32C* pValues){
//   MO_ASSERT(pValues);
//   THashCode hash = 0;
//   TInt length = wcslen(pValues);
//   while(--length >= 0){
//      hash += (hash << 4) + (hash << 3) + (hash << 2) + (hash << 1) + towlower(pValues[length]);
//   }
//   return hash;
//}
//
////============================================================
//// <T>获取ANSI字符串的哈希值。</T>
////
//// @param ptr ANSI字符串指针
//// @return 哈希值
////============================================================
//TBool RString32::AllocCopy(TChar32C* pValues, TChar32** ppTarget){
//   if(NULL != pValues){
//      TSize length = wcslen(pValues) + 1;
//      TChar32* pAlloc = MO_STATIC_TYPES_ALLOC(TChar32, length);
//      MO_LIB_MEMCPY(pAlloc, length, pValues, length);
//      *ppTarget = pAlloc;
//   }
//   return ETrue;
//}
//
////============================================================
//TBool RString32::AllocFree(TChar32* pValues){
//   MO_FREE(pValues);
//   return ETrue;
//}
//
////============================================================
//// <T>安全复制字符串到目标字符串。</T>
//// <P>如果目标字符串或值字符串为空，则不进行复制。</P>
//// <P>如果目标字符串长度不足，则只复制能复制的部分数据。</P>
//// <P>复制后，末尾字符总是留空。</P>
////
//// @param pTarget 目标字符串指针
//// @param size 目标字符窜长度
//// @param pValue 值字符串指针
//// @return 复制长度
////============================================================
//TInt RString32::SafeCopy(TChar32* pTarget, TSize size, TChar32C* pValue){
//   if((NULL != pTarget) && (size > 0) && (NULL != pValue)){
//      TSize length = wcslen(pValue);
//      if(length > 0){
//         if(length >= size){
//            length = size - 1;
//         }
//         memcpy(pTarget, pValue, length);
//         pTarget[length] = 0;
//      }else{
//         pTarget[0] = 0;
//      }
//      return length;
//   }
//   return 0;
//}
//
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
//TInt RString32::ForceCopy(TChar32* pTarget, TSize size, TChar32C* pValue){
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

//============================================================
// <T>获得将字符串转化为8位字符串所需要的长度</T>
//
// @param pValue 被转换字符串
// @return 所需要长度
//============================================================
TInt RString32::ConvertToString8(TChar32C* pValue){
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
TInt RString32::ConvertToString8(TChar8* pTarget, TInt size, TChar32C* pValue){
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
TInt RString32::ConvertToString16(TChar32C* pValue){
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
TInt RString32::ConvertToString16(TChar16* pTarget, TInt size, TChar32C* pValue){
   return 0;
}

//============================================================
// <T>获得将字符串转化为32位字符串所需要的长度</T>
//
// @param pValue 被转换字符串
// @return 所需要长度
//============================================================
TInt RString32::ConvertToString32(TChar32C* pValue){
   MO_ASSERT(pValue);
   return MO_LIB_STRING_LENGTH32(pValue);
}

//============================================================
// <T>将字符串转化为16位字符串</T>
//
// @param pTarget转换后的字符串
// @param size pTarget容量
// @param pValue 被转换字符串
// @return 所需要长度，如果失败返回-1
//============================================================
TInt RString32::ConvertToString32(TChar32* pTarget, TInt size, TChar32C* pValue){
   MO_ASSERT(pTarget);
   MO_ASSERT(pValue);
   TSize length = MO_LIB_STRING_LENGTH32(pValue);
   MO_LIB_MEMORY_COPY(pTarget, sizeof(TChar32) * length, pValue, sizeof(TChar32) * length);
   return length;
}

MO_NAMESPACE_END
