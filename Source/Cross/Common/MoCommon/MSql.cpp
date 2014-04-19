#include "MoCmData.h"
#include "MoCmMemory.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>追加一个布尔值。</T>
//
// @param value 布尔值
//============================================================
void MSql::AppendBoolean(TBool value){
   if(value){
      Append(TC("TRUE"));
   }else{
      Append(TC("FALSE"));
   }
}

//============================================================
// <T>追加一个整数。</T>
//
// @param value 整数
//============================================================
void MSql::AppendInteger(TInt value){
   TChar buffer[MO_FS_NUMBER_LENGTH];
   Append(RNumber<TInt32>::ToSignString<TChar, TUint32>(buffer, MO_FS_NUMBER_LENGTH, value));
}

//============================================================
// <T>追加一个字符串到当前字符串对象的末尾。</T>
// <P>如果长度小于0，则自动计算指针的数据长度。</P>
//
// @param pValue 内容
// @param length 长度
//============================================================
void MSql::AppendString(TCharC* pValue, TInt length){
   // 检查内存（禁止追加自内存）
   if(_pMemory == pValue){
      return;
   }
   // 检查参数
   if(NULL == pValue){
      if(0 != length){
         MO_FATAL(TC("Invalid string memory. (value=0x%08X, length=%d)"), pValue, length);
         return;
      }
   }else{
      // 计算长度
      if(length <= 0){
         length = RTypes<TChar>::IndexOf(pValue, 0);
      }
   }
   MO_ASSERT(length >= 0);
   // 复制数据
   if(0 == length){
      Append(TC("NULL"));
   }else{
      // 调整内存大小
      TInt maxLength = _length + length + length + 1;
      if(maxLength >= _capacity){
         EnsureSize(maxLength);
      }
      // 复制字符串
      TChar* pWriter = _pMemory + _length;
      *pWriter++ = '\'';
      for(TInt n = 0; n < length; n++){
         TChar value = pValue[n];
         if(value == '\''){
            // 特殊字符 (')
            *pWriter++ = '\\';
            *pWriter++ = value;
         }else if(value == '"'){
            // 特殊字符 (")
            *pWriter++ = '\\';
            *pWriter++ = value;
         }else if(value == '\\'){
            // 特殊字符 (\)
            *pWriter++ = '\\';
            *pWriter++ = value;
         }else if(value == '\r'){
            // 特殊字符 (\r)
            *pWriter++ = '\\';
            *pWriter++ = value;
         }else if(value == '\n'){
            // 特殊字符 (\n)
            *pWriter++ = '\\';
            *pWriter++ = value;
         }else{
            *pWriter++ = value;
         }
      }
      *pWriter++ = '\'';
      // 计算长度
      _length = pWriter - _pMemory;
      _pMemory[_length] = 0;
   }
}

//============================================================
// <T>追加一个格式化字符串到对象尾部。</T>
//
// @param pFormat 格式字符串
//============================================================
void MSql::AppendStringFormat(TCharC* pFormat, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pFormat);
   // 输出日志信息
#ifdef _MO_LINUX
   TChar buffer[MO_FS_SPRINT_LENGTH];
   TInt length = vsnprintf(buffer, MO_FS_SPRINT_LENGTH - 1, pFormat, params);
   buffer[length] = 0;
   AppendString(buffer);
#endif
   va_end(params);
}

MO_NAMESPACE_END
