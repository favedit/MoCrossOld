#ifndef __MO_CR_COMMON_H__
#define __MO_CR_COMMON_H__

#ifndef __MO_COMMON_H__
#include <MoCommon.h>
#endif // __MO_COMMON_H__

/// @define 导出定义
#ifdef _MO_CR_EXPORTS
#define MO_CR_DECLARE MO_EXPORT
#else
#define MO_CR_DECLARE MO_IMPORT
#endif

#define MO_FS_RECORD_ID_LENGTH   32
#define MO_FS_OBJECT_ID_LENGTH   32
#define MO_FS_TEMPLATE_ID_LENGTH 32
#define MO_FS_RESOURCE_ID_LENGTH 32
#define MO_FS_GROUP_ID_LENGTH    32
#define MO_FS_LINK_ID_LENGTH     64
#define MO_FS_UNIQUE_ID_LENGTH   64

MO_NAMESPACE_BEGIN

//============================================================
// <T>分组编号。</T>
typedef TUint32 TGroupId;
// <T>对象编号。</T>
typedef TUint32 TObjectId;
// <T>模板编号。</T>
typedef TUint32 TTemplateId;
// <T>资源编号。</T>
typedef TUint32 TResourceId;
// <T>关联编号。</T>
typedef TUint128 TLinkId;
// <T>唯一编号。</T>
typedef TUint128 TUniqueId;

//============================================================
// <T>分组编号</T>
// [8Bit] = 256 分组类型 (0类型无效)
// [8Bit] = 256 分组编号 (从0开始)
// [8Bit] = 256 项目类型 (0类型无效)
// [8Bit] = 256 项目编号 (从0开始)
//============================================================
union SGroupData{
   TUint32 value;
   struct{
      TUint16 itemHandle;
      TUint16 groupHandle;
   } handles;
   struct{
      TUint8 itemId;
      TUint8 itemType;
      TUint8 groupId;
      TUint8 groupType;
   } items;
};
//------------------------------------------------------------
struct MO_CR_DECLARE SGroupId{
public:
   SGroupData data;
public:
   //------------------------------------------------------------
   SGroupId(){
      data.value = 0;
   }
   //------------------------------------------------------------
   SGroupId(TGroupId id){
      data.value = id;
   }
   //------------------------------------------------------------
   SGroupId(TUint16 groupHandle, TUint16 itemHandle){
      data.handles.groupHandle = groupHandle;
      data.handles.itemHandle = itemHandle;
   }
   //------------------------------------------------------------
   SGroupId(TUint8 groupType, TUint8 groupId, TUint8 itemType, TUint8 itemId){
      data.items.groupType = groupType;
      data.items.groupId = groupId;
      data.items.itemType = itemType;
      data.items.itemId = itemId;
   }
public:
   //------------------------------------------------------------
   MO_INLINE void operator=(TGroupId value){
      data.value = value;
   }
   //------------------------------------------------------------
   MO_INLINE TBool operator==(TGroupId value){
      return (data.value == value);
   }
   //------------------------------------------------------------
   MO_INLINE TBool operator!=(TGroupId value){
      return (data.value != value);
   }
   //------------------------------------------------------------
   MO_INLINE operator TGroupId(){
      return data.value;
   }
public:
   //------------------------------------------------------------
   MO_INLINE TUint16 GroupHandle(){
      return data.handles.groupHandle;
   };
   //------------------------------------------------------------
   MO_INLINE void SetGroupHandle(TUint16 groupHandle){
      data.handles.groupHandle = groupHandle;
   };
   //------------------------------------------------------------
   MO_INLINE TUint16 ItemHandle(){
      return data.handles.itemHandle;
   };
   //------------------------------------------------------------
   MO_INLINE void SetItemHandle(TUint16 itemHandle){
      data.handles.itemHandle = itemHandle;
   };
   //------------------------------------------------------------
   MO_INLINE TUint8 GroupType(){
      return data.items.groupType;
   };
   //------------------------------------------------------------
   MO_INLINE void SetGroupType(TUint8 groupType){
      data.items.groupType = groupType;
   };
   //------------------------------------------------------------
   MO_INLINE TUint8 GroupId(){
      return data.items.groupId;
   };
   //------------------------------------------------------------
   MO_INLINE void SetGroupId(TUint8 groupId){
      data.items.groupId = groupId;
   };
   //------------------------------------------------------------
   MO_INLINE TUint8 ItemType(){
      return data.items.itemType;
   };
   //------------------------------------------------------------
   MO_INLINE void SetItemType(TUint8 itemType){
      data.items.itemType = itemType;
   };
   //------------------------------------------------------------
   MO_INLINE TUint8 ItemId(){
      return data.items.itemId;
   };
   //------------------------------------------------------------
   MO_INLINE void SetItemId(TUint8 itemId){
      data.items.itemId = itemId;
   };
public:
   //------------------------------------------------------------
   MO_INLINE TBool IsValid(){
      return (data.items.groupType > 0);
   };
   //------------------------------------------------------------
   MO_INLINE TGroupId Get(){
      return data.value;
   }
   //------------------------------------------------------------
   MO_INLINE void Set(TGroupId value){
      data.value = value;
   }
   //------------------------------------------------------------
   MO_INLINE void Clear(){
      data.value = 0;
   }
public:
   //------------------------------------------------------------
   TCharC* ToDisplay(TChar* pText, TSize capacity){
      TStringRefer text(pText, capacity);
      text.AssignFormat(TC("%02X:%02X-%02X:%02X"),
            data.items.groupType, data.items.groupId,
            data.items.itemType, data.items.itemId);
      return pText;
   }
};

