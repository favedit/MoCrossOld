#ifndef __MO_CM_STRING{type}_H__
#define __MO_CM_STRING{type}_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_TYPE_H__
#include "MoCmType.h"
#endif // __MO_CM_TYPE_H__

#ifndef __MO_CM_ARRAY_H__
#include "MoCmArray.h"
#endif // __MO_CM_ARRAY_H__

#ifndef __MO_CM_VECTOR_H__
#include "MoCmVector.h"
#endif // __MO_CM_VECTOR_H__

#ifndef __MO_CM_LIST_H__
#include "MoCmList.h"
#endif // __MO_CM_LIST_H__

MO_NAMESPACE_BEGIN

//============================================================
class IDataInput;
class IDataOutput;

//============================================================
// <T>ANSI字符串指针。</T>
//============================================================
class T{string}PtrC : public {char}PtrC{
public:
   //------------------------------------------------------------
   // <T>从数据指针构建对象。</T>
   T{string}PtrC({char}C* pMemory){
      Assign(pMemory);
   }
   //------------------------------------------------------------
   // <T>从数据指针和长度构建对象。</T>
   T{string}PtrC({char}C* pMemory, TInt length){
      Set(pMemory, length);
   }
   //------------------------------------------------------------
   // <T>从其他指针对象构建对象。</T>
   T{string}PtrC(const T{string}PtrC& ptr){
      Set(ptr);
   }
public:
   //------------------------------------------------------------
   // <T>重载指针赋值操作。</T>
   void operator=({char}C* pMemory){
      Assign(pMemory);
   }
public:
   //------------------------------------------------------------
   // <T>重载指针复制操作。</T>
   void Assign({char}C* pMemory){
      TInt length = RChar{type}s::Length(pMemory);
      Set(pMemory, length);
   }
};

//============================================================
// <T>只读ANSI字符串。</T>
//============================================================
class MO_CM_DECLARE M{string} : public MArray<{char}>{
public:
   //------------------------------------------------------------
   // <T>当前字符串上追加一个字符串。</T>
   MO_INLINE void operator+=({char}C* pValue){
      Append(pValue);
   }
 public:
   {char}* MemoryZ();
   T{string}PtrC PtrZ();
 public:
   T{string}PtrC StrC() const;
   T{string}PtrC LeftStrC(TInt length) const;
   T{string}PtrC MidStrC(TInt offset) const;
   T{string}PtrC MidStrC(TInt offset, TInt length) const;
   T{string}PtrC RightStrC(TInt length) const;
   T{string}PtrC SubStrC(TInt begin, TInt end) const;
   T{string}PtrC TrimLeftStrC() const;
   T{string}PtrC TrimRightStrC() const;
   T{string}PtrC TrimStrC() const;
public:
   TBool Equals({char}C* pValue) const;
   TBool EqualsIgnoreCase({char}C* pValue) const;
   TBool EqualsIgnoreCase(const T{string}PtrC& value) const;
   TBool StartsWith({char}C* pValue) const;
   TBool EndsWith({char}C* pValue) const;
   TInt CompareIgnoreCase({char}C* pValue) const;
   TInt CompareIgnoreCase(const T{string}PtrC& value) const;
   THashCode HashCode() const;
   TInt Find({char}C* pValue);
   TInt Find({char}C* pValue, TInt offset);
   TInt LastFind({char}C* pValue);
public:
   TBool Assign({char}C* pValue, TInt length = -1);
   TBool Assign(const TPtrC<{char}>& ptr);
   TBool Assign(const M{string}& value);
   TBool AssignPointer(const M{string}* pValue);
   TResult AssignFormat({char}C* pFormat, ...);
   TResult AssignFormatParameters({char}C* pFormat, va_list& params);
   TBool Append({char} value);
   TBool Append({char}C* pValues, TInt length = -1);
   TBool Append(const TPtrC<{char}>& ptr);
   TBool Append(const M{string}& value);
   TBool AppendInt(TInt value);
   TBool AppendRepeat({char}C* pValue, TInt count);
   TResult AppendFormat({char}C* pFormat, ...);
   TResult AppendFormatParameters({char}C* pFormat, va_list& params);
   void AppendLine();
   void AppendLine({char} value);
   void AppendLine({char}C* pValues, TInt length = -1);
   TResult AppendLineFormat({char}C* pFormat, ...);
   TResult AppendLineFormatParameters({char}C* pFormat, va_list& params);
public:
   void Assign8(TChar8C* pValue);
   void Assign16(TChar16C* pValue);
   void Assign32(TChar32C* pValue);
public:
   void ToLower();
   void ToUpper();
   void PadLeft({char} pad, TInt length);
   void PadRight({char} pad, TInt length);
   TInt Replace({char} source, {char} target);
public:
   TInt ToInt();
   TUint ToUint();
   TInt64 ToInt64();
   TUint64 ToUint64();
   TFloat ToFloat();
   TDouble ToDouble();
   void SetInt(TInt value);
   void SetUint(TUint value);
   void SetInt64(TInt64 value);
   void SetUint64(TUint64 value);
   void SetFloat(TFloat value);
   void SetDouble(TDouble value);
public:
   void Serialize(IDataOutput* pOutput);
   void Unserialize(IDataInput* pInput);
   void UnserializeAutomatic(IDataInput* pInput);
public:
   void SetLength(TInt length);
   void Fix();
   void Clear();
};

