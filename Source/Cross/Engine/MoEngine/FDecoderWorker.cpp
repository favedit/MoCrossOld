#include "MoEgDecoder.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造资源工作器线程。</T>
//============================================================
FDecoderWorker::FDecoderWorker(){
   _name = TC("decoder.worker");
}

//============================================================
// <T>析构资源工作器线程。</T>
//============================================================
FDecoderWorker::~FDecoderWorker(){
}

//============================================================
// <T>数据处理。</T>
//
// @return 处理结果
//============================================================
TResult FDecoderWorker::OnProcess(){
   // 获取对象
   FDecoder* pWorker = RDecoderManager::Instance().PopWaitWorker();
   if(NULL != pWorker){
      MO_DEBUG(TC("Process resource worker. (worker=0x%08X, code=%d)"), pWorker, pWorker->Code());
      // 处理
      if(pWorker->Process()){
         // 完成处理
         pWorker->Complete();
         // 放入完成集合
         MO_DEBUG(TC("Push worker to finish queue (worker=0x%08X)"), pWorker);
         RDecoderManager::Instance().PushFinishWorker(pWorker);
      }
      return EFailure;
   }
   return ESuccess;
}

MO_NAMESPACE_END
