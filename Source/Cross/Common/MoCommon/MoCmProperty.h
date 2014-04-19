#ifndef __MO_CM_PROPERTY_H__
#define __MO_CM_PROPERTY_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_COLLECTION_H__
#include "MoCmCollection.h"
#endif // __MO_CM_COLLECTION_H__

#ifndef __MO_CM_STRING_H__
#include "MoCmString.h"
#endif // __MO_CM_STRING_H__

#define MO_PROPERTY_MAXCNT                     1024

MO_NAMESPACE_BEGIN

//============================================================
// 类型定义
typedef TUint16 TPropertyId;

//============================================================
// 结构定义
class TProperty;
class TFsProperties;

//============================================================
// <T>属性检索方式。</T>
//============================================================
enum EPropertySearch{
   // @member 未知
   EPropertySearch_Unknown = 0,
   // @member 编号
   EPropertySearch_Id = 1,
   // @member 名称
   EPropertySearch_Name = 2,
};

//============================================================
// <T>属性修改方式。</T>
//============================================================
enum EPropertyModify{
   // @member 无操作
   EPropertyModify_None,
   // @member 获取
   EPropertyModify_Get,
   // @member 设置
   EPropertyModify_Set,
   // @member 修改
   EPropertyModify_Mod,
};

//============================================================
// <T>属性打包方式。</T>
//============================================================
enum EPropertyPack{
   // @member 未知
   EPropertyPack_Unknown = 0,
   // @member 编号
   EPropertyPack_Id = 1,
   // @member 名称
   EPropertyPack_Name = 2,
};

//============================================================
// <T>属性打包版本。</T>
//============================================================
enum EPropertyVersion{
   // @member A版本
   EPropertyVersion_A = 0,
   // @member 总数
   EPropertyVersion_Count = 1,
};

