#include "MoCmLanguage.h"
#include "MoCmStream.h"
#include "MoCmString8.h"
#include "MoCmString16.h"
#include "MoCmString32.h"

#define MO_LINE_CHAR '\n'

MO_NAMESPACE_BEGIN

//============================================================
// <T>获得一个末尾是空的字符串。</T>
//
// @return 字符串
//============================================================
TChar16* MString16::MemoryZ(){
   // 设置结束符
   EnsureSize(_length + 1);
   _pMemory[_length] = 0;
   // 返回内存指针
   return _pMemory;
}

//============================================================
// <T>获得字符串指针。</T>
//
// @return 字符串指针
//============================================================
TString16PtrC MString16::PtrZ(){
   // 设置结束符
   EnsureSize(_length + 1);
   _pMemory[_length] = 0;
   // 返回内存指针
   return TString16PtrC(_pMemory, _length);
}

//============================================================
// <T>获得只读字符串指针。</T>
//
// @return 字符串指针
//============================================================
TString16PtrC MString16::StrC() const{
   return TString16PtrC(_pMemory, _length);
}

//============================================================
// <T>获得当前数组中的左边部分数组的引用。</T>
//
// @param length 长度
// @return 字符串指针
//============================================================
TString16PtrC MString16::LeftStrC(TInt length) const{
   MO_ASSERT_BETWEEN(length, 0, _length);
   return TString16PtrC(_pMemory, length);
}

//============================================================
// <T>获得当前数组中的开始到结尾的引用。</T>
//
// @param offset 位置
// @return 字符串指针
//============================================================
TString16PtrC MString16::MidStrC(TInt offset) const{
   MO_ASSERT_BETWEEN(offset, 0, _length);
   return TString16PtrC(_pMemory + offset, _length - offset);
}

//============================================================
// <T>获得当前数组中的部分数组的引用。</T>
//
// @param offset 位置
// @param length 长度
// @return 字符串指针
//============================================================
TString16PtrC MString16::MidStrC(TInt offset, TInt length) const{
   MO_ASSERT_BETWEEN(offset, 0, _length);
   MO_ASSERT_BETWEEN(length, 0, _length - offset);
   return TString16PtrC(_pMemory + offset, length);
}

//============================================================
// <T>获得当前数组中的右边部分数组的引用。</T>
//
// @param length 长度
// @return 字符串指针
//============================================================
TString16PtrC MString16::RightStrC(TInt length) const{
   MO_ASSERT_BETWEEN(length, 0, _length);
   return TString16PtrC(_pMemory + (_length - length), length);
}

//============================================================
// <T>获得当前数组中的部分数组的引用。</T>
//
// @param begin 开始位置
// @param end 结束位置
// @return 字符串指针
//============================================================
TString16PtrC MString16::SubStrC(TInt begin, TInt end) const{
   MO_ASSERT_BETWEEN(begin, 0, _length);
   MO_ASSERT_BETWEEN(end, 0, _length);
   MO_ASSERT(begin <= end);
   return TString16PtrC(_pMemory + begin, end - begin);
}

//============================================================
// <T>去掉左边的空白，返回字符串指针。</T>
//
// @return 字符串指针
//============================================================
TString16PtrC MString16::TrimLeftStrC() const{
   TInt position = 0;
   TInt length = Length();
   TChar16* pMemory = _pMemory;
   while((position < length) && (pMemory[position] <= ' ')){
      position++;
   }
   return TString16PtrC(pMemory + position, length - position);
}

//============================================================
// <T>去掉右边的空白，返回字符串指针。</T>
//
// @return 字符串指针
//============================================================
TString16PtrC MString16::TrimRightStrC() const{
   TInt position = Length() - 1;
   TChar16* pMemory = _pMemory;
   while((position >= 0) && (pMemory[position] <= ' ')){
      position--;
   }
   return TString16PtrC(pMemory, position);
}

//============================================================
// <T>去掉两边的空白，返回字符串指针。</T>
//
// @return 字符串指针
//============================================================
TString16PtrC MString16::TrimStrC() const{
   TInt start = 0;
   TInt length = Length();
   TChar16* pMemory = _pMemory;
   // 去掉左边空白字符
   while((start < length) && (pMemory[start] <= ' ')){
      start++;
   }
   // 去掉右边空白字符
   TInt end = length - 1;
   while((end >= 0) && (pMemory[end] <= ' ')){
      end--;
   }
   return TString16PtrC(pMemory + start, end - start + 1);
}

