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
{char}* M{string}::MemoryZ(){
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
T{string}PtrC M{string}::PtrZ(){
   // 设置结束符
   EnsureSize(_length + 1);
   _pMemory[_length] = 0;
   // 返回内存指针
   return T{string}PtrC(_pMemory, _length);
}

//============================================================
// <T>获得只读字符串指针。</T>
//
// @return 字符串指针
//============================================================
T{string}PtrC M{string}::StrC() const{
   return T{string}PtrC(_pMemory, _length);
}

//============================================================
// <T>获得当前数组中的左边部分数组的引用。</T>
//
// @param length 长度
// @return 字符串指针
//============================================================
T{string}PtrC M{string}::LeftStrC(TInt length) const{
   MO_ASSERT_BETWEEN(length, 0, _length);
   return T{string}PtrC(_pMemory, length);
}

//============================================================
// <T>获得当前数组中的开始到结尾的引用。</T>
//
// @param offset 位置
// @return 字符串指针
//============================================================
T{string}PtrC M{string}::MidStrC(TInt offset) const{
   MO_ASSERT_BETWEEN(offset, 0, _length);
   return T{string}PtrC(_pMemory + offset, _length - offset);
}

//============================================================
// <T>获得当前数组中的部分数组的引用。</T>
//
// @param offset 位置
// @param length 长度
// @return 字符串指针
//============================================================
T{string}PtrC M{string}::MidStrC(TInt offset, TInt length) const{
   MO_ASSERT_BETWEEN(offset, 0, _length);
   MO_ASSERT_BETWEEN(length, 0, _length - offset);
   return T{string}PtrC(_pMemory + offset, length);
}

//============================================================
// <T>获得当前数组中的右边部分数组的引用。</T>
//
// @param length 长度
// @return 字符串指针
//============================================================
T{string}PtrC M{string}::RightStrC(TInt length) const{
   MO_ASSERT_BETWEEN(length, 0, _length);
   return T{string}PtrC(_pMemory + (_length - length), length);
}

//============================================================
// <T>获得当前数组中的部分数组的引用。</T>
//
// @param begin 开始位置
// @param end 结束位置
// @return 字符串指针
//============================================================
T{string}PtrC M{string}::SubStrC(TInt begin, TInt end) const{
   MO_ASSERT_BETWEEN(begin, 0, _length);
   MO_ASSERT_BETWEEN(end, 0, _length);
   MO_ASSERT(begin <= end);
   return T{string}PtrC(_pMemory + begin, end - begin);
}

//============================================================
// <T>去掉左边的空白，返回字符串指针。</T>
//
// @return 字符串指针
//============================================================
T{string}PtrC M{string}::TrimLeftStrC() const{
   TInt position = 0;
   TInt length = Length();
   {char}* pMemory = _pMemory;
   while((position < length) && (pMemory[position] <= ' ')){
      position++;
   }
   return T{string}PtrC(pMemory + position, length - position);
}

//============================================================
// <T>去掉右边的空白，返回字符串指针。</T>
//
// @return 字符串指针
//============================================================
T{string}PtrC M{string}::TrimRightStrC() const{
   TInt position = Length() - 1;
   {char}* pMemory = _pMemory;
   while((position >= 0) && (pMemory[position] <= ' ')){
      position--;
   }
   return T{string}PtrC(pMemory, position);
}

//============================================================
// <T>去掉两边的空白，返回字符串指针。</T>
//
// @return 字符串指针
//============================================================
T{string}PtrC M{string}::TrimStrC() const{
   TInt start = 0;
   TInt length = Length();
   {char}* pMemory = _pMemory;
   // 去掉左边空白字符
   while((start < length) && (pMemory[start] <= ' ')){
      start++;
   }
   // 去掉右边空白字符
   TInt end = length - 1;
   while((end >= 0) && (pMemory[end] <= ' ')){
      end--;
   }
   return T{string}PtrC(pMemory + start, end - start + 1);
}

//============================================================
// <T>判断当前字符串和指定字符串是否相等。</T>
//
// @param pValue 指定字符串
// @return <L value='ETrue'>相等</L>
//         <L value='EFalse'>不相等</L>
//============================================================
TBool M{string}::Equals({char}C* pValue) const{
   return R{string}::Equals(MemoryC(), pValue);
}

//============================================================
// <T>不关心字符大小写，判断当前字符串和指定字符串是否相等。</T>
//
// @param pValue 指定字符串
// @return <L value='ETrue'>相等</L>
//         <L value='EFalse'>不相等</L>
//============================================================
TBool M{string}::EqualsIgnoreCase({char}C* pValue) const{
   return (0 == MO_LIB_STRING_COMPARE_IGNORECASE{type}(MemoryC(), pValue));
}

//============================================================
// <T>不关心字符大小写，判断当前字符串和指定字符串是否相等。</T>
//
// @param value 指定字符串
// @return <L value='ETrue'>相等</L>
//         <L value='EFalse'>不相等</L>
//============================================================
TBool M{string}::EqualsIgnoreCase(const T{string}PtrC& value) const{
   return (0 == MO_LIB_STRING_COMPARE_IGNORECASE{type}(MemoryC(), value.MemoryC()));
}

//============================================================
// <T>查找指定数组是否出现在当前数组的开始位置。</T>
//
// @param pValue 指定字符串
// @return <L value='ETrue'>相等</L>
//         <L value='EFalse'>不相等</L>
//============================================================
TBool M{string}::StartsWith({char}C* pValue) const{
   MO_ASSERT(pValue);
   TInt length = MO_LIB_STRING_LENGTH{type}(pValue);
   return RTypes<{char}C>::StartsWith(_pMemory, _length, pValue, length);
}

//============================================================
// <T>查找指定数组是否出现在当前数组的结束位置。</T>
//
// @param pValue 指定字符串
// @return <L value='ETrue'>相等</L>
//         <L value='EFalse'>不相等</L>
//============================================================
TBool M{string}::EndsWith({char}C* pValue) const{
   MO_ASSERT(pValue);
   TInt length = MO_LIB_STRING_LENGTH{type}(pValue);
   return RTypes<{char}C>::EndsWith(_pMemory, _length, pValue, length);
}

//============================================================
// <T>不关心字符大小写，判断当前字符串和指定字符串的大小。</T>
//
// @param pValue 指定字符串
// @return <L value='小于0'>当前字符串小于指定字符串</L>
//         <L value='等于0'>当前字符串相等指定字符串</L>
//         <L value='大于0'>当前字符串大于指定字符串</L>
//============================================================
TInt M{string}::CompareIgnoreCase({char}C* pValue) const{
   return MO_LIB_STRING_COMPARE_IGNORECASE{type}(MemoryC(), pValue);
}

//============================================================
// <T>不关心字符大小写，判断当前字符串和指定字符串的大小。</T>
//
// @param value 指定字符串
// @return <L value='小于0'>当前字符串小于指定字符串</L>
//         <L value='等于0'>当前字符串相等指定字符串</L>
//         <L value='大于0'>当前字符串大于指定字符串</L>
//============================================================
TInt M{string}::CompareIgnoreCase(const T{string}PtrC& value) const{
   return MO_LIB_STRING_COMPARE_IGNORECASE{type}(MemoryC(), value.MemoryC());
}

//============================================================
// <T>计算当前字符串的哈希值。</T>
//
// @return 哈希值
//============================================================
THashCode M{string}::HashCode() const{
   return RTypes<{char}>::MakeHashCode(_pMemory, _length);
}

//============================================================
// <T>从当前字符串中查找指定字符串的索引位置。</T>
//
// @param pValue 字符串
// @return 索引位置
//============================================================
TInt M{string}::Find({char}C* pValue){
   TInt length = MO_LIB_STRING_LENGTH{type}(pValue);
   return RTypes<{char}>::Find(_pMemory, _length, pValue, length);
}

//============================================================
// <T>从当前字符串中查找指定字符串的索引位置。</T>
//
// @param pValue 字符串
// @param offset 位置
// @return 索引位置
//============================================================
TInt M{string}::Find({char}C* pValue, TInt offset){
   MO_ASSERT_BETWEEN(offset, 0, _length);
   TInt length = MO_LIB_STRING_LENGTH{type}(pValue);
   TInt result = RTypes<{char}>::Find(_pMemory + offset, _length - offset, pValue, length);
   return (ENotFound != result) ? offset + result : ENotFound;
}

//============================================================
// <T>从当前字符串中倒序查找指定字符串的索引位置。</T>
//
// @param pValue 字符串
// @return 索引位置
//============================================================
TInt M{string}::LastFind({char}C* pValue){
   TInt length = MO_LIB_STRING_LENGTH{type}(pValue);
   return RTypes<{char}>::LastFind(_pMemory, _length, pValue, length);
}

//============================================================
// <T>接受一个字符串到当前字符串。</T>
// <P>如果长度小于0，则自动计算指针的数据长度。</P>
//
// @param pValue 字符串
// @param length 长度
// @return 处理结果
//============================================================
TBool M{string}::Assign({char}C* pValue, TInt length){
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
      length = MO_LIB_STRING_LENGTH{type}(pValue);
   }
   // 复制数据
   if(length > 0){
      // 调整内存大小
      InnerResize(length + 1, EFalse, EFalse, EFalse);
      // 复制数据
      RChar{type}s::Copy(_pMemory, pValue, length);
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
TBool M{string}::Assign(const TPtrC<{char}>& ptr){
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
TBool M{string}::Assign(const M{string}& value){
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
TBool M{string}::AssignPointer(const M{string}* pValue){
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
TResult M{string}::AssignFormat({char}C* pFormat, ...){
   // 检查参数
   MO_CHECK(pFormat, return ENull);
   // 清空内容
   Clear();
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pFormat);
   // 追加内容
#ifdef _MO_WINDOWS
   TInt length = MO_LIB_STRING_FORMAT_LENGTH{type}(pFormat, params);
   InnerResize(length + 1, EFalse, EFalse, EFalse);
   MO_LIB_STRING_NFORMAT{type}(_pMemory, length + 1, pFormat, params);
#else
   {char} buffer[MO_FS_SPRINT_LENGTH];
   TInt length = MO_LIB_STRING_NFORMAT{type}(buffer, MO_FS_SPRINT_LENGTH - 1, pFormat, params);
   MO_ERROR_CHECK(length >= 0, return EUnsupport,
         "Format string failure. (format=%s)", pFormat);
   InnerResize(length + 1, EFalse, EFalse, EFalse);
   MO_LIB_TYPES_COPY({char}, _pMemory, length, buffer, length);
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
TResult M{string}::AssignFormatParameters({char}C* pFormat, va_list& params){
   // 检查参数
   MO_CHECK(pFormat, return ENull);
   // 清空内容
   Clear();
   // 追加内容
#ifdef _MO_WINDOWS
   TInt length = MO_LIB_STRING_FORMAT_LENGTH{type}(pFormat, params);
   InnerResize(length + 1, EFalse, EFalse, EFalse);
   MO_LIB_STRING_NFORMAT{type}(_pMemory, length + 1, pFormat, params);
#else
   {char} buffer[MO_FS_SPRINT_LENGTH];
   TInt length = MO_LIB_STRING_NFORMAT{type}(buffer, MO_FS_SPRINT_LENGTH - 1, pFormat, params);
   MO_ERROR_CHECK(length >= 0, return EUnsupport,
         "Format string failure. (format=%s)", pFormat);
   InnerResize(length + 1, EFalse, EFalse, EFalse);
   MO_LIB_TYPES_COPY({char}, _pMemory, length, buffer, length);
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
TBool M{string}::Append({char} value){
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
TBool M{string}::Append({char}C* pValue, TInt length){
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
      length = MO_LIB_STRING_LENGTH{type}(pValue);
   }
   // 复制数据
   if(length > 0){
      // 调整内存大小
      InnerResize(_length + length + 1, ETrue, ETrue, EFalse);
      // 复制数据
      RTypes<{char}>::Copy(_pMemory + _length, pValue, length);
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
TBool M{string}::Append(const TPtrC<{char}>& ptr){
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
TBool M{string}::Append(const M{string}& value){
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
TBool M{string}::AppendInt(TInt value){
   {char} buffer[MO_FS_NUMBER_LENGTH];
   return Append(RNumber<TInt32>::ToSignString<{char}, TUint32>(buffer, MO_FS_NUMBER_LENGTH, value));
}

//============================================================
// <T>重复追加一个字符串到当前字符串对象的末尾。</T>
//
// @param pValue 字符串
// @param count 总数
// @return 处理结果
//============================================================
TBool M{string}::AppendRepeat({char}C* pValue, TInt count){
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
TResult M{string}::AppendFormat({char}C* pFormat, ...){
   // 检查参数
   MO_CHECK(pFormat, return ENull);
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pFormat);
   // 输出日志信息
#ifdef _MO_WINDOWS
   TInt length = MO_LIB_STRING_FORMAT_LENGTH{type}(pFormat, params);
   InnerResize(_length + length + 1, ETrue, ETrue, EFalse);
   MO_LIB_STRING_NFORMAT{type}(_pMemory + _length, length + 1, pFormat, params);
#else
   {char} buffer[MO_FS_SPRINT_LENGTH];
   TInt length = MO_LIB_STRING_NFORMAT{type}(buffer, MO_FS_SPRINT_LENGTH - 1, pFormat, params);
   MO_ERROR_CHECK(length >= 0, return EUnsupport,
         "Format string failure. (format=%s)", pFormat);
   InnerResize(_length + length + 1, ETrue, ETrue, EFalse);
   MO_LIB_TYPES_COPY({char}, _pMemory + _length, length, buffer, length);
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
TResult M{string}::AppendFormatParameters({char}C* pFormat, va_list& params){
   // 检查参数
   MO_CHECK(pFormat, return ENull);
   // 格式化可变参数字符串信息
#ifdef _MO_WINDOWS
   TInt length = MO_LIB_STRING_FORMAT_LENGTH{type}(pFormat, params);
   InnerResize(_length + length + 1, ETrue, ETrue, EFalse);
   length = MO_LIB_STRING_NFORMAT{type}(_pMemory + _length, length + 1, pFormat, params);
   _length += length;
#else
   {char} buffer[MO_FS_SPRINT_LENGTH];
   TInt length = MO_LIB_STRING_NFORMAT{type}(buffer, MO_FS_SPRINT_LENGTH - 1, pFormat, params);
   MO_ERROR_CHECK(length >= 0, return EUnsupport,
         "Format string failure. (format=%s)", pFormat);
   InnerResize(_length + length + 1, ETrue, ETrue, EFalse);
   MO_LIB_TYPES_COPY({char}, _pMemory + _length, length, buffer, length);
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
void M{string}::AppendLine(){
   Append(MO_LINE_CHAR);
}
//============================================================
// <T>追加一个字符作为一行到当前字符串对象的末尾。</T>
//
// @param value 字符
// @return 处理结果
//============================================================
void M{string}::AppendLine({char} value){
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
void M{string}::AppendLine({char}C* pValue, TInt length){
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
TResult M{string}::AppendLineFormat({char}C* pFormat, ...){
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
TResult M{string}::AppendLineFormatParameters({char}C* pFormat, va_list& params){
   TResult result = AppendFormat(pFormat, params);
   Append(MO_LINE_CHAR);
   return result;
}

//============================================================
// <T>将指定UNICODE字符串指针对象赋值给当前字符串。 </T>
//
// @param pValue UNICODE 字符串指针对象
//============================================================
void M{string}::Assign8(TChar8C* pValue){
   // 计算转换后长度
   TInt length = RString8::ConvertToString{type}(pValue);
   if(length != -1){
      // 强制容量
      ForceSize(length + 1);
      // 转换内容
      _length = RString8::ConvertToString{type}(_pMemory, length, pValue);
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
void M{string}::Assign16(TChar16C* pValue){
   // 计算转换后长度
   TInt length = RString16::ConvertToString{type}(pValue);
   if(length != -1){
      // 强制容量
      ForceSize(length + 1);
      // 转换内容
      _length = RString16::ConvertToString{type}(_pMemory, length, pValue);
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
void M{string}::Assign32(TChar32C* pValue){
   // 计算转换后长度
   TInt length = RString32::ConvertToString{type}(pValue);
   if(length != -1){
      // 强制容量
      ForceSize(length + 1);
      // 转换内容
      _length = RString32::ConvertToString{type}(_pMemory, length, pValue);
      _pMemory[_length] = 0;
   }else{
      // 清除数据
      Clear();
   }
}

//============================================================
// <T>将内部字符串转换为小写。</T>
//============================================================
void M{string}::ToLower(){
   RChar{type}::ToLower(_pMemory, _length);
}

//============================================================
// <T>将内部字符串转换为大写。</T>
//============================================================
void M{string}::ToUpper(){
   RChar{type}::ToUpper(_pMemory, _length);
}

//============================================================
// TODO: 左侧补齐字符串
//============================================================
void M{string}::PadLeft({char} pad, TInt length){
   MO_FATAL_UNSUPPORT();
}

//============================================================
// 右侧补齐字符串
//============================================================
void M{string}::PadRight({char} pad, TInt length){
   TInt count = length - _length;
   for(TInt n=0; n<count; n++){
      _pMemory[_length++] = pad;
   }
}

//============================================================
// <T>替换指定字符为另外字符。</T>
//============================================================
TInt M{string}::Replace({char} source, {char} target){
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
TInt M{string}::ToInt(){
   return RInt::Parse{type}(_pMemory);
}

//============================================================
// <T>获得非负整数内容。</T>
//
// @return 非负整数
//============================================================
TUint M{string}::ToUint(){
   return RUint::Parse{type}(_pMemory);
}

//============================================================
// <T>获得64位整数内容。</T>
//
// @return 64位整数
//============================================================
TInt64 M{string}::ToInt64(){
   return RInt64::Parse{type}(_pMemory);
}

//============================================================
// <T>获得64位非负整数内容。</T>
//
// @return 64位非负整数
//============================================================
TUint64 M{string}::ToUint64(){
   return RUint64::Parse{type}(_pMemory);
}

//============================================================
// <T>获得浮点数内容。</T>
//
// @return 浮点数
//============================================================
TFloat M{string}::ToFloat(){
   return RFloat::Parse{type}(_pMemory);
}

//============================================================
// <T>获得双精度浮点数内容。</T>
//
// @return 双精度浮点数
//============================================================
TDouble M{string}::ToDouble(){
   return RDouble::Parse{type}(_pMemory);
}

//============================================================
// <T>设置整数内容。</T>
//
// @param value 整数
//============================================================
void M{string}::SetInt(TInt value){
   InnerResize(MO_FS_INTEGER_LENGTH, EFalse, EFalse, EFalse);
   RInt::ToString{type}(value, _pMemory, _capacity);
   Fix();
}

//============================================================
// <T>设置非负整数内容。</T>
//
// @param value 非负整数
//============================================================
void M{string}::SetUint(TUint value){
   InnerResize(MO_FS_INTEGER_LENGTH, EFalse, EFalse, EFalse);
   RUint::ToString{type}(value, _pMemory, _capacity);
   Fix();
}

//============================================================
// <T>设置64位整数内容。</T>
//
// @param value 64位整数
//============================================================
void M{string}::SetInt64(TInt64 value){
   InnerResize(MO_FS_INTEGER64_LENGTH, EFalse, EFalse, EFalse);
   RInt64::ToString{type}(value, _pMemory, _capacity);
   Fix();
}

//============================================================
// <T>设置64位非负整数内容。</T>
//
// @param value 64位非负整数
//============================================================
void M{string}::SetUint64(TUint64 value){
   InnerResize(MO_FS_INTEGER64_LENGTH, EFalse, EFalse, EFalse);
   RUint64::ToString{type}(value, _pMemory, _capacity);
   Fix();
}

//============================================================
// <T>设置浮点数内容。</T>
//
// @param value 浮点数
//============================================================
void M{string}::SetFloat(TFloat value){
   InnerResize(MO_FS_FLOAT_LENGTH, EFalse, EFalse, EFalse);
   RFloat::ToString{type}(value, _pMemory, _capacity);
   Fix();
}

//============================================================
// <T>设置双精度浮点数内容。</T>
//
// @param value 双精度浮点数
//============================================================
void M{string}::SetDouble(TDouble value){
   InnerResize(MO_FS_DOUBLE_LENGTH, EFalse, EFalse, EFalse);
   RDouble::ToString{type}(value, _pMemory, _capacity);
   Fix();
}

//============================================================
// <T>序列化字符串内容到输出流。</T>
//
// @param pOutput 输出流
//============================================================
void M{string}::Serialize(IDataOutput* pOutput){
   MO_ASSERT(_length < 0x10000);
   pOutput->WriteUint16((TUint16)_length);
   if(_length > 0){
      pOutput->Write(_pMemory, sizeof({char}) * _length);
   }
}

//============================================================
// <T>从输入流内反序列化字符串内容。</T>
//
// @param pOutput 输出流
//============================================================
void M{string}::Unserialize(IDataInput* pInput){
   TInt length = pInput->ReadUint16();
   if(length > 0){
      InnerResize(length + 1, EFalse, EFalse, EFalse);
      pInput->Read(_pMemory, sizeof({char}) * length);
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
void M{string}::UnserializeAutomatic(IDataInput* pInput){
   EChar charCd = (EChar)pInput->ReadUint8();
   TInt length = pInput->ReadUint16();
   if(length > 0){
      InnerResize(length + 1, EFalse, EFalse, EFalse);
      if(charCd == EChar_Char8){
         for(TInt n = 0; n < length; n++){
            _pMemory[n] = ({char})pInput->ReadUint8();
         }
      }else if(charCd == EChar_Char16){
         for(TInt n = 0; n < length; n++){
            _pMemory[n] = ({char})pInput->ReadUint16();
         }
      }else if(charCd == EChar_Char32){
         for(TInt n = 0; n < length; n++){
            _pMemory[n] = ({char})pInput->ReadUint32();
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
void M{string}::SetLength(TInt length){
   InnerResize(length + 1, ETrue, EFalse, EFalse);
   _length = length;
   _pMemory[length] = 0;
}

//============================================================
// <T>修正当前字符串内容。</T>
// <P>如果字符串中含有0，则废弃后面的部分，并修正长度。</P>
// <P>如果字符串结尾处没有0，则自动添入0进行结尾。</P>
//============================================================
void M{string}::Fix(){
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
void M{string}::Clear(){
   _length = 0;
   if(_pMemory != NULL){
      _pMemory[0] = 0;
   }
}

MO_NAMESPACE_END
