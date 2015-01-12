//============================================================
// <T>线程锁管理类</T>
//============================================================
#ifndef __MO_CM_CLASSBASE_H__
#define __MO_CM_CLASSBASE_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_LOOPER_H__
#include "MoCmLooper.h"
#endif // __MO_CM_LOOPER_H__

#ifndef __MO_CM_STRING_H__
#include "MoCmString.h"
#endif // __MO_CM_STRING_H__

#ifndef __MO_CM_DICTIONARY_H__
#include "MoCmDictionary.h"
#endif // __MO_CM_DICTIONARY_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>类定义。</T>
class FClass;
class FInstance;
typedef MO_CM_DECLARE TList<FInstance*> TInstanceList;
typedef MO_CM_DECLARE TLooper<FInstance*> TInstanceLooper;
typedef MO_CM_DECLARE FDictionary<FInstance*> FInstanceDictionary;

//============================================================
// <T>类信息定义。</T>
// <P>解析格式：N2MO21FConfigurationConsoleE。</P>
//============================================================
class MO_CM_DECLARE TClassInfo{
protected:
   TFsName _space;
   TFsName _name;
   TFsName _fullName;
public:
   TClassInfo();
   TClassInfo(TChar8C* pValue);
   TClassInfo(TChar16C* pValue);
   MO_ABSTRACT ~TClassInfo();
public:
   //------------------------------------------------------------
   // <T>获得命名空间。</T>
   MO_INLINE TCharC* Space(){
      return _space;
   }
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>获得全称。</T>
   MO_INLINE TCharC* FullName(){
      return _fullName.MemoryC();
   }
public:
   TBool Parse(TCharC* pValue);
};

//============================================================
// <T>类对象。</T>
//
// @class
// @history 140217 MAOCY 创建
//============================================================
class MO_CM_DECLARE FClass : public FBase{
protected:
   TInt _looperLimit;
   TCharC* _pParentClassName;
   FClass* _pParentClass;
   TCharC* _pName;
   TInt _memorySize;
   TInt64 _activeCount;
   TInt64 _allocTotal;
   TInstanceList _pool;
   TInstanceLooper _looper;
public:
   FClass();
   MO_ABSTRACT ~FClass();
public:
   //------------------------------------------------------------
   // <T>判断是否指定名称。</T>
   MO_INLINE TBool IsName(TCharC* pName){
      return RString::Equals(_pName, pName);
   }
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _pName;
   }
   //------------------------------------------------------------
   // <T>获得内存大小。</T>
   MO_INLINE TInt MemorySize(){
      return _memorySize;
   }
   //------------------------------------------------------------
   // <T>获得激活个数。</T>
   MO_INLINE TInt64 ActiveCount(){
      return _activeCount;
   }
   //------------------------------------------------------------
   // <T>获得收集总数。</T>
   MO_INLINE TInt64 AllocTotal(){
      return _allocTotal;
   }
   //------------------------------------------------------------
   // <T>获得实例集合。</T>
   MO_INLINE TInstanceList& Pool(){
      return _pool;
   }
   //------------------------------------------------------------
   // <T>获得实例集合。</T>
   MO_INLINE TInstanceLooper& Looper(){
      return _looper;
   }
public:
   FClass* ParentClass();
public:
   TBool IsInheritsFrom(TCharC* pClassName);
   TBool IsInheritsFrom(FClass* pClass);
public:
   MO_VIRTUAL FInstance* InstanceCreate() = 0;
   MO_VIRTUAL FInstance* InstanceAlloc() = 0;
   MO_VIRTUAL void InstanceFree(FInstance* pInstance) = 0;
   MO_VIRTUAL void InstanceDelete(FInstance* pInstance) = 0;
public:
   FInstance* InstanceInheritCreate(FClass* pClass);
   template <class T>
   T* InstanceInheritCreate(){
      return (T*)InstanceInheritCreate(T::Class());
   }
public:
   MO_ABSTRACT TResult Process();
   MO_OVERRIDE TCharC* Dump(TChar* pDump, TInt capacity);
};
//------------------------------------------------------------
typedef MO_CM_DECLARE FVector<FClass*> FClassCollection;
typedef MO_CM_DECLARE TLooper<FClass*> TClassLooper;
typedef MO_CM_DECLARE FDictionary<FClass*> FClassDictionary;
typedef MO_CM_DECLARE TDictionary<FClass*> TClassDictionary;

