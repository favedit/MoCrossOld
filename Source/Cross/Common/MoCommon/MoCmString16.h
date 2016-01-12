#ifndef __MO_CM_STRING16_H__
#define __MO_CM_STRING16_H__

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
class TString16PtrC : public TChar16PtrC{
public:
   //------------------------------------------------------------
   // <T>从数据指针构建对象。</T>
   TString16PtrC(TChar16C* pMemory){
      Assign(pMemory);
   }
   //------------------------------------------------------------
   // <T>从数据指针和长度构建对象。</T>
   TString16PtrC(TChar16C* pMemory, TInt length){
      Set(pMemory, length);
   }
   //------------------------------------------------------------
   // <T>从其他指针对象构建对象。</T>
   TString16PtrC(const TString16PtrC& ptr){
      Set(ptr);
   }
public:
   //------------------------------------------------------------
   // <T>重载指针赋值操作。</T>
   void operator=(TChar16C* pMemory){
      Assign(pMemory);
   }
public:
   //------------------------------------------------------------
   // <T>重载指针复制操作。</T>
   void Assign(TChar16C* pMemory){
      TInt length = RChar16s::Length(pMemory);
      Set(pMemory, length);
   }
};

//============================================================
// <T>只读ANSI字符串。</T>
//============================================================
class MO_CM_DECLARE MString16 : public MArray<TChar16>{
public:
   //------------------------------------------------------------
   // <T>当前字符串上追加一个字符串。</T>
   MO_INLINE void operator+=(TChar16C* pValue){
      Append(pValue);
   }
 public:
   TChar16* MemoryZ();
   TString16PtrC PtrZ();
 public:
   TString16PtrC StrC() const;
   TString16PtrC LeftStrC(TInt length) const;
   TString16PtrC MidStrC(TInt offset) const;
   TString16PtrC MidStrC(TInt offset, TInt length) const;
   TString16PtrC RightStrC(TInt length) const;
   TString16PtrC SubStrC(TInt begin, TInt end) const;
   TString16PtrC TrimLeftStrC() const;
   TString16PtrC TrimRightStrC() const;
   TString16PtrC TrimStrC() const;
public:
   TBool Equals(TChar16C* pValue) const;
   TBool EqualsIgnoreCase(TChar16C* pValue) const;
   TBool EqualsIgnoreCase(const TString16PtrC& value) const;
   TBool StartsWith(TChar16C* pValue) const;
   TBool EndsWith(TChar16C* pValue) const;
   TInt CompareIgnoreCase(TChar16C* pValue) const;
   TInt CompareIgnoreCase(const TString16PtrC& value) const;
   THashCode HashCode() const;
   TInt Find(TChar16C* pValue);
   TInt Find(TChar16C* pValue, TInt offset);
   TInt LastFind(TChar16C* pValue);
public:
   TBool Assign(TChar16C* pValue, TInt length = -1);
   TBool Assign(const TPtrC<TChar16>& ptr);
   TBool Assign(const MString16& value);
   TBool AssignPointer(const MString16* pValue);
   TResult AssignFormat(TChar16C* pFormat, ...);
   TResult AssignFormatParameters(TChar16C* pFormat, va_list& params);
   TBool Append(TChar16 value);
   TBool Append(TChar16C* pValues, TInt length = -1);
   TBool Append(const TPtrC<TChar16>& ptr);
   TBool Append(const MString16& value);
   TBool AppendInt(TInt value);
   TBool AppendRepeat(TChar16C* pValue, TInt count);
   TResult AppendFormat(TChar16C* pFormat, ...);
   TResult AppendFormatParameters(TChar16C* pFormat, va_list& params);
   void AppendLine();
   void AppendLine(TChar16 value);
   void AppendLine(TChar16C* pValues, TInt length = -1);
   TResult AppendLineFormat(TChar16C* pFormat, ...);
   TResult AppendLineFormatParameters(TChar16C* pFormat, va_list& params);
public:
   void Assign8(TChar8C* pValue);
   void Assign16(TChar16C* pValue);
   void Assign32(TChar32C* pValue);
public:
   void ToLower();
   void ToUpper();
   void PadLeft(TChar16 pad, TInt length);
   void PadRight(TChar16 pad, TInt length);
   TInt Replace(TChar16 source, TChar16 target);
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
class MO_CM_DECLARE TString16Refer : public MString16{
public:
   //------------------------------------------------------------
   // <T>构造字符串引用。</T>
   TString16Refer(TChar16C* pValue){
      MO_ASSERT(pValue);
      _pMemory = MO_CAST_CONST(pValue, TChar16*);
      _length = RChar16s::Length(pValue);
      _capacity = _length;
   }
   //------------------------------------------------------------
   // <T>构造字符串引用。</T>
   TString16Refer(TChar16* pValue){
      MO_ASSERT(pValue);
      _pMemory = pValue;
      _length = RChar16s::Length(pValue);
      _capacity = _length;
   }
   //------------------------------------------------------------
   // <T>构造字符串引用。</T>
   TString16Refer(TChar16* pValue, TInt size){
      MO_ASSERT(pValue);
      _length = 0;
      _pMemory = pValue;
      _capacity = size;
   }
   //------------------------------------------------------------
   // <T>构造字符串引用。</T>
   TString16Refer(const TChar16PtrC& ptr){
      _length = ptr.Length();
      _pMemory = MO_CAST_CONST(ptr.MemoryC(), TChar16*);
      _capacity = _length;
   }
   //------------------------------------------------------------
   // <T>析构字符串引用。</T>
   MO_ABSTRACT ~TString16Refer(){
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
   MO_INLINE void operator=(TChar16C* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>接收数据内容到内部。</T>
   MO_INLINE void operator=(const TString16PtrC& value){
      Assign(value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>接收数据内容到内部。</T>
   MO_INLINE void operator=(const TString16Refer& value){
      Assign(value.MemoryC(), value.Length());
   }
};

//============================================================
// <T>字符串。</T>
//============================================================
class MO_CM_DECLARE TString16 : public MString16{
public:
   TString16();
   TString16(TChar16C* pValue, TInt length = -1);
   TString16(const TString16PtrC& ptr);
   TString16(const MString16& value);
   TString16(const TString16& value);
   MO_ABSTRACT ~TString16();
protected:
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force);
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串的内容。</T>
   MO_INLINE void operator=(TChar16C* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定ANSI字符串指针对象。</T>
   MO_INLINE void operator=(const TString16PtrC& value){
      Assign(value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串指针对象。</T>
   MO_INLINE void operator=(const MString16& value){
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
class MO_CM_DECLARE FString16 :
      public FObject,
      public MString16{
protected:
   TBool _buffered;
public:
   FString16();
   FString16(TChar16C* pValue, TInt length = -1);
   FString16(const TString16PtrC& value);
   FString16(const MString16& value);
   MO_ABSTRACT ~FString16();
protected:
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force);
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串的内容。</T>
   MO_INLINE void operator=(TChar16C* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串指针对象。</T>
   MO_INLINE void operator=(const TString16PtrC& value){
      Assign(value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串指针对象。</T>
   MO_INLINE void operator=(const MString16& value){
      Assign(value.MemoryC(), value.Length());
   }
};
//------------------------------------------------------------
typedef MO_CM_DECLARE TVectorIteratorC<FString16*> TString16sIteratorC;
typedef MO_CM_DECLARE FVector<FString16*> FString16Vector;

//============================================================
// <T>定长ANSI字符串工具类。</T>
//
// @tools
// @author maocy
// @version 1.0.1
//============================================================
template <TInt S>
class TFixString16 : public MString16{
protected:
   TChar16 _memory[S];
public:
   //------------------------------------------------------------
   // <T>构造一个定长字符串类。</T>
   TFixString16(){
      InnerInitialize();
   }
   //------------------------------------------------------------
   // <T>构造一个定长字符串类。</T>
   TFixString16(TChar16C* pValue, TInt length = -1){
      InnerInitialize();
      Assign(pValue, length);
   }
   //------------------------------------------------------------
   // <T>构造一个定长字符串类。</T>
   TFixString16(const TString16PtrC& ptr){
      InnerInitialize();
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造一个定长字符串类。</T>
   TFixString16(const TFixString16<S>& value){
      InnerInitialize();
      Assign(value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>析构一个定长字符串类。</T>
   MO_ABSTRACT ~TFixString16(){
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
   MO_INLINE void operator=(TChar16C* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串对象的内容。</T>
   MO_INLINE void operator=(const TPtrC<TChar16>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串对象的内容。</T>
   MO_INLINE void operator=(const TFixString16<S>& value){
      Assign(value.MemoryC(), value.Length());
   }
public:
   //------------------------------------------------------------
   // <T>获得容纳长度。</T>
   static MO_INLINE TInt Size(){
      return S;
   }
};

//============================================================
// <T>定长字符串。</T>
//============================================================
#define MO_BCD_TFIXSTRING16(C, P) \
class MO_CM_DECLARE C : public P{ \
public: \
   C(){ \
   } \
   C(const C& value) : P(value){ \
   } \
   C(TChar16C* pValue, TInt length=-1) : P(pValue, length){ \
   } \
   C(const TString16PtrC& ptr) : P(ptr){ \
   } \
   MO_ABSTRACT ~C(){ \
   } \
public: \
   MO_INLINE void operator=(TChar16C* pValue){ \
      Assign(pValue); \
   } \
   MO_INLINE void operator=(const C& value){ \
      Assign(value.MemoryC(), value.Length()); \
   } \
   MO_INLINE void operator=(const TString16PtrC& value){ \
      Assign(value.MemoryC(), value.Length()); \
   } \
public: \
   static MO_INLINE TInt Size(){ \
      return P::Size(); \
   } \
};
//------------------------------------------------------------
MO_BCD_TFIXSTRING16(TFsNumber16,   TFixString16<MO_FS_NUMBER_LENGTH>);
MO_BCD_TFIXSTRING16(TFsTimeTick16, TFixString16<MO_FS_TIMETICK_LENGTH>);
MO_BCD_TFIXSTRING16(TFsCode16,     TFixString16<MO_FS_CODE_LENGTH>);
MO_BCD_TFIXSTRING16(TFsName16,     TFixString16<MO_FS_NAME_LENGTH>);
MO_BCD_TFIXSTRING16(TFsLabel16,    TFixString16<MO_FS_LABEL_LENGTH>);
MO_BCD_TFIXSTRING16(TFsText16,     TFixString16<MO_FS_TEXT_LENGTH>);
MO_BCD_TFIXSTRING16(TFsCommand16,  TFixString16<MO_FS_COMMAND_LENGTH>);
MO_BCD_TFIXSTRING16(TFsNote16,     TFixString16<MO_FS_NOTE_LENGTH>);
MO_BCD_TFIXSTRING16(TFsLogger16,   TFixString16<MO_FS_LOGGER_LENGTH>);
MO_BCD_TFIXSTRING16(TFsDump16,     TFixString16<MO_FS_DUMP_LENGTH>);
MO_BCD_TFIXSTRING16(TFsSql16,      TFixString16<MO_FS_SQL_LENGTH>);
MO_BCD_TFIXSTRING16(TFsTrack16,    TFixString16<MO_FS_TRACK_LENGTH>);
MO_BCD_TFIXSTRING16(TFsFileName16, TFixString16<MO_FS_FILENAME_LENGTH>);
MO_BCD_TFIXSTRING16(TFsPath16,     TFixString16<MO_FS_PATH_LENGTH>);

//============================================================
// <T>字符串缓冲。</T>
//============================================================
class MO_CM_DECLARE TStringBuffer16 : public MString16{
public:
   TStringBuffer16();
   TStringBuffer16(TChar16C* pValue, TInt length = -1);
   TStringBuffer16(const TString16PtrC& ptr);
   TStringBuffer16(const MString16& value);
   MO_ABSTRACT ~TStringBuffer16();
protected:
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force);
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串的内容。</T>
   MO_INLINE void operator=(TChar16C* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定ANSI字符串指针对象。</T>
   MO_INLINE void operator=(const TString16PtrC& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串指针对象。</T>
   MO_INLINE void operator=(const MString16& value){
      Assign(value.MemoryC(), value.Length());
   }
};

//============================================================
// <T>字符串缓冲。</T>
//============================================================
class MO_CM_DECLARE FStringBuffer16 :
      public FObject,
      public MString16{
public:
   FStringBuffer16();
   FStringBuffer16(TChar16C* pValue, TInt length = -1);
   FStringBuffer16(const TString16PtrC& ptr);
   FStringBuffer16(const MString16& value);
   MO_ABSTRACT ~FStringBuffer16();
protected:
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force);
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串的内容。</T>
   MO_INLINE void operator=(TChar16C* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串指针对象。</T>
   MO_INLINE void operator=(const TString16PtrC& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串指针对象。</T>
   MO_INLINE void operator=(const MString16& value){
      Assign(value.MemoryC(), value.Length());
   }
};

//============================================================
// <T>字符串分割器。</T>
//============================================================
template<TInt S>
class TFixStringToken16{
protected:
   TInt _count;
   TInt _results[S];
   TChar16 _memory[S];
public:
   //------------------------------------------------------------
   // <T>构造字符串分割器。</T>
   TFixStringToken16(){
      _count = 0;
   }
   //------------------------------------------------------------
   // <T>构造字符串分割器。</T>
   TFixStringToken16(TChar16C* pSource, TChar16 splitter){
      _count = 0;
      Split(pSource, splitter);
   }
   //------------------------------------------------------------
   // <T>构造字符串分割器。</T>
   TFixStringToken16(TChar16C* pSource, TChar16C* pSplitter){
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
   void Split(TChar16C* pSource, TChar16 splitter){
      TString16Refer value(pSource);
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
   void Split(TChar16C* pSource, TChar16C* pSplitter){
      TInt lengthSource = RChar16s::Length(pSource);
      TInt lengthSplitter = RChar16s::Length(pSplitter);
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
typedef TFixStringToken16<MO_FS_TEXT_LENGTH> TFsStringToken16;

//============================================================
// <T>字符串集合。</T>
//============================================================
class MO_CM_DECLARE MString16s{
protected:
   TInt _count;
   FString16Vector* _pStrings;
public:
   MString16s();
   MO_ABSTRACT ~MString16s();
public:
   TChar16C* operator[](TInt index) const;
   void operator +=(TChar16C* pValue);
   void operator +=(const MString16s& strings);
   void operator -=(TChar16C* pValue);
   void operator -=(const MString16s& strings);
public:
   TBool IsEmpty() const;
   TInt Count() const;
   TBool Contains(TChar16C* pValue) const;
public:
   TString16sIteratorC IteratorC() const;
   TChar16C* First() const;
   TChar16C* Last() const;
   TChar16C* Get(TInt index) const;
   TInt IndexOf(TChar16C* pValue) const;
   TString16 Join(TChar16 splitter);
   TString16 Join(TChar16C* splitter);
   TInt Pack(TChar16* pPack, TInt length);
   TString16 Pack();
public:
   void EnsureSize(TInt size);
   void Set(TInt index, TChar16C* pValue);
   void Assign(const MString16s& strings);
   void Append(const MString16s& strings);
   void AppendSplit(TChar16C* pValue, TChar16 splitter);
   void AppendSplit(TChar16C* pValue, TChar16C* pSplitter);
   void Push(TChar16C* pValue);
   void Push(const TString16PtrC& value);
   void Remove(TChar16C* pValue);
   void Remove(const MString16s& strings);
   void Delete(TInt index);
   void Split(TChar16C* pValue, TChar16 splitter);
   void Split(TChar16C* pValue, TChar16C* pSplitter);
   void Unpack(TChar16C* pPack);
   void Clear();
};

//============================================================
class MO_CM_DECLARE TString16s : public MString16s{
public:
   //------------------------------------------------------------
   TString16s(){
   }
   //------------------------------------------------------------
   TString16s(const MString16s& strings){
      Append(strings);
   }
   //------------------------------------------------------------
   TString16s(const TString16s& strings){
      Append(strings);
   }
   //------------------------------------------------------------
   MO_ABSTRACT ~TString16s(){
   }
public:
   //------------------------------------------------------------
   void operator=(const MString16s& strings){
      Assign(strings);
   }
   //------------------------------------------------------------
   void operator=(const TString16s& strings){
      Assign(strings);
   }
};

//============================================================
class MO_CM_DECLARE FString16s :
      public FObject,
      public MString16s{
public:
   //------------------------------------------------------------
   FString16s(){
   }
   //------------------------------------------------------------
   FString16s(const MString16s& strings){
      Append(strings);
   }
   //------------------------------------------------------------
   FString16s(const FString16s& strings){
      Append(strings);
   }
   //------------------------------------------------------------
   MO_ABSTRACT ~FString16s(){
   }
public:
   //------------------------------------------------------------
   void operator=(const MString16s& strings){
      Assign(strings);
   }
   //------------------------------------------------------------
   void operator=(const FString16s& strings){
      Assign(strings);
   }
};

//============================================================
// <T>只读链表迭代器。</T>
//
// @tool
//============================================================
class MO_CM_DECLARE MProperties16IteratorC{
public:
   TInt _index;
   TInt _count;
   FString16s* _pNames;
   FString16s* _pValues;
public:
   //------------------------------------------------------------
   // 构造只读迭代器
   MO_INLINE MProperties16IteratorC(){
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   MO_INLINE MProperties16IteratorC(const MProperties16IteratorC& iterator){
   }
   //------------------------------------------------------------
   // 构造只读迭代器
   MO_INLINE MProperties16IteratorC(TInt count, FString16s* pNames, FString16s* pValues){
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
class MO_CM_DECLARE MProperties16Iterator : public MProperties16IteratorC{
public:
   //------------------------------------------------------------
   // 构造链表迭代器
   MProperties16Iterator(){
   }
   //------------------------------------------------------------
   // 构造链表迭代器
   MProperties16Iterator(const MProperties16IteratorC& iterator){
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
class MO_CM_DECLARE MProperties16{
protected:
   TInt _count;
   FString16s* _pNames;
   FString16s* _pValues;
public:
   MProperties16();
   MO_ABSTRACT ~MProperties16();
public:
   TChar16C* operator[](TChar16C* pName);
public:
   TBool IsEmpty() const;
   TInt Count() const;
   TBool Contains(TChar16C* pName) const;
   TBool ContainsValue(TChar16C* pValue) const;
   FString16s* Names();
   FString16s* Values();
public:
   TChar16C* Name(TInt index) const;
   void SetName(TInt index, TChar16C* pName) const;
   TChar16C* Value(TInt index) const;
   void SetValue(TInt index, TChar16C* pValue) const;
   TChar16C* Get(TChar16C* pName) const;
   TChar16C* Find(TChar16C* pName) const;
   TChar16C* FindNvl(TChar16C* pName, TChar16C* pDefault) const;
   TChar16C* Search(TChar16C* pValue) const;
   void Join(FString16* pValue, TChar16 nameSpliter, TChar16 valueSpliter);
   void Join(FString16* pValue, TChar16C* pNameSpliter, TChar16C* valueSpliter);
   TInt Pack(TChar16* pPack, TInt length);
   // TAttributesIteratorC IteratorC() const;
   TString16 Dump() const;
public:
   void EnsureSize(TInt size);
   void Assign(const MProperties16& properties);
   void Append(const MProperties16& properties);
   void AppendSplit(TChar16C* pValue, TChar16 nameSpliter, TChar16 valueSpliter);
   void AppendSplit(TChar16C* pValue, TChar16C* pNameSpliter, TChar16C* valueSpliter);
   void Add(TChar16C* pName, TChar16C* pValue);
   void Set(TChar16C* pName, TChar16C* pValue);
   void Split(TChar16C* pValue, TChar16 nameSpliter, TChar16 valueSpliter);
   void Split(TChar16C* pValue, TChar16C* pNameSpliter, TChar16C* valueSpliter);
   void Unpack(TChar16C* pPack);
   TString16 Remove(TChar16C* pName);
   void Clear();
};

//============================================================
// <T>属性表对象。</T>
//============================================================
class MO_CM_DECLARE TProperties16 : public MProperties16{
public:
   //------------------------------------------------------------
   // <T>创建属性表对象。</T>
   TProperties16(){
   }
   //------------------------------------------------------------
   // <T>创建属性表对象。</T>
   TProperties16(TInt capacity){
      EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   TProperties16(const MProperties16& properties){
      Append(properties);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   TProperties16(const TProperties16& properties){
      Append(properties);
   }
   //------------------------------------------------------------
   // <T>析构属性表。</T>
   MO_ABSTRACT ~TProperties16(){
   }
};

//============================================================
// <T>属性表对象。</T>
//============================================================
class MO_CM_DECLARE FProperties16 :
      public FObject,
      public MProperties16{
public:
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FProperties16(){
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FProperties16(TInt capacity){
      EnsureSize(capacity);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FProperties16(const MProperties16& properties){
      Append(properties);
   }
   //------------------------------------------------------------
   // <T>构造属性表。</T>
   FProperties16(const FProperties16& properties){
      Append(properties);
   }
   //------------------------------------------------------------
   // <T>析构属性表。</T>
   MO_ABSTRACT ~FProperties16(){
   }
};

//============================================================
// <T>ANSI字符串工具类。</T>
//============================================================
class MO_CM_DECLARE RString16{
public:
   static TChar16C* EmptyPtr;
   static TString16 EmptyString;
public:
   static TInt Length(TChar16C* pValue);
   static TBool Equals(TChar16C* pSource, TChar16C* pTarget);
   static TBool Constains(TChar16C* pSource, TChar16C* pValue);
   static TInt Find(TChar16C* pSource, TChar16C* pValue);
   static THashCode MakeHashCode(TChar16C* pValues);
   static THashCode MakeNocaseHashCode(TChar16C* pValues);
public:
   static TBool AllocCopy(TChar16C* pValues, TChar16** ppTarget);
   static TBool AllocFree(TChar16* pTarget);
   static TInt SafeCopy(TChar16* pTarget, TSize size, TChar8C* pValue);
   static TInt SafeCopy(TChar16* pTarget, TSize size, TChar16C* pValue);
   static TInt ForceCopy(TChar16* pTarget, TSize size, TChar16C* pValue);
   static TInt ReplaceAll(TChar16* pValues, TChar16* pSource, TChar16* pTarget, TChar16* pBuffer, TInt bufferLength);
public:
   static TInt ConvertToString8(TChar16C* pValue);
   static TInt ConvertToString8(TChar8* pTarget, TInt capacity, TChar16C* pValue);
   static TInt ConvertToString16(TChar16C* pValue);
   static TInt ConvertToString16(TChar16* pTarget, TInt capacity, TChar16C* pValue);
   static TInt ConvertToString32(TChar16C* pValue);
   static TInt ConvertToString32(TChar32* pTarget, TInt capacity, TChar16C* pValue);
};

MO_NAMESPACE_END

#endif // __MO_CM_STRING16_H__
