#include "MoMtFormat.h"

MO_NAMESPACE_BEGIN

//============================================================
TFsFloatVector3::TFsFloatVector3(){
}

//============================================================
TFsFloatVector3::TFsFloatVector3(const SFloatVector3& value){
   _value = value;
}

//============================================================
void TFsFloatVector3::operator=(const SFloatVector3& value){
   _value = value;
}

//============================================================
void TFsFloatVector3::operator=(TCharC* pValue){
   this->Assign(pValue);
}

//============================================================
void TFsFloatVector3::operator=(const TFsFloatVector3& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
void TFsFloatVector3::operator=(const TStringPtrC& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
SFloatVector3 TFsFloatVector3::Value() const{
   return _value;
}

//============================================================
void TFsFloatVector3::SetValue(SFloatVector3 value){
   _value = value;
}

//============================================================
TCharC* TFsFloatVector3::Format(){
   this->AssignFormat(TC("%f,%f,%f"), _value.x, _value.y, _value.z);
   return this->MemoryC();
}

MO_NAMESPACE_END