//============================================================
// <T>判断当前字符串和指定字符串是否相等。</T>
//
// @param pValue 指定字符串
// @return <L value='ETrue'>相等</L>
//         <L value='EFalse'>不相等</L>
//============================================================
TBool MString16::Equals(TChar16C* pValue) const{
   return RString16::Equals(MemoryC(), pValue);
}

//============================================================
// <T>不关心字符大小写，判断当前字符串和指定字符串是否相等。</T>
//
// @param pValue 指定字符串
// @return <L value='ETrue'>相等</L>
//         <L value='EFalse'>不相等</L>
//============================================================
TBool MString16::EqualsIgnoreCase(TChar16C* pValue) const{
   return (0 == MO_LIB_STRING_COMPARE_IGNORECASE16(MemoryC(), pValue));
}

//============================================================
// <T>不关心字符大小写，判断当前字符串和指定字符串是否相等。</T>
//
// @param value 指定字符串
// @return <L value='ETrue'>相等</L>
//         <L value='EFalse'>不相等</L>
//============================================================
TBool MString16::EqualsIgnoreCase(const TString16PtrC& value) const{
   return (0 == MO_LIB_STRING_COMPARE_IGNORECASE16(MemoryC(), value.MemoryC()));
}

//============================================================
// <T>查找指定数组是否出现在当前数组的开始位置。</T>
//
// @param pValue 指定字符串
// @return <L value='ETrue'>相等</L>
//         <L value='EFalse'>不相等</L>
//============================================================
TBool MString16::StartsWith(TChar16C* pValue) const{
   MO_ASSERT(pValue);
   TInt length = MO_LIB_STRING_LENGTH16(pValue);
   return RTypes<TChar16C>::StartsWith(_pMemory, _length, pValue, length);
}

//============================================================
// <T>查找指定数组是否出现在当前数组的结束位置。</T>
//
// @param pValue 指定字符串
// @return <L value='ETrue'>相等</L>
//         <L value='EFalse'>不相等</L>
//============================================================
TBool MString16::EndsWith(TChar16C* pValue) const{
   MO_ASSERT(pValue);
   TInt length = MO_LIB_STRING_LENGTH16(pValue);
   return RTypes<TChar16C>::EndsWith(_pMemory, _length, pValue, length);
}

//============================================================
// <T>不关心字符大小写，判断当前字符串和指定字符串的大小。</T>
//
// @param pValue 指定字符串
// @return <L value='小于0'>当前字符串小于指定字符串</L>
//         <L value='等于0'>当前字符串相等指定字符串</L>
//         <L value='大于0'>当前字符串大于指定字符串</L>
//============================================================
TInt MString16::CompareIgnoreCase(TChar16C* pValue) const{
   return MO_LIB_STRING_COMPARE_IGNORECASE16(MemoryC(), pValue);
}

//============================================================
// <T>不关心字符大小写，判断当前字符串和指定字符串的大小。</T>
//
// @param value 指定字符串
// @return <L value='小于0'>当前字符串小于指定字符串</L>
//         <L value='等于0'>当前字符串相等指定字符串</L>
//         <L value='大于0'>当前字符串大于指定字符串</L>
//============================================================
TInt MString16::CompareIgnoreCase(const TString16PtrC& value) const{
   return MO_LIB_STRING_COMPARE_IGNORECASE16(MemoryC(), value.MemoryC());
}

//============================================================
// <T>计算当前字符串的哈希值。</T>
//
// @return 哈希值
//============================================================
THashCode MString16::HashCode() const{
   return RTypes<TChar16>::MakeHashCode(_pMemory, _length);
}

//============================================================
// <T>从当前字符串中查找指定字符串的索引位置。</T>
//
// @param pValue 字符串
// @return 索引位置
//============================================================
TInt MString16::Find(TChar16C* pValue){
   TInt length = MO_LIB_STRING_LENGTH16(pValue);
   return RTypes<TChar16>::Find(_pMemory, _length, pValue, length);
}

//============================================================
// <T>从当前字符串中查找指定字符串的索引位置。</T>
//
// @param pValue 字符串
// @param offset 位置
// @return 索引位置
//============================================================
TInt MString16::Find(TChar16C* pValue, TInt offset){
   MO_ASSERT_BETWEEN(offset, 0, _length);
   TInt length = MO_LIB_STRING_LENGTH16(pValue);
   TInt result = RTypes<TChar16>::Find(_pMemory + offset, _length - offset, pValue, length);
   return (ENotFound != result) ? offset + result : ENotFound;
}

