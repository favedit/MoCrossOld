#include "MoCrCommon.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造分组编号。</T>
//============================================================
TFsGroupId::TFsGroupId(){
}

//============================================================
// <T>构造分组编号。</T>
//
// @param value 内容
//============================================================
TFsGroupId::TFsGroupId(const SGroupId& value){
   _value = value;
}

//============================================================
void TFsGroupId::operator=(const SGroupId& value){
   _value = value;
}

//============================================================
void TFsGroupId::operator=(TCharC* pValue){
   this->Assign(pValue);
}

//============================================================
void TFsGroupId::operator=(const TFsGroupId& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
void TFsGroupId::operator=(const TStringPtrC& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
SGroupId TFsGroupId::Value() const{
   return _value;
}

//============================================================
void TFsGroupId::SetValue(SGroupId value){
   _value = value;
}

//============================================================
TCharC* TFsGroupId::Format(){
   AssignFormat(TC("%02X:%02X-%02X:%02X"),
         _value.data.items.groupType, _value.data.items.groupId,
         _value.data.items.itemType, _value.data.items.itemId);
   return MemoryC();
}

MO_NAMESPACE_END