//============================================================
// <T>属性头信息。</T>
//============================================================
struct MO_CM_DECLARE SPropertyHead{
protected:
   // 属性编号
   TPropertyId _id;
   // 属性名称
   TFsName _name;
   // 属性类型
   EType _typeCd;
public:
   //------------------------------------------------------------
   // <T>构造属性头信息。</T>
   SPropertyHead(){
      _id = 0;
      _typeCd = EType_Any;
   }
public:
   //------------------------------------------------------------
   // <T>获得属性编号。</T>
   MO_INLINE TPropertyId Id() const{
      return _id;
   }
   //------------------------------------------------------------
   // <T>设置属性编号。</T>
   MO_INLINE void SetId(TPropertyId id){
      _id = id;
   }
   //------------------------------------------------------------
   // <T>获得属性名称。</T>
   MO_INLINE TCharC* Name() const{
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置属性名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>获得属性类型。</T>
   MO_INLINE EType TypeCd() const{
      return _typeCd;
   }
   //------------------------------------------------------------
   // <T>设置属性类型。</T>
   MO_INLINE void SetTypeCd(EType typeCd){
      _typeCd = typeCd;
   }
};

//============================================================
// <T>属性描述信息。</T>
//============================================================
struct MO_CM_DECLARE SPropertyInfo{
protected:
   TPropertyId _id; // 属性编号
   TCharC* _pName;  // 属性名称
   EType _typeCd;   // 数据类型
public:
   //------------------------------------------------------------
   // <T>构造属性描述信息。</T>
   SPropertyInfo(const SPropertyInfo& info){
   }
   //------------------------------------------------------------
   // <T>构造属性描述信息。</T>
   SPropertyInfo(TPropertyId id, TCharC* pName, EType typeCd){
      _id = id;
      _pName = pName;
      _typeCd = typeCd;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否指定属性编号。</T>
   MO_INLINE TBool IsId(TPropertyId id) const{
      return (_id == id);
   }
   //------------------------------------------------------------
   // <T>判断是否指定属性名称。</T>
   MO_INLINE TBool IsName(TCharC* pName) const{
      return RString::Equals(_pName, pName);
   }
   //------------------------------------------------------------
   // <T>判断是否指定数据类型。</T>
   MO_INLINE TBool IsTypeCd(EType typeCd) const{
      return (_typeCd == typeCd);
   }
public:
   //------------------------------------------------------------
   // <T>获得属性编号。</T>
   MO_INLINE TPropertyId Id() const{
      return _id;
   }
   //------------------------------------------------------------
   // <T>获得属性名称。</T>
   MO_INLINE TCharC* Name() const{
      return _pName;
   }
   //------------------------------------------------------------
   // <T>获得数据类型。</T>
   MO_INLINE EType TypeCd() const {
      return _typeCd;
   }
};

//============================================================
// <T>属性集合。</T>
//============================================================
class MO_CM_DECLARE IProperties{
public:
   //------------------------------------------------------------
   // <T>析构属性集合。</T>
   MO_ABSTRACT ~IProperties(){
   }
public:
   MO_VIRTUAL TBool PropertyGet(TProperty* pProperty, TPropertyId propertyId) = 0;
   MO_VIRTUAL TBool PropertyGet(TProperty* pProperty, TCharC* pName) = 0;
   MO_VIRTUAL TBool PropertySet(const TProperty* pProperty, EPropertySearch serachCd = EPropertySearch_Id) = 0;
   MO_VIRTUAL TBool PropertyGetAll(TFsProperties* pProperties) = 0;
   MO_VIRTUAL TBool PropertySetAll(TFsProperties* pProperties) = 0;
};

//============================================================
// <T>属性对象。</T>
//============================================================
class MO_CM_DECLARE TProperty{
protected:
   SPropertyInfo* _pInfo;
   TAny* _pData;
   EModify _modifyCd;
public:
   TProperty();
   TProperty(SPropertyInfo* pInfo);
   TProperty(SPropertyInfo* pInfo, TAny* pData);
   TProperty(SPropertyInfo* pInfo, EModify modifyCd, TAny* pData);
public:
   //------------------------------------------------------------
   // <T>关联属性信息。</T>
   MO_INLINE TBool Link(SPropertyInfo* pInfo, TAny* pData){
      _pInfo = pInfo;
      _pData = pData;
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>获得属性编号。</T>
   MO_INLINE TPropertyId PropertyId() const{
      if(NULL != _pInfo){
         return _pInfo->Id();
      }
      return 0;
   }
   //------------------------------------------------------------
   // <T>获得属性名称。</T>
   MO_INLINE TCharC* PropertyName() const{
      if(NULL != _pInfo){
         return _pInfo->Name();
      }
      return NULL;
   }
   //------------------------------------------------------------
   // <T>获得属性类型。</T>
   MO_INLINE EType PropertyTypeCd() const{
      if(NULL != _pInfo){
         return _pInfo->TypeCd();
      }
      return EType_Any;
   }
   //------------------------------------------------------------
   // <T>获得修改类型。</T>
   MO_INLINE EModify ModifyCd() const{
      return _modifyCd;
   }
   //------------------------------------------------------------
   // <T>设置修改类型。</T>
   MO_INLINE void SetModifyCd(EModify modifyCd){
      _modifyCd = modifyCd;
   }
public:
   TBool Attach(TProperty& property);
   TBool Assign(const TProperty* pProperty);
   TBool Modify(const TProperty* pProperty, EPropertyModify modifyCd);
public:
   TBool IsValid();
   SPropertyInfo* Info();
   void SetInfo(SPropertyInfo* pInfo);
   TAnyC* GetC();
   TAny* Get();
   void Set(TAny* pValue);
   void Set(SPropertyInfo* pInfo, TAny* pValue);
public:
   TBool AsBool();
   TInt8 AsInt8();
   TInt16 AsInt16();
   TInt32 AsInt32();
   TInt64 AsInt64();
   TUint8 AsUint8();
   TUint16 AsUint16();
   TUint32 AsUint32();
   TUint64 AsUint64();
   TFloat AsFloat();
   TDouble AsDouble();
   TDate AsDate();
   TTime AsTime();
   TTick AsTick();
   TSpan AsSpan();
   TDateTime AsDateTime();
   TTimeTick AsTimeTick();
   TTimeSpan AsTimeSpan();
   TCharC* AsString();
public:
   //------------------------------------------------------------
   template <typename T> T AsEnum(){
      return *(T*)Get();
   }
   //------------------------------------------------------------
   template <typename T> T AsSet(){
      return *(T*)Get();
   }
   //------------------------------------------------------------
   template <typename T> T* AsStruct(){
      return (T*)Get();
   }
   //------------------------------------------------------------
   template <typename T> T* AsObject(){
      return (T*)Get();
   }
};
//------------------------------------------------------------
class TFsProperties : public TFixVector<TProperty, MO_PROPERTY_MAXCNT>{
};

//============================================================
// <T>打包字符串。</T>
// <P>OptionEmpty为假的时候，不写入内容为默认值的属性。</P>
// <P>属性 name=value：{名称长度的长度}{名称长度}{名称}{内容长度的长度}{内容长度}{内容}。</P>
// <P>每个节点包含属性集合和节点集合，如果字母A开头就是属性集合，如果N开头就是节点集合，属性集合中不允许再包含节点集合。</P>
// <P>属性集合       ：A      {[属性]，[属性]，[属性]...}。</P>
// <P>节点集合       ：N[个数]{[节点]，[节点]，[节点]...}。</P>
//
// @tools
//============================================================
class MO_CM_DECLARE MPack : public MString{
protected:
   EPropertyVersion _versionCd;
   EPropertyPack _packCd;
   TBool _optionEmpty;
   TInt _position;
   SPropertyHead _readHead;
public:
   MPack();
   MO_ABSTRACT ~MPack();
public:
   //------------------------------------------------------------
   // <T>获得打包版本。</T>
   MO_INLINE EPropertyVersion Version(){
      return _versionCd;
   }
   //------------------------------------------------------------
   // <T>设置打包版本。</T>
   MO_INLINE void SetVersion(EPropertyVersion versionCd){
      _versionCd = versionCd;
   }
   //------------------------------------------------------------
   // <T>获得打包类型。</T>
   MO_INLINE EPropertyPack PackCd(){
      return _packCd;
   }
   //------------------------------------------------------------
   // <T>设置打包类型。</T>
   MO_INLINE void SetPackCd(EPropertyPack packCd){
      _packCd = packCd;
   }
   //------------------------------------------------------------
   // <T>获得允许空。</T>
   MO_INLINE TBool OptionEmpty(){
      return _optionEmpty;
   }
   //------------------------------------------------------------
   // <T>设置允许空。</T>
   MO_INLINE void SetOptionEmpty(TBool optionEmpty){
      _optionEmpty = optionEmpty;
   }
   //------------------------------------------------------------
   // <T>获得位置。</T>
   MO_INLINE TInt Position(){
      return _position;
   }
   //------------------------------------------------------------
   // <T>设置位置。</T>
   MO_INLINE void SetPosition(TInt position){
      _position = position;
   }
protected:
   TInt ReadChar();
   TInt ReadString(TChar* pValue, TInt length);
   void WriteChar(TChar value);
   void WriteString(TCharC* pValue, TInt length = -1);
public:
   TBool TestAttribute();
   TBool TestNode();
public:
   TBool ReadAttributeBegin();
   TBool ReadAttributeEnd();
   TBool ReadNodeBegin(const SPropertyInfo* pInfo);
   TBool ReadNodeEnd();
   TBool ReadCollectionBegin(TInt* pCount);
   TBool ReadCollectionEnd();
   TBool ReadPropertyHead(SPropertyHead* pHead);
public:
   TBool ReadPropertyBool(const SPropertyInfo* pInfo);
   TByte ReadPropertyByte(const SPropertyInfo* pInfo);
   TChar ReadPropertyChar(const SPropertyInfo* pInfo);
   TInt8 ReadPropertyInt8(const SPropertyInfo* pInfo);
   TInt16 ReadPropertyInt16(const SPropertyInfo* pInfo);
   TInt32 ReadPropertyInt32(const SPropertyInfo* pInfo);
   TInt64 ReadPropertyInt64(const SPropertyInfo* pInfo);
   TUint8 ReadPropertyUint8(const SPropertyInfo* pInfo);
   TUint16 ReadPropertyUint16(const SPropertyInfo* pInfo);
   TUint32 ReadPropertyUint32(const SPropertyInfo* pInfo);
   TUint64 ReadPropertyUint64(const SPropertyInfo* pInfo);
   TFloat ReadPropertyFloat(const SPropertyInfo* pInfo);
   TDouble ReadPropertyDouble(const SPropertyInfo* pInfo);
   TTimeTick ReadPropertyTimeTick(const SPropertyInfo* pInfo);
   TTimeSpan ReadPropertyTimeSpan(const SPropertyInfo* pInfo);
   TTimeSpan ReadPropertyDateTime(const SPropertyInfo* pInfo);
   TInt ReadPropertyEnum(const SPropertyInfo* pInfo);
   TInt ReadPropertySet(const SPropertyInfo* pInfo);
   TInt ReadPropertyString(const SPropertyInfo* pInfo, TChar* pValue, TInt capacity);
   TInt ReadPropertyFixString(const SPropertyInfo* pInfo, TChar* pValue, TInt length);
   TCharC* ReadPropertyType(const SPropertyInfo* pInfo, TChar* pValue, TInt capacity);
public:
   TBool UnpackBegin();
   TBool UnpackEnd();
public:
   TBool WriteInfo(const SPropertyInfo* pInfo);
   TBool WriteAttributeBegin();
   TBool WriteAttributeEnd();
   TBool WriteNodeBegin(const SPropertyInfo* pInfo);
   TBool WriteNodeBegin(const SPropertyInfo* pInfo, TInt count);
   TBool WriteNodeEnd();
   TBool WriteCollectionBegin(TInt count);
   TBool WriteCollectionEnd();
public:
   void WritePropertyBool(const SPropertyInfo* pInfo, TBool value);
   void WritePropertyByte(const SPropertyInfo* pInfo, TByte value);
   void WritePropertyChar(const SPropertyInfo* pInfo, TChar value);
   void WritePropertyInt8(const SPropertyInfo* pInfo, TInt8 value);
   void WritePropertyInt16(const SPropertyInfo* pInfo, TInt16 value);
   void WritePropertyInt32(const SPropertyInfo* pInfo, TInt32 value);
   void WritePropertyInt64(const SPropertyInfo* pInfo, TInt64 value);
   void WritePropertyUint8(const SPropertyInfo* pInfo, TUint8 value);
   void WritePropertyUint16(const SPropertyInfo* pInfo, TUint16 value);
   void WritePropertyUint32(const SPropertyInfo* pInfo, TUint32 value);
   void WritePropertyUint64(const SPropertyInfo* pInfo, TUint64 value);
   void WritePropertyFloat(const SPropertyInfo* pInfo, TFloat value);
   void WritePropertyDouble(const SPropertyInfo* pInfo, TDouble value);
   void WritePropertyTimeTick(const SPropertyInfo* pInfo, TTimeTick value);
   void WritePropertyTimeSpan(const SPropertyInfo* pInfo, TTimeSpan value);
   void WritePropertyDateTime(const SPropertyInfo* pInfo, TDateTime value);
   void WritePropertyEnum(const SPropertyInfo* pInfo, TInt value);
   void WritePropertySet(const SPropertyInfo* pInfo, TInt value);
   void WritePropertyString(const SPropertyInfo* pInfo, TCharC* pValue, TInt length = -1);
   void WritePropertyFixString(const SPropertyInfo* pInfo, TCharC* pValue, TInt length);
   void WritePropertyType(const SPropertyInfo* pInfo, TCharC* pValue);
public:
   TBool PackBegin();
   TBool PackEnd();
};

//============================================================
// <T>字符串打包工具。</T>
//
// @history 130422 MAOCY 创建
//============================================================
class MO_CM_DECLARE FPack :
      public FObject,
      public MPack{
public:
   FPack();
   FPack(EPropertyPack packCd);
   MO_ABSTRACT ~FPack();
protected:
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends);
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串的内容。</T>
   MO_INLINE void operator=(TCharC* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串对象的内容。</T>
   MO_INLINE void operator=(const TPtrC<TChar>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串对象的内容。</T>
   MO_INLINE void operator=(const MPack& value){
      Assign(value.MemoryC(), value.Length());
   }
};

//============================================================
// <T>字符串打包工具。</T>
//
// @history 130422 MAOCY 创建
//============================================================
class MO_CM_DECLARE TPack : public MPack{
protected:
   TChar _memory[MO_FS_PACK_LENGTH];
public:
   //------------------------------------------------------------
   // <T>构造字符串打包工具。</T>
   TPack(){
      InnerInitialize();
   }
   //------------------------------------------------------------
   // <T>构造字符串打包工具。</T>
   TPack(EPropertyPack packCd){
      InnerInitialize();
      _packCd = packCd;
   }
   //------------------------------------------------------------
   // <T>构造字符串打包工具。</T>
   TPack(TCharC* pValue, TInt length = -1){
      InnerInitialize();
      Assign(pValue, length);
   }
   //------------------------------------------------------------
   // <T>构造字符串打包工具。</T>
   TPack(const TPtrC<TChar>& ptr){
      InnerInitialize();
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造字符串打包工具。</T>
   TPack(const MPack& value){
      InnerInitialize();
      Assign(value.MemoryC(), value.Length());
   }
protected:
   //------------------------------------------------------------
   // <T>内部初始化。</T>
   MO_INLINE void InnerInitialize(){
      _pMemory = _memory;
      _capacity = MO_FS_PACK_LENGTH;
      _memory[0] = 0;
   }
   //------------------------------------------------------------
   // <T>调整内存大小。</T>
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends){
      MO_ASSERT(size <= MO_FS_PACK_LENGTH);
   }
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串的内容。</T>
   MO_INLINE void operator=(TCharC* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串对象的内容。</T>
   MO_INLINE void operator=(const TPtrC<TChar>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串对象的内容。</T>
   MO_INLINE void operator=(const MPack& value){
      Assign(value.MemoryC(), value.Length());
   }
public:
   //------------------------------------------------------------
   // <T>获得容纳长度。</T>
   MO_INLINE static TInt Size(){
      return MO_FS_PACK_LENGTH;
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_PROPERTY_H__
