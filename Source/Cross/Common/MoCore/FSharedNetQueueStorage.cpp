#include "MoCrNetPipe.h"
#include "MoCrNetSocket.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造链接对象，不需要参数。</T>
// <P>以后使用前先要设置输入输出管道大小。</P>
//
// @return 当前实例指针
//============================================================
FSharedNetQueueStorage::FSharedNetQueueStorage(){
   _name = TC("Storage.NetQueue");
   // 初始化所有对象
   _capacity = 0;
   _blockCapacity = 0;
   MO_CLEAR(_pAllocator);
   // 创建管道
   _pConnection = MO_CREATE(FNetBufferedQueueConnection);
}

//============================================================
// <T>析构链接对象。</T>
//============================================================
FSharedNetQueueStorage::~FSharedNetQueueStorage(){
   MO_DELETE(_pConnection);
}

//============================================================
// <T>加载设置信息。</T>
//
// @return 处理结果
//============================================================
TResult FSharedNetQueueStorage::LoadConfig(FXmlNode* pConfig){
   // 加载设置信息
   TXmlNodeIteratorC iterator = pConfig->NodeIteratorC();
   while(iterator.Next(TC("Property"))){
      FXmlNode* pNode = *iterator;
      if(pNode->IsAttribute(TC("name"), TC("capacity"))){
         _capacity = pNode->TextAsInt();
         MO_ASSERT(_capacity > 0);
         MO_DEBUG(TC("Load module(%s) property. (capacity=%d)"), (TCharC*)_name, _capacity);
      }else if(pNode->IsAttribute(TC("name"), TC("block_capacity"))){
         _blockCapacity = pNode->TextAsInt();
         MO_ASSERT(_blockCapacity > 0);
         MO_DEBUG(TC("Load module(%s) property. (output_capacity=%d)"), (TCharC*)_name, _blockCapacity);
      }
   }
   // 收集一个收集器
   _pAllocator = RNetQueueBlockPoolModule::Instance().Alloc(_blockCapacity);
   // 设置管道
   _pConnection->Setup(_capacity, _pAllocator);
   return ESuccess;
}

//============================================================
// <T>获得收队列的指针。</T>
//
// @return 收队列的指针。
//============================================================
FNetBufferedQueueConnection* FSharedNetQueueStorage::Connection(){
   return _pConnection;
}

MO_NAMESPACE_END
