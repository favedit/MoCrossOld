#include "MoEaAsset.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEaAsset, FAsset);

//============================================================
// <T>构造资源。</T>
//============================================================
FEaAsset::FEaAsset(){
   MO_CLEAR(_pNative);
}

//============================================================
// <T>析构资源。</T>
//============================================================
FEaAsset::~FEaAsset(){
   Close();
}

//============================================================
// <T>获得长度。</T>
//============================================================
TInt FEaAsset::Length(){
   TInt length = -1;
   if(_pNative != NULL){
      length = AAsset_getLength(_pNative);
   }
   return length;
}

//============================================================
// <T>定位处理。</T>
//============================================================
TResult FEaAsset::Seek(TInt position){
   if(_pNative != NULL){
      AAsset_seek(_pNative, position, 0);
   }
   return ESuccess;
}

//============================================================
// <T>读取指定长度的数据。</T>
//
// @param pData 数据指针
// @param length 数据长度
// @return 处理结果
//============================================================
TResult FEaAsset::Read(TAny* pData, TInt length){
   if(_pNative != NULL){
      AAsset_read(_pNative, pData, length);
   }
   return ESuccess;
}

//============================================================
// <T>关闭处理。</T>
//============================================================
TResult FEaAsset::Close(){
   if(_pNative != NULL){
      AAsset_close(_pNative);
      MO_CLEAR(_pNative);
   }
   return ESuccess;
}

MO_NAMESPACE_END