//============================================================
// <T>对象编号</T>
// [ 8Bit] = 256   类型 (0类型无效)
// [ 8Bit] = 256   组号 (从0开始)
// [16Bit] = 65535 索引 (从0开始)
//============================================================
union SObjectData{
   TUint32 value;
   struct{
      TUint16 index;
      TUint8 group;
      TUint8 type;
   } items;
};
//------------------------------------------------------------
struct MO_CR_DECLARE SObjectId{
public:
   SObjectData data;
public:
   //------------------------------------------------------------
   SObjectId(){
      data.value = 0;
   }
   //------------------------------------------------------------
   SObjectId(TObjectId id){
      data.value = id;
   }
   //------------------------------------------------------------
   SObjectId(TUint8 type, TUint32 handle){
      data.items.type = type;
      data.items.group = (TUint8)(handle >> 16);
      data.items.index = (TUint16)(handle & 0xFFFF);
   }
   //------------------------------------------------------------
   SObjectId(TUint8 type, TUint8 group, TUint16 index){
      data.items.type = type;
      data.items.group = group;
      data.items.index = index;
   }
public:
   //------------------------------------------------------------
   MO_INLINE void operator=(TObjectId value){
      data.value = value;
   }
   //------------------------------------------------------------
   MO_INLINE TBool operator==(TObjectId value){
      return (data.value == value);
   }
   //------------------------------------------------------------
   MO_INLINE TBool operator!=(TObjectId value){
      return (data.value != value);
   }
   //------------------------------------------------------------
   MO_INLINE operator TObjectId(){
      return data.value;
   }
public:
   //------------------------------------------------------------
   MO_INLINE TUint8 Type(){
      return data.items.type;
   };
   //------------------------------------------------------------
   MO_INLINE void SetType(TUint8 type){
      data.items.type = type;
   };
   //------------------------------------------------------------
   MO_INLINE TUint8 Group(){
      return data.items.group;
   };
   //------------------------------------------------------------
   MO_INLINE void SetGroup(TUint8 group){
      data.items.group = group;
   };
   //------------------------------------------------------------
   MO_INLINE TUint16 Index(){
      return data.items.index;
   };
   //------------------------------------------------------------
   MO_INLINE void SetIndex(TUint16 index){
      data.items.index = index;
   };
public:
   //------------------------------------------------------------
   MO_INLINE TUint32 Handle(){
      return (data.items.group << 16) + data.items.index;
   };
   //------------------------------------------------------------
   MO_INLINE void SetHandle(TUint32 handle){
      data.items.group = (handle >> 16) & 0x000000FF;
      data.items.index = (handle & 0x0000FFFF);
   };
public:
   //------------------------------------------------------------
   MO_INLINE TBool IsValid(){
      return (data.items.type > 0);
   };
   //------------------------------------------------------------
   MO_INLINE TObjectId Get(){
      return data.value;
   }
   //------------------------------------------------------------
   MO_INLINE void Set(TObjectId value){
      data.value = value;
   }
   //------------------------------------------------------------
   MO_INLINE void Set(TUint8 type, TUint32 handle){
      data.items.type = type;
      data.items.group = (TUint8)(handle >> 16);
      data.items.index = (TUint16)(handle & 0xFFFF);
   }
   //------------------------------------------------------------
   MO_INLINE void Set(TUint8 type, TUint8 group, TUint16 index){
      data.items.type = type;
      data.items.group = group;
      data.items.index = index;
   }
   //------------------------------------------------------------
   MO_INLINE void Clear(){
      data.value = 0;
   }
public:
   //------------------------------------------------------------
   TCharC* ToDisplay(TChar* pText, TSize capacity){
      TStringRefer text(pText, capacity);
      text.AssignFormat(TC("%02X-%02X-%04X(%d)"),
            data.items.type,
            data.items.group,
            data.items.index,
            Handle());
      return pText;
   }
};

