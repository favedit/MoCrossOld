#include "MoFgMaterial.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造材质控制台。</T>
//============================================================
FMaterialConsole::FMaterialConsole(){
   _pMaterials = MO_CREATE(FMaterialCollection);
}

//============================================================
// <T>析构材质控制台。</T>
//============================================================
FMaterialConsole::~FMaterialConsole(){
   MO_DELETE(_pMaterials);
}

////============================================================
//// <T>根据一张图片查找一个位图材质。</T>
////
//// @param pBitmap 位图
//// @return 位图材质
////============================================================
//FBitmapMaterial* FMaterialConsole::BitmapMaterialFind(FBitmap* pBitmap){
//   MO_ASSERT(pBitmap);
//   TInt count = _pBitmapMaterials->Count();
//   for(TInt n = 0; n < count; n++){
//      FBitmapMaterial* pMaterial = (FBitmapMaterial*)_pBitmapMaterials->Get(n);
//      if(pMaterial->Bitmap() == pBitmap){
//         return pMaterial;
//      }
//   }
//   return NULL;
//}
//
////============================================================
//// <T>根据一张图片同步一个位图材质。</T>
////
//// @param pBitmap 位图
//// @return 位图材质
////============================================================
//FBitmapMaterial* FMaterialConsole::BitmapMaterialSync(FBitmap* pBitmap){
//   MO_ASSERT(pBitmap);
//   // 查找材质
//   FBitmapMaterial* pMaterial = BitmapMaterialFind(pBitmap);
//   // 创建材质
//   if(pMaterial == NULL){
//      pMaterial = MO_CREATE(FBitmapMaterial);
//      pMaterial->SetBitmap(pBitmap);
//      pMaterial->Setup();
//      _pBitmapMaterials->Push(pMaterial);
//   }
//   return pMaterial;
//}

MO_NAMESPACE_END
