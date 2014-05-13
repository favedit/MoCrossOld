#ifndef __MO_BG_CORE_H__
#define __MO_BG_CORE_H__
//************************************************************

#ifndef __MO_BG_COMMON_H__
#include "MoBgCommon.h"
#endif // __MO_BG_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>设备工具。</T>
//============================================================
template <class T>
class MO_BG_DECLARE FBridgeObject : public FInstance
{
   T* _pInstance;
   FScriptInstance* _pScriptInstance;
public:
   //------------------------------------------------------------
   // <T>构造桥接对象。</T>
   FBridgeObject(){
      MO_CLEAR(_pDevice);
      MO_CLEAR(_pScriptInstance);
   }
   //------------------------------------------------------------
   // <T>析构桥接对象。</T>
   MO_ABSTRACT ~FBridgeObject(){
   }
public:
   //------------------------------------------------------------
   // <T>获得实例。</T>
   MO_INLINE operator T*(){
      return _pInstance;
   }
public:
   //------------------------------------------------------------
   // <T>获得实例。</T>
   MO_INLINE T* Instance(){
      return _pInstance;
   }
   //------------------------------------------------------------
   // <T>设置实例。</T>
   MO_INLINE void SetInstance(T* pInstance){
      _pInstance = pInstance;
   }
   //------------------------------------------------------------
   // <T>获得脚本实例。</T>
   MO_INLINE FScriptInstance* ScriptInstance(){
      return _pScriptInstance;
   }
   //------------------------------------------------------------
   // <T>设置脚本实例。</T>
   MO_INLINE void SetScriptInstance(FScriptInstance* pScriptInstance){
      _pScriptInstance = pScriptInstance;
   }
public:
   //------------------------------------------------------------
   // <T>调用处理。</T>
   TResult Invoke(){
      return ESuccess;
   }
};

//============================================================
struct SBridgeLinker{
   TUint32 instanceId;
};

//============================================================
// <T>桥接控制台。</T>
//============================================================
class MO_BG_DECLARE FBridgeConsole : public FConsole
{
protected:
   FClassFactory* _pClassFactory;
public:
   FBridgeConsole();
   MO_ABSTRACT ~FBridgeConsole();
public:
   //------------------------------------------------------------
   // <T>获得类工厂。</T>
   MO_INLINE FClassFactory* ClassFactory(){
      return _pClassFactory;
   }
public:
   TInt CreateObject(SBridgeLinker* pLinker, TCharC* pClassName);
};

//============================================================
// <T>桥接管理器。</T>
//============================================================
class MO_BG_DECLARE RBridgeManager : public RSingleton<FBridgeConsole>
{
};

EXTERN_C MO_BG_DECLARE TInt RBridgeManager_CreateObject(SBridgeLinker* pLinker, TCharC* pClassName);
EXTERN_C MO_BG_DECLARE TInt RBridgeManager_Invoke(SBridgeLinker* pLinker, TCharC* pMethodName);

MO_NAMESPACE_END

//************************************************************
#endif // __MO_BG_CORE_H__
