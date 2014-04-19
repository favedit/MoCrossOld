#include "MoCmShared.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造共享管理器。</T>
//============================================================
FSharedConsole::FSharedConsole(){
   _pShareds = MO_CREATE(FSharedSet);
   _pGroups = MO_CREATE(FSharedGroupList);
}

//============================================================
// <T>析构共享管理器。</T>
//============================================================
FSharedConsole::~FSharedConsole(){
   MO_DELETE(_pShareds);
   MO_DELETE(_pGroups);
}

//============================================================
TSize FSharedConsole::SharedCapacity(){
   TListIteratorC<ISingleton*> iterator = RSingletonManager::Instance().Singletons()->IteratorC();
   TSize total = 0;
   while(iterator.Next()){
      /*if(ESingleton_Shared == iterator->SingletonType()){
         // 获得共享对象
         IShared* pShared = (IShared*)iterator.Get();
         TSize capacity = pShared->SharedCapacity()
         if(capacity > 0){
            MO_DEBUG("Calculate [%s] shared capacity. (capacity=%d)", pShared->Name(), capacity);
              total += pShared->SharedCapacity();
         }
      }*/
   }
   return total;
}

//============================================================
void FSharedConsole::SharedLink(TShareSegment segment){
   TListIteratorC<ISingleton*> iterator = RSingletonManager::Instance().Singletons()->IteratorC();
   while(iterator.Next()){
      /*if(ESingleton_Shared == iterator->SingletonType()){
         // 获得共享对象
         IShared* pShared = (IShared*)iterator.Get();
         TSize capacity = pShared->SharedCapacity();
         if(capacity > 0){
            pShared->SharedLink(segment.CreateSegment(capacity));
         }
      }*/
   }
}

//============================================================
MShared* FSharedConsole::Get(TInt code){
   return _pShareds->Get(code);
}

//============================================================
MShared* FSharedConsole::Find(TInt code){
   return _pShareds->Find(code);
}

//============================================================
void FSharedConsole::Register(MShared* pShared){
   //_pShareds->Set(pShared->Code(), pShared);
}

//============================================================
void FSharedConsole::Unregister(MShared* pShared){
   //_pShareds->Remove(pShared->Code());
}

//============================================================
// <T>根据主键查找关联共享组。</T>
//
// @param key 主键
// @param pKeyName 主键名称
// @return 共享组
//============================================================
FSharedGroup* FSharedConsole::FindGroup(TShareKey key, TCharC* pKeyName){
   TListIteratorC<FSharedGroup*> iterator = _pGroups->IteratorC();
   while(iterator.Next()){
      FSharedGroup* pGroup = *iterator;
      if((pGroup->Key() == key) && RString::Equals(pGroup->KeyName(), pKeyName)){
         return pGroup;
      }
   }
   return NULL;
}

//============================================================
// <T>关联共享组。</T>
//
// @param key 主键
// @param pKeyName 主键名称
// @return 共享组
//============================================================
FSharedGroup* FSharedConsole::LinkGroup(TShareKey key, TCharC* pKeyName){
   // 查找共享组
   FSharedGroup* pGroup = FindGroup(key, pKeyName);
   // 创建共享组
   if(NULL == pGroup){
      pGroup = MO_CREATE(FSharedGroup);
      pGroup->SetKey(key);
      pGroup->SetKeyName(pKeyName);
      _pGroups->Push(pGroup);
   }
   return pGroup;
}

MO_NAMESPACE_END
