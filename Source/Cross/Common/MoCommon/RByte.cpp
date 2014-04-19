#include "MoCmType.h"

#define HEX_HEX_DELIMER ' '
#define HEX_TEX_DELIMER '-'

#define MO_LINE_MAXLENGTH 32

MO_NAMESPACE_BEGIN

//============================================================
TChar RByte::HEX_CHARS[16] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
TByte RByte::HEX_BYTES[256] = {
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0, 0, 0,
      0,10,11,12,13,14,15, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0,10,11,12,13,14,15, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
      0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
   };

//============================================================
// <T>解析16进制字符串成为字节数组。</T>
//
// @param pSource 字符串指针
// @param length 字符串长度
// @param pBytes 字节数组
// @param capacity 字节容量
//============================================================
TSize RByte::Parse(TCharC* pSource, TSize length, TByte* pBytes, TSize capacity){
   // 检查参数
   if((NULL == pSource) || (0 == length) || (NULL == pBytes) || (0 == capacity)){
      return 0;
   }
   // 处理字节流
   TSize count = 0;
   TByte* pWrite = pBytes;
   while(ETrue){
      TByte value = 0;
      // 读取高4位
      if(0 == length){
         break;
      }
      TCharC hb = *pSource++;
      if(!(((hb >= '0') && (hb <= '9')) || ((hb >= 'A') && (hb <= 'F')))){
         MO_STATIC_FATAL(TC("Unknown high hex char. (char=%d)"), hb);
      }
      length--;
      value = ((hb >= 'A') ? (10 + hb - 'A') : (hb - '0')) << 4;
      // 读取底4位
      if(0 == length){
         break;
      }
      TCharC lb = *pSource++;
      if(!(((lb >= '0') && (lb <= '9')) || ((lb >= 'A') && (lb <= 'F')))){
         MO_STATIC_FATAL(TC("Unknown lower hex char. (char=%d)"), lb);
      }
      length--;
      value += (lb >= 'A') ? (10 + lb - 'A') : (lb - '0');
      *pWrite++ = value;
      count++;
      capacity--;
      if(0 == capacity){
         break;
      }
   }
   // 写入结尾
   *pWrite = 0;
   // 返回结果
   return count;
}

//============================================================
// <T>格式化字节数组成为16进制字符串。</T>
//
// @param pBytes 字节数组
// @param length 字节长度
// @param pResult 字符串指针
// @param capacity 字符串容量
//============================================================
TCharC* RByte::Format(TByteC* pBytes, TInt length, TChar* pResult, TSize capacity){
   // 检查参数
   MO_ASSERT(pBytes);
   MO_ASSERT(pResult);
   if((0 == length) || (0 == capacity)){
      return pResult;
   }
   // 字符转换
   TInt n = -1;
   TChar* pWrite = pResult;
   while(++n < length){
      TUint8 byte = pBytes[n];
      // 写入高字节
      *pWrite++ = HEX_CHARS[byte >> 4];
      if(--capacity < 1){
         break;
      }
      // 写入低字节
      *pWrite++ = HEX_CHARS[byte & 0x0F];
      if(--capacity < 1){
         break;
      }
   }
   // 写入结尾
   *pWrite = 0;
   // 返回结果
   return pResult;
}

//============================================================
// <T>格式化字节数组成为16进制显示字符串。</T>
//
// @param pBytes 字节数组
// @param length 字节长度
// @param pResult 字符串指针
// @param capacity 字符串容量
//============================================================
TCharC* RByte::FormatDisplay(TByteC* pBytes, TInt length, TChar* pResult, TSize capacity){
   // 检查参数
   MO_ASSERT(pBytes);
   MO_ASSERT(pResult);
   if((0 == length) || (0 == capacity)){
      return pResult;
   }
   // 字符转换
   TInt n = -1;
   TChar* pWrite = pResult;
   while(++n < length){
      TUint8 byte = pBytes[n];
      // 写入空字符
      if(n > 0){
         *pWrite++ = ' ';
         if(--capacity < 1){
            break;
         }
      }
      // 写入高字节
      *pWrite++ = HEX_CHARS[byte >> 4];
      if(--capacity < 1){
         break;
      }
      // 写入低字节
      *pWrite++ = HEX_CHARS[byte & 0x0F];
      if(--capacity < 1){
         break;
      }
   }
   // 写入结尾
   *pWrite = 0;
   // 返回结果
   return pResult;
}

//============================================================
// <T>字节流格式化。</T>
//
// <P>输出格式：地址: 十六进制数据 文本</P>
// @param pBytes 输入字节流首地址
// @param count 输入字节流的长度
// @param columns 显示的列数
// @param pResult 结果字符串缓存
// @param length  结果字符串缓存长度
// @return 格式化后的字符串指针
//============================================================
TCharC* RByte::FormatMemory(TByteC* pBytes, TInt count, TInt columns, TChar* pResult, TInt length){
   TInt index = 0;
   TInt position = 0;
   TInt size = (columns << 2) + 22;
   while(length > size + 4){
      MO_LIB_STRING_FORMAT(pResult + position, length - position, TC(" - 0x%04") MO_FMT_HEX TC("(% 6") MO_FMT_INT TC("):"), index, index);
      TChar* pWrite = pResult + position + 18;
      TChar* pChars = pWrite + (3 * columns);
      *pChars++ = ' ';
      *pChars++ = '[';
      for(int n = 0; n < columns; n++){
         *pWrite++ = ' ';
         if(index < count){
            TByte byte = pBytes[index++] & 0xFF;
            *pWrite++ = HEX_CHARS[byte >> 4];
            *pWrite++ = HEX_CHARS[byte & 0x0F];
            *pChars++ = ((byte >= 32) && (byte < 127) ) ? (TChar)byte : '.';
         }else{
            *pWrite++ = ' ';
            *pWrite++ = ' ';
            *pChars++ = ' ';
         }
      }
      *pChars++ = ']';
      if(index >= count){
         *pChars = 0;
         break;
      }
      length -= size;
      if(length < size + 4){
         *pChars++ = ' ';
         *pChars++ = '>';
         *pChars++ = '>';
      }else{
         *pChars = '\n';
      }
      position += size;
   }
   return pResult;
}

//============================================================
// <T>字节流格式化。</T>
//
// <P>输出格式：地址: 十六进制数据 文本</P>
// @param pBytes 输入字节流首地址
// @param count 输入字节流的长度
// @param pResult 结果字符串缓存
// @param length  结果字符串缓存长度
// @return 格式化后的字符串指针
//============================================================
TCharC* RByte::Dump(TByteC* pBytes, TInt count, TChar* pResult, TInt length){
   return FormatMemory(pBytes, count, 16, pResult, length);
}

MO_NAMESPACE_END
