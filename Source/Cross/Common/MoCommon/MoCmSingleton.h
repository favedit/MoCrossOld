#ifndef __MO_CM_SINGLETON_H__
#define __MO_CM_SINGLETON_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_LOCK_H__
#include "MoCmLock.h"
#endif // __MO_CM_LOCK_H__

#ifndef __MO_CM_CLASSBASE_H__
#include "MoCmClassBase.h"
#endif // __MO_CM_CLASSBASE_H__

#ifndef __MO_CM_LANGUAGE_H__
#include "MoCmLanguage.h"
#endif // __MO_CM_LANGUAGE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>唯一对象类型。</T>
//
// @history 100309 MAOCY 创建
//============================================================
enum ESingleton{
   ESingleton_Object,
   ESingleton_Module,  // 模块
   ESingleton_Manager, // 管理器
   ESingleton_Service, // 服务
   ESingleton_Server   // 服务器
};

//============================================================
// <T>唯一对象接口。</T>
//
// @history 100309 MAOCY 创建
//============================================================
class MO_CM_DECLARE ISingleton : public IDispose{
public:
   MO_ABSTRACT ~ISingleton(){
   }
public:
   MO_VIRTUAL ESingleton SingletonType() = 0;
   MO_VIRTUAL TInt Code() = 0;
   MO_VIRTUAL TCharC* Name() = 0;
public:
   MO_VIRTUAL TResult Construct() = 0;
};
//------------------------------------------------------------
typedef FList<ISingleton*> FSingletonList;
typedef FSet<TInt, ISingleton*> FSingletonSet;

