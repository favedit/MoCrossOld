#include "MoCrNetPipe.h"
#include "MoCrNetSocket.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造套接字模块对象。</T>
//
// @return 当前对象指针。
//============================================================
FNetBufferedSocketModule::FNetBufferedSocketModule(){
   // 初始化所有对象
   _name = TC("Module.NetSockets");
   _socketCount = 0;
   _socketCapacity = 1024 * 1024 * 4;
   _socketReceiveBuffer = 1024 * 64;
   _socketSendBuffer = 1024 * 64;
   _socketSerial = 0;
   _blockCapacity = 0;
   _pHandles = MO_CREATE(FNetBufferedSocketSet);
   _pPool = MO_CREATE(FNetBufferedSocketPool);
   _sendTimeout = 0;
   _receiveTimeout = 0;
}

//============================================================
// <T>析构套接字模块对象。</T>
//
// @return 无返回值
//============================================================
FNetBufferedSocketModule::~FNetBufferedSocketModule(){
   MO_DELETE(_pHandles);
   if(NULL != _pPool){
      _pPool->Release();
      MO_DELETE(_pPool);
   }
}

//============================================================
// <T>加载设置信息。</T>
//
// @return 处理结果
//============================================================
TResult FNetBufferedSocketModule::OnLoadConfig(FXmlNode* pConfig){
   // 读取设置
   TXmlNodeIteratorC iterator = pConfig->NodeIteratorC();
   while(iterator.Next(TC("Property"))){
      FXmlNode* pNode = *iterator;
      if(pNode->IsAttribute(TC("name"), TC("socket_count"))){
         _socketCount = pNode->TextAsInt();
         MO_DEBUG(TC("Load module(%s) property. (socket_count=%d)"), (TCharC*)_name, _socketCount);
      }else if(pNode->IsAttribute(TC("name"), TC("socket_capacity"))){
         _socketCapacity = pNode->TextAsInt();
         MO_DEBUG(TC("Load module(%s) property. (socket_capacity=%d)"), (TCharC*)_name, _socketCapacity);
      }else if(pNode->IsAttribute(TC("name"), TC("socket_receive_buffer"))){
         _socketReceiveBuffer = pNode->TextAsInt();
         MO_DEBUG(TC("Load module(%s) property. (socket_receive_buffer=%d)"), (TCharC*)_name, _socketReceiveBuffer);
      }else if(pNode->IsAttribute(TC("name"), TC("socket_send_buffer"))){
         _socketSendBuffer = pNode->TextAsInt();
         MO_DEBUG(TC("Load module(%s) property. (socket_send_buffer=%d)"), (TCharC*)_name, _socketSendBuffer);
      }else if(pNode->IsAttribute(TC("name"), TC("block_capacity"))){
         _blockCapacity = pNode->TextAsInt();
         MO_DEBUG(TC("Load module(%s) property. (block_capacity=%d)"), (TCharC*)_name, _blockCapacity);
      }
   }
   return ESuccess;
}

//============================================================
// <T>初始化处理。</T>
//
// @return 处理结果
//============================================================
TResult FNetBufferedSocketModule::OnInitialize(){
   // 获得管道分块收集器
   FBufferedPipeBlockAllocator* pAllocator = RNetPipeBlockPoolModule::Instance().Alloc(_blockCapacity);
   // 建立端口对象
   _sectionPool.Enter();
   for(TInt n = 0; n < _socketCount; n++){
      // 创建链接对象
      FNetBufferedSocket* pSocket = MO_CREATE(FNetBufferedSocket);
      // 存储缓冲
      TInt index = _pPool->Store(pSocket);
      pSocket->SetIndex((TUint16)index);
      pSocket->NetInfo()->receiveBufferSize = (TUint32)_socketReceiveBuffer;
      pSocket->NetInfo()->sendBufferSize = (TUint32)_socketSendBuffer;
      pSocket->LinkAllocator(_socketCapacity, pAllocator);
   }
   _sectionPool.Leave();
   return ESuccess;
}

//============================================================
// <T>根据句柄关联一个网络链接。</T>
// <P>如果链接已满，则返回空指针。</P>
//
// @param handle 句柄
// @return 网络链接对象
//============================================================
FNetBufferedSocket* FNetBufferedSocketModule::LinkHandle(TUint32 handle){
   FNetBufferedSocket* pSocket = NULL;
   // 查找对象
   _sectionSet.Enter();
   pSocket = _pHandles->Find(handle);
   _sectionSet.Leave();
   // 创建对象
   if(NULL == pSocket){
      // 收集对象
      _sectionPool.Enter();
      if(_pPool->HasFreeItem()){
         pSocket = _pPool->AllocFirst();
      }
      _sectionPool.Leave();
      // 存储对象
      if(NULL != pSocket){
         _sectionSet.Enter();
         _pHandles->Set(handle, pSocket);
         _sectionSet.Leave();
      }else{
         MO_ERROR(TC("Alloc socket failure. (count=%d, useing_count=%d, free_count=%d)"),
               _pPool->Count(), _pPool->UsingCount(), _pPool->FreeCount());
      }
   }
   return pSocket;
}

