#include "MoCmString{type}.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>当前字符串和指定字符串是否相等。</T>
//
// @param pValue 字符串指针
// @param length 长度
// @return <L value='ETrue'>相等</L>
//         <L value='EFalse'>不相等</L>
//============================================================
TBool MString{type}::Equals(const {char}* pValue, TInt length) const{
   MO_ASSERT(pValue);
   if(length < 0){
      length = R{char}::Length(pValue);
   }
   return RTypes<{char}>::Equals({memory}, _length, pValue, length);
}

//============================================================
// <T>当前字符串和指定字符串是否相等。</T>
//
// @param value 字符串
// @return <L value='ETrue'>相等</L>
//         <L value='EFalse'>不相等</L>
//============================================================
TBool MString{type}::Equals(const MString{type}& value) const{
   return RTypes<{char}>::Equals({memory}, _length, value.MemoryC(), value.Length());
}

//============================================================
// <T>不关心字符大小写，判断当前字符串和指定字符串是否相等。</T>
//
// @param pValue 字符串指针
// @return <L value='ETrue'>相等</L>
//         <L value='EFalse'>不相等</L>
//============================================================
TBool MString{type}::EqualsIgnoreCase(const {char}* pValue, TInt length) const{
   if(NULL != pValue){
      return EFalse;
   }
#ifdef _WINDOWS
   return (0 == _stricmp({memory}, pValue));
#endif
#ifdef _LINUX
   return (0 == strcasecmp({memory}, pValue));
#endif
}

//============================================================
// <T>判断从当前字符串中是否含有指定字符。</T>
//
// @param value 字符串
// @return <L value='ETrue'>含有</L>
//         <L value='EFalse'>不含有</L>
//============================================================
TBool MString{type}::Contains({char} value) const{
   TInt result = RTypes<{char}>::IndexOf({memory}, _length, value);
   return (ENotFound != result);
}

//============================================================
// <T>判断从当前字符串中是否含有指定字符串。</T>
//
// @param pValue 字符串指针
// @param length 字符串长度
// @return <L value='ETrue'>含有</L>
//         <L value='EFalse'>不含有</L>
//============================================================
TBool MString{type}::Contains(const {char}* pValue, TInt length) const{
   MO_ASSERT(pValue);
   if(length < 0){
      length = R{char}::Length(pValue);
   }
   TInt result = RTypes<{char}>::Find({memory}, _length, pValue, length);
   return (ENotFound != result);
}

//============================================================
// <T>判断从当前字符串中是否含有指定字符串。</T>
//
// @param value 字符串
// @return <L value='ETrue'>含有</L>
//         <L value='EFalse'>不含有</L>
//============================================================
TBool MString{type}::Contains(const MString{type}& value) const{
   TInt result = RTypes<{char}>::Find({memory}, _length, value.MemoryC(), value.Length());
   return (ENotFound != result);
}

//============================================================
// <T>判断指定字符串是否出现在当前字符串的开始位置。</T>
//
// @param pValue 字符串指针
// @param length 字符串长度
// @return <L value='ETrue'>是</L>
//         <L value='EFalse'>否</L>
//============================================================
TBool MString{type}::StartsWith(const {char}* pValue, TInt length) const{
   MO_ASSERT(pValue);
   if(length < 0){
      length = R{char}::Length(pValue);
   }
   return RTypes<{char}>::StartsWith({memory}, _length, pValue, length);
}

//============================================================
// <T>判断指定字符串是否出现在当前字符串的开始位置。</T>
//
// @param value 字符串
// @return <L value='ETrue'>是</L>
//         <L value='EFalse'>否</L>
//============================================================
TBool MString{type}::StartsWith(const MString{type}& value) const{
   return RTypes<{char}>::StartsWith({memory}, _length, value.MemoryC(), value.Length());
}

//============================================================
// <T>判断指定字符串是否出现在当前字符串的结束位置。</T>
//
// @param pValue 字符串指针
// @param length 字符串长度
// @return <L value='ETrue'>是</L>
//         <L value='EFalse'>否</L>
//============================================================
TBool MString{type}::EndsWith(const {char}* pValue, TInt length) const{
   MO_ASSERT(pValue);
   if(length < 0){
      length = R{char}::Length(pValue);
   }
   return RTypes<{char}>::EndsWith({memory}, _length, pValue, length);
}

