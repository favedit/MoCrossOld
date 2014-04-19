#include "MoCmData.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造数据集合信息。</T>
//============================================================
FDatasetMeta::FDatasetMeta(){
   _logicCode = 0;
   MO_CLEAR(_pLogicName);
   MO_CLEAR(_pDataName);
   MO_CLEAR(_pDataFields);
   // 重置计数器
   Reset();
}

//============================================================
// <T>析构数据集合信息。</T>
//============================================================
FDatasetMeta::~FDatasetMeta(){
}

//============================================================
// <T>析构构造监视器线程。</T>
//
// @return 处理结果
//============================================================
TBool FDatasetMeta::Setup(){
   return ESuccess;
}

//============================================================
// <T>处理逻辑。</T>
//============================================================
void FDatasetMeta::Process(ESqlProcess processCd){
   _counters[processCd]++;
}

//============================================================
// <T>重置处理。</T>
//============================================================
void FDatasetMeta::Reset(){
   for(TInt n = 0; n < ESqlProcess_Count; n++){
      _counters[n] = 0;
   }
}

//============================================================
// <T>获得内部信息。</T>
//
// @param pDump 信息字符串
//============================================================
void FDatasetMeta::Dump(MString* pDump){
   TInt64 findTotal = _counters[ESqlProcess_Find];
   TInt64 fetchTotal = _counters[ESqlProcess_Fetch];
   TInt64 insertTotal = _counters[ESqlProcess_Insert];
   TInt64 updateTotal = _counters[ESqlProcess_Update];
   TInt64 deleteTotal = _counters[ESqlProcess_Delete];
   pDump->AppendFormat(TC("[G=%8lld, F=%8lld, I=%8lld, U=%8lld, D=%8lld]"),
         findTotal, fetchTotal, insertTotal, updateTotal, deleteTotal);
}

MO_NAMESPACE_END
