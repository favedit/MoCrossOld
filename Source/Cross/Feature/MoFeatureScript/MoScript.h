#ifndef __MO_FC_SCRIPT_H__
#define __MO_FC_SCRIPT_H__
//************************************************************

#ifndef __MO_FC_COMMON_H__
#include "MoFcCommon.h"
#endif // __MO_FC_COMMON_H__

MO_NAMESPACE_BEGIN

#define MO_SCRIPT_METHOD_PARAM_MAXCNT 64

#define MO_SCRIPT_FIELD    "Field"
#define MO_SCRIPT_PROPERTY "Property"
#define MO_SCRIPT_METHOD   "Method"
#define MO_SCRIPT_CLASS    "Class"

class FScriptObject;
class FScriptInstance;
class FScriptField;
class FScriptClass;
class FScriptLibrary;
class FScriptMachine;

//============================================================
// <T>脚本对象。</T>
//============================================================
class MO_FC_DECLARE FScriptObject : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FScriptObject, FInstance);
public:
   FScriptObject();
   MO_ABSTRACT ~FScriptObject();
public:
   MO_ABSTRACT TResult Open();
   MO_ABSTRACT TResult Close();
};
//------------------------------------------------------------
typedef MO_FC_DECLARE GPtrs<FScriptObject> GScriptObjectPtrs;

//============================================================
// <T>脚本实例。</T>
//============================================================
class MO_FC_DECLARE FScriptInstance : public FScriptObject
{
   MO_CLASS_DECLARE_INHERITS(FScriptInstance, FScriptObject);
protected:
   FScriptClass* _pScriptClass;
public:
   FScriptInstance();
   MO_ABSTRACT ~FScriptInstance();
public:
   //------------------------------------------------------------
   // <T>获得类对象。</T>
   MO_INLINE FScriptClass* ScriptClass(){
      return _pScriptClass;
   }
   //------------------------------------------------------------
   // <T>设置类对象。</T>
   MO_INLINE void SetScriptClass(FScriptClass* pScriptClass){
      _pScriptClass = pScriptClass;
   }
public:
   MO_ABSTRACT TResult FieldGet(TCharC* pFileName, TAny* pFieldValue);
   MO_ABSTRACT TResult FieldSet(TCharC* pFileName, TAny* pFieldValue);
   MO_ABSTRACT TResult FieldGetString(TCharC* pFileName, MString* pFieldValue);
   MO_ABSTRACT TResult FieldSetString(TCharC* pFileName, TCharC* pFieldValue);
};
//------------------------------------------------------------
typedef MO_FC_DECLARE GPtrs<FScriptInstance> GScriptInstancePtrs;

