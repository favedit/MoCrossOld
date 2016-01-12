#include "MoCrNetPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造数据队列分块缓冲池模块。</T>
//============================================================
FNetQueueBlockPoolModule::FNetQueueBlockPoolModule(){
   _name = TC("Module.NetQueue.Pool");
   _blockLimit = 0;
   _pAllocators = MO_CREATE(FBufferedQueueBlockAllocatorVector);
}

//============================================================
// <T>析构数据队列分块缓冲池模块。</T>
//============================================================
FNetQueueBlockPoolModule::~FNetQueueBlockPoolModule(){
   MO_DELETE(_pAllocators);
}

//============================================================
// <T>加载设置信息。</T>
//
// @return 处理结果
//============================================================
TResult FNetQueueBlockPoolModule::LoadConfig(FXmlNode* pConfig){
   // 加载设置信息
   TXmlNodeIteratorC iterator = pConfig->NodeIteratorC();
   while(iterator.Next()){
      FXmlNode* pNode = *iterator;
      if(pNode->IsAttribute(TC("name"), TC("block_limit"))){
         // 加载分块限制
         _blockLimit = pNode->TextAsInt();
         MO_ASSERT(_blockLimit > 0);
         MO_DEBUG(TC("Load module(%s) property. (block_limit=%d)"), (TCharC*)_name, _blockLimit);
      }else if(pNode->IsName(TC("Allocator"))){
         // 获得属性
         TInt blockCapacity = pNode->GetAsInt(TC("block_capacity"));
         MO_ASSERT(blockCapacity > 0);
         TInt blockLimit = pNode->GetAsInt(TC("block_limit"));
         MO_ASSERT(blockLimit > 0);
         // 创建对象
         FBufferedQueueBlockAllocator* pAlloc = MO_CREATE(FBufferedQueueBlockAllocator);
         pAlloc->SetBlockCapacity(blockCapacity);
         pAlloc->SetBlockLimit(blockLimit);
         _pAllocators->Push(pAlloc);
      }
   }
   return ESuccess;
}

//============================================================
// <T>根据分块容量收集一个数据队列分块缓冲池。</T>
//
// @param blockCapacity 分块容量
// @return 数据队列分块缓冲池
//============================================================
FBufferedQueueBlockAllocator* FNetQueueBlockPoolModule::Alloc(TInt blockCapacity){
   MO_ASSERT(blockCapacity > 0);
   // 查找对象
   TInt count = _pAllocators->Count();
   for(TInt n = 0; n < count; n++){
      FBufferedQueueBlockAllocator* pFind = _pAllocators->Get(n);
      if(pFind->BlockCapacity() == blockCapacity){
         return pFind;
      }
   }
   // 创建对象
   FBufferedQueueBlockAllocator* pAlloc = MO_CREATE(FBufferedQueueBlockAllocator);
   pAlloc->SetBlockCapacity(blockCapacity);
   pAlloc->SetBlockLimit(_blockLimit);
   _pAllocators->Push(pAlloc);
   return pAlloc;
}

//============================================================
// <T>释放数据队列分块缓冲池。</T>
//============================================================
void FNetQueueBlockPoolModule::Free(FBufferedQueueBlockAllocator* pPool){
   MO_ASSERT(pPool);
   if(_pAllocators->Contains(pPool)){
      _pAllocators->Remove(pPool);
      MO_DELETE(pPool);
   }else{
      MO_FATAL(TC("Free pool object is not exists."));
   }
}

//============================================================
// <T>统计刷新处理。</T>
//
// @return 处理结果
//============================================================
TResult FNetQueueBlockPoolModule::StatisticsRefresh(){
   TFsDump dump;
   TInt count = _pAllocators->Count();
   for(TInt n = 0; n < count; n++){
      FBufferedQueueBlockAllocator* pAllocator = _pAllocators->Get(n);
      pAllocator->Track(&dump);
   }
   MO_INFO(TC("Queue block pool statistics.%s"), (TCharC*)dump);
   return ESuccess;
}

MO_NAMESPACE_END
