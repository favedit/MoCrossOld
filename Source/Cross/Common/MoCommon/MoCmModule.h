#ifndef __MO_CM_MODULE_H__
#define __MO_CM_MODULE_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_CLASSBASE_H__
#include "MoCmClassBase.h"
#endif // __MO_CM_CLASSBASE_H__

#ifndef __MO_CM_SINGLETON_H__
#include "MoCmSingleton.h"
#endif // __MO_CM_SINGLETON_H__

MO_NAMESPACE_BEGIN

//============================================================
class FXmlNode;

//============================================================
// <T>可配置数据接口。<T>
//============================================================
class MO_CM_DECLARE IConfig{
public:
   //------------------------------------------------------------
   // <T>析构可配置数据接口。<T>
   MO_ABSTRACT ~IConfig(){
   }
public:
   MO_VIRTUAL TResult LoadConfig(FXmlNode* pConfig) = 0;
};
//------------------------------------------------------------
typedef FList<IConfig*> FConfigList;
typedef FSet<TInt, IConfig*> FConfigSet;

//============================================================
// <T>控制台接口。</T>
//============================================================
class MO_CM_DECLARE IConsole :
      public ISingleton,
      public IConfig{
public:
   MO_VIRTUAL TResult OnSerialize() = 0;
   MO_VIRTUAL TResult OnUnserialize() = 0;
public:
   MO_VIRTUAL TResult Suspend() = 0;
   MO_VIRTUAL TResult Resume() = 0;
};
//------------------------------------------------------------
typedef MO_CM_DECLARE FList<IConsole*> FManagerList;
typedef MO_CM_DECLARE FSet<TInt, IConsole*> FManagerSet;

//============================================================
// <T>控制台基类。</T>
//
// @history 100303 MAOCY 创建
//============================================================
class MO_CM_DECLARE FConsole :
      public FInstance,
      public IConsole
{
   MO_CLASS_DECLARE_INHERITS(FConsole, FInstance);
protected:
   TInt _code;
   TFsName _name;
public:
   FConsole();
   MO_ABSTRACT ~FConsole();
public:
   MO_OVERRIDE TResult Construct();
   MO_OVERRIDE TResult Dispose();
public:
   MO_OVERRIDE ESingleton SingletonType();
   MO_OVERRIDE TInt Code();
   MO_OVERRIDE TCharC* Name();
public:
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
public:
   MO_OVERRIDE TResult OnSerialize();
   MO_OVERRIDE TResult OnUnserialize();
public:
   MO_OVERRIDE TResult LoadConfig(FXmlNode* pConfig);
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
};

MO_NAMESPACE_END

#endif // __MO_CM_MODULE_H__