//============================================================
// <T>关联编号</T>
// [32Bit] = 整数编号
// [32Bit] = 对象编号
//============================================================
union SLinkData{
   SUint128Base value;
   struct{
      SObjectData objectId;
      TUint32 flag;
      TRecordId code;
   } items;
};
//------------------------------------------------------------
struct MO_CR_DECLARE SLinkId{
public:
   SLinkData data;
public:
   //------------------------------------------------------------
   SLinkId(){
      data.value.Clear();
   }
   //------------------------------------------------------------
   SLinkId(TLinkId value){
      data.value = value;
   }
   //------------------------------------------------------------
   SLinkId(TRecordId code, TObjectId objectId){
      data.items.code = code;
      data.items.objectId.value = objectId;
   }
public:
   //------------------------------------------------------------
   MO_INLINE void operator=(TLinkId value){
      data.value = value;
   }
   //------------------------------------------------------------
   MO_INLINE TBool operator==(TLinkId value){
      return (data.value == value);
   }
   //------------------------------------------------------------
   MO_INLINE TBool operator!=(TLinkId value){
      return (data.value != value);
   }
   //------------------------------------------------------------
   MO_INLINE operator TLinkId(){
      TLinkId value;
      value.high = data.value.high;
      value.lower = data.value.lower;
      return value;
   }
public:
   //------------------------------------------------------------
   MO_INLINE TBool IsCode(TRecordId code){
      return (data.items.code == code);
   };
   //------------------------------------------------------------
   MO_INLINE TRecordId Code(){
      return data.items.code;
   };
   //------------------------------------------------------------
   MO_INLINE void SetCode(TRecordId code){
      data.items.code = code;
   };
   //------------------------------------------------------------
   MO_INLINE SObjectData& Object(){
      return data.items.objectId;
   };
   //------------------------------------------------------------
   MO_INLINE TBool IsObjectId(TObjectId objectId){
      return (data.items.objectId.value == objectId);
   };
   //------------------------------------------------------------
   MO_INLINE TObjectId ObjectId(){
      return data.items.objectId.value;
   };
   //------------------------------------------------------------
   MO_INLINE void SetObjectId(TObjectId objectId){
      data.items.objectId.value = objectId;
   };
   //------------------------------------------------------------
   MO_INLINE TUint8 Type(){
      return data.items.objectId.items.type;
   };
   //------------------------------------------------------------
   MO_INLINE void SetType(TUint8 type){
      data.items.objectId.items.type = type;
   };
   //------------------------------------------------------------
   MO_INLINE TUint32 Handle(){
      return (data.items.objectId.items.group << 16) + data.items.objectId.items.index;
   };
   //------------------------------------------------------------
   MO_INLINE void SetHandle(TUint32 handle){
      data.items.objectId.items.group = (handle >> 16) & 0x000000FF;
      data.items.objectId.items.index = handle & 0x0000FFFF;
   };
   //------------------------------------------------------------
   MO_INLINE TUint8 Group(){
      return data.items.objectId.items.group;
   };
   //------------------------------------------------------------
   MO_INLINE void SetGroup(TUint8 group){
      data.items.objectId.items.group = group;
   };
   //------------------------------------------------------------
   MO_INLINE TUint16 Index(){
      return data.items.objectId.items.index;
   };
   //------------------------------------------------------------
   MO_INLINE void SetIndex(TUint16 index){
      data.items.objectId.items.index = index;
   };
   //------------------------------------------------------------
   MO_INLINE TUint32 Flag(){
      return data.items.flag;
   };
   //------------------------------------------------------------
   MO_INLINE void SetFlag(TUint32 flag){
      data.items.flag = flag;
   };
public:
   //------------------------------------------------------------
   MO_INLINE TBool IsValid(){
      return (data.items.objectId.items.type > 0);
   };
   //------------------------------------------------------------
   MO_INLINE TLinkId Get(){
      TLinkId value;
      value.high = data.value.high;
      value.lower = data.value.lower;
      return value;
   }
   //------------------------------------------------------------
   MO_INLINE void Set(TLinkId value){
      data.value = value;
   }
   //------------------------------------------------------------
   MO_INLINE void Set(TRecordId code, SObjectId objectId){
      data.items.code = code;
      data.items.objectId.value = objectId.data.value;
   }
   //------------------------------------------------------------
   MO_INLINE void Clear(){
      data.value.Clear();
   }
public:
   //------------------------------------------------------------
   TCharC* ToDisplay(TChar* pText, TSize capacity){
      TStringRefer text(pText, capacity);
      text.AssignFormat("%02X-%02X-%04X(%d):%X:%016X(%" MO_FMT_INT64 ")",
            data.items.objectId.items.type,
            data.items.objectId.items.group,
            data.items.objectId.items.index,
            Handle(),
            data.items.flag,
            data.items.code,
            data.items.code);
      return pText;
   }
};

