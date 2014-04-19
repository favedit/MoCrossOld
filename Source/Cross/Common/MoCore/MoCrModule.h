#ifndef __MO_CR_MODULE_H__
#define __MO_CR_MODULE_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

#ifndef __MO_CR_CONFIGURATION_H__
#include "MoCrConfigruation.h"
#endif // __MO_CR_CONFIGURATION_H__

#define MO_CR_MEMORY_LIMIT_RATE 1.4f

MO_NAMESPACE_BEGIN

//============================================================
class FModule;
class FModuleConsole;

//============================================================
// <T>模块。</T>
//
// @history 100312 MAOCY 创建
//============================================================
class MO_CR_DECLARE FModule :
      public FObject,
      public ISingleton,
      public IConfiguration{
protected:
   TInt _code;
   TFsName _name;
public:
   FModule();
   MO_ABSTRACT ~FModule();
public:
   MO_OVERRIDE TResult Construct();
   MO_OVERRIDE TResult Dispose();
public:
   MO_ABSTRACT TResult OnLoadConfig(FXmlNode* pConfig);
   MO_ABSTRACT TResult OnInitialize() ;
   MO_ABSTRACT TResult OnReload();
   MO_ABSTRACT TResult OnUnload();
   MO_ABSTRACT TResult OnRelease();
   MO_ABSTRACT TResult OnSerialize();
   MO_ABSTRACT TResult OnUnserialize();
public:
   MO_OVERRIDE ESingleton SingletonType();
   MO_OVERRIDE TInt Code();
   MO_OVERRIDE TCharC* Name();
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
public:
   MO_OVERRIDE TResult LoadConfig(FXmlNode* pConfig);
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
public:
   TResult Initialize();
   TResult Reload();
   TResult Unload();
   TResult Release();
};
//------------------------------------------------------------
typedef MO_CR_DECLARE FList<FModule*> FModuleList;
typedef MO_CR_DECLARE FSet<TInt, FModule*> FModuleSet;

//============================================================
// <T>模块控制台。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CR_DECLARE FModuleConsole :
      public FConsole,
      public MApplicationListeners{
private:
   FModuleList* _pModules;
public:
   FModuleConsole();
   MO_ABSTRACT ~FModuleConsole();
public:
   FModuleList* Modules();
public:
   void Register(FModule* pModule);
   void Unregister(FModule* pModule);
public:
   MO_ABSTRACT TResult Initialize();
   MO_OVERRIDE TResult Interrupt();
   MO_OVERRIDE TResult Reload();
   MO_OVERRIDE TResult Unload();
   MO_ABSTRACT TResult Release();
};
//------------------------------------------------------------
class MO_CR_DECLARE RModuleManager : public RSingleton<FModuleConsole>{
};

//============================================================
// <T>模块唯一对象模板类。</T>
//
// @history 100312 MAOCY 创建
//============================================================
template <typename T>
class RModuleSingleton : public RSingleton<T>{
public:
   //------------------------------------------------------------
   // <T>初始化对象的实例。</T>
   static void Create(T* pInstance = NULL){
      RSingleton<T>::Create(pInstance);
      RConfigurationManager::Instance().Register(RSingleton<T>::_pInstance);
      RModuleManager::Instance().Register(RSingleton<T>::_pInstance);
   }
   //------------------------------------------------------------
   // <T>释放对象的实例。</T>
   static void Destroy(){
      RModuleManager::Instance().Unregister(RSingleton<T>::_pInstance);
      RConfigurationManager::Instance().Unregister(RSingleton<T>::_pInstance);
      RSingleton<T>::Destroy();
   }
};

//============================================================
// <T>共享模块。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CR_DECLARE FSharedModule :
      public FModule,
      public MShared{
public:
   FSharedModule();
   MO_ABSTRACT ~FSharedModule();
};
//------------------------------------------------------------
typedef MO_CR_DECLARE FList<FSharedModule*> FSharedModuleList;
typedef MO_CR_DECLARE FSet<TInt, FSharedModule*> FSharedModuleSet;

