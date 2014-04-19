public:
   //------------------------------------------------------------
   // <T>当前字符串和指定字符串是否相等。</T>
   TBool Equals({char}C* pValue, TInt length = -1) const{
      if(NULL != pValue){
         // 获得长度
         if(length < 0){
            length = RChar{type}::Length(pValue);
         }
         // 比较位置
         return RTypes<{char}>::Equals({memory}, _length, pValue, length);
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>当前字符串和指定字符串是否相等。</T>
   TBool Equals(const MString{type}& value) const{
      return RTypes<{char}>::Equals({memory}, _length, value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>不关心字符大小写，判断当前字符串和指定字符串是否相等。</T>
   TBool EqualsIgnoreCase({char}C* pValue, TInt length = -1) const{
      if(NULL != pValue){
         TInt result = MO_LB_STR_ICMP{type}({memory}, pValue);
         return (0 == result);
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>不关心字符大小写，判断当前字符串和指定字符串是否相等。</T>
   TBool EqualsIgnoreCase(const MString{type}& value) const{
      return EqualsIgnoreCase(value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>判断从当前字符串中是否含有指定字符。</T>
   TBool Contains({char} value) const{
      TInt result = RTypes<{char}>::IndexOf({memory}, _length, value);
      return (ENotFound != result);
   }
   //------------------------------------------------------------
   // <T>判断从当前字符串中是否含有指定字符串。</T>
   TBool Contains(const {char}* pValue, TInt length = -1) const{
      if(NULL != pValue){
         // 获得长度
         if(length < 0){
            length = RChar{type}::Length(pValue);
         }
         // 查找位置
         TInt result = RTypes<{char}>::Find({memory}, _length, pValue, length);
         return (ENotFound != result);
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>判断从当前字符串中是否含有指定字符串。</T>
   TBool Contains(const MString{type}& value) const{
      TInt result = RTypes<{char}>::Find({memory}, _length, value.MemoryC(), value.Length());
      return (ENotFound != result);
   }
   //------------------------------------------------------------
   // <T>判断指定字符串是否出现在当前字符串的开始位置。</T>
   TBool StartsWith(const {char}* pValue, TInt length = -1) const{
      MO_ASSERT(pValue);
      if(length < 0){
         length = RChar{type}::Length(pValue);
      }
      return RTypes<{char}>::StartsWith({memory}, _length, pValue, length);
   }
   //------------------------------------------------------------
   // <T>判断指定字符串是否出现在当前字符串的开始位置。</T>
   TBool StartsWith(const MString{type}& value) const{
      return RTypes<{char}>::StartsWith({memory}, _length, value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>判断指定字符串是否出现在当前字符串的结束位置。</T>
   TBool EndsWith(const {char}* pValue, TInt length = -1) const{
      MO_ASSERT(pValue);
      if(length < 0){
         length = RChar{type}::Length(pValue);
      }
      return RTypes<{char}>::EndsWith({memory}, _length, pValue, length);
   }
   //------------------------------------------------------------
   // <T>判断指定字符串是否出现在当前字符串的结束位置。</T>
   TBool EndsWith(const MString{type}& value) const{
      return RTypes<{char}>::EndsWith({memory}, _length, value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>比较当前数组中和指定数组的大小。</T>
   TInt Compare(const {char}* pValue, TInt length = -1) const{
      MO_ASSERT(pValue);
      if(length < 0){
         length = RChar{type}::Length(pValue);
      }
      return RTypes<{char}>::Compare({memory}, _length, pValue, length);
   }
   //------------------------------------------------------------
   // <T>比较当前数组中和指定数组的大小。</T>
   TInt Compare(const MString{type}& value) const{
      return RTypes<{char}>::Compare({memory}, _length, value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>不关心字符大小写，判断当前字符串和指定字符串的大小。</T>
   TInt CompareIgnoreCase(const {char}* pValue, TInt length = -1) const{
      return RChar{type}::CompareIgnoreCase({memory}, pValue);
   }
   //------------------------------------------------------------
   // <T>查找从当前字符串中指定字符出现的索引位置。</T>
   TInt IndexOf({char} value) const{
      return RTypes<{char}>::IndexOf({memory}, _length, value);
   }
   //------------------------------------------------------------
   // <T>查找从当前字符串中指定字符出现的索引位置。</T>
   TInt IndexOf({char} value, TInt offset) const{
      MO_ASSERT_RANGE(offset, 0, _length);
      TInt find = RTypes<{char}>::IndexOf({memory} + offset, _length - offset, value);
      if(ENotFound == find){
         return find;
      }
      return offset + find;
   }
   //------------------------------------------------------------
   // <T>查找从当前字符串中指定字符出现的最后索引位置。</T>
   TInt LastIndexOf({char} value) const{
      return RTypes<{char}>::LastIndexOf({memory}, _length, value);
   }
   //------------------------------------------------------------
   // <T>查找从当前字符串中指定字符出现的最后索引位置。</T>
   TInt LastIndexOf({char} value, TInt offset) const{
      MO_ASSERT_RANGE(offset, 0, _length);
      return RTypes<{char}>::LastIndexOf({memory}, offset, value);
   }
   //------------------------------------------------------------
   // <T>查找从当前字符串中指定字符串出现的索引位置。</T>
   TInt Find(const {char}* pValue, TInt length = -1) const{
      MO_ASSERT(pValue);
      if(length < 0){
         length = RChar{type}::Length(pValue);
      }
      return RTypes<{char}>::Find({memory}, _length, pValue, length);
   }
   //------------------------------------------------------------
   // <T>查找从当前字符串中指定字符串出现的索引位置。</T>
   TInt Find(const MString{type}& value) const{
      return RTypes<{char}>::Find({memory}, _length, value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>查找从当前字符串中指定字符串出现的最后索引位置。</T>
   TInt LastFind(const {char}* pValue, TInt length = -1) const{
      MO_ASSERT(pValue);
      if(length < 0){
         length = RChar{type}::Length(pValue);
      }
      return RTypes<{char}>::LastFind({memory}, _length, pValue, length);
   }
   //------------------------------------------------------------
   // <T>查找从当前字符串中指定字符串出现的最后索引位置。</T>
   TInt LastFind(const MString{type}& value) const{
      return RTypes<{char}>::LastFind({memory}, _length, value.MemoryC(), value.Length());
   }
public:
   //------------------------------------------------------------
   // <T>根据索引位置获得数据内容。</T>
   MO_INLINE( {char} Get(TInt index) ){
      MO_ASSERT_RANGE(index, 0, _length);
      return {memory}[index];
   }
   //------------------------------------------------------------
   // <T>根据索引位置设置数据内容。</T>
   MO_INLINE( void Set(TInt index, {char} value) ){
      MO_ASSERT_RANGE(index, 0, _length);
      {memory}[index] = value;
   }
public:
   //------------------------------------------------------------
   // <T>获得只读字符串指针。</T>
   TStringPtrC StrC() const{
      return TStringPtrC(_length, {memory});
   }
   //------------------------------------------------------------
   // 获得当前数组中的左边部分数组的字符串指针。
   TStringPtrC LeftStrC(TInt length) const{
      MO_ASSERT_BETWEEN(length, 0, _length);
      return TStringPtrC(length, {memory});
   }
   //------------------------------------------------------------
   // 获得当前数组中的开始到结尾的字符串指针。
   TStringPtrC MidStrC(TInt offset) const{
      MO_ASSERT_BETWEEN(offset, 0, _length);
      return TStringPtrC(_length - offset, {memory} + offset);
   }
   //------------------------------------------------------------
   // 获得当前数组中的部分数组的字符串指针。
   TStringPtrC MidStrC(TInt offset, TInt length) const{
      MO_ASSERT_BETWEEN(offset, 0, _length);
      MO_ASSERT_BETWEEN(length, 0, _length - offset);
      return TStringPtrC(length, {memory} + offset);
   }
   //------------------------------------------------------------
   // 获得当前数组中的右边部分数组的字符串指针。
   TStringPtrC RightStrC(TInt length) const{
      MO_ASSERT_BETWEEN(length, 0, _length);
      return TStringPtrC(length, {memory} + _length - length);
   }
   //------------------------------------------------------------
   // 获得当前数组中的部分数组的字符串指针。
   TStringPtrC SubStrC(TInt begin, TInt end) const{
      MO_ASSERT_BETWEEN(begin, 0, _length);
      MO_ASSERT_BETWEEN(end, 0, _length);
      MO_ASSERT(begin <= end);
      return TStringPtrC(end - begin, {memory} + begin);
   }
   //------------------------------------------------------------
   // <T>去掉左边的空白，返回字符串指针。</T>
   TStringPtrC TrimLeftStrC() const{
      TInt position = 0;
      TInt length = _length;
      {char}C* pMemory = {memory};
      while((position < length) && (pMemory[position] <= ' ')){
         position++;
      }
      return TStringPtrC(length - position, pMemory + position);
   }
   //------------------------------------------------------------
   // <T>去掉右边的空白，返回字符串指针。</T>
   TStringPtrC TrimRightStrC() const{
      TInt position = _length - 1;
      {char}C* pMemory = {memory};
      while((position >= 0) && (pMemory[position] <= ' ')){
         position--;
      }
      return TStringPtrC(position, pMemory);
   }
   //------------------------------------------------------------
   // <T>去掉两边的空白，返回字符串指针。</T>
   TStringPtrC TrimStrC() const{
      TInt start = 0;
      TInt length = _length;
      {char}C* pMemory = {memory};
      // 去掉左边空白字符
      while((start < length) && (pMemory[start] <= ' ')){
         start++;
      }
      // 去掉右边空白字符
      TInt end = length - 1;
      while((end >= 0) && (pMemory[end] <= ' ')){
         end--;
      }
      return TStringPtrC(end - start + 1, pMemory + start);
   }
public:
   //------------------------------------------------------------
   // <T>保证内存扩张，保留以前内容。</T>
   void EnsureExtend(TInt size){
      TInt capacity = _length + size;
      if(capacity > {capacity}){
         EnsureResize(capacity);
      }
   }
   //------------------------------------------------------------
   // <T>接收一个字符串内容。</T>
   void Assign(const {char}* pValue, TInt length = -1){
      if(NULL == pValue){
         length = 0;
      }else if(length < 0){
         length = RChar{type}::Length(pValue);
      }
      if(length > 0){
         EnsureSize(length + 1);
         RTypes<{char}>::CopyFast({memory}, pValue, length);
         _length = length;
         {memory}[_length] = 0;
      }
   }
   //------------------------------------------------------------
   // <T>接收一个字符指针对象。</T>
   void Assign(const TPtrC<{char}>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>接收一个字符串。</T>
   void Assign(const MString{type}& value){
      Assign(value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>追加一个字符内容到尾部。</T>
   void Append({char} value){
      Push(value);
   }
   //------------------------------------------------------------
   // <T>追加一个字符串内容到尾部。</T>
   void Append(const {char}* pValue, TInt length = -1){
      if(NULL == pValue){
         length = 0;
      }else if(length < 0){
         length = RChar{type}::Length(pValue);
      }
      if(length > 0){
         EnsureResize(_length + length + 1);
         RTypes<{char}>::CopyFast({memory} + _length, pValue, length);
         _length += length;
         {memory}[_length] = 0;
      }
   }
   //------------------------------------------------------------
   // <T>追加一个字符指针对象到尾部。</T>
   void Append(const TPtrC<{char}>& ptr){
      Append(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>追加一个字符串内容到尾部。</T>
   void Append(const MString{type}& value){
      Append(value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>在指定索引位置插入一个字符内容。</T>
   void Insert(TInt index, {char} value){
      MO_ASSERT_BETWEEN(index, 0, _length);
      EnsureResize(_length + 1);
      RTypes<{char}>::CopySafe({memory} + index + 1, {memory} + index, _length - index);
      {memory}[index] = value;
      _length++;
   }
   //------------------------------------------------------------
   // <T>在指定索引位置插入一个字符串内容。</T>
   void Insert(TInt index, const {char}* pValue, TInt length = -1){
      MO_ASSERT_BETWEEN(index, 0, _length);
      if(length < 0){
         length = RChar{type}::Length(pValue);
      }
      EnsureResize(_length + length);
      RTypes<{char}>::CopySafe({memory} + index + length, {memory} + index, _length - index);
      RTypes<{char}>::CopyFast({memory} + index, pValue, length);
      _length += length;
   }
   //------------------------------------------------------------
   // <T>在指定索引位置插入一个字符串内容。</T>
   void Insert(TInt index, const MString{type}& value){
      Insert(index, value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>从首部弹出一个字符内容。</T>
   {char} Shift(){
      MO_ASSERT(_length > 0);
      {char} value = {memory}[0];
      _length -= 1;
      if(_length > 0){
         RTypes<{char}>::CopySafe({memory}, {memory} + 1, _length);
      }
      return value;
   }
   //------------------------------------------------------------
   // <T>从首部压入一个字符内容。</T>
   void Unshift({char} value){
      EnsureResize(_length + 1);
      if(_length > 0){
         RTypes<{char}>::CopySafe({memory} + 1, {memory}, _length);
      }
      {memory}[0] = value;
      _length++;
   }
   //------------------------------------------------------------
   // <T>从尾部弹出一个字符内容。</T>
   {char} Pop(){
      MO_ASSERT(_length > 0);
      _length--;
      {char} value = {memory}[_length];
      {memory}[_length] = 0;
      return value;
   }
   //------------------------------------------------------------
   // <T>从尾部推入一个字符内容。</T>
   void Push({char} value){
      EnsureResize(_length + 1);
      {memory}[_length++] = value;
      {memory}[_length] = 0;
   }
   //------------------------------------------------------------
   // <T>使用指定字符内容填充当前字符串。</T>
   void Fill({char} value){
      RTypes<{char}>::Fill({memory}, _length, value);
   }
   //------------------------------------------------------------
   // <T>使用指定字符内容填充当前字符串。</T>
   void Fill({char} value, TInt length){
      MO_ASSERT_RANGE(length, 0, _length)
      RTypes<{char}>::Fill({memory}, length, value);
   }
   //------------------------------------------------------------
   // <T>使用指定字符内容填充当前字符串。</T>
   void Fill({char} value, TInt offset, TInt length){
      MO_ASSERT_RANGE(offset, 0, _length)
      MO_ASSERT_RANGE(length, 0, _length - offset)
      RTypes<{char}>::Fill({memory} + offset, length, value);
   }
   //------------------------------------------------------------
   // <T>使用字符在左侧补齐字符串到指定长度。</T>
   void PadLeft({char} pad, TInt length){
      TInt count = length - _length;
      if(count > 0){
         EnsureResize(length + 1);
         RTypes<{char}>::CopySafe({memory} + count, {memory}, _length);
         RTypes<{char}>::Fill({memory}, count, pad);
         _length += count;
         {memory}[_length] = 0;
      }
   }
   //------------------------------------------------------------
   // <T>使用字符在右侧补齐字符串到指定长度。</T>
   void PadRight({char} pad, TInt length){
      TInt count = length - _length;
      if(count > 0){
         EnsureResize(length + 1);
         RTypes<{char}>::Fill({memory} + _length, count, pad);
         _length += count;
         {memory}[_length] = 0;
      }
   }
   //------------------------------------------------------------
   // <T>从字符串中替换来源字符为目标字符。</T>
   void Replace({char} source, {char} target){
      RTypes<{char}>::Replace({memory}, _length, source, target);
   }
   //------------------------------------------------------------
   // <T>从字符串中替换来源字符串为目标字符串。</T>
   void Replace(const {char}* pSource, const {char}* pTarget){
      TInt sourceLength = RChar{type}::Length(pSource);
      TInt targetLength = RChar{type}::Length(pTarget);
      Replace(pSource, sourceLength, pTarget, targetLength);
   }
   //------------------------------------------------------------
   // <T>从字符串中替换来源字符串为目标字符串。</T>
   void Replace(const {char}* pSource, TInt sourceLength, const {char}* pTarget, TInt targetLength){
      if(targetLength > sourceLength){
         // 需要重置大小
         TInt count = RTypes<{char}>::Count({memory}, _length, pSource, sourceLength);
         TInt size = (targetLength - sourceLength) * count + _length;
         {char}* pBuffer = InnerAlloc(size);
         TInt length = RTypes<{char}>::Replace({memory}, _length, pSource, sourceLength, pTarget, targetLength, pBuffer, size);
         EnsureResize(length);
         RTypes<{char}>::CopyFast({memory}, pBuffer, length);
         _length = length;
         InnerFree(pBuffer);
      }else{
         _length = RTypes<{char}>::Replace({memory}, _length, pSource, sourceLength, pTarget, targetLength, {memory}, {capacity});
      }
   }
   //------------------------------------------------------------
   // <T>从字符串中将来源位置和目标位置的字符交换。</T>
   // <T>交换当前数组中两个位置的值。</T>
   void Swap(TInt source, TInt target){
      MO_ASSERT_RANGE(source, 0, _length);
      MO_ASSERT_RANGE(target, 0, _length);
      if(source != target){
         {char} value = {memory}[source];
         {memory}[source] = {memory}[target];
         {memory}[target] = value;
      }
   }
   //------------------------------------------------------------
   // <T>将字符串转换为小写。</T>
   void ToLower(){
      RChar{type}::ToLower({memory}, _length);
   }
   //------------------------------------------------------------
   // <T>将字符串转换为大写。</T>
   void ToUpper(){
      RChar{type}::ToUpper({memory}, _length);
   }
   //------------------------------------------------------------
   // <T>使用排序器对字符进行排序。</T>
   void Sort(HComparer hComparer){
      MO_ASSERT(hComparer);
      RTypes<{char}>::SortQuick({memory}, _length, hComparer);
   }
   //------------------------------------------------------------
   // <T>此函数只可在排序后调用。返回指定数据的索引位置。</T>
   TInt SortSearch({char} value, HComparer hComparer){
      MO_ASSERT(hComparer);
      return RTypes<{char}>::SortSearch({memory}, _length, value, hComparer);
   }
   //------------------------------------------------------------
   // <T>删除指定索引位置的字符。</T>
   {char} Delete(TInt index){
      MO_ASSERT_RANGE(index, 0, _length);
      {char} value = {memory}[index];
      if(index != (_length - 1)){
         RTypes<{char}>::CopySafe({memory} + index, {memory} + index + 1, _length - index);
      }
      _length--;
      return value;
   }
   //------------------------------------------------------------
   // <T>删除指定位置起的长度的数据。</T>
   // <P>如果长度超过，则删除指定位置到结束位置之间的数据</P>
   void Delete(TInt offset, TInt length){
      MO_ASSERT_RANGE(offset, 0, _length);
      MO_ASSERT_RANGE(length, 0, _length - offset);
      TInt count = _length - offset - length;
      if(count > 0){
         RTypes<{char}>::CopySafe({memory} + offset, {memory} + offset + length, count);
      }
      _length -= length;
   }
   //------------------------------------------------------------
   // <P>移除素有指定字符的数据。</P>
   void Remove({char} value){
      _length = RTypes<{char}>::Remove({memory}, _length, value);
   }
   //------------------------------------------------------------
   // <T>修正当前字符串内容。</T>
   // <P>如果字符串中含有0，则废弃后面的部分，并修正长度。</P>
   // <P>如果字符串结尾处没有0，则自动添入0进行结尾。</P>
   void Fix(){
      TInt size = {capacity};
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
   //------------------------------------------------------------
   // <T>设置数据长度。</T>
   // <P>长度变小后，多余的内容将丢失。如果存储的是指针，一定要自己先释放多余的对象后，再缩小长度。
   // 长度变大后，空余内存将被置为默认值。</P>
   void SetLength(TInt length){
      EnsureResize(length + 1);
      _length = length;
      {memory}[length] = 0;
   }
   //------------------------------------------------------------
   // <T>清除所有数据。</T>
   // <P>注意本操作对数据不做任何处理，如果存储指针，一定要先自己释放。只是将长度置为空，可以重新放数据。</P>
   void Clear(){
      _length = 0;
      if({capacity} > 0){
         {memory}[0] = 0;
      }
   }
   //------------------------------------------------------------
   // <T>释放内存。</T>
   void Release(){
      InnerFree({memory});
   }
public:
   //------------------------------------------------------------
   // <T>接收一个格式化字符串。</T>
   void AssignFormat(const {char}* pFormat, ...){
      // 格式化可变参数字符串信息
      va_list params;
      va_start(params, pFormat);
      // 输出日志信息
#ifdef _MO_MODE_WINDOWS
      TInt length = MO_LB_VSCPRINTF{type}(pFormat, params);
      EnsureSize(length + 1);
      length = MO_LB_STR_VSPRINTF{type}({memory}, length + 1, pFormat, params);
      _length = length;
#endif //_MO_MODE_WINDOWS
#ifdef _MO_MODE_LINUX
      {char} buffer[MO_FS_FORMAT_LENGTH];
      MO_LB_STR_VSPRINTF{type}(buffer, MO_FS_FORMAT_LENGTH, pFormat, params);
      Assign(buffer);
#endif // _MO_MODE_LINUX
      va_end(params);
   }
   //------------------------------------------------------------
   // <T>接收一个格式化字符串。</T>
   void AssignFormat(const {char}* pFormat, va_list& params){
      // 输出日志信息
#ifdef _MO_MODE_WINDOWS
      TInt length = MO_LB_VSCPRINTF{type}(pFormat, params);
      EnsureSize(length + 1);
      length = MO_LB_STR_VSPRINTF{type}({memory}, length + 1, pFormat, params);
      _length = length;
#endif //_MO_MODE_WINDOWS
#ifdef _MO_MODE_LINUX
      {char} buffer[MO_FS_FORMAT_LENGTH];
      MO_LB_STR_VSPRINTF{type}(buffer, MO_FS_FORMAT_LENGTH, pFormat, params);
      Assign(buffer);
#endif // _MO_MODE_LINUX
   }
   //------------------------------------------------------------
   // <T>追加一个格式化字符串到对象尾部。</T>
   void AppendFormat(const {char}* pFormat, ...){
      // 格式化可变参数字符串信息
      va_list params;
      va_start(params, pFormat);
      // 输出日志信息
#ifdef _MO_MODE_WINDOWS
      TInt length = MO_LB_VSCPRINTF{type}(pFormat, params);
      EnsureExtend(length + 1);
      length = MO_LB_STR_VSPRINTF{type}({memory} + _length, length + 1, pFormat, params);
      _length += length;
#endif //_MO_MODE_WINDOWS
#ifdef _MO_MODE_LINUX
      {char} buffer[MO_FS_FORMAT_LENGTH];
      MO_LB_STR_VSPRINTF{type}(buffer, MO_FS_FORMAT_LENGTH, pFormat, params);
      Append(buffer);
#endif // _MO_MODE_LINUX
      va_end(params);
   }
   //------------------------------------------------------------
   // <T>追加一个格式化字符串到对象尾部。</T>
   void AppendFormat(const {char}* pFormat, va_list& params){
#ifdef _MO_MODE_WINDOWS
      TInt length = MO_LB_VSCPRINTF{type}(pFormat, params);
      EnsureExtend(length + 1);
      length = MO_LB_STR_VSPRINTF{type}({memory} + _length, length + 1, pFormat, params);
      _length += length;
#endif //_MO_MODE_WINDOWS
#ifdef _MO_MODE_LINUX
      {char} buffer[MO_FS_FORMAT_LENGTH];
      MO_LB_STR_VSPRINTF{type}(buffer, MO_FS_FORMAT_LENGTH, pFormat, params);
      Append(buffer);
#endif // _MO_MODE_LINUX
   }