//============================================================
// <T>唯一对象基类。</T>
//
// @history 100424 MAOCY 创建
//============================================================
class MO_CM_DECLARE MSingleton : public ISingleton{
protected:
   TInt _code;
   TFsName _name;
public:
   MSingleton();
   MO_ABSTRACT ~MSingleton();
public:
   MO_OVERRIDE ESingleton SingletonType();
   MO_OVERRIDE TInt Code();
   MO_OVERRIDE TCharC* Name();
public:
   MO_OVERRIDE TResult Construct();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
// <T>唯一对象控制台。</T>
//
// @history 100309 MAOCY 创建
//============================================================
class MO_CM_DECLARE FSingletonConsole{
protected:
   FSingletonList* _pSingletons;
public:
   FSingletonConsole();
   MO_ABSTRACT ~FSingletonConsole();
public:
   FSingletonList* Singletons();
   void Register(ISingleton* pSingleton);
   void Unregister(ISingleton* pSingleton);
};

//============================================================
// <T>唯一对象管理器。</T>
//
// @history 100309 MAOCY 创建
//============================================================
class MO_CM_DECLARE RSingletonManager{
protected:
   static TThreadLocker _locker;
   static FSingletonConsole* _pInstance;
public:
   //------------------------------------------------------------
   // <T>获得对象的实例。</T>
   static MO_INLINE FSingletonConsole& Instance(){
      return *_pInstance;
   }
   //------------------------------------------------------------
   // <T>获得对象的实例指针。</T>
   static MO_INLINE FSingletonConsole* InstancePtr(){
      return _pInstance;
   }
   //------------------------------------------------------------
   // <T>安全获得对象的实例。</T>
   static MO_INLINE FSingletonConsole& SafeInstance(){
      _locker.Enter();
      if(_pInstance == NULL){
         Create();
      }
      _locker.Leave();
      return *_pInstance;
   }
   //------------------------------------------------------------
   // <T>安全获得对象的实例指针。</T>
   static MO_INLINE FSingletonConsole* SafeInstancePtr(){
      if(_pInstance == NULL){
         Create();
      }
      return _pInstance;
   }
public:
   static void Create();
   static void Destroy();
public:
   //------------------------------------------------------------
   // <T>安全初始化对象的实例。</T>
   static MO_INLINE void SafeCreate(){
      _locker.Enter();
      if(_pInstance == NULL){
         Create();
      }
      _locker.Leave();
   }
   //------------------------------------------------------------
   // <T>安全释放对象的实例。</T>
   static MO_INLINE void SafeDestroy(){
      _locker.Enter();
      if(_pInstance != NULL){
         Destroy();
      }
      _locker.Leave();
   }

public:
   static void Register(ISingleton* pSingleton);
   static void Unregister(ISingleton* pSingleton);
};

//============================================================
// <T>唯一对象实体化类。</T>
//
// @history 100309 MAOCY 创建
//============================================================
template <typename T>
class RSingleton{
protected:
   static TThreadLocker _locker;
   static T* _pInstance;
public:
   //------------------------------------------------------------
   // <T>初始化对象的实例。</T>
   static void Create(T* pInstance = NULL){
      // 设置内容
      MO_ASSERT(!_pInstance);
      if(NULL == pInstance){
         _pInstance = MO_PTR_CREATE(T);
      }else{
         _pInstance = pInstance;
      }
      // 构建对象
#ifdef _MO_ANDROID
      TClassInfo info = MO_TYPENAME(T);
#else
      TClassInfo info = typeid(T).name();
#endif // _MO_ANDROID
      MO_STATIC_DEBUG(TC("Initialize singleton instance. (instance=0x%08X, class=%s)"), _pInstance, info.FullName());
      _pInstance->Construct();
      // 注册管理
      RSingletonManager::Register(_pInstance);
   }
   //------------------------------------------------------------
   // <T>释放对象的实例。</T>
   static void Destroy(){
      // 注销管理
      MO_ASSERT(_pInstance);
      RSingletonManager::Unregister(_pInstance);
      // 删除对象
#ifdef _MO_ANDROID
      TClassInfo info = MO_TYPENAME(T);
#else
      TClassInfo info = typeid(T).name();
#endif // _MO_ANDROID
      MO_STATIC_DEBUG(TC("Release singleton instance. (instance=0x%08X, class=%s)"), _pInstance, info.FullName());
      MO_PTR_DELETE(_pInstance);
   }
public:
   //------------------------------------------------------------
   // <T>安全初始化对象的实例。</T>
   static MO_INLINE void SafeCreate(T* pInstance = NULL){
      _locker.Enter();
      if(_pInstance == NULL){
         Create();
      }
      _locker.Leave();
   }
   //------------------------------------------------------------
   // <T>安全释放对象的实例。</T>
   static MO_INLINE void SafeDestroy(){
      _locker.Enter();
      if(_pInstance != NULL){
         Destroy();
      }
      _locker.Leave();
   }
public:
   //------------------------------------------------------------
   // <T>获得是否有效。</T>
   static TBool IsValid(){
      return (NULL != _pInstance);
   }
public:
   //------------------------------------------------------------
   // <T>获得对象的实例。</T>
   static MO_INLINE T& Instance(){
      return *_pInstance;
   }
   //------------------------------------------------------------
   // <T>获得对象的实例指针。</T>
   static MO_INLINE T* InstancePtr(){
      return _pInstance;
   }
   //------------------------------------------------------------
   // <T>安全获得对象的实例。</T>
   static MO_INLINE T& SafeInstance(){
      _locker.Enter();
      if(_pInstance == NULL){
         Create();
      }
      _locker.Leave();
      return *_pInstance;
   }
   //------------------------------------------------------------
   // <T>安全获得对象的实例指针。</T>
   static MO_INLINE T* SafeInstancePtr(){
      _locker.Enter();
      if(_pInstance == NULL){
         Create();
      }
      _locker.Leave();
      return _pInstance;
   }
};
//------------------------------------------------------------
template <typename T> TThreadLocker RSingleton<T>::_locker;
template <typename T> T* RSingleton<T>::_pInstance = NULL;

MO_NAMESPACE_END

#endif // __MO_CM_SINGLETON_H__