//============================================================
// <T>唯一编号</T>
// [ 6Bit] = 64        种类型(0类型无效)
// [ 2Bit] = 4         个场景服务器(从0开始)
// [24Bit] = 1677.7215 个实体(从0开始)
//============================================================
union SUniqueData{
   SUint128Base value;
   struct{
      SObjectData objectId;
      SGroupData groupId;
   } items;
};
//------------------------------------------------------------
struct MO_CR_DECLARE SUniqueId{
public:
   SUniqueData data;
public:
   //------------------------------------------------------------
   SUniqueId(){
      data.value.Clear();
   }
   //------------------------------------------------------------
   SUniqueId(TUniqueId value){
      data.value = value;
   }
   //------------------------------------------------------------
   SUniqueId(TGroupId groupId, TObjectId objectId){
      data.items.groupId.value = groupId;
      data.items.objectId.value = objectId;
   }
public:
   //------------------------------------------------------------
   MO_INLINE void operator=(TUniqueId value){
      data.value = value;
   }
   //------------------------------------------------------------
   MO_INLINE TBool operator==(TUniqueId value){
      return (data.value == value);
   }
   //------------------------------------------------------------
   MO_INLINE TBool operator!=(TUniqueId value){
      return (data.value != value);
   }
   //------------------------------------------------------------
   MO_INLINE operator TUniqueId(){
      TUniqueId value;
      value.high = data.value.high;
      value.lower = data.value.lower;
      return value;
   }
public:
   //------------------------------------------------------------
   MO_INLINE SGroupData& Group(){
      return data.items.groupId;
   };
   //------------------------------------------------------------
   MO_INLINE TGroupId GroupId(){
      return data.items.groupId.value;
   };
   //------------------------------------------------------------
   MO_INLINE void SetGroupId(TGroupId groupId){
      data.items.groupId.value = groupId;
   };
   //------------------------------------------------------------
   MO_INLINE SObjectData& Object(){
      return data.items.objectId;
   };
   //------------------------------------------------------------
   MO_INLINE TObjectId ObjectId(){
      return data.items.objectId.value;
   };
   //------------------------------------------------------------
   MO_INLINE void SetObjectId(TObjectId objectId){
      data.items.objectId.value = objectId;
   };
public:
   //------------------------------------------------------------
   MO_INLINE TBool IsValid(){
      return (data.items.groupId.items.groupType > 0) && (data.items.objectId.items.type > 0);
   };
   //------------------------------------------------------------
   MO_INLINE TBool IsGroupValid(){
      return (data.items.groupId.items.groupType > 0);
   };
   //------------------------------------------------------------
   MO_INLINE TBool IsObjectValid(){
      return (data.items.objectId.items.type > 0);
   };
   //------------------------------------------------------------
   MO_INLINE TUniqueId Get(){
      TUniqueId value;
      value.high = data.value.high;
      value.lower = data.value.lower;
      return value;
   }
   //------------------------------------------------------------
   MO_INLINE void Set(TUniqueId value){
      data.value = value;
   }
   //------------------------------------------------------------
   MO_INLINE void Clear(){
      data.value.Clear();
   }
public:
   //------------------------------------------------------------
   TCharC* ToDisplay(TChar* pText, TSize capacity){
      TStringRefer text(pText, capacity);
      text.AssignFormat(TC("%02X:%02X-%02X:%02X %02X-%02X-%04X"),
            data.items.groupId.items.groupType, data.items.groupId.items.groupId,
            data.items.groupId.items.itemType, data.items.groupId.items.itemId,
            data.items.objectId.items.type, data.items.objectId.items.group,
            data.items.objectId.items.index);
      return pText;
   }
};