//============================================================
// <T>判断指定字符串是否出现在当前字符串的结束位置。</T>
//
// @param value 字符串指针
// @return <L value='ETrue'>是</L>
//         <L value='EFalse'>否</L>
//============================================================
TBool MString{type}::EndsWith(const MString{type}& value) const{
   return RTypes<{char}>::EndsWith({memory}, _length, value.MemoryC(), value.Length());
}

//============================================================
// <T>比较当前数组中和指定数组的大小。</T>
//
// @param pValue 字符串指针
// @param length 字符串长度
// @return 比较结果
//============================================================
TInt MString{type}::Compare(const {char}* pValue, TInt length) const{
   MO_ASSERT(pValue);
   if(length < 0){
      length = R{char}::Length(pValue);
   }
   return RTypes<{char}>::Compare({memory}, _length, pValue, length);
}

//============================================================
// <T>比较当前数组中和指定数组的大小。</T>
//
// @param value 字符串
// @return 比较结果
//============================================================
TInt MString{type}::Compare(const MString{type}& value) const{
   return RTypes<{char}>::Compare({memory}, _length, value.MemoryC(), value.Length());
}

//============================================================
// <T>不关心字符大小写，判断当前字符串和指定字符串的大小。</T>
//
// @param pValue 指定字符串
// @return <L value='小于0'>当前字符串小于指定字符串</L>
//         <L value='等于0'>当前字符串相等指定字符串</L>
//         <L value='大于0'>当前字符串大于指定字符串</L>
//============================================================
TInt MString{type}::CompareIgnoreCase(const {char}* pValue, TInt length) const{
   if(NULL == pValue){
      return 0;
   }
#ifdef _WINDOWS
   return _stricmp({memory}, pValue);
#else
   return strcasecmp({memory}, pValue);
#endif
}

//============================================================
// <T>查找从当前字符串中指定字符出现的索引位置。</T>
//
// @param value 字符
// @return 索引位置
//============================================================
TInt MString{type}::IndexOf({char} value) const{
   return RTypes<{char}>::IndexOf({memory}, _length, value);
}

//============================================================
// <T>查找从当前字符串中指定字符出现的索引位置。</T>
//
// @param value 字符
// @param offset 索引位置
// @return 索引位置
//============================================================
TInt MString{type}::IndexOf({char} value, TInt offset) const{
   MO_ASSERT_RANGE(offset, 0, _length);
   TInt find = RTypes<{char}>::IndexOf({memory} + offset, _length - offset, value);
   if(ENotFound == find){
      return find;
   }
   return offset + find;
}

//============================================================
// <T>查找从当前字符串中指定字符出现的最后索引位置。</T>
//
// @param value 字符
// @return 索引位置
//============================================================
TInt MString{type}::LastIndexOf({char} value) const{
   return RTypes<{char}>::LastIndexOf({memory}, _length, value);
}

//============================================================
// <T>查找从当前字符串中指定字符出现的最后索引位置。</T>
//
// @param value 字符
// @param offset 索引位置
// @return 索引位置
//============================================================
TInt MString{type}::LastIndexOf({char} value, TInt offset) const{
   MO_ASSERT_RANGE(offset, 0, _length);
   return RTypes<{char}>::LastIndexOf({memory}, offset, value);
}

//============================================================
// <T>查找从当前字符串中指定字符串出现的索引位置。</T>
//
// @param pValue 字符串指针
// @param length 字符串长度
// @return 索引位置
//============================================================
// <T>查找指定数组对象在当前数组对象出现的索引位置。</T>
TInt MString{type}::Find(const {char}* pValue, TInt length) const{
   MO_ASSERT(pValue);
   if(length < 0){
      length = R{char}::Length(pValue);
   }
   return RTypes<{char}>::Find({memory}, _length, pValue, length);
}

