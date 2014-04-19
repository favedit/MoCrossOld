public:
   //------------------------------------------------------------
   // <T>获得数据只读指针</T>
   MO_INLINE( operator const T*() const ){
      return _atom.memoryPtr;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE( operator T*() ){
      return _atom.memoryPtr;
   }
   //------------------------------------------------------------
   // 获得指定位置的数据内容
   MO_INLINE( const T operator[](TInt index) const ){
      MO_ASSERT_RANGE(index, 0, _atom.length);
      return _atom.memoryPtr[index];
   }
   //------------------------------------------------------------
   // <T>设置指定位置的数据内容。</T>
   MO_INLINE( T& operator[](TInt index) ){
      MO_ASSERT_RANGE(index, 0, _atom.length);
      return _atom.memoryPtr[index];
   }
   //------------------------------------------------------------
   // 判断当前数组和指定数组中所有数据内容是否相等。
   MO_INLINE( TBool operator==(const MString{char}& values) const ){
      return RTypes<T>::Equals(_atom.memoryPtr, _atom.length, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // 判断当前数组和指定数组中所有数据内容是否不相等。
   MO_INLINE( TBool operator!=(const MString{char}& values) const ){
      return !RTypes<T>::Equals(_atom.memoryPtr, _atom.length, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否小于指定数组中数据内容。
   MO_INLINE( TBool operator<(const MString{char}& values) const ){
      return RTypes<T>::Compare(_atom.memoryPtr, _atom.length, values.MemoryC(), values.Length()) < 0;
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否大于指定数组中数据内容。
   MO_INLINE( TBool operator>(const MString{char}& values) const ){
      return RTypes<T>::Compare(_atom.memoryPtr, _atom.length, values.MemoryC(), values.Length()) > 0;
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否小于等于指定数组中数据内容。
   MO_INLINE( TBool operator<=(const MString{char}& values) const ){
      return RTypes<T>::Compare(_atom.memoryPtr, _atom.length, values.MemoryC(), values.Length()) <= 0;
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否大于等于指定数组中数据内容。
   MO_INLINE( TBool operator>=(const MString{char}& values) const ){
      return RTypes<T>::Compare(_atom.memoryPtr, _atom.length, values.MemoryC(), values.Length()) >= 0;
   }
   //------------------------------------------------------------
   // <T>追加一个数据到当前数组尾部。</T>
   MO_INLINE( void operator+=(T value) ){
      Push(value);
   }
   //------------------------------------------------------------
   // <T>追加一个数据到当前数组尾部。</T>
   MO_INLINE( void operator+=(const T* pValue) ){
      TInt length = RTypeChar<T>::Length(pValue);
      Append(pValue, length);
   }
   //------------------------------------------------------------
   // <T>追加一个数组到当前数组尾部。</T>
   MO_INLINE( void operator+=(const TPtrC<T>& ptr) ){
      Append(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>追加一个数组到当前数组尾部。</T>
   MO_INLINE( void operator+=(const MString{char}<T>& values) ){
      Append(values.MemoryC(), values.Length());
   }
public:
   //------------------------------------------------------------
   // <T>判断内容是否为空。</T>
   MO_INLINE( TBool IsEmpty() const ){
      return (0 == _atom.length);
   }
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE( const T* MemoryC() const ){
      return _atom.memoryPtr;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE( T* Memory() ){
      return _atom.memoryPtr;
   }
   //------------------------------------------------------------
   // <T>获得一个末尾是空的字符串。</T>
   T* MemoryZ(){
      EnsureExtend(1);
      _atom.memoryPtr[_atom.length] = 0;
      return _atom.memoryPtr;
   }
   //------------------------------------------------------------
   // <T>获得数据长度。</T>
   MO_INLINE( TInt Length() const ){
      return _atom.length;
   }
   //------------------------------------------------------------
   // <T>获得数据容量。</T>
   MO_INLINE( TInt Capacity() const ){
      return _atom.capacity;
   }
public:
   //------------------------------------------------------------
   // <T>获取迭代器。</T>
   TIterator Iterator(){
      return TIterator(&_atom, -1);
   }
   //------------------------------------------------------------
   // <T>获取指定位置开始的迭代器。</T>
   TIterator Iterator(TInt index){
      MO_ASSERT_RANGE(index, 0, _atom.length);
      return TIterator(&_atom, index);
   }
   //------------------------------------------------------------
   // <T>获取反向迭代器。</T>
   MO_INLINE( TIterator LastIterator() ){
      return TIterator(&_atom, _atom.length);
   }
   //------------------------------------------------------------
   // <T>获取指定位置开始的反向迭代器。</T>
   MO_INLINE( TIterator LastIterator(TInt index) ){
      MO_ASSERT_RANGE(index, 0, _atom.length);
      return TIterator(&_atom, _atom.length - index);
   }
   //------------------------------------------------------------
   // <T>获取当前数组的指针对象。</T>
   MO_INLINE( TPtr<T> Ptr() ){
      return TPtr<T>(_atom.memoryPtr, _atom.length, _atom.capacity);
   }
   //------------------------------------------------------------
   // <T>获得末尾为0的指针。</T>
   TStringBasePtrC<T> PtrZ(){
      EnsureExtend(1);
      _atom.memoryPtr[_atom.length] = 0;
      return TStringBasePtr<T>(_atom.memoryPtr, _atom.length);
   }
   //------------------------------------------------------------
   // <T>计算当前集合的哈希值。</T>
   MO_INLINE( THashCode HashCode() const ){
      return RTypes<T>::MakeHashCode(_atom.memoryPtr, _atom.length);
   }
public:
   //------------------------------------------------------------
   // <T>当前数组中是否和指定数组相等。</T>
   TBool Equals(const T* pValue, TInt length = -1) const{
      MO_ASSERT(pValue);
      if(length < 0){
         length = RTypeChar<T>::Length(pValue);
      }
      return RTypes<T>::Equals(_atom.memoryPtr, _atom.length, pValue, length);
   }
   //------------------------------------------------------------
   // <T>当前数组中是否和指定数组相等。</T>
   TBool Equals(const MString{char}<T>& values) const{
      return RTypes<T>::Equals(_atom.memoryPtr, _atom.length, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>判断从当前数组中是否含有指定数据。</T>
   TBool Contains(T value) const{
      TInt result = RTypes<T>::IndexOf(_atom.memoryPtr, _atom.length, value);
      return (ENotFound != result);
   }
   //------------------------------------------------------------
   // <T>判断从当前数组中是否含有指定数组。</T>
   TBool Contains(const T* pValue, TInt length = -1) const{
      MO_ASSERT(pValue);
      if(length < 0){
         length = RTypeChar<T>::Length(pValue);
      }
      TInt result = RTypes<T>::Find(_atom.memoryPtr, _atom.length, pValue, length);
      return (ENotFound != result);
   }
   //------------------------------------------------------------
   // <T>判断从当前数组中是否含有指定数组。</T>
   TBool Contains(const MString{char}<T>& values) const{
      TInt result = RTypes<T>::Find(_atom.memoryPtr, _atom.length, values.MemoryC(), values.Length());
      return (ENotFound != result);
   }
   //------------------------------------------------------------
   // <T>查找指定数组是否出现在当前数组的开始位置。</T>
   TBool StartsWith(const T* pValue, TInt length = -1) const{
      MO_ASSERT(pValue);
      if(length < 0){
         length = RTypeChar<T>::Length(pValue);
      }
      return RTypes<T>::StartsWith(_atom.memoryPtr, _atom.length, pValue, length);
   }
   //------------------------------------------------------------
   // <T>查找指定数组是否出现在当前数组的开始位置。</T>
   TBool StartsWith(const MString{char}<T>& values) const{
      return RTypes<T>::StartsWith(_atom.memoryPtr, _atom.length, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>查找指定数组是否出现在当前数组的结束位置。</T>
   TBool EndsWith(const T* pValue, TInt length = -1) const{
      MO_ASSERT(pValue);
      if(length < 0){
         length = RTypeChar<T>::Length(pValue);
      }
      return RTypes<T>::EndsWith(_atom.memoryPtr, _atom.length, pValue, length);
   }
   //------------------------------------------------------------
   // <T>查找指定数组是否出现在当前数组的结束位置。</T>
   TBool EndsWith(const MString{char}<T>& values) const{
      return RTypes<T>::EndsWith(_atom.memoryPtr, _atom.length, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>比较当前数组中和指定数组的大小。</T>
   TInt Compare(const T* pValue, TInt length = -1) const{
      MO_ASSERT(pValue);
      if(length < 0){
         length = RTypeChar<T>::Length(pValue);
      }
      return RTypes<T>::Compare(_atom.memoryPtr, _atom.length, pValue, length);
   }
   //------------------------------------------------------------
   // <T>比较当前数组中和指定数组的大小。</T>
   TInt Compare(const MString{char}<T>& values) const{
      return RTypes<T>::Compare(_atom.memoryPtr, _atom.length, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>从当前数组中查找指定值出现的索引位置。</T>
   TInt IndexOf(T value) const{
      return RTypes<T>::IndexOf(_atom.memoryPtr, _atom.length, value);
   }
   //------------------------------------------------------------
   // <T>从当前数组中，从指定位置之后查找指定值出现的索引位置。</T>
   TInt IndexOf(T value, TInt offset) const{
      MO_ASSERT_RANGE(offset, 0, _atom.length);
      TInt find = RTypes<T>::IndexOf(_atom.memoryPtr + offset, _atom.length - offset, value);
      if(ENotFound == find){
         return find;
      }
      return offset + find;
   }
   //------------------------------------------------------------
   // <T>从当前数组中查找最后出现的索引位置。</T>
   TInt LastIndexOf(T value) const{
      return RTypes<T>::LastIndexOf(_atom.memoryPtr, _atom.length, value);
   }
   //------------------------------------------------------------
   // <T>从当前数组中查找最后出现的索引位置。</T>
   TInt LastIndexOf(T value, TInt offset) const{
      MO_ASSERT_RANGE(offset, 0, _atom.length);
      return RTypes<T>::LastIndexOf(_atom.memoryPtr, offset, value);
   }
   //------------------------------------------------------------
   // <T>查找指定数组对象在当前数组对象出现的索引位置。</T>
   TInt Find(const T* pValue, TInt length = -1) const{
      MO_ASSERT(pValue);
      if(length < 0){
         length = RTypeChar<T>::Length(pValue);
      }
      return RTypes<T>::Find(_atom.memoryPtr, _atom.length, pValue, length);
   }
   //------------------------------------------------------------
   // <T>查找指定数组对象在当前数组对象出现的索引位置。</T>
   TInt Find(const MString{char}<T>& values) const{
      return RTypes<T>::Find(_atom.memoryPtr, _atom.length, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>查找指定数组对象在当前数组对象出现的索引位置。</T>
   TInt LastFind(const T* pValue, TInt length = -1) const{
      MO_ASSERT(pValue);
      if(length < 0){
         length = RTypeChar<T>::Length(pValue);
      }
      return RTypes<T>::LastFind(_atom.memoryPtr, _atom.length, pValue, length);
   }
   //------------------------------------------------------------
   // <T>查找指定数组对象在当前数组对象出现的索引位置。</T>
   TInt LastFind(const MString{char}<T>& values) const{
      return RTypes<T>::LastFind(_atom.memoryPtr, _atom.length, values.MemoryC(), values.Length());
   }
public:
   //------------------------------------------------------------
   // <T>根据索引位置获得数据内容。</T>
   MO_INLINE( T& Get(TInt index) ){
      return _atom.Get(index);
   }
   //------------------------------------------------------------
   // <T>根据索引位置设置数据内容。</T>
   MO_INLINE( void Set(TInt index, T value) ){
      _atom.Set(index, value);
   }
public:
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   TStringBasePtrC<T> StrC() const{
      return TStringBasePtrC<T>(_atom.memoryPtr, _atom.length);
   }
   //------------------------------------------------------------
   // 获得当前数组中的左边部分数组的引用。
   TStringBasePtrC<T> LeftStrC(TInt length) const{
      MO_ASSERT_BETWEEN(length, 0, _atom.length);
      return TStringBasePtrC<T>(_atom.memoryPtr, length);
   }
   //------------------------------------------------------------
   // 获得当前数组中的开始到结尾的引用。
   TStringBasePtrC<T> MidStrC(TInt offset) const{
      MO_ASSERT_BETWEEN(offset, 0, _atom.length);
      return TStringBasePtrC<T>(_atom.memoryPtr + offset, _atom.length - offset);
   }
   //------------------------------------------------------------
   // 获得当前数组中的部分数组的引用。
   TStringBasePtrC<T> MidStrC(TInt offset, TInt length) const{
      MO_ASSERT_BETWEEN(offset, 0, _atom.length);
      MO_ASSERT_BETWEEN(length, 0, _atom.length - offset);
      return TStringBasePtrC<T>(_atom.memoryPtr + offset, length);
   }
   //------------------------------------------------------------
   // 获得当前数组中的右边部分数组的引用。
   TStringBasePtrC<T> RightStrC(TInt length) const{
      MO_ASSERT_BETWEEN(length, 0, _atom.length);
      return TStringBasePtrC<T>(_atom.memoryPtr + _atom.length - length, length);
   }
   //------------------------------------------------------------
   // 获得当前数组中的部分数组的引用。
   TStringBasePtrC<T> SubStrC(TInt begin, TInt end) const{
      MO_ASSERT_BETWEEN(begin, 0, _atom.length);
      MO_ASSERT_BETWEEN(end, 0, _atom.length);
      MO_ASSERT(begin <= end);
      return TStringBasePtrC<T>(_atom.memoryPtr + begin, end - begin);
   }
public:
   //------------------------------------------------------------
   // <T>保证内存大小，不保留以前内容。</T>
   void EnsureSize(TInt size){
      if(size > _atom.capacity){
         // 当内存不足时，重新计算内存容量
         TInt capacity = _pAllocator->CalculateCapacity(&_atom, size);
         MO_ASSERT(capacity >= size);
         // 如果收集不成功，则不进行复制数据处理
         T* pAlloc = _pAllocator->Alloc(capacity);
         MO_ASSERT(pAlloc);
         // 如果以前内存有数据，则复制以前内存内容
         if(_atom.HasMemory()){
            _pAllocator->Free(_atom.memoryPtr);
         }
         // 设置新的内存
         _atom.memoryPtr = pAlloc;
         _atom.capacity = capacity;
      }
   }
   //------------------------------------------------------------
   // <T>保证内存大小，保留以前内容。</T>
   void EnsureResize(TInt size){
      if(size > _atom.capacity){
         // 当内存不足时，重新计算内存容量
         TInt capacity = _pAllocator->CalculateCapacity(&_atom, size);
         MO_ASSERT(size >= capacity);
         // 如果收集不成功，则不进行复制数据处理
         T* pAlloc = _pAllocator->Alloc(capacity);
         MO_ASSERT(pAlloc);
         // 如果以前内存有数据，则复制以前内存内容
         if(_atom.HasMemory()){
            if(_atom.HasData()){
               RTypes<T>::CopyFast(pAlloc, _atom.memoryPtr, _atom.length);
            }
            _pAllocator->Free(_atom.memoryPtr);
         }
         // 设置新的内存
         _atom.memoryPtr = pAlloc;
         _atom.capacity = capacity;
      }
   }
   //------------------------------------------------------------
   // <T>保证内存扩张。</T>
   MO_INLINE( void EnsureExtend(TInt size) ){
      TInt capacity = _atom.length + size;
      if(capacity > _atom.capacity){
         EnsureResize(capacity);
      }
   }
   //------------------------------------------------------------
   // <T>接受一个变长数组到当前数组尾部。</T>
   void Assign(const T* pValue, TInt length = -1){
      MO_ASSERT(pValue);
      if(length < 0){
         length = RTypeChar<T>::Length(pValue);
      }
      EnsureSize(length + 1);
      RTypes<T>::CopyFast(_atom.memoryPtr, pValue, length);
      _atom.length = length;
      _atom.memoryPtr[_atom.length] = 0;
   }
   //------------------------------------------------------------
   // <T>接受一个变长数组到当前数组尾部。</T>
   void Assign(const TPtrC<T>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>接受一个变长数组到当前数组尾部。</T>
   void Assign(const MString{char}<T>& values){
      Assign(values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>追加一个字符到当前数组尾部。</T>
   void Append(T value){
      Push(value);
   }
   //------------------------------------------------------------
   // <T>追加一个数组指针到当前数组尾部。</T>
   void Append(const T* pValue, TInt length = -1){
      MO_ASSERT(pValue);
      if(length < 0){
         length = RTypeChar<T>::Length(pValue);
      }
      EnsureResize(_atom.length + length + 1);
      RTypes<T>::CopyFast(_atom.memoryPtr + _atom.length, pValue, length);
      _atom.length += length;
      _atom.memoryPtr[_atom.length] = 0;
   }
   //------------------------------------------------------------
   // <T>追加一个变长数组对象到当前数组尾部。</T>
   void Append(const TPtrC<T>& ptr){
      Append(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>追加一个变长数组对象到当前数组尾部。</T>
   void Append(const MString{char}<T>& values){
      Append(values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>插入一个数据在指定位置。</T>
   void Insert(TInt index, T value){
      MO_ASSERT_BETWEEN(index, 0, _atom.length);
      EnsureResize(_atom.length + 1);
      RTypes<T>::CopySafe(_atom.memoryPtr + index + 1, _atom.memoryPtr + index, _atom.length - index);
      _atom.memoryPtr[index] = value;
      _atom.length++;
   }
   //------------------------------------------------------------
   // <T>插入一个数据指针在指定位置。</T>
   void Insert(TInt index, const T* pValue, TInt length){
      MO_ASSERT_BETWEEN(index, 0, _atom.length);
      if(length < 0){
         length = RTypeChar<T>::Length(pValue);
      }
      EnsureResize(_atom.length + length);
      RTypes<T>::CopySafe(_atom.memoryPtr + index + length, _atom.memoryPtr + index, _atom.length - index);
      RTypes<T>::CopyFast(_atom.memoryPtr + index, pValue, length);
      _atom.length += length;
   }
   //------------------------------------------------------------
   // <T>插入一个数组在指定位置。</T>
   void Insert(TInt index, const MString{char}<T>& values){
      Insert(index, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>从首部弹出一个数据。</T>
   T& Shift(){
      MO_ASSERT(_atom.length > 0);
      T& value = _atom.memoryPtr[0];
      _atom.length -= 1;
      if(_atom.length > 0){
         RTypes<T>::CopySafe(_atom.memoryPtr, _atom.memoryPtr + 1, _atom.length);
      }
      return value;
   }
   //------------------------------------------------------------
   // <T>从首部压入一个数据。</T>
   void Unshift(const T& value){
      EnsureResize(_atom.length + 1);
      if(_atom.length > 0){
         RTypes<T>::CopySafe(_atom.memoryPtr + 1, _atom.memoryPtr, _atom.length);
      }
      _atom.memoryPtr[0] = value;
      _atom.length++;
   }
   //------------------------------------------------------------
   // <T>从首部压入一个数据。</T>
   void Unshift(T value){
      EnsureResize(_atom.length + 1);
      if(_atom.length > 0){
         RTypes<T>::CopySafe(_atom.memoryPtr + 1, _atom.memoryPtr, _atom.length);
      }
      _atom.memoryPtr[0] = value;
      _atom.length++;
   }
   //------------------------------------------------------------
   // <T>从尾部弹出一个数据。</T>
   T& Pop(){
      MO_ASSERT(_atom.length > 0);
      _atom.length--;
      return _atom.memoryPtr[_atom.length];
   }
   //------------------------------------------------------------
   // <T>追加一个数据到当前数组尾部。</T>
   void Push(T value){
      EnsureResize(_atom.length + 1);
      _atom.memoryPtr[_atom.length++] = value;
   }
   //------------------------------------------------------------
   // <T>使用指定内容填充当前数组。</T>
   void Fill(T value){
      RTypes<T>::Fill(_atom.memoryPtr, _atom.length, value);
   }
   //------------------------------------------------------------
   // <T>使用指定内容和指定个数填充当前数组。</T>
   void Fill(T value, TInt length){
      MO_ASSERT_RANGE(length, 0, _atom.length)
      RTypes<T>::Fill(_atom.memoryPtr, length, value);
   }
   //------------------------------------------------------------
   // <T>使用指定内容和指定个数从指定位置填充当前数组。</T>
   void Fill(T value, TInt offset, TInt length){
      MO_ASSERT_RANGE(offset, 0, _atom.length)
      MO_ASSERT_RANGE(length, 0, _atom.length - offset)
      RTypes<T>::Fill(_atom.memoryPtr + offset, length, value);
   }
   //------------------------------------------------------------
   // <T>左侧补齐字符串。</T>
   void PadLeft(T pad, TInt length){
      TInt count = length - _atom.length;
      if(count > 0){
         EnsureResize(length + 1);
         RTypes<T>::CopySafe(_atom.memoryPtr + count, _atom.memoryPtr, _atom.length);
         RTypes<T>::Fill(_atom.memoryPtr, count, pad);
         _atom.length += count;
         _atom.memoryPtr[_atom.length] = 0;
      }
   }

   //------------------------------------------------------------
   // <T>右侧补齐字符串。</T>
   void PadRight(T pad, TInt length){
      TInt count = length - _atom.length;
      if(count > 0){
         EnsureResize(length + 1);
         RTypes<T>::Fill(_atom.memoryPtr + _atom.length, count, pad);
         _atom.length += count;
         _atom.memoryPtr[_atom.length] = 0;
      }
   }
   //------------------------------------------------------------
   // <T>从源内容替换为目标内容。</T>
   void Replace(T source, T target){
      RTypes<T>::Replace(_atom.memoryPtr, _atom.length, source, target);
   }
   //------------------------------------------------------------
   // <T>从源数组替换为目标数组。</T>
   void Replace(const T* pSource, const T* pTarget){
      TInt sourceLength = RTypeChar<T>::Length(pSource);
      TInt targetLength = RTypeChar<T>::Length(pTarget);
      Replace(pSource, sourceLength, pTarget, targetLength);
   }
   //------------------------------------------------------------
   // <T>从源数组替换为目标数组。</T>
   void Replace(const T* pSource, TInt sourceLength, const T* pTarget, TInt targetLength){
      if(targetLength > sourceLength){
         // 需要重置大小
         TInt count = RTypes<T>::Count(_atom.memoryPtr, _atom.length, pSource, sourceLength);
         TInt size = (targetLength - sourceLength) * count + _atom.length;
         T* pBuffer = _pAllocator->Alloc(size);
         TInt length = RTypes<T>::Replace(_atom.memoryPtr, _atom.length, pSource, sourceLength, pTarget, targetLength, pBuffer, size);
         EnsureResize(length);
         RTypes<T>::CopyFast(_atom.memoryPtr, pBuffer, length);
         _atom.length = length;
         _pAllocator->Free(pBuffer);
      }else{
         _atom.length = RTypes<T>::Replace(_atom.memoryPtr, _atom.length, pSource, sourceLength, pTarget, targetLength, _atom.memoryPtr, _atom.capacity);
      }
   }
   //------------------------------------------------------------
   // <T>交换当前数组中两个位置的值。</T>
   void Swap(TInt from, TInt to){
      MO_ASSERT_RANGE(from, 0, _atom.length);
      MO_ASSERT_RANGE(to, 0, _atom.length);
      if(from != to){
         T value = _atom.memoryPtr[from];
         _atom.memoryPtr[from] = _atom.memoryPtr[to];
         _atom.memoryPtr[to] = value;
      }
   }
   //------------------------------------------------------------
   // <T>使用排序器对集合对象进行排序。</T>
   void Sort(HComparer hComparer){
      MO_ASSERT(hComparer);
      RTypes<T>::SortQuick(_atom.memoryPtr, _atom.length, hComparer);
   }
   //------------------------------------------------------------
   // <T>此函数只可在排序后调用。返回指定元素的位置。</T>
   TInt SortSearch(const T& data, HComparer hComparer){
      MO_ASSERT(hComparer);
      return RTypes<T>::SortSearch(_atom.memoryPtr, _atom.length, data, hComparer);
   }
   //------------------------------------------------------------
   // <T>删除指定位置的数据。</T>
   T& Delete(TInt index){
      return _atom.Delete(index);
   }
   //------------------------------------------------------------
   // <T>删除指定位置起的长度的数据。</T>
   // <P>如果长度超过，则删除指定位置到结束位置之间的数据</P>
   void Delete(TInt offset, TInt length){
      MO_ASSERT_RANGE(offset, 0, _atom.length);
      MO_ASSERT_RANGE(length, 0, _atom.length - offset);
      TInt count = _atom.length - offset - length;
      if(count > 0){
         RTypes<T>::CopySafe(_atom.memoryPtr + offset, _atom.memoryPtr + offset + length, count);
      }
      _atom.length -= length;
   }
   //------------------------------------------------------------
   // <P>移除空数据，可能会移除多个相同的数据。</P>
   void Remove(T value){
      _atom.length = RTypes<T>::Remove(_atom.memoryPtr, _atom.length, value);
   }
   //------------------------------------------------------------
   // <T>修正当前字符串内容。</T>
   // <P>如果字符串中含有0，则废弃后面的部分，并修正长度。</P>
   // <P>如果字符串结尾处没有0，则自动添入0进行结尾。</P>
   void Fix(){
      TInt size = _atom.capacity;
      TInt length = 0;
      for(TInt n = 0; n < size; n++){
         if(0 == _atom.memoryPtr[n]){
            length = n;
            return;
         }
      }
      EnsureResize(length + 1);
      _atom.memoryPtr[length] = 0;
      _atom.length = length;
   }
   //------------------------------------------------------------
   // <T>设置数据长度。</T>
   // <P>长度变小后，多余的内容将丢失。如果存储的是指针，一定要自己先释放多余的对象后，再缩小长度。
   // 长度变大后，空余内存将被置为默认值。</P>
   void SetLength(TInt length){
      EnsureResize(_atom.length + length);
      if(length > _atom.length){
         T* pMemory = _atom.memoryPtr;
         for(TInt n = _atom.length; n < length; n++){
            pMemory[n] = RType<T>::Default();
         }
      }
      _atom.length = length;
   }
   //------------------------------------------------------------
   // <T>清除所有数据。</T>
   // <P>注意本操作对数据不做任何处理，如果存储指针，一定要先自己释放。只是将长度置为空，可以重新放数据。</P>
   void Clear(){
      _atom.length = 0;
   }
   //------------------------------------------------------------
   // <T>释放内存。</T>
   void Release(){
      if(_atom.HasMemory()){
         _pAllocator->Free(_atom.memoryPtr);
      }
      _atom.Reset();
   }