//============================================================
// <T>脚本字段。</T>
//============================================================
class MO_FC_DECLARE FScriptField : public FScriptObject
{
   MO_CLASS_DECLARE_INHERITS(FScriptField, FScriptObject);
protected:
   FScriptClass* _pScriptClass;
   TString _name;
public:
   FScriptField();
   MO_ABSTRACT ~FScriptField();
public:
   //------------------------------------------------------------
   // <T>获得类对象。</T>
   MO_INLINE FScriptClass* ScriptClass(){
      return _pScriptClass;
   }
   //------------------------------------------------------------
   // <T>设置类对象。</T>
   MO_INLINE void SetScriptClass(FScriptClass* pScriptClass){
      _pScriptClass = pScriptClass;
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
public:
   MO_ABSTRACT TResult Get(FScriptInstance* pInstance, TAny* pValue);
   MO_ABSTRACT TResult Set(FScriptInstance* pInstance, TAny* pValue);
   MO_ABSTRACT TResult GetString(FScriptInstance* pInstance, MString* pValue);
   MO_ABSTRACT TResult SetString(FScriptInstance* pInstance, TCharC* pValue);
};
//------------------------------------------------------------
typedef MO_FC_DECLARE GPtrDictionary<FScriptField> GScriptFieldDictionary;

//============================================================
// <T>脚本属性。</T>
//============================================================
class MO_FC_DECLARE FScriptProperty : public FScriptObject
{
   MO_CLASS_DECLARE_INHERITS(FScriptProperty, FScriptObject);
protected:
   FScriptClass* _pScriptClass;
   TString _name;
public:
   FScriptProperty();
   MO_ABSTRACT ~FScriptProperty();
public:
   //------------------------------------------------------------
   // <T>获得类对象。</T>
   MO_INLINE FScriptClass* ScriptClass(){
      return _pScriptClass;
   }
   //------------------------------------------------------------
   // <T>设置类对象。</T>
   MO_INLINE void SetScriptClass(FScriptClass* pScriptClass){
      _pScriptClass = pScriptClass;
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
public:
   MO_ABSTRACT TResult Get(FScriptInstance* pInstance, TAny* pValue);
   MO_ABSTRACT TResult Set(FScriptInstance* pInstance, TAny* pValue);
};
//------------------------------------------------------------
typedef MO_FC_DECLARE GPtrDictionary<FScriptProperty> GScriptPropertyDictionary;

//============================================================
// <T>脚本函数。</T>
//============================================================
class MO_FC_DECLARE FScriptMethod : public FScriptObject
{
   MO_CLASS_DECLARE_INHERITS(FScriptMethod, FScriptObject);
protected:
   FScriptClass* _pScriptClass;
   TString _name;
   TString _descriptor;
public:
   FScriptMethod();
   MO_ABSTRACT ~FScriptMethod();
public:
   //------------------------------------------------------------
   // <T>获得类对象。</T>
   MO_INLINE FScriptClass* ScriptClass(){
      return _pScriptClass;
   }
   //------------------------------------------------------------
   // <T>设置类对象。</T>
   MO_INLINE void SetScriptClass(FScriptClass* pScriptClass){
      _pScriptClass = pScriptClass;
   }
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>获得描述。</T>
   MO_INLINE TCharC* Descriptor(){
      return _descriptor;
   }
public:
   void SetDescriptor(TCharC* pDescriptor);
public:
   MO_ABSTRACT TResult InvokeParamaters(FScriptInstance* pInstance, TAny** ppResult, TInt paramCount, TAny** pParameters);
   TResult Invoke(FScriptInstance* pInstance, TAny** ppResult);
   TResult Invoke1(FScriptInstance* pInstance, TAny** ppResult, TAny* pParam1);
   TResult Invoke2(FScriptInstance* pInstance, TAny** ppResult, TAny* pParam1, TAny* pParam2);
   TResult Invoke3(FScriptInstance* pInstance, TAny** ppResult, TAny* pParam1, TAny* pParam2, TAny* pParam3);
   TResult Invoke4(FScriptInstance* pInstance, TAny** ppResult, TAny* pParam1, TAny* pParam2, TAny* pParam3, TAny* pParam4);
   TResult Invoke5(FScriptInstance* pInstance, TAny** ppResult, TAny* pParam1, TAny* pParam2, TAny* pParam3, TAny* pParam4, TAny* pParam5);
   TResult Invoke6(FScriptInstance* pInstance, TAny** ppResult, TAny* pParam1, TAny* pParam2, TAny* pParam3, TAny* pParam4, TAny* pParam5, TAny* pParam6);
};
//------------------------------------------------------------
typedef MO_FC_DECLARE GPtrDictionary<FScriptMethod> GScriptMethodDictionary;

//============================================================
// <T>脚本类对象。</T>
//============================================================
class MO_FC_DECLARE FScriptClass : public FScriptObject
{
   MO_CLASS_DECLARE_INHERITS(FScriptClass, FScriptObject);
protected:
   FScriptLibrary* _pScriptLibrary;
   TString _spaceName;
   TString _className;
   TString _name;
   GScriptFieldDictionary _fields;
   GScriptPropertyDictionary _properties;
   GScriptMethodDictionary _methods;
public:
   FScriptClass();
   MO_ABSTRACT ~FScriptClass();
public:
   //------------------------------------------------------------
   // <T>获得脚本库。</T>
   MO_INLINE FScriptLibrary* ScriptLibrary(){
      return _pScriptLibrary;
   }
   //------------------------------------------------------------
   // <T>设置脚本库。</T>
   MO_INLINE void SetScriptLibrary(FScriptLibrary* pScriptLibrary){
      _pScriptLibrary = pScriptLibrary;
   }
   //------------------------------------------------------------
   // <T>获得空间名称。</T>
   MO_INLINE TCharC* SpaceName(){
      return _spaceName;
   }
   //------------------------------------------------------------
   // <T>获得对象名称。</T>
   MO_INLINE TCharC* ClassName(){
      return _className;
   }
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>获得字段集合。</T>
   MO_INLINE GScriptFieldDictionary& Fields(){
      return _fields;
   }
   //------------------------------------------------------------
   // <T>获得属性集合。</T>
   MO_INLINE GScriptPropertyDictionary& Properties(){
      return _properties;
   }
   //------------------------------------------------------------
   // <T>获得类集合。</T>
   MO_INLINE GScriptMethodDictionary& Methods(){
      return _methods;
   }
public:
   FScriptMachine* ScriptMachine();
   void SetName(TCharC* pName);
public:
   MO_ABSTRACT FScriptField* FindField(TCharC* pName);
   MO_ABSTRACT FScriptProperty* FindProperty(TCharC* pName);
   MO_ABSTRACT FScriptMethod* FindMethod(TCharC* pDescriptor);
public:
   MO_ABSTRACT FScriptInstance* CreateInstance();
};
//------------------------------------------------------------
typedef MO_FC_DECLARE GPtrDictionary<FScriptClass> GScriptClassDictionary;

//============================================================
// <T>脚本库。</T>
//============================================================
class MO_FC_DECLARE FScriptLibrary : public FScriptObject
{
   MO_CLASS_DECLARE_INHERITS(FScriptLibrary, FScriptObject);
protected:
   FScriptMachine* _pScriptMachine;
   TString _name;
   GScriptClassDictionary _classes;
public:
   FScriptLibrary();
   MO_ABSTRACT ~FScriptLibrary();
public:
   //------------------------------------------------------------
   // <T>获得工作机。</T>
   MO_INLINE FScriptMachine* ScriptMachine(){
      return _pScriptMachine;
   }
   //------------------------------------------------------------
   // <T>设置工作机。</T>
   MO_INLINE void SetScriptMachine(FScriptMachine* pScriptMachine){
      _pScriptMachine = pScriptMachine;
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
   // <T>获得类集合。</T>
   MO_INLINE GScriptClassDictionary& Classes(){
      return _classes;
   }
public:
   MO_ABSTRACT FScriptClass* FindClass(TCharC* pSpaceName, TCharC* pClassName);
   MO_ABSTRACT FScriptClass* FindClass(TCharC* pName);
};
//------------------------------------------------------------
typedef MO_FC_DECLARE GPtrDictionary<FScriptLibrary> GScriptLibraryDictionary;

//============================================================
// <T>脚本工作器。</T>
//============================================================
class MO_FC_DECLARE FScriptMachine : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FScriptMachine, FInstance);
protected:
   FClassFactory* _pClassFactory;
   GScriptLibraryDictionary _librarys;
public:
   FScriptMachine();
   MO_ABSTRACT ~FScriptMachine();
public:
   //------------------------------------------------------------
   // <T>获得类工厂。</T>
   MO_INLINE FClassFactory* ClassFactory(){
      return _pClassFactory;
   }
   //------------------------------------------------------------
   // <T>获得脚本库集合。</T>
   MO_INLINE GScriptLibraryDictionary& Librarys(){
      return _librarys;
   }
};
//------------------------------------------------------------
typedef MO_FC_DECLARE GPtrDictionary<FScriptMachine> GScriptMachineDictionary;

//============================================================
// <T>脚本控制台。</T>
//============================================================
class MO_FC_DECLARE FScriptConsole : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FScriptConsole, FConsole);
protected:
   GScriptMachineDictionary _machines;
public:
   FScriptConsole();
   MO_ABSTRACT ~FScriptConsole();
public:
   //------------------------------------------------------------
   // <T>获得工作器集合。</T>
   MO_INLINE GScriptMachineDictionary& Machines(){
      return _machines;
   }
};

//============================================================
// <T>脚本管理器。</T>
//============================================================
class MO_FC_DECLARE RScriptManager : public RSingleton<FScriptConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FC_SCRIPT_H__