//============================================================
// <T>类模板对象。</T>
//
// @class
// @history 140217 MAOCY 创建
//============================================================
template <class T>
class FClassTemplate : public FClass{
public:
   //------------------------------------------------------------
   // <T>创建类实例。</T>
   FClassTemplate(TCharC* pName, TCharC* pParentName, TInt memorySize){
      _pName = pName;
      _pParentClassName = pParentName;
      _memorySize = memorySize;
   }
public:
   //------------------------------------------------------------
   // <T>创建类实例。</T>
   MO_OVERRIDE FInstance* InstanceCreate(){
      FInstance* pInstance = MO_CREATE(T);
      // _looper.Push(pInstance);
      _activeCount++;
      _allocTotal++;
      return pInstance;
   }
   //------------------------------------------------------------
   // <T>收集类实例。</T>
   MO_OVERRIDE FInstance* InstanceAlloc(){
      _activeCount++;
      _allocTotal++;
      T* pInstance = NULL;
      if(_pool.IsEmpty()){
         pInstance = MO_CREATE(T);
         // _looper.Push(pInstance);
      }else{
         pInstance = (T*)_pool.Shift();
      }
      return pInstance;
   }
   //------------------------------------------------------------
   // <T>释放类实例。</T>
   MO_OVERRIDE void InstanceFree(FInstance* pInstance){
      MO_ASSERT(pInstance);
      _activeCount--;
      _pool.Push(pInstance);
   }
   //------------------------------------------------------------
   // <T>删除类实例。</T>
   MO_OVERRIDE void InstanceDelete(FInstance* pInstance){
      MO_ASSERT(pInstance);
      _activeCount--;
      T* pFreeable = (T*)pInstance;
      MO_DELETE(pFreeable);
   }
};

//============================================================
// <T>虚类模板对象。</T>
//
// @class
// @history 140217 MAOCY 创建
//============================================================
template <class T>
class FClassAbstractTemplate : public FClass{
public:
   //------------------------------------------------------------
   // <T>创建虚类模板对象。</T>
   FClassAbstractTemplate(TCharC* pName, TCharC* pParentName, TInt memorySize){
      _pName = pName;
      _pParentClassName = pParentName;
      _memorySize = memorySize;
   }
public:
   //------------------------------------------------------------
   // <T>创建类实例。</T>
   MO_OVERRIDE FInstance* InstanceCreate(){
      MO_FATAL(TC("Abstract class disable instance create"));
      return NULL;
   }
   //------------------------------------------------------------
   // <T>收集类实例。</T>
   MO_OVERRIDE FInstance* InstanceAlloc(){
      MO_FATAL(TC("Abstract class disable instance alloc"));
      return NULL;
   }
   //------------------------------------------------------------
   // <T>释放类实例。</T>
   MO_OVERRIDE void InstanceFree(FInstance* pInstance){
      MO_FATAL(TC("Abstract class disable instance free"));
   }
   //------------------------------------------------------------
   // <T>删除类实例。</T>
   MO_OVERRIDE void InstanceDelete(FInstance* pInstance){
      MO_FATAL(TC("Abstract class disable instance delete"));
   }
};