//============================================================
// <T>记录编号字符串。</T>
//============================================================
class MO_CR_DECLARE TFsRecordId : public TFixString<MO_FS_RECORD_ID_LENGTH>{
protected:
   TRecordId _value;
public:
   TFsRecordId();
   TFsRecordId(TRecordId value);
public:
   static TRecordId Decode(TCharC* pValue, TInt length = -1);
   static TCharC* Encode(TRecordId value, TChar* pBuffer, TInt capacity);
public:
   void operator=(TRecordId value);
   void operator=(TCharC* pValue);
   void operator=(const TFsRecordId& value);
   void operator=(const TStringPtrC& value);
public:
   TRecordId Get() const;
   void Set(TRecordId value);
public:
   TCharC* Pack();
   TCharC* Format();
};

//============================================================
// <T>组编号字符串。</T>
//============================================================
class MO_CR_DECLARE TFsGroupId : public TFixString<MO_FS_GROUP_ID_LENGTH>{
protected:
   SGroupId _value;
public:
   TFsGroupId();
   TFsGroupId(const SGroupId& value);
public:
   void operator=(const TFsGroupId& value);
   void operator=(TCharC* pValue);
   void operator=(const SGroupId& value);
   void operator=(const TStringPtrC& value);
public:
   SGroupId Value() const;
   void SetValue(SGroupId value);
   TCharC* Format();
};

