#include "MoFrLoader.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造加载器工作器。</T>
//============================================================
FLoaderWorker::FLoaderWorker(){
}

//============================================================
// <T>析构加载器工作器。</T>
//============================================================
FLoaderWorker::~FLoaderWorker(){
}

//============================================================
// <T>数据处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoaderWorker::OnProcess(){
   TResult result = ESuccess;
   //............................................................
   // 获取处理对象
   FLoader* pLoader = RLoaderManager::Instance().PopWaitLoader();
   if(NULL != pLoader){
      // 处理资源
      if(pLoader->Process()){
         MO_DEBUG(TC("Process loader success. (loader=0x%08X)"), pLoader);
      }else{
         MO_ERROR(TC("Process loader failure. (loader=0x%08X)"), pLoader);
      }
      // 释放资源
      MO_DELETE(pLoader);
      result = EFailure;
   }
   //............................................................
   return result;
}

MO_NAMESPACE_END
