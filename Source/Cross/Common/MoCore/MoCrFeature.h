#ifndef __MO_CR_FEATURE_H__
#define __MO_CR_FEATURE_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>功能。</T>
//
// @history 140413 MAOCY 创建
//============================================================
class MO_CR_DECLARE FFeature : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FFeature, FConsole);
public:
   FFeature();
   MO_ABSTRACT ~FFeature();
public:
   MO_ABSTRACT TResult Startup();
   MO_ABSTRACT TResult Suspend();
   MO_ABSTRACT TResult Resume();
   MO_ABSTRACT TResult Shutdown();
};
//------------------------------------------------------------
typedef MO_CR_DECLARE GPtrs<FFeature> GFeaturePtrs;

//============================================================
// <T>功能控制台。</T>
//
// @history 140413 MAOCY 创建
//============================================================
class MO_CR_DECLARE FFeatureConsole : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FFeatureConsole, FConsole);
protected:
   FClassCollection* _pClasses;
   GFeaturePtrs _features;
public:
   FFeatureConsole();
   MO_ABSTRACT ~FFeatureConsole();
public:
   TResult Register(FClass* pClass);
   TResult Unregister(FClass* pClass);
public:
   MO_OVERRIDE TResult Construct();
   MO_ABSTRACT TResult Startup();
   MO_ABSTRACT TResult Suspend();
   MO_ABSTRACT TResult Resume();
   MO_ABSTRACT TResult Shutdown();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
// <T>功能管理器。</T>
//
// @history 140413 MAOCY 创建
//============================================================
class MO_CR_DECLARE RFeatureManager : public RSingleton<FFeatureConsole>{
};

MO_NAMESPACE_END

#endif // __MO_CR_FEATURE_H__
