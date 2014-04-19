#ifndef __MO_CR_ENVIRONMENT_H__
#define __MO_CR_ENVIRONMENT_H__
//************************************************************

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

MO_NAMESPACE_BEGIN

#define MO_GM_ENV_RESOURCE_ROOT "resource.root"

//============================================================
// <T>环境。</T>
//============================================================
class MO_CR_DECLARE FEnvironment : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FEnvironment, FInstance);
protected:
   TString _name;
   TString _value;
   TString _data;
public:
   FEnvironment();
   MO_ABSTRACT ~FEnvironment();
public:
   //------------------------------------------------------------
   // <T>判断是否指定名称。</T>
   MO_INLINE TBool IsName(TCharC* pName){
      return _name.Equals(pName);
   }
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>获得内容。</T>
   MO_INLINE TCharC* Value(){
      return _value;
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void SetValue(TCharC* pValue){
      _value = pValue;
   }
   //------------------------------------------------------------
   // <T>获得数据。</T>
   MO_INLINE TCharC* Data(){
      return _data;
   }
   //------------------------------------------------------------
   // <T>设置数据。</T>
   MO_INLINE void SetData(TCharC* pData){
      _data = pData;
   }
};
//------------------------------------------------------------
typedef MO_CR_DECLARE FObjects<FEnvironment*> FEnvironmentCollection;

//============================================================
// <T>环境控制台。</T>
//============================================================
class MO_CR_DECLARE FEnvironmentConsole : public FConsole{
protected:
   FEnvironmentCollection* _pEnvironments;
public:
   FEnvironmentConsole();
   MO_ABSTRACT ~FEnvironmentConsole();
public:
   //------------------------------------------------------------
   // <T>获得工作器集合。</T>
   MO_INLINE FEnvironmentCollection* Environments(){
      return _pEnvironments;
   }
public:
   TBool Exists(TCharC* pName);
   FEnvironment* FindEnvironment(TCharC* pName);
   TCharC* FindValue(TCharC* pName);
   TCharC* ParseValue(TCharC* pName);
public:
   void Register(TCharC* pName, TCharC* pValue);
   void Unregister(TCharC* pName);
public:
public:
   void Clear();
};

//============================================================
// <T>环境管理器。</T>
//============================================================
class MO_CR_DECLARE REnvironmentManager : public RSingleton<FEnvironmentConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_CR_ENVIRONMENT_H__