//============================================================
// <T>全局指针。</T>
//
// @template
//============================================================
template <typename T>
class GPtr{
protected:
   T* _pInstance;
public:
   //------------------------------------------------------------
   // <T>构造全局指针。</T>
   MO_INLINE GPtr(){
      MO_CLEAR(_pInstance);
   }
   //------------------------------------------------------------
   // <T>构造全局指针。</T>
   MO_INLINE GPtr(T* pInstance){
      _pInstance = pInstance;
      if(_pInstance != NULL){
         _pInstance->ReferIncrease();
      }
   }
   //------------------------------------------------------------
   // <T>构造全局指针。</T>
   MO_INLINE GPtr(const GPtr<T>& instance){
      _pInstance = instance._pInstance;
      if(_pInstance != NULL){
         _pInstance->ReferIncrease();
      }
   }
   //------------------------------------------------------------
   // <T>析构全局指针。</T>
   MO_INLINE ~GPtr(){
      if(_pInstance != NULL){
         _pInstance->ReferDecrease();
      }
   }
public:
   //------------------------------------------------------------
   // <T>获取对象地址。</T>
   MO_INLINE T& operator *(){
      return *_pInstance;
   }
   //------------------------------------------------------------
   // <T>获取对象指针。</T>
   MO_INLINE T* operator ->(){
      MO_ASSERT(_pInstance);
      return _pInstance;
   }
   //------------------------------------------------------------
   // <T>转换为布尔值。</T>
   MO_INLINE operator TBool() const{
	   return (_pInstance != NULL);
   }
   //------------------------------------------------------------
   // <T>转换为类型。</T>
   MO_INLINE operator T*() const{
      return _pInstance;
   }
public:
   //------------------------------------------------------------
   // <T>设置全局指针。</T>
   MO_INLINE void operator = (T* pInstance){
      Set(pInstance);
   }
   //------------------------------------------------------------
   // <T>设置全局指针。</T>
   MO_INLINE void operator = (const GPtr<T>& instance){
      Set(instance._pInstance);
   }
public:
   //------------------------------------------------------------
   // <T>判断指针是否相等。</T>
   MO_INLINE TBool operator == (const T* pInstance) const{
       return (_pInstance == pInstance);
   }
   //------------------------------------------------------------
   // <T>判断指针是否相等。</T>
   MO_INLINE TBool operator == (T* pInstance) const{
       return (_pInstance == pInstance);
   }
   //------------------------------------------------------------
   // <T>判断指针是否相等。</T>
   MO_INLINE TBool operator == (const GPtr<T>& instance) const{
      return (_pInstance == instance._pInstance);
   }
   //------------------------------------------------------------
   // <T>判断指针是否不相等。</T>
   MO_INLINE TBool operator != (const T* pInstance) const{
      return (_pInstance != pInstance);
   }
   //------------------------------------------------------------
   // <T>判断指针是否不相等。</T>
   MO_INLINE TBool operator != (T* pInstance) const{
      return (_pInstance != pInstance);
   }
   //------------------------------------------------------------
   // <T>判断指针是否不相等。</T>
   MO_INLINE TBool operator != (const GPtr<T>& instance) const{
      return (_pInstance != instance._pInstance);
   }
   //------------------------------------------------------------
   // <T>判断指针是否小于。</T>
   MO_INLINE TBool operator < (const GPtr<T>& instance) const{
      return (_pInstance < instance._pInstance);
   }
   //------------------------------------------------------------
   // <T>判断指针是否大于。</T>
   MO_INLINE TBool operator > (const GPtr<T>& instance) const{
      return (_pInstance > instance._pInstance);
   }
public:
   //------------------------------------------------------------
   // <T>获得全局指针。</T>
   MO_INLINE T* Get() const{
      return _pInstance;
   }
   //------------------------------------------------------------
   // <T>安全转换为指定类型。</T>
   template<class C>
   const C* Convert() const{
#ifdef _MO_DEBUG
      C* pInstance = NULL;
      if(_pInstance != NULL){
         pInstance = _pInstance->Convert<C>();
      }
      return pInstance;
#else
      return (C*)_pInstance;
#endif // _MO_DEBUG
   }
   //------------------------------------------------------------
   // <T>安全转换为指定基础类型。</T>
   template<class C>
   const C* ConvertParent() const{
#ifdef _MO_DEBUG
      RClassManager::CheckInherit(T::Class(), C::Class());
#endif // _MO_DEBUG
      return (C*)_pInstance;
   }
   //------------------------------------------------------------
   // <T>安全转换为指定派生类型。</T>
   template<class C>
   const C* CastInherit() const{
#ifdef _MO_DEBUG
      RClassManager::CheckInherit(C::Class(), T::Class());
#endif // _MO_DEBUG
      return (C*)_pInstance;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否有效。</T>
   MO_INLINE TBool IsValid() const{
      return (_pInstance != NULL);
   }
   //------------------------------------------------------------
   // <T>获得引用次数。</T>
   MO_INLINE TInt ReferCount(){
      TInt referCount = 0;
      if(_pInstance != NULL){
         referCount = _pInstance->ReferCount();
      }
      return referCount;
   }
   //------------------------------------------------------------
   // <T>增加一次引用。</T>
   MO_INLINE void ReferIncrease(){
      if(_pInstance != NULL){
         _pInstance->ReferIncrease();
      }
   }
   //------------------------------------------------------------
   // <T>减少一次引用。</T>
   MO_INLINE TResult ReferDecrease(){
      TResult resultCd = ESuccess;
      if(_pInstance != NULL){
         resultCd = _pInstance->ReferDecrease();
         if(resultCd == ERelease){
            MO_CLEAR(_pInstance);
         }
      }
      return resultCd;
   }
   //------------------------------------------------------------
   // <T>设置指针。</T>
   MO_INLINE void Set(T* pInstance){
      if(_pInstance != pInstance){
         // 内部记数减
         if(_pInstance != NULL){
            TResult resultCd = _pInstance->ReferDecrease();
            if(resultCd == ERelease){
               MO_CLEAR(_pInstance);
            }
         }
         // 外部记数加
         _pInstance = pInstance;
         if(_pInstance != NULL){
            _pInstance->ReferIncrease();
         }
      }
   }
   //------------------------------------------------------------
   // <T>设置指针。</T>
   MO_INLINE void Set(const GPtr<T>& instance){
      Set(instance._pInstance);
   }
   //------------------------------------------------------------
   // <T>交换全局指针。</T>
   MO_INLINE void Swap(GPtr<T>& instance) {
      T* pTempInstance = instance._pInstance;
      instance._pInstance = _pInstance;
	   _pInstance = pTempInstance;
   }
   //------------------------------------------------------------
   // <T>返回对象，但是不修改引用。</T>
   MO_INLINE T* Flip(){
      T* pInstance = _pInstance;
      _pInstance = NULL;
      return pInstance;
   }
   //------------------------------------------------------------
   // <T>重置对象。</T>
   MO_INLINE void Reset(){
      if(_pInstance != NULL){
         _pInstance->ReferDecrease();
         MO_CLEAR(_pInstance);
      }
   }
   //------------------------------------------------------------
   // <T>释放对象。</T>
   MO_INLINE void Release(){
      if(_pInstance != NULL){
         _pInstance->Release();
         MO_CLEAR(_pInstance);
      }
   }
};

//============================================================
// <T>定义类声明。</T>
// <P>必须从Instance对象派生出来。</P>
//============================================================
#define MO_CLASS_DECLARE(T) \
private: \
   static FClass* _pClass; \
public: \
   static MO_INLINE FClass* Class(){ \
      MO_ASSERT(_pClass); \
      return _pClass; \
   } \
   static MO_INLINE T* InstanceCreate(){ \
      MO_ASSERT(_pClass); \
      T* pInstance = (T*)_pClass->InstanceCreate(); \
      return pInstance; \
   } \
   static MO_INLINE T* InstanceAlloc(){ \
      MO_ASSERT(_pClass); \
      T* pInstance = (T*)_pClass->InstanceAlloc(); \
      return pInstance; \
   } \
   static MO_INLINE void InstanceFree(T* pInstance){ \
      MO_ASSERT(_pClass); \
      return _pClass->InstanceFree(pInstance); \
   } \
   static MO_INLINE void InstanceDelete(T* pInstance){ \
      MO_ASSERT(_pClass); \
      return _pClass->InstanceDelete(pInstance); \
   } \
public: \
   MO_ABSTRACT FClass* GetClass(); \

//============================================================
// <T>定义类继承声明。</T>
// <P>必须从Instance对象派生出来。</P>
//============================================================
#define MO_CLASS_DECLARE_INHERITS(T, B) \
   MO_CLASS_DECLARE(T) \
private: \
   static FClass* _pParentClass; \
public: \
   static MO_INLINE FClass* ParentClass(){ \
      if(_pParentClass == NULL){ \
         _pParentClass = _pClass->ParentClass(); \
      } \
      return _pParentClass; \
   } \
public: \
   MO_ABSTRACT FClass* GetParentClass(); \

//============================================================
// <T>定义虚类声明。</T>
// <P>必须从Instance对象派生出来。</P>
//============================================================
#define MO_CLASS_ABSTRACT_DECLARE(T) \
private: \
   static FClass* _pClass; \
public: \
   static MO_INLINE FClass* Class(){ \
      return _pClass; \
   } \
public: \
   MO_ABSTRACT FClass* GetClass(); \

//============================================================
// <T>定义类继承声明。</T>
// <P>必须从Instance对象派生出来。</P>
//============================================================
#define MO_CLASS_ABSTRACT_DECLARE_INHERITS(T, B) \
   MO_CLASS_ABSTRACT_DECLARE(T) \
private: \
   static FClass* _pParentClass; \
public: \
   static MO_INLINE FClass* ParentClass(){ \
      if(_pParentClass == NULL){ \
         _pParentClass = _pClass->ParentClass(); \
      } \
      return _pParentClass; \
   } \
public: \
   MO_ABSTRACT FClass* GetParentClass(); \

//============================================================
// <T>实例对象。</T>
//============================================================
class MO_CM_DECLARE FInstance : public FObject
{
   MO_CLASS_DECLARE(FInstance);
protected:
    volatile TInt _referCount;
public:
   FInstance();
   MO_ABSTRACT ~FInstance();
public:
   //------------------------------------------------------------
   // <T>获得引用次数。</T>
   MO_INLINE TInt ReferCount(){
      return _referCount;
   }
   //------------------------------------------------------------
   // <T>增加一次引用。</T>
   MO_INLINE void ReferIncrease(){
      _referCount++;
   }
   //------------------------------------------------------------
   // <T>减少一次引用。</T>
   MO_INLINE TResult ReferDecrease(){
      MO_ASSERT(_referCount > 0);
      // 减少引用
      _referCount--;
      // 释放对象
      if(_referCount == 0){
         Release();
         return ERelease;
      }
      return ESuccess;
   }
   //------------------------------------------------------------
   // <T>释放对象。</T>
   MO_INLINE void Release(){
      _referCount = 0;
      InstanceDelete(this);
   }
public:
   TBool IsInstanceOf(TCharC* pClassName);
   TBool IsInstanceOf(FClass* pClass);
   TBool IsInheritsFrom(TCharC* pClassName);
   TBool IsInheritsFrom(FClass* pClass);
   FInstance* Convert(FClass* pClass);
public:
   //------------------------------------------------------------
   // <T>转换类型。</T>
   template <class T>
   MO_INLINE T* Convert(){
#ifdef _MO_DEBUG
      return (T*)Convert(T::Class());
#else
      return (T*)this;
#endif // _MO_DEBUG
   }
};
//------------------------------------------------------------
typedef MO_CM_DECLARE GPtr<FInstance> GInstancePtr;

//============================================================
// <T>类工厂。</T>
//
// @class
// @history 140308 MAOCY 创建
//============================================================
class MO_CM_DECLARE FClassFactory : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FClassFactory, FInstance);
protected:
   FClassDictionary* _pClasses;
