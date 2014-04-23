#include "MoEgProcessor.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造异步处理线程。</T>
//============================================================
FProcessorThread::FProcessorThread(){
   _name = "processor.thread";
   _interval = 10000;
   _looperLimit = 16;
   _pLooper = MO_CREATE(FProcessorLooper);
}

//============================================================
// <T>析构异步处理线程。</T>
//============================================================
FProcessorThread::~FProcessorThread(){
   MO_DELETE(_pLooper);
}

//============================================================
// <T>注册一个处理器。</T>
//
// @param pProcessor 处理器
// @return 处理结果
//============================================================
TResult FProcessorThread::Register(IProcessor* pProcessor){
   _locker.Enter();
   _pLooper->Push(pProcessor);
   _locker.Leave();
   return ESuccess;
}

//============================================================
// <T>注销一个处理器。</T>
//
// @param pProcessor 处理器
// @return 处理结果
//============================================================
TResult FProcessorThread::Unregister(IProcessor* pProcessor){
   _locker.Enter();
   _pLooper->Remove(pProcessor);
   _locker.Leave();
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FProcessorThread::Process(){
   while(!IsStop()){
      TBool statusPause = RDeviceManager::Instance().StatusPause();
      if(!statusPause){
         // 执行处理
         _locker.Enter();
         _pLooper->Record();
         for(TInt n = 0; n < _looperLimit; n++){
            IProcessor* pProcessor = _pLooper->Next();
            if(pProcessor == NULL){
               break;
            }
            if(!IsStop()){
               pProcessor->AnsyProcess();
            }
         }
         _locker.Leave();
      }
      // 休息处理
      SleepMicro(_interval);
   }
   return ESuccess;
}

//============================================================
// <T>清空处理。</T>
//
// @return 处理结果
//============================================================
TResult FProcessorThread::Clear(){
   _locker.Enter();
   _pLooper->Clear();
   _locker.Leave();
   return ESuccess;
}

MO_NAMESPACE_END