//============================================================
// <T>共享类型模块。</T>
//============================================================
template <typename T>
class FSharedTypeModule : public FSharedModule{
protected:
   T* _pObject;
public:
   //------------------------------------------------------------
   // 构造共享类型模块。
   FSharedTypeModule(){
      _pObject = MO_CREATE(T);
   }
   //------------------------------------------------------------
   // 析构共享类型模块。
   MO_ABSTRACT ~FSharedTypeModule(){
      MO_DELETE(_pObject);
   }
public:
   //------------------------------------------------------------
   // <T>获得对象。</T>
   T* Get(){
      return _pObject;
   }
public:
   //------------------------------------------------------------
   // <T>获得内部对象容量。</T>
   MO_OVERRIDE TSize SharedCapacity(){
      return T::CalculateCapacity();
   }
   //------------------------------------------------------------
   // <T>分配共享内存。</T>
   MO_OVERRIDE void OnSharedLink(TShareSegment& segment){
      // 创建对象
      segment.SharedLink(_pObject);
   }
public:
   //------------------------------------------------------------
   // <T>输出共享内存分配信息。</T>
   void DumpShared(){
      TChar format[MO_MEMORY_FORMATLENGTH];
      TInt capacity = T::CalculateCapacity();
      TInt total = capacity;
      MO_INFO(MO_DUMP_SHARED_FMT "%4d + %4d",
            (TCharC*)this->_name,
            RInt::FormatCapacity(total, format, MO_MEMORY_FORMATLENGTH),
            0, capacity);
   }
};

//============================================================
// <T>全局管理器的管理器。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CR_DECLARE FSharedModuleConsole : public FConsole{
protected:
   FSharedModuleList* _pModules;
public:
   FSharedModuleConsole();
   MO_ABSTRACT ~FSharedModuleConsole();
public:
   FSharedModuleList* Modules();
   void Register(FSharedModule* pModule);
   void Unregister(FSharedModule* pModule);
};
//------------------------------------------------------------
class MO_CR_DECLARE RSharedModuleManager : public RSingleton<FSharedModuleConsole>{
};

//============================================================
// <T>共享模块唯一对象模板类。</T>
//
// @history 100312 MAOCY 创建
//============================================================
template <typename T>
class RSharedModuleSingleton : public RSingleton<T>{
public:
   //------------------------------------------------------------
   // <T>初始化对象的实例。</T>
   static void Create(){
      RSingleton<T>::Create();
      RConfigurationManager::Instance().Register(RSingleton<T>::_pInstance);
      RModuleManager::Instance().Register(RSingleton<T>::_pInstance);
      RSharedModuleManager::Instance().Register(RSingleton<T>::_pInstance);
   }
   //------------------------------------------------------------
   // <T>释放对象的实例。</T>
   static void Destroy(){
      RSharedModuleManager::Instance().Unregister(RSingleton<T>::_pInstance);
      RModuleManager::Instance().Unregister(RSingleton<T>::_pInstance);
      RConfigurationManager::Instance().Unregister(RSingleton<T>::_pInstance);
      RSingleton<T>::Destroy();
   }
};

//============================================================
// <T>收集模块接口</T>
//
// @history 100414 MAOCY 创建
//============================================================
template <typename T>
class FTypeAllocator : public FList<T>{
protected:
   TInt _allocCount;
public:
   //------------------------------------------------------------
   // <T>构造收集模块。</T>
   FTypeAllocator(){
      _allocCount = 0;
   }
   //------------------------------------------------------------
   // <T>析构收集模块。</T>
   ~FTypeAllocator(){
   }
public:
   //------------------------------------------------------------
   TInt AllocCount(){
      return _allocCount;
   }
   //------------------------------------------------------------
   // <T>收集对象。</T>
   T Alloc(){
      T pObject = this->Shift();
      MO_ASSERT(pObject);
      _allocCount++;
      return pObject;
   }
   //------------------------------------------------------------
   // <T>释放对象。</T>
   void Free(T pObject){
      MO_ASSERT(pObject);
      this->Unshift(pObject);
      _allocCount--;
   }
   //------------------------------------------------------------
   // <T>存储。</T>
   void Store(T pObject){
      MO_ASSERT(pObject);
      this->Push(pObject);
   }
};

