#ifndef __MO_CR_ASSET_H__
#define __MO_CR_ASSET_H__
//************************************************************

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>资源空间。</T>
//============================================================
class MO_CR_DECLARE FAssetSpace : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FAssetSpace, FInstance);
protected:
   TString _name;
   TString _directory;
public:
   FAssetSpace();
   MO_ABSTRACT ~FAssetSpace();
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
   // <T>获得目录。</T>
   MO_INLINE TCharC* Directory(){
      return _directory;
   }
   //------------------------------------------------------------
   // <T>设置目录。</T>
   MO_INLINE void SetDirectory(TCharC* directory){
      _directory = directory;
   }
};
//------------------------------------------------------------
typedef MO_CR_DECLARE FDictionary<FAssetSpace*> FAssetSpaceDictionary;

//============================================================
// <T>资源目录。</T>
//============================================================
class MO_CR_DECLARE FAssetDirectory : public FInstance
{
   MO_CLASS_ABSTRACT_DECLARE_INHERITS(FAssetDirectory, FInstance);
public:
   FAssetDirectory();
   MO_ABSTRACT ~FAssetDirectory();
public:
   MO_VIRTUAL TBool Next() = 0;
   MO_VIRTUAL TCharC* Current() = 0;
public:
   MO_VIRTUAL TResult Close() = 0;
};

//============================================================
// <T>资源。</T>
//============================================================
class MO_CR_DECLARE FAsset : public FInstance
{
   MO_CLASS_ABSTRACT_DECLARE_INHERITS(FAsset, FInstance);
protected:
   TString _name;
public:
   FAsset();
   MO_ABSTRACT ~FAsset();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_OVERRIDE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
public:
   MO_VIRTUAL TInt Length() = 0;
public:
   MO_VIRTUAL TResult Seek(TInt position) = 0;
   MO_VIRTUAL TResult Read(TAny* pData, TInt length) = 0;
   MO_VIRTUAL TResult Close() = 0;
};

//============================================================
// <T>资源流。</T>
//============================================================
class MO_CR_DECLARE FAssetStream : public FByteStream
{
   MO_CLASS_DECLARE_INHERITS(FAssetStream, FByteStream);
protected:
   TString _name;
public:
   FAssetStream();
   MO_ABSTRACT ~FAssetStream();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_OVERRIDE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
};
//------------------------------------------------------------
typedef MO_CR_DECLARE GPtr<FAssetStream> GAssetStreamPtr;

//============================================================
// <T>资源控制台。</T>
//============================================================
class MO_CR_DECLARE FAssetConsole : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FAssetConsole, FConsole);
protected:
   FAssetSpaceDictionary* _pSpaces;
public:
   FAssetConsole();
   MO_ABSTRACT ~FAssetConsole();
public:
   MO_ABSTRACT FAssetSpace* FindSpace(TCharC* pName);
   MO_ABSTRACT TCharC* ParseSpace(TCharC* pName);
   MO_ABSTRACT TResult RegisterSpace(FAssetSpace* pSpace);
   MO_ABSTRACT TResult RegisterSpace(TCharC* pName, TCharC* pDirectory);
   MO_ABSTRACT TResult UnregisterSpace(TCharC* pName);
   MO_ABSTRACT TResult ParsePath(MString* pResult, TCharC* pPath);
public:
   MO_ABSTRACT FAssetDirectory* OpenDirectory(TCharC* pDirectory);
   MO_ABSTRACT TResult CloseDirectory(FAssetDirectory* pDirectory);
public:
   MO_ABSTRACT FAsset* OpenAsset(TCharC* pName);
   MO_ABSTRACT FAsset* OpenAssetFormat(TCharC* pFormat, ...);
   MO_ABSTRACT TResult CloseAsset(FAsset* pAsset);
public:
   MO_ABSTRACT TResult OpenAssetString(MString* pSource, TCharC* pName);
   MO_ABSTRACT FAssetStream* OpenAssetStream(TCharC* pName);
   MO_ABSTRACT FAssetStream* OpenAssetStreamFormat(TCharC* pFormat, ...);
   MO_ABSTRACT TResult CloseAssetStream(FAssetStream* pAssetStream);
public:
   MO_ABSTRACT TResult OpenData(FByteStream* pData, TCharC* pName);
public:
   MO_ABSTRACT TResult Setup();
};

//============================================================
// <T>资源管理器。</T>
//============================================================
class MO_CR_DECLARE RAssetManager : public RSingleton<FAssetConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_CR_ASSET_H__