//============================================================
// <T>根据代码，获得网络句柄。</T>
//
// @param handle 句柄
// @return 网络链接对象
//============================================================
FNetBufferedSocket* FNetBufferedSocketModule::FindHandle(TUint32 handle){
   FNetBufferedSocket* pSocket = NULL;
   // 根据句柄获得网络端口
   _sectionSet.Enter();
   pSocket = _pHandles->Find(handle);
   _sectionSet.Leave();
   // 检查是否正常开启的网络端口
   if(NULL != pSocket){
      if(!pSocket->IsProcessing()){
         pSocket = NULL;
      }
   }
   return pSocket;
}

//============================================================
// <T>根据索引位置，获得网络句柄。</T>
//
// @param index 索引位置
// @return 网络链接对象
//============================================================
FNetBufferedSocket* FNetBufferedSocketModule::FindIndex(TUint16 index){
   FNetBufferedSocket* pSocket = NULL;
   // 根据索引获得网络端口
   _sectionPool.Enter();
   pSocket = _pPool->Storage()->Nvl(index, NULL);
   _sectionPool.Leave();
   // 检查是否正常开启的网络端口
   if(NULL != pSocket){
      if(!pSocket->IsProcessing()){
         pSocket = NULL;
      }
   }
   return pSocket;
}

//============================================================
// <T>打开网络链接。</T>
//
// @param pSocket 网络链接
//============================================================
void FNetBufferedSocketModule::OpenSocket(FNetBufferedSocket* pSocket){
   MO_ASSERT(pSocket);
   // 启动链接
   _socketSerial++;
   pSocket->Startup();
   pSocket->SetSerial((TUint16)_socketSerial);
}

//============================================================
// <T>关闭网络链接。</T>
//
// @param pSocket 网络链接
//============================================================
void FNetBufferedSocketModule::CloseSocket(FNetBufferedSocket* pSocket){
   MO_ASSERT(pSocket);
   // 关闭链接
   TUint32 handle = pSocket->Handle();
   pSocket->Shutdown();
   // 释放句柄
   _sectionSet.Enter();
   MO_ASSERT(_pHandles->Get(handle) == pSocket);
   _pHandles->Remove(handle);
   _sectionSet.Leave();
   // 释放对象
   _sectionPool.Enter();
   MO_ASSERT(_pPool->Storage()->Get(pSocket->Index()) == pSocket);
   _pPool->FreeLast(pSocket);
   _sectionPool.Leave();
}

//============================================================
// <T>刷新处理。</T>
//
// @return 处理结果
//============================================================
TResult FNetBufferedSocketModule::TriggerRefresh(TTimeTick currentTick){
   // 检查端口，查看是否有非法信息
//   TDateTime current = RDateTime::Current();
//   TListIteratorC<FNetBufferedSocket*> iterator = _pPool->UsingItems()->IteratorC();
//   while(iterator.Next()){
//      FNetBufferedSocket* pSocket = *iterator;
//      SNetSocketInfo* pInfo = pSocket->Info();
//      // 检查发送超时
//      if(_sendTimeout > 0){
//         TTimeTick sendTick = current - pInfo->sendDateTime;
//         if(sendTick > _sendTimeout){
//            MO_WARN("   Socket send timeout. (socket=0x%08X, send_timeout=%d, send_tick=%d)", pSocket, _sendTimeout, sendTick);
//         }
//      }
//      // 检查接收超时
//      if(_receiveTimeout > 0){
//         TTimeTick receiveTick = current - pInfo->receiveDateTime;
//         if(receiveTick > _receiveTimeout){
//            MO_WARN("   Socket receive timeout. (socket=0x%08X, send_timeout=%d, send_tick=%d)", pSocket, _receiveTimeout, receiveTick);
//         }
//      }
//   }
   return ESuccess;
}

//============================================================
// <T>统计刷新处理。</T>
//
// @return 处理结果
//============================================================
TResult FNetBufferedSocketModule::StatisticsRefresh(){
   // 显示一半信息
   TInt count = _pPool->Count();
   TInt usingCount = _pPool->UsingCount();
   TInt freeCount = _pPool->FreeCount();
   MO_INFO(TC("Buffered socket statistics. (name=%s, count=%d, using_count=%d, free_count=%d)"), (TCharC*)_name, count, usingCount, freeCount);
   // 检查端口，查看是否有非法信息
   TDateTime current = RDateTime::Current();
   TListIteratorC<FNetBufferedSocket*> iterator = _pPool->UsingItems()->IteratorC();
   while(iterator.Next()){
      FNetBufferedSocket* pSocket = *iterator;
      SNetSocketInfo* pInfo = pSocket->Info();
      // 检查发送超时
      if(_sendTimeout > 0){
         TTimeTick sendTick = current - pInfo->sendDateTime;
         if(sendTick > _sendTimeout){
            MO_WARN(TC("   Socket send timeout. (socket=0x%08X, send_timeout=%d, send_tick=%d)"), pSocket, _sendTimeout, sendTick);
         }
      }
      // 检查接收超时
      if(_receiveTimeout > 0){
         TTimeTick receiveTick = current - pInfo->receiveDateTime;
         if(receiveTick > _receiveTimeout){
            MO_WARN(TC("   Socket receive timeout. (socket=0x%08X, send_timeout=%d, send_tick=%d)"), pSocket, _receiveTimeout, receiveTick);
         }
      }
   }
   return ESuccess;
}

MO_NAMESPACE_END