//============================================================
// <T>从当前字符串中倒序查找指定字符串的索引位置。</T>
//
// @param pValue 字符串
// @return 索引位置
//============================================================
TInt MString16::LastFind(TChar16C* pValue){
   TInt length = MO_LIB_STRING_LENGTH16(pValue);
   return RTypes<TChar16>::LastFind(_pMemory, _length, pValue, length);
}

//============================================================
// <T>接受一个字符串到当前字符串。</T>
// <P>如果长度小于0，则自动计算指针的数据长度。</P>
//
// @param pValue 字符串
// @param length 长度
// @return 处理结果
//============================================================
TBool MString16::Assign(TChar16C* pValue, TInt length){
   // 清空内容
   Clear();
   // 检查参数
   if(NULL == pValue){
      return EFalse;
   }
   // 检查内存
   if(_pMemory == pValue){
      return EFalse;
   }
   // 计算长度
   if(length <= 0){
      length = MO_LIB_STRING_LENGTH16(pValue);
   }
   // 复制数据
   if(length > 0){
      // 调整内存大小
      InnerResize(length + 1, EFalse, EFalse, EFalse);
      // 复制数据
      RChar16s::Copy(_pMemory, pValue, length);
      _length = length;
      _pMemory[_length] = 0;
   }
   return ETrue;
}

//============================================================
// <T>接受一个字符串到当前字符串。</T>
//
// @param ptr 字符串指针
// @return 处理结果
//============================================================
TBool MString16::Assign(const TPtrC<TChar16>& ptr){
   // 检查参数
   if(ptr.IsEmpty()){
      Clear();
      return ETrue;
   }
   // 接受内容
   return Assign(ptr.MemoryC(), ptr.Length());
}

//============================================================
// <T>接受一个字符串到当前字符串。</T>
//
// @param value 字符串
// @return 处理结果
//============================================================
TBool MString16::Assign(const MString16& value){
   // 检查参数
   if(value.IsEmpty()){
      Clear();
      return ETrue;
   }
   // 接受内容
   return Assign(value.MemoryC(), value.Length());
}

//============================================================
// <T>接受一个字符串到当前字符串。</T>
//
// @param value 字符串
// @return 处理结果
//============================================================
TBool MString16::AssignPointer(const MString16* pValue){
   // 指针为空
   if(NULL == pValue){
      Clear();
      return ETrue;
   }
   // 内容为空
   if(pValue->IsEmpty()){
      Clear();
      return ETrue;
   }
   // 接收字符串
   return Assign(pValue->MemoryC(), pValue->Length());
}

//============================================================
// <T>追加一个格式化字符串到对象尾部。</T>
//
// @param pFormat 格式
// @param params 参数集合
// @return 处理结果
//============================================================
TResult MString16::AssignFormat(TChar16C* pFormat, ...){
   // 检查参数
   MO_CHECK(pFormat, return ENull);
   // 清空内容
   Clear();
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pFormat);
   // 追加内容
#ifdef _MO_WINDOWS
   TInt length = MO_LIB_STRING_FORMAT_LENGTH16(pFormat, params);
   InnerResize(length + 1, EFalse, EFalse, EFalse);
   MO_LIB_STRING_NFORMAT16(_pMemory, length + 1, pFormat, params);
#else
   TChar16 buffer[MO_FS_SPRINT_LENGTH];
   TInt length = MO_LIB_STRING_NFORMAT16(buffer, MO_FS_SPRINT_LENGTH - 1, pFormat, params);
   MO_ERROR_CHECK(length >= 0, return EUnsupport,
         "Format string failure. (format=%s)", pFormat);
   InnerResize(length + 1, EFalse, EFalse, EFalse);
   MO_LIB_TYPES_COPY(TChar16, _pMemory, length, buffer, length);
#endif // _MO_WINDOWS
   va_end(params);
   // 设置内容
   _length = length;
   _pMemory[length] = 0;
   return ESuccess;
}

