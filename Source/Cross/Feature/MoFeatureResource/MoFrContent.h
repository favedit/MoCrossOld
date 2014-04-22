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
   TTimeTick _updateTick;
public:
   FContent();
   MO_ABSTRACT ~FContent();
public:
   //------------------------------------------------------------
   // <T>获得类型名称。</T>
   MO_INLINE TCharC* TypeName(){
      return _typeName;
   }
   //------------------------------------------------------------
   // <T>设置类型名称。</T>
   MO_INLINE void SetTypeName(TCharC* pTypeName){
      _typeName = pTypeName;
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
   // <T>获得超时时长。</T>
   MO_INLINE TTimeSpan TimeoutSpan(){
      return _timeoutSpan;
   }
   //------------------------------------------------------------
   // <T>设置超时时长。</T>
   MO_INLINE void SetTimeoutSpan(TTimeSpan timeoutSpan){
      _timeoutSpan = timeoutSpan;
   }
   //------------------------------------------------------------
   // <T>获得更新时刻。</T>
   MO_INLINE TTimeTick UpdateTick(){
      return _updateTick;
   }
   //------------------------------------------------------------
   // <T>设置更新时刻。</T>
   MO_INLINE void SetUpdateTick(TTimeTick updateTick){
      _updateTick = updateTick;
   }
public:
   MO_ABSTRACT TBool TestReady();
   MO_ABSTRACT TBool TestValid();
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
public:
   MO_ABSTRACT TResult Process();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtr<FContent>  GContentPtr;
typedef MO_FR_DECLARE GPtrs<FContent> GContentPtrs;

//============================================================
// <T>内容管道。</T>
//============================================================
class MO_FR_DECLARE FContentPipeline : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FContent, FInstance);
protected:
   GByteStreamPtr _stream;
   GContentPtr _content;
public:
   FContentPipeline();
   MO_ABSTRACT ~FContentPipeline();
public:
   //------------------------------------------------------------
   // <T>获得数据流。</T>
   MO_INLINE FByteStream* Stream(){
      return _stream;
   }
   //------------------------------------------------------------
   // <T>设置数据流。</T>
   MO_INLINE void SetStream(FByteStream* pStream){
      _stream = pStream;
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
   MO_ABSTRACT TResult Process();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtr<FContentPipeline> GContentPipelinePtr;

//============================================================
// <T>内容加载器。</T>
//============================================================
class MO_FR_DECLARE FContentLoader : public FLoader
{
   MO_CLASS_DECLARE_INHERITS(FContentLoader, FLoader);
protected:
   GContentPtr _resource;
public:
   FContentLoader();
   MO_ABSTRACT ~FContentLoader();
public:
   //------------------------------------------------------------
   // <T>获得资源。</T>
   MO_INLINE FContent* Content(){
      return _resource;
   }
   //------------------------------------------------------------
   // <T>设置资源。</T>
   MO_INLINE void SetContent(FContent* pContent){
      _resource = pContent;
   }
public:
   MO_OVERRIDE TResult Process();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtrs<FContent> GContentPtrs;

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
   MO_ABSTRACT TResult Load(FContent* pContent);
};

//============================================================
// <T>内容管理器。</T>
//============================================================
class MO_FR_DECLARE RContentManager : RSingleton<FContentConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FR_CONTENT_H__