//============================================================
// <T>查找从当前字符串中指定字符串出现的索引位置。</T>
//
// @param value 字符串
// @return 索引位置
//============================================================
TInt MString{type}::Find(const MString{type}& value) const{
   return RTypes<{char}>::Find({memory}, _length, value.MemoryC(), value.Length());
}

//============================================================
// <T>查找从当前字符串中指定字符串出现的最后索引位置。</T>
//
// @param pValue 字符串指针
// @param length 字符串长度
// @return 索引位置
//============================================================
TInt MString{type}::LastFind(const {char}* pValue, TInt length) const{
   MO_ASSERT(pValue);
   if(length < 0){
      length = R{char}::Length(pValue);
   }
   return RTypes<{char}>::LastFind({memory}, _length, pValue, length);
}

//============================================================
// <T>查找从当前字符串中指定字符串出现的最后索引位置。</T>
//
// @param value 字符串
// @return 索引位置
//============================================================
TInt MString{type}::LastFind(const MString{type}& values) const{
   return RTypes<{char}>::LastFind({memory}, _length, values.MemoryC(), values.Length());
}

//============================================================
// <T>保证内存大小，不保留以前内容。</T>
//
// @param size 大小
//============================================================
void MString{type}::EnsureSize(TInt size){
   if(size > _capacity){
      // 当内存不足时，重新计算内存容量
      TInt capacity = CalculateCapacity(size);
      MO_ASSERT(capacity >= size);
      // 如果收集不成功，则不进行复制数据处理
      {char}* pAlloc = MO_MEM_ALLOC(capacity);
      MO_ASSERT(pAlloc);
      // 如果以前内存有数据，则复制以前内存内容
      if(NULL != {memory}){
         _pAllocator->Free({memory});
      }
      // 设置新的内存
      {memory} = pAlloc;
      _atom.capacity = capacity;
   }
}

//============================================================
// <T>保证内存大小，保留以前内容。</T>
//
// @param size 大小
//============================================================
void MString{type}::EnsureResize(TInt size){
   if(size > _atom.capacity){
      // 当内存不足时，重新计算内存容量
      TInt capacity = CalculateCapacity(size);
      MO_ASSERT(size >= capacity);
      // 如果收集不成功，则不进行复制数据处理
      {char}* pAlloc = ->Alloc(capacity);
      MO_ASSERT(pAlloc);
      // 如果以前内存有数据，则复制以前内存内容
      if(NULL != {memory}){
         if(_length > 0){
            RTypes<{char}>::CopyFast(pAlloc, {memory}, _length);
         }
         MO_MEM_FREE({memory});
      }
      // 设置新的内存
      {memory} = pAlloc;
      _capacity = capacity;
   }
}

//============================================================
// <T>保证内存扩张，保留以前内容。</T>
//
// @param size 大小
//============================================================
void MString{type}::EnsureExtend(TInt size){
   TInt capacity = _length + size;
   if(capacity > _atom.capacity){
      EnsureResize(capacity);
   }
}

//============================================================
// <T>接收一个字符串内容。</T>
//
// @param pValue 字符串指针
// @param length 字符串长度
//============================================================
void MString{type}::Assign(const {char}* pValue, TInt length){
   MO_ASSERT(pValue);
   if(length < 0){
      length = R{char}::Length(pValue);
   }
   EnsureSize(length + 1);
   RTypes<{char}>::CopyFast({memory}, pValue, length);
   _length = length;
   {memory}[_length] = 0;
}

//============================================================
// <T>接收一个字符指针对象。</T>
//
// @param ptr 字符指针对象
//============================================================
void MString{type}::Assign(const TPtrC<{char}>& ptr){
   Assign(ptr.MemoryC(), ptr.Length());
}

//============================================================
// <T>接收一个字符串。</T>
//
// @param value 字符串
//============================================================
void MString{type}::Assign(const MString{type}& value){
   Assign(value.MemoryC(), value.Length());
}

//============================================================
// <T>追加一个字符内容到尾部。</T>
//
// @param value 字符
//============================================================
void MString{type}::Append({char} value){
   Push(value);
}

