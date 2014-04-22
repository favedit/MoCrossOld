#ifndef __MO_MT_FORMAT_H__
#define __MO_MT_FORMAT_H__

#ifndef __MO_MT_COMMON_H__
#include "MoMtCommon.h"
#endif // __MO_MT_COMMON_H__

#ifndef __MO_MT_GEOM_H__
#include "MoMtGeom.h"
#endif // __MO_MT_GEOM_H__

#ifndef __MO_MT_SIZE_H__
#include "MoMtSize.h"
#endif // __MO_MT_SIZE_H__

#ifndef __MO_MT_POINT_H__
#include "MoMtPoint.h"
#endif // __MO_MT_POINT_H__

#ifndef __MO_MT_VECTOR_H__
#include "MoMtVector.h"
#endif // __MO_MT_VECTOR_H__

#define MO_FMT_OBJECT_NAME TC("[%-40s]")

MO_NAMESPACE_BEGIN

//============================================================
class MO_MT_DECLARE TFsIntPoint2 : public TFixString<MO_FS_CODE_LENGTH>{
protected:
   SIntPoint2 _value;
public:
   TFsIntPoint2();
   TFsIntPoint2(const SIntPoint2& value);
public:
   void operator=(const TFsIntPoint2& value);
   void operator=(TCharC* pValue);
   void operator=(const SIntPoint2& value);
   void operator=(const TStringPtrC& value);
public:
   SIntPoint2 Value() const;
   void SetValue(SIntPoint2 value);
   TCharC* Format();
};

//============================================================
class MO_MT_DECLARE TFsIntPoint3 : public TFixString<MO_FS_CODE_LENGTH>{
protected:
   SIntPoint3 _value;
public:
   TFsIntPoint3();
   TFsIntPoint3(const SIntPoint3& value);
public:
   void operator=(const TFsIntPoint3& value);
   void operator=(TCharC* pValue);
   void operator=(const SIntPoint3& value);
   void operator=(const TStringPtrC& value);
public:
   SIntPoint3 Value() const;
   void SetValue(SIntPoint3 value);
   TCharC* Format();
};

//============================================================
class MO_MT_DECLARE TFsFloatRange : public TFixString<MO_FS_CODE_LENGTH>{
protected:
   SFloatRange _value;
public:
   TFsFloatRange();
   TFsFloatRange(const SFloatRange& value);
public:
   void operator=(const TFsFloatRange& value);
   void operator=(TCharC* pValue);
   void operator=(const SFloatRange& value);
   void operator=(const TStringPtrC& value);
public:
   SFloatRange Value() const;
   void SetValue(SFloatRange value);
   TCharC* Format();
};

//============================================================
class MO_MT_DECLARE TFsIntSize : public TFixString<MO_FS_CODE_LENGTH>{
protected:
   SIntSize2 _value;
public:
   TFsIntSize();
   TFsIntSize(const SIntSize2& value);
public:
   void operator=(const TFsIntSize& value);
   void operator=(TCharC* pValue);
   void operator=(const SIntSize2& value);
   void operator=(const TStringPtrC& value);
public:
   SIntSize2 Value() const;
   void SetValue(SIntSize2 value);
   TCharC* Format();
};

//============================================================
class MO_MT_DECLARE TFsFloatSize : public TFixString<MO_FS_CODE_LENGTH>{
protected:
   SFloatSize2 _value;
public:
   TFsFloatSize();
   TFsFloatSize(const SFloatSize2& value);
public:
   void operator=(const TFsFloatSize& value);
   void operator=(TCharC* pValue);
   void operator=(const SFloatSize2& value);
   void operator=(const TStringPtrC& value);
public:
   SFloatSize2 Value() const;
   void SetValue(SFloatSize2 value);
   TCharC* Format();
};

//============================================================
class MO_MT_DECLARE TFsFloatPoint3 : public TFixString<MO_FS_CODE_LENGTH>{
protected:
   SFloatPoint3 _value;
public:
   TFsFloatPoint3();
   TFsFloatPoint3(const SFloatPoint3& value);
public:
   void operator=(const TFsFloatPoint3& value);
   void operator=(TCharC* pValue);
   void operator=(const SFloatPoint3& value);
   void operator=(const TStringPtrC& value);
public:
   SFloatPoint3 Value() const;
   void SetValue(SFloatPoint3 value);
   TCharC* Format();
};

//============================================================
class MO_MT_DECLARE TFsFloatVector3 : public TFixString<MO_FS_CODE_LENGTH>{
protected:
   SFloatVector3 _value;
public:
   TFsFloatVector3();
   TFsFloatVector3(const SFloatVector3& value);
public:
   void operator=(const TFsFloatVector3& value);
   void operator=(TCharC* pValue);
   void operator=(const SFloatVector3& value);
   void operator=(const TStringPtrC& value);
public:
   SFloatVector3 Value() const;
   void SetValue(SFloatVector3 value);
   TCharC* Format();
};

MO_NAMESPACE_END

#endif // __MO_MT_FORMAT_H__
