#include "MoCmString{type}.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>将指定UNICODE字符串指针对象赋值给当前字符串。 </T>
//
// @param pValue UNICODE 字符串指针对象
//============================================================
void MString{type}::Assign16(TChar16C* pValue){
   Clear();
   Append16(pValue);
}

//============================================================
// <T>将指定UNICODE字符串指针对象赋值给当前字符串。 </T>
//
// @param pValue UNICODE 字符串指针对象
//============================================================
void MString{type}::Assign32(TChar32C* pValue){
   Clear();
   Append32(pValue);
}

//============================================================
// <T>追加一个格式化字符串到对象尾部。</T>
//============================================================
void MString{type}::AssignFormat(TChar8C* pFormat, ...){
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
void MString{type}::AssignFormat(TChar8C* pFormat, va_list& params){
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
void MString{type}::Append16(TChar16C* pValue){
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
void MString{type}::Append32(TChar32C* pValue){
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
void MString{type}::AppendFormat(TChar8C* pFormat, ...){
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
void MString{type}::AppendFormat(TChar8C* pFormat, va_list& params){
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
void MString{type}::AppendRepeat(TChar8C* pValue, TInt count){
   for(TInt n = 0; n < count; n++){
      Append(pValue);
   }
}

//============================================================
// <T>追加一个空行。</T>
//============================================================
void MString{type}::AppendLine(){
   Push('\n');
}

//============================================================
// <T>将内部字符串转换为小写。</T>
//============================================================
void MString{type}::ToLower(){
   RChar8::ToLower({memory}, _length);
}

//============================================================
// <T>将内部字符串转换为大写。</T>
//============================================================
void MString{type}::ToUpper(){
   RChar8::ToUpper({memory}, _length);
}

MO_NAMESPACE_END
