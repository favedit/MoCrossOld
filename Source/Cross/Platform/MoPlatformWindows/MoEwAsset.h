#ifndef __MO_EW_ASSET_H__
#define __MO_EW_ASSET_H__
//************************************************************

#ifndef __MO_EW_COMMON_H__
#include "MoEwCommon.h"
#endif // __MO_EW_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>资源目录。</T>
//============================================================
class MO_EW_DECLARE FEwAssetDirectory : public FAssetDirectory
{
   MO_CLASS_DECLARE_INHERITS(FEwAssetDirectory, FAssetDirectory);
protected:
   TCharC* _pFileName;
public:
   FEwAssetDirectory();
   MO_ABSTRACT ~FEwAssetDirectory();
public:
   MO_OVERRIDE TBool Next();
   MO_OVERRIDE TCharC* Current();
public:
   MO_OVERRIDE TResult Close();
};

//============================================================
// <T>资源。</T>
//============================================================
class MO_EW_DECLARE FEwAsset : public FAsset
{
   MO_CLASS_DECLARE_INHERITS(FEwAsset, FAsset);
public:
   FEwAsset();
   MO_ABSTRACT ~FEwAsset();
public:
   MO_OVERRIDE TInt Length();
public:
   MO_OVERRIDE TResult Seek(TInt position);
   MO_OVERRIDE TResult Read(TAny* pData, TInt length);
   MO_OVERRIDE TResult Close();
};

//============================================================
// <T>资源控制台。</T>
//============================================================
class MO_EW_DECLARE FEwAssetConsole : public FAssetConsole
{
   MO_CLASS_DECLARE_INHERITS(FEwAssetConsole, FAssetConsole);
protected:
   TString _directory;
public:
   FEwAssetConsole();
   MO_ABSTRACT ~FEwAssetConsole();
public:
   //------------------------------------------------------------
   // <T>获得路径。</T>
   TCharC* Directory(){
      return _directory;
   }
   //------------------------------------------------------------
   // <T>设置路径。</T>
   void SetDirectory(TCharC* pDirectory){
      _directory = pDirectory;
   }
public:
   MO_OVERRIDE TResult Setup();
public:
   MO_OVERRIDE FAssetDirectory* OpenDirectory(TCharC* pDirectory);
   MO_OVERRIDE FAsset* OpenAsset(TCharC* pName);
   MO_OVERRIDE TResult OpenAssetString(MString* pSource, TCharC* pName);
   MO_OVERRIDE TResult OpenData(FBytes* pData, TCharC* pName);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EW_ASSET_H__
