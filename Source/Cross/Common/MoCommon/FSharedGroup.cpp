#include "MoCmShared.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造共享组。</T>
//============================================================
FSharedGroup::FSharedGroup(){
   _key = 0;
   _pShareds = MO_CREATE(FSharedList);
   _pAllocator = NULL;
}

//============================================================
// <T>析构共享组。</T>
//============================================================
FSharedGroup::~FSharedGroup(){
   MO_DELETE(_pShareds);
   MO_DELETE(_pAllocator);
}

//============================================================
// <T>计算共享内存大小。</T>
//
// @return 内存大小
//============================================================
TSize FSharedGroup::SharedCapacity(){
   TListIteratorC<MShared*> iterator = _pShareds->IteratorC();
   TSize total = 0;
   while(iterator.Next()){
      // 获得共享对象
      TSize capacity = iterator->SharedCapacity();
      if(capacity > 0){
         total += capacity;
      }
      // MO_INFO("Calculate shared capacity. (capacity=%d, total=%d, count=%d)", capacity, total, _pShareds->Count());
   }
   return total;
}

//============================================================
// <T>计算共享内存大小。</T>
//
// @return 内存大小
//============================================================
TBool FSharedGroup::SharedLink(TShareSegment segment){
   TListIteratorC<MShared*> iterator = _pShareds->IteratorC();
   while(iterator.Next()){
      // 获得共享对象
      TSize capacity = iterator->SharedCapacity();
      if(capacity > 0){
         iterator->SharedLink(segment.CreateSegment(capacity));
      }
   }
   return ETrue;
}

//============================================================
// <T>注册共享块。</T>
//
// @param pShared 共享块
//============================================================
void FSharedGroup::Register(MShared* pShared){
   MO_ASSERT(pShared);
   _pShareds->Push(pShared);
}

//============================================================
// <T>注销共享块。</T>
//
// @param pShared 共享块
//============================================================
void FSharedGroup::Unregister(MShared* pShared){
   MO_ASSERT(pShared);
   _pShareds->Remove(pShared);
}

//============================================================
// <T>创建内存。</T>
//
// @return 处理结果
//============================================================
TBool FSharedGroup::Create(){
   // 计算需要共享内存大小
   TSize capacity = SharedCapacity();
   // 收集共享内存
   MO_ASSERT(NULL == _pAllocator);
   FShareMemory* pShareMemory = MO_CREATE(FShareMemory);
   pShareMemory->SetKey(_key);
   pShareMemory->SetKeyName(_keyName);
   pShareMemory->SetCapacity(capacity);
   pShareMemory->Create();
   _pAllocator = pShareMemory;
   // 分配共享内存
   return SharedLink(_pAllocator->CreateSegment(capacity));
}

//============================================================
// <T>只读方式链接内存。</T>
//
// @return 处理结果
//============================================================
TBool FSharedGroup::Connect(){
   // 计算需要共享内存大小
   TSize capacity = SharedCapacity();
   // 收集共享内存
   MO_ASSERT(NULL == _pAllocator);
   FShareMemory* pShareMemory = MO_CREATE(FShareMemory);
   pShareMemory->SetKey(_key);
   pShareMemory->SetKeyName(_keyName);
   pShareMemory->SetCapacity(capacity);
   pShareMemory->Connect();
   if(!pShareMemory->IsLinked()){
      MO_FATAL(TC("Connect share memory error. (key=%d, key_name=%s)"), _key, (TCharC*)_keyName);
   }
   _pAllocator = pShareMemory;
   // 分配共享内存
   return SharedLink(_pAllocator->CreateSegment(capacity));
}

//============================================================
// <T>读写方式链接内存。</T>
//
// @return 处理结果
//============================================================
TBool FSharedGroup::Link(){
   // 计算需要共享内存大小
   TSize capacity = SharedCapacity();
   // 收集共享内存
   MO_ASSERT(NULL == _pAllocator);
   FShareMemory* pShareMemory = MO_CREATE(FShareMemory);
   pShareMemory->SetKey(_key);
   pShareMemory->SetKeyName(_keyName);
   pShareMemory->SetCapacity(capacity);
   pShareMemory->Connect();
   if(!pShareMemory->IsLinked()){
      MO_FATAL(TC("Link share memory error. (key=%d, key_name=%s)"), _key, (TCharC*)_keyName);
   }
   _pAllocator = pShareMemory;
   // 分配共享内存
   return SharedLink(_pAllocator->CreateSegment(capacity));
}

//============================================================
// <T>查看共享内存分配信息。</T>
//
// @return 处理结果
//============================================================
TBool FSharedGroup::Dump(){
   TInt size = 0;
   MO_INFO(TC("Name                                     |              Total |  Head    Data"));
   MO_INFO(MO_DUMP_SHARED_LA);
   TListIteratorC<MShared*> iterator = _pShareds->IteratorC();
   while(iterator.Next()){
      // 获得共享对象
      TSize capacity = iterator->SharedCapacity();
      if(capacity > 0){
         size += capacity;
         iterator->DumpShared();
      }
   }
   MO_INFO(MO_DUMP_SHARED_LA);
   TChar format[MO_MEMORY_FORMATLENGTH];
   MO_INFO(TC("                                         | %18s |"),
         RInt::FormatCapacity(size, format, MO_MEMORY_FORMATLENGTH));
   return ETrue;
}

MO_NAMESPACE_END
