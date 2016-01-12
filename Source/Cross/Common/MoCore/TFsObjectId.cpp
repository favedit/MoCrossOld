#include "MoCrCommon.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造对象编号字符串。</T>
//============================================================
TFsObjectId::TFsObjectId(){
}

//============================================================
// <T>构造对象编号字符串。</T>
//
// @param value 数据
//============================================================
TFsObjectId::TFsObjectId(const SObjectId& value){
   _value = value;
}

//============================================================
// <T>将字符串转换为对象编号。</T>
//
// @param pValue 字符串
// @param length 长度
// @return 对象编号
//============================================================
TObjectId TFsObjectId::Decode(TCharC* pValue, TInt length){
   return RUint32::ParseHex(pValue, length);
}

//============================================================
// <T>将对象编号转换为字符串。</T>
//
// @param value 对象编号
// @param pBuffer 字符串缓冲
// @param capacity 字符串容量
// @return 字符串
//============================================================
TCharC* TFsObjectId::Encode(TObjectId value, TChar* pBuffer, TInt capacity){
   RUint32::ToHexString(value, pBuffer, capacity);
   pBuffer[8] = 0;
   return pBuffer;
}

//============================================================
// <T>设置数据内容。</T>
//
// @param value 数据
//============================================================
void TFsObjectId::operator=(const SObjectId& value){
   _value = value;
}

//============================================================
// <T>设置数据内容。</T>
//
// @param value 数据
//============================================================
void TFsObjectId::operator=(TCharC* pValue){
   this->Assign(pValue);
}

//============================================================
// <T>设置数据内容。</T>
//
// @param value 数据
//============================================================
void TFsObjectId::operator=(const TStringPtrC& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
// <T>设置数据内容。</T>
//
// @param value 数据
//============================================================
void TFsObjectId::operator=(const TFsObjectId& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
// <T>获得数据内容。</T>
//
// @return 数据
//============================================================
SObjectId TFsObjectId::Value() const{
   return _value;
}

//============================================================
// <T>设置数据内容。</T>
//
// @param value 数据
//============================================================
void TFsObjectId::SetValue(SObjectId value){
   _value = value;
}

//============================================================
// <T>打包数据为字符串。</T>
//
// @return 字符串
//============================================================
TCharC* TFsObjectId::Pack(){
   return Encode(_value, _pMemory, _capacity);
}

//============================================================
// <T>格式化为字符串。</T>
//
// @return 字符串
//============================================================
TCharC* TFsObjectId::Format(){
   AssignFormat(TC("%02X-%02X-%04X(%d)"),
         _value.data.items.type, _value.data.items.group, _value.data.items.index,
         _value.Handle());
   return MemoryC();
}

MO_NAMESPACE_END
