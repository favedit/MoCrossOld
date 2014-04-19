#ifndef __MO_FC_SCRIPT_H__
#define __MO_FC_SCRIPT_H__
//************************************************************

#ifndef __MO_FC_COMMON_H__
#include "MoFcCommon.h"
#endif // __MO_FC_COMMON_H__

MO_NAMESPACE_BEGIN

class FResource;
class FFileLoader;
class FLoaderWorker;

//============================================================
// <T>脚本对象。</T>
//============================================================
class MO_FC_DECLARE FScriptObject : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FScriptObject, FInstance);
public:
   FScriptObject();
   MO_ABSTRACT ~FScriptObject();
};
//------------------------------------------------------------
typedef MO_FC_DECLARE GPtrs<FScriptObject> GScriptObjectPtrs;
typedef MO_FC_DECLARE FDictionary<FScriptObject*> FScriptObjectDictionary;

//============================================================
// <T>加载器。</T>
//============================================================
class MO_FC_DECLARE FScriptMachine : public FObject{
public:
   FScriptMachine();
   MO_ABSTRACT ~FScriptMachine();
public:
   MO_ABSTRACT TBool Process();
};
//------------------------------------------------------------
typedef MO_FC_DECLARE FDictionary<FScriptMachine*> FScriptMachineDictionary;

//============================================================
// <T>脚本控制台。</T>
//============================================================
class MO_FC_DECLARE FScriptConsole : public FConsole{
protected:
   FScriptMachineDictionary* _pMachineDictionary;
public:
   FScriptConsole();
   MO_ABSTRACT ~FScriptConsole();
};

//============================================================
// <T>脚本管理器。</T>
//============================================================
class MO_FC_DECLARE RScriptManager : public RSingleton<FScriptConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FC_SCRIPT_H__
