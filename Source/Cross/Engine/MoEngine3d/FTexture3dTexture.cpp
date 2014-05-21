#include "MoE3Texture.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTexture3dTexture, FInstance);

//============================================================
// <T>构造资源3D纹理。</T>
//============================================================
FTexture3dTexture::FTexture3dTexture(){
   _pBitmaps = MO_CREATE(FTexture3dBitmapCollection);
}

//============================================================
// <T>析构资源3D纹理。</T>
//============================================================
FTexture3dTexture::~FTexture3dTexture(){
   MO_DELETE(_pBitmaps);
}

//============================================================
// <T>根据代码查找位图。</T>
//
// @param pCode 代码
// @return 位图
//============================================================
FTexture3dBitmap* FTexture3dTexture::FindByCode(TCharC* pCode){
   TInt count = _pBitmaps->Count();
   for(TInt n = 0; n < count; n++){
      FTexture3dBitmap* pBitmap = _pBitmaps->Get(n);
      if(pBitmap != NULL){
         FRs3dTextureBitmap* pBitmapResource = pBitmap->Resource();
         if(pBitmapResource != NULL){
            if(pBitmapResource->IsCode(pCode)){
               return pBitmap;
            }
         }
      }
   }
   return NULL;
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FTexture3dTexture::LoadResource(FRs3dTexture* pResource){
   MO_CHECK(pResource, return ENull);
   _pResource = pResource;
   // 读取动位图集合
   GRs3dTextureBitmapPtrs& bitmapResources = pResource->Bitmaps();
   TInt count = bitmapResources.Count();
   for(TInt n = 0; n < count; n++){
      FRs3dTextureBitmap* pBitmapResource = bitmapResources.Get(n);
      // 创建图形
      FTexture3dBitmap* pBitmap = FTexture3dBitmap::InstanceCreate();
      pBitmap->LoadResource(pBitmapResource);
      _pBitmaps->Push(pBitmap);
   }
   return ESuccess;
}

//============================================================
// <T>打开处理。</T>
//
// @return 处理结果
//============================================================
TResult FTexture3dTexture::Open(){
   TInt count = _pBitmaps->Count();
   for(TInt n = 0; n < count; n++){
      FTexture3dBitmap* pBitmap = _pBitmaps->Get(n);
      MO_CHECK(pBitmap, continue);
      pBitmap->Open();
   }
   return ESuccess;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FTexture3dTexture::Close(){
   TInt count = _pBitmaps->Count();
   for(TInt n = 0; n < count; n++){
      GPtr<FTexture3dBitmap> bitmap = _pBitmaps->Get(n);
      bitmap->Close();
      FTexture3dBitmap::InstanceDelete(bitmap);
   }
   return ESuccess;
}

MO_NAMESPACE_END
