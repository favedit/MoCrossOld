#include "MoFrLoader.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FLoaderMonitor, FMonitor);

//============================================================
// <T>构造加载器工作器。</T>
//============================================================
FLoaderMonitor::FLoaderMonitor(){
}

//============================================================
// <T>析构加载器工作器。</T>
//============================================================
FLoaderMonitor::~FLoaderMonitor(){
}

//============================================================
// <T>数据处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoaderMonitor::Process(){
   TResult result = ESuccess;
   //............................................................
   // 获取处理对象
   FLoader* pLoader = RLoaderManager::Instance().PopWaitLoader();
   if(pLoader != NULL){
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
