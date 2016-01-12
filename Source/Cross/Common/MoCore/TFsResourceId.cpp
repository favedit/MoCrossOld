#include "MoCrCommon.h"

MO_NAMESPACE_BEGIN

//============================================================
TFsResourceId::TFsResourceId(){
}

//============================================================
TFsResourceId::TFsResourceId(TResourceId value){
   _value = value;
}

//============================================================
void TFsResourceId::operator=(TResourceId value){
   _value = value;
}

//============================================================
void TFsResourceId::operator=(TCharC* pValue){
   this->Assign(pValue);
}

//============================================================
void TFsResourceId::operator=(const TFsResourceId& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
void TFsResourceId::operator=(const TStringPtrC& value){
   this->Assign(value.MemoryC(), value.Length());
}

//============================================================
TResourceId TFsResourceId::Value() const{
   return _value;
}

//============================================================
void TFsResourceId::SetValue(TResourceId value){
   _value = value;
}

//============================================================
TCharC* TFsResourceId::Format(){
   this->AssignFormat(TC("%01d-%04d-%04d"),
         _value / 100000000, (_value / 10000) % 10000, _value % 10000);
   return this->MemoryC();
}

MO_NAMESPACE_END
