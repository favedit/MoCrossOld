public:
   //------------------------------------------------------------
   // <T>获得数据只读指针</T>
   MO_INLINE( operator const T*() const ){
      return {memory};
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE( operator T*() ){
      return {memory};
   }
   //------------------------------------------------------------
   // <T>接收一个数据指针。</T>
   MO_INLINE( void operator=(const TPtrC<T>& ptr) ){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>接收一个数组指针。</T>
   MO_INLINE( void operator=(const M{type}<T>& values) ){
      Assign(values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // 获得指定位置的数据内容
   MO_INLINE( const T& operator[](TInt index) const ){
      MO_ASSERT_RANGE(index, 0, _{length});
      return {memory}[index];
   }
   //------------------------------------------------------------
   // <T>设置指定位置的数据内容。</T>
   MO_INLINE( T& operator[](TInt index) ){
      MO_ASSERT_RANGE(index, 0, _{length});
      return {memory}[index];
   }
   //------------------------------------------------------------
   // 判断当前数组和指定数组中所有数据内容是否相等。
   MO_INLINE( TBool operator==(const M{type}<T>& values) const ){
      return RTypes<T>::Equals({memory}, _{length}, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // 判断当前数组和指定数组中所有数据内容是否不相等。
   MO_INLINE( TBool operator!=(const M{type}<T>& values) const ){
      return !RTypes<T>::Equals({memory}, _{length}, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否小于指定数组中数据内容。
   MO_INLINE( TBool operator<(const M{type}<T>& values) const ){
      return RTypes<T>::Compare({memory}, _{length}, values.MemoryC(), values.Length()) < 0;
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否大于指定数组中数据内容。
   MO_INLINE( TBool operator>(const M{type}<T>& values) const ){
      return RTypes<T>::Compare({memory}, _{length}, values.MemoryC(), values.Length()) > 0;
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否小于等于指定数组中数据内容。
   MO_INLINE( TBool operator<=(const M{type}<T>& values) const ){
      return RTypes<T>::Compare({memory}, _{length}, values.MemoryC(), values.Length()) <= 0;
   }
   //------------------------------------------------------------
   // 判断当前数组中数据内容是否大于等于指定数组中数据内容。
   MO_INLINE( TBool operator>=(const M{type}<T>& values) const ){
      return RTypes<T>::Compare({memory}, _{length}, values.MemoryC(), values.Length()) >= 0;
   }
   //------------------------------------------------------------
   // <T>追加一个数据到当前数组尾部。</T>
   MO_INLINE( void operator+=(T value) ){
      Push(value);
   }
   //------------------------------------------------------------
   // <T>追加一个数组到当前数组尾部。</T>
   MO_INLINE( void operator+=(const TPtrC<T>& ptr) ){
      Append(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>追加一个数组到当前数组尾部。</T>
   MO_INLINE( void operator+=(const M{type}<T>& values) ){
      Append(values.MemoryC(), values.Length());
   }
public:
   //------------------------------------------------------------
   // <T>判断内容是否为空。</T>
   MO_INLINE( TBool IsEmpty() const ){
      return (0 == _{length});
   }
   //------------------------------------------------------------
   // <T>获得数据容量。</T>
   MO_INLINE( TInt Capacity() const ){
      return {capacity};
   }
   //------------------------------------------------------------
   // <T>获得{length_label}。</T>
   MO_INLINE( TInt {Length}() const ){
      return _{length};
   }
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE( const T* MemoryC() const ){
      return {memory};
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE( T* Memory() ){
      return {memory};
   }
public:
   //------------------------------------------------------------
   // <T>获取只读迭代器。</T>
   TIteratorC IteratorC(){
      return TIteratorC(_{length}, {memory});
   }
   //------------------------------------------------------------
   // <T>获取只读迭代器。</T>
   TIteratorC IteratorC(TInt index){
      MO_ASSERT_RANGE(index, 0, _{length});
      return TIteratorC(_{length}, {memory}, index - 1);
   }
   //------------------------------------------------------------
   // <T>获取迭代器。</T>
   TIterator Iterator(){
      return TIterator(&_{length}, {memory});
   }
   //------------------------------------------------------------
   // <T>获取迭代器。</T>
   TIterator Iterator(TInt index){
      MO_ASSERT_RANGE(index, 0, _{length});
      return TIterator(&_{length}, {memory}, index - 1);
   }
   //------------------------------------------------------------
   // <T>获取反向只读迭代器。</T>
   MO_INLINE( TIteratorC LastIteratorC() ){
      return TIteratorC(_{length}, {memory}, _{length});
   }
   //------------------------------------------------------------
   // <T>获取反向只读迭代器。</T>
   MO_INLINE( TIteratorC LastIteratorC(TInt index) ){
      MO_ASSERT_RANGE(index, 0, _{length});
      return TIteratorC(_{length}, {memory}, _{length} - index);
   }
   //------------------------------------------------------------
   // <T>获取反向迭代器。</T>
   MO_INLINE( TIterator LastIterator() ){
      return TIterator(&_{length}, {memory}, _{length});
   }
   //------------------------------------------------------------
   // <T>获取指定位置开始的反向迭代器。</T>
   MO_INLINE( TIterator LastIterator(TInt index) ){
      MO_ASSERT_RANGE(index, 0, _{length});
      return TIterator(&_{length}, {memory}, _{length} - index);
   }
   //------------------------------------------------------------
   // <T>获取只读指针对象。</T>
   MO_INLINE( TPtrC<T> PtrC() const ){
      return TPtrC<T>(_{length}, {memory});
   }
   //------------------------------------------------------------
   // <T>获取指针对象。</T>
   MO_INLINE( TPtr<T> Ptr() ){
      return TPtr<T>({capacity}, &_{length}, {memory});
   }
   //------------------------------------------------------------
   // <T>计算当前集合的哈希值。</T>
   MO_INLINE( THashCode HashCode() const ){
      return RTypes<T>::MakeHashCode({memory}, _{length});
   }
public:
   //------------------------------------------------------------
   // <T>当前数组中是否和指定数组相等。</T>
   TBool Equals(const T* pMemory, TInt length) const{
      return RTypes<T>::Equals({memory}, _{length}, pMemory, length);
   }
   //------------------------------------------------------------
   // <T>当前数组中是否和指定数组相等。</T>
   TBool Equals(const M{type}<T>& values) const{
      return RTypes<T>::Equals({memory}, _{length}, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>判断从当前数组中是否含有指定数据。</T>
   TBool Contains(T value) const{
      TInt result = RTypes<T>::IndexOf({memory}, _{length}, value);
      return (ENotFound != result);
   }
   //------------------------------------------------------------
   // <T>判断从当前数组中是否含有指定数组。</T>
   TBool Contains(const T* pMemory, TInt length) const{
      TInt result = RTypes<T>::Find({memory}, _{length}, pMemory, length);
      return (ENotFound != result);
   }
   //------------------------------------------------------------
   // <T>判断从当前数组中是否含有指定数组。</T>
   TBool Contains(const M{type}<T>& values) const{
      TInt result = RTypes<T>::Find({memory}, _{length}, values.MemoryC(), values.Length());
      return (ENotFound != result);
   }
   //------------------------------------------------------------
   // <T>查找指定数组是否出现在当前数组的开始位置。</T>
   TBool StartsWith(const T* pMemory, TInt length) const{
      return RTypes<T>::StartsWith({memory}, _{length}, pMemory, length);
   }
   //------------------------------------------------------------
   // <T>查找指定数组是否出现在当前数组的开始位置。</T>
   TBool StartsWith(const M{type}<T>& values) const{
      return RTypes<T>::StartsWith({memory}, _{length}, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>查找指定数组是否出现在当前数组的结束位置。</T>
   TBool EndsWith(const T* pMemory, TInt length) const{
      return RTypes<T>::EndsWith({memory}, _{length}, pMemory, length);
   }
   //------------------------------------------------------------
   // <T>查找指定数组是否出现在当前数组的结束位置。</T>
   TBool EndsWith(const M{type}<T>& values) const{
      return RTypes<T>::EndsWith({memory}, _{length}, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>比较当前数组中和指定数组的大小。</T>
   TInt Compare(const T* pMemory, TInt length) const{
      return RTypes<T>::Compare({memory}, _{length}, pMemory, length);
   }
   //------------------------------------------------------------
   // <T>比较当前数组中和指定数组的大小。</T>
   TInt Compare(const M{type}<T>& values) const{
      return RTypes<T>::Compare({memory}, _{length}, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>从当前数组中查找指定值出现的索引位置。</T>
   TInt IndexOf(T value) const{
      return RTypes<T>::IndexOf({memory}, _{length}, value);
   }
   //------------------------------------------------------------
   // <T>从当前数组中，从指定位置之后查找指定值出现的索引位置。</T>
   TInt IndexOf(T value, TInt offset) const{
      MO_ASSERT_RANGE(offset, 0, _{length});
      TInt find = RTypes<T>::IndexOf({memory} + offset, _{length} - offset, value);
      if(ENotFound == find){
         return find;
      }
      return offset + find;
   }
   //------------------------------------------------------------
   // <T>从当前数组中查找最后出现的索引位置。</T>
   TInt LastIndexOf(T value) const{
      return RTypes<T>::LastIndexOf({memory}, _{length}, value);
   }
   //------------------------------------------------------------
   // <T>从当前数组中查找最后出现的索引位置。</T>
   TInt LastIndexOf(T value, TInt offset) const{
      MO_ASSERT_RANGE(offset, 0, _{length});
      return RTypes<T>::LastIndexOf({memory}, offset, value);
   }
   //------------------------------------------------------------
   // <T>查找指定数组对象在当前数组对象出现的索引位置。</T>
   TInt Find(const T* pValue, TInt length) const{
      MO_ASSERT(pValue);
      return RTypes<T>::Find({memory}, _{length}, pValue, length);
   }
   //------------------------------------------------------------
   // <T>查找指定数组对象在当前数组对象出现的索引位置。</T>
   TInt Find(const M{type}<T>& values) const{
      return RTypes<T>::Find({memory}, _{length}, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>查找指定数组对象在当前数组对象出现的索引位置。</T>
   TInt LastFind(const T* pValue, TInt length) const{
      MO_ASSERT(pValue);
      return RTypes<T>::LastFind({memory}, _{length}, pValue, length);
   }
   //------------------------------------------------------------
   // <T>查找指定数组对象在当前数组对象出现的索引位置。</T>
   TInt LastFind(const M{type}<T>& values) const{
      return RTypes<T>::LastFind({memory}, _{length}, values.MemoryC(), values.Length());
   }
public:
   //------------------------------------------------------------
   // <T>获取指定索引位置的数据。</T>
   MO_INLINE( T GetC(TInt index) const ){
      MO_ASSERT_RANGE(index, 0, _{length});
      return {memory}[index];
   }
   //------------------------------------------------------------
   // <T>获取指定索引位置的数据。</T>
   MO_INLINE( T GetC(TInt index, T nvl) const ){
      if((index >= 0) && (index < _{length})){
         return {memory}[index];
      }
      return nvl;
   }
   //------------------------------------------------------------
   // <T>根据索引位置获得数据内容。</T>
   MO_INLINE( T& Get(TInt index) ){
      MO_ASSERT_RANGE(index, 0, _{length});
      return {memory}[index];
   }
   //------------------------------------------------------------
   // <T>设置指定索引位置的数据。</T>
   MO_INLINE( void Set(TInt index, T value) ){
      {memory}[index] = value;
   }
   //------------------------------------------------------------
   // <T>设置指定索引位置的数据。</T>
   MO_INLINE( void ExtendSet(TInt index, T value) ){
      TInt length = index + 1;
      InnerResize(length, ETrue, ETrue);
      if(length > _{length}){
         _{length} = length;
      }
      {memory}[index] = value;
   }
public:
   //------------------------------------------------------------
   // 获得当前数组中的左边部分数组的引用。
   TPtrC<T> LeftPtrC(TInt length) const{
      MO_ASSERT_BETWEEN(length, 0, _{length});
      return TPtrC<T>(length, {memory});
   }
   //------------------------------------------------------------
   // 获得当前数组中的开始到结尾的引用。
   TPtrC<T> MidPtrC(TInt offset) const{
      MO_ASSERT_BETWEEN(offset, 0, _{length});
      return TPtrC<T>(_{length} - offset, {memory} + offset);
   }
   //------------------------------------------------------------
   // 获得当前数组中的部分数组的引用。
   TPtrC<T> MidPtrC(TInt offset, TInt length) const{
      MO_ASSERT_BETWEEN(offset, 0, _{length});
      MO_ASSERT_BETWEEN(length, 0, _{length} - offset);
      return TPtrC<T>(length, {memory} + offset);
   }
   //------------------------------------------------------------
   // 获得当前数组中的右边部分数组的引用。
   TPtrC<T> RightPtrC(TInt length) const{
      MO_ASSERT_BETWEEN(length, 0, _{length});
      return TPtrC<T>(length, {memory} + _{length} - length);
   }
   //------------------------------------------------------------
   // 获得当前数组中的部分数组的引用。
   TPtrC<T> SubPtrC(TInt begin, TInt end) const{
      MO_ASSERT_BETWEEN(begin, 0, _{length});
      MO_ASSERT_BETWEEN(end, 0, _{length});
      MO_ASSERT(begin <= end);
      return TPtrC<T>(end - begin, {memory} + begin);
   }
public:
   //------------------------------------------------------------
   // <T>接受一个数组到当前数组尾部。</T>
   void Assign(const T* pValues, TInt {length}){
      if((NULL != pValues) && ({length} > 0)){
         InnerResize({length}, EFalse, EFalse);
         RTypes<T>::CopyFast({memory}, pValues, {length});
         _{length} = {length};
      }
   }
   //------------------------------------------------------------
   // <T>接受一个变长数组到当前数组尾部。</T>
   void Assign(const M{type}<T>* pValues){
      if(NULL != pValues){
         Assign(pValues->MemoryC(), pValues->{Length}());
      }
   }
   //------------------------------------------------------------
   // <T>接受一个变长数组到当前数组尾部。</T>
   void Assign(const TPtrC<T>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>接受一个变长数组到当前数组尾部。</T>
   void Assign(const M{type}<T>& values){
      Assign(values.MemoryC(), values.{Length}());
   }
   //------------------------------------------------------------
   // <T>追加一个数组指针到当前数组尾部。</T>
   void Append(const T* pValues, TInt {length}){
      if((NULL != pValues) && ({length} > 0)){
         InnerResize(_{length} + {length}, ETrue, ETrue);
         RTypes<T>::CopyFast({memory} + _{length}, pValues, {length});
         _{length} += {length};
      }
   }
   //------------------------------------------------------------
   // <T>追加一个变长数组对象到当前数组尾部。</T>
   void Append(const M{type}<T>* pValues){
      if(NULL != pValues){
         Append(pValues->MemoryC(), pValues->{Length}());
      }
   }
   //------------------------------------------------------------
   // <T>追加一个变长数组对象到当前数组尾部。</T>
   void Append(const TPtrC<T>& ptr){
      Append(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>追加一个变长数组对象到当前数组尾部。</T>
   void Append(const M{type}<T>& values){
      Append(values.MemoryC(), values.{Length}());
   }
   //------------------------------------------------------------
   // <T>插入一个数据在指定位置。</T>
   void Insert(TInt index, T value){
      MO_ASSERT_BETWEEN(index, 0, _{length});
      InnerResize(_{length} + 1, ETrue, ETrue);
      RTypes<T>::CopySafe({memory} + index + 1, {memory} + index, _{length} - index);
      {memory}[index] = value;
      _{length}++;
   }
   //------------------------------------------------------------
   // <T>插入一个数据指针在指定位置。</T>
   void Insert(TInt index, const T* pValues, TInt length){
      MO_ASSERT_BETWEEN(index, 0, _{length});
      InnerResize(_{length} + length, ETrue, ETrue);
      RTypes<T>::CopySafe({memory} + index + length, {memory} + index, _{length} - index);
      RTypes<T>::CopyFast({memory} + index, pValues, length);
      _{length} += length;
   }
   //------------------------------------------------------------
   // <T>插入一个数组在指定位置。</T>
   void Insert(TInt index, const M{type}<T>& values){
      Insert(index, values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>从首部弹出一个数据。</T>
   T Shift(){
      MO_ASSERT(_{length} > 0);
      T& value = {memory}[0];
      _{length}--;
      if(_{length} > 0){
         RTypes<T>::CopySafe({memory}, {memory} + 1, _{length});
      }
      return value;
   }
   //------------------------------------------------------------
   // <T>从首部压入一个数据。</T>
   void Unshift(T value){
      InnerResize(_{length} + 1, ETrue, ETrue);
      if(_{length} > 0){
         RTypes<T>::CopySafe({memory} + 1, {memory}, _{length});
      }
      {memory}[0] = value;
      _{length}++;
   }
   //------------------------------------------------------------
   // <T>从尾部弹出一个数据。</T>
   T Pop(){
      MO_ASSERT(_{length} > 0);
      return {memory}[--_{length}];
   }
   //------------------------------------------------------------
   // <T>追加一个数据到当前数组尾部。</T>
   void Push(T value){
      InnerResize(_{length} + 1, ETrue, ETrue);
      {memory}[_{length}++] = value;
   }
   //------------------------------------------------------------
   // <T>追加一个不重复的数据到当前数组尾部。</T>
   void PushUnique(T value){
      TBool exists = Contains(value);
      if(!exists){
         InnerResize(_{length} + 1, ETrue, ETrue);
         {memory}[_{length}++] = value;
      }
   }
   //------------------------------------------------------------
   // <T>使用指定内容填充当前数组。</T>
   void Fill(T value){
      RTypes<T>::Fill({memory}, _{length}, value);
   }
   //------------------------------------------------------------
   // <T>使用指定内容和指定个数填充当前数组。</T>
   void Fill(T value, TInt length){
      MO_ASSERT_RANGE(length, 0, _{length})
      RTypes<T>::Fill({memory}, length, value);
   }
   //------------------------------------------------------------
   // <T>使用指定内容和指定个数从指定位置填充当前数组。</T>
   void Fill(T value, TInt offset, TInt length){
      MO_ASSERT_RANGE(offset, 0, _{length})
      MO_ASSERT_RANGE(length, 0, _{length} - offset)
      RTypes<T>::Fill({memory} + offset, length, value);
   }
   //------------------------------------------------------------
   // <T>从源内容替换为目标内容。</T>
   void Replace(T source, T target){
      RTypes<T>::Replace({memory}, _{length}, source, target);
   }
   //------------------------------------------------------------
   // <T>交换当前数组中两个位置的值。</T>
   void Swap(TInt from, TInt to){
      MO_ASSERT_RANGE(from, 0, _{length});
      MO_ASSERT_RANGE(to, 0, _{length});
      if(from != to){
         T value = {memory}[from];
         {memory}[from] = {memory}[to];
         {memory}[to] = value;
      }
   }
   //------------------------------------------------------------
   // <T>使用排序器对集合对象进行排序。</T>
   void Sort(HComparer hComparer){
      MO_ASSERT(hComparer);
      RTypes<T>::SortQuick({memory}, _{length}, hComparer);
   }
   //------------------------------------------------------------
   // <T>此函数只可在排序后调用。返回指定元素的位置。</T>
   TInt SortSearch(const T& data, HComparer hComparer){
      MO_ASSERT(hComparer);
      return RTypes<T>::SortSearch({memory}, _{length}, data, hComparer);
   }
   //------------------------------------------------------------
   // <T>删除指定位置的数据。</T>
   T Delete(TInt index){
      MO_ASSERT_RANGE(index, 0, _{length});
      T& value = {memory}[index];
      if(index != _{length} - 1){
         RTypes<T>::CopySafe({memory} + index, {memory} + index + 1, _{length} - index);
      }
      _{length}--;
      return value;
   }
   //------------------------------------------------------------
   // <T>删除指定位置起的长度的数据。</T>
   // <P>如果长度超过，则删除指定位置到结束位置之间的数据</P>
   void Delete(TInt offset, TInt length){
      MO_ASSERT_RANGE(offset, 0, _{length});
      MO_ASSERT_RANGE(length, 0, _{length} - offset);
      TInt count = _{length} - offset - length;
      if(count > 0){
         RTypes<T>::CopySafe({memory} + offset, {memory} + offset + length, MO_LG_MIN(count, length));
      }
      _{length} -= length;
   }
   //------------------------------------------------------------
   // <P>移除空数据，可能会移除多个相同的数据。</P>
   void Remove(T value){
      _{length} = RTypes<T>::Remove({memory}, _{length}, value);
   }
   //------------------------------------------------------------
   // <T>设置数据长度。</T>
   // <P>长度变小后，多余的内容将丢失。如果存储的是指针，一定要自己先释放多余的对象后，再缩小长度。
   // 长度变大后，空余内存将被置为空。</P>
   void Set{Length}(TInt {length}){
      InnerResize({length}, EFalse, ETrue);
      if({length} > _{length}){
         MO_LB_MEM_CLEAR({memory} + _{length}, sizeof(T) * ({length} - _{length}));
      }
      _{length} = {length};
   }
   //------------------------------------------------------------
   // <T>强制设置数据长度，不保留任何数据。</T>
   void Force{Length}(TInt {length}, TBool reset = EFalse){
      InnerResize({length}, EFalse, EFalse);
      if(reset){
         MO_LB_MEM_CLEAR({memory}, sizeof(T) * {length});
      }
      _{length} = {length};
   }
   //------------------------------------------------------------
   // <T>清除所有数据。</T>
   // <P>注意本操作对数据不做任何处理，如果存储指针，一定要先自己释放。只是将长度置为空，可以重新放数据。</P>
   void Clear(){
      _{length} = 0;
   }
   //------------------------------------------------------------
   // <T>释放内存。</T>
   void Release(){
      _{length} = 0;
      MO_MEM_FREE({memory});
   }