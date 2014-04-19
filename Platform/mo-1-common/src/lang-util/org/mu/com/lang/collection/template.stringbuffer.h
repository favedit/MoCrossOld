#ifndef __MO_CM_STRINGBUFFER{type}_H__
#define __MO_CM_STRINGBUFFER{type}_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_TYPE_H__
#include "MoCmType.h"
#endif // __MO_CM_TYPE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>字符串基类。</T>
//
// @manager
// @type T 数据类型
// @history 120701 MAOCY 创建
//============================================================
class MO_CM_DECLARE MStringBuffer{type}{
protected:
   // @attribute 数据容量
   TInt _capacity;
   // @attribute 数据长度
   TInt _length;
   // @attribute 数据指针
   {char}* _pMemory;
public:
   //------------------------------------------------------------
   // <T>构造字符串基类。</T>
   MStringBuffer{type}(){
      _capacity = 0;
      _length = 0;
      MO_CLEAR(_pMemory);
   }
   //------------------------------------------------------------
   // <T>析构字符串基类。</T>
   MO_ABSTRACT( ~MStringBuffer{type}() ){
      MO_FREE(_pMemory);
   }
public:
   //------------------------------------------------------------
   // <T>获得数据只读指针</T>
   MO_INLINE( operator {char}C*() const ){
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE( operator {char}*() ){
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>判断当前数组和指定数组中所有数据内容是否相等。</T>
   MO_INLINE( TBool operator==({char}C* pValue) const ){
      return RChar{type}s::Equals(_pMemory, pValue);
   }
   //------------------------------------------------------------
   // <T>判断当前数组和指定数组中所有数据内容是否相等。</T>
   MO_INLINE( TBool operator==(const MString{type}& value) const ){
      return RChar{type}s::Equals(_pMemory, value.MemoryC());
   }
   //------------------------------------------------------------
   // <T>判断当前数组和指定数组中所有数据内容是否不相等。</T>
   MO_INLINE( TBool operator!=({char}C* pValue) const ){
      return !RChar{type}s::Equals(_pMemory, pValue);
   }
   //------------------------------------------------------------
   // <T>判断当前数组和指定数组中所有数据内容是否不相等。</T>
   MO_INLINE( TBool operator!=(const MString{type}& value) const ){
      return !RChar{type}s::Equals(_pMemory, value.MemoryC());
   }
   //------------------------------------------------------------
   // <T>判断当前数组中数据内容是否小于指定数组中数据内容。</T>
   MO_INLINE( TBool operator<(const MString{type}& value) const ){
      return RChar{type}s::Compare(_pMemory, _length, value.MemoryC(), value.Length()) < 0;
   }
   //------------------------------------------------------------
   // <T>判断当前数组中数据内容是否大于指定数组中数据内容。</T>
   MO_INLINE( TBool operator>(const MString{type}& value) const ){
      return RChar{type}s::Compare(_pMemory, _length, value.MemoryC(), value.Length()) > 0;
   }
   //------------------------------------------------------------
   // <T>判断当前数组中数据内容是否小于等于指定数组中数据内容。</T>
   MO_INLINE( TBool operator<=(const MString{type}& value) const ){
      return RChar{type}s::Compare(_pMemory, _length, value.MemoryC(), value.Length()) <= 0;
   }
   //------------------------------------------------------------
   // <T>判断当前数组中数据内容是否大于等于指定数组中数据内容。</T>
   MO_INLINE( TBool operator>=(const MString{type}& value) const ){
      return RChar{type}s::Compare(_pMemory, _length, value.MemoryC(), value.Length()) >= 0;
   }
   //------------------------------------------------------------
   // <T>获得指定位置的数据内容。</T>
   MO_INLINE( {char} operator[](TInt index) const ){
      MO_ASSERT_RANGE(index, 0, _length);
      return _pMemory[index];
   }
   //------------------------------------------------------------
   // <T>设置指定位置的数据内容。</T>
   MO_INLINE( {char}& operator[](TInt index) ){
      MO_ASSERT_RANGE(index, 0, _length);
      return _pMemory[index];
   }
   //------------------------------------------------------------
   // <T>追加一个数据到当前数组尾部。</T>
   MO_INLINE( void operator+=({char} value) ){
      Push(value);
   }
   //------------------------------------------------------------
   // <T>追加一个数据到当前数组尾部。</T>
   MO_INLINE( void operator+=(const {char}* pValue) ){
      TInt length = RChar{type}::Length(pValue);
      Append(pValue, length);
   }
   //------------------------------------------------------------
   // <T>追加一个数组到当前数组尾部。</T>
   MO_INLINE( void operator+=(const TPtrC<{char}>& ptr) ){
      Append(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>追加一个数组到当前数组尾部。</T>
   MO_INLINE( void operator+=(const MString{type}& value) ){
      Append(value.MemoryC(), value.Length());
   }
public:
   //------------------------------------------------------------
   // <T>判断内容是否为空。</T>
   MO_INLINE( TBool IsEmpty() const ){
      return (0 == _length);
   }
   //------------------------------------------------------------
   // <T>获得数据容量。</T>
   MO_INLINE( TInt Capacity() const ){
      return _capacity;
   }
   //------------------------------------------------------------
   // <T>获得数据长度。</T>
   MO_INLINE( TInt Length() const ){
      return _length;
   }
   //------------------------------------------------------------
   // <T>获得字节数据长度。</T>
   MO_INLINE( TInt ByteLength() const ){
      return sizeof({char}) * _length;
   }
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE( const {char}* MemoryC() const ){
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE( {char}* Memory() ){
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得一个末尾是空的字符串。</T>
   MO_INLINE( {char}* MemoryZ() ){
      EnsureExtend(1);
      _pMemory[_length] = 0;
      return _pMemory;
   }
public:
   //------------------------------------------------------------
   // <T>获取迭代器。</T>
   MO_INLINE( TIteratorC IteratorC() ){
      return TIteratorC(_length, _pMemory);
   }
   //------------------------------------------------------------
   // <T>获取迭代器。</T>
   MO_INLINE( TIterator Iterator() ){
      return TIterator(&_length, _pMemory);
   }
   //------------------------------------------------------------
   // <T>获取指定位置开始的迭代器。</T>
   MO_INLINE( TIterator Iterator(TInt index) ){
      MO_ASSERT_RANGE(index, 0, _length);
      return TIterator(&_length, _pMemory, index);
   }
   //------------------------------------------------------------
   // <T>获取当前数组的指针对象。</T>
   MO_INLINE( TString{type}PtrC PtrC() ){
      return TString{type}PtrC(_length, _pMemory);
   }
   //------------------------------------------------------------
   // <T>获取当前数组的指针对象。</T>
   MO_INLINE( TString{type}Ptr Ptr() ){
      return TString{type}Ptr(_capacity, &_length, _pMemory);
   }
   //------------------------------------------------------------
   // <T>获得末尾为0的指针。</T>
   MO_INLINE( TString{type}Ptr PtrZ() ){
      EnsureExtend(1);
      _pMemory[_length] = 0;
      return TString{type}Ptr(_capacity, &_length, _pMemory);
   }
   //------------------------------------------------------------
   // <T>计算当前集合的哈希值。</T>
   MO_INLINE( THashCode HashCode() const ){
      return RTypes<{char}>::MakeHashCode(_pMemory, _length);
   }
public:
   TInt InnerCalculateCapacity(TInt size);
   void InnerResize(TInt size, TBool copy, TBool extends);
public:
   void ForceSize(TInt size);
   void EnsureSize(TInt size);
{source_method}
};

//============================================================
// <T>字符串工具。</T>
//============================================================
class MO_CM_DECLARE TString{type} : public MString{type}{
public:
   //------------------------------------------------------------
   // <T>构造字符串工具。</T>
   TString{type}(){
   }
   //------------------------------------------------------------
   // <T>构造字符串对象。</T>
   TString{type}(TInt capacity){
      EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>构造字符串工具。</T>
   TString{type}({char}C* pValue, TInt length = -1){
      Assign(pValue, length);
   }
   //------------------------------------------------------------
   // <T>构造字符串工具。</T>
   TString{type}(const TString{type}PtrC& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造字符串工具。</T>
   TString{type}(const MString{type}& value){
      Assign(value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>构造字符串工具。</T>
   TString{type}(const TString{type}& value){
      Assign(value.MemoryC(), value.Length());
   }
public:
   //------------------------------------------------------------
   // <T>赋值操作符重载。</T>
   MO_INLINE( void operator=({char}C* pValue) ){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>赋值操作符重载。</T>
   MO_INLINE( void operator=(const TString{type}PtrC& ptr) ){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>赋值操作符重载。</T>
   MO_INLINE( void operator=(const TString{type}& value) ){
      Assign(value.MemoryC(), value.Length());
   }
public:
   //------------------------------------------------------------
   // <T>字符串对象和字符串相加。</T>
   //friend static TString{type} operator+(const TString{type}& left, const TString{type}& right){
   //   TString{type} value;
   //   value.Append(left);
   //   value.Append(right);
   //   return value;
   //}
   //------------------------------------------------------------
   // <T>字符串对象和字符串相加。</T>
   //friend static TString{type} operator+(const TString{type}& left, {char}C* pRight){
   //   TString{type} value;
   //   value.Append(left);
   //   value.Append(pRight);
   //   return value;
   //}
};

//============================================================
// <T>字符串对象。</T>
//============================================================
class MO_CM_DECLARE FString{type} :
      public FFree,
      public MString{type}{
public:
   //------------------------------------------------------------
   // <T>构造字符串对象。</T>
   FString{type}(){
   }
   //------------------------------------------------------------
   // <T>构造字符串对象。</T>
   FString{type}(TInt capacity){
      EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>构造字符串对象。</T>
   FString{type}({char}C* pValue, TInt length = -1){
      Assign(pValue, length);
   }
   //------------------------------------------------------------
   // <T>构造字符串工具。</T>
   FString{type}(const TString{type}PtrC& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造字符串工具。</T>
   FString{type}(const MString{type}& value){
      Assign(value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>构造字符串工具。</T>
   FString{type}(const FString{type}& value){
      Assign(value.MemoryC(), value.Length());
   }
public:
   //------------------------------------------------------------
   // <T>赋值操作符重载。</T>
   MO_INLINE( void operator=({char}C* pValue) ){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>赋值操作符重载。</T>
   MO_INLINE( void operator=(const TString{type}PtrC& ptr) ){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>赋值操作符重载。</T>
   MO_INLINE( void operator=(const FString{type}& value) ){
      Assign(value.MemoryC(), value.Length());
   }
};
//------------------------------------------------------------
typedef MO_CM_DECLARE TVectorIterator<FString{type}*> TString{type}sIterator;
typedef MO_CM_DECLARE FVector<FString{type}*> FString{type}Vector;

//============================================================
// <T>定长字符串工具。</T>
//
// @tools
// @author maocy
// @version 1.0.1
//============================================================
template <TInt S>
class TFixString{type}{
public:
   // @type 比较函数
   typedef TInt (*HComparer)(const {char}* pSource, const {char}* pTarget, TAny* pCondition);
   // @type 只读指针定义
   typedef TString{type}PtrC TStringPtrC;
   // @type 指针定义
   typedef TString{type}Ptr TStringPtr;
   // @type 只读迭代器定义
   typedef TString{type}IteratorC TIteratorC;
   // @type 迭代器定义
   typedef TString{type}Iterator TIterator;
protected:
   // @attribute 数据长度
   TInt _length;
   // @attribute 数据指针
   {char} _memory[S];
public:
   //------------------------------------------------------------
   // <T>构造定长字符串工具。</T>
   TFixString{type}(){
      // 重置变量
      _length = 0;
      _memory[0] = 0;
   }
   //------------------------------------------------------------
   // <T>构造定长字符串工具。</T>
   TFixString{type}({char}C* pValue, TInt length=-1){
      // 重置变量
      _length = 0;
      // 接收内容
      this->Assign(pValue, length);
   }
   //------------------------------------------------------------
   // <T>构造定长字符串工具。</T>
   TFixString{type}(const TString{type}PtrC& ptr){
      // 重置变量
      _length = 0;
      // 接收内容
      this->Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造定长字符串工具。</T>
   TFixString{type}(const TFixString{type}<S>& value){
      // 重置变量
      _length = 0;
      // 接收内容
      this->Assign(value.MemoryC(), value.Length());
   }
protected:
   //------------------------------------------------------------
   // <T>收集内存。</T>
   MO_INLINE( {char}* InnerAlloc(TInt size) ){
      MO_ASSERT(size <= S);
      return _memory;
   }
   //------------------------------------------------------------
   // <T>析构字符串基类。</T>
   MO_INLINE( void InnerFree({char}* pMemory) ){
   }
public:
   //------------------------------------------------------------
   // <T>保证内存大小，不保留以前内容。</T>
   void EnsureSize(TInt size){
      MO_ASSERT(size <= S);
   }
   //------------------------------------------------------------
   // <T>保证内存大小，保留以前内容。</T>
   void EnsureResize(TInt size){
      MO_ASSERT(size <= S);
   }
{source_public_2}
{source_type_2}
};

//============================================================
// <T>定义定长字符串。</T>
//============================================================
#define MO_BCD_TFIXSTRING{type}(C, P) \
class MO_CM_DECLARE C : public P{ \
public: \
   C(){ \
   } \
   C({char}C* valuePtr, TInt length=-1) : P(valuePtr, length){ \
   } \
   C(const TString{type}PtrC& ptr) : P(ptr){ \
   } \
   C(const C& value) : P(value){ \
   } \
public: \
   void operator=({char}C* valuePtr){ \
      this->Assign(valuePtr); \
   } \
   void operator=(const C& value){ \
      this->Assign(value.MemoryC(), value.Length()); \
   } \
   void operator=(const TString{type}PtrC& value){ \
      this->Assign(value.MemoryC(), value.Length()); \
   } \
};
//------------------------------------------------------------
MO_BCD_TFIXSTRING{type}(TFsInteger{type},  TFixString{type}<MO_FS_INTEGER_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsFloat{type},    TFixString{type}<MO_FS_FLOAT_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsDouble{type},   TFixString{type}<MO_FS_DOUBLE_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsTimeTick{type}, TFixString{type}<MO_FS_TIMETICK_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsCode{type},     TFixString{type}<MO_FS_CODE_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsName{type},     TFixString{type}<MO_FS_NAME_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsLabel{type},    TFixString{type}<MO_FS_LABEL_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsText{type},     TFixString{type}<MO_FS_TEXT_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsCommand{type},  TFixString{type}<MO_FS_COMMAND_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsSql{type},      TFixString{type}<MO_FS_SQL_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsFileName{type}, TFixString{type}<MO_FS_FILENAME_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsPath{type},     TFixString{type}<MO_FS_PATH_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsNote{type},     TFixString{type}<MO_FS_NOTE_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsLogger{type},   TFixString{type}<MO_FS_LOGGER_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsTrack{type},    TFixString{type}<MO_FS_TRACK_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsDump{type},     TFixString{type}<MO_FS_DUMP_LENGTH>);

//============================================================
// <T>字符串只读引用。</T>
//============================================================
class MO_CM_DECLARE TString{type}ReferC : public MString{type}{
public:
   //------------------------------------------------------------
   // <T>构造字符串只读引用。</T>
   TString{type}ReferC({char}C* pValue){
      _length = RTypeChar<{char}>::Length(pValue);
      _capacity = _length;
      _pMemory = MO_CAST_CONST(pValue, {char}*);
   }
   //------------------------------------------------------------
   // <T>构造字符串只读引用。</T>
   TString{type}ReferC(TString{type}PtrC ptr){
      _length = ptr.Length();
      _capacity = ptr.Length();
      _pMemory = MO_CAST_CONST(ptr.MemoryC(), {char}*);
   }
   //------------------------------------------------------------
   // <T>析构字符串只读引用。</T>
   ~TString{type}ReferC(){
      MO_CLEAR(_pMemory);
   }
};

//============================================================
// <T>字符串引用。</T>
//============================================================
class MO_CM_DECLARE TString{type}Refer : public MString{type}{
public:
   //------------------------------------------------------------
   // <T>构造字符串引用。</T>
   TString{type}Refer({char}* pValue){
      _length = RTypeChar<{char}>::Length(pValue);
      _capacity = _length;
      _pMemory = pValue;
   }
   //------------------------------------------------------------
   // <T>构造字符串引用。</T>
   TString{type}Refer({char}* pValue, TInt capacity){
      _length = RTypeChar<{char}>::Length(pValue);
      _capacity = capacity;
      _pMemory = pValue;
   }
   //------------------------------------------------------------
   // <T>构造字符串引用。</T>
   TString{type}Refer(TString{type}Ptr ptr){
      _length = ptr.Length();
      _capacity = ptr.Capacity();
      _pMemory = ptr.Memory();
   }
   //------------------------------------------------------------
   // <T>析构字符串只读引用。</T>
   ~TString{type}Refer(){
      MO_CLEAR(_pMemory);
   }
};

//============================================================
// <T>字符串分割器。</T>
//============================================================
template<TInt S>
class TFixStringToken{type}{
protected:
   TInt _count;
   TInt _results[S];
   {char} _memory[S];
public:
   //------------------------------------------------------------
   // <T>构造字符串分割器。</T>
   TFixStringToken{type}(){
      _count = 0;
   }
   //------------------------------------------------------------
   // <T>构造字符串分割器。</T>
   TFixStringToken{type}({char}C* pSource, {char} splitter){
      _count = 0;
      Split(pSource, splitter);
   }
   //------------------------------------------------------------
   // <T>构造字符串分割器。</T>
   TFixStringToken{type}({char}C* pSource, {char}C* pSplitter){
      _count = 0;
      Split(pSource, pSplitter);
   }
public:
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   TInt Count(){
      return _count;
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   const {char}* Get(TInt n){
      return &_memory[_results[n]];
   }
public:
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   void Split({char}C* pSource, {char} splitter){
      TString{type}ReferC value(pSource);
      TInt begin = 0;
      TInt length = value.Length();
      TInt count = 0;
      while(ETrue){
         TInt index = value.IndexOf(splitter, begin);
         if(ENotFound == index){
            break;
         }
         _results[_count++] = begin;
         for(TInt n = begin; n < index; n++){
            _memory[count++] = pSource[n];
         }
         _memory[count++] = '\0';
         begin = index + 1;
      }
      _results[_count++] = begin;
      for(TInt n = begin; n < length; n++){
         _memory[count++] = pSource[n];
      }
      _memory[count++] = '\0';
      MO_ASSERT(count <= S);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   void Split({char}C* pSource, {char}C* pSplitter){
      TInt lengthSource = RChar{type}s::Length(pSource);
      TInt lengthSplitter = RChar{type}s::Length(pSplitter);
      TInt begin = 0;
      TInt index = 0;
      TInt count = 0;
      for(TInt n = 0; n < lengthSource; n++){
         if(pSource[n] == pSplitter[0]){
            TBool result = ETrue;
            index = n;
            for(TInt m = 1; m < lengthSplitter; m++){
               if(pSource[n + m] != pSplitter[m]){
                  result = EFalse;
                  break;
               }
               result = ETrue;
            }
            if(ETrue == result){
               _results[_count++] = begin;
               for(TInt n = begin; n < index; n++){
                  _memory[count++] = pSource[n];
               }
               _memory[count++] = '\0';
               begin = index + lengthSplitter;
            }
         }
      }
      _results[_count++] = begin;
      for(TInt n = begin; n < lengthSource; n++){
         _memory[count++] = pSource[n];
      }
      _memory[count++] = '\0';
      MO_ASSERT(count <= S);
   }
};

//============================================================
// <T>定义字符串分割器。</T>
//============================================================
#define MO_BCD_TFIXSTRINGTOKEN{type}(C, P) \
class MO_CM_DECLARE C : public P{ \
public: \
   C(){ \
   } \
   C({char}C* pSource, {char} splitter) : P(pSource, splitter){ \
   } \
   C({char}C* pSource, {char}C* pSplitter) : P(pSource, pSplitter){ \
   } \
};
//------------------------------------------------------------
MO_BCD_TFIXSTRINGTOKEN{type}(TFsTokenText{type},   TFixStringToken{type}<MO_FS_TEXT_LENGTH>);
MO_BCD_TFIXSTRINGTOKEN{type}(TFsTokenNote{type},   TFixStringToken{type}<MO_FS_NOTE_LENGTH>);
MO_BCD_TFIXSTRINGTOKEN{type}(TFsTokenFormat{type}, TFixStringToken{type}<MO_FS_FORMAT_LENGTH>);

//============================================================
// <T>字符串集合基类。</T>
//============================================================
class MO_CM_DECLARE MString{type}s{
public:
   typedef TString{type} TString;
   typedef FString{type} FString;
   typedef FVector<FString*> FStringVector;
protected:
   FStringVector* _pStrings;
public:
   //------------------------------------------------------------
   // <T>构造字符串集合基类。</T>
   MString{type}s(){
      _pStrings = MO_CREATE(FStringVector);
   }
   //------------------------------------------------------------
   // <T>构造字符串集合基类。</T>
   MString{type}s(TInt capacity){
      _pStrings = MO_CREATE(FStringVector);
   }
   //------------------------------------------------------------
   // <T>构造字符串集合基类。</T>
   MString{type}s(const MString{type}s& strings){
      _pStrings = MO_CREATE(FStringVector);
   }
   //------------------------------------------------------------
   // <T>析构字符串集合基类。</T>
   ~MString{type}s(){
      TInt count = _pStrings->Count();
      for(TInt n = 0; n < count; n++){
         FString* pString = _pStrings->Get(n);
         MO_DELETE(pString);
      }
      MO_DELETE(_pStrings);
   }
public:
   //------------------------------------------------------------
   const {char}* operator[](TInt index) const{
      FString* pString = _pStrings->Get(index);
      return pString->MemoryC();
   }
   //------------------------------------------------------------
   void operator +=(const {char}* pValue){
   }
   //------------------------------------------------------------
   void operator -=(const {char}* pValue){
   }
public:
   //------------------------------------------------------------
   TBool IsEmpty() const{
      return _pStrings->IsEmpty();
   }
   //------------------------------------------------------------
   TInt Count() const{
      return _pStrings->IsEmpty();
   }
   //------------------------------------------------------------
   TBool Contains(const {char}* pValue) const{
      return EFalse;
   }
public:
   //------------------------------------------------------------
   const {char}* First() const{
      TInt count = _pStrings->Count();
      if(0 == count){
         return NULL;
      }
      FString* pString = _pStrings->Get(0);
      return pString->MemoryC();
   }
   //------------------------------------------------------------
   const {char}* Last() const{
      TInt count = _pStrings->Count();
      if(0 == count){
         return NULL;
      }
      FString* pString = _pStrings->Get(count - 1);
      return pString->MemoryC();
   }
   //------------------------------------------------------------
   TInt IndexOf(const {char}* pValue) const{
      TInt count = _pStrings->Count();
      for(TInt n = 0; n < count; n++){
         FString* pItem = _pStrings->Get(n);
         if(pItem->Equals(pValue)){
            return n;
         }
      }
      return ENotFound;
   }
   //------------------------------------------------------------
   const {char}* Get(TInt index) const{
      FString* pString = _pStrings->Get(index);
      return pString->MemoryC();
   }
   //------------------------------------------------------------
   void Set(TInt index, const {char}* pValue){
      FString* pString = _pStrings->Get(index);
      pString->Assign(pValue);
   }
public:
   //------------------------------------------------------------
   void Assign(const MString{type}s& values){
      _pStrings->Clear();
      TInt count = values.Count();
      for(TInt i = 0; i < count; i++){
         FString* pString = MO_CREATE(FString);
         pString->Assign(values[i]);
         _pStrings->Push(pString);
      }
   }
   //------------------------------------------------------------
   void Append(const MString{type}s& values){
      TInt count = values.Count();
      for(TInt n = 0; n < count; n++){
         const {char}* pValue = values.Get(n);
         Push(pValue);
      }
   }
   //------------------------------------------------------------
   // <T>将一个字符串按指定字符分割成多个字符串，并存入容器。</T>
   void AppendSplit(const {char}* pValue, {char} splitter){
      //TString{type}Refer value(pValue);
      //TInt begin = 0;
      //TInt index = value.IndexOf(splitter, begin);
      //while(ENotFound != index){
      //   Push(value.SubStrC(begin, index));
      //   begin = index + 1;
      //   index = value.IndexOf(splitter, begin);
      //}
   }
   //------------------------------------------------------------
   // <T>将一个字符串用指定字符串分割成多个字符串，并存入容器。</T>
   void AppendSplit(const {char}* pValue, const {char}* pSplitter){
      //TInt length = strlen(pSplitter);
      //TString{type}Refer value(pValue);
      // 上一个结束位置
      //TInt begin = 0 ;
      //TInt index = value.Find(pSplitter);
      //while(ENotFound != index){
      //   Push(value.SubStrC(begin, index));
      //   begin = index + length;
      //   index = value.Find(pSplitter, begin);
      //}
   }
   //------------------------------------------------------------
   void Push(const {char}* pValue){
      FString* pString = MO_CREATE(FString, pValue);
      _pStrings->Push(pString);
   }
   //------------------------------------------------------------
   void Push(const TString{type}PtrC& value){
      FString* pString = MO_CREATE(FString, value);
      _pStrings->Push(pString);
   }
   //------------------------------------------------------------
   void Delete(TInt index){
   }
   //------------------------------------------------------------
   void Remove(const {char}* pValue){
      //for(TInt n = 0; n < _count; n++){
      //   FString8* pString = _pStrings->Get(n);
      //   if(pString->Equals(pValue)){
      //      _pStrings->Delete(n);
      //      --_count;
      //      _pStrings->Push(pString);
      //   }
      //}
   }
   //------------------------------------------------------------
   void Remove(const MString{type}s* pStrings){
      TInt count = pStrings->Count();
      for(TInt n = 0; n < count; n++){
         const {char}* pValue = pStrings->Get(n);
         Remove(pValue);
      }
   }
public:
   //------------------------------------------------------------
   TString Join(TChar8 splitter){
      TString{type} result;
      TInt count = _pStrings->Count();
      for(TInt n = 0; n < count; n++){
         if(n){
            result.Append(splitter);
         }
         result.Append(_pStrings->Get(n)->MemoryC());
      }
      return result;
   }
   //------------------------------------------------------------
   TString Join(const {char}* pSplitter){
      TString{type} result;
      TInt count = _pStrings->Count();
      for(TInt n = 0; n < count; n++){
         if(n){
            result.Append(pSplitter);
         }
         result.Append(_pStrings->Get(n)->MemoryC());
      }
      return result;
   }
   //------------------------------------------------------------
   void Split(const {char}* pValue, {char} splitter){
      Clear();
      AppendSplit(pValue, splitter);
   }
   //------------------------------------------------------------
   void Split(const {char}* pValue, const {char}* pSplitter){
      Clear();
      AppendSplit(pValue, pSplitter);
   }
   //------------------------------------------------------------
   TString Pack(){
      //TInt valueLen, valueLenLen, byteCount = 0;
      //FString{type}* pString;
      //for(TInt n = 0; n < _count; n++){
      //   pString = _pStrings->Get(n);
      //   valueLen = pString->Length();
      //   valueLenLen = RInt::CountDigit(valueLen);
      //   if(pPack){
      //      {char} buffer[16];
      //      RRuntime::IntToString8(valueLenLen, buffer, 16);
      //      pPack[byteCount++] = buffer[0];
      //      RRuntime::IntToString8(valueLen, buffer, 16);
      //      MO_LIB_MEMCPY(&pPack[byteCount], length - byteCount, buffer, valueLenLen);
      //      byteCount += valueLenLen;
      //      MO_LIB_MEMCPY(&pPack[byteCount], length - byteCount, pString->MemoryC(), valueLen);
      //      byteCount += valueLen;
      //   }else{
      //      byteCount += (1 + valueLenLen + valueLen);
      //   }
      //}
      //if(pPack){
      //   pPack[byteCount] = 0;
      //}
      //return byteCount;
      return TString();
   }
   //------------------------------------------------------------
   void Unpack(const {char}* pPack){
      //TInt count = strlen(pPack);
      //TInt offset = 0;
      //while(offset < count){
      //   TInt valueLenLen = pPack[offset++] - 48;
      //   MO_ASSERT(valueLenLen > 0 && valueLenLen <= 9);
      //   {char} buffer[16];
      //   MO_LIB_MEMCPY(buffer, 16, pPack + offset, valueLenLen);
      //   offset += valueLenLen;
      //   buffer[valueLenLen] = 0;
      //   TInt valueLen = RInt::Parse8(buffer);
      //   FString{type}* pString = MO_CREATE(FString8,  pPack + offset, valueLen);
      //   offset += valueLen;
      //}
   }
   //------------------------------------------------------------
   void Clear(){
      _pStrings->Clear();
   }
};

//============================================================
// <T>字符串集合。</T>
//============================================================
class MO_CM_DECLARE TString{type}s : public MString{type}s{
public:
   //------------------------------------------------------------
   // <T>构造字符串集合。</T>
   TString{type}s(){
   }
   //------------------------------------------------------------
   // <T>构造字符串集合。</T>
   TString{type}s(TInt capacity) : MString{type}s(capacity){
   }
   //------------------------------------------------------------
   // <T>构造字符串集合。</T>
   TString{type}s(const MString{type}s& strings) : MString{type}s(strings){
   }
   //------------------------------------------------------------
   // <T>构造字符串集合。</T>
   TString{type}s(const TString{type}s& strings) : MString{type}s(strings){
   }
};

//============================================================
// <T>字符串集合。</T>
//============================================================
class MO_CM_DECLARE FString{type}s :
      public FFree,
      public MString{type}s{
public:
   //------------------------------------------------------------
   // <T>构造字符串集合。</T>
   FString{type}s(){
   }
   //------------------------------------------------------------
   // <T>构造字符串集合。</T>
   FString{type}s(TInt capacity) : MString{type}s(capacity){
   }
   //------------------------------------------------------------
   // <T>构造字符串集合。</T>
   FString{type}s(const MString{type}s& strings) : MString{type}s(strings){
   }
   //------------------------------------------------------------
   // <T>构造字符串集合。</T>
   FString{type}s(const FString{type}s& strings) : MString{type}s(strings){
   }
};

//============================================================
// <T>属性集合迭代器。</T>
//
// @tool
//============================================================
class MO_CM_DECLARE MProperties{type}IteratorC{
public:
   TInt _index;
   TInt _count;
   FString{type}s* _pNames;
   FString{type}s* _pValues;
public:
   //------------------------------------------------------------
   // <T>构造属性集合迭代器。</T>
   inline MProperties{type}IteratorC(){
   }
   //------------------------------------------------------------
   // <T>构造属性集合迭代器。</T>
   inline MProperties{type}IteratorC(const MProperties{type}IteratorC& iterator){
   }
   //------------------------------------------------------------
   // <T>构造属性集合迭代器。</T>
   inline MProperties{type}IteratorC(TInt count, FString{type}s* pNames, FString{type}s* pValues){
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   //inline const FString& operator *() const{
   //}
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   //inline const FString* operator->() const{
   //}
public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   inline TBool IsEmpty(){
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   inline TBool HasNext(){
      return ETrue;
   }
   //------------------------------------------------------------
   //<T>移动到下一个位置。</T>
   inline TBool Next(){
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   inline TBool HasPrior(){
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   inline TBool Prior(){
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>重置位置。</T>
   inline void Reset(){
   }
public:
   //------------------------------------------------------------
   // <T>判断名称是否相同。</T>
   MO_INLINE( TBool IsName(const {char}* pName) const ){
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>判断内容是否相同。</T>
   MO_INLINE( TBool IsValue(const {char}* pValue) const ){
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据名称。</T>
   MO_INLINE( const {char}* Name() const ){
      return NULL;
   }
   //------------------------------------------------------------
   // 设置当前位置的数据内容
   MO_INLINE( void SetName(const {char}* pName) const ){
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const {char}* Value() const ){
      return NULL;
   }
   //------------------------------------------------------------
   // 设置当前位置的数据内容
   MO_INLINE( void SetValue(const {char}* pValue) const ){
   }
};

//============================================================
// <T>属性表节点</T>
//
// @struct
//============================================================
template <typename V>
struct SDictionary{type}Entry{
public:
   THashCode hash;
   SDictionary{type}Entry* priorPtr;
   SDictionary{type}Entry* nextPtr;
   SDictionary{type}Entry* linkPtr;
   FString{type}* namePtr;
   V value;
public:
   //------------------------------------------------------------
   // <T>构造属性表节点</T>
   SDictionary{type}Entry(){
      hash = 0;
      MO_CLEAR(priorPtr);
      MO_CLEAR(nextPtr);
      MO_CLEAR(linkPtr);
      MO_CLEAR(namePtr);
   }
   //------------------------------------------------------------
   // <T>析构属性表节点</T>
   ~SDictionary{type}Entry(){
      MO_DELETE(namePtr);
   }
public:
   //------------------------------------------------------------
   // <T>判断名称是否相等。</T>
   MO_INLINE( TBool IsName(const {char}* pName) const ){
      MO_ASSERT(pName);
      MO_ASSERT(this->namePtr);
      return this->namePtr->Equals(pName);
   }
   //------------------------------------------------------------
   // <T>判断不区分大小写名称是否相等。</T>
   MO_INLINE( TBool IsNameIgnoreCase(const {char}* pName) const ){
      MO_ASSERT(pName);
      MO_ASSERT(this->namePtr);
      return this->namePtr->EqualsIgnoreCase(pName);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据名称。</T>
   MO_INLINE( const {char}* Name() const ){
      if(NULL == namePtr){
         return NULL;
      }
      return namePtr->MemoryC();
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( V Value() ){
      return value;
   }
public:
   //------------------------------------------------------------
   // <T>设置名称。</T>
   void SetName(const {char}* pName){
      if(NULL == namePtr){
         namePtr = MO_CREATE(FString{type});
      }
      namePtr->Assign(pName);
   }
};

//============================================================
// <T>只读属性迭代器。</T>
//
// @tool
//============================================================
template <typename V>
class TDictionary{type}IteratorC{
public:
   typedef SDictionary{type}Entry<V> SEntry;
protected:
   // @attribute 开始入口节点
   const SEntry* _pFirst;
   // @attribute 结束入口节点
   const SEntry* _pLast;
   // @attribute 当前入口节点
   const SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // <T>构造只读属性迭代器。</T>
   TDictionary{type}IteratorC(){
   }
   //------------------------------------------------------------
   // <T>构造只读属性迭代器。</T>
   TDictionary{type}IteratorC(const TDictionary{type}IteratorC& iterator){
      _pFirst = iterator._pFirst;
      _pLast = iterator._pLast;
      _pEntry = iterator._pEntry;
   }
   //------------------------------------------------------------
   // <T>构造只读属性迭代器。</T>
   TDictionary{type}IteratorC(SEntry* pFirst, SEntry* pLast, SEntry* pEntry = NULL){
      _pFirst = pFirst;
      _pLast = pLast;
      _pEntry = pEntry;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const V& operator *() const ){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const V operator->() const ){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
public:
   //------------------------------------------------------------
   // <T>判断名称是否相同。</T>
   MO_INLINE( TBool IsName(const {char}* pName) const ){
      MO_ASSERT(_pEntry);
      return _pEntry->IsName(pName);
   }
   //------------------------------------------------------------
   // <T>判断内容是否相同。</T>
   MO_INLINE( TBool IsValue(V value) const ){
      MO_ASSERT(_pEntry);
      return (_pEntry->value == value);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据名称。</T>
   MO_INLINE( const {char}* Name() const ){
      MO_ASSERT(_pEntry);
      return _pEntry->namePtr->MemoryC();
   }
   //------------------------------------------------------------
   // <T>获得当前位置的只读数据内容。</T>
   MO_INLINE( V Value() ){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
{source_entry_iterator}
};

//============================================================
// <T>属性迭代器。</T>
//
// @tool
//============================================================
template <typename V>
class TDictionary{type}Iterator{
public:
   typedef SDictionary{type}Entry<V> SEntry;
protected:
   // @attribute 开始入口节点
   const SEntry* _pFirst;
   // @attribute 结束入口节点
   const SEntry* _pLast;
   // @attribute 当前入口节点
   const SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // <T>构造属性迭代器。</T>
   TDictionary{type}Iterator(){
   }
   //------------------------------------------------------------
   // <T>构造属性迭代器。</T>
   TDictionary{type}Iterator(const TDictionary{type}Iterator& iterator){
      _pFirst = iterator._pFirst;
      _pLast = iterator._pLast;
      _pEntry = iterator._pEntry;
   }
   //------------------------------------------------------------
   // <T>构造属性迭代器。</T>
   TDictionary{type}Iterator(SEntry* pFirst, SEntry* pLast, SEntry* pEntry = NULL){
      _pFirst = pFirst;
      _pLast = pLast;
      _pEntry = pEntry;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( V& operator *() ){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( V operator->() ){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
public:
   //------------------------------------------------------------
   // <T>判断名称是否相同。</T>
   MO_INLINE( TBool IsName(const {char}* pName) const ){
      MO_ASSERT(_pEntry);
      return _pEntry->IsName(pName);
   }
   //------------------------------------------------------------
   // <T>判断内容是否相同。</T>
   MO_INLINE( TBool IsValue(V value) const ){
      MO_ASSERT(_pEntry);
      return (_pEntry->value == value);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据名称。</T>
   MO_INLINE( const {char}* Name() const ){
      MO_ASSERT(_pEntry);
      return _pEntry->namePtr->MemoryC();
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( V Value() ){
      MO_ASSERT(_pEntry);
      return _pEntry->value;
   }
   //------------------------------------------------------------
   // <T>设置当前位置的数据内容。</T>
   MO_INLINE( void SetValue(V value) ){
      MO_ASSERT(_pEntry);
      _pEntry->value = value;
   }
{source_entry_iterator}
};

//============================================================
// <T>属性表。</T>
// <P>键值和内容都为字符串的哈希表。</T>
//
// @manager
// @history 091207 MAOCY 创建
//============================================================
template <typename V>
class MDictionary{type}{
public:
   typedef SDictionary{type}Entry<V> SEntry;
   typedef TDictionary{type}IteratorC<V> TIteratorC;
   typedef TDictionary{type}Iterator<V> TIterator;
protected:
   // @attribute 个数
   TInt _count;
   // @attribute 首指针
   SEntry* _pFirst;
   // @attribute 尾指针
   SEntry* _pLast;
   // @attribute 未使用指针
   SEntry* _pFree;
   // @attribute 入口总数
   TInt _entryCount;
   // @attribute 入口集合
   SEntry** _ppEntries;
public:
   //------------------------------------------------------------
   // <T>构造哈希表对象。</T>
   MDictionary{type}(){
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
      MO_CLEAR(_pFree);
      _entryCount = 0;
      MO_CLEAR(_ppEntries);
   }
   //------------------------------------------------------------
   // <T>析构哈希表对象。</T>
   ~MDictionary{type}(){
      Release();
   }
public:
   //------------------------------------------------------------
   // <T>创建一个入口对象。</T>
   MO_INLINE( SEntry* InnerEntryCreate() ){
      return MO_MEM_CREATE(SEntry);
   }
   //------------------------------------------------------------
   // <T>删除一个入口对象。</T>
   MO_INLINE( void InnerEntryDelete(SEntry* pEntry) ){
      MO_MEM_DELETE(pEntry);
   }
   //------------------------------------------------------------
   // <T>创建入口对象集合。</T>
   MO_INLINE( SEntry** InnerEntriesAlloc(TInt size) ){
      return (SEntry**)MO_MEM_ALLOC(sizeof(SEntry*) * size);
   }
   //------------------------------------------------------------
   // <T>删除入口对象集合。</T>
   MO_INLINE( void InnerEntriesFree(SEntry** ppEntries) ){
      MO_MEM_FREE(ppEntries);
   }
{source_entry_list}
protected:
   //------------------------------------------------------------
   // <T>查找指定名称的索引位置。</T>
   MO_INLINE( SEntry* InnerFindByName(const {char}* pName) const ){
      // 数据存在时
      if(_count > 0){
         THashCode hash = RChar{type}::MakeHashCode(pName);
         TInt index = hash % _entryCount;
         // 查找名称的索引位置
         SEntry* pEntry = _ppEntries[index];
         while(NULL != pEntry){
            if(pEntry->hash == hash){
               if(pEntry->IsName(pName)){
                  return pEntry;
               }
            }
            pEntry = pEntry->linkPtr;
         }
      }
      // 未找到时返回结果
      return NULL;
   }
   //------------------------------------------------------------
   // <T>查找指定名称的索引位置。</T>
   MO_INLINE( SEntry* InnerFindByValue(const V value) const ){
      // 数据存在时
      if(_count > 0){
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->value == value){
               return pEntry;
            }
            pEntry = pEntry->nextPtr;
         }
      }
      // 未找到时返回结果
      return NULL;
   }
public:
   //------------------------------------------------------------
   // <T>查找指定名称获得数据内容。</T>
   const V operator[](const {char}* pName){
      SEntry* pEntry = InnerFindByName(pName);
      MO_ASSERT(pEntry);
      return pEntry->Value();
   }
public:
   //------------------------------------------------------------
   // <T>当前哈希集合对象是否为空。</T>
   MO_INLINE( TBool IsEmpty() const ){
      return (0 == _count);
   }
   //------------------------------------------------------------
   // <T>获得数据个数。</T>
   MO_INLINE( TInt Count() const ){
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得首位置的只读链表迭代器。</T>
   MO_INLINE( TIteratorC IteratorC() ){
      return TIteratorC(_pFirst, _pLast);
   }
   //------------------------------------------------------------
   // <T>获得首位置的链表迭代器。</T>
   MO_INLINE( TIterator Iterator() ){
      return TIterator(_pFirst, _pLast);
   }
public:
   //------------------------------------------------------------
   // <T>判断指定名称是否存在。</T>
   TBool Contains(const {char}* pName) const{
      return (NULL != InnerFindByName(pName));
   }
   //------------------------------------------------------------
   // <T>判断指定内容是否存在。</T>
   TBool ContainsValue(const V value) const{
      return (NULL != InnerFindByValue(value));
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   const V Find(const {char}* pName) const{
      SEntry* pEntry = InnerFindByName(pName);
      if(NULL == pEntry){
         return (V)NULL;
      }
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   const V GetC(const {char}* pName) const{
      SEntry* pEntry = InnerFindByName(pName);
      MO_ASSERT(pEntry);
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   const V GetC(const {char}* pName, const V value) const{
      SEntry* pEntry = InnerFindByName(pName);
      if(NULL == pEntry){
         return value;
      }
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   V& Get(const {char}* pName){
      SEntry* pEntry = InnerFindByName(pName);
      MO_ASSERT(pEntry);
      return pEntry->value;
   }
   //------------------------------------------------------------
   // <T>获得指定数据的首个名称。</T>
   const {char}* SearchFirst(const V value) const{
      SEntry* pEntry = InnerFindByValue(value);
      MO_ASSERT(pEntry);
      return pEntry->namePtr->MemoryC();
   }
   //------------------------------------------------------------
   // <T>获得指定数据的首个名称。</T>
   const {char}* SearchFirst(const V value, const {char}* pName) const{
      SEntry* pEntry = InnerFindByValue(value);
      if(NULL == pEntry){
         return pName;
      }
      return pEntry->namePtr->MemoryC();
   }
   //------------------------------------------------------------
   // <T>获得指定数据的个数。</T>
   TInt SearchCount(const V value) const{
      TInt count = 0;
      if(_count > 0){
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->value == value){
               count++;
            }
            pEntry = pEntry->nextPtr;
         }
      }
      return count;
   }
   //------------------------------------------------------------
   // <T>获得指定数据的名称集合。</T>
   TInt Search(const V value, const {char}** ppNames, TInt capacity) const{
      TInt count = 0;
      if(_count > 0){
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->value == value){
               MO_ASSERT(count < capacity);
               ppNames[count++] = pEntry->namePtr->MemoryC();
            }
            pEntry = pEntry->nextPtr;
         }
      }
      return count;
   }
public:
   //------------------------------------------------------------
   // <T>确保当前对象可以容纳指定大小的数据。</T>
   // 当哈希表是旧表的8倍时开始扩充，扩大2倍。</P>
   void EnsureSize(TInt size){
      if(NULL == _ppEntries) {
         // 生成哈希表
         _entryCount = MO_LG_MAX(size, MO_OBJECT_CAPACITY);
         _ppEntries = InnerEntriesAlloc(_entryCount);
         RTypes<SEntry*>::Clear(_ppEntries, _entryCount);
      } else if (size > (_entryCount << 3)) {
         // 释放旧节点内存
         InnerEntriesFree(_ppEntries);
         // 扩充内存时处理
         TInt capacity = _entryCount + ((MO_LG_MAX(_entryCount, size)) >> 1);
         // 当总数大于节点列表长度8倍时，重新扩充节点列表
         SEntry** ppEntries = InnerEntriesAlloc(capacity);
         RTypes<SEntry*>::Clear(ppEntries, capacity);
         // 循环取出旧的节点列表内容，移到新的节点列表上
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            // 将当前节点存储到新建的节点列表上去
            TInt index = pEntry->hash % capacity;
            pEntry->linkPtr = ppEntries[index];
            ppEntries[index] = pEntry;
            // 获得保存的下一个节点指针
            pEntry = pEntry->nextPtr;
         }
         // 保存新的节点列表
         _ppEntries = ppEntries;
         _entryCount = capacity;
      }
   }
   //------------------------------------------------------------
   // <T>接收源哈希表内的全部数据。</T>
   void Assign(const MDictionary{type}<V>& values){
      Clear();
      Append(values);
   }
   //------------------------------------------------------------
   // <T>追加源哈希表内的全部数据。</T>
   void Append(const MDictionary{type}<V>& values){
      SEntry* pEntry = values._pFirst;
      while(NULL != pEntry){
         Set(pEntry->namePtr->MemoryC(), pEntry->value);
         pEntry = pEntry->nextPtr;
      }
   }
   //------------------------------------------------------------
   // <T>根据名称设置数据。</T>
   void Set(const {char}* pName, V value){
      THashCode hash = RChar{type}::MakeHashCode(pName);
      // 查找数据出现的位置
      if(_count > 0){
         TInt index = hash % _entryCount;
         SEntry* pEntry = _ppEntries[index];
         while(NULL != pEntry) {
            if(pEntry->hash == hash){
               if(pEntry->IsName(pName)){
                  pEntry->value = value;
                  return;
               }
            }
            pEntry = pEntry->linkPtr;
         }
      }
      // 检查是否需要扩展内存
      EnsureSize(_count + 1);
      // 如果名称不存在，创建新入口对象
      TInt allocIndex = hash % _entryCount;
      SEntry* pAlloc = InnerEntryAlloc();
      pAlloc->hash = hash;
      pAlloc->linkPtr = _ppEntries[allocIndex];
      pAlloc->SetName(pName);
      pAlloc->value = value;
      // 追加到链表尾部
      _ppEntries[allocIndex] = pAlloc;
      InnerEntryPush(pAlloc);
   }
   //------------------------------------------------------------
   // <T>移除指定名称的数据。</T>
   V Remove(const {char}* pName){
      V value = NULL;
      // 计算哈希值
      THashCode hash = RChar{type}::MakeHashCode(pName);
      TInt index = hash % _count;
      // 查找数据出现的位置
      SEntry* pPrior = _ppEntries[index];
      SEntry* pEntry = pPrior;
      while(NULL != pEntry){
         if(pEntry->hash == hash){
            if(pEntry->IsName(pName)){
               // 设置输出内容
               value = pEntry->value;
               // 从链表上移除节点
               if(pEntry == pPrior){
                  // 当前对象是第一个对象时
                  _ppEntries[index] = pEntry->linkPtr;
               }else{
                  // 当前对象不是第一个对象时
                  pPrior->linkPtr = pEntry->linkPtr;
               }
               // 删除当前节点
               InnerEntryDelete(pEntry);
               break;
            }
         }
         pPrior = pEntry;
         pEntry = pEntry->linkPtr;
      }
      return value;
   }
   //------------------------------------------------------------
   // <T>清除所有资源。</T>
   void Clear(){
      // 清空链表
      InnerClear();
      // 清空节点数组
      RTypes<SEntry*>::Clear(_ppEntries, _entryCount);
   }
   //------------------------------------------------------------
   // <T>释放所有资源。</T>
   void Release(){
      // 释放链表
      InnerRelease();
      // 释放节点数组
      InnerEntriesFree(_ppEntries);
   }
public:
   //------------------------------------------------------------
   // <T>获得对象运行时信息。</T>
   void Dump(MString{type} dump) const{
      //dump.Append(TC("MDictionary{type}{"));
      //dump.AppendFormat("%d", _count);
      //if(_count > 0){
      //   SEntry* pEntry = _pFirst;
      //   while(NULL != pEntry){
      //      //dump.Append(*pEntry->pName);
      //      dump.Append(TC("="));
      //      //dump.Append(*pEntry->pValue);
      //      // 查找下一个
      //      pEntry = pEntry->nextPtr;
      //      if(NULL != pEntry){
      //         dump.Append(',');
      //      }
      //   }
      //}
   }
};

//============================================================
// <T>属性表对象。</T>
//============================================================
template <class V>
class TDictionary{type} : public MDictionary{type}<V>{
public:
   //------------------------------------------------------------
   // <T>创建属性表对象。</T>
   TDictionary{type}(){
   }
   //------------------------------------------------------------
   // <T>创建属性表对象。</T>
   TDictionary{type}(TInt capacity){
      this->EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   TDictionary{type}(const MDictionary{type}<V>& values){
      this->Append(values);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   TDictionary{type}(const TDictionary{type}<V>& values){
      this->Append(values);
   }
};

//============================================================
// <T>属性表对象。</T>
//============================================================
template <class V>
class FDictionary{type} :
      public FFree,
      public MDictionary{type}<V>{
public:
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FDictionary{type}(){
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FDictionary{type}(TInt capacity){
      this->EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FDictionary{type}(const MDictionary{type}<V>& values){
      this->Append(values);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FDictionary{type}(const FDictionary{type}<V>& values){
      this->Append(values);
   }
};

//============================================================
// <T>属性表节点</T>
//
// @struct
//============================================================
struct SAttributes{type}Entry{
public:
   THashCode hash;
   SAttributes{type}Entry* linkPtr;
   SAttributes{type}Entry* priorPtr;
   SAttributes{type}Entry* nextPtr;
   MString{type}* namePtr;
   MString{type}* valuePtr;
public:
   //------------------------------------------------------------
   // <T>构造属性表节点</T>
   SAttributes{type}Entry(){
      hash = 0;
      MO_CLEAR(linkPtr);
      MO_CLEAR(priorPtr);
      MO_CLEAR(nextPtr);
      MO_CLEAR(namePtr);
      MO_CLEAR(valuePtr);
   }
   //------------------------------------------------------------
   // <T>析构属性表节点</T>
   ~SAttributes{type}Entry(){
      MO_DELETE(namePtr);
      MO_DELETE(valuePtr);
   }
public:
   //------------------------------------------------------------
   // <T>生成索引。</T>
   MO_INLINE( TInt MakeIndex() ){
      return (TInt)hash;
   }
public:
   //------------------------------------------------------------
   // <T>判断名称是否相等。</T>
   MO_INLINE( TBool IsName(const {char}* pName) const ){
      MO_ASSERT(pName);
      if(NULL == namePtr){
         return EFalse;
      }
      return namePtr->Equals(pName);
   }
   //------------------------------------------------------------
   // <T>判断不区分大小写名称是否相等。</T>
   MO_INLINE( TBool IsNameIgnoreCase(const {char}* pName) const ){
      MO_ASSERT(pName);
      if(NULL == namePtr){
         return EFalse;
      }
      return namePtr->EqualsIgnoreCase(pName);
   }
   //------------------------------------------------------------
   // <T>判断内容是否相等。</T>
   MO_INLINE( TBool IsValue(const {char}* pValue) const ){
      MO_ASSERT(pValue);
      if(NULL == valuePtr){
         return EFalse;
      }
      return valuePtr->Equals(pValue);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据名称。</T>
   MO_INLINE( const {char}* Name() const ){
      if(NULL == namePtr){
         return NULL;
      }
      return namePtr->MemoryC();
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const {char}* Value() const ){
      if(NULL == valuePtr){
         return NULL;
      }
      return valuePtr->MemoryC();
   }
public:
   //------------------------------------------------------------
   // <T>设置名称。</T>
   void SetName(const {char}* pName){
      if(NULL == namePtr){
         namePtr = MO_CREATE(FString{type});
      }
      namePtr->Assign(pName);
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   void SetValue(const {char}* pValue){
      if(NULL == valuePtr){
         valuePtr = MO_CREATE(FString{type});
      }
      valuePtr->Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>设置名称和内容。</T>
   void Set(const {char}* pName, const {char}* pValue){
      SetName(pName);
      SetValue(pValue);
   }
};

//============================================================
// <T>只读属性迭代器。</T>
//
// @tool
//============================================================
class TAttributes{type}IteratorC{
public:
   typedef SAttributes{type}Entry SEntry;
protected:
   // @attribute 开始入口节点
   const SEntry* _pFirst;
   // @attribute 结束入口节点
   const SEntry* _pLast;
   // @attribute 当前入口节点
   const SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // <T>构造只读属性迭代器。</T>
   TAttributes{type}IteratorC(){
   }
   //------------------------------------------------------------
   // <T>构造只读属性迭代器。</T>
   TAttributes{type}IteratorC(const TAttributes{type}IteratorC& iterator){
      _pFirst = iterator._pFirst;
      _pLast = iterator._pLast;
      _pEntry = iterator._pEntry;
   }
   //------------------------------------------------------------
   // <T>构造只读属性迭代器。</T>
   TAttributes{type}IteratorC(const SEntry* pFirst, const SEntry* pLast, const SEntry* pEntry = NULL){
      _pFirst = pFirst;
      _pLast = pLast;
      _pEntry = pEntry;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const TChar{type}* operator->() const ){
      MO_ASSERT(_pEntry);
      return _pEntry->Value();
   }
public:
   //------------------------------------------------------------
   // <T>判断名称是否相同。</T>
   MO_INLINE( TBool IsName(const {char}* pName) const ){
      MO_ASSERT(_pEntry);
      return _pEntry->IsName(pName);
   }
   //------------------------------------------------------------
   // <T>判断内容是否相同。</T>
   MO_INLINE( TBool IsValue(const {char}* pValue) const ){
      MO_ASSERT(_pEntry);
      return _pEntry->IsValue(pValue);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据名称。</T>
   MO_INLINE( const {char}* Name() const ){
      MO_ASSERT(_pEntry);
      return _pEntry->Name();
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const {char}* Value() const ){
      MO_ASSERT(_pEntry);
      return _pEntry->Value();
   }
{source_entry_iterator}
};

//============================================================
// <T>属性迭代器。</T>
//
// @tool
//============================================================
class TAttributes{type}Iterator{
public:
   typedef SAttributes{type}Entry SEntry;
protected:
   // @attribute 开始入口节点
   SEntry* _pFirst;
   // @attribute 结束入口节点
   SEntry* _pLast;
   // @attribute 当前入口节点
   SEntry* _pEntry;
public:
   //------------------------------------------------------------
   // <T>构造属性迭代器。</T>
   TAttributes{type}Iterator(){
   }
   //------------------------------------------------------------
   // <T>构造属性迭代器。</T>
   TAttributes{type}Iterator(const TAttributes{type}Iterator& iterator){
      _pFirst = iterator._pFirst;
      _pLast = iterator._pLast;
      _pEntry = iterator._pEntry;
   }
   //------------------------------------------------------------
   // <T>构造属性迭代器。</T>
   TAttributes{type}Iterator(SEntry* pFirst, SEntry* pLast, SEntry* pEntry = NULL){
      _pFirst = pFirst;
      _pLast = pLast;
      _pEntry = pEntry;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const TChar{type}* operator->() const ){
      MO_ASSERT(_pEntry);
      return _pEntry->Value();
   }
public:
   //------------------------------------------------------------
   // <T>判断名称是否相同。</T>
   MO_INLINE( TBool IsName(const {char}* pName) const ){
      MO_ASSERT(_pEntry);
      return _pEntry->IsName(pName);
   }
   //------------------------------------------------------------
   // <T>判断内容是否相同。</T>
   MO_INLINE( TBool IsValue(const {char}* pValue) const ){
      MO_ASSERT(_pEntry);
      return _pEntry->IsValue(pValue);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据名称。</T>
   MO_INLINE( const {char}* Name() const ){
      MO_ASSERT(_pEntry);
      return _pEntry->Name();
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const {char}* Value() const ){
      MO_ASSERT(_pEntry);
      return _pEntry->Value();
   }
{source_entry_iterator}
};

//============================================================
// <T>属性表。</T>
// <P>键值和内容都为字符串的哈希表。</T>
//
// @manager
// @history 091207 MAOCY 创建
//============================================================
class MO_CM_DECLARE MAttributes{type}{
public:
   // @type 比较函数
   typedef TInt (*HComparer)(const {char}* pSource, const {char}* pTarget, TAny* pCondition);
   // @type 入口类型
   typedef SAttributes{type}Entry SEntry;
   // @type 只读迭代器
   typedef TAttributes{type}IteratorC TIteratorC;
   // @type 迭代器
   typedef TAttributes{type}Iterator TIterator;
protected:
   // @attribute 个数
   TInt _count;
   // @attribute 首指针
   SEntry* _pFirst;
   // @attribute 尾指针
   SEntry* _pLast;
   // @attribute 未使用指针
   SEntry* _pFree;
   // @attribute 入口总数
   TInt _entryCount;
   // @attribute 入口集合
   SEntry** _ppEntries;
public:
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   MAttributes{type}(){
      _count = 0;
      MO_CLEAR(_pFirst);
      MO_CLEAR(_pLast);
      MO_CLEAR(_pFree);
      _entryCount = 0;
      MO_CLEAR(_ppEntries);
   }
   //------------------------------------------------------------
   // <T>析构属性表。</T>
   ~MAttributes{type}(){
      InnerRelease();
   }
public:
   //------------------------------------------------------------
   // <T>创建一个入口对象。</T>
   MO_INLINE( SEntry* InnerEntryCreate() ){
      return MO_MEM_CREATE(SEntry);
   }
   //------------------------------------------------------------
   // <T>删除一个入口对象。</T>
   MO_INLINE( void InnerEntryDelete(SEntry* pEntry) ){
      MO_MEM_DELETE(pEntry);
   }
   //------------------------------------------------------------
   // <T>创建入口对象集合。</T>
   MO_INLINE( SEntry** InnerEntriesAlloc(TInt size) ){
      return (SEntry**)MO_MEM_ALLOC(sizeof(SEntry*) * size);
   }
   //------------------------------------------------------------
   // <T>删除入口对象集合。</T>
   MO_INLINE( void InnerEntriesFree(SEntry** ppEntries) ){
      MO_MEM_FREE(ppEntries);
   }
{source_entry_list}
protected:
   //------------------------------------------------------------
   // <T>查找指定名称的索引位置。</T>
   MO_INLINE( SEntry* InnerFindByName(const {char}* pName) const ){
      // 数据存在时
      if(_count > 0){
      THashCode hash = RChar{type}::MakeHashCode(pName);
         TInt index = hash % _entryCount;
         // 查找名称的索引位置
         SEntry* pEntry = _ppEntries[index];
         while(NULL != pEntry){
            if(pEntry->hash == hash){
               if(pEntry->IsName(pName)){
                  return pEntry;
               }
            }
            pEntry = pEntry->linkPtr;
         }
      }
      // 未找到时返回结果
      return NULL;
   }
   //------------------------------------------------------------
   // <T>查找指定名称的索引位置。</T>
   MO_INLINE( SEntry* InnerFindByValue(const {char}* pValue) const ){
      // 数据存在时
      if(_count > 0){
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            if(pEntry->IsValue(pValue)){
               return pEntry;
            }
            pEntry = pEntry->nextPtr;
         }
      }
      // 未找到时返回结果
      return NULL;
   }
public:
   //------------------------------------------------------------
   // <T>查找指定名称获得数据内容。</T>
   const {char}* operator[](const {char}* pName){
      SEntry* pEntry = InnerFindByName(pName);
      MO_ASSERT(pEntry);
      return pEntry->Value();
   }
public:
   //------------------------------------------------------------
   // <T>当前哈希集合对象是否为空。</T>
   MO_INLINE( TBool IsEmpty() const ){
      return (0 == _count);
   }
   //------------------------------------------------------------
   // <T>获得数据个数。</T>
   MO_INLINE( TInt Count() const ){
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得只读迭代器。</T>
   MO_INLINE( TIteratorC IteratorC() ){
      return TIteratorC(_pFirst, _pLast, NULL);
   }
   //------------------------------------------------------------
   // <T>获得迭代器。</T>
   MO_INLINE( TIterator Iterator() ){
      return TIterator(_pFirst, _pLast, NULL);
   }
public:
   //------------------------------------------------------------
   // <T>判断指定名称是否存在。</T>
   TBool Contains(const {char}* pName) const{
      return (NULL != InnerFindByName(pName));
   }
   //------------------------------------------------------------
   // <T>判断指定内容是否存在。</T>
   TBool ContainsValue(const {char}* pValue) const{
      return (NULL != InnerFindByValue(pValue));
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   const {char}* Find(const {char}* pName) const{
      SEntry* pEntry = InnerFindByName(pName);
      return (NULL != pEntry) ? pEntry->Value() : NULL;
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   const {char}* Get(const {char}* pName) const{
      SEntry* pEntry = InnerFindByName(pName);
      MO_ASSERT(pEntry);
      return pEntry->Value();
   }
   //------------------------------------------------------------
   // <T>获得指定名称的数据。</T>
   const {char}* Get(const {char}* pName, const {char}* pDefault) const{
      SEntry* pEntry = InnerFindByName(pName);
      if(NULL == pEntry){
         return pDefault;
      }
      return pEntry->Value();
   }
   //------------------------------------------------------------
   // <T>根据名称设置数据。</T>
   void Set(const {char}* pName, const {char}* pValue){
      THashCode hash = RChar{type}::MakeHashCode(pName);
      // 查找数据出现的位置
      if(_count > 0){
         TInt index = hash % _entryCount;
         SEntry* pEntry = _ppEntries[index];
         while(NULL != pEntry) {
            if(pEntry->hash == hash){
               if(pEntry->IsName(pName)){
                  pEntry->SetValue(pValue);
                  return;
               }
            }
            pEntry = pEntry->linkPtr;
         }
      }
      // 检查是否需要扩展内存
      EnsureSize(_count + 1);
      // 如果名称不存在，创建新入口对象
      TInt allocIndex = hash % _entryCount;
      SEntry* pAlloc = InnerEntryAlloc();
      pAlloc->hash = hash;
      pAlloc->linkPtr = _ppEntries[allocIndex];
      pAlloc->Set(pName, pValue);
      // 追加到链表尾部
      _ppEntries[allocIndex] = pAlloc;
      InnerEntryPush(pAlloc);
   }
   //------------------------------------------------------------
   // <T>获得指定数据的名称。</T>
   const {char}* Search(const {char}* pValue) const{
      SEntry* pEntry = InnerFindByValue(pValue);
      MO_ASSERT(pEntry);
      return pEntry->Name();
   }
   //------------------------------------------------------------
   // <T>获得指定数据的名称。</T>
   const {char}* Search(const {char}* pValue, const {char}* pDefault) const{
      SEntry* pEntry = InnerFindByValue(pValue);
      if(NULL == pEntry){
         return NULL;
      }
      return pEntry->Name();
   }
public:
   //------------------------------------------------------------
   // <T>确保当前对象可以容纳指定大小的数据。</T>
   // 当哈希表是旧表的8倍时开始扩充，扩大2倍。</P>
   void EnsureSize(TInt size = MO_OBJECT_CAPACITY){
      if(NULL == _ppEntries) {
         // 生成哈希表
         _entryCount = MO_LG_MAX(size, MO_OBJECT_CAPACITY);
         _ppEntries = InnerEntriesAlloc(_entryCount);
         RTypes<SEntry*>::Clear(_ppEntries, _entryCount);
      } else if (size > (_entryCount << 3)) {
         // 释放旧节点内存
         InnerEntriesFree(_ppEntries);
         // 扩充内存时处理
         TInt capacity = _entryCount + ((MO_LG_MAX(_entryCount, size)) >> 1);
         // 当总数大于节点列表长度8倍时，重新扩充节点列表
         SEntry** ppEntries = InnerEntriesAlloc(capacity);
         RTypes<SEntry*>::Clear(ppEntries, capacity);
         // 循环取出旧的节点列表内容，移到新的节点列表上
         SEntry* pEntry = _pFirst;
         while(NULL != pEntry){
            // 将当前节点存储到新建的节点列表上去
            TInt index = pEntry->hash % capacity;
            pEntry->linkPtr = ppEntries[index];
            ppEntries[index] = pEntry;
            // 获得保存的下一个节点指针
            pEntry = pEntry->nextPtr;
         }
         // 保存新的节点列表
         _ppEntries = ppEntries;
         _entryCount = capacity;
      }
   }
   //------------------------------------------------------------
   // <T>接收源哈希表内的全部数据。</T>
   void Assign(const MAttributes{type}& attributes){
      Clear();
      Append(attributes);
   }
   //------------------------------------------------------------
   // <T>追加源哈希表内的全部数据。</T>
   void Append(const MAttributes{type}& attributes){
      SEntry* pEntry = attributes._pFirst;
      while(NULL != pEntry){
         Set(pEntry->namePtr->MemoryC(), pEntry->valuePtr->MemoryC());
         pEntry = pEntry->nextPtr;
      }
   }
   //------------------------------------------------------------
   // <T>移除指定名称的数据。</T>
   TBool Remove(const {char}* pName, MString{type}* pValue = NULL){
      // 计算哈希值
      THashCode hash = RChar{type}::MakeHashCode(pName);
      TInt index = hash % _count;
      // 查找数据出现的位置
      SEntry* pPrior = _ppEntries[index];
      SEntry* pEntry = pPrior;
      while(NULL != pEntry){
         if(pEntry->hash == hash){
            if(pEntry->IsName(pName)){
               // 设置输出内容
               if(NULL != pValue){
                  pValue->Assign((const {char}*)pEntry->valuePtr);
               }
               // 从链表上移除节点
               if(pEntry == pPrior){
                  // 当前对象是第一个对象时
                  _ppEntries[index] = pEntry->linkPtr;
               }else{
                  // 当前对象不是第一个对象时
                  pPrior->linkPtr = pEntry->linkPtr;
               }
               // 删除当前节点
               InnerEntryDelete(pEntry);
               return ETrue;
            }
         }
         pPrior = pEntry;
         pEntry = pEntry->linkPtr;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>清除所有资源。</T>
   void Clear(){
      // 清空链表
      InnerClear();
      // 清空节点数组
      RTypes<SEntry*>::Clear(_ppEntries, _entryCount);
   }
   //------------------------------------------------------------
   // <T>释放所有资源。</T>
   void Release(){
      // 释放链表
      InnerRelease();
      // 释放节点数组
      InnerEntriesFree(_ppEntries);
   }
public:
   //------------------------------------------------------------
   // <T>获得对象运行时信息。</T>
   void Dump(MString{type} dump) const{
      //dump.Append(TC("MAttributes{type}{"));
      //dump.AppendFormat("%d", _count);
      //if(_count > 0){
      //   SEntry* pEntry = _pFirst;
      //   while(NULL != pEntry){
      //      //dump.Append(*pEntry->pName);
      //      dump.Append(TC("="));
      //      //dump.Append(*pEntry->pValue);
      //      // 查找下一个
      //      pEntry = pEntry->nextPtr;
      //      if(NULL != pEntry){
      //         dump.Append(',');
      //      }
      //   }
      //}
   }
};

//============================================================
// <T>属性表对象。</T>
//============================================================
class MO_CM_DECLARE TAttributes{type} : public MAttributes{type}{
public:
   //------------------------------------------------------------
   // <T>创建属性表对象。</T>
   TAttributes{type}(){
      EnsureSize(MO_OBJECT_CAPACITY);
   }
   //------------------------------------------------------------
   // <T>创建属性表对象。</T>
   TAttributes{type}(TInt capacity){
      EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   TAttributes{type}(const MAttributes{type}& properties){
      Append(properties);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   TAttributes{type}(const TAttributes{type}& properties){
      Append(properties);
   }
};

//============================================================
// <T>属性表对象。</T>
//============================================================
class MO_CM_DECLARE FAttributes{type} :
      public FFree,
      public MAttributes{type}{
public:
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FAttributes{type}(){
      EnsureSize(MO_OBJECT_CAPACITY);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FAttributes{type}(TInt capacity){
      EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FAttributes{type}(const MAttributes{type}& properties){
      Append(properties);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FAttributes{type}(const FAttributes{type}& properties){
      Append(properties);
   }
};

//============================================================
// <T>属性集合节点</T>
//
// @struct
//============================================================
struct SProperties{type}Entry{
public:
   MString{type}* namePtr;
   MString{type}* valuePtr;
public:
   //------------------------------------------------------------
   // <T>构造属性表节点</T>
   SProperties{type}Entry(){
      MO_CLEAR(namePtr);
      MO_CLEAR(valuePtr);
   }
   //------------------------------------------------------------
   // <T>析构属性表节点</T>
   ~SProperties{type}Entry(){
      MO_DELETE(namePtr);
      MO_DELETE(valuePtr);
   }
public:
   //------------------------------------------------------------
   // <T>判断名称是否相等。</T>
   MO_INLINE( TBool IsName(const {char}* pName) const ){
      MO_ASSERT(pName);
      MO_ASSERT(namePtr);
      return namePtr->Equals(pName);
   }
   //------------------------------------------------------------
   // <T>判断不区分大小写名称是否相等。</T>
   MO_INLINE( TBool IsNameIgnoreCase(const {char}* pName) const ){
      MO_ASSERT(pName);
      MO_ASSERT(namePtr);
      return namePtr->EqualsIgnoreCase(pName);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据名称。</T>
   MO_INLINE( const {char}* Name() const ){
      MO_ASSERT(namePtr);
      return namePtr->MemoryC();
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE( const {char}* Value() const ){
      MO_ASSERT(valuePtr);
      return valuePtr->MemoryC();
   }
};

//============================================================
// <T>属性集合。</T>
// <P>键值和内容可以重复的集合。</P>
//
// @manager
// @history 091207 MAOCY 创建
//============================================================
class MO_CM_DECLARE MProperties{type}{
protected:
   // @attribute 数据容量
   TInt _capacity;
   // @attribute 数据长度
   TInt _count;
   // @attribute 数据内存
   SProperties{type}Entry** _ppMemory;
public:
   //------------------------------------------------------------
   MProperties{type}(){
   }
   //------------------------------------------------------------
   MProperties{type}(TInt capacity){
   }
   //------------------------------------------------------------
   MProperties{type}(const MProperties{type}& properties){
   }
   //------------------------------------------------------------
   MO_ABSTRACT( ~MProperties{type}() ){
   }
public:
//   {char}C* operator[]({char}C* pName);
//public:
//   TBool IsEmpty() const;
//   TInt Count() const;
//   TBool Contains({char}C* pName) const;
//   TBool ContainsValue({char}C* pValue) const;
//   FString{type}s* Names();
//   FString{type}s* Values();
//public:
//   {char}C* Name(TInt index) const;
//   void SetName(TInt index, {char}C* pName) const;
//   {char}C* Value(TInt index) const;
//   void SetValue(TInt index, {char}C* pValue) const;
//   {char}C* Get({char}C* pName) const;
//   void Set({char}C* pName, {char}C* pValue);
//   {char}C* Find({char}C* pName) const;
//   {char}C* FindNvl({char}C* pName, {char}C* pDefault) const;
//   {char}C* Search({char}C* pValue) const;
//public:
//   void EnsureSize(TInt size);
//   void Assign(const MProperties{type}& properties);
//   void Append(const MProperties{type}& properties);
//   void AppendSplit({char}C* pValue, {char} nameSpliter, {char} valueSpliter);
//   void AppendSplit({char}C* pValue, {char}C* pNameSpliter, {char}C* valueSpliter);
//   void Add({char}C* pName, {char}C* pValue);
//public:
//   void Join(FString{type}* pValue, {char} nameSpliter, {char} valueSpliter);
//   void Join(FString{type}* pValue, {char}C* pNameSpliter, {char}C* valueSpliter);
//   TInt Pack({char}* pPack, TInt length);
//   void Split({char}C* pValue, {char} nameSpliter, {char} valueSpliter);
//   void Split({char}C* pValue, {char}C* pNameSpliter, {char}C* valueSpliter);
//   void Unpack({char}C* pPack);
//   TString{type} Remove({char}C* pName);
//   void Clear();
//public:
//   TString{type} Dump() const;
};

//============================================================
// <T>属性表对象。</T>
//============================================================
class MO_CM_DECLARE TProperties{type} : public MProperties{type}{
public:
   //------------------------------------------------------------
   // <T>创建属性表对象。</T>
   TProperties{type}(){
   }
   //------------------------------------------------------------
   // <T>创建属性表对象。</T>
   TProperties{type}(TInt capacity) : MProperties{type}(capacity){
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   TProperties{type}(const MProperties{type}& properties) : MProperties{type}(properties){
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   TProperties{type}(const TProperties{type}& properties) : MProperties{type}(properties){
   }
};

//============================================================
// <T>属性表对象。</T>
//============================================================
class MO_CM_DECLARE FProperties{type} : public MProperties{type}{
public:
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FProperties{type}(){
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FProperties{type}(TInt capacity) : MProperties{type}(capacity){
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FProperties{type}(const MProperties{type}& properties) : MProperties{type}(properties){
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FProperties{type}(const FProperties{type}& properties) : MProperties{type}(properties){
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_STRINGBUFFER{type}_H__