//============================================================
// <T>收集模块接口</T>
//
// @history 100414 MAOCY 创建
//============================================================
template <typename T>
class MAllocatorModule{
public:
   typedef FList<T*> FPtrList;
protected:
   FPtrList* _pItems;
   FPtrList* _pFrees;
   FPtrList* _pStorage;
public:
   //------------------------------------------------------------
   // <T>构造收集模块。</T>
   MAllocatorModule(){
      _pItems = MO_CREATE(FPtrList);
      _pFrees = MO_CREATE(FPtrList);
      _pStorage = MO_CREATE(FPtrList);
   }
   //------------------------------------------------------------
   // <T>析构收集模块。</T>
   ~MAllocatorModule(){
      MO_DELETE(_pItems);
      MO_DELETE(_pFrees);
      MO_DELETE(_pStorage);
   }
protected:
   //------------------------------------------------------------
   // <T>获得使用列表。</T>
   void InnerStore(T* pItem){
      _pFrees->Push(pItem);
      _pStorage->Push(pItem);
   }
   //------------------------------------------------------------
   // <T>收集一个对象。</T>
   T* InnerAlloc(){
      T* pItem = _pFrees->Shift();
      MO_ASSERT(pItem);
      _pItems->Push(pItem);
      return pItem;
   }
   //------------------------------------------------------------
   // <T>释放一个对象。</T>
   void InnerFree(T* pItem){
      MO_ASSERT(pItem);
      _pItems->Remove(pItem);
      _pFrees->Unshift(pItem);
   }
public:
   //------------------------------------------------------------
   // <T>获得使用列表。</T>
   FPtrList* Items(){
      return _pItems;
   }
   //------------------------------------------------------------
   // <T>获得使用列表。</T>
   FPtrList* Frees(){
      return _pFrees;
   }
   //------------------------------------------------------------
   // <T>获得存储列表。</T>
   FPtrList* Storage(){
      return _pStorage;
   }
   //------------------------------------------------------------
   // <T>收集对象。</T>
   T* Alloc(){
      T* pItem = _pStorage->Shift();
      MO_ASSERT(pItem);
      _pItems->Push(pItem);
      return pItem;
   }
   //------------------------------------------------------------
   // <T>释放对象。</T>
   void Free(T* pItem){
      MO_ASSERT(pItem);
      _pItems->Remove(pItem);
      _pStorage->Unshift(pItem);
   }
};

