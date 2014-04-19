#ifndef __MO_CM_FORMAT_H__
#define __MO_CM_FORMAT_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_STRING_H__
#include "MoCmString.h"
#endif // __MO_CM_STRING_H__

#define MO_FMT_OBJECT_NAME TC("[%-40s]")

MO_NAMESPACE_BEGIN

//============================================================
// <T>整数字符串。</T>
//
// @history 120522 MAOCY 创建
//============================================================
class MO_CM_DECLARE TFsInteger : public TFixString<MO_FS_INTEGER_LENGTH>{
protected:
   TInt _value;
public:
   TFsInteger();
   TFsInteger(TInt value);
   TFsInteger(TCharC* pValue);
   TFsInteger(const TStringPtrC& ptr);
   TFsInteger(const TFsInteger& value);
public:
   void operator=(TInt value);
   void operator=(TCharC* pValue);
   void operator=(const TStringPtrC& ptr);
   void operator=(const TFsInteger& value);
public:
   TInt Get();
   void Set(TInt value);
   TInt Parse();
   TInt Parse(TCharC* pValue);
   TCharC* ToString();
};

//============================================================
// <T>长整数字符串。</T>
//
// @history 130504 MAOCY 创建
//============================================================
class MO_CM_DECLARE TFsInteger64 : public TFixString<MO_FS_INTEGER64_LENGTH>{
protected:
   TInt64 _value;
public:
   TFsInteger64();
   TFsInteger64(TInt64 value);
   TFsInteger64(TCharC* pValue);
   TFsInteger64(const TStringPtrC& ptr);
   TFsInteger64(const TFsInteger64& value);
public:
   void operator=(TInt64 value);
   void operator=(TCharC* pValue);
   void operator=(const TStringPtrC& ptr);
   void operator=(const TFsInteger64& value);
public:
   TInt64 Get();
   void Set(TInt64 value);
   TInt64 Parse();
   TInt64 Parse(TCharC* pValue);
   TCharC* ToString();
};

//============================================================
// <T>浮点数字符串。</T>
//
// @history 100901 MAOCY 创建
//============================================================
class MO_CM_DECLARE TFsFloat : public TFixString<MO_FS_FLOAT_LENGTH>{
protected:
   TFloat _value;
public:
   TFsFloat();
   TFsFloat(TFloat value);
   TFsFloat(TCharC* pValue);
   TFsFloat(const TStringPtrC& ptr);
   TFsFloat(const TFsFloat& value);
public:
   void operator=(TFloat value);
   void operator=(TCharC* pValue);
   void operator=(const TStringPtrC& ptr);
   void operator=(const TFsFloat& value);
public:
   TFloat Get();
   void Set(TFloat value);
   TFloat Parse();
   TFloat Parse(TCharC* pValue);
   TCharC* ToString();
};

//============================================================
// <T>双精度数数字符串。</T>
//
// @history 120308 MAOCY 创建
//============================================================
class MO_CM_DECLARE TFsDouble : public TFixString<MO_FS_DOUBLE_LENGTH>{
protected:
   TDouble _value;
public:
   TFsDouble();
   TFsDouble(TDouble value);
   TFsDouble(TCharC* pValue);
   TFsDouble(const TStringPtrC& ptr);
   TFsDouble(const TFsDouble& value);
public:
   void operator=(TDouble value);
   void operator=(TCharC* pValue);
   void operator=(const TStringPtrC& ptr);
   void operator=(const TFsDouble& value);
public:
   TDouble Get();
   void Set(TDouble value);
   TDouble Parse();
   TDouble Parse(TCharC* pValue);
   TCharC* ToString();
};

//============================================================
// <T>时间日期类。</T>
//
// @history 100622 MAOCY 创建
//============================================================
class MO_CM_DECLARE TFsDateTime : public TFixString<MO_FS_DATETIME_LENGTH>{
protected:
   TDateTime _value;
public:
   TFsDateTime();
   TFsDateTime(TDateTime value);
   TFsDateTime(const TFsDateTime& value);
   TFsDateTime(TCharC* pValue, TInt length=-1);
   TFsDateTime(const TStringPtrC& value);
public:
   static TDateTime Decode(TCharC* pValue, TInt length = -1);
   static TCharC* Encode(TDateTime value, TChar* pBuffer, TInt capacity);
public:
   void operator=(TDateTime value);
   void operator=(TCharC* pValue);
   void operator=(const TFsDateTime& value);
   void operator=(const TStringPtrC& value);
public:
   TDateTime Value();
   void SetValue(TDateTime value);
   TCharC* Format();
   TCharC* Format(TCharC* pFormat);
   TBool Parse(TCharC* pValue, TCharC* pFormat);
public:
   TCharC* Pack();
};

MO_NAMESPACE_END

#endif // __MO_CM_FORMAT_H__
