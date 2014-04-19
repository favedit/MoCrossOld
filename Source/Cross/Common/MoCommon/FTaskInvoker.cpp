#include "MoCmTask.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTaskInvoker, FTask);

//============================================================
// <T>构造任务调用。</T>
//============================================================
FTaskInvoker::FTaskInvoker(){
   MO_CLEAR(_pTask);
}

//============================================================
// <T>析构任务调用。</T>
//============================================================
FTaskInvoker::~FTaskInvoker(){
}

//============================================================
// <T>执行处理。</T>
//
// @return 处理结果
//============================================================
TResult FTaskInvoker::Process(){
   MO_CHECK(_pTask, return ENull);
   TResult resultCd = _pTask->TaskProcess();
   return resultCd;
}

MO_NAMESPACE_END