//============================================================
// <T>收集模块接口</T>
//
// @history 100414 MAOCY 创建
//============================================================
template <typename T>
class MAllocatorPool{
public:
   typedef FList<T> FItemList;
   typedef FVector<T> FItemVector;
protected:
   FItemList* _pItems;
   FItemList* _pFrees;
   FItemVector* _pStorage;
public:
   //------------------------------------------------------------
   // <T>构造收集模块。</T>
   MAllocatorPool(){
      _pItems = MO_CREATE(FItemList);
      _pFrees = MO_CREATE(FItemList);
      _pStorage = MO_CREATE(FItemVector);
   }
   //------------------------------------------------------------
   // <T>析构收集模块。</T>
   MO_ABSTRACT( ~MAllocatorPool() ){
      MO_DELETE(_pItems);
      MO_DELETE(_pFrees);
      MO_DELETE(_pStorage);
   }
protected:
   //------------------------------------------------------------
   // <T>获得使用列表。</T>
   T InnerStore(T pItem){
      // 检查对象
      if(NULL != pItem){
         // 放入自由链表
         _pFrees->Push(pItem);
         // 放入存储数组
         _pStorage->Push(pItem);
      }
      return pItem;
   }
   //------------------------------------------------------------
   // <T>收集一个对象。</T>
   T InnerAlloc(){
      T pItem = NULL;
      if(!_pFrees->IsEmpty()){
         pItem = _pFrees->Shift();
         _pItems->Push(pItem);
      }else{
         MO_FATAL("Free item is empty. alloc item failure. (total=%d, used=%d, free=%d)",
               _pStorage->Count(), _pItems->Count(), _pFrees->Count());
      }
      return pItem;
   }
   //------------------------------------------------------------
   // <T>收集一个对象。</T>
   T InnerAlloc(TInt index){
      T pItem = NULL;
      if(!_pFrees->IsEmpty()){
         pItem = _pStorage->Get(index);
         MO_ASSERT(_pFrees->Contains(pItem));
         _pFrees->Remove(pItem);
         _pItems->Push(pItem);
      }else{
         MO_FATAL("Free item is empty. alloc item failure. (total=%d, used=%d, free=%d)",
               _pStorage->Count(), _pItems->Count(), _pFrees->Count());
      }
      return pItem;
   }
   //------------------------------------------------------------
   // <T>释放一个对象到缓冲池最顶部。</T>
   void InnerFree(T pItem){
      // 检查对象
      if(NULL == pItem){
         return;
      }
      // 从使用集合移除
#ifdef _MO_DEBUG
      if(!_pItems->Contains(pItem)){
         MO_ERROR("Free item is not exists in use collection. (using_count=%d, free_count=%d)",
               _pItems->Count(), _pFrees->Count());
      }
#endif // _MO_DEBUG
      _pItems->Remove(pItem);
      // 放入释放集合中
      if(!_pFrees->Contains(pItem)){
         _pFrees->Unshift(pItem);
      }else{
         MO_ERROR("Free item is already exists in free collection. (using_count=%d, free_count=%d)",
               _pItems->Count(), _pFrees->Count());
      }
   }
   //------------------------------------------------------------
   // <T>释放一个对象到缓冲池最底部。</T>
   void InnerFreeLast(T pItem){
      // 检查对象
      if(NULL == pItem){
         return;
      }
      // 从使用集合移除
#ifdef _MO_DEBUG
      if(!_pItems->Contains(pItem)){
         MO_ERROR("Free item is not exists in use collection. (using_count=%d, free_count=%d)",
               _pItems->Count(), _pFrees->Count());
      }
#endif // _MO_DEBUG
      _pItems->Remove(pItem);
      // 放入释放集合中
      if(!_pFrees->Contains(pItem)){
         _pFrees->Push(pItem);
      }else{
         MO_ERROR("Free item is already exists in free collection. (using_count=%d, free_count=%d)",
               _pItems->Count(), _pFrees->Count());
      }
   }
   //------------------------------------------------------------
   // <T>释放所有对象。</T>
   void InnerReleaseAll(){
      // 删除所有子对象
      TVectorIteratorC<T> iterator = _pStorage->IteratorC();
      while(iterator.Next()){
         T pItem = *iterator;
         MO_DELETE(pItem);
      }
      // 清空集合
      _pItems->Clear();
      _pFrees->Clear();
      _pStorage->Clear();
   }
public:
   //------------------------------------------------------------
   // <T>获得使用列表。</T>
   FItemList* Items(){
      return _pItems;
   }
   //------------------------------------------------------------
   // <T>获得是否还有空闲对象。</T>
   TBool HasFrees(){
      return !_pFrees->IsEmpty();
   }
   //------------------------------------------------------------
   // <T>获得使用列表。</T>
   FItemList* Frees(){
      return _pFrees;
   }
   //------------------------------------------------------------
   // <T>获得存储列表。</T>
   FItemVector* Storage(){
      return _pStorage;
   }
};