//============================================================
// <T>追加一个格式化字符串到对象尾部。</T>
//
// @param pFormat 格式
// @param params 参数集合
// @return 处理结果
//============================================================
TResult MString16::AssignFormatParameters(TChar16C* pFormat, va_list& params){
   // 检查参数
   MO_CHECK(pFormat, return ENull);
   // 清空内容
   Clear();
   // 追加内容
#ifdef _MO_WINDOWS
   TInt length = MO_LIB_STRING_FORMAT_LENGTH16(pFormat, params);
   InnerResize(length + 1, EFalse, EFalse, EFalse);
   MO_LIB_STRING_NFORMAT16(_pMemory, length + 1, pFormat, params);
#else
   TChar16 buffer[MO_FS_SPRINT_LENGTH];
   TInt length = MO_LIB_STRING_NFORMAT16(buffer, MO_FS_SPRINT_LENGTH - 1, pFormat, params);
   MO_ERROR_CHECK(length >= 0, return EUnsupport,
         "Format string failure. (format=%s)", pFormat);
   InnerResize(length + 1, EFalse, EFalse, EFalse);
   MO_LIB_TYPES_COPY(TChar16, _pMemory, length, buffer, length);
#endif // _MO_WINDOWS
   // 设置内容
   _length = length;
   _pMemory[length] = 0;
   return ESuccess;
}

//============================================================
// <T>追加一个字符到当前字符串对象的末尾。</T>
//
// @param value 字符
// @return 处理结果
//============================================================
TBool MString16::Append(TChar16 value){
   InnerResize(_length + 2, ETrue, ETrue, EFalse);
   _pMemory[_length++] = value;
   _pMemory[_length] = 0;
   return ETrue;
}

//============================================================
// <T>追加一个字符串到当前字符串对象的末尾。</T>
// <P>如果长度小于0，则自动计算指针的数据长度。</P>
//
// @param pValue 字符串
// @param length 长度
// @return 处理结果
//============================================================
TBool MString16::Append(TChar16C* pValue, TInt length){
   // 检查参数
   if(NULL == pValue){
      return EFalse;
   }
   // 检查内存
   if(_pMemory == pValue){
      return EFalse;
   }
   // 计算长度
   if(length <= 0){
      length = MO_LIB_STRING_LENGTH16(pValue);
   }
   // 复制数据
   if(length > 0){
      // 调整内存大小
      InnerResize(_length + length + 1, ETrue, ETrue, EFalse);
      // 复制数据
      RTypes<TChar16>::Copy(_pMemory + _length, pValue, length);
      _length += length;
      _pMemory[_length] = 0;
   }
   return ETrue;
}

//============================================================
// <T>追加一个字符串到当前字符串对象的末尾。</T>
//
// @param ptr 字符串指针
// @return 处理结果
//============================================================
TBool MString16::Append(const TPtrC<TChar16>& ptr){
   // 检查参数
   if(ptr.IsEmpty()){
      return EFalse;
   }
   // 追加内容
   return Append(ptr.MemoryC(), ptr.Length());
}

//============================================================
// <T>追加一个字符串到当前字符串对象的末尾。</T>
//
// @param value 字符串
// @return 处理结果
//============================================================
TBool MString16::Append(const MString16& value){
   // 检查参数
   if(value.IsEmpty()){
      return EFalse;
   }
   // 追加内容
   return Append(value.MemoryC(), value.Length());
}

//============================================================
// <T>追加一个数字到当前字符串对象的末尾。</T>
//
// @param value 数字
// @return 处理结果
//============================================================
TBool MString16::AppendInt(TInt value){
   TChar16 buffer[MO_FS_NUMBER_LENGTH];
   return Append(RNumber<TInt32>::ToSignString<TChar16, TUint32>(buffer, MO_FS_NUMBER_LENGTH, value));
}

//============================================================
// <T>重复追加一个字符串到当前字符串对象的末尾。</T>
//
// @param pValue 字符串
// @param count 总数
// @return 处理结果
//============================================================
TBool MString16::AppendRepeat(TChar16C* pValue, TInt count){
   // 检查参数
   if((NULL == pValue) || (0 == count)){
      return EFalse;
   }
   // 追加内容
   for(TInt n = 0; n < count; n++){
      Append(pValue);
   }
   return ETrue;
}

//============================================================
// <T>追加一个格式化字符串到对象尾部。</T>
//
// @param pFormat 格式
// @param params 参数集合
// @return 处理结果
//============================================================
TResult MString16::AppendFormat(TChar16C* pFormat, ...){
   // 检查参数
   MO_CHECK(pFormat, return ENull);
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pFormat);
   // 输出日志信息
