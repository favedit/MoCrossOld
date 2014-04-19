#ifndef __MO_EA_ASSET_H__
#define __MO_EA_ASSET_H__
//************************************************************

#ifndef ANDROID_ASSET_MANAGER_JNI_H
#include <android\asset_manager_jni.h>
#endif // ANDROID_ASSET_MANAGER_JNI_H

#ifndef __MO_EA_COMMON_H__
#include "MoEaCommon.h"
#endif // __MO_EA_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>资源目录。</T>
//============================================================
class MO_EA_DECLARE FEaAssetDirectory : public FAssetDirectory
{
   MO_CLASS_DECLARE_INHERITS(FEaAssetDirectory, FAssetDirectory);
protected:
   TCharC* _pFileName;
   AAssetDir* _pNative;
public:
   FEaAssetDirectory();
   MO_ABSTRACT ~FEaAssetDirectory();
public:
   //------------------------------------------------------------
   // <T>获得本地对象。</T>
   MO_INLINE AAssetDir* Native(){
      return _pNative;
   }
   //------------------------------------------------------------
   // <T>设置本地对象。</T>
   MO_INLINE void SetNative(AAssetDir* pNative){
      _pNative = pNative;
   }
public:
   MO_OVERRIDE TBool Next();
   MO_OVERRIDE TCharC* Current();
public:
   MO_OVERRIDE TResult Close();
};

//============================================================
// <T>资源。</T>
//============================================================
class MO_EA_DECLARE FEaAsset : public FAsset
{
   MO_CLASS_DECLARE_INHERITS(FEaAsset, FAsset);
protected:
   AAsset* _pNative;
public:
   FEaAsset();
   MO_ABSTRACT ~FEaAsset();
public:
   //------------------------------------------------------------
   // <T>获得本地对象。</T>
   MO_INLINE AAsset* Native(){
      return _pNative;
   }
   //------------------------------------------------------------
   // <T>设置本地对象。</T>
   MO_INLINE void SetNative(AAsset* pNative){
      _pNative = pNative;
   }
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
class MO_EA_DECLARE FEaAssetConsole : public FAssetConsole
{
   MO_CLASS_DECLARE_INHERITS(FEaAssetConsole, FAssetConsole);
protected:
   AAssetManager* _pManager;
public:
   FEaAssetConsole();
   MO_ABSTRACT ~FEaAssetConsole();
public:
   void Link(JNIEnv* pEnvironment, jobject assetManager);
public:
   MO_OVERRIDE TResult Setup();
public:
   MO_OVERRIDE FAssetDirectory* OpenDirectory(TCharC* pDirectory);
   MO_OVERRIDE FAsset* OpenAsset(TCharC* pName);
   TResult OpenAssetString(MString* pSource, TCharC* pName);
   MO_OVERRIDE TResult OpenData(FBytes* pData, TCharC* pName);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EA_ASSET_H__
