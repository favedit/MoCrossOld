#include "MoCmThread.h"

MO_NAMESPACE_BEGIN

//============================================================
FThreadRunable::FThreadRunable(){
   MO_CLEAR(_runableHandle);
}

//============================================================
// <T>构造可运行线程对象。</T>
//
// @return 可运行线程对象
//============================================================
FThreadRunable::FThreadRunable(TRunableHandle handle){
   _runableHandle = handle;
}

//============================================================
// <T>设置工作函数。</T>
//
// @param cRunable 工作函数
//============================================================
void FThreadRunable::SetRunable(TRunableHandle handle){
   _runableHandle = handle;
}

//============================================================
// <T>执行处理。</T>
//
// @return 处理结果
//============================================================
TResult FThreadRunable::Process(){
   if(NULL != _runableHandle){
      return (*_runableHandle)(this);
   }
   return ESuccess;
}

MO_NAMESPACE_END
