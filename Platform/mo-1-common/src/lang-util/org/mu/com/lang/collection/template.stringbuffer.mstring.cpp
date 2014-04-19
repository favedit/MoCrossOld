#include "MoCmString{type}.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>计算内存扩展后容量。</T>
//
// @param size 大小
// @return 扩展后大小
//============================================================
TInt MStringBuffer{type}::InnerCalculateCapacity(TInt size){
   // 但收集量不得小于默认值<C>MO_MEMORY_CAPACITY</C>的内存
   TInt capacity = size;
   if(capacity < MO_MEMORY_CAPACITY){
      capacity = MO_MEMORY_CAPACITY;
   }
   // 当内存不足时，扩大1.5倍内存
   if(NULL != _pMemory){
      capacity += capacity >> 1;
   }
   return capacity;
}

//============================================================
// <T>调整内存大小。</T>
//
// @param size 大小
// @param copy 复制数据
// @param extends 自动扩展
//============================================================
void MStringBuffer{type}::InnerResize(TInt size, TBool copy, TBool extends){
   if(size > _capacity){
      // 当内存不足时，重新计算内存容量
      TInt capacity = size;
      if(extends){
         capacity = CalculateCapacity(size)
      }
      // 如果收集不成功，则不进行复制数据处理
      {char}* pAlloc = MO_TYPES_ALLOC({char}, capacity);
      MO_ASSERT(pAlloc);
      // 如果存在以前内存
      if(NULL != _pMemory){
         // 如果是缩小内存，则检查长度
         if(_length > capacity){
            _length = capacity;
         }
         // 复制有效数据
         if(copy && (_length > 0)){
            MO_LIB_TYPES_COPY({char}, pAlloc, capacity, _pMemory, _length);
         }
         // 释放以前内存
         MO_FREE(_pMemory);
      }
      // 设置新的内存
      _pMemory = pAlloc;
      _capacity = capacity;
   }
}

//============================================================
// <T>保证内存大小，不保留以前内容。</T>
//============================================================
void MStringBuffer{type}::ForceSize(TInt size){
   InnerResize(size, ETrue, EFalse);
}

//============================================================
// <T>保证内存大小，保留以前内容。</T>
//============================================================
void MStringBuffer{type}::EnsureSize(TInt size){
   InnerResize(size, ETrue, ETrue);
}

//============================================================
// <T>将指定UNICODE字符串指针对象赋值给当前字符串。 </T>
//
// @param pValue UNICODE 字符串指针对象
//============================================================
void MStringBuffer{type}::Assign16(TChar16C* pValue){
   Clear();
   Append16(pValue);
}

//============================================================
// <T>将指定UNICODE字符串指针对象赋值给当前字符串。 </T>
//
// @param pValue UNICODE 字符串指针对象
//============================================================
void MStringBuffer{type}::Assign32(TChar32C* pValue){
   Clear();
   Append32(pValue);
}

//============================================================
// <T>追加一个格式化字符串到对象尾部。</T>
//============================================================
void MStringBuffer{type}::AssignFormat(TChar8C* pFormat, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pFormat);
   // 输出日志信息
#ifdef _WINDOWS
   TInt length = _vscprintf(pFormat, params);
   EnsureExtend(length + 1);
   length = MO_LIB_VSPRINTF({memory}, length + 1, pFormat, params)
   _length = length;
#else
   TChar buffer[MO_FS_FORMAT_LENGTH];
   vsnprintf(buffer, MO_FS_FORMAT_LENGTH, pFormat, params);
   Assign(buffer);
#endif
   va_end(params);
}

//============================================================
// <T>追加一个格式化字符串到对象尾部。</T>
//============================================================
void MStringBuffer{type}::AssignFormat(TChar8C* pFormat, va_list& params){
   // 输出日志信息
#ifdef _WINDOWS
   TInt length = _vscprintf(pFormat, params);
   EnsureExtend(length + 1);
   length = vsprintf_s({memory}, length + 1, pFormat, params);
   _length = length;
#else
   TChar buffer[MO_FS_FORMAT_LENGTH];
   vsnprintf(buffer, MO_FS_FORMAT_LENGTH, pFormat, params);
   Assign(buffer);
#endif
}

//============================================================
// <T>将指定UNICODE字符串指针对象添加到当前字符串。 </T>
//
// @param pValue UNICODE 字符串指针对象
//============================================================
void MStringBuffer{type}::Append16(TChar16C* pValue){
   //TSize length = RString16::ConvertToString8(pValue);
   //if(0 == length){
   //   MO_ERROR(TC("Convert error!"));
   //}else{
   //   InnerInitialize(length + _length);
   //   RString16::ConvertToString8(_pMemory + length, length, pValue);
   //   _length = _length + length;
   //}
}

//============================================================
// <T>将指定UNICODE字符串指针对象添加到当前字符串。 </T>
//
// @param pValue UNICODE 字符串指针对象
//============================================================
void MStringBuffer{type}::Append32(TChar32C* pValue){
   //TSize length = RString32::ConvertToString8(pValue);
   //if(-1 == length){
   //   MO_ERROR("convert error!");
   //}else{
   //   EnsureSize(length + _length);
   //   RString32::ConvertToString8(_pMemory + length, length, pValue);
   //   _length = _length + length;
   //}
}

//============================================================
// <T>追加一个格式化字符串到对象尾部。</T>
//============================================================
void MStringBuffer{type}::AppendFormat(TChar8C* pFormat, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pFormat);
   // 输出日志信息
#ifdef _WINDOWS
   TInt length = _vscprintf(pFormat, params);
   EnsureExtend(length + 1);
   length = vsprintf_s({memory} + _length, length + 1, pFormat, params);
   _length += length;
#else
   TChar buffer[MO_FS_FORMAT_LENGTH];
   vsnprintf(buffer, MO_FS_FORMAT_LENGTH, pFormat, params);
   Append(buffer);
#endif
   va_end(params);
}

//============================================================
// <T>追加一个格式字符串</T>
//============================================================
void MStringBuffer{type}::AppendFormat(TChar8C* pFormat, va_list& params){
#ifdef _WINDOWS
   TInt length = _vscprintf(pFormat, params);
   EnsureExtend(length + 1);
   length = vsprintf_s({memory} + _length, length + 1, pFormat, params);
   _length += length;
#else
   TChar buffer[MO_FS_FORMAT_LENGTH];
   vsnprintf(buffer, MO_FS_FORMAT_LENGTH, pFormat, params);
   Append(buffer);
#endif
}

//============================================================
// <T>重复追加一个字符串到当前字符串对象的末尾。</T>
//============================================================
void MStringBuffer{type}::AppendRepeat(TChar8C* pValue, TInt count){
   for(TInt n = 0; n < count; n++){
      Append(pValue);
   }
}

//============================================================
// <T>追加一个空行。</T>
//============================================================
void MStringBuffer{type}::AppendLine(){
   Push('\n');
}

//============================================================
// <T>将内部字符串转换为小写。</T>
//============================================================
void MStringBuffer{type}::ToLower(){
   RChar8::ToLower({memory}, _length);
}

//============================================================
// <T>将内部字符串转换为大写。</T>
//============================================================
void MStringBuffer{type}::ToUpper(){
   RChar8::ToUpper({memory}, _length);
}

MO_NAMESPACE_END
