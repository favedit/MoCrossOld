#ifndef __MO_FR_CONTENT_H__
#define __MO_FR_CONTENT_H__
//************************************************************

#ifndef __MO_FR_COMMON_H__
#include "MoFrCommon.h"
#endif // __MO_FR_COMMON_H__

#ifndef __MO_FR_LOADER_H__
#include "MoFrLoader.h"
#endif // __MO_FR_LOADER_H__

MO_NAMESPACE_BEGIN

class FContent;
class FContentLoader;
class FContentConsole;

//============================================================
// <T>内容。</T>
//============================================================
class MO_FR_DECLARE FContentObject : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FContentObject, FInstance);
protected:
   TString _typeName;
   TInt _version;
public:
   FContentObject();
   MO_ABSTRACT ~FContentObject();
public:
   MO_SOURCE_GETSET(TCharC*, TypeName, _typeName);
   MO_SOURCE_GETSET(TInt, Version, _version);
public:
   //MO_ABSTRACT TResult Serialize(IDataOutput* pOutput);
   //MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>内容。</T>
//============================================================
class MO_FR_DECLARE FContent : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FContent, FInstance);
protected:
   TString _typeName;
   TString _name;
   TTimeSpan _timeoutSpan;
   TBool _statusReady;
   TTimeTick _updateTick;
public:
   FContent();
   MO_ABSTRACT ~FContent();
public:
   MO_SOURCE_GETSET(TCharC*, TypeName, _typeName);
   MO_SOURCE_GETSET(TCharC*, Name, _name);
   MO_SOURCE_GETSET(TTimeSpan, TimeoutSpan, _timeoutSpan);
   MO_SOURCE_GETSET(TTimeTick, UpdateTick, _updateTick);
public:
   MO_ABSTRACT TBool TestReady();
   MO_ABSTRACT TBool TestValid();
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
   MO_ABSTRACT TResult Complete();
public:
   MO_ABSTRACT TResult Process();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtr<FContent>  GContentPtr;
typedef MO_FR_DECLARE GPtrs<FContent> GContentPtrs;

//============================================================
// <T>内容加载器。</T>
//============================================================
class MO_FR_DECLARE FContentLoader : public FLoader
{
   MO_CLASS_DECLARE_INHERITS(FContentLoader, FLoader);
protected:
   FContentConsole* _pConsole;
   TString _assetName;
   GContentPtr _content;
public:
   FContentLoader();
   MO_ABSTRACT ~FContentLoader();
public:
   //------------------------------------------------------------
   // <T>获得控制台。</T>
   MO_INLINE FContentConsole* Console(){
      return _pConsole;
   }
   //------------------------------------------------------------
   // <T>设置控制台。</T>
   MO_INLINE void SetConsole(FContentConsole* pConsole){
      _pConsole = pConsole;
   }
   //------------------------------------------------------------
   // <T>获得资源名称。</T>
   MO_INLINE TCharC* AssetName(){
      return _assetName;
   }
   //------------------------------------------------------------
   // <T>设置资源名称。</T>
   MO_INLINE void SetAssetName(TCharC* pAssetName){
      _assetName = pAssetName;
   }
   //------------------------------------------------------------
   // <T>获得内容。</T>
   MO_INLINE FContent* Content(){
      return _content;
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void SetContent(FContent* pContent){
      _content = pContent;
   }
public:
   MO_OVERRIDE TResult Process();
};

//============================================================
// <T>内容控制台。</T>
//============================================================
class MO_FR_DECLARE FContentConsole : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FContentConsole, FConsole);
protected:
   GClassInstanceFactoryPtr _pipelineFactory;
public:
   FContentConsole();
   MO_ABSTRACT ~FContentConsole();
public:
   //------------------------------------------------------------
   // <T>获得管道工厂。</T>
   MO_INLINE FClassInstanceFactory* PipelineFactory(){
      return _pipelineFactory;
   }
public:
   MO_ABSTRACT TResult PushLoader(FContentLoader* pLoader);
};

//============================================================
// <T>内容管理器。</T>
//============================================================
class MO_FR_DECLARE RContentManager : public RSingleton<FContentConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FR_CONTENT_H__
