#include "MoCrAsset.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FAssetConsole, FConsole);

//============================================================
// <T>构造资源控制台。</T>
//============================================================
FAssetConsole::FAssetConsole(){
   _pSpaces = MO_CREATE(FAssetSpaceDictionary);
}

//============================================================
// <T>析构资源控制台。</T>
//============================================================
FAssetConsole::~FAssetConsole(){
   MO_DELETE(_pSpaces);
}

//============================================================
// <T>根据名称查找资源空间。</T>
//
// @param pName 名称
// @return 资源空间
//============================================================
FAssetSpace* FAssetConsole::FindSpace(TCharC* pName){
   MO_CHECK(pName, return NULL);
   return _pSpaces->Find(pName);
}

//============================================================
// <T>根据名称解析资源空间。</T>
//
// @param pName 名称
// @return 资源空间
//============================================================
TCharC* FAssetConsole::ParseSpace(TCharC* pName){
   MO_CHECK(pName, return NULL);
   FAssetSpace* pSpace = _pSpaces->Find(pName);
   if(pSpace != NULL){
      return pSpace->Directory();
   }
   return NULL;
}

//============================================================
// <T>注册一个资源空间。</T>
//
// @param pSpace 资源空间
// @return 处理结果
//============================================================
TResult FAssetConsole::RegisterSpace(FAssetSpace* pSpace){
   MO_CHECK(pSpace, return ENull);
   _pSpaces->Set(pSpace->Name(), pSpace);
   return ESuccess;
}

//============================================================
// <T>注册一个资源空间。</T>
//
// @param pName 名称
// @param pDirectory 目录
// @return 处理结果
//============================================================
TResult FAssetConsole::RegisterSpace(TCharC* pName, TCharC* pDirectory){
   MO_CHECK(pName, return ENull);
   MO_CHECK(pDirectory, return ENull);
   FAssetSpace* pSpace = FAssetSpace::InstanceCreate();
   pSpace->SetName(pName);
   pSpace->SetDirectory(pDirectory);
   _pSpaces->Set(pName, pSpace);
   return ESuccess;
}

//============================================================
// <T>注销一个资源空间。</T>
//
// @param pName 名称
// @return 处理结果
//============================================================
TResult FAssetConsole::UnregisterSpace(TCharC* pName){
   MO_CHECK(pName, return ENull);
   _pSpaces->Remove(pName);
   return ESuccess;
}

//============================================================
// <T>根据名称解析资源目录。</T>
//
// @param pResult 结果
// @param pPath 名称
// @return 资源目录
//============================================================
TResult FAssetConsole::ParsePath(MString* pResult, TCharC* pPath){
   MO_CHECK(pResult, return ENull);
   MO_CHECK(pPath, return ENull);
   TStringRefer path = pPath;
   TInt splitIndex = path.IndexOf(':');
   if(splitIndex == ENotFound){
      pResult->Append(pPath);
   }else{
      TFsName spaceName = path.LeftStrC(splitIndex);
      TFsPath subPath = path.MidStrC(splitIndex + 1);
      TCharC* pDirectory = ParseSpace(spaceName);
      MO_FATAL_CHECK(pDirectory, return ENull, TC("Parse space failure. (space=%s)"), (TCharC*)spaceName);
      pResult->Append(pDirectory);
      if(!subPath.StartsWith(TC("/"))){
         pResult->Append('/');
      }
      pResult->Append(subPath);
   }
   return ESuccess;
}

//============================================================
// <T>打开一个资源目录。</T>
//
// @param pDirectory 目录名称
// @return 资源目录
//============================================================
FAssetDirectory* FAssetConsole::OpenDirectory(TCharC* pDirectory){
   return NULL;
}

//============================================================
// <T>关闭一个资源目录。</T>
//
// @param pDirectory 资源目录
// @return 处理结果
//============================================================
TResult FAssetConsole::CloseDirectory(FAssetDirectory* pDirectory){
   MO_DELETE(pDirectory);
   return ESuccess;
}

//============================================================
// <T>打开一个资源。</T>
//
// @param pName 名称
// @return 资源
//============================================================
FAsset* FAssetConsole::OpenAsset(TCharC* pName){
   return NULL;
}

//============================================================
// <T>打开一个资源。</T>
//
// @param pFormat 格式
// @param ... 参数
// @return 资源
//============================================================
FAsset* FAssetConsole::OpenAssetFormat(TCharC* pFormat, ...){
   // 获得名称
   TFsFileName format;
   va_list params;
   va_start(params, pFormat);
   format.AppendFormatParameters(pFormat, params);
   va_end(params);
   // 打开资源流
   return OpenAsset(format);
}

//============================================================
// <T>关闭一个资源。</T>
//
// @param pAsset 资源
// @return 资源
//============================================================
TResult FAssetConsole::CloseAsset(FAsset* pAsset){
   MO_DELETE(pAsset);
   return ESuccess;
}

//============================================================
// <T>打开一个文本数据。</T>
//
// @param pSource 文本数据
// @param pName 名称
// @return 处理结果
//============================================================
TResult FAssetConsole::OpenAssetString(MString* pSource, TCharC* pName){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>打开一个资源流。</T>
//
// @param pName 名称
// @return 资源流
//============================================================
FAssetStream* FAssetConsole::OpenAssetStream(TCharC* pName){
   FAssetStream* pStream = MO_CREATE(FAssetStream);
   TResult resultCd = OpenData(pStream, pName);
   if(resultCd != ESuccess){
      MO_DELETE(pStream);
      return NULL;
   }
   return pStream;
}

//============================================================
// <T>打开一个资源流。</T>
//
// @param pFormat 格式
// @param ... 参数
// @return 资源流
//============================================================
FAssetStream* FAssetConsole::OpenAssetStreamFormat(TCharC* pFormat, ...){
   // 获得名称
   TFsFileName format;
   va_list params;
   va_start(params, pFormat);
   format.AppendFormatParameters(pFormat, params);
   va_end(params);
   // 打开资源流
   return OpenAssetStream(format);
}

//============================================================
// <T>关闭一个资源流。</T>
//
// @param pAssetStream 资源流
// @return 资源
//============================================================
TResult FAssetConsole::CloseAssetStream(FAssetStream* pAssetStream){
   MO_DELETE(pAssetStream);
   return ESuccess;
}

//============================================================
// <T>打开一个资源数据。</T>
//
// @param pData 数据
// @param pName 名称
// @return 处理结果
//============================================================
TResult FAssetConsole::OpenData(FByteStream* pData, TCharC* pName){
   return EUnsupport;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FAssetConsole::Setup(){
   return ESuccess;
}

MO_NAMESPACE_END