//============================================================
// <T>收集对象接口</T>
//
// @history 110729 MAOCY 创建
//============================================================
template <typename T>
class MObjectPool{
public:
   typedef FList<T*> FObjectList;
protected:
   FObjectList* _pItems;
   FObjectList* _pFrees;
public:
   //------------------------------------------------------------
   // <T>构造收集模块。</T>
   MObjectPool(){
      _pItems = MO_CREATE(FObjectList);
      _pFrees = MO_CREATE(FObjectList);
   }
   //------------------------------------------------------------
   // <T>析构收集模块。</T>
   MO_ABSTRACT ~MObjectPool(){
      this->InnerReleaseAll();
      MO_DELETE(_pItems);
      MO_DELETE(_pFrees);
   }
protected:
   //------------------------------------------------------------
   // <T>创建对象。</T>
   MO_ABSTRACT T* InnerCreate(){
      MO_FATAL("Free object is empty. create object failure. (total=%d, used=%d, free=%d)",
            _pItems->Count() + _pFrees->Count(), _pItems->Count(), _pFrees->Count());
   }
   //------------------------------------------------------------
   // <T>删除对象。</T>
   MO_ABSTRACT void InnerDelete(T* pObject){
      MO_FATAL("Delete object is invalid.");
   }
   //------------------------------------------------------------
   // <T>获得使用列表。</T>
   T* InnerStore(T* pObject){
      MO_ASSERT(pObject);
      // 放入自由链表
      _pFrees->Push(pObject);
      return pObject;
   }
   //------------------------------------------------------------
   // <T>收集一个对象。</T>
   MO_ABSTRACT T* InnerAlloc(){
      T* pObject = NULL;
      if(!_pFrees->IsEmpty()){
         pObject = _pFrees->Shift();
      }else{
         pObject = InnerCreate();
      }
      _pItems->Push(pObject);
      return pObject;
   }
   //------------------------------------------------------------
   // <T>释放一个对象到缓冲池最顶部。</T>
   void InnerFreeFirst(T* pObject){
      MO_ASSERT(pObject);
      if(!_pItems->Contains(pObject)){
         MO_FATAL("Free object is not exists collection. (total=%d, used=%d, free=%d)",
               _pItems->Count() + _pFrees->Count(), _pItems->Count(), _pFrees->Count());
      }
      _pItems->Remove(pObject);
      _pFrees->Unshift(pObject);
   }
   //------------------------------------------------------------
   // <T>释放一个对象到缓冲池最底部。</T>
   void InnerFreeLast(T* pObject){
      MO_ASSERT(pObject);
      if(!_pItems->Contains(pObject)){
         MO_FATAL("Free object is not exists collection. (total=%d, used=%d, free=%d)",
               _pItems->Count() + _pFrees->Count(), _pItems->Count(), _pFrees->Count());
      }
      _pItems->Remove(pObject);
      _pFrees->Push(pObject);
   }
   //------------------------------------------------------------
   // <T>释放一个对象。</T>
   MO_ABSTRACT void InnerFree(T* pObject){
      InnerFreeLast(pObject);
   }
   //------------------------------------------------------------
   // <T>释放所有对象。</T>
   void InnerReleaseAll(){
      // 删除所有使用集合
      if(!_pItems->IsEmpty()){
         TListIteratorC<T*> iterator = _pItems->IteratorC();
         while(iterator.Next()){
            T* pObject = *iterator;
            MO_DELETE(pObject);
         }
         _pItems->Clear();
      }
      // 删除所有自由集合
      if(!_pFrees->IsEmpty()){
         TListIteratorC<T*> iterator = _pFrees->IteratorC();
         while(iterator.Next()){
            T* pObject = *iterator;
            MO_DELETE(pObject);
         }
         _pFrees->Clear();
      }
   }
public:
   //------------------------------------------------------------
   // <T>获得是否有使用对象。</T>
   MO_INLINE TBool HasItem(){
      return !_pItems->IsEmpty();
   }
   //------------------------------------------------------------
   // <T>获得使用列表。</T>
   MO_INLINE FObjectList* Items(){
      return _pItems;
   }
   //------------------------------------------------------------
   // <T>获得是否有空闲对象。</T>
   MO_INLINE TBool HasFree(){
      return !_pFrees->IsEmpty();
   }
   //------------------------------------------------------------
   // <T>获得使用列表。</T>
   MO_INLINE FObjectList* Frees(){
      return _pFrees;
   }
};