#ifdef _MO_WINDOWS
   TInt length = MO_LIB_STRING_FORMAT_LENGTH16(pFormat, params);
   InnerResize(_length + length + 1, ETrue, ETrue, EFalse);
   MO_LIB_STRING_NFORMAT16(_pMemory + _length, length + 1, pFormat, params);
#else
   TChar16 buffer[MO_FS_SPRINT_LENGTH];
   TInt length = MO_LIB_STRING_NFORMAT16(buffer, MO_FS_SPRINT_LENGTH - 1, pFormat, params);
   MO_ERROR_CHECK(length >= 0, return EUnsupport,
         "Format string failure. (format=%s)", pFormat);
   InnerResize(_length + length + 1, ETrue, ETrue, EFalse);
   MO_LIB_TYPES_COPY(TChar16, _pMemory + _length, length, buffer, length);
#endif // _MO_WINDOWS
   va_end(params);
   // 设置内容
   _length += length;
   _pMemory[_length] = 0;
   return ETrue;
}

//============================================================
// <T>追加一个格式化字符串到对象尾部。</T>
//
// @param pFormat 格式
// @param params 参数集合
// @return 处理结果
//============================================================
TResult MString16::AppendFormatParameters(TChar16C* pFormat, va_list& params){
   // 检查参数
   MO_CHECK(pFormat, return ENull);
   // 格式化可变参数字符串信息
#ifdef _MO_WINDOWS
   TInt length = MO_LIB_STRING_FORMAT_LENGTH16(pFormat, params);
   InnerResize(_length + length + 1, ETrue, ETrue, EFalse);
   length = MO_LIB_STRING_NFORMAT16(_pMemory + _length, length + 1, pFormat, params);
   _length += length;
#else
   TChar16 buffer[MO_FS_SPRINT_LENGTH];
   TInt length = MO_LIB_STRING_NFORMAT16(buffer, MO_FS_SPRINT_LENGTH - 1, pFormat, params);
   MO_ERROR_CHECK(length >= 0, return EUnsupport,
         "Format string failure. (format=%s)", pFormat);
   InnerResize(_length + length + 1, ETrue, ETrue, EFalse);
   MO_LIB_TYPES_COPY(TChar16, _pMemory + _length, length, buffer, length);
#endif // _MO_WINDOWS
   // 设置内容
   _length += length;
   _pMemory[_length] = 0;
   return ETrue;
}

//============================================================
// <T>追加一个空行。</T>
//
// @return 处理结果
//============================================================
void MString16::AppendLine(){
   Append(MO_LINE_CHAR);
}
//============================================================
// <T>追加一个字符作为一行到当前字符串对象的末尾。</T>
//
// @param value 字符
// @return 处理结果
//============================================================
void MString16::AppendLine(TChar16 value){
   Append(value);
   Append(MO_LINE_CHAR);
}

//============================================================
// <T>追加一个字符串作为一行到当前字符串对象的末尾。</T>
// <P>如果长度小于0，则自动计算指针的数据长度。</P>
//
// @param pValue 字符串
// @param length 长度
// @return 处理结果
//============================================================
void MString16::AppendLine(TChar16C* pValue, TInt length){
   Append(pValue, length);
   Append(MO_LINE_CHAR);
}

//============================================================
// <T>追加一个格式化字符串作为一行到对象尾部。</T>
//
// @param pFormat 格式
// @param params 参数集合
// @return 处理结果
//============================================================
TResult MString16::AppendLineFormat(TChar16C* pFormat, ...){
   va_list params;
   va_start(params, pFormat);
   TResult result = AppendFormat(pFormat, params);
   va_end(params);
   Append(MO_LINE_CHAR);
   return result;
}

//============================================================
// <T>追加一个格式化字符串作为一行到对象尾部。</T>
//
// @param pFormat 格式
// @param params 参数集合
// @return 处理结果
//============================================================
TResult MString16::AppendLineFormatParameters(TChar16C* pFormat, va_list& params){
   TResult result = AppendFormat(pFormat, params);
   Append(MO_LINE_CHAR);
   return result;
}

