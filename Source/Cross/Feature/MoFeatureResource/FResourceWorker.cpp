#include "MoFrContent2d.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造资源线程。</T>
//============================================================
FResourceWorker::FResourceWorker(){
   _name = TC("resource.worker");
}

//============================================================
// <T>析构资源线程。</T>
//============================================================
FResourceWorker::~FResourceWorker(){
}

//============================================================
// <T>数据处理。</T>
//
// @return 处理结果
//============================================================
TResult FResourceWorker::OnProcess(){
   TResult result = ESuccess;
   ////............................................................
   //// 获取处理对象
   //FResource* pResource = RResourceManager::Instance().PopWaitResource();
   //if(NULL != pResource){
   //   // 处理资源
   //   if(pResource->Process()){
   //      // 放入完成集合
   //      RResourceManager::Instance().PushFinishResource(pResource);
   //      MO_DEBUG(TC("Process resource success. (resource=0x%08X, code=%d, code_name=%s, type_cd=%d, type_name=%s)"),
   //            pResource, pResource->Code(), pResource->CodeName(), pResource->TypeCd(), pResource->TypeName());
   //   }else{
   //      MO_ERROR(TC("Process resource failure. (resource=0x%08X, code=%d, code_name=%s, type_cd=%d, type_name=%s)"),
   //            pResource, pResource->Code(), pResource->CodeName(), pResource->TypeCd(), pResource->TypeName());
   //   }
   //   result = EFalse;
   //}
   ////............................................................
   //// 处理超时资源
   //RResourceManager::Instance().ProcessTimeOutResources();
   //// 获取释放对象
   //while(ETrue){
   //   FResource* pResource = RResourceManager::Instance().PopReleaseResource();
   //   if(NULL != pResource){
   //      MO_DELETE(pResource);
   //   }else{
   //      break;
   //   }
   //}
   return result;
}

MO_NAMESPACE_END