//============================================================
// <T>追加一个字符串内容到尾部。</T>
//
// @param pValue 字符串指针
// @param length 字符串长度
//============================================================
void MString{type}::Append(const {char}* pValue, TInt length){
   MO_ASSERT(pValue);
   if(length < 0){
      length = R{char}::Length(pValue);
   }
   EnsureResize(_length + length + 1);
   RTypes<{char}>::CopyFast({memory} + _length, pValue, length);
   _length += length;
   {memory}[_length] = 0;
}

//============================================================
// <T>追加一个字符指针对象到尾部。</T>
//
// @param ptr 字符指针对象
//============================================================
void MString{type}::Append(const TPtrC<{char}>& ptr){
   Append(ptr.MemoryC(), ptr.Length());
}

//============================================================
// <T>追加一个字符串内容到尾部。</T>
//
// @param value 字符串
//============================================================
void MString{type}::Append(const MString{type}& value){
   Append(value.MemoryC(), value.Length());
}

//============================================================
// <T>在指定索引位置插入一个字符内容。</T>
//
// @param index 索引位置
// @param value 字符
//============================================================
void MString{type}::Insert(TInt index, {char} value){
   MO_ASSERT_BETWEEN(index, 0, _length);
   EnsureResize(_length + 1);
   RTypes<{char}>::CopySafe({memory} + index + 1, {memory} + index, _length - index);
   {memory}[index] = value;
   _length++;
}

//============================================================
// <T>在指定索引位置插入一个字符串内容。</T>
//
// @param index 索引位置
// @param pValue 字符串指针
// @param length 字符串长度
//============================================================
void MString{type}::Insert(TInt index, const {char}* pValue, TInt length){
   MO_ASSERT_BETWEEN(index, 0, _length);
   if(length < 0){
      length = R{char}::Length(pValue);
   }
   EnsureResize(_length + length);
   RTypes<{char}>::CopySafe({memory} + index + length, {memory} + index, _length - index);
   RTypes<{char}>::CopyFast({memory} + index, pValue, length);
   _length += length;
}

//============================================================
// <T>在指定索引位置插入一个字符串内容。</T>
//
// @param index 索引位置
// @param value 字符串
//============================================================
void MString{type}::Insert(TInt index, const MString{type}& value){
   Insert(index, value.MemoryC(), value.Length());
}

//============================================================
// <T>从首部弹出一个字符内容。</T>
//
// @return 字符
//============================================================
{char} MString{type}::Shift(){
   MO_ASSERT(_length > 0);
   T& value = {memory}[0];
   _length -= 1;
   if(_length > 0){
      RTypes<{char}>::CopySafe({memory}, {memory} + 1, _length);
   }
   return value;
}

//============================================================
// <T>从首部压入一个字符内容。</T>
//
// @param value 字符
//============================================================
void MString{type}::Unshift({char} value){
   EnsureResize(_length + 1);
   if(_length > 0){
      RTypes<{char}>::CopySafe({memory} + 1, {memory}, _length);
   }
   {memory}[0] = value;
   _length++;
}

//============================================================
// <T>从尾部弹出一个字符内容。</T>
//
// @return 字符
//============================================================
{char} MString{type}::Pop(){
   MO_ASSERT(_length > 0);
   _length--;
   return {memory}[_length];
}

//============================================================
// <T>从尾部推入一个字符内容。</T>
//
// @param value 字符
//============================================================
void MString{type}::Push({char} value){
   EnsureResize(_length + 1);
   {memory}[_length++] = value;
}

//============================================================
// <T>使用指定字符内容填充当前字符串。</T>
//
// @param value 字符
//============================================================
void MString{type}::Fill({char} value){
   RTypes<{char}>::Fill({memory}, _length, value);
}

//============================================================
// <T>使用指定字符内容填充当前字符串。</T>
//
// @param value 字符
// @param length 长度
//============================================================
void MString{type}::Fill({char} value, TInt length){
   MO_ASSERT_RANGE(length, 0, _length)
   RTypes<{char}>::Fill({memory}, length, value);
}

//============================================================
// <T>使用指定字符内容填充当前字符串。</T>
//
// @param value 字符
// @param offset 位置
// @param length 长度
//============================================================
void MString{type}::Fill({char} value, TInt offset, TInt length){
   MO_ASSERT_RANGE(offset, 0, _length)
   MO_ASSERT_RANGE(length, 0, _length - offset)
   RTypes<{char}>::Fill({memory} + offset, length, value);
}