//============================================================
// <T>字符串引用。</T>
//============================================================
class MO_CM_DECLARE T{string}Refer : public M{string}{
public:
   //------------------------------------------------------------
   // <T>构造字符串引用。</T>
   T{string}Refer({char}C* pValue){
      MO_ASSERT(pValue);
      _pMemory = MO_CAST_CONST(pValue, {char}*);
      _length = RChar{type}s::Length(pValue);
      _capacity = _length;
   }
   //------------------------------------------------------------
   // <T>构造字符串引用。</T>
   T{string}Refer({char}* pValue){
      MO_ASSERT(pValue);
      _pMemory = pValue;
      _length = RChar{type}s::Length(pValue);
      _capacity = _length;
   }
   //------------------------------------------------------------
   // <T>构造字符串引用。</T>
   T{string}Refer({char}* pValue, TInt size){
      MO_ASSERT(pValue);
      _length = 0;
      _pMemory = pValue;
      _capacity = size;
   }
   //------------------------------------------------------------
   // <T>构造字符串引用。</T>
   T{string}Refer(const {char}PtrC& ptr){
      _length = ptr.Length();
      _pMemory = MO_CAST_CONST(ptr.MemoryC(), {char}*);
      _capacity = _length;
   }
   //------------------------------------------------------------
   // <T>析构字符串引用。</T>
   MO_ABSTRACT ~T{string}Refer(){
   }
protected:
   //------------------------------------------------------------
   // <T>调整内存大小。</T>
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force){
      MO_ASSERT(size <= _capacity);
   }
