#include "MoCmData.h"
#include "MoCmMemory.h"

MO_NAMESPACE_BEGIN

//============================================================
FDataset::FDataset(){
   MO_CLEAR(_pFields);
}

//============================================================
FDataset::~FDataset(){
   MO_DELETE(_pFields);
}

//============================================================
FFields* FDataset::Fields(){
   if(NULL != _pFields){
      _pFields = MO_CREATE(FFields);
   }
   return _pFields;
}

//============================================================
// <T>创建一个数据行。</T>
//============================================================
FRow* FDataset::CreateRow(){
   FRow* pRow = MO_CREATE(FRow, this);
   Push(pRow);
   return pRow;
}

//============================================================
TInt FDataset::Count(){
   return 0;
}

//============================================================
TAny** FDataset::Ptrs(){
   return NULL;
}

//============================================================
void FDataset::ForceCount(TInt count){
}

MO_NAMESPACE_END