//============================================================
// <T>将指定UNICODE字符串指针对象赋值给当前字符串。 </T>
//
// @param pValue UNICODE 字符串指针对象
//============================================================
void MString16::Assign8(TChar8C* pValue){
   // 计算转换后长度
   TInt length = RString8::ConvertToString16(pValue);
   if(length != -1){
      // 强制容量
      ForceSize(length + 1);
      // 转换内容
      _length = RString8::ConvertToString16(_pMemory, length, pValue);
      _pMemory[_length] = 0;
   }else{
      // 清除数据
      Clear();
   }
}

//============================================================
// <T>将指定UNICODE字符串指针对象赋值给当前字符串。 </T>
//
// @param pValue UNICODE 字符串指针对象
//============================================================
void MString16::Assign16(TChar16C* pValue){
   // 计算转换后长度
   TInt length = RString16::ConvertToString16(pValue);
   if(length != -1){
      // 强制容量
      ForceSize(length + 1);
      // 转换内容
      _length = RString16::ConvertToString16(_pMemory, length, pValue);
      _pMemory[_length] = 0;
   }else{
      // 清除数据
      Clear();
   }
}

//============================================================
// <T>将指定UNICODE字符串指针对象赋值给当前字符串。 </T>
//
// @param pValue UNICODE 字符串指针对象
//============================================================
void MString16::Assign32(TChar32C* pValue){
   // 计算转换后长度
   TInt length = RString32::ConvertToString16(pValue);
   if(length != -1){
      // 强制容量
      ForceSize(length + 1);
      // 转换内容
      _length = RString32::ConvertToString16(_pMemory, length, pValue);
      _pMemory[_length] = 0;
   }else{
      // 清除数据
      Clear();
   }
}

//============================================================
// <T>将内部字符串转换为小写。</T>
//============================================================
void MString16::ToLower(){
   RChar16::ToLower(_pMemory, _length);
}

//============================================================
// <T>将内部字符串转换为大写。</T>
//============================================================
void MString16::ToUpper(){
   RChar16::ToUpper(_pMemory, _length);
}

//============================================================
// TODO: 左侧补齐字符串
//============================================================
void MString16::PadLeft(TChar16 pad, TInt length){
   MO_FATAL_UNSUPPORT();
}

//============================================================
// 右侧补齐字符串
//============================================================
void MString16::PadRight(TChar16 pad, TInt length){
   TInt count = length - _length;
   for(TInt n=0; n<count; n++){
      _pMemory[_length++] = pad;
   }
}

//============================================================
// <T>替换指定字符为另外字符。</T>
//============================================================
TInt MString16::Replace(TChar16 source, TChar16 target){
   TInt result = 0;
   for(TInt n = 0; n < _length; n++){
      if(source == _pMemory[n]){
         _pMemory[n] = target;
         result++;
      }
   }
   return result;
}

//============================================================
// <T>获得整数内容。</T>
//
// @return 整数
//============================================================
TInt MString16::ToInt(){
   return RInt::Parse16(_pMemory);
}

//============================================================
// <T>获得非负整数内容。</T>
//
// @return 非负整数
//============================================================
TUint MString16::ToUint(){
   return RUint::Parse16(_pMemory);
}

//============================================================
// <T>获得64位整数内容。</T>
//
// @return 64位整数
//============================================================
TInt64 MString16::ToInt64(){
   return RInt64::Parse16(_pMemory);
}

//============================================================
// <T>获得64位非负整数内容。</T>
//
// @return 64位非负整数
//============================================================
TUint64 MString16::ToUint64(){
   return RUint64::Parse16(_pMemory);
}

//============================================================
// <T>获得浮点数内容。</T>
//
// @return 浮点数
//============================================================
TFloat MString16::ToFloat(){
   return RFloat::Parse16(_pMemory);
}

//============================================================
// <T>获得双精度浮点数内容。</T>
//
// @return 双精度浮点数
//============================================================
TDouble MString16::ToDouble(){
   return RDouble::Parse16(_pMemory);
}

//============================================================
// <T>设置整数内容。</T>
//
// @param value 整数
//============================================================
void MString16::SetInt(TInt value){
   InnerResize(MO_FS_INTEGER_LENGTH, EFalse, EFalse, EFalse);
   RInt::ToString16(value, _pMemory, _capacity);
   Fix();
}

//============================================================
// <T>设置非负整数内容。</T>
//
// @param value 非负整数
//============================================================
void MString16::SetUint(TUint value){
   InnerResize(MO_FS_INTEGER_LENGTH, EFalse, EFalse, EFalse);
   RUint::ToString16(value, _pMemory, _capacity);
   Fix();
}

