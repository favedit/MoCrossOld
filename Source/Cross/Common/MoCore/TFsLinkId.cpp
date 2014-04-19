#include "MoCrCommon.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造关联编号字符串。</T>
//============================================================
TFsLinkId::TFsLinkId(){
}

//============================================================
// <T>构造关联编号字符串。</T>
//
// @param value 数据
//============================================================
TFsLinkId::TFsLinkId(const SLinkId& value){
   _value = value;
}

//============================================================
// <T>将字符串转换为关联编号。</T>
//
// @param pValue 字符串
// @param length 长度
// @return 关联编号
//============================================================
TLinkId TFsLinkId::Decode(TCharC* pValue, TInt length){
   TLinkId value;
   value.high = RUint64::ParseHex(pValue, 16);
   value.lower = RUint64::ParseHex(pValue + 16, 16);
   return value;
}

//============================================================
// <T>将关联编号转换为字符串。</T>
//
// @param value 关联编号
// @param pBuffer 字符串缓冲
// @param capacity 字符串容量
// @return 字符串
//============================================================
TCharC* TFsLinkId::Encode(TLinkId value, TChar* pBuffer, TInt capacity){
   RUint64::ToHexString(value.high, pBuffer, capacity);
   RUint64::ToHexString(value.lower, pBuffer + 16, capacity);
   pBuffer[32] = 0;
   return pBuffer;
}

//============================================================
// <T>设置数据内容。</T>
//
// @param value 数据
//============================================================
void TFsLinkId::operator=(const SLinkId& value){
   _value = value;
}

//============================================================
// <T>设置数据内容。</T>
//
// @param pValue 数据
//============================================================
void TFsLinkId::operator=(TCharC* pValue){
   this->Assign(pValue);
}

//============================================================
// <T>设置数据内容。</T>
//
// @param value 数据
//============================================================
void TFsLinkId::operator=(const TStringPtrC& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
// <T>设置数据内容。</T>
//
// @param value 数据
//============================================================
void TFsLinkId::operator=(const TFsLinkId& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
// <T>获得数据内容。</T>
//
// @return 数据
//============================================================
SLinkId TFsLinkId::Value() const{
   return _value;
}

//============================================================
// <T>设置数据内容。</T>
//
// @param value 数据
//============================================================
void TFsLinkId::SetValue(SLinkId value){
   _value = value;
}

//============================================================
// <T>打包数据为字符串。</T>
//
// @return 字符串
//============================================================
TCharC* TFsLinkId::Pack(){
   return Encode(_value, _pMemory, _capacity);
}

//============================================================
// <T>格式化为字符串。</T>
//
// @return 字符串
//============================================================
TCharC* TFsLinkId::Format(){
   SObjectData& objectId = _value.data.items.objectId;
   AssignFormat("%02X-%02X-%04X(%d):%X:%016X(%" MO_FMT_INT64 ")",
         objectId.items.type, objectId.items.group, objectId.items.index,
         _value.Handle(),
         _value.data.items.flag, _value.data.items.code, _value.data.items.code);
   return _pMemory;
}

MO_NAMESPACE_END
