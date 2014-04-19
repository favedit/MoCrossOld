#ifndef __MO_FR_LOADER_H__
#define __MO_FR_LOADER_H__
//************************************************************

#ifndef __MO_FR_COMMON_H__
#include "MoFrCommon.h"
#endif // __MO_FR_COMMON_H__

MO_NAMESPACE_BEGIN

class FResource;
class FFileLoader;
class FLoaderWorker;

//============================================================
// <T>加载器。</T>
//============================================================
class MO_FR_DECLARE FLoader : public FObject{
public:
   FLoader();
   MO_ABSTRACT ~FLoader();
public:
   MO_ABSTRACT TBool Process();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE FList<FLoader*> FLoaderList;

//============================================================
// <T>文件加载器。</T>
//============================================================
class MO_FR_DECLARE FFileLoader : public FLoader{
protected:
   TInt _sourceId;
   FByteFile* _pByteFile;
public:
   FFileLoader();
   MO_ABSTRACT ~FFileLoader();
public:
   //------------------------------------------------------------
   // <T>取得资源编号。</T>
   MO_INLINE TInt SourceId(){
      return _sourceId;
   }
   //------------------------------------------------------------
   // <T>设置资源编号。</T>
   MO_INLINE void SetSourceId(TInt sourceId){
      _sourceId = sourceId;
   }
   //------------------------------------------------------------
   // <T>取得加载数据。</T>
   MO_INLINE FByteStream* LoadData(){
      return _pByteFile;
   }
   ////------------------------------------------------------------
   //// <T>加载文件。</T>
   //MO_INLINE( TBool LoadFile(TChar* pFileName) ){
   //   return _pByteFile->LoadFile(pFileName);
   //}
public:
   TBool LoadFile();
};

//============================================================
// <T>加载器工作器。</T>
//============================================================
class MO_FR_DECLARE FLoaderWorker : public FWorker{
public:
   FLoaderWorker();
   MO_ABSTRACT ~FLoaderWorker();
public:
   MO_OVERRIDE TResult OnProcess();
};

//============================================================
// <T>加载器控制台。</T>
//============================================================
class MO_FR_DECLARE FLoaderConsole : public FConsole{
protected:
   TThreadLocker _locker;
   FLoaderWorker* _pWorker;
   FLoaderList* _pWaitLoaders;
public:
   FLoaderConsole();
   MO_ABSTRACT ~FLoaderConsole();
public:
   //------------------------------------------------------------
   // <T>获得等待的加载器集合。</T>
   MO_INLINE FLoaderList* WaitLoaders(){
      return _pWaitLoaders;
   }
public:
   void Startup();
   void Shutdown();
public:
   void PushWaitLoader(FLoader* pLoader);
   FLoader* PopWaitLoader();
};

//============================================================
// <T>加载器管理器。</T>
//============================================================
class MO_FR_DECLARE RLoaderManager : public RSingleton<FLoaderConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FG_LOADER_H__