//============================================================
// <T>设置64位整数内容。</T>
//
// @param value 64位整数
//============================================================
void MString16::SetInt64(TInt64 value){
   InnerResize(MO_FS_INTEGER64_LENGTH, EFalse, EFalse, EFalse);
   RInt64::ToString16(value, _pMemory, _capacity);
   Fix();
}

//============================================================
// <T>设置64位非负整数内容。</T>
//
// @param value 64位非负整数
//============================================================
void MString16::SetUint64(TUint64 value){
   InnerResize(MO_FS_INTEGER64_LENGTH, EFalse, EFalse, EFalse);
   RUint64::ToString16(value, _pMemory, _capacity);
   Fix();
}

//============================================================
// <T>设置浮点数内容。</T>
//
// @param value 浮点数
//============================================================
void MString16::SetFloat(TFloat value){
   InnerResize(MO_FS_FLOAT_LENGTH, EFalse, EFalse, EFalse);
   RFloat::ToString16(value, _pMemory, _capacity);
   Fix();
}

//============================================================
// <T>设置双精度浮点数内容。</T>
//
// @param value 双精度浮点数
//============================================================
void MString16::SetDouble(TDouble value){
   InnerResize(MO_FS_DOUBLE_LENGTH, EFalse, EFalse, EFalse);
   RDouble::ToString16(value, _pMemory, _capacity);
   Fix();
}

//============================================================
// <T>序列化字符串内容到输出流。</T>
//
// @param pOutput 输出流
//============================================================
void MString16::Serialize(IDataOutput* pOutput){
   MO_ASSERT(_length < 0x10000);
   pOutput->WriteUint16((TUint16)_length);
   if(_length > 0){
      pOutput->Write(_pMemory, sizeof(TChar16) * _length);
   }
}

//============================================================
// <T>从输入流内反序列化字符串内容。</T>
//
// @param pOutput 输出流
//============================================================
void MString16::Unserialize(IDataInput* pInput){
   TInt length = pInput->ReadUint16();
   if(length > 0){
      InnerResize(length + 1, EFalse, EFalse, EFalse);
      pInput->Read(_pMemory, sizeof(TChar16) * length);
   }
   _length = length;
   if(_pMemory != NULL){
      _pMemory[length] = 0;
   }
}

//============================================================
// <T>从输入流内反序列化字符串内容。</T>
//
// @param pOutput 输出流
//============================================================
void MString16::UnserializeAutomatic(IDataInput* pInput){
   EChar charCd = (EChar)pInput->ReadUint8();
   TInt length = pInput->ReadUint16();
   if(length > 0){
      InnerResize(length + 1, EFalse, EFalse, EFalse);
      if(charCd == EChar_Char8){
         for(TInt n = 0; n < length; n++){
            _pMemory[n] = (TChar16)pInput->ReadUint8();
         }
      }else if(charCd == EChar_Char16){
         for(TInt n = 0; n < length; n++){
            _pMemory[n] = (TChar16)pInput->ReadUint16();
         }
      }else if(charCd == EChar_Char32){
         for(TInt n = 0; n < length; n++){
            _pMemory[n] = (TChar16)pInput->ReadUint32();
         }
      }
   }
   _length = length;
   if(_pMemory != NULL){
      _pMemory[length] = 0;
   }
}

//============================================================
// <T>设置字符串的长度。</T>
//
// @param length 长度
//============================================================
void MString16::SetLength(TInt length){
   InnerResize(length + 1, ETrue, EFalse, EFalse);
   _length = length;
   _pMemory[length] = 0;
}

//============================================================
// <T>修正当前字符串内容。</T>
// <P>如果字符串中含有0，则废弃后面的部分，并修正长度。</P>
// <P>如果字符串结尾处没有0，则自动添入0进行结尾。</P>
//============================================================
void MString16::Fix(){
   if(_pMemory != NULL){
      for(TInt n = 0; n < _capacity; n++){
         if(_pMemory[n] == 0){
            _length = n;
            break;
         }
      }
      _pMemory[_length] = 0;
   }
}

//============================================================
// <T>清除所有数据。</T>
//============================================================
void MString16::Clear(){
   _length = 0;
   if(_pMemory != NULL){
      _pMemory[0] = 0;
   }
}

MO_NAMESPACE_END
