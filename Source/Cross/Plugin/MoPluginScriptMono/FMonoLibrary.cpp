#include "MoPsmScript.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FMonoLibrary, FScriptLibrary);

//============================================================
// <T>构造脚本库。</T>
//============================================================
FMonoLibrary::FMonoLibrary(){
   MO_CLEAR(_pMonoImage);
}

//============================================================
// <T>析构脚本库。</T>
//============================================================
FMonoLibrary::~FMonoLibrary(){
   Close();
}

//============================================================
// <T>析构脚本工作器。</T>
//
// @return 处理结果
//============================================================
TResult FMonoLibrary::Open(){
   TResult resultCd = FScriptLibrary::Open();
   // 打开资源
   FAssetStream* pStream = RAssetManager::Instance().OpenAssetStreamFormat(_name);
   MO_ERROR_CHECK(pStream, return NULL, "Open template stream failure. (name=%s)", (TCharC*)_name);
   // 加载映像
   MonoImageOpenStatus statusCd = MONO_IMAGE_IMAGE_INVALID;
   _pMonoImage = mono_image_open_from_data_full((TChar*)pStream->MemoryC(), pStream->Length(), true, &statusCd, false);
   MO_FATAL_CHECK(_pMonoImage, return EFailure, "Open assembly image failure. (name=%s)", (TCharC*)_name);
   if(statusCd != MONO_IMAGE_OK){
      MO_FATAL("Open assembly image failure. (file_name=%s)", _name);
      return EFailure;
   }
   // 加载装配数据
   MonoImageOpenStatus assemblyStatusCd = MONO_IMAGE_IMAGE_INVALID;
   MonoAssembly* pAssembly = mono_assembly_load_from_full(_pMonoImage, _name, &assemblyStatusCd, false);
   MO_FATAL_CHECK(pAssembly, return EFailure, "Load assembly failure. (name=%s)", (TCharC*)_name);
   if(mono_assembly_get_image(pAssembly) != _pMonoImage){
      MO_FATAL("Check mono image failure. (image=0x%08X)", _pMonoImage);
      return EFailure;
   }
   // 释放资源
   RAssetManager::Instance().CloseAssetStream(pStream);
   return resultCd;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonoLibrary::Close(){
   TResult resultCd = FScriptLibrary::Close();
   if(_pMonoImage != NULL){
      mono_image_close(_pMonoImage);
      MO_CLEAR(_pMonoImage);
   }
   return resultCd;
}

MO_NAMESPACE_END