//============================================================
// <T>使用字符在左侧补齐字符串到指定长度。</T>
//
// @param value 字符
// @param length 长度
//============================================================
void MString{type}::PadLeft({char} pad, TInt length){
   TInt count = length - _length;
   if(count > 0){
      EnsureResize(length + 1);
      RTypes<{char}>::CopySafe({memory} + count, {memory}, _length);
      RTypes<{char}>::Fill({memory}, count, pad);
      _length += count;
      {memory}[_length] = 0;
   }
}

//============================================================
// <T>使用字符在右侧补齐字符串到指定长度。</T>
//
// @param value 字符
// @param length 长度
//============================================================
void MString{type}::PadRight({char} pad, TInt length){
   TInt count = length - _length;
   if(count > 0){
      EnsureResize(length + 1);
      RTypes<{char}>::Fill({memory} + _length, count, pad);
      _length += count;
      {memory}[_length] = 0;
   }
}

//============================================================
// <T>从字符串中替换来源字符为目标字符。</T>
//
// @param source 来源字符
// @param target 目标字符
//============================================================
void MString{type}::Replace({char} source, {char} target){
   RTypes<{char}>::Replace({memory}, _length, source, target);
}

//============================================================
// <T>从字符串中替换来源字符串为目标字符串。</T>
//
// @param pSource 来源字符串指针
// @param pTarget 目标字符串指针
//============================================================
void MString{type}::Replace(const {char}* pSource, const {char}* pTarget){
   TInt sourceLength = R{char}::Length(pSource);
   TInt targetLength = R{char}::Length(pTarget);
   Replace(pSource, sourceLength, pTarget, targetLength);
}

//============================================================
// <T>从字符串中替换来源字符串为目标字符串。</T>
//
// @param pSource 来源字符串指针
// @param sourceLength 来源字符串长度
// @param pTarget 目标字符串指针
// @param targetLength 目标字符串长度
//============================================================
void MString{type}::Replace(const {char}* pSource, TInt sourceLength, const {char}* pTarget, TInt targetLength){
   if(targetLength > sourceLength){
      // 需要重置大小
      TInt count = RTypes<{char}>::Count({memory}, _length, pSource, sourceLength);
      TInt size = (targetLength - sourceLength) * count + _length;
      {char}* pBuffer = _pAllocator->Alloc(size);
      TInt length = RTypes<{char}>::Replace({memory}, _length, pSource, sourceLength, pTarget, targetLength, pBuffer, size);
      EnsureResize(length);
      RTypes<{char}>::CopyFast({memory}, pBuffer, length);
      _length = length;
      _pAllocator->Free(pBuffer);
   }else{
      _length = RTypes<{char}>::Replace({memory}, _length, pSource, sourceLength, pTarget, targetLength, {memory}, _atom.capacity);
   }
}

//============================================================
// <T>从字符串中将来源位置和目标位置的字符交换。</T>
//
// @param source 来源位置
// @param target 目标位置
//============================================================
// <T>交换当前数组中两个位置的值。</T>
void MString{type}::Swap(TInt source, TInt target){
   MO_ASSERT_RANGE(source, 0, _length);
   MO_ASSERT_RANGE(target, 0, _length);
   if(source != target){
      {char} value = {memory}[source];
      {memory}[source] = {memory}[target];
      {memory}[target] = value;
   }
}

//============================================================
// <T>使用排序器对字符进行排序。</T>
//
// @param hComparer 排序器
//============================================================
void MString{type}::Sort(HComparer hComparer){
   MO_ASSERT(hComparer);
   RTypes<{char}>::SortQuick({memory}, _length, hComparer);
}

//============================================================
// <T>此函数只可在排序后调用。返回指定数据的索引位置。</T>
//
// @param data 数据
// @param hComparer 排序器
// @return 索引位置
//============================================================
TInt MString{type}::SortSearch(const T& data, HComparer hComparer){
   MO_ASSERT(hComparer);
   return RTypes<{char}>::SortSearch({memory}, _length, data, hComparer);
}

