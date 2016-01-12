#ifndef __MO_CM_LANGUAGE_H__
#define __MO_CM_LANGUAGE_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_STRING_H__
#include "MoCmString.h"
#endif // __MO_CM_STRING_H__

#ifndef __MO_CM_PROPERTY_H__
#include "MoCmProperty.h"
#endif // __MO_CM_PROPERTY_H__

#define MO_DATE_MICROSECONDS_PER_DAY    86400000000LL
#define MO_DATE_MICROSECONDS_PER_HOUR    3600000000LL
#define MO_DATE_MICROSECONDS_PER_MINUTE    60000000L
#define MO_DATE_MICROSECONDS_PER_SECONDE    1000000L
#define MO_DATE_MILLISECONDS_PER_DAY       86400000
#define MO_DATE_MILLISECONDS_PER_HOUR       3600000
#define MO_DATE_MILLISECONDS_PER_MINUTE       60000
#define MO_DATE_MILLISECONDS_PER_SECONDE       1000
#define MO_DATE_SECONDS_PER_DAY               86400
#define MO_DATE_SECONDS_PER_HOUR               3600
#define MO_DATE_SECONDS_PER_MINUTE               60

#define MO_PROPERTY_MAXCNT                     1024

MO_NAMESPACE_BEGIN

//============================================================
// <T>对象集合。</T>
//============================================================
template <typename T>
class FObjects : public FVector<T>{
public:
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FObjects(){
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FObjects(TInt capacity){
      this->InnerResize(capacity, EFalse, EFalse, EFalse);
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FObjects(const T* pValues, TInt count){
      this->Assign(pValues, count);
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FObjects(const TPtrC<T>& ptr){
      this->Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FObjects(const MVector<T>& values){
      this->Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>构造变长数组。</T>
   FObjects(const FObjects<T>& values){
      this->Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>析构变长数组。</T>
   MO_ABSTRACT ~FObjects(){
   }
public:
   //------------------------------------------------------------
   // <T>将当前内容设置为指定数据指针。</T>
   MO_INLINE void operator=(const TPtrC<T>& ptr){
      this->Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前内容设置为指定数据数组。</T>
   MO_INLINE void operator=(const MVector<T>& values){
      this->Assign(values.MemoryC(), values.Count());
   }
   //------------------------------------------------------------
   // <T>将当前内容设置为指定数据数组。</T>
   MO_INLINE void operator=(const FVector<T>& values){
      this->Assign(values.MemoryC(), values.Count());
   }
};
//------------------------------------------------------------
typedef MO_CM_DECLARE FList<FObject*> FObjectList;
typedef MO_CM_DECLARE FVector<FObject*> FObjectVector;
typedef MO_CM_DECLARE FSet<TUint32, FObject*> FObjectSet;

//============================================================
// <T>整数操作的引用类。</T>
//============================================================
class MO_CM_DECLARE RInt : public RType<TInt>{
public:
   static TBool IsInteger(TCharC* pValue);
public:
   static TInt InRange(TInt value, TInt min, TInt max);
   static TInt InBetween(TInt value, TInt min, TInt max);
public:
   static TInt Parse8(TChar8C* pValue);
   static TInt Parse16(TChar16C* pValue);
   static TInt Parse32(TChar32C* pValue);
   static TInt Parse(TCharC* pValue);
   static TInt ParseNvl(TCharC* pValue);
public:
   static TChar8C* ToString8(TInt value, TChar8* pBuffer, TInt capacity);
   static TChar16C* ToString16(TInt value, TChar16* pBuffer, TInt capacity);
   static TChar32C* ToString32(TInt value, TChar32* pBuffer, TInt capacity);
   static TCharC* ToString(TInt value, TChar* pBuffer, TInt capacity);
   static TCharC* ToHexString(TInt value, TChar* pBuffer, TInt capacity);
public:
   static TCharC* FormatCapacity(TInt64 size, TChar* pBuffer, TInt capacity);
   static TInt CountDigit(TInt value);
};

//============================================================
// <T>无符号整数操作的引用类。</T>
//============================================================
class MO_CM_DECLARE RUint : public RType<TUint>{
public:
   static TUint InRange(TUint value, TUint min, TUint max);
   static TUint InBetween(TUint value, TUint min, TUint max);
public:
   static TUint Parse8(TChar8C* pValue);
   static TUint Parse16(TChar16C* pValue);
   static TUint Parse32(TChar32C* pValue);
   static TUint Parse(TCharC* pValue);
   static TUint ParseNvl(TCharC* pValue);
   static TUint ParseHex(TCharC* pValue);
   static TUint ParseHexNvl(TCharC* pValue);
public:
   static TChar8C* ToString8(TUint value, TChar8* pBuffer, TInt capacity);
   static TChar16C* ToString16(TUint value, TChar16* pBuffer, TInt capacity);
   static TChar32C* ToString32(TUint value, TChar32* pBuffer, TInt capacity);
   static TCharC* ToString(TUint value, TChar* pBuffer, TInt capacity);
   static TCharC* ToHexString(TUint value, TChar* pBuffer, TInt capacity);
};

//============================================================
// <T>时间段操作类。</T>
//============================================================
class MO_CM_DECLARE RTimeSpan{
public:
   //------------------------------------------------------------
   // <T>获得间隔。</T>
   static MO_INLINE TInt CalculateTick(TTimeTick beginTick, TTimeTick endTick){
      return (TInt)(endTick - beginTick);
   }
   //------------------------------------------------------------
   // <T>获得间隔秒。</T>
   static MO_INLINE TInt CalculateSecond(TTimeTick beginTick, TTimeTick endTick){
      return (TInt)((endTick - beginTick) / 1000000LL);
   }
   //------------------------------------------------------------
   // <T>获得间隔秒。</T>
   static MO_INLINE TFloat CalculateFloatSecond(TTimeTick beginTick, TTimeTick endTick){
      return (TFloat)(endTick - beginTick) / 1000000.0f;
   }
};

//============================================================
// <T>时间日期结构。</T>
//============================================================
struct SDateTime{
   TInt second;			/* Seconds.	[0-60] (1 leap second) */
   TInt minute;			/* Minutes.	[0-59] */
   TInt hour;			/* Hours.	[0-23] */
   TInt day;			/* Day.		[1-31] */
   TInt month;			/* Month.	[0-11] */
   TInt year;			/* Year	- 1900.  */
   TInt dayWeek;		/* Day of week.	[0-6] */
   TInt dayYear;		/* Days in year.[0-365]	*/
};

//============================================================
// <T>时间日期工具类。</T>
// <P>描述定义：</P>
// <P>%Y ：4位长年，      0000-9999</P>
// <P>%y ：2位长年，      00-99</P>
// <P>%m ：2位长12月，    01-12</P>
// <P>%d ：2位长31日，    01-31</P>
// <P>%j ：3位长第几日，  001-366</P>
// <P>%H ：2位长24小时制，00-23</P>
// <P>%I ：2位长12小时制，01-12</P>
// <P>%M ：2位长分钟，    00-59</P>
// <P>%S ：2位长秒，      00-59</P>
// <P>%s ：3位长毫秒，    000-999</P>
//
//============================================================
class MO_CM_DECLARE RDateTime{
public:
   static TCharC* DefaultFormat;
   static TCharC* DefaultFormatT;
public:
   static TDateTime Current();
   static TDateTime Parse(TCharC* pValue);
   static TDateTime Parse(TCharC* pValue, TCharC* pFormat);
   static TDateTime ParseNvl(TCharC* pValue);
   static TDateTime ParseNvl(TCharC* pValue, TCharC* pFormat);
public:
   static TCharC* ToString(TChar* pBuffer, TSize length, TDateTime dateTime, TCharC* pFormat);
   static TUint32 To32(TDateTime dateTime);
   static TUint64 To64(TUint32 dateTime);
public:
   static void Split(TDateTime current, SDateTime& dateTime);
   static TDateTime Mktime(SDateTime& dateTime);
	static TBool IsTheNextDay( TDateTime dayEarly, TDateTime dayLate );
   static TInt GetNumberOfWeek(SDateTime& dateTime);
};

//============================================================
// <T>时间刻度。</T>
// <P>本身没有意义，用来记录时间差。</P>
//
// @manager
// @source RDateTime.cpp
// @history 100128 MAOCY 创建
//============================================================
class MO_CM_DECLARE RTimeTick{
#ifdef _MO_WINDOWS
protected:
   static TUint64 _frequency;
#endif
public:
   static TTimeTick Current();
   static TFsTimeTick Format(TTimeTick tick);
};

//============================================================
// <T>唯一编号类。</T>
//
// @manager
// @history 110426 MAOCY 创建
//============================================================
typedef MO_CM_DECLARE TFixString<40> TGuid;

//============================================================
// <T>唯一编号工具类。</T>
//
// @manager
// @history 110426 MAOCY 创建
//============================================================
class MO_CM_DECLARE RGuid{
public:
   static TGuid Generate();
};

//============================================================
// <T>随机数工具类。</T>
//
// @manager
// @history 100304 MAOCY 创建
//============================================================
class MO_CM_DECLARE RRandom{
protected:
   static TUint32 _seed;
public:
   static void Initialize();
   static TInt Get();
   static TInt Get(TInt min, TInt max);
   static TInt Generator();
   static TInt Generator(TInt min, TInt max);
   static TFloat GeneratorFloat();
   static TFloat GeneratorFloat(TFloat min, TFloat max);
};

//============================================================
// <T>数据转换的引用类。</T>
//
// @reference
// @history 110703 MAOCY 创建
//============================================================
class MO_CM_DECLARE RTypeConverter{
public:
   static TBool ToBool(EType typeCd, TAny* pValue, TBool& value);
   static TBool ToInt8(EType typeCd, TAny* pValue, TInt8& value);
   static TBool ToInt16(EType typeCd, TAny* pValue, TInt16& value);
   static TBool ToInt32(EType typeCd, TAny* pValue, TInt32& value);
   static TBool ToInt64(EType typeCd, TAny* pValue, TInt64& value);
   static TBool ToUint8(EType typeCd, TAny* pValue, TUint8& value);
   static TBool ToUint16(EType typeCd, TAny* pValue, TUint16& value);
   static TBool ToUint32(EType typeCd, TAny* pValue, TUint32& value);
   static TBool ToUint64(EType typeCd, TAny* pValue, TUint64& value);
   static TBool ToFloat(EType typeCd, TAny* pValue, TFloat& value);
   static TBool ToDouble(EType typeCd, TAny* pValue, TDouble& value);
   static TBool ToDate(EType typeCd, TAny* pValue, TDate& value);
   static TBool ToTime(EType typeCd, TAny* pValue, TTime& value);
   static TBool ToTick(EType typeCd, TAny* pValue, TTick& value);
   static TBool ToSpan(EType typeCd, TAny* pValue, TSpan& value);
   static TBool ToDateTime(EType typeCd, TAny* pValue, TDateTime& value);
   static TBool ToTimeTick(EType typeCd, TAny* pValue, TTimeTick& value);
   static TBool ToTimeSpan(EType typeCd, TAny* pValue, TTimeSpan& value);
   static TBool ToString(EType typeCd, TAny* pValue, TCharC** ppValue);
public:
   static TBool Convert(EType sourceTypeCd, TAny* pSource, EType targetTypeCd, TAny** pTarget);
};

//============================================================
// <T>线程计数器。</T>
//
// @tool
// @history 091214 MAOCY 创建
//============================================================
class MO_CM_DECLARE TThreadCounter{
private:
   TInt _value;
public:
   TThreadCounter();
public:
   TInt Get();
   void Set(TInt value);
   void Increment();
   void Decrement();
};

//============================================================
// <T>命名对象基类。</T>
//============================================================
class MO_CM_DECLARE MNamed{
protected:
   TFsName _name;
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   TCharC* Name() const{
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   void SetName(TCharC* pName){
      _name = pName;
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_LANGUAGE_H__
