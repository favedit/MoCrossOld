#include "MoCmData.h"
#include "MoCmMemory.h"

MO_NAMESPACE_BEGIN

//============================================================
FRow::FRow(FDataset* pDataset){
   _pDataset = pDataset;
   _pValues = MO_CREATE(FStringArray);
}

//============================================================
FRow::~FRow(){
   MO_DELETE(_pValues);
}

//============================================================
TCharC* FRow::operator[](TInt index){
   return Get(index);
}

//============================================================
TCharC* FRow::operator[](TCharC* pName){
   return Get(pName);
}

//============================================================
TCharC* FRow::Get(TInt index){
   //return _pValues->Get(index);
   return NULL;
}

//============================================================
TCharC* FRow::Get(TCharC* pName){
   return pName;
}

//============================================================
void FRow::Set(TInt index, TCharC* pValue){
}

//============================================================
void FRow::Set(TCharC* pName, TCharC* pValue){
}

MO_NAMESPACE_END