//============================================================
// <T>共享对象池模块</T>
//
// @history 110526 MAOCY 创建
//============================================================
template <typename T>
class FSharedPoolModule :
      public FSharedModule,
      public MObjectPool<T>{
public:
   typedef FSet<TInt, T*> FItemSet;
protected:
   TInt _capacity;
   TInt _counter;
   FItemSet* _pItemSet;
public:
   //------------------------------------------------------------
   FSharedPoolModule(){
      _capacity = 0;
      _counter = 0;
      _pItemSet = MO_CREATE(FItemSet);
   }
   //------------------------------------------------------------
   MO_ABSTRACT ~FSharedPoolModule(){
      this->InnerReleaseAll();
      MO_DELETE(_pItemSet);
   }
public:
   //------------------------------------------------------------
   TSize Capacity(){
      return _capacity;
   }
   //------------------------------------------------------------
   MO_ABSTRACT TSize ObjectCapacity(){
      return T::CalculateCapacity();
   }
   //------------------------------------------------------------
   MO_ABSTRACT T* CreateObject(){
      return MO_CREATE(T);
   }
   //------------------------------------------------------------
   // <T>加载配置信息。</T>
   MO_OVERRIDE TBool OnLoadConfig(FXmlNode* pConfig){
      if(pConfig->HasNode()){
         TXmlNodeIteratorC iterator = pConfig->NodeIteratorC();
         while(iterator.Next("Property")){
            if(iterator->IsAttribute("name", "capacity")){
               _capacity = iterator->TextAsInt();
            }
         }
      }
      MO_DEBUG("Load shared module(%s) property. (capacity=%d)", (TCharC*)_name, _capacity);
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>分配共享内存。</T>
   MO_OVERRIDE void OnSharedLink(TShareSegment& segment){
      MO_ASSERT(_capacity > 0)
      for(TInt n=0; n<_capacity; n++){
         // 创建对象
         T* pObject = CreateObject();
         segment.SharedLink(pObject);
         InnerStore(pObject);
      }
   }
   //------------------------------------------------------------
   // <T>计算共享内存大小。</T>
   MO_OVERRIDE TSize SharedCapacity(){
      TSize objectCapacity = ObjectCapacity();
      TSize capacity = objectCapacity * _capacity;
      return capacity;
   }
public:
   //------------------------------------------------------------
   T* GetByIndex(TInt index){
      return this->_pStorage->Get(index);
   }
   //------------------------------------------------------------
   T* FindByIndex(TInt index){
      return this->_pStorage->Nvl(index, NULL);
   }
   //------------------------------------------------------------
   T* GetById(TInt id){
      return this->_pItemSet->Get(id);
   }
   //------------------------------------------------------------
   T* FindById(TInt id){
      return this->_pItemSet->Find(id);
   }
public:
   //------------------------------------------------------------
   MO_ABSTRACT T* Alloc(){
      T* pItem = this->InnerAlloc();
      MO_ASSERT(pItem);
      return pItem;
   }
   //------------------------------------------------------------
   MO_ABSTRACT T* Alloc(TInt index){
      MO_ASSERT(index >= 0);
      T* pItem = this->InnerAlloc(index);
      MO_ASSERT(pItem);
      return pItem;
   }
   //------------------------------------------------------------
   MO_ABSTRACT T* AllocById(TInt id){
      MO_ASSERT(id > 0);
      T* pItem = this->Alloc();
      this->_pItemSet->Set(id, pItem);
      return pItem;
   }
   //------------------------------------------------------------
   MO_ABSTRACT void Free(T* pItem){
      MO_ASSERT(pItem);
      this->InnerFree(pItem);
   }
   //------------------------------------------------------------
   MO_ABSTRACT void FreeById(TInt id){
      MO_ASSERT(id > 0);
      T* pItem = GetById(id);
      MO_ASSERT(pItem);
      this->Free(pItem);
   }
public:
   //------------------------------------------------------------
   // <T>根据编号，获得关联对象。</T>
   T* LinkById(TInt id){
      MO_ASSERT(id > 0);
      T* pItem = _pItemSet->Find(id);
      if(NULL != pItem){
         MO_FATAL("Item is already exists. (name=%s, id=%d)", (TCharC*)_name, id);
      }
      pItem = Alloc();
      pItem->SetId(id);
      _pItemSet->Set(id, pItem);
      return pItem;
   }
   //------------------------------------------------------------
   // <T>根据编号，获得同步对象。</T>
   T* SyncById(TInt id){
      MO_ASSERT(id > 0);
      T* pItem = _pItemSet->Find(id);
      if(NULL == pItem){
         pItem = Alloc();
         pItem->SetId(id);
         _pItemSet->Set(id, pItem);
      }
      return pItem;
   }
   //------------------------------------------------------------
   void Unlink(T* pItem){
      TInt id = pItem->Id();
      MO_ASSERT(id > 0);
      T* pFind = _pItemSet->Find(id);
      if(NULL != pFind){
         MO_ASSERT(pFind == pItem);
         _pItemSet->Set(id, NULL);
         InnerFree(pFind);
      }
   }
public:
   //------------------------------------------------------------
   // <T>输出共享内存的分配信息。</T>
   void DumpShared(){
      TChar format[MO_MEMORY_FORMATLENGTH];
      TInt capacity = T::CalculateCapacity();
      TInt total = capacity * _capacity;
      MO_INFO(MO_DUMP_SHARED_FMT "%4d + %4d * %d",
            (TCharC*)_name,
            RInt::FormatCapacity(total, format, MO_MEMORY_FORMATLENGTH),
            0, capacity, _capacity);
   }
};

MO_NAMESPACE_END

#endif // __MO_CR_MODULE_H__