public:
   FClassFactory();
   MO_ABSTRACT ~FClassFactory();
public:
   //------------------------------------------------------------
   // <T>获得类定义集合。</T>
   MO_INLINE FClassDictionary* Classes(){
      return _pClasses;
   }
public:
   TResult Register(FClass* pClass);
   TResult Register(TCharC* pName, FClass* pClass);
   TResult Unregister(TCharC* pName);
public:
   TBool Contains(TCharC* pName);
   FInstance* Create(TCharC* pName);
   FInstance* Alloc(TCharC* pName);
   void Free(FInstance* pInstance);
   void Delete(FInstance* pInstance);
public:
   //------------------------------------------------------------
   // <T>创建类对象实例。</T>
   template <class T>
   MO_INLINE T* Create(TCharC* pName){
      FInstance* pInstance = this->Create(pName);
      return pInstance->Convert<T>();
   }
   //------------------------------------------------------------
   // <T>收集类对象实例。</T>
   template <class T>
   MO_INLINE T* Alloc(TCharC* pName){
      FInstance* pInstance = this->Alloc(pName);
      return pInstance->Convert<T>();
   }
public:
   TCharC* Dump(TChar* pDump, TInt capacity);
};

//============================================================
// <T>实例类工厂。</T>
//
// @class
// @history 140315 MAOCY 创建
//============================================================
class MO_CM_DECLARE FClassInstanceFactory : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FClassInstanceFactory, FInstance);
protected:
   FClass* _pDefaultClass;
   FClassDictionary* _pClasses;
   GInstancePtr _defaultInstance;
   FInstanceDictionary* _pInstances;