//============================================================
// <T>删除指定索引位置的字符。</T>
//
// @param index 索引位置
// @return 字符
//============================================================
{char} MString{type}::Delete(TInt index){
   return _atom.Delete(index);
}

//============================================================
// <T>删除指定位置起的长度的数据。</T>
// <P>如果长度超过，则删除指定位置到结束位置之间的数据</P>
//
// @param offset 位置
// @param length 长度
//============================================================
void MString{type}::Delete(TInt offset, TInt length){
   MO_ASSERT_RANGE(offset, 0, _length);
   MO_ASSERT_RANGE(length, 0, _length - offset);
   TInt count = _length - offset - length;
   if(count > 0){
      RTypes<{char}>::CopySafe({memory} + offset, {memory} + offset + length, count);
   }
   _length -= length;
}

//============================================================
// <P>移除素有指定字符的数据。</P>
//
// @param value 字符
//============================================================
void MString{type}::Remove({char} value){
   _length = RTypes<{char}>::Remove({memory}, _length, value);
}

//============================================================
// <T>修正当前字符串内容。</T>
// <P>如果字符串中含有0，则废弃后面的部分，并修正长度。</P>
// <P>如果字符串结尾处没有0，则自动添入0进行结尾。</P>
//============================================================
void MString{type}::Fix(){
   TInt size = _atom.capacity;
   TInt length = 0;
   for(TInt n = 0; n < size; n++){
      if(0 == {memory}[n]){
         length = n;
         return;
      }
   }
   EnsureResize(length + 1);
   {memory}[length] = 0;
   _length = length;
}

//============================================================
// <T>设置数据长度。</T>
// <P>长度变小后，多余的内容将丢失。如果存储的是指针，一定要自己先释放多余的对象后，再缩小长度。
// 长度变大后，空余内存将被置为默认值。</P>
//============================================================
void MString{type}::SetLength(TInt length){
   EnsureResize(_length + length);
   if(length > _length){
      {char}* pMemory = {memory};
      for(TInt n = _length; n < length; n++){
         pMemory[n] = RType<T>::Default();
      }
   }
   _length = length;
}

//============================================================
// <T>清除所有数据。</T>
// <P>注意本操作对数据不做任何处理，如果存储指针，一定要先自己释放。只是将长度置为空，可以重新放数据。</P>
//============================================================
void MString{type}::Clear(){
   _length = 0;
}

//============================================================
// <T>释放内存。</T>
//============================================================
void MString{type}::Release(){
   MO_MEM_FREE({memory});
}








//============================================================
// <T>去掉左边的空白，返回字符串引用。</T>
//
// @return 字符串指针对象
//============================================================
TString8PtrC MString{type}::TrimLeftStrC() const{
   TInt position = 0;
   TInt length = _length;
   TChar8C* pMemory = {memory};
   while((position < length) && (pMemory[position] <= ' ')){
      position++;
   }
   return TString8PtrC(pMemory + position, length - position);
}

//============================================================
// <T>去掉右边的空白，返回字符串引用。</T>
//
// @return 字符串指针对象
//============================================================
TString8PtrC MString{type}::TrimRightStrC() const{
   TInt position = _length - 1;
   TChar8C* pMemory = {memory};
   while((position >= 0) && (pMemory[position] <= ' ')){
      position--;
   }
   return TString8PtrC(pMemory, position);
}

//============================================================
// <T>去掉两边的空白，返回字符串引用。</T>
//
// @return 字符串指针对象
//============================================================
TString8PtrC MString{type}::TrimStrC() const{
   TInt start = 0;
   TInt length = _length;
   TChar8C* pMemory = {memory};
   // 去掉左边空白字符
   while((start < length) && (pMemory[start] <= ' ')){
      start++;
   }
   // 去掉右边空白字符
   TInt end = length - 1;
   while((end >= 0) && (pMemory[end] <= ' ')){
      end--;
   }
   return TString8PtrC(pMemory + start, end - start + 1);
}

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
   length = vsprintf_s({memory}, length + 1, pFormat, params);
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
