#include "MoFrContent3dTexture.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dTexture, FResource3d);

//============================================================
// <T>构造资源3D纹理。</T>
//============================================================
FRs3dTexture::FRs3dTexture(){
}

//============================================================
// <T>析构资源3D纹理。</T>
//============================================================
FRs3dTexture::~FRs3dTexture(){
   Close();
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dTexture::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取父信息
   FResource3d::Unserialize(pInput);
   // 读取动位图集合
   TInt count = pInput->ReadInt16();
   for(TInt n = 0; n < count; n++){
      FRs3dTextureBitmap* pBitmap = MO_CREATE(FRs3dTextureBitmap);
      pBitmap->Unserialize(pInput);
      _bitmaps.Push(pBitmap);
   }
   return ESuccess;
}

MO_NAMESPACE_END
