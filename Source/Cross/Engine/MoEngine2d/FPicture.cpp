#include "MoE2Display.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造图片对象。</T>
//============================================================
FPicture::FPicture(){
   //_objectCd |= EComponent_Renderable;
   _typeCd = EDisplayType_Picture;
   _pMaterial = MO_CREATE(FMaterial);
   //_pMaterial->SetEffectCd(ERenderEffect_2dTexture);
   MO_CLEAR(_pResource);
}

//============================================================
// <T>析构图片对象。</T>
//============================================================
FPicture::~FPicture(){
   MO_DELETE(_pMaterial);
}

//============================================================
// <T>设置位图。</T>
//
// @param pResource 资源
// @return 处理结果
//============================================================
TResult FPicture::SetResource(FPictureResource* pResource){
   MO_CHECK(pResource, return ENull);
   SIntSize2& size = pResource->Size();
   _pResource = pResource;
   //_pMaterial->SetOptionDepth(EFalse);
   //_pMaterial->SetOptionAlpha(ETrue);
   //_pMaterial->TextureSet(pResource->Bitmap()->RenderTexture());
   //// 如果未设置过尺寸，测使用默认尺寸
   //if(_size.IsEmpty()){
   //   _size.Set((TFloat)size.width, (TFloat)size.height);
   //}
   return ESuccess;
}

MO_NAMESPACE_END