public:
   //------------------------------------------------------------
   // <T>接收数据内容到内部。</T>
   MO_INLINE void operator=({char}C* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>接收数据内容到内部。</T>
   MO_INLINE void operator=(const T{string}PtrC& value){
      Assign(value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>接收数据内容到内部。</T>
   MO_INLINE void operator=(const T{string}Refer& value){
      Assign(value.MemoryC(), value.Length());
   }
};

//============================================================
// <T>字符串。</T>
//============================================================
class MO_CM_DECLARE T{string} : public M{string}{
public:
   T{string}();
   T{string}({char}C* pValue, TInt length = -1);
   T{string}(const T{string}PtrC& ptr);
   T{string}(const M{string}& value);
   T{string}(const T{string}& value);
   MO_ABSTRACT ~T{string}();
protected:
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force);
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串的内容。</T>
   MO_INLINE void operator=({char}C* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定ANSI字符串指针对象。</T>
   MO_INLINE void operator=(const T{string}PtrC& value){
      Assign(value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串指针对象。</T>
   MO_INLINE void operator=(const M{string}& value){
      Assign(value.MemoryC(), value.Length());
   }
public:
   void Append8(TChar8C* pValue);
   void Append16(TChar16C* pValue);
   void Append32(TChar32C* pValue);
};

//============================================================
// <T>字符串。</T>
//============================================================
class MO_CM_DECLARE F{string} :
      public FObject,
      public M{string}{
protected:
   TBool _buffered;
public:
   F{string}();
   F{string}({char}C* pValue, TInt length = -1);
   F{string}(const T{string}PtrC& value);
   F{string}(const M{string}& value);
   MO_ABSTRACT ~F{string}();
protected:
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force);
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串的内容。</T>
   MO_INLINE void operator=({char}C* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串指针对象。</T>
   MO_INLINE void operator=(const T{string}PtrC& value){
      Assign(value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串指针对象。</T>
   MO_INLINE void operator=(const M{string}& value){
      Assign(value.MemoryC(), value.Length());
   }
};
//------------------------------------------------------------
typedef MO_CM_DECLARE TVectorIteratorC<F{string}*> T{string}sIteratorC;
typedef MO_CM_DECLARE FVector<F{string}*> F{string}Vector;

//============================================================
// <T>定长ANSI字符串工具类。</T>
//
// @tools
// @author maocy
// @version 1.0.1
//============================================================
template <TInt S>
class TFix{string} : public M{string}{
protected:
   {char} _memory[S];
public:
   //------------------------------------------------------------
   // <T>构造一个定长字符串类。</T>
   TFix{string}(){
      InnerInitialize();
   }
   //------------------------------------------------------------
   // <T>构造一个定长字符串类。</T>
   TFix{string}({char}C* pValue, TInt length = -1){
      InnerInitialize();
      Assign(pValue, length);
   }
   //------------------------------------------------------------
   // <T>构造一个定长字符串类。</T>
   TFix{string}(const T{string}PtrC& ptr){
      InnerInitialize();
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造一个定长字符串类。</T>
   TFix{string}(const TFix{string}<S>& value){
      InnerInitialize();
      Assign(value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>析构一个定长字符串类。</T>
   MO_ABSTRACT ~TFix{string}(){
   }
protected:
   //------------------------------------------------------------
   // <T>内部初始化。</T>
   MO_INLINE void InnerInitialize(){
      MO_ASSERT(S > 0);
      _pMemory = _memory;
      _capacity = S;
      _memory[0] = 0;
   }
   //------------------------------------------------------------
   // <T>调整内存大小。</T>
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force){
      MO_ASSERT(size <= S);
   }
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串的内容。</T>
   MO_INLINE void operator=({char}C* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串对象的内容。</T>
   MO_INLINE void operator=(const TPtrC<{char}>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串对象的内容。</T>
   MO_INLINE void operator=(const TFix{string}<S>& value){
      Assign(value.MemoryC(), value.Length());
   }
public:
   //------------------------------------------------------------
   // <T>获得容纳长度。</T>
   MO_INLINE static TInt Size(){
      return S;
   }
};

//============================================================
// <T>定长字符串。</T>
//============================================================
#define MO_BCD_TFIXSTRING{type}(C, P) \
class MO_CM_DECLARE C : public P{ \
public: \
   C(){ \
   } \
   C(const C& value) : P(value){ \
   } \
   C({char}C* pValue, TInt length=-1) : P(pValue, length){ \
   } \
   C(const T{string}PtrC& ptr) : P(ptr){ \
   } \
   MO_ABSTRACT ~C(){ \
   } \
public: \
   void operator=({char}C* pValue){ \
   Assign(pValue); \
   } \
   void operator=(const C& value){ \
      Assign(value.MemoryC(), value.Length()); \
   } \
   void operator=(const T{string}PtrC& value){ \
      Assign(value.MemoryC(), value.Length()); \
   } \
};
//------------------------------------------------------------
MO_BCD_TFIXSTRING{type}(TFsNumber{type},   TFix{string}<MO_FS_NUMBER_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsTimeTick{type}, TFix{string}<MO_FS_TIMETICK_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsCode{type},     TFix{string}<MO_FS_CODE_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsName{type},     TFix{string}<MO_FS_NAME_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsLabel{type},    TFix{string}<MO_FS_LABEL_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsText{type},     TFix{string}<MO_FS_TEXT_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsCommand{type},  TFix{string}<MO_FS_COMMAND_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsNote{type},     TFix{string}<MO_FS_NOTE_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsLogger{type},   TFix{string}<MO_FS_LOGGER_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsDump{type},     TFix{string}<MO_FS_DUMP_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsSql{type},      TFix{string}<MO_FS_SQL_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsTrack{type},    TFix{string}<MO_FS_TRACK_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsFileName{type}, TFix{string}<MO_FS_FILENAME_LENGTH>);
MO_BCD_TFIXSTRING{type}(TFsPath{type},     TFix{string}<MO_FS_PATH_LENGTH>);

//============================================================
// <T>字符串缓冲。</T>
//============================================================
class MO_CM_DECLARE TStringBuffer{type} : public M{string}{
public:
   TStringBuffer{type}();
   TStringBuffer{type}(TChar{type}C* pValue, TInt length = -1);
   TStringBuffer{type}(const TString{type}PtrC& ptr);
   TStringBuffer{type}(const M{string}& value);
   MO_ABSTRACT ~TStringBuffer{type}();
protected:
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force);
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串的内容。</T>
   MO_INLINE void operator=({char}C* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定ANSI字符串指针对象。</T>
   MO_INLINE void operator=(const T{string}PtrC& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串指针对象。</T>
   MO_INLINE void operator=(const M{string}& value){
      Assign(value.MemoryC(), value.Length());
   }
};

//============================================================
// <T>字符串缓冲。</T>
//============================================================
class MO_CM_DECLARE FStringBuffer{type} :
      public FObject,
      public M{string}{
public:
   FStringBuffer{type}();
   FStringBuffer{type}({char}C* pValue, TInt length = -1);
   FStringBuffer{type}(const T{string}PtrC& ptr);
   FStringBuffer{type}(const M{string}& value);
   MO_ABSTRACT ~FStringBuffer{type}();
protected:
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force);
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串的内容。</T>
   MO_INLINE void operator=({char}C* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串指针对象。</T>
   MO_INLINE void operator=(const T{string}PtrC& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串指针对象。</T>
   MO_INLINE void operator=(const M{string}& value){
      Assign(value.MemoryC(), value.Length());
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
   TCharC* Get(TInt n){
      return &_memory[_results[n]];
   }
public:
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   void Split({char}C* pSource, {char} splitter){
      T{string}Refer value(pSource);
      TInt begin = 0;
      TInt length = value.Length();
      TInt count = 0;
      while(begin < length){
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
      MO_ASSERT(count <= MO_FS_TEXT_LENGTH);
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
      MO_ASSERT(count <= MO_FS_TEXT_LENGTH);
   }
};
//------------------------------------------------------------
typedef TFixStringToken{type}<MO_FS_TEXT_LENGTH> TFsStringToken{type};

//============================================================
// <T>字符串集合。</T>
//============================================================
class MO_CM_DECLARE M{string}s{
protected:
   TInt _count;
   F{string}Vector* _pStrings;
public:
   M{string}s();
   MO_ABSTRACT ~M{string}s();
public:
   {char}C* operator[](TInt index) const;
   void operator +=({char}C* pValue);
   void operator +=(const M{string}s& strings);
   void operator -=({char}C* pValue);
   void operator -=(const M{string}s& strings);
public:
   TBool IsEmpty() const;
   TInt Count() const;
   TBool Contains({char}C* pValue) const;
public:
   T{string}sIteratorC IteratorC() const;
   {char}C* First() const;
   {char}C* Last() const;
   {char}C* Get(TInt index) const;
   TInt IndexOf({char}C* pValue) const;
   T{string} Join({char} splitter);
   T{string} Join({char}C* splitter);
   TInt Pack({char}* pPack, TInt length);
   T{string} Pack();
public:
   void EnsureSize(TInt size);
   void Set(TInt index, {char}C* pValue);
   void Assign(const M{string}s& strings);
   void Append(const M{string}s& strings);
   void AppendSplit({char}C* pValue, {char} splitter);
   void AppendSplit({char}C* pValue, {char}C* pSplitter);
   void Push({char}C* pValue);
   void Push(const T{string}PtrC& value);
   void Remove({char}C* pValue);
   void Remove(const M{string}s& strings);
   void Delete(TInt index);
   void Split({char}C* pValue, {char} splitter);
   void Split({char}C* pValue, {char}C* pSplitter);
   void Unpack({char}C* pPack);
   void Clear();
};

//============================================================
class MO_CM_DECLARE T{string}s : public M{string}s{
public:
   //------------------------------------------------------------
   T{string}s(){
   }
   //------------------------------------------------------------
   T{string}s(const M{string}s& strings){
      Append(strings);
   }
   //------------------------------------------------------------
   T{string}s(const T{string}s& strings){
      Append(strings);
   }
   //------------------------------------------------------------
   MO_ABSTRACT ~T{string}s(){
   }
public:
   //------------------------------------------------------------
   void operator=(const M{string}s& strings){
      Assign(strings);
   }
   //------------------------------------------------------------
   void operator=(const T{string}s& strings){
      Assign(strings);
   }
};

//============================================================
class MO_CM_DECLARE F{string}s :
      public FObject,
      public M{string}s{
public:
   //------------------------------------------------------------
   F{string}s(){
   }
   //------------------------------------------------------------
   F{string}s(const M{string}s& strings){
      Append(strings);
   }
   //------------------------------------------------------------
   F{string}s(const F{string}s& strings){
      Append(strings);
   }
   //------------------------------------------------------------
   MO_ABSTRACT ~F{string}s(){
   }
public:
   //------------------------------------------------------------
   void operator=(const M{string}s& strings){
      Assign(strings);
   }
   //------------------------------------------------------------
   void operator=(const F{string}s& strings){
      Assign(strings);
   }
};

//============================================================
// <T>只读链表迭代器。</T>
//
// @tool
//============================================================
class MO_CM_DECLARE MProperties{type}IteratorC{
public:
   TInt _index;
   TInt _count;
   F{string}s* _pNames;
   F{string}s* _pValues;
public:
   //------------------------------------------------------------
   // 构造只读迭代器
   MO_INLINE MProperties{type}IteratorC(){
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   MO_INLINE MProperties{type}IteratorC(const MProperties{type}IteratorC& iterator){
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   MO_INLINE MProperties{type}IteratorC(TInt count, F{string}s* pNames, F{string}s* pValues){
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
   MO_INLINE TBool IsEmpty(){
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasNext(){
      return ETrue;
   }
   //------------------------------------------------------------
   //<T>移动到下一个位置。</T>
   MO_INLINE TBool Next(){
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasPrior(){
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE TBool Prior(){
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>重置位置。</T>
   MO_INLINE void Reset(){
   }
public:
   //------------------------------------------------------------
   // <T>判断名称是否相同。</T>
   MO_INLINE TBool IsName(TCharC* pName) const{
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>判断内容是否相同。</T>
   MO_INLINE TBool IsValue(TCharC* pValue) const{
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据名称。</T>
   MO_INLINE TCharC* Name() const{
      return NULL;
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE TCharC* Value() const{
      return NULL;
   }
};

//============================================================
// <T>可写链表迭代器。</T>
//
// @tool
//============================================================
class MO_CM_DECLARE MProperties{type}Iterator : public MProperties{type}IteratorC{
public:
   //------------------------------------------------------------
   // 构造链表迭代器
   MProperties{type}Iterator(){
   }
   //------------------------------------------------------------
   // 构造链表迭代器
   MProperties{type}Iterator(const MProperties{type}IteratorC& iterator){
   }
public:
   //------------------------------------------------------------
   // 设置当前位置的数据内容
   MO_INLINE void SetName(TCharC* pName) const{
   }
   //------------------------------------------------------------
   // 设置当前位置的数据内容
   MO_INLINE void SetValue(TCharC* pValue) const{
   }
};

//============================================================
// <T>属性集合（键值和内容可以重复的集合）。</T>
//
// @manager
// @history 091207 MAOCY 创建
//============================================================
class MO_CM_DECLARE MProperties{type}{
protected:
   TInt _count;
   F{string}s* _pNames;
   F{string}s* _pValues;
public:
   MProperties{type}();
   MO_ABSTRACT ~MProperties{type}();
public:
   {char}C* operator[]({char}C* pName);
public:
   TBool IsEmpty() const;
   TInt Count() const;
   TBool Contains({char}C* pName) const;
   TBool ContainsValue({char}C* pValue) const;
   F{string}s* Names();
   F{string}s* Values();
public:
   {char}C* Name(TInt index) const;
   void SetName(TInt index, {char}C* pName) const;
   {char}C* Value(TInt index) const;
   void SetValue(TInt index, {char}C* pValue) const;
   {char}C* Get({char}C* pName) const;
   {char}C* Find({char}C* pName) const;
   {char}C* FindNvl({char}C* pName, {char}C* pDefault) const;
   {char}C* Search({char}C* pValue) const;
   void Join(F{string}* pValue, {char} nameSpliter, {char} valueSpliter);
   void Join(F{string}* pValue, {char}C* pNameSpliter, {char}C* valueSpliter);
   TInt Pack({char}* pPack, TInt length);
   // TAttributesIteratorC IteratorC() const;
   T{string} Dump() const;
public:
   void EnsureSize(TInt size);
   void Assign(const MProperties{type}& properties);
   void Append(const MProperties{type}& properties);
   void AppendSplit({char}C* pValue, {char} nameSpliter, {char} valueSpliter);
   void AppendSplit({char}C* pValue, {char}C* pNameSpliter, {char}C* valueSpliter);
   void Add({char}C* pName, {char}C* pValue);
   void Set({char}C* pName, {char}C* pValue);
   void Split({char}C* pValue, {char} nameSpliter, {char} valueSpliter);
   void Split({char}C* pValue, {char}C* pNameSpliter, {char}C* valueSpliter);
   void Unpack({char}C* pPack);
   T{string} Remove({char}C* pName);
   void Clear();
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
   TProperties{type}(TInt capacity){
      EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   TProperties{type}(const MProperties{type}& properties){
      Append(properties);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   TProperties{type}(const TProperties{type}& properties){
      Append(properties);
   }
   //------------------------------------------------------------
   // <T>析构属性表。</T>
   MO_ABSTRACT ~TProperties{type}(){
   }
};

//============================================================
// <T>属性表对象。</T>
//============================================================
class MO_CM_DECLARE FProperties{type} :
      public FObject,
      public MProperties{type}{
public:
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FProperties{type}(){
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FProperties{type}(TInt capacity){
      EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FProperties{type}(const MProperties{type}& properties){
      Append(properties);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FProperties{type}(const FProperties{type}& properties){
      Append(properties);
   }
   //------------------------------------------------------------
   // <T>析构属性表。</T>
   MO_ABSTRACT ~FProperties{type}(){
   }
};

//============================================================
// <T>ANSI字符串工具类。</T>
//============================================================
class MO_CM_DECLARE R{string}{
public:
   static {char}C* EmptyPtr;
   static T{string} EmptyString;
public:
   static TInt Length({char}C* pValue);
   static TBool Equals({char}C* pSource, {char}C* pTarget);
   static TBool Constains({char}C* pSource, {char}C* pValue);
   static TInt Find({char}C* pSource, {char}C* pValue);
   static THashCode MakeHashCode({char}C* pValues);
   static THashCode MakeNocaseHashCode({char}C* pValues);
public:
   static TBool AllocCopy({char}C* pValues, {char}** ppTarget);
   static TBool AllocFree({char}* pTarget);
   static TInt SafeCopy({char}* pTarget, TSize size, {char}C* pValue);
   static TInt ForceCopy({char}* pTarget, TSize size, {char}C* pValue);
   static TInt ReplaceAll({char}* pValues, {char}* pSource, {char}* pTarget, {char}* pBuffer, TInt bufferLength);
public:
   static TInt ConvertToString8({char}C* pValue);
   static TInt ConvertToString8(TChar8* pTarget, TInt capacity, {char}C* pValue);
   static TInt ConvertToString16({char}C* pValue);
   static TInt ConvertToString16(TChar16* pTarget, TInt capacity, {char}C* pValue);
   static TInt ConvertToString32({char}C* pValue);
   static TInt ConvertToString32(TChar32* pTarget, TInt capacity, {char}C* pValue);
};

MO_NAMESPACE_END

#endif // __MO_CM_STRING{type}_H__
