#include "MoEwAsset.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEwAssetDirectory, FAssetDirectory);

//============================================================
// <T>构造资源目录。</T>
//============================================================
FEwAssetDirectory::FEwAssetDirectory(){
   //MO_CLEAR(_pFileName);
   //MO_CLEAR(_pNative);
}

//============================================================
// <T>析构资源目录。</T>
//============================================================
FEwAssetDirectory::~FEwAssetDirectory(){
   Close();
}

//============================================================
// <T>获得下一个内容。</T>
//
// @return 是否获得
//============================================================
TBool FEwAssetDirectory::Next(){
   //if(_pNative != NULL){
   //   _pFileName = AAssetDir_getNextFileName(_pNative);
   //   if(_pFileName != NULL){
   //      return ETrue;
   //   }
   //}
   return EFalse;
}

//============================================================
// <T>获得当前内容。</T>
//
// @return 当前内容
//============================================================
TCharC* FEwAssetDirectory::Current(){
   return _pFileName;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FEwAssetDirectory::Close(){
//   AAssetDir_close(_pNative);
   return ESuccess;
}

MO_NAMESPACE_END