public:
   FClassInstanceFactory();
   MO_ABSTRACT ~FClassInstanceFactory();
public:
   //------------------------------------------------------------
   // <T>获得默认类。</T>
   MO_INLINE FClass* DefaultClass(){
      return _pDefaultClass;
   }
   //------------------------------------------------------------
   // <T>设置默认类。</T>
   MO_INLINE void SetDefaultClass(FClass* pClass){
      _pDefaultClass = pClass;
   }
   //------------------------------------------------------------
   // <T>获得类字典。</T>
   MO_INLINE FClassDictionary* Classes(){
      return _pClasses;
   }
   //------------------------------------------------------------
   // <T>获得实例字典。</T>
   MO_INLINE FInstanceDictionary* Instances(){
      return _pInstances;
   }
public:
   TResult Register(TCharC* pName, FClass* pClass);
   TResult Unregister(TCharC* pName);
public:
   FInstance* Find(TCharC* pName);
   FInstance* Get(TCharC* pName);
   TResult Free(TCharC* pName);
public:
   TCharC* Dump(TChar* pDump, TInt capacity);
};
//------------------------------------------------------------
typedef MO_CM_DECLARE GPtr<FClassInstanceFactory> GClassInstanceFactoryPtr;
   
MO_NAMESPACE_END

#endif // __MO_CM_CLASSBASE_H__