//============================================================
// <T>对象编号字符串。</T>
//============================================================
class MO_CR_DECLARE TFsObjectId : public TFixString<MO_FS_OBJECT_ID_LENGTH>{
protected:
   SObjectId _value;
public:
   TFsObjectId();
   TFsObjectId(const SObjectId& value);
public:
   static TObjectId Decode(TCharC* pValue, TInt length = -1);
   static TCharC* Encode(TObjectId value, TChar* pBuffer, TInt capacity);
public:
   void operator=(TCharC* pValue);
   void operator=(const SObjectId& value);
   void operator=(const TStringPtrC& value);
   void operator=(const TFsObjectId& value);
public:
   SObjectId Value() const;
   void SetValue(SObjectId value);
public:
   TCharC* Pack();
   TCharC* Format();
};

//============================================================
// <T>模板编号字符串。</T>
//============================================================
class MO_CR_DECLARE TFsTemplateId : public TFixString<MO_FS_TEMPLATE_ID_LENGTH>{
protected:
   TTemplateId _value;
public:
   TFsTemplateId();
   TFsTemplateId(TTemplateId value);
public:
   static TTemplateId Decode(TCharC* pValue, TInt length = -1);
   static TCharC* Encode(TTemplateId value, TChar* pBuffer, TInt capacity);
public:
   void operator=(const TFsTemplateId& value);
   void operator=(TCharC* pValue);
   void operator=(TTemplateId value);
   void operator=(const TStringPtrC& value);
public:
   TTemplateId Value() const;
   void SetValue(TTemplateId value);
public:
   TCharC* Pack();
   TCharC* Format();
};

//============================================================
// <T>资源编号字符串。</T>
//============================================================
class MO_CR_DECLARE TFsResourceId : public TFixString<MO_FS_RESOURCE_ID_LENGTH>{
protected:
   TResourceId _value;
public:
   TFsResourceId();
   TFsResourceId(TResourceId value);
public:
   void operator=(TResourceId value);
   void operator=(TCharC* pValue);
   void operator=(const TStringPtrC& value);
   void operator=(const TFsResourceId& value);
public:
   TResourceId Value() const;
   void SetValue(TResourceId value);
public:
   TCharC* Format();
};

//============================================================
// <T>关联编号字符串。</T>
//============================================================
class MO_CR_DECLARE TFsLinkId : public TFixString<MO_FS_LINK_ID_LENGTH>{
protected:
   SLinkId _value;
public:
   TFsLinkId();
   TFsLinkId(const SLinkId& value);
public:
   static TLinkId Decode(TCharC* pValue, TInt length = -1);
   static TCharC* Encode(TLinkId value, TChar* pBuffer, TInt capacity);
public:
   void operator=(const SLinkId& value);
   void operator=(TCharC* pValue);
   void operator=(const TStringPtrC& value);
   void operator=(const TFsLinkId& value);
public:
   SLinkId Value() const;
   void SetValue(SLinkId value);
public:
   TCharC* Pack();
   TCharC* Format();
};

//============================================================
// <T>唯一编号字符串。</T>
//============================================================
class MO_CR_DECLARE TFsUniqueId : public TFixString<MO_FS_UNIQUE_ID_LENGTH>{
protected:
   SUniqueId _value;
public:
   TFsUniqueId();
   TFsUniqueId(const SUniqueId& value);
public:
   static TUniqueId Decode(TCharC* pValue, TInt length = -1);
   static TCharC* Encode(TUniqueId value, TChar* pBuffer, TInt capacity);
public:
   void operator=(const TFsUniqueId& value);
   void operator=(TCharC* pValue);
   void operator=(const SUniqueId& value);
   void operator=(const TStringPtrC& value);
public:
   SUniqueId Value() const;
   void SetValue(SUniqueId value);
public:
   TCharC* Pack();
   TCharC* Format();
};

MO_NAMESPACE_END

#endif // __MO_CR_COMMON_H__